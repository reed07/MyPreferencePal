package com.samsung.android.sdk.healthdata;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.samsung.android.sdk.healthdata.HealthResultHolder.BaseResult;

public interface HealthResultHolder<T extends BaseResult> {

    public static class BaseResult implements Parcelable {
        public static final Creator<BaseResult> CREATOR = new Creator<BaseResult>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new BaseResult[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new BaseResult(parcel);
            }
        };
        public static final int STATUS_CANCELED = 2;
        public static final int STATUS_FAILED = 4;
        public static final int STATUS_INVALID_INPUT_DATA = 8;
        public static final int STATUS_OUT_OF_SPACE = 16;
        public static final int STATUS_SUCCESSFUL = 1;
        public static final int STATUS_UNKNOWN = 0;
        protected final boolean mCached;
        protected final int mCount;
        protected final int mStatus;

        public int describeContents() {
            return 0;
        }

        public BaseResult(int i, int i2) {
            this.mStatus = i;
            this.mCount = i2;
            this.mCached = true;
        }

        public BaseResult(int i, int i2, boolean z) {
            this.mStatus = i;
            this.mCount = i2;
            this.mCached = z;
        }

        protected BaseResult(Parcel parcel) {
            this.mStatus = parcel.readInt();
            this.mCount = parcel.readInt();
            boolean z = true;
            if (parcel.readInt() != 1) {
                z = false;
            }
            this.mCached = z;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mStatus);
            parcel.writeInt(this.mCount);
            parcel.writeInt(this.mCached ? 1 : 0);
        }

        public int getStatus() {
            return this.mStatus;
        }

        public int getCount() {
            return this.mCount;
        }
    }

    public interface ResultListener<T extends BaseResult> {
        void onResult(T t);
    }

    T await();

    void cancel();

    void setResultListener(ResultListener<T> resultListener);
}
