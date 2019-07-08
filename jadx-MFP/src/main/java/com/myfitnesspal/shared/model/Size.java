package com.myfitnesspal.shared.model;

import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.uacf.core.util.Strings;

public class Size {
    private final int height;
    private final int width;

    public Size(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Size)) {
            return false;
        }
        Size size = (Size) obj;
        if (this.width == size.width && this.height == size.height) {
            z = true;
        }
        return z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.width);
        sb.append(AvidJSONUtil.KEY_X);
        sb.append(this.height);
        return sb.toString();
    }

    private static NumberFormatException invalidSize(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid Size: \"");
        sb.append(str);
        sb.append("\"");
        throw new NumberFormatException(sb.toString());
    }

    public static Size parseSize(String str) throws NumberFormatException {
        if (Strings.isEmpty(str)) {
            return null;
        }
        int indexOf = str.indexOf(42);
        if (indexOf < 0) {
            indexOf = str.indexOf(120);
        }
        if (indexOf >= 0) {
            try {
                return new Size(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
            } catch (NumberFormatException unused) {
                throw invalidSize(str);
            }
        } else {
            throw invalidSize(str);
        }
    }

    public int hashCode() {
        int i = this.height;
        int i2 = this.width;
        return i ^ ((i2 >>> 16) | (i2 << 16));
    }
}
