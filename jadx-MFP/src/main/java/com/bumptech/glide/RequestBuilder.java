package com.bumptech.glide;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.CheckResult;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestCoordinator;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.SingleRequest;
import com.bumptech.glide.request.ThumbnailRequestCoordinator;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.signature.ApplicationVersionSignature;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.util.ArrayList;
import java.util.List;

public class RequestBuilder<TranscodeType> implements Cloneable {
    protected static final RequestOptions DOWNLOAD_ONLY_OPTIONS = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA).priority(Priority.LOW).skipMemoryCache(true);
    private final Context context;
    private final RequestOptions defaultRequestOptions;
    @Nullable
    private RequestBuilder<TranscodeType> errorBuilder;
    private final Glide glide;
    private final GlideContext glideContext;
    private boolean isDefaultTransitionOptionsSet = true;
    private boolean isModelSet;
    private boolean isThumbnailBuilt;
    @Nullable
    private Object model;
    @Nullable
    private List<RequestListener<TranscodeType>> requestListeners;
    private final RequestManager requestManager;
    @NonNull
    protected RequestOptions requestOptions;
    @Nullable
    private Float thumbSizeMultiplier;
    @Nullable
    private RequestBuilder<TranscodeType> thumbnailBuilder;
    private final Class<TranscodeType> transcodeClass;
    @NonNull
    private TransitionOptions<?, ? super TranscodeType> transitionOptions;

    /* renamed from: com.bumptech.glide.RequestBuilder$2 reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ScaleType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|(3:31|32|34)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|(3:31|32|34)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0052 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0066 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0071 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x007c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0087 */
        static {
            /*
                com.bumptech.glide.Priority[] r0 = com.bumptech.glide.Priority.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$bumptech$glide$Priority = r0
                r0 = 1
                int[] r1 = $SwitchMap$com$bumptech$glide$Priority     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.bumptech.glide.Priority r2 = com.bumptech.glide.Priority.LOW     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = $SwitchMap$com$bumptech$glide$Priority     // Catch:{ NoSuchFieldError -> 0x001f }
                com.bumptech.glide.Priority r3 = com.bumptech.glide.Priority.NORMAL     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = $SwitchMap$com$bumptech$glide$Priority     // Catch:{ NoSuchFieldError -> 0x002a }
                com.bumptech.glide.Priority r4 = com.bumptech.glide.Priority.HIGH     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                r3 = 4
                int[] r4 = $SwitchMap$com$bumptech$glide$Priority     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.bumptech.glide.Priority r5 = com.bumptech.glide.Priority.IMMEDIATE     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                android.widget.ImageView$ScaleType[] r4 = android.widget.ImageView.ScaleType.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$android$widget$ImageView$ScaleType = r4
                int[] r4 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0048 }
                android.widget.ImageView$ScaleType r5 = android.widget.ImageView.ScaleType.CENTER_CROP     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0052 }
                android.widget.ImageView$ScaleType r4 = android.widget.ImageView.ScaleType.CENTER_INSIDE     // Catch:{ NoSuchFieldError -> 0x0052 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0052 }
                r0[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0052 }
            L_0x0052:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x005c }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_CENTER     // Catch:{ NoSuchFieldError -> 0x005c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005c }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005c }
            L_0x005c:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0066 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_START     // Catch:{ NoSuchFieldError -> 0x0066 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0066 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0066 }
            L_0x0066:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0071 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_END     // Catch:{ NoSuchFieldError -> 0x0071 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0071 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0071 }
            L_0x0071:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x007c }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.FIT_XY     // Catch:{ NoSuchFieldError -> 0x007c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007c }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007c }
            L_0x007c:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0087 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER     // Catch:{ NoSuchFieldError -> 0x0087 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0087 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0087 }
            L_0x0087:
                int[] r0 = $SwitchMap$android$widget$ImageView$ScaleType     // Catch:{ NoSuchFieldError -> 0x0093 }
                android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.MATRIX     // Catch:{ NoSuchFieldError -> 0x0093 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0093 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0093 }
            L_0x0093:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.RequestBuilder.AnonymousClass2.<clinit>():void");
        }
    }

    protected RequestBuilder(Glide glide2, RequestManager requestManager2, Class<TranscodeType> cls, Context context2) {
        this.glide = glide2;
        this.requestManager = requestManager2;
        this.transcodeClass = cls;
        this.defaultRequestOptions = requestManager2.getDefaultRequestOptions();
        this.context = context2;
        this.transitionOptions = requestManager2.getDefaultTransitionOptions(cls);
        this.requestOptions = this.defaultRequestOptions;
        this.glideContext = glide2.getGlideContext();
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> apply(@NonNull RequestOptions requestOptions2) {
        Preconditions.checkNotNull(requestOptions2);
        this.requestOptions = getMutableOptions().apply(requestOptions2);
        return this;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public RequestOptions getMutableOptions() {
        RequestOptions requestOptions2 = this.defaultRequestOptions;
        RequestOptions requestOptions3 = this.requestOptions;
        return requestOptions2 == requestOptions3 ? requestOptions3.clone() : requestOptions3;
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> listener(@Nullable RequestListener<TranscodeType> requestListener) {
        this.requestListeners = null;
        return addListener(requestListener);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> addListener(@Nullable RequestListener<TranscodeType> requestListener) {
        if (requestListener != null) {
            if (this.requestListeners == null) {
                this.requestListeners = new ArrayList();
            }
            this.requestListeners.add(requestListener);
        }
        return this;
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable Object obj) {
        return loadGeneric(obj);
    }

    @NonNull
    private RequestBuilder<TranscodeType> loadGeneric(@Nullable Object obj) {
        this.model = obj;
        this.isModelSet = true;
        return this;
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable String str) {
        return loadGeneric(str);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable Uri uri) {
        return loadGeneric(uri);
    }

    @CheckResult
    @NonNull
    public RequestBuilder<TranscodeType> load(@Nullable @RawRes @DrawableRes Integer num) {
        return loadGeneric(num).apply(RequestOptions.signatureOf(ApplicationVersionSignature.obtain(this.context)));
    }

    @CheckResult
    public RequestBuilder<TranscodeType> clone() {
        try {
            RequestBuilder<TranscodeType> requestBuilder = (RequestBuilder) super.clone();
            requestBuilder.requestOptions = requestBuilder.requestOptions.clone();
            requestBuilder.transitionOptions = requestBuilder.transitionOptions.clone();
            return requestBuilder;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    public <Y extends Target<TranscodeType>> Y into(@NonNull Y y) {
        return into(y, null);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public <Y extends Target<TranscodeType>> Y into(@NonNull Y y, @Nullable RequestListener<TranscodeType> requestListener) {
        return into(y, requestListener, getMutableOptions());
    }

    private <Y extends Target<TranscodeType>> Y into(@NonNull Y y, @Nullable RequestListener<TranscodeType> requestListener, @NonNull RequestOptions requestOptions2) {
        Util.assertMainThread();
        Preconditions.checkNotNull(y);
        if (this.isModelSet) {
            RequestOptions autoClone = requestOptions2.autoClone();
            Request buildRequest = buildRequest(y, requestListener, autoClone);
            Request request = y.getRequest();
            if (!buildRequest.isEquivalentTo(request) || isSkipMemoryCacheWithCompletePreviousRequest(autoClone, request)) {
                this.requestManager.clear(y);
                y.setRequest(buildRequest);
                this.requestManager.track(y, buildRequest);
                return y;
            }
            buildRequest.recycle();
            if (!((Request) Preconditions.checkNotNull(request)).isRunning()) {
                request.begin();
            }
            return y;
        }
        throw new IllegalArgumentException("You must call #load() before calling #into()");
    }

    private boolean isSkipMemoryCacheWithCompletePreviousRequest(RequestOptions requestOptions2, Request request) {
        return !requestOptions2.isMemoryCacheable() && request.isComplete();
    }

    @NonNull
    public ViewTarget<ImageView, TranscodeType> into(@NonNull ImageView imageView) {
        Util.assertMainThread();
        Preconditions.checkNotNull(imageView);
        RequestOptions requestOptions2 = this.requestOptions;
        if (!requestOptions2.isTransformationSet() && requestOptions2.isTransformationAllowed() && imageView.getScaleType() != null) {
            switch (AnonymousClass2.$SwitchMap$android$widget$ImageView$ScaleType[imageView.getScaleType().ordinal()]) {
                case 1:
                    requestOptions2 = requestOptions2.clone().optionalCenterCrop();
                    break;
                case 2:
                    requestOptions2 = requestOptions2.clone().optionalCenterInside();
                    break;
                case 3:
                case 4:
                case 5:
                    requestOptions2 = requestOptions2.clone().optionalFitCenter();
                    break;
                case 6:
                    requestOptions2 = requestOptions2.clone().optionalCenterInside();
                    break;
            }
        }
        return (ViewTarget) into(this.glideContext.buildImageViewTarget(imageView, this.transcodeClass), null, requestOptions2);
    }

    @Deprecated
    public FutureTarget<TranscodeType> into(int i, int i2) {
        return submit(i, i2);
    }

    @NonNull
    public FutureTarget<TranscodeType> submit(int i, int i2) {
        final RequestFutureTarget requestFutureTarget = new RequestFutureTarget(this.glideContext.getMainHandler(), i, i2);
        if (Util.isOnBackgroundThread()) {
            this.glideContext.getMainHandler().post(new Runnable() {
                public void run() {
                    if (!requestFutureTarget.isCancelled()) {
                        RequestBuilder requestBuilder = RequestBuilder.this;
                        RequestFutureTarget requestFutureTarget = requestFutureTarget;
                        requestBuilder.into(requestFutureTarget, (RequestListener<TranscodeType>) requestFutureTarget);
                    }
                }
            });
        } else {
            into((Y) requestFutureTarget, (RequestListener<TranscodeType>) requestFutureTarget);
        }
        return requestFutureTarget;
    }

    @NonNull
    private Priority getThumbnailPriority(@NonNull Priority priority) {
        switch (priority) {
            case LOW:
                return Priority.NORMAL;
            case NORMAL:
                return Priority.HIGH;
            case HIGH:
            case IMMEDIATE:
                return Priority.IMMEDIATE;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("unknown priority: ");
                sb.append(this.requestOptions.getPriority());
                throw new IllegalArgumentException(sb.toString());
        }
    }

    private Request buildRequest(Target<TranscodeType> target, @Nullable RequestListener<TranscodeType> requestListener, RequestOptions requestOptions2) {
        return buildRequestRecursive(target, requestListener, null, this.transitionOptions, requestOptions2.getPriority(), requestOptions2.getOverrideWidth(), requestOptions2.getOverrideHeight(), requestOptions2);
    }

    /* JADX WARNING: type inference failed for: r15v0 */
    /* JADX WARNING: type inference failed for: r3v0, types: [com.bumptech.glide.request.RequestCoordinator] */
    /* JADX WARNING: type inference failed for: r13v0, types: [com.bumptech.glide.request.RequestCoordinator] */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r15v2 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r0v4, types: [com.bumptech.glide.request.ErrorRequestCoordinator] */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r15v3 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.bumptech.glide.request.Request buildRequestRecursive(com.bumptech.glide.request.target.Target<TranscodeType> r20, @android.support.annotation.Nullable com.bumptech.glide.request.RequestListener<TranscodeType> r21, @android.support.annotation.Nullable com.bumptech.glide.request.RequestCoordinator r22, com.bumptech.glide.TransitionOptions<?, ? super TranscodeType> r23, com.bumptech.glide.Priority r24, int r25, int r26, com.bumptech.glide.request.RequestOptions r27) {
        /*
            r19 = this;
            r9 = r19
            com.bumptech.glide.RequestBuilder<TranscodeType> r0 = r9.errorBuilder
            if (r0 == 0) goto L_0x0010
            com.bumptech.glide.request.ErrorRequestCoordinator r0 = new com.bumptech.glide.request.ErrorRequestCoordinator
            r1 = r22
            r0.<init>(r1)
            r3 = r0
            r15 = r3
            goto L_0x0015
        L_0x0010:
            r1 = r22
            r0 = 0
            r15 = r0
            r3 = r1
        L_0x0015:
            r0 = r19
            r1 = r20
            r2 = r21
            r4 = r23
            r5 = r24
            r6 = r25
            r7 = r26
            r8 = r27
            com.bumptech.glide.request.Request r0 = r0.buildThumbnailRequestRecursive(r1, r2, r3, r4, r5, r6, r7, r8)
            if (r15 != 0) goto L_0x002c
            return r0
        L_0x002c:
            com.bumptech.glide.RequestBuilder<TranscodeType> r1 = r9.errorBuilder
            com.bumptech.glide.request.RequestOptions r1 = r1.requestOptions
            int r1 = r1.getOverrideWidth()
            com.bumptech.glide.RequestBuilder<TranscodeType> r2 = r9.errorBuilder
            com.bumptech.glide.request.RequestOptions r2 = r2.requestOptions
            int r2 = r2.getOverrideHeight()
            boolean r3 = com.bumptech.glide.util.Util.isValidDimensions(r25, r26)
            if (r3 == 0) goto L_0x0059
            com.bumptech.glide.RequestBuilder<TranscodeType> r3 = r9.errorBuilder
            com.bumptech.glide.request.RequestOptions r3 = r3.requestOptions
            boolean r3 = r3.isValidOverride()
            if (r3 != 0) goto L_0x0059
            int r1 = r27.getOverrideWidth()
            int r2 = r27.getOverrideHeight()
            r16 = r1
            r17 = r2
            goto L_0x005d
        L_0x0059:
            r16 = r1
            r17 = r2
        L_0x005d:
            com.bumptech.glide.RequestBuilder<TranscodeType> r10 = r9.errorBuilder
            com.bumptech.glide.TransitionOptions<?, ? super TranscodeType> r14 = r10.transitionOptions
            com.bumptech.glide.request.RequestOptions r1 = r10.requestOptions
            com.bumptech.glide.Priority r1 = r1.getPriority()
            com.bumptech.glide.RequestBuilder<TranscodeType> r2 = r9.errorBuilder
            com.bumptech.glide.request.RequestOptions r2 = r2.requestOptions
            r11 = r20
            r12 = r21
            r13 = r15
            r3 = r15
            r15 = r1
            r18 = r2
            com.bumptech.glide.request.Request r1 = r10.buildRequestRecursive(r11, r12, r13, r14, r15, r16, r17, r18)
            r3.setRequests(r0, r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.RequestBuilder.buildRequestRecursive(com.bumptech.glide.request.target.Target, com.bumptech.glide.request.RequestListener, com.bumptech.glide.request.RequestCoordinator, com.bumptech.glide.TransitionOptions, com.bumptech.glide.Priority, int, int, com.bumptech.glide.request.RequestOptions):com.bumptech.glide.request.Request");
    }

    private Request buildThumbnailRequestRecursive(Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, @Nullable RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions2, Priority priority, int i, int i2, RequestOptions requestOptions2) {
        int i3;
        int i4;
        RequestCoordinator requestCoordinator2 = requestCoordinator;
        Priority priority2 = priority;
        RequestBuilder<TranscodeType> requestBuilder = this.thumbnailBuilder;
        if (requestBuilder != null) {
            if (!this.isThumbnailBuilt) {
                TransitionOptions<?, ? super TranscodeType> transitionOptions3 = requestBuilder.isDefaultTransitionOptionsSet ? transitionOptions2 : requestBuilder.transitionOptions;
                Priority priority3 = this.thumbnailBuilder.requestOptions.isPrioritySet() ? this.thumbnailBuilder.requestOptions.getPriority() : getThumbnailPriority(priority2);
                int overrideWidth = this.thumbnailBuilder.requestOptions.getOverrideWidth();
                int overrideHeight = this.thumbnailBuilder.requestOptions.getOverrideHeight();
                if (!Util.isValidDimensions(i, i2) || this.thumbnailBuilder.requestOptions.isValidOverride()) {
                    i4 = overrideWidth;
                    i3 = overrideHeight;
                } else {
                    i4 = requestOptions2.getOverrideWidth();
                    i3 = requestOptions2.getOverrideHeight();
                }
                ThumbnailRequestCoordinator thumbnailRequestCoordinator = new ThumbnailRequestCoordinator(requestCoordinator2);
                Request obtainRequest = obtainRequest(target, requestListener, requestOptions2, thumbnailRequestCoordinator, transitionOptions2, priority, i, i2);
                this.isThumbnailBuilt = true;
                RequestBuilder<TranscodeType> requestBuilder2 = this.thumbnailBuilder;
                ThumbnailRequestCoordinator thumbnailRequestCoordinator2 = thumbnailRequestCoordinator;
                Request buildRequestRecursive = requestBuilder2.buildRequestRecursive(target, requestListener, thumbnailRequestCoordinator, transitionOptions3, priority3, i4, i3, requestBuilder2.requestOptions);
                this.isThumbnailBuilt = false;
                thumbnailRequestCoordinator2.setRequests(obtainRequest, buildRequestRecursive);
                return thumbnailRequestCoordinator2;
            }
            throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
        } else if (this.thumbSizeMultiplier == null) {
            return obtainRequest(target, requestListener, requestOptions2, requestCoordinator, transitionOptions2, priority, i, i2);
        } else {
            ThumbnailRequestCoordinator thumbnailRequestCoordinator3 = new ThumbnailRequestCoordinator(requestCoordinator2);
            RequestListener<TranscodeType> requestListener2 = requestListener;
            ThumbnailRequestCoordinator thumbnailRequestCoordinator4 = thumbnailRequestCoordinator3;
            TransitionOptions<?, ? super TranscodeType> transitionOptions4 = transitionOptions2;
            int i5 = i;
            int i6 = i2;
            thumbnailRequestCoordinator3.setRequests(obtainRequest(target, requestListener2, requestOptions2, thumbnailRequestCoordinator4, transitionOptions4, priority, i5, i6), obtainRequest(target, requestListener2, requestOptions2.clone().sizeMultiplier(this.thumbSizeMultiplier.floatValue()), thumbnailRequestCoordinator4, transitionOptions4, getThumbnailPriority(priority2), i5, i6));
            return thumbnailRequestCoordinator3;
        }
    }

    private Request obtainRequest(Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, RequestOptions requestOptions2, RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions2, Priority priority, int i, int i2) {
        Context context2 = this.context;
        GlideContext glideContext2 = this.glideContext;
        return SingleRequest.obtain(context2, glideContext2, this.model, this.transcodeClass, requestOptions2, i, i2, priority, target, requestListener, this.requestListeners, requestCoordinator, glideContext2.getEngine(), transitionOptions2.getTransitionFactory());
    }
}
