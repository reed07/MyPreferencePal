package io.grpc;

@ExperimentalApi
public enum ConnectivityState {
    CONNECTING,
    READY,
    TRANSIENT_FAILURE,
    IDLE,
    SHUTDOWN
}
