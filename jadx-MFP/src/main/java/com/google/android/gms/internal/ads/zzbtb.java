package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;
import java.util.Map;

interface zzbtb {
    int getTag();

    double readDouble() throws IOException;

    float readFloat() throws IOException;

    String readString() throws IOException;

    void readStringList(List<String> list) throws IOException;

    <T> T zza(zzbtc<T> zzbtc, zzbqq zzbqq) throws IOException;

    <T> void zza(List<T> list, zzbtc<T> zzbtc, zzbqq zzbqq) throws IOException;

    <K, V> void zza(Map<K, V> map, zzbse<K, V> zzbse, zzbqq zzbqq) throws IOException;

    void zzaa(List<Integer> list) throws IOException;

    void zzab(List<Integer> list) throws IOException;

    void zzac(List<Long> list) throws IOException;

    void zzad(List<Integer> list) throws IOException;

    void zzae(List<Long> list) throws IOException;

    long zzakv() throws IOException;

    long zzakw() throws IOException;

    int zzakx() throws IOException;

    long zzaky() throws IOException;

    int zzakz() throws IOException;

    boolean zzala() throws IOException;

    String zzalb() throws IOException;

    zzbpu zzalc() throws IOException;

    int zzald() throws IOException;

    int zzale() throws IOException;

    int zzalf() throws IOException;

    long zzalg() throws IOException;

    int zzalh() throws IOException;

    long zzali() throws IOException;

    int zzals() throws IOException;

    boolean zzalt() throws IOException;

    @Deprecated
    <T> T zzb(zzbtc<T> zzbtc, zzbqq zzbqq) throws IOException;

    @Deprecated
    <T> void zzb(List<T> list, zzbtc<T> zzbtc, zzbqq zzbqq) throws IOException;

    void zzp(List<Double> list) throws IOException;

    void zzq(List<Float> list) throws IOException;

    void zzr(List<Long> list) throws IOException;

    void zzs(List<Long> list) throws IOException;

    void zzt(List<Integer> list) throws IOException;

    void zzu(List<Long> list) throws IOException;

    void zzv(List<Integer> list) throws IOException;

    void zzw(List<Boolean> list) throws IOException;

    void zzx(List<String> list) throws IOException;

    void zzy(List<zzbpu> list) throws IOException;

    void zzz(List<Integer> list) throws IOException;
}
