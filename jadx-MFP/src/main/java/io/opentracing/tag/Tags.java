package io.opentracing.tag;

public final class Tags {
    public static final StringTag COMPONENT = new StringTag("component");
    public static final StringTag DB_INSTANCE = new StringTag("db.instance");
    public static final StringTag DB_STATEMENT = new StringTag("db.statement");
    public static final StringTag DB_TYPE = new StringTag("db.type");
    public static final StringTag DB_USER = new StringTag("db.user");
    public static final BooleanTag ERROR = new BooleanTag("error");
    public static final StringTag HTTP_METHOD = new StringTag("http.method");
    public static final IntTag HTTP_STATUS = new IntTag("http.status_code");
    public static final StringTag HTTP_URL = new StringTag("http.url");
    public static final StringTag MESSAGE_BUS_DESTINATION = new StringTag("message_bus.destination");
    public static final StringTag PEER_HOSTNAME = new StringTag("peer.hostname");
    public static final IntOrStringTag PEER_HOST_IPV4 = new IntOrStringTag("peer.ipv4");
    public static final StringTag PEER_HOST_IPV6 = new StringTag("peer.ipv6");
    public static final IntTag PEER_PORT = new IntTag("peer.port");
    public static final StringTag PEER_SERVICE = new StringTag("peer.service");
    public static final IntTag SAMPLING_PRIORITY = new IntTag("sampling.priority");
    public static final StringTag SPAN_KIND = new StringTag("span.kind");

    private Tags() {
    }
}
