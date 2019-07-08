package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;
import java.util.Map;

interface zzwk {
    int getTag();

    double readDouble() throws IOException;

    float readFloat() throws IOException;

    String readString() throws IOException;

    void readStringList(List<String> list) throws IOException;

    <T> T zza(zzwl<T> zzwl, zzub zzub) throws IOException;

    <T> void zza(List<T> list, zzwl<T> zzwl, zzub zzub) throws IOException;

    <K, V> void zza(Map<K, V> map, zzvo<K, V> zzvo, zzub zzub) throws IOException;

    @Deprecated
    <T> T zzb(zzwl<T> zzwl, zzub zzub) throws IOException;

    @Deprecated
    <T> void zzb(List<T> list, zzwl<T> zzwl, zzub zzub) throws IOException;

    void zzi(List<Double> list) throws IOException;

    void zzj(List<Float> list) throws IOException;

    void zzk(List<Long> list) throws IOException;

    void zzl(List<Long> list) throws IOException;

    void zzm(List<Integer> list) throws IOException;

    void zzn(List<Long> list) throws IOException;

    void zzo(List<Integer> list) throws IOException;

    void zzp(List<Boolean> list) throws IOException;

    void zzq(List<String> list) throws IOException;

    void zzr(List<zzte> list) throws IOException;

    void zzs(List<Integer> list) throws IOException;

    void zzt(List<Integer> list) throws IOException;

    void zzu(List<Integer> list) throws IOException;

    long zzuk() throws IOException;

    long zzul() throws IOException;

    int zzum() throws IOException;

    long zzun() throws IOException;

    int zzuo() throws IOException;

    boolean zzup() throws IOException;

    String zzuq() throws IOException;

    zzte zzur() throws IOException;

    int zzus() throws IOException;

    int zzut() throws IOException;

    int zzuu() throws IOException;

    long zzuv() throws IOException;

    int zzuw() throws IOException;

    long zzux() throws IOException;

    void zzv(List<Long> list) throws IOException;

    int zzvh() throws IOException;

    boolean zzvi() throws IOException;

    void zzw(List<Integer> list) throws IOException;

    void zzx(List<Long> list) throws IOException;
}
