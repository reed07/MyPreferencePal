package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintAnchor.Strength;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;
import java.util.ArrayList;

public class ConstraintTableLayout extends ConstraintWidgetContainer {
    private ArrayList<Guideline> mHorizontalGuidelines = new ArrayList<>();
    private ArrayList<HorizontalSlice> mHorizontalSlices = new ArrayList<>();
    private int mNumCols = 0;
    private int mNumRows = 0;
    private int mPadding = 8;
    private boolean mVerticalGrowth = true;
    private ArrayList<Guideline> mVerticalGuidelines = new ArrayList<>();
    private ArrayList<VerticalSlice> mVerticalSlices = new ArrayList<>();
    private LinearSystem system = null;

    class HorizontalSlice {
        ConstraintWidget bottom;
        ConstraintWidget top;

        HorizontalSlice() {
        }
    }

    class VerticalSlice {
        int alignment = 1;
        ConstraintWidget left;
        ConstraintWidget right;

        VerticalSlice() {
        }
    }

    public boolean handlesInternalConstraints() {
        return true;
    }

    public void setNumCols(int i) {
        if (this.mVerticalGrowth && this.mNumCols != i) {
            this.mNumCols = i;
            setVerticalSlices();
            setTableDimensions();
        }
    }

    public void setNumRows(int i) {
        if (!this.mVerticalGrowth && this.mNumCols != i) {
            this.mNumRows = i;
            setHorizontalSlices();
            setTableDimensions();
        }
    }

    public void addToSolver(LinearSystem linearSystem) {
        super.addToSolver(linearSystem);
        int size = this.mChildren.size();
        if (size != 0) {
            setTableDimensions();
            if (linearSystem == this.mSystem) {
                int size2 = this.mVerticalGuidelines.size();
                int i = 0;
                while (true) {
                    boolean z = true;
                    if (i >= size2) {
                        break;
                    }
                    Guideline guideline = (Guideline) this.mVerticalGuidelines.get(i);
                    if (getHorizontalDimensionBehaviour() != DimensionBehaviour.WRAP_CONTENT) {
                        z = false;
                    }
                    guideline.setPositionRelaxed(z);
                    guideline.addToSolver(linearSystem);
                    i++;
                }
                int size3 = this.mHorizontalGuidelines.size();
                for (int i2 = 0; i2 < size3; i2++) {
                    Guideline guideline2 = (Guideline) this.mHorizontalGuidelines.get(i2);
                    guideline2.setPositionRelaxed(getVerticalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT);
                    guideline2.addToSolver(linearSystem);
                }
                for (int i3 = 0; i3 < size; i3++) {
                    ((ConstraintWidget) this.mChildren.get(i3)).addToSolver(linearSystem);
                }
            }
        }
    }

