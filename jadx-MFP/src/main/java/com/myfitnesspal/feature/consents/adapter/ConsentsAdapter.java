package com.myfitnesspal.feature.consents.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelperImpl;
import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper;
import com.myfitnesspal.feature.registration.util.SignUpUtil;
import com.myfitnesspal.shared.constants.Constants.Challenges;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.SpanUtils;
import dagger.Lazy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.uacf.consentservices.sdk.UacfConsent;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 !2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003!\"#Bw\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004\u0012\u001e\u0010\b\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\t\u0012\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0007\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0016J\u0018\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0016H\u0016J\u0018\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0016H\u0016J \u0010\u001f\u001a\u00020\u000b2\u0018\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004J\u0010\u0010 \u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0016H\u0002R \u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R&\u0010\b\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\tX\u0004¢\u0006\u0002\n\u0000R \u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/myfitnesspal/feature/consents/adapter/ConsentsAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "consents", "", "Lkotlin/Pair;", "Lio/uacf/consentservices/sdk/UacfConsent;", "", "onConsentChecked", "Lkotlin/Function3;", "Landroid/view/View;", "", "onLearnedMoreClicked", "Lkotlin/Function2;", "onUAAffiliatesClicked", "Landroid/view/View$OnClickListener;", "isManageConsents", "updatedTermsAnalyticsHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/registration/service/UpdatedTermsAnalyticsHelper;", "(Ljava/util/List;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Landroid/view/View$OnClickListener;ZLdagger/Lazy;)V", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setConsents", "shouldShowLinks", "Companion", "ConsentViewHolder", "LinksViewHolder", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ConsentsAdapter.kt */
public final class ConsentsAdapter extends Adapter<ViewHolder> {
    public static final Companion Companion = new Companion(null);
    private static final int VIEW_CONSENT = 0;
    private static final int VIEW_LINKS = 1;
    private List<? extends Pair<? extends UacfConsent, Boolean>> consents;
    private final boolean isManageConsents;
    private final Function3<UacfConsent, View, Boolean, Unit> onConsentChecked;
    private final Function2<UacfConsent, View, Unit> onLearnedMoreClicked;
    private final OnClickListener onUAAffiliatesClicked;
    private final Lazy<UpdatedTermsAnalyticsHelper> updatedTermsAnalyticsHelper;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/myfitnesspal/feature/consents/adapter/ConsentsAdapter$Companion;", "", "()V", "VIEW_CONSENT", "", "VIEW_LINKS", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ConsentsAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\\\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00062\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t2\u001e\u0010\f\u001a\u001a\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000e0\r2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000e0\u0010J*\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00030\t0\u00122\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\nH\u0002¨\u0006\u0015"}, d2 = {"Lcom/myfitnesspal/feature/consents/adapter/ConsentsAdapter$ConsentViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "bind", "Lio/reactivex/disposables/Disposable;", "kotlin.jvm.PlatformType", "consent", "Lkotlin/Pair;", "Lio/uacf/consentservices/sdk/UacfConsent;", "", "onConsentChecked", "Lkotlin/Function3;", "", "onLearnedMoreClicked", "Lkotlin/Function2;", "fromView", "Lio/reactivex/Observable;", "moreView", "uacfConsent", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ConsentsAdapter.kt */
    public static final class ConsentViewHolder extends ViewHolder {
        public ConsentViewHolder(@NotNull View view) {
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            super(view);
        }

        public final Disposable bind(@NotNull Pair<? extends UacfConsent, Boolean> pair, @NotNull Function3<? super UacfConsent, ? super View, ? super Boolean, Unit> function3, @NotNull Function2<? super UacfConsent, ? super View, Unit> function2) {
            Intrinsics.checkParameterIsNotNull(pair, "consent");
            Intrinsics.checkParameterIsNotNull(function3, "onConsentChecked");
            Intrinsics.checkParameterIsNotNull(function2, "onLearnedMoreClicked");
            View view = this.itemView;
            TextView textView = (TextView) view.findViewById(R.id.title);
            Intrinsics.checkExpressionValueIsNotNull(textView, "title");
            textView.setText(((UacfConsent) pair.getFirst()).getTitle());
            TextView textView2 = (TextView) view.findViewById(R.id.details);
            Intrinsics.checkExpressionValueIsNotNull(textView2, Challenges.CHALLENGE_TAB_DETAILS);
            textView2.setText(((UacfConsent) pair.getFirst()).getContentSummary());
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            Intrinsics.checkExpressionValueIsNotNull(checkBox, "checkBox");
            checkBox.setChecked(((Boolean) pair.getSecond()).booleanValue());
            ((CheckBox) view.findViewById(R.id.checkBox)).setOnCheckedChangeListener(new ConsentsAdapter$ConsentViewHolder$bind$$inlined$with$lambda$1(this, pair, function3, function2));
            TextView textView3 = (TextView) view.findViewById(R.id.more);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "more");
            return fromView(textView3, (UacfConsent) pair.getFirst()).debounce(500, TimeUnit.MILLISECONDS).subscribe((Consumer<? super T>) new ConsentsAdapter$ConsentViewHolder$bind$$inlined$with$lambda$2<Object>(this, pair, function3, function2));
        }

        private final Observable<Pair<UacfConsent, View>> fromView(View view, UacfConsent uacfConsent) {
            PublishSubject create = PublishSubject.create();
            Intrinsics.checkExpressionValueIsNotNull(create, "PublishSubject.create()");
            view.setOnClickListener(new ConsentsAdapter$ConsentViewHolder$fromView$1(this, create, uacfConsent));
            return create;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¨\u0006\u000e"}, d2 = {"Lcom/myfitnesspal/feature/consents/adapter/ConsentsAdapter$LinksViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "bind", "", "isManageConsents", "", "onUAAffiliatesClicked", "Landroid/view/View$OnClickListener;", "updatedTermsAnalyticsHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/registration/service/UpdatedTermsAnalyticsHelper;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ConsentsAdapter.kt */
    public static final class LinksViewHolder extends ViewHolder {
        public LinksViewHolder(@NotNull View view) {
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            super(view);
        }

        public final void bind(boolean z, @NotNull OnClickListener onClickListener, @NotNull Lazy<UpdatedTermsAnalyticsHelper> lazy) {
            Intrinsics.checkParameterIsNotNull(onClickListener, "onUAAffiliatesClicked");
            Intrinsics.checkParameterIsNotNull(lazy, "updatedTermsAnalyticsHelper");
            View view = this.itemView;
            View view2 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view2, "itemView");
            Context context = view2.getContext();
            if (context != null) {
                MfpActivity mfpActivity = (MfpActivity) context;
                if (z) {
                    SignUpUtil.setupDisclaimerTextForGDPR((TextView) view.findViewById(R.id.pp_terms_text), mfpActivity.getNavigationHelper(), R.string.terms_pp_text, lazy, ConsentsAnalyticsHelperImpl.PRIVACY_CENTER_CONSENTS_SEE);
                    TextView textView = (TextView) view.findViewById(R.id.pp_terms_text);
                    Intrinsics.checkExpressionValueIsNotNull(textView, "pp_terms_text");
                    textView.setVisibility(0);
                }
                SpanUtils.setSpannableTextOn((TextView) view.findViewById(R.id.about_ua_text), R.string.ua_affiliates, R.string.about_ua_affiliates, onClickListener);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.myfitnesspal.shared.ui.activity.MfpActivity");
        }
    }

    public ConsentsAdapter(@NotNull List<? extends Pair<? extends UacfConsent, Boolean>> list, @NotNull Function3<? super UacfConsent, ? super View, ? super Boolean, Unit> function3, @NotNull Function2<? super UacfConsent, ? super View, Unit> function2, @NotNull OnClickListener onClickListener, boolean z, @NotNull Lazy<UpdatedTermsAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(list, "consents");
        Intrinsics.checkParameterIsNotNull(function3, "onConsentChecked");
        Intrinsics.checkParameterIsNotNull(function2, "onLearnedMoreClicked");
        Intrinsics.checkParameterIsNotNull(onClickListener, "onUAAffiliatesClicked");
        Intrinsics.checkParameterIsNotNull(lazy, "updatedTermsAnalyticsHelper");
        this.consents = list;
        this.onConsentChecked = function3;
        this.onLearnedMoreClicked = function2;
        this.onUAAffiliatesClicked = onClickListener;
        this.isManageConsents = z;
        this.updatedTermsAnalyticsHelper = lazy;
    }

    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        if (i == 0) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.consent_item, viewGroup, false);
            Intrinsics.checkExpressionValueIsNotNull(inflate, Promotion.ACTION_VIEW);
            return new ConsentViewHolder(inflate);
        }
        View inflate2 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.links_item, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate2, Promotion.ACTION_VIEW);
        return new LinksViewHolder(inflate2);
    }

    public int getItemCount() {
        if (this.consents.isEmpty()) {
            return 0;
        }
        return this.consents.size() + 1;
    }

    public void onBindViewHolder(@NotNull ViewHolder viewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "holder");
        if (viewHolder instanceof ConsentViewHolder) {
            ((ConsentViewHolder) viewHolder).bind((Pair) this.consents.get(i), this.onConsentChecked, this.onLearnedMoreClicked);
            return;
        }
        if (!(viewHolder instanceof LinksViewHolder)) {
            viewHolder = null;
        }
        LinksViewHolder linksViewHolder = (LinksViewHolder) viewHolder;
        if (linksViewHolder != null) {
            linksViewHolder.bind(this.isManageConsents, this.onUAAffiliatesClicked, this.updatedTermsAnalyticsHelper);
        }
    }

    public final void setConsents(@NotNull List<? extends Pair<? extends UacfConsent, Boolean>> list) {
        Intrinsics.checkParameterIsNotNull(list, "consents");
        this.consents = list;
    }

    public int getItemViewType(int i) {
        return shouldShowLinks(i) ? 1 : 0;
    }

    private final boolean shouldShowLinks(int i) {
        return i == getItemCount() - 1;
    }
}
