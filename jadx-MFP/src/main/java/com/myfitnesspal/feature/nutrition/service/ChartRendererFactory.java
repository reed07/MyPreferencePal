package com.myfitnesspal.feature.nutrition.service;

import android.content.Context;
import com.myfitnesspal.feature.nutrition.service.renderer.CoreChartRendererBaseConstructorArgs;
import dagger.Lazy;

public class ChartRendererFactory {
    private Context context;
    private Lazy<CoreChartRendererBaseConstructorArgs> coreChartRendererBaseConstructorArgs;

    public ChartRendererFactory(Context context2, Lazy<CoreChartRendererBaseConstructorArgs> lazy) {
        this.context = context2;
        this.coreChartRendererBaseConstructorArgs = lazy;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.feature.nutrition.service.CoreChartRenderer getChartRenderer(java.lang.String r3) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            switch(r0) {
                case -1997878713: goto L_0x0030;
                case -104321242: goto L_0x0026;
                case 875663799: goto L_0x001c;
                case 1737874764: goto L_0x0012;
                case 1933126767: goto L_0x0008;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x003a
        L_0x0008:
            java.lang.String r0 = "SingleNutrient"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x003a
            r3 = 3
            goto L_0x003b
        L_0x0012:
            java.lang.String r0 = "Nutrients"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x003a
            r3 = 1
            goto L_0x003b
        L_0x001c:
            java.lang.String r0 = "FoodLists"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x003a
            r3 = 4
            goto L_0x003b
        L_0x0026:
            java.lang.String r0 = "Calories"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x003a
            r3 = 0
            goto L_0x003b
        L_0x0030:
            java.lang.String r0 = "Macros"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x003a
            r3 = 2
            goto L_0x003b
        L_0x003a:
            r3 = -1
        L_0x003b:
            switch(r3) {
                case 0: goto L_0x0068;
                case 1: goto L_0x005e;
                case 2: goto L_0x0054;
                case 3: goto L_0x004a;
                case 4: goto L_0x0040;
                default: goto L_0x003e;
            }
        L_0x003e:
            r3 = 0
            return r3
        L_0x0040:
            com.myfitnesspal.feature.nutrition.service.renderer.CompleteFoodListsChartRendererImpl r3 = new com.myfitnesspal.feature.nutrition.service.renderer.CompleteFoodListsChartRendererImpl
            android.content.Context r0 = r2.context
            dagger.Lazy<com.myfitnesspal.feature.nutrition.service.renderer.CoreChartRendererBaseConstructorArgs> r1 = r2.coreChartRendererBaseConstructorArgs
            r3.<init>(r0, r1)
            return r3
        L_0x004a:
            com.myfitnesspal.feature.nutrition.service.renderer.SingleNutrientChartRendererImpl r3 = new com.myfitnesspal.feature.nutrition.service.renderer.SingleNutrientChartRendererImpl
            android.content.Context r0 = r2.context
            dagger.Lazy<com.myfitnesspal.feature.nutrition.service.renderer.CoreChartRendererBaseConstructorArgs> r1 = r2.coreChartRendererBaseConstructorArgs
            r3.<init>(r0, r1)
            return r3
        L_0x0054:
            com.myfitnesspal.feature.nutrition.service.renderer.MacrosChartRendererImpl r3 = new com.myfitnesspal.feature.nutrition.service.renderer.MacrosChartRendererImpl
            android.content.Context r0 = r2.context
            dagger.Lazy<com.myfitnesspal.feature.nutrition.service.renderer.CoreChartRendererBaseConstructorArgs> r1 = r2.coreChartRendererBaseConstructorArgs
            r3.<init>(r0, r1)
            return r3
        L_0x005e:
            com.myfitnesspal.feature.nutrition.service.renderer.NutrientsChartRendererImpl r3 = new com.myfitnesspal.feature.nutrition.service.renderer.NutrientsChartRendererImpl
            android.content.Context r0 = r2.context
            dagger.Lazy<com.myfitnesspal.feature.nutrition.service.renderer.CoreChartRendererBaseConstructorArgs> r1 = r2.coreChartRendererBaseConstructorArgs
            r3.<init>(r0, r1)
            return r3
        L_0x0068:
            com.myfitnesspal.feature.nutrition.service.renderer.CaloriesChartRendererImpl r3 = new com.myfitnesspal.feature.nutrition.service.renderer.CaloriesChartRendererImpl
            android.content.Context r0 = r2.context
            dagger.Lazy<com.myfitnesspal.feature.nutrition.service.renderer.CoreChartRendererBaseConstructorArgs> r1 = r2.coreChartRendererBaseConstructorArgs
            r3.<init>(r0, r1)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.nutrition.service.ChartRendererFactory.getChartRenderer(java.lang.String):com.myfitnesspal.feature.nutrition.service.CoreChartRenderer");
    }
}
