package io.uacf.clientevents.internal;

import android.content.Context;
import android.util.LongSparseArray;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import io.uacf.clientevents.internal.database.ClientEventsTable;
import io.uacf.clientevents.sdk.UacfClientEvent;
import io.uacf.clientevents.sdk.UacfClientEvent.Builder;
import io.uacf.clientevents.sdk.UacfClientEventAttributes;
import io.uacf.core.api.UacfApiEnvironmentProvider;
import io.uacf.core.api.UacfApiException;
import io.uacf.core.api.UacfUserAgentProvider;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.auth.UacfAuthProvider;
import io.uacf.net.retrofit.RetrofitBasedServiceImpl;
import io.uacf.net.retrofit.UacfRetrofitHelper;
import io.uacf.net.retrofit.tracers.UacfOkHttpNetworkTracer;
import java.util.Date;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class ClientEventsServiceImpl extends RetrofitBasedServiceImpl implements ClientEventsService {
    private final UacfAppId appId;
    private final ClientEventsTable clientEventsTable;
    private final String deviceId;

    private interface AnalyticsConsumer {
        @POST("client-events")
        Call<ClientEventsApiError> postEvent(@Body UacfClientEvent uacfClientEvent);
    }

    public ClientEventsServiceImpl(Context context, String str, UacfUserAgentProvider uacfUserAgentProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfAuthProvider uacfAuthProvider, UacfAppId uacfAppId, ClientEventsTable clientEventsTable2, OkHttpClient okHttpClient, UacfOkHttpNetworkTracer uacfOkHttpNetworkTracer) {
        super(context, uacfUserAgentProvider, uacfApiEnvironmentProvider, uacfAuthProvider, okHttpClient, uacfOkHttpNetworkTracer);
        this.deviceId = str;
        this.appId = uacfAppId;
        this.clientEventsTable = clientEventsTable2;
    }

    /* access modifiers changed from: protected */
    public Class<?> getConsumerClass() {
        return AnalyticsConsumer.class;
    }

    public void reportEvent(String str, Object obj, Date date) {
        reportEvent(createClientEventAttributes(str, obj, date));
    }

    public void reportEvent(UacfClientEventAttributes uacfClientEventAttributes) {
        getClientEventsTable().insert(buildEvent(uacfClientEventAttributes));
    }

    public boolean sendEventsToBackend() throws UacfApiException {
        if (getCurrentApiEnvironment() == null) {
            return false;
        }
        LongSparseArray next = getClientEventsTable().next(500);
        int size = next.size();
        if (size == 0) {
            return false;
        }
        AnalyticsConsumer analyticsConsumer = (AnalyticsConsumer) getConsumerWithUnderscoresAndBasicAuthUsingCurrentEnvironment();
        try {
            Builder builder = new Builder((UacfClientEvent) next.valueAt(0));
            for (int i = 1; i < size; i++) {
                builder.withEvents(((UacfClientEvent) next.valueAt(i)).getEvents());
            }
            handleError((ClientEventsApiError) UacfRetrofitHelper.execute(analyticsConsumer.postEvent(builder.build())));
        } catch (UacfApiException e) {
            if (e.getStatusCode() != 400) {
                throw e;
            }
        }
        getClientEventsTable().remove(next);
        return true;
    }

    private void handleError(ClientEventsApiError clientEventsApiError) throws UacfApiException {
        if (clientEventsApiError != null && Strings.notEmpty(clientEventsApiError.getError())) {
            Ln.d("ANALYTICS: sendEventsToBackend, made call, error is %s (%s)", clientEventsApiError.getError(), clientEventsApiError.getErrorDescription());
            throw new UacfApiException(clientEventsApiError.getError(), 0, clientEventsApiError.getErrorDescription());
        }
    }

    private UacfClientEventAttributes createClientEventAttributes(String str, Object obj, Date date) {
        return new UacfClientEventAttributes.Builder().withEventName(str).withAttributes(obj).withTimestamp(date).build();
    }

    private ClientEventsTable getClientEventsTable() {
        return this.clientEventsTable;
    }

    private UacfClientEvent buildEvent(UacfClientEventAttributes uacfClientEventAttributes) {
        Builder withLocale = new Builder().withClientId(getCurrentApiEnvironment().getClientId()).withEvent(uacfClientEventAttributes).withUserAgent(this.userAgentProvider.get()).withDeviceId(this.deviceId).withApplicationName(this.appId).withUacfId(this.authProvider.getUacfUserId()).withUserId(this.authProvider.getDomainUserId()).withLocale(this.authProvider.getUserLocale());
        if (this.authProvider.getDomain() != null) {
            withLocale.withDomain(this.authProvider.getDomain().toString());
        }
        return withLocale.build();
    }
}
