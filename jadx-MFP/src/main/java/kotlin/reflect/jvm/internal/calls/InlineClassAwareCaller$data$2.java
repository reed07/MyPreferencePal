package kotlin.reflect.jvm.internal.calls;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.BoundStatic;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002 \u0001*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/calls/InlineClassAwareCaller$BoxUnboxData;", "M", "Ljava/lang/reflect/Member;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: InlineClassAwareCaller.kt */
final class InlineClassAwareCaller$data$2 extends Lambda implements Function0<BoxUnboxData> {
    final /* synthetic */ InlineClassAwareCaller this$0;

    InlineClassAwareCaller$data$2(InlineClassAwareCaller inlineClassAwareCaller) {
        this.this$0 = inlineClassAwareCaller;
        super(0);
    }

    @NotNull
    public final BoxUnboxData invoke() {
        Method method;
        int i = this.this$0.caller instanceof BoundStatic ? -1 : (this.this$0.descriptor.getDispatchReceiverParameter() == null || (this.this$0.caller instanceof BoundCaller)) ? 0 : 1;
        int i2 = this.this$0.isDefault ? 2 : 0;
        ReceiverParameterDescriptor extensionReceiverParameter = this.this$0.descriptor.getExtensionReceiverParameter();
        Method method2 = null;
        Collection listOfNotNull = CollectionsKt.listOfNotNull(extensionReceiverParameter != null ? extensionReceiverParameter.getType() : null);
        List valueParameters = this.this$0.descriptor.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters, "descriptor.valueParameters");
        Iterable<ValueParameterDescriptor> iterable = valueParameters;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (ValueParameterDescriptor type : iterable) {
            arrayList.add(type.getType());
        }
        List plus = CollectionsKt.plus(listOfNotNull, (Iterable<? extends T>) (List) arrayList);
        int size = plus.size() + i + i2;
        if (CallerKt.getArity(this.this$0) == size) {
            IntRange until = RangesKt.until(Math.max(i, 0), plus.size() + i);
            Method[] methodArr = new Method[size];
            int length = methodArr.length;
            for (int i3 = 0; i3 < length; i3++) {
                if (until.contains(i3)) {
                    InlineClassAwareCaller inlineClassAwareCaller = this.this$0;
                    Object obj = plus.get(i3 - i);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "kotlinParameterTypes[i - shift]");
                    Class access$toInlineClass = inlineClassAwareCaller.toInlineClass((KotlinType) obj);
                    method = access$toInlineClass != null ? this.this$0.getUnboxMethod(access$toInlineClass) : null;
                } else {
                    method = null;
                }
                methodArr[i3] = method;
            }
            InlineClassAwareCaller inlineClassAwareCaller2 = this.this$0;
            KotlinType returnType = inlineClassAwareCaller2.descriptor.getReturnType();
            if (returnType == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(returnType, "descriptor.returnType!!");
            Class access$toInlineClass2 = inlineClassAwareCaller2.toInlineClass(returnType);
            if (access$toInlineClass2 != null) {
                method2 = this.this$0.getBoxMethod(access$toInlineClass2);
            }
            return new BoxUnboxData(until, methodArr, method2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Inconsistent number of parameters in the descriptor and Java reflection object: ");
        sb.append(CallerKt.getArity(this.this$0));
        sb.append(" != ");
        sb.append(size);
        sb.append(10);
        sb.append("Calling: ");
        sb.append(this.this$0.descriptor);
        sb.append(10);
        sb.append("Parameter types: ");
        sb.append(this.this$0.getParameterTypes());
        sb.append(")\n");
        sb.append("Default: ");
        sb.append(this.this$0.isDefault);
        throw new KotlinReflectionInternalError(sb.toString());
    }
}
