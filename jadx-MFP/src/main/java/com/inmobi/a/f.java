package com.inmobi.a;

import com.inmobi.commons.core.network.c;
import com.inmobi.commons.core.utilities.uid.d;
import com.myfitnesspal.shared.constants.HttpConstants;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/* compiled from: CarbPostListNetworkRequest */
public final class f extends c {
    int a;
    int b;
    private c c;
    private List<d> d;

    f(String str, int i, int i2, d dVar, List<d> list, c cVar) {
        super(HttpConstants.METHOD_POST, str, true, dVar);
        this.a = i;
        this.b = i2;
        this.d = list;
        this.c = cVar;
        this.o.put("req_id", this.c.c);
        this.o.put("i_till", Integer.toString(this.c.d));
        Map map = this.o;
        String str2 = "p_a_apps";
        JSONArray jSONArray = new JSONArray();
        for (int i3 = 0; i3 < this.d.size(); i3++) {
            jSONArray.put(((d) this.d.get(i3)).b);
        }
        map.put(str2, jSONArray.toString());
    }
}
