package com.facebook.share.widget;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.brightcove.player.captioning.preferences.CaptionConstants;
import com.facebook.FacebookException;
import com.facebook.common.R;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.FragmentWrapper;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.share.internal.LikeActionController;
import com.facebook.share.internal.LikeActionController.CreationCallback;
import com.facebook.share.internal.LikeBoxCountView;
import com.facebook.share.internal.LikeBoxCountView.LikeBoxCountViewCaretPosition;
import com.facebook.share.internal.LikeButton;
import com.myfitnesspal.shared.constants.Constants.Ads.Keywords;

@Deprecated
public class LikeView extends FrameLayout {
    private static final int NO_FOREGROUND_COLOR = -1;
    private AuxiliaryViewPosition auxiliaryViewPosition = AuxiliaryViewPosition.DEFAULT;
    private BroadcastReceiver broadcastReceiver;
    private LinearLayout containerView;
    /* access modifiers changed from: private */
    public LikeActionControllerCreationCallback creationCallback;
    private int edgePadding;
    private boolean explicitlyDisabled = true;
    private int foregroundColor = -1;
    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.DEFAULT;
    private int internalPadding;
    private LikeActionController likeActionController;
    private LikeBoxCountView likeBoxCountView;
    private LikeButton likeButton;
    private Style likeViewStyle = Style.DEFAULT;
    /* access modifiers changed from: private */
    public String objectId;
    /* access modifiers changed from: private */
    public ObjectType objectType;
    /* access modifiers changed from: private */
    public OnErrorListener onErrorListener;
    private FragmentWrapper parentFragment;
    private TextView socialSentenceView;

    @Deprecated
    public enum AuxiliaryViewPosition {
        BOTTOM("bottom", 0),
        INLINE("inline", 1),
        TOP(Keywords.POSITION_TOP, 2);
        
        static AuxiliaryViewPosition DEFAULT;
        private int intValue;
        private String stringValue;

        static {
            AuxiliaryViewPosition auxiliaryViewPosition;
            DEFAULT = auxiliaryViewPosition;
        }

        static AuxiliaryViewPosition fromInt(int i) {
            AuxiliaryViewPosition[] values;
            for (AuxiliaryViewPosition auxiliaryViewPosition : values()) {
                if (auxiliaryViewPosition.getValue() == i) {
                    return auxiliaryViewPosition;
                }
            }
            return null;
        }

        private AuxiliaryViewPosition(String str, int i) {
            this.stringValue = str;
            this.intValue = i;
        }

        public String toString() {
            return this.stringValue;
        }

        /* access modifiers changed from: private */
        public int getValue() {
            return this.intValue;
        }
    }

    @Deprecated
    public enum HorizontalAlignment {
        CENTER(TtmlNode.CENTER, 0),
        LEFT(TtmlNode.LEFT, 1),
        RIGHT(TtmlNode.RIGHT, 2);
        
        static HorizontalAlignment DEFAULT;
        private int intValue;
        private String stringValue;

        static {
            HorizontalAlignment horizontalAlignment;
            DEFAULT = horizontalAlignment;
        }

        static HorizontalAlignment fromInt(int i) {
            HorizontalAlignment[] values;
            for (HorizontalAlignment horizontalAlignment : values()) {
                if (horizontalAlignment.getValue() == i) {
                    return horizontalAlignment;
                }
            }
            return null;
        }

        private HorizontalAlignment(String str, int i) {
            this.stringValue = str;
            this.intValue = i;
        }

        public String toString() {
            return this.stringValue;
        }

        /* access modifiers changed from: private */
        public int getValue() {
            return this.intValue;
        }
    }

    private class LikeActionControllerCreationCallback implements CreationCallback {
        private boolean isCancelled;

        private LikeActionControllerCreationCallback() {
        }

        public void cancel() {
            this.isCancelled = true;
        }

        public void onComplete(LikeActionController likeActionController, FacebookException facebookException) {
            if (!this.isCancelled) {
                if (likeActionController != null) {
                    if (!likeActionController.shouldEnableView()) {
                        facebookException = new FacebookException("Cannot use LikeView. The device may not be supported.");
                    }
                    LikeView.this.associateWithLikeActionController(likeActionController);
                    LikeView.this.updateLikeStateAndLayout();
                }
                if (!(facebookException == null || LikeView.this.onErrorListener == null)) {
                    LikeView.this.onErrorListener.onError(facebookException);
                }
                LikeView.this.creationCallback = null;
            }
        }
    }

