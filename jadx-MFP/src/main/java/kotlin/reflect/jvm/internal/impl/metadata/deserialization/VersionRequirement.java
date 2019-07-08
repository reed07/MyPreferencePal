package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.DeprecationLevel;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.Level;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement.VersionKind;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: VersionRequirement.kt */
public final class VersionRequirement {
    public static final Companion Companion = new Companion(null);
    @Nullable
    private final Integer errorCode;
    @NotNull
    private final VersionKind kind;
    @NotNull
    private final DeprecationLevel level;
    @Nullable
    private final String message;
    @NotNull
    private final Version version;

    /* compiled from: VersionRequirement.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final List<VersionRequirement> create(@NotNull MessageLite messageLite, @NotNull NameResolver nameResolver, @NotNull VersionRequirementTable versionRequirementTable) {
            List list;
            Intrinsics.checkParameterIsNotNull(messageLite, "proto");
            Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
            Intrinsics.checkParameterIsNotNull(versionRequirementTable, "table");
            if (messageLite instanceof Class) {
                list = ((Class) messageLite).getVersionRequirementList();
            } else if (messageLite instanceof Constructor) {
                list = ((Constructor) messageLite).getVersionRequirementList();
            } else if (messageLite instanceof Function) {
                list = ((Function) messageLite).getVersionRequirementList();
            } else if (messageLite instanceof Property) {
                list = ((Property) messageLite).getVersionRequirementList();
            } else if (messageLite instanceof TypeAlias) {
                list = ((TypeAlias) messageLite).getVersionRequirementList();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected declaration: ");
                sb.append(messageLite.getClass());
                throw new IllegalStateException(sb.toString());
            }
            Intrinsics.checkExpressionValueIsNotNull(list, "ids");
            Iterable<Integer> iterable = list;
            Collection arrayList = new ArrayList();
            for (Integer num : iterable) {
                Companion companion = VersionRequirement.Companion;
                Intrinsics.checkExpressionValueIsNotNull(num, "id");
                VersionRequirement create = companion.create(num.intValue(), nameResolver, versionRequirementTable);
                if (create != null) {
                    arrayList.add(create);
                }
            }
            return (List) arrayList;
        }

        @Nullable
        public final VersionRequirement create(int i, @NotNull NameResolver nameResolver, @NotNull VersionRequirementTable versionRequirementTable) {
            DeprecationLevel deprecationLevel;
            Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
            Intrinsics.checkParameterIsNotNull(versionRequirementTable, "table");
            kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirement versionRequirement = versionRequirementTable.get(i);
            String str = null;
            if (versionRequirement == null) {
                return null;
            }
            Version decode = Version.Companion.decode(versionRequirement.hasVersion() ? Integer.valueOf(versionRequirement.getVersion()) : null, versionRequirement.hasVersionFull() ? Integer.valueOf(versionRequirement.getVersionFull()) : null);
            Level level = versionRequirement.getLevel();
            if (level == null) {
                Intrinsics.throwNpe();
            }
            switch (level) {
                case WARNING:
                    deprecationLevel = DeprecationLevel.WARNING;
                    break;
                case ERROR:
                    deprecationLevel = DeprecationLevel.ERROR;
                    break;
                case HIDDEN:
                    deprecationLevel = DeprecationLevel.HIDDEN;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            Integer valueOf = versionRequirement.hasErrorCode() ? Integer.valueOf(versionRequirement.getErrorCode()) : null;
            if (versionRequirement.hasMessage()) {
                str = nameResolver.getString(versionRequirement.getMessage());
            }
            String str2 = str;
            VersionKind versionKind = versionRequirement.getVersionKind();
            Intrinsics.checkExpressionValueIsNotNull(versionKind, "info.versionKind");
            VersionRequirement versionRequirement2 = new VersionRequirement(decode, versionKind, deprecationLevel, valueOf, str2);
            return versionRequirement2;
        }
    }

    /* compiled from: VersionRequirement.kt */
    public static final class Version {
        public static final Companion Companion = new Companion(null);
        @NotNull
        @JvmField
        public static final Version INFINITY = new Version(256, 256, 256);
        private final int major;
        private final int minor;
        private final int patch;

        /* compiled from: VersionRequirement.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final Version decode(@Nullable Integer num, @Nullable Integer num2) {
                if (num2 != null) {
                    return new Version(num2.intValue() & 255, (num2.intValue() >> 8) & 255, (num2.intValue() >> 16) & 255);
                }
                if (num != null) {
                    return new Version(num.intValue() & 7, (num.intValue() >> 3) & 15, (num.intValue() >> 7) & Statements.GetOwnedFoodIdsDateDescending);
                }
                return Version.INFINITY;
            }
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof Version) {
                    Version version = (Version) obj;
                    if (this.major == version.major) {
                        if (this.minor == version.minor) {
                            if (this.patch == version.patch) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.major * 31) + this.minor) * 31) + this.patch;
        }

        public Version(int i, int i2, int i3) {
            this.major = i;
            this.minor = i2;
            this.patch = i3;
        }

        public /* synthetic */ Version(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i4 & 4) != 0) {
                i3 = 0;
            }
            this(i, i2, i3);
        }

        @NotNull
        public final String asString() {
            int i;
            StringBuilder sb;
            if (this.patch == 0) {
                sb = new StringBuilder();
                sb.append(this.major);
                sb.append('.');
                i = this.minor;
            } else {
                sb = new StringBuilder();
                sb.append(this.major);
                sb.append('.');
                sb.append(this.minor);
                sb.append('.');
                i = this.patch;
            }
            sb.append(i);
            return sb.toString();
        }

        @NotNull
        public String toString() {
            return asString();
        }
    }

    public VersionRequirement(@NotNull Version version2, @NotNull VersionKind versionKind, @NotNull DeprecationLevel deprecationLevel, @Nullable Integer num, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(version2, "version");
        Intrinsics.checkParameterIsNotNull(versionKind, "kind");
        Intrinsics.checkParameterIsNotNull(deprecationLevel, Param.LEVEL);
        this.version = version2;
        this.kind = versionKind;
        this.level = deprecationLevel;
        this.errorCode = num;
        this.message = str;
    }

    @NotNull
    public final Version getVersion() {
        return this.version;
    }

    @NotNull
    public final VersionKind getKind() {
        return this.kind;
    }

    @NotNull
    public String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append("since ");
        sb.append(this.version);
        sb.append(' ');
        sb.append(this.level);
        if (this.errorCode != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(" error ");
            sb2.append(this.errorCode);
            str = sb2.toString();
        } else {
            str = "";
        }
        sb.append(str);
        if (this.message != null) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(": ");
            sb3.append(this.message);
            str2 = sb3.toString();
        } else {
            str2 = "";
        }
        sb.append(str2);
        return sb.toString();
    }
}
