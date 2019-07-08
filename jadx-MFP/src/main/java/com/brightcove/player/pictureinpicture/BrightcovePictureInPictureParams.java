package com.brightcove.player.pictureinpicture;

import android.annotation.TargetApi;
import android.app.PictureInPictureParams;
import android.app.RemoteAction;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.util.Rational;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.List;

public final class BrightcovePictureInPictureParams implements Parcelable {
    public static final Creator<BrightcovePictureInPictureParams> CREATOR = new Creator<BrightcovePictureInPictureParams>() {
        public BrightcovePictureInPictureParams createFromParcel(Parcel parcel) {
            return new BrightcovePictureInPictureParams(parcel);
        }

        public BrightcovePictureInPictureParams[] newArray(int i) {
            return new BrightcovePictureInPictureParams[i];
        }
    };
    @Nullable
    private Rational mAspectRatio;
    private boolean mClosedCaptionsEnabled;
    private float mClosedCaptionsReductionScaleFactor;
    private boolean mOnUserLeaveEnabled;
    @Nullable
    private Rect mSourceRectHint;
    @Nullable
    private List<RemoteAction> mUserActions;

    public static class Builder {
        @Nullable
        private Rational mAspectRatio;
        private boolean mClosedCaptionsEnabled;
        private float mClosedCaptionsReductionScaleFactor = 1.0f;
        private boolean mOnUserLeaveEnabled;
        @Nullable
        private Rect mSourceRectHint;
        @Nullable
        private List<RemoteAction> mUserActions;

        public Builder setAspectRatio(Rational rational) {
            this.mAspectRatio = rational;
            return this;
        }

        public Builder setActions(List<RemoteAction> list) {
            if (this.mUserActions != null) {
                this.mUserActions = null;
            }
            if (list != null) {
                this.mUserActions = new ArrayList(list);
            }
            return this;
        }

        public Builder setSourceRectHint(Rect rect) {
            if (rect == null) {
                this.mSourceRectHint = null;
            } else {
                this.mSourceRectHint = new Rect(rect);
            }
            return this;
        }

        public Builder setClosedCaptionsEnabled(boolean z) {
            this.mClosedCaptionsEnabled = z;
            return this;
        }

        public Builder setOnUserLeaveEnabled(boolean z) {
            this.mOnUserLeaveEnabled = z;
            return this;
        }

        public Builder setClosedCaptionsReductionScaleFactor(float f) {
            if (f > BitmapDescriptorFactory.HUE_RED && f <= 1.0f) {
                this.mClosedCaptionsReductionScaleFactor = f;
            }
            return this;
        }

        public BrightcovePictureInPictureParams build() {
            BrightcovePictureInPictureParams brightcovePictureInPictureParams = new BrightcovePictureInPictureParams(this.mAspectRatio, this.mUserActions, this.mSourceRectHint, this.mClosedCaptionsEnabled, this.mOnUserLeaveEnabled, this.mClosedCaptionsReductionScaleFactor);
            return brightcovePictureInPictureParams;
        }
    }

    public int describeContents() {
        return 0;
    }

    BrightcovePictureInPictureParams() {
    }

    BrightcovePictureInPictureParams(Parcel parcel) {
        if (VERSION.SDK_INT >= 21 && parcel.readInt() != 0) {
            readRationalParcel(parcel);
        }
        if (VERSION.SDK_INT >= 26 && parcel.readInt() != 0) {
            readRemoteActionParcel(parcel);
        }
        if (parcel.readInt() != 0) {
            this.mSourceRectHint = (Rect) Rect.CREATOR.createFromParcel(parcel);
        }
        if (parcel.readInt() != 0) {
            this.mClosedCaptionsEnabled = true;
        }
        if (parcel.readInt() != 0) {
            this.mOnUserLeaveEnabled = true;
        }
        this.mClosedCaptionsReductionScaleFactor = parcel.readFloat();
    }

