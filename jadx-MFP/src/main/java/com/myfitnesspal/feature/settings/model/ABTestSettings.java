package com.myfitnesspal.feature.settings.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.constants.Constants.ABTest;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.FileUtils;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;

public class ABTestSettings {
    private static final ApiJsonMapper MAPPER = new ApiJsonMapper(FieldNamingPolicy.IDENTITY);
    private static final String VARIANT_OVERRIDE_PREFIX = "abtest.overrides.key_";
    private final Context context;
    private List<ABTestOverrideDesc> overrides;
    private final SharedPreferences prefs;

    public static final class ABTestOverrideDesc {
        @Expose
        private List<LegalVariant> legalVariants;
        @Expose
        private String name;
        @Expose
        private String prettyName;

        public static class LIST_MAPPER extends ArrayList<ABTestOverrideDesc> {
        }

        public static final class LegalVariant {
            @Expose
            private String displayName;
            @Expose
            private Map<String, String> properties;
            @Expose
            private String value;

            public LegalVariant() {
            }

            public LegalVariant(String str, String str2) {
                this.value = str;
                this.displayName = str2;
            }

            public String getDisplayName() {
                return this.displayName;
            }

            public void setDisplayName(String str) {
                this.displayName = str;
            }

            public String getValue() {
                return this.value;
            }

            public void setValue(String str) {
                this.value = str;
            }

            public Map<String, String> getProperties() {
                return this.properties;
            }

            public void setProperties(Map<String, String> map) {
                this.properties = map;
            }
        }

        public String getPrettyName() {
            return this.prettyName;
        }

        public void setPrettyName(String str) {
            this.prettyName = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public List<LegalVariant> getLegalVariants() {
            return this.legalVariants;
        }

        public void setLegalVariants(List<LegalVariant> list) {
            this.legalVariants = list;
        }
    }

    @Inject
    public ABTestSettings(Context context2, @Named("abtest-settings") SharedPreferences sharedPreferences) {
        this.context = context2;
        this.prefs = sharedPreferences;
    }

    public List<ABTestOverrideDesc> getOverrides() {
        ensureOverridesLoaded();
        return this.overrides;
    }

    private void ensureOverridesLoaded() {
        if (this.overrides == null) {
            this.overrides = (List) MAPPER.tryMapFrom(FileUtils.readAllLinesFromAsset(this.context, "config/abtests.json"), LIST_MAPPER.class);
            if (CollectionUtils.notEmpty((Collection<?>) this.overrides)) {
                for (ABTestOverrideDesc legalVariants : this.overrides) {
                    List legalVariants2 = legalVariants.getLegalVariants();
                    legalVariants2.add(0, new LegalVariant("false", "Not In Test"));
                    legalVariants2.add(0, new LegalVariant(ABTest.VARIANT_NO_OVERRIDE, "Not Overridden"));
                }
            }
        }
    }

    public boolean shouldOverride(String str) {
        String variantOverrideFor = getVariantOverrideFor(str, null);
        return Strings.notEmpty(variantOverrideFor) && !Strings.equals(variantOverrideFor, ABTest.VARIANT_NO_OVERRIDE);
    }

    public String getVariantOverrideFor(String str, String str2) {
        ensureOverridesLoaded();
        return this.prefs.getString(getKeyFor(str), str2);
    }

    public void setVariantOverrideFor(String str, String str2) {
        Editor edit = this.prefs.edit();
        String keyFor = getKeyFor(str);
        if (!Strings.notEmpty(str2)) {
            str2 = ABTest.VARIANT_NO_OVERRIDE;
        }
        edit.putString(keyFor, str2).commit();
    }

    public Map<String, String> getPropertyOverridesFor(String str, String str2) {
        if (CollectionUtils.notEmpty((Collection<?>) this.overrides)) {
            ABTestOverrideDesc currentTest = getCurrentTest(str);
            if (currentTest != null) {
                return getCurrentTestVariantProperties(currentTest.getLegalVariants(), str2);
            }
        }
        return null;
    }

    private ABTestOverrideDesc getCurrentTest(String str) {
        for (ABTestOverrideDesc aBTestOverrideDesc : this.overrides) {
            if (Strings.equals(aBTestOverrideDesc.getName(), str)) {
                return aBTestOverrideDesc;
            }
        }
        return null;
    }

    private Map<String, String> getCurrentTestVariantProperties(List<LegalVariant> list, String str) {
        for (LegalVariant legalVariant : list) {
            if (Strings.equals(legalVariant.getValue(), str)) {
                return legalVariant.getProperties();
            }
        }
        return null;
    }

    public String getKeyFor(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(VARIANT_OVERRIDE_PREFIX);
        sb.append(str);
        return sb.toString();
    }
}
