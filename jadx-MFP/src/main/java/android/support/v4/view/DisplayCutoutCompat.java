package android.support.v4.view;

public final class DisplayCutoutCompat {
    private final Object mDisplayCutout;

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DisplayCutoutCompat displayCutoutCompat = (DisplayCutoutCompat) obj;
        Object obj2 = this.mDisplayCutout;
        if (obj2 != null) {
            z = obj2.equals(displayCutoutCompat.mDisplayCutout);
        } else if (displayCutoutCompat.mDisplayCutout != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        Object obj = this.mDisplayCutout;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DisplayCutoutCompat{");
        sb.append(this.mDisplayCutout);
        sb.append("}");
        return sb.toString();
    }
}
