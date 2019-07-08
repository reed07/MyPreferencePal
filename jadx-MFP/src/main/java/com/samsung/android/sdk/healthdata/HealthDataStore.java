package com.samsung.android.sdk.healthdata;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.samsung.android.sdk.healthdata.HealthResultHolder.BaseResult;
import com.samsung.android.sdk.healthdata.HealthResultHolder.ResultListener;
import com.samsung.android.sdk.internal.healthdata.HealthResultReceiver.ForwardAsync;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import java.io.File;
import java.lang.ref.WeakReference;

public class HealthDataStore {
    private static String b = "com.sec.android.app.shealth";
    /* access modifiers changed from: private */
    public static String g = null;
    /* access modifiers changed from: private */
    public static long h = 0;
    private static long j = 60000;
    HealthResultHolder<BaseResult> a = null;
    /* access modifiers changed from: private */
    public final ConnectionListener c;
    /* access modifiers changed from: private */
    public IHealth d;
    /* access modifiers changed from: private */
    public final Context e;
    /* access modifiers changed from: private */
    public final b f = new b(this);
    private Boolean i = null;
    /* access modifiers changed from: private */
    public long k = j;
    private final ServiceConnection l = new ServiceConnection() {
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            com.samsung.android.sdk.healthdata.HealthDataStore.a(r6.a, com.samsung.android.sdk.healthdata.HealthDataStore.f(r6.a));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x010b, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0113, code lost:
            if (com.samsung.android.sdk.healthdata.HealthDataStore.a(r6.a) != null) goto L_0x0115;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0115, code lost:
            com.samsung.android.sdk.healthdata.HealthDataStore.a(r6.a).onConnectionFailed(new com.samsung.android.sdk.healthdata.HealthConnectionErrorResult(0, false));
            r6.a.a = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0127, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0128, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onServiceConnected(android.content.ComponentName r7, android.os.IBinder r8) {
            /*
                r6 = this;
                java.lang.String r7 = "HealthDataStore"
                java.lang.String r0 = "Service for HealthDataStore is connected"
                android.util.Log.d(r7, r0)
                com.samsung.android.sdk.healthdata.HealthDataStore r7 = com.samsung.android.sdk.healthdata.HealthDataStore.this
                com.samsung.android.sdk.healthdata.IHealth r8 = com.samsung.android.sdk.healthdata.IHealth.Stub.asInterface(r8)
                r7.d = r8
                android.os.Bundle r7 = new android.os.Bundle
                r7.<init>()
                java.lang.String r8 = "packageName"
                com.samsung.android.sdk.healthdata.HealthDataStore r0 = com.samsung.android.sdk.healthdata.HealthDataStore.this
                android.content.Context r0 = r0.e
                java.lang.String r0 = r0.getPackageName()
                r7.putString(r8, r0)
                java.lang.String r8 = "clientVersion"
                r0 = 1003001(0xf4df9, float:1.405504E-39)
                r7.putInt(r8, r0)
                com.samsung.android.sdk.healthdata.HealthDataStore r8 = com.samsung.android.sdk.healthdata.HealthDataStore.this
                android.content.Context r8 = r8.e
                boolean r8 = r8 instanceof android.app.Activity
                r1 = 0
                if (r8 == 0) goto L_0x003d
                java.lang.String r8 = "userPasswordInputMode"
                r7.putInt(r8, r1)
                goto L_0x0043
            L_0x003d:
                java.lang.String r8 = "userPasswordInputMode"
                r2 = 1
                r7.putInt(r8, r2)
            L_0x0043:
                r8 = 0
                com.samsung.android.sdk.healthdata.HealthDataStore r2 = com.samsung.android.sdk.healthdata.HealthDataStore.this     // Catch:{ RemoteException -> 0x006f }
                int r2 = r2.b()     // Catch:{ RemoteException -> 0x006f }
                r3 = 4600000(0x4630c0, float:6.445973E-39)
                if (r2 < r3) goto L_0x005a
                com.samsung.android.sdk.healthdata.HealthDataStore r0 = com.samsung.android.sdk.healthdata.HealthDataStore.this     // Catch:{ RemoteException -> 0x006f }
                com.samsung.android.sdk.healthdata.IHealth r0 = r0.d     // Catch:{ RemoteException -> 0x006f }
                android.os.Bundle r7 = r0.getConnectionResult2(r7)     // Catch:{ RemoteException -> 0x006f }
                goto L_0x008b
            L_0x005a:
                com.samsung.android.sdk.healthdata.HealthDataStore r7 = com.samsung.android.sdk.healthdata.HealthDataStore.this     // Catch:{ RemoteException -> 0x006f }
                com.samsung.android.sdk.healthdata.IHealth r7 = r7.d     // Catch:{ RemoteException -> 0x006f }
                com.samsung.android.sdk.healthdata.HealthDataStore r2 = com.samsung.android.sdk.healthdata.HealthDataStore.this     // Catch:{ RemoteException -> 0x006f }
                android.content.Context r2 = r2.e     // Catch:{ RemoteException -> 0x006f }
                java.lang.String r2 = r2.getPackageName()     // Catch:{ RemoteException -> 0x006f }
                android.os.Bundle r7 = r7.getConnectionResult(r2, r0)     // Catch:{ RemoteException -> 0x006f }
                goto L_0x008b
            L_0x006f:
                com.samsung.android.sdk.healthdata.HealthDataStore r7 = com.samsung.android.sdk.healthdata.HealthDataStore.this
                com.samsung.android.sdk.healthdata.HealthDataStore$ConnectionListener r7 = r7.c
                if (r7 == 0) goto L_0x008a
                com.samsung.android.sdk.healthdata.HealthDataStore r7 = com.samsung.android.sdk.healthdata.HealthDataStore.this
                com.samsung.android.sdk.healthdata.HealthDataStore$ConnectionListener r7 = r7.c
                com.samsung.android.sdk.healthdata.HealthConnectionErrorResult r0 = new com.samsung.android.sdk.healthdata.HealthConnectionErrorResult
                r0.<init>(r1, r1)
                r7.onConnectionFailed(r0)
                com.samsung.android.sdk.healthdata.HealthDataStore r7 = com.samsung.android.sdk.healthdata.HealthDataStore.this
                r7.a = r8
            L_0x008a:
                r7 = r8
            L_0x008b:
                if (r7 == 0) goto L_0x00a8
                java.lang.String r0 = "result"
                int r0 = r7.getInt(r0, r1)
                java.lang.String r2 = "socketKey"
                java.lang.String r2 = r7.getString(r2)
                com.samsung.android.sdk.healthdata.HealthDataStore.g = r2
                java.lang.String r2 = "myUserId"
                r3 = 0
                long r2 = r7.getLong(r2, r3)
                com.samsung.android.sdk.healthdata.HealthDataStore.h = r2
                goto L_0x00a9
            L_0x00a8:
                r0 = 0
            L_0x00a9:
                r2 = 2
                switch(r0) {
                    case -3: goto L_0x00e6;
                    case -2: goto L_0x0100;
                    case -1: goto L_0x00d0;
                    default: goto L_0x00ae;
                }
            L_0x00ae:
                java.lang.String r7 = "HealthDataStore"
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                java.lang.String r1 = "HealthConnectionErrorResult code : "
                r8.<init>(r1)
                r8.append(r0)
                java.lang.String r8 = r8.toString()
                android.util.Log.d(r7, r8)
                com.samsung.android.sdk.healthdata.HealthDataStore r7 = com.samsung.android.sdk.healthdata.HealthDataStore.this
                r7.disconnectService()
                com.samsung.android.sdk.healthdata.HealthDataStore r7 = com.samsung.android.sdk.healthdata.HealthDataStore.this
                com.samsung.android.sdk.healthdata.HealthDataStore$b r7 = r7.f
                r7.sendEmptyMessageDelayed(r0, r2)
                goto L_0x0128
            L_0x00d0:
                com.samsung.android.sdk.healthdata.HealthDataStore r7 = com.samsung.android.sdk.healthdata.HealthDataStore.this
                com.samsung.android.sdk.healthdata.HealthDataStore$ConnectionListener r7 = r7.c
                if (r7 == 0) goto L_0x0128
                com.samsung.android.sdk.healthdata.HealthDataStore r7 = com.samsung.android.sdk.healthdata.HealthDataStore.this
                com.samsung.android.sdk.healthdata.HealthDataStore$ConnectionListener r7 = r7.c
                r7.onConnected()
                com.samsung.android.sdk.healthdata.HealthDataStore r7 = com.samsung.android.sdk.healthdata.HealthDataStore.this
                r7.a = r8
                return
            L_0x00e6:
                java.lang.String r4 = "HealthDataStore"
                java.lang.String r5 = "User password popup is required"
                android.util.Log.d(r4, r5)
                android.os.Message r4 = new android.os.Message
                r4.<init>()
                r4.what = r0
                r4.setData(r7)
                com.samsung.android.sdk.healthdata.HealthDataStore r7 = com.samsung.android.sdk.healthdata.HealthDataStore.this
                com.samsung.android.sdk.healthdata.HealthDataStore$b r7 = r7.f
                r7.sendMessageDelayed(r4, r2)
            L_0x0100:
                com.samsung.android.sdk.healthdata.HealthDataStore r7 = com.samsung.android.sdk.healthdata.HealthDataStore.this     // Catch:{ RemoteException -> 0x010c }
                com.samsung.android.sdk.healthdata.HealthDataStore r0 = com.samsung.android.sdk.healthdata.HealthDataStore.this     // Catch:{ RemoteException -> 0x010c }
                long r2 = r0.k     // Catch:{ RemoteException -> 0x010c }
                com.samsung.android.sdk.healthdata.HealthDataStore.a(r7, r2)     // Catch:{ RemoteException -> 0x010c }
                return
            L_0x010c:
                com.samsung.android.sdk.healthdata.HealthDataStore r7 = com.samsung.android.sdk.healthdata.HealthDataStore.this
                com.samsung.android.sdk.healthdata.HealthDataStore$ConnectionListener r7 = r7.c
                if (r7 == 0) goto L_0x0127
                com.samsung.android.sdk.healthdata.HealthDataStore r7 = com.samsung.android.sdk.healthdata.HealthDataStore.this
                com.samsung.android.sdk.healthdata.HealthDataStore$ConnectionListener r7 = r7.c
                com.samsung.android.sdk.healthdata.HealthConnectionErrorResult r0 = new com.samsung.android.sdk.healthdata.HealthConnectionErrorResult
                r0.<init>(r1, r1)
                r7.onConnectionFailed(r0)
                com.samsung.android.sdk.healthdata.HealthDataStore r7 = com.samsung.android.sdk.healthdata.HealthDataStore.this
                r7.a = r8
            L_0x0127:
                return
            L_0x0128:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.healthdata.HealthDataStore.AnonymousClass2.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            Log.d("HealthDataStore", "Service for HealthDataStore is disconnected");
            HealthDataStore.this.d = null;
            if (HealthDataStore.this.c != null) {
                HealthDataStore.this.c.onDisconnected();
                HealthDataStore.this.a = null;
            }
        }
    };

    public interface ConnectionListener {
        void onConnected();

        void onConnectionFailed(HealthConnectionErrorResult healthConnectionErrorResult);

        void onDisconnected();
    }

    class a extends AsyncTask<Void, Void, Boolean> {
        private final int a;

        /* synthetic */ a(HealthDataStore healthDataStore, int i, byte b2) {
            this(i);
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Boolean bool = (Boolean) obj;
            if (HealthDataStore.this.c != null) {
                HealthConnectionErrorResult healthConnectionErrorResult = new HealthConnectionErrorResult(this.a, bool == null ? false : bool.booleanValue());
                healthConnectionErrorResult.setPackageManager(HealthDataStore.this.e.getPackageManager());
                HealthDataStore.this.c.onConnectionFailed(healthConnectionErrorResult);
            }
        }

        private a(int i) {
            this.a = i;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return Boolean.valueOf(HealthDataStore.this.c());
        }
    }

    static class b extends Handler {
        private final WeakReference<HealthDataStore> a;

        b(HealthDataStore healthDataStore) {
            this.a = new WeakReference<>(healthDataStore);
        }

        public final void handleMessage(Message message) {
            HealthDataStore healthDataStore = (HealthDataStore) this.a.get();
            if (healthDataStore != null) {
                int i = message.what;
                if (i != -3) {
                    if (i == 5) {
                        if (healthDataStore.a != null) {
                            healthDataStore.a.cancel();
                            healthDataStore.a = null;
                            Log.i("HealthDataStore", "Init ResultHolder is canceled by time out");
                        } else {
                            return;
                        }
                    }
                    HealthDataStore.a(healthDataStore, message.what);
                    return;
                }
                String string = message.getData().getString("pincode_activity_pkg");
                String string2 = message.getData().getString("pincode_activity_class");
                Intent intent = new Intent();
                intent.addFlags(4194304);
                intent.putExtra("type", 1);
                intent.setComponent(new ComponentName(string, string2));
                StringBuilder sb = new StringBuilder("Pop up PinCode activity pkg = ");
                sb.append(string);
                sb.append(", classname = ");
                sb.append(string2);
                Log.i("HealthDataStore", sb.toString());
                try {
                    if (!(healthDataStore.e instanceof Activity)) {
                        intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    }
                    healthDataStore.e.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    StringBuilder sb2 = new StringBuilder("Only this app cannot access with this ");
                    sb2.append(e.getMessage());
                    Log.i("HealthDataStore", sb2.toString());
                }
            }
        }
    }

    private static boolean a(Signature[] signatureArr) {
        Signature signature = new Signature("308204d4308203bca003020102020900e5eff0a8f66d92b3300d06092a864886f70d01010505003081a2310b3009060355040613024b52311430120603550408130b536f757468204b6f726561311330110603550407130a5375776f6e2043697479311c301a060355040a131353616d73756e6720436f72706f726174696f6e310c300a060355040b1303444d43311530130603550403130c53616d73756e6720436572743125302306092a864886f70d0109011616616e64726f69642e6f734073616d73756e672e636f6d301e170d3131303632323132323531335a170d3338313130373132323531335a3081a2310b3009060355040613024b52311430120603550408130b536f757468204b6f726561311330110603550407130a5375776f6e2043697479311c301a060355040a131353616d73756e6720436f72706f726174696f6e310c300a060355040b1303444d43311530130603550403130c53616d73756e6720436572743125302306092a864886f70d0109011616616e64726f69642e6f734073616d73756e672e636f6d30820120300d06092a864886f70d01010105000382010d00308201080282010100e9f1edb42423201dce62e68f2159ed8ea766b43a43d348754841b72e9678ce6b03d06d31532d88f2ef2d5ba39a028de0857983cd321f5b7786c2d3699df4c0b40c8d856f147c5dc54b9d1d671d1a51b5c5364da36fc5b0fe825afb513ec7a2db862c48a6046c43c3b71a1e275155f6c30aed2a68326ac327f60160d427cf55b617230907a84edbff21cc256c628a16f15d55d49138cdf2606504e1591196ed0bdc25b7cc4f67b33fb29ec4dbb13dbe6f3467a0871a49e620067755e6f095c3bd84f8b7d1e66a8c6d1e5150f7fa9d95475dc7061a321aaf9c686b09be23ccc59b35011c6823ffd5874d8fa2a1e5d276ee5aa381187e26112c7d5562703b36210b020103a382010b30820107301d0603551d0e041604145b115b23db35655f9f77f78756961006eebe3a9e3081d70603551d230481cf3081cc80145b115b23db35655f9f77f78756961006eebe3a9ea181a8a481a53081a2310b3009060355040613024b52311430120603550408130b536f757468204b6f726561311330110603550407130a5375776f6e2043697479311c301a060355040a131353616d73756e6720436f72706f726174696f6e310c300a060355040b1303444d43311530130603550403130c53616d73756e6720436572743125302306092a864886f70d0109011616616e64726f69642e6f734073616d73756e672e636f6d820900e5eff0a8f66d92b3300c0603551d13040530030101ff300d06092a864886f70d0101050500038201010039c91877eb09c2c84445443673c77a1219c5c02e6552fa2fbad0d736bc5ab6ebaf0375e520fe9799403ecb71659b23afda1475a34ef4b2e1ffcba8d7ff385c21cb6482540bce3837e6234fd4f7dd576d7fcfe9cfa925509f772c494e1569fe44e6fcd4122e483c2caa2c639566dbcfe85ed7818d5431e73154ad453289fb56b607643919cf534fbeefbdc2009c7fcb5f9b1fa97490462363fa4bedc5e0b9d157e448e6d0e7cfa31f1a2faa9378d03c8d1163d3803bc69bf24ec77ce7d559abcaf8d345494abf0e3276f0ebd2aa08e4f4f6f5aaea4bc523d8cc8e2c9200ba551dd3d4e15d5921303ca9333f42f992ddb70c2958e776c12d7e3b7bd74222eb5c7a");
        Signature signature2 = new Signature("308201e53082014ea00302010202044f54468b300d06092a864886f70d01010505003037310b30090603550406130255533110300e060355040a1307416e64726f6964311630140603550403130d416e64726f6964204465627567301e170d3132303330353034353232375a170d3432303232363034353232375a3037310b30090603550406130255533110300e060355040a1307416e64726f6964311630140603550403130d416e64726f696420446562756730819f300d06092a864886f70d010101050003818d00308189028181008a53be36d02befe1d152724281630bd1c42eff0edf5fdca8eb944f536ab3f54dca9b22cfb421b37706a4ad259101815723202b359250cf6c59905032798273462bfa3f9f1881f7475ee5b25849edefac81085815f42383a44cb2be1bfd5c1f049ef42f5818f35fe0b1131c769cee347d558395a5fa87c3d425b2b9c819cf91870203010001300d06092a864886f70d0101050500038181000512992268a01e0941481931f3f9b6647fbe25ee0bc9648f35d56c55f8cfa6c935fb3d435125fd60ef566769ac7e64fe2823409461ca7a04570c43baaab3fb877bf3a6a8dd9ef7e69944f65b0e5e36f2ac2bf085fdeda063898855ea2ce84c60655d824844fe1659a77c12604c3fb84d41df6f1a7705a1b9962ac2fdc9933122");
        Signature signature3 = new Signature("308204a830820390a003020102020900936eacbe07f201df300d06092a864886f70d0101050500308194310b3009060355040613025553311330110603550408130a43616c69666f726e6961311630140603550407130d4d6f756e7461696e20566965773110300e060355040a1307416e64726f69643110300e060355040b1307416e64726f69643110300e06035504031307416e64726f69643122302006092a864886f70d0109011613616e64726f696440616e64726f69642e636f6d301e170d3038303232393031333334365a170d3335303731373031333334365a308194310b3009060355040613025553311330110603550408130a43616c69666f726e6961311630140603550407130d4d6f756e7461696e20566965773110300e060355040a1307416e64726f69643110300e060355040b1307416e64726f69643110300e06035504031307416e64726f69643122302006092a864886f70d0109011613616e64726f696440616e64726f69642e636f6d30820120300d06092a864886f70d01010105000382010d00308201080282010100d6931904dec60b24b1edc762e0d9d8253e3ecd6ceb1de2ff068ca8e8bca8cd6bd3786ea70aa76ce60ebb0f993559ffd93e77a943e7e83d4b64b8e4fea2d3e656f1e267a81bbfb230b578c20443be4c7218b846f5211586f038a14e89c2be387f8ebecf8fcac3da1ee330c9ea93d0a7c3dc4af350220d50080732e0809717ee6a053359e6a694ec2cb3f284a0a466c87a94d83b31093a67372e2f6412c06e6d42f15818dffe0381cc0cd444da6cddc3b82458194801b32564134fbfde98c9287748dbf5676a540d8154c8bbca07b9e247553311c46b9af76fdeeccc8e69e7c8a2d08e782620943f99727d3c04fe72991d99df9bae38a0b2177fa31d5b6afee91f020103a381fc3081f9301d0603551d0e04160414485900563d272c46ae118605a47419ac09ca8c113081c90603551d230481c13081be8014485900563d272c46ae118605a47419ac09ca8c11a1819aa48197308194310b3009060355040613025553311330110603550408130a43616c69666f726e6961311630140603550407130d4d6f756e7461696e20566965773110300e060355040a1307416e64726f69643110300e060355040b1307416e64726f69643110300e06035504031307416e64726f69643122302006092a864886f70d0109011613616e64726f696440616e64726f69642e636f6d820900936eacbe07f201df300c0603551d13040530030101ff300d06092a864886f70d010105050003820101007aaf968ceb50c441055118d0daabaf015b8a765a27a715a2c2b44f221415ffdace03095abfa42df70708726c2069e5c36eddae0400be29452c084bc27eb6a17eac9dbe182c204eb15311f455d824b656dbe4dc2240912d7586fe88951d01a8feb5ae5a4260535df83431052422468c36e22c2a5ef994d61dd7306ae4c9f6951ba3c12f1d1914ddc61f1a62da2df827f603fea5603b2c540dbd7c019c36bab29a4271c117df523cdbc5f3817a49e0efa60cbd7f74177e7a4f193d43f4220772666e4c4d83e1bd5a86087cf34f2dec21e245ca6c2bb016e683638050d2c430eea7c26a1c49d3760a58ab7f1a82cc938b4831384324bd0401fa12163a50570e684d");
        Signature signature4 = new Signature("308204a830820390a003020102020900b3998086d056cffa300d06092a864886f70d0101040500308194310b3009060355040613025553311330110603550408130a43616c69666f726e6961311630140603550407130d4d6f756e7461696e20566965773110300e060355040a1307416e64726f69643110300e060355040b1307416e64726f69643110300e06035504031307416e64726f69643122302006092a864886f70d0109011613616e64726f696440616e64726f69642e636f6d301e170d3038303431353232343035305a170d3335303930313232343035305a308194310b3009060355040613025553311330110603550408130a43616c69666f726e6961311630140603550407130d4d6f756e7461696e20566965773110300e060355040a1307416e64726f69643110300e060355040b1307416e64726f69643110300e06035504031307416e64726f69643122302006092a864886f70d0109011613616e64726f696440616e64726f69642e636f6d30820120300d06092a864886f70d01010105000382010d003082010802820101009c780592ac0d5d381cdeaa65ecc8a6006e36480c6d7207b12011be50863aabe2b55d009adf7146d6f2202280c7cd4d7bdb26243b8a806c26b34b137523a49268224904dc01493e7c0acf1a05c874f69b037b60309d9074d24280e16bad2a8734361951eaf72a482d09b204b1875e12ac98c1aa773d6800b9eafde56d58bed8e8da16f9a360099c37a834a6dfedb7b6b44a049e07a269fccf2c5496f2cf36d64df90a3b8d8f34a3baab4cf53371ab27719b3ba58754ad0c53fc14e1db45d51e234fbbe93c9ba4edf9ce54261350ec535607bf69a2ff4aa07db5f7ea200d09a6c1b49e21402f89ed1190893aab5a9180f152e82f85a45753cf5fc19071c5eec827020103a381fc3081f9301d0603551d0e041604144fe4a0b3dd9cba29f71d7287c4e7c38f2086c2993081c90603551d230481c13081be80144fe4a0b3dd9cba29f71d7287c4e7c38f2086c299a1819aa48197308194310b3009060355040613025553311330110603550408130a43616c69666f726e6961311630140603550407130d4d6f756e7461696e20566965773110300e060355040a1307416e64726f69643110300e060355040b1307416e64726f69643110300e06035504031307416e64726f69643122302006092a864886f70d0109011613616e64726f696440616e64726f69642e636f6d820900b3998086d056cffa300c0603551d13040530030101ff300d06092a864886f70d01010405000382010100572551b8d93a1f73de0f6d469f86dad6701400293c88a0cd7cd778b73dafcc197fab76e6212e56c1c761cfc42fd733de52c50ae08814cefc0a3b5a1a4346054d829f1d82b42b2048bf88b5d14929ef85f60edd12d72d55657e22e3e85d04c831d613d19938bb8982247fa321256ba12d1d6a8f92ea1db1c373317ba0c037f0d1aff645aef224979fba6e7a14bc025c71b98138cef3ddfc059617cf24845cf7b40d6382f7275ed738495ab6e5931b9421765c491b72fb68e080dbdb58c2029d347c8b328ce43ef6a8b15533edfbe989bd6a48dd4b202eda94c6ab8dd5b8399203daae2ed446232e4fe9bd961394c6300e5138e3cfd285e6e4e483538cb8b1b357");
        Signature signature5 = new Signature("308204d4308203bca003020102020900d20995a79c0daad6300d06092a864886f70d01010505003081a2310b3009060355040613024b52311430120603550408130b536f757468204b6f726561311330110603550407130a5375776f6e2043697479311c301a060355040a131353616d73756e6720436f72706f726174696f6e310c300a060355040b1303444d43311530130603550403130c53616d73756e6720436572743125302306092a864886f70d0109011616616e64726f69642e6f734073616d73756e672e636f6d301e170d3131303632323132323531325a170d3338313130373132323531325a3081a2310b3009060355040613024b52311430120603550408130b536f757468204b6f726561311330110603550407130a5375776f6e2043697479311c301a060355040a131353616d73756e6720436f72706f726174696f6e310c300a060355040b1303444d43311530130603550403130c53616d73756e6720436572743125302306092a864886f70d0109011616616e64726f69642e6f734073616d73756e672e636f6d30820120300d06092a864886f70d01010105000382010d00308201080282010100c986384a3e1f2fb206670e78ef232215c0d26f45a22728db99a44da11c35ac33a71fe071c4a2d6825a9b4c88b333ed96f3c5e6c666d60f3ee94c490885abcf8dc660f707aabc77ead3e2d0d8aee8108c15cd260f2e85042c28d2f292daa3c6da0c7bf2391db7841aade8fdf0c9d0defcf77124e6d2de0a9e0d2da746c3670e4ffcdc85b701bb4744861b96ff7311da3603c5a10336e55ffa34b4353eedc85f51015e1518c67e309e39f87639ff178107f109cd18411a6077f26964b6e63f8a70b9619db04306a323c1a1d23af867e19f14f570ffe573d0e3a0c2b30632aaec3173380994be1e341e3a90bd2e4b615481f46db39ea83816448ec35feb1735c1f3020103a382010b30820107301d0603551d0e04160414932c3af70b627a0c7610b5a0e7427d6cfaea3f1e3081d70603551d230481cf3081cc8014932c3af70b627a0c7610b5a0e7427d6cfaea3f1ea181a8a481a53081a2310b3009060355040613024b52311430120603550408130b536f757468204b6f726561311330110603550407130a5375776f6e2043697479311c301a060355040a131353616d73756e6720436f72706f726174696f6e310c300a060355040b1303444d43311530130603550403130c53616d73756e6720436572743125302306092a864886f70d0109011616616e64726f69642e6f734073616d73756e672e636f6d820900d20995a79c0daad6300c0603551d13040530030101ff300d06092a864886f70d01010505000382010100329601fe40e036a4a86cc5d49dd8c1b5415998e72637538b0d430369ac51530f63aace8c019a1a66616a2f1bb2c5fabd6f313261f380e3471623f053d9e3c53f5fd6d1965d7b000e4dc244c1b27e2fe9a323ff077f52c4675e86247aa801187137e30c9bbf01c567a4299db4bf0b25b7d7107a7b81ee102f72ff47950164e26752e114c42f8b9d2a42e7308897ec640ea1924ed13abbe9d120912b62f4926493a86db94c0b46f44c6161d58c2f648164890c512dfb28d42c855bf470dbee2dab6960cad04e81f71525ded46cdd0f359f99c460db9f007d96ce83b4b218ac2d82c48f12608d469733f05a3375594669ccbf8a495544d6c5701e9369c08c810158");
        Signature[] signatureArr2 = {signature, signature5, signature2, signature3, signature4};
        Signature[] signatureArr3 = {signature, signature5};
        if (Build.TYPE.equalsIgnoreCase("eng") || Build.TYPE.equalsIgnoreCase("userdebug")) {
            Log.d("HealthDataStore", " SIGNATURES_ENG ");
            signatureArr3 = signatureArr2;
        }
        for (Signature signature6 : signatureArr) {
            for (Signature equals : signatureArr3) {
                if (equals.equals(signature6)) {
                    Log.d("HealthDataStore", " signature matched ");
                    return true;
                }
            }
        }
        if (signatureArr.length > 0) {
            for (Signature signature7 : signatureArr) {
                int length = signature7.toCharsString().length();
                StringBuilder sb = new StringBuilder(" signature : ");
                sb.append(signature7.toCharsString().substring(length - 5, length));
                Log.d("HealthDataStore", sb.toString());
            }
        } else {
            Log.d("HealthDataStore", " no signatures");
        }
        return false;
    }

    public HealthDataStore(Context context, ConnectionListener connectionListener) {
        this.e = context;
        this.c = connectionListener;
        try {
            Bundle bundle = this.e.getPackageManager().getApplicationInfo(this.e.getPackageName(), 128).metaData;
            if (bundle != null) {
                if ("dev".equalsIgnoreCase(bundle.getString("com.samsung.android.health.platform_type"))) {
                    b = "com.samsung.android.sdkapp.health";
                }
            }
        } catch (NameNotFoundException unused) {
        } catch (NullPointerException unused2) {
        }
    }

    public static String getPlatformPackageName() {
        return b;
    }

    public static String getSocketKey() {
        return g;
    }

    public static long getMyUserId() {
        return h;
    }

    /* access modifiers changed from: 0000 */
    public final Context a() {
        return this.e;
    }

    public void connectService(long j2) {
        if (this.e == null) {
            throw new IllegalStateException("Context is not specified(null)");
        } else if (HealthDataService.a) {
            if (!"com.samsung.android.sdkapp.health".equals(b)) {
                try {
                    PackageInfo packageInfo = this.e.getPackageManager().getPackageInfo(b, 64);
                    if (!b.equals(this.e.getPackageName())) {
                        try {
                            if (!a(packageInfo.signatures)) {
                                this.f.sendEmptyMessageDelayed(8, 2);
                                return;
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            Log.e("HealthDataStore", e2.toString());
                            this.f.sendEmptyMessageDelayed(8, 2);
                            return;
                        }
                    }
                } catch (NameNotFoundException unused) {
                    this.f.sendEmptyMessageDelayed(2, 2);
                    return;
                } catch (Exception e3) {
                    StringBuilder sb = new StringBuilder("Context is not valid. ");
                    sb.append(e3.toString());
                    throw new IllegalStateException(sb.toString());
                }
            }
            Intent intent = new Intent("com.samsung.android.sdk.healthdata.IHealthDataStore");
            intent.setPackage(b);
            try {
                if (this.e.bindService(intent, this.l, 65)) {
                    this.k = j2;
                    return;
                }
                try {
                    PackageInfo packageInfo2 = this.e.getPackageManager().getPackageInfo(b, 128);
                    if (packageInfo2.versionCode < 4000000) {
                        this.f.sendEmptyMessageDelayed(4, 2);
                    } else if (packageInfo2.applicationInfo.enabled) {
                        this.f.sendEmptyMessageDelayed(1, 2);
                    } else {
                        this.f.sendEmptyMessageDelayed(6, 2);
                    }
                } catch (NameNotFoundException unused2) {
                    this.f.sendEmptyMessageDelayed(2, 2);
                } catch (Exception e4) {
                    StringBuilder sb2 = new StringBuilder("Context is not valid. ");
                    sb2.append(e4.toString());
                    throw new IllegalStateException(sb2.toString());
                }
            } catch (Exception e5) {
                StringBuilder sb3 = new StringBuilder("Context is not valid. ");
                sb3.append(e5.toString());
                throw new IllegalStateException(sb3.toString());
            }
        } else {
            throw new IllegalStateException("HealthDataService is not initialized correctly");
        }
    }

    public void connectService() {
        connectService(j);
    }

    public void disconnectService() {
        Context context = this.e;
        if (context != null) {
            try {
                context.unbindService(this.l);
            } catch (IllegalArgumentException | NullPointerException unused) {
                Log.e("HealthDataStore", "disconnectService: Context instance is invalid");
            }
        }
    }

    public static IHealth getInterface(HealthDataStore healthDataStore) {
        if (healthDataStore != null) {
            IHealth iHealth = healthDataStore.d;
            if (iHealth != null) {
                return iHealth;
            }
            throw new IllegalStateException("Health data service is not connected");
        }
        throw new IllegalStateException("HealthDataStore is null");
    }

    /* access modifiers changed from: private */
    public int b() {
        PackageManager packageManager = this.e.getPackageManager();
        if (packageManager != null) {
            try {
                return packageManager.getPackageInfo("com.sec.android.app.shealth", 128).versionCode;
            } catch (NameNotFoundException unused) {
            }
        }
        return -1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00f9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean c() {
        /*
            r6 = this;
            android.content.Context r0 = r6.e
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.Boolean r0 = r6.i
            if (r0 == 0) goto L_0x000f
            boolean r0 = r0.booleanValue()
            return r0
        L_0x000f:
            java.lang.String r0 = android.os.Build.MODEL
            java.lang.String r2 = "OMAP_SS"
            boolean r2 = r0.startsWith(r2)
            if (r2 == 0) goto L_0x001d
            java.lang.String r0 = d()
        L_0x001d:
            java.lang.String r2 = "SAMSUNG-"
            boolean r2 = r0.startsWith(r2)
            if (r2 == 0) goto L_0x002b
            r2 = 8
            java.lang.String r0 = r0.substring(r2)
        L_0x002b:
            java.lang.String r2 = "http://hub.samsungapps.com/product/appCheck.as?"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            java.lang.String r2 = "appInfo=com.sec.android.app.shealth@0"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            java.lang.String r2 = "&deviceId="
            r3.append(r2)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = "&mnc="
            r2.append(r0)
            android.content.Context r0 = r6.e
            r3 = 3
            if (r0 == 0) goto L_0x007f
            java.lang.String r4 = "phone"
            java.lang.Object r0 = r0.getSystemService(r4)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            if (r0 == 0) goto L_0x007f
            java.lang.String r0 = r0.getSimOperator()
            if (r0 == 0) goto L_0x007f
            int r4 = r0.length()
            if (r4 == 0) goto L_0x007f
            java.lang.String r0 = r0.substring(r3)
            goto L_0x0081
        L_0x007f:
            java.lang.String r0 = ""
        L_0x0081:
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = "&csc="
            r2.append(r0)
            java.lang.String r0 = ""
            boolean r4 = e()
            if (r4 == 0) goto L_0x00af
            java.lang.String r4 = f()
            if (r4 == 0) goto L_0x00af
            java.lang.String r5 = "FAIL"
            boolean r5 = r4.equalsIgnoreCase(r5)
            if (r5 != 0) goto L_0x00af
            java.lang.String r0 = r4.substring(r1, r3)
        L_0x00af:
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = "&openApi="
            r2.append(r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.io.File r2 = new java.io.File
            java.lang.String r4 = "mnt/sdcard/pd.test"
            r2.<init>(r4)
            boolean r2 = r2.exists()
            if (r2 == 0) goto L_0x00f9
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = "&pd=1"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = "&mcc=000"
            goto L_0x0158
        L_0x00f9:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = "&pd="
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = "&mcc="
            r2.append(r0)
            android.content.Context r0 = r6.e
            if (r0 != 0) goto L_0x011e
            java.lang.String r0 = ""
            goto L_0x0158
        L_0x011e:
            java.lang.String r4 = "phone"
            java.lang.Object r0 = r0.getSystemService(r4)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            if (r0 != 0) goto L_0x012b
            java.lang.String r0 = ""
            goto L_0x0158
        L_0x012b:
            java.lang.String r4 = r0.getSimOperator()
            int r5 = r0.getPhoneType()
            if (r5 == 0) goto L_0x0149
            java.lang.String r0 = r0.getSimOperator()
            if (r0 == 0) goto L_0x0146
            int r4 = r0.length()
            if (r4 == 0) goto L_0x0146
            java.lang.String r0 = r0.substring(r1, r3)
            goto L_0x0158
        L_0x0146:
            java.lang.String r0 = ""
            goto L_0x0158
        L_0x0149:
            if (r4 == 0) goto L_0x0156
            int r0 = r4.length()
            if (r0 == 0) goto L_0x0156
            java.lang.String r0 = r4.substring(r1, r3)
            goto L_0x0158
        L_0x0156:
            java.lang.String r0 = ""
        L_0x0158:
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.String r2 = "HealthDataStore"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Server URL : "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            android.util.Log.d(r2, r3)
            java.net.URL r2 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0186 }
            r2.<init>(r0)     // Catch:{ MalformedURLException -> 0x0186 }
            boolean r0 = a(r2)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r6.i = r0
            boolean r0 = r0.booleanValue()
            return r0
        L_0x0186:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            r6.i = r0
            boolean r0 = r0.booleanValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.healthdata.HealthDataStore.c():boolean");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0025 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String d() {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "/system/version"
            java.io.File r2 = new java.io.File
            r2.<init>(r1)
            boolean r1 = r2.isFile()
            if (r1 == 0) goto L_0x002f
            r1 = 128(0x80, float:1.794E-43)
            byte[] r1 = new byte[r1]
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x002e }
            r3.<init>(r2)     // Catch:{ FileNotFoundException -> 0x002e }
            int r2 = r3.read(r1)     // Catch:{ Exception -> 0x0025, all -> 0x0029 }
            if (r2 <= 0) goto L_0x0025
            java.lang.String r4 = new java.lang.String     // Catch:{ Exception -> 0x0025, all -> 0x0029 }
            r5 = 0
            r4.<init>(r1, r5, r2)     // Catch:{ Exception -> 0x0025, all -> 0x0029 }
            r0 = r4
        L_0x0025:
            r3.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x002f
        L_0x0029:
            r0 = move-exception
            r3.close()     // Catch:{ IOException -> 0x002d }
        L_0x002d:
            throw r0
        L_0x002e:
            return r0
        L_0x002f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.healthdata.HealthDataStore.d():java.lang.String");
    }

    private static boolean e() {
        try {
            return new File("/system/csc/sales_code.dat").exists();
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0036, code lost:
        if (r3 != null) goto L_0x0027;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0031 A[SYNTHETIC, Splitter:B:17:0x0031] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String f() {
        /*
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "/system/csc/sales_code.dat"
            r0.<init>(r1)
            boolean r1 = r0.isFile()
            r2 = 0
            if (r1 == 0) goto L_0x0039
            r1 = 20
            byte[] r1 = new byte[r1]
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0035, all -> 0x002d }
            r3.<init>(r0)     // Catch:{ IOException -> 0x0035, all -> 0x002d }
            int r0 = r3.read(r1)     // Catch:{ IOException -> 0x0036, all -> 0x002b }
            if (r0 <= 0) goto L_0x0024
            java.lang.String r0 = new java.lang.String     // Catch:{ IOException -> 0x0036, all -> 0x002b }
            r0.<init>(r1)     // Catch:{ IOException -> 0x0036, all -> 0x002b }
            r2 = r0
            goto L_0x0027
        L_0x0024:
            java.lang.String r0 = "FAIL"
            r2 = r0
        L_0x0027:
            r3.close()     // Catch:{ IOException -> 0x0039 }
            goto L_0x0039
        L_0x002b:
            r0 = move-exception
            goto L_0x002f
        L_0x002d:
            r0 = move-exception
            r3 = r2
        L_0x002f:
            if (r3 == 0) goto L_0x0034
            r3.close()     // Catch:{ IOException -> 0x0034 }
        L_0x0034:
            throw r0
        L_0x0035:
            r3 = r2
        L_0x0036:
            if (r3 == 0) goto L_0x0039
            goto L_0x0027
        L_0x0039:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.healthdata.HealthDataStore.f():java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x00c4 A[SYNTHETIC, Splitter:B:55:0x00c4] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00cb A[SYNTHETIC, Splitter:B:63:0x00cb] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.net.URL r9) {
        /*
            java.lang.String r0 = ""
            r1 = 0
            r2 = 0
            org.xmlpull.v1.XmlPullParserFactory r3 = org.xmlpull.v1.XmlPullParserFactory.newInstance()     // Catch:{ Exception -> 0x00c8, all -> 0x00c0 }
            org.xmlpull.v1.XmlPullParser r3 = r3.newPullParser()     // Catch:{ Exception -> 0x00c8, all -> 0x00c0 }
            java.io.InputStream r9 = r9.openStream()     // Catch:{ Exception -> 0x00c8, all -> 0x00c0 }
            r3.setInput(r9, r1)     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            int r1 = r3.getEventType()     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            r4 = r0
            r0 = 0
        L_0x0019:
            r5 = 1
            if (r1 == r5) goto L_0x00b8
            r6 = 2
            if (r1 != r6) goto L_0x0090
            java.lang.String r6 = r3.getName()     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            java.lang.String r7 = "appId"
            boolean r7 = r7.equals(r6)     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            r8 = 4
            if (r7 == 0) goto L_0x0036
            int r6 = r3.next()     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            if (r6 != r8) goto L_0x0090
            r3.getText()     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            goto L_0x0090
        L_0x0036:
            java.lang.String r7 = "resultCode"
            boolean r7 = r7.equals(r6)     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            if (r7 == 0) goto L_0x0049
            int r6 = r3.next()     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            if (r6 != r8) goto L_0x0090
            java.lang.String r4 = r3.getText()     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            goto L_0x0090
        L_0x0049:
            java.lang.String r7 = "resultMsg"
            boolean r7 = r7.equals(r6)     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            if (r7 == 0) goto L_0x005b
            int r6 = r3.next()     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            if (r6 != r8) goto L_0x0090
            r3.getText()     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            goto L_0x0090
        L_0x005b:
            java.lang.String r7 = "version"
            boolean r7 = r7.equals(r6)     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            if (r7 == 0) goto L_0x006d
            int r6 = r3.next()     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            if (r6 != r8) goto L_0x0090
            r3.getText()     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            goto L_0x0090
        L_0x006d:
            java.lang.String r7 = "versionCode"
            boolean r6 = r7.equals(r6)     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            if (r6 == 0) goto L_0x0090
            int r6 = r3.next()     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            if (r6 != r8) goto L_0x0090
            java.lang.String r6 = r3.getText()     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            java.lang.String r7 = "%010d"
            java.lang.Object[] r8 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            r8[r2] = r6     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            java.lang.String.format(r7, r8)     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
        L_0x0090:
            r6 = 3
            if (r1 != r6) goto L_0x00b2
            java.lang.String r1 = r3.getName()     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            java.lang.String r6 = "appInfo"
            boolean r1 = r1.equals(r6)     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            if (r1 == 0) goto L_0x00b2
            java.lang.String r0 = "2"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            if (r0 != 0) goto L_0x00b1
            java.lang.String r0 = "1"
            boolean r0 = r0.equals(r4)     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            if (r0 == 0) goto L_0x00b0
            goto L_0x00b1
        L_0x00b0:
            r5 = 0
        L_0x00b1:
            r0 = r5
        L_0x00b2:
            int r1 = r3.next()     // Catch:{ Exception -> 0x00c9, all -> 0x00be }
            goto L_0x0019
        L_0x00b8:
            if (r9 == 0) goto L_0x00bd
            r9.close()     // Catch:{ IOException -> 0x00bd }
        L_0x00bd:
            return r0
        L_0x00be:
            r0 = move-exception
            goto L_0x00c2
        L_0x00c0:
            r0 = move-exception
            r9 = r1
        L_0x00c2:
            if (r9 == 0) goto L_0x00c7
            r9.close()     // Catch:{ IOException -> 0x00c7 }
        L_0x00c7:
            throw r0
        L_0x00c8:
            r9 = r1
        L_0x00c9:
            if (r9 == 0) goto L_0x00ce
            r9.close()     // Catch:{ IOException -> 0x00ce }
        L_0x00ce:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.healthdata.HealthDataStore.a(java.net.URL):boolean");
    }

    static /* synthetic */ HealthResultHolder a(HealthDataStore healthDataStore, long j2) throws RemoteException {
        IHealth iHealth = healthDataStore.d;
        Log.d("HealthDataStore", "Waiting for initialization of Health framework ...");
        HealthResultHolder<BaseResult> healthResultHolder = healthDataStore.a;
        if (healthResultHolder != null) {
            healthResultHolder.cancel();
        }
        ForwardAsync forwardAsync = new ForwardAsync();
        HealthResultHolder<BaseResult> asyncResultHolder = IpcUtil.getAsyncResultHolder(forwardAsync, Looper.myLooper());
        iHealth.waitForInit2(healthDataStore.e.getPackageName(), forwardAsync, j2);
        healthDataStore.a = asyncResultHolder;
        healthDataStore.a.setResultListener(new ResultListener<BaseResult>() {
            public final void onResult(BaseResult baseResult) {
                if (baseResult.getStatus() != 1) {
                    HealthDataStore.this.f.sendEmptyMessage(7);
                } else if (HealthDataStore.this.c != null) {
                    HealthDataStore.this.c.onConnected();
                    HealthDataStore.this.a = null;
                }
            }
        });
        Message message = new Message();
        message.what = 5;
        healthDataStore.f.sendMessageDelayed(message, j2);
        return healthDataStore.a;
    }

    static /* synthetic */ void a(HealthDataStore healthDataStore, int i2) {
        if (healthDataStore.c != null) {
            StringBuilder sb = new StringBuilder("Trying to connect with Health Service fails (error code: ");
            sb.append(i2);
            sb.append(")");
            Log.d("HealthDataStore", sb.toString());
            if (i2 == 2 || i2 == 4) {
                Context context = healthDataStore.e;
                if (context != null && context.checkCallingOrSelfPermission("android.permission.INTERNET") == 0) {
                    Log.d("HealthDataStore", "Check SupportedDevice");
                    new a(healthDataStore, i2, 0).execute(new Void[0]);
                    return;
                }
            }
            HealthConnectionErrorResult healthConnectionErrorResult = new HealthConnectionErrorResult(i2, false);
            if (i2 == 2 || i2 == 4 || i2 == 6) {
                healthConnectionErrorResult.setPackageManager(healthDataStore.e.getPackageManager());
            }
            healthDataStore.c.onConnectionFailed(healthConnectionErrorResult);
        }
    }
}
