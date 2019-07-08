package com.myfitnesspal.feature.friends.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.friends.ui.activity.InviteFriendActivity;
import com.myfitnesspal.feature.profile.ui.activity.ProfileView;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityParticipant;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityParticipant.Relationship;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedLikeDetails;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.util.Toaster;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class LikesListFragment extends MfpFragment {
    private String commentId;
    private String entryId;
    /* access modifiers changed from: private */
    public ListView listView;
    @Inject
    NewsFeedService newsFeedService;

    public class LikeListAdapter extends BaseAdapter {
        final List<MfpNewsFeedActivityParticipant> participants;

        public long getItemId(int i) {
            return (long) i;
        }

        public LikeListAdapter(List<MfpNewsFeedActivityParticipant> list) {
            this.participants = list;
        }

        public int getCount() {
            return CollectionUtils.size((Collection<?>) this.participants);
        }

        public MfpNewsFeedActivityParticipant getItem(int i) {
            return (MfpNewsFeedActivityParticipant) this.participants.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(LikesListFragment.this.getActivity()).inflate(R.layout.like_list_item, viewGroup, false);
            }
            final MfpNewsFeedActivityParticipant item = getItem(i);
            TextView textView = (TextView) view.findViewById(R.id.userName);
            MfpImageView mfpImageView = (MfpImageView) view.findViewById(R.id.btnUserImg);
            final ImageView imageView = (ImageView) view.findViewById(R.id.ivAddFriend);
            final ImageView imageView2 = (ImageView) view.findViewById(R.id.ivExistingFriend);
            if (item.getRelationship().equals(Relationship.FRIEND)) {
                imageView2.setVisibility(0);
                imageView2.setClickable(false);
                imageView.setVisibility(8);
            } else {
                imageView2.setVisibility(8);
                imageView.setVisibility(0);
            }
            textView.setText(Strings.toString(item.getUsername()));
            mfpImageView.setUrl(item.getProfilePhotoUrl());
            textView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    LikesListFragment.this.getNavigationHelper().withIntent(ProfileView.newStartIntent(LikesListFragment.this.getActivity(), item.getUsername(), item.getUserId())).startActivity(36);
                }
            });
            mfpImageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    LikesListFragment.this.getNavigationHelper().withIntent(ProfileView.newStartIntent(LikesListFragment.this.getActivity(), item.getUsername(), item.getUserId())).startActivity(36);
                }
            });
            imageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    LikesListFragment.this.getNavigationHelper().withIntent(InviteFriendActivity.newStartIntent(LikesListFragment.this.getActivity(), item.getUsername())).startActivity(18);
                    imageView.setVisibility(8);
                    imageView2.setVisibility(0);
                }
            });
            return view;
        }
    }

    public static LikesListFragment newInstance(String str) {
        return newInstance(str, null);
    }

    public static LikesListFragment newInstance(String str, String str2) {
        LikesListFragment likesListFragment = new LikesListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Extras.NEWSFEED_ENTRY_ID, str);
        bundle.putString(Extras.COMMENT_ID, str2);
        likesListFragment.setArguments(bundle);
        return likesListFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.generic_list_fragment, viewGroup, false);
        this.listView = (ListView) inflate.findViewById(R.id.list);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getView().setBackgroundColor(getResources().getColor(R.color.feed_background));
        if (bundle == null) {
            bundle = getArguments();
        }
        ListView listView2 = this.listView;
        if (listView2 != null) {
            listView2.setAdapter(null);
        }
        if (bundle == null) {
            notifyFailedToLoad();
            return;
        }
        this.entryId = bundle.getString(Extras.NEWSFEED_ENTRY_ID);
        if (Strings.isEmpty(this.entryId)) {
            notifyFailedToLoad();
            return;
        }
        this.commentId = bundle.getString(Extras.COMMENT_ID);
        if (Strings.isEmpty(this.commentId)) {
            setBusyInternal(true);
            this.newsFeedService.getLikesAsync(this.entryId, getSuccessCallback(), getErrorCallback());
            return;
        }
        setBusyInternal(true);
        this.newsFeedService.getLikesAsyncForConversationEntry(this.entryId, this.commentId, getSuccessCallback(), getErrorCallback());
    }

    private Function1<MfpNewsFeedLikeDetails> getSuccessCallback() {
        return new Function1<MfpNewsFeedLikeDetails>() {
            public void execute(MfpNewsFeedLikeDetails mfpNewsFeedLikeDetails) throws RuntimeException {
                LikesListFragment.this.setBusyInternal(false);
                if (mfpNewsFeedLikeDetails == null || !CollectionUtils.notEmpty((Collection<?>) mfpNewsFeedLikeDetails.getParticipants())) {
                    Ln.e("Could not retrieve liking users", new Object[0]);
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("MfpNewsFeedLikeDetails received: ");
                sb.append(mfpNewsFeedLikeDetails.getCount());
                Ln.d(sb.toString(), new Object[0]);
                LikesListFragment.this.listView.setAdapter(new LikeListAdapter(mfpNewsFeedLikeDetails.getParticipants()));
            }
        };
    }

    private MfpV2ApiErrorCallback getErrorCallback() {
        return new MfpV2ApiErrorCallback() {
            public void execute(ApiResponseBase apiResponseBase) {
                Ln.e("NEW LIKES : error = %s", apiResponseBase.getErrorDescription());
                LikesListFragment.this.setBusyInternal(false);
                LikesListFragment.this.notifyFailedToLoad();
            }
        };
    }

    /* access modifiers changed from: private */
    public void notifyFailedToLoad() {
        if (isEnabled()) {
            Toaster.showLong((Activity) getActivity(), (int) R.string.failed_to_retrieve_likes);
        }
    }

    /* access modifiers changed from: private */
    public void setBusyInternal(boolean z) {
        setBusy(1, z);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(Extras.NEWSFEED_ENTRY_ID, this.entryId);
        bundle.putString(Extras.COMMENT_ID, this.commentId);
    }

    public void onPause() {
        super.onPause();
    }
}
