package android.support.constraint.solver;

import android.support.constraint.solver.SolverVariable.Type;
import com.facebook.appevents.UserDataStore;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class ArrayRow implements Row {
    float constantValue = BitmapDescriptorFactory.HUE_RED;
    boolean isSimpleDefinition = false;
    boolean used = false;
    SolverVariable variable = null;
    public final ArrayLinkedVariables variables;

    public ArrayRow(Cache cache) {
        this.variables = new ArrayLinkedVariables(this, cache);
    }

    /* access modifiers changed from: 0000 */
    public boolean hasKeyVariable() {
        SolverVariable solverVariable = this.variable;
        return solverVariable != null && (solverVariable.mType == Type.UNRESTRICTED || this.constantValue >= BitmapDescriptorFactory.HUE_RED);
    }

    public String toString() {
        return toReadableString();
    }

    /* access modifiers changed from: 0000 */
    public String toReadableString() {
        String str;
        boolean z;
        String str2;
        String str3 = "";
        if (this.variable == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str3);
            sb.append("0");
            str = sb.toString();
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str3);
            sb2.append(this.variable);
            str = sb2.toString();
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str);
        sb3.append(" = ");
        String sb4 = sb3.toString();
        if (this.constantValue != BitmapDescriptorFactory.HUE_RED) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(sb4);
            sb5.append(this.constantValue);
            sb4 = sb5.toString();
            z = true;
        } else {
            z = false;
        }
        int i = this.variables.currentSize;
        for (int i2 = 0; i2 < i; i2++) {
            SolverVariable variable2 = this.variables.getVariable(i2);
            if (variable2 != null) {
                float variableValue = this.variables.getVariableValue(i2);
                int i3 = (variableValue > BitmapDescriptorFactory.HUE_RED ? 1 : (variableValue == BitmapDescriptorFactory.HUE_RED ? 0 : -1));
                if (i3 != 0) {
                    String solverVariable = variable2.toString();
                    if (!z) {
                        if (variableValue < BitmapDescriptorFactory.HUE_RED) {
                            StringBuilder sb6 = new StringBuilder();
                            sb6.append(str2);
                            sb6.append("- ");
                            str2 = sb6.toString();
                            variableValue *= -1.0f;
                        }
                    } else if (i3 > 0) {
                        StringBuilder sb7 = new StringBuilder();
                        sb7.append(str2);
                        sb7.append(" + ");
                        str2 = sb7.toString();
                    } else {
                        StringBuilder sb8 = new StringBuilder();
                        sb8.append(str2);
                        sb8.append(" - ");
                        str2 = sb8.toString();
                        variableValue *= -1.0f;
                    }
                    if (variableValue == 1.0f) {
                        StringBuilder sb9 = new StringBuilder();
                        sb9.append(str2);
                        sb9.append(solverVariable);
                        str2 = sb9.toString();
                    } else {
                        StringBuilder sb10 = new StringBuilder();
                        sb10.append(str2);
                        sb10.append(variableValue);
                        sb10.append(" ");
                        sb10.append(solverVariable);
                        str2 = sb10.toString();
                    }
                    z = true;
                }
            }
        }
        if (z) {
            return str2;
        }
        StringBuilder sb11 = new StringBuilder();
        sb11.append(str2);
        sb11.append("0.0");
        return sb11.toString();
    }

    public void reset() {
        this.variable = null;
        this.variables.clear();
        this.constantValue = BitmapDescriptorFactory.HUE_RED;
        this.isSimpleDefinition = false;
    }

    /* access modifiers changed from: 0000 */
    public boolean hasVariable(SolverVariable solverVariable) {
        return this.variables.containsKey(solverVariable);
    }

    /* access modifiers changed from: 0000 */
    public ArrayRow createRowDefinition(SolverVariable solverVariable, int i) {
        this.variable = solverVariable;
        float f = (float) i;
        solverVariable.computedValue = f;
        this.constantValue = f;
        this.isSimpleDefinition = true;
        return this;
    }

    public ArrayRow createRowEquals(SolverVariable solverVariable, int i) {
        if (i < 0) {
            this.constantValue = (float) (i * -1);
            this.variables.put(solverVariable, 1.0f);
        } else {
            this.constantValue = (float) i;
            this.variables.put(solverVariable, -1.0f);
        }
        return this;
    }

    public ArrayRow createRowEquals(SolverVariable solverVariable, SolverVariable solverVariable2, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.constantValue = (float) i;
        }
        if (!z) {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
        } else {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
        }
        return this;
    }

    /* access modifiers changed from: 0000 */
    public ArrayRow addSingleError(SolverVariable solverVariable, int i) {
        this.variables.put(solverVariable, (float) i);
        return this;
    }

    public ArrayRow createRowGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.constantValue = (float) i;
        }
        if (!z) {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            this.variables.put(solverVariable3, 1.0f);
        } else {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, -1.0f);
        }
        return this;
    }

    public ArrayRow createRowLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i *= -1;
                z = true;
            }
            this.constantValue = (float) i;
        }
        if (!z) {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            this.variables.put(solverVariable3, -1.0f);
        } else {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, 1.0f);
        }
        return this;
    }

    public ArrayRow createRowEqualMatchDimensions(float f, float f2, float f3, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        this.constantValue = BitmapDescriptorFactory.HUE_RED;
        if (f2 == BitmapDescriptorFactory.HUE_RED || f == f3) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.variables.put(solverVariable3, -1.0f);
        } else if (f == BitmapDescriptorFactory.HUE_RED) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
        } else if (f3 == BitmapDescriptorFactory.HUE_RED) {
            this.variables.put(solverVariable3, 1.0f);
            this.variables.put(solverVariable4, -1.0f);
        } else {
            float f4 = (f / f2) / (f3 / f2);
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, f4);
            this.variables.put(solverVariable3, -f4);
        }
        return this;
    }

    /* access modifiers changed from: 0000 */
    public ArrayRow createRowCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2) {
        if (solverVariable2 == solverVariable3) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.variables.put(solverVariable2, -2.0f);
            return this;
        }
        if (f == 0.5f) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            if (i > 0 || i2 > 0) {
                this.constantValue = (float) ((-i) + i2);
            }
        } else if (f <= BitmapDescriptorFactory.HUE_RED) {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            this.constantValue = (float) i;
        } else if (f >= 1.0f) {
            this.variables.put(solverVariable3, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.constantValue = (float) i2;
        } else {
            float f2 = 1.0f - f;
            this.variables.put(solverVariable, f2 * 1.0f);
            this.variables.put(solverVariable2, f2 * -1.0f);
            this.variables.put(solverVariable3, -1.0f * f);
            this.variables.put(solverVariable4, 1.0f * f);
            if (i > 0 || i2 > 0) {
                this.constantValue = (((float) (-i)) * f2) + (((float) i2) * f);
            }
        }
        return this;
    }

    public ArrayRow addError(LinearSystem linearSystem, int i) {
        this.variables.put(linearSystem.createErrorVariable(i, "ep"), 1.0f);
        this.variables.put(linearSystem.createErrorVariable(i, UserDataStore.EMAIL), -1.0f);
        return this;
    }

    /* access modifiers changed from: 0000 */
    public ArrayRow createRowDimensionPercent(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, float f) {
        this.variables.put(solverVariable, -1.0f);
        this.variables.put(solverVariable2, 1.0f - f);
        this.variables.put(solverVariable3, f);
        return this;
    }

    public ArrayRow createRowDimensionRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.variables.put(solverVariable, -1.0f);
        this.variables.put(solverVariable2, 1.0f);
        this.variables.put(solverVariable3, f);
        this.variables.put(solverVariable4, -f);
        return this;
    }

    public ArrayRow createRowWithAngle(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.variables.put(solverVariable3, 0.5f);
        this.variables.put(solverVariable4, 0.5f);
        this.variables.put(solverVariable, -0.5f);
        this.variables.put(solverVariable2, -0.5f);
        this.constantValue = -f;
        return this;
    }

    /* access modifiers changed from: 0000 */
    public void ensurePositiveConstant() {
        float f = this.constantValue;
        if (f < BitmapDescriptorFactory.HUE_RED) {
            this.constantValue = f * -1.0f;
            this.variables.invert();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean chooseSubject(LinearSystem linearSystem) {
        boolean z;
        SolverVariable chooseSubject = this.variables.chooseSubject(linearSystem);
        if (chooseSubject == null) {
            z = true;
        } else {
            pivot(chooseSubject);
            z = false;
        }
        if (this.variables.currentSize == 0) {
            this.isSimpleDefinition = true;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public SolverVariable pickPivot(SolverVariable solverVariable) {
        return this.variables.getPivotCandidate(null, solverVariable);
    }

    /* access modifiers changed from: 0000 */
    public void pivot(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.variable;
        if (solverVariable2 != null) {
            this.variables.put(solverVariable2, -1.0f);
            this.variable = null;
        }
        float remove = this.variables.remove(solverVariable, true) * -1.0f;
        this.variable = solverVariable;
        if (remove != 1.0f) {
            this.constantValue /= remove;
            this.variables.divideByAmount(remove);
        }
    }

    public boolean isEmpty() {
        return this.variable == null && this.constantValue == BitmapDescriptorFactory.HUE_RED && this.variables.currentSize == 0;
    }

    public SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr) {
        return this.variables.getPivotCandidate(zArr, null);
    }

    public void clear() {
        this.variables.clear();
        this.variable = null;
        this.constantValue = BitmapDescriptorFactory.HUE_RED;
    }

    public void initFromRow(Row row) {
        if (row instanceof ArrayRow) {
            ArrayRow arrayRow = (ArrayRow) row;
            this.variable = null;
            this.variables.clear();
            for (int i = 0; i < arrayRow.variables.currentSize; i++) {
                this.variables.add(arrayRow.variables.getVariable(i), arrayRow.variables.getVariableValue(i), true);
            }
        }
    }

    public void addError(SolverVariable solverVariable) {
        float f = 1.0f;
        if (solverVariable.strength != 1) {
            if (solverVariable.strength == 2) {
                f = 1000.0f;
            } else if (solverVariable.strength == 3) {
                f = 1000000.0f;
            } else if (solverVariable.strength == 4) {
                f = 1.0E9f;
            } else if (solverVariable.strength == 5) {
                f = 1.0E12f;
            }
        }
        this.variables.put(solverVariable, f);
    }

    public SolverVariable getKey() {
        return this.variable;
    }
}
