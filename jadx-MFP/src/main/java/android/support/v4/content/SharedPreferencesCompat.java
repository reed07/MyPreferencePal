package android.support.v4.content;

@Deprecated
public final class SharedPreferencesCompat {

    @Deprecated
    public static final class EditorCompat {
        private final Helper mHelper = new Helper();

        private static class Helper {
            Helper() {
            }
        }

        private EditorCompat() {
        }
    }

    private SharedPreferencesCompat() {
    }
}
