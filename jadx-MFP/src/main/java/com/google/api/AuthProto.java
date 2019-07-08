package com.google.api;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;

public final class AuthProto {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    static final Descriptor internal_static_google_api_AuthProvider_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(2));
    static final FieldAccessorTable internal_static_google_api_AuthProvider_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_AuthProvider_descriptor, new String[]{"Id", "Issuer", "JwksUri", "Audiences", "AuthorizationUrl"});
    static final Descriptor internal_static_google_api_AuthRequirement_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(4));
    static final FieldAccessorTable internal_static_google_api_AuthRequirement_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_AuthRequirement_descriptor, new String[]{"ProviderId", "Audiences"});
    static final Descriptor internal_static_google_api_AuthenticationRule_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    static final FieldAccessorTable internal_static_google_api_AuthenticationRule_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_AuthenticationRule_descriptor, new String[]{"Selector", "Oauth", "AllowWithoutCredential", "Requirements"});
    static final Descriptor internal_static_google_api_Authentication_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    static final FieldAccessorTable internal_static_google_api_Authentication_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_Authentication_descriptor, new String[]{"Rules", "Providers"});
    static final Descriptor internal_static_google_api_OAuthRequirements_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(3));
    static final FieldAccessorTable internal_static_google_api_OAuthRequirements_fieldAccessorTable = new FieldAccessorTable(internal_static_google_api_OAuthRequirements_descriptor, new String[]{"CanonicalScopes"});

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private AuthProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor[] fileDescriptorArr = {AnnotationsProto.getDescriptor()};
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0015google/api/auth.proto\u0012\ngoogle.api\u001a\u001cgoogle/api/annotations.proto\"l\n\u000eAuthentication\u0012-\n\u0005rules\u0018\u0003 \u0003(\u000b2\u001e.google.api.AuthenticationRule\u0012+\n\tproviders\u0018\u0004 \u0003(\u000b2\u0018.google.api.AuthProvider\"©\u0001\n\u0012AuthenticationRule\u0012\u0010\n\bselector\u0018\u0001 \u0001(\t\u0012,\n\u0005oauth\u0018\u0002 \u0001(\u000b2\u001d.google.api.OAuthRequirements\u0012 \n\u0018allow_without_credential\u0018\u0005 \u0001(\b\u00121\n\frequirements\u0018\u0007 \u0003(\u000b2\u001b.google.api.AuthRequirement\"j\n\fAuthProvider\u0012\n\n\u0002id\u0018\u0001 \u0001(\t\u0012\u000e\n\u0006issuer\u0018\u0002 \u0001(\t\u0012\u0010\n\bjwks_uri\u0018\u0003 \u0001(\t\u0012\u0011\n\taudiences\u0018\u0004 \u0001(\t\u0012\u0019\n\u0011authorization_url\u0018\u0005 \u0001(\t\"-\n\u0011OAuthRequirements\u0012\u0018\n\u0010canonical_scopes\u0018\u0001 \u0001(\t\"9\n\u000fAuthRequirement\u0012\u0013\n\u000bprovider_id\u0018\u0001 \u0001(\t\u0012\u0011\n\taudiences\u0018\u0002 \u0001(\tBk\n\u000ecom.google.apiB\tAuthProtoP\u0001ZEgoogle.golang.org/genproto/googleapis/api/serviceconfig;serviceconfig¢\u0002\u0004GAPIb\u0006proto3"}, fileDescriptorArr, new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                AuthProto.descriptor = fileDescriptor;
                return null;
            }
        });
        AnnotationsProto.getDescriptor();
    }
}
