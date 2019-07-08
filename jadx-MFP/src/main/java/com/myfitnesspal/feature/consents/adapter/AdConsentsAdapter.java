package com.myfitnesspal.feature.consents.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.model.AdConsentsViewModel.Mode;
import com.myfitnesspal.shared.constants.Constants.Challenges;
import com.myfitnesspal.shared.util.SpanUtils;
import io.uacf.consentservices.sdk.UacfConsent;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0004#$%&Bc\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004\u0012\u001e\u0010\b\u001a\u001a\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\f0\t\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f0\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0019H\u0016J\u0018\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0019H\u0016J\u0018\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0019H\u0016J\b\u0010\"\u001a\u00020\u0007H\u0002R,\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R&\u0010\b\u001a\u001a\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\f0\tX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f0\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/myfitnesspal/feature/consents/adapter/AdConsentsAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "consents", "", "Lkotlin/Pair;", "Lio/uacf/consentservices/sdk/UacfConsent;", "", "onConsentChecked", "Lkotlin/Function3;", "", "Landroid/view/View;", "", "onUAAffiliatesClicked", "Landroid/view/View$OnClickListener;", "onLearnMoreClicked", "Lkotlin/Function1;", "mode", "Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel$Mode;", "(Ljava/util/List;Lkotlin/jvm/functions/Function3;Landroid/view/View$OnClickListener;Lkotlin/jvm/functions/Function1;Lcom/myfitnesspal/feature/consents/model/AdConsentsViewModel$Mode;)V", "getConsents", "()Ljava/util/List;", "setConsents", "(Ljava/util/List;)V", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "shouldShowConsents", "AdConsentHeaderViewHolder", "AdConsentViewHolder", "Companion", "UAAffiliatesViewHolder", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AdConsentsAdapter.kt */
public final class AdConsentsAdapter extends Adapter<ViewHolder> {
    private static final int CONSENT_VIEW = 1;
    public static final Companion Companion = new Companion(null);
    private static final int FOOTER_VIEW = 2;
    private static final int HEADER_AND_FOOTER_COUNT = 2;
    private static final int HEADER_VIEW = 0;
    @NotNull
    private List<? extends Pair<? extends UacfConsent, Boolean>> consents;
    private final Mode mode;
    private final Function3<String, View, Boolean, Unit> onConsentChecked;
    private final Function1<UacfConsent, Unit> onLearnMoreClicked;
    private final OnClickListener onUAAffiliatesClicked;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/consents/adapter/AdConsentsAdapter$AdConsentHeaderViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "bind", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AdConsentsAdapter.kt */
    public static final class AdConsentHeaderViewHolder extends ViewHolder {
        public AdConsentHeaderViewHolder(@NotNull View view) {
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            super(view);
        }

        public final void bind() {
            View view = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view, "itemView");
            ((TextView) view.findViewById(R.id.personalized_consent_header)).setText(R.string.personalized_consents_header);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JV\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u001e\u0010\u000b\u001a\u001a\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00060\f2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\u000f2\u0006\u0010\u0010\u001a\u00020\n¨\u0006\u0011"}, d2 = {"Lcom/myfitnesspal/feature/consents/adapter/AdConsentsAdapter$AdConsentViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "bind", "", "consent", "Lkotlin/Pair;", "Lio/uacf/consentservices/sdk/UacfConsent;", "", "onConsentChecked", "Lkotlin/Function3;", "", "onLearnedMoreClicked", "Lkotlin/Function1;", "shouldShowCheckBox", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AdConsentsAdapter.kt */
    public static final class AdConsentViewHolder extends ViewHolder {
        public AdConsentViewHolder(@NotNull View view) {
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            super(view);
        }

