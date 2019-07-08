package com.myfitnesspal.shared.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader.Factory;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GlideUtil {
    private static AtomicBoolean initialized = new AtomicBoolean(false);

    public static Glide init(Context context, final Lazy<ApiUrlProvider> lazy) {
        if (!initialized.get()) {
            Glide.get(context).getRegistry().append(GlideUrl.class, InputStream.class, (ModelLoaderFactory<Model, Data>) new Factory<Model,Data>(new Builder().addInterceptor(new Interceptor() {
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder newBuilder = chain.request().newBuilder();
                    if (chain.request().url().toString().startsWith(((ApiUrlProvider) lazy.get()).getApiV2BaseUrl())) {
                        Map credentials = ((ApiUrlProvider) lazy.get()).getCredentials();
                        for (String str : credentials.keySet()) {
                            newBuilder.addHeader(str, (String) credentials.get(str));
                        }
                    }
                    Response proceed = chain.proceed(newBuilder.build());
                    int code = proceed.code();
                    if (code >= 200 && code < 300) {
                        ResponseBody body = proceed.body();
                        if (body != null && body.contentLength() == 0) {
                            throw new IOException("cannot have an image with no body!");
                        }
                    }
                    return proceed;
                }
            }).build()));
            initialized.set(true);
        }
        Glide glide = Glide.get(context);
        glide.setMemoryCategory(MemoryCategory.LOW);
        return glide;
    }

    public static void loadImage(Context context, String str, int i, ImageView imageView) {
        RequestManager with = Glide.with(context);
        if (Strings.isEmpty(str)) {
            str = null;
        }
        with.load(str).apply(new RequestOptions().fitCenter().centerCrop().placeholder(i)).into(imageView);
    }

    public static void loadImage(Context context, String str, ImageView imageView) {
        RequestManager with = Glide.with(context);
        if (Strings.isEmpty(str)) {
            str = null;
        }
        with.load(str).into(imageView);
    }

    public static void loadImageWithProgressSpinner(Context context, String str, int i, ImageView imageView, final ProgressBar progressBar) {
        RequestManager with = Glide.with(context);
        if (Strings.isEmpty(str)) {
            str = null;
        }
        with.load(str).apply(new RequestOptions().fitCenter().centerCrop().placeholder(i)).listener(new RequestListener<Drawable>() {
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                return false;
            }

            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                ViewUtils.setVisible(false, progressBar);
                return false;
            }
        }).into(imageView);
    }

    public static void loadImageWithProgressSpinner(Context context, String str, ImageView imageView, final ProgressBar progressBar) {
        RequestManager with = Glide.with(context);
        if (Strings.isEmpty(str)) {
            str = null;
        }
        with.load(str).listener(new RequestListener<Drawable>() {
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                return false;
            }

            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                ViewUtils.setVisible(false, progressBar);
                return false;
            }
        }).into(imageView);
    }

    public static void loadImageWithFailureHandling(Context context, String str, ImageView imageView, final View view) {
        RequestManager with = Glide.with(context);
        if (Strings.isEmpty(str)) {
            str = null;
        }
        with.load(str).listener(new RequestListener<Drawable>() {
            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                return false;
            }

            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                ViewUtils.setInvisible(view);
                return false;
            }
        }).into(imageView);
    }

    public static void loadImageWithListener(Context context, String str, ImageView imageView, RequestListener requestListener) {
        RequestManager with = Glide.with(context);
        if (Strings.isEmpty(str)) {
            str = null;
        }
        with.load(str).listener(requestListener).into(imageView);
    }
}
