package com.integralads.avid.library.inmobi.walking.async;

import android.text.TextUtils;
import com.integralads.avid.library.inmobi.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.inmobi.utils.AvidCommand;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.integralads.avid.library.inmobi.walking.async.AvidAsyncTask.StateProvider;
import java.util.HashSet;
import org.json.JSONObject;

public class AvidPublishAsyncTask extends AbstractAvidPublishAsyncTask {
    public AvidPublishAsyncTask(StateProvider stateProvider, AvidAdSessionRegistry avidAdSessionRegistry, HashSet<String> hashSet, JSONObject jSONObject, double d) {
        super(stateProvider, avidAdSessionRegistry, hashSet, jSONObject, d);
    }

    /* access modifiers changed from: protected */
    public String doInBackground(Object... objArr) {
        if (AvidJSONUtil.equalStates(this.state, this.stateProvider.getPreviousState())) {
            return null;
        }
        this.stateProvider.setPreviousState(this.state);
        return AvidCommand.setNativeViewState(AvidJSONUtil.getTreeJSONObject(this.state, this.timestamp).toString());
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String str) {
        if (!TextUtils.isEmpty(str)) {
            injectCommand(str);
        }
        super.onPostExecute(str);
    }

    private void injectCommand(String str) {
        for (InternalAvidAdSession internalAvidAdSession : this.adSessionRegistry.getInternalAvidAdSessions()) {
            if (this.sessionIds.contains(internalAvidAdSession.getAvidAdSessionId())) {
                internalAvidAdSession.publishNativeViewStateCommand(str, this.timestamp);
            }
        }
    }
}
