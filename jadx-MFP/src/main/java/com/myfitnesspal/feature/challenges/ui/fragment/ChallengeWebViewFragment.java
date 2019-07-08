package com.myfitnesspal.feature.challenges.ui.fragment;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.ChallengeImageOutput;
import com.myfitnesspal.feature.challenges.ui.view.ChallengeBannerImageView;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.ui.view.MfpWebView;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 !2\u00020\u0001:\u0002!\"B\u0005¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002¢\u0006\u0002\u0010\u0014J\u0012\u0010\u0015\u001a\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J&\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u001e\u001a\u00020\u0013H\u0016J\u0010\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\rH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/myfitnesspal/feature/challenges/ui/fragment/ChallengeWebViewFragment;", "Lcom/myfitnesspal/feature/challenges/ui/fragment/ChallengeTabFragmentBase;", "()V", "bannerImage", "Lcom/myfitnesspal/feature/challenges/model/ChallengeImageOutput;", "imageService", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/images/service/ImageService;", "getImageService", "()Ldagger/Lazy;", "setImageService", "(Ldagger/Lazy;)V", "openExternal", "", "url", "", "webViewEntry", "Lcom/myfitnesspal/feature/challenges/ui/fragment/ChallengeWebViewFragment$WebViewEntry;", "notifyListenerOfExternalWebLoaded", "", "()Lkotlin/Unit;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onPause", "setUserVisibleHint", "isVisibleToUser", "Companion", "WebViewEntry", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: ChallengeWebViewFragment.kt */
public final class ChallengeWebViewFragment extends ChallengeTabFragmentBase {
    private static final String BANNER_IMAGE_URL = "banner_image_url";
    public static final Companion Companion = new Companion(null);
    private static final String EXTRAS_ENTRY = "entry";
    private static final String EXTRAS_URL = "url";
    private static final String OPEN_EXTERNAL = "open_external";
    private HashMap _$_findViewCache;
    private ChallengeImageOutput bannerImage;
    @Inject
    @NotNull
    public Lazy<ImageService> imageService;
    private boolean openExternal;
    private String url;
    private WebViewEntry webViewEntry;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0007J*\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/myfitnesspal/feature/challenges/ui/fragment/ChallengeWebViewFragment$Companion;", "", "()V", "BANNER_IMAGE_URL", "", "EXTRAS_ENTRY", "EXTRAS_URL", "OPEN_EXTERNAL", "newInstance", "Lcom/myfitnesspal/feature/challenges/ui/fragment/ChallengeWebViewFragment;", "url", "bannerImage", "Lcom/myfitnesspal/feature/challenges/model/ChallengeImageOutput;", "openExternal", "", "webViewEntry", "Lcom/myfitnesspal/feature/challenges/ui/fragment/ChallengeWebViewFragment$WebViewEntry;", "newInstanceForSponsor", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ChallengeWebViewFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final ChallengeWebViewFragment newInstance(@NotNull String str, @NotNull ChallengeImageOutput challengeImageOutput) {
            Intrinsics.checkParameterIsNotNull(str, "url");
            Intrinsics.checkParameterIsNotNull(challengeImageOutput, "bannerImage");
            return newInstance(str, false, challengeImageOutput, WebViewEntry.RULES);
        }

        @JvmStatic
        @NotNull
        public final ChallengeWebViewFragment newInstanceForSponsor(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "url");
            return newInstance(str, false, null, WebViewEntry.SPONSOR);
        }

        private final ChallengeWebViewFragment newInstance(String str, boolean z, ChallengeImageOutput challengeImageOutput, WebViewEntry webViewEntry) {
            ChallengeWebViewFragment challengeWebViewFragment = new ChallengeWebViewFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", str);
            bundle.putBoolean(ChallengeWebViewFragment.OPEN_EXTERNAL, z);
            bundle.putInt(ChallengeWebViewFragment.EXTRAS_ENTRY, webViewEntry.ordinal());
            if (challengeImageOutput != null) {
                bundle.putString(ChallengeWebViewFragment.BANNER_IMAGE_URL, challengeImageOutput.getUrl());
            }
            challengeWebViewFragment.setArguments(bundle);
            return challengeWebViewFragment;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/feature/challenges/ui/fragment/ChallengeWebViewFragment$WebViewEntry;", "", "(Ljava/lang/String;I)V", "RULES", "SPONSOR", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: ChallengeWebViewFragment.kt */
    private enum WebViewEntry {
        RULES,
        SPONSOR
    }

    @JvmStatic
    @NotNull
    public static final ChallengeWebViewFragment newInstance(@NotNull String str, @NotNull ChallengeImageOutput challengeImageOutput) {
        return Companion.newInstance(str, challengeImageOutput);
    }

    @JvmStatic
    @NotNull
    public static final ChallengeWebViewFragment newInstanceForSponsor(@NotNull String str) {
        return Companion.newInstanceForSponsor(str);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @NotNull
    public final Lazy<ImageService> getImageService() {
        Lazy<ImageService> lazy = this.imageService;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imageService");
        }
        return lazy;
    }

    public final void setImageService(@NotNull Lazy<ImageService> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.imageService = lazy;
    }

    public void onCreate(@Nullable Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
    }

    public void onPause() {
        super.onPause();
        if (this.openExternal) {
            notifyListenerOfExternalWebLoaded();
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.url = BundleUtils.getString(getArguments(), "url");
        this.openExternal = BundleUtils.getBoolean(getArguments(), OPEN_EXTERNAL);
        this.webViewEntry = WebViewEntry.values()[BundleUtils.getInt(getArguments(), EXTRAS_ENTRY)];
        String string = BundleUtils.getString(getArguments(), BANNER_IMAGE_URL, "");
        if (Strings.notEmpty(string)) {
            this.bannerImage = new ChallengeImageOutput(string);
        }
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.challenge_webview_fragment, viewGroup, false);
        if (this.openExternal) {
            return inflate;
        }
        boolean z = this.webViewEntry == WebViewEntry.RULES;
        Intrinsics.checkExpressionValueIsNotNull(inflate, Promotion.ACTION_VIEW);
        ViewUtils.setVisible(z, (TextView) inflate.findViewById(R.id.titleView), (ChallengeBannerImageView) inflate.findViewById(R.id.challengeBannerImage));
        ViewUtils.setVisible((ProgressBar) inflate.findViewById(R.id.rulesProgressBar));
        ChallengeBannerImageView challengeBannerImageView = (ChallengeBannerImageView) inflate.findViewById(R.id.challengeBannerImage);
        Context activity = getActivity();
        ChallengeImageOutput challengeImageOutput = this.bannerImage;
        Lazy<ImageService> lazy = this.imageService;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imageService");
        }
        challengeBannerImageView.setBannerImage(activity, challengeImageOutput, lazy);
        MfpWebView mfpWebView = (MfpWebView) inflate.findViewById(R.id.challengeWebView);
        Intrinsics.checkExpressionValueIsNotNull(mfpWebView, "it");
        mfpWebView.setWebViewClient(new ChallengeWebViewFragment$onCreateView$$inlined$let$lambda$1(this, inflate));
        WebSettings settings = mfpWebView.getSettings();
        Intrinsics.checkExpressionValueIsNotNull(settings, "it.settings");
        settings.setJavaScriptEnabled(true);
        mfpWebView.loadUrl(this.url);
        mfpWebView.setOnLongClickListener(ChallengeWebViewFragment$onCreateView$1$2.INSTANCE);
        if (VERSION.SDK_INT >= 23) {
            mfpWebView.setOnContextClickListener(ChallengeWebViewFragment$onCreateView$1$3.INSTANCE);
        }
        return inflate;
    }

    private final Unit notifyListenerOfExternalWebLoaded() {
        ChallengeWebViewLoadListener challengeWebViewLoadListener = (ChallengeWebViewLoadListener) getActivity();
        if (challengeWebViewLoadListener == null) {
            return null;
        }
        challengeWebViewLoadListener.onExternalWebViewLoaded();
        return Unit.INSTANCE;
    }
}
