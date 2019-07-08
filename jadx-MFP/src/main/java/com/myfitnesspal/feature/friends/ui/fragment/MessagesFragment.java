package com.myfitnesspal.feature.friends.ui.fragment;

import android.content.Context;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.friends.service.MessageService;
import com.myfitnesspal.feature.friends.ui.activity.DetailedMessageActivity;
import com.myfitnesspal.feature.friends.ui.activity.MessagesActivity.Type;
import com.myfitnesspal.feature.friends.ui.adapter.MessagesAdapter;
import com.myfitnesspal.feature.friends.ui.adapter.MessagesAdapter.OnItemClickListener;
import com.myfitnesspal.feature.friends.ui.viewmodel.MessagesViewModel;
import com.myfitnesspal.feature.friends.ui.viewmodel.MessagesViewModel.Property;
import com.myfitnesspal.shared.model.v1.InboxMessage;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.view.EmptyStateView;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;

public class MessagesFragment extends MfpFragment {
    private static final String EXTRA_MESSAGE_TYPE_ORDINAL = "message_type_ordinal";
    private static final String EXTRA_VIEW_TYPE_ORDINAL = "view_type_ordinal";
    private static final int INBOX_SPINNER_INDEX = 0;
    private static final String MESSAGE_FETCH_FAILED_TAG = "message_fetch_failed";
    private static final int SENT_SPINNER_INDEX = 1;
    private MessagesAdapter adapter;
    @BindView(2131362461)
    EmptyStateView emptyStateView;
    @BindView(2131362947)
    View loadingProgressView;
    @Inject
    Lazy<MessageService> messageService;
    /* access modifiers changed from: private */
    public Type messageType;
    @BindView(2131363058)
    RecyclerView messagesRecyclerView;
    private final OnItemClickListener onItemClickListener = new OnItemClickListener() {
        public void onItemClick(InboxMessage inboxMessage, int i) {
            MessagesFragment.this.getNavigationHelper().withIntent(DetailedMessageActivity.newStartIntent((Context) MessagesFragment.this.getActivity(), inboxMessage, "messages")).fromFragment(MessagesFragment.this).startActivity(88);
            inboxMessage.markAsReadLocally();
            MessagesFragment.this.notifyMessagesChanged();
        }
    };
    private OnMessagesChangedListener onMessagesChangedListener;
    @BindView(2131363427)
    SwipeRefreshLayout refreshLayout;
    @BindView(2131363687)
    Spinner spinner;
    @BindView(2131363694)
    View spinnerContainer;
    private OnItemSelectedListener spinnerItemSelectedListener = new OnItemSelectedListener() {
        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            MessagesFragment.this.messageType = i == 1 ? Type.SentMessages : Type.ReceivedMessages;
            MessagesFragment.this.viewModel.setTypeAndReloadIfNecessary(MessagesFragment.this.messageType);
        }
    };
    /* access modifiers changed from: private */
    public MessagesViewModel viewModel;

    public interface OnMessagesChangedListener {
        void onMessagesChanged(List<InboxMessage> list, Type type);
    }

    public enum ViewType {
        Simple,
        Switchable
    }

    public static MessagesFragment newInstance(Type type) {
        return newInstance(type, ViewType.Simple);
    }

    public static MessagesFragment newInstance(Type type, ViewType viewType) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_MESSAGE_TYPE_ORDINAL, type.ordinal());
        bundle.putInt(EXTRA_VIEW_TYPE_ORDINAL, viewType.ordinal());
        MessagesFragment messagesFragment = new MessagesFragment();
        messagesFragment.setArguments(bundle);
        return messagesFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.messages_list, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.messageType = Type.values()[BundleUtils.getInt(EXTRA_MESSAGE_TYPE_ORDINAL, Integer.valueOf(Type.ReceivedMessages.ordinal()), bundle, getArguments()).intValue()];
        setupRecyclerView();
        initViewModel(this.messageType);
        initSpinner(ViewType.values()[BundleUtils.getInt(getArguments(), EXTRA_VIEW_TYPE_ORDINAL)]);
        this.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                MessagesFragment.this.viewModel.load(new Void[0]);
            }
        });
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(EXTRA_MESSAGE_TYPE_ORDINAL, this.messageType.ordinal());
    }

    public void onResume() {
        super.onResume();
        if (this.viewModel.hasMessages()) {
            displayMessages();
            this.adapter.addAll(this.viewModel.getMessages());
        } else {
            displayLoadingState();
        }
        this.viewModel.load(new Void[0]);
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        this.refreshLayout.setRefreshing(false);
        if (i == Property.MESSAGES_FETCHED) {
            if (this.viewModel.hasMessages()) {
                displayMessages();
                this.adapter.addAll(this.viewModel.getMessages());
                notifyMessagesChanged();
                return;
            }
            displayEmptyState();
        } else if (i == Property.MESSAGES_FETCH_FAILED) {
            showDialogFragment(((AlertDialogFragment) new AlertDialogFragment().setMessage((int) R.string.failRetrieveMessages)).setPositiveText(R.string.ok, null), MESSAGE_FETCH_FAILED_TAG);
            displayEmptyState();
        } else if (i == Property.LOAD_STATE && this.viewModel.isLoading()) {
            displayLoadingState();
        }
    }

    public void setOnMessagesChangedListener(OnMessagesChangedListener onMessagesChangedListener2) {
        this.onMessagesChangedListener = onMessagesChangedListener2;
    }

    /* access modifiers changed from: private */
    public void notifyMessagesChanged() {
        OnMessagesChangedListener onMessagesChangedListener2 = this.onMessagesChangedListener;
        if (onMessagesChangedListener2 != null) {
            onMessagesChangedListener2.onMessagesChanged(this.viewModel.getMessages(), this.messageType);
        }
    }

    private void setupRecyclerView() {
        this.messagesRecyclerView.setHasFixedSize(true);
        this.messagesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.adapter = new MessagesAdapter(this.onItemClickListener);
        this.messagesRecyclerView.setAdapter(this.adapter);
        MaterialUtils.addDecoratorForRemovingFabOverlapOnLastItem(this.messagesRecyclerView);
    }

    private void initViewModel(Type type) {
        this.viewModel = (MessagesViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (MessagesViewModel) setViewModel(new MessagesViewModel(getRunner(), this.messageService, type));
        }
    }

    private void displayLoadingState() {
        ViewUtils.setVisible(this.loadingProgressView);
        ViewUtils.setGone(this.messagesRecyclerView);
        ViewUtils.setGone(this.emptyStateView);
    }

    private void displayMessages() {
        ViewUtils.setVisible(this.messagesRecyclerView);
        ViewUtils.setGone(this.loadingProgressView);
        ViewUtils.setGone(this.emptyStateView);
    }

    private void displayEmptyState() {
        this.emptyStateView.initializeForType(this.messageType.getEmptyStateType());
        ViewUtils.setVisible(this.emptyStateView);
        ViewUtils.setGone(this.loadingProgressView);
        ViewUtils.setGone(this.messagesRecyclerView);
    }

    private void initSpinner(ViewType viewType) {
        if (viewType == ViewType.Simple) {
            ViewUtils.setGone(this.spinnerContainer);
            return;
        }
        ArrayAdapter createFromResource = ArrayAdapter.createFromResource(getActivity(), R.array.message_type_spinner_list, 17367048);
        this.spinnerContainer.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MessagesFragment.this.spinner.performClick();
            }
        });
        createFromResource.setDropDownViewResource(17367049);
        this.spinner.setAdapter(createFromResource);
        this.spinner.setOnItemSelectedListener(this.spinnerItemSelectedListener);
        this.spinner.setSelection(this.messageType == Type.ReceivedMessages ? 0 : 1);
    }
}
