package com.facebook.stetho.dumpapp.plugins;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.brightcove.player.event.AbstractEvent;
import com.facebook.stetho.dumpapp.DumpUsageException;
import com.facebook.stetho.dumpapp.DumperContext;
import com.facebook.stetho.dumpapp.DumperPlugin;
import java.io.File;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SharedPreferencesDumperPlugin implements DumperPlugin {
    private static final String NAME = "prefs";
    private static final String XML_SUFFIX = ".xml";
    private final Context mAppContext;

    private enum Type {
        BOOLEAN(AbstractEvent.BOOLEAN),
        INT("int"),
        LONG("long"),
        FLOAT("float"),
        STRING("string"),
        SET("set");
        
        private final String name;

        private Type(String str) {
            this.name = str;
        }

        @Nullable
        public static Type of(String str) {
            Type[] values;
            for (Type type : values()) {
                if (type.name.equals(str)) {
                    return type;
                }
            }
            return null;
        }

        public static StringBuilder appendNamesList(StringBuilder sb, String str) {
            Type[] values;
            boolean z = true;
            for (Type type : values()) {
                if (z) {
                    z = false;
                } else {
                    sb.append(str);
                }
                sb.append(type.name);
            }
            return sb;
        }
    }

    public String getName() {
        return NAME;
    }

    public SharedPreferencesDumperPlugin(Context context) {
        this.mAppContext = context.getApplicationContext();
    }

    public void dump(DumperContext dumperContext) throws DumpUsageException {
        PrintStream stdout = dumperContext.getStdout();
        List argsAsList = dumperContext.getArgsAsList();
        String str = argsAsList.isEmpty() ? "" : (String) argsAsList.remove(0);
        if (str.equals("print")) {
            doPrint(stdout, argsAsList);
        } else if (str.equals("write")) {
            doWrite(argsAsList);
        } else {
            doUsage(stdout);
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    private void doWrite(List<String> list) throws DumpUsageException {
        Iterator it = list.iterator();
        String nextArg = nextArg(it, "Expected <path>");
        String nextArg2 = nextArg(it, "Expected <key>");
        Type of = Type.of(nextArg(it, "Expected <type>"));
        if (of != null) {
            Editor edit = getSharedPreferences(nextArg).edit();
            switch (of) {
                case BOOLEAN:
                    edit.putBoolean(nextArg2, Boolean.valueOf(nextArgValue(it)).booleanValue());
                    break;
                case INT:
                    edit.putInt(nextArg2, Integer.valueOf(nextArgValue(it)).intValue());
                    break;
                case LONG:
                    edit.putLong(nextArg2, Long.valueOf(nextArgValue(it)).longValue());
                    break;
                case FLOAT:
                    edit.putFloat(nextArg2, Float.valueOf(nextArgValue(it)).floatValue());
                    break;
                case STRING:
                    edit.putString(nextArg2, nextArgValue(it));
                    break;
                case SET:
                    putStringSet(edit, nextArg2, it);
                    break;
            }
            edit.commit();
            return;
        }
        throw new DumpUsageException(Type.appendNamesList(new StringBuilder("Usage: prefs write <path> <key> <type> <value>, where type is one of: "), ", ").toString());
    }

    @Nonnull
    private static String nextArg(Iterator<String> it, String str) throws DumpUsageException {
        if (it.hasNext()) {
            return (String) it.next();
        }
        throw new DumpUsageException(str);
    }

    @Nonnull
    private static String nextArgValue(Iterator<String> it) throws DumpUsageException {
        return nextArg(it, "Expected <value>");
    }

    @TargetApi(11)
    private static void putStringSet(Editor editor, String str, Iterator<String> it) {
        HashSet hashSet = new HashSet();
        while (it.hasNext()) {
            hashSet.add(it.next());
        }
        editor.putStringSet(str, hashSet);
    }

    private void doPrint(PrintStream printStream, List<String> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mAppContext.getApplicationInfo().dataDir);
        sb.append("/shared_prefs");
        printRecursive(printStream, sb.toString(), "", list.isEmpty() ? "" : (String) list.get(0), list.size() > 1 ? (String) list.get(1) : "");
    }

    private void printRecursive(PrintStream printStream, String str, String str2, String str3, String str4) {
        String str5;
        File file = new File(str, str2);
        if (file.isFile()) {
            if (str2.endsWith(XML_SUFFIX)) {
                printFile(printStream, str2.substring(0, str2.length() - 4), str4);
            }
        } else if (file.isDirectory()) {
            String[] list = file.list();
            if (list != null) {
                for (int i = 0; i < list.length; i++) {
                    if (TextUtils.isEmpty(str2)) {
                        str5 = list[i];
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str2);
                        sb.append(File.separator);
                        sb.append(list[i]);
                        str5 = sb.toString();
                    }
                    String str6 = str5;
                    if (str6.startsWith(str3)) {
                        printRecursive(printStream, str, str6, str3, str4);
                    }
                }
            }
        }
    }

    private void printFile(PrintStream printStream, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(":");
        printStream.println(sb.toString());
        for (Entry entry : getSharedPreferences(str).getAll().entrySet()) {
            if (((String) entry.getKey()).startsWith(str2)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("  ");
                sb2.append((String) entry.getKey());
                sb2.append(" = ");
                sb2.append(entry.getValue());
                printStream.println(sb2.toString());
            }
        }
    }

    private void doUsage(PrintStream printStream) {
        String str = "Usage: dumpapp prefs ";
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("<command> [command-options]");
        printStream.println(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append("print [pathPrefix [keyPrefix]]");
        printStream.println(sb2.toString());
        StringBuilder sb3 = new StringBuilder("       dumpapp prefs ");
        sb3.append("write <path> <key> <");
        StringBuilder appendNamesList = Type.appendNamesList(sb3, "|");
        appendNamesList.append("> <value>");
        printStream.println(appendNamesList);
        printStream.println();
        printStream.println("dumpapp prefs print: Print all matching values from the shared preferences");
        printStream.println();
        printStream.println("dumpapp prefs write: Writes a value to the shared preferences");
    }

    private SharedPreferences getSharedPreferences(String str) {
        return this.mAppContext.getSharedPreferences(str, 4);
    }
}
