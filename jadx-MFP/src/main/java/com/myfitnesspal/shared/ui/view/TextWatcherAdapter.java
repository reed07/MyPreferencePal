package com.myfitnesspal.shared.ui.view;

import android.text.Editable;
import android.text.TextWatcher;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u0011B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J(\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J(\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/myfitnesspal/shared/ui/view/TextWatcherAdapter;", "Landroid/text/TextWatcher;", "listener", "Lcom/myfitnesspal/shared/ui/view/TextWatcherAdapter$TextWatcherListener;", "(Lcom/myfitnesspal/shared/ui/view/TextWatcherAdapter$TextWatcherListener;)V", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "TextWatcherListener", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: TextWatcherAdapter.kt */
public final class TextWatcherAdapter implements TextWatcher {
    private final TextWatcherListener listener;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J(\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH&J(\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH&¨\u0006\u000e"}, d2 = {"Lcom/myfitnesspal/shared/ui/view/TextWatcherAdapter$TextWatcherListener;", "", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: TextWatcherAdapter.kt */
    public interface TextWatcherListener {
        void afterTextChanged(@NotNull Editable editable);

        void beforeTextChanged(@NotNull CharSequence charSequence, int i, int i2, int i3);

        void onTextChanged(@NotNull CharSequence charSequence, int i, int i2, int i3);
    }

    public TextWatcherAdapter(@Nullable TextWatcherListener textWatcherListener) {
        this.listener = textWatcherListener;
    }

    public void onTextChanged(@NotNull CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(charSequence, "s");
        TextWatcherListener textWatcherListener = this.listener;
        if (textWatcherListener != null) {
            textWatcherListener.onTextChanged(charSequence, i, i2, i3);
        }
    }

    public void beforeTextChanged(@NotNull CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(charSequence, "s");
        TextWatcherListener textWatcherListener = this.listener;
        if (textWatcherListener != null) {
            textWatcherListener.beforeTextChanged(charSequence, i, i2, i3);
        }
    }

    public void afterTextChanged(@NotNull Editable editable) {
        Intrinsics.checkParameterIsNotNull(editable, "s");
        TextWatcherListener textWatcherListener = this.listener;
        if (textWatcherListener != null) {
            textWatcherListener.afterTextChanged(editable);
        }
    }
}
