package com.mopub.common;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.common.util.Reflection;
import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class AdvancedBiddingTokens implements AdvancedBiddersInitializedListener {
    @NonNull
    private List<MoPubAdvancedBidder> mAdvancedBidders = new ArrayList();
    @Nullable
    private final SdkInitializationListener mSdkInitializationListener;

    private static class AdvancedBiddersInitializationAsyncTask extends AsyncTask<Void, Void, List<MoPubAdvancedBidder>> {
        @NonNull
        private final List<Class<? extends MoPubAdvancedBidder>> advancedBidderClasses;
        @NonNull
        private final AdvancedBiddersInitializedListener mAdvancedBiddersInitializedListener;

        AdvancedBiddersInitializationAsyncTask(@NonNull List<Class<? extends MoPubAdvancedBidder>> list, @NonNull AdvancedBiddersInitializedListener advancedBiddersInitializedListener) {
            Preconditions.checkNotNull(list);
            Preconditions.checkNotNull(advancedBiddersInitializedListener);
            this.advancedBidderClasses = list;
            this.mAdvancedBiddersInitializedListener = advancedBiddersInitializedListener;
        }

        /* access modifiers changed from: protected */
        public List<MoPubAdvancedBidder> doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            for (Class cls : this.advancedBidderClasses) {
                try {
                    arrayList.add((MoPubAdvancedBidder) Reflection.instantiateClassWithEmptyConstructor(cls.getName(), MoPubAdvancedBidder.class));
                } catch (Exception unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unable to find class ");
                    sb.append(cls.getName());
                    MoPubLog.e(sb.toString());
                }
            }
            return arrayList;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(List<MoPubAdvancedBidder> list) {
            this.mAdvancedBiddersInitializedListener.onAdvancedBiddersInitialized(list);
        }
    }

    public AdvancedBiddingTokens(@Nullable SdkInitializationListener sdkInitializationListener) {
        this.mSdkInitializationListener = sdkInitializationListener;
    }

    public void addAdvancedBidders(@NonNull List<Class<? extends MoPubAdvancedBidder>> list) {
        Preconditions.checkNotNull(list);
        AsyncTasks.safeExecuteOnExecutor(new AdvancedBiddersInitializationAsyncTask(list, this), new Void[0]);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getTokensAsJsonString(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        JSONObject tokensAsJsonObject = getTokensAsJsonObject(context);
        if (tokensAsJsonObject == null) {
            return null;
        }
        return tokensAsJsonObject.toString();
    }

    @Nullable
    private JSONObject getTokensAsJsonObject(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        if (this.mAdvancedBidders.isEmpty()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (MoPubAdvancedBidder moPubAdvancedBidder : this.mAdvancedBidders) {
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(GooglePlayConstants.BILLING_JSON_FIELD_TOKEN, moPubAdvancedBidder.getToken(context));
                jSONObject.put(moPubAdvancedBidder.getCreativeNetworkName(), jSONObject2);
            } catch (JSONException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("JSON parsing failed for creative network name: ");
                sb.append(moPubAdvancedBidder.getCreativeNetworkName());
                MoPubLog.d(sb.toString());
            }
        }
        return jSONObject;
    }

    public void onAdvancedBiddersInitialized(@NonNull List<MoPubAdvancedBidder> list) {
        Preconditions.checkNotNull(list);
        this.mAdvancedBidders = list;
        SdkInitializationListener sdkInitializationListener = this.mSdkInitializationListener;
        if (sdkInitializationListener != null) {
            sdkInitializationListener.onInitializationFinished();
        }
    }
}
