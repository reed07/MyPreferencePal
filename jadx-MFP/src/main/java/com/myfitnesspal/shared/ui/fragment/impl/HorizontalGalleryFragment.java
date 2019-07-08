package com.myfitnesspal.shared.ui.fragment.impl;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.GlideHideProgressListener;
import com.myfitnesspal.shared.ui.view.PaddingView;
import com.myfitnesspal.shared.util.HorizontalRecyclerViewUtil;
import com.uacf.core.util.Debouncer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HorizontalGalleryFragment extends Fragment {
    private static final String EXTRA_POSITION = "extra_position";
    /* access modifiers changed from: private */
    public static final RequestOptions GLIDE_OPTIONS = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().placeholder((int) R.drawable.ic_photo_camera_black_24dp);
    private static final int RESIZED_SETTLED_DURATION_MILLIS = 1000;
    /* access modifiers changed from: private */
    public Adapter adapter;
    /* access modifiers changed from: private */
    public Handler handler = new Handler();
    private ImageMetadataProvider imageMetadataProvider;
    private int itemWidthPx = 0;
    /* access modifiers changed from: private */
    public Rect lastLayoutSize;
    private LinearLayoutManager layoutManager;
    private OnClickListener onItemClickListener = new OnClickListener() {
        public void onClick(View view) {
            HorizontalGalleryFragment.this.scrollToPosition(((ViewHolder) view.getTag()).position, ScrollMode.Animate);
        }
    };
    /* access modifiers changed from: private */
    public Runnable onLayoutChangedRunnable = new Runnable() {
        public void run() {
            HorizontalGalleryFragment.this.adapter.setRecyclerWidth(HorizontalGalleryFragment.this.recyclerView.getWidth());
            HorizontalGalleryFragment.this.handler.post(HorizontalGalleryFragment.this.refreshScrollPositionRunnable);
        }
    };
    private OnPositionChangedListener onPositionChangedListener;
    /* access modifiers changed from: private */
    public OnScrollSettledListener onScrollSettledListener;
    /* access modifiers changed from: private */
    public RecyclerView recyclerView;
    /* access modifiers changed from: private */
    public Runnable refreshScrollPositionRunnable = new Runnable() {
        public void run() {
            HorizontalGalleryFragment horizontalGalleryFragment = HorizontalGalleryFragment.this;
            horizontalGalleryFragment.setPosition(horizontalGalleryFragment.relativePosition, ScrollMode.Immediate);
        }
    };
    /* access modifiers changed from: private */
    public int relativePosition = -1;
    /* access modifiers changed from: private */
    public boolean resizeSettled;
    /* access modifiers changed from: private */
    public Debouncer<Void> resizeSettledDebouncer = new Debouncer<Void>(1000) {
        /* access modifiers changed from: protected */
        public void onDebounced(Void voidR) {
            HorizontalGalleryFragment.this.resizeSettled = true;
        }
    };
    private OnScrollListener scrollChangeListener = new OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            if (i == 1) {
                HorizontalGalleryFragment.this.userDragging = true;
            }
            if (i == 0) {
                if (HorizontalGalleryFragment.this.userDragging) {
                    HorizontalGalleryFragment.this.userDragging = false;
                    HorizontalGalleryFragment.this.snapToMiddleOfFocusedItem();
                }
                if (HorizontalGalleryFragment.this.onScrollSettledListener != null) {
                    HorizontalGalleryFragment.this.onScrollSettledListener.onScrollSettled(HorizontalGalleryFragment.this.relativePosition);
                }
            }
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            if (HorizontalGalleryFragment.this.userDragging) {
                HorizontalGalleryFragment.this.updateCurrentPosition();
                return;
            }
            HorizontalGalleryFragment horizontalGalleryFragment = HorizontalGalleryFragment.this;
            horizontalGalleryFragment.setRelativePosition(horizontalGalleryFragment.relativePosition);
        }
    };
    /* access modifiers changed from: private */
    public boolean userDragging;

    private static final class Adapter extends android.support.v7.widget.RecyclerView.Adapter<ViewHolder> {
        private static final int VIEW_TYPE_IMAGE = 2;
        private static final int VIEW_TYPE_IMPORT = 3;
        private static final int VIEW_TYPE_PADDING = 1;
        private final Context context;
        private OnClickListener imageClickListener;
        private ImageMetadataProvider imageMetadataProvider;
        private final LayoutInflater inflater;
        private int paddingWidth;

        public Adapter(Context context2, OnClickListener onClickListener, LayoutInflater layoutInflater, ImageMetadataProvider imageMetadataProvider2) {
            this.context = context2.getApplicationContext();
            this.imageClickListener = onClickListener;
            this.inflater = layoutInflater;
            this.imageMetadataProvider = imageMetadataProvider2;
        }

        public void setImageMetadataProvider(ImageMetadataProvider imageMetadataProvider2) {
            if (this.imageMetadataProvider != imageMetadataProvider2) {
                this.imageMetadataProvider = imageMetadataProvider2;
                notifyDataSetChanged();
            }
        }

        public void setRecyclerWidth(int i) {
            int i2 = i / 2;
            if (i2 != this.paddingWidth) {
                this.paddingWidth = i2;
                notifyDataSetChanged();
            }
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            int itemViewType = getItemViewType(i);
            int i2 = i - 1;
            if (itemViewType == 2) {
                viewHolder.bindAsImage(this.context, this.imageMetadataProvider, i2, this.imageClickListener);
            } else if (itemViewType == 3) {
                viewHolder.bindAsImport(this.imageMetadataProvider, i2, this.imageClickListener);
            } else if (itemViewType == 1) {
                viewHolder.paddingView.setWidth(this.paddingWidth);
            }
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            Context context2 = viewGroup.getContext();
            if (i == 1) {
                return new ViewHolder(new PaddingView(context2, this.paddingWidth));
            }
            if (i == 2) {
                return new ViewHolder(this.inflater.inflate(R.layout.horizontal_gallery_image_item, viewGroup, false));
            }
            if (i == 3) {
                return new ViewHolder(this.inflater.inflate(R.layout.horizontal_gallery_import_item, viewGroup, false));
            }
            throw new IllegalArgumentException("unknown viewType!");
        }

        public int getItemCount() {
            ImageMetadataProvider imageMetadataProvider2 = this.imageMetadataProvider;
            if (imageMetadataProvider2 == null) {
                return 0;
            }
            return imageMetadataProvider2.getCount() + 2;
        }

        public int getItemViewType(int i) {
            if (i == 0 || i == getItemCount() - 1) {
                return 1;
            }
            return this.imageMetadataProvider.getUri(i - 1) == null ? 3 : 2;
        }
    }

    public interface ImageMetadataProvider {
        int getCount();

        Date getDate(int i);

        String getUri(int i);
    }

    public interface OnPositionChangedListener {
        void onChanged(int i, boolean z);
    }

    public interface OnScrollSettledListener {
        void onScrollSettled(int i);
    }

    public enum ScrollMode {
        Immediate,
        Animate
    }

    private static final class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private static final SimpleDateFormat IMPORT_DAY_MONTH_FORMAT = new SimpleDateFormat("LLL dd", Locale.getDefault());
        private static final Calendar calendar = Calendar.getInstance();
        public TextView dayMonthView;
        public ImageView imageView;
        private GlideHideProgressListener loadListener;
        public PaddingView paddingView;
        public int position;
        public View progress;
        public View root;
        public TextView yearView;

        public ViewHolder(View view) {
            super(view);
            this.root = view;
            this.imageView = (ImageView) view.findViewById(R.id.image_view);
            this.paddingView = view instanceof PaddingView ? (PaddingView) view : null;
            this.dayMonthView = (TextView) view.findViewById(R.id.date_month_day);
            this.yearView = (TextView) view.findViewById(R.id.date_year);
            this.progress = view.findViewById(R.id.progress);
            this.loadListener = new GlideHideProgressListener();
        }

        /* access modifiers changed from: private */
        public void bindAsImage(Context context, ImageMetadataProvider imageMetadataProvider, int i, OnClickListener onClickListener) {
            this.imageView.setScaleType(ScaleType.CENTER);
            this.loadListener.setLoading(this.progress, this.imageView);
            Glide.with(context).load(imageMetadataProvider.getUri(i)).listener(this.loadListener).apply(HorizontalGalleryFragment.GLIDE_OPTIONS).into(this.imageView);
            this.position = i;
            this.root.setTag(this);
            this.root.setOnClickListener(onClickListener);
        }

        /* access modifiers changed from: private */
        public void bindAsImport(ImageMetadataProvider imageMetadataProvider, int i, OnClickListener onClickListener) {
            calendar.setTime(imageMetadataProvider.getDate(i));
            this.dayMonthView.setText(IMPORT_DAY_MONTH_FORMAT.format(calendar.getTime()));
            this.yearView.setText(String.valueOf(calendar.get(1)));
            this.root.setTag(this);
            this.root.setOnClickListener(onClickListener);
            this.position = i;
        }
    }

    public static HorizontalGalleryFragment newInstance() {
        return new HorizontalGalleryFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.horizontal_picker_layout, viewGroup, false);
        this.layoutManager = new LinearLayoutManager(getActivity());
        this.layoutManager.setOrientation(0);
        int i = this.relativePosition;
        if (i == -1 && bundle != null) {
            i = bundle.getInt(EXTRA_POSITION, i);
        }
        this.itemWidthPx = getResources().getDimensionPixelOffset(R.dimen.progress_photo_gallery_item);
        this.adapter = new Adapter(viewGroup.getContext(), this.onItemClickListener, layoutInflater, this.imageMetadataProvider);
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.addOnScrollListener(this.scrollChangeListener);
        this.recyclerView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                Rect rect = new Rect(i, i2, i3, i4);
                if (HorizontalGalleryFragment.this.lastLayoutSize == null || !HorizontalGalleryFragment.this.lastLayoutSize.equals(rect)) {
                    HorizontalGalleryFragment.this.lastLayoutSize = rect;
                    HorizontalGalleryFragment.this.resizeSettled = false;
                    HorizontalGalleryFragment.this.handler.post(HorizontalGalleryFragment.this.onLayoutChangedRunnable);
                    HorizontalGalleryFragment.this.resizeSettledDebouncer.call();
                }
            }
        });
        this.recyclerView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    HorizontalGalleryFragment.this.userDragging = true;
                }
                return false;
            }
        });
        this.relativePosition = i;
        this.recyclerView.setAdapter(this.adapter);
        return inflate;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(EXTRA_POSITION, this.relativePosition);
    }

    public double getPosition() {
        return (double) this.relativePosition;
    }

    public void setImageMetadataProvider(ImageMetadataProvider imageMetadataProvider2) {
        this.imageMetadataProvider = imageMetadataProvider2;
        Adapter adapter2 = this.adapter;
        if (adapter2 != null) {
            adapter2.setImageMetadataProvider(imageMetadataProvider2);
            checkScrollToFirstItem();
        }
    }

    public void setOnPositionChangedListener(OnPositionChangedListener onPositionChangedListener2) {
        this.onPositionChangedListener = onPositionChangedListener2;
    }

    public void setOnScrollSettledListener(OnScrollSettledListener onScrollSettledListener2) {
        this.onScrollSettledListener = onScrollSettledListener2;
    }

    public void setPosition(int i) {
        setPosition(i, ScrollMode.Animate);
    }

    public void setPosition(int i, ScrollMode scrollMode) {
        scrollToPosition(i, scrollMode);
    }

    public void notifyDataSetChanged() {
        Adapter adapter2 = this.adapter;
        if (adapter2 != null) {
            adapter2.notifyDataSetChanged();
            checkScrollToFirstItem();
        }
    }

    private void checkScrollToFirstItem() {
        if (this.relativePosition == -1) {
            Adapter adapter2 = this.adapter;
            if (adapter2 != null && adapter2.getItemCount() > 0) {
                setPosition(0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void setRelativePosition(int i) {
        this.relativePosition = i;
        OnPositionChangedListener onPositionChangedListener2 = this.onPositionChangedListener;
        if (onPositionChangedListener2 != null) {
            int i2 = this.relativePosition;
            if (i2 >= 0) {
                onPositionChangedListener2.onChanged(i2, this.userDragging);
            }
        }
    }

    /* access modifiers changed from: private */
    public void scrollToPosition(int i, ScrollMode scrollMode) {
        int i2 = i + 1;
        Adapter adapter2 = this.adapter;
        if (adapter2 != null && i2 > 0) {
            int min = Math.min(adapter2.getItemCount() - 2, Math.max(1, i2));
            if (!this.resizeSettled || scrollMode == ScrollMode.Immediate) {
                this.layoutManager.scrollToPositionWithOffset(min + 1, (this.recyclerView.getWidth() / 2) + (this.itemWidthPx / 2));
            } else {
                int calculateScrollX = calculateScrollX();
                int i3 = min - 1;
                int i4 = this.itemWidthPx;
                int i5 = ((i3 * i4) + (i4 / 2)) - calculateScrollX;
                if (i5 != 0) {
                    this.recyclerView.smoothScrollBy(i5, 0);
                }
            }
            setRelativePosition(min - 1);
        }
    }

    private View findCenteredItemView() {
        int findCenteredItemPosition = findCenteredItemPosition();
        if (findCenteredItemPosition == -1) {
            return null;
        }
        return this.layoutManager.findViewByPosition(findCenteredItemPosition);
    }

    private int findCenteredItemPosition() {
        return HorizontalRecyclerViewUtil.findCenteredItemPosition(this.recyclerView, this.layoutManager, 1, this.adapter.getItemCount() - 1);
    }

    /* access modifiers changed from: private */
    public void updateCurrentPosition() {
        int max = Math.max(1, Math.min(findCenteredItemPosition(), this.adapter.getItemCount() - 2)) - 1;
        if (max != this.relativePosition) {
            setRelativePosition(max);
        }
    }

    /* access modifiers changed from: private */
    public void snapToMiddleOfFocusedItem() {
        View findCenteredItemView = findCenteredItemView();
        if (findCenteredItemView != null && !(findCenteredItemView instanceof PaddingView)) {
            int max = (int) ((((double) Math.max(0, (findCenteredItemPosition() - 1) * this.itemWidthPx)) + ((double) (this.itemWidthPx / 2))) - ((double) calculateScrollX()));
            if (max != 0) {
                this.recyclerView.smoothScrollBy(max, 0);
            }
        }
    }

    private int calculateScrollX() {
        int findFirstVisibleItemPosition = this.layoutManager.findFirstVisibleItemPosition();
        View findViewByPosition = this.layoutManager.findViewByPosition(findFirstVisibleItemPosition);
        if (findViewByPosition == null) {
            return 0;
        }
        if (findFirstVisibleItemPosition == 0) {
            return Math.abs(findViewByPosition.getLeft());
        }
        return ((this.recyclerView.getWidth() / 2) + (this.itemWidthPx * (findFirstVisibleItemPosition - 1))) - findViewByPosition.getLeft();
    }

    private static int measureReferenceView(View view) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        view.measure(makeMeasureSpec, makeMeasureSpec);
        return view.getMeasuredWidth();
    }
}
