package com.myfitnesspal.feature.foodeditor.ui.mixin.impl;

import com.myfitnesspal.feature.search.model.SponsoredFoodSearchAd;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KProperty1;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* compiled from: SponsoredFoodMixin.kt */
final class SponsoredFoodMixin$sponsoredInfo$1 extends PropertyReference1 {
    public static final KProperty1 INSTANCE = new SponsoredFoodMixin$sponsoredInfo$1();

    SponsoredFoodMixin$sponsoredInfo$1() {
    }

    public String getName() {
        return "javaClass";
    }

    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinPackage(JvmClassMappingKt.class, "app_googleRelease");
    }

    public String getSignature() {
        return "getJavaClass(Ljava/lang/Object;)Ljava/lang/Class;";
    }

    @Nullable
    public Object get(@Nullable Object obj) {
        return JvmClassMappingKt.getJavaClass((SponsoredFoodSearchAd) obj);
    }
}
