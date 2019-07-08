package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class Optimizer {
    static boolean[] flags = new boolean[3];

    static void checkMatchParent(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ConstraintWidget constraintWidget) {
        if (constraintWidgetContainer.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT && constraintWidget.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_PARENT) {
            int i = constraintWidget.mLeft.mMargin;
            int width = constraintWidgetContainer.getWidth() - constraintWidget.mRight.mMargin;
            constraintWidget.mLeft.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mLeft);
            constraintWidget.mRight.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mRight);
            linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, i);
            linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, width);
            constraintWidget.mHorizontalResolution = 2;
            constraintWidget.setHorizontalDimension(i, width);
        }
        if (constraintWidgetContainer.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT && constraintWidget.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_PARENT) {
            int i2 = constraintWidget.mTop.mMargin;
            int height = constraintWidgetContainer.getHeight() - constraintWidget.mBottom.mMargin;
            constraintWidget.mTop.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mTop);
            constraintWidget.mBottom.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBottom);
            linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, i2);
            linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, height);
            if (constraintWidget.mBaselineDistance > 0 || constraintWidget.getVisibility() == 8) {
                constraintWidget.mBaseline.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBaseline);
                linearSystem.addEquality(constraintWidget.mBaseline.mSolverVariable, constraintWidget.mBaselineDistance + i2);
            }
            constraintWidget.mVerticalResolution = 2;
            constraintWidget.setVerticalDimension(i2, height);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x003e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean optimizableMatchConstraint(android.support.constraint.solver.widgets.ConstraintWidget r4, int r5) {
        /*
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r4.mListDimensionBehaviors
            r0 = r0[r5]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r2 = 0
            if (r0 == r1) goto L_0x000a
            return r2
        L_0x000a:
            float r0 = r4.mDimensionRatio
            r1 = 0
            r3 = 1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 == 0) goto L_0x0020
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r4.mListDimensionBehaviors
            if (r5 != 0) goto L_0x0017
            goto L_0x0018
        L_0x0017:
            r3 = 0
        L_0x0018:
            r4 = r4[r3]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r5) goto L_0x001f
            return r2
        L_0x001f:
            return r2
        L_0x0020:
            if (r5 != 0) goto L_0x0030
            int r5 = r4.mMatchConstraintDefaultWidth
            if (r5 == 0) goto L_0x0027
            return r2
        L_0x0027:
            int r5 = r4.mMatchConstraintMinWidth
            if (r5 != 0) goto L_0x002f
            int r4 = r4.mMatchConstraintMaxWidth
            if (r4 == 0) goto L_0x003e
        L_0x002f:
            return r2
        L_0x0030:
            int r5 = r4.mMatchConstraintDefaultHeight
            if (r5 == 0) goto L_0x0035
            return r2
        L_0x0035:
            int r5 = r4.mMatchConstraintMinHeight
            if (r5 != 0) goto L_0x003f
            int r4 = r4.mMatchConstraintMaxHeight
            if (r4 == 0) goto L_0x003e
            goto L_0x003f
        L_0x003e:
            return r3
        L_0x003f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Optimizer.optimizableMatchConstraint(android.support.constraint.solver.widgets.ConstraintWidget, int):boolean");
    }

    static void analyze(int i, ConstraintWidget constraintWidget) {
        ConstraintWidget constraintWidget2 = constraintWidget;
        constraintWidget.updateResolutionNodes();
        ResolutionAnchor resolutionNode = constraintWidget2.mLeft.getResolutionNode();
        ResolutionAnchor resolutionNode2 = constraintWidget2.mTop.getResolutionNode();
        ResolutionAnchor resolutionNode3 = constraintWidget2.mRight.getResolutionNode();
        ResolutionAnchor resolutionNode4 = constraintWidget2.mBottom.getResolutionNode();
        boolean z = (i & 8) == 8;
        boolean z2 = constraintWidget2.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(constraintWidget2, 0);
        if (!(resolutionNode.type == 4 || resolutionNode3.type == 4)) {
            if (constraintWidget2.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED || (z2 && constraintWidget.getVisibility() == 8)) {
                if (constraintWidget2.mLeft.mTarget == null && constraintWidget2.mRight.mTarget == null) {
                    resolutionNode.setType(1);
                    resolutionNode3.setType(1);
                    if (z) {
                        resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode3.dependsOn(resolutionNode, constraintWidget.getWidth());
                    }
                } else if (constraintWidget2.mLeft.mTarget != null && constraintWidget2.mRight.mTarget == null) {
                    resolutionNode.setType(1);
                    resolutionNode3.setType(1);
                    if (z) {
                        resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode3.dependsOn(resolutionNode, constraintWidget.getWidth());
                    }
                } else if (constraintWidget2.mLeft.mTarget == null && constraintWidget2.mRight.mTarget != null) {
                    resolutionNode.setType(1);
                    resolutionNode3.setType(1);
                    resolutionNode.dependsOn(resolutionNode3, -constraintWidget.getWidth());
                    if (z) {
                        resolutionNode.dependsOn(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode.dependsOn(resolutionNode3, -constraintWidget.getWidth());
                    }
                } else if (!(constraintWidget2.mLeft.mTarget == null || constraintWidget2.mRight.mTarget == null)) {
                    resolutionNode.setType(2);
                    resolutionNode3.setType(2);
                    if (z) {
                        constraintWidget.getResolutionWidth().addDependent(resolutionNode);
                        constraintWidget.getResolutionWidth().addDependent(resolutionNode3);
                        resolutionNode.setOpposite(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                        resolutionNode3.setOpposite(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode.setOpposite(resolutionNode3, (float) (-constraintWidget.getWidth()));
                        resolutionNode3.setOpposite(resolutionNode, (float) constraintWidget.getWidth());
                    }
                }
            } else if (z2) {
                int width = constraintWidget.getWidth();
                resolutionNode.setType(1);
                resolutionNode3.setType(1);
                if (constraintWidget2.mLeft.mTarget == null && constraintWidget2.mRight.mTarget == null) {
                    if (z) {
                        resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode3.dependsOn(resolutionNode, width);
                    }
                } else if (constraintWidget2.mLeft.mTarget == null || constraintWidget2.mRight.mTarget != null) {
                    if (constraintWidget2.mLeft.mTarget != null || constraintWidget2.mRight.mTarget == null) {
                        if (!(constraintWidget2.mLeft.mTarget == null || constraintWidget2.mRight.mTarget == null)) {
                            if (z) {
                                constraintWidget.getResolutionWidth().addDependent(resolutionNode);
                                constraintWidget.getResolutionWidth().addDependent(resolutionNode3);
                            }
                            if (constraintWidget2.mDimensionRatio == BitmapDescriptorFactory.HUE_RED) {
                                resolutionNode.setType(3);
                                resolutionNode3.setType(3);
                                resolutionNode.setOpposite(resolutionNode3, BitmapDescriptorFactory.HUE_RED);
                                resolutionNode3.setOpposite(resolutionNode, BitmapDescriptorFactory.HUE_RED);
                            } else {
                                resolutionNode.setType(2);
                                resolutionNode3.setType(2);
                                resolutionNode.setOpposite(resolutionNode3, (float) (-width));
                                resolutionNode3.setOpposite(resolutionNode, (float) width);
                                constraintWidget2.setWidth(width);
                            }
                        }
                    } else if (z) {
                        resolutionNode.dependsOn(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode.dependsOn(resolutionNode3, -width);
                    }
                } else if (z) {
                    resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                } else {
                    resolutionNode3.dependsOn(resolutionNode, width);
                }
            }
        }
        boolean z3 = constraintWidget2.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(constraintWidget2, 1);
        if (resolutionNode2.type != 4 && resolutionNode4.type != 4) {
            if (constraintWidget2.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED || (z3 && constraintWidget.getVisibility() == 8)) {
                if (constraintWidget2.mTop.mTarget == null && constraintWidget2.mBottom.mTarget == null) {
                    resolutionNode2.setType(1);
                    resolutionNode4.setType(1);
                    if (z) {
                        resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode4.dependsOn(resolutionNode2, constraintWidget.getHeight());
                    }
                    if (constraintWidget2.mBaseline.mTarget != null) {
                        constraintWidget2.mBaseline.getResolutionNode().setType(1);
                        resolutionNode2.dependsOn(1, constraintWidget2.mBaseline.getResolutionNode(), -constraintWidget2.mBaselineDistance);
                    }
                } else if (constraintWidget2.mTop.mTarget != null && constraintWidget2.mBottom.mTarget == null) {
                    resolutionNode2.setType(1);
                    resolutionNode4.setType(1);
                    if (z) {
                        resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode4.dependsOn(resolutionNode2, constraintWidget.getHeight());
                    }
                    if (constraintWidget2.mBaselineDistance > 0) {
                        constraintWidget2.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget2.mBaselineDistance);
                    }
                } else if (constraintWidget2.mTop.mTarget == null && constraintWidget2.mBottom.mTarget != null) {
                    resolutionNode2.setType(1);
                    resolutionNode4.setType(1);
                    if (z) {
                        resolutionNode2.dependsOn(resolutionNode4, -1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode2.dependsOn(resolutionNode4, -constraintWidget.getHeight());
                    }
                    if (constraintWidget2.mBaselineDistance > 0) {
                        constraintWidget2.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget2.mBaselineDistance);
                    }
                } else if (constraintWidget2.mTop.mTarget != null && constraintWidget2.mBottom.mTarget != null) {
                    resolutionNode2.setType(2);
                    resolutionNode4.setType(2);
                    if (z) {
                        resolutionNode2.setOpposite(resolutionNode4, -1, constraintWidget.getResolutionHeight());
                        resolutionNode4.setOpposite(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                        constraintWidget.getResolutionHeight().addDependent(resolutionNode2);
                        constraintWidget.getResolutionWidth().addDependent(resolutionNode4);
                    } else {
                        resolutionNode2.setOpposite(resolutionNode4, (float) (-constraintWidget.getHeight()));
                        resolutionNode4.setOpposite(resolutionNode2, (float) constraintWidget.getHeight());
                    }
                    if (constraintWidget2.mBaselineDistance > 0) {
                        constraintWidget2.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget2.mBaselineDistance);
                    }
                }
            } else if (z3) {
                int height = constraintWidget.getHeight();
                resolutionNode2.setType(1);
                resolutionNode4.setType(1);
                if (constraintWidget2.mTop.mTarget == null && constraintWidget2.mBottom.mTarget == null) {
                    if (z) {
                        resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode4.dependsOn(resolutionNode2, height);
                    }
                } else if (constraintWidget2.mTop.mTarget == null || constraintWidget2.mBottom.mTarget != null) {
                    if (constraintWidget2.mTop.mTarget != null || constraintWidget2.mBottom.mTarget == null) {
                        if (constraintWidget2.mTop.mTarget != null && constraintWidget2.mBottom.mTarget != null) {
                            if (z) {
                                constraintWidget.getResolutionHeight().addDependent(resolutionNode2);
                                constraintWidget.getResolutionWidth().addDependent(resolutionNode4);
                            }
                            if (constraintWidget2.mDimensionRatio == BitmapDescriptorFactory.HUE_RED) {
                                resolutionNode2.setType(3);
                                resolutionNode4.setType(3);
                                resolutionNode2.setOpposite(resolutionNode4, BitmapDescriptorFactory.HUE_RED);
                                resolutionNode4.setOpposite(resolutionNode2, BitmapDescriptorFactory.HUE_RED);
                                return;
                            }
                            resolutionNode2.setType(2);
                            resolutionNode4.setType(2);
                            resolutionNode2.setOpposite(resolutionNode4, (float) (-height));
                            resolutionNode4.setOpposite(resolutionNode2, (float) height);
                            constraintWidget2.setHeight(height);
                            if (constraintWidget2.mBaselineDistance > 0) {
                                constraintWidget2.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget2.mBaselineDistance);
                            }
                        }
                    } else if (z) {
                        resolutionNode2.dependsOn(resolutionNode4, -1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode2.dependsOn(resolutionNode4, -height);
                    }
                } else if (z) {
                    resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                } else {
                    resolutionNode4.dependsOn(resolutionNode2, height);
                }
            }
        }
    }

    static boolean applyChainOptimized(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i, int i2, ChainHead chainHead) {
        boolean z;
        boolean z2;
        boolean z3;
        float f;
        boolean z4;
        float f2;
        float f3;
        ConstraintWidget constraintWidget;
        boolean z5;
        LinearSystem linearSystem2 = linearSystem;
        int i3 = i;
        ChainHead chainHead2 = chainHead;
        ConstraintWidget constraintWidget2 = chainHead2.mFirst;
        ConstraintWidget constraintWidget3 = chainHead2.mLast;
        ConstraintWidget constraintWidget4 = chainHead2.mFirstVisibleWidget;
        ConstraintWidget constraintWidget5 = chainHead2.mLastVisibleWidget;
        ConstraintWidget constraintWidget6 = chainHead2.mHead;
        float f4 = chainHead2.mTotalWeight;
        ConstraintWidget constraintWidget7 = chainHead2.mFirstMatchConstraintWidget;
        ConstraintWidget constraintWidget8 = chainHead2.mLastMatchConstraintWidget;
        DimensionBehaviour dimensionBehaviour = constraintWidgetContainer.mListDimensionBehaviors[i3];
        DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.WRAP_CONTENT;
        if (i3 == 0) {
            z2 = constraintWidget6.mHorizontalChainStyle == 0;
            z = constraintWidget6.mHorizontalChainStyle == 1;
            z3 = constraintWidget6.mHorizontalChainStyle == 2;
        } else {
            z2 = constraintWidget6.mVerticalChainStyle == 0;
            z = constraintWidget6.mVerticalChainStyle == 1;
            z3 = constraintWidget6.mVerticalChainStyle == 2;
        }
        ConstraintWidget constraintWidget9 = constraintWidget2;
        int i4 = 0;
        boolean z6 = false;
        int i5 = 0;
        float f5 = BitmapDescriptorFactory.HUE_RED;
        float f6 = BitmapDescriptorFactory.HUE_RED;
        while (!z6) {
            if (constraintWidget9.getVisibility() != 8) {
                i5++;
                if (i3 == 0) {
                    f5 += (float) constraintWidget9.getWidth();
                } else {
                    f5 += (float) constraintWidget9.getHeight();
                }
                if (constraintWidget9 != constraintWidget4) {
                    f5 += (float) constraintWidget9.mListAnchors[i2].getMargin();
                }
                if (constraintWidget9 != constraintWidget5) {
                    f5 += (float) constraintWidget9.mListAnchors[i2 + 1].getMargin();
                }
                f6 = f6 + ((float) constraintWidget9.mListAnchors[i2].getMargin()) + ((float) constraintWidget9.mListAnchors[i2 + 1].getMargin());
            }
            ConstraintAnchor constraintAnchor = constraintWidget9.mListAnchors[i2];
            if (constraintWidget9.getVisibility() != 8 && constraintWidget9.mListDimensionBehaviors[i3] == DimensionBehaviour.MATCH_CONSTRAINT) {
                i4++;
                if (i3 != 0) {
                    z5 = false;
                    if (!(constraintWidget9.mMatchConstraintDefaultHeight == 0 && constraintWidget9.mMatchConstraintMinHeight == 0 && constraintWidget9.mMatchConstraintMaxHeight == 0)) {
                        return false;
                    }
                } else if (constraintWidget9.mMatchConstraintDefaultWidth != 0) {
                    return false;
                } else {
                    z5 = false;
                    if (!(constraintWidget9.mMatchConstraintMinWidth == 0 && constraintWidget9.mMatchConstraintMaxWidth == 0)) {
                        return false;
                    }
                }
                if (constraintWidget9.mDimensionRatio != BitmapDescriptorFactory.HUE_RED) {
                    return z5;
                }
            }
            ConstraintAnchor constraintAnchor2 = constraintWidget9.mListAnchors[i2 + 1].mTarget;
            if (constraintAnchor2 != null) {
                ConstraintWidget constraintWidget10 = constraintAnchor2.mOwner;
                constraintWidget = (constraintWidget10.mListAnchors[i2].mTarget == null || constraintWidget10.mListAnchors[i2].mTarget.mOwner != constraintWidget9) ? null : constraintWidget10;
            } else {
                constraintWidget = null;
            }
            if (constraintWidget != null) {
                constraintWidget9 = constraintWidget;
            } else {
                z6 = true;
            }
        }
        ResolutionAnchor resolutionNode = constraintWidget2.mListAnchors[i2].getResolutionNode();
        int i6 = i2 + 1;
        ResolutionAnchor resolutionNode2 = constraintWidget3.mListAnchors[i6].getResolutionNode();
        if (resolutionNode.target == null || resolutionNode2.target == null) {
            return false;
        }
        ConstraintWidget constraintWidget11 = constraintWidget2;
        if (resolutionNode.target.state != 1 || resolutionNode2.target.state != 1) {
            return false;
        }
        if (i4 > 0 && i4 != i5) {
            return false;
        }
        if (z3 || z2 || z) {
            f = constraintWidget4 != null ? (float) constraintWidget4.mListAnchors[i2].getMargin() : BitmapDescriptorFactory.HUE_RED;
            if (constraintWidget5 != null) {
                f += (float) constraintWidget5.mListAnchors[i6].getMargin();
            }
        } else {
            f = BitmapDescriptorFactory.HUE_RED;
        }
        float f7 = resolutionNode.target.resolvedOffset;
        float f8 = resolutionNode2.target.resolvedOffset;
        float f9 = f7 < f8 ? (f8 - f7) - f5 : (f7 - f8) - f5;
        if (i4 <= 0 || i4 != i5) {
            if (f9 < BitmapDescriptorFactory.HUE_RED) {
                z3 = true;
                z2 = false;
                z = false;
            }
            if (z3) {
                ConstraintWidget constraintWidget12 = constraintWidget11;
                float biasPercent = f7 + ((f9 - f) * constraintWidget12.getBiasPercent(i3));
                while (constraintWidget12 != null) {
                    if (LinearSystem.sMetrics != null) {
                        Metrics metrics = LinearSystem.sMetrics;
                        metrics.nonresolvedWidgets--;
                        Metrics metrics2 = LinearSystem.sMetrics;
                        metrics2.resolvedWidgets++;
                        Metrics metrics3 = LinearSystem.sMetrics;
                        metrics3.chainConnectionResolved++;
                    }
                    ConstraintWidget constraintWidget13 = constraintWidget12.mNextChainWidget[i3];
                    if (constraintWidget13 != null || constraintWidget12 == constraintWidget3) {
                        if (i3 == 0) {
                            f3 = (float) constraintWidget12.getWidth();
                        } else {
                            f3 = (float) constraintWidget12.getHeight();
                        }
                        float margin = biasPercent + ((float) constraintWidget12.mListAnchors[i2].getMargin());
                        constraintWidget12.mListAnchors[i2].getResolutionNode().resolve(resolutionNode.resolvedTarget, margin);
                        float f10 = margin + f3;
                        constraintWidget12.mListAnchors[i6].getResolutionNode().resolve(resolutionNode.resolvedTarget, f10);
                        constraintWidget12.mListAnchors[i2].getResolutionNode().addResolvedValue(linearSystem2);
                        constraintWidget12.mListAnchors[i6].getResolutionNode().addResolvedValue(linearSystem2);
                        biasPercent = f10 + ((float) constraintWidget12.mListAnchors[i6].getMargin());
                    }
                    constraintWidget12 = constraintWidget13;
                }
                z4 = true;
            } else {
                ConstraintWidget constraintWidget14 = constraintWidget11;
                if (z2 || z) {
                    if (z2) {
                        f9 -= f;
                    } else if (z) {
                        f9 -= f;
                    }
                    float f11 = f9 / ((float) (i5 + 1));
                    if (z) {
                        f11 = i5 > 1 ? f9 / ((float) (i5 - 1)) : f9 / 2.0f;
                    }
                    float f12 = constraintWidget14.getVisibility() != 8 ? f7 + f11 : f7;
                    if (z && i5 > 1) {
                        f12 = ((float) constraintWidget4.mListAnchors[i2].getMargin()) + f7;
                    }
                    if (z2 && constraintWidget4 != null) {
                        f12 += (float) constraintWidget4.mListAnchors[i2].getMargin();
                    }
                    while (constraintWidget14 != null) {
                        if (LinearSystem.sMetrics != null) {
                            Metrics metrics4 = LinearSystem.sMetrics;
                            metrics4.nonresolvedWidgets--;
                            Metrics metrics5 = LinearSystem.sMetrics;
                            metrics5.resolvedWidgets++;
                            Metrics metrics6 = LinearSystem.sMetrics;
                            metrics6.chainConnectionResolved++;
                        }
                        ConstraintWidget constraintWidget15 = constraintWidget14.mNextChainWidget[i3];
                        if (constraintWidget15 != null || constraintWidget14 == constraintWidget3) {
                            if (i3 == 0) {
                                f2 = (float) constraintWidget14.getWidth();
                            } else {
                                f2 = (float) constraintWidget14.getHeight();
                            }
                            if (constraintWidget14 != constraintWidget4) {
                                f12 += (float) constraintWidget14.mListAnchors[i2].getMargin();
                            }
                            constraintWidget14.mListAnchors[i2].getResolutionNode().resolve(resolutionNode.resolvedTarget, f12);
                            constraintWidget14.mListAnchors[i6].getResolutionNode().resolve(resolutionNode.resolvedTarget, f12 + f2);
                            constraintWidget14.mListAnchors[i2].getResolutionNode().addResolvedValue(linearSystem2);
                            constraintWidget14.mListAnchors[i6].getResolutionNode().addResolvedValue(linearSystem2);
                            f12 += f2 + ((float) constraintWidget14.mListAnchors[i6].getMargin());
                            if (constraintWidget15 != null) {
                                if (constraintWidget15.getVisibility() != 8) {
                                    f12 += f11;
                                }
                            }
                        }
                        constraintWidget14 = constraintWidget15;
                    }
                    z4 = true;
                } else {
                    z4 = true;
                }
            }
            return z4;
        } else if (constraintWidget9.getParent() != null && constraintWidget9.getParent().mListDimensionBehaviors[i3] == DimensionBehaviour.WRAP_CONTENT) {
            return false;
        } else {
            float f13 = (f9 + f5) - f6;
            ConstraintWidget constraintWidget16 = constraintWidget11;
            while (constraintWidget16 != null) {
                if (LinearSystem.sMetrics != null) {
                    Metrics metrics7 = LinearSystem.sMetrics;
                    metrics7.nonresolvedWidgets--;
                    Metrics metrics8 = LinearSystem.sMetrics;
                    metrics8.resolvedWidgets++;
                    Metrics metrics9 = LinearSystem.sMetrics;
                    metrics9.chainConnectionResolved++;
                }
                ConstraintWidget constraintWidget17 = constraintWidget16.mNextChainWidget[i3];
                if (constraintWidget17 != null || constraintWidget16 == constraintWidget3) {
                    float f14 = f13 / ((float) i4);
                    if (f4 > BitmapDescriptorFactory.HUE_RED) {
                        if (constraintWidget16.mWeight[i3] == -1.0f) {
                            f14 = BitmapDescriptorFactory.HUE_RED;
                        } else {
                            f14 = (constraintWidget16.mWeight[i3] * f13) / f4;
                        }
                    }
                    if (constraintWidget16.getVisibility() == 8) {
                        f14 = BitmapDescriptorFactory.HUE_RED;
                    }
                    float margin2 = f7 + ((float) constraintWidget16.mListAnchors[i2].getMargin());
                    constraintWidget16.mListAnchors[i2].getResolutionNode().resolve(resolutionNode.resolvedTarget, margin2);
                    float f15 = margin2 + f14;
                    constraintWidget16.mListAnchors[i6].getResolutionNode().resolve(resolutionNode.resolvedTarget, f15);
                    constraintWidget16.mListAnchors[i2].getResolutionNode().addResolvedValue(linearSystem2);
                    constraintWidget16.mListAnchors[i6].getResolutionNode().addResolvedValue(linearSystem2);
                    f7 = f15 + ((float) constraintWidget16.mListAnchors[i6].getMargin());
                }
                constraintWidget16 = constraintWidget17;
            }
            return true;
        }
    }

    static void setOptimizedWidget(ConstraintWidget constraintWidget, int i, int i2) {
        int i3 = i * 2;
        int i4 = i3 + 1;
        constraintWidget.mListAnchors[i3].getResolutionNode().resolvedTarget = constraintWidget.getParent().mLeft.getResolutionNode();
        constraintWidget.mListAnchors[i3].getResolutionNode().resolvedOffset = (float) i2;
        constraintWidget.mListAnchors[i3].getResolutionNode().state = 1;
        constraintWidget.mListAnchors[i4].getResolutionNode().resolvedTarget = constraintWidget.mListAnchors[i3].getResolutionNode();
        constraintWidget.mListAnchors[i4].getResolutionNode().resolvedOffset = (float) constraintWidget.getLength(i);
        constraintWidget.mListAnchors[i4].getResolutionNode().state = 1;
    }
}
