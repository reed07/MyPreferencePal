package com.myfitnesspal.framework.deeplink;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import com.google.android.exoplayer2.C;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.UriUtils;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class DeepLinkRouter {
    protected static DeepLinkRouter router;
    protected final Context context;
    private Uri deepLink;
    private Dispatcher dispatcher;
    private Bundle extras;
    private Object instance;
    private Class routes;

    /* access modifiers changed from: protected */
    public boolean isUserAuthenticated() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPostRoute(Uri uri) {
    }

    /* access modifiers changed from: protected */
    public void onPreRoute(Uri uri) {
    }

    /* access modifiers changed from: protected */
    public void onUserNotAuthenticated() {
    }

    public static DeepLinkRouter getInstance(Context context2, Class cls, Uri uri) {
        DeepLinkRouter deepLinkRouter = router;
        if (deepLinkRouter == null) {
            router = new DeepLinkRouter(context2, cls, uri);
        } else {
            deepLinkRouter.setRoutes(cls);
            router.setDeepLink(uri);
        }
        return router;
    }

    public static DeepLinkRouter getInstance(Context context2, Class cls, Dispatcher dispatcher2, Uri uri) {
        DeepLinkRouter deepLinkRouter = router;
        if (deepLinkRouter == null) {
            router = new DeepLinkRouter(context2, cls, dispatcher2, uri);
        } else {
            deepLinkRouter.setRoutes(cls);
            router.setDeepLink(uri);
            router.setDispatcher(dispatcher2);
        }
        return router;
    }

    public static DeepLinkRouter getInstance() {
        return router;
    }

    protected DeepLinkRouter(Context context2, Class cls, Uri uri) {
        this(context2, cls, null, uri);
    }

    protected DeepLinkRouter(Context context2, Class cls, Dispatcher dispatcher2, Uri uri) {
        this.context = context2;
        this.routes = cls;
        this.deepLink = uri;
        if (dispatcher2 == null) {
            dispatcher2 = new Dispatcher(context2);
        }
        this.dispatcher = dispatcher2;
    }

    public void setRoutes(Class cls) {
        this.routes = cls;
    }

    public void setDeepLink(Uri uri) {
        this.deepLink = uri;
    }

    public void setExtras(Bundle bundle) {
        this.extras = bundle;
    }

    public Uri getDeepLink() {
        return this.deepLink;
    }

    public void setDispatcher(Dispatcher dispatcher2) {
        if (dispatcher2 == null) {
            dispatcher2 = new Dispatcher(this.context);
        }
        this.dispatcher = dispatcher2;
    }

    public void route() {
        route(this.context);
    }

    public void route(Context context2) {
        boolean z;
        Ln.d("DeepLinkRouter: route() entered", new Object[0]);
        try {
            this.instance = this.routes.newInstance();
            Method[] methods = this.instance.getClass().getMethods();
            sortRoutesByOrder(methods);
            int length = methods.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                }
                Method method = methods[i];
                Route route = (Route) method.getAnnotation(Route.class);
                if (routable(route, this.deepLink)) {
                    onPreRoute(this.deepLink);
                    Ln.d("DeepLinkRouter: found valid route!", new Object[0]);
                    if (!route.requiresAuthentication() || isUserAuthenticated()) {
                        Ln.d("DeepLinkRouter: route invoke start", new Object[0]);
                        method.invoke(this.instance, new Object[]{context2, this.dispatcher, this.deepLink, this.extras});
                        Ln.d("DeepLinkRouter: route invoke end", new Object[0]);
                        z = true;
                    } else {
                        Ln.d("DeepLinkRouter: authentication required. bailing!", new Object[0]);
                        onUserNotAuthenticated();
                        return;
                    }
                } else {
                    i++;
                }
            }
            String str = "DeepLinkRouter: routed=%s";
            Object[] objArr = new Object[1];
            objArr[0] = z ? "true" : "false";
            Ln.d(str, objArr);
            if (!z) {
                onLinkNotRouted();
            } else {
                onPostRoute(this.deepLink);
            }
        } catch (Exception e) {
            Ln.d("DeepLinkRouter: caught exception while routing!", new Object[0]);
            Ln.d(e);
            Object[] objArr2 = new Object[1];
            Uri uri = this.deepLink;
            objArr2[0] = uri == null ? "" : uri.toString();
            throw new IllegalStateException(String.format("Tried to route %s, but could not.", objArr2), e);
        }
    }

    /* access modifiers changed from: protected */
    public void sortRoutesByOrder(Method[] methodArr) {
        Arrays.sort(methodArr, new Comparator<Method>() {
            public int compare(Method method, Method method2) {
                Route route = (Route) method.getAnnotation(Route.class);
                Route route2 = (Route) method2.getAnnotation(Route.class);
                if (route != null && route2 != null) {
                    int order = route.order() - route2.order();
                    if (order == 0) {
                        order = Strings.toString(route.pattern()).compareToIgnoreCase(Strings.toString(route2.pattern()));
                    }
                    return order;
                } else if (route != null) {
                    return -1;
                } else {
                    if (route2 != null) {
                        return 1;
                    }
                    return method.getName().compareTo(method2.getName());
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void redirectToBrowser(Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW", getRedirectUri(uri));
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        this.context.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public Uri getRedirectUri(Uri uri) {
        Builder buildUpon = Uri.withAppendedPath(Uri.parse("http://www.myfitnesspal.com"), uri.getPath().replace("/dispatch/", "redirect/")).buildUpon();
        for (String str : UriUtils.getQueryParameterNames(uri)) {
            buildUpon.appendQueryParameter(str, uri.getQueryParameter(str));
        }
        return buildUpon.build();
    }

    /* access modifiers changed from: protected */
    public boolean routable(Route route, Uri uri) {
        if (route == null) {
            return false;
        }
        return uri.getPath().toLowerCase().matches(route.pattern());
    }

    /* access modifiers changed from: protected */
    public void onLinkNotRouted() {
        redirectToBrowser(this.deepLink);
    }
}
