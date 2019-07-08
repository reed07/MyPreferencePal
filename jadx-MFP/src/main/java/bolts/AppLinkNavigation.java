package bolts;

public class AppLinkNavigation {

    public enum NavigationResult {
        FAILED("failed", false),
        WEB("web", true),
        APP("app", true);
        
        private String code;
        private boolean succeeded;

        private NavigationResult(String str, boolean z) {
            this.code = str;
            this.succeeded = z;
        }
    }
}
