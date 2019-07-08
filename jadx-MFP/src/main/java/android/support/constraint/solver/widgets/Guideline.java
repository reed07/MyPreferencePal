package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;
import java.util.ArrayList;

public class Guideline extends ConstraintWidget {
    private ConstraintAnchor mAnchor = this.mTop;
    private Rectangle mHead;
    private int mHeadSize;
    private boolean mIsPositionRelaxed;
    private int mMinimumPosition;
    private int mOrientation;
    protected int mRelativeBegin = -1;
    protected int mRelativeEnd = -1;
    protected float mRelativePercent = -1.0f;

    public boolean allowedInBarrier() {
        return true;
    }

    public Guideline() {
        this.mOrientation = 0;
        this.mIsPositionRelaxed = false;
        this.mMinimumPosition = 0;
        this.mHead = new Rectangle();
        this.mHeadSize = 8;
        this.mAnchors.clear();
        this.mAnchors.add(this.mAnchor);
        int length = this.mListAnchors.length;
        for (int i = 0; i < length; i++) {
            this.mListAnchors[i] = this.mAnchor;
        }
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            this.mAnchors.clear();
            if (this.mOrientation == 1) {
                this.mAnchor = this.mLeft;
            } else {
                this.mAnchor = this.mTop;
            }
            this.mAnchors.add(this.mAnchor);
            int length = this.mListAnchors.length;
            for (int i2 = 0; i2 < length; i2++) {
                this.mListAnchors[i2] = this.mAnchor;
            }
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setPositionRelaxed(boolean z) {
        if (this.mIsPositionRelaxed != z) {
            this.mIsPositionRelaxed = z;
        }
    }

    public ConstraintAnchor getAnchor(Type type) {
        switch (type) {
            case LEFT:
            case RIGHT:
                if (this.mOrientation == 1) {
                    return this.mAnchor;
                }
                break;
            case TOP:
            case BOTTOM:
                if (this.mOrientation == 0) {
                    return this.mAnchor;
                }
                break;
            case BASELINE:
            case CENTER:
            case CENTER_X:
            case CENTER_Y:
            case NONE:
                return null;
        }
        throw new AssertionError(type.name());
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setGuidePercent(int i) {
        setGuidePercent(((float) i) / 100.0f);
    }

    public void setGuidePercent(float f) {
        if (f > -1.0f) {
            this.mRelativePercent = f;
            this.mRelativeBegin = -1;
            this.mRelativeEnd = -1;
        }
    }

    public void setGuideBegin(int i) {
        if (i > -1) {
            this.mRelativePercent = -1.0f;
            this.mRelativeBegin = i;
            this.mRelativeEnd = -1;
        }
    }

    public void setGuideEnd(int i) {
        if (i > -1) {
            this.mRelativePercent = -1.0f;
            this.mRelativeBegin = -1;
            this.mRelativeEnd = i;
        }
    }

    public void analyze(int i) {
        ConstraintWidget parent = getParent();
        if (parent != null) {
            if (getOrientation() == 1) {
                this.mTop.getResolutionNode().dependsOn(1, parent.mTop.getResolutionNode(), 0);
                this.mBottom.getResolutionNode().dependsOn(1, parent.mTop.getResolutionNode(), 0);
                if (this.mRelativeBegin != -1) {
                    this.mLeft.getResolutionNode().dependsOn(1, parent.mLeft.getResolutionNode(), this.mRelativeBegin);
                    this.mRight.getResolutionNode().dependsOn(1, parent.mLeft.getResolutionNode(), this.mRelativeBegin);
                } else if (this.mRelativeEnd != -1) {
                    this.mLeft.getResolutionNode().dependsOn(1, parent.mRight.getResolutionNode(), -this.mRelativeEnd);
                    this.mRight.getResolutionNode().dependsOn(1, parent.mRight.getResolutionNode(), -this.mRelativeEnd);
                } else if (this.mRelativePercent != -1.0f && parent.getHorizontalDimensionBehaviour() == DimensionBehaviour.FIXED) {
                    int i2 = (int) (((float) parent.mWidth) * this.mRelativePercent);
                    this.mLeft.getResolutionNode().dependsOn(1, parent.mLeft.getResolutionNode(), i2);
                    this.mRight.getResolutionNode().dependsOn(1, parent.mLeft.getResolutionNode(), i2);
                }
            } else {
                this.mLeft.getResolutionNode().dependsOn(1, parent.mLeft.getResolutionNode(), 0);
                this.mRight.getResolutionNode().dependsOn(1, parent.mLeft.getResolutionNode(), 0);
                if (this.mRelativeBegin != -1) {
                    this.mTop.getResolutionNode().dependsOn(1, parent.mTop.getResolutionNode(), this.mRelativeBegin);
                    this.mBottom.getResolutionNode().dependsOn(1, parent.mTop.getResolutionNode(), this.mRelativeBegin);
                } else if (this.mRelativeEnd != -1) {
                    this.mTop.getResolutionNode().dependsOn(1, parent.mBottom.getResolutionNode(), -this.mRelativeEnd);
                    this.mBottom.getResolutionNode().dependsOn(1, parent.mBottom.getResolutionNode(), -this.mRelativeEnd);
                } else if (this.mRelativePercent != -1.0f && parent.getVerticalDimensionBehaviour() == DimensionBehaviour.FIXED) {
                    int i3 = (int) (((float) parent.mHeight) * this.mRelativePercent);
                    this.mTop.getResolutionNode().dependsOn(1, parent.mTop.getResolutionNode(), i3);
                    this.mBottom.getResolutionNode().dependsOn(1, parent.mTop.getResolutionNode(), i3);
                }
            }
        }
    }

    public void addToSolver(LinearSystem linearSystem) {
        ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) getParent();
        if (constraintWidgetContainer != null) {
            ConstraintAnchor anchor = constraintWidgetContainer.getAnchor(Type.LEFT);
            ConstraintAnchor anchor2 = constraintWidgetContainer.getAnchor(Type.RIGHT);
            boolean z = this.mParent != null && this.mParent.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT;
            if (this.mOrientation == 0) {
                anchor = constraintWidgetContainer.getAnchor(Type.TOP);
                anchor2 = constraintWidgetContainer.getAnchor(Type.BOTTOM);
                z = this.mParent != null && this.mParent.mListDimensionBehaviors[1] == DimensionBehaviour.WRAP_CONTENT;
            }
            if (this.mRelativeBegin != -1) {
                SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mAnchor);
                linearSystem.addEquality(createObjectVariable, linearSystem.createObjectVariable(anchor), this.mRelativeBegin, 6);
                if (z) {
                    linearSystem.addGreaterThan(linearSystem.createObjectVariable(anchor2), createObjectVariable, 0, 5);
                }
            } else if (this.mRelativeEnd != -1) {
                SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mAnchor);
                SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(anchor2);
                linearSystem.addEquality(createObjectVariable2, createObjectVariable3, -this.mRelativeEnd, 6);
                if (z) {
                    linearSystem.addGreaterThan(createObjectVariable2, linearSystem.createObjectVariable(anchor), 0, 5);
                    linearSystem.addGreaterThan(createObjectVariable3, createObjectVariable2, 0, 5);
                }
            } else if (this.mRelativePercent != -1.0f) {
                linearSystem.addConstraint(LinearSystem.createRowDimensionPercent(linearSystem, linearSystem.createObjectVariable(this.mAnchor), linearSystem.createObjectVariable(anchor), linearSystem.createObjectVariable(anchor2), this.mRelativePercent, this.mIsPositionRelaxed));
            }
        }
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        if (getParent() != null) {
            int objectVariableValue = linearSystem.getObjectVariableValue(this.mAnchor);
            if (this.mOrientation == 1) {
                setX(objectVariableValue);
                setY(0);
                setHeight(getParent().getHeight());
                setWidth(0);
            } else {
                setX(0);
                setY(objectVariableValue);
                setWidth(getParent().getWidth());
                setHeight(0);
            }
        }
    }
}
