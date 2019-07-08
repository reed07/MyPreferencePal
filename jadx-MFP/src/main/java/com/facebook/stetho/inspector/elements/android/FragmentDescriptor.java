package com.facebook.stetho.inspector.elements.android;

import android.graphics.Rect;
import android.view.View;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.android.FragmentAccessor;
import com.facebook.stetho.common.android.FragmentCompat;
import com.facebook.stetho.common.android.ResourcesUtil;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.AttributeAccumulator;
import com.facebook.stetho.inspector.elements.Descriptor;
import com.facebook.stetho.inspector.elements.Descriptor.Host;
import com.facebook.stetho.inspector.elements.DescriptorMap;
import javax.annotation.Nullable;

final class FragmentDescriptor extends AbstractChainedDescriptor<Object> implements HighlightableDescriptor<Object> {
    private static final String ID_ATTRIBUTE_NAME = "id";
    private static final String TAG_ATTRIBUTE_NAME = "tag";
    private final FragmentAccessor mAccessor;

    public static DescriptorMap register(DescriptorMap descriptorMap) {
        maybeRegister(descriptorMap, FragmentCompat.getSupportLibInstance());
        maybeRegister(descriptorMap, FragmentCompat.getFrameworkInstance());
        return descriptorMap;
    }

    private static void maybeRegister(DescriptorMap descriptorMap, @Nullable FragmentCompat fragmentCompat) {
        if (fragmentCompat != null) {
            Class fragmentClass = fragmentCompat.getFragmentClass();
            LogUtil.d("Adding support for %s", fragmentClass.getName());
            descriptorMap.registerDescriptor(fragmentClass, (Descriptor) new FragmentDescriptor(fragmentCompat));
        }
    }

    private FragmentDescriptor(FragmentCompat fragmentCompat) {
        this.mAccessor = fragmentCompat.forFragment();
    }

    /* access modifiers changed from: protected */
    public void onGetAttributes(Object obj, AttributeAccumulator attributeAccumulator) {
        int id = this.mAccessor.getId(obj);
        if (id != 0) {
            attributeAccumulator.store("id", ResourcesUtil.getIdStringQuietly(obj, this.mAccessor.getResources(obj), id));
        }
        String tag = this.mAccessor.getTag(obj);
        if (tag != null && tag.length() > 0) {
            attributeAccumulator.store("tag", tag);
        }
    }

    /* access modifiers changed from: protected */
    public void onGetChildren(Object obj, Accumulator<Object> accumulator) {
        View view = this.mAccessor.getView(obj);
        if (view != null) {
            accumulator.store(view);
        }
    }

    @Nullable
    public View getViewAndBoundsForHighlighting(Object obj, Rect rect) {
        return this.mAccessor.getView(obj);
    }

    @Nullable
    public Object getElementToHighlightAtPosition(Object obj, int i, int i2, Rect rect) {
        Object obj2;
        HighlightableDescriptor highlightableDescriptor;
        Host host = getHost();
        if (host instanceof AndroidDescriptorHost) {
            obj2 = this.mAccessor.getView(obj);
            highlightableDescriptor = ((AndroidDescriptorHost) host).getHighlightableDescriptor(obj2);
        } else {
            obj2 = null;
            highlightableDescriptor = null;
        }
        if (highlightableDescriptor == null) {
            return null;
        }
        return highlightableDescriptor.getElementToHighlightAtPosition(obj2, i, i2, rect);
    }
}
