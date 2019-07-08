package com.myfitnesspal.feature.blog.service;

import com.myfitnesspal.shared.service.install.CountryService;
import dagger.Lazy;
import java.util.HashSet;
import java.util.Set;

public class BlogServiceImpl implements BlogService {
    private static final String BRAZILIAN_PORTUGESE = "pt-br";
    private static final String GERMAN = "de-de";
    private Lazy<CountryService> countryService;
    final Set<String> supportedBlogLocales = new HashSet();

    public BlogServiceImpl(Lazy<CountryService> lazy) {
        this.countryService = lazy;
        this.supportedBlogLocales.add("pt-br");
        this.supportedBlogLocales.add("de-de");
    }

    public boolean isBlogEnabled() {
        return ((CountryService) this.countryService.get()).isEnglishCurrentDialect() || this.supportedBlogLocales.contains(((CountryService) this.countryService.get()).getCurrentLocaleIdentifier().toLowerCase());
    }
}
