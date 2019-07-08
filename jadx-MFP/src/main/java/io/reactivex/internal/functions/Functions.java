package io.reactivex.internal.functions;

import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.LongConsumer;
import io.reactivex.functions.Predicate;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import org.reactivestreams.Subscription;

public final class Functions {
    static final Predicate<Object> ALWAYS_FALSE = new FalsePredicate();
    static final Predicate<Object> ALWAYS_TRUE = new TruePredicate();
    public static final Action EMPTY_ACTION = new EmptyAction();
    static final Consumer<Object> EMPTY_CONSUMER = new EmptyConsumer();
    public static final LongConsumer EMPTY_LONG_CONSUMER = new EmptyLongConsumer();
    public static final Runnable EMPTY_RUNNABLE = new EmptyRunnable();
    public static final Consumer<Throwable> ERROR_CONSUMER = new ErrorConsumer();
    static final Function<Object, Object> IDENTITY = new Identity();
    static final Comparator<Object> NATURAL_COMPARATOR = new NaturalObjectComparator();
    static final Callable<Object> NULL_SUPPLIER = new NullCallable();
    public static final Consumer<Throwable> ON_ERROR_MISSING = new OnErrorMissingConsumer();
    public static final Consumer<Subscription> REQUEST_MAX = new MaxRequestSubscription();

    static final class Array2Func<T1, T2, R> implements Function<Object[], R> {
        final BiFunction<? super T1, ? super T2, ? extends R> f;

        Array2Func(BiFunction<? super T1, ? super T2, ? extends R> biFunction) {
            this.f = biFunction;
        }

        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 2) {
                return this.f.apply(objArr[0], objArr[1]);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Array of size 2 expected but got ");
            sb.append(objArr.length);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    static final class Array3Func<T1, T2, T3, R> implements Function<Object[], R> {
        final Function3<T1, T2, T3, R> f;

        Array3Func(Function3<T1, T2, T3, R> function3) {
            this.f = function3;
        }

        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 3) {
                return this.f.apply(objArr[0], objArr[1], objArr[2]);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Array of size 3 expected but got ");
            sb.append(objArr.length);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    static final class ArrayListCapacityCallable<T> implements Callable<List<T>> {
        final int capacity;

        ArrayListCapacityCallable(int i) {
            this.capacity = i;
        }

        public List<T> call() throws Exception {
            return new ArrayList(this.capacity);
        }
    }

    public static class BoundedConsumer implements Consumer<Subscription> {
        final int bufferSize;

        public void accept(Subscription subscription) throws Exception {
            subscription.request((long) this.bufferSize);
        }
    }

    static final class EmptyAction implements Action {
        public void run() {
        }

        public String toString() {
            return "EmptyAction";
        }

        EmptyAction() {
        }
    }

    static final class EmptyConsumer implements Consumer<Object> {
        public void accept(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }

        EmptyConsumer() {
        }
    }

    static final class EmptyLongConsumer implements LongConsumer {
        public void accept(long j) {
        }

        EmptyLongConsumer() {
        }
    }

    static final class EmptyRunnable implements Runnable {
        public void run() {
        }

        public String toString() {
            return "EmptyRunnable";
        }

        EmptyRunnable() {
        }
    }

    static final class ErrorConsumer implements Consumer<Throwable> {
        ErrorConsumer() {
        }

        public void accept(Throwable th) {
            RxJavaPlugins.onError(th);
        }
    }

    static final class FalsePredicate implements Predicate<Object> {
        public boolean test(Object obj) {
            return false;
        }

        FalsePredicate() {
        }
    }

    static final class Identity implements Function<Object, Object> {
        public Object apply(Object obj) {
            return obj;
        }

        public String toString() {
            return "IdentityFunction";
        }

        Identity() {
        }
    }

    static final class JustValue<T, U> implements Function<T, U>, Callable<U> {
        final U value;

        JustValue(U u) {
            this.value = u;
        }

        public U call() throws Exception {
            return this.value;
        }

        public U apply(T t) throws Exception {
            return this.value;
        }
    }

    static final class MaxRequestSubscription implements Consumer<Subscription> {
        MaxRequestSubscription() {
        }

        public void accept(Subscription subscription) throws Exception {
            subscription.request(Long.MAX_VALUE);
        }
    }

    static final class NaturalObjectComparator implements Comparator<Object> {
        NaturalObjectComparator() {
        }

        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    static final class NullCallable implements Callable<Object> {
        public Object call() {
            return null;
        }

        NullCallable() {
        }
    }

    static final class OnErrorMissingConsumer implements Consumer<Throwable> {
        OnErrorMissingConsumer() {
        }

        public void accept(Throwable th) {
            RxJavaPlugins.onError(new OnErrorNotImplementedException(th));
        }
    }

    static final class TruePredicate implements Predicate<Object> {
        public boolean test(Object obj) {
            return true;
        }

        TruePredicate() {
        }
    }

    private Functions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T1, T2, R> Function<Object[], R> toFunction(BiFunction<? super T1, ? super T2, ? extends R> biFunction) {
        ObjectHelper.requireNonNull(biFunction, "f is null");
        return new Array2Func(biFunction);
    }

    public static <T1, T2, T3, R> Function<Object[], R> toFunction(Function3<T1, T2, T3, R> function3) {
        ObjectHelper.requireNonNull(function3, "f is null");
        return new Array3Func(function3);
    }

    public static <T> Function<T, T> identity() {
        return IDENTITY;
    }

    public static <T> Consumer<T> emptyConsumer() {
        return EMPTY_CONSUMER;
    }

    public static <T> Callable<T> justCallable(T t) {
        return new JustValue(t);
    }

    public static <T> Callable<List<T>> createArrayList(int i) {
        return new ArrayListCapacityCallable(i);
    }
}
