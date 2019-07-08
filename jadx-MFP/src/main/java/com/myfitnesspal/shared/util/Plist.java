package com.myfitnesspal.shared.util;

import android.content.Context;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Basic;
import com.myfitnesspal.shared.model.v1.Timezone;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Plist {
    public ArrayList<Timezone> parseTimezonePlist(Context context) {
        try {
            ArrayList<Timezone> arrayList = new ArrayList<>();
            Iterator it = new PlistParser(context.getAssets().open("config/timezone_map.plist")).getParsedList().iterator();
            while (it.hasNext()) {
                HashMap hashMap = (HashMap) it.next();
                Timezone timezone = new Timezone();
                timezone.setTimezone_name((String) hashMap.get("timezone_name"));
                timezone.setTimezone_identifier((String) hashMap.get(Basic.TIMEZONE_IDENTIFIER));
                arrayList.add(timezone);
            }
            return arrayList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public LinkedHashMap<String, String> parseRegistrationSettings(URL url) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        try {
            HashMap parsedDict = new PlistParser((Reader) new BufferedReader(new InputStreamReader(url.openStream()))).getParsedDict();
            for (String str : parsedDict.keySet()) {
                linkedHashMap.put(str, String.valueOf(parsedDict.get(str)));
            }
            return linkedHashMap;
        } catch (Exception e) {
            e.printStackTrace();
            return linkedHashMap;
        }
    }
}
