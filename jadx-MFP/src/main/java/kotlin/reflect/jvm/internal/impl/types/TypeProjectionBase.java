package kotlin.reflect.jvm.internal.impl.types;

import com.brightcove.player.event.EventType;

public abstract class TypeProjectionBase implements TypeProjection {
    public String toString() {
        if (isStarProjection()) {
            return EventType.ANY;
        }
        if (getProjectionKind() == Variance.INVARIANT) {
            return getType().toString();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getProjectionKind());
        sb.append(" ");
        sb.append(getType());
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TypeProjection)) {
            return false;
        }
        TypeProjection typeProjection = (TypeProjection) obj;
        return isStarProjection() == typeProjection.isStarProjection() && getProjectionKind() == typeProjection.getProjectionKind() && getType().equals(typeProjection.getType());
    }

    public int hashCode() {
        return (getProjectionKind().hashCode() * 31) + (isStarProjection() ? 17 : getType().hashCode());
    }
}
