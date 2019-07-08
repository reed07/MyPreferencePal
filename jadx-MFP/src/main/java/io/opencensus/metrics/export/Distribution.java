package io.opencensus.metrics.export;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class Distribution {

    @Immutable
    public static abstract class Bucket {
        Bucket() {
        }
    }

    @Immutable
    public static abstract class BucketOptions {

        @Immutable
        public static abstract class ExplicitOptions extends BucketOptions {
            ExplicitOptions() {
                super();
            }
        }

        private BucketOptions() {
        }
    }

    @Immutable
    public static abstract class Exemplar {
        Exemplar() {
        }
    }

    Distribution() {
    }
}
