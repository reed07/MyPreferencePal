package com.brightcove.player.model;

import com.brightcove.player.util.ErrorUtil;
import java.util.Locale;
import java.util.Map;

public class CuePoint extends MetadataObject implements Comparable<CuePoint> {
    private int position;
    private PositionType positionType;
    private String type;

    public enum PositionType {
        BEFORE,
        POINT_IN_TIME,
        AFTER
    }

    public CuePoint(PositionType positionType2, String str, Map<String, Object> map) {
        super(map);
        if (positionType2 != PositionType.POINT_IN_TIME) {
            this.positionType = positionType2;
            this.type = str;
            return;
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.OTHER_CONSTRUCTOR));
    }

    public CuePoint(int i, String str, Map<String, Object> map) {
        super(map);
        this.positionType = PositionType.POINT_IN_TIME;
        this.position = i;
        this.type = str;
    }

    public PositionType getPositionType() {
        return this.positionType;
    }

    public int getPosition() {
        if (this.positionType == PositionType.POINT_IN_TIME) {
            return this.position;
        }
        throw new IllegalStateException(String.format(Locale.getDefault(), ErrorUtil.getMessage(ErrorUtil.INVALID_POINT_IN_TIME), new Object[]{this.positionType.toString()}));
    }

    public String getType() {
        return this.type;
    }

    public int compareTo(CuePoint cuePoint) {
        if (this.positionType == PositionType.BEFORE && cuePoint.positionType != PositionType.BEFORE) {
            return -1;
        }
        if (this.positionType == PositionType.AFTER && cuePoint.positionType != PositionType.AFTER) {
            return 1;
        }
        if (this.positionType == PositionType.POINT_IN_TIME) {
            if (cuePoint.positionType == PositionType.BEFORE) {
                return 1;
            }
            if (cuePoint.positionType == PositionType.AFTER) {
                return -1;
            }
            int i = this.position;
            int i2 = cuePoint.position;
            if (i < i2) {
                return -1;
            }
            if (i > i2) {
                return 1;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CuePoint) || compareTo((CuePoint) obj) != 0) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((this.positionType.hashCode() * 31) + this.position) * 31) + this.type.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CuePoint {");
        sb.append("position:");
        sb.append(this.position);
        sb.append(" positionType:");
        Object obj = this.positionType;
        if (obj == null) {
            obj = "null";
        }
        sb.append(obj);
        sb.append(" type:");
        String str = this.type;
        if (str == null) {
            str = "null";
        }
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }
}
