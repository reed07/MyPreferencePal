package android.support.design.widget;

import android.graphics.Outline;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;

@RequiresApi
@RestrictTo
public class CircularBorderDrawableLollipop extends CircularBorderDrawable {
    public void getOutline(Outline outline) {
        copyBounds(this.rect);
        outline.setOval(this.rect);
    }
}
