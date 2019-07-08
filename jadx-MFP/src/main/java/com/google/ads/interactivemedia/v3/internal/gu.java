package com.google.ads.interactivemedia.v3.internal;

import com.brightcove.player.event.AbstractEvent;
import com.google.android.exoplayer2.source.hls.DefaultHlsExtractorFactory;
import com.samsung.android.sdk.healthdata.HealthConstants.BloodPressure;

/* compiled from: IMASDK */
class gu {
    public static final int A = vf.h("ac-3");
    public static final int B = vf.h("dac3");
    public static final int C = vf.h("ec-3");
    public static final int D = vf.h("dec3");
    public static final int E = vf.h("dtsc");
    public static final int F = vf.h("dtsh");
    public static final int G = vf.h("dtsl");
    public static final int H = vf.h("dtse");
    public static final int I = vf.h("ddts");
    public static final int J = vf.h("tfdt");
    public static final int K = vf.h("tfhd");
    public static final int L = vf.h("trex");
    public static final int M = vf.h("trun");
    public static final int N = vf.h("sidx");
    public static final int O = vf.h("moov");
    public static final int P = vf.h("mvhd");
    public static final int Q = vf.h("trak");
    public static final int R = vf.h("mdia");
    public static final int S = vf.h("minf");
    public static final int T = vf.h("stbl");
    public static final int U = vf.h("esds");
    public static final int V = vf.h("moof");
    public static final int W = vf.h("traf");
    public static final int X = vf.h("mvex");
    public static final int Y = vf.h("mehd");
    public static final int Z = vf.h("tkhd");
    public static final int a = vf.h("ftyp");
    public static final int aA = vf.h("stsz");
    public static final int aB = vf.h("stz2");
    public static final int aC = vf.h("stco");
    public static final int aD = vf.h("co64");
    public static final int aE = vf.h("tx3g");
    public static final int aF = vf.h("wvtt");
    public static final int aG = vf.h("stpp");
    public static final int aH = vf.h("c608");
    public static final int aI = vf.h("samr");
    public static final int aJ = vf.h("sawb");
    public static final int aK = vf.h("udta");
    public static final int aL = vf.h("meta");
    public static final int aM = vf.h("keys");
    public static final int aN = vf.h("ilst");
    public static final int aO = vf.h(BloodPressure.MEAN);
    public static final int aP = vf.h("name");
    public static final int aQ = vf.h("data");
    public static final int aR = vf.h("emsg");
    public static final int aS = vf.h("st3d");
    public static final int aT = vf.h("sv3d");
    public static final int aU = vf.h("proj");
    public static final int aV = vf.h("camm");
    public static final int aW = vf.h("alac");
    public static final int aX = vf.h("alaw");
    public static final int aY = vf.h("ulaw");
    public static final int aZ = vf.h("Opus");
    public static final int aa = vf.h("edts");
    public static final int ab = vf.h("elst");
    public static final int ac = vf.h("mdhd");
    public static final int ad = vf.h("hdlr");
    public static final int ae = vf.h("stsd");
    public static final int af = vf.h("pssh");
    public static final int ag = vf.h("sinf");
    public static final int ah = vf.h("schm");
    public static final int ai = vf.h("schi");
    public static final int aj = vf.h("tenc");
    public static final int ak = vf.h("encv");
    public static final int al = vf.h("enca");
    public static final int am = vf.h("frma");
    public static final int an = vf.h("saiz");
    public static final int ao = vf.h("saio");
    public static final int ap = vf.h("sbgp");
    public static final int aq = vf.h("sgpd");
    public static final int ar = vf.h(AbstractEvent.UUID);
    public static final int as = vf.h("senc");
    public static final int at = vf.h("pasp");
    public static final int au = vf.h("TTML");
    public static final int av = vf.h("mp4v");
    public static final int aw = vf.h("stts");
    public static final int ax = vf.h("stss");
    public static final int ay = vf.h("ctts");
    public static final int az = vf.h("stsc");
    public static final int b = vf.h("avc1");
    public static final int ba = vf.h("dOps");
    public static final int bb = vf.h("fLaC");
    public static final int bc = vf.h("dfLa");
    public static final int c = vf.h("avc3");
    public static final int d = vf.h("avcC");
    public static final int e = vf.h("hvc1");
    public static final int f = vf.h("hev1");
    public static final int g = vf.h("hvcC");
    public static final int h = vf.h("vp08");
    public static final int i = vf.h("vp09");
    public static final int j = vf.h("vpcC");
    public static final int k = vf.h("av01");
    public static final int l = vf.h("av1C");
    public static final int m = vf.h("dvav");
    public static final int n = vf.h("dva1");
    public static final int o = vf.h("dvhe");
    public static final int p = vf.h("dvh1");
    public static final int q = vf.h("dvcC");
    public static final int r = vf.h("dvvC");
    public static final int s = vf.h("s263");
    public static final int t = vf.h("d263");
    public static final int u = vf.h("mdat");
    public static final int v = vf.h("mp4a");
    public static final int w = vf.h(DefaultHlsExtractorFactory.MP3_FILE_EXTENSION);
    public static final int x = vf.h("wave");
    public static final int y = vf.h("lpcm");
    public static final int z = vf.h("sowt");
    public final int bd;

    public gu(int i2) {
        this.bd = i2;
    }

    public static int a(int i2) {
        return (i2 >> 24) & 255;
    }

    public static int b(int i2) {
        return i2 & 16777215;
    }

    public String toString() {
        return c(this.bd);
    }

    public static String c(int i2) {
        char c2 = (char) (i2 >>> 24);
        char c3 = (char) ((i2 >> 16) & 255);
        char c4 = (char) ((i2 >> 8) & 255);
        char c5 = (char) (i2 & 255);
        StringBuilder sb = new StringBuilder(4);
        sb.append(c2);
        sb.append(c3);
        sb.append(c4);
        sb.append(c5);
        return sb.toString();
    }

    static {
        vf.h("vmhd");
    }
}
