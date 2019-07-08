package io.requery.sql;

import io.requery.query.BaseScalar;
import io.requery.query.Scalar;
import io.requery.query.element.QueryElement;
import io.requery.query.element.QueryOperation;

class SelectCountOperation implements QueryOperation<Scalar<Integer>> {
    /* access modifiers changed from: private */
    public final RuntimeConfiguration configuration;
    /* access modifiers changed from: private */
    public final TupleResultReader reader;

    SelectCountOperation(RuntimeConfiguration runtimeConfiguration) {
        this.configuration = runtimeConfiguration;
        this.reader = new TupleResultReader(runtimeConfiguration);
    }

    public Scalar<Integer> evaluate(final QueryElement<Scalar<Integer>> queryElement) {
        return new BaseScalar<Integer>(this.configuration.getWriteExecutor()) {
            /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
                r0.close();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
                r1.addSuppressed(r0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:14:0x0034, code lost:
                r0.close();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:5:0x0025, code lost:
                r2 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:9:0x0029, code lost:
                if (r1 != null) goto L_0x002b;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Integer evaluate() {
                /*
                    r4 = this;
                    io.requery.sql.SelectResult r0 = new io.requery.sql.SelectResult
                    io.requery.sql.SelectCountOperation r1 = io.requery.sql.SelectCountOperation.this
                    io.requery.sql.RuntimeConfiguration r1 = r1.configuration
                    io.requery.query.element.QueryElement r2 = r3
                    io.requery.sql.SelectCountOperation r3 = io.requery.sql.SelectCountOperation.this
                    io.requery.sql.TupleResultReader r3 = r3.reader
                    r0.<init>(r1, r2, r3)
                    r1 = 0
                    java.lang.Object r2 = r0.first()     // Catch:{ Throwable -> 0x0027 }
                    io.requery.query.Tuple r2 = (io.requery.query.Tuple) r2     // Catch:{ Throwable -> 0x0027 }
                    r3 = 0
                    java.lang.Object r2 = r2.get(r3)     // Catch:{ Throwable -> 0x0027 }
                    java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ Throwable -> 0x0027 }
                    r0.close()
                    return r2
                L_0x0025:
                    r2 = move-exception
                    goto L_0x0029
                L_0x0027:
                    r1 = move-exception
                    throw r1     // Catch:{ all -> 0x0025 }
                L_0x0029:
                    if (r1 == 0) goto L_0x0034
                    r0.close()     // Catch:{ Throwable -> 0x002f }
                    goto L_0x0037
                L_0x002f:
                    r0 = move-exception
                    r1.addSuppressed(r0)
                    goto L_0x0037
                L_0x0034:
                    r0.close()
                L_0x0037:
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: io.requery.sql.SelectCountOperation.AnonymousClass1.evaluate():java.lang.Integer");
            }
        };
    }
}
