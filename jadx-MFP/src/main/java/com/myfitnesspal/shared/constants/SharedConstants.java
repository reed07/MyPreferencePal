package com.myfitnesspal.shared.constants;

public final class SharedConstants {

    public static final class APIToken {
        public static final String GOOGLE_INTEG_TOKEN = "YTptZnAtbW9iaWxlLWFuZHJvaWQtZ29vZ2xlOjo6ZXZlbnRzJTIwcmVnaW9uczoxNDQ2MTU5NzEyNDgxOjE2MDM4Mzk3MTI0ODFENR9Nolto26byqnSfsSnDQxSsMa6fJdNk5KynATWsnVG1te58Uth7FYFQpVWwlwcsCsd21JPAeNoEc27gwzXRzuIm59JsBCkik4v7AC78OcfhMW6AttqeR+FZ0I2FQD48pEhaI1+SIFv5/t1mliTMjjvU4emacQmJ2EDuD9yVVA==";
        public static final String GOOGLE_PROD_TOKEN = "YTptZnAtbW9iaWxlLWFuZHJvaWQtZ29vZ2xlOjo6ZXZlbnRzJTIwcmVnaW9uczoxNDQ2MTU5NzEyNDgxOjE2MDM4Mzk3MTI0ODGVaIVcDkCJx1v2ziIc5W2Yj0KuRqJ7lZxISl4ijHclyE5YB/JGDED4afZNuMREM+lmttIJVeaHCgZ3J56sEv1MDsz9Ry2KX+Csp26bv/JQjSAvn6VBEOawF+iqU9uh1XRBin5KJPsPoDAvhud9BkZqnJ9Yxw6R6heW2IAmWNYwKA==";
    }

    public static final class Api {
        public static final String CURRENT_API_VERSION = "2.0.50";
    }

    public static final class Cache {
        public static final String APP_GALLERY_DESC_KEY = "cache_app_gallery_desc_%s_%s";
    }

    public static final class Extras {
        public static final String HANDLE_ALL_CLICKS_EXTERNALLY = "handle_all_clicks_externally";
        public static final String IS_MAIN_SCREEN = "is_main_screen";
        public static final String TAG = "tag";
        public static final String TITLE = "title";
        public static final String URL = "url";
        public static final String YEAR = "year";
    }

    public static final class Facebook {
        public static final String BIRTHDAY = "birthday";
        public static final String EMAIL = "email";
        public static final String GENDER = "gender";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String TIMEZONE = "timezone";
    }

    public static final class Http {
        public static final String ACCEPT_LANGUAGE = "Accept-Language";
        public static final String ACCESS_TOKEN = "access_token";
        public static final String ACCOUNT = "account";
        public static final String API_VERSION = "api-version";
        public static final String APPLICATION = "application";
        public static final String APP_PREFERENCES = "app_preferences";
        public static final String APP_VERSION = "app_version";
        public static final String ARTICLE = "article_id";
        public static final String BEARER_AUTH_FORMAT = "Bearer %s";
        public static final String BETA = "beta";
        public static final String BINARY_API_VERSION = "api_version";
        public static final String BUILD_NUMBER = "build_number";
        public static final String BUILD_VERSION = "build_version";
        public static final String CLIENT_BUILD = "client_build";
        public static final String CLIENT_ID = "client_id";
        public static final String CODE = "code";
        public static final String DAIRY_PREFERENCES = "dairy_preferences";
        public static final String DEVICE_ID = "device_id";
        public static final String FACEBOOK_TOKEN = "facebook_token";
        public static final String FIELDS_ARRAY = "fields[]";
        public static final String FLOW_ID_HEADER = "mfp-flow-id";
        public static final String GOAL_DISPLAYS = "goal_displays";
        public static final String GOAL_PREFERENCES = "goal_preferences";
        public static final String GOOGLE_FIT_ENABLED = "google_fit_enabled_v2";
        public static final String GRANT_TYPE = "grant_type";
        public static final String HARDWARE = "hardware";
        public static final String HEADER_AUTHORIZATION = "Authorization";
        public static final String HEADER_X_HTTP_METHOD_OVERRIDE = "X-HTTP-Method-Override";
        public static final String LANG = "lang";
        public static final String LOCALE = "locale";
        public static final String LOCATION_PREFERENCES = "location_preferences";
        public static final String MAX_ITEMS = "max_items";
        public static final String METHOD_PATCH = "PATCH";
        public static final String MFP_CLIENT_ID = "mfp-client-id";
        public static final String MFP_DEVICE_ID = "mfp-device-id";
        public static final String MFP_USER_ID = "mfp-user-id";
        public static final String NOTIFICATION_PREFERENCES = "notification_preferences";
        public static final String OS = "os";
        public static final String PASSWORD = "password";
        public static final String PIXEL_RATIO = "pixel_ratio";
        public static final String PLATFORM = "platform";
        public static final String PLATFORM_APPS_TYPE = "platform_apps_type";
        public static final String PLATFORM_SUBTYPE = "platform_subtype";
        public static final String PLATFORM_TYPE = "platform_type";
        public static final String PRIVACY_PREFERENCES = "privacy_preferences";
        public static final String PROFILES = "profiles";
        public static final String REFRESH_TOKEN = "refresh_token";
        public static final String SCREEN_DENSITY = "Screen-Density";
        public static final String SCREEN_HEIGHT = "Screen-Height";
        public static final String SCREEN_WIDTH = "Screen-Width";
        public static final String SOCIAL_PREFERENCES = "social_preferences";
        public static final String SOURCE = "source";
        public static final String STEPS_SOURCES = "step_sources";
        public static final String SYSTEM_DATA = "system_data";
        public static final String UNIT_PREFERENCES = "unit_preferences";
        public static final String USERNAME = "username";
        public static final String USERNAME_OR_EMAIL = "username_or_email";
        public static final String USER_ID = "user_id";
        public static final String VERSION = "version";
    }

