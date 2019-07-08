package io.opentracing.propagation;

import java.nio.ByteBuffer;

public interface Format<C> {

    public static final class Builtin<C> implements Format<C> {
        public static final Format<ByteBuffer> BINARY = new Builtin("BINARY");
        public static final Format<TextMap> HTTP_HEADERS = new Builtin("HTTP_HEADERS");
        public static final Format<TextMap> TEXT_MAP = new Builtin("TEXT_MAP");
        private final String name;

        private Builtin(String str) {
            this.name = str;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(Builtin.class.getSimpleName());
            sb.append(".");
            sb.append(this.name);
            return sb.toString();
        }
    }
}
