package com.integralads.avid.library.inmobi.session.internal;

import android.view.View;
import com.integralads.avid.library.inmobi.weakreference.AvidView;
import java.util.ArrayList;

public class ObstructionsWhiteList {
    private final ArrayList<AvidView> whiteList = new ArrayList<>();

    public void add(View view) {
        this.whiteList.add(new AvidView(view));
    }

    public ArrayList<AvidView> getWhiteList() {
        return this.whiteList;
    }
}
