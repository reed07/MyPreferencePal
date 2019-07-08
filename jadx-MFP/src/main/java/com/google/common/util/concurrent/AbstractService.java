package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Monitor.Guard;
import com.google.common.util.concurrent.Service.Listener;
import com.google.common.util.concurrent.Service.State;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtIncompatible
@Beta
public abstract class AbstractService implements Service {
    private static final Event<Listener> RUNNING_EVENT = new Event<Listener>() {
        public String toString() {
            return "running()";
        }

        public void call(Listener listener) {
            listener.running();
        }
    };
    private static final Event<Listener> STARTING_EVENT = new Event<Listener>() {
        public String toString() {
            return "starting()";
        }

        public void call(Listener listener) {
            listener.starting();
        }
    };
    private static final Event<Listener> STOPPING_FROM_RUNNING_EVENT = stoppingEvent(State.RUNNING);
    private static final Event<Listener> STOPPING_FROM_STARTING_EVENT = stoppingEvent(State.STARTING);
    private static final Event<Listener> TERMINATED_FROM_NEW_EVENT = terminatedEvent(State.NEW);
    private static final Event<Listener> TERMINATED_FROM_RUNNING_EVENT = terminatedEvent(State.RUNNING);
    private static final Event<Listener> TERMINATED_FROM_STOPPING_EVENT = terminatedEvent(State.STOPPING);
    private final Guard hasReachedRunning = new HasReachedRunningGuard();
    private final Guard isStartable = new IsStartableGuard();
    private final Guard isStoppable = new IsStoppableGuard();
    private final Guard isStopped = new IsStoppedGuard();
    private final ListenerCallQueue<Listener> listeners = new ListenerCallQueue<>();
    /* access modifiers changed from: private */
    public final Monitor monitor = new Monitor();
    private volatile StateSnapshot snapshot = new StateSnapshot(State.NEW);

