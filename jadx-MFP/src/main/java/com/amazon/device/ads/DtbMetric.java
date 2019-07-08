package com.amazon.device.ads;

enum DtbMetric {
    AAX_BID_TIME("dabtw", "dabt3", DtbMetricType.TIMER),
    AAX_PUNTED("dapw", "dap3", DtbMetricType.COUNTER),
    AAX_NETWORK_FAILURE("danfw", "danf3", DtbMetricType.COUNTER),
    BANNER_ADSERVER_TIME("dbastw", "dbast3", DtbMetricType.TIMER),
    INTERSTITIAL_ADSERVER_TIME("diastw", "diast3", DtbMetricType.TIMER),
    ADSERVER_ADLOAD_FAILURE("dasfw", "dasf3", DtbMetricType.COUNTER),
    BANNER_TOTAL_LOAD_TIME("dbtltw", "dbtlt3", DtbMetricType.TIMER),
    INTERSTITIAL_TOTAL_LOAD_TIME("ditltw", "ditlt3", DtbMetricType.TIMER),
    CONFIG_DOWNLOAD_LATENCY("acl", "acl", DtbMetricType.TIMER),
    SIS_LATENCY_UPDATE_DEVICE_INFO("sul", "sul", DtbMetricType.TIMER),
    SIS_COUNTER_IDENTIFIED_DEVICE_CHANGED("sid", "sid", DtbMetricType.COUNTER),
    SIS_LATENCY_REGISTER_EVENT("srel", "srel", DtbMetricType.TIMER);
    
    private final String aaxName3g;
    private final String aaxNameWifi;
    private final DtbMetricType metricType;

    enum DtbMetricType {
        private static final /* synthetic */ DtbMetricType[] $VALUES = null;
        public static final DtbMetricType COUNTER = null;
        public static final DtbMetricType TIMER = null;

        private DtbMetricType(String str, int i) {
        }

        public static DtbMetricType valueOf(String str) {
            return (DtbMetricType) Enum.valueOf(DtbMetricType.class, str);
        }

        public static DtbMetricType[] values() {
            return (DtbMetricType[]) $VALUES.clone();
        }

        static {
            COUNTER = new DtbMetricType("COUNTER", 0);
            TIMER = new DtbMetricType("TIMER", 1);
            $VALUES = new DtbMetricType[]{COUNTER, TIMER};
        }
    }

    private DtbMetric(String str, String str2, DtbMetricType dtbMetricType) {
        this.aaxNameWifi = str;
        this.aaxName3g = str2;
        this.metricType = dtbMetricType;
    }

    public String getAAXName() {
        return DtbNetworkState.INSTANCE.isWifiConnection() ? this.aaxNameWifi : this.aaxName3g;
    }

    public DtbMetricType getMetricType() {
        return this.metricType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(getAAXName());
        sb.append(", ");
        sb.append(getMetricType());
        sb.append("]");
        return sb.toString();
    }
}
