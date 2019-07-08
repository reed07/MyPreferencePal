package io.uacf.thumbprint.ui.internal.email;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog.Builder;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import io.uacf.thumbprint.ui.R;
import io.uacf.thumbprint.ui.internal.analytics.ThumbprintAnalyticsAttributes.ScreenClosed.Action;
import io.uacf.thumbprint.ui.internal.analytics.ThumbprintAnalyticsAttributes.ScreenViewed.Source;
import io.uacf.thumbprint.ui.internal.base.UacfBaseActivity;
import io.uacf.thumbprint.ui.internal.email.UacfEmailViewModel.State;
import java.util.Locale;

public final class UacfEmailVerificationActivity extends UacfBaseActivity {
    static boolean currentlyShowing;
    TextView description;
    TextView detailText;
    TextView editButton;
    TextView email;
    ExportType exportType;
    TextView label;
    TextView title;
    Type type;
    UacfEmailViewModel viewModel;

    public interface Actions {
        void editEmailClicked();
    }

    public enum ExportType {
        EXPORT_DIARY,
        EXPORT_PROGRESS
    }

    enum Type {
        FILE_EXPORT,
        APP_LAUNCH
    }

    public static void showOnFileExport(@NonNull Context context, @NonNull ExportType exportType2) {
        Intent intent = new Intent(context, UacfEmailVerificationActivity.class);
        intent.putExtra("TypeKey", Type.FILE_EXPORT);
        intent.putExtra("ExportTypeKey", exportType2);
        context.startActivity(intent);
    }

    public static void showOnAppLaunch(@NonNull Context context) {
        Intent intent = new Intent(context, UacfEmailVerificationActivity.class);
        intent.putExtra("TypeKey", Type.APP_LAUNCH);
        context.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        currentlyShowing = true;
        this.type = (Type) getIntent().getSerializableExtra("TypeKey");
        this.exportType = (ExportType) getIntent().getSerializableExtra("ExportTypeKey");
        this.viewModel = (UacfEmailViewModel) ViewModelProviders.of(this).get(UacfEmailViewModel.class);
        UacfEmailViewModel uacfEmailViewModel = this.viewModel;
        Source source = this.type == Type.APP_LAUNCH ? Source.APP_LAUNCH : this.exportType == ExportType.EXPORT_DIARY ? Source.EXPORT_DIARY : Source.EXPORT_PROGRESS;
        uacfEmailViewModel.reportEmailVerificationScreenViewedEvent(source);
        setActionBarTitleText(R.string.emailVerification_title);
        this.title = (TextView) findViewById(R.id.emailVerification_title);
        this.description = (TextView) findViewById(R.id.emailVerification_description);
        this.label = (TextView) findViewById(R.id.emailVerification_label);
        this.email = (TextView) findViewById(R.id.emailVerification_email);
        this.editButton = (TextView) findViewById(R.id.emailVerification_editButton);
        this.detailText = (TextView) findViewById(R.id.emailVerification_detailText);
        if (this.type == Type.APP_LAUNCH) {
            this.description.setText(getString(R.string.emailVerification_description_softBlock));
            setActionBarTitleCentered(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            this.viewModel.sendVerificationEmail();
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        this.editButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UacfEmailVerificationActivity.this.viewModel.editEmailClicked();
            }
        });
        createSendVerificationLink();
        setUpObservables();
    }

    private void createSendVerificationLink() {
        String string = getString(R.string.emailVerification_resendEmail_link);
        String string2 = getString(R.string.emailVerification_resendEmail, new Object[]{string});
        SpannableString spannableString = new SpannableString(string2);
        AnonymousClass2 r3 = new ClickableSpan() {
            public void onClick(View view) {
                UacfEmailVerificationActivity.this.viewModel.sendVerificationEmailClicked();
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
                textPaint.setColor(ContextCompat.getColor(UacfEmailVerificationActivity.this, R.color.link_blue));
            }
        };
        int indexOf = string2.indexOf(string);
        spannableString.setSpan(r3, indexOf, string.length() + indexOf, 33);
        this.detailText.setText(spannableString);
        this.detailText.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setUpObservables() {
        this.viewModel.getCurrentState().observe(this, new Observer<State>() {
            public void onChanged(@Nullable State state) {
                if (state == State.EMAIL_VERIFIED) {
                    UacfEmailVerificationActivity.this.showVerifiedDialog();
                }
            }
        });
        this.viewModel.getCurrentEmail().observe(this, new Observer<String>() {
            public void onChanged(@Nullable String str) {
                UacfEmailVerificationActivity.this.email.setText(str);
            }
        });
        this.viewModel.getShowBottomBarMessage().observe(this, new Observer<String>() {
            public void onChanged(@Nullable String str) {
                UacfEmailVerificationActivity.this.showBottomBarMessage(str);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.viewModel.startPolling();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.viewModel.stopPolling();
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        currentlyShowing = false;
        super.onDestroy();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.type == Type.APP_LAUNCH) {
            getMenuInflater().inflate(R.menu.verify_email_menu, menu);
            View actionView = menu.findItem(R.id.not_now).getActionView();
            TextView textView = (TextView) actionView.findViewById(R.id.not_now_menu_item_text);
            ImageView imageView = (ImageView) actionView.findViewById(R.id.not_now_menu_item_icon);
            AnonymousClass6 r2 = new OnClickListener() {
                public void onClick(View view) {
                    UacfEmailVerificationActivity.this.viewModel.reportEmailVerificationScreenClosedEvent(Action.NOT_NOW);
                    UacfEmailVerificationActivity.this.finish();
                }
            };
            textView.setOnClickListener(r2);
            imageView.setOnClickListener(r2);
            boolean equals = Locale.getDefault().getLanguage().equals(Locale.ENGLISH.getLanguage());
            int i = 0;
            textView.setVisibility(equals ? 0 : 8);
            if (equals) {
                i = 8;
            }
            imageView.setVisibility(i);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            this.viewModel.reportEmailVerificationScreenClosedEvent(Action.BACK);
            finish();
        }
        return true;
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.viewModel.reportEmailVerificationScreenClosedEvent(Action.BACK);
        finish();
    }

    public int getLayoutResourceId() {
        return R.layout.activity_email_verification;
    }

    /* access modifiers changed from: 0000 */
    public void showBottomBarMessage(String str) {
        final Snackbar make = Snackbar.make(findViewById(R.id.emailVerification_root), (CharSequence) str, -1);
        make.setAction((CharSequence) getString(R.string.action_ok), (OnClickListener) new OnClickListener() {
            public void onClick(View view) {
                make.dismiss();
            }
        });
        make.show();
    }

    /* access modifiers changed from: 0000 */
    public void showVerifiedDialog() {
        new Builder(this).setMessage(R.string.emailVerification_userVerifiedAlert).setPositiveButton((CharSequence) getString(R.string.action_ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                UacfEmailVerificationActivity.this.viewModel.reportEmailVerificationScreenClosedEvent(Action.VERIFIED);
                dialogInterface.dismiss();
                UacfEmailVerificationActivity.this.finish();
            }
        }).setCancelable(false).show();
    }
}
