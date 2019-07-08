package io.uacf.inbox.ui.debug.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.uacf.core.caching.LruCache;
import com.uacf.core.caching.image.DiskBackedBitmapCache;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.exception.UacfNotImplementedException;
import io.uacf.inbox.R;
import io.uacf.inbox.sdk.UacfNotificationSdk;
import io.uacf.inbox.sdk.UacfNotificationSdkFactory;
import io.uacf.inbox.sdk.model.UacfNotification;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Map;

public class InboxCursorRecyclerViewAdapter extends Adapter<ViewHolder> {
    private NotificationCheckedCallback callback;
    /* access modifiers changed from: private */
    public Map<String, Integer> checkedNotifications;
    private final Context context;
    private InboxCursorAdapter cursorAdapter;
    private DiskBackedBitmapCache imageCache;
    private UacfNotificationSdk notificationSdk = new UacfNotificationSdkFactory().newSdkInstance();

    private static class ImageLoadTask extends AsyncTask<String, Void, Bitmap> {
        private WeakReference<DiskBackedBitmapCache> imageCacheWeakReference;
        private WeakReference<ViewHolder> viewHolderWeakReference;

        private ImageLoadTask(ViewHolder viewHolder, DiskBackedBitmapCache diskBackedBitmapCache) {
            this.viewHolderWeakReference = new WeakReference<>(viewHolder);
            this.imageCacheWeakReference = new WeakReference<>(diskBackedBitmapCache);
        }

