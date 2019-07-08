package com.myfitnesspal.shared.service.strings;

import com.myfitnesspal.shared.service.install.CountryService;
import com.uacf.core.util.Strings;

public class GrammarServiceImpl implements GrammarService {
    private final CountryService countryService;

    public GrammarServiceImpl(CountryService countryService2) {
        this.countryService = countryService2;
    }

    public boolean isSpecialCaseForPossessive(String str) {
        boolean z = false;
        if (Strings.isEmpty(str)) {
            return false;
        }
        String currentLanguage = this.countryService.getCurrentLanguage();
        char charAt = str.toLowerCase().charAt(str.length() - 1);
        if (currentLanguage.equals("sv") || currentLanguage.equals("nb")) {
            if (charAt == 's' || charAt == 'x' || charAt == 'y') {
                z = true;
            }
            return z;
        } else if (!currentLanguage.equals("en")) {
            return false;
        } else {
            if (charAt == 's') {
                z = true;
            }
            return z;
        }
    }
}
