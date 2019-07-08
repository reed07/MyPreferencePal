package com.brightcove.player.controller;

import com.brightcove.player.event.EventEmitter;

public class ExoPlayerSourceSelectionController extends DefaultSourceSelectionController {
    public ExoPlayerSourceSelectionController(EventEmitter eventEmitter) {
        super(eventEmitter);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.brightcove.player.model.Source selectSource(com.brightcove.player.model.Video r3) throws com.brightcove.player.controller.NoSourceFoundException {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0037
            java.util.Map r0 = r3.getSourceCollections()
            if (r0 == 0) goto L_0x0031
            int r1 = r0.size()
            if (r1 == 0) goto L_0x0031
            com.brightcove.player.model.DeliveryType r1 = com.brightcove.player.model.DeliveryType.DASH
            boolean r1 = r0.containsKey(r1)
            if (r1 == 0) goto L_0x0029
            com.brightcove.player.model.DeliveryType r1 = com.brightcove.player.model.DeliveryType.DASH
            java.lang.Object r0 = r0.get(r1)
            com.brightcove.player.model.SourceCollection r0 = (com.brightcove.player.model.SourceCollection) r0
            if (r0 == 0) goto L_0x0029
            java.util.Set r0 = r0.getSources()
            com.brightcove.player.model.Source r0 = r2.selectSource(r0)
            goto L_0x002a
        L_0x0029:
            r0 = 0
        L_0x002a:
            if (r0 != 0) goto L_0x0030
            com.brightcove.player.model.Source r0 = super.selectSource(r3)
        L_0x0030:
            return r0
        L_0x0031:
            com.brightcove.player.controller.NoSourceFoundException r3 = new com.brightcove.player.controller.NoSourceFoundException
            r3.<init>()
            throw r3
        L_0x0037:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "videoRequired"
            java.lang.String r0 = com.brightcove.player.util.ErrorUtil.getMessage(r0)
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.controller.ExoPlayerSourceSelectionController.selectSource(com.brightcove.player.model.Video):com.brightcove.player.model.Source");
    }
}
