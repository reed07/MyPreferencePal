package io.uacf.core.api;

import java.io.IOException;

public class UacfApiException extends IOException {
    private String body;
    private int statusCode;

    public UacfApiException(String str) {
        super(str);
    }

    public UacfApiException(String str, int i) {
        this(str);
        this.statusCode = i;
    }

    public UacfApiException(String str, Throwable th, int i) {
        super(str);
        initCause(th);
        this.statusCode = i;
    }

    public UacfApiException(String str, String str2) {
        this(str);
        this.body = str2;
    }

    public UacfApiException(String str, int i, String str2) {
        this(str, str2);
        this.statusCode = i;
    }

    public UacfApiException(Throwable th) {
        super(th);
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getBody() {
        return this.body;
    }

    public String toString() {
        String str;
        String iOException = super.toString();
        String str2 = "";
        if (this.statusCode > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append("statusCode: ");
            sb.append(this.statusCode);
            str2 = sb.toString();
        }
        String str3 = this.body;
        if (str3 != null && str3.length() > 0) {
            if (this.statusCode > 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str2);
                sb2.append(" ");
                str2 = sb2.toString();
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str2);
            sb3.append("body: ");
            sb3.append(this.body);
            str2 = sb3.toString();
        }
        StringBuilder sb4 = new StringBuilder();
        sb4.append(iOException);
        if (str2.isEmpty()) {
            str = "";
        } else {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(" {");
            sb5.append(str2);
            sb5.append("}");
            str = sb5.toString();
        }
        sb4.append(str);
        return sb4.toString();
    }
}
