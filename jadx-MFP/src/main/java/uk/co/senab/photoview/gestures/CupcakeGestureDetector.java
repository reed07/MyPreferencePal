package uk.co.senab.photoview.gestures;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import uk.co.senab.photoview.log.LogManager;

public class CupcakeGestureDetector implements GestureDetector {
    private boolean mIsDragging;
    float mLastTouchX;
    float mLastTouchY;
    protected OnGestureListener mListener;
    final float mMinimumVelocity;
    final float mTouchSlop;
    private VelocityTracker mVelocityTracker;

    public boolean isScaling() {
        return false;
    }

    public void setOnGestureListener(OnGestureListener onGestureListener) {
        this.mListener = onGestureListener;
    }

    public CupcakeGestureDetector(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mMinimumVelocity = (float) viewConfiguration.getScaledMinimumFlingVelocity();
        this.mTouchSlop = (float) viewConfiguration.getScaledTouchSlop();
    }

    /* access modifiers changed from: 0000 */
    public float getActiveX(MotionEvent motionEvent) {
        return motionEvent.getX();
    }

    /* access modifiers changed from: 0000 */
    public float getActiveY(MotionEvent motionEvent) {
        return motionEvent.getY();
    }

    public boolean isDragging() {
        return this.mIsDragging;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        switch (motionEvent.getAction()) {
            case 0:
                this.mVelocityTracker = VelocityTracker.obtain();
                VelocityTracker velocityTracker = this.mVelocityTracker;
                if (velocityTracker != null) {
                    velocityTracker.addMovement(motionEvent);
                } else {
                    LogManager.getLogger().i("CupcakeGestureDetector", "Velocity tracker is null");
                }
                this.mLastTouchX = getActiveX(motionEvent);
                this.mLastTouchY = getActiveY(motionEvent);
                this.mIsDragging = false;
                break;
            case 1:
                if (this.mIsDragging && this.mVelocityTracker != null) {
                    this.mLastTouchX = getActiveX(motionEvent);
                    this.mLastTouchY = getActiveY(motionEvent);
                    this.mVelocityTracker.addMovement(motionEvent);
                    this.mVelocityTracker.computeCurrentVelocity(1000);
                    float xVelocity = this.mVelocityTracker.getXVelocity();
                    float yVelocity = this.mVelocityTracker.getYVelocity();
                    if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.mMinimumVelocity) {
                        this.mListener.onFling(this.mLastTouchX, this.mLastTouchY, -xVelocity, -yVelocity);
                    }
                }
                VelocityTracker velocityTracker2 = this.mVelocityTracker;
                if (velocityTracker2 != null) {
                    velocityTracker2.recycle();
                    this.mVelocityTracker = null;
                    break;
                }
                break;
            case 2:
                float activeX = getActiveX(motionEvent);
                float activeY = getActiveY(motionEvent);
                float f = activeX - this.mLastTouchX;
                float f2 = activeY - this.mLastTouchY;
                if (!this.mIsDragging) {
                    if (Math.sqrt((double) ((f * f) + (f2 * f2))) >= ((double) this.mTouchSlop)) {
                        z = true;
                    }
                    this.mIsDragging = z;
                }
                if (this.mIsDragging) {
                    this.mListener.onDrag(f, f2);
                    this.mLastTouchX = activeX;
                    this.mLastTouchY = activeY;
                    VelocityTracker velocityTracker3 = this.mVelocityTracker;
                    if (velocityTracker3 != null) {
                        velocityTracker3.addMovement(motionEvent);
                        break;
                    }
                }
                break;
            case 3:
                VelocityTracker velocityTracker4 = this.mVelocityTracker;
                if (velocityTracker4 != null) {
                    velocityTracker4.recycle();
                    this.mVelocityTracker = null;
                    break;
                }
                break;
        }
        return true;
    }
}
