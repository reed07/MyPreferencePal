package com.google.api;

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
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Page extends GeneratedMessageV3 implements PageOrBuilder {
    public static final int CONTENT_FIELD_NUMBER = 2;
    private static final Page DEFAULT_INSTANCE = new Page();
    public static final int NAME_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final Parser<Page> PARSER = new AbstractParser<Page>() {
        public Page parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Page(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SUBPAGES_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public volatile Object content_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object name_;
    /* access modifiers changed from: private */
    public List<Page> subpages_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements PageOrBuilder {
        private int bitField0_;
        private Object content_;
        private Object name_;
        private RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> subpagesBuilder_;
        private List<Page> subpages_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return DocumentationProto.internal_static_google_api_Page_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return DocumentationProto.internal_static_google_api_Page_fieldAccessorTable.ensureFieldAccessorsInitialized(Page.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.content_ = "";
            this.subpages_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.content_ = "";
            this.subpages_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Page.alwaysUseFieldBuilders) {
                getSubpagesFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.content_ = "";
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.subpages_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return DocumentationProto.internal_static_google_api_Page_descriptor;
        }

        public Page getDefaultInstanceForType() {
            return Page.getDefaultInstance();
        }

        public Page build() {
            Page buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Page buildPartial() {
            Page page = new Page((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            page.name_ = this.name_;
            page.content_ = this.content_;
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 4) == 4) {
                    this.subpages_ = Collections.unmodifiableList(this.subpages_);
                    this.bitField0_ &= -5;
                }
                page.subpages_ = this.subpages_;
            } else {
                page.subpages_ = repeatedFieldBuilderV3.build();
            }
            page.bitField0_ = 0;
            onBuilt();
            return page;
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
            if (message instanceof Page) {
                return mergeFrom((Page) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Page page) {
            if (page == Page.getDefaultInstance()) {
                return this;
            }
            if (!page.getName().isEmpty()) {
                this.name_ = page.name_;
                onChanged();
            }
            if (!page.getContent().isEmpty()) {
                this.content_ = page.content_;
                onChanged();
            }
            if (this.subpagesBuilder_ == null) {
                if (!page.subpages_.isEmpty()) {
                    if (this.subpages_.isEmpty()) {
                        this.subpages_ = page.subpages_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureSubpagesIsMutable();
                        this.subpages_.addAll(page.subpages_);
                    }
                    onChanged();
                }
            } else if (!page.subpages_.isEmpty()) {
                if (this.subpagesBuilder_.isEmpty()) {
                    this.subpagesBuilder_.dispose();
                    RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = null;
                    this.subpagesBuilder_ = null;
                    this.subpages_ = page.subpages_;
                    this.bitField0_ &= -5;
                    if (Page.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getSubpagesFieldBuilder();
                    }
                    this.subpagesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.subpagesBuilder_.addAllMessages(page.subpages_);
                }
            }
            mergeUnknownFields(page.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.Page.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Page.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.Page r3 = (com.google.api.Page) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.Page r4 = (com.google.api.Page) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Page.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Page$Builder");
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setName(String str) {
            if (str != null) {
                this.name_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearName() {
            this.name_ = Page.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                Page.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getContent() {
            Object obj = this.content_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.content_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getContentBytes() {
            Object obj = this.content_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.content_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setContent(String str) {
            if (str != null) {
                this.content_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearContent() {
            this.content_ = Page.getDefaultInstance().getContent();
            onChanged();
            return this;
        }

        public Builder setContentBytes(ByteString byteString) {
            if (byteString != null) {
                Page.checkByteStringIsUtf8(byteString);
                this.content_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureSubpagesIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.subpages_ = new ArrayList(this.subpages_);
                this.bitField0_ |= 4;
            }
        }

        public List<Page> getSubpagesList() {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.subpages_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getSubpagesCount() {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.subpages_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Page getSubpages(int i) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Page) this.subpages_.get(i);
            }
            return (Page) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setSubpages(int i, Page page) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, page);
            } else if (page != null) {
                ensureSubpagesIsMutable();
                this.subpages_.set(i, page);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setSubpages(int i, Builder builder) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSubpagesIsMutable();
                this.subpages_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addSubpages(Page page) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(page);
            } else if (page != null) {
                ensureSubpagesIsMutable();
                this.subpages_.add(page);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addSubpages(int i, Page page) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, page);
            } else if (page != null) {
                ensureSubpagesIsMutable();
                this.subpages_.add(i, page);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addSubpages(Builder builder) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSubpagesIsMutable();
                this.subpages_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addSubpages(int i, Builder builder) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSubpagesIsMutable();
                this.subpages_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllSubpages(Iterable<? extends Page> iterable) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSubpagesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.subpages_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearSubpages() {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.subpages_ = Collections.emptyList();
                this.bitField0_ &= -5;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeSubpages(int i) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSubpagesIsMutable();
                this.subpages_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Builder getSubpagesBuilder(int i) {
            return (Builder) getSubpagesFieldBuilder().getBuilder(i);
        }

        public PageOrBuilder getSubpagesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (PageOrBuilder) this.subpages_.get(i);
            }
            return (PageOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends PageOrBuilder> getSubpagesOrBuilderList() {
            RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.subpagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.subpages_);
        }

        public Builder addSubpagesBuilder() {
            return (Builder) getSubpagesFieldBuilder().addBuilder(Page.getDefaultInstance());
        }

        public Builder addSubpagesBuilder(int i) {
            return (Builder) getSubpagesFieldBuilder().addBuilder(i, Page.getDefaultInstance());
        }

        public List<Builder> getSubpagesBuilderList() {
            return getSubpagesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Page, Builder, PageOrBuilder> getSubpagesFieldBuilder() {
            if (this.subpagesBuilder_ == null) {
                this.subpagesBuilder_ = new RepeatedFieldBuilderV3<>(this.subpages_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                this.subpages_ = null;
            }
            return this.subpagesBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private Page(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Page() {
        this.memoizedIsInitialized = -1;
        this.name_ = "";
        this.content_ = "";
        this.subpages_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Page(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag == 0) {
                        z = true;
                    } else if (readTag == 10) {
                        this.name_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        this.content_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 26) {
                        if (!(z2 & true)) {
                            this.subpages_ = new ArrayList();
                            z2 |= true;
                        }
                        this.subpages_.add(codedInputStream.readMessage(parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.subpages_ = Collections.unmodifiableList(this.subpages_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.subpages_ = Collections.unmodifiableList(this.subpages_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return DocumentationProto.internal_static_google_api_Page_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return DocumentationProto.internal_static_google_api_Page_fieldAccessorTable.ensureFieldAccessorsInitialized(Page.class, Builder.class);
    }

    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.name_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getContent() {
        Object obj = this.content_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.content_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getContentBytes() {
        Object obj = this.content_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.content_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public List<Page> getSubpagesList() {
        return this.subpages_;
    }

    public List<? extends PageOrBuilder> getSubpagesOrBuilderList() {
        return this.subpages_;
    }

    public int getSubpagesCount() {
        return this.subpages_.size();
    }

    public Page getSubpages(int i) {
        return (Page) this.subpages_.get(i);
    }

    public PageOrBuilder getSubpagesOrBuilder(int i) {
        return (PageOrBuilder) this.subpages_.get(i);
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
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (!getContentBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.content_);
        }
        for (int i = 0; i < this.subpages_.size(); i++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.subpages_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) + 0 : 0;
        if (!getContentBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.content_);
        }
        for (int i2 = 0; i2 < this.subpages_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(3, (MessageLite) this.subpages_.get(i2));
        }
        int serializedSize = computeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Page)) {
            return super.equals(obj);
        }
        Page page = (Page) obj;
        if (!(((getName().equals(page.getName())) && getContent().equals(page.getContent())) && getSubpagesList().equals(page.getSubpagesList())) || !this.unknownFields.equals(page.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getContent().hashCode();
        if (getSubpagesCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getSubpagesList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Page parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Page) PARSER.parseFrom(byteBuffer);
    }

    public static Page parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Page) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Page parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Page) PARSER.parseFrom(byteString);
    }

    public static Page parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Page) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Page parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Page) PARSER.parseFrom(bArr);
    }

    public static Page parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Page) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Page parseFrom(InputStream inputStream) throws IOException {
        return (Page) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Page parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Page) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Page parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Page) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Page parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Page) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Page parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Page) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Page parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Page) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Page page) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(page);
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

    public static Page getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Page> parser() {
        return PARSER;
    }

    public Parser<Page> getParserForType() {
        return PARSER;
    }

    public Page getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
