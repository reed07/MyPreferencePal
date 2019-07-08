package com.bumptech.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools.Pool;
import android.util.Log;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.Engine.LoadStatus;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.DrawableDecoderCompat;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.FactoryPools;
import com.bumptech.glide.util.pool.FactoryPools.Factory;
import com.bumptech.glide.util.pool.FactoryPools.Poolable;
import com.bumptech.glide.util.pool.StateVerifier;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.util.List;

public final class SingleRequest<R> implements Request, ResourceCallback, SizeReadyCallback, Poolable {
    private static final boolean IS_VERBOSE_LOGGABLE = Log.isLoggable("Request", 2);
    private static final Pool<SingleRequest<?>> POOL = FactoryPools.simple(150, new Factory<SingleRequest<?>>() {
        public SingleRequest<?> create() {
            return new SingleRequest<>();
        }
    });
    private TransitionFactory<? super R> animationFactory;
    private Context context;
    private Engine engine;
    private Drawable errorDrawable;
    private Drawable fallbackDrawable;
    private GlideContext glideContext;
    private int height;
    private boolean isCallingCallbacks;
    private LoadStatus loadStatus;
    @Nullable
    private Object model;
    private int overrideHeight;
    private int overrideWidth;
    private Drawable placeholderDrawable;
    private Priority priority;
    private RequestCoordinator requestCoordinator;
    @Nullable
    private List<RequestListener<R>> requestListeners;
    private RequestOptions requestOptions;
    private Resource<R> resource;
    private long startTime;
    private final StateVerifier stateVerifier;
    private Status status;
    @Nullable
    private final String tag;
    private Target<R> target;
    @Nullable
    private RequestListener<R> targetListener;
    private Class<R> transcodeClass;
    private int width;