    private final class HasReachedRunningGuard extends Guard {
        HasReachedRunningGuard() {
            super(AbstractService.this.monitor);
        }

        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(State.RUNNING) >= 0;
        }
    }

    private final class IsStartableGuard extends Guard {
        IsStartableGuard() {
            super(AbstractService.this.monitor);
        }

        public boolean isSatisfied() {
            return AbstractService.this.state() == State.NEW;
        }
    }

    private final class IsStoppableGuard extends Guard {
        IsStoppableGuard() {
            super(AbstractService.this.monitor);
        }

        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(State.RUNNING) <= 0;
        }
    }

    private final class IsStoppedGuard extends Guard {
        IsStoppedGuard() {
            super(AbstractService.this.monitor);
        }

        public boolean isSatisfied() {
            return AbstractService.this.state().isTerminal();
        }
    }

    private static final class StateSnapshot {
        @NullableDecl
        final Throwable failure;
        final boolean shutdownWhenStartupFinishes;
        final State state;

        StateSnapshot(State state2) {
            this(state2, false, null);
        }

        StateSnapshot(State state2, boolean z, @NullableDecl Throwable th) {
            boolean z2 = false;
            Preconditions.checkArgument(!z || state2 == State.STARTING, "shutdownWhenStartupFinishes can only be set if state is STARTING. Got %s instead.", (Object) state2);
            if (!((th != null) ^ (state2 == State.FAILED))) {
                z2 = true;
            }
            Preconditions.checkArgument(z2, "A failure cause should be set if and only if the state is failed.  Got %s and %s instead.", (Object) state2, (Object) th);
            this.state = state2;
            this.shutdownWhenStartupFinishes = z;
            this.failure = th;
        }

        /* access modifiers changed from: 0000 */
        public State externalState() {
            if (!this.shutdownWhenStartupFinishes || this.state != State.STARTING) {
                return this.state;
            }
            return State.STOPPING;
        }

        /* access modifiers changed from: 0000 */
        public Throwable failureCause() {
            Preconditions.checkState(this.state == State.FAILED, "failureCause() is only valid if the service has failed, service is %s", (Object) this.state);
            return this.failure;
        }
    }

    /* access modifiers changed from: protected */
    @ForOverride
    public abstract void doStart();

    /* access modifiers changed from: protected */
    @ForOverride
    public abstract void doStop();

    private static Event<Listener> terminatedEvent(final State state) {
        return new Event<Listener>() {
            public void call(Listener listener) {
                listener.terminated(state);
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("terminated({from = ");
                sb.append(state);
                sb.append("})");
                return sb.toString();
            }
        };
    }

    private static Event<Listener> stoppingEvent(final State state) {
        return new Event<Listener>() {
            public void call(Listener listener) {
                listener.stopping(state);
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("stopping({from = ");
                sb.append(state);
                sb.append("})");
                return sb.toString();
            }
        };
    }

    protected AbstractService() {
    }

    @CanIgnoreReturnValue
    public final Service startAsync() {
        if (this.monitor.enterIf(this.isStartable)) {
            try {
                this.snapshot = new StateSnapshot(State.STARTING);
                enqueueStartingEvent();
                doStart();
            } catch (Throwable th) {
                this.monitor.leave();
                dispatchListenerEvents();
                throw th;
            }
            this.monitor.leave();
            dispatchListenerEvents();
            return this;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Service ");
        sb.append(this);
        sb.append(" has already been started");
        throw new IllegalStateException(sb.toString());
    }

    @CanIgnoreReturnValue
    public final Service stopAsync() {
        if (this.monitor.enterIf(this.isStoppable)) {
            try {
                State state = state();
                switch (state) {
                    case NEW:
                        this.snapshot = new StateSnapshot(State.TERMINATED);
                        enqueueTerminatedEvent(State.NEW);
                        break;
                    case STARTING:
                        this.snapshot = new StateSnapshot(State.STARTING, true, null);
                        enqueueStoppingEvent(State.STARTING);
                        break;
                    case RUNNING:
                        this.snapshot = new StateSnapshot(State.STOPPING);
                        enqueueStoppingEvent(State.RUNNING);
                        doStop();
                        break;
                    case STOPPING:
                    case TERMINATED:
                    case FAILED:
                        StringBuilder sb = new StringBuilder();
                        sb.append("isStoppable is incorrectly implemented, saw: ");
                        sb.append(state);
                        throw new AssertionError(sb.toString());
                    default:
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Unexpected state: ");
                        sb2.append(state);
                        throw new AssertionError(sb2.toString());
                }
            } catch (Throwable th) {
                this.monitor.leave();
                dispatchListenerEvents();
                throw th;
            }
            this.monitor.leave();
            dispatchListenerEvents();
        }
        return this;
    }

    public final void awaitRunning() {
        this.monitor.enterWhenUninterruptibly(this.hasReachedRunning);
        try {
            checkCurrentState(State.RUNNING);
        } finally {
            this.monitor.leave();
        }
    }

    public final void awaitRunning(long j, TimeUnit timeUnit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.hasReachedRunning, j, timeUnit)) {
            try {
                checkCurrentState(State.RUNNING);
            } finally {
                this.monitor.leave();
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Timed out waiting for ");
            sb.append(this);
            sb.append(" to reach the RUNNING state.");
            throw new TimeoutException(sb.toString());
        }
    }

    public final void awaitTerminated() {
        this.monitor.enterWhenUninterruptibly(this.isStopped);
        try {
            checkCurrentState(State.TERMINATED);
        } finally {
            this.monitor.leave();
        }
    }

    public final void awaitTerminated(long j, TimeUnit timeUnit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.isStopped, j, timeUnit)) {
            try {
                checkCurrentState(State.TERMINATED);
            } finally {
                this.monitor.leave();
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Timed out waiting for ");
            sb.append(this);
            sb.append(" to reach a terminal state. Current state: ");
            sb.append(state());
            throw new TimeoutException(sb.toString());
        }
    }

    @GuardedBy("monitor")
    private void checkCurrentState(State state) {
        State state2 = state();
        if (state2 == state) {
            return;
        }
        if (state2 == State.FAILED) {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected the service ");
            sb.append(this);
            sb.append(" to be ");
            sb.append(state);
            sb.append(", but the service has FAILED");
            throw new IllegalStateException(sb.toString(), failureCause());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Expected the service ");
        sb2.append(this);
        sb2.append(" to be ");
        sb2.append(state);
        sb2.append(", but was ");
        sb2.append(state2);
        throw new IllegalStateException(sb2.toString());
    }

    /* access modifiers changed from: protected */
    public final void notifyStarted() {
        this.monitor.enter();
        try {
            if (this.snapshot.state == State.STARTING) {
                if (this.snapshot.shutdownWhenStartupFinishes) {
                    this.snapshot = new StateSnapshot(State.STOPPING);
                    doStop();
                } else {
                    this.snapshot = new StateSnapshot(State.RUNNING);
                    enqueueRunningEvent();
                }
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Cannot notifyStarted() when the service is ");
            sb.append(this.snapshot.state);
            IllegalStateException illegalStateException = new IllegalStateException(sb.toString());
            notifyFailed(illegalStateException);
            throw illegalStateException;
        } finally {
            this.monitor.leave();
            dispatchListenerEvents();
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyStopped() {
        this.monitor.enter();
        try {
            State state = this.snapshot.state;
            if (state != State.STOPPING) {
                if (state != State.RUNNING) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Cannot notifyStopped() when the service is ");
                    sb.append(state);
                    IllegalStateException illegalStateException = new IllegalStateException(sb.toString());
                    notifyFailed(illegalStateException);
                    throw illegalStateException;
                }
            }
            this.snapshot = new StateSnapshot(State.TERMINATED);
            enqueueTerminatedEvent(state);
        } finally {
            this.monitor.leave();
            dispatchListenerEvents();
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyFailed(Throwable th) {
        Preconditions.checkNotNull(th);
        this.monitor.enter();
        try {
            State state = state();
            switch (state) {
                case NEW:
                case TERMINATED:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed while in state:");
                    sb.append(state);
                    throw new IllegalStateException(sb.toString(), th);
                case STARTING:
                case RUNNING:
                case STOPPING:
                    this.snapshot = new StateSnapshot(State.FAILED, false, th);
                    enqueueFailedEvent(state, th);
                    break;
                case FAILED:
                    break;
                default:
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Unexpected state: ");
                    sb2.append(state);
                    throw new AssertionError(sb2.toString());
            }
        } finally {
            this.monitor.leave();
            dispatchListenerEvents();
        }
    }

    public final boolean isRunning() {
        return state() == State.RUNNING;
    }

    public final State state() {
        return this.snapshot.externalState();
    }

    public final Throwable failureCause() {
        return this.snapshot.failureCause();
    }

    public final void addListener(Listener listener, Executor executor) {
        this.listeners.addListener(listener, executor);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append(state());
        sb.append("]");
        return sb.toString();
    }

    private void dispatchListenerEvents() {
        if (!this.monitor.isOccupiedByCurrentThread()) {
            this.listeners.dispatch();
        }
    }

    private void enqueueStartingEvent() {
        this.listeners.enqueue(STARTING_EVENT);
    }

    private void enqueueRunningEvent() {
        this.listeners.enqueue(RUNNING_EVENT);
    }

    private void enqueueStoppingEvent(State state) {
        if (state == State.STARTING) {
            this.listeners.enqueue(STOPPING_FROM_STARTING_EVENT);
        } else if (state == State.RUNNING) {
            this.listeners.enqueue(STOPPING_FROM_RUNNING_EVENT);
        } else {
            throw new AssertionError();
        }
    }

    private void enqueueTerminatedEvent(State state) {
        int i = AnonymousClass6.$SwitchMap$com$google$common$util$concurrent$Service$State[state.ordinal()];
        if (i != 1) {
            switch (i) {
                case 3:
                    this.listeners.enqueue(TERMINATED_FROM_RUNNING_EVENT);
                    return;
                case 4:
                    this.listeners.enqueue(TERMINATED_FROM_STOPPING_EVENT);
                    return;
                default:
                    throw new AssertionError();
            }
        } else {
            this.listeners.enqueue(TERMINATED_FROM_NEW_EVENT);
        }
    }

    private void enqueueFailedEvent(final State state, final Throwable th) {
        this.listeners.enqueue(new Event<Listener>() {
            public void call(Listener listener) {
                listener.failed(state, th);
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("failed({from = ");
                sb.append(state);
                sb.append(", cause = ");
                sb.append(th);
                sb.append("})");
                return sb.toString();
            }
        });
    }
}
