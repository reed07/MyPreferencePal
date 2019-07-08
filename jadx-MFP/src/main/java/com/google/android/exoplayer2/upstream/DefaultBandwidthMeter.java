package com.google.android.exoplayer2.upstream;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.upstream.BandwidthMeter.EventListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.EventDispatcher;
import com.google.android.exoplayer2.util.EventDispatcher.Event;
import com.google.android.exoplayer2.util.SlidingPercentile;
import com.google.android.exoplayer2.util.Util;
import com.google.zxing.client.result.ExpandedProductParsedResult;
import com.myfitnesspal.shared.model.v1.Country;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class DefaultBandwidthMeter implements BandwidthMeter, TransferListener {
    private static final int BYTES_TRANSFERRED_FOR_ESTIMATE = 524288;
    public static final Map<String, int[]> DEFAULT_INITIAL_BITRATE_COUNTRY_GROUPS = createInitialBitrateCountryGroupAssignment();
    public static final long DEFAULT_INITIAL_BITRATE_ESTIMATE = 1000000;
    public static final long[] DEFAULT_INITIAL_BITRATE_ESTIMATES_2G = {169000, 129000, 114000, 102000, 87000};
    public static final long[] DEFAULT_INITIAL_BITRATE_ESTIMATES_3G = {2100000, 1300000, 950000, 700000, 400000};
    public static final long[] DEFAULT_INITIAL_BITRATE_ESTIMATES_4G = {6900000, 4300000, 2700000, 1600000, 450000};
    public static final long[] DEFAULT_INITIAL_BITRATE_ESTIMATES_WIFI = {5700000, 3400000, 1900000, 1000000, 400000};
    public static final int DEFAULT_SLIDING_WINDOW_MAX_WEIGHT = 2000;
    private static final int ELAPSED_MILLIS_FOR_ESTIMATE = 2000;
    private long bitrateEstimate;
    private final Clock clock;
    private final EventDispatcher<EventListener> eventDispatcher;
    private long sampleBytesTransferred;
    private long sampleStartTimeMs;
    private final SlidingPercentile slidingPercentile;
    private int streamCount;
    private long totalBytesTransferred;
    private long totalElapsedTimeMs;

    public static final class Builder {
        private Clock clock;
        @Nullable
        private final Context context;
        @Nullable
        private Handler eventHandler;
        @Nullable
        private EventListener eventListener;
        private SparseArray<Long> initialBitrateEstimates;
        private int slidingWindowMaxWeight;

        @Deprecated
        public Builder() {
            this(null);
        }

        public Builder(@Nullable Context context2) {
            Context context3;
            if (context2 == null) {
                context3 = null;
            } else {
                context3 = context2.getApplicationContext();
            }
            this.context = context3;
            this.initialBitrateEstimates = getInitialBitrateEstimatesForCountry(Util.getCountryCode(context2));
            this.slidingWindowMaxWeight = 2000;
            this.clock = Clock.DEFAULT;
        }

        public Builder setEventListener(Handler handler, EventListener eventListener2) {
            Assertions.checkArgument((handler == null || eventListener2 == null) ? false : true);
            this.eventHandler = handler;
            this.eventListener = eventListener2;
            return this;
        }

        public Builder setSlidingWindowMaxWeight(int i) {
            this.slidingWindowMaxWeight = i;
            return this;
        }

        public Builder setInitialBitrateEstimate(long j) {
            for (int i = 0; i < this.initialBitrateEstimates.size(); i++) {
                this.initialBitrateEstimates.setValueAt(i, Long.valueOf(j));
            }
            return this;
        }

        public Builder setInitialBitrateEstimate(int i, long j) {
            this.initialBitrateEstimates.put(i, Long.valueOf(j));
            return this;
        }

        public Builder setInitialBitrateEstimate(String str) {
            this.initialBitrateEstimates = getInitialBitrateEstimatesForCountry(Util.toUpperInvariant(str));
            return this;
        }

        public Builder setClock(Clock clock2) {
            this.clock = clock2;
            return this;
        }

        public DefaultBandwidthMeter build() {
            Long l = (Long) this.initialBitrateEstimates.get(Util.getNetworkType(this.context));
            if (l == null) {
                l = (Long) this.initialBitrateEstimates.get(0);
            }
            DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter(l.longValue(), this.slidingWindowMaxWeight, this.clock);
            Handler handler = this.eventHandler;
            if (handler != null) {
                EventListener eventListener2 = this.eventListener;
                if (eventListener2 != null) {
                    defaultBandwidthMeter.addEventListener(handler, eventListener2);
                }
            }
            return defaultBandwidthMeter;
        }

        private static SparseArray<Long> getInitialBitrateEstimatesForCountry(String str) {
            int[] countryGroupIndices = getCountryGroupIndices(str);
            SparseArray<Long> sparseArray = new SparseArray<>(6);
            sparseArray.append(0, Long.valueOf(1000000));
            sparseArray.append(2, Long.valueOf(DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_WIFI[countryGroupIndices[0]]));
            sparseArray.append(3, Long.valueOf(DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_2G[countryGroupIndices[1]]));
            sparseArray.append(4, Long.valueOf(DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_3G[countryGroupIndices[2]]));
            sparseArray.append(5, Long.valueOf(DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_4G[countryGroupIndices[3]]));
            sparseArray.append(7, Long.valueOf(DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_ESTIMATES_WIFI[countryGroupIndices[0]]));
            return sparseArray;
        }

        private static int[] getCountryGroupIndices(String str) {
            int[] iArr = (int[]) DefaultBandwidthMeter.DEFAULT_INITIAL_BITRATE_COUNTRY_GROUPS.get(str);
            return iArr == null ? new int[]{2, 2, 2, 2} : iArr;
        }
    }

    @Nullable
    public TransferListener getTransferListener() {
        return this;
    }

    public void onTransferInitializing(DataSource dataSource, DataSpec dataSpec, boolean z) {
    }

    public DefaultBandwidthMeter() {
        this(1000000, 2000, Clock.DEFAULT);
    }

    @Deprecated
    public DefaultBandwidthMeter(Handler handler, EventListener eventListener) {
        this(1000000, 2000, Clock.DEFAULT);
        if (handler != null && eventListener != null) {
            addEventListener(handler, eventListener);
        }
    }

    @Deprecated
    public DefaultBandwidthMeter(Handler handler, EventListener eventListener, int i) {
        this(1000000, i, Clock.DEFAULT);
        if (handler != null && eventListener != null) {
            addEventListener(handler, eventListener);
        }
    }

    private DefaultBandwidthMeter(long j, int i, Clock clock2) {
        this.eventDispatcher = new EventDispatcher<>();
        this.slidingPercentile = new SlidingPercentile(i);
        this.clock = clock2;
        this.bitrateEstimate = j;
    }

    public synchronized long getBitrateEstimate() {
        return this.bitrateEstimate;
    }

    public void addEventListener(Handler handler, EventListener eventListener) {
        this.eventDispatcher.addListener(handler, eventListener);
    }

    public void removeEventListener(EventListener eventListener) {
        this.eventDispatcher.removeListener(eventListener);
    }

    public synchronized void onTransferStart(DataSource dataSource, DataSpec dataSpec, boolean z) {
        if (z) {
            if (this.streamCount == 0) {
                this.sampleStartTimeMs = this.clock.elapsedRealtime();
            }
            this.streamCount++;
        }
    }

    public synchronized void onBytesTransferred(DataSource dataSource, DataSpec dataSpec, boolean z, int i) {
        if (z) {
            this.sampleBytesTransferred += (long) i;
        }
    }

    public synchronized void onTransferEnd(DataSource dataSource, DataSpec dataSpec, boolean z) {
        if (z) {
            Assertions.checkState(this.streamCount > 0);
            long elapsedRealtime = this.clock.elapsedRealtime();
            int i = (int) (elapsedRealtime - this.sampleStartTimeMs);
            long j = (long) i;
            this.totalElapsedTimeMs += j;
            this.totalBytesTransferred += this.sampleBytesTransferred;
            if (i > 0) {
                this.slidingPercentile.addSample((int) Math.sqrt((double) this.sampleBytesTransferred), (float) ((this.sampleBytesTransferred * 8000) / j));
                if (this.totalElapsedTimeMs >= AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS || this.totalBytesTransferred >= 524288) {
                    this.bitrateEstimate = (long) this.slidingPercentile.getPercentile(0.5f);
                }
            }
            notifyBandwidthSample(i, this.sampleBytesTransferred, this.bitrateEstimate);
            int i2 = this.streamCount - 1;
            this.streamCount = i2;
            if (i2 > 0) {
                this.sampleStartTimeMs = elapsedRealtime;
            }
            this.sampleBytesTransferred = 0;
        }
    }

    private void notifyBandwidthSample(int i, long j, long j2) {
        EventDispatcher<EventListener> eventDispatcher2 = this.eventDispatcher;
        $$Lambda$DefaultBandwidthMeter$0dWpVoCfeEm8PONlagOKGMu96M r1 = new Event(i, j, j2) {
            private final /* synthetic */ int f$0;
            private final /* synthetic */ long f$1;
            private final /* synthetic */ long f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r4;
            }

            public final void sendTo(Object obj) {
                ((EventListener) obj).onBandwidthSample(this.f$0, this.f$1, this.f$2);
            }
        };
        eventDispatcher2.dispatch(r1);
    }

    private static Map<String, int[]> createInitialBitrateCountryGroupAssignment() {
        HashMap hashMap = new HashMap();
        hashMap.put("AD", new int[]{1, 0, 0, 0});
        hashMap.put("AE", new int[]{1, 3, 4, 4});
        hashMap.put("AF", new int[]{4, 4, 3, 2});
        hashMap.put("AG", new int[]{3, 2, 1, 2});
        hashMap.put("AI", new int[]{1, 0, 0, 2});
        hashMap.put("AL", new int[]{1, 1, 1, 1});
        hashMap.put("AM", new int[]{2, 2, 4, 3});
        hashMap.put("AO", new int[]{2, 4, 2, 0});
        hashMap.put("AR", new int[]{2, 3, 2, 3});
        hashMap.put("AS", new int[]{3, 4, 4, 1});
        hashMap.put("AT", new int[]{0, 1, 0, 0});
        hashMap.put("AU", new int[]{0, 3, 0, 0});
        hashMap.put("AW", new int[]{1, 1, 0, 4});
        hashMap.put("AX", new int[]{0, 1, 0, 0});
        hashMap.put("AZ", new int[]{3, 3, 2, 2});
        hashMap.put("BA", new int[]{1, 1, 1, 2});
        hashMap.put("BB", new int[]{0, 1, 0, 0});
        hashMap.put("BD", new int[]{2, 1, 3, 2});
        hashMap.put("BE", new int[]{0, 0, 0, 0});
        hashMap.put("BF", new int[]{4, 4, 4, 1});
        hashMap.put("BG", new int[]{0, 0, 0, 1});
        hashMap.put("BH", new int[]{2, 1, 3, 4});
        hashMap.put("BI", new int[]{4, 3, 4, 4});
        hashMap.put("BJ", new int[]{4, 3, 4, 3});
        hashMap.put("BL", new int[]{1, 0, 1, 2});
        hashMap.put("BM", new int[]{1, 0, 0, 0});
        hashMap.put("BN", new int[]{4, 3, 3, 3});
        hashMap.put("BO", new int[]{2, 2, 1, 2});
        hashMap.put("BQ", new int[]{1, 1, 2, 4});
        hashMap.put("BR", new int[]{2, 3, 2, 2});
        hashMap.put("BS", new int[]{1, 1, 0, 2});
        hashMap.put("BT", new int[]{3, 0, 2, 1});
        hashMap.put("BW", new int[]{4, 4, 2, 3});
        hashMap.put("BY", new int[]{1, 1, 1, 1});
        hashMap.put("BZ", new int[]{2, 3, 3, 1});
        hashMap.put(Country.CANADA_SHORT, new int[]{0, 2, 2, 3});
        hashMap.put("CD", new int[]{4, 4, 2, 1});
        hashMap.put("CF", new int[]{4, 4, 3, 3});
        hashMap.put("CG", new int[]{4, 4, 4, 4});
        hashMap.put("CH", new int[]{0, 0, 0, 0});
        hashMap.put("CI", new int[]{4, 4, 4, 4});
        hashMap.put("CK", new int[]{2, 4, 2, 0});
        hashMap.put("CL", new int[]{2, 2, 2, 3});
        hashMap.put("CM", new int[]{3, 4, 3, 1});
        hashMap.put(Country.TRADITIONAL_CHINESE_SHORT, new int[]{2, 0, 1, 2});
        hashMap.put("CO", new int[]{2, 3, 2, 1});
        hashMap.put("CR", new int[]{2, 2, 4, 4});
        hashMap.put("CU", new int[]{4, 4, 4, 1});
        hashMap.put("CV", new int[]{2, 2, 2, 4});
        hashMap.put("CW", new int[]{1, 1, 0, 0});
        hashMap.put("CX", new int[]{1, 2, 2, 2});
        hashMap.put("CY", new int[]{1, 1, 0, 0});
        hashMap.put("CZ", new int[]{0, 1, 0, 0});
        hashMap.put("DE", new int[]{0, 2, 2, 2});
        hashMap.put("DJ", new int[]{3, 4, 4, 0});
        hashMap.put("DK", new int[]{0, 0, 0, 0});
        hashMap.put("DM", new int[]{2, 0, 3, 4});
        hashMap.put("DO", new int[]{3, 3, 4, 4});
        hashMap.put("DZ", new int[]{3, 3, 4, 4});
        hashMap.put("EC", new int[]{2, 3, 3, 1});
        hashMap.put("EE", new int[]{0, 0, 0, 0});
        hashMap.put("EG", new int[]{3, 3, 1, 1});
        hashMap.put("EH", new int[]{2, 0, 2, 3});
        hashMap.put("ER", new int[]{4, 2, 2, 2});
        hashMap.put("ES", new int[]{0, 0, 1, 1});
        hashMap.put("ET", new int[]{4, 4, 4, 0});
        hashMap.put("FI", new int[]{0, 0, 1, 0});
        hashMap.put("FJ", new int[]{3, 2, 3, 3});
        hashMap.put("FK", new int[]{3, 4, 2, 1});
        hashMap.put("FM", new int[]{4, 2, 4, 0});
        hashMap.put("FO", new int[]{0, 0, 0, 1});
        hashMap.put("FR", new int[]{1, 0, 2, 1});
        hashMap.put("GA", new int[]{3, 3, 2, 1});
        hashMap.put("GB", new int[]{0, 1, 3, 2});
        hashMap.put("GD", new int[]{2, 0, 3, 0});
        hashMap.put("GE", new int[]{1, 1, 0, 3});
        hashMap.put("GF", new int[]{1, 2, 4, 4});
        hashMap.put("GG", new int[]{0, 1, 0, 0});
        hashMap.put("GH", new int[]{3, 2, 2, 2});
        hashMap.put("GI", new int[]{0, 0, 0, 1});
        hashMap.put("GL", new int[]{2, 4, 1, 4});
        hashMap.put("GM", new int[]{4, 3, 3, 0});
        hashMap.put("GN", new int[]{4, 4, 3, 4});
        hashMap.put("GP", new int[]{2, 2, 1, 3});
        hashMap.put("GQ", new int[]{4, 4, 3, 1});
        hashMap.put("GR", new int[]{1, 1, 0, 1});
        hashMap.put("GT", new int[]{3, 2, 3, 4});
        hashMap.put("GU", new int[]{1, 0, 4, 4});
        hashMap.put("GW", new int[]{4, 4, 4, 0});
        hashMap.put("GY", new int[]{3, 4, 1, 0});
        hashMap.put("HK", new int[]{0, 2, 3, 4});
        hashMap.put("HN", new int[]{3, 3, 2, 2});
        hashMap.put("HR", new int[]{1, 0, 0, 2});
        hashMap.put("HT", new int[]{3, 3, 3, 3});
        hashMap.put("HU", new int[]{0, 0, 1, 0});
        hashMap.put("ID", new int[]{2, 3, 3, 4});
        hashMap.put("IE", new int[]{0, 0, 1, 1});
        hashMap.put("IL", new int[]{0, 1, 1, 3});
        hashMap.put("IM", new int[]{0, 1, 0, 1});
        hashMap.put("IN", new int[]{2, 3, 3, 4});
        hashMap.put("IO", new int[]{4, 2, 2, 2});
        hashMap.put("IQ", new int[]{3, 3, 4, 3});
        hashMap.put("IR", new int[]{3, 2, 4, 4});
        hashMap.put("IS", new int[]{0, 0, 0, 0});
        hashMap.put("IT", new int[]{1, 0, 1, 3});
        hashMap.put("JE", new int[]{0, 0, 0, 1});
        hashMap.put("JM", new int[]{3, 3, 3, 2});
        hashMap.put("JO", new int[]{1, 1, 1, 2});
        hashMap.put(Country.JAPANESE_SHORT, new int[]{0, 1, 1, 2});
        hashMap.put("KE", new int[]{3, 3, 3, 3});
        hashMap.put(ExpandedProductParsedResult.KILOGRAM, new int[]{2, 2, 3, 3});
        hashMap.put("KH", new int[]{1, 0, 4, 4});
        hashMap.put("KI", new int[]{4, 4, 4, 4});
        hashMap.put("KM", new int[]{4, 4, 2, 2});
        hashMap.put("KN", new int[]{1, 0, 1, 3});
        hashMap.put("KP", new int[]{1, 2, 2, 2});
        hashMap.put(Country.KOREA_SOUTH_SHORT, new int[]{0, 4, 0, 2});
        hashMap.put("KW", new int[]{1, 2, 1, 2});
        hashMap.put("KY", new int[]{1, 1, 0, 2});
        hashMap.put("KZ", new int[]{1, 2, 2, 3});
        hashMap.put("LA", new int[]{3, 2, 2, 2});
        hashMap.put(ExpandedProductParsedResult.POUND, new int[]{3, 2, 0, 0});
        hashMap.put("LC", new int[]{2, 2, 1, 0});
        hashMap.put("LI", new int[]{0, 0, 1, 2});
        hashMap.put("LK", new int[]{1, 1, 2, 2});
        hashMap.put("LR", new int[]{3, 4, 3, 1});
        hashMap.put("LS", new int[]{3, 3, 2, 0});
        hashMap.put("LT", new int[]{0, 0, 0, 1});
        hashMap.put("LU", new int[]{0, 0, 1, 0});
        hashMap.put("LV", new int[]{0, 0, 0, 0});
        hashMap.put("LY", new int[]{4, 4, 4, 4});
        hashMap.put("MA", new int[]{2, 1, 2, 2});
        hashMap.put("MC", new int[]{1, 0, 1, 0});
        hashMap.put("MD", new int[]{1, 1, 0, 0});
        hashMap.put("ME", new int[]{1, 2, 2, 3});
        hashMap.put("MF", new int[]{1, 4, 3, 3});
        hashMap.put("MG", new int[]{3, 4, 1, 2});
        hashMap.put("MH", new int[]{4, 0, 2, 3});
        hashMap.put("MK", new int[]{1, 0, 0, 1});
        hashMap.put("ML", new int[]{4, 4, 4, 4});
        hashMap.put("MM", new int[]{2, 3, 1, 2});
        hashMap.put("MN", new int[]{2, 2, 2, 4});
        hashMap.put("MO", new int[]{0, 1, 4, 4});
        hashMap.put("MP", new int[]{0, 0, 4, 4});
        hashMap.put("MQ", new int[]{1, 1, 1, 3});
        hashMap.put("MR", new int[]{4, 2, 4, 2});
        hashMap.put("MS", new int[]{1, 2, 1, 2});
        hashMap.put("MT", new int[]{0, 0, 0, 0});
        hashMap.put("MU", new int[]{2, 2, 4, 4});
        hashMap.put("MV", new int[]{4, 2, 0, 1});
        hashMap.put("MW", new int[]{3, 2, 1, 1});
        hashMap.put("MX", new int[]{2, 4, 3, 1});
        hashMap.put("MY", new int[]{2, 3, 3, 3});
        hashMap.put("MZ", new int[]{3, 3, 2, 4});
        hashMap.put("NA", new int[]{4, 2, 1, 1});
        hashMap.put("NC", new int[]{2, 1, 3, 3});
        hashMap.put("NE", new int[]{4, 4, 4, 4});
        hashMap.put("NF", new int[]{0, 2, 2, 2});
        hashMap.put("NG", new int[]{3, 4, 2, 2});
        hashMap.put("NI", new int[]{3, 4, 3, 3});
        hashMap.put("NL", new int[]{0, 1, 3, 2});
        hashMap.put("NO", new int[]{0, 0, 1, 0});
        hashMap.put("NP", new int[]{2, 3, 2, 2});
        hashMap.put("NR", new int[]{4, 3, 4, 1});
        hashMap.put("NU", new int[]{4, 2, 2, 2});
        hashMap.put("NZ", new int[]{0, 0, 0, 1});
        hashMap.put("OM", new int[]{2, 2, 1, 3});
        hashMap.put("PA", new int[]{1, 3, 2, 3});
        hashMap.put("PE", new int[]{2, 2, 4, 4});
        hashMap.put("PF", new int[]{2, 2, 0, 1});
        hashMap.put("PG", new int[]{4, 4, 4, 4});
        hashMap.put("PH", new int[]{3, 0, 4, 4});
        hashMap.put("PK", new int[]{3, 3, 3, 3});
        hashMap.put("PL", new int[]{1, 0, 1, 3});
        hashMap.put("PM", new int[]{0, 2, 2, 3});
        hashMap.put("PR", new int[]{2, 3, 4, 3});
        hashMap.put("PS", new int[]{2, 3, 0, 4});
        hashMap.put("PT", new int[]{1, 1, 1, 1});
        hashMap.put("PW", new int[]{3, 2, 3, 0});
        hashMap.put("PY", new int[]{2, 1, 3, 3});
        hashMap.put("QA", new int[]{2, 3, 1, 2});
        hashMap.put("RE", new int[]{1, 1, 2, 2});
        hashMap.put("RO", new int[]{0, 1, 1, 3});
        hashMap.put("RS", new int[]{1, 1, 0, 0});
        hashMap.put("RU", new int[]{0, 1, 1, 1});
        hashMap.put("RW", new int[]{3, 4, 3, 1});
        hashMap.put("SA", new int[]{3, 2, 2, 3});
        hashMap.put("SB", new int[]{4, 4, 3, 0});
        hashMap.put("SC", new int[]{4, 2, 0, 1});
        hashMap.put("SD", new int[]{3, 4, 4, 4});
        hashMap.put("SE", new int[]{0, 0, 0, 0});
        hashMap.put("SG", new int[]{1, 2, 3, 3});
        hashMap.put("SH", new int[]{4, 2, 2, 2});
        hashMap.put("SI", new int[]{0, 1, 0, 0});
        hashMap.put("SJ", new int[]{3, 2, 0, 2});
        hashMap.put("SK", new int[]{0, 1, 0, 1});
        hashMap.put("SL", new int[]{4, 3, 2, 4});
        hashMap.put("SM", new int[]{1, 0, 1, 1});
        hashMap.put("SN", new int[]{4, 4, 4, 2});
        hashMap.put("SO", new int[]{4, 4, 4, 3});
        hashMap.put("SR", new int[]{3, 2, 2, 3});
        hashMap.put("SS", new int[]{4, 3, 4, 2});
        hashMap.put("ST", new int[]{3, 2, 2, 2});
        hashMap.put("SV", new int[]{2, 3, 2, 3});
        hashMap.put("SX", new int[]{2, 4, 2, 0});
        hashMap.put("SY", new int[]{4, 4, 2, 0});
        hashMap.put("SZ", new int[]{3, 4, 1, 1});
        hashMap.put("TC", new int[]{2, 1, 2, 1});
        hashMap.put("TD", new int[]{4, 4, 4, 3});
        hashMap.put("TG", new int[]{3, 2, 2, 0});
        hashMap.put("TH", new int[]{1, 3, 4, 4});
        hashMap.put("TJ", new int[]{4, 4, 4, 4});
        hashMap.put("TL", new int[]{4, 2, 4, 4});
        hashMap.put("TM", new int[]{4, 1, 3, 3});
        hashMap.put("TN", new int[]{2, 2, 1, 2});
        hashMap.put("TO", new int[]{2, 3, 3, 1});
        hashMap.put("TR", new int[]{1, 2, 0, 2});
        hashMap.put("TT", new int[]{2, 1, 1, 0});
        hashMap.put("TV", new int[]{4, 2, 2, 4});
        hashMap.put(Country.SIMPLIFIED_CHINESE_SHORT, new int[]{0, 0, 0, 1});
        hashMap.put("TZ", new int[]{3, 3, 3, 2});
        hashMap.put("UA", new int[]{0, 2, 1, 3});
        hashMap.put("UG", new int[]{4, 3, 2, 2});
        hashMap.put(Country.UNITED_STATES_SHORT, new int[]{0, 1, 3, 3});
        hashMap.put("UY", new int[]{2, 1, 2, 2});
        hashMap.put("UZ", new int[]{4, 3, 2, 4});
        hashMap.put("VA", new int[]{1, 2, 2, 2});
        hashMap.put("VC", new int[]{2, 0, 3, 2});
        hashMap.put("VE", new int[]{3, 4, 4, 3});
        hashMap.put("VG", new int[]{3, 1, 3, 4});
        hashMap.put("VI", new int[]{1, 0, 2, 4});
        hashMap.put("VN", new int[]{0, 2, 4, 4});
        hashMap.put("VU", new int[]{4, 1, 3, 2});
        hashMap.put("WS", new int[]{3, 2, 3, 0});
        hashMap.put("XK", new int[]{1, 2, 1, 0});
        hashMap.put("YE", new int[]{4, 4, 4, 2});
        hashMap.put("YT", new int[]{3, 1, 1, 2});
        hashMap.put("ZA", new int[]{2, 3, 1, 2});
        hashMap.put("ZM", new int[]{3, 3, 3, 1});
        hashMap.put("ZW", new int[]{3, 3, 2, 1});
        return Collections.unmodifiableMap(hashMap);
    }
}
