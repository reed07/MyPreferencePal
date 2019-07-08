package com.brightcove.player.model;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class MediaFormat implements Parcelable {
    public static final Creator<MediaFormat> CREATOR = new Creator() {
        public MediaFormat createFromParcel(Parcel parcel) {
            return new MediaFormat(parcel);
        }

        public MediaFormat[] newArray(int i) {
            return new MediaFormat[i];
        }
    };
    public static final List<byte[]> NO_INITIALIZATION_DATA = Collections.unmodifiableList(new ArrayList());
    public static final long OFFSET_SAMPLE_RELATIVE = Long.MAX_VALUE;
    public final boolean adaptive;
    public final int bitrate;
    public final int channelCount;
    public final long durationUs;
    public final int encoderDelay;
    public final int encoderPadding;
    private android.media.MediaFormat frameworkMediaFormat;
    private int hashCode;
    public final int height;
    @NonNull
    public final List<byte[]> initializationData;
    @Nullable
    public final String language;
    public final int maxHeight;
    public final int maxInputSize;
    public final int maxWidth;
    @NonNull
    public final String mimeType;
    public final int pcmEncoding;
    public final float pixelWidthHeightRatio;
    @Nullable
    public final byte[] projectionData;
    public final int rotationDegrees;
    public final int sampleRate;
    public final int stereoMode;
    public final long subSampleOffsetUs;
    @Nullable
    public final String trackId;
    public final int width;

    public int describeContents() {
        return 0;
    }

    MediaFormat(@Nullable String str, @NonNull String str2, int i, int i2, long j, int i3, int i4, int i5, float f, int i6, int i7, String str3, long j2, List<byte[]> list, boolean z, int i8, int i9, int i10, int i11, int i12, byte[] bArr, int i13) {
        if (!TextUtils.isEmpty(str2)) {
            this.trackId = str;
            this.mimeType = str2;
            this.bitrate = i;
            this.maxInputSize = i2;
            this.durationUs = j;
            this.width = i3;
            this.height = i4;
            this.rotationDegrees = i5;
            this.pixelWidthHeightRatio = f;
            this.channelCount = i6;
            this.sampleRate = i7;
            this.language = str3;
            this.subSampleOffsetUs = j2;
            this.initializationData = list == null ? NO_INITIALIZATION_DATA : Collections.unmodifiableList(list);
            this.adaptive = z;
            this.maxWidth = i8;
            this.maxHeight = i9;
            this.pcmEncoding = i10;
            this.encoderDelay = i11;
            this.encoderPadding = i12;
            this.projectionData = bArr;
            this.stereoMode = i13;
            return;
        }
        throw new IllegalArgumentException("Mime type is empty!");
    }

    public static MediaFormat createVideoFormat(String str, String str2, int i, int i2, long j, int i3, int i4, List<byte[]> list) {
        return createVideoFormat(str, str2, i, i2, j, i3, i4, list, -1, -1.0f, null, -1);
    }

    public static MediaFormat createVideoFormat(String str, String str2, int i, int i2, long j, int i3, int i4, List<byte[]> list, int i5, float f) {
        MediaFormat mediaFormat = new MediaFormat(str, str2, i, i2, j, i3, i4, i5, f, -1, -1, null, Long.MAX_VALUE, list, false, -1, -1, -1, -1, -1, null, -1);
        return mediaFormat;
    }

    public static MediaFormat createVideoFormat(String str, String str2, int i, int i2, long j, int i3, int i4, List<byte[]> list, int i5, float f, byte[] bArr, int i6) {
        MediaFormat mediaFormat = new MediaFormat(str, str2, i, i2, j, i3, i4, i5, f, -1, -1, null, Long.MAX_VALUE, list, false, -1, -1, -1, -1, -1, bArr, i6);
        return mediaFormat;
    }

    public static MediaFormat createAudioFormat(String str, String str2, int i, int i2, long j, int i3, int i4, List<byte[]> list, String str3) {
        return createAudioFormat(str, str2, i, i2, j, i3, i4, list, str3, -1);
    }

    public static MediaFormat createAudioFormat(String str, String str2, int i, int i2, long j, int i3, int i4, List<byte[]> list, String str3, int i5) {
        MediaFormat mediaFormat = new MediaFormat(str, str2, i, i2, j, -1, -1, -1, -1.0f, i3, i4, str3, Long.MAX_VALUE, list, false, -1, -1, i5, -1, -1, null, -1);
        return mediaFormat;
    }

    public static MediaFormat createTextFormat(String str, String str2, int i, long j, String str3) {
        return createTextFormat(str, str2, i, j, str3, Long.MAX_VALUE);
    }

    public static MediaFormat createTextFormat(String str, String str2, int i, long j, String str3, long j2) {
        MediaFormat mediaFormat = new MediaFormat(str, str2, i, -1, j, -1, -1, -1, -1.0f, -1, -1, str3, j2, null, false, -1, -1, -1, -1, -1, null, -1);
        return mediaFormat;
    }

    public static MediaFormat createImageFormat(String str, String str2, int i, long j, List<byte[]> list, String str3) {
        MediaFormat mediaFormat = new MediaFormat(str, str2, i, -1, j, -1, -1, -1, -1.0f, -1, -1, str3, Long.MAX_VALUE, list, false, -1, -1, -1, -1, -1, null, -1);
        return mediaFormat;
    }

    public static MediaFormat createFormatForMimeType(String str, String str2, int i, long j) {
        MediaFormat mediaFormat = new MediaFormat(str, str2, i, -1, j, -1, -1, -1, -1.0f, -1, -1, null, Long.MAX_VALUE, null, false, -1, -1, -1, -1, -1, null, -1);
        return mediaFormat;
    }

    public static MediaFormat createId3Format() {
        return createFormatForMimeType(null, MimeTypes.APPLICATION_ID3, -1, -1);
    }

    public MediaFormat copyWithMaxInputSize(int i) {
        int i2 = i;
        MediaFormat mediaFormat = new MediaFormat(this.trackId, this.mimeType, this.bitrate, i2, this.durationUs, this.width, this.height, this.rotationDegrees, this.pixelWidthHeightRatio, this.channelCount, this.sampleRate, this.language, this.subSampleOffsetUs, this.initializationData, this.adaptive, this.maxWidth, this.maxHeight, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.projectionData, this.stereoMode);
        return mediaFormat;
    }

    public MediaFormat copyWithMaxVideoDimensions(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        MediaFormat mediaFormat = new MediaFormat(this.trackId, this.mimeType, this.bitrate, this.maxInputSize, this.durationUs, this.width, this.height, this.rotationDegrees, this.pixelWidthHeightRatio, this.channelCount, this.sampleRate, this.language, this.subSampleOffsetUs, this.initializationData, this.adaptive, i3, i4, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.projectionData, this.stereoMode);
        return mediaFormat;
    }

    public MediaFormat copyWithSubSampleOffsetUs(long j) {
        MediaFormat mediaFormat = new MediaFormat(this.trackId, this.mimeType, this.bitrate, this.maxInputSize, this.durationUs, this.width, this.height, this.rotationDegrees, this.pixelWidthHeightRatio, this.channelCount, this.sampleRate, this.language, j, this.initializationData, this.adaptive, this.maxWidth, this.maxHeight, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.projectionData, this.stereoMode);
        return mediaFormat;
    }

    public MediaFormat copyWithDurationUs(long j) {
        long j2 = j;
        MediaFormat mediaFormat = new MediaFormat(this.trackId, this.mimeType, this.bitrate, this.maxInputSize, j2, this.width, this.height, this.rotationDegrees, this.pixelWidthHeightRatio, this.channelCount, this.sampleRate, this.language, this.subSampleOffsetUs, this.initializationData, this.adaptive, this.maxWidth, this.maxHeight, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.projectionData, this.stereoMode);
        return mediaFormat;
    }

    public MediaFormat copyWithLanguage(String str) {
        String str2 = str;
        MediaFormat mediaFormat = new MediaFormat(this.trackId, this.mimeType, this.bitrate, this.maxInputSize, this.durationUs, this.width, this.height, this.rotationDegrees, this.pixelWidthHeightRatio, this.channelCount, this.sampleRate, str2, this.subSampleOffsetUs, this.initializationData, this.adaptive, this.maxWidth, this.maxHeight, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.projectionData, this.stereoMode);
        return mediaFormat;
    }

    public MediaFormat copyWithFixedTrackInfo(String str, int i, int i2, int i3, String str2) {
        String str3 = str;
        String str4 = str;
        MediaFormat mediaFormat = new MediaFormat(str4, this.mimeType, i, this.maxInputSize, this.durationUs, i2, i3, this.rotationDegrees, this.pixelWidthHeightRatio, this.channelCount, this.sampleRate, str2, this.subSampleOffsetUs, this.initializationData, this.adaptive, -1, -1, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.projectionData, this.stereoMode);
        return mediaFormat;
    }

    public MediaFormat copyAsAdaptive(String str) {
        MediaFormat mediaFormat = new MediaFormat(str, this.mimeType, -1, -1, this.durationUs, -1, -1, -1, -1.0f, -1, -1, null, Long.MAX_VALUE, null, true, this.maxWidth, this.maxHeight, -1, -1, -1, null, this.stereoMode);
        return mediaFormat;
    }

    public MediaFormat copyWithGaplessInfo(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        MediaFormat mediaFormat = new MediaFormat(this.trackId, this.mimeType, this.bitrate, this.maxInputSize, this.durationUs, this.width, this.height, this.rotationDegrees, this.pixelWidthHeightRatio, this.channelCount, this.sampleRate, this.language, this.subSampleOffsetUs, this.initializationData, this.adaptive, this.maxWidth, this.maxHeight, this.pcmEncoding, i3, i4, this.projectionData, this.stereoMode);
        return mediaFormat;
    }

    @SuppressLint({"InlinedApi"})
    @TargetApi(16)
    public final android.media.MediaFormat getFrameworkMediaFormatV16() {
        if (this.frameworkMediaFormat == null) {
            android.media.MediaFormat mediaFormat = new android.media.MediaFormat();
            mediaFormat.setString("mime", this.mimeType);
            maybeSetStringV16(mediaFormat, "language", this.language);
            maybeSetIntegerV16(mediaFormat, "max-input-size", this.maxInputSize);
            maybeSetIntegerV16(mediaFormat, "width", this.width);
            maybeSetIntegerV16(mediaFormat, "height", this.height);
            maybeSetIntegerV16(mediaFormat, "rotation-degrees", this.rotationDegrees);
            maybeSetIntegerV16(mediaFormat, "max-width", this.maxWidth);
            maybeSetIntegerV16(mediaFormat, "max-height", this.maxHeight);
            maybeSetIntegerV16(mediaFormat, "channel-count", this.channelCount);
            maybeSetIntegerV16(mediaFormat, "sample-rate", this.sampleRate);
            maybeSetIntegerV16(mediaFormat, "encoder-delay", this.encoderDelay);
            maybeSetIntegerV16(mediaFormat, "encoder-padding", this.encoderPadding);
            for (int i = 0; i < this.initializationData.size(); i++) {
                StringBuilder sb = new StringBuilder();
                sb.append("csd-");
                sb.append(i);
                mediaFormat.setByteBuffer(sb.toString(), ByteBuffer.wrap((byte[]) this.initializationData.get(i)));
            }
            long j = this.durationUs;
            if (j != -1) {
                mediaFormat.setLong("durationUs", j);
            }
            this.frameworkMediaFormat = mediaFormat;
        }
        return this.frameworkMediaFormat;
    }

    @TargetApi(16)
    private static void maybeSetStringV16(android.media.MediaFormat mediaFormat, String str, String str2) {
        if (str2 != null) {
            mediaFormat.setString(str, str2);
        }
    }

    @TargetApi(16)
    private static void maybeSetIntegerV16(android.media.MediaFormat mediaFormat, String str, int i) {
        if (i != -1) {
            mediaFormat.setInteger(str, i);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MediaFormat(");
        sb.append(this.trackId);
        sb.append(", ");
        sb.append(this.mimeType);
        sb.append(", ");
        sb.append(this.bitrate);
        sb.append(", ");
        sb.append(this.maxInputSize);
        sb.append(", ");
        sb.append(this.width);
        sb.append(", ");
        sb.append(this.height);
        sb.append(", ");
        sb.append(this.rotationDegrees);
        sb.append(", ");
        sb.append(this.pixelWidthHeightRatio);
        sb.append(", ");
        sb.append(this.channelCount);
        sb.append(", ");
        sb.append(this.sampleRate);
        sb.append(", ");
        sb.append(this.language);
        sb.append(", ");
        sb.append(this.durationUs);
        sb.append(", ");
        sb.append(this.adaptive);
        sb.append(", ");
        sb.append(this.maxWidth);
        sb.append(", ");
        sb.append(this.maxHeight);
        sb.append(", ");
        sb.append(this.pcmEncoding);
        sb.append(", ");
        sb.append(this.encoderDelay);
        sb.append(", ");
        sb.append(this.encoderPadding);
        sb.append(")");
        return sb.toString();
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            String str = this.trackId;
            int hashCode2 = (527 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.mimeType;
            int hashCode3 = (((((((((((((((((((((((((((((((hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.bitrate) * 31) + this.maxInputSize) * 31) + this.width) * 31) + this.height) * 31) + this.rotationDegrees) * 31) + Float.floatToRawIntBits(this.pixelWidthHeightRatio)) * 31) + ((int) this.durationUs)) * 31) + (this.adaptive ? 1231 : 1237)) * 31) + this.maxWidth) * 31) + this.maxHeight) * 31) + this.channelCount) * 31) + this.sampleRate) * 31) + this.pcmEncoding) * 31) + this.encoderDelay) * 31) + this.encoderPadding) * 31;
            String str3 = this.language;
            int hashCode4 = ((hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31) + ((int) this.subSampleOffsetUs);
            for (int i = 0; i < this.initializationData.size(); i++) {
                hashCode4 = (hashCode4 * 31) + Arrays.hashCode((byte[]) this.initializationData.get(i));
            }
            this.hashCode = (((hashCode4 * 31) + Arrays.hashCode(this.projectionData)) * 31) + this.stereoMode;
        }
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaFormat mediaFormat = (MediaFormat) obj;
        if (this.adaptive != mediaFormat.adaptive || this.bitrate != mediaFormat.bitrate || this.maxInputSize != mediaFormat.maxInputSize || this.durationUs != mediaFormat.durationUs || this.width != mediaFormat.width || this.height != mediaFormat.height || this.rotationDegrees != mediaFormat.rotationDegrees || this.pixelWidthHeightRatio != mediaFormat.pixelWidthHeightRatio || this.maxWidth != mediaFormat.maxWidth || this.maxHeight != mediaFormat.maxHeight || this.channelCount != mediaFormat.channelCount || this.sampleRate != mediaFormat.sampleRate || this.pcmEncoding != mediaFormat.pcmEncoding || this.encoderDelay != mediaFormat.encoderDelay || this.encoderPadding != mediaFormat.encoderPadding || this.subSampleOffsetUs != mediaFormat.subSampleOffsetUs || !TextUtils.equals(this.trackId, mediaFormat.trackId) || !TextUtils.equals(this.language, mediaFormat.language) || !TextUtils.equals(this.mimeType, mediaFormat.mimeType) || this.initializationData.size() != mediaFormat.initializationData.size() || !Arrays.equals(this.projectionData, mediaFormat.projectionData) || this.stereoMode != mediaFormat.stereoMode) {
            return false;
        }
        for (int i = 0; i < this.initializationData.size(); i++) {
            if (!Arrays.equals((byte[]) this.initializationData.get(i), (byte[]) mediaFormat.initializationData.get(i))) {
                return false;
            }
        }
        return true;
    }

    MediaFormat(@NonNull Parcel parcel) {
        this.trackId = parcel.readString();
        this.mimeType = parcel.readString();
        this.bitrate = parcel.readInt();
        this.maxInputSize = parcel.readInt();
        this.durationUs = parcel.readLong();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.rotationDegrees = parcel.readInt();
        this.pixelWidthHeightRatio = parcel.readFloat();
        this.channelCount = parcel.readInt();
        this.sampleRate = parcel.readInt();
        this.language = parcel.readString();
        this.subSampleOffsetUs = parcel.readLong();
        ArrayList arrayList = new ArrayList();
        byte[] bArr = null;
        parcel.readList(arrayList, null);
        this.initializationData = Collections.unmodifiableList(arrayList);
        boolean z = false;
        this.adaptive = parcel.readInt() == 1;
        this.maxWidth = parcel.readInt();
        this.maxHeight = parcel.readInt();
        this.pcmEncoding = parcel.readInt();
        this.encoderDelay = parcel.readInt();
        this.encoderPadding = parcel.readInt();
        if (parcel.readInt() != 0) {
            z = true;
        }
        if (z) {
            bArr = parcel.createByteArray();
        }
        this.projectionData = bArr;
        this.stereoMode = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.trackId);
        parcel.writeString(this.mimeType);
        parcel.writeInt(this.bitrate);
        parcel.writeInt(this.maxInputSize);
        parcel.writeLong(this.durationUs);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeInt(this.rotationDegrees);
        parcel.writeFloat(this.pixelWidthHeightRatio);
        parcel.writeInt(this.channelCount);
        parcel.writeInt(this.sampleRate);
        parcel.writeString(this.language);
        parcel.writeLong(this.subSampleOffsetUs);
        parcel.writeList(this.initializationData);
        parcel.writeInt(this.adaptive ? 1 : 0);
        parcel.writeInt(this.maxWidth);
        parcel.writeInt(this.maxHeight);
        parcel.writeInt(this.pcmEncoding);
        parcel.writeInt(this.encoderDelay);
        parcel.writeInt(this.encoderPadding);
        parcel.writeInt(this.projectionData != null ? 1 : 0);
        byte[] bArr = this.projectionData;
        if (bArr != null) {
            parcel.writeByteArray(bArr);
        }
        parcel.writeInt(this.stereoMode);
    }
}
