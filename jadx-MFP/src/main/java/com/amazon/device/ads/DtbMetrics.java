package com.amazon.device.ads;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DTBMetrics */
class DtbMetrics implements Cloneable {
    private final String LOG_TAG = DtbMetrics.class.getSimpleName();
    private volatile Map<DtbMetric, Long> collectedMetrics = new EnumMap(DtbMetric.class);
    private String instPxlUrl = null;
    private volatile Map<DtbMetric, Long> tempTimer = new EnumMap(DtbMetric.class);

    /* compiled from: DTBMetrics */
    static class Submitter {
        public static final Submitter INSTANCE = new Submitter();
        private final String LOG_TAG = Submitter.class.getSimpleName();
        private final Queue<DtbMetrics> metricsQueue = new ConcurrentLinkedQueue();

        private Submitter() {
        }

        public void submitMetrics(DtbMetrics dtbMetrics) {
            if (dtbMetrics.getMetricsCount() > 0) {
                this.metricsQueue.add(dtbMetrics.clone());
                dtbMetrics.reset();
                DtbLog.debug("Scheduling metrics submission in background thread.");
                DtbThreadService.getInstance().schedule(new Runnable() {
                    public final void run() {
                        Submitter.lambda$submitMetrics$0(Submitter.this);
                    }
                });
                DtbLog.debug("Dispatched the metrics submit task on a background schedule thread.");
            }
        }

        public static /* synthetic */ void lambda$submitMetrics$0(Submitter submitter) {
            DtbLog.debug("Starting metrics submission..");
            submitter.submitMetrics();
            DtbLog.debug("Metrics submission thread complete.");
        }

        private void submitMetrics() {
            Iterator it = this.metricsQueue.iterator();
            int i = 0;
            while (it.hasNext()) {
                DtbMetrics dtbMetrics = (DtbMetrics) it.next();
                i++;
                StringBuilder sb = new StringBuilder();
                sb.append("Starting metrics submission - Sequence ");
                sb.append(i);
                DtbLog.debug(sb.toString());
                if (dtbMetrics.getInstPxlUrl() == null) {
                    it.remove();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("No metric url found- Sequence ");
                    sb2.append(i);
                    sb2.append(", skipping..");
                    DtbLog.debug(sb2.toString());
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(dtbMetrics.getInstPxlUrl());
                    sb3.append(dtbMetrics.toURLEncodedString());
                    String sb4 = sb3.toString();
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("Metrics URL:");
                    sb5.append(sb4);
                    DtbLog.debug(sb5.toString());
                    try {
                        DtbHttpClient dtbHttpClient = new DtbHttpClient(sb4);
                        dtbHttpClient.setUseSecure(DtbDebugProperties.getIsSecure(true));
                        dtbHttpClient.executeGET();
                        if (dtbHttpClient.isHttpStatusCodeOK()) {
                            StringBuilder sb6 = new StringBuilder();
                            sb6.append("Metrics submitted- Sequence ");
                            sb6.append(i);
                            DtbLog.debug(sb6.toString());
                            it.remove();
                        } else {
                            StringBuilder sb7 = new StringBuilder();
                            sb7.append("Metrics submission failed- Sequence ");
                            sb7.append(i);
                            sb7.append(", response invalid");
                            DtbLog.debug(sb7.toString());
                            return;
                        }
                    } catch (Exception e) {
                        StringBuilder sb8 = new StringBuilder();
                        sb8.append("Metrics submission failed- Sequence ");
                        sb8.append(i);
                        sb8.append(", encountered error:");
                        sb8.append(e.toString());
                        DtbLog.debug(sb8.toString());
                        return;
                    }
                }
            }
        }
    }

    DtbMetrics() {
    }

    public void incrementMetric(DtbMetric dtbMetric) {
        if (dtbMetric == null || dtbMetric.getMetricType() != DtbMetricType.COUNTER) {
            throw new IllegalArgumentException("Either metric is null or metric type is not counter.");
        }
        if (this.collectedMetrics.get(dtbMetric) == null) {
            this.collectedMetrics.put(dtbMetric, Long.valueOf(0));
        }
        this.collectedMetrics.put(dtbMetric, Long.valueOf(((Long) this.collectedMetrics.get(dtbMetric)).longValue() + 1));
    }

    public void startTimer(DtbMetric dtbMetric) {
        if (dtbMetric == null || dtbMetric.getMetricType() != DtbMetricType.TIMER) {
            throw new IllegalArgumentException("Either metric is null or metric type is not timer.");
        } else if (this.collectedMetrics.get(dtbMetric) == null) {
            this.tempTimer.put(dtbMetric, Long.valueOf(System.currentTimeMillis()));
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(dtbMetric);
            sb.append(" is already set, your operation is trying to override a value.");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public void resetMetric(DtbMetric dtbMetric) {
        this.collectedMetrics.remove(dtbMetric);
    }

    public void stopTimer(DtbMetric dtbMetric) {
        if (dtbMetric == null || dtbMetric.getMetricType() == DtbMetricType.COUNTER) {
            throw new IllegalArgumentException("Either metric is null or metric type is not timer.");
        } else if (this.tempTimer.get(dtbMetric) == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("You are trying to stop a metric, which is not yet started: ");
            sb.append(dtbMetric);
            throw new IllegalArgumentException(sb.toString());
        } else if (this.collectedMetrics.get(dtbMetric) == null) {
            this.collectedMetrics.put(dtbMetric, Long.valueOf(System.currentTimeMillis() - ((Long) this.tempTimer.get(dtbMetric)).longValue()));
            this.tempTimer.remove(dtbMetric);
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(dtbMetric);
            sb2.append(" is already set, your operation is trying to override a value.");
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("c", "dtbm");
            for (Entry entry : this.collectedMetrics.entrySet()) {
                DtbMetric dtbMetric = (DtbMetric) entry.getKey();
                jSONObject.put(dtbMetric.getAAXName(), (Long) entry.getValue());
            }
        } catch (JSONException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error while adding values to JSON object: ");
            sb.append(e.getLocalizedMessage());
            DtbLog.debug(sb.toString());
        }
        return jSONObject.toString();
    }

    public String toURLEncodedString() {
        return DtbCommonUtils.getURLEncodedString(toString());
    }

    public int getMetricsCount() {
        return this.collectedMetrics.size();
    }

    public String getInstPxlUrl() {
        return this.instPxlUrl;
    }

    public void setInstPxlUrl(String str) {
        if (str != null) {
            int indexOf = str.indexOf("://");
            if (indexOf != -1) {
                str = str.substring(indexOf + 3);
            }
        }
        this.instPxlUrl = str;
    }

    /* access modifiers changed from: protected */
    public DtbMetrics clone() {
        DtbMetrics dtbMetrics = new DtbMetrics();
        dtbMetrics.collectedMetrics.putAll(this.collectedMetrics);
        dtbMetrics.tempTimer.putAll(this.tempTimer);
        dtbMetrics.instPxlUrl = this.instPxlUrl;
        return dtbMetrics;
    }

    public void reset() {
        this.collectedMetrics.clear();
        this.tempTimer.clear();
    }
}
