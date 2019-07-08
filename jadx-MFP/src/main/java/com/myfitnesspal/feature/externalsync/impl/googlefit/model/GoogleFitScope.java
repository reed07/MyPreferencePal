package com.myfitnesspal.feature.externalsync.impl.googlefit.model;

public final class GoogleFitScope {
    private String scope;

    public GoogleFitScope(String str) {
        if (str != null) {
            this.scope = str;
            return;
        }
        throw new IllegalArgumentException("Scope must not be null.");
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String str) {
        this.scope = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GoogleFitScope)) {
            return false;
        }
        return getScope().equals(((GoogleFitScope) obj).getScope());
    }

    public int hashCode() {
        return getScope().hashCode();
    }
}
