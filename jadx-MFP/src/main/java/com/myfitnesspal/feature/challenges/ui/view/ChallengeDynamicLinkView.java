package com.myfitnesspal.feature.challenges.ui.view;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.ChallengeDynamicTab;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeDynamicLinksAdapter;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.ui.view.LinearLayoutAdapterView.OnItemClickListener;
import com.myfitnesspal.shared.ui.view.LinearLayoutListAdapterView;
import com.uacf.core.util.Ln;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.List;

public class ChallengeDynamicLinkView {
    public View createViewAndReturn(Context context, List<ChallengeDynamicTab> list, String str, String str2, String str3, Lazy<ChallengesAnalyticsHelper> lazy) {
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup viewGroup = (ViewGroup) from.inflate(R.layout.challenge_dynamic_links_list, null, false);
        LinearLayoutListAdapterView linearLayoutListAdapterView = (LinearLayoutListAdapterView) ViewUtils.findById(viewGroup, R.id.lv_dynamic_links);
        final Context context2 = context;
        TextViewUtils.setText((TextView) ViewUtils.findById(viewGroup, R.id.tv_sponsor), context.getString(R.string.more_from, new Object[]{str}));
        List<ChallengeDynamicTab> list2 = list;
        linearLayoutListAdapterView.setAdapter(new ChallengeDynamicLinksAdapter(from, list, R.layout.challenge_dynamic_link));
        final List<ChallengeDynamicTab> list3 = list;
        final Lazy<ChallengesAnalyticsHelper> lazy2 = lazy;
        final String str4 = str2;
        final String str5 = str3;
        AnonymousClass1 r6 = new OnItemClickListener() {
            public void onClick(View view, int i) {
                ChallengeDynamicTab challengeDynamicTab = (ChallengeDynamicTab) list3.get(i);
                try {
                    ((ChallengesAnalyticsHelper) lazy2.get()).reportSponsorLinkClicked(str4, str5, i, challengeDynamicTab.getTitle());
                    context2.startActivity(SharedIntents.newUriIntent(challengeDynamicTab.getUrl(), true).addFlags(1073741824));
                } catch (ActivityNotFoundException unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("exception launching external browser for dynamic tab with title: ");
                    sb.append(challengeDynamicTab.getTitle());
                    sb.append(" ,url:");
                    sb.append(challengeDynamicTab.getUrl());
                    Ln.e(sb.toString(), new Object[0]);
                }
            }
        };
        linearLayoutListAdapterView.setOnItemClickListener(r6);
        return viewGroup;
    }
}
