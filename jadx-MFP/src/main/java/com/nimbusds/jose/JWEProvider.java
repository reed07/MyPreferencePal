package com.nimbusds.jose;

import com.nimbusds.jose.jca.JCAAware;
import com.nimbusds.jose.jca.JWEJCAContext;

public interface JWEProvider extends JOSEProvider, JCAAware<JWEJCAContext> {
}
