package io.grpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Preconditions;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import io.grpc.ClientStreamTracer.Factory;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@CheckReturnValue
@Immutable
public final class CallOptions {
    public static final CallOptions DEFAULT = new CallOptions();
    @Nullable
    private String authority;
    @Nullable
    private String compressorName;
    @Nullable
    private CallCredentials credentials;
    private Object[][] customOptions = ((Object[][]) Array.newInstance(Object.class, new int[]{0, 2}));
    private Deadline deadline;
    private Executor executor;
    @Nullable
    private Integer maxInboundMessageSize;
    @Nullable
    private Integer maxOutboundMessageSize;
    private List<Factory> streamTracerFactories = Collections.emptyList();
    private boolean waitForReady;

    public static final class Key<T> {
        private final String debugString;
        /* access modifiers changed from: private */
        public final T defaultValue;

        private Key(String str, T t) {
            this.debugString = str;
            this.defaultValue = t;
        }

        public String toString() {
            return this.debugString;
        }

        public static <T> Key<T> create(String str) {
            Preconditions.checkNotNull(str, "debugString");
            return new Key<>(str, null);
        }
    }

    public CallOptions withCallCredentials(@Nullable CallCredentials callCredentials) {
        CallOptions callOptions = new CallOptions(this);
        callOptions.credentials = callCredentials;
        return callOptions;
    }

    @ExperimentalApi
    public CallOptions withCompression(@Nullable String str) {
        CallOptions callOptions = new CallOptions(this);
        callOptions.compressorName = str;
        return callOptions;
    }

    public CallOptions withDeadline(@Nullable Deadline deadline2) {
        CallOptions callOptions = new CallOptions(this);
        callOptions.deadline = deadline2;
        return callOptions;
    }

    public CallOptions withDeadlineAfter(long j, TimeUnit timeUnit) {
        return withDeadline(Deadline.after(j, timeUnit));
    }

    @Nullable
    public Deadline getDeadline() {
        return this.deadline;
    }

    public CallOptions withWaitForReady() {
        CallOptions callOptions = new CallOptions(this);
        callOptions.waitForReady = true;
        return callOptions;
    }

    public CallOptions withoutWaitForReady() {
        CallOptions callOptions = new CallOptions(this);
        callOptions.waitForReady = false;
        return callOptions;
    }

    @ExperimentalApi
    @Nullable
    public String getCompressor() {
        return this.compressorName;
    }

    @ExperimentalApi
    @Nullable
    public String getAuthority() {
        return this.authority;
    }

    @Nullable
    public CallCredentials getCredentials() {
        return this.credentials;
    }

    public CallOptions withExecutor(Executor executor2) {
        CallOptions callOptions = new CallOptions(this);
        callOptions.executor = executor2;
        return callOptions;
    }

    @ExperimentalApi
    public CallOptions withStreamTracerFactory(Factory factory) {
        CallOptions callOptions = new CallOptions(this);
        ArrayList arrayList = new ArrayList(this.streamTracerFactories.size() + 1);
        arrayList.addAll(this.streamTracerFactories);
        arrayList.add(factory);
        callOptions.streamTracerFactories = Collections.unmodifiableList(arrayList);
        return callOptions;
    }

    @ExperimentalApi
    public List<Factory> getStreamTracerFactories() {
        return this.streamTracerFactories;
    }

    public <T> CallOptions withOption(Key<T> key, T t) {
        Preconditions.checkNotNull(key, IpcUtil.KEY_CODE);
        Preconditions.checkNotNull(t, "value");
        CallOptions callOptions = new CallOptions(this);
        int i = 0;
        while (true) {
            Object[][] objArr = this.customOptions;
            if (i >= objArr.length) {
                i = -1;
                break;
            } else if (key.equals(objArr[i][0])) {
                break;
            } else {
                i++;
            }
        }
        callOptions.customOptions = (Object[][]) Array.newInstance(Object.class, new int[]{this.customOptions.length + (i == -1 ? 1 : 0), 2});
        Object[][] objArr2 = this.customOptions;
        System.arraycopy(objArr2, 0, callOptions.customOptions, 0, objArr2.length);
        if (i == -1) {
            callOptions.customOptions[this.customOptions.length] = new Object[]{key, t};
        } else {
            callOptions.customOptions[i][1] = t;
        }
        return callOptions;
    }

    @ExperimentalApi
    public <T> T getOption(Key<T> key) {
        Preconditions.checkNotNull(key, IpcUtil.KEY_CODE);
        int i = 0;
        while (true) {
            Object[][] objArr = this.customOptions;
            if (i >= objArr.length) {
                return key.defaultValue;
            }
            if (key.equals(objArr[i][0])) {
                return this.customOptions[i][1];
            }
            i++;
        }
    }

    @Nullable
    public Executor getExecutor() {
        return this.executor;
    }

    private CallOptions() {
    }

    public boolean isWaitForReady() {
        return this.waitForReady;
    }

    @ExperimentalApi
    public CallOptions withMaxInboundMessageSize(int i) {
        Preconditions.checkArgument(i >= 0, "invalid maxsize %s", i);
        CallOptions callOptions = new CallOptions(this);
        callOptions.maxInboundMessageSize = Integer.valueOf(i);
        return callOptions;
    }

    @ExperimentalApi
    public CallOptions withMaxOutboundMessageSize(int i) {
        Preconditions.checkArgument(i >= 0, "invalid maxsize %s", i);
        CallOptions callOptions = new CallOptions(this);
        callOptions.maxOutboundMessageSize = Integer.valueOf(i);
        return callOptions;
    }

    @ExperimentalApi
    @Nullable
    public Integer getMaxInboundMessageSize() {
        return this.maxInboundMessageSize;
    }

    @ExperimentalApi
    @Nullable
    public Integer getMaxOutboundMessageSize() {
        return this.maxOutboundMessageSize;
    }

    private CallOptions(CallOptions callOptions) {
        this.deadline = callOptions.deadline;
        this.authority = callOptions.authority;
        this.credentials = callOptions.credentials;
        this.executor = callOptions.executor;
        this.compressorName = callOptions.compressorName;
        this.customOptions = callOptions.customOptions;
        this.waitForReady = callOptions.waitForReady;
        this.maxInboundMessageSize = callOptions.maxInboundMessageSize;
        this.maxOutboundMessageSize = callOptions.maxOutboundMessageSize;
        this.streamTracerFactories = callOptions.streamTracerFactories;
    }

    public String toString() {
        ToStringHelper add = MoreObjects.toStringHelper((Object) this).add("deadline", (Object) this.deadline).add("authority", (Object) this.authority).add("callCredentials", (Object) this.credentials);
        String str = "executor";
        Executor executor2 = this.executor;
        return add.add(str, (Object) executor2 != null ? executor2.getClass() : null).add("compressorName", (Object) this.compressorName).add("customOptions", (Object) Arrays.deepToString(this.customOptions)).add("waitForReady", isWaitForReady()).add("maxInboundMessageSize", (Object) this.maxInboundMessageSize).add("maxOutboundMessageSize", (Object) this.maxOutboundMessageSize).add("streamTracerFactories", (Object) this.streamTracerFactories).toString();
    }
}
