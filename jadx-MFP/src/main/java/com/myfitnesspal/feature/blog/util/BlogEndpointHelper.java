package com.myfitnesspal.feature.blog.util;

import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.constants.Environment.Blog;
import com.myfitnesspal.shared.service.install.CountryService;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;

public class BlogEndpointHelper {
    public static final String BLOG_HOST_BR = "blog.myfitnesspal.com.br";
    public static final String BLOG_HOST_DE = "blog.myfitnesspal.de";
    public static final String BLOG_HOST_EN = "blog.myfitnesspal.com";
    public static final String BLOG_URL_BR = "http://blog.myfitnesspal.com.br/";
    public static final String BLOG_URL_DE = "http://blog.myfitnesspal.de/";
    public static final String BLOG_URL_EN = "http://blog.myfitnesspal.com/";
    public static final String BRAZILIAN_PORTUGESE = "pt-br";
    public static final String GERMAN = "de-de";
    private String baseUrlFromApiProvider;
    String currentLocaleIdentifier;
    public String host;
    private Map<String, BlogEndpointHelper> localeToEndpointMap;
    public String url;

    public BlogEndpointHelper(Lazy<CountryService> lazy, Lazy<ApiUrlProvider> lazy2) {
        this.localeToEndpointMap = new HashMap();
        this.localeToEndpointMap.put(BRAZILIAN_PORTUGESE, new BlogEndpointHelper(BLOG_HOST_BR, BLOG_URL_BR));
        this.localeToEndpointMap.put(GERMAN, new BlogEndpointHelper(BLOG_HOST_DE, BLOG_URL_DE));
        this.currentLocaleIdentifier = ((CountryService) lazy.get()).getCurrentLocaleIdentifier().toLowerCase();
        this.baseUrlFromApiProvider = ((ApiUrlProvider) lazy2.get()).getBaseUrlForBlog();
    }

    private BlogEndpointHelper(String str, String str2) {
        this.host = str;
        this.url = str2;
    }

    private String getHost() {
        return this.host;
    }

    private String getUrl() {
        return this.url;
    }

    public String getBlogUrlForCurrentLocale() {
        if (Strings.equalsIgnoreCase(this.baseUrlFromApiProvider, Blog.INTEG)) {
            return this.baseUrlFromApiProvider;
        }
        return this.localeToEndpointMap.containsKey(this.currentLocaleIdentifier) ? ((BlogEndpointHelper) this.localeToEndpointMap.get(this.currentLocaleIdentifier)).getUrl() : BLOG_URL_EN;
    }

    public String getBlogHostForCurrentLocale() {
        return this.localeToEndpointMap.containsKey(this.currentLocaleIdentifier) ? ((BlogEndpointHelper) this.localeToEndpointMap.get(this.currentLocaleIdentifier)).getHost() : BLOG_HOST_EN;
    }
}
