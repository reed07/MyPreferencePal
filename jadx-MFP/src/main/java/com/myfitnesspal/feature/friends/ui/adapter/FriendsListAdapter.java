package com.myfitnesspal.feature.friends.ui.adapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService.WeightType;
import com.myfitnesspal.shared.ui.graphics.TextDrawable;
import com.myfitnesspal.shared.ui.graphics.TextDrawable.Builder;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.util.DateUtil;
import com.uacf.core.util.ViewUtils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FriendsListAdapter extends Adapter<ViewHolder> {
    private static final int VIEW_TYPE_ITEM = 101;
    private static final int VIEW_TYPE_SEPARATOR = 100;
    private final Context context;
    private final Map<Character, List<MiniUserInfo>> firstLetterToFriendMap;
    private int itemCount = 0;
    /* access modifiers changed from: private */
    public OnItemClickListener onItemClickListener;
    private final Map<Integer, Character> positionToSeparatorTitle;
    private final UserWeightService userWeightService;

    class ItemViewHolder extends ViewHolder {
        @BindView(2131362663)
        TextView lastLoginDate;
        @BindView(2131362664)
        ImageView lockIcon;
        @BindView(2131362665)
        TextView lostWeight;
        @BindView(2131362683)
        MfpImageView thumbnail;
        @BindView(2131362666)
        TextView userName;

        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
            this.thumbnail.setTransformCircular(true);
        }
    }

    public class ItemViewHolder_ViewBinding implements Unbinder {
        private ItemViewHolder target;

        @UiThread
        public ItemViewHolder_ViewBinding(ItemViewHolder itemViewHolder, View view) {
            this.target = itemViewHolder;
            itemViewHolder.userName = (TextView) Utils.findRequiredViewAsType(view, R.id.friend_item_username, "field 'userName'", TextView.class);
            itemViewHolder.lastLoginDate = (TextView) Utils.findRequiredViewAsType(view, R.id.friend_item_date_active, "field 'lastLoginDate'", TextView.class);
            itemViewHolder.lostWeight = (TextView) Utils.findRequiredViewAsType(view, R.id.friend_item_lost_weight, "field 'lostWeight'", TextView.class);
            itemViewHolder.lockIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.friend_item_lock_icon, "field 'lockIcon'", ImageView.class);
            itemViewHolder.thumbnail = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.friends_item_thumbnail, "field 'thumbnail'", MfpImageView.class);
        }

        @CallSuper
        public void unbind() {
            ItemViewHolder itemViewHolder = this.target;
            if (itemViewHolder != null) {
                this.target = null;
                itemViewHolder.userName = null;
                itemViewHolder.lastLoginDate = null;
                itemViewHolder.lostWeight = null;
                itemViewHolder.lockIcon = null;
                itemViewHolder.thumbnail = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public interface OnItemClickListener {
        void onItemClick(MiniUserInfo miniUserInfo);
    }

    class SeparatorViewHolder extends ViewHolder {
        @BindView(2131364033)
        TextView title;

        SeparatorViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class SeparatorViewHolder_ViewBinding implements Unbinder {
        private SeparatorViewHolder target;

        @UiThread
        public SeparatorViewHolder_ViewBinding(SeparatorViewHolder separatorViewHolder, View view) {
            this.target = separatorViewHolder;
            separatorViewHolder.title = (TextView) Utils.findRequiredViewAsType(view, R.id.txtSection, "field 'title'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            SeparatorViewHolder separatorViewHolder = this.target;
            if (separatorViewHolder != null) {
                this.target = null;
                separatorViewHolder.title = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public FriendsListAdapter(Context context2, Map<Character, List<MiniUserInfo>> map, UserWeightService userWeightService2) {
        this.context = context2;
        this.firstLetterToFriendMap = map;
        this.userWeightService = userWeightService2;
        this.positionToSeparatorTitle = new LinkedHashMap();
        prepareData();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.context);
        if (i == 100) {
            return new SeparatorViewHolder(from.inflate(R.layout.friends_list_header, viewGroup, false));
        }
        return new ItemViewHolder(from.inflate(R.layout.friends_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (viewHolder instanceof SeparatorViewHolder) {
            bindSeparatorViewHolder((SeparatorViewHolder) viewHolder, (Character) this.positionToSeparatorTitle.get(Integer.valueOf(i)));
        } else if (viewHolder instanceof ItemViewHolder) {
            bindItemViewHolder((ItemViewHolder) viewHolder, findItemByAbsolutePosition(i));
        }
    }

    public int getItemViewType(int i) {
        return isSeparatorPosition(i) ? 100 : 101;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    private void prepareData() {
        this.itemCount = 0;
        for (Character ch : this.firstLetterToFriendMap.keySet()) {
            this.positionToSeparatorTitle.put(Integer.valueOf(this.itemCount), ch);
            this.itemCount += ((List) this.firstLetterToFriendMap.get(ch)).size() + 1;
        }
    }

    private boolean isSeparatorPosition(int i) {
        return this.positionToSeparatorTitle.get(Integer.valueOf(i)) != null;
    }

    private MiniUserInfo findItemByAbsolutePosition(int i) {
        int i2 = 0;
        for (Integer num : this.positionToSeparatorTitle.keySet()) {
            if (num.intValue() >= i) {
                break;
            }
            i2 = num.intValue();
        }
        List list = (List) this.firstLetterToFriendMap.get(this.positionToSeparatorTitle.get(Integer.valueOf(i2)));
        if (list != null) {
            return (MiniUserInfo) list.get((i - i2) - 1);
        }
        return null;
    }

    private void bindSeparatorViewHolder(SeparatorViewHolder separatorViewHolder, Character ch) {
        separatorViewHolder.title.setText(ch.toString());
    }

    private void bindItemViewHolder(ItemViewHolder itemViewHolder, final MiniUserInfo miniUserInfo) {
        itemViewHolder.userName.setText(miniUserInfo.getUsername());
        itemViewHolder.lastLoginDate.setText(DateUtil.getTimestamp(this.context, miniUserInfo.getLastLoginDate(), false));
        TextDrawable build = new Builder().text(String.valueOf(Character.toUpperCase(miniUserInfo.getUsername().charAt(0)))).build();
        if (miniUserInfo.getImageUrl() == null || miniUserInfo.getImageUrl().isEmpty()) {
            itemViewHolder.thumbnail.setImageDrawable(build);
        } else {
            itemViewHolder.thumbnail.setPlaceholderDrawable(build);
            itemViewHolder.thumbnail.setUrl(miniUserInfo.getImageUrl());
        }
        if (miniUserInfo.isProfileViewable()) {
            TextView textView = itemViewHolder.lostWeight;
            textView.setText(this.userWeightService.getDisplayableString(WeightType.JUST_WEIGHT, miniUserInfo.getPoundsLost()));
            ViewUtils.setVisible(true, textView);
            ViewUtils.setVisible(false, itemViewHolder.lockIcon);
        } else {
            ViewUtils.setVisible(false, itemViewHolder.lostWeight);
            ViewUtils.setVisible(true, itemViewHolder.lockIcon);
        }
        itemViewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (FriendsListAdapter.this.onItemClickListener != null) {
                    FriendsListAdapter.this.onItemClickListener.onItemClick(miniUserInfo);
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }
}
