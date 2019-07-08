package com.myfitnesspal.shared.ui.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.Factory;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B.\b\u0007\u0012%\u0010\u0002\u001a!\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00050\u0006¢\u0006\u0002\b\u00070\u0003¢\u0006\u0002\u0010\bJ'\u0010\t\u001a\u0002H\n\"\n\b\u0000\u0010\n*\u0004\u0018\u00010\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\u0004H\u0016¢\u0006\u0002\u0010\fR-\u0010\u0002\u001a!\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00050\u0006¢\u0006\u0002\b\u00070\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/myfitnesspal/shared/ui/factory/VMFactory;", "Landroid/arch/lifecycle/ViewModelProvider$Factory;", "creators", "", "Ljava/lang/Class;", "Landroid/arch/lifecycle/ViewModel;", "Ljavax/inject/Provider;", "Lkotlin/jvm/JvmSuppressWildcards;", "(Ljava/util/Map;)V", "create", "T", "modelClass", "(Ljava/lang/Class;)Landroid/arch/lifecycle/ViewModel;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: VMFactory.kt */
public final class VMFactory implements Factory {
    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public VMFactory(@NotNull Map<Class<? extends ViewModel>, Provider<ViewModel>> map) {
        Intrinsics.checkParameterIsNotNull(map, "creators");
        this.creators = map;
    }

    public <T extends ViewModel> T create(@NotNull Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Provider provider = (Provider) this.creators.get(cls);
        if (provider == null) {
            Iterator it = this.creators.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Entry entry = (Entry) it.next();
                if (cls.isAssignableFrom((Class) entry.getKey())) {
                    provider = (Provider) entry.getValue();
                    break;
                }
            }
        }
        if (provider != null) {
            try {
                return (ViewModel) provider.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown model class");
            sb.append(cls);
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
