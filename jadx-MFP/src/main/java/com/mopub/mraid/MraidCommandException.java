package com.mopub.mraid;

class MraidCommandException extends Exception {
    MraidCommandException() {
    }

    MraidCommandException(String str) {
        super(str);
    }

    MraidCommandException(Throwable th) {
        super(th);
    }
}
