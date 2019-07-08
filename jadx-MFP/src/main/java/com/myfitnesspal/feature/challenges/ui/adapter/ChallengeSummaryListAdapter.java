package com.myfitnesspal.feature.challenges.ui.adapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeSummaryViewModel;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.ui.view.ViewHolder;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChallengeSummaryListAdapter extends ArrayAdapter<ChallengeListItem> {
    private static Map<Class<?>, Integer> CLASS_TO_VIEW_ID_MAP = new HashMap();
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public ItemDecorator decorator;
    /* access modifiers changed from: private */
    public Lazy<ImageService> imageService;
    private LayoutInflater inflater;
    private List<ChallengeListItem> list;

    public class ChallengeViewHolder extends ViewHolder<ChallengeListItem> {
        @BindView(2131363900)
        public TextView begin;
        @BindView(2131362932)
        public LinearLayout daysLeftAndParticipants;
        @BindView(2131362339)
        public View divider;
        @BindView(2131363895)
        public TextView goal;
        @BindView(2131362857)
        public MfpImageView imageView;
        @BindView(2131362935)
        public LinearLayout mainView;
        @BindView(2131363897)
        public TextView name;
        @BindView(2131363898)
        public TextView participants;
        @BindView(2131363254)
        public ProgressBar pbDaysRem;
        @BindView(2131363899)
        public TextView sponsor;

        ChallengeViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }

        public void setData(ChallengeListItem challengeListItem, int i) {
            ChallengeSummaryViewModel challengeSummary = ((ChallengeSummaryListItem) challengeListItem).getChallengeSummary();
            ChallengesUtil.setImageToImageView(ChallengeSummaryListAdapter.this.context, challengeSummary.getThumbImage(), this.imageView, ChallengeSummaryListAdapter.this.imageService);
            TextViewUtils.setText(this.name, challengeSummary.getTitle());
            TextViewUtils.setText(this.sponsor, challengeSummary.getSponsor());
            TextViewUtils.setText(this.participants, ChallengeSummaryListAdapter.this.context.getString(R.string.format_participants, new Object[]{NumberUtils.formatWithSuffix(NumberUtils.tryParseLong(challengeSummary.getParticipants()))}));
            TextViewUtils.setText(this.goal, challengeSummary.getChallengeGoal());
            if (Strings.notEmpty(challengeSummary.getStartDate())) {
                if (challengeSummary.hasChallengeEnded()) {
                    TextViewUtils.setText(this.begin, ChallengeSummaryListAdapter.this.context.getString(R.string.challenge_ended, new Object[]{challengeSummary.getFormattedEndDate()}));
                } else if (challengeSummary.hasChallengeStarted()) {
                    this.begin.setText(challengeSummary.getTimeRemainingInChallenge(ChallengeSummaryListAdapter.this.context));
                    boolean isUserInChallenge = challengeSummary.isUserInChallenge();
                    ViewUtils.setVisible(isUserInChallenge, this.pbDaysRem);
                    if (isUserInChallenge) {
                        int numberOfDaysBetween = ((int) DateTimeUtils.getNumberOfDaysBetween(DateTimeUtils.parseGivenFormat(ChallengesUtil.newIso8601DateTimeFormatWithTimezone().toPattern(), challengeSummary.getStartDate()), new Date())) + 1;
                        long numberOfDaysBetween2 = DateTimeUtils.getNumberOfDaysBetween(ChallengesUtil.newIso8601DateTimeFormatWithTimezone().toPattern(), challengeSummary.getStartDate(), challengeSummary.getEndDate());
                        this.pbDaysRem.setProgress(numberOfDaysBetween);
                        this.pbDaysRem.setMax((int) numberOfDaysBetween2);
                    }
                } else {
                    TextViewUtils.setText(this.begin, ChallengeSummaryListAdapter.this.context.getString(R.string.challenge_begins, new Object[]{challengeSummary.getFormattedStartDate()}));
                }
            }
            if (ChallengeSummaryListAdapter.this.decorator != null) {
                ChallengeSummaryListAdapter.this.decorator.decorate(this, challengeSummary);
            }
        }
    }

    public class ChallengeViewHolder_ViewBinding implements Unbinder {
        private ChallengeViewHolder target;

        @UiThread
        public ChallengeViewHolder_ViewBinding(ChallengeViewHolder challengeViewHolder, View view) {
            this.target = challengeViewHolder;
            challengeViewHolder.mainView = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.llMain, "field 'mainView'", LinearLayout.class);
            challengeViewHolder.imageView = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.ivChallenge, "field 'imageView'", MfpImageView.class);
            challengeViewHolder.name = (TextView) Utils.findRequiredViewAsType(view, R.id.tvChallengeName, "field 'name'", TextView.class);
            challengeViewHolder.sponsor = (TextView) Utils.findRequiredViewAsType(view, R.id.tvChallengeSponsor, "field 'sponsor'", TextView.class);
            challengeViewHolder.begin = (TextView) Utils.findRequiredViewAsType(view, R.id.tvChallengeStart, "field 'begin'", TextView.class);
            challengeViewHolder.participants = (TextView) Utils.findRequiredViewAsType(view, R.id.tvChallengeParticipants, "field 'participants'", TextView.class);
            challengeViewHolder.goal = (TextView) Utils.findRequiredViewAsType(view, R.id.tvChallengeGoal, "field 'goal'", TextView.class);
            challengeViewHolder.daysLeftAndParticipants = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.llDaysLeftAndParticipants, "field 'daysLeftAndParticipants'", LinearLayout.class);
            challengeViewHolder.pbDaysRem = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.pb_days_rem, "field 'pbDaysRem'", ProgressBar.class);
            challengeViewHolder.divider = Utils.findRequiredView(view, R.id.divider, "field 'divider'");
        }

        @CallSuper
        public void unbind() {
            ChallengeViewHolder challengeViewHolder = this.target;
            if (challengeViewHolder != null) {
                this.target = null;
                challengeViewHolder.mainView = null;
                challengeViewHolder.imageView = null;
                challengeViewHolder.name = null;
                challengeViewHolder.sponsor = null;
                challengeViewHolder.begin = null;
                challengeViewHolder.participants = null;
                challengeViewHolder.goal = null;
                challengeViewHolder.daysLeftAndParticipants = null;
                challengeViewHolder.pbDaysRem = null;
                challengeViewHolder.divider = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    class EndedChallengeViewHolder extends ViewHolder<ChallengeListItem> {
        @BindView(2131363900)
        TextView begin;
        @BindView(2131362857)
        MfpImageView imageView;
        @BindView(2131363897)
        TextView name;
        @BindView(2131363899)
        TextView sponsor;

        EndedChallengeViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }

        public void setData(ChallengeListItem challengeListItem, int i) {
            ChallengeSummaryViewModel challengeSummary = ((EndedChallengeListItem) challengeListItem).getChallengeSummary();
            ChallengesUtil.setImageToImageView(ChallengeSummaryListAdapter.this.context, challengeSummary.getThumbImage(), this.imageView, ChallengeSummaryListAdapter.this.imageService);
            TextViewUtils.setText(this.name, challengeSummary.getTitle());
            TextViewUtils.setText(this.sponsor, challengeSummary.getSponsor());
            if (!Strings.notEmpty(challengeSummary.getStartDate())) {
                return;
            }
            if (challengeSummary.hasChallengeEnded()) {
                TextViewUtils.setText(this.begin, ChallengeSummaryListAdapter.this.context.getString(R.string.challenge_ended, new Object[]{challengeSummary.getFormattedEndDate()}));
            } else if (challengeSummary.hasChallengeStarted()) {
                this.begin.setText(challengeSummary.getTimeRemainingInChallenge(ChallengeSummaryListAdapter.this.context));
            } else {
                TextViewUtils.setText(this.begin, ChallengeSummaryListAdapter.this.context.getString(R.string.challenge_begins, new Object[]{challengeSummary.getFormattedStartDate()}));
            }
        }
    }

    public class EndedChallengeViewHolder_ViewBinding implements Unbinder {
        private EndedChallengeViewHolder target;

        @UiThread
        public EndedChallengeViewHolder_ViewBinding(EndedChallengeViewHolder endedChallengeViewHolder, View view) {
            this.target = endedChallengeViewHolder;
            endedChallengeViewHolder.imageView = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.ivChallenge, "field 'imageView'", MfpImageView.class);
            endedChallengeViewHolder.name = (TextView) Utils.findRequiredViewAsType(view, R.id.tvChallengeName, "field 'name'", TextView.class);
            endedChallengeViewHolder.sponsor = (TextView) Utils.findRequiredViewAsType(view, R.id.tvChallengeSponsor, "field 'sponsor'", TextView.class);
            endedChallengeViewHolder.begin = (TextView) Utils.findRequiredViewAsType(view, R.id.tvChallengeStart, "field 'begin'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            EndedChallengeViewHolder endedChallengeViewHolder = this.target;
            if (endedChallengeViewHolder != null) {
                this.target = null;
                endedChallengeViewHolder.imageView = null;
                endedChallengeViewHolder.name = null;
                endedChallengeViewHolder.sponsor = null;
                endedChallengeViewHolder.begin = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public interface ItemDecorator {
        void decorate(ChallengeViewHolder challengeViewHolder, ChallengeSummaryViewModel challengeSummaryViewModel);
    }

    class ListNameViewHolder extends ViewHolder<ChallengeListItem> {
        @BindView(2131362545)
        View extraSeparator;
        @BindView(2131363896)
        TextView listName;

        protected ListNameViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }

        public void setData(ChallengeListItem challengeListItem, int i) {
            getParent().setEnabled(false);
            getParent().setOnClickListener(null);
            ChallengeListItemWithTitle challengeListItemWithTitle = (ChallengeListItemWithTitle) challengeListItem;
            TextViewUtils.setText(this.listName, challengeListItemWithTitle.getTitle());
            ViewUtils.setVisible(challengeListItemWithTitle.getListType() != 0, this.extraSeparator);
        }
    }

    public class ListNameViewHolder_ViewBinding implements Unbinder {
        private ListNameViewHolder target;

        @UiThread
        public ListNameViewHolder_ViewBinding(ListNameViewHolder listNameViewHolder, View view) {
            this.target = listNameViewHolder;
            listNameViewHolder.listName = (TextView) Utils.findRequiredViewAsType(view, R.id.tvChallengeListName, "field 'listName'", TextView.class);
            listNameViewHolder.extraSeparator = Utils.findRequiredView(view, R.id.extra_separator, "field 'extraSeparator'");
        }

        @CallSuper
        public void unbind() {
            ListNameViewHolder listNameViewHolder = this.target;
            if (listNameViewHolder != null) {
                this.target = null;
                listNameViewHolder.listName = null;
                listNameViewHolder.extraSeparator = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    static {
        CLASS_TO_VIEW_ID_MAP.put(ChallengeSummaryListItem.class, Integer.valueOf(0));
        CLASS_TO_VIEW_ID_MAP.put(ChallengeListItemWithTitle.class, Integer.valueOf(1));
        CLASS_TO_VIEW_ID_MAP.put(EndedChallengeListItem.class, Integer.valueOf(2));
    }

    public ChallengeSummaryListAdapter(LayoutInflater layoutInflater, List<ChallengeListItem> list2, Lazy<ImageService> lazy, ItemDecorator itemDecorator, Context context2) {
        super(context2, -1, list2);
        this.inflater = layoutInflater;
        this.list = list2;
        this.imageService = lazy;
        this.decorator = itemDecorator;
        this.context = context2;
    }

    public ChallengeSummaryListAdapter(LayoutInflater layoutInflater, List<ChallengeListItem> list2, Lazy<ImageService> lazy, Context context2) {
        this(layoutInflater, list2, lazy, null, context2);
    }

    public int getCount() {
        return this.list.size();
    }

    public ChallengeListItem getItem(int i) {
        return (ChallengeListItem) this.list.get(i);
    }

    public int getViewTypeCount() {
        return CLASS_TO_VIEW_ID_MAP.size();
    }

    public int getItemViewType(int i) {
        return ListViewUtils.getViewType(this.list, CLASS_TO_VIEW_ID_MAP, i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        ChallengeListItem challengeListItem = (ChallengeListItem) this.list.get(i);
        if (view == null) {
            if (challengeListItem instanceof ChallengeSummaryListItem) {
                view = this.inflater.inflate(R.layout.challenge_summary_item, viewGroup, false);
                viewHolder = new ChallengeViewHolder(view);
            } else if (challengeListItem instanceof ChallengeListItemWithTitle) {
                view = this.inflater.inflate(R.layout.challenge_list_title_item, viewGroup, false);
                viewHolder = new ListNameViewHolder(view);
            } else if (challengeListItem instanceof EndedChallengeListItem) {
                view = this.inflater.inflate(R.layout.ended_challenge_layout_item, viewGroup, false);
                viewHolder = new EndedChallengeViewHolder(view);
            } else {
                throw new RuntimeException("unknown item type!");
            }
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.setData(this.list.get(i), i);
        return view;
    }
}