        /* access modifiers changed from: protected */
        public Bitmap doInBackground(String... strArr) {
            DiskBackedBitmapCache diskBackedBitmapCache = (DiskBackedBitmapCache) this.imageCacheWeakReference.get();
            String str = strArr[0];
            Bitmap bitmap = null;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (diskBackedBitmapCache != null && diskBackedBitmapCache.contains(str)) {
                return diskBackedBitmapCache.get(str);
            }
            try {
                bitmap = BitmapFactory.decodeStream(new URL(str).openConnection().getInputStream());
                if (diskBackedBitmapCache != null) {
                    diskBackedBitmapCache.put(str, bitmap);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return bitmap;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Bitmap bitmap) {
            ViewHolder viewHolder = (ViewHolder) this.viewHolderWeakReference.get();
            if (viewHolder == null) {
                return;
            }
            if (bitmap != null) {
                viewHolder.image.setVisibility(0);
                viewHolder.noImageText.setVisibility(4);
                viewHolder.image.setImageBitmap(bitmap);
                return;
            }
            viewHolder.image.setVisibility(4);
            viewHolder.noImageText.setVisibility(0);
        }
    }

    private final class InboxCursorAdapter extends CursorAdapter {
        private InboxCursorAdapter(Context context, Cursor cursor, int i) {
            super(context, cursor, i);
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            throw new UacfNotImplementedException();
        }

        public void bindView(View view, Context context, Cursor cursor) {
            throw new UacfNotImplementedException();
        }

        public void bindView(ViewHolder viewHolder, Context context, Cursor cursor) {
            viewHolder.setData(cursor);
        }
    }

    public interface NotificationCheckedCallback {
        void notificationChecked(boolean z, String str, int i);
    }

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private TextView bodyText;
        /* access modifiers changed from: private */
        public NotificationCheckedCallback callback;
        private TextView categoryText;
        private CheckBox checkBox;
        private Context context;
        private TextView createAtText;
        private TextView expirationDateText;
        private TextView idText;
        /* access modifiers changed from: private */
        public ImageView image;
        private DiskBackedBitmapCache imageCache;
        /* access modifiers changed from: private */
        public UacfNotification item;
        private TextView markedAsExpiredText;
        /* access modifiers changed from: private */
        public TextView noImageText;
        private UacfNotificationSdk notificationSdk = new UacfNotificationSdkFactory().newSdkInstance();
        private TextView priorityText;
        private View rootView;
        private TextView stateText;

        public ViewHolder(Context context2, View view, NotificationCheckedCallback notificationCheckedCallback, DiskBackedBitmapCache diskBackedBitmapCache) {
            super(view);
            this.context = context2;
            this.rootView = view;
            this.callback = notificationCheckedCallback;
            this.imageCache = diskBackedBitmapCache;
            this.idText = (TextView) this.rootView.findViewById(R.id.idText);
            this.stateText = (TextView) this.rootView.findViewById(R.id.stateText);
            this.bodyText = (TextView) this.rootView.findViewById(R.id.bodyText);
            this.createAtText = (TextView) this.rootView.findViewById(R.id.time_created);
            this.expirationDateText = (TextView) this.rootView.findViewById(R.id.expirationDate);
            this.priorityText = (TextView) this.rootView.findViewById(R.id.priority);
            this.categoryText = (TextView) this.rootView.findViewById(R.id.category);
            this.markedAsExpiredText = (TextView) this.rootView.findViewById(R.id.markedAsExpired);
            this.image = (ImageView) this.rootView.findViewById(R.id.image);
            this.noImageText = (TextView) this.rootView.findViewById(R.id.noImageText);
            this.checkBox = (CheckBox) this.rootView.findViewById(R.id.checkBox);
        }

        /* access modifiers changed from: private */
        public void setData(Cursor cursor) {
            this.item = this.notificationSdk.getObjectFromCursor(cursor);
            this.idText.setText(this.item.getEngagementId());
            this.bodyText.setText(this.item.getBodyText());
            String format = Format.newDatabaseDateTimeFormat().format(new Date(this.item.getCreatedAt().getTime()));
            this.createAtText.setText(String.format(this.context.getString(R.string.created_at_value_string), new Object[]{format}));
            this.stateText.setText(String.format(this.context.getString(R.string.state_value_string), new Object[]{this.item.getState()}));
            this.expirationDateText.setText(String.format(this.context.getString(R.string.expiration_date_value_string), new Object[]{this.item.getExpiresAt()}));
            this.priorityText.setText(String.format(this.context.getString(R.string.priority_value_string), new Object[]{Boolean.valueOf(this.item.getPriority())}));
            this.categoryText.setText(String.format(this.context.getString(R.string.category_value_string), new Object[]{this.item.getCategory()}));
            this.markedAsExpiredText.setText(String.format(this.context.getString(R.string.marked_as_expired_value_string), new Object[]{Boolean.valueOf(this.item.getMarkedAsExpired())}));
            if (this.item.getPrimaryImageUri() != null) {
                new ImageLoadTask(this, this.imageCache).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{this.item.getPrimaryImageUri()});
            }
            this.checkBox.setChecked(InboxCursorRecyclerViewAdapter.this.checkedNotifications.containsKey(this.item.getEngagementId()));
            this.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    ViewHolder.this.callback.notificationChecked(z, ViewHolder.this.item.getEngagementId(), ViewHolder.this.getAdapterPosition());
                }
            });
        }
    }

    public InboxCursorRecyclerViewAdapter(Context context2, NotificationCheckedCallback notificationCheckedCallback, Map<String, Integer> map) {
        this.context = context2;
        InboxCursorAdapter inboxCursorAdapter = new InboxCursorAdapter(context2, this.notificationSdk.getAllNotificationsAsCursor(), 0);
        this.cursorAdapter = inboxCursorAdapter;
        this.callback = notificationCheckedCallback;
        this.checkedNotifications = map;
        this.imageCache = new DiskBackedBitmapCache(context2, "ni_image_cache", new LruCache());
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder viewHolder = new ViewHolder(this.context, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_list_item, viewGroup, false), this.callback, this.imageCache);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        this.cursorAdapter.getCursor().moveToPosition(i);
        InboxCursorAdapter inboxCursorAdapter = this.cursorAdapter;
        inboxCursorAdapter.bindView(viewHolder, this.context, inboxCursorAdapter.getCursor());
    }

    public int getItemCount() {
        return this.cursorAdapter.getCount();
    }

    public void refreshCursor() {
        this.cursorAdapter.changeCursor(this.notificationSdk.getAllNotificationsAsCursor());
    }
}
