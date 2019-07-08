package com.integralads.avid.library.inmobi.processing;

import android.view.View;
import com.integralads.avid.library.inmobi.activity.AvidActivityStack;
import com.integralads.avid.library.inmobi.processing.IAvidNodeProcessor.IAvidViewWalker;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import org.json.JSONObject;

public class AvidSceenProcessor implements IAvidNodeProcessor {
    private final IAvidNodeProcessor childrenProcessor;

    public AvidSceenProcessor(IAvidNodeProcessor iAvidNodeProcessor) {
        this.childrenProcessor = iAvidNodeProcessor;
    }

    public JSONObject getState(View view) {
        return AvidJSONUtil.getViewState(0, 0, 0, 0);
    }

    public void iterateChildren(View view, JSONObject jSONObject, IAvidViewWalker iAvidViewWalker, boolean z) {
        for (View walkView : AvidActivityStack.getInstance().getRootViews()) {
            iAvidViewWalker.walkView(walkView, this.childrenProcessor, jSONObject);
        }
    }
}
