package kotlin.reflect.jvm.internal.impl.incremental.components;

import java.io.Serializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LookupLocation.kt */
public final class Position implements Serializable {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final Position NO_POSITION = new Position(-1, -1);
    private final int column;
    private final int line;

    /* compiled from: LookupLocation.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Position getNO_POSITION() {
            return Position.NO_POSITION;
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Position) {
                Position position = (Position) obj;
                if (this.line == position.line) {
                    if (this.column == position.column) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.line * 31) + this.column;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Position(line=");
        sb.append(this.line);
        sb.append(", column=");
        sb.append(this.column);
        sb.append(")");
        return sb.toString();
    }

    public Position(int i, int i2) {
        this.line = i;
        this.column = i2;
    }
}