    private class LikeControllerBroadcastReceiver extends BroadcastReceiver {
        private LikeControllerBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Bundle extras = intent.getExtras();
            boolean z = true;
            if (extras != null) {
                String string = extras.getString(LikeActionController.ACTION_OBJECT_ID_KEY);
                if (!Utility.isNullOrEmpty(string) && !Utility.areObjectsEqual(LikeView.this.objectId, string)) {
                    z = false;
                }
            }
            if (z) {
                if (LikeActionController.ACTION_LIKE_ACTION_CONTROLLER_UPDATED.equals(action)) {
                    LikeView.this.updateLikeStateAndLayout();
                } else if (LikeActionController.ACTION_LIKE_ACTION_CONTROLLER_DID_ERROR.equals(action)) {
                    if (LikeView.this.onErrorListener != null) {
                        LikeView.this.onErrorListener.onError(NativeProtocol.getExceptionFromErrorData(extras));
                    }
                } else if (LikeActionController.ACTION_LIKE_ACTION_CONTROLLER_DID_RESET.equals(action)) {
                    LikeView likeView = LikeView.this;
                    likeView.setObjectIdAndTypeForced(likeView.objectId, LikeView.this.objectType);
                    LikeView.this.updateLikeStateAndLayout();
                }
            }
        }
    }

    @Deprecated
    public enum ObjectType {
        UNKNOWN("unknown", 0),
        OPEN_GRAPH("open_graph", 1),
        PAGE("page", 2);
        
        public static ObjectType DEFAULT;
        private int intValue;
        private String stringValue;

        static {
            ObjectType objectType;
            DEFAULT = objectType;
        }

        public static ObjectType fromInt(int i) {
            ObjectType[] values;
            for (ObjectType objectType : values()) {
                if (objectType.getValue() == i) {
                    return objectType;
                }
            }
            return null;
        }

        private ObjectType(String str, int i) {
            this.stringValue = str;
            this.intValue = i;
        }

        public String toString() {
            return this.stringValue;
        }

        public int getValue() {
            return this.intValue;
        }
    }

    public interface OnErrorListener {
        void onError(FacebookException facebookException);
    }

    @Deprecated
    public enum Style {
        STANDARD(CaptionConstants.PREF_STANDARD, 0),
        BUTTON("button", 1),
        BOX_COUNT("box_count", 2);
        
        static Style DEFAULT;
        private int intValue;
        private String stringValue;

        static {
            Style style;
            DEFAULT = style;
        }

        static Style fromInt(int i) {
            Style[] values;
            for (Style style : values()) {
                if (style.getValue() == i) {
                    return style;
                }
            }
            return null;
        }

        private Style(String str, int i) {
            this.stringValue = str;
            this.intValue = i;
        }

        public String toString() {
            return this.stringValue;
        }

        /* access modifiers changed from: private */
        public int getValue() {
            return this.intValue;
        }
    }

    @Deprecated
    public LikeView(Context context) {
        super(context);
        initialize(context);
    }

    @Deprecated
    public LikeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        parseAttributes(attributeSet);
        initialize(context);
    }

    @Deprecated
    public void setObjectIdAndType(String str, ObjectType objectType2) {
        String coerceValueIfNullOrEmpty = Utility.coerceValueIfNullOrEmpty(str, null);
        if (objectType2 == null) {
            objectType2 = ObjectType.DEFAULT;
        }
        if (!Utility.areObjectsEqual(coerceValueIfNullOrEmpty, this.objectId) || objectType2 != this.objectType) {
            setObjectIdAndTypeForced(coerceValueIfNullOrEmpty, objectType2);
            updateLikeStateAndLayout();
        }
    }

    @Deprecated
    public void setLikeViewStyle(Style style) {
        if (style == null) {
            style = Style.DEFAULT;
        }
        if (this.likeViewStyle != style) {
            this.likeViewStyle = style;
            updateLayout();
        }
    }

    @Deprecated
    public void setAuxiliaryViewPosition(AuxiliaryViewPosition auxiliaryViewPosition2) {
        if (auxiliaryViewPosition2 == null) {
            auxiliaryViewPosition2 = AuxiliaryViewPosition.DEFAULT;
        }
        if (this.auxiliaryViewPosition != auxiliaryViewPosition2) {
            this.auxiliaryViewPosition = auxiliaryViewPosition2;
            updateLayout();
        }
    }

    @Deprecated
    public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment2) {
        if (horizontalAlignment2 == null) {
            horizontalAlignment2 = HorizontalAlignment.DEFAULT;
        }
        if (this.horizontalAlignment != horizontalAlignment2) {
            this.horizontalAlignment = horizontalAlignment2;
            updateLayout();
        }
    }

    @Deprecated
    public void setForegroundColor(int i) {
        if (this.foregroundColor != i) {
            this.socialSentenceView.setTextColor(i);
        }
    }

    @Deprecated
    public void setOnErrorListener(OnErrorListener onErrorListener2) {
        this.onErrorListener = onErrorListener2;
    }

    @Deprecated
    public OnErrorListener getOnErrorListener() {
        return this.onErrorListener;
    }

    @Deprecated
    public void setFragment(Fragment fragment) {
        this.parentFragment = new FragmentWrapper(fragment);
    }

    @Deprecated
    public void setFragment(android.app.Fragment fragment) {
        this.parentFragment = new FragmentWrapper(fragment);
    }

    @Deprecated
    public void setEnabled(boolean z) {
        this.explicitlyDisabled = true;
        updateLikeStateAndLayout();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        setObjectIdAndType(null, ObjectType.UNKNOWN);
        super.onDetachedFromWindow();
    }

    private void parseAttributes(AttributeSet attributeSet) {
        if (attributeSet != null && getContext() != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.com_facebook_like_view);
            if (obtainStyledAttributes != null) {
                this.objectId = Utility.coerceValueIfNullOrEmpty(obtainStyledAttributes.getString(R.styleable.com_facebook_like_view_com_facebook_object_id), null);
                this.objectType = ObjectType.fromInt(obtainStyledAttributes.getInt(R.styleable.com_facebook_like_view_com_facebook_object_type, ObjectType.DEFAULT.getValue()));
                this.likeViewStyle = Style.fromInt(obtainStyledAttributes.getInt(R.styleable.com_facebook_like_view_com_facebook_style, Style.DEFAULT.getValue()));
                if (this.likeViewStyle != null) {
                    this.auxiliaryViewPosition = AuxiliaryViewPosition.fromInt(obtainStyledAttributes.getInt(R.styleable.com_facebook_like_view_com_facebook_auxiliary_view_position, AuxiliaryViewPosition.DEFAULT.getValue()));
                    if (this.auxiliaryViewPosition != null) {
                        this.horizontalAlignment = HorizontalAlignment.fromInt(obtainStyledAttributes.getInt(R.styleable.com_facebook_like_view_com_facebook_horizontal_alignment, HorizontalAlignment.DEFAULT.getValue()));
                        if (this.horizontalAlignment != null) {
                            this.foregroundColor = obtainStyledAttributes.getColor(R.styleable.com_facebook_like_view_com_facebook_foreground_color, -1);
                            obtainStyledAttributes.recycle();
                            return;
                        }
                        throw new IllegalArgumentException("Unsupported value for LikeView 'horizontal_alignment'");
                    }
                    throw new IllegalArgumentException("Unsupported value for LikeView 'auxiliary_view_position'");
                }
                throw new IllegalArgumentException("Unsupported value for LikeView 'style'");
            }
        }
    }

    private void initialize(Context context) {
        this.edgePadding = getResources().getDimensionPixelSize(R.dimen.com_facebook_likeview_edge_padding);
        this.internalPadding = getResources().getDimensionPixelSize(R.dimen.com_facebook_likeview_internal_padding);
        if (this.foregroundColor == -1) {
            this.foregroundColor = getResources().getColor(R.color.com_facebook_likeview_text_color);
        }
        setBackgroundColor(0);
        this.containerView = new LinearLayout(context);
        this.containerView.setLayoutParams(new LayoutParams(-2, -2));
        initializeLikeButton(context);
        initializeSocialSentenceView(context);
        initializeLikeCountView(context);
        this.containerView.addView(this.likeButton);
        this.containerView.addView(this.socialSentenceView);
        this.containerView.addView(this.likeBoxCountView);
        addView(this.containerView);
        setObjectIdAndTypeForced(this.objectId, this.objectType);
        updateLikeStateAndLayout();
    }

    private void initializeLikeButton(Context context) {
        LikeActionController likeActionController2 = this.likeActionController;
        this.likeButton = new LikeButton(context, likeActionController2 != null && likeActionController2.isObjectLiked());
        this.likeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                LikeView.this.toggleLike();
            }
        });
        this.likeButton.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
    }

    private void initializeSocialSentenceView(Context context) {
        this.socialSentenceView = new TextView(context);
        this.socialSentenceView.setTextSize(0, getResources().getDimension(R.dimen.com_facebook_likeview_text_size));
        this.socialSentenceView.setMaxLines(2);
        this.socialSentenceView.setTextColor(this.foregroundColor);
        this.socialSentenceView.setGravity(17);
        this.socialSentenceView.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
    }

    private void initializeLikeCountView(Context context) {
        this.likeBoxCountView = new LikeBoxCountView(context);
        this.likeBoxCountView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    }

    /* access modifiers changed from: private */
    public void toggleLike() {
        if (this.likeActionController != null) {
            Activity activity = null;
            if (this.parentFragment == null) {
                activity = getActivity();
            }
            this.likeActionController.toggleLike(activity, this.parentFragment, getAnalyticsParameters());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0018  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.app.Activity getActivity() {
        /*
            r3 = this;
            android.content.Context r0 = r3.getContext()
        L_0x0004:
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 != 0) goto L_0x0013
            boolean r2 = r0 instanceof android.content.ContextWrapper
            if (r2 == 0) goto L_0x0013
            android.content.ContextWrapper r0 = (android.content.ContextWrapper) r0
            android.content.Context r0 = r0.getBaseContext()
            goto L_0x0004
        L_0x0013:
            if (r1 == 0) goto L_0x0018
            android.app.Activity r0 = (android.app.Activity) r0
            return r0
        L_0x0018:
            com.facebook.FacebookException r0 = new com.facebook.FacebookException
            java.lang.String r1 = "Unable to get Activity."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.share.widget.LikeView.getActivity():android.app.Activity");
    }

    private Bundle getAnalyticsParameters() {
        Bundle bundle = new Bundle();
        bundle.putString("style", this.likeViewStyle.toString());
        bundle.putString(AnalyticsEvents.PARAMETER_LIKE_VIEW_AUXILIARY_POSITION, this.auxiliaryViewPosition.toString());
        bundle.putString(AnalyticsEvents.PARAMETER_LIKE_VIEW_HORIZONTAL_ALIGNMENT, this.horizontalAlignment.toString());
        bundle.putString("object_id", Utility.coerceValueIfNullOrEmpty(this.objectId, ""));
        bundle.putString("object_type", this.objectType.toString());
        return bundle;
    }

    /* access modifiers changed from: private */
    public void setObjectIdAndTypeForced(String str, ObjectType objectType2) {
        tearDownObjectAssociations();
        this.objectId = str;
        this.objectType = objectType2;
        if (!Utility.isNullOrEmpty(str)) {
            this.creationCallback = new LikeActionControllerCreationCallback();
            if (!isInEditMode()) {
                LikeActionController.getControllerForObjectId(str, objectType2, this.creationCallback);
            }
        }
    }

    /* access modifiers changed from: private */
    public void associateWithLikeActionController(LikeActionController likeActionController2) {
        this.likeActionController = likeActionController2;
        this.broadcastReceiver = new LikeControllerBroadcastReceiver();
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(getContext());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LikeActionController.ACTION_LIKE_ACTION_CONTROLLER_UPDATED);
        intentFilter.addAction(LikeActionController.ACTION_LIKE_ACTION_CONTROLLER_DID_ERROR);
        intentFilter.addAction(LikeActionController.ACTION_LIKE_ACTION_CONTROLLER_DID_RESET);
        instance.registerReceiver(this.broadcastReceiver, intentFilter);
    }

    private void tearDownObjectAssociations() {
        if (this.broadcastReceiver != null) {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.broadcastReceiver);
            this.broadcastReceiver = null;
        }
        LikeActionControllerCreationCallback likeActionControllerCreationCallback = this.creationCallback;
        if (likeActionControllerCreationCallback != null) {
            likeActionControllerCreationCallback.cancel();
            this.creationCallback = null;
        }
        this.likeActionController = null;
    }

    /* access modifiers changed from: private */
    public void updateLikeStateAndLayout() {
        boolean z = !this.explicitlyDisabled;
        LikeActionController likeActionController2 = this.likeActionController;
        if (likeActionController2 == null) {
            this.likeButton.setSelected(false);
            this.socialSentenceView.setText(null);
            this.likeBoxCountView.setText(null);
        } else {
            this.likeButton.setSelected(likeActionController2.isObjectLiked());
            this.socialSentenceView.setText(this.likeActionController.getSocialSentence());
            this.likeBoxCountView.setText(this.likeActionController.getLikeCountString());
            z &= this.likeActionController.shouldEnableView();
        }
        super.setEnabled(z);
        this.likeButton.setEnabled(z);
        updateLayout();
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateLayout() {
        /*
            r6 = this;
            android.widget.LinearLayout r0 = r6.containerView
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            android.widget.FrameLayout$LayoutParams r0 = (android.widget.FrameLayout.LayoutParams) r0
            com.facebook.share.internal.LikeButton r1 = r6.likeButton
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r1 = (android.widget.LinearLayout.LayoutParams) r1
            com.facebook.share.widget.LikeView$HorizontalAlignment r2 = r6.horizontalAlignment
            com.facebook.share.widget.LikeView$HorizontalAlignment r3 = com.facebook.share.widget.LikeView.HorizontalAlignment.LEFT
            r4 = 1
            if (r2 != r3) goto L_0x0019
            r2 = 3
            goto L_0x0022
        L_0x0019:
            com.facebook.share.widget.LikeView$HorizontalAlignment r2 = r6.horizontalAlignment
            com.facebook.share.widget.LikeView$HorizontalAlignment r3 = com.facebook.share.widget.LikeView.HorizontalAlignment.CENTER
            if (r2 != r3) goto L_0x0021
            r2 = 1
            goto L_0x0022
        L_0x0021:
            r2 = 5
        L_0x0022:
            r3 = r2 | 48
            r0.gravity = r3
            r1.gravity = r2
            android.widget.TextView r0 = r6.socialSentenceView
            r1 = 8
            r0.setVisibility(r1)
            com.facebook.share.internal.LikeBoxCountView r0 = r6.likeBoxCountView
            r0.setVisibility(r1)
            com.facebook.share.widget.LikeView$Style r0 = r6.likeViewStyle
            com.facebook.share.widget.LikeView$Style r1 = com.facebook.share.widget.LikeView.Style.STANDARD
            if (r0 != r1) goto L_0x004b
            com.facebook.share.internal.LikeActionController r0 = r6.likeActionController
            if (r0 == 0) goto L_0x004b
            java.lang.String r0 = r0.getSocialSentence()
            boolean r0 = com.facebook.internal.Utility.isNullOrEmpty(r0)
            if (r0 != 0) goto L_0x004b
            android.widget.TextView r0 = r6.socialSentenceView
            goto L_0x0064
        L_0x004b:
            com.facebook.share.widget.LikeView$Style r0 = r6.likeViewStyle
            com.facebook.share.widget.LikeView$Style r1 = com.facebook.share.widget.LikeView.Style.BOX_COUNT
            if (r0 != r1) goto L_0x00dd
            com.facebook.share.internal.LikeActionController r0 = r6.likeActionController
            if (r0 == 0) goto L_0x00dd
            java.lang.String r0 = r0.getLikeCountString()
            boolean r0 = com.facebook.internal.Utility.isNullOrEmpty(r0)
            if (r0 != 0) goto L_0x00dd
            r6.updateBoxCountCaretPosition()
            com.facebook.share.internal.LikeBoxCountView r0 = r6.likeBoxCountView
        L_0x0064:
            r1 = 0
            r0.setVisibility(r1)
            android.view.ViewGroup$LayoutParams r3 = r0.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r3 = (android.widget.LinearLayout.LayoutParams) r3
            r3.gravity = r2
            android.widget.LinearLayout r2 = r6.containerView
            com.facebook.share.widget.LikeView$AuxiliaryViewPosition r3 = r6.auxiliaryViewPosition
            com.facebook.share.widget.LikeView$AuxiliaryViewPosition r5 = com.facebook.share.widget.LikeView.AuxiliaryViewPosition.INLINE
            if (r3 != r5) goto L_0x0079
            goto L_0x007a
        L_0x0079:
            r1 = 1
        L_0x007a:
            r2.setOrientation(r1)
            com.facebook.share.widget.LikeView$AuxiliaryViewPosition r1 = r6.auxiliaryViewPosition
            com.facebook.share.widget.LikeView$AuxiliaryViewPosition r2 = com.facebook.share.widget.LikeView.AuxiliaryViewPosition.TOP
            if (r1 == r2) goto L_0x009b
            com.facebook.share.widget.LikeView$AuxiliaryViewPosition r1 = r6.auxiliaryViewPosition
            com.facebook.share.widget.LikeView$AuxiliaryViewPosition r2 = com.facebook.share.widget.LikeView.AuxiliaryViewPosition.INLINE
            if (r1 != r2) goto L_0x0090
            com.facebook.share.widget.LikeView$HorizontalAlignment r1 = r6.horizontalAlignment
            com.facebook.share.widget.LikeView$HorizontalAlignment r2 = com.facebook.share.widget.LikeView.HorizontalAlignment.RIGHT
            if (r1 != r2) goto L_0x0090
            goto L_0x009b
        L_0x0090:
            android.widget.LinearLayout r1 = r6.containerView
            r1.removeView(r0)
            android.widget.LinearLayout r1 = r6.containerView
            r1.addView(r0)
            goto L_0x00a9
        L_0x009b:
            android.widget.LinearLayout r1 = r6.containerView
            com.facebook.share.internal.LikeButton r2 = r6.likeButton
            r1.removeView(r2)
            android.widget.LinearLayout r1 = r6.containerView
            com.facebook.share.internal.LikeButton r2 = r6.likeButton
            r1.addView(r2)
        L_0x00a9:
            int[] r1 = com.facebook.share.widget.LikeView.AnonymousClass2.$SwitchMap$com$facebook$share$widget$LikeView$AuxiliaryViewPosition
            com.facebook.share.widget.LikeView$AuxiliaryViewPosition r2 = r6.auxiliaryViewPosition
            int r2 = r2.ordinal()
            r1 = r1[r2]
            switch(r1) {
                case 1: goto L_0x00d5;
                case 2: goto L_0x00cd;
                case 3: goto L_0x00b7;
                default: goto L_0x00b6;
            }
        L_0x00b6:
            goto L_0x00dc
        L_0x00b7:
            com.facebook.share.widget.LikeView$HorizontalAlignment r1 = r6.horizontalAlignment
            com.facebook.share.widget.LikeView$HorizontalAlignment r2 = com.facebook.share.widget.LikeView.HorizontalAlignment.RIGHT
            if (r1 != r2) goto L_0x00c5
            int r1 = r6.edgePadding
            int r2 = r6.internalPadding
            r0.setPadding(r1, r1, r2, r1)
            goto L_0x00dc
        L_0x00c5:
            int r1 = r6.internalPadding
            int r2 = r6.edgePadding
            r0.setPadding(r1, r2, r2, r2)
            goto L_0x00dc
        L_0x00cd:
            int r1 = r6.edgePadding
            int r2 = r6.internalPadding
            r0.setPadding(r1, r2, r1, r1)
            goto L_0x00dc
        L_0x00d5:
            int r1 = r6.edgePadding
            int r2 = r6.internalPadding
            r0.setPadding(r1, r1, r1, r2)
        L_0x00dc:
            return
        L_0x00dd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.share.widget.LikeView.updateLayout():void");
    }

    private void updateBoxCountCaretPosition() {
        switch (this.auxiliaryViewPosition) {
            case TOP:
                this.likeBoxCountView.setCaretPosition(LikeBoxCountViewCaretPosition.BOTTOM);
                return;
            case BOTTOM:
                this.likeBoxCountView.setCaretPosition(LikeBoxCountViewCaretPosition.TOP);
                return;
            case INLINE:
                this.likeBoxCountView.setCaretPosition(this.horizontalAlignment == HorizontalAlignment.RIGHT ? LikeBoxCountViewCaretPosition.RIGHT : LikeBoxCountViewCaretPosition.LEFT);
                return;
            default:
                return;
        }
    }
}
