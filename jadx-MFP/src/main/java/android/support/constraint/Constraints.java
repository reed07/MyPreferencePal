package android.support.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class Constraints extends ViewGroup {
    ConstraintSet myConstraintSet;

    public static class LayoutParams extends android.support.constraint.ConstraintLayout.LayoutParams {
        public float alpha;
        public boolean applyElevation;
        public float elevation;
        public float rotation;
        public float rotationX;
        public float rotationY;
        public float scaleX;
        public float scaleY;
        public float transformPivotX;
        public float transformPivotY;
        public float translationX;
        public float translationY;
        public float translationZ;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.alpha = 1.0f;
            this.applyElevation = false;
            this.elevation = BitmapDescriptorFactory.HUE_RED;
            this.rotation = BitmapDescriptorFactory.HUE_RED;
            this.rotationX = BitmapDescriptorFactory.HUE_RED;
            this.rotationY = BitmapDescriptorFactory.HUE_RED;
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.transformPivotX = BitmapDescriptorFactory.HUE_RED;
            this.transformPivotY = BitmapDescriptorFactory.HUE_RED;
            this.translationX = BitmapDescriptorFactory.HUE_RED;
            this.translationY = BitmapDescriptorFactory.HUE_RED;
            this.translationZ = BitmapDescriptorFactory.HUE_RED;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.alpha = 1.0f;
            this.applyElevation = false;
            this.elevation = BitmapDescriptorFactory.HUE_RED;
            this.rotation = BitmapDescriptorFactory.HUE_RED;
            this.rotationX = BitmapDescriptorFactory.HUE_RED;
            this.rotationY = BitmapDescriptorFactory.HUE_RED;
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.transformPivotX = BitmapDescriptorFactory.HUE_RED;
            this.transformPivotY = BitmapDescriptorFactory.HUE_RED;
            this.translationX = BitmapDescriptorFactory.HUE_RED;
            this.translationY = BitmapDescriptorFactory.HUE_RED;
            this.translationZ = BitmapDescriptorFactory.HUE_RED;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConstraintSet);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ConstraintSet_android_alpha) {
                    this.alpha = obtainStyledAttributes.getFloat(index, this.alpha);
                } else if (index == R.styleable.ConstraintSet_android_elevation) {
                    this.elevation = obtainStyledAttributes.getFloat(index, this.elevation);
                    this.applyElevation = true;
                } else if (index == R.styleable.ConstraintSet_android_rotationX) {
                    this.rotationX = obtainStyledAttributes.getFloat(index, this.rotationX);
                } else if (index == R.styleable.ConstraintSet_android_rotationY) {
                    this.rotationY = obtainStyledAttributes.getFloat(index, this.rotationY);
                } else if (index == R.styleable.ConstraintSet_android_rotation) {
                    this.rotation = obtainStyledAttributes.getFloat(index, this.rotation);
                } else if (index == R.styleable.ConstraintSet_android_scaleX) {
                    this.scaleX = obtainStyledAttributes.getFloat(index, this.scaleX);
                } else if (index == R.styleable.ConstraintSet_android_scaleY) {
                    this.scaleY = obtainStyledAttributes.getFloat(index, this.scaleY);
                } else if (index == R.styleable.ConstraintSet_android_transformPivotX) {
                    this.transformPivotX = obtainStyledAttributes.getFloat(index, this.transformPivotX);
                } else if (index == R.styleable.ConstraintSet_android_transformPivotY) {
                    this.transformPivotY = obtainStyledAttributes.getFloat(index, this.transformPivotY);
                } else if (index == R.styleable.ConstraintSet_android_translationX) {
                    this.translationX = obtainStyledAttributes.getFloat(index, this.translationX);
                } else if (index == R.styleable.ConstraintSet_android_translationY) {
                    this.translationY = obtainStyledAttributes.getFloat(index, this.translationY);
                } else if (index == R.styleable.ConstraintSet_android_translationZ) {
                    this.translationX = obtainStyledAttributes.getFloat(index, this.translationZ);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public Constraints(Context context) {
        super(context);
        super.setVisibility(8);
    }

    public Constraints(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
        super.setVisibility(8);
    }

    public Constraints(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
        super.setVisibility(8);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    private void init(AttributeSet attributeSet) {
        Log.v("Constraints", " ################# init");
    }

    /* access modifiers changed from: protected */
    public android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new android.support.constraint.ConstraintLayout.LayoutParams(layoutParams);
    }

    public ConstraintSet getConstraintSet() {
        if (this.myConstraintSet == null) {
            this.myConstraintSet = new ConstraintSet();
        }
        this.myConstraintSet.clone(this);
        return this.myConstraintSet;
    }
}
