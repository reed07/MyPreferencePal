package com.google.android.gms.internal.measurement;

import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.appindexing.Indexable;

@ShowFirstParty
@VisibleForTesting
public final class zzcf {
    public static zzcg<Integer> zzaaa = zzcg.zza("analytics.http_connection.read_timeout_millis", 61000, 61000);
    public static zzcg<Long> zzaab = zzcg.zza("analytics.campaigns.time_limit", 86400000, 86400000);
    private static zzcg<String> zzaac;
    private static zzcg<Integer> zzaad = zzcg.zza("analytics.first_party_experiment_variant", 0, 0);
    public static zzcg<Boolean> zzaae = zzcg.zza("analytics.test.disable_receiver", false, false);
    public static zzcg<Long> zzaaf = zzcg.zza("analytics.service_client.idle_disconnect_millis", 10000, 10000);
    public static zzcg<Long> zzaag = zzcg.zza("analytics.service_client.connect_timeout_millis", (long) DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, (long) DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    private static zzcg<Long> zzaah = zzcg.zza("analytics.service_client.second_connect_delay_millis", (long) DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, (long) DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    private static zzcg<Long> zzaai = zzcg.zza("analytics.service_client.unexpected_reconnect_millis", (long) DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS, (long) DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS);
    public static zzcg<Long> zzaaj = zzcg.zza("analytics.service_client.reconnect_throttle_millis", 1800000, 1800000);
    public static zzcg<Long> zzaak = zzcg.zza("analytics.monitoring.sample_period_millis", 86400000, 86400000);
    public static zzcg<Long> zzaal = zzcg.zza("analytics.initialization_warning_threshold", (long) DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, (long) DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    public static zzcg<Boolean> zzaam = zzcg.zza("analytics.gcm_task_service", false, false);
    private static zzcg<Boolean> zzyv = zzcg.zza("analytics.service_enabled", false, false);
    public static zzcg<Boolean> zzyw = zzcg.zza("analytics.service_client_enabled", true, true);
    public static zzcg<String> zzyx = zzcg.zza("analytics.log_tag", "GAv4", "GAv4-SVC");
    private static zzcg<Long> zzyy = zzcg.zza("analytics.max_tokens", 60, 60);
    private static zzcg<Float> zzyz = zzcg.zza("analytics.tokens_per_sec", 0.5f, 0.5f);
    public static zzcg<Integer> zzza = zzcg.zza("analytics.max_stored_hits", 2000, (int) Indexable.MAX_STRING_LENGTH);
    private static zzcg<Integer> zzzb = zzcg.zza("analytics.max_stored_hits_per_app", 2000, 2000);
    public static zzcg<Integer> zzzc = zzcg.zza("analytics.max_stored_properties_per_app", 100, 100);
    public static zzcg<Long> zzzd = zzcg.zza("analytics.local_dispatch_millis", 1800000, 120000);
    public static zzcg<Long> zzze = zzcg.zza("analytics.initial_local_dispatch_millis", (long) DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, (long) DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    private static zzcg<Long> zzzf = zzcg.zza("analytics.min_local_dispatch_millis", 120000, 120000);
    private static zzcg<Long> zzzg = zzcg.zza("analytics.max_local_dispatch_millis", 7200000, 7200000);
    public static zzcg<Long> zzzh = zzcg.zza("analytics.dispatch_alarm_millis", 7200000, 7200000);
    public static zzcg<Long> zzzi = zzcg.zza("analytics.max_dispatch_alarm_millis", 32400000, 32400000);
    public static zzcg<Integer> zzzj = zzcg.zza("analytics.max_hits_per_dispatch", 20, 20);
    public static zzcg<Integer> zzzk = zzcg.zza("analytics.max_hits_per_batch", 20, 20);
    public static zzcg<String> zzzl;
    public static zzcg<String> zzzm;
    public static zzcg<String> zzzn;
    public static zzcg<String> zzzo;
    public static zzcg<Integer> zzzp = zzcg.zza("analytics.max_get_length", 2036, 2036);
    public static zzcg<String> zzzq = zzcg.zza("analytics.batching_strategy.k", zzbn.BATCH_BY_COUNT.name(), zzbn.BATCH_BY_COUNT.name());
    public static zzcg<String> zzzr;
    private static zzcg<Integer> zzzs = zzcg.zza("analytics.max_hits_per_request.k", 20, 20);
    public static zzcg<Integer> zzzt = zzcg.zza("analytics.max_hit_length.k", 8192, 8192);
    public static zzcg<Integer> zzzu = zzcg.zza("analytics.max_post_length.k", 8192, 8192);
    public static zzcg<Integer> zzzv = zzcg.zza("analytics.max_batch_post_length", 8192, 8192);
    public static zzcg<String> zzzw;
    public static zzcg<Integer> zzzx = zzcg.zza("analytics.batch_retry_interval.seconds.k", 3600, 3600);
    private static zzcg<Long> zzzy = zzcg.zza("analytics.service_monitor_interval", 86400000, 86400000);
    public static zzcg<Integer> zzzz = zzcg.zza("analytics.http_connection.connect_timeout_millis", 60000, 60000);

    static {
        String str = "http://www.google-analytics.com";
        zzzl = zzcg.zza("analytics.insecure_host", str, str);
        String str2 = "https://ssl.google-analytics.com";
        zzzm = zzcg.zza("analytics.secure_host", str2, str2);
        String str3 = "/collect";
        zzzn = zzcg.zza("analytics.simple_endpoint", str3, str3);
        String str4 = "/batch";
        zzzo = zzcg.zza("analytics.batching_endpoint", str4, str4);
        String name = zzbt.GZIP.name();
        zzzr = zzcg.zza("analytics.compression_strategy.k", name, name);
        String str5 = "404,502";
        zzzw = zzcg.zza("analytics.fallback_responses.k", str5, str5);
        String str6 = "";
        zzaac = zzcg.zza("analytics.first_party_experiment_id", str6, str6);
    }
}