    private enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CLEARED
    }

    public static <R> SingleRequest<R> obtain(Context context2, GlideContext glideContext2, Object obj, Class<R> cls, RequestOptions requestOptions2, int i, int i2, Priority priority2, Target<R> target2, RequestListener<R> requestListener, @Nullable List<RequestListener<R>> list, RequestCoordinator requestCoordinator2, Engine engine2, TransitionFactory<? super R> transitionFactory) {
        SingleRequest<R> singleRequest = (SingleRequest) POOL.acquire();
        if (singleRequest == null) {
            singleRequest = new SingleRequest<>();
        }
        singleRequest.init(context2, glideContext2, obj, cls, requestOptions2, i, i2, priority2, target2, requestListener, list, requestCoordinator2, engine2, transitionFactory);
        return singleRequest;
    }

    SingleRequest() {
        this.tag = IS_VERBOSE_LOGGABLE ? String.valueOf(super.hashCode()) : null;
        this.stateVerifier = StateVerifier.newInstance();
    }

    private void init(Context context2, GlideContext glideContext2, Object obj, Class<R> cls, RequestOptions requestOptions2, int i, int i2, Priority priority2, Target<R> target2, RequestListener<R> requestListener, @Nullable List<RequestListener<R>> list, RequestCoordinator requestCoordinator2, Engine engine2, TransitionFactory<? super R> transitionFactory) {
        this.context = context2;
        this.glideContext = glideContext2;
        this.model = obj;
        this.transcodeClass = cls;
        this.requestOptions = requestOptions2;
        this.overrideWidth = i;
        this.overrideHeight = i2;
        this.priority = priority2;
        this.target = target2;
        this.targetListener = requestListener;
        this.requestListeners = list;
        this.requestCoordinator = requestCoordinator2;
        this.engine = engine2;
        this.animationFactory = transitionFactory;
        this.status = Status.PENDING;
    }

    @NonNull
    public StateVerifier getVerifier() {
        return this.stateVerifier;
    }

    public void recycle() {
        assertNotCallingCallbacks();
        this.context = null;
        this.glideContext = null;
        this.model = null;
        this.transcodeClass = null;
        this.requestOptions = null;
        this.overrideWidth = -1;
        this.overrideHeight = -1;
        this.target = null;
        this.requestListeners = null;
        this.targetListener = null;
        this.requestCoordinator = null;
        this.animationFactory = null;
        this.loadStatus = null;
        this.errorDrawable = null;
        this.placeholderDrawable = null;
        this.fallbackDrawable = null;
        this.width = -1;
        this.height = -1;
        POOL.release(this);
    }

    public void begin() {
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        this.startTime = LogTime.getLogTime();
        if (this.model == null) {
            if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) {
                this.width = this.overrideWidth;
                this.height = this.overrideHeight;
            }
            onLoadFailed(new GlideException("Received null model"), getFallbackDrawable() == null ? 5 : 3);
        } else if (this.status == Status.RUNNING) {
            throw new IllegalArgumentException("Cannot restart a running request");
        } else if (this.status == Status.COMPLETE) {
            onResourceReady(this.resource, DataSource.MEMORY_CACHE);
        } else {
            this.status = Status.WAITING_FOR_SIZE;
            if (Util.isValidDimensions(this.overrideWidth, this.overrideHeight)) {
                onSizeReady(this.overrideWidth, this.overrideHeight);
            } else {
                this.target.getSize(this);
            }
            if ((this.status == Status.RUNNING || this.status == Status.WAITING_FOR_SIZE) && canNotifyStatusChanged()) {
                this.target.onLoadStarted(getPlaceholderDrawable());
            }
            if (IS_VERBOSE_LOGGABLE) {
                StringBuilder sb = new StringBuilder();
                sb.append("finished run method in ");
                sb.append(LogTime.getElapsedMillis(this.startTime));
                logV(sb.toString());
            }
        }
    }

    private void cancel() {
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        this.target.removeCallback(this);
        LoadStatus loadStatus2 = this.loadStatus;
        if (loadStatus2 != null) {
            loadStatus2.cancel();
            this.loadStatus = null;
        }
    }

    private void assertNotCallingCallbacks() {
        if (this.isCallingCallbacks) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    public void clear() {
        Util.assertMainThread();
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        if (this.status != Status.CLEARED) {
            cancel();
            Resource<R> resource2 = this.resource;
            if (resource2 != null) {
                releaseResource(resource2);
            }
            if (canNotifyCleared()) {
                this.target.onLoadCleared(getPlaceholderDrawable());
            }
            this.status = Status.CLEARED;
        }
    }

    private void releaseResource(Resource<?> resource2) {
        this.engine.release(resource2);
        this.resource = null;
    }

    public boolean isRunning() {
        return this.status == Status.RUNNING || this.status == Status.WAITING_FOR_SIZE;
    }

    public boolean isComplete() {
        return this.status == Status.COMPLETE;
    }

    public boolean isResourceSet() {
        return isComplete();
    }

    public boolean isCleared() {
        return this.status == Status.CLEARED;
    }

    public boolean isFailed() {
        return this.status == Status.FAILED;
    }

    private Drawable getErrorDrawable() {
        if (this.errorDrawable == null) {
            this.errorDrawable = this.requestOptions.getErrorPlaceholder();
            if (this.errorDrawable == null && this.requestOptions.getErrorId() > 0) {
                this.errorDrawable = loadDrawable(this.requestOptions.getErrorId());
            }
        }
        return this.errorDrawable;
    }

    private Drawable getPlaceholderDrawable() {
        if (this.placeholderDrawable == null) {
            this.placeholderDrawable = this.requestOptions.getPlaceholderDrawable();
            if (this.placeholderDrawable == null && this.requestOptions.getPlaceholderId() > 0) {
                this.placeholderDrawable = loadDrawable(this.requestOptions.getPlaceholderId());
            }
        }
        return this.placeholderDrawable;
    }

    private Drawable getFallbackDrawable() {
        if (this.fallbackDrawable == null) {
            this.fallbackDrawable = this.requestOptions.getFallbackDrawable();
            if (this.fallbackDrawable == null && this.requestOptions.getFallbackId() > 0) {
                this.fallbackDrawable = loadDrawable(this.requestOptions.getFallbackId());
            }
        }
        return this.fallbackDrawable;
    }

    private Drawable loadDrawable(@DrawableRes int i) {
        return DrawableDecoderCompat.getDrawable((Context) this.glideContext, i, this.requestOptions.getTheme() != null ? this.requestOptions.getTheme() : this.context.getTheme());
    }

    private void setErrorPlaceholder() {
        if (canNotifyStatusChanged()) {
            Drawable drawable = null;
            if (this.model == null) {
                drawable = getFallbackDrawable();
            }
            if (drawable == null) {
                drawable = getErrorDrawable();
            }
            if (drawable == null) {
                drawable = getPlaceholderDrawable();
            }
            this.target.onLoadFailed(drawable);
        }
    }

    public void onSizeReady(int i, int i2) {
        this.stateVerifier.throwIfRecycled();
        if (IS_VERBOSE_LOGGABLE) {
            StringBuilder sb = new StringBuilder();
            sb.append("Got onSizeReady in ");
            sb.append(LogTime.getElapsedMillis(this.startTime));
            logV(sb.toString());
        }
        if (this.status == Status.WAITING_FOR_SIZE) {
            this.status = Status.RUNNING;
            float sizeMultiplier = this.requestOptions.getSizeMultiplier();
            this.width = maybeApplySizeMultiplier(i, sizeMultiplier);
            this.height = maybeApplySizeMultiplier(i2, sizeMultiplier);
            if (IS_VERBOSE_LOGGABLE) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("finished setup for calling load in ");
                sb2.append(LogTime.getElapsedMillis(this.startTime));
                logV(sb2.toString());
            }
            Engine engine2 = this.engine;
            GlideContext glideContext2 = this.glideContext;
            LoadStatus load = engine2.load(glideContext2, this.model, this.requestOptions.getSignature(), this.width, this.height, this.requestOptions.getResourceClass(), this.transcodeClass, this.priority, this.requestOptions.getDiskCacheStrategy(), this.requestOptions.getTransformations(), this.requestOptions.isTransformationRequired(), this.requestOptions.isScaleOnlyOrNoTransform(), this.requestOptions.getOptions(), this.requestOptions.isMemoryCacheable(), this.requestOptions.getUseUnlimitedSourceGeneratorsPool(), this.requestOptions.getUseAnimationPool(), this.requestOptions.getOnlyRetrieveFromCache(), this);
            this.loadStatus = load;
            if (this.status != Status.RUNNING) {
                this.loadStatus = null;
            }
            if (IS_VERBOSE_LOGGABLE) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("finished onSizeReady in ");
                sb3.append(LogTime.getElapsedMillis(this.startTime));
                logV(sb3.toString());
            }
        }
    }

    private static int maybeApplySizeMultiplier(int i, float f) {
        return i == Integer.MIN_VALUE ? i : Math.round(f * ((float) i));
    }

    private boolean canSetResource() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || requestCoordinator2.canSetImage(this);
    }

    private boolean canNotifyCleared() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || requestCoordinator2.canNotifyCleared(this);
    }

    private boolean canNotifyStatusChanged() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || requestCoordinator2.canNotifyStatusChanged(this);
    }

    private boolean isFirstReadyResource() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || !requestCoordinator2.isAnyResourceSet();
    }

    private void notifyLoadSuccess() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        if (requestCoordinator2 != null) {
            requestCoordinator2.onRequestSuccess(this);
        }
    }

    private void notifyLoadFailed() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        if (requestCoordinator2 != null) {
            requestCoordinator2.onRequestFailed(this);
        }
    }

    public void onResourceReady(Resource<?> resource2, DataSource dataSource) {
        this.stateVerifier.throwIfRecycled();
        this.loadStatus = null;
        if (resource2 == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected to receive a Resource<R> with an object of ");
            sb.append(this.transcodeClass);
            sb.append(" inside, but instead got null.");
            onLoadFailed(new GlideException(sb.toString()));
            return;
        }
        Object obj = resource2.get();
        if (obj == null || !this.transcodeClass.isAssignableFrom(obj.getClass())) {
            releaseResource(resource2);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Expected to receive an object of ");
            sb2.append(this.transcodeClass);
            sb2.append(" but instead got ");
            sb2.append(obj != null ? obj.getClass() : "");
            sb2.append("{");
            sb2.append(obj);
            sb2.append("} inside Resource{");
            sb2.append(resource2);
            sb2.append("}.");
            sb2.append(obj != null ? "" : " To indicate failure return a null Resource object, rather than a Resource object containing null data.");
            onLoadFailed(new GlideException(sb2.toString()));
        } else if (!canSetResource()) {
            releaseResource(resource2);
            this.status = Status.COMPLETE;
        } else {
            onResourceReady(resource2, obj, dataSource);
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00ab A[Catch:{ all -> 0x00bc }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void onResourceReady(com.bumptech.glide.load.engine.Resource<R> r11, R r12, com.bumptech.glide.load.DataSource r13) {
        /*
            r10 = this;
            boolean r6 = r10.isFirstReadyResource()
            com.bumptech.glide.request.SingleRequest$Status r0 = com.bumptech.glide.request.SingleRequest.Status.COMPLETE
            r10.status = r0
            r10.resource = r11
            com.bumptech.glide.GlideContext r11 = r10.glideContext
            int r11 = r11.getLogLevel()
            r0 = 3
            if (r11 > r0) goto L_0x006a
            java.lang.String r11 = "Glide"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Finished loading "
            r0.append(r1)
            java.lang.Class r1 = r12.getClass()
            java.lang.String r1 = r1.getSimpleName()
            r0.append(r1)
            java.lang.String r1 = " from "
            r0.append(r1)
            r0.append(r13)
            java.lang.String r1 = " for "
            r0.append(r1)
            java.lang.Object r1 = r10.model
            r0.append(r1)
            java.lang.String r1 = " with size ["
            r0.append(r1)
            int r1 = r10.width
            r0.append(r1)
            java.lang.String r1 = "x"
            r0.append(r1)
            int r1 = r10.height
            r0.append(r1)
            java.lang.String r1 = "] in "
            r0.append(r1)
            long r1 = r10.startTime
            double r1 = com.bumptech.glide.util.LogTime.getElapsedMillis(r1)
            r0.append(r1)
            java.lang.String r1 = " ms"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r11, r0)
        L_0x006a:
            r11 = 1
            r10.isCallingCallbacks = r11
            r7 = 0
            java.util.List<com.bumptech.glide.request.RequestListener<R>> r0 = r10.requestListeners     // Catch:{ all -> 0x00bc }
            if (r0 == 0) goto L_0x0092
            java.util.List<com.bumptech.glide.request.RequestListener<R>> r0 = r10.requestListeners     // Catch:{ all -> 0x00bc }
            java.util.Iterator r8 = r0.iterator()     // Catch:{ all -> 0x00bc }
            r9 = 0
        L_0x0079:
            boolean r0 = r8.hasNext()     // Catch:{ all -> 0x00bc }
            if (r0 == 0) goto L_0x0093
            java.lang.Object r0 = r8.next()     // Catch:{ all -> 0x00bc }
            com.bumptech.glide.request.RequestListener r0 = (com.bumptech.glide.request.RequestListener) r0     // Catch:{ all -> 0x00bc }
            java.lang.Object r2 = r10.model     // Catch:{ all -> 0x00bc }
            com.bumptech.glide.request.target.Target<R> r3 = r10.target     // Catch:{ all -> 0x00bc }
            r1 = r12
            r4 = r13
            r5 = r6
            boolean r0 = r0.onResourceReady(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x00bc }
            r9 = r9 | r0
            goto L_0x0079
        L_0x0092:
            r9 = 0
        L_0x0093:
            com.bumptech.glide.request.RequestListener<R> r0 = r10.targetListener     // Catch:{ all -> 0x00bc }
            if (r0 == 0) goto L_0x00a7
            com.bumptech.glide.request.RequestListener<R> r0 = r10.targetListener     // Catch:{ all -> 0x00bc }
            java.lang.Object r2 = r10.model     // Catch:{ all -> 0x00bc }
            com.bumptech.glide.request.target.Target<R> r3 = r10.target     // Catch:{ all -> 0x00bc }
            r1 = r12
            r4 = r13
            r5 = r6
            boolean r0 = r0.onResourceReady(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x00bc }
            if (r0 == 0) goto L_0x00a7
            goto L_0x00a8
        L_0x00a7:
            r11 = 0
        L_0x00a8:
            r11 = r11 | r9
            if (r11 != 0) goto L_0x00b6
            com.bumptech.glide.request.transition.TransitionFactory<? super R> r11 = r10.animationFactory     // Catch:{ all -> 0x00bc }
            com.bumptech.glide.request.transition.Transition r11 = r11.build(r13, r6)     // Catch:{ all -> 0x00bc }
            com.bumptech.glide.request.target.Target<R> r13 = r10.target     // Catch:{ all -> 0x00bc }
            r13.onResourceReady(r12, r11)     // Catch:{ all -> 0x00bc }
        L_0x00b6:
            r10.isCallingCallbacks = r7
            r10.notifyLoadSuccess()
            return
        L_0x00bc:
            r11 = move-exception
            r10.isCallingCallbacks = r7
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.onResourceReady(com.bumptech.glide.load.engine.Resource, java.lang.Object, com.bumptech.glide.load.DataSource):void");
    }

    public void onLoadFailed(GlideException glideException) {
        onLoadFailed(glideException, 5);
    }

    /* JADX INFO: finally extract failed */
    private void onLoadFailed(GlideException glideException, int i) {
        boolean z;
        this.stateVerifier.throwIfRecycled();
        int logLevel = this.glideContext.getLogLevel();
        if (logLevel <= i) {
            StringBuilder sb = new StringBuilder();
            sb.append("Load failed for ");
            sb.append(this.model);
            sb.append(" with size [");
            sb.append(this.width);
            sb.append(AvidJSONUtil.KEY_X);
            sb.append(this.height);
            sb.append("]");
            Log.w("Glide", sb.toString(), glideException);
            if (logLevel <= 4) {
                glideException.logRootCauses("Glide");
            }
        }
        this.loadStatus = null;
        this.status = Status.FAILED;
        boolean z2 = true;
        this.isCallingCallbacks = true;
        try {
            if (this.requestListeners != null) {
                z = false;
                for (RequestListener onLoadFailed : this.requestListeners) {
                    z |= onLoadFailed.onLoadFailed(glideException, this.model, this.target, isFirstReadyResource());
                }
            } else {
                z = false;
            }
            if (this.targetListener == null || !this.targetListener.onLoadFailed(glideException, this.model, this.target, isFirstReadyResource())) {
                z2 = false;
            }
            if (!z && !z2) {
                setErrorPlaceholder();
            }
            this.isCallingCallbacks = false;
            notifyLoadFailed();
        } catch (Throwable th) {
            this.isCallingCallbacks = false;
            throw th;
        }
    }

    public boolean isEquivalentTo(Request request) {
        boolean z = false;
        if (!(request instanceof SingleRequest)) {
            return false;
        }
        SingleRequest singleRequest = (SingleRequest) request;
        if (this.overrideWidth == singleRequest.overrideWidth && this.overrideHeight == singleRequest.overrideHeight && Util.bothModelsNullEquivalentOrEquals(this.model, singleRequest.model) && this.transcodeClass.equals(singleRequest.transcodeClass) && this.requestOptions.equals(singleRequest.requestOptions) && this.priority == singleRequest.priority && listenerCountEquals(this, singleRequest)) {
            z = true;
        }
        return z;
    }

    private static boolean listenerCountEquals(SingleRequest<?> singleRequest, SingleRequest<?> singleRequest2) {
        List<RequestListener<R>> list = singleRequest.requestListeners;
        int size = list == null ? 0 : list.size();
        List<RequestListener<R>> list2 = singleRequest2.requestListeners;
        if (size == (list2 == null ? 0 : list2.size())) {
            return true;
        }
        return false;
    }

    private void logV(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" this: ");
        sb.append(this.tag);
        Log.v("Request", sb.toString());
    }
}
