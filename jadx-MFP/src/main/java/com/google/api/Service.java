package com.google.api;

import com.brightcove.player.C;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Api;
import com.google.protobuf.ApiOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.Enum;
import com.google.protobuf.EnumOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Type;
import com.google.protobuf.TypeOrBuilder;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt32ValueOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Service extends GeneratedMessageV3 implements ServiceOrBuilder {
    public static final int APIS_FIELD_NUMBER = 3;
    public static final int AUTHENTICATION_FIELD_NUMBER = 11;
    public static final int BACKEND_FIELD_NUMBER = 8;
    public static final int BILLING_FIELD_NUMBER = 26;
    public static final int CONFIG_VERSION_FIELD_NUMBER = 20;
    public static final int CONTEXT_FIELD_NUMBER = 12;
    public static final int CONTROL_FIELD_NUMBER = 21;
    private static final Service DEFAULT_INSTANCE = new Service();
    public static final int DOCUMENTATION_FIELD_NUMBER = 6;
    public static final int ENDPOINTS_FIELD_NUMBER = 18;
    public static final int ENUMS_FIELD_NUMBER = 5;
    public static final int EXPERIMENTAL_FIELD_NUMBER = 101;
    public static final int HTTP_FIELD_NUMBER = 9;
    public static final int ID_FIELD_NUMBER = 33;
    public static final int LOGGING_FIELD_NUMBER = 27;
    public static final int LOGS_FIELD_NUMBER = 23;
    public static final int METRICS_FIELD_NUMBER = 24;
    public static final int MONITORED_RESOURCES_FIELD_NUMBER = 25;
    public static final int MONITORING_FIELD_NUMBER = 28;
    public static final int NAME_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final Parser<Service> PARSER = new AbstractParser<Service>() {
        public Service parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Service(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PRODUCER_PROJECT_ID_FIELD_NUMBER = 22;
    public static final int QUOTA_FIELD_NUMBER = 10;
    public static final int SOURCE_INFO_FIELD_NUMBER = 37;
    public static final int SYSTEM_PARAMETERS_FIELD_NUMBER = 29;
    public static final int TITLE_FIELD_NUMBER = 2;
    public static final int TYPES_FIELD_NUMBER = 4;
    public static final int USAGE_FIELD_NUMBER = 15;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public List<Api> apis_;
    /* access modifiers changed from: private */
    public Authentication authentication_;
    /* access modifiers changed from: private */
    public Backend backend_;
    /* access modifiers changed from: private */
    public Billing billing_;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public UInt32Value configVersion_;
    /* access modifiers changed from: private */
    public Context context_;
    /* access modifiers changed from: private */
    public Control control_;
    /* access modifiers changed from: private */
    public Documentation documentation_;
    /* access modifiers changed from: private */
    public List<Endpoint> endpoints_;
    /* access modifiers changed from: private */
    public List<Enum> enums_;
    /* access modifiers changed from: private */
    public Experimental experimental_;
    /* access modifiers changed from: private */
    public Http http_;
    /* access modifiers changed from: private */
    public volatile Object id_;
    /* access modifiers changed from: private */
    public Logging logging_;
    /* access modifiers changed from: private */
    public List<LogDescriptor> logs_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public List<MetricDescriptor> metrics_;
    /* access modifiers changed from: private */
    public List<MonitoredResourceDescriptor> monitoredResources_;
    /* access modifiers changed from: private */
    public Monitoring monitoring_;
    /* access modifiers changed from: private */
    public volatile Object name_;
    /* access modifiers changed from: private */
    public volatile Object producerProjectId_;
    /* access modifiers changed from: private */
    public Quota quota_;
    /* access modifiers changed from: private */
    public SourceInfo sourceInfo_;
    /* access modifiers changed from: private */
    public SystemParameters systemParameters_;
    /* access modifiers changed from: private */
    public volatile Object title_;
    /* access modifiers changed from: private */
    public List<Type> types_;
    /* access modifiers changed from: private */
    public Usage usage_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ServiceOrBuilder {
        private RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> apisBuilder_;
        private List<Api> apis_;
        private SingleFieldBuilderV3<Authentication, com.google.api.Authentication.Builder, AuthenticationOrBuilder> authenticationBuilder_;
        private Authentication authentication_;
        private SingleFieldBuilderV3<Backend, com.google.api.Backend.Builder, BackendOrBuilder> backendBuilder_;
        private Backend backend_;
        private SingleFieldBuilderV3<Billing, com.google.api.Billing.Builder, BillingOrBuilder> billingBuilder_;
        private Billing billing_;
        private int bitField0_;
        private SingleFieldBuilderV3<UInt32Value, com.google.protobuf.UInt32Value.Builder, UInt32ValueOrBuilder> configVersionBuilder_;
        private UInt32Value configVersion_;
        private SingleFieldBuilderV3<Context, com.google.api.Context.Builder, ContextOrBuilder> contextBuilder_;
        private Context context_;
        private SingleFieldBuilderV3<Control, com.google.api.Control.Builder, ControlOrBuilder> controlBuilder_;
        private Control control_;
        private SingleFieldBuilderV3<Documentation, com.google.api.Documentation.Builder, DocumentationOrBuilder> documentationBuilder_;
        private Documentation documentation_;
        private RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> endpointsBuilder_;
        private List<Endpoint> endpoints_;
        private RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> enumsBuilder_;
        private List<Enum> enums_;
        private SingleFieldBuilderV3<Experimental, com.google.api.Experimental.Builder, ExperimentalOrBuilder> experimentalBuilder_;
        private Experimental experimental_;
        private SingleFieldBuilderV3<Http, com.google.api.Http.Builder, HttpOrBuilder> httpBuilder_;
        private Http http_;
        private Object id_;
        private SingleFieldBuilderV3<Logging, com.google.api.Logging.Builder, LoggingOrBuilder> loggingBuilder_;
        private Logging logging_;
        private RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> logsBuilder_;
        private List<LogDescriptor> logs_;
        private RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> metricsBuilder_;
        private List<MetricDescriptor> metrics_;
        private RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> monitoredResourcesBuilder_;
        private List<MonitoredResourceDescriptor> monitoredResources_;
        private SingleFieldBuilderV3<Monitoring, com.google.api.Monitoring.Builder, MonitoringOrBuilder> monitoringBuilder_;
        private Monitoring monitoring_;
        private Object name_;
        private Object producerProjectId_;
        private SingleFieldBuilderV3<Quota, com.google.api.Quota.Builder, QuotaOrBuilder> quotaBuilder_;
        private Quota quota_;
        private SingleFieldBuilderV3<SourceInfo, com.google.api.SourceInfo.Builder, SourceInfoOrBuilder> sourceInfoBuilder_;
        private SourceInfo sourceInfo_;
        private SingleFieldBuilderV3<SystemParameters, com.google.api.SystemParameters.Builder, SystemParametersOrBuilder> systemParametersBuilder_;
        private SystemParameters systemParameters_;
        private Object title_;
        private RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> typesBuilder_;
        private List<Type> types_;
        private SingleFieldBuilderV3<Usage, com.google.api.Usage.Builder, UsageOrBuilder> usageBuilder_;
        private Usage usage_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ServiceProto.internal_static_google_api_Service_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ServiceProto.internal_static_google_api_Service_fieldAccessorTable.ensureFieldAccessorsInitialized(Service.class, Builder.class);
        }

        private Builder() {
            this.configVersion_ = null;
            this.name_ = "";
            this.id_ = "";
            this.title_ = "";
            this.producerProjectId_ = "";
            this.apis_ = Collections.emptyList();
            this.types_ = Collections.emptyList();
            this.enums_ = Collections.emptyList();
            this.documentation_ = null;
            this.backend_ = null;
            this.http_ = null;
            this.quota_ = null;
            this.authentication_ = null;
            this.context_ = null;
            this.usage_ = null;
            this.endpoints_ = Collections.emptyList();
            this.control_ = null;
            this.logs_ = Collections.emptyList();
            this.metrics_ = Collections.emptyList();
            this.monitoredResources_ = Collections.emptyList();
            this.billing_ = null;
            this.logging_ = null;
            this.monitoring_ = null;
            this.systemParameters_ = null;
            this.sourceInfo_ = null;
            this.experimental_ = null;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.configVersion_ = null;
            this.name_ = "";
            this.id_ = "";
            this.title_ = "";
            this.producerProjectId_ = "";
            this.apis_ = Collections.emptyList();
            this.types_ = Collections.emptyList();
            this.enums_ = Collections.emptyList();
            this.documentation_ = null;
            this.backend_ = null;
            this.http_ = null;
            this.quota_ = null;
            this.authentication_ = null;
            this.context_ = null;
            this.usage_ = null;
            this.endpoints_ = Collections.emptyList();
            this.control_ = null;
            this.logs_ = Collections.emptyList();
            this.metrics_ = Collections.emptyList();
            this.monitoredResources_ = Collections.emptyList();
            this.billing_ = null;
            this.logging_ = null;
            this.monitoring_ = null;
            this.systemParameters_ = null;
            this.sourceInfo_ = null;
            this.experimental_ = null;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Service.alwaysUseFieldBuilders) {
                getApisFieldBuilder();
                getTypesFieldBuilder();
                getEnumsFieldBuilder();
                getEndpointsFieldBuilder();
                getLogsFieldBuilder();
                getMetricsFieldBuilder();
                getMonitoredResourcesFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            if (this.configVersionBuilder_ == null) {
                this.configVersion_ = null;
            } else {
                this.configVersion_ = null;
                this.configVersionBuilder_ = null;
            }
            this.name_ = "";
            this.id_ = "";
            this.title_ = "";
            this.producerProjectId_ = "";
            RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.apis_ = Collections.emptyList();
                this.bitField0_ &= -33;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> repeatedFieldBuilderV32 = this.typesBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.types_ = Collections.emptyList();
                this.bitField0_ &= -65;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV33 = this.enumsBuilder_;
            if (repeatedFieldBuilderV33 == null) {
                this.enums_ = Collections.emptyList();
                this.bitField0_ &= -129;
            } else {
                repeatedFieldBuilderV33.clear();
            }
            if (this.documentationBuilder_ == null) {
                this.documentation_ = null;
            } else {
                this.documentation_ = null;
                this.documentationBuilder_ = null;
            }
            if (this.backendBuilder_ == null) {
                this.backend_ = null;
            } else {
                this.backend_ = null;
                this.backendBuilder_ = null;
            }
            if (this.httpBuilder_ == null) {
                this.http_ = null;
            } else {
                this.http_ = null;
                this.httpBuilder_ = null;
            }
            if (this.quotaBuilder_ == null) {
                this.quota_ = null;
            } else {
                this.quota_ = null;
                this.quotaBuilder_ = null;
            }
            if (this.authenticationBuilder_ == null) {
                this.authentication_ = null;
            } else {
                this.authentication_ = null;
                this.authenticationBuilder_ = null;
            }
            if (this.contextBuilder_ == null) {
                this.context_ = null;
            } else {
                this.context_ = null;
                this.contextBuilder_ = null;
            }
            if (this.usageBuilder_ == null) {
                this.usage_ = null;
            } else {
                this.usage_ = null;
                this.usageBuilder_ = null;
            }
            RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV34 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV34 == null) {
                this.endpoints_ = Collections.emptyList();
                this.bitField0_ &= -32769;
            } else {
                repeatedFieldBuilderV34.clear();
            }
            if (this.controlBuilder_ == null) {
                this.control_ = null;
            } else {
                this.control_ = null;
                this.controlBuilder_ = null;
            }
            RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV35 = this.logsBuilder_;
            if (repeatedFieldBuilderV35 == null) {
                this.logs_ = Collections.emptyList();
                this.bitField0_ &= -131073;
            } else {
                repeatedFieldBuilderV35.clear();
            }
            RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV36 = this.metricsBuilder_;
            if (repeatedFieldBuilderV36 == null) {
                this.metrics_ = Collections.emptyList();
                this.bitField0_ &= -262145;
            } else {
                repeatedFieldBuilderV36.clear();
            }
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV37 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV37 == null) {
                this.monitoredResources_ = Collections.emptyList();
                this.bitField0_ &= -524289;
            } else {
                repeatedFieldBuilderV37.clear();
            }
            if (this.billingBuilder_ == null) {
                this.billing_ = null;
            } else {
                this.billing_ = null;
                this.billingBuilder_ = null;
            }
            if (this.loggingBuilder_ == null) {
                this.logging_ = null;
            } else {
                this.logging_ = null;
                this.loggingBuilder_ = null;
            }
            if (this.monitoringBuilder_ == null) {
                this.monitoring_ = null;
            } else {
                this.monitoring_ = null;
                this.monitoringBuilder_ = null;
            }
            if (this.systemParametersBuilder_ == null) {
                this.systemParameters_ = null;
            } else {
                this.systemParameters_ = null;
                this.systemParametersBuilder_ = null;
            }
            if (this.sourceInfoBuilder_ == null) {
                this.sourceInfo_ = null;
            } else {
                this.sourceInfo_ = null;
                this.sourceInfoBuilder_ = null;
            }
            if (this.experimentalBuilder_ == null) {
                this.experimental_ = null;
            } else {
                this.experimental_ = null;
                this.experimentalBuilder_ = null;
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ServiceProto.internal_static_google_api_Service_descriptor;
        }

        public Service getDefaultInstanceForType() {
            return Service.getDefaultInstance();
        }

        public Service build() {
            Service buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Service buildPartial() {
            Service service = new Service((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            SingleFieldBuilderV3<UInt32Value, com.google.protobuf.UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.configVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                service.configVersion_ = this.configVersion_;
            } else {
                service.configVersion_ = (UInt32Value) singleFieldBuilderV3.build();
            }
            service.name_ = this.name_;
            service.id_ = this.id_;
            service.title_ = this.title_;
            service.producerProjectId_ = this.producerProjectId_;
            RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 32) == 32) {
                    this.apis_ = Collections.unmodifiableList(this.apis_);
                    this.bitField0_ &= -33;
                }
                service.apis_ = this.apis_;
            } else {
                service.apis_ = repeatedFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> repeatedFieldBuilderV32 = this.typesBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 64) == 64) {
                    this.types_ = Collections.unmodifiableList(this.types_);
                    this.bitField0_ &= -65;
                }
                service.types_ = this.types_;
            } else {
                service.types_ = repeatedFieldBuilderV32.build();
            }
            RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV33 = this.enumsBuilder_;
            if (repeatedFieldBuilderV33 == null) {
                if ((this.bitField0_ & 128) == 128) {
                    this.enums_ = Collections.unmodifiableList(this.enums_);
                    this.bitField0_ &= -129;
                }
                service.enums_ = this.enums_;
            } else {
                service.enums_ = repeatedFieldBuilderV33.build();
            }
            SingleFieldBuilderV3<Documentation, com.google.api.Documentation.Builder, DocumentationOrBuilder> singleFieldBuilderV32 = this.documentationBuilder_;
            if (singleFieldBuilderV32 == null) {
                service.documentation_ = this.documentation_;
            } else {
                service.documentation_ = (Documentation) singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<Backend, com.google.api.Backend.Builder, BackendOrBuilder> singleFieldBuilderV33 = this.backendBuilder_;
            if (singleFieldBuilderV33 == null) {
                service.backend_ = this.backend_;
            } else {
                service.backend_ = (Backend) singleFieldBuilderV33.build();
            }
            SingleFieldBuilderV3<Http, com.google.api.Http.Builder, HttpOrBuilder> singleFieldBuilderV34 = this.httpBuilder_;
            if (singleFieldBuilderV34 == null) {
                service.http_ = this.http_;
            } else {
                service.http_ = (Http) singleFieldBuilderV34.build();
            }
            SingleFieldBuilderV3<Quota, com.google.api.Quota.Builder, QuotaOrBuilder> singleFieldBuilderV35 = this.quotaBuilder_;
            if (singleFieldBuilderV35 == null) {
                service.quota_ = this.quota_;
            } else {
                service.quota_ = (Quota) singleFieldBuilderV35.build();
            }
            SingleFieldBuilderV3<Authentication, com.google.api.Authentication.Builder, AuthenticationOrBuilder> singleFieldBuilderV36 = this.authenticationBuilder_;
            if (singleFieldBuilderV36 == null) {
                service.authentication_ = this.authentication_;
            } else {
                service.authentication_ = (Authentication) singleFieldBuilderV36.build();
            }
            SingleFieldBuilderV3<Context, com.google.api.Context.Builder, ContextOrBuilder> singleFieldBuilderV37 = this.contextBuilder_;
            if (singleFieldBuilderV37 == null) {
                service.context_ = this.context_;
            } else {
                service.context_ = (Context) singleFieldBuilderV37.build();
            }
            SingleFieldBuilderV3<Usage, com.google.api.Usage.Builder, UsageOrBuilder> singleFieldBuilderV38 = this.usageBuilder_;
            if (singleFieldBuilderV38 == null) {
                service.usage_ = this.usage_;
            } else {
                service.usage_ = (Usage) singleFieldBuilderV38.build();
            }
            RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV34 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV34 == null) {
                if ((this.bitField0_ & 32768) == 32768) {
                    this.endpoints_ = Collections.unmodifiableList(this.endpoints_);
                    this.bitField0_ &= -32769;
                }
                service.endpoints_ = this.endpoints_;
            } else {
                service.endpoints_ = repeatedFieldBuilderV34.build();
            }
            SingleFieldBuilderV3<Control, com.google.api.Control.Builder, ControlOrBuilder> singleFieldBuilderV39 = this.controlBuilder_;
            if (singleFieldBuilderV39 == null) {
                service.control_ = this.control_;
            } else {
                service.control_ = (Control) singleFieldBuilderV39.build();
            }
            RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV35 = this.logsBuilder_;
            if (repeatedFieldBuilderV35 == null) {
                if ((this.bitField0_ & 131072) == 131072) {
                    this.logs_ = Collections.unmodifiableList(this.logs_);
                    this.bitField0_ &= -131073;
                }
                service.logs_ = this.logs_;
            } else {
                service.logs_ = repeatedFieldBuilderV35.build();
            }
            RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV36 = this.metricsBuilder_;
            if (repeatedFieldBuilderV36 == null) {
                if ((this.bitField0_ & C.DASH_ROLE_SUB_FLAG) == 262144) {
                    this.metrics_ = Collections.unmodifiableList(this.metrics_);
                    this.bitField0_ &= -262145;
                }
                service.metrics_ = this.metrics_;
            } else {
                service.metrics_ = repeatedFieldBuilderV36.build();
            }
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV37 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV37 == null) {
                if ((this.bitField0_ & 524288) == 524288) {
                    this.monitoredResources_ = Collections.unmodifiableList(this.monitoredResources_);
                    this.bitField0_ &= -524289;
                }
                service.monitoredResources_ = this.monitoredResources_;
            } else {
                service.monitoredResources_ = repeatedFieldBuilderV37.build();
            }
            SingleFieldBuilderV3<Billing, com.google.api.Billing.Builder, BillingOrBuilder> singleFieldBuilderV310 = this.billingBuilder_;
            if (singleFieldBuilderV310 == null) {
                service.billing_ = this.billing_;
            } else {
                service.billing_ = (Billing) singleFieldBuilderV310.build();
            }
            SingleFieldBuilderV3<Logging, com.google.api.Logging.Builder, LoggingOrBuilder> singleFieldBuilderV311 = this.loggingBuilder_;
            if (singleFieldBuilderV311 == null) {
                service.logging_ = this.logging_;
            } else {
                service.logging_ = (Logging) singleFieldBuilderV311.build();
            }
            SingleFieldBuilderV3<Monitoring, com.google.api.Monitoring.Builder, MonitoringOrBuilder> singleFieldBuilderV312 = this.monitoringBuilder_;
            if (singleFieldBuilderV312 == null) {
                service.monitoring_ = this.monitoring_;
            } else {
                service.monitoring_ = (Monitoring) singleFieldBuilderV312.build();
            }
            SingleFieldBuilderV3<SystemParameters, com.google.api.SystemParameters.Builder, SystemParametersOrBuilder> singleFieldBuilderV313 = this.systemParametersBuilder_;
            if (singleFieldBuilderV313 == null) {
                service.systemParameters_ = this.systemParameters_;
            } else {
                service.systemParameters_ = (SystemParameters) singleFieldBuilderV313.build();
            }
            SingleFieldBuilderV3<SourceInfo, com.google.api.SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV314 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV314 == null) {
                service.sourceInfo_ = this.sourceInfo_;
            } else {
                service.sourceInfo_ = (SourceInfo) singleFieldBuilderV314.build();
            }
            SingleFieldBuilderV3<Experimental, com.google.api.Experimental.Builder, ExperimentalOrBuilder> singleFieldBuilderV315 = this.experimentalBuilder_;
            if (singleFieldBuilderV315 == null) {
                service.experimental_ = this.experimental_;
            } else {
                service.experimental_ = (Experimental) singleFieldBuilderV315.build();
            }
            service.bitField0_ = 0;
            onBuilt();
            return service;
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
            if (message instanceof Service) {
                return mergeFrom((Service) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Service service) {
            if (service == Service.getDefaultInstance()) {
                return this;
            }
            if (service.hasConfigVersion()) {
                mergeConfigVersion(service.getConfigVersion());
            }
            if (!service.getName().isEmpty()) {
                this.name_ = service.name_;
                onChanged();
            }
            if (!service.getId().isEmpty()) {
                this.id_ = service.id_;
                onChanged();
            }
            if (!service.getTitle().isEmpty()) {
                this.title_ = service.title_;
                onChanged();
            }
            if (!service.getProducerProjectId().isEmpty()) {
                this.producerProjectId_ = service.producerProjectId_;
                onChanged();
            }
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = null;
            if (this.apisBuilder_ == null) {
                if (!service.apis_.isEmpty()) {
                    if (this.apis_.isEmpty()) {
                        this.apis_ = service.apis_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureApisIsMutable();
                        this.apis_.addAll(service.apis_);
                    }
                    onChanged();
                }
            } else if (!service.apis_.isEmpty()) {
                if (this.apisBuilder_.isEmpty()) {
                    this.apisBuilder_.dispose();
                    this.apisBuilder_ = null;
                    this.apis_ = service.apis_;
                    this.bitField0_ &= -33;
                    this.apisBuilder_ = Service.alwaysUseFieldBuilders ? getApisFieldBuilder() : null;
                } else {
                    this.apisBuilder_.addAllMessages(service.apis_);
                }
            }
            if (this.typesBuilder_ == null) {
                if (!service.types_.isEmpty()) {
                    if (this.types_.isEmpty()) {
                        this.types_ = service.types_;
                        this.bitField0_ &= -65;
                    } else {
                        ensureTypesIsMutable();
                        this.types_.addAll(service.types_);
                    }
                    onChanged();
                }
            } else if (!service.types_.isEmpty()) {
                if (this.typesBuilder_.isEmpty()) {
                    this.typesBuilder_.dispose();
                    this.typesBuilder_ = null;
                    this.types_ = service.types_;
                    this.bitField0_ &= -65;
                    this.typesBuilder_ = Service.alwaysUseFieldBuilders ? getTypesFieldBuilder() : null;
                } else {
                    this.typesBuilder_.addAllMessages(service.types_);
                }
            }
            if (this.enumsBuilder_ == null) {
                if (!service.enums_.isEmpty()) {
                    if (this.enums_.isEmpty()) {
                        this.enums_ = service.enums_;
                        this.bitField0_ &= -129;
                    } else {
                        ensureEnumsIsMutable();
                        this.enums_.addAll(service.enums_);
                    }
                    onChanged();
                }
            } else if (!service.enums_.isEmpty()) {
                if (this.enumsBuilder_.isEmpty()) {
                    this.enumsBuilder_.dispose();
                    this.enumsBuilder_ = null;
                    this.enums_ = service.enums_;
                    this.bitField0_ &= -129;
                    this.enumsBuilder_ = Service.alwaysUseFieldBuilders ? getEnumsFieldBuilder() : null;
                } else {
                    this.enumsBuilder_.addAllMessages(service.enums_);
                }
            }
            if (service.hasDocumentation()) {
                mergeDocumentation(service.getDocumentation());
            }
            if (service.hasBackend()) {
                mergeBackend(service.getBackend());
            }
            if (service.hasHttp()) {
                mergeHttp(service.getHttp());
            }
            if (service.hasQuota()) {
                mergeQuota(service.getQuota());
            }
            if (service.hasAuthentication()) {
                mergeAuthentication(service.getAuthentication());
            }
            if (service.hasContext()) {
                mergeContext(service.getContext());
            }
            if (service.hasUsage()) {
                mergeUsage(service.getUsage());
            }
            if (this.endpointsBuilder_ == null) {
                if (!service.endpoints_.isEmpty()) {
                    if (this.endpoints_.isEmpty()) {
                        this.endpoints_ = service.endpoints_;
                        this.bitField0_ &= -32769;
                    } else {
                        ensureEndpointsIsMutable();
                        this.endpoints_.addAll(service.endpoints_);
                    }
                    onChanged();
                }
            } else if (!service.endpoints_.isEmpty()) {
                if (this.endpointsBuilder_.isEmpty()) {
                    this.endpointsBuilder_.dispose();
                    this.endpointsBuilder_ = null;
                    this.endpoints_ = service.endpoints_;
                    this.bitField0_ &= -32769;
                    this.endpointsBuilder_ = Service.alwaysUseFieldBuilders ? getEndpointsFieldBuilder() : null;
                } else {
                    this.endpointsBuilder_.addAllMessages(service.endpoints_);
                }
            }
            if (service.hasControl()) {
                mergeControl(service.getControl());
            }
            if (this.logsBuilder_ == null) {
                if (!service.logs_.isEmpty()) {
                    if (this.logs_.isEmpty()) {
                        this.logs_ = service.logs_;
                        this.bitField0_ &= -131073;
                    } else {
                        ensureLogsIsMutable();
                        this.logs_.addAll(service.logs_);
                    }
                    onChanged();
                }
            } else if (!service.logs_.isEmpty()) {
                if (this.logsBuilder_.isEmpty()) {
                    this.logsBuilder_.dispose();
                    this.logsBuilder_ = null;
                    this.logs_ = service.logs_;
                    this.bitField0_ &= -131073;
                    this.logsBuilder_ = Service.alwaysUseFieldBuilders ? getLogsFieldBuilder() : null;
                } else {
                    this.logsBuilder_.addAllMessages(service.logs_);
                }
            }
            if (this.metricsBuilder_ == null) {
                if (!service.metrics_.isEmpty()) {
                    if (this.metrics_.isEmpty()) {
                        this.metrics_ = service.metrics_;
                        this.bitField0_ &= -262145;
                    } else {
                        ensureMetricsIsMutable();
                        this.metrics_.addAll(service.metrics_);
                    }
                    onChanged();
                }
            } else if (!service.metrics_.isEmpty()) {
                if (this.metricsBuilder_.isEmpty()) {
                    this.metricsBuilder_.dispose();
                    this.metricsBuilder_ = null;
                    this.metrics_ = service.metrics_;
                    this.bitField0_ &= -262145;
                    this.metricsBuilder_ = Service.alwaysUseFieldBuilders ? getMetricsFieldBuilder() : null;
                } else {
                    this.metricsBuilder_.addAllMessages(service.metrics_);
                }
            }
            if (this.monitoredResourcesBuilder_ == null) {
                if (!service.monitoredResources_.isEmpty()) {
                    if (this.monitoredResources_.isEmpty()) {
                        this.monitoredResources_ = service.monitoredResources_;
                        this.bitField0_ &= -524289;
                    } else {
                        ensureMonitoredResourcesIsMutable();
                        this.monitoredResources_.addAll(service.monitoredResources_);
                    }
                    onChanged();
                }
            } else if (!service.monitoredResources_.isEmpty()) {
                if (this.monitoredResourcesBuilder_.isEmpty()) {
                    this.monitoredResourcesBuilder_.dispose();
                    this.monitoredResourcesBuilder_ = null;
                    this.monitoredResources_ = service.monitoredResources_;
                    this.bitField0_ &= -524289;
                    if (Service.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getMonitoredResourcesFieldBuilder();
                    }
                    this.monitoredResourcesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.monitoredResourcesBuilder_.addAllMessages(service.monitoredResources_);
                }
            }
            if (service.hasBilling()) {
                mergeBilling(service.getBilling());
            }
            if (service.hasLogging()) {
                mergeLogging(service.getLogging());
            }
            if (service.hasMonitoring()) {
                mergeMonitoring(service.getMonitoring());
            }
            if (service.hasSystemParameters()) {
                mergeSystemParameters(service.getSystemParameters());
            }
            if (service.hasSourceInfo()) {
                mergeSourceInfo(service.getSourceInfo());
            }
            if (service.hasExperimental()) {
                mergeExperimental(service.getExperimental());
            }
            mergeUnknownFields(service.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.Service.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Service.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.Service r3 = (com.google.api.Service) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.Service r4 = (com.google.api.Service) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Service.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Service$Builder");
        }

        public boolean hasConfigVersion() {
            return (this.configVersionBuilder_ == null && this.configVersion_ == null) ? false : true;
        }

        public UInt32Value getConfigVersion() {
            SingleFieldBuilderV3<UInt32Value, com.google.protobuf.UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.configVersionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (UInt32Value) singleFieldBuilderV3.getMessage();
            }
            UInt32Value uInt32Value = this.configVersion_;
            if (uInt32Value == null) {
                uInt32Value = UInt32Value.getDefaultInstance();
            }
            return uInt32Value;
        }

        public Builder setConfigVersion(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, com.google.protobuf.UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.configVersionBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(uInt32Value);
            } else if (uInt32Value != null) {
                this.configVersion_ = uInt32Value;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setConfigVersion(com.google.protobuf.UInt32Value.Builder builder) {
            SingleFieldBuilderV3<UInt32Value, com.google.protobuf.UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.configVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.configVersion_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeConfigVersion(UInt32Value uInt32Value) {
            SingleFieldBuilderV3<UInt32Value, com.google.protobuf.UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.configVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                UInt32Value uInt32Value2 = this.configVersion_;
                if (uInt32Value2 != null) {
                    this.configVersion_ = UInt32Value.newBuilder(uInt32Value2).mergeFrom(uInt32Value).buildPartial();
                } else {
                    this.configVersion_ = uInt32Value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(uInt32Value);
            }
            return this;
        }

        public Builder clearConfigVersion() {
            if (this.configVersionBuilder_ == null) {
                this.configVersion_ = null;
                onChanged();
            } else {
                this.configVersion_ = null;
                this.configVersionBuilder_ = null;
            }
            return this;
        }

        public com.google.protobuf.UInt32Value.Builder getConfigVersionBuilder() {
            onChanged();
            return (com.google.protobuf.UInt32Value.Builder) getConfigVersionFieldBuilder().getBuilder();
        }

        public UInt32ValueOrBuilder getConfigVersionOrBuilder() {
            SingleFieldBuilderV3<UInt32Value, com.google.protobuf.UInt32Value.Builder, UInt32ValueOrBuilder> singleFieldBuilderV3 = this.configVersionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (UInt32ValueOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            UInt32Value uInt32Value = this.configVersion_;
            if (uInt32Value == null) {
                uInt32Value = UInt32Value.getDefaultInstance();
            }
            return uInt32Value;
        }

        private SingleFieldBuilderV3<UInt32Value, com.google.protobuf.UInt32Value.Builder, UInt32ValueOrBuilder> getConfigVersionFieldBuilder() {
            if (this.configVersionBuilder_ == null) {
                this.configVersionBuilder_ = new SingleFieldBuilderV3<>(getConfigVersion(), getParentForChildren(), isClean());
                this.configVersion_ = null;
            }
            return this.configVersionBuilder_;
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
            this.name_ = Service.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                Service.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getId() {
            Object obj = this.id_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.id_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getIdBytes() {
            Object obj = this.id_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.id_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setId(String str) {
            if (str != null) {
                this.id_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearId() {
            this.id_ = Service.getDefaultInstance().getId();
            onChanged();
            return this;
        }

        public Builder setIdBytes(ByteString byteString) {
            if (byteString != null) {
                Service.checkByteStringIsUtf8(byteString);
                this.id_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getTitle() {
            Object obj = this.title_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.title_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getTitleBytes() {
            Object obj = this.title_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.title_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setTitle(String str) {
            if (str != null) {
                this.title_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearTitle() {
            this.title_ = Service.getDefaultInstance().getTitle();
            onChanged();
            return this;
        }

        public Builder setTitleBytes(ByteString byteString) {
            if (byteString != null) {
                Service.checkByteStringIsUtf8(byteString);
                this.title_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getProducerProjectId() {
            Object obj = this.producerProjectId_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.producerProjectId_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getProducerProjectIdBytes() {
            Object obj = this.producerProjectId_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.producerProjectId_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setProducerProjectId(String str) {
            if (str != null) {
                this.producerProjectId_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearProducerProjectId() {
            this.producerProjectId_ = Service.getDefaultInstance().getProducerProjectId();
            onChanged();
            return this;
        }

        public Builder setProducerProjectIdBytes(ByteString byteString) {
            if (byteString != null) {
                Service.checkByteStringIsUtf8(byteString);
                this.producerProjectId_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureApisIsMutable() {
            if ((this.bitField0_ & 32) != 32) {
                this.apis_ = new ArrayList(this.apis_);
                this.bitField0_ |= 32;
            }
        }

        public List<Api> getApisList() {
            RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.apis_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getApisCount() {
            RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.apis_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Api getApis(int i) {
            RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Api) this.apis_.get(i);
            }
            return (Api) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setApis(int i, Api api) {
            RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, api);
            } else if (api != null) {
                ensureApisIsMutable();
                this.apis_.set(i, api);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setApis(int i, com.google.protobuf.Api.Builder builder) {
            RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureApisIsMutable();
                this.apis_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addApis(Api api) {
            RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(api);
            } else if (api != null) {
                ensureApisIsMutable();
                this.apis_.add(api);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addApis(int i, Api api) {
            RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, api);
            } else if (api != null) {
                ensureApisIsMutable();
                this.apis_.add(i, api);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addApis(com.google.protobuf.Api.Builder builder) {
            RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureApisIsMutable();
                this.apis_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addApis(int i, com.google.protobuf.Api.Builder builder) {
            RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureApisIsMutable();
                this.apis_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllApis(Iterable<? extends Api> iterable) {
            RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureApisIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.apis_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearApis() {
            RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.apis_ = Collections.emptyList();
                this.bitField0_ &= -33;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeApis(int i) {
            RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureApisIsMutable();
                this.apis_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.protobuf.Api.Builder getApisBuilder(int i) {
            return (com.google.protobuf.Api.Builder) getApisFieldBuilder().getBuilder(i);
        }

        public ApiOrBuilder getApisOrBuilder(int i) {
            RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (ApiOrBuilder) this.apis_.get(i);
            }
            return (ApiOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends ApiOrBuilder> getApisOrBuilderList() {
            RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> repeatedFieldBuilderV3 = this.apisBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.apis_);
        }

        public com.google.protobuf.Api.Builder addApisBuilder() {
            return (com.google.protobuf.Api.Builder) getApisFieldBuilder().addBuilder(Api.getDefaultInstance());
        }

        public com.google.protobuf.Api.Builder addApisBuilder(int i) {
            return (com.google.protobuf.Api.Builder) getApisFieldBuilder().addBuilder(i, Api.getDefaultInstance());
        }

        public List<com.google.protobuf.Api.Builder> getApisBuilderList() {
            return getApisFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Api, com.google.protobuf.Api.Builder, ApiOrBuilder> getApisFieldBuilder() {
            if (this.apisBuilder_ == null) {
                this.apisBuilder_ = new RepeatedFieldBuilderV3<>(this.apis_, (this.bitField0_ & 32) == 32, getParentForChildren(), isClean());
                this.apis_ = null;
            }
            return this.apisBuilder_;
        }

        private void ensureTypesIsMutable() {
            if ((this.bitField0_ & 64) != 64) {
                this.types_ = new ArrayList(this.types_);
                this.bitField0_ |= 64;
            }
        }

        public List<Type> getTypesList() {
            RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.types_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getTypesCount() {
            RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.types_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Type getTypes(int i) {
            RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Type) this.types_.get(i);
            }
            return (Type) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setTypes(int i, Type type) {
            RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, type);
            } else if (type != null) {
                ensureTypesIsMutable();
                this.types_.set(i, type);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setTypes(int i, com.google.protobuf.Type.Builder builder) {
            RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTypesIsMutable();
                this.types_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addTypes(Type type) {
            RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(type);
            } else if (type != null) {
                ensureTypesIsMutable();
                this.types_.add(type);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addTypes(int i, Type type) {
            RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, type);
            } else if (type != null) {
                ensureTypesIsMutable();
                this.types_.add(i, type);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addTypes(com.google.protobuf.Type.Builder builder) {
            RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTypesIsMutable();
                this.types_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addTypes(int i, com.google.protobuf.Type.Builder builder) {
            RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTypesIsMutable();
                this.types_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllTypes(Iterable<? extends Type> iterable) {
            RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTypesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.types_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearTypes() {
            RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.types_ = Collections.emptyList();
                this.bitField0_ &= -65;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeTypes(int i) {
            RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTypesIsMutable();
                this.types_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.protobuf.Type.Builder getTypesBuilder(int i) {
            return (com.google.protobuf.Type.Builder) getTypesFieldBuilder().getBuilder(i);
        }

        public TypeOrBuilder getTypesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (TypeOrBuilder) this.types_.get(i);
            }
            return (TypeOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends TypeOrBuilder> getTypesOrBuilderList() {
            RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> repeatedFieldBuilderV3 = this.typesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.types_);
        }

        public com.google.protobuf.Type.Builder addTypesBuilder() {
            return (com.google.protobuf.Type.Builder) getTypesFieldBuilder().addBuilder(Type.getDefaultInstance());
        }

        public com.google.protobuf.Type.Builder addTypesBuilder(int i) {
            return (com.google.protobuf.Type.Builder) getTypesFieldBuilder().addBuilder(i, Type.getDefaultInstance());
        }

        public List<com.google.protobuf.Type.Builder> getTypesBuilderList() {
            return getTypesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Type, com.google.protobuf.Type.Builder, TypeOrBuilder> getTypesFieldBuilder() {
            if (this.typesBuilder_ == null) {
                this.typesBuilder_ = new RepeatedFieldBuilderV3<>(this.types_, (this.bitField0_ & 64) == 64, getParentForChildren(), isClean());
                this.types_ = null;
            }
            return this.typesBuilder_;
        }

        private void ensureEnumsIsMutable() {
            if ((this.bitField0_ & 128) != 128) {
                this.enums_ = new ArrayList(this.enums_);
                this.bitField0_ |= 128;
            }
        }

        public List<Enum> getEnumsList() {
            RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.enums_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getEnumsCount() {
            RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.enums_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Enum getEnums(int i) {
            RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Enum) this.enums_.get(i);
            }
            return (Enum) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setEnums(int i, Enum enumR) {
            RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, enumR);
            } else if (enumR != null) {
                ensureEnumsIsMutable();
                this.enums_.set(i, enumR);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setEnums(int i, com.google.protobuf.Enum.Builder builder) {
            RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEnumsIsMutable();
                this.enums_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addEnums(Enum enumR) {
            RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(enumR);
            } else if (enumR != null) {
                ensureEnumsIsMutable();
                this.enums_.add(enumR);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addEnums(int i, Enum enumR) {
            RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, enumR);
            } else if (enumR != null) {
                ensureEnumsIsMutable();
                this.enums_.add(i, enumR);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addEnums(com.google.protobuf.Enum.Builder builder) {
            RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEnumsIsMutable();
                this.enums_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addEnums(int i, com.google.protobuf.Enum.Builder builder) {
            RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEnumsIsMutable();
                this.enums_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllEnums(Iterable<? extends Enum> iterable) {
            RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEnumsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.enums_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearEnums() {
            RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.enums_ = Collections.emptyList();
                this.bitField0_ &= -129;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeEnums(int i) {
            RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEnumsIsMutable();
                this.enums_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.protobuf.Enum.Builder getEnumsBuilder(int i) {
            return (com.google.protobuf.Enum.Builder) getEnumsFieldBuilder().getBuilder(i);
        }

        public EnumOrBuilder getEnumsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (EnumOrBuilder) this.enums_.get(i);
            }
            return (EnumOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends EnumOrBuilder> getEnumsOrBuilderList() {
            RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> repeatedFieldBuilderV3 = this.enumsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.enums_);
        }

        public com.google.protobuf.Enum.Builder addEnumsBuilder() {
            return (com.google.protobuf.Enum.Builder) getEnumsFieldBuilder().addBuilder(Enum.getDefaultInstance());
        }

        public com.google.protobuf.Enum.Builder addEnumsBuilder(int i) {
            return (com.google.protobuf.Enum.Builder) getEnumsFieldBuilder().addBuilder(i, Enum.getDefaultInstance());
        }

        public List<com.google.protobuf.Enum.Builder> getEnumsBuilderList() {
            return getEnumsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Enum, com.google.protobuf.Enum.Builder, EnumOrBuilder> getEnumsFieldBuilder() {
            if (this.enumsBuilder_ == null) {
                this.enumsBuilder_ = new RepeatedFieldBuilderV3<>(this.enums_, (this.bitField0_ & 128) == 128, getParentForChildren(), isClean());
                this.enums_ = null;
            }
            return this.enumsBuilder_;
        }

        public boolean hasDocumentation() {
            return (this.documentationBuilder_ == null && this.documentation_ == null) ? false : true;
        }

        public Documentation getDocumentation() {
            SingleFieldBuilderV3<Documentation, com.google.api.Documentation.Builder, DocumentationOrBuilder> singleFieldBuilderV3 = this.documentationBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Documentation) singleFieldBuilderV3.getMessage();
            }
            Documentation documentation = this.documentation_;
            if (documentation == null) {
                documentation = Documentation.getDefaultInstance();
            }
            return documentation;
        }

        public Builder setDocumentation(Documentation documentation) {
            SingleFieldBuilderV3<Documentation, com.google.api.Documentation.Builder, DocumentationOrBuilder> singleFieldBuilderV3 = this.documentationBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(documentation);
            } else if (documentation != null) {
                this.documentation_ = documentation;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setDocumentation(com.google.api.Documentation.Builder builder) {
            SingleFieldBuilderV3<Documentation, com.google.api.Documentation.Builder, DocumentationOrBuilder> singleFieldBuilderV3 = this.documentationBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.documentation_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeDocumentation(Documentation documentation) {
            SingleFieldBuilderV3<Documentation, com.google.api.Documentation.Builder, DocumentationOrBuilder> singleFieldBuilderV3 = this.documentationBuilder_;
            if (singleFieldBuilderV3 == null) {
                Documentation documentation2 = this.documentation_;
                if (documentation2 != null) {
                    this.documentation_ = Documentation.newBuilder(documentation2).mergeFrom(documentation).buildPartial();
                } else {
                    this.documentation_ = documentation;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(documentation);
            }
            return this;
        }

        public Builder clearDocumentation() {
            if (this.documentationBuilder_ == null) {
                this.documentation_ = null;
                onChanged();
            } else {
                this.documentation_ = null;
                this.documentationBuilder_ = null;
            }
            return this;
        }

        public com.google.api.Documentation.Builder getDocumentationBuilder() {
            onChanged();
            return (com.google.api.Documentation.Builder) getDocumentationFieldBuilder().getBuilder();
        }

        public DocumentationOrBuilder getDocumentationOrBuilder() {
            SingleFieldBuilderV3<Documentation, com.google.api.Documentation.Builder, DocumentationOrBuilder> singleFieldBuilderV3 = this.documentationBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (DocumentationOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Documentation documentation = this.documentation_;
            if (documentation == null) {
                documentation = Documentation.getDefaultInstance();
            }
            return documentation;
        }

        private SingleFieldBuilderV3<Documentation, com.google.api.Documentation.Builder, DocumentationOrBuilder> getDocumentationFieldBuilder() {
            if (this.documentationBuilder_ == null) {
                this.documentationBuilder_ = new SingleFieldBuilderV3<>(getDocumentation(), getParentForChildren(), isClean());
                this.documentation_ = null;
            }
            return this.documentationBuilder_;
        }

        public boolean hasBackend() {
            return (this.backendBuilder_ == null && this.backend_ == null) ? false : true;
        }

        public Backend getBackend() {
            SingleFieldBuilderV3<Backend, com.google.api.Backend.Builder, BackendOrBuilder> singleFieldBuilderV3 = this.backendBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Backend) singleFieldBuilderV3.getMessage();
            }
            Backend backend = this.backend_;
            if (backend == null) {
                backend = Backend.getDefaultInstance();
            }
            return backend;
        }

        public Builder setBackend(Backend backend) {
            SingleFieldBuilderV3<Backend, com.google.api.Backend.Builder, BackendOrBuilder> singleFieldBuilderV3 = this.backendBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(backend);
            } else if (backend != null) {
                this.backend_ = backend;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setBackend(com.google.api.Backend.Builder builder) {
            SingleFieldBuilderV3<Backend, com.google.api.Backend.Builder, BackendOrBuilder> singleFieldBuilderV3 = this.backendBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.backend_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeBackend(Backend backend) {
            SingleFieldBuilderV3<Backend, com.google.api.Backend.Builder, BackendOrBuilder> singleFieldBuilderV3 = this.backendBuilder_;
            if (singleFieldBuilderV3 == null) {
                Backend backend2 = this.backend_;
                if (backend2 != null) {
                    this.backend_ = Backend.newBuilder(backend2).mergeFrom(backend).buildPartial();
                } else {
                    this.backend_ = backend;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(backend);
            }
            return this;
        }

        public Builder clearBackend() {
            if (this.backendBuilder_ == null) {
                this.backend_ = null;
                onChanged();
            } else {
                this.backend_ = null;
                this.backendBuilder_ = null;
            }
            return this;
        }

        public com.google.api.Backend.Builder getBackendBuilder() {
            onChanged();
            return (com.google.api.Backend.Builder) getBackendFieldBuilder().getBuilder();
        }

        public BackendOrBuilder getBackendOrBuilder() {
            SingleFieldBuilderV3<Backend, com.google.api.Backend.Builder, BackendOrBuilder> singleFieldBuilderV3 = this.backendBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (BackendOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Backend backend = this.backend_;
            if (backend == null) {
                backend = Backend.getDefaultInstance();
            }
            return backend;
        }

        private SingleFieldBuilderV3<Backend, com.google.api.Backend.Builder, BackendOrBuilder> getBackendFieldBuilder() {
            if (this.backendBuilder_ == null) {
                this.backendBuilder_ = new SingleFieldBuilderV3<>(getBackend(), getParentForChildren(), isClean());
                this.backend_ = null;
            }
            return this.backendBuilder_;
        }

        public boolean hasHttp() {
            return (this.httpBuilder_ == null && this.http_ == null) ? false : true;
        }

        public Http getHttp() {
            SingleFieldBuilderV3<Http, com.google.api.Http.Builder, HttpOrBuilder> singleFieldBuilderV3 = this.httpBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Http) singleFieldBuilderV3.getMessage();
            }
            Http http = this.http_;
            if (http == null) {
                http = Http.getDefaultInstance();
            }
            return http;
        }

        public Builder setHttp(Http http) {
            SingleFieldBuilderV3<Http, com.google.api.Http.Builder, HttpOrBuilder> singleFieldBuilderV3 = this.httpBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(http);
            } else if (http != null) {
                this.http_ = http;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setHttp(com.google.api.Http.Builder builder) {
            SingleFieldBuilderV3<Http, com.google.api.Http.Builder, HttpOrBuilder> singleFieldBuilderV3 = this.httpBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.http_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeHttp(Http http) {
            SingleFieldBuilderV3<Http, com.google.api.Http.Builder, HttpOrBuilder> singleFieldBuilderV3 = this.httpBuilder_;
            if (singleFieldBuilderV3 == null) {
                Http http2 = this.http_;
                if (http2 != null) {
                    this.http_ = Http.newBuilder(http2).mergeFrom(http).buildPartial();
                } else {
                    this.http_ = http;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(http);
            }
            return this;
        }

        public Builder clearHttp() {
            if (this.httpBuilder_ == null) {
                this.http_ = null;
                onChanged();
            } else {
                this.http_ = null;
                this.httpBuilder_ = null;
            }
            return this;
        }

        public com.google.api.Http.Builder getHttpBuilder() {
            onChanged();
            return (com.google.api.Http.Builder) getHttpFieldBuilder().getBuilder();
        }

        public HttpOrBuilder getHttpOrBuilder() {
            SingleFieldBuilderV3<Http, com.google.api.Http.Builder, HttpOrBuilder> singleFieldBuilderV3 = this.httpBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (HttpOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Http http = this.http_;
            if (http == null) {
                http = Http.getDefaultInstance();
            }
            return http;
        }

        private SingleFieldBuilderV3<Http, com.google.api.Http.Builder, HttpOrBuilder> getHttpFieldBuilder() {
            if (this.httpBuilder_ == null) {
                this.httpBuilder_ = new SingleFieldBuilderV3<>(getHttp(), getParentForChildren(), isClean());
                this.http_ = null;
            }
            return this.httpBuilder_;
        }

        public boolean hasQuota() {
            return (this.quotaBuilder_ == null && this.quota_ == null) ? false : true;
        }

        public Quota getQuota() {
            SingleFieldBuilderV3<Quota, com.google.api.Quota.Builder, QuotaOrBuilder> singleFieldBuilderV3 = this.quotaBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Quota) singleFieldBuilderV3.getMessage();
            }
            Quota quota = this.quota_;
            if (quota == null) {
                quota = Quota.getDefaultInstance();
            }
            return quota;
        }

        public Builder setQuota(Quota quota) {
            SingleFieldBuilderV3<Quota, com.google.api.Quota.Builder, QuotaOrBuilder> singleFieldBuilderV3 = this.quotaBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(quota);
            } else if (quota != null) {
                this.quota_ = quota;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setQuota(com.google.api.Quota.Builder builder) {
            SingleFieldBuilderV3<Quota, com.google.api.Quota.Builder, QuotaOrBuilder> singleFieldBuilderV3 = this.quotaBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.quota_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeQuota(Quota quota) {
            SingleFieldBuilderV3<Quota, com.google.api.Quota.Builder, QuotaOrBuilder> singleFieldBuilderV3 = this.quotaBuilder_;
            if (singleFieldBuilderV3 == null) {
                Quota quota2 = this.quota_;
                if (quota2 != null) {
                    this.quota_ = Quota.newBuilder(quota2).mergeFrom(quota).buildPartial();
                } else {
                    this.quota_ = quota;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(quota);
            }
            return this;
        }

        public Builder clearQuota() {
            if (this.quotaBuilder_ == null) {
                this.quota_ = null;
                onChanged();
            } else {
                this.quota_ = null;
                this.quotaBuilder_ = null;
            }
            return this;
        }

        public com.google.api.Quota.Builder getQuotaBuilder() {
            onChanged();
            return (com.google.api.Quota.Builder) getQuotaFieldBuilder().getBuilder();
        }

        public QuotaOrBuilder getQuotaOrBuilder() {
            SingleFieldBuilderV3<Quota, com.google.api.Quota.Builder, QuotaOrBuilder> singleFieldBuilderV3 = this.quotaBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (QuotaOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Quota quota = this.quota_;
            if (quota == null) {
                quota = Quota.getDefaultInstance();
            }
            return quota;
        }

        private SingleFieldBuilderV3<Quota, com.google.api.Quota.Builder, QuotaOrBuilder> getQuotaFieldBuilder() {
            if (this.quotaBuilder_ == null) {
                this.quotaBuilder_ = new SingleFieldBuilderV3<>(getQuota(), getParentForChildren(), isClean());
                this.quota_ = null;
            }
            return this.quotaBuilder_;
        }

        public boolean hasAuthentication() {
            return (this.authenticationBuilder_ == null && this.authentication_ == null) ? false : true;
        }

        public Authentication getAuthentication() {
            SingleFieldBuilderV3<Authentication, com.google.api.Authentication.Builder, AuthenticationOrBuilder> singleFieldBuilderV3 = this.authenticationBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Authentication) singleFieldBuilderV3.getMessage();
            }
            Authentication authentication = this.authentication_;
            if (authentication == null) {
                authentication = Authentication.getDefaultInstance();
            }
            return authentication;
        }

        public Builder setAuthentication(Authentication authentication) {
            SingleFieldBuilderV3<Authentication, com.google.api.Authentication.Builder, AuthenticationOrBuilder> singleFieldBuilderV3 = this.authenticationBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(authentication);
            } else if (authentication != null) {
                this.authentication_ = authentication;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setAuthentication(com.google.api.Authentication.Builder builder) {
            SingleFieldBuilderV3<Authentication, com.google.api.Authentication.Builder, AuthenticationOrBuilder> singleFieldBuilderV3 = this.authenticationBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.authentication_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeAuthentication(Authentication authentication) {
            SingleFieldBuilderV3<Authentication, com.google.api.Authentication.Builder, AuthenticationOrBuilder> singleFieldBuilderV3 = this.authenticationBuilder_;
            if (singleFieldBuilderV3 == null) {
                Authentication authentication2 = this.authentication_;
                if (authentication2 != null) {
                    this.authentication_ = Authentication.newBuilder(authentication2).mergeFrom(authentication).buildPartial();
                } else {
                    this.authentication_ = authentication;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(authentication);
            }
            return this;
        }

        public Builder clearAuthentication() {
            if (this.authenticationBuilder_ == null) {
                this.authentication_ = null;
                onChanged();
            } else {
                this.authentication_ = null;
                this.authenticationBuilder_ = null;
            }
            return this;
        }

        public com.google.api.Authentication.Builder getAuthenticationBuilder() {
            onChanged();
            return (com.google.api.Authentication.Builder) getAuthenticationFieldBuilder().getBuilder();
        }

        public AuthenticationOrBuilder getAuthenticationOrBuilder() {
            SingleFieldBuilderV3<Authentication, com.google.api.Authentication.Builder, AuthenticationOrBuilder> singleFieldBuilderV3 = this.authenticationBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (AuthenticationOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Authentication authentication = this.authentication_;
            if (authentication == null) {
                authentication = Authentication.getDefaultInstance();
            }
            return authentication;
        }

        private SingleFieldBuilderV3<Authentication, com.google.api.Authentication.Builder, AuthenticationOrBuilder> getAuthenticationFieldBuilder() {
            if (this.authenticationBuilder_ == null) {
                this.authenticationBuilder_ = new SingleFieldBuilderV3<>(getAuthentication(), getParentForChildren(), isClean());
                this.authentication_ = null;
            }
            return this.authenticationBuilder_;
        }

        public boolean hasContext() {
            return (this.contextBuilder_ == null && this.context_ == null) ? false : true;
        }

        public Context getContext() {
            SingleFieldBuilderV3<Context, com.google.api.Context.Builder, ContextOrBuilder> singleFieldBuilderV3 = this.contextBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Context) singleFieldBuilderV3.getMessage();
            }
            Context context = this.context_;
            if (context == null) {
                context = Context.getDefaultInstance();
            }
            return context;
        }

        public Builder setContext(Context context) {
            SingleFieldBuilderV3<Context, com.google.api.Context.Builder, ContextOrBuilder> singleFieldBuilderV3 = this.contextBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(context);
            } else if (context != null) {
                this.context_ = context;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setContext(com.google.api.Context.Builder builder) {
            SingleFieldBuilderV3<Context, com.google.api.Context.Builder, ContextOrBuilder> singleFieldBuilderV3 = this.contextBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.context_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeContext(Context context) {
            SingleFieldBuilderV3<Context, com.google.api.Context.Builder, ContextOrBuilder> singleFieldBuilderV3 = this.contextBuilder_;
            if (singleFieldBuilderV3 == null) {
                Context context2 = this.context_;
                if (context2 != null) {
                    this.context_ = Context.newBuilder(context2).mergeFrom(context).buildPartial();
                } else {
                    this.context_ = context;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(context);
            }
            return this;
        }

        public Builder clearContext() {
            if (this.contextBuilder_ == null) {
                this.context_ = null;
                onChanged();
            } else {
                this.context_ = null;
                this.contextBuilder_ = null;
            }
            return this;
        }

        public com.google.api.Context.Builder getContextBuilder() {
            onChanged();
            return (com.google.api.Context.Builder) getContextFieldBuilder().getBuilder();
        }

        public ContextOrBuilder getContextOrBuilder() {
            SingleFieldBuilderV3<Context, com.google.api.Context.Builder, ContextOrBuilder> singleFieldBuilderV3 = this.contextBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ContextOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Context context = this.context_;
            if (context == null) {
                context = Context.getDefaultInstance();
            }
            return context;
        }

        private SingleFieldBuilderV3<Context, com.google.api.Context.Builder, ContextOrBuilder> getContextFieldBuilder() {
            if (this.contextBuilder_ == null) {
                this.contextBuilder_ = new SingleFieldBuilderV3<>(getContext(), getParentForChildren(), isClean());
                this.context_ = null;
            }
            return this.contextBuilder_;
        }

        public boolean hasUsage() {
            return (this.usageBuilder_ == null && this.usage_ == null) ? false : true;
        }

        public Usage getUsage() {
            SingleFieldBuilderV3<Usage, com.google.api.Usage.Builder, UsageOrBuilder> singleFieldBuilderV3 = this.usageBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Usage) singleFieldBuilderV3.getMessage();
            }
            Usage usage = this.usage_;
            if (usage == null) {
                usage = Usage.getDefaultInstance();
            }
            return usage;
        }

        public Builder setUsage(Usage usage) {
            SingleFieldBuilderV3<Usage, com.google.api.Usage.Builder, UsageOrBuilder> singleFieldBuilderV3 = this.usageBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(usage);
            } else if (usage != null) {
                this.usage_ = usage;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setUsage(com.google.api.Usage.Builder builder) {
            SingleFieldBuilderV3<Usage, com.google.api.Usage.Builder, UsageOrBuilder> singleFieldBuilderV3 = this.usageBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.usage_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeUsage(Usage usage) {
            SingleFieldBuilderV3<Usage, com.google.api.Usage.Builder, UsageOrBuilder> singleFieldBuilderV3 = this.usageBuilder_;
            if (singleFieldBuilderV3 == null) {
                Usage usage2 = this.usage_;
                if (usage2 != null) {
                    this.usage_ = Usage.newBuilder(usage2).mergeFrom(usage).buildPartial();
                } else {
                    this.usage_ = usage;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(usage);
            }
            return this;
        }

        public Builder clearUsage() {
            if (this.usageBuilder_ == null) {
                this.usage_ = null;
                onChanged();
            } else {
                this.usage_ = null;
                this.usageBuilder_ = null;
            }
            return this;
        }

        public com.google.api.Usage.Builder getUsageBuilder() {
            onChanged();
            return (com.google.api.Usage.Builder) getUsageFieldBuilder().getBuilder();
        }

        public UsageOrBuilder getUsageOrBuilder() {
            SingleFieldBuilderV3<Usage, com.google.api.Usage.Builder, UsageOrBuilder> singleFieldBuilderV3 = this.usageBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (UsageOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Usage usage = this.usage_;
            if (usage == null) {
                usage = Usage.getDefaultInstance();
            }
            return usage;
        }

        private SingleFieldBuilderV3<Usage, com.google.api.Usage.Builder, UsageOrBuilder> getUsageFieldBuilder() {
            if (this.usageBuilder_ == null) {
                this.usageBuilder_ = new SingleFieldBuilderV3<>(getUsage(), getParentForChildren(), isClean());
                this.usage_ = null;
            }
            return this.usageBuilder_;
        }

        private void ensureEndpointsIsMutable() {
            if ((this.bitField0_ & 32768) != 32768) {
                this.endpoints_ = new ArrayList(this.endpoints_);
                this.bitField0_ |= 32768;
            }
        }

        public List<Endpoint> getEndpointsList() {
            RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.endpoints_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getEndpointsCount() {
            RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.endpoints_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Endpoint getEndpoints(int i) {
            RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Endpoint) this.endpoints_.get(i);
            }
            return (Endpoint) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setEndpoints(int i, Endpoint endpoint) {
            RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, endpoint);
            } else if (endpoint != null) {
                ensureEndpointsIsMutable();
                this.endpoints_.set(i, endpoint);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setEndpoints(int i, com.google.api.Endpoint.Builder builder) {
            RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEndpointsIsMutable();
                this.endpoints_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addEndpoints(Endpoint endpoint) {
            RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(endpoint);
            } else if (endpoint != null) {
                ensureEndpointsIsMutable();
                this.endpoints_.add(endpoint);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addEndpoints(int i, Endpoint endpoint) {
            RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, endpoint);
            } else if (endpoint != null) {
                ensureEndpointsIsMutable();
                this.endpoints_.add(i, endpoint);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addEndpoints(com.google.api.Endpoint.Builder builder) {
            RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEndpointsIsMutable();
                this.endpoints_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addEndpoints(int i, com.google.api.Endpoint.Builder builder) {
            RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEndpointsIsMutable();
                this.endpoints_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllEndpoints(Iterable<? extends Endpoint> iterable) {
            RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEndpointsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.endpoints_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearEndpoints() {
            RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.endpoints_ = Collections.emptyList();
                this.bitField0_ &= -32769;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeEndpoints(int i) {
            RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureEndpointsIsMutable();
                this.endpoints_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.Endpoint.Builder getEndpointsBuilder(int i) {
            return (com.google.api.Endpoint.Builder) getEndpointsFieldBuilder().getBuilder(i);
        }

        public EndpointOrBuilder getEndpointsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (EndpointOrBuilder) this.endpoints_.get(i);
            }
            return (EndpointOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends EndpointOrBuilder> getEndpointsOrBuilderList() {
            RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> repeatedFieldBuilderV3 = this.endpointsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.endpoints_);
        }

        public com.google.api.Endpoint.Builder addEndpointsBuilder() {
            return (com.google.api.Endpoint.Builder) getEndpointsFieldBuilder().addBuilder(Endpoint.getDefaultInstance());
        }

        public com.google.api.Endpoint.Builder addEndpointsBuilder(int i) {
            return (com.google.api.Endpoint.Builder) getEndpointsFieldBuilder().addBuilder(i, Endpoint.getDefaultInstance());
        }

        public List<com.google.api.Endpoint.Builder> getEndpointsBuilderList() {
            return getEndpointsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Endpoint, com.google.api.Endpoint.Builder, EndpointOrBuilder> getEndpointsFieldBuilder() {
            if (this.endpointsBuilder_ == null) {
                this.endpointsBuilder_ = new RepeatedFieldBuilderV3<>(this.endpoints_, (this.bitField0_ & 32768) == 32768, getParentForChildren(), isClean());
                this.endpoints_ = null;
            }
            return this.endpointsBuilder_;
        }

        public boolean hasControl() {
            return (this.controlBuilder_ == null && this.control_ == null) ? false : true;
        }

        public Control getControl() {
            SingleFieldBuilderV3<Control, com.google.api.Control.Builder, ControlOrBuilder> singleFieldBuilderV3 = this.controlBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Control) singleFieldBuilderV3.getMessage();
            }
            Control control = this.control_;
            if (control == null) {
                control = Control.getDefaultInstance();
            }
            return control;
        }

        public Builder setControl(Control control) {
            SingleFieldBuilderV3<Control, com.google.api.Control.Builder, ControlOrBuilder> singleFieldBuilderV3 = this.controlBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(control);
            } else if (control != null) {
                this.control_ = control;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setControl(com.google.api.Control.Builder builder) {
            SingleFieldBuilderV3<Control, com.google.api.Control.Builder, ControlOrBuilder> singleFieldBuilderV3 = this.controlBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.control_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeControl(Control control) {
            SingleFieldBuilderV3<Control, com.google.api.Control.Builder, ControlOrBuilder> singleFieldBuilderV3 = this.controlBuilder_;
            if (singleFieldBuilderV3 == null) {
                Control control2 = this.control_;
                if (control2 != null) {
                    this.control_ = Control.newBuilder(control2).mergeFrom(control).buildPartial();
                } else {
                    this.control_ = control;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(control);
            }
            return this;
        }

        public Builder clearControl() {
            if (this.controlBuilder_ == null) {
                this.control_ = null;
                onChanged();
            } else {
                this.control_ = null;
                this.controlBuilder_ = null;
            }
            return this;
        }

        public com.google.api.Control.Builder getControlBuilder() {
            onChanged();
            return (com.google.api.Control.Builder) getControlFieldBuilder().getBuilder();
        }

        public ControlOrBuilder getControlOrBuilder() {
            SingleFieldBuilderV3<Control, com.google.api.Control.Builder, ControlOrBuilder> singleFieldBuilderV3 = this.controlBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ControlOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Control control = this.control_;
            if (control == null) {
                control = Control.getDefaultInstance();
            }
            return control;
        }

        private SingleFieldBuilderV3<Control, com.google.api.Control.Builder, ControlOrBuilder> getControlFieldBuilder() {
            if (this.controlBuilder_ == null) {
                this.controlBuilder_ = new SingleFieldBuilderV3<>(getControl(), getParentForChildren(), isClean());
                this.control_ = null;
            }
            return this.controlBuilder_;
        }

        private void ensureLogsIsMutable() {
            if ((this.bitField0_ & 131072) != 131072) {
                this.logs_ = new ArrayList(this.logs_);
                this.bitField0_ |= 131072;
            }
        }

        public List<LogDescriptor> getLogsList() {
            RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.logs_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getLogsCount() {
            RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.logs_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public LogDescriptor getLogs(int i) {
            RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (LogDescriptor) this.logs_.get(i);
            }
            return (LogDescriptor) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setLogs(int i, LogDescriptor logDescriptor) {
            RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, logDescriptor);
            } else if (logDescriptor != null) {
                ensureLogsIsMutable();
                this.logs_.set(i, logDescriptor);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setLogs(int i, com.google.api.LogDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLogsIsMutable();
                this.logs_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addLogs(LogDescriptor logDescriptor) {
            RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(logDescriptor);
            } else if (logDescriptor != null) {
                ensureLogsIsMutable();
                this.logs_.add(logDescriptor);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addLogs(int i, LogDescriptor logDescriptor) {
            RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, logDescriptor);
            } else if (logDescriptor != null) {
                ensureLogsIsMutable();
                this.logs_.add(i, logDescriptor);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addLogs(com.google.api.LogDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLogsIsMutable();
                this.logs_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addLogs(int i, com.google.api.LogDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLogsIsMutable();
                this.logs_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllLogs(Iterable<? extends LogDescriptor> iterable) {
            RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLogsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.logs_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearLogs() {
            RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.logs_ = Collections.emptyList();
                this.bitField0_ &= -131073;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeLogs(int i) {
            RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLogsIsMutable();
                this.logs_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.LogDescriptor.Builder getLogsBuilder(int i) {
            return (com.google.api.LogDescriptor.Builder) getLogsFieldBuilder().getBuilder(i);
        }

        public LogDescriptorOrBuilder getLogsOrBuilder(int i) {
            RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (LogDescriptorOrBuilder) this.logs_.get(i);
            }
            return (LogDescriptorOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends LogDescriptorOrBuilder> getLogsOrBuilderList() {
            RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> repeatedFieldBuilderV3 = this.logsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.logs_);
        }

        public com.google.api.LogDescriptor.Builder addLogsBuilder() {
            return (com.google.api.LogDescriptor.Builder) getLogsFieldBuilder().addBuilder(LogDescriptor.getDefaultInstance());
        }

        public com.google.api.LogDescriptor.Builder addLogsBuilder(int i) {
            return (com.google.api.LogDescriptor.Builder) getLogsFieldBuilder().addBuilder(i, LogDescriptor.getDefaultInstance());
        }

        public List<com.google.api.LogDescriptor.Builder> getLogsBuilderList() {
            return getLogsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LogDescriptor, com.google.api.LogDescriptor.Builder, LogDescriptorOrBuilder> getLogsFieldBuilder() {
            if (this.logsBuilder_ == null) {
                this.logsBuilder_ = new RepeatedFieldBuilderV3<>(this.logs_, (this.bitField0_ & 131072) == 131072, getParentForChildren(), isClean());
                this.logs_ = null;
            }
            return this.logsBuilder_;
        }

        private void ensureMetricsIsMutable() {
            if ((this.bitField0_ & C.DASH_ROLE_SUB_FLAG) != 262144) {
                this.metrics_ = new ArrayList(this.metrics_);
                this.bitField0_ |= C.DASH_ROLE_SUB_FLAG;
            }
        }

        public List<MetricDescriptor> getMetricsList() {
            RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.metrics_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getMetricsCount() {
            RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.metrics_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public MetricDescriptor getMetrics(int i) {
            RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (MetricDescriptor) this.metrics_.get(i);
            }
            return (MetricDescriptor) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setMetrics(int i, MetricDescriptor metricDescriptor) {
            RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, metricDescriptor);
            } else if (metricDescriptor != null) {
                ensureMetricsIsMutable();
                this.metrics_.set(i, metricDescriptor);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setMetrics(int i, com.google.api.MetricDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricsIsMutable();
                this.metrics_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addMetrics(MetricDescriptor metricDescriptor) {
            RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(metricDescriptor);
            } else if (metricDescriptor != null) {
                ensureMetricsIsMutable();
                this.metrics_.add(metricDescriptor);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addMetrics(int i, MetricDescriptor metricDescriptor) {
            RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, metricDescriptor);
            } else if (metricDescriptor != null) {
                ensureMetricsIsMutable();
                this.metrics_.add(i, metricDescriptor);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addMetrics(com.google.api.MetricDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricsIsMutable();
                this.metrics_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addMetrics(int i, com.google.api.MetricDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricsIsMutable();
                this.metrics_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllMetrics(Iterable<? extends MetricDescriptor> iterable) {
            RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.metrics_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearMetrics() {
            RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.metrics_ = Collections.emptyList();
                this.bitField0_ &= -262145;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeMetrics(int i) {
            RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricsIsMutable();
                this.metrics_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.MetricDescriptor.Builder getMetricsBuilder(int i) {
            return (com.google.api.MetricDescriptor.Builder) getMetricsFieldBuilder().getBuilder(i);
        }

        public MetricDescriptorOrBuilder getMetricsOrBuilder(int i) {
            RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (MetricDescriptorOrBuilder) this.metrics_.get(i);
            }
            return (MetricDescriptorOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends MetricDescriptorOrBuilder> getMetricsOrBuilderList() {
            RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> repeatedFieldBuilderV3 = this.metricsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.metrics_);
        }

        public com.google.api.MetricDescriptor.Builder addMetricsBuilder() {
            return (com.google.api.MetricDescriptor.Builder) getMetricsFieldBuilder().addBuilder(MetricDescriptor.getDefaultInstance());
        }

        public com.google.api.MetricDescriptor.Builder addMetricsBuilder(int i) {
            return (com.google.api.MetricDescriptor.Builder) getMetricsFieldBuilder().addBuilder(i, MetricDescriptor.getDefaultInstance());
        }

        public List<com.google.api.MetricDescriptor.Builder> getMetricsBuilderList() {
            return getMetricsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<MetricDescriptor, com.google.api.MetricDescriptor.Builder, MetricDescriptorOrBuilder> getMetricsFieldBuilder() {
            if (this.metricsBuilder_ == null) {
                this.metricsBuilder_ = new RepeatedFieldBuilderV3<>(this.metrics_, (this.bitField0_ & C.DASH_ROLE_SUB_FLAG) == 262144, getParentForChildren(), isClean());
                this.metrics_ = null;
            }
            return this.metricsBuilder_;
        }

        private void ensureMonitoredResourcesIsMutable() {
            if ((this.bitField0_ & 524288) != 524288) {
                this.monitoredResources_ = new ArrayList(this.monitoredResources_);
                this.bitField0_ |= 524288;
            }
        }

        public List<MonitoredResourceDescriptor> getMonitoredResourcesList() {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.monitoredResources_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getMonitoredResourcesCount() {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.monitoredResources_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public MonitoredResourceDescriptor getMonitoredResources(int i) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (MonitoredResourceDescriptor) this.monitoredResources_.get(i);
            }
            return (MonitoredResourceDescriptor) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setMonitoredResources(int i, MonitoredResourceDescriptor monitoredResourceDescriptor) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, monitoredResourceDescriptor);
            } else if (monitoredResourceDescriptor != null) {
                ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.set(i, monitoredResourceDescriptor);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setMonitoredResources(int i, com.google.api.MonitoredResourceDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addMonitoredResources(MonitoredResourceDescriptor monitoredResourceDescriptor) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(monitoredResourceDescriptor);
            } else if (monitoredResourceDescriptor != null) {
                ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.add(monitoredResourceDescriptor);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addMonitoredResources(int i, MonitoredResourceDescriptor monitoredResourceDescriptor) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, monitoredResourceDescriptor);
            } else if (monitoredResourceDescriptor != null) {
                ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.add(i, monitoredResourceDescriptor);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addMonitoredResources(com.google.api.MonitoredResourceDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addMonitoredResources(int i, com.google.api.MonitoredResourceDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllMonitoredResources(Iterable<? extends MonitoredResourceDescriptor> iterable) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMonitoredResourcesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.monitoredResources_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearMonitoredResources() {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.monitoredResources_ = Collections.emptyList();
                this.bitField0_ &= -524289;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeMonitoredResources(int i) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMonitoredResourcesIsMutable();
                this.monitoredResources_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.MonitoredResourceDescriptor.Builder getMonitoredResourcesBuilder(int i) {
            return (com.google.api.MonitoredResourceDescriptor.Builder) getMonitoredResourcesFieldBuilder().getBuilder(i);
        }

        public MonitoredResourceDescriptorOrBuilder getMonitoredResourcesOrBuilder(int i) {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (MonitoredResourceDescriptorOrBuilder) this.monitoredResources_.get(i);
            }
            return (MonitoredResourceDescriptorOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends MonitoredResourceDescriptorOrBuilder> getMonitoredResourcesOrBuilderList() {
            RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> repeatedFieldBuilderV3 = this.monitoredResourcesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.monitoredResources_);
        }

        public com.google.api.MonitoredResourceDescriptor.Builder addMonitoredResourcesBuilder() {
            return (com.google.api.MonitoredResourceDescriptor.Builder) getMonitoredResourcesFieldBuilder().addBuilder(MonitoredResourceDescriptor.getDefaultInstance());
        }

        public com.google.api.MonitoredResourceDescriptor.Builder addMonitoredResourcesBuilder(int i) {
            return (com.google.api.MonitoredResourceDescriptor.Builder) getMonitoredResourcesFieldBuilder().addBuilder(i, MonitoredResourceDescriptor.getDefaultInstance());
        }

        public List<com.google.api.MonitoredResourceDescriptor.Builder> getMonitoredResourcesBuilderList() {
            return getMonitoredResourcesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<MonitoredResourceDescriptor, com.google.api.MonitoredResourceDescriptor.Builder, MonitoredResourceDescriptorOrBuilder> getMonitoredResourcesFieldBuilder() {
            if (this.monitoredResourcesBuilder_ == null) {
                this.monitoredResourcesBuilder_ = new RepeatedFieldBuilderV3<>(this.monitoredResources_, (this.bitField0_ & 524288) == 524288, getParentForChildren(), isClean());
                this.monitoredResources_ = null;
            }
            return this.monitoredResourcesBuilder_;
        }

        public boolean hasBilling() {
            return (this.billingBuilder_ == null && this.billing_ == null) ? false : true;
        }

        public Billing getBilling() {
            SingleFieldBuilderV3<Billing, com.google.api.Billing.Builder, BillingOrBuilder> singleFieldBuilderV3 = this.billingBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Billing) singleFieldBuilderV3.getMessage();
            }
            Billing billing = this.billing_;
            if (billing == null) {
                billing = Billing.getDefaultInstance();
            }
            return billing;
        }

        public Builder setBilling(Billing billing) {
            SingleFieldBuilderV3<Billing, com.google.api.Billing.Builder, BillingOrBuilder> singleFieldBuilderV3 = this.billingBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(billing);
            } else if (billing != null) {
                this.billing_ = billing;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setBilling(com.google.api.Billing.Builder builder) {
            SingleFieldBuilderV3<Billing, com.google.api.Billing.Builder, BillingOrBuilder> singleFieldBuilderV3 = this.billingBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.billing_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeBilling(Billing billing) {
            SingleFieldBuilderV3<Billing, com.google.api.Billing.Builder, BillingOrBuilder> singleFieldBuilderV3 = this.billingBuilder_;
            if (singleFieldBuilderV3 == null) {
                Billing billing2 = this.billing_;
                if (billing2 != null) {
                    this.billing_ = Billing.newBuilder(billing2).mergeFrom(billing).buildPartial();
                } else {
                    this.billing_ = billing;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(billing);
            }
            return this;
        }

        public Builder clearBilling() {
            if (this.billingBuilder_ == null) {
                this.billing_ = null;
                onChanged();
            } else {
                this.billing_ = null;
                this.billingBuilder_ = null;
            }
            return this;
        }

        public com.google.api.Billing.Builder getBillingBuilder() {
            onChanged();
            return (com.google.api.Billing.Builder) getBillingFieldBuilder().getBuilder();
        }

        public BillingOrBuilder getBillingOrBuilder() {
            SingleFieldBuilderV3<Billing, com.google.api.Billing.Builder, BillingOrBuilder> singleFieldBuilderV3 = this.billingBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (BillingOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Billing billing = this.billing_;
            if (billing == null) {
                billing = Billing.getDefaultInstance();
            }
            return billing;
        }

        private SingleFieldBuilderV3<Billing, com.google.api.Billing.Builder, BillingOrBuilder> getBillingFieldBuilder() {
            if (this.billingBuilder_ == null) {
                this.billingBuilder_ = new SingleFieldBuilderV3<>(getBilling(), getParentForChildren(), isClean());
                this.billing_ = null;
            }
            return this.billingBuilder_;
        }

        public boolean hasLogging() {
            return (this.loggingBuilder_ == null && this.logging_ == null) ? false : true;
        }

        public Logging getLogging() {
            SingleFieldBuilderV3<Logging, com.google.api.Logging.Builder, LoggingOrBuilder> singleFieldBuilderV3 = this.loggingBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Logging) singleFieldBuilderV3.getMessage();
            }
            Logging logging = this.logging_;
            if (logging == null) {
                logging = Logging.getDefaultInstance();
            }
            return logging;
        }

        public Builder setLogging(Logging logging) {
            SingleFieldBuilderV3<Logging, com.google.api.Logging.Builder, LoggingOrBuilder> singleFieldBuilderV3 = this.loggingBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(logging);
            } else if (logging != null) {
                this.logging_ = logging;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setLogging(com.google.api.Logging.Builder builder) {
            SingleFieldBuilderV3<Logging, com.google.api.Logging.Builder, LoggingOrBuilder> singleFieldBuilderV3 = this.loggingBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.logging_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeLogging(Logging logging) {
            SingleFieldBuilderV3<Logging, com.google.api.Logging.Builder, LoggingOrBuilder> singleFieldBuilderV3 = this.loggingBuilder_;
            if (singleFieldBuilderV3 == null) {
                Logging logging2 = this.logging_;
                if (logging2 != null) {
                    this.logging_ = Logging.newBuilder(logging2).mergeFrom(logging).buildPartial();
                } else {
                    this.logging_ = logging;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(logging);
            }
            return this;
        }

        public Builder clearLogging() {
            if (this.loggingBuilder_ == null) {
                this.logging_ = null;
                onChanged();
            } else {
                this.logging_ = null;
                this.loggingBuilder_ = null;
            }
            return this;
        }

        public com.google.api.Logging.Builder getLoggingBuilder() {
            onChanged();
            return (com.google.api.Logging.Builder) getLoggingFieldBuilder().getBuilder();
        }

        public LoggingOrBuilder getLoggingOrBuilder() {
            SingleFieldBuilderV3<Logging, com.google.api.Logging.Builder, LoggingOrBuilder> singleFieldBuilderV3 = this.loggingBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (LoggingOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Logging logging = this.logging_;
            if (logging == null) {
                logging = Logging.getDefaultInstance();
            }
            return logging;
        }

        private SingleFieldBuilderV3<Logging, com.google.api.Logging.Builder, LoggingOrBuilder> getLoggingFieldBuilder() {
            if (this.loggingBuilder_ == null) {
                this.loggingBuilder_ = new SingleFieldBuilderV3<>(getLogging(), getParentForChildren(), isClean());
                this.logging_ = null;
            }
            return this.loggingBuilder_;
        }

        public boolean hasMonitoring() {
            return (this.monitoringBuilder_ == null && this.monitoring_ == null) ? false : true;
        }

        public Monitoring getMonitoring() {
            SingleFieldBuilderV3<Monitoring, com.google.api.Monitoring.Builder, MonitoringOrBuilder> singleFieldBuilderV3 = this.monitoringBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Monitoring) singleFieldBuilderV3.getMessage();
            }
            Monitoring monitoring = this.monitoring_;
            if (monitoring == null) {
                monitoring = Monitoring.getDefaultInstance();
            }
            return monitoring;
        }

        public Builder setMonitoring(Monitoring monitoring) {
            SingleFieldBuilderV3<Monitoring, com.google.api.Monitoring.Builder, MonitoringOrBuilder> singleFieldBuilderV3 = this.monitoringBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(monitoring);
            } else if (monitoring != null) {
                this.monitoring_ = monitoring;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setMonitoring(com.google.api.Monitoring.Builder builder) {
            SingleFieldBuilderV3<Monitoring, com.google.api.Monitoring.Builder, MonitoringOrBuilder> singleFieldBuilderV3 = this.monitoringBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.monitoring_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeMonitoring(Monitoring monitoring) {
            SingleFieldBuilderV3<Monitoring, com.google.api.Monitoring.Builder, MonitoringOrBuilder> singleFieldBuilderV3 = this.monitoringBuilder_;
            if (singleFieldBuilderV3 == null) {
                Monitoring monitoring2 = this.monitoring_;
                if (monitoring2 != null) {
                    this.monitoring_ = Monitoring.newBuilder(monitoring2).mergeFrom(monitoring).buildPartial();
                } else {
                    this.monitoring_ = monitoring;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(monitoring);
            }
            return this;
        }

        public Builder clearMonitoring() {
            if (this.monitoringBuilder_ == null) {
                this.monitoring_ = null;
                onChanged();
            } else {
                this.monitoring_ = null;
                this.monitoringBuilder_ = null;
            }
            return this;
        }

        public com.google.api.Monitoring.Builder getMonitoringBuilder() {
            onChanged();
            return (com.google.api.Monitoring.Builder) getMonitoringFieldBuilder().getBuilder();
        }

        public MonitoringOrBuilder getMonitoringOrBuilder() {
            SingleFieldBuilderV3<Monitoring, com.google.api.Monitoring.Builder, MonitoringOrBuilder> singleFieldBuilderV3 = this.monitoringBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (MonitoringOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Monitoring monitoring = this.monitoring_;
            if (monitoring == null) {
                monitoring = Monitoring.getDefaultInstance();
            }
            return monitoring;
        }

        private SingleFieldBuilderV3<Monitoring, com.google.api.Monitoring.Builder, MonitoringOrBuilder> getMonitoringFieldBuilder() {
            if (this.monitoringBuilder_ == null) {
                this.monitoringBuilder_ = new SingleFieldBuilderV3<>(getMonitoring(), getParentForChildren(), isClean());
                this.monitoring_ = null;
            }
            return this.monitoringBuilder_;
        }

        public boolean hasSystemParameters() {
            return (this.systemParametersBuilder_ == null && this.systemParameters_ == null) ? false : true;
        }

        public SystemParameters getSystemParameters() {
            SingleFieldBuilderV3<SystemParameters, com.google.api.SystemParameters.Builder, SystemParametersOrBuilder> singleFieldBuilderV3 = this.systemParametersBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (SystemParameters) singleFieldBuilderV3.getMessage();
            }
            SystemParameters systemParameters = this.systemParameters_;
            if (systemParameters == null) {
                systemParameters = SystemParameters.getDefaultInstance();
            }
            return systemParameters;
        }

        public Builder setSystemParameters(SystemParameters systemParameters) {
            SingleFieldBuilderV3<SystemParameters, com.google.api.SystemParameters.Builder, SystemParametersOrBuilder> singleFieldBuilderV3 = this.systemParametersBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(systemParameters);
            } else if (systemParameters != null) {
                this.systemParameters_ = systemParameters;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setSystemParameters(com.google.api.SystemParameters.Builder builder) {
            SingleFieldBuilderV3<SystemParameters, com.google.api.SystemParameters.Builder, SystemParametersOrBuilder> singleFieldBuilderV3 = this.systemParametersBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.systemParameters_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSystemParameters(SystemParameters systemParameters) {
            SingleFieldBuilderV3<SystemParameters, com.google.api.SystemParameters.Builder, SystemParametersOrBuilder> singleFieldBuilderV3 = this.systemParametersBuilder_;
            if (singleFieldBuilderV3 == null) {
                SystemParameters systemParameters2 = this.systemParameters_;
                if (systemParameters2 != null) {
                    this.systemParameters_ = SystemParameters.newBuilder(systemParameters2).mergeFrom(systemParameters).buildPartial();
                } else {
                    this.systemParameters_ = systemParameters;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(systemParameters);
            }
            return this;
        }

        public Builder clearSystemParameters() {
            if (this.systemParametersBuilder_ == null) {
                this.systemParameters_ = null;
                onChanged();
            } else {
                this.systemParameters_ = null;
                this.systemParametersBuilder_ = null;
            }
            return this;
        }

        public com.google.api.SystemParameters.Builder getSystemParametersBuilder() {
            onChanged();
            return (com.google.api.SystemParameters.Builder) getSystemParametersFieldBuilder().getBuilder();
        }

        public SystemParametersOrBuilder getSystemParametersOrBuilder() {
            SingleFieldBuilderV3<SystemParameters, com.google.api.SystemParameters.Builder, SystemParametersOrBuilder> singleFieldBuilderV3 = this.systemParametersBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (SystemParametersOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            SystemParameters systemParameters = this.systemParameters_;
            if (systemParameters == null) {
                systemParameters = SystemParameters.getDefaultInstance();
            }
            return systemParameters;
        }

        private SingleFieldBuilderV3<SystemParameters, com.google.api.SystemParameters.Builder, SystemParametersOrBuilder> getSystemParametersFieldBuilder() {
            if (this.systemParametersBuilder_ == null) {
                this.systemParametersBuilder_ = new SingleFieldBuilderV3<>(getSystemParameters(), getParentForChildren(), isClean());
                this.systemParameters_ = null;
            }
            return this.systemParametersBuilder_;
        }

        public boolean hasSourceInfo() {
            return (this.sourceInfoBuilder_ == null && this.sourceInfo_ == null) ? false : true;
        }

        public SourceInfo getSourceInfo() {
            SingleFieldBuilderV3<SourceInfo, com.google.api.SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV3 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (SourceInfo) singleFieldBuilderV3.getMessage();
            }
            SourceInfo sourceInfo = this.sourceInfo_;
            if (sourceInfo == null) {
                sourceInfo = SourceInfo.getDefaultInstance();
            }
            return sourceInfo;
        }

        public Builder setSourceInfo(SourceInfo sourceInfo) {
            SingleFieldBuilderV3<SourceInfo, com.google.api.SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV3 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(sourceInfo);
            } else if (sourceInfo != null) {
                this.sourceInfo_ = sourceInfo;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setSourceInfo(com.google.api.SourceInfo.Builder builder) {
            SingleFieldBuilderV3<SourceInfo, com.google.api.SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV3 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.sourceInfo_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSourceInfo(SourceInfo sourceInfo) {
            SingleFieldBuilderV3<SourceInfo, com.google.api.SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV3 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV3 == null) {
                SourceInfo sourceInfo2 = this.sourceInfo_;
                if (sourceInfo2 != null) {
                    this.sourceInfo_ = SourceInfo.newBuilder(sourceInfo2).mergeFrom(sourceInfo).buildPartial();
                } else {
                    this.sourceInfo_ = sourceInfo;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(sourceInfo);
            }
            return this;
        }

        public Builder clearSourceInfo() {
            if (this.sourceInfoBuilder_ == null) {
                this.sourceInfo_ = null;
                onChanged();
            } else {
                this.sourceInfo_ = null;
                this.sourceInfoBuilder_ = null;
            }
            return this;
        }

        public com.google.api.SourceInfo.Builder getSourceInfoBuilder() {
            onChanged();
            return (com.google.api.SourceInfo.Builder) getSourceInfoFieldBuilder().getBuilder();
        }

        public SourceInfoOrBuilder getSourceInfoOrBuilder() {
            SingleFieldBuilderV3<SourceInfo, com.google.api.SourceInfo.Builder, SourceInfoOrBuilder> singleFieldBuilderV3 = this.sourceInfoBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (SourceInfoOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            SourceInfo sourceInfo = this.sourceInfo_;
            if (sourceInfo == null) {
                sourceInfo = SourceInfo.getDefaultInstance();
            }
            return sourceInfo;
        }

        private SingleFieldBuilderV3<SourceInfo, com.google.api.SourceInfo.Builder, SourceInfoOrBuilder> getSourceInfoFieldBuilder() {
            if (this.sourceInfoBuilder_ == null) {
                this.sourceInfoBuilder_ = new SingleFieldBuilderV3<>(getSourceInfo(), getParentForChildren(), isClean());
                this.sourceInfo_ = null;
            }
            return this.sourceInfoBuilder_;
        }

        public boolean hasExperimental() {
            return (this.experimentalBuilder_ == null && this.experimental_ == null) ? false : true;
        }

        public Experimental getExperimental() {
            SingleFieldBuilderV3<Experimental, com.google.api.Experimental.Builder, ExperimentalOrBuilder> singleFieldBuilderV3 = this.experimentalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Experimental) singleFieldBuilderV3.getMessage();
            }
            Experimental experimental = this.experimental_;
            if (experimental == null) {
                experimental = Experimental.getDefaultInstance();
            }
            return experimental;
        }

        public Builder setExperimental(Experimental experimental) {
            SingleFieldBuilderV3<Experimental, com.google.api.Experimental.Builder, ExperimentalOrBuilder> singleFieldBuilderV3 = this.experimentalBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(experimental);
            } else if (experimental != null) {
                this.experimental_ = experimental;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setExperimental(com.google.api.Experimental.Builder builder) {
            SingleFieldBuilderV3<Experimental, com.google.api.Experimental.Builder, ExperimentalOrBuilder> singleFieldBuilderV3 = this.experimentalBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.experimental_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeExperimental(Experimental experimental) {
            SingleFieldBuilderV3<Experimental, com.google.api.Experimental.Builder, ExperimentalOrBuilder> singleFieldBuilderV3 = this.experimentalBuilder_;
            if (singleFieldBuilderV3 == null) {
                Experimental experimental2 = this.experimental_;
                if (experimental2 != null) {
                    this.experimental_ = Experimental.newBuilder(experimental2).mergeFrom(experimental).buildPartial();
                } else {
                    this.experimental_ = experimental;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(experimental);
            }
            return this;
        }

        public Builder clearExperimental() {
            if (this.experimentalBuilder_ == null) {
                this.experimental_ = null;
                onChanged();
            } else {
                this.experimental_ = null;
                this.experimentalBuilder_ = null;
            }
            return this;
        }

        public com.google.api.Experimental.Builder getExperimentalBuilder() {
            onChanged();
            return (com.google.api.Experimental.Builder) getExperimentalFieldBuilder().getBuilder();
        }

        public ExperimentalOrBuilder getExperimentalOrBuilder() {
            SingleFieldBuilderV3<Experimental, com.google.api.Experimental.Builder, ExperimentalOrBuilder> singleFieldBuilderV3 = this.experimentalBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ExperimentalOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Experimental experimental = this.experimental_;
            if (experimental == null) {
                experimental = Experimental.getDefaultInstance();
            }
            return experimental;
        }

        private SingleFieldBuilderV3<Experimental, com.google.api.Experimental.Builder, ExperimentalOrBuilder> getExperimentalFieldBuilder() {
            if (this.experimentalBuilder_ == null) {
                this.experimentalBuilder_ = new SingleFieldBuilderV3<>(getExperimental(), getParentForChildren(), isClean());
                this.experimental_ = null;
            }
            return this.experimentalBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private Service(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Service() {
        this.memoizedIsInitialized = -1;
        this.name_ = "";
        this.id_ = "";
        this.title_ = "";
        this.producerProjectId_ = "";
        this.apis_ = Collections.emptyList();
        this.types_ = Collections.emptyList();
        this.enums_ = Collections.emptyList();
        this.endpoints_ = Collections.emptyList();
        this.logs_ = Collections.emptyList();
        this.metrics_ = Collections.emptyList();
        this.monitoredResources_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARNING: type inference failed for: r12v0 */
    /* JADX WARNING: type inference failed for: r12v1, types: [com.google.api.Documentation$Builder] */
    /* JADX WARNING: type inference failed for: r12v2, types: [com.google.api.Documentation$Builder] */
    /* JADX WARNING: type inference failed for: r12v3, types: [com.google.api.Backend$Builder] */
    /* JADX WARNING: type inference failed for: r12v4, types: [com.google.api.Backend$Builder] */
    /* JADX WARNING: type inference failed for: r12v5, types: [com.google.api.Http$Builder] */
    /* JADX WARNING: type inference failed for: r12v6, types: [com.google.api.Http$Builder] */
    /* JADX WARNING: type inference failed for: r12v7, types: [com.google.api.Quota$Builder] */
    /* JADX WARNING: type inference failed for: r12v8, types: [com.google.api.Quota$Builder] */
    /* JADX WARNING: type inference failed for: r12v9, types: [com.google.api.Authentication$Builder] */
    /* JADX WARNING: type inference failed for: r12v10, types: [com.google.api.Authentication$Builder] */
    /* JADX WARNING: type inference failed for: r12v11, types: [com.google.api.Context$Builder] */
    /* JADX WARNING: type inference failed for: r12v12, types: [com.google.api.Context$Builder] */
    /* JADX WARNING: type inference failed for: r12v13, types: [com.google.api.Usage$Builder] */
    /* JADX WARNING: type inference failed for: r12v14, types: [com.google.api.Usage$Builder] */
    /* JADX WARNING: type inference failed for: r12v15, types: [com.google.protobuf.UInt32Value$Builder] */
    /* JADX WARNING: type inference failed for: r12v16, types: [com.google.protobuf.UInt32Value$Builder] */
    /* JADX WARNING: type inference failed for: r12v17, types: [com.google.api.Control$Builder] */
    /* JADX WARNING: type inference failed for: r12v18, types: [com.google.api.Control$Builder] */
    /* JADX WARNING: type inference failed for: r12v19, types: [com.google.api.Billing$Builder] */
    /* JADX WARNING: type inference failed for: r12v20, types: [com.google.api.Billing$Builder] */
    /* JADX WARNING: type inference failed for: r12v21, types: [com.google.api.Logging$Builder] */
    /* JADX WARNING: type inference failed for: r12v22, types: [com.google.api.Logging$Builder] */
    /* JADX WARNING: type inference failed for: r12v23, types: [com.google.api.Monitoring$Builder] */
    /* JADX WARNING: type inference failed for: r12v24, types: [com.google.api.Monitoring$Builder] */
    /* JADX WARNING: type inference failed for: r12v25, types: [com.google.api.SystemParameters$Builder] */
    /* JADX WARNING: type inference failed for: r12v26, types: [com.google.api.SystemParameters$Builder] */
    /* JADX WARNING: type inference failed for: r12v27, types: [com.google.api.SourceInfo$Builder] */
    /* JADX WARNING: type inference failed for: r12v28, types: [com.google.api.SourceInfo$Builder] */
    /* JADX WARNING: type inference failed for: r12v29, types: [com.google.api.Experimental$Builder] */
    /* JADX WARNING: type inference failed for: r12v30, types: [com.google.api.Experimental$Builder] */
    /* JADX WARNING: type inference failed for: r12v31 */
    /* JADX WARNING: type inference failed for: r12v32 */
    /* JADX WARNING: type inference failed for: r12v33 */
    /* JADX WARNING: type inference failed for: r12v34 */
    /* JADX WARNING: type inference failed for: r12v35 */
    /* JADX WARNING: type inference failed for: r12v36 */
    /* JADX WARNING: type inference failed for: r12v37 */
    /* JADX WARNING: type inference failed for: r12v38 */
    /* JADX WARNING: type inference failed for: r12v39 */
    /* JADX WARNING: type inference failed for: r12v40 */
    /* JADX WARNING: type inference failed for: r12v41 */
    /* JADX WARNING: type inference failed for: r12v42 */
    /* JADX WARNING: type inference failed for: r12v43 */
    /* JADX WARNING: type inference failed for: r12v44 */
    /* JADX WARNING: type inference failed for: r12v45 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r12v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.google.api.Backend$Builder, com.google.api.Documentation$Builder, com.google.api.Http$Builder, com.google.api.Quota$Builder, com.google.api.Authentication$Builder, com.google.api.Context$Builder, com.google.api.Usage$Builder, com.google.protobuf.UInt32Value$Builder, com.google.api.Control$Builder, com.google.api.Billing$Builder, com.google.api.Logging$Builder, com.google.api.Monitoring$Builder, com.google.api.SystemParameters$Builder, com.google.api.SourceInfo$Builder, com.google.api.Experimental$Builder]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], com.google.api.Documentation$Builder, com.google.api.Backend$Builder, com.google.api.Http$Builder, com.google.api.Quota$Builder, com.google.api.Authentication$Builder, com.google.api.Context$Builder, com.google.api.Usage$Builder, com.google.protobuf.UInt32Value$Builder, com.google.api.Control$Builder, com.google.api.Billing$Builder, com.google.api.Logging$Builder, com.google.api.Monitoring$Builder, com.google.api.SystemParameters$Builder, com.google.api.SourceInfo$Builder, com.google.api.Experimental$Builder]
  mth insns count: 408
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 16 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private Service(com.google.protobuf.CodedInputStream r14, com.google.protobuf.ExtensionRegistryLite r15) throws com.google.protobuf.InvalidProtocolBufferException {
        /*
            r13 = this;
            r13.<init>()
            if (r15 == 0) goto L_0x040a
            com.google.protobuf.UnknownFieldSet$Builder r0 = com.google.protobuf.UnknownFieldSet.newBuilder()
            r1 = 0
            r2 = 0
        L_0x000b:
            r3 = 524288(0x80000, float:7.34684E-40)
            r4 = 262144(0x40000, float:3.67342E-40)
            r5 = 131072(0x20000, float:1.83671E-40)
            r6 = 32768(0x8000, float:4.5918E-41)
            r7 = 128(0x80, float:1.794E-43)
            r8 = 64
            r9 = 32
            if (r1 != 0) goto L_0x03ac
            int r10 = r14.readTag()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r11 = 1
            r12 = 0
            switch(r10) {
                case 0: goto L_0x0333;
                case 10: goto L_0x032b;
                case 18: goto L_0x0323;
                case 26: goto L_0x0307;
                case 34: goto L_0x02eb;
                case 42: goto L_0x02cf;
                case 50: goto L_0x02aa;
                case 66: goto L_0x0285;
                case 74: goto L_0x0260;
                case 82: goto L_0x023b;
                case 90: goto L_0x0216;
                case 98: goto L_0x01f1;
                case 122: goto L_0x01cc;
                case 146: goto L_0x01b1;
                case 162: goto L_0x018c;
                case 170: goto L_0x0167;
                case 178: goto L_0x015f;
                case 186: goto L_0x0144;
                case 194: goto L_0x0129;
                case 202: goto L_0x010e;
                case 210: goto L_0x00e9;
                case 218: goto L_0x00c4;
                case 226: goto L_0x009f;
                case 234: goto L_0x007a;
                case 266: goto L_0x0073;
                case 298: goto L_0x004f;
                case 810: goto L_0x002b;
                default: goto L_0x0025;
            }     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
        L_0x0025:
            boolean r3 = r13.parseUnknownFieldProto3(r14, r0, r15, r10)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x0336
        L_0x002b:
            com.google.api.Experimental r10 = r13.experimental_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r10 == 0) goto L_0x0035
            com.google.api.Experimental r10 = r13.experimental_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Experimental$Builder r12 = r10.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
        L_0x0035:
            com.google.protobuf.Parser r10 = com.google.api.Experimental.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r10 = r14.readMessage(r10, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Experimental r10 = (com.google.api.Experimental) r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.experimental_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r12 == 0) goto L_0x000b
            com.google.api.Experimental r10 = r13.experimental_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r12.mergeFrom(r10)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Experimental r10 = r12.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.experimental_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x004f:
            com.google.api.SourceInfo r10 = r13.sourceInfo_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r10 == 0) goto L_0x0059
            com.google.api.SourceInfo r10 = r13.sourceInfo_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.SourceInfo$Builder r12 = r10.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
        L_0x0059:
            com.google.protobuf.Parser r10 = com.google.api.SourceInfo.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r10 = r14.readMessage(r10, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.SourceInfo r10 = (com.google.api.SourceInfo) r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.sourceInfo_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r12 == 0) goto L_0x000b
            com.google.api.SourceInfo r10 = r13.sourceInfo_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r12.mergeFrom(r10)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.SourceInfo r10 = r12.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.sourceInfo_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x0073:
            java.lang.String r10 = r14.readStringRequireUtf8()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.id_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x007a:
            com.google.api.SystemParameters r10 = r13.systemParameters_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r10 == 0) goto L_0x0084
            com.google.api.SystemParameters r10 = r13.systemParameters_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.SystemParameters$Builder r12 = r10.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
        L_0x0084:
            com.google.protobuf.Parser r10 = com.google.api.SystemParameters.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r10 = r14.readMessage(r10, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.SystemParameters r10 = (com.google.api.SystemParameters) r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.systemParameters_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r12 == 0) goto L_0x000b
            com.google.api.SystemParameters r10 = r13.systemParameters_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r12.mergeFrom(r10)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.SystemParameters r10 = r12.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.systemParameters_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x009f:
            com.google.api.Monitoring r10 = r13.monitoring_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r10 == 0) goto L_0x00a9
            com.google.api.Monitoring r10 = r13.monitoring_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Monitoring$Builder r12 = r10.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
        L_0x00a9:
            com.google.protobuf.Parser r10 = com.google.api.Monitoring.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r10 = r14.readMessage(r10, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Monitoring r10 = (com.google.api.Monitoring) r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.monitoring_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r12 == 0) goto L_0x000b
            com.google.api.Monitoring r10 = r13.monitoring_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r12.mergeFrom(r10)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Monitoring r10 = r12.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.monitoring_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x00c4:
            com.google.api.Logging r10 = r13.logging_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r10 == 0) goto L_0x00ce
            com.google.api.Logging r10 = r13.logging_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Logging$Builder r12 = r10.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
        L_0x00ce:
            com.google.protobuf.Parser r10 = com.google.api.Logging.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r10 = r14.readMessage(r10, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Logging r10 = (com.google.api.Logging) r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.logging_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r12 == 0) goto L_0x000b
            com.google.api.Logging r10 = r13.logging_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r12.mergeFrom(r10)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Logging r10 = r12.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.logging_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x00e9:
            com.google.api.Billing r10 = r13.billing_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r10 == 0) goto L_0x00f3
            com.google.api.Billing r10 = r13.billing_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Billing$Builder r12 = r10.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
        L_0x00f3:
            com.google.protobuf.Parser r10 = com.google.api.Billing.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r10 = r14.readMessage(r10, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Billing r10 = (com.google.api.Billing) r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.billing_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r12 == 0) goto L_0x000b
            com.google.api.Billing r10 = r13.billing_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r12.mergeFrom(r10)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Billing r10 = r12.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.billing_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x010e:
            r10 = r2 & r3
            if (r10 == r3) goto L_0x011a
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r10.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.monitoredResources_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r2 = r2 | r3
        L_0x011a:
            java.util.List<com.google.api.MonitoredResourceDescriptor> r10 = r13.monitoredResources_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.Parser r11 = com.google.api.MonitoredResourceDescriptor.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r11 = r14.readMessage(r11, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r10.add(r11)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x0129:
            r10 = r2 & r4
            if (r10 == r4) goto L_0x0135
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r10.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.metrics_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r2 = r2 | r4
        L_0x0135:
            java.util.List<com.google.api.MetricDescriptor> r10 = r13.metrics_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.Parser r11 = com.google.api.MetricDescriptor.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r11 = r14.readMessage(r11, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r10.add(r11)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x0144:
            r10 = r2 & r5
            if (r10 == r5) goto L_0x0150
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r10.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.logs_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r2 = r2 | r5
        L_0x0150:
            java.util.List<com.google.api.LogDescriptor> r10 = r13.logs_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.Parser r11 = com.google.api.LogDescriptor.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r11 = r14.readMessage(r11, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r10.add(r11)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x015f:
            java.lang.String r10 = r14.readStringRequireUtf8()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.producerProjectId_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x0167:
            com.google.api.Control r10 = r13.control_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r10 == 0) goto L_0x0171
            com.google.api.Control r10 = r13.control_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Control$Builder r12 = r10.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
        L_0x0171:
            com.google.protobuf.Parser r10 = com.google.api.Control.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r10 = r14.readMessage(r10, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Control r10 = (com.google.api.Control) r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.control_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r12 == 0) goto L_0x000b
            com.google.api.Control r10 = r13.control_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r12.mergeFrom(r10)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Control r10 = r12.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.control_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x018c:
            com.google.protobuf.UInt32Value r10 = r13.configVersion_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r10 == 0) goto L_0x0196
            com.google.protobuf.UInt32Value r10 = r13.configVersion_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.UInt32Value$Builder r12 = r10.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
        L_0x0196:
            com.google.protobuf.Parser r10 = com.google.protobuf.UInt32Value.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r10 = r14.readMessage(r10, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.UInt32Value r10 = (com.google.protobuf.UInt32Value) r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.configVersion_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r12 == 0) goto L_0x000b
            com.google.protobuf.UInt32Value r10 = r13.configVersion_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r12.mergeFrom(r10)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.UInt32Value r10 = r12.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.configVersion_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x01b1:
            r10 = r2 & r6
            if (r10 == r6) goto L_0x01bd
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r10.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.endpoints_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r2 = r2 | r6
        L_0x01bd:
            java.util.List<com.google.api.Endpoint> r10 = r13.endpoints_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.Parser r11 = com.google.api.Endpoint.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r11 = r14.readMessage(r11, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r10.add(r11)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x01cc:
            com.google.api.Usage r10 = r13.usage_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r10 == 0) goto L_0x01d6
            com.google.api.Usage r10 = r13.usage_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Usage$Builder r12 = r10.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
        L_0x01d6:
            com.google.protobuf.Parser r10 = com.google.api.Usage.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r10 = r14.readMessage(r10, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Usage r10 = (com.google.api.Usage) r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.usage_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r12 == 0) goto L_0x000b
            com.google.api.Usage r10 = r13.usage_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r12.mergeFrom(r10)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Usage r10 = r12.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.usage_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x01f1:
            com.google.api.Context r10 = r13.context_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r10 == 0) goto L_0x01fb
            com.google.api.Context r10 = r13.context_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Context$Builder r12 = r10.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
        L_0x01fb:
            com.google.protobuf.Parser r10 = com.google.api.Context.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r10 = r14.readMessage(r10, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Context r10 = (com.google.api.Context) r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.context_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r12 == 0) goto L_0x000b
            com.google.api.Context r10 = r13.context_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r12.mergeFrom(r10)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Context r10 = r12.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.context_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x0216:
            com.google.api.Authentication r10 = r13.authentication_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r10 == 0) goto L_0x0220
            com.google.api.Authentication r10 = r13.authentication_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Authentication$Builder r12 = r10.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
        L_0x0220:
            com.google.protobuf.Parser r10 = com.google.api.Authentication.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r10 = r14.readMessage(r10, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Authentication r10 = (com.google.api.Authentication) r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.authentication_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r12 == 0) goto L_0x000b
            com.google.api.Authentication r10 = r13.authentication_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r12.mergeFrom(r10)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Authentication r10 = r12.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.authentication_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x023b:
            com.google.api.Quota r10 = r13.quota_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r10 == 0) goto L_0x0245
            com.google.api.Quota r10 = r13.quota_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Quota$Builder r12 = r10.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
        L_0x0245:
            com.google.protobuf.Parser r10 = com.google.api.Quota.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r10 = r14.readMessage(r10, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Quota r10 = (com.google.api.Quota) r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.quota_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r12 == 0) goto L_0x000b
            com.google.api.Quota r10 = r13.quota_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r12.mergeFrom(r10)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Quota r10 = r12.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.quota_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x0260:
            com.google.api.Http r10 = r13.http_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r10 == 0) goto L_0x026a
            com.google.api.Http r10 = r13.http_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Http$Builder r12 = r10.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
        L_0x026a:
            com.google.protobuf.Parser r10 = com.google.api.Http.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r10 = r14.readMessage(r10, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Http r10 = (com.google.api.Http) r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.http_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r12 == 0) goto L_0x000b
            com.google.api.Http r10 = r13.http_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r12.mergeFrom(r10)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Http r10 = r12.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.http_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x0285:
            com.google.api.Backend r10 = r13.backend_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r10 == 0) goto L_0x028f
            com.google.api.Backend r10 = r13.backend_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Backend$Builder r12 = r10.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
        L_0x028f:
            com.google.protobuf.Parser r10 = com.google.api.Backend.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r10 = r14.readMessage(r10, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Backend r10 = (com.google.api.Backend) r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.backend_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r12 == 0) goto L_0x000b
            com.google.api.Backend r10 = r13.backend_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r12.mergeFrom(r10)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Backend r10 = r12.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.backend_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x02aa:
            com.google.api.Documentation r10 = r13.documentation_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r10 == 0) goto L_0x02b4
            com.google.api.Documentation r10 = r13.documentation_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Documentation$Builder r12 = r10.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
        L_0x02b4:
            com.google.protobuf.Parser r10 = com.google.api.Documentation.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r10 = r14.readMessage(r10, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Documentation r10 = (com.google.api.Documentation) r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.documentation_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            if (r12 == 0) goto L_0x000b
            com.google.api.Documentation r10 = r13.documentation_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r12.mergeFrom(r10)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.api.Documentation r10 = r12.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.documentation_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x02cf:
            r10 = r2 & 128(0x80, float:1.794E-43)
            if (r10 == r7) goto L_0x02dc
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r10.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.enums_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r2 = r2 | 128(0x80, float:1.794E-43)
        L_0x02dc:
            java.util.List<com.google.protobuf.Enum> r10 = r13.enums_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.Parser r11 = com.google.protobuf.Enum.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r11 = r14.readMessage(r11, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r10.add(r11)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x02eb:
            r10 = r2 & 64
            if (r10 == r8) goto L_0x02f8
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r10.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.types_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r2 = r2 | 64
        L_0x02f8:
            java.util.List<com.google.protobuf.Type> r10 = r13.types_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.Parser r11 = com.google.protobuf.Type.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r11 = r14.readMessage(r11, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r10.add(r11)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x0307:
            r10 = r2 & 32
            if (r10 == r9) goto L_0x0314
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r10.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.apis_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r2 = r2 | 32
        L_0x0314:
            java.util.List<com.google.protobuf.Api> r10 = r13.apis_     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.Parser r11 = com.google.protobuf.Api.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            com.google.protobuf.MessageLite r11 = r14.readMessage(r11, r15)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r10.add(r11)     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x0323:
            java.lang.String r10 = r14.readStringRequireUtf8()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.title_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x032b:
            java.lang.String r10 = r14.readStringRequireUtf8()     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            r13.name_ = r10     // Catch:{ InvalidProtocolBufferException -> 0x0348, IOException -> 0x033d }
            goto L_0x000b
        L_0x0333:
            r1 = 1
            goto L_0x000b
        L_0x0336:
            if (r3 != 0) goto L_0x000b
            r1 = 1
            goto L_0x000b
        L_0x033b:
            r14 = move-exception
            goto L_0x034e
        L_0x033d:
            r14 = move-exception
            com.google.protobuf.InvalidProtocolBufferException r15 = new com.google.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x033b }
            r15.<init>(r14)     // Catch:{ all -> 0x033b }
            com.google.protobuf.InvalidProtocolBufferException r14 = r15.setUnfinishedMessage(r13)     // Catch:{ all -> 0x033b }
            throw r14     // Catch:{ all -> 0x033b }
        L_0x0348:
            r14 = move-exception
            com.google.protobuf.InvalidProtocolBufferException r14 = r14.setUnfinishedMessage(r13)     // Catch:{ all -> 0x033b }
            throw r14     // Catch:{ all -> 0x033b }
        L_0x034e:
            r15 = r2 & 32
            if (r15 != r9) goto L_0x035a
            java.util.List<com.google.protobuf.Api> r15 = r13.apis_
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.apis_ = r15
        L_0x035a:
            r15 = r2 & 64
            if (r15 != r8) goto L_0x0366
            java.util.List<com.google.protobuf.Type> r15 = r13.types_
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.types_ = r15
        L_0x0366:
            r15 = r2 & 128(0x80, float:1.794E-43)
            if (r15 != r7) goto L_0x0372
            java.util.List<com.google.protobuf.Enum> r15 = r13.enums_
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.enums_ = r15
        L_0x0372:
            r15 = r2 & r6
            if (r15 != r6) goto L_0x037e
            java.util.List<com.google.api.Endpoint> r15 = r13.endpoints_
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.endpoints_ = r15
        L_0x037e:
            r15 = r2 & r5
            if (r15 != r5) goto L_0x038a
            java.util.List<com.google.api.LogDescriptor> r15 = r13.logs_
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.logs_ = r15
        L_0x038a:
            r15 = r2 & r4
            if (r15 != r4) goto L_0x0396
            java.util.List<com.google.api.MetricDescriptor> r15 = r13.metrics_
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.metrics_ = r15
        L_0x0396:
            r15 = r2 & r3
            if (r15 != r3) goto L_0x03a2
            java.util.List<com.google.api.MonitoredResourceDescriptor> r15 = r13.monitoredResources_
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.monitoredResources_ = r15
        L_0x03a2:
            com.google.protobuf.UnknownFieldSet r15 = r0.build()
            r13.unknownFields = r15
            r13.makeExtensionsImmutable()
            throw r14
        L_0x03ac:
            r14 = r2 & 32
            if (r14 != r9) goto L_0x03b8
            java.util.List<com.google.protobuf.Api> r14 = r13.apis_
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.apis_ = r14
        L_0x03b8:
            r14 = r2 & 64
            if (r14 != r8) goto L_0x03c4
            java.util.List<com.google.protobuf.Type> r14 = r13.types_
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.types_ = r14
        L_0x03c4:
            r14 = r2 & 128(0x80, float:1.794E-43)
            if (r14 != r7) goto L_0x03d0
            java.util.List<com.google.protobuf.Enum> r14 = r13.enums_
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.enums_ = r14
        L_0x03d0:
            r14 = r2 & r6
            if (r14 != r6) goto L_0x03dc
            java.util.List<com.google.api.Endpoint> r14 = r13.endpoints_
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.endpoints_ = r14
        L_0x03dc:
            r14 = r2 & r5
            if (r14 != r5) goto L_0x03e8
            java.util.List<com.google.api.LogDescriptor> r14 = r13.logs_
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.logs_ = r14
        L_0x03e8:
            r14 = r2 & r4
            if (r14 != r4) goto L_0x03f4
            java.util.List<com.google.api.MetricDescriptor> r14 = r13.metrics_
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.metrics_ = r14
        L_0x03f4:
            r14 = r2 & r3
            if (r14 != r3) goto L_0x0400
            java.util.List<com.google.api.MonitoredResourceDescriptor> r14 = r13.monitoredResources_
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.monitoredResources_ = r14
        L_0x0400:
            com.google.protobuf.UnknownFieldSet r14 = r0.build()
            r13.unknownFields = r14
            r13.makeExtensionsImmutable()
            return
        L_0x040a:
            java.lang.NullPointerException r14 = new java.lang.NullPointerException
            r14.<init>()
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.Service.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
    }

    public static final Descriptor getDescriptor() {
        return ServiceProto.internal_static_google_api_Service_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ServiceProto.internal_static_google_api_Service_fieldAccessorTable.ensureFieldAccessorsInitialized(Service.class, Builder.class);
    }

    public boolean hasConfigVersion() {
        return this.configVersion_ != null;
    }

    public UInt32Value getConfigVersion() {
        UInt32Value uInt32Value = this.configVersion_;
        return uInt32Value == null ? UInt32Value.getDefaultInstance() : uInt32Value;
    }

    public UInt32ValueOrBuilder getConfigVersionOrBuilder() {
        return getConfigVersion();
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

    public String getId() {
        Object obj = this.id_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.id_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getIdBytes() {
        Object obj = this.id_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.id_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getTitle() {
        Object obj = this.title_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.title_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getTitleBytes() {
        Object obj = this.title_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.title_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getProducerProjectId() {
        Object obj = this.producerProjectId_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.producerProjectId_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getProducerProjectIdBytes() {
        Object obj = this.producerProjectId_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.producerProjectId_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public List<Api> getApisList() {
        return this.apis_;
    }

    public List<? extends ApiOrBuilder> getApisOrBuilderList() {
        return this.apis_;
    }

    public int getApisCount() {
        return this.apis_.size();
    }

    public Api getApis(int i) {
        return (Api) this.apis_.get(i);
    }

    public ApiOrBuilder getApisOrBuilder(int i) {
        return (ApiOrBuilder) this.apis_.get(i);
    }

    public List<Type> getTypesList() {
        return this.types_;
    }

    public List<? extends TypeOrBuilder> getTypesOrBuilderList() {
        return this.types_;
    }

    public int getTypesCount() {
        return this.types_.size();
    }

    public Type getTypes(int i) {
        return (Type) this.types_.get(i);
    }

    public TypeOrBuilder getTypesOrBuilder(int i) {
        return (TypeOrBuilder) this.types_.get(i);
    }

    public List<Enum> getEnumsList() {
        return this.enums_;
    }

    public List<? extends EnumOrBuilder> getEnumsOrBuilderList() {
        return this.enums_;
    }

    public int getEnumsCount() {
        return this.enums_.size();
    }

    public Enum getEnums(int i) {
        return (Enum) this.enums_.get(i);
    }

    public EnumOrBuilder getEnumsOrBuilder(int i) {
        return (EnumOrBuilder) this.enums_.get(i);
    }

    public boolean hasDocumentation() {
        return this.documentation_ != null;
    }

    public Documentation getDocumentation() {
        Documentation documentation = this.documentation_;
        return documentation == null ? Documentation.getDefaultInstance() : documentation;
    }

    public DocumentationOrBuilder getDocumentationOrBuilder() {
        return getDocumentation();
    }

    public boolean hasBackend() {
        return this.backend_ != null;
    }

    public Backend getBackend() {
        Backend backend = this.backend_;
        return backend == null ? Backend.getDefaultInstance() : backend;
    }

    public BackendOrBuilder getBackendOrBuilder() {
        return getBackend();
    }

    public boolean hasHttp() {
        return this.http_ != null;
    }

    public Http getHttp() {
        Http http = this.http_;
        return http == null ? Http.getDefaultInstance() : http;
    }

    public HttpOrBuilder getHttpOrBuilder() {
        return getHttp();
    }

    public boolean hasQuota() {
        return this.quota_ != null;
    }

    public Quota getQuota() {
        Quota quota = this.quota_;
        return quota == null ? Quota.getDefaultInstance() : quota;
    }

    public QuotaOrBuilder getQuotaOrBuilder() {
        return getQuota();
    }

    public boolean hasAuthentication() {
        return this.authentication_ != null;
    }

    public Authentication getAuthentication() {
        Authentication authentication = this.authentication_;
        return authentication == null ? Authentication.getDefaultInstance() : authentication;
    }

    public AuthenticationOrBuilder getAuthenticationOrBuilder() {
        return getAuthentication();
    }

    public boolean hasContext() {
        return this.context_ != null;
    }

    public Context getContext() {
        Context context = this.context_;
        return context == null ? Context.getDefaultInstance() : context;
    }

    public ContextOrBuilder getContextOrBuilder() {
        return getContext();
    }

    public boolean hasUsage() {
        return this.usage_ != null;
    }

    public Usage getUsage() {
        Usage usage = this.usage_;
        return usage == null ? Usage.getDefaultInstance() : usage;
    }

    public UsageOrBuilder getUsageOrBuilder() {
        return getUsage();
    }

    public List<Endpoint> getEndpointsList() {
        return this.endpoints_;
    }

    public List<? extends EndpointOrBuilder> getEndpointsOrBuilderList() {
        return this.endpoints_;
    }

    public int getEndpointsCount() {
        return this.endpoints_.size();
    }

    public Endpoint getEndpoints(int i) {
        return (Endpoint) this.endpoints_.get(i);
    }

    public EndpointOrBuilder getEndpointsOrBuilder(int i) {
        return (EndpointOrBuilder) this.endpoints_.get(i);
    }

    public boolean hasControl() {
        return this.control_ != null;
    }

    public Control getControl() {
        Control control = this.control_;
        return control == null ? Control.getDefaultInstance() : control;
    }

    public ControlOrBuilder getControlOrBuilder() {
        return getControl();
    }

    public List<LogDescriptor> getLogsList() {
        return this.logs_;
    }

    public List<? extends LogDescriptorOrBuilder> getLogsOrBuilderList() {
        return this.logs_;
    }

    public int getLogsCount() {
        return this.logs_.size();
    }

    public LogDescriptor getLogs(int i) {
        return (LogDescriptor) this.logs_.get(i);
    }

    public LogDescriptorOrBuilder getLogsOrBuilder(int i) {
        return (LogDescriptorOrBuilder) this.logs_.get(i);
    }

    public List<MetricDescriptor> getMetricsList() {
        return this.metrics_;
    }

    public List<? extends MetricDescriptorOrBuilder> getMetricsOrBuilderList() {
        return this.metrics_;
    }

    public int getMetricsCount() {
        return this.metrics_.size();
    }

    public MetricDescriptor getMetrics(int i) {
        return (MetricDescriptor) this.metrics_.get(i);
    }

    public MetricDescriptorOrBuilder getMetricsOrBuilder(int i) {
        return (MetricDescriptorOrBuilder) this.metrics_.get(i);
    }

    public List<MonitoredResourceDescriptor> getMonitoredResourcesList() {
        return this.monitoredResources_;
    }

    public List<? extends MonitoredResourceDescriptorOrBuilder> getMonitoredResourcesOrBuilderList() {
        return this.monitoredResources_;
    }

    public int getMonitoredResourcesCount() {
        return this.monitoredResources_.size();
    }

    public MonitoredResourceDescriptor getMonitoredResources(int i) {
        return (MonitoredResourceDescriptor) this.monitoredResources_.get(i);
    }

    public MonitoredResourceDescriptorOrBuilder getMonitoredResourcesOrBuilder(int i) {
        return (MonitoredResourceDescriptorOrBuilder) this.monitoredResources_.get(i);
    }

    public boolean hasBilling() {
        return this.billing_ != null;
    }

    public Billing getBilling() {
        Billing billing = this.billing_;
        return billing == null ? Billing.getDefaultInstance() : billing;
    }

    public BillingOrBuilder getBillingOrBuilder() {
        return getBilling();
    }

    public boolean hasLogging() {
        return this.logging_ != null;
    }

    public Logging getLogging() {
        Logging logging = this.logging_;
        return logging == null ? Logging.getDefaultInstance() : logging;
    }

    public LoggingOrBuilder getLoggingOrBuilder() {
        return getLogging();
    }

    public boolean hasMonitoring() {
        return this.monitoring_ != null;
    }

    public Monitoring getMonitoring() {
        Monitoring monitoring = this.monitoring_;
        return monitoring == null ? Monitoring.getDefaultInstance() : monitoring;
    }

    public MonitoringOrBuilder getMonitoringOrBuilder() {
        return getMonitoring();
    }

    public boolean hasSystemParameters() {
        return this.systemParameters_ != null;
    }

    public SystemParameters getSystemParameters() {
        SystemParameters systemParameters = this.systemParameters_;
        return systemParameters == null ? SystemParameters.getDefaultInstance() : systemParameters;
    }

    public SystemParametersOrBuilder getSystemParametersOrBuilder() {
        return getSystemParameters();
    }

    public boolean hasSourceInfo() {
        return this.sourceInfo_ != null;
    }

    public SourceInfo getSourceInfo() {
        SourceInfo sourceInfo = this.sourceInfo_;
        return sourceInfo == null ? SourceInfo.getDefaultInstance() : sourceInfo;
    }

    public SourceInfoOrBuilder getSourceInfoOrBuilder() {
        return getSourceInfo();
    }

    public boolean hasExperimental() {
        return this.experimental_ != null;
    }

    public Experimental getExperimental() {
        Experimental experimental = this.experimental_;
        return experimental == null ? Experimental.getDefaultInstance() : experimental;
    }

    public ExperimentalOrBuilder getExperimentalOrBuilder() {
        return getExperimental();
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
        if (!getTitleBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.title_);
        }
        for (int i = 0; i < this.apis_.size(); i++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.apis_.get(i));
        }
        for (int i2 = 0; i2 < this.types_.size(); i2++) {
            codedOutputStream.writeMessage(4, (MessageLite) this.types_.get(i2));
        }
        for (int i3 = 0; i3 < this.enums_.size(); i3++) {
            codedOutputStream.writeMessage(5, (MessageLite) this.enums_.get(i3));
        }
        if (this.documentation_ != null) {
            codedOutputStream.writeMessage(6, getDocumentation());
        }
        if (this.backend_ != null) {
            codedOutputStream.writeMessage(8, getBackend());
        }
        if (this.http_ != null) {
            codedOutputStream.writeMessage(9, getHttp());
        }
        if (this.quota_ != null) {
            codedOutputStream.writeMessage(10, getQuota());
        }
        if (this.authentication_ != null) {
            codedOutputStream.writeMessage(11, getAuthentication());
        }
        if (this.context_ != null) {
            codedOutputStream.writeMessage(12, getContext());
        }
        if (this.usage_ != null) {
            codedOutputStream.writeMessage(15, getUsage());
        }
        for (int i4 = 0; i4 < this.endpoints_.size(); i4++) {
            codedOutputStream.writeMessage(18, (MessageLite) this.endpoints_.get(i4));
        }
        if (this.configVersion_ != null) {
            codedOutputStream.writeMessage(20, getConfigVersion());
        }
        if (this.control_ != null) {
            codedOutputStream.writeMessage(21, getControl());
        }
        if (!getProducerProjectIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 22, this.producerProjectId_);
        }
        for (int i5 = 0; i5 < this.logs_.size(); i5++) {
            codedOutputStream.writeMessage(23, (MessageLite) this.logs_.get(i5));
        }
        for (int i6 = 0; i6 < this.metrics_.size(); i6++) {
            codedOutputStream.writeMessage(24, (MessageLite) this.metrics_.get(i6));
        }
        for (int i7 = 0; i7 < this.monitoredResources_.size(); i7++) {
            codedOutputStream.writeMessage(25, (MessageLite) this.monitoredResources_.get(i7));
        }
        if (this.billing_ != null) {
            codedOutputStream.writeMessage(26, getBilling());
        }
        if (this.logging_ != null) {
            codedOutputStream.writeMessage(27, getLogging());
        }
        if (this.monitoring_ != null) {
            codedOutputStream.writeMessage(28, getMonitoring());
        }
        if (this.systemParameters_ != null) {
            codedOutputStream.writeMessage(29, getSystemParameters());
        }
        if (!getIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 33, this.id_);
        }
        if (this.sourceInfo_ != null) {
            codedOutputStream.writeMessage(37, getSourceInfo());
        }
        if (this.experimental_ != null) {
            codedOutputStream.writeMessage(101, getExperimental());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) + 0 : 0;
        if (!getTitleBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.title_);
        }
        int i2 = computeStringSize;
        for (int i3 = 0; i3 < this.apis_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(3, (MessageLite) this.apis_.get(i3));
        }
        for (int i4 = 0; i4 < this.types_.size(); i4++) {
            i2 += CodedOutputStream.computeMessageSize(4, (MessageLite) this.types_.get(i4));
        }
        for (int i5 = 0; i5 < this.enums_.size(); i5++) {
            i2 += CodedOutputStream.computeMessageSize(5, (MessageLite) this.enums_.get(i5));
        }
        if (this.documentation_ != null) {
            i2 += CodedOutputStream.computeMessageSize(6, getDocumentation());
        }
        if (this.backend_ != null) {
            i2 += CodedOutputStream.computeMessageSize(8, getBackend());
        }
        if (this.http_ != null) {
            i2 += CodedOutputStream.computeMessageSize(9, getHttp());
        }
        if (this.quota_ != null) {
            i2 += CodedOutputStream.computeMessageSize(10, getQuota());
        }
        if (this.authentication_ != null) {
            i2 += CodedOutputStream.computeMessageSize(11, getAuthentication());
        }
        if (this.context_ != null) {
            i2 += CodedOutputStream.computeMessageSize(12, getContext());
        }
        if (this.usage_ != null) {
            i2 += CodedOutputStream.computeMessageSize(15, getUsage());
        }
        for (int i6 = 0; i6 < this.endpoints_.size(); i6++) {
            i2 += CodedOutputStream.computeMessageSize(18, (MessageLite) this.endpoints_.get(i6));
        }
        if (this.configVersion_ != null) {
            i2 += CodedOutputStream.computeMessageSize(20, getConfigVersion());
        }
        if (this.control_ != null) {
            i2 += CodedOutputStream.computeMessageSize(21, getControl());
        }
        if (!getProducerProjectIdBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(22, this.producerProjectId_);
        }
        for (int i7 = 0; i7 < this.logs_.size(); i7++) {
            i2 += CodedOutputStream.computeMessageSize(23, (MessageLite) this.logs_.get(i7));
        }
        for (int i8 = 0; i8 < this.metrics_.size(); i8++) {
            i2 += CodedOutputStream.computeMessageSize(24, (MessageLite) this.metrics_.get(i8));
        }
        for (int i9 = 0; i9 < this.monitoredResources_.size(); i9++) {
            i2 += CodedOutputStream.computeMessageSize(25, (MessageLite) this.monitoredResources_.get(i9));
        }
        if (this.billing_ != null) {
            i2 += CodedOutputStream.computeMessageSize(26, getBilling());
        }
        if (this.logging_ != null) {
            i2 += CodedOutputStream.computeMessageSize(27, getLogging());
        }
        if (this.monitoring_ != null) {
            i2 += CodedOutputStream.computeMessageSize(28, getMonitoring());
        }
        if (this.systemParameters_ != null) {
            i2 += CodedOutputStream.computeMessageSize(29, getSystemParameters());
        }
        if (!getIdBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(33, this.id_);
        }
        if (this.sourceInfo_ != null) {
            i2 += CodedOutputStream.computeMessageSize(37, getSourceInfo());
        }
        if (this.experimental_ != null) {
            i2 += CodedOutputStream.computeMessageSize(101, getExperimental());
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
        if (!(obj instanceof Service)) {
            return super.equals(obj);
        }
        Service service = (Service) obj;
        boolean z2 = hasConfigVersion() == service.hasConfigVersion();
        if (hasConfigVersion()) {
            z2 = z2 && getConfigVersion().equals(service.getConfigVersion());
        }
        boolean z3 = (((((((z2 && getName().equals(service.getName())) && getId().equals(service.getId())) && getTitle().equals(service.getTitle())) && getProducerProjectId().equals(service.getProducerProjectId())) && getApisList().equals(service.getApisList())) && getTypesList().equals(service.getTypesList())) && getEnumsList().equals(service.getEnumsList())) && hasDocumentation() == service.hasDocumentation();
        if (hasDocumentation()) {
            z3 = z3 && getDocumentation().equals(service.getDocumentation());
        }
        boolean z4 = z3 && hasBackend() == service.hasBackend();
        if (hasBackend()) {
            z4 = z4 && getBackend().equals(service.getBackend());
        }
        boolean z5 = z4 && hasHttp() == service.hasHttp();
        if (hasHttp()) {
            z5 = z5 && getHttp().equals(service.getHttp());
        }
        boolean z6 = z5 && hasQuota() == service.hasQuota();
        if (hasQuota()) {
            z6 = z6 && getQuota().equals(service.getQuota());
        }
        boolean z7 = z6 && hasAuthentication() == service.hasAuthentication();
        if (hasAuthentication()) {
            z7 = z7 && getAuthentication().equals(service.getAuthentication());
        }
        boolean z8 = z7 && hasContext() == service.hasContext();
        if (hasContext()) {
            z8 = z8 && getContext().equals(service.getContext());
        }
        boolean z9 = z8 && hasUsage() == service.hasUsage();
        if (hasUsage()) {
            z9 = z9 && getUsage().equals(service.getUsage());
        }
        boolean z10 = (z9 && getEndpointsList().equals(service.getEndpointsList())) && hasControl() == service.hasControl();
        if (hasControl()) {
            z10 = z10 && getControl().equals(service.getControl());
        }
        boolean z11 = (((z10 && getLogsList().equals(service.getLogsList())) && getMetricsList().equals(service.getMetricsList())) && getMonitoredResourcesList().equals(service.getMonitoredResourcesList())) && hasBilling() == service.hasBilling();
        if (hasBilling()) {
            z11 = z11 && getBilling().equals(service.getBilling());
        }
        boolean z12 = z11 && hasLogging() == service.hasLogging();
        if (hasLogging()) {
            z12 = z12 && getLogging().equals(service.getLogging());
        }
        boolean z13 = z12 && hasMonitoring() == service.hasMonitoring();
        if (hasMonitoring()) {
            z13 = z13 && getMonitoring().equals(service.getMonitoring());
        }
        boolean z14 = z13 && hasSystemParameters() == service.hasSystemParameters();
        if (hasSystemParameters()) {
            z14 = z14 && getSystemParameters().equals(service.getSystemParameters());
        }
        boolean z15 = z14 && hasSourceInfo() == service.hasSourceInfo();
        if (hasSourceInfo()) {
            z15 = z15 && getSourceInfo().equals(service.getSourceInfo());
        }
        boolean z16 = z15 && hasExperimental() == service.hasExperimental();
        if (hasExperimental()) {
            z16 = z16 && getExperimental().equals(service.getExperimental());
        }
        if (!z16 || !this.unknownFields.equals(service.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasConfigVersion()) {
            hashCode = (((hashCode * 37) + 20) * 53) + getConfigVersion().hashCode();
        }
        int hashCode2 = (((((((((((((((hashCode * 37) + 1) * 53) + getName().hashCode()) * 37) + 33) * 53) + getId().hashCode()) * 37) + 2) * 53) + getTitle().hashCode()) * 37) + 22) * 53) + getProducerProjectId().hashCode();
        if (getApisCount() > 0) {
            hashCode2 = (((hashCode2 * 37) + 3) * 53) + getApisList().hashCode();
        }
        if (getTypesCount() > 0) {
            hashCode2 = (((hashCode2 * 37) + 4) * 53) + getTypesList().hashCode();
        }
        if (getEnumsCount() > 0) {
            hashCode2 = (((hashCode2 * 37) + 5) * 53) + getEnumsList().hashCode();
        }
        if (hasDocumentation()) {
            hashCode2 = (((hashCode2 * 37) + 6) * 53) + getDocumentation().hashCode();
        }
        if (hasBackend()) {
            hashCode2 = (((hashCode2 * 37) + 8) * 53) + getBackend().hashCode();
        }
        if (hasHttp()) {
            hashCode2 = (((hashCode2 * 37) + 9) * 53) + getHttp().hashCode();
        }
        if (hasQuota()) {
            hashCode2 = (((hashCode2 * 37) + 10) * 53) + getQuota().hashCode();
        }
        if (hasAuthentication()) {
            hashCode2 = (((hashCode2 * 37) + 11) * 53) + getAuthentication().hashCode();
        }
        if (hasContext()) {
            hashCode2 = (((hashCode2 * 37) + 12) * 53) + getContext().hashCode();
        }
        if (hasUsage()) {
            hashCode2 = (((hashCode2 * 37) + 15) * 53) + getUsage().hashCode();
        }
        if (getEndpointsCount() > 0) {
            hashCode2 = (((hashCode2 * 37) + 18) * 53) + getEndpointsList().hashCode();
        }
        if (hasControl()) {
            hashCode2 = (((hashCode2 * 37) + 21) * 53) + getControl().hashCode();
        }
        if (getLogsCount() > 0) {
            hashCode2 = (((hashCode2 * 37) + 23) * 53) + getLogsList().hashCode();
        }
        if (getMetricsCount() > 0) {
            hashCode2 = (((hashCode2 * 37) + 24) * 53) + getMetricsList().hashCode();
        }
        if (getMonitoredResourcesCount() > 0) {
            hashCode2 = (((hashCode2 * 37) + 25) * 53) + getMonitoredResourcesList().hashCode();
        }
        if (hasBilling()) {
            hashCode2 = (((hashCode2 * 37) + 26) * 53) + getBilling().hashCode();
        }
        if (hasLogging()) {
            hashCode2 = (((hashCode2 * 37) + 27) * 53) + getLogging().hashCode();
        }
        if (hasMonitoring()) {
            hashCode2 = (((hashCode2 * 37) + 28) * 53) + getMonitoring().hashCode();
        }
        if (hasSystemParameters()) {
            hashCode2 = (((hashCode2 * 37) + 29) * 53) + getSystemParameters().hashCode();
        }
        if (hasSourceInfo()) {
            hashCode2 = (((hashCode2 * 37) + 37) * 53) + getSourceInfo().hashCode();
        }
        if (hasExperimental()) {
            hashCode2 = (((hashCode2 * 37) + 101) * 53) + getExperimental().hashCode();
        }
        int hashCode3 = (hashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode3;
        return hashCode3;
    }

    public static Service parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Service) PARSER.parseFrom(byteBuffer);
    }

    public static Service parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Service) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Service parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Service) PARSER.parseFrom(byteString);
    }

    public static Service parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Service) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Service parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Service) PARSER.parseFrom(bArr);
    }

    public static Service parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Service) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Service parseFrom(InputStream inputStream) throws IOException {
        return (Service) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Service parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Service) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Service parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Service) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Service parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Service) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Service parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Service) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Service parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Service) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Service service) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(service);
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

    public static Service getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Service> parser() {
        return PARSER;
    }

    public Parser<Service> getParserForType() {
        return PARSER;
    }

    public Service getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