    public void setTableDimensions() {
        int size = this.mChildren.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += ((ConstraintWidget) this.mChildren.get(i2)).getContainerItemSkip();
        }
        int i3 = size + i;
        if (this.mVerticalGrowth) {
            if (this.mNumCols == 0) {
                setNumCols(1);
            }
            int i4 = this.mNumCols;
            int i5 = i3 / i4;
            if (i4 * i5 < i3) {
                i5++;
            }
            if (this.mNumRows != i5 || this.mVerticalGuidelines.size() != this.mNumCols - 1) {
                this.mNumRows = i5;
                setHorizontalSlices();
            } else {
                return;
            }
        } else {
            if (this.mNumRows == 0) {
                setNumRows(1);
            }
            int i6 = this.mNumRows;
            int i7 = i3 / i6;
            if (i6 * i7 < i3) {
                i7++;
            }
            if (this.mNumCols != i7 || this.mHorizontalGuidelines.size() != this.mNumRows - 1) {
                this.mNumCols = i7;
                setVerticalSlices();
            } else {
                return;
            }
        }
        setChildrenConnections();
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        this.system = linearSystem;
        super.setDebugSolverName(linearSystem, str);
        updateDebugSolverNames();
    }

    private void updateDebugSolverNames() {
        if (this.system != null) {
            int size = this.mVerticalGuidelines.size();
            for (int i = 0; i < size; i++) {
                Guideline guideline = (Guideline) this.mVerticalGuidelines.get(i);
                LinearSystem linearSystem = this.system;
                StringBuilder sb = new StringBuilder();
                sb.append(getDebugName());
                sb.append(".VG");
                sb.append(i);
                guideline.setDebugSolverName(linearSystem, sb.toString());
            }
            int size2 = this.mHorizontalGuidelines.size();
            for (int i2 = 0; i2 < size2; i2++) {
                Guideline guideline2 = (Guideline) this.mHorizontalGuidelines.get(i2);
                LinearSystem linearSystem2 = this.system;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(getDebugName());
                sb2.append(".HG");
                sb2.append(i2);
                guideline2.setDebugSolverName(linearSystem2, sb2.toString());
            }
        }
    }

    private void setVerticalSlices() {
        this.mVerticalSlices.clear();
        float f = 100.0f / ((float) this.mNumCols);
        int i = 0;
        ConstraintWidget constraintWidget = this;
        float f2 = f;
        while (i < this.mNumCols) {
            VerticalSlice verticalSlice = new VerticalSlice();
            verticalSlice.left = constraintWidget;
            if (i < this.mNumCols - 1) {
                Guideline guideline = new Guideline();
                guideline.setOrientation(1);
                guideline.setParent(this);
                guideline.setGuidePercent((int) f2);
                f2 += f;
                verticalSlice.right = guideline;
                this.mVerticalGuidelines.add(guideline);
            } else {
                verticalSlice.right = this;
            }
            ConstraintWidget constraintWidget2 = verticalSlice.right;
            this.mVerticalSlices.add(verticalSlice);
            i++;
            constraintWidget = constraintWidget2;
        }
        updateDebugSolverNames();
    }

    private void setHorizontalSlices() {
        this.mHorizontalSlices.clear();
        float f = 100.0f / ((float) this.mNumRows);
        ConstraintWidget constraintWidget = this;
        float f2 = f;
        int i = 0;
        while (i < this.mNumRows) {
            HorizontalSlice horizontalSlice = new HorizontalSlice();
            horizontalSlice.top = constraintWidget;
            if (i < this.mNumRows - 1) {
                Guideline guideline = new Guideline();
                guideline.setOrientation(0);
                guideline.setParent(this);
                guideline.setGuidePercent((int) f2);
                f2 += f;
                horizontalSlice.bottom = guideline;
                this.mHorizontalGuidelines.add(guideline);
            } else {
                horizontalSlice.bottom = this;
            }
            ConstraintWidget constraintWidget2 = horizontalSlice.bottom;
            this.mHorizontalSlices.add(horizontalSlice);
            i++;
            constraintWidget = constraintWidget2;
        }
        updateDebugSolverNames();
    }

    private void setChildrenConnections() {
        int size = this.mChildren.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i2);
            int containerItemSkip = i + constraintWidget.getContainerItemSkip();
            int i3 = this.mNumCols;
            int i4 = containerItemSkip % i3;
            HorizontalSlice horizontalSlice = (HorizontalSlice) this.mHorizontalSlices.get(containerItemSkip / i3);
            VerticalSlice verticalSlice = (VerticalSlice) this.mVerticalSlices.get(i4);
            ConstraintWidget constraintWidget2 = verticalSlice.left;
            ConstraintWidget constraintWidget3 = verticalSlice.right;
            ConstraintWidget constraintWidget4 = horizontalSlice.top;
            ConstraintWidget constraintWidget5 = horizontalSlice.bottom;
            constraintWidget.getAnchor(Type.LEFT).connect(constraintWidget2.getAnchor(Type.LEFT), this.mPadding);
            if (constraintWidget3 instanceof Guideline) {
                constraintWidget.getAnchor(Type.RIGHT).connect(constraintWidget3.getAnchor(Type.LEFT), this.mPadding);
            } else {
                constraintWidget.getAnchor(Type.RIGHT).connect(constraintWidget3.getAnchor(Type.RIGHT), this.mPadding);
            }
            switch (verticalSlice.alignment) {
                case 1:
                    constraintWidget.getAnchor(Type.LEFT).setStrength(Strength.STRONG);
                    constraintWidget.getAnchor(Type.RIGHT).setStrength(Strength.WEAK);
                    break;
                case 2:
                    constraintWidget.getAnchor(Type.LEFT).setStrength(Strength.WEAK);
                    constraintWidget.getAnchor(Type.RIGHT).setStrength(Strength.STRONG);
                    break;
                case 3:
                    constraintWidget.setHorizontalDimensionBehaviour(DimensionBehaviour.MATCH_CONSTRAINT);
                    break;
            }
            constraintWidget.getAnchor(Type.TOP).connect(constraintWidget4.getAnchor(Type.TOP), this.mPadding);
            if (constraintWidget5 instanceof Guideline) {
                constraintWidget.getAnchor(Type.BOTTOM).connect(constraintWidget5.getAnchor(Type.TOP), this.mPadding);
            } else {
                constraintWidget.getAnchor(Type.BOTTOM).connect(constraintWidget5.getAnchor(Type.BOTTOM), this.mPadding);
            }
            i = containerItemSkip + 1;
        }
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        super.updateFromSolver(linearSystem);
        if (linearSystem == this.mSystem) {
            int size = this.mVerticalGuidelines.size();
            for (int i = 0; i < size; i++) {
                ((Guideline) this.mVerticalGuidelines.get(i)).updateFromSolver(linearSystem);
            }
            int size2 = this.mHorizontalGuidelines.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ((Guideline) this.mHorizontalGuidelines.get(i2)).updateFromSolver(linearSystem);
            }
        }
    }
}
