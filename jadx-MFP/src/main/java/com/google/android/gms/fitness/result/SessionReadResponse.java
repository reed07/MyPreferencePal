package com.google.android.gms.fitness.result;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import java.util.List;

public class SessionReadResponse extends Response<SessionReadResult> {
    public SessionReadResponse() {
    }

    protected SessionReadResponse(SessionReadResult sessionReadResult) {
        super(sessionReadResult);
    }

    public List<Session> getSessions() {
        return ((SessionReadResult) getResult()).getSessions();
    }

    public List<DataSet> getDataSet(Session session, DataType dataType) {
        return ((SessionReadResult) getResult()).getDataSet(session, dataType);
    }

    public List<DataSet> getDataSet(Session session) {
        return ((SessionReadResult) getResult()).getDataSet(session);
    }

    public Status getStatus() {
        return ((SessionReadResult) getResult()).getStatus();
    }
}