    @TargetApi(21)
    private void readRationalParcel(Parcel parcel) {
        this.mAspectRatio = new Rational(parcel.readInt(), parcel.readInt());
    }

    @TargetApi(26)
    private void readRemoteActionParcel(Parcel parcel) {
        this.mUserActions = new ArrayList();
        parcel.readList(this.mUserActions, RemoteAction.class.getClassLoader());
    }

    BrightcovePictureInPictureParams(@Nullable Rational rational, @Nullable List<RemoteAction> list, @Nullable Rect rect, boolean z, boolean z2, float f) {
        this.mAspectRatio = rational;
        this.mUserActions = list;
        this.mSourceRectHint = rect;
        this.mClosedCaptionsEnabled = z;
        this.mOnUserLeaveEnabled = z2;
        this.mClosedCaptionsReductionScaleFactor = f;
    }

    public void copyOnlySet(BrightcovePictureInPictureParams brightcovePictureInPictureParams) {
        if (brightcovePictureInPictureParams.hasSetAspectRatio()) {
            this.mAspectRatio = brightcovePictureInPictureParams.mAspectRatio;
        }
        if (brightcovePictureInPictureParams.hasSetActions()) {
            this.mUserActions = brightcovePictureInPictureParams.mUserActions;
        }
        if (brightcovePictureInPictureParams.hasSourceBoundsHint()) {
            this.mSourceRectHint = new Rect(brightcovePictureInPictureParams.getSourceRectHint());
        }
    }

    public float getAspectRatio() {
        if (VERSION.SDK_INT >= 21) {
            Rational rational = this.mAspectRatio;
            if (rational != null) {
                return rational.floatValue();
            }
        }
        return BitmapDescriptorFactory.HUE_RED;
    }

    public Rational getAspectRatioRational() {
        return this.mAspectRatio;
    }

    public boolean hasSetAspectRatio() {
        return this.mAspectRatio != null;
    }

    public List<RemoteAction> getActions() {
        return this.mUserActions;
    }

    public boolean hasSetActions() {
        return this.mUserActions != null;
    }

    public void truncateActions(int i) {
        List<RemoteAction> list = this.mUserActions;
        if (list != null) {
            this.mUserActions = list.subList(0, Math.min(list.size(), i));
        }
    }

    public Rect getSourceRectHint() {
        return this.mSourceRectHint;
    }

    public boolean hasSourceBoundsHint() {
        Rect rect = this.mSourceRectHint;
        return rect != null && !rect.isEmpty();
    }

    public boolean isClosedCaptionsEnabled() {
        return this.mClosedCaptionsEnabled;
    }

    public boolean isOnUserLeaveEnabled() {
        return this.mOnUserLeaveEnabled;
    }

    public float getClosedCaptionsReductionScaleFactor() {
        return this.mClosedCaptionsReductionScaleFactor;
    }

    @TargetApi(26)
    public PictureInPictureParams getAndroidPictureInPictureParams() {
        return new android.app.PictureInPictureParams.Builder().setActions(this.mUserActions).setAspectRatio(this.mAspectRatio).setSourceRectHint(this.mSourceRectHint).build();
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.mAspectRatio == null) {
            parcel.writeInt(0);
        } else if (VERSION.SDK_INT >= 21) {
            parcel.writeInt(1);
            parcel.writeInt(this.mAspectRatio.getNumerator());
            parcel.writeInt(this.mAspectRatio.getDenominator());
        }
        if (this.mUserActions == null) {
            parcel.writeInt(0);
        } else if (VERSION.SDK_INT >= 26) {
            parcel.writeInt(1);
            parcel.writeList(this.mUserActions);
        }
        if (this.mSourceRectHint != null) {
            parcel.writeInt(1);
            this.mSourceRectHint.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.mClosedCaptionsEnabled ? 1 : 0);
        parcel.writeInt(this.mOnUserLeaveEnabled ? 1 : 0);
        parcel.writeFloat(this.mClosedCaptionsReductionScaleFactor);
    }
}