    public static final class Injection {

        public static final class Named {
            public static final String APP_SESSION_ID = "app_session_id";
            public static final String APP_VERSION_CODE = "appVersionCode";
            public static final String APP_VERSION_NAME = "appVersionName";
            public static final String CACHE_STORE = "cache-store";
            public static final String CARRIER_NAME = "carrier_name";
            public static final String CLIENT_ID = "client_id";
            public static final String DEVICE_UUID = "deviceUUID";
            public static final String DEVICE_UUID_BYTES = "deviceUUIDBytes";
            public static final String EMAIL_VALIDATOR = "emailValidator";
            public static final String FACEBOOK_APP_ID = "facebook-app-id";
            public static final String FACEBOOK_PERMISSIONS = "facebook-permissions";
            public static final String FRIEND_INVITE_SETTINGS_STORE = "friend-invite-settings";
            public static final String GEO_LOCATION_SETTINGS_STORE = "geo-location";
            public static final String GUEST_ACCESS_TOKEN = "guestAccessToken";
            public static final String LOGS_DIR = "logFile";
            public static final String SERVING_SIZE_VALIDATOR = "servingSizeValidator";
            public static final String SYNC_V1_SETTINGS_STORE = "sync-settings";
            public static final String WEIGHT_VALIDATOR = "weightValidator";
        }
    }

    public static final class LaunchActions {
        public static final String AUTHORIZE = "authorize";
        public static final String MFP_CONNECT = "mfpconnect";
    }

    public static final class LaunchParams {
        public static final String ACTION = "action";
        public static final String CLIENT_ID = "client_id";
        public static final String OPERATION = "operation";
        public static final String REDIRECT_URI = "redirect_uri";
        public static final String SUFFIX = "suffix";
        public static final String USE_REDIRECT_URI_INSTEAD_OF_ACTIVITY_RESULT = "useUriInsteadOfActivityResult";
    }

    public static final class Params {
        public static final String DATE = "date";
        public static final String FROM = "from";
        public static final String SCOPE = "scope";
        public static final String TO = "to";
    }

    public final class Performance {
        public static final String API_FETCH_FORMAT = "API Call %s";
        public static final String API_MAP_FORMAT = "API Map %s";

        public Performance() {
        }
    }

    public static final class Preference {
        public static final String MOCK_API_PREF = "mockApiPref";
    }

    public static final class RequestCodes {
        public static final int CROP_FROM_CAMERA = 1002;
        public static final int FACEBOOK_AUTH = 1000;
        public static final int PICK_FROM_CAMERA = 1003;
        public static final int PICK_FROM_FILE = 1004;
        public static final int URL = 1001;
    }

    public static final class Settings {

