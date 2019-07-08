package com.myfitnesspal.shared.service.analytics;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MultiAnalyticsService extends SimpleAsyncServiceBase implements AnalyticsService {
    /* access modifiers changed from: private */
    public final List<AnalyticsService> services;

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 2;
    }

    public boolean isEnabled() {
        return true;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return MultiAnalyticsService.class.getCanonicalName();
    }

    public MultiAnalyticsService(Lazy<AppIndexerBot> lazy, AnalyticsService... analyticsServiceArr) {
        List<AnalyticsService> list;
        if (((AppIndexerBot) lazy.get()).isActive()) {
            list = new ArrayList<>();
        } else {
            list = Arrays.asList(analyticsServiceArr);
        }
        this.services = list;
    }

    public void initialize(final Activity activity) {
        auto(new Runnable() {
            public void run() {
                for (AnalyticsService initialize : MultiAnalyticsService.this.services) {
                    initialize.initialize(activity);
                }
            }
        });
    }

    public void updateUserPremiumStatus(@NonNull String str) {
        auto(new Runnable(str) {
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MultiAnalyticsService.lambda$updateUserPremiumStatus$0(MultiAnalyticsService.this, this.f$1);
            }
        });
    }

    public static /* synthetic */ void lambda$updateUserPremiumStatus$0(@NonNull MultiAnalyticsService multiAnalyticsService, String str) {
        for (AnalyticsService updateUserPremiumStatus : multiAnalyticsService.services) {
            updateUserPremiumStatus.updateUserPremiumStatus(str);
        }
    }

    public void reportInstall() {
        auto(new Runnable() {
            public void run() {
                for (AnalyticsService reportInstall : MultiAnalyticsService.this.services) {
                    reportInstall.reportInstall();
                }
            }
        });
    }

    public void reportUpgrade() {
        auto(new Runnable() {
            public void run() {
                for (AnalyticsService reportUpgrade : MultiAnalyticsService.this.services) {
                    reportUpgrade.reportUpgrade();
                }
            }
        });
    }

    public void reportRegistration() {
        auto(new Runnable() {
            public void run() {
                for (AnalyticsService reportRegistration : MultiAnalyticsService.this.services) {
                    reportRegistration.reportRegistration();
                }
            }
        });
    }

    public void reportEvent(final String str) {
        auto(new Runnable() {
            public void run() {
                for (AnalyticsService reportEvent : MultiAnalyticsService.this.services) {
                    reportEvent.reportEvent(str);
                }
            }
        });
    }

    public void reportEvent(final String str, final int i) {
        auto(new Runnable() {
            public void run() {
                for (AnalyticsService reportEvent : MultiAnalyticsService.this.services) {
                    reportEvent.reportEvent(str, i);
                }
            }
        });
    }

    public void reportEvent(final String str, final Map<String, String> map) {
        auto(new Runnable() {
            public void run() {
                for (AnalyticsService reportEvent : MultiAnalyticsService.this.services) {
                    reportEvent.reportEvent(str, map);
                }
            }
        });
    }

    public void reportEvent(final String str, final Map<String, String> map, final String str2) {
        auto(new Runnable() {
            public void run() {
                for (AnalyticsService reportEvent : MultiAnalyticsService.this.services) {
                    reportEvent.reportEvent(str, map, str2);
                }
            }
        });
    }

    public void reportEvent(String str, Map<String, String> map, String str2, int i) {
        final String str3 = str;
        final Map<String, String> map2 = map;
        final String str4 = str2;
        final int i2 = i;
        AnonymousClass9 r0 = new Runnable() {
            public void run() {
                for (AnalyticsService reportEvent : MultiAnalyticsService.this.services) {
                    reportEvent.reportEvent(str3, map2, str4, i2);
                }
            }
        };
        auto(r0);
    }

    public void reportScreenView(final String str) {
        auto(new Runnable() {
            public void run() {
                for (AnalyticsService reportScreenView : MultiAnalyticsService.this.services) {
                    reportScreenView.reportScreenView(str);
                }
            }
        });
    }

    public void reportScreenView(final String str, final Map<String, String> map) {
        auto(new Runnable() {
            public void run() {
                for (AnalyticsService reportScreenView : MultiAnalyticsService.this.services) {
                    reportScreenView.reportScreenView(str, map);
                }
            }
        });
    }

    public void reportUserId(final String str) {
        auto(new Runnable() {
            public void run() {
                for (AnalyticsService reportUserId : MultiAnalyticsService.this.services) {
                    reportUserId.reportUserId(str);
                }
            }
        });
    }

    public void reportSessionStart() {
        auto(new Runnable() {
            public void run() {
                for (AnalyticsService reportSessionStart : MultiAnalyticsService.this.services) {
                    reportSessionStart.reportSessionStart();
                }
            }
        });
    }

    public void reportLogin(final String str) {
        auto(new Runnable() {
            public void run() {
                for (AnalyticsService reportLogin : MultiAnalyticsService.this.services) {
                    reportLogin.reportLogin(str);
                }
            }
        });
    }

    public void reportLogout(final String str) {
        auto(new Runnable() {
            public void run() {
                for (AnalyticsService reportLogout : MultiAnalyticsService.this.services) {
                    reportLogout.reportLogout(str);
                }
            }
        });
    }

    public void reportExerciseLogged(String str, int i, String str2, int i2, String str3, int i3) {
        final String str4 = str;
        final int i4 = i;
        final String str5 = str2;
        final int i5 = i2;
        final String str6 = str3;
        final int i6 = i3;
        AnonymousClass16 r0 = new Runnable() {
            public void run() {
                for (AnalyticsService reportExerciseLogged : MultiAnalyticsService.this.services) {
                    reportExerciseLogged.reportExerciseLogged(str4, i4, str5, i5, str6, i6);
                }
            }
        };
        auto(r0);
    }

    public void reportFoodLookup(final Map<String, String> map) {
        auto(new Runnable() {
            public void run() {
                for (AnalyticsService reportFoodLookup : MultiAnalyticsService.this.services) {
                    reportFoodLookup.reportFoodLookup(map);
                }
            }
        });
    }

    public void reportExperimentStart(final String str, final String str2) {
        auto(new Runnable() {
            public void run() {
                for (AnalyticsService reportExperimentStart : MultiAnalyticsService.this.services) {
                    reportExperimentStart.reportExperimentStart(str, str2);
                }
            }
        });
    }

    public void reportRequiredConsents(String str, int i, String[] strArr) {
        auto(new Runnable(str, i, strArr) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ int f$2;
            private final /* synthetic */ String[] f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                MultiAnalyticsService.lambda$reportRequiredConsents$1(MultiAnalyticsService.this, this.f$1, this.f$2, this.f$3);
            }
        });
    }

    public static /* synthetic */ void lambda$reportRequiredConsents$1(MultiAnalyticsService multiAnalyticsService, String str, int i, String[] strArr) {
        for (AnalyticsService reportRequiredConsents : multiAnalyticsService.services) {
            reportRequiredConsents.reportRequiredConsents(str, i, strArr);
        }
    }

    public void restartSession() {
        auto(new Runnable() {
            public void run() {
                for (AnalyticsService restartSession : MultiAnalyticsService.this.services) {
                    restartSession.restartSession();
                }
            }
        });
    }

    public List<AnalyticsService> getServices() {
        return new ArrayList(this.services);
    }
}
