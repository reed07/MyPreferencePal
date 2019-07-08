package io.uacf.consentservices.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.uacf.core.util.Ln;
import io.uacf.core.api.UacfApiException;
import java.net.MalformedURLException;
import java.net.URL;

public class UacfConsent implements Parcelable {
    public static final Creator<UacfConsent> CREATOR = new Creator<UacfConsent>() {
        public UacfConsent createFromParcel(Parcel parcel) {
            return new UacfConsent(parcel);
        }

        public UacfConsent[] newArray(int i) {
            return new UacfConsent[i];
        }
    };
    private String contentSummary;
    private URL contentUrl;
    private String id;
    private boolean isAccepted;
    private boolean isRequired;
    private String title;

    static class Builder {
        private String contentSummary;
        private URL contentUrl;
        private String id;
        private boolean isAccepted;
        private boolean isRequired;
        private String title;

        Builder() {
        }

        /* access modifiers changed from: 0000 */
        public Builder setId(String str) throws UacfApiException {
            if (!TextUtils.isEmpty(str)) {
                this.id = str;
                return this;
            }
            throw new UacfApiException("Error parsing response");
        }

        /* access modifiers changed from: 0000 */
        public Builder setTitle(String str) throws UacfApiException {
            if (!TextUtils.isEmpty(str)) {
                this.title = str;
                return this;
            }
            throw new UacfApiException("Error parsing response");
        }

        /* access modifiers changed from: 0000 */
        public Builder setContentSummary(String str) throws UacfApiException {
            if (!TextUtils.isEmpty(str)) {
                this.contentSummary = str;
                return this;
            }
            throw new UacfApiException("Error parsing response");
        }

        /* access modifiers changed from: 0000 */
        public Builder setIsRequired(boolean z) throws UacfApiException {
            this.isRequired = z;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder setIsAccepted(boolean z) throws UacfApiException {
            this.isAccepted = z;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public Builder setContentUrl(String str) throws UacfApiException {
            try {
                this.contentUrl = new URL(str);
                return this;
            } catch (MalformedURLException e) {
                Ln.e(e);
                throw new UacfApiException("Error parsing content url");
            }
        }

        /* access modifiers changed from: 0000 */
        public UacfConsent build() {
            UacfConsent uacfConsent = new UacfConsent(this.id, this.title, this.contentSummary, this.contentUrl, this.isRequired, this.isAccepted);
            return uacfConsent;
        }
    }

    public int describeContents() {
        return 0;
    }

    private UacfConsent(String str, String str2, String str3, URL url, boolean z, boolean z2) {
        this.id = str;
        this.title = str2;
        this.contentSummary = str3;
        this.contentUrl = url;
        this.isRequired = z;
        this.isAccepted = z2;
    }

    protected UacfConsent(Parcel parcel) {
        this.id = parcel.readString();
        this.title = parcel.readString();
        this.contentUrl = (URL) parcel.readSerializable();
        this.contentSummary = parcel.readString();
        boolean z = true;
        this.isRequired = parcel.readByte() != 0;
        if (parcel.readByte() == 0) {
            z = false;
        }
        this.isAccepted = z;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContentSummary() {
        return this.contentSummary;
    }

    public URL getContentUrl() {
        return this.contentUrl;
    }

    public boolean isRequired() {
        return this.isRequired;
    }

    public boolean isAccepted() {
        return this.isAccepted;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.title);
        parcel.writeSerializable(this.contentUrl);
        parcel.writeString(this.contentSummary);
        parcel.writeByte(this.isRequired ? (byte) 1 : 0);
        parcel.writeByte(this.isAccepted ? (byte) 1 : 0);
    }
}