        public static final class App {
            public static final String BASE_APIV2_URL = "app.baseApiV2Url";
            public static final String BASE_BLOG_URL = "app.baseBlogUrl";
            public static final String BASE_CONFIG_URL = "app.baseConfigUrl";
            public static final String BASE_WEBSITE_URL = "app.baseWebUrl";
            public static final String CONSENTS_API_ENVIRONMENT = "app.consentsApiEnvironment";
            public static final String CUSTOM_APIV2_URLS = "app.customApiV2Urls";
            public static final String CUSTOM_BLOG_BASE_URLS = "app.customBlogUrls";
            public static final String CUSTOM_SYNC_URLS = "app.customSyncUrls";
            public static final String CUSTOM_WEBSITE_BASE_URLS = "app.customWebUrls";
            public static final String DEVICE_ACTIVATION_API_ENVIRONMENT = "app.deviceActivationEnvironment";
            public static final String MFP_SERVER_CERTIFICATE_IS_TRUSTED_BY_THIS_DEVICE = "app.mfpServiceCertificateIsTrusted";
            public static final String NIS_API_ENVIRONMENT = "app.nisApiEnvironment";
            public static final String OAUTH_OBFUSCATED_USER_ID = "app.oauth.obfuscatedUserId";
            public static final String OAUTH_REQUEST_TRIPLE = "app.oauthRequestTriple";
            public static final String OAUTH_RESOURCE_OWNER_ACCESS_TOKEN = "app.oauth.resourceOwnerAccessToken";
            public static final String OAUTH_RESOURCE_OWNER_REFRESH_TOKEN = "app.oauth.resourceOwnerRefreshToken";
            public static final String OAUTH_REVERSE_MIGRATION_PENDING = "app.oauth.reverseMigrationPending";
            public static final String SERVER_SIDE_THIRD_PARTY_TOKEN_VALID = "app.serverSideThirdPartyTokenIsValid";
            public static final String SSO_AUTH_API_ENVIRONMENT = "app.ssoAuthApiEnvironment";
            public static final String SYNCV2_API_ENVIRONMENT = "app.syncV2ApiEnvironment";
        }

        public static final class Sync {
            public static final String API_VERSION = "shared.sync.apiVersion";
            public static final String BASE_URL = "app.sync.baseUrl";
            public static final String DEFAULT_BASE_URL = "https://sync.myfitnesspal.com";
            public static final String MASTER_ID_FOR_MOST_RECENT_THIRD_PARTY_ADD_OR_DELETE = "shared.sync.masterIdForMostRecentThirdPartyAddOrDelete";
        }

        public static final class URLs {
            public static final String DEFAULT_BASE_APIV2_URL = "https://api.myfitnesspal.com";
            public static final String DEFAULT_BASE_APIV2_URL_INTEG = "https://integ.myfitnesspal.com";
            public static final String DEFAULT_BASE_BLOG_URL = "http://blog.myfitnesspal.com";
            public static final String DEFAULT_BASE_WEBSITE_URL = "https://www.myfitnesspal.com";
        }
    }

    public static final class Uri {
        public static final String ACCOUNT_RESTRICTED = "/account/restricted";
        public static final String ACTION_REQUEST = "iphone_api/synchronize";
        public static final String BASE_BETA_FEEDBACK_RELATIVE_PATH = "/v2/desk/create_feedback";
        public static final String BASE_FAQ_RELATIVE_PATH = "/v2/desk/create";
        public static final String BLOG_FEED = "/feed";
        public static final String CHECK_AVAILABILITY = "iphone_api/check_availability";
        public static final String CHECK_DIAGNOSTIC_STATUS = "iphone_api/check_diagnostic_status";
        public static final String CLEAR_DEVICE_TOKEN = "iphone_api/clear_device_token_by_token_id";
        public static final String CURRENT_USER = "/v2/users/__USERID__";
        public static final String DIAGNOSTIC_UPLOAD = "iphone_api/diagnostic_upload";
        public static final String FORGOT_PASSWORD = "iphone_api/forgot_password";
        public static final String INFORMATION_REQUEST = "iphone_api/synchronize";
        public static final String IPHONE_API = "iphone_api";
        public static final String OAUTH2_AUTHORIZE = "/oauth2/authorize";
        public static final String OAUTH2_TOKEN = "/v2/oauth2/token";
        public static final String ONLINE_SEARCH = "iphone_api/online_search";
        public static final String SYNCHRONIZE = "iphone_api/synchronize";
        public static final String USERID_TOKEN = "__USERID__";
        public static final String V2_IMPORT = "/v2/import";
        public static final String V2_SYNC = "/v2/sync";
        private static final String VERSION = "/v2";
    }

    public static final class UserGoals {
        public static float LOW_CALORIES_MEN = 1500.0f;
        public static float LOW_CALORIES_WOMEN = 1200.0f;
    }

    public static final class Validators {
        public static final String EMAIL = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+";
        public static final String SERVING_SIZE_REGEX_FORMAT = "^(\\d+((%1$s)|((%1$s)(\\d)*))?)|((%1$s)(\\d+))$";
        public static final String WEIGHT_REGEX_FORMAT = "^\\d+(((%1$s))|((%1$s)(\\d)*))?$";
    }

    public static final class Values {
        public static final String PLATFORM_ANDROID = "android";
    }
}
