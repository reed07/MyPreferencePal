package com.google.type;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

public final class PostalAddress extends GeneratedMessageV3 implements PostalAddressOrBuilder {
    public static final int ADDRESS_LINES_FIELD_NUMBER = 9;
    public static final int ADMINISTRATIVE_AREA_FIELD_NUMBER = 6;
    private static final PostalAddress DEFAULT_INSTANCE = new PostalAddress();
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 3;
    public static final int LOCALITY_FIELD_NUMBER = 7;
    public static final int ORGANIZATION_FIELD_NUMBER = 11;
    /* access modifiers changed from: private */
    public static final Parser<PostalAddress> PARSER = new AbstractParser<PostalAddress>() {
        public PostalAddress parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new PostalAddress(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int POSTAL_CODE_FIELD_NUMBER = 4;
    public static final int RECIPIENTS_FIELD_NUMBER = 10;
    public static final int REGION_CODE_FIELD_NUMBER = 2;
    public static final int REVISION_FIELD_NUMBER = 1;
    public static final int SORTING_CODE_FIELD_NUMBER = 5;
    public static final int SUBLOCALITY_FIELD_NUMBER = 8;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public LazyStringList addressLines_;
    /* access modifiers changed from: private */
    public volatile Object administrativeArea_;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public volatile Object languageCode_;
    /* access modifiers changed from: private */
    public volatile Object locality_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object organization_;
    /* access modifiers changed from: private */
    public volatile Object postalCode_;
    /* access modifiers changed from: private */
    public LazyStringList recipients_;
    /* access modifiers changed from: private */
    public volatile Object regionCode_;
    /* access modifiers changed from: private */
    public int revision_;
    /* access modifiers changed from: private */
    public volatile Object sortingCode_;
    /* access modifiers changed from: private */
    public volatile Object sublocality_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements PostalAddressOrBuilder {
        private LazyStringList addressLines_;
        private Object administrativeArea_;
        private int bitField0_;
        private Object languageCode_;
        private Object locality_;
        private Object organization_;
        private Object postalCode_;
        private LazyStringList recipients_;
        private Object regionCode_;
        private int revision_;
        private Object sortingCode_;
        private Object sublocality_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return PostalAddressProto.internal_static_google_type_PostalAddress_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return PostalAddressProto.internal_static_google_type_PostalAddress_fieldAccessorTable.ensureFieldAccessorsInitialized(PostalAddress.class, Builder.class);
        }

        private Builder() {
            this.regionCode_ = "";
            this.languageCode_ = "";
            this.postalCode_ = "";
            this.sortingCode_ = "";
            this.administrativeArea_ = "";
            this.locality_ = "";
            this.sublocality_ = "";
            this.addressLines_ = LazyStringArrayList.EMPTY;
            this.recipients_ = LazyStringArrayList.EMPTY;
            this.organization_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.regionCode_ = "";
            this.languageCode_ = "";
            this.postalCode_ = "";
            this.sortingCode_ = "";
            this.administrativeArea_ = "";
            this.locality_ = "";
            this.sublocality_ = "";
            this.addressLines_ = LazyStringArrayList.EMPTY;
            this.recipients_ = LazyStringArrayList.EMPTY;
            this.organization_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            PostalAddress.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.revision_ = 0;
            this.regionCode_ = "";
            this.languageCode_ = "";
            this.postalCode_ = "";
            this.sortingCode_ = "";
            this.administrativeArea_ = "";
            this.locality_ = "";
            this.sublocality_ = "";
            this.addressLines_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -257;
            this.recipients_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -513;
            this.organization_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return PostalAddressProto.internal_static_google_type_PostalAddress_descriptor;
        }

        public PostalAddress getDefaultInstanceForType() {
            return PostalAddress.getDefaultInstance();
        }

        public PostalAddress build() {
            PostalAddress buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public PostalAddress buildPartial() {
            PostalAddress postalAddress = new PostalAddress((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            postalAddress.revision_ = this.revision_;
            postalAddress.regionCode_ = this.regionCode_;
            postalAddress.languageCode_ = this.languageCode_;
            postalAddress.postalCode_ = this.postalCode_;
            postalAddress.sortingCode_ = this.sortingCode_;
            postalAddress.administrativeArea_ = this.administrativeArea_;
            postalAddress.locality_ = this.locality_;
            postalAddress.sublocality_ = this.sublocality_;
            if ((this.bitField0_ & 256) == 256) {
                this.addressLines_ = this.addressLines_.getUnmodifiableView();
                this.bitField0_ &= -257;
            }
            postalAddress.addressLines_ = this.addressLines_;
            if ((this.bitField0_ & 512) == 512) {
                this.recipients_ = this.recipients_.getUnmodifiableView();
                this.bitField0_ &= -513;
            }
            postalAddress.recipients_ = this.recipients_;
            postalAddress.organization_ = this.organization_;
            postalAddress.bitField0_ = 0;
            onBuilt();
            return postalAddress;
        }

        public Builder clone() {
            return (Builder) super.clone();
        }

        public Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        public Builder clearField(FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        public Builder clearOneof(OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        public Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        public Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        public Builder mergeFrom(Message message) {
            if (message instanceof PostalAddress) {
                return mergeFrom((PostalAddress) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(PostalAddress postalAddress) {
            if (postalAddress == PostalAddress.getDefaultInstance()) {
                return this;
            }
            if (postalAddress.getRevision() != 0) {
                setRevision(postalAddress.getRevision());
            }
            if (!postalAddress.getRegionCode().isEmpty()) {
                this.regionCode_ = postalAddress.regionCode_;
                onChanged();
            }
            if (!postalAddress.getLanguageCode().isEmpty()) {
                this.languageCode_ = postalAddress.languageCode_;
                onChanged();
            }
            if (!postalAddress.getPostalCode().isEmpty()) {
                this.postalCode_ = postalAddress.postalCode_;
                onChanged();
            }
            if (!postalAddress.getSortingCode().isEmpty()) {
                this.sortingCode_ = postalAddress.sortingCode_;
                onChanged();
            }
            if (!postalAddress.getAdministrativeArea().isEmpty()) {
                this.administrativeArea_ = postalAddress.administrativeArea_;
                onChanged();
            }
            if (!postalAddress.getLocality().isEmpty()) {
                this.locality_ = postalAddress.locality_;
                onChanged();
            }
            if (!postalAddress.getSublocality().isEmpty()) {
                this.sublocality_ = postalAddress.sublocality_;
                onChanged();
            }
            if (!postalAddress.addressLines_.isEmpty()) {
                if (this.addressLines_.isEmpty()) {
                    this.addressLines_ = postalAddress.addressLines_;
                    this.bitField0_ &= -257;
                } else {
                    ensureAddressLinesIsMutable();
                    this.addressLines_.addAll(postalAddress.addressLines_);
                }
                onChanged();
            }
            if (!postalAddress.recipients_.isEmpty()) {
                if (this.recipients_.isEmpty()) {
                    this.recipients_ = postalAddress.recipients_;
                    this.bitField0_ &= -513;
                } else {
                    ensureRecipientsIsMutable();
                    this.recipients_.addAll(postalAddress.recipients_);
                }
                onChanged();
            }
            if (!postalAddress.getOrganization().isEmpty()) {
                this.organization_ = postalAddress.organization_;
                onChanged();
            }
            mergeUnknownFields(postalAddress.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.type.PostalAddress.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.type.PostalAddress.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.type.PostalAddress r3 = (com.google.type.PostalAddress) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                if (r3 == 0) goto L_0x0010
                r2.mergeFrom(r3)
            L_0x0010:
                return r2
            L_0x0011:
                r3 = move-exception
                goto L_0x0021
            L_0x0013:
                r3 = move-exception
                com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch:{ all -> 0x0011 }
                com.google.type.PostalAddress r4 = (com.google.type.PostalAddress) r4     // Catch:{ all -> 0x0011 }
                java.io.IOException r3 = r3.unwrapIOException()     // Catch:{ all -> 0x001f }
                throw r3     // Catch:{ all -> 0x001f }
            L_0x001f:
                r3 = move-exception
                r0 = r4
            L_0x0021:
                if (r0 == 0) goto L_0x0026
                r2.mergeFrom(r0)
            L_0x0026:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.type.PostalAddress.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.type.PostalAddress$Builder");
        }

        public int getRevision() {
            return this.revision_;
        }

        public Builder setRevision(int i) {
            this.revision_ = i;
            onChanged();
            return this;
        }

        public Builder clearRevision() {
            this.revision_ = 0;
            onChanged();
            return this;
        }

        public String getRegionCode() {
            Object obj = this.regionCode_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.regionCode_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getRegionCodeBytes() {
            Object obj = this.regionCode_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.regionCode_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setRegionCode(String str) {
            if (str != null) {
                this.regionCode_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearRegionCode() {
            this.regionCode_ = PostalAddress.getDefaultInstance().getRegionCode();
            onChanged();
            return this;
        }

        public Builder setRegionCodeBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                this.regionCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getLanguageCode() {
            Object obj = this.languageCode_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.languageCode_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getLanguageCodeBytes() {
            Object obj = this.languageCode_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.languageCode_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setLanguageCode(String str) {
            if (str != null) {
                this.languageCode_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearLanguageCode() {
            this.languageCode_ = PostalAddress.getDefaultInstance().getLanguageCode();
            onChanged();
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                this.languageCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getPostalCode() {
            Object obj = this.postalCode_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.postalCode_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getPostalCodeBytes() {
            Object obj = this.postalCode_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.postalCode_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setPostalCode(String str) {
            if (str != null) {
                this.postalCode_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearPostalCode() {
            this.postalCode_ = PostalAddress.getDefaultInstance().getPostalCode();
            onChanged();
            return this;
        }

        public Builder setPostalCodeBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                this.postalCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getSortingCode() {
            Object obj = this.sortingCode_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.sortingCode_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getSortingCodeBytes() {
            Object obj = this.sortingCode_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.sortingCode_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setSortingCode(String str) {
            if (str != null) {
                this.sortingCode_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearSortingCode() {
            this.sortingCode_ = PostalAddress.getDefaultInstance().getSortingCode();
            onChanged();
            return this;
        }

        public Builder setSortingCodeBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                this.sortingCode_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getAdministrativeArea() {
            Object obj = this.administrativeArea_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.administrativeArea_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getAdministrativeAreaBytes() {
            Object obj = this.administrativeArea_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.administrativeArea_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setAdministrativeArea(String str) {
            if (str != null) {
                this.administrativeArea_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearAdministrativeArea() {
            this.administrativeArea_ = PostalAddress.getDefaultInstance().getAdministrativeArea();
            onChanged();
            return this;
        }

        public Builder setAdministrativeAreaBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                this.administrativeArea_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getLocality() {
            Object obj = this.locality_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.locality_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getLocalityBytes() {
            Object obj = this.locality_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.locality_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setLocality(String str) {
            if (str != null) {
                this.locality_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearLocality() {
            this.locality_ = PostalAddress.getDefaultInstance().getLocality();
            onChanged();
            return this;
        }

        public Builder setLocalityBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                this.locality_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getSublocality() {
            Object obj = this.sublocality_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.sublocality_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getSublocalityBytes() {
            Object obj = this.sublocality_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.sublocality_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setSublocality(String str) {
            if (str != null) {
                this.sublocality_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearSublocality() {
            this.sublocality_ = PostalAddress.getDefaultInstance().getSublocality();
            onChanged();
            return this;
        }

        public Builder setSublocalityBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                this.sublocality_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureAddressLinesIsMutable() {
            if ((this.bitField0_ & 256) != 256) {
                this.addressLines_ = new LazyStringArrayList(this.addressLines_);
                this.bitField0_ |= 256;
            }
        }

        public ProtocolStringList getAddressLinesList() {
            return this.addressLines_.getUnmodifiableView();
        }

        public int getAddressLinesCount() {
            return this.addressLines_.size();
        }

        public String getAddressLines(int i) {
            return (String) this.addressLines_.get(i);
        }

        public ByteString getAddressLinesBytes(int i) {
            return this.addressLines_.getByteString(i);
        }

        public Builder setAddressLines(int i, String str) {
            if (str != null) {
                ensureAddressLinesIsMutable();
                this.addressLines_.set(i, str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addAddressLines(String str) {
            if (str != null) {
                ensureAddressLinesIsMutable();
                this.addressLines_.add(str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addAllAddressLines(Iterable<String> iterable) {
            ensureAddressLinesIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, (List<? super T>) this.addressLines_);
            onChanged();
            return this;
        }

        public Builder clearAddressLines() {
            this.addressLines_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -257;
            onChanged();
            return this;
        }

        public Builder addAddressLinesBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                ensureAddressLinesIsMutable();
                this.addressLines_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureRecipientsIsMutable() {
            if ((this.bitField0_ & 512) != 512) {
                this.recipients_ = new LazyStringArrayList(this.recipients_);
                this.bitField0_ |= 512;
            }
        }

        public ProtocolStringList getRecipientsList() {
            return this.recipients_.getUnmodifiableView();
        }

        public int getRecipientsCount() {
            return this.recipients_.size();
        }

        public String getRecipients(int i) {
            return (String) this.recipients_.get(i);
        }

        public ByteString getRecipientsBytes(int i) {
            return this.recipients_.getByteString(i);
        }

        public Builder setRecipients(int i, String str) {
            if (str != null) {
                ensureRecipientsIsMutable();
                this.recipients_.set(i, str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addRecipients(String str) {
            if (str != null) {
                ensureRecipientsIsMutable();
                this.recipients_.add(str);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder addAllRecipients(Iterable<String> iterable) {
            ensureRecipientsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, (List<? super T>) this.recipients_);
            onChanged();
            return this;
        }

        public Builder clearRecipients() {
            this.recipients_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -513;
            onChanged();
            return this;
        }

        public Builder addRecipientsBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                ensureRecipientsIsMutable();
                this.recipients_.add(byteString);
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getOrganization() {
            Object obj = this.organization_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.organization_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getOrganizationBytes() {
            Object obj = this.organization_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.organization_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setOrganization(String str) {
            if (str != null) {
                this.organization_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearOrganization() {
            this.organization_ = PostalAddress.getDefaultInstance().getOrganization();
            onChanged();
            return this;
        }

        public Builder setOrganizationBytes(ByteString byteString) {
            if (byteString != null) {
                PostalAddress.checkByteStringIsUtf8(byteString);
                this.organization_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private PostalAddress(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private PostalAddress() {
        this.memoizedIsInitialized = -1;
        this.revision_ = 0;
        this.regionCode_ = "";
        this.languageCode_ = "";
        this.postalCode_ = "";
        this.sortingCode_ = "";
        this.administrativeArea_ = "";
        this.locality_ = "";
        this.sublocality_ = "";
        this.addressLines_ = LazyStringArrayList.EMPTY;
        this.recipients_ = LazyStringArrayList.EMPTY;
        this.organization_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private PostalAddress(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            z = true;
                            break;
                        case 8:
                            this.revision_ = codedInputStream.readInt32();
                            break;
                        case 18:
                            this.regionCode_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 26:
                            this.languageCode_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 34:
                            this.postalCode_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 42:
                            this.sortingCode_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 50:
                            this.administrativeArea_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 58:
                            this.locality_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 66:
                            this.sublocality_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 74:
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if (!(z2 & true)) {
                                this.addressLines_ = new LazyStringArrayList();
                                z2 |= true;
                            }
                            this.addressLines_.add(readStringRequireUtf8);
                            break;
                        case 82:
                            String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                            if (!(z2 & true)) {
                                this.recipients_ = new LazyStringArrayList();
                                z2 |= true;
                            }
                            this.recipients_.add(readStringRequireUtf82);
                            break;
                        case 90:
                            this.organization_ = codedInputStream.readStringRequireUtf8();
                            break;
                        default:
                            if (parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                break;
                            } else {
                                z = true;
                                break;
                            }
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.addressLines_ = this.addressLines_.getUnmodifiableView();
                    }
                    if (z2 & true) {
                        this.recipients_ = this.recipients_.getUnmodifiableView();
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.addressLines_ = this.addressLines_.getUnmodifiableView();
            }
            if (z2 & true) {
                this.recipients_ = this.recipients_.getUnmodifiableView();
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return PostalAddressProto.internal_static_google_type_PostalAddress_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return PostalAddressProto.internal_static_google_type_PostalAddress_fieldAccessorTable.ensureFieldAccessorsInitialized(PostalAddress.class, Builder.class);
    }

    public int getRevision() {
        return this.revision_;
    }

    public String getRegionCode() {
        Object obj = this.regionCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.regionCode_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getRegionCodeBytes() {
        Object obj = this.regionCode_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.regionCode_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getLanguageCode() {
        Object obj = this.languageCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.languageCode_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getLanguageCodeBytes() {
        Object obj = this.languageCode_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.languageCode_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getPostalCode() {
        Object obj = this.postalCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.postalCode_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getPostalCodeBytes() {
        Object obj = this.postalCode_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.postalCode_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getSortingCode() {
        Object obj = this.sortingCode_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.sortingCode_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getSortingCodeBytes() {
        Object obj = this.sortingCode_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.sortingCode_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getAdministrativeArea() {
        Object obj = this.administrativeArea_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.administrativeArea_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getAdministrativeAreaBytes() {
        Object obj = this.administrativeArea_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.administrativeArea_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getLocality() {
        Object obj = this.locality_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.locality_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getLocalityBytes() {
        Object obj = this.locality_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.locality_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getSublocality() {
        Object obj = this.sublocality_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.sublocality_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getSublocalityBytes() {
        Object obj = this.sublocality_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.sublocality_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public ProtocolStringList getAddressLinesList() {
        return this.addressLines_;
    }

    public int getAddressLinesCount() {
        return this.addressLines_.size();
    }

    public String getAddressLines(int i) {
        return (String) this.addressLines_.get(i);
    }

    public ByteString getAddressLinesBytes(int i) {
        return this.addressLines_.getByteString(i);
    }

    public ProtocolStringList getRecipientsList() {
        return this.recipients_;
    }

    public int getRecipientsCount() {
        return this.recipients_.size();
    }

    public String getRecipients(int i) {
        return (String) this.recipients_.get(i);
    }

    public ByteString getRecipientsBytes(int i) {
        return this.recipients_.getByteString(i);
    }

    public String getOrganization() {
        Object obj = this.organization_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.organization_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getOrganizationBytes() {
        Object obj = this.organization_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.organization_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = 1;
        return true;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        int i = this.revision_;
        if (i != 0) {
            codedOutputStream.writeInt32(1, i);
        }
        if (!getRegionCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.regionCode_);
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.languageCode_);
        }
        if (!getPostalCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.postalCode_);
        }
        if (!getSortingCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.sortingCode_);
        }
        if (!getAdministrativeAreaBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.administrativeArea_);
        }
        if (!getLocalityBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.locality_);
        }
        if (!getSublocalityBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 8, this.sublocality_);
        }
        for (int i2 = 0; i2 < this.addressLines_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 9, this.addressLines_.getRaw(i2));
        }
        for (int i3 = 0; i3 < this.recipients_.size(); i3++) {
            GeneratedMessageV3.writeString(codedOutputStream, 10, this.recipients_.getRaw(i3));
        }
        if (!getOrganizationBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 11, this.organization_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = this.revision_;
        int computeInt32Size = i2 != 0 ? CodedOutputStream.computeInt32Size(1, i2) + 0 : 0;
        if (!getRegionCodeBytes().isEmpty()) {
            computeInt32Size += GeneratedMessageV3.computeStringSize(2, this.regionCode_);
        }
        if (!getLanguageCodeBytes().isEmpty()) {
            computeInt32Size += GeneratedMessageV3.computeStringSize(3, this.languageCode_);
        }
        if (!getPostalCodeBytes().isEmpty()) {
            computeInt32Size += GeneratedMessageV3.computeStringSize(4, this.postalCode_);
        }
        if (!getSortingCodeBytes().isEmpty()) {
            computeInt32Size += GeneratedMessageV3.computeStringSize(5, this.sortingCode_);
        }
        if (!getAdministrativeAreaBytes().isEmpty()) {
            computeInt32Size += GeneratedMessageV3.computeStringSize(6, this.administrativeArea_);
        }
        if (!getLocalityBytes().isEmpty()) {
            computeInt32Size += GeneratedMessageV3.computeStringSize(7, this.locality_);
        }
        if (!getSublocalityBytes().isEmpty()) {
            computeInt32Size += GeneratedMessageV3.computeStringSize(8, this.sublocality_);
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.addressLines_.size(); i4++) {
            i3 += computeStringSizeNoTag(this.addressLines_.getRaw(i4));
        }
        int size = computeInt32Size + i3 + (getAddressLinesList().size() * 1);
        int i5 = 0;
        for (int i6 = 0; i6 < this.recipients_.size(); i6++) {
            i5 += computeStringSizeNoTag(this.recipients_.getRaw(i6));
        }
        int size2 = size + i5 + (getRecipientsList().size() * 1);
        if (!getOrganizationBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(11, this.organization_);
        }
        int serializedSize = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PostalAddress)) {
            return super.equals(obj);
        }
        PostalAddress postalAddress = (PostalAddress) obj;
        if (!(((((((((((getRevision() == postalAddress.getRevision()) && getRegionCode().equals(postalAddress.getRegionCode())) && getLanguageCode().equals(postalAddress.getLanguageCode())) && getPostalCode().equals(postalAddress.getPostalCode())) && getSortingCode().equals(postalAddress.getSortingCode())) && getAdministrativeArea().equals(postalAddress.getAdministrativeArea())) && getLocality().equals(postalAddress.getLocality())) && getSublocality().equals(postalAddress.getSublocality())) && getAddressLinesList().equals(postalAddress.getAddressLinesList())) && getRecipientsList().equals(postalAddress.getRecipientsList())) && getOrganization().equals(postalAddress.getOrganization())) || !this.unknownFields.equals(postalAddress.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getRevision()) * 37) + 2) * 53) + getRegionCode().hashCode()) * 37) + 3) * 53) + getLanguageCode().hashCode()) * 37) + 4) * 53) + getPostalCode().hashCode()) * 37) + 5) * 53) + getSortingCode().hashCode()) * 37) + 6) * 53) + getAdministrativeArea().hashCode()) * 37) + 7) * 53) + getLocality().hashCode()) * 37) + 8) * 53) + getSublocality().hashCode();
        if (getAddressLinesCount() > 0) {
            hashCode = (((hashCode * 37) + 9) * 53) + getAddressLinesList().hashCode();
        }
        if (getRecipientsCount() > 0) {
            hashCode = (((hashCode * 37) + 10) * 53) + getRecipientsList().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 11) * 53) + getOrganization().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static PostalAddress parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (PostalAddress) PARSER.parseFrom(byteBuffer);
    }

    public static PostalAddress parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (PostalAddress) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static PostalAddress parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (PostalAddress) PARSER.parseFrom(byteString);
    }

    public static PostalAddress parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (PostalAddress) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static PostalAddress parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (PostalAddress) PARSER.parseFrom(bArr);
    }

    public static PostalAddress parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (PostalAddress) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static PostalAddress parseFrom(InputStream inputStream) throws IOException {
        return (PostalAddress) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static PostalAddress parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PostalAddress) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static PostalAddress parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (PostalAddress) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static PostalAddress parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PostalAddress) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static PostalAddress parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (PostalAddress) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static PostalAddress parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PostalAddress) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PostalAddress postalAddress) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(postalAddress);
    }

    public Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* access modifiers changed from: protected */
    public Builder newBuilderForType(BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static PostalAddress getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PostalAddress> parser() {
        return PARSER;
    }

    public Parser<PostalAddress> getParserForType() {
        return PARSER;
    }

    public PostalAddress getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
