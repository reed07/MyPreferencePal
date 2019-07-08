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

public final class Documentation extends GeneratedMessageV3 implements DocumentationOrBuilder {
    private static final Documentation DEFAULT_INSTANCE = new Documentation();
    public static final int DOCUMENTATION_ROOT_URL_FIELD_NUMBER = 4;
    public static final int OVERVIEW_FIELD_NUMBER = 2;
    public static final int PAGES_FIELD_NUMBER = 5;
    /* access modifiers changed from: private */
    public static final Parser<Documentation> PARSER = new AbstractParser<Documentation>() {
        public Documentation parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Documentation(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int RULES_FIELD_NUMBER = 3;
    public static final int SUMMARY_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public volatile Object documentationRootUrl_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object overview_;
    /* access modifiers changed from: private */
    public List<Page> pages_;
    /* access modifiers changed from: private */
    public List<DocumentationRule> rules_;
    /* access modifiers changed from: private */
    public volatile Object summary_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements DocumentationOrBuilder {
        private int bitField0_;
        private Object documentationRootUrl_;
        private Object overview_;
        private RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> pagesBuilder_;
        private List<Page> pages_;
        private RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> rulesBuilder_;
        private List<DocumentationRule> rules_;
        private Object summary_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return DocumentationProto.internal_static_google_api_Documentation_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return DocumentationProto.internal_static_google_api_Documentation_fieldAccessorTable.ensureFieldAccessorsInitialized(Documentation.class, Builder.class);
        }

        private Builder() {
            this.summary_ = "";
            this.pages_ = Collections.emptyList();
            this.rules_ = Collections.emptyList();
            this.documentationRootUrl_ = "";
            this.overview_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.summary_ = "";
            this.pages_ = Collections.emptyList();
            this.rules_ = Collections.emptyList();
            this.documentationRootUrl_ = "";
            this.overview_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Documentation.alwaysUseFieldBuilders) {
                getPagesFieldBuilder();
                getRulesFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.summary_ = "";
            RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.pages_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV32 = this.rulesBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            this.documentationRootUrl_ = "";
            this.overview_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return DocumentationProto.internal_static_google_api_Documentation_descriptor;
        }

        public Documentation getDefaultInstanceForType() {
            return Documentation.getDefaultInstance();
        }

        public Documentation build() {
            Documentation buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Documentation buildPartial() {
            Documentation documentation = new Documentation((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            documentation.summary_ = this.summary_;
            RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.pages_ = Collections.unmodifiableList(this.pages_);
                    this.bitField0_ &= -3;
                }
                documentation.pages_ = this.pages_;
            } else {
                documentation.pages_ = repeatedFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV32 = this.rulesBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 4) == 4) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                    this.bitField0_ &= -5;
                }
                documentation.rules_ = this.rules_;
            } else {
                documentation.rules_ = repeatedFieldBuilderV32.build();
            }
            documentation.documentationRootUrl_ = this.documentationRootUrl_;
            documentation.overview_ = this.overview_;
            documentation.bitField0_ = 0;
            onBuilt();
            return documentation;
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
            if (message instanceof Documentation) {
                return mergeFrom((Documentation) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Documentation documentation) {
            if (documentation == Documentation.getDefaultInstance()) {
                return this;
            }
            if (!documentation.getSummary().isEmpty()) {
                this.summary_ = documentation.summary_;
                onChanged();
            }
            RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = null;
            if (this.pagesBuilder_ == null) {
                if (!documentation.pages_.isEmpty()) {
                    if (this.pages_.isEmpty()) {
                        this.pages_ = documentation.pages_;
                        this.bitField0_ &= -3;
                    } else {
                        ensurePagesIsMutable();
                        this.pages_.addAll(documentation.pages_);
                    }
                    onChanged();
                }
            } else if (!documentation.pages_.isEmpty()) {
                if (this.pagesBuilder_.isEmpty()) {
                    this.pagesBuilder_.dispose();
                    this.pagesBuilder_ = null;
                    this.pages_ = documentation.pages_;
                    this.bitField0_ &= -3;
                    this.pagesBuilder_ = Documentation.alwaysUseFieldBuilders ? getPagesFieldBuilder() : null;
                } else {
                    this.pagesBuilder_.addAllMessages(documentation.pages_);
                }
            }
            if (this.rulesBuilder_ == null) {
                if (!documentation.rules_.isEmpty()) {
                    if (this.rules_.isEmpty()) {
                        this.rules_ = documentation.rules_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureRulesIsMutable();
                        this.rules_.addAll(documentation.rules_);
                    }
                    onChanged();
                }
            } else if (!documentation.rules_.isEmpty()) {
                if (this.rulesBuilder_.isEmpty()) {
                    this.rulesBuilder_.dispose();
                    this.rulesBuilder_ = null;
                    this.rules_ = documentation.rules_;
                    this.bitField0_ &= -5;
                    if (Documentation.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getRulesFieldBuilder();
                    }
                    this.rulesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.rulesBuilder_.addAllMessages(documentation.rules_);
                }
            }
            if (!documentation.getDocumentationRootUrl().isEmpty()) {
                this.documentationRootUrl_ = documentation.documentationRootUrl_;
                onChanged();
            }
            if (!documentation.getOverview().isEmpty()) {
                this.overview_ = documentation.overview_;
                onChanged();
            }
            mergeUnknownFields(documentation.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.Documentation.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Documentation.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.Documentation r3 = (com.google.api.Documentation) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.Documentation r4 = (com.google.api.Documentation) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Documentation.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Documentation$Builder");
        }

        public String getSummary() {
            Object obj = this.summary_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.summary_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getSummaryBytes() {
            Object obj = this.summary_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.summary_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setSummary(String str) {
            if (str != null) {
                this.summary_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearSummary() {
            this.summary_ = Documentation.getDefaultInstance().getSummary();
            onChanged();
            return this;
        }

        public Builder setSummaryBytes(ByteString byteString) {
            if (byteString != null) {
                Documentation.checkByteStringIsUtf8(byteString);
                this.summary_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensurePagesIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.pages_ = new ArrayList(this.pages_);
                this.bitField0_ |= 2;
            }
        }

        public List<Page> getPagesList() {
            RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.pages_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getPagesCount() {
            RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.pages_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Page getPages(int i) {
            RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Page) this.pages_.get(i);
            }
            return (Page) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setPages(int i, Page page) {
            RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, page);
            } else if (page != null) {
                ensurePagesIsMutable();
                this.pages_.set(i, page);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setPages(int i, com.google.api.Page.Builder builder) {
            RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePagesIsMutable();
                this.pages_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addPages(Page page) {
            RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(page);
            } else if (page != null) {
                ensurePagesIsMutable();
                this.pages_.add(page);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addPages(int i, Page page) {
            RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, page);
            } else if (page != null) {
                ensurePagesIsMutable();
                this.pages_.add(i, page);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addPages(com.google.api.Page.Builder builder) {
            RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePagesIsMutable();
                this.pages_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addPages(int i, com.google.api.Page.Builder builder) {
            RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePagesIsMutable();
                this.pages_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllPages(Iterable<? extends Page> iterable) {
            RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePagesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.pages_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearPages() {
            RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.pages_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removePages(int i) {
            RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePagesIsMutable();
                this.pages_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.Page.Builder getPagesBuilder(int i) {
            return (com.google.api.Page.Builder) getPagesFieldBuilder().getBuilder(i);
        }

        public PageOrBuilder getPagesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (PageOrBuilder) this.pages_.get(i);
            }
            return (PageOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends PageOrBuilder> getPagesOrBuilderList() {
            RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> repeatedFieldBuilderV3 = this.pagesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.pages_);
        }

        public com.google.api.Page.Builder addPagesBuilder() {
            return (com.google.api.Page.Builder) getPagesFieldBuilder().addBuilder(Page.getDefaultInstance());
        }

        public com.google.api.Page.Builder addPagesBuilder(int i) {
            return (com.google.api.Page.Builder) getPagesFieldBuilder().addBuilder(i, Page.getDefaultInstance());
        }

        public List<com.google.api.Page.Builder> getPagesBuilderList() {
            return getPagesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Page, com.google.api.Page.Builder, PageOrBuilder> getPagesFieldBuilder() {
            if (this.pagesBuilder_ == null) {
                this.pagesBuilder_ = new RepeatedFieldBuilderV3<>(this.pages_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                this.pages_ = null;
            }
            return this.pagesBuilder_;
        }

        private void ensureRulesIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.rules_ = new ArrayList(this.rules_);
                this.bitField0_ |= 4;
            }
        }

        public List<DocumentationRule> getRulesList() {
            RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.rules_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getRulesCount() {
            RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public DocumentationRule getRules(int i) {
            RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (DocumentationRule) this.rules_.get(i);
            }
            return (DocumentationRule) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRules(int i, DocumentationRule documentationRule) {
            RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, documentationRule);
            } else if (documentationRule != null) {
                ensureRulesIsMutable();
                this.rules_.set(i, documentationRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setRules(int i, com.google.api.DocumentationRule.Builder builder) {
            RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addRules(DocumentationRule documentationRule) {
            RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(documentationRule);
            } else if (documentationRule != null) {
                ensureRulesIsMutable();
                this.rules_.add(documentationRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addRules(int i, DocumentationRule documentationRule) {
            RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, documentationRule);
            } else if (documentationRule != null) {
                ensureRulesIsMutable();
                this.rules_.add(i, documentationRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addRules(com.google.api.DocumentationRule.Builder builder) {
            RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addRules(int i, com.google.api.DocumentationRule.Builder builder) {
            RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllRules(Iterable<? extends DocumentationRule> iterable) {
            RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.rules_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearRules() {
            RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -5;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRules(int i) {
            RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.DocumentationRule.Builder getRulesBuilder(int i) {
            return (com.google.api.DocumentationRule.Builder) getRulesFieldBuilder().getBuilder(i);
        }

        public DocumentationRuleOrBuilder getRulesOrBuilder(int i) {
            RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (DocumentationRuleOrBuilder) this.rules_.get(i);
            }
            return (DocumentationRuleOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends DocumentationRuleOrBuilder> getRulesOrBuilderList() {
            RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.rules_);
        }

        public com.google.api.DocumentationRule.Builder addRulesBuilder() {
            return (com.google.api.DocumentationRule.Builder) getRulesFieldBuilder().addBuilder(DocumentationRule.getDefaultInstance());
        }

        public com.google.api.DocumentationRule.Builder addRulesBuilder(int i) {
            return (com.google.api.DocumentationRule.Builder) getRulesFieldBuilder().addBuilder(i, DocumentationRule.getDefaultInstance());
        }

        public List<com.google.api.DocumentationRule.Builder> getRulesBuilderList() {
            return getRulesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<DocumentationRule, com.google.api.DocumentationRule.Builder, DocumentationRuleOrBuilder> getRulesFieldBuilder() {
            if (this.rulesBuilder_ == null) {
                this.rulesBuilder_ = new RepeatedFieldBuilderV3<>(this.rules_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                this.rules_ = null;
            }
            return this.rulesBuilder_;
        }

        public String getDocumentationRootUrl() {
            Object obj = this.documentationRootUrl_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.documentationRootUrl_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getDocumentationRootUrlBytes() {
            Object obj = this.documentationRootUrl_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.documentationRootUrl_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setDocumentationRootUrl(String str) {
            if (str != null) {
                this.documentationRootUrl_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearDocumentationRootUrl() {
            this.documentationRootUrl_ = Documentation.getDefaultInstance().getDocumentationRootUrl();
            onChanged();
            return this;
        }

        public Builder setDocumentationRootUrlBytes(ByteString byteString) {
            if (byteString != null) {
                Documentation.checkByteStringIsUtf8(byteString);
                this.documentationRootUrl_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getOverview() {
            Object obj = this.overview_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.overview_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getOverviewBytes() {
            Object obj = this.overview_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.overview_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setOverview(String str) {
            if (str != null) {
                this.overview_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearOverview() {
            this.overview_ = Documentation.getDefaultInstance().getOverview();
            onChanged();
            return this;
        }

        public Builder setOverviewBytes(ByteString byteString) {
            if (byteString != null) {
                Documentation.checkByteStringIsUtf8(byteString);
                this.overview_ = byteString;
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

    private Documentation(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Documentation() {
        this.memoizedIsInitialized = -1;
        this.summary_ = "";
        this.pages_ = Collections.emptyList();
        this.rules_ = Collections.emptyList();
        this.documentationRootUrl_ = "";
        this.overview_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Documentation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.summary_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        this.overview_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 26) {
                        if (!(z2 & true)) {
                            this.rules_ = new ArrayList();
                            z2 |= true;
                        }
                        this.rules_.add(codedInputStream.readMessage(DocumentationRule.parser(), extensionRegistryLite));
                    } else if (readTag == 34) {
                        this.documentationRootUrl_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 42) {
                        if (!(z2 & true)) {
                            this.pages_ = new ArrayList();
                            z2 |= true;
                        }
                        this.pages_.add(codedInputStream.readMessage(Page.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.rules_ = Collections.unmodifiableList(this.rules_);
                    }
                    if (z2 & true) {
                        this.pages_ = Collections.unmodifiableList(this.pages_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.rules_ = Collections.unmodifiableList(this.rules_);
            }
            if (z2 & true) {
                this.pages_ = Collections.unmodifiableList(this.pages_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return DocumentationProto.internal_static_google_api_Documentation_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return DocumentationProto.internal_static_google_api_Documentation_fieldAccessorTable.ensureFieldAccessorsInitialized(Documentation.class, Builder.class);
    }

    public String getSummary() {
        Object obj = this.summary_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.summary_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getSummaryBytes() {
        Object obj = this.summary_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.summary_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public List<Page> getPagesList() {
        return this.pages_;
    }

    public List<? extends PageOrBuilder> getPagesOrBuilderList() {
        return this.pages_;
    }

    public int getPagesCount() {
        return this.pages_.size();
    }

    public Page getPages(int i) {
        return (Page) this.pages_.get(i);
    }

    public PageOrBuilder getPagesOrBuilder(int i) {
        return (PageOrBuilder) this.pages_.get(i);
    }

    public List<DocumentationRule> getRulesList() {
        return this.rules_;
    }

    public List<? extends DocumentationRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    public int getRulesCount() {
        return this.rules_.size();
    }

    public DocumentationRule getRules(int i) {
        return (DocumentationRule) this.rules_.get(i);
    }

    public DocumentationRuleOrBuilder getRulesOrBuilder(int i) {
        return (DocumentationRuleOrBuilder) this.rules_.get(i);
    }

    public String getDocumentationRootUrl() {
        Object obj = this.documentationRootUrl_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.documentationRootUrl_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getDocumentationRootUrlBytes() {
        Object obj = this.documentationRootUrl_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.documentationRootUrl_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getOverview() {
        Object obj = this.overview_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.overview_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getOverviewBytes() {
        Object obj = this.overview_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.overview_ = copyFromUtf8;
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
        if (!getSummaryBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.summary_);
        }
        if (!getOverviewBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.overview_);
        }
        for (int i = 0; i < this.rules_.size(); i++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.rules_.get(i));
        }
        if (!getDocumentationRootUrlBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.documentationRootUrl_);
        }
        for (int i2 = 0; i2 < this.pages_.size(); i2++) {
            codedOutputStream.writeMessage(5, (MessageLite) this.pages_.get(i2));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getSummaryBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.summary_) + 0 : 0;
        if (!getOverviewBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.overview_);
        }
        int i2 = computeStringSize;
        for (int i3 = 0; i3 < this.rules_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(3, (MessageLite) this.rules_.get(i3));
        }
        if (!getDocumentationRootUrlBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(4, this.documentationRootUrl_);
        }
        for (int i4 = 0; i4 < this.pages_.size(); i4++) {
            i2 += CodedOutputStream.computeMessageSize(5, (MessageLite) this.pages_.get(i4));
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
        if (!(obj instanceof Documentation)) {
            return super.equals(obj);
        }
        Documentation documentation = (Documentation) obj;
        if (!(((((getSummary().equals(documentation.getSummary())) && getPagesList().equals(documentation.getPagesList())) && getRulesList().equals(documentation.getRulesList())) && getDocumentationRootUrl().equals(documentation.getDocumentationRootUrl())) && getOverview().equals(documentation.getOverview())) || !this.unknownFields.equals(documentation.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSummary().hashCode();
        if (getPagesCount() > 0) {
            hashCode = (((hashCode * 37) + 5) * 53) + getPagesList().hashCode();
        }
        if (getRulesCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getRulesList().hashCode();
        }
        int hashCode2 = (((((((((hashCode * 37) + 4) * 53) + getDocumentationRootUrl().hashCode()) * 37) + 2) * 53) + getOverview().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Documentation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Documentation) PARSER.parseFrom(byteBuffer);
    }

    public static Documentation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Documentation) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Documentation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Documentation) PARSER.parseFrom(byteString);
    }

    public static Documentation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Documentation) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Documentation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Documentation) PARSER.parseFrom(bArr);
    }

    public static Documentation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Documentation) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Documentation parseFrom(InputStream inputStream) throws IOException {
        return (Documentation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Documentation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Documentation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Documentation parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Documentation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Documentation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Documentation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Documentation parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Documentation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Documentation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Documentation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Documentation documentation) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(documentation);
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

    public static Documentation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Documentation> parser() {
        return PARSER;
    }

    public Parser<Documentation> getParserForType() {
        return PARSER;
    }

    public Documentation getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
