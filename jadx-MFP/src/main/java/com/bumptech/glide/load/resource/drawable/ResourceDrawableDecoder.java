package com.bumptech.glide.load.resource.drawable;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import java.util.List;

public class ResourceDrawableDecoder implements ResourceDecoder<Uri, Drawable> {
    private final Context context;

    public ResourceDrawableDecoder(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public boolean handles(@NonNull Uri uri, @NonNull Options options) {
        return uri.getScheme().equals("android.resource");
    }

    @Nullable
    public Resource<Drawable> decode(@NonNull Uri uri, int i, int i2, @NonNull Options options) {
        int loadResourceIdFromUri = loadResourceIdFromUri(uri);
        String authority = uri.getAuthority();
        return NonOwnedDrawableResource.newInstance(DrawableDecoderCompat.getDrawable(this.context, authority.equals(this.context.getPackageName()) ? this.context : getContextForPackage(uri, authority), loadResourceIdFromUri));
    }

    @NonNull
    private Context getContextForPackage(Uri uri, String str) {
        try {
            return this.context.createPackageContext(str, 0);
        } catch (NameNotFoundException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to obtain context or unrecognized Uri format for: ");
            sb.append(uri);
            throw new IllegalArgumentException(sb.toString(), e);
        }
    }

    @DrawableRes
    private int loadResourceIdFromUri(Uri uri) {
        Integer num;
        List pathSegments = uri.getPathSegments();
        if (pathSegments.size() == 2) {
            String str = (String) pathSegments.get(0);
            String str2 = (String) pathSegments.get(1);
            num = Integer.valueOf(this.context.getResources().getIdentifier(str2, str, uri.getAuthority()));
        } else {
            if (pathSegments.size() == 1) {
                try {
                    num = Integer.valueOf((String) pathSegments.get(0));
                } catch (NumberFormatException unused) {
                }
            }
            num = null;
        }
        if (num == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unrecognized Uri format: ");
            sb.append(uri);
            throw new IllegalArgumentException(sb.toString());
        } else if (num.intValue() != 0) {
            return num.intValue();
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to obtain resource id for: ");
            sb2.append(uri);
            throw new IllegalArgumentException(sb2.toString());
        }
    }
}
