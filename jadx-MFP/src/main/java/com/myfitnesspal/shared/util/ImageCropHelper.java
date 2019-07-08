package com.myfitnesspal.shared.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.ui.adapter.CropOption;
import com.myfitnesspal.feature.settings.ui.adapter.CropOptionAdapter;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.SafeAsyncTask;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class ImageCropHelper {
    private static final String ASPECT_X = "aspectX";
    private static final String ASPECT_Y = "aspectY";
    private static final String CROP = "crop";
    private static final String OUTPUT_FORMAT = "outputFormat";
    private static final String OUTPUT_X = "outputX";
    private static final String OUTPUT_Y = "outputY";
    private static final String RETURN_DATA = "return-data";
    private final NavigationHelper navigationHelper;

    @Inject
    public ImageCropHelper(NavigationHelper navigationHelper2) {
        this.navigationHelper = navigationHelper2;
    }

    public void cropImage(Context context, Uri uri, Uri uri2, Function1<Bitmap> function1, int i) {
        Context context2 = context;
        Uri uri3 = uri;
        Uri uri4 = uri2;
        int i2 = i;
        Resources resources = context.getResources();
        PackageManager packageManager = context.getPackageManager();
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.addFlags(1);
            intent.addFlags(2);
            intent.setType("image/*");
            List queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
            int size = CollectionUtils.size((Collection<?>) queryIntentActivities);
            if (size == 0) {
                final Context context3 = context;
                final Uri uri5 = uri;
                final int i3 = i;
                final Function1<Bitmap> function12 = function1;
                final Uri uri6 = uri2;
                AnonymousClass1 r1 = new SafeAsyncTask<Bitmap>() {
                    public Bitmap call() throws Exception {
                        RequestBuilder apply = Glide.with(context3).asBitmap().load(uri5).apply(new RequestOptions().centerCrop());
                        int i = i3;
                        return (Bitmap) apply.into(i, i).get();
                    }

                    /* access modifiers changed from: protected */
                    public void onSuccess(Bitmap bitmap) throws Exception {
                        super.onSuccess(bitmap);
                        FunctionUtils.invokeIfValid(function12, bitmap);
                        ImageCropHelper.cleanOnFinish(uri6);
                    }

                    /* access modifiers changed from: protected */
                    public void onException(Exception exc) {
                        super.onException(exc);
                        FunctionUtils.invokeIfValid(function12, null);
                        ImageCropHelper.cleanOnFinish(uri6);
                    }
                };
                r1.execute();
                return;
            }
            intent.setDataAndType(uri3, "image/*");
            intent.putExtra(ASPECT_X, 1);
            intent.putExtra(ASPECT_Y, 1);
            intent.putExtra(CROP, "true");
            intent.putExtra("outputFormat", CompressFormat.PNG.toString());
            intent.putExtra(OUTPUT_X, i2);
            intent.putExtra(OUTPUT_Y, i2);
            intent.putExtra("return-data", true);
            intent.putExtra("output", uri4);
            if (size == 1) {
                navigateToImageCrop(intent, (ResolveInfo) queryIntentActivities.get(0));
                return;
            }
            new MfpAlertDialogBuilder(context).setTitle((CharSequence) resources.getString(R.string.choose_crop_app)).setAdapter(new CropOptionAdapter(context, (ArrayList) Enumerable.select((Collection<T>) queryIntentActivities, (ReturningFunction1<U, T>) new ReturningFunction1(packageManager) {
                private final /* synthetic */ PackageManager f$0;

                {
                    this.f$0 = r1;
                }

                public final Object execute(Object obj) {
                    return ImageCropHelper.lambda$cropImage$0(this.f$0, (ResolveInfo) obj);
                }
            })), new OnItemClickListener(intent, queryIntentActivities) {
                private final /* synthetic */ Intent f$1;
                private final /* synthetic */ List f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                    ImageCropHelper.this.navigateToImageCrop(this.f$1, (ResolveInfo) this.f$2.get(i));
                }
            }).setOnCancelListener(new OnCancelListener(uri3, function1, uri4) {
                private final /* synthetic */ Uri f$0;
                private final /* synthetic */ Function1 f$1;
                private final /* synthetic */ Uri f$2;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onCancel(DialogInterface dialogInterface) {
                    ImageCropHelper.lambda$cropImage$2(this.f$0, this.f$1, this.f$2, dialogInterface);
                }
            }).show();
        } catch (Exception e) {
            Ln.e(e);
            Toast.makeText(context, resources.getString(R.string.cannot_crop_image_and_saved), 0).show();
            cleanOnFinish(uri2);
        }
    }

    static /* synthetic */ CropOption lambda$cropImage$0(PackageManager packageManager, ResolveInfo resolveInfo) throws RuntimeException {
        CropOption cropOption = new CropOption();
        cropOption.title = packageManager.getApplicationLabel(resolveInfo.activityInfo.applicationInfo);
        cropOption.icon = packageManager.getApplicationIcon(resolveInfo.activityInfo.applicationInfo);
        cropOption.resolveInfo = resolveInfo;
        return cropOption;
    }

    static /* synthetic */ void lambda$cropImage$2(Uri uri, Function1 function1, Uri uri2, DialogInterface dialogInterface) {
        if (uri != null) {
            FunctionUtils.invokeIfValid(function1, null);
            cleanOnFinish(uri2);
        }
    }

    /* access modifiers changed from: private */
    public void navigateToImageCrop(Intent intent, ResolveInfo resolveInfo) {
        this.navigationHelper.withIntent(new Intent(intent).setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name))).startActivity(1002);
    }

    public static void cleanOnFinish(Uri uri) {
        if (uri != null && uri.getPath() != null) {
            File file = new File(uri.getPath());
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
