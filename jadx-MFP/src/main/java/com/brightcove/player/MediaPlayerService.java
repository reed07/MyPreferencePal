package com.brightcove.player;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.brightcove.player.model.Playlist;
import com.brightcove.player.model.Video;

public interface MediaPlayerService extends IInterface {

    public static abstract class Stub extends Binder implements MediaPlayerService {
        private static final String DESCRIPTOR = "com.brightcove.player.MediaPlayerService";
        static final int TRANSACTION_addVideo = 1;
        static final int TRANSACTION_clearPlaylist = 3;
        static final int TRANSACTION_countTracks = 8;
        static final int TRANSACTION_getCurrentTrackIndex = 9;
        static final int TRANSACTION_getPlaylist = 4;
        static final int TRANSACTION_hasNext = 7;
        static final int TRANSACTION_hasPrevious = 6;
        static final int TRANSACTION_isPlaying = 5;
        static final int TRANSACTION_pausePlayback = 16;
        static final int TRANSACTION_playFirstTrack = 11;
        static final int TRANSACTION_playLastTrack = 14;
        static final int TRANSACTION_playNextTrack = 13;
        static final int TRANSACTION_playPreviousTrack = 12;
        static final int TRANSACTION_playTrack = 15;
        static final int TRANSACTION_resumePlayback = 17;
        static final int TRANSACTION_setListener = 19;
        static final int TRANSACTION_setPlaylist = 2;
        static final int TRANSACTION_startPlayback = 10;
        static final int TRANSACTION_stopPlayback = 18;

        private static class Proxy implements MediaPlayerService {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void addVideo(Video video) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (video != null) {
                        obtain.writeInt(1);
                        video.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setPlaylist(Playlist playlist) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (playlist != null) {
                        obtain.writeInt(1);
                        playlist.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void clearPlaylist() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Playlist getPlaylist() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Playlist) Playlist.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isPlaying() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean hasPrevious() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean hasNext() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int countTracks() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getCurrentTrackIndex() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void startPlayback() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void playFirstTrack() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void playPreviousTrack() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void playNextTrack() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void playLastTrack() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void playTrack(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void pausePlayback() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void resumePlayback() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void stopPlayback() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setListener(MediaPlayerListener mediaPlayerListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(mediaPlayerListener != null ? mediaPlayerListener.asBinder() : null);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static MediaPlayerService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof MediaPlayerService)) {
                return new Proxy(iBinder);
            }
            return (MediaPlayerService) queryLocalInterface;
        }

