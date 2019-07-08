package com.mopub.nativeads;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.brightcove.player.model.VideoFields;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSource.CC;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException;
import com.mopub.common.CacheService;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpDiskCompositeDataSource implements DataSource {
    @Nullable
    private byte[] mCachedBytes;
    private int mDataBlockOffset;
    @Nullable
    private DataSpec mDataSpec;
    @Nullable
    private Integer mExpectedFileLength;
    @NonNull
    private final HttpDataSource mHttpDataSource;
    @NonNull
    private final TreeSet<IntInterval> mIntervals;
    private boolean mIsDirty;
    private boolean mIsHttpSourceOpen;
    @Nullable
    private String mKey;
    private int mSegment;
    private int mStartInDataBlock;
    private int mStartInFile;

    private static boolean areBytesAvailableInCache(int i, int i2, int i3) {
        return i > i2 + i3;
    }

    public /* synthetic */ Map<String, List<String>> getResponseHeaders() {
        return CC.$default$getResponseHeaders(this);
    }

    public HttpDiskCompositeDataSource(@NonNull Context context, @NonNull String str) {
        this(context, str, new DefaultHttpDataSource(str, null));
    }

    @VisibleForTesting
    HttpDiskCompositeDataSource(@NonNull Context context, @NonNull String str, @NonNull HttpDataSource httpDataSource) {
        this.mExpectedFileLength = null;
        this.mHttpDataSource = httpDataSource;
        CacheService.initialize(context);
        this.mIntervals = new TreeSet<>();
    }

    public long open(@NonNull DataSpec dataSpec) throws IOException {
        long j;
        long j2;
        DataSpec dataSpec2 = dataSpec;
        Preconditions.checkNotNull(dataSpec);
        if (dataSpec2.uri == null) {
            return -1;
        }
        this.mIsDirty = false;
        this.mDataSpec = dataSpec2;
        this.mKey = dataSpec2.uri.toString();
        if (this.mKey == null) {
            return -1;
        }
        this.mStartInFile = (int) dataSpec2.absoluteStreamPosition;
        this.mSegment = this.mStartInFile / 512000;
        StringBuilder sb = new StringBuilder();
        sb.append(this.mSegment);
        sb.append(this.mKey);
        this.mCachedBytes = CacheService.getFromDiskCache(sb.toString());
        this.mStartInDataBlock = this.mStartInFile % 512000;
        this.mDataBlockOffset = 0;
        this.mExpectedFileLength = getExpectedFileLengthFromDisk(this.mKey);
        populateIntervalsFromDisk(this.mKey, this.mIntervals);
        int firstContiguousPointAfter = getFirstContiguousPointAfter(this.mStartInFile, this.mIntervals);
        if (this.mCachedBytes == null) {
            this.mCachedBytes = new byte[512000];
            if (firstContiguousPointAfter > this.mStartInFile) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Cache segment ");
                sb2.append(this.mSegment);
                sb2.append(" was evicted. Invalidating cache");
                MoPubLog.d(sb2.toString());
                this.mIntervals.clear();
                firstContiguousPointAfter = (int) dataSpec2.absoluteStreamPosition;
            }
        }
        Integer num = this.mExpectedFileLength;
        if (num == null || firstContiguousPointAfter != num.intValue()) {
            if (this.mDataSpec.length == -1) {
                j2 = -1;
            } else {
                j2 = this.mDataSpec.length - ((long) (firstContiguousPointAfter - this.mStartInFile));
            }
            DataSpec dataSpec3 = r8;
            DataSpec dataSpec4 = new DataSpec(dataSpec2.uri, (long) firstContiguousPointAfter, j2, dataSpec2.key, dataSpec2.flags);
            try {
                long open = this.mHttpDataSource.open(dataSpec3);
                if (this.mExpectedFileLength == null && j2 == -1) {
                    this.mExpectedFileLength = Integer.valueOf((int) (((long) this.mStartInFile) + open));
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("expectedsize-");
                    sb3.append(this.mKey);
                    CacheService.putToDiskCache(sb3.toString(), String.valueOf(this.mExpectedFileLength).getBytes());
                }
                this.mIsHttpSourceOpen = true;
                j = open;
            } catch (InvalidResponseCodeException e) {
                if (e.responseCode == 416) {
                    Integer num2 = this.mExpectedFileLength;
                    j = num2 == null ? (long) (firstContiguousPointAfter - this.mStartInFile) : (long) (num2.intValue() - this.mStartInFile);
                    this.mIsHttpSourceOpen = false;
                } else {
                    throw e;
                }
            }
        } else {
            j = dataSpec2.length == -1 ? (long) (this.mExpectedFileLength.intValue() - this.mStartInFile) : dataSpec2.length;
        }
        return j;
    }

    private static void populateIntervalsFromDisk(@NonNull String str, @NonNull TreeSet<IntInterval> treeSet) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(treeSet);
        treeSet.clear();
        StringBuilder sb = new StringBuilder();
        sb.append("intervals-sorted-");
        sb.append(str);
        byte[] fromDiskCache = CacheService.getFromDiskCache(sb.toString());
        if (fromDiskCache != null) {
            try {
                JSONArray jSONArray = new JSONArray(new String(fromDiskCache));
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = new JSONObject((String) jSONArray.get(i));
                    treeSet.add(new IntInterval(jSONObject.getInt(TtmlNode.START), jSONObject.getInt(VideoFields.DURATION)));
                }
            } catch (JSONException e) {
                MoPubLog.d("clearing cache since invalid json intervals found", e);
                treeSet.clear();
            } catch (ClassCastException unused) {
                MoPubLog.d("clearing cache since unable to read json data");
                treeSet.clear();
            }
        }
    }

    private static Integer getExpectedFileLengthFromDisk(@NonNull String str) {
        Preconditions.checkNotNull(str);
        StringBuilder sb = new StringBuilder();
        sb.append("expectedsize-");
        sb.append(str);
        byte[] fromDiskCache = CacheService.getFromDiskCache(sb.toString());
        if (fromDiskCache == null) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(new String(fromDiskCache)));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public Uri getUri() {
        DataSpec dataSpec = this.mDataSpec;
        if (dataSpec != null) {
            return dataSpec.uri;
        }
        return null;
    }

    public void close() throws IOException {
        if (!TextUtils.isEmpty(this.mKey) && this.mCachedBytes != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mSegment);
            sb.append(this.mKey);
            CacheService.putToDiskCache(sb.toString(), this.mCachedBytes);
            addNewInterval(this.mIntervals, this.mStartInFile, this.mDataBlockOffset);
            writeIntervalsToDisk(this.mIntervals, this.mKey);
        }
        this.mCachedBytes = null;
        this.mHttpDataSource.close();
        this.mIsHttpSourceOpen = false;
        this.mStartInFile = 0;
        this.mDataBlockOffset = 0;
        this.mStartInDataBlock = 0;
        this.mExpectedFileLength = null;
        this.mIsDirty = false;
    }

    private static void writeIntervalsToDisk(@NonNull TreeSet<IntInterval> treeSet, @NonNull String str) {
        Preconditions.checkNotNull(treeSet);
        Preconditions.checkNotNull(str);
        JSONArray jSONArray = new JSONArray();
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            jSONArray.put((IntInterval) it.next());
        }
        StringBuilder sb = new StringBuilder();
        sb.append("intervals-sorted-");
        sb.append(str);
        CacheService.putToDiskCache(sb.toString(), jSONArray.toString().getBytes());
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        if (i4 > 512000) {
            StringBuilder sb = new StringBuilder();
            sb.append("Reading more than the block size (512000 bytes) at once is not possible. length = ");
            sb.append(i4);
            MoPubLog.d(sb.toString());
            return -1;
        } else if (this.mDataSpec == null) {
            MoPubLog.d("Unable to read from data source when no spec provided");
            return -1;
        } else if (this.mCachedBytes == null) {
            MoPubLog.d("No cache set up. Call open before read.");
            return -1;
        } else {
            int i5 = 512000 - this.mStartInDataBlock;
            int i6 = this.mDataBlockOffset;
            int i7 = i5 - i6;
            int firstContiguousPointAfter = getFirstContiguousPointAfter(this.mStartInFile + i6, this.mIntervals);
            int min = Math.min((firstContiguousPointAfter - this.mStartInFile) - this.mDataBlockOffset, i4);
            if (!areBytesAvailableInCache(firstContiguousPointAfter, this.mStartInFile, this.mDataBlockOffset)) {
                min = 0;
            } else if (min <= i7) {
                System.arraycopy(this.mCachedBytes, this.mStartInDataBlock + this.mDataBlockOffset, bArr2, i3, min);
                this.mDataBlockOffset += min;
                min += 0;
            } else {
                System.arraycopy(this.mCachedBytes, this.mStartInDataBlock + this.mDataBlockOffset, bArr2, i3, i7);
                this.mDataBlockOffset += i7;
                int i8 = i7 + 0;
                writeCacheToDiskAndClearVariables();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(this.mSegment);
                sb2.append(this.mKey);
                this.mCachedBytes = CacheService.getFromDiskCache(sb2.toString());
                byte[] bArr3 = this.mCachedBytes;
                if (bArr3 == null) {
                    MoPubLog.d("Unexpected cache miss. Invalidating cache");
                    this.mIntervals.clear();
                    this.mCachedBytes = new byte[512000];
                    this.mHttpDataSource.close();
                    HttpDataSource httpDataSource = this.mHttpDataSource;
                    DataSpec dataSpec = new DataSpec(this.mDataSpec.uri, (long) (this.mStartInFile + this.mDataBlockOffset), -1, this.mDataSpec.key, this.mDataSpec.flags);
                    httpDataSource.open(dataSpec);
                    this.mIsHttpSourceOpen = true;
                    min = i8;
                } else {
                    int i9 = i3 + i8;
                    int i10 = min - i8;
                    System.arraycopy(bArr3, this.mStartInDataBlock + this.mDataBlockOffset, bArr2, i9, i10);
                    this.mDataBlockOffset += i10;
                }
            }
            int i11 = i4 - min;
            if (i11 <= 0) {
                return min;
            }
            this.mIsDirty = true;
            if (!this.mIsHttpSourceOpen) {
                MoPubLog.d("end of cache reached. No http source open");
                return -1;
            }
            int i12 = i3 + min;
            int read = this.mHttpDataSource.read(bArr2, i12, i11);
            int i13 = this.mStartInDataBlock;
            int i14 = 512000 - i13;
            int i15 = this.mDataBlockOffset;
            int i16 = i14 - i15;
            if (i16 < read) {
                System.arraycopy(bArr2, i12, this.mCachedBytes, i13 + i15, i16);
                this.mDataBlockOffset += i16;
                writeCacheToDiskAndClearVariables();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(this.mSegment);
                sb3.append(this.mKey);
                this.mCachedBytes = CacheService.getFromDiskCache(sb3.toString());
                if (this.mCachedBytes == null) {
                    this.mCachedBytes = new byte[512000];
                }
                int i17 = read - i16;
                System.arraycopy(bArr2, i3 + i16 + min, this.mCachedBytes, this.mStartInDataBlock + this.mDataBlockOffset, i17);
                this.mDataBlockOffset += i17;
            } else {
                System.arraycopy(bArr2, i12, this.mCachedBytes, i13 + i15, read);
                this.mDataBlockOffset += read;
            }
            return read + min;
        }
    }

    private void writeCacheToDiskAndClearVariables() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mSegment);
        sb.append(this.mKey);
        CacheService.putToDiskCache(sb.toString(), this.mCachedBytes);
        addNewInterval(this.mIntervals, this.mStartInFile, this.mDataBlockOffset);
        this.mStartInDataBlock = 0;
        this.mStartInFile += this.mDataBlockOffset;
        this.mDataBlockOffset = 0;
        this.mSegment = this.mStartInFile / 512000;
    }

    @VisibleForTesting
    static int getFirstContiguousPointAfter(int i, @NonNull TreeSet<IntInterval> treeSet) {
        Preconditions.checkNotNull(treeSet);
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            IntInterval intInterval = (IntInterval) it.next();
            if (intInterval.getStart() <= i) {
                i = Math.max(i, intInterval.getStart() + intInterval.getLength());
            }
        }
        return i;
    }

    @VisibleForTesting
    static void addNewInterval(@NonNull TreeSet<IntInterval> treeSet, int i, int i2) {
        Preconditions.checkNotNull(treeSet);
        if (getFirstContiguousPointAfter(i, treeSet) < i + i2) {
            treeSet.add(new IntInterval(i, i2));
        }
    }
}