        public final void bind(@NotNull Pair<? extends UacfConsent, Boolean> pair, @NotNull Function3<? super String, ? super View, ? super Boolean, Unit> function3, @NotNull Function1<? super UacfConsent, Unit> function1, boolean z) {
            Intrinsics.checkParameterIsNotNull(pair, "consent");
            Intrinsics.checkParameterIsNotNull(function3, "onConsentChecked");
            Intrinsics.checkParameterIsNotNull(function1, "onLearnedMoreClicked");
            View view = this.itemView;
            TextView textView = (TextView) view.findViewById(R.id.title);
            Intrinsics.checkExpressionValueIsNotNull(textView, "title");
            textView.setText(((UacfConsent) pair.getFirst()).getTitle());
            TextView textView2 = (TextView) view.findViewById(R.id.details);
            Intrinsics.checkExpressionValueIsNotNull(textView2, Challenges.CHALLENGE_TAB_DETAILS);
            textView2.setText(((UacfConsent) pair.getFirst()).getContentSummary());
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            Intrinsics.checkExpressionValueIsNotNull(checkBox, "checkBox");
            checkBox.setVisibility(z ? 0 : 8);
            if (z) {
                CheckBox checkBox2 = (CheckBox) view.findViewById(R.id.checkBox);
                checkBox2.setOnCheckedChangeListener(null);
                Intrinsics.checkExpressionValueIsNotNull(checkBox2, "it");
                checkBox2.setChecked(((Boolean) pair.getSecond()).booleanValue());
                AdConsentsAdapter$AdConsentViewHolder$bind$$inlined$with$lambda$1 adConsentsAdapter$AdConsentViewHolder$bind$$inlined$with$lambda$1 = new AdConsentsAdapter$AdConsentViewHolder$bind$$inlined$with$lambda$1(view, pair, z, function3, function1);
                checkBox2.setOnCheckedChangeListener(adConsentsAdapter$AdConsentViewHolder$bind$$inlined$with$lambda$1);
            }
            TextView textView3 = (TextView) view.findViewById(R.id.more);
            textView3.setText(R.string.learn_more);
            textView3.setOnClickListener(new AdConsentsAdapter$AdConsentViewHolder$bind$$inlined$with$lambda$2(pair, z, function3, function1));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/feature/consents/adapter/AdConsentsAdapter$Companion;", "", "()V", "CONSENT_VIEW", "", "FOOTER_VIEW", "HEADER_AND_FOOTER_COUNT", "HEADER_VIEW", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AdConsentsAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/myfitnesspal/feature/consents/adapter/AdConsentsAdapter$UAAffiliatesViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "bind", "", "onUAAffiliatesClicked", "Landroid/view/View$OnClickListener;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AdConsentsAdapter.kt */
    public static final class UAAffiliatesViewHolder extends ViewHolder {
        public UAAffiliatesViewHolder(@NotNull View view) {
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            super(view);
        }

        public final void bind(@NotNull OnClickListener onClickListener) {
            Intrinsics.checkParameterIsNotNull(onClickListener, "onUAAffiliatesClicked");
            View view = this.itemView;
            TextView textView = (TextView) view.findViewById(R.id.about_ua_text);
            Intrinsics.checkExpressionValueIsNotNull(textView, "about_ua_text");
            textView.setGravity(8388611);
            SpanUtils.setSpannableTextOn((TextView) view.findViewById(R.id.about_ua_text), R.string.ua_affiliates, R.string.about_ua_affiliates, onClickListener);
        }
    }

    @NotNull
    public final List<Pair<UacfConsent, Boolean>> getConsents() {
        return this.consents;
    }

    public final void setConsents(@NotNull List<? extends Pair<? extends UacfConsent, Boolean>> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.consents = list;
    }

    public AdConsentsAdapter(@NotNull List<? extends Pair<? extends UacfConsent, Boolean>> list, @NotNull Function3<? super String, ? super View, ? super Boolean, Unit> function3, @NotNull OnClickListener onClickListener, @NotNull Function1<? super UacfConsent, Unit> function1, @NotNull Mode mode2) {
        Intrinsics.checkParameterIsNotNull(list, "consents");
        Intrinsics.checkParameterIsNotNull(function3, "onConsentChecked");
        Intrinsics.checkParameterIsNotNull(onClickListener, "onUAAffiliatesClicked");
        Intrinsics.checkParameterIsNotNull(function1, "onLearnMoreClicked");
        Intrinsics.checkParameterIsNotNull(mode2, InternalAvidAdSessionContext.CONTEXT_MODE);
        this.consents = list;
        this.onConsentChecked = function3;
        this.onUAAffiliatesClicked = onClickListener;
        this.onLearnMoreClicked = function1;
        this.mode = mode2;
    }

    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        switch (i) {
            case 1:
                View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.consent_item, viewGroup, false);
                Intrinsics.checkExpressionValueIsNotNull(inflate, Promotion.ACTION_VIEW);
                return new AdConsentViewHolder(inflate);
            case 2:
                View inflate2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.links_item, viewGroup, false);
                Intrinsics.checkExpressionValueIsNotNull(inflate2, Promotion.ACTION_VIEW);
                return new UAAffiliatesViewHolder(inflate2);
            default:
                View inflate3 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ad_consent_header, viewGroup, false);
                Intrinsics.checkExpressionValueIsNotNull(inflate3, Promotion.ACTION_VIEW);
                return new AdConsentHeaderViewHolder(inflate3);
        }
    }

    public int getItemCount() {
        if (this.consents.isEmpty()) {
            return 0;
        }
        return this.consents.size() + 2;
    }

    public void onBindViewHolder(@NotNull ViewHolder viewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "holder");
        int i2 = i - 1;
        if (viewHolder instanceof AdConsentViewHolder) {
            ((AdConsentViewHolder) viewHolder).bind((Pair) this.consents.get(i2), this.onConsentChecked, this.onLearnMoreClicked, shouldShowConsents());
        } else if (viewHolder instanceof UAAffiliatesViewHolder) {
            ((UAAffiliatesViewHolder) viewHolder).bind(this.onUAAffiliatesClicked);
        } else {
            if (!(viewHolder instanceof AdConsentHeaderViewHolder)) {
                viewHolder = null;
            }
            AdConsentHeaderViewHolder adConsentHeaderViewHolder = (AdConsentHeaderViewHolder) viewHolder;
            if (adConsentHeaderViewHolder != null) {
                adConsentHeaderViewHolder.bind();
            }
        }
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        return i == getItemCount() - 1 ? 2 : 1;
    }

    private final boolean shouldShowConsents() {
        if (this.mode != Mode.SETTINGS) {
            return this.consents.size() > 1 && this.mode == Mode.INTERRUPTION;
        }
        return true;
    }
}
