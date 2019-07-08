package com.shinobicontrols.charts;

class PropertyChangedEvent extends ag<Handler> {
    static final b a = new b();

    public interface Handler extends a {
        void onPropertyChanged();
    }

    PropertyChangedEvent() {
    }

    /* access modifiers changed from: 0000 */
    public b a() {
        return a;
    }

    /* access modifiers changed from: 0000 */
    public void a(Handler handler) {
        handler.onPropertyChanged();
    }
}
