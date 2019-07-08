package com.nimbusds.jose.proc;

import com.nimbusds.jose.proc.SecurityContext;

public interface ConfigurableJOSEProcessor<C extends SecurityContext> extends JOSEProcessor<C>, JOSEProcessorConfiguration<C> {
}
