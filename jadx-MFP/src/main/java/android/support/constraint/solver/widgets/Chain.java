package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;

class Chain {
    Chain() {
    }

    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i) {
        int i2;
        ChainHead[] chainHeadArr;
        int i3;
        if (i == 0) {
            int i4 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i2 = i4;
            i3 = 0;
        } else {
            i3 = 2;
            int i5 = constraintWidgetContainer.mVerticalChainsSize;
            i2 = i5;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i6 = 0; i6 < i2; i6++) {
            ChainHead chainHead = chainHeadArr[i6];
            chainHead.define();
            if (!constraintWidgetContainer.optimizeFor(4)) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i3, chainHead);
            } else if (!Optimizer.applyChainOptimized(constraintWidgetContainer, linearSystem, i, i3, chainHead)) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i3, chainHead);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:207:0x03c6  */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x049d  */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x04d2  */
    /* JADX WARNING: Removed duplicated region for block: B:269:0x04f7  */
    /* JADX WARNING: Removed duplicated region for block: B:270:0x04fc  */
    /* JADX WARNING: Removed duplicated region for block: B:273:0x0502  */
    /* JADX WARNING: Removed duplicated region for block: B:274:0x0507  */
    /* JADX WARNING: Removed duplicated region for block: B:276:0x050b  */
    /* JADX WARNING: Removed duplicated region for block: B:281:0x051c  */
    /* JADX WARNING: Removed duplicated region for block: B:283:0x051f  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0167  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0185  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer r35, android.support.constraint.solver.LinearSystem r36, int r37, int r38, android.support.constraint.solver.widgets.ChainHead r39) {
        /*
            r0 = r35
            r9 = r36
            r1 = r39
            android.support.constraint.solver.widgets.ConstraintWidget r10 = r1.mFirst
            android.support.constraint.solver.widgets.ConstraintWidget r11 = r1.mLast
            android.support.constraint.solver.widgets.ConstraintWidget r12 = r1.mFirstVisibleWidget
            android.support.constraint.solver.widgets.ConstraintWidget r13 = r1.mLastVisibleWidget
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r1.mHead
            float r3 = r1.mTotalWeight
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r1.mFirstMatchConstraintWidget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r1.mLastMatchConstraintWidget
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r0.mListDimensionBehaviors
            r4 = r4[r37]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r7 = 1
            if (r4 != r5) goto L_0x0021
            r4 = 1
            goto L_0x0022
        L_0x0021:
            r4 = 0
        L_0x0022:
            r5 = 2
            if (r37 != 0) goto L_0x0041
            int r8 = r2.mHorizontalChainStyle
            if (r8 != 0) goto L_0x002b
            r8 = 1
            goto L_0x002c
        L_0x002b:
            r8 = 0
        L_0x002c:
            int r14 = r2.mHorizontalChainStyle
            if (r14 != r7) goto L_0x0032
            r14 = 1
            goto L_0x0033
        L_0x0032:
            r14 = 0
        L_0x0033:
            int r15 = r2.mHorizontalChainStyle
            if (r15 != r5) goto L_0x0039
            r5 = 1
            goto L_0x003a
        L_0x0039:
            r5 = 0
        L_0x003a:
            r15 = r8
            r8 = r10
            r16 = r14
            r14 = r5
            r5 = 0
            goto L_0x005c
        L_0x0041:
            int r8 = r2.mVerticalChainStyle
            if (r8 != 0) goto L_0x0047
            r8 = 1
            goto L_0x0048
        L_0x0047:
            r8 = 0
        L_0x0048:
            int r14 = r2.mVerticalChainStyle
            if (r14 != r7) goto L_0x004e
            r14 = 1
            goto L_0x004f
        L_0x004e:
            r14 = 0
        L_0x004f:
            int r15 = r2.mVerticalChainStyle
            if (r15 != r5) goto L_0x0055
            r5 = 1
            goto L_0x0056
        L_0x0055:
            r5 = 0
        L_0x0056:
            r15 = r8
            r8 = r10
            r16 = r14
            r14 = r5
            r5 = 0
        L_0x005c:
            r21 = 0
            if (r5 != 0) goto L_0x013a
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r8.mListAnchors
            r7 = r7[r38]
            if (r4 != 0) goto L_0x006c
            if (r14 == 0) goto L_0x0069
            goto L_0x006c
        L_0x0069:
            r23 = 4
            goto L_0x006e
        L_0x006c:
            r23 = 1
        L_0x006e:
            int r24 = r7.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r7.mTarget
            if (r6 == 0) goto L_0x0083
            if (r8 == r10) goto L_0x0083
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r7.mTarget
            int r6 = r6.getMargin()
            int r24 = r24 + r6
            r6 = r24
            goto L_0x0085
        L_0x0083:
            r6 = r24
        L_0x0085:
            if (r14 == 0) goto L_0x0091
            if (r8 == r10) goto L_0x0091
            if (r8 == r12) goto L_0x0091
            r24 = r3
            r23 = r5
            r3 = 6
            goto L_0x00a1
        L_0x0091:
            if (r15 == 0) goto L_0x009b
            if (r4 == 0) goto L_0x009b
            r24 = r3
            r23 = r5
            r3 = 4
            goto L_0x00a1
        L_0x009b:
            r24 = r3
            r3 = r23
            r23 = r5
        L_0x00a1:
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r7.mTarget
            if (r5 == 0) goto L_0x00ce
            if (r8 != r12) goto L_0x00b6
            android.support.constraint.solver.SolverVariable r5 = r7.mSolverVariable
            r25 = r15
            android.support.constraint.solver.widgets.ConstraintAnchor r15 = r7.mTarget
            android.support.constraint.solver.SolverVariable r15 = r15.mSolverVariable
            r26 = r2
            r2 = 5
            r9.addGreaterThan(r5, r15, r6, r2)
            goto L_0x00c4
        L_0x00b6:
            r26 = r2
            r25 = r15
            android.support.constraint.solver.SolverVariable r2 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r7.mTarget
            android.support.constraint.solver.SolverVariable r5 = r5.mSolverVariable
            r15 = 6
            r9.addGreaterThan(r2, r5, r6, r15)
        L_0x00c4:
            android.support.constraint.solver.SolverVariable r2 = r7.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r7.mTarget
            android.support.constraint.solver.SolverVariable r5 = r5.mSolverVariable
            r9.addEquality(r2, r5, r6, r3)
            goto L_0x00d2
        L_0x00ce:
            r26 = r2
            r25 = r15
        L_0x00d2:
            if (r4 == 0) goto L_0x0109
            int r2 = r8.getVisibility()
            r3 = 8
            if (r2 == r3) goto L_0x00f8
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r8.mListDimensionBehaviors
            r2 = r2[r37]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r3) goto L_0x00f8
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            int r3 = r38 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r8.mListAnchors
            r3 = r3[r38]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            r5 = 0
            r6 = 5
            r9.addGreaterThan(r2, r3, r5, r6)
            goto L_0x00f9
        L_0x00f8:
            r5 = 0
        L_0x00f9:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            r2 = r2[r38]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r0.mListAnchors
            r3 = r3[r38]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            r6 = 6
            r9.addGreaterThan(r2, r3, r5, r6)
        L_0x0109:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            int r3 = r38 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x012a
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r2.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x012a
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            if (r3 == r8) goto L_0x0128
            goto L_0x012a
        L_0x0128:
            r21 = r2
        L_0x012a:
            if (r21 == 0) goto L_0x0131
            r8 = r21
            r5 = r23
            goto L_0x0132
        L_0x0131:
            r5 = 1
        L_0x0132:
            r3 = r24
            r15 = r25
            r2 = r26
            goto L_0x005c
        L_0x013a:
            r26 = r2
            r24 = r3
            r25 = r15
            if (r13 == 0) goto L_0x0164
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r11.mListAnchors
            int r3 = r38 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0164
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r5 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r11.mListAnchors
            r3 = r6[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            int r2 = r2.getMargin()
            int r2 = -r2
            r6 = 5
            r9.addLowerThan(r5, r3, r2, r6)
            goto L_0x0165
        L_0x0164:
            r6 = 5
        L_0x0165:
            if (r4 == 0) goto L_0x0181
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r0.mListAnchors
            int r2 = r38 + 1
            r0 = r0[r2]
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r2]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r2 = r4[r2]
            int r2 = r2.getMargin()
            r4 = 6
            r9.addGreaterThan(r0, r3, r2, r4)
        L_0x0181:
            java.util.ArrayList<android.support.constraint.solver.widgets.ConstraintWidget> r0 = r1.mWeightedMatchConstraintsWidgets
            if (r0 == 0) goto L_0x023a
            int r2 = r0.size()
            r3 = 1
            if (r2 <= r3) goto L_0x023a
            boolean r4 = r1.mHasUndefinedWeights
            if (r4 == 0) goto L_0x0198
            boolean r4 = r1.mHasComplexMatchWeights
            if (r4 != 0) goto L_0x0198
            int r4 = r1.mWidgetsMatchCount
            float r4 = (float) r4
            goto L_0x019a
        L_0x0198:
            r4 = r24
        L_0x019a:
            r5 = 0
            r8 = r21
            r7 = 0
            r28 = 0
        L_0x01a0:
            if (r7 >= r2) goto L_0x023a
            java.lang.Object r15 = r0.get(r7)
            android.support.constraint.solver.widgets.ConstraintWidget r15 = (android.support.constraint.solver.widgets.ConstraintWidget) r15
            float[] r3 = r15.mWeight
            r3 = r3[r37]
            int r23 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r23 >= 0) goto L_0x01cf
            boolean r3 = r1.mHasComplexMatchWeights
            if (r3 == 0) goto L_0x01ca
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r15.mListAnchors
            int r23 = r38 + 1
            r3 = r3[r23]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r15 = r15.mListAnchors
            r15 = r15[r38]
            android.support.constraint.solver.SolverVariable r15 = r15.mSolverVariable
            r5 = 4
            r6 = 0
            r9.addEquality(r3, r15, r6, r5)
            r5 = 0
            r6 = 6
            goto L_0x01e8
        L_0x01ca:
            r5 = 4
            r3 = 1065353216(0x3f800000, float:1.0)
            r6 = 0
            goto L_0x01d1
        L_0x01cf:
            r5 = 4
            r6 = 0
        L_0x01d1:
            int r20 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r20 != 0) goto L_0x01ed
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r15.mListAnchors
            int r20 = r38 + 1
            r3 = r3[r20]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r15 = r15.mListAnchors
            r15 = r15[r38]
            android.support.constraint.solver.SolverVariable r15 = r15.mSolverVariable
            r5 = 0
            r6 = 6
            r9.addEquality(r3, r15, r5, r6)
        L_0x01e8:
            r24 = r0
            r22 = r2
            goto L_0x022f
        L_0x01ed:
            r5 = 0
            r6 = 6
            if (r8 == 0) goto L_0x0228
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r8.mListAnchors
            r5 = r5[r38]
            android.support.constraint.solver.SolverVariable r5 = r5.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r8.mListAnchors
            int r22 = r38 + 1
            r8 = r8[r22]
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r15.mListAnchors
            r6 = r6[r38]
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
            r24 = r0
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r15.mListAnchors
            r0 = r0[r22]
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            r22 = r2
            android.support.constraint.solver.ArrayRow r2 = r36.createRow()
            r27 = r2
            r29 = r4
            r30 = r3
            r31 = r5
            r32 = r8
            r33 = r6
            r34 = r0
            r27.createRowEqualMatchDimensions(r28, r29, r30, r31, r32, r33, r34)
            r9.addConstraint(r2)
            goto L_0x022c
        L_0x0228:
            r24 = r0
            r22 = r2
        L_0x022c:
            r28 = r3
            r8 = r15
        L_0x022f:
            int r7 = r7 + 1
            r2 = r22
            r0 = r24
            r3 = 1
            r5 = 0
            r6 = 5
            goto L_0x01a0
        L_0x023a:
            if (r12 == 0) goto L_0x02a5
            if (r12 == r13) goto L_0x0240
            if (r14 == 0) goto L_0x02a5
        L_0x0240:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r10.mListAnchors
            r0 = r0[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r11.mListAnchors
            int r2 = r38 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r10.mListAnchors
            r3 = r3[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x025b
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r10.mListAnchors
            r3 = r3[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x025d
        L_0x025b:
            r3 = r21
        L_0x025d:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r4 = r4[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x026f
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r4 = r4[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            r5 = r4
            goto L_0x0271
        L_0x026f:
            r5 = r21
        L_0x0271:
            if (r12 != r13) goto L_0x027b
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r2]
        L_0x027b:
            if (r3 == 0) goto L_0x04e3
            if (r5 == 0) goto L_0x04e3
            if (r37 != 0) goto L_0x0287
            r2 = r26
            float r2 = r2.mHorizontalBiasPercent
            r4 = r2
            goto L_0x028c
        L_0x0287:
            r2 = r26
            float r2 = r2.mVerticalBiasPercent
            r4 = r2
        L_0x028c:
            int r6 = r0.getMargin()
            int r7 = r1.getMargin()
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r8 = r1.mSolverVariable
            r10 = 5
            r0 = r36
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = r8
            r8 = r10
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x04e3
        L_0x02a5:
            if (r25 == 0) goto L_0x03a9
            if (r12 == 0) goto L_0x03a9
            int r0 = r1.mWidgetsMatchCount
            if (r0 <= 0) goto L_0x02b6
            int r0 = r1.mWidgetsCount
            int r1 = r1.mWidgetsMatchCount
            if (r0 != r1) goto L_0x02b6
            r17 = 1
            goto L_0x02b8
        L_0x02b6:
            r17 = 0
        L_0x02b8:
            r14 = r12
            r15 = r14
        L_0x02ba:
            if (r14 == 0) goto L_0x04e3
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r14.mNextChainWidget
            r0 = r0[r37]
            r8 = r0
        L_0x02c1:
            if (r8 == 0) goto L_0x02d0
            int r0 = r8.getVisibility()
            r7 = 8
            if (r0 != r7) goto L_0x02d2
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r8.mNextChainWidget
            r8 = r0[r37]
            goto L_0x02c1
        L_0x02d0:
            r7 = 8
        L_0x02d2:
            if (r8 != 0) goto L_0x02df
            if (r14 != r13) goto L_0x02d7
            goto L_0x02df
        L_0x02d7:
            r18 = r8
            r19 = 6
            r20 = 4
            goto L_0x039c
        L_0x02df:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r14.mListAnchors
            r0 = r0[r38]
            android.support.constraint.solver.SolverVariable r1 = r0.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            if (r2 == 0) goto L_0x02ee
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x02f0
        L_0x02ee:
            r2 = r21
        L_0x02f0:
            if (r15 == r14) goto L_0x02fb
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r15.mListAnchors
            int r3 = r38 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x0312
        L_0x02fb:
            if (r14 != r12) goto L_0x0312
            if (r15 != r14) goto L_0x0312
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r10.mListAnchors
            r2 = r2[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0310
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r10.mListAnchors
            r2 = r2[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            goto L_0x0312
        L_0x0310:
            r2 = r21
        L_0x0312:
            int r0 = r0.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r14.mListAnchors
            int r4 = r38 + 1
            r3 = r3[r4]
            int r3 = r3.getMargin()
            if (r8 == 0) goto L_0x032f
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r8.mListAnchors
            r5 = r5[r38]
            android.support.constraint.solver.SolverVariable r6 = r5.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r14.mListAnchors
            r7 = r7[r4]
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            goto L_0x0342
        L_0x032f:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r11.mListAnchors
            r5 = r5[r4]
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x033a
            android.support.constraint.solver.SolverVariable r6 = r5.mSolverVariable
            goto L_0x033c
        L_0x033a:
            r6 = r21
        L_0x033c:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r14.mListAnchors
            r7 = r7[r4]
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
        L_0x0342:
            if (r5 == 0) goto L_0x0349
            int r5 = r5.getMargin()
            int r3 = r3 + r5
        L_0x0349:
            if (r15 == 0) goto L_0x0354
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r15.mListAnchors
            r5 = r5[r4]
            int r5 = r5.getMargin()
            int r0 = r0 + r5
        L_0x0354:
            if (r1 == 0) goto L_0x0396
            if (r2 == 0) goto L_0x0396
            if (r6 == 0) goto L_0x0396
            if (r7 == 0) goto L_0x0396
            if (r14 != r12) goto L_0x0368
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r38]
            int r0 = r0.getMargin()
            r5 = r0
            goto L_0x0369
        L_0x0368:
            r5 = r0
        L_0x0369:
            if (r14 != r13) goto L_0x0376
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r4]
            int r0 = r0.getMargin()
            r18 = r0
            goto L_0x0378
        L_0x0376:
            r18 = r3
        L_0x0378:
            if (r17 == 0) goto L_0x037d
            r22 = 6
            goto L_0x037f
        L_0x037d:
            r22 = 4
        L_0x037f:
            r4 = 1056964608(0x3f000000, float:0.5)
            r0 = r36
            r3 = r5
            r20 = 4
            r5 = r6
            r23 = 6
            r6 = r7
            r19 = 6
            r7 = r18
            r18 = r8
            r8 = r22
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x039c
        L_0x0396:
            r18 = r8
            r19 = 6
            r20 = 4
        L_0x039c:
            int r0 = r14.getVisibility()
            r8 = 8
            if (r0 == r8) goto L_0x03a5
            r15 = r14
        L_0x03a5:
            r14 = r18
            goto L_0x02ba
        L_0x03a9:
            r8 = 8
            r19 = 6
            r20 = 4
            if (r16 == 0) goto L_0x04e3
            if (r12 == 0) goto L_0x04e3
            int r0 = r1.mWidgetsMatchCount
            if (r0 <= 0) goto L_0x03c0
            int r0 = r1.mWidgetsCount
            int r1 = r1.mWidgetsMatchCount
            if (r0 != r1) goto L_0x03c0
            r17 = 1
            goto L_0x03c2
        L_0x03c0:
            r17 = 0
        L_0x03c2:
            r14 = r12
            r15 = r14
        L_0x03c4:
            if (r14 == 0) goto L_0x0485
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r14.mNextChainWidget
            r0 = r0[r37]
        L_0x03ca:
            if (r0 == 0) goto L_0x03d7
            int r1 = r0.getVisibility()
            if (r1 != r8) goto L_0x03d7
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r0.mNextChainWidget
            r0 = r0[r37]
            goto L_0x03ca
        L_0x03d7:
            if (r14 == r12) goto L_0x0472
            if (r14 == r13) goto L_0x0472
            if (r0 == 0) goto L_0x0472
            if (r0 != r13) goto L_0x03e2
            r7 = r21
            goto L_0x03e3
        L_0x03e2:
            r7 = r0
        L_0x03e3:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r14.mListAnchors
            r0 = r0[r38]
            android.support.constraint.solver.SolverVariable r1 = r0.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            if (r2 == 0) goto L_0x03f1
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r0.mTarget
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
        L_0x03f1:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r15.mListAnchors
            int r3 = r38 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            int r0 = r0.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r14.mListAnchors
            r4 = r4[r3]
            int r4 = r4.getMargin()
            if (r7 == 0) goto L_0x0419
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r7.mListAnchors
            r5 = r5[r38]
            android.support.constraint.solver.SolverVariable r6 = r5.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r5.mTarget
            if (r8 == 0) goto L_0x0416
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r5.mTarget
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            goto L_0x042c
        L_0x0416:
            r8 = r21
            goto L_0x042c
        L_0x0419:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r14.mListAnchors
            r5 = r5[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x0424
            android.support.constraint.solver.SolverVariable r6 = r5.mSolverVariable
            goto L_0x0426
        L_0x0424:
            r6 = r21
        L_0x0426:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r14.mListAnchors
            r8 = r8[r3]
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
        L_0x042c:
            if (r5 == 0) goto L_0x0436
            int r5 = r5.getMargin()
            int r4 = r4 + r5
            r18 = r4
            goto L_0x0438
        L_0x0436:
            r18 = r4
        L_0x0438:
            if (r15 == 0) goto L_0x0445
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r15.mListAnchors
            r3 = r4[r3]
            int r3 = r3.getMargin()
            int r0 = r0 + r3
            r3 = r0
            goto L_0x0446
        L_0x0445:
            r3 = r0
        L_0x0446:
            if (r17 == 0) goto L_0x044b
            r22 = 6
            goto L_0x044d
        L_0x044b:
            r22 = 4
        L_0x044d:
            if (r1 == 0) goto L_0x0469
            if (r2 == 0) goto L_0x0469
            if (r6 == 0) goto L_0x0469
            if (r8 == 0) goto L_0x0469
            r4 = 1056964608(0x3f000000, float:0.5)
            r0 = r36
            r5 = r6
            r6 = r8
            r23 = r7
            r7 = r18
            r18 = r15
            r15 = 8
            r8 = r22
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x046f
        L_0x0469:
            r23 = r7
            r18 = r15
            r15 = 8
        L_0x046f:
            r0 = r23
            goto L_0x0476
        L_0x0472:
            r18 = r15
            r15 = 8
        L_0x0476:
            int r1 = r14.getVisibility()
            if (r1 == r15) goto L_0x047d
            goto L_0x047f
        L_0x047d:
            r14 = r18
        L_0x047f:
            r15 = r14
            r8 = 8
            r14 = r0
            goto L_0x03c4
        L_0x0485:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r10.mListAnchors
            r1 = r1[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            int r3 = r38 + 1
            r10 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r11.mListAnchors
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r14 = r2.mTarget
            if (r1 == 0) goto L_0x04d2
            if (r12 == r13) goto L_0x04ac
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            int r0 = r0.getMargin()
            r15 = 5
            r9.addEquality(r2, r1, r0, r15)
            goto L_0x04d3
        L_0x04ac:
            r15 = 5
            if (r14 == 0) goto L_0x04d3
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r3 = r1.mSolverVariable
            int r4 = r0.getMargin()
            r5 = 1056964608(0x3f000000, float:0.5)
            android.support.constraint.solver.SolverVariable r6 = r10.mSolverVariable
            android.support.constraint.solver.SolverVariable r7 = r14.mSolverVariable
            int r8 = r10.getMargin()
            r17 = 5
            r0 = r36
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r17
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x04d3
        L_0x04d2:
            r15 = 5
        L_0x04d3:
            if (r14 == 0) goto L_0x04e3
            if (r12 == r13) goto L_0x04e3
            android.support.constraint.solver.SolverVariable r0 = r10.mSolverVariable
            android.support.constraint.solver.SolverVariable r1 = r14.mSolverVariable
            int r2 = r10.getMargin()
            int r2 = -r2
            r9.addEquality(r0, r1, r2, r15)
        L_0x04e3:
            if (r25 != 0) goto L_0x04e7
            if (r16 == 0) goto L_0x054c
        L_0x04e7:
            if (r12 == 0) goto L_0x054c
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            int r2 = r38 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mTarget
            if (r3 == 0) goto L_0x04fc
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x04fe
        L_0x04fc:
            r3 = r21
        L_0x04fe:
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r1.mTarget
            if (r4 == 0) goto L_0x0507
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r1.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            goto L_0x0509
        L_0x0507:
            r4 = r21
        L_0x0509:
            if (r11 == r13) goto L_0x051c
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r4 = r4[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r5 = r4.mTarget
            if (r5 == 0) goto L_0x0518
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            goto L_0x051a
        L_0x0518:
            r4 = r21
        L_0x051a:
            r5 = r4
            goto L_0x051d
        L_0x051c:
            r5 = r4
        L_0x051d:
            if (r12 != r13) goto L_0x0527
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r38]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r2]
        L_0x0527:
            if (r3 == 0) goto L_0x054c
            if (r5 == 0) goto L_0x054c
            r4 = 1056964608(0x3f000000, float:0.5)
            int r6 = r0.getMargin()
            if (r13 != 0) goto L_0x0534
            goto L_0x0535
        L_0x0534:
            r11 = r13
        L_0x0535:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r11.mListAnchors
            r2 = r7[r2]
            int r7 = r2.getMargin()
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r8 = r1.mSolverVariable
            r10 = 5
            r0 = r36
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = r8
            r8 = r10
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x054c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Chain.applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ChainHead):void");
    }
}
