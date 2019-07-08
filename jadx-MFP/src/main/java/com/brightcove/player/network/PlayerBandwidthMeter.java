package com.brightcove.player.network;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.BandwidthMeter.EventListener;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter.Builder;
import com.google.android.exoplayer2.upstream.TransferListener;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class PlayerBandwidthMeter implements BandwidthMeter, TransferListener {
    public long NO_ESTIMATE;
    private final AtomicReference<BandwidthMeter> delegate;
    private final AtomicLong totalBytesTransferred;

    @Nullable
    public TransferListener getTransferListener() {
        return this;
    }

    public PlayerBandwidthMeter() {
        this(null, null);
    }

    public PlayerBandwidthMeter(@Nullable Handler handler, @Nullable EventListener eventListener) {
        this(null, handler, eventListener);
    }

    public PlayerBandwidthMeter(@Nullable Context context, @Nullable Handler handler, @Nullable EventListener eventListener) {
        this(new Builder(context).setEventListener(handler, eventListener).build());
    }

    public PlayerBandwidthMeter(@Nullable BandwidthMeter bandwidthMeter) {
        this.NO_ESTIMATE = -1;
        this.delegate = new AtomicReference<>();
        this.totalBytesTransferred = new AtomicLong();
        this.delegate.set(bandwidthMeter);
    }

    public long getBitrateEstimate() {
        BandwidthMeter bandwidthMeter = (BandwidthMeter) this.delegate.get();
        return bandwidthMeter == null ? this.NO_ESTIMATE : bandwidthMeter.getBitrateEstimate();
    }

    public void addEventListener(Handler handler, EventListener eventListener) {
        if (((BandwidthMeter) this.delegate.get()) != null) {
            ((BandwidthMeter) this.delegate.get()).addEventListener(handler, eventListener);
        }
    }

    public void removeEventListener(EventListener eventListener) {
        BandwidthMeter bandwidthMeter = (BandwidthMeter) this.delegate.get();
        if (bandwidthMeter != null) {
            bandwidthMeter.removeEventListener(eventListener);
        }
    }

    @Nullable
    public BandwidthMeter getDelegate() {
        return (BandwidthMeter) this.delegate.get();
    }

    public void setDelegate(@Nullable BandwidthMeter bandwidthMeter) {
        this.delegate.set(bandwidthMeter);
    }

    public void onTransferInitializing(DataSource dataSource, DataSpec dataSpec, boolean z) {
        BandwidthMeter bandwidthMeter = (BandwidthMeter) this.delegate.get();
        if (bandwidthMeter instanceof TransferListener) {
            ((TransferListener) bandwidthMeter).onTransferInitializing(dataSource, dataSpec, z);
        }
    }

    public void onTransferStart(DataSource dataSource, DataSpec dataSpec, boolean z) {
        BandwidthMeter bandwidthMeter = (BandwidthMeter) this.delegate.get();
        if (bandwidthMeter instanceof TransferListener) {
            ((TransferListener) bandwidthMeter).onTransferStart(dataSource, dataSpec, z);
        }
    }

    public void onBytesTransferred(DataSource dataSource, DataSpec dataSpec, boolean z, int i) {
        this.totalBytesTransferred.addAndGet((long) i);
        BandwidthMeter bandwidthMeter = (BandwidthMeter) this.delegate.get();
        if (bandwidthMeter instanceof TransferListener) {
            ((TransferListener) bandwidthMeter).onBytesTransferred(dataSource, dataSpec, z, i);
        }
    }

    public void onTransferEnd(DataSource dataSource, DataSpec dataSpec, boolean z) {
        BandwidthMeter bandwidthMeter = (BandwidthMeter) this.delegate.get();
        if (bandwidthMeter instanceof TransferListener) {
            ((TransferListener) bandwidthMeter).onTransferEnd(dataSource, dataSpec, z);
        }
    }

    public long getTotalBytesTransferred() {
        return this.totalBytesTransferred.get();
    }

    public PlayerBandwidthMeter resetTotalBytesTransferred() {
        this.totalBytesTransferred.set(0);
        return this;
    }
}
