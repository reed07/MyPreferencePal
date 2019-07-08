package com.twitter.sdk.android.tweetcomposer;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.internal.UserUtils;
import com.twitter.sdk.android.core.internal.UserUtils.AvatarSize;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.tweetcomposer.internal.util.ObservableScrollView;
import com.twitter.sdk.android.tweetcomposer.internal.util.ObservableScrollView.ScrollViewListener;
import java.util.Locale;

public class ComposerView extends LinearLayout {
    ImageView avatarView;
    ComposerCallbacks callbacks;
    TextView charCountView;
    ImageView closeView;
    View divider;
    private Picasso imageLoader;
    ImageView imageView;
    ColorDrawable mediaBg;
    ObservableScrollView scrollView;
    Button tweetButton;
    EditText tweetEditView;

    public ComposerView(Context context) {
        this(context, null);
    }

    public ComposerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ComposerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.imageLoader = Picasso.with(getContext());
        this.mediaBg = new ColorDrawable(context.getResources().getColor(R.color.tw__composer_light_gray));
        inflate(context, R.layout.tw__composer_view, this);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        findSubviews();
        this.closeView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ComposerView.this.callbacks.onCloseClick();
            }
        });
        this.tweetButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ComposerView.this.callbacks.onTweetPost(ComposerView.this.getTweetText());
            }
        });
        this.tweetEditView.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                ComposerView.this.callbacks.onTweetPost(ComposerView.this.getTweetText());
                return true;
            }
        });
        this.tweetEditView.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                ComposerView.this.callbacks.onTextChanged(ComposerView.this.getTweetText());
            }
        });
        this.scrollView.setScrollViewListener(new ScrollViewListener() {
            public void onScrollChanged(int i) {
                if (i > 0) {
                    ComposerView.this.divider.setVisibility(0);
                } else {
                    ComposerView.this.divider.setVisibility(4);
                }
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void findSubviews() {
        this.avatarView = (ImageView) findViewById(R.id.tw__author_avatar);
        this.closeView = (ImageView) findViewById(R.id.tw__composer_close);
        this.tweetEditView = (EditText) findViewById(R.id.tw__edit_tweet);
        this.charCountView = (TextView) findViewById(R.id.tw__char_count);
        this.tweetButton = (Button) findViewById(R.id.tw__post_tweet);
        this.scrollView = (ObservableScrollView) findViewById(R.id.tw__composer_scroll_view);
        this.divider = findViewById(R.id.tw__composer_profile_divider);
        this.imageView = (ImageView) findViewById(R.id.tw__image_view);
    }

    /* access modifiers changed from: 0000 */
    public void setCallbacks(ComposerCallbacks composerCallbacks) {
        this.callbacks = composerCallbacks;
    }

    /* access modifiers changed from: 0000 */
    public void setProfilePhotoView(User user) {
        String profileImageUrlHttps = UserUtils.getProfileImageUrlHttps(user, AvatarSize.REASONABLY_SMALL);
        Picasso picasso = this.imageLoader;
        if (picasso != null) {
            picasso.load(profileImageUrlHttps).placeholder((Drawable) this.mediaBg).into(this.avatarView);
        }
    }

    /* access modifiers changed from: 0000 */
    public String getTweetText() {
        return this.tweetEditView.getText().toString();
    }

    /* access modifiers changed from: 0000 */
    public void setTweetText(String str) {
        this.tweetEditView.setText(str);
    }

    /* access modifiers changed from: 0000 */
    public void setCharCount(int i) {
        this.charCountView.setText(String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(i)}));
    }

    /* access modifiers changed from: 0000 */
    public void setCharCountTextStyle(int i) {
        this.charCountView.setTextAppearance(getContext(), i);
    }

    /* access modifiers changed from: 0000 */
    public void postTweetEnabled(boolean z) {
        this.tweetButton.setEnabled(z);
    }

    /* access modifiers changed from: 0000 */
    public void setImageView(Uri uri) {
        if (this.imageLoader != null) {
            this.imageView.setVisibility(0);
            this.imageLoader.load(uri).into(this.imageView);
        }
    }
}
