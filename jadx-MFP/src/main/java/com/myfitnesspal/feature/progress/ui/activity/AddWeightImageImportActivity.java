package com.myfitnesspal.feature.progress.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.activity.MfpActivity;

public class AddWeightImageImportActivity extends MfpActivity {
    private static final int SAVE_ACTION_ITEM = 100;
    private Context context;
    private Uri imageUri;
    @BindView(2131362797)
    ImageView imported_image;

    public static Intent newStartIntent(Context context2, Uri uri) {
        return new Intent(context2, AddWeightImageImportActivity.class).setData(uri);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.context = getApplicationContext();
        setContentView((int) R.layout.add_weight_import_activity);
        setupUI();
    }

    private void setupUI() {
        this.imageUri = getIntent().getData();
        Glide.with(this.context).load(this.imageUri.toString()).apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).error(R.drawable.ic_photo_camera_black_24dp).fitCenter()).into(this.imported_image);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuItemCompat.setShowAsAction(menu.add(0, 100, 0, R.string.save).setIcon(R.drawable.ic_check_white_24dp).setEnabled(true), 2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 100) {
            onSaveClicked();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void onSaveClicked() {
        Intent intent = new Intent();
        intent.setData(this.imageUri);
        setResult(-1, intent);
        finish();
    }
}