        /* JADX WARNING: type inference failed for: r1v1 */
        /* JADX WARNING: type inference failed for: r1v2, types: [com.brightcove.player.model.Video] */
        /* JADX WARNING: type inference failed for: r1v4, types: [com.brightcove.player.model.Video] */
        /* JADX WARNING: type inference failed for: r1v5, types: [com.brightcove.player.model.Playlist] */
        /* JADX WARNING: type inference failed for: r1v7, types: [com.brightcove.player.model.Playlist] */
        /* JADX WARNING: type inference failed for: r1v8 */
        /* JADX WARNING: type inference failed for: r1v9 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v1
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], com.brightcove.player.model.Playlist, com.brightcove.player.model.Video]
  uses: [com.brightcove.player.model.Video, com.brightcove.player.model.Playlist]
  mth insns count: 110
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 3 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                java.lang.String r0 = "com.brightcove.player.MediaPlayerService"
                r1 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r2 = 1
                if (r4 == r1) goto L_0x011b
                r1 = 0
                switch(r4) {
                    case 1: goto L_0x0102;
                    case 2: goto L_0x00e9;
                    case 3: goto L_0x00df;
                    case 4: goto L_0x00c7;
                    case 5: goto L_0x00b9;
                    case 6: goto L_0x00ab;
                    case 7: goto L_0x009d;
                    case 8: goto L_0x008f;
                    case 9: goto L_0x0081;
                    case 10: goto L_0x0077;
                    case 11: goto L_0x006d;
                    case 12: goto L_0x0063;
                    case 13: goto L_0x0059;
                    case 14: goto L_0x004f;
                    case 15: goto L_0x0041;
                    case 16: goto L_0x0037;
                    case 17: goto L_0x002d;
                    case 18: goto L_0x0023;
                    case 19: goto L_0x0011;
                    default: goto L_0x000c;
                }
            L_0x000c:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0011:
                r5.enforceInterface(r0)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.brightcove.player.MediaPlayerListener r4 = com.brightcove.player.MediaPlayerListener.Stub.asInterface(r4)
                r3.setListener(r4)
                r6.writeNoException()
                return r2
            L_0x0023:
                r5.enforceInterface(r0)
                r3.stopPlayback()
                r6.writeNoException()
                return r2
            L_0x002d:
                r5.enforceInterface(r0)
                r3.resumePlayback()
                r6.writeNoException()
                return r2
            L_0x0037:
                r5.enforceInterface(r0)
                r3.pausePlayback()
                r6.writeNoException()
                return r2
            L_0x0041:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                r3.playTrack(r4)
                r6.writeNoException()
                return r2
            L_0x004f:
                r5.enforceInterface(r0)
                r3.playLastTrack()
                r6.writeNoException()
                return r2
            L_0x0059:
                r5.enforceInterface(r0)
                r3.playNextTrack()
                r6.writeNoException()
                return r2
            L_0x0063:
                r5.enforceInterface(r0)
                r3.playPreviousTrack()
                r6.writeNoException()
                return r2
            L_0x006d:
                r5.enforceInterface(r0)
                r3.playFirstTrack()
                r6.writeNoException()
                return r2
            L_0x0077:
                r5.enforceInterface(r0)
                r3.startPlayback()
                r6.writeNoException()
                return r2
            L_0x0081:
                r5.enforceInterface(r0)
                int r4 = r3.getCurrentTrackIndex()
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x008f:
                r5.enforceInterface(r0)
                int r4 = r3.countTracks()
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x009d:
                r5.enforceInterface(r0)
                boolean r4 = r3.hasNext()
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x00ab:
                r5.enforceInterface(r0)
                boolean r4 = r3.hasPrevious()
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x00b9:
                r5.enforceInterface(r0)
                boolean r4 = r3.isPlaying()
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x00c7:
                r5.enforceInterface(r0)
                com.brightcove.player.model.Playlist r4 = r3.getPlaylist()
                r6.writeNoException()
                if (r4 == 0) goto L_0x00da
                r6.writeInt(r2)
                r4.writeToParcel(r6, r2)
                goto L_0x00de
            L_0x00da:
                r4 = 0
                r6.writeInt(r4)
            L_0x00de:
                return r2
            L_0x00df:
                r5.enforceInterface(r0)
                r3.clearPlaylist()
                r6.writeNoException()
                return r2
            L_0x00e9:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x00fb
                android.os.Parcelable$Creator<com.brightcove.player.model.Playlist> r4 = com.brightcove.player.model.Playlist.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.brightcove.player.model.Playlist r1 = (com.brightcove.player.model.Playlist) r1
            L_0x00fb:
                r3.setPlaylist(r1)
                r6.writeNoException()
                return r2
            L_0x0102:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0114
                android.os.Parcelable$Creator<com.brightcove.player.model.Video> r4 = com.brightcove.player.model.Video.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.brightcove.player.model.Video r1 = (com.brightcove.player.model.Video) r1
            L_0x0114:
                r3.addVideo(r1)
                r6.writeNoException()
                return r2
            L_0x011b:
                r6.writeString(r0)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.MediaPlayerService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void addVideo(Video video) throws RemoteException;

    void clearPlaylist() throws RemoteException;

    int countTracks() throws RemoteException;

    int getCurrentTrackIndex() throws RemoteException;

    Playlist getPlaylist() throws RemoteException;

    boolean hasNext() throws RemoteException;

    boolean hasPrevious() throws RemoteException;

    boolean isPlaying() throws RemoteException;

    void pausePlayback() throws RemoteException;

    void playFirstTrack() throws RemoteException;

    void playLastTrack() throws RemoteException;

    void playNextTrack() throws RemoteException;

    void playPreviousTrack() throws RemoteException;

    void playTrack(int i) throws RemoteException;

    void resumePlayback() throws RemoteException;

    void setListener(MediaPlayerListener mediaPlayerListener) throws RemoteException;

    void setPlaylist(Playlist playlist) throws RemoteException;

    void startPlayback() throws RemoteException;

    void stopPlayback() throws RemoteException;
}
