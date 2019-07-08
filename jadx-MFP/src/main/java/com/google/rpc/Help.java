package com.google.rpc;

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
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Help extends GeneratedMessageV3 implements HelpOrBuilder {
    private static final Help DEFAULT_INSTANCE = new Help();
    public static final int LINKS_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final Parser<Help> PARSER = new AbstractParser<Help>() {
        public Help parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Help(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public List<Link> links_;
    private byte memoizedIsInitialized;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements HelpOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> linksBuilder_;
        private List<Link> links_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_fieldAccessorTable.ensureFieldAccessorsInitialized(Help.class, Builder.class);
        }

        private Builder() {
            this.links_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.links_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Help.alwaysUseFieldBuilders) {
                getLinksFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.links_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_descriptor;
        }

        public Help getDefaultInstanceForType() {
            return Help.getDefaultInstance();
        }

        public Help build() {
            Help buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Help buildPartial() {
            Help help = new Help((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.links_ = Collections.unmodifiableList(this.links_);
                    this.bitField0_ &= -2;
                }
                help.links_ = this.links_;
            } else {
                help.links_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return help;
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
            if (message instanceof Help) {
                return mergeFrom((Help) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Help help) {
            if (help == Help.getDefaultInstance()) {
                return this;
            }
            if (this.linksBuilder_ == null) {
                if (!help.links_.isEmpty()) {
                    if (this.links_.isEmpty()) {
                        this.links_ = help.links_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureLinksIsMutable();
                        this.links_.addAll(help.links_);
                    }
                    onChanged();
                }
            } else if (!help.links_.isEmpty()) {
                if (this.linksBuilder_.isEmpty()) {
                    this.linksBuilder_.dispose();
                    RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> repeatedFieldBuilderV3 = null;
                    this.linksBuilder_ = null;
                    this.links_ = help.links_;
                    this.bitField0_ &= -2;
                    if (Help.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getLinksFieldBuilder();
                    }
                    this.linksBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.linksBuilder_.addAllMessages(help.links_);
                }
            }
            mergeUnknownFields(help.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.rpc.Help.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.Help.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.rpc.Help r3 = (com.google.rpc.Help) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.rpc.Help r4 = (com.google.rpc.Help) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.Help.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.Help$Builder");
        }

        private void ensureLinksIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.links_ = new ArrayList(this.links_);
                this.bitField0_ |= 1;
            }
        }

        public List<Link> getLinksList() {
            RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.links_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getLinksCount() {
            RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.links_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Link getLinks(int i) {
            RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Link) this.links_.get(i);
            }
            return (Link) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setLinks(int i, Link link) {
            RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, link);
            } else if (link != null) {
                ensureLinksIsMutable();
                this.links_.set(i, link);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setLinks(int i, Builder builder) {
            RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLinksIsMutable();
                this.links_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addLinks(Link link) {
            RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(link);
            } else if (link != null) {
                ensureLinksIsMutable();
                this.links_.add(link);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addLinks(int i, Link link) {
            RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, link);
            } else if (link != null) {
                ensureLinksIsMutable();
                this.links_.add(i, link);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addLinks(Builder builder) {
            RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLinksIsMutable();
                this.links_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addLinks(int i, Builder builder) {
            RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLinksIsMutable();
                this.links_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllLinks(Iterable<? extends Link> iterable) {
            RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLinksIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.links_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearLinks() {
            RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.links_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeLinks(int i) {
            RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLinksIsMutable();
                this.links_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Builder getLinksBuilder(int i) {
            return (Builder) getLinksFieldBuilder().getBuilder(i);
        }

        public LinkOrBuilder getLinksOrBuilder(int i) {
            RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (LinkOrBuilder) this.links_.get(i);
            }
            return (LinkOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends LinkOrBuilder> getLinksOrBuilderList() {
            RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> repeatedFieldBuilderV3 = this.linksBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.links_);
        }

        public Builder addLinksBuilder() {
            return (Builder) getLinksFieldBuilder().addBuilder(Link.getDefaultInstance());
        }

        public Builder addLinksBuilder(int i) {
            return (Builder) getLinksFieldBuilder().addBuilder(i, Link.getDefaultInstance());
        }

        public List<Builder> getLinksBuilderList() {
            return getLinksFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Link, Builder, LinkOrBuilder> getLinksFieldBuilder() {
            if (this.linksBuilder_ == null) {
                List<Link> list = this.links_;
                boolean z = true;
                if ((this.bitField0_ & 1) != 1) {
                    z = false;
                }
                this.linksBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                this.links_ = null;
            }
            return this.linksBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public static final class Link extends GeneratedMessageV3 implements LinkOrBuilder {
        private static final Link DEFAULT_INSTANCE = new Link();
        public static final int DESCRIPTION_FIELD_NUMBER = 1;
        /* access modifiers changed from: private */
        public static final Parser<Link> PARSER = new AbstractParser<Link>() {
            public Link parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Link(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int URL_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public volatile Object description_;
        private byte memoizedIsInitialized;
        /* access modifiers changed from: private */
        public volatile Object url_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements LinkOrBuilder {
            private Object description_;
            private Object url_;

            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptor getDescriptor() {
                return ErrorDetailsProto.internal_static_google_rpc_Help_Link_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return ErrorDetailsProto.internal_static_google_rpc_Help_Link_fieldAccessorTable.ensureFieldAccessorsInitialized(Link.class, Builder.class);
            }

            private Builder() {
                this.description_ = "";
                this.url_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.description_ = "";
                this.url_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                Link.alwaysUseFieldBuilders;
            }

            public Builder clear() {
                super.clear();
                this.description_ = "";
                this.url_ = "";
                return this;
            }

            public Descriptor getDescriptorForType() {
                return ErrorDetailsProto.internal_static_google_rpc_Help_Link_descriptor;
            }

            public Link getDefaultInstanceForType() {
                return Link.getDefaultInstance();
            }

            public Link build() {
                Link buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Link buildPartial() {
                Link link = new Link((com.google.protobuf.GeneratedMessageV3.Builder) this);
                link.description_ = this.description_;
                link.url_ = this.url_;
                onBuilt();
                return link;
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
                if (message instanceof Link) {
                    return mergeFrom((Link) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Link link) {
                if (link == Link.getDefaultInstance()) {
                    return this;
                }
                if (!link.getDescription().isEmpty()) {
                    this.description_ = link.description_;
                    onChanged();
                }
                if (!link.getUrl().isEmpty()) {
                    this.url_ = link.url_;
                    onChanged();
                }
                mergeUnknownFields(link.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.rpc.Help.Link.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.google.rpc.Help.Link.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    com.google.rpc.Help$Link r3 = (com.google.rpc.Help.Link) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                    com.google.rpc.Help$Link r4 = (com.google.rpc.Help.Link) r4     // Catch:{ all -> 0x0011 }
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
                throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.Help.Link.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.Help$Link$Builder");
            }

            public String getDescription() {
                Object obj = this.description_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.description_ = stringUtf8;
                return stringUtf8;
            }

            public ByteString getDescriptionBytes() {
                Object obj = this.description_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.description_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setDescription(String str) {
                if (str != null) {
                    this.description_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearDescription() {
                this.description_ = Link.getDefaultInstance().getDescription();
                onChanged();
                return this;
            }

            public Builder setDescriptionBytes(ByteString byteString) {
                if (byteString != null) {
                    Link.checkByteStringIsUtf8(byteString);
                    this.description_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public String getUrl() {
                Object obj = this.url_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.url_ = stringUtf8;
                return stringUtf8;
            }

            public ByteString getUrlBytes() {
                Object obj = this.url_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.url_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setUrl(String str) {
                if (str != null) {
                    this.url_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearUrl() {
                this.url_ = Link.getDefaultInstance().getUrl();
                onChanged();
                return this;
            }

            public Builder setUrlBytes(ByteString byteString) {
                if (byteString != null) {
                    Link.checkByteStringIsUtf8(byteString);
                    this.url_ = byteString;
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

        private Link(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private Link() {
            this.memoizedIsInitialized = -1;
            this.description_ = "";
            this.url_ = "";
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Link(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag == 0) {
                            z = true;
                        } else if (readTag == 10) {
                            this.description_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 18) {
                            this.url_ = codedInputStream.readStringRequireUtf8();
                        } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            z = true;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
                return;
            }
            throw new NullPointerException();
        }

        public static final Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_Link_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_Help_Link_fieldAccessorTable.ensureFieldAccessorsInitialized(Link.class, Builder.class);
        }

        public String getDescription() {
            Object obj = this.description_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.description_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getDescriptionBytes() {
            Object obj = this.description_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.description_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public String getUrl() {
            Object obj = this.url_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.url_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getUrlBytes() {
            Object obj = this.url_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.url_ = copyFromUtf8;
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
            if (!getDescriptionBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.description_);
            }
            if (!getUrlBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.url_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!getDescriptionBytes().isEmpty()) {
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.description_);
            }
            if (!getUrlBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(2, this.url_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Link)) {
                return super.equals(obj);
            }
            Link link = (Link) obj;
            if (!((getDescription().equals(link.getDescription())) && getUrl().equals(link.getUrl())) || !this.unknownFields.equals(link.unknownFields)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getDescription().hashCode()) * 37) + 2) * 53) + getUrl().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static Link parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Link) PARSER.parseFrom(byteBuffer);
        }

        public static Link parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Link) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Link parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Link) PARSER.parseFrom(byteString);
        }

        public static Link parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Link) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Link parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Link) PARSER.parseFrom(bArr);
        }

        public static Link parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Link) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Link parseFrom(InputStream inputStream) throws IOException {
            return (Link) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Link parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Link) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Link parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Link) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Link parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Link) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Link parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Link) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Link parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Link) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Link link) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(link);
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

        public static Link getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Link> parser() {
            return PARSER;
        }

        public Parser<Link> getParserForType() {
            return PARSER;
        }

        public Link getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public interface LinkOrBuilder extends MessageOrBuilder {
        String getDescription();

        ByteString getDescriptionBytes();

        String getUrl();

        ByteString getUrlBytes();
    }

    private Help(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Help() {
        this.memoizedIsInitialized = -1;
        this.links_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Help(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (!z2 || !true) {
                            this.links_ = new ArrayList();
                            z2 |= true;
                        }
                        this.links_.add(codedInputStream.readMessage(Link.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.links_ = Collections.unmodifiableList(this.links_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.links_ = Collections.unmodifiableList(this.links_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_Help_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_Help_fieldAccessorTable.ensureFieldAccessorsInitialized(Help.class, Builder.class);
    }

    public List<Link> getLinksList() {
        return this.links_;
    }

    public List<? extends LinkOrBuilder> getLinksOrBuilderList() {
        return this.links_;
    }

    public int getLinksCount() {
        return this.links_.size();
    }

    public Link getLinks(int i) {
        return (Link) this.links_.get(i);
    }

    public LinkOrBuilder getLinksOrBuilder(int i) {
        return (LinkOrBuilder) this.links_.get(i);
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
        for (int i = 0; i < this.links_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.links_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.links_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.links_.get(i3));
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Help)) {
            return super.equals(obj);
        }
        Help help = (Help) obj;
        if (!(getLinksList().equals(help.getLinksList())) || !this.unknownFields.equals(help.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getLinksCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getLinksList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Help parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Help) PARSER.parseFrom(byteBuffer);
    }

    public static Help parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Help) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Help parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Help) PARSER.parseFrom(byteString);
    }

    public static Help parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Help) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Help parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Help) PARSER.parseFrom(bArr);
    }

    public static Help parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Help) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Help parseFrom(InputStream inputStream) throws IOException {
        return (Help) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Help parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Help) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Help parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Help) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Help parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Help) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Help parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Help) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Help parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Help) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Help help) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(help);
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

    public static Help getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Help> parser() {
        return PARSER;
    }

    public Parser<Help> getParserForType() {
        return PARSER;
    }

    public Help getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
