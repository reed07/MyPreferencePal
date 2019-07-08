package com.brightcove.player.model;

import java.io.Serializable;

public class Element implements Serializable {
    protected String id;

    public void setID(String str) {
        this.id = str;
    }

    public String getID() {
        return this.id;
    }
}
