package com.brightcove.player.captioning;

import com.brightcove.player.model.Block;
import com.brightcove.player.model.Span;
import java.util.ArrayList;
import java.util.List;

public class BrightcoveClosedCaption extends Block {
    private String caption;
    private List<List<Span>> lines;

    public BrightcoveClosedCaption() {
    }

    public BrightcoveClosedCaption(int i, int i2, List<List<Span>> list) {
        this.beginTime = Integer.valueOf(i);
        this.endTime = Integer.valueOf(i2);
        this.lines = list;
    }

    public BrightcoveClosedCaption(int i, int i2, String str) {
        this.beginTime = Integer.valueOf(i);
        this.endTime = Integer.valueOf(i2);
        this.caption = str;
    }

    public List<List<Span>> getLines() {
        return this.lines;
    }

    public void setLines(List<List<Span>> list) {
        this.lines = list;
    }

    public String getCaption() {
        return this.caption;
    }

    public ArrayList<Integer> getTimeRange() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int ceil = (int) Math.ceil(((double) this.endTime.intValue()) / 1000.0d);
        for (int floor = (int) Math.floor(((double) this.beginTime.intValue()) / 1000.0d); floor < ceil; floor++) {
            arrayList.add(Integer.valueOf(floor));
        }
        return arrayList;
    }
}
