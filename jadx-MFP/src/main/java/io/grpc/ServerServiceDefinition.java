package io.grpc;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ServerServiceDefinition {
    private final Map<String, ServerMethodDefinition<?, ?>> methods;
    private final ServiceDescriptor serviceDescriptor;

    public static final class Builder {
        private final Map<String, ServerMethodDefinition<?, ?>> methods;
        private final ServiceDescriptor serviceDescriptor;
        private final String serviceName;

        private Builder(ServiceDescriptor serviceDescriptor2) {
            this.methods = new HashMap();
            this.serviceDescriptor = (ServiceDescriptor) Preconditions.checkNotNull(serviceDescriptor2, "serviceDescriptor");
            this.serviceName = serviceDescriptor2.getName();
        }

        public <ReqT, RespT> Builder addMethod(MethodDescriptor<ReqT, RespT> methodDescriptor, ServerCallHandler<ReqT, RespT> serverCallHandler) {
            return addMethod(ServerMethodDefinition.create((MethodDescriptor) Preconditions.checkNotNull(methodDescriptor, "method must not be null"), (ServerCallHandler) Preconditions.checkNotNull(serverCallHandler, "handler must not be null")));
        }

        public <ReqT, RespT> Builder addMethod(ServerMethodDefinition<ReqT, RespT> serverMethodDefinition) {
            MethodDescriptor methodDescriptor = serverMethodDefinition.getMethodDescriptor();
            Preconditions.checkArgument(this.serviceName.equals(MethodDescriptor.extractFullServiceName(methodDescriptor.getFullMethodName())), "Method name should be prefixed with service name and separated with '/'. Expected service name: '%s'. Actual fully qualifed method name: '%s'.", (Object) this.serviceName, (Object) methodDescriptor.getFullMethodName());
            String fullMethodName = methodDescriptor.getFullMethodName();
            Preconditions.checkState(!this.methods.containsKey(fullMethodName), "Method by same name already registered: %s", (Object) fullMethodName);
            this.methods.put(fullMethodName, serverMethodDefinition);
            return this;
        }

        public ServerServiceDefinition build() {
            ServiceDescriptor serviceDescriptor2 = this.serviceDescriptor;
            if (serviceDescriptor2 == null) {
                ArrayList arrayList = new ArrayList(this.methods.size());
                for (ServerMethodDefinition methodDescriptor : this.methods.values()) {
                    arrayList.add(methodDescriptor.getMethodDescriptor());
                }
                serviceDescriptor2 = new ServiceDescriptor(this.serviceName, (Collection<MethodDescriptor<?, ?>>) arrayList);
            }
            HashMap hashMap = new HashMap(this.methods);
            for (MethodDescriptor methodDescriptor2 : serviceDescriptor2.getMethods()) {
                ServerMethodDefinition serverMethodDefinition = (ServerMethodDefinition) hashMap.remove(methodDescriptor2.getFullMethodName());
                if (serverMethodDefinition == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("No method bound for descriptor entry ");
                    sb.append(methodDescriptor2.getFullMethodName());
                    throw new IllegalStateException(sb.toString());
                } else if (serverMethodDefinition.getMethodDescriptor() != methodDescriptor2) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Bound method for ");
                    sb2.append(methodDescriptor2.getFullMethodName());
                    sb2.append(" not same instance as method in service descriptor");
                    throw new IllegalStateException(sb2.toString());
                }
            }
            if (hashMap.size() <= 0) {
                return new ServerServiceDefinition(serviceDescriptor2, this.methods);
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("No entry in descriptor matching bound method ");
            sb3.append(((ServerMethodDefinition) hashMap.values().iterator().next()).getMethodDescriptor().getFullMethodName());
            throw new IllegalStateException(sb3.toString());
        }
    }

    public static Builder builder(ServiceDescriptor serviceDescriptor2) {
        return new Builder(serviceDescriptor2);
    }

    private ServerServiceDefinition(ServiceDescriptor serviceDescriptor2, Map<String, ServerMethodDefinition<?, ?>> map) {
        this.serviceDescriptor = (ServiceDescriptor) Preconditions.checkNotNull(serviceDescriptor2, "serviceDescriptor");
        this.methods = Collections.unmodifiableMap(new HashMap(map));
    }
}
