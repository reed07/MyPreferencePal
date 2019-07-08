package com.myfitnesspal.feature.walkthrough.util;

import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtilImpl.HideAnimationType;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtilImpl.ShowAnimationType;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtilImpl.WalkthroughType;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.ReturningFunction0;

public interface WalkthroughUtil {
    void hide(View view);

    void hide(View view, HideAnimationType hideAnimationType, Function1<View> function1);

    void initialize(Function1<View> function1, View view, int i, int i2);

    void initialize(ReturningFunction0<String> returningFunction0, View view, int i, int i2);

    void initialize(ReturningFunction0<String> returningFunction0, View view, int i, int i2, boolean z, boolean z2);

    void showDescription(boolean z);

    void showDiaryWalkthrough(View view, Function2<View, WalkthroughType> function2);

    void showNewMealPickerWalkthrough(View view, Function2<View, WalkthroughType> function2, OnItemClickListener onItemClickListener);

    void showWalkthrough(View view, WalkthroughType walkthroughType, Function2<View, WalkthroughType> function2);

    void showWalkthrough(View view, WalkthroughType walkthroughType, Function2<View, WalkthroughType> function2, ShowAnimationType showAnimationType);

    void update(int i, int i2, boolean z);
}
