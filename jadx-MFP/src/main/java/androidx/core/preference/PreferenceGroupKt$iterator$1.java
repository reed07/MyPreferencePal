package androidx.core.preference;

import android.preference.Preference;
import android.preference.PreferenceGroup;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMutableIterator;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000%\n\u0000\n\u0002\u0010)\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\t\u0010\u0006\u001a\u00020\u0007H\u0002J\t\u0010\b\u001a\u00020\u0002H\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"androidx/core/preference/PreferenceGroupKt$iterator$1", "", "Landroid/preference/Preference;", "(Landroid/preference/PreferenceGroup;)V", "index", "", "hasNext", "", "next", "remove", "", "core-ktx_release"}, k = 1, mv = {1, 1, 9})
/* compiled from: PreferenceGroup.kt */
public final class PreferenceGroupKt$iterator$1 implements Iterator<Preference>, KMutableIterator {
    private int index;
    final /* synthetic */ PreferenceGroup receiver$0;

    public boolean hasNext() {
        return this.index < this.receiver$0.getPreferenceCount();
    }

    @NotNull
    public Preference next() {
        PreferenceGroup preferenceGroup = this.receiver$0;
        int i = this.index;
        this.index = i + 1;
        Preference preference = preferenceGroup.getPreference(i);
        if (preference != null) {
            return preference;
        }
        throw new IndexOutOfBoundsException();
    }

    public void remove() {
        PreferenceGroup preferenceGroup = this.receiver$0;
        this.index--;
        preferenceGroup.removePreference(preferenceGroup.getPreference(this.index));
    }
}
