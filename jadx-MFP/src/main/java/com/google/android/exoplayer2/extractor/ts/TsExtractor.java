package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap.Unseekable;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader.DvbSubtitleInfo;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader.EsInfo;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader.Factory;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader.TrackIdGenerator;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class TsExtractor implements Extractor {
    /* access modifiers changed from: private */
    public static final long AC3_FORMAT_IDENTIFIER = ((long) Util.getIntegerCodeForString("AC-3"));
    private static final int BUFFER_SIZE = 9400;
    /* access modifiers changed from: private */
    public static final long E_AC3_FORMAT_IDENTIFIER = ((long) Util.getIntegerCodeForString("EAC3"));
    public static final ExtractorsFactory FACTORY = $$Lambda$TsExtractor$fUE6PC86cqq4VqVoFQnPhfFZ8.INSTANCE;
    /* access modifiers changed from: private */
    public static final long HEVC_FORMAT_IDENTIFIER = ((long) Util.getIntegerCodeForString("HEVC"));
    private static final int MAX_PID_PLUS_ONE = 8192;
    public static final int MODE_HLS = 2;
    public static final int MODE_MULTI_PMT = 0;
    public static final int MODE_SINGLE_PMT = 1;
    private static final int SNIFF_TS_PACKET_COUNT = 5;
    public static final int TS_PACKET_SIZE = 188;
    private static final int TS_PAT_PID = 0;
    public static final int TS_STREAM_TYPE_AAC_ADTS = 15;
    public static final int TS_STREAM_TYPE_AAC_LATM = 17;
    public static final int TS_STREAM_TYPE_AC3 = 129;
    public static final int TS_STREAM_TYPE_DTS = 138;
    public static final int TS_STREAM_TYPE_DVBSUBS = 89;
    public static final int TS_STREAM_TYPE_E_AC3 = 135;
    public static final int TS_STREAM_TYPE_H262 = 2;
    public static final int TS_STREAM_TYPE_H264 = 27;
    public static final int TS_STREAM_TYPE_H265 = 36;
    public static final int TS_STREAM_TYPE_HDMV_DTS = 130;
    public static final int TS_STREAM_TYPE_ID3 = 21;
    public static final int TS_STREAM_TYPE_MPA = 3;
    public static final int TS_STREAM_TYPE_MPA_LSF = 4;
    public static final int TS_STREAM_TYPE_SPLICE_INFO = 134;
    public static final int TS_SYNC_BYTE = 71;
    private int bytesSinceLastSync;
    private final SparseIntArray continuityCounters;
    private final TsDurationReader durationReader;
    private boolean hasOutputSeekMap;
    /* access modifiers changed from: private */
    public TsPayloadReader id3Reader;
    /* access modifiers changed from: private */
    public final int mode;
    /* access modifiers changed from: private */
    public ExtractorOutput output;
    /* access modifiers changed from: private */
    public final Factory payloadReaderFactory;
    /* access modifiers changed from: private */
    public int pcrPid;
    private boolean pendingSeekToStart;
    /* access modifiers changed from: private */
    public int remainingPmts;
    /* access modifiers changed from: private */
    public final List<TimestampAdjuster> timestampAdjusters;
    /* access modifiers changed from: private */
    public final SparseBooleanArray trackIds;
    /* access modifiers changed from: private */
    public final SparseBooleanArray trackPids;
    /* access modifiers changed from: private */
    public boolean tracksEnded;
    private TsBinarySearchSeeker tsBinarySearchSeeker;
    private final ParsableByteArray tsPacketBuffer;
    /* access modifiers changed from: private */
    public final SparseArray<TsPayloadReader> tsPayloadReaders;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    private class PatReader implements SectionPayloadReader {
        private final ParsableBitArray patScratch = new ParsableBitArray(new byte[4]);

        public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TrackIdGenerator trackIdGenerator) {
        }

        public PatReader() {
        }

        public void consume(ParsableByteArray parsableByteArray) {
            if (parsableByteArray.readUnsignedByte() == 0) {
                parsableByteArray.skipBytes(7);
                int bytesLeft = parsableByteArray.bytesLeft() / 4;
                for (int i = 0; i < bytesLeft; i++) {
                    parsableByteArray.readBytes(this.patScratch, 4);
                    int readBits = this.patScratch.readBits(16);
                    this.patScratch.skipBits(3);
                    if (readBits == 0) {
                        this.patScratch.skipBits(13);
                    } else {
                        int readBits2 = this.patScratch.readBits(13);
                        TsExtractor.this.tsPayloadReaders.put(readBits2, new SectionReader(new PmtReader(readBits2)));
                        
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x004f: INVOKE  (wrap: com.google.android.exoplayer2.extractor.ts.TsExtractor
                              0x004d: IGET  (r4v5 com.google.android.exoplayer2.extractor.ts.TsExtractor) = (r9v0 'this' com.google.android.exoplayer2.extractor.ts.TsExtractor$PatReader A[THIS]) com.google.android.exoplayer2.extractor.ts.TsExtractor.PatReader.this$0 com.google.android.exoplayer2.extractor.ts.TsExtractor) com.google.android.exoplayer2.extractor.ts.TsExtractor.access$108(com.google.android.exoplayer2.extractor.ts.TsExtractor):int type: STATIC in method: com.google.android.exoplayer2.extractor.ts.TsExtractor.PatReader.consume(com.google.android.exoplayer2.util.ParsableByteArray):void, dex: classes.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:148)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                            	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:209)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:138)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                            	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                            	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                            	at jadx.core.codegen.ClassGen.addInnerClasses(ClassGen.java:237)
                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:224)
                            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
                            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
                            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
                            	at jadx.core.ProcessClass.process(ProcessClass.java:36)
                            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                            Caused by: org.objenesis.ObjenesisException: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                            	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:57)
                            	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.newConstructorForSerialization(SunReflectionFactoryHelper.java:37)
                            	at org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.<init>(SunReflectionFactoryInstantiator.java:41)
                            	at org.objenesis.strategy.StdInstantiatorStrategy.newInstantiatorOf(StdInstantiatorStrategy.java:68)
                            	at org.objenesis.ObjenesisBase.getInstantiatorOf(ObjenesisBase.java:94)
                            	at org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
                            	at com.rits.cloning.ObjenesisInstantiationStrategy.newInstance(ObjenesisInstantiationStrategy.java:17)
                            	at com.rits.cloning.Cloner.newInstance(Cloner.java:300)
                            	at com.rits.cloning.Cloner.cloneObject(Cloner.java:461)
                            	at com.rits.cloning.Cloner.cloneInternal(Cloner.java:456)
                            	at com.rits.cloning.Cloner.deepClone(Cloner.java:326)
                            	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:352)
                            	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                            	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:24)
                            	at jadx.core.dex.instructions.IndexInsnNode.copy(IndexInsnNode.java:9)
                            	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                            	... 39 more
                            Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                            	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                            	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                            	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                            	at java.base/java.lang.Class.forName0(Native Method)
                            	at java.base/java.lang.Class.forName(Unknown Source)
                            	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                            	... 57 more
                            */
                        /*
                            this = this;
                            int r0 = r10.readUnsignedByte()
                            if (r0 == 0) goto L_0x0007
                            return
                        L_0x0007:
                            r0 = 7
                            r10.skipBytes(r0)
                            int r0 = r10.bytesLeft()
                            r1 = 4
                            int r0 = r0 / r1
                            r2 = 0
                            r3 = 0
                        L_0x0013:
                            if (r3 >= r0) goto L_0x0055
                            com.google.android.exoplayer2.util.ParsableBitArray r4 = r9.patScratch
                            r10.readBytes(r4, r1)
                            com.google.android.exoplayer2.util.ParsableBitArray r4 = r9.patScratch
                            r5 = 16
                            int r4 = r4.readBits(r5)
                            com.google.android.exoplayer2.util.ParsableBitArray r5 = r9.patScratch
                            r6 = 3
                            r5.skipBits(r6)
                            r5 = 13
                            if (r4 != 0) goto L_0x0032
                            com.google.android.exoplayer2.util.ParsableBitArray r4 = r9.patScratch
                            r4.skipBits(r5)
                            goto L_0x0052
                        L_0x0032:
                            com.google.android.exoplayer2.util.ParsableBitArray r4 = r9.patScratch
                            int r4 = r4.readBits(r5)
                            com.google.android.exoplayer2.extractor.ts.TsExtractor r5 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                            android.util.SparseArray r5 = r5.tsPayloadReaders
                            com.google.android.exoplayer2.extractor.ts.SectionReader r6 = new com.google.android.exoplayer2.extractor.ts.SectionReader
                            com.google.android.exoplayer2.extractor.ts.TsExtractor$PmtReader r7 = new com.google.android.exoplayer2.extractor.ts.TsExtractor$PmtReader
                            com.google.android.exoplayer2.extractor.ts.TsExtractor r8 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                            r7.<init>(r4)
                            r6.<init>(r7)
                            r5.put(r4, r6)
                            com.google.android.exoplayer2.extractor.ts.TsExtractor r4 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                            
                            // error: 0x004f: INVOKE  (r4 I:com.google.android.exoplayer2.extractor.ts.TsExtractor) com.google.android.exoplayer2.extractor.ts.TsExtractor.access$108(com.google.android.exoplayer2.extractor.ts.TsExtractor):int type: STATIC
                        L_0x0052:
                            int r3 = r3 + 1
                            goto L_0x0013
                        L_0x0055:
                            com.google.android.exoplayer2.extractor.ts.TsExtractor r10 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                            int r10 = r10.mode
                            r0 = 2
                            if (r10 == r0) goto L_0x0067
                            com.google.android.exoplayer2.extractor.ts.TsExtractor r10 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                            android.util.SparseArray r10 = r10.tsPayloadReaders
                            r10.remove(r2)
                        L_0x0067:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.TsExtractor.PatReader.consume(com.google.android.exoplayer2.util.ParsableByteArray):void");
                    }
                }

                private class PmtReader implements SectionPayloadReader {
                    private static final int TS_PMT_DESC_AC3 = 106;
                    private static final int TS_PMT_DESC_DTS = 123;
                    private static final int TS_PMT_DESC_DVBSUBS = 89;
                    private static final int TS_PMT_DESC_EAC3 = 122;
                    private static final int TS_PMT_DESC_ISO639_LANG = 10;
                    private static final int TS_PMT_DESC_REGISTRATION = 5;
                    private final int pid;
                    private final ParsableBitArray pmtScratch = new ParsableBitArray(new byte[5]);
                    private final SparseIntArray trackIdToPidScratch = new SparseIntArray();
                    private final SparseArray<TsPayloadReader> trackIdToReaderScratch = new SparseArray<>();

                    public void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TrackIdGenerator trackIdGenerator) {
                    }

                    public PmtReader(int i) {
                        this.pid = i;
                    }

                    public void consume(ParsableByteArray parsableByteArray) {
                        TimestampAdjuster timestampAdjuster;
                        TsPayloadReader tsPayloadReader;
                        ParsableByteArray parsableByteArray2 = parsableByteArray;
                        if (parsableByteArray.readUnsignedByte() == 2) {
                            int i = 0;
                            if (TsExtractor.this.mode == 1 || TsExtractor.this.mode == 2 || TsExtractor.this.remainingPmts == 1) {
                                timestampAdjuster = (TimestampAdjuster) TsExtractor.this.timestampAdjusters.get(0);
                            } else {
                                timestampAdjuster = new TimestampAdjuster(((TimestampAdjuster) TsExtractor.this.timestampAdjusters.get(0)).getFirstSampleTimestampUs());
                                TsExtractor.this.timestampAdjusters.add(timestampAdjuster);
                            }
                            parsableByteArray2.skipBytes(2);
                            int readUnsignedShort = parsableByteArray.readUnsignedShort();
                            int i2 = 3;
                            parsableByteArray2.skipBytes(3);
                            parsableByteArray2.readBytes(this.pmtScratch, 2);
                            this.pmtScratch.skipBits(3);
                            int i3 = 13;
                            TsExtractor.this.pcrPid = this.pmtScratch.readBits(13);
                            parsableByteArray2.readBytes(this.pmtScratch, 2);
                            int i4 = 4;
                            this.pmtScratch.skipBits(4);
                            parsableByteArray2.skipBytes(this.pmtScratch.readBits(12));
                            if (TsExtractor.this.mode == 2 && TsExtractor.this.id3Reader == null) {
                                EsInfo esInfo = new EsInfo(21, null, null, Util.EMPTY_BYTE_ARRAY);
                                TsExtractor tsExtractor = TsExtractor.this;
                                tsExtractor.id3Reader = tsExtractor.payloadReaderFactory.createPayloadReader(21, esInfo);
                                TsExtractor.this.id3Reader.init(timestampAdjuster, TsExtractor.this.output, new TrackIdGenerator(readUnsignedShort, 21, 8192));
                            }
                            this.trackIdToReaderScratch.clear();
                            this.trackIdToPidScratch.clear();
                            int bytesLeft = parsableByteArray.bytesLeft();
                            while (bytesLeft > 0) {
                                parsableByteArray2.readBytes(this.pmtScratch, 5);
                                int readBits = this.pmtScratch.readBits(8);
                                this.pmtScratch.skipBits(i2);
                                int readBits2 = this.pmtScratch.readBits(i3);
                                this.pmtScratch.skipBits(i4);
                                int readBits3 = this.pmtScratch.readBits(12);
                                EsInfo readEsInfo = readEsInfo(parsableByteArray2, readBits3);
                                if (readBits == 6) {
                                    readBits = readEsInfo.streamType;
                                }
                                bytesLeft -= readBits3 + 5;
                                int i5 = TsExtractor.this.mode == 2 ? readBits : readBits2;
                                if (!TsExtractor.this.trackIds.get(i5)) {
                                    if (TsExtractor.this.mode == 2 && readBits == 21) {
                                        tsPayloadReader = TsExtractor.this.id3Reader;
                                    } else {
                                        tsPayloadReader = TsExtractor.this.payloadReaderFactory.createPayloadReader(readBits, readEsInfo);
                                    }
                                    if (TsExtractor.this.mode != 2 || readBits2 < this.trackIdToPidScratch.get(i5, 8192)) {
                                        this.trackIdToPidScratch.put(i5, readBits2);
                                        this.trackIdToReaderScratch.put(i5, tsPayloadReader);
                                    }
                                }
                                i2 = 3;
                                i4 = 4;
                                i3 = 13;
                            }
                            int size = this.trackIdToPidScratch.size();
                            for (int i6 = 0; i6 < size; i6++) {
                                int keyAt = this.trackIdToPidScratch.keyAt(i6);
                                int valueAt = this.trackIdToPidScratch.valueAt(i6);
                                TsExtractor.this.trackIds.put(keyAt, true);
                                TsExtractor.this.trackPids.put(valueAt, true);
                                TsPayloadReader tsPayloadReader2 = (TsPayloadReader) this.trackIdToReaderScratch.valueAt(i6);
                                if (tsPayloadReader2 != null) {
                                    if (tsPayloadReader2 != TsExtractor.this.id3Reader) {
                                        tsPayloadReader2.init(timestampAdjuster, TsExtractor.this.output, new TrackIdGenerator(readUnsignedShort, keyAt, 8192));
                                    }
                                    TsExtractor.this.tsPayloadReaders.put(valueAt, tsPayloadReader2);
                                }
                            }
                            if (TsExtractor.this.mode != 2) {
                                TsExtractor.this.tsPayloadReaders.remove(this.pid);
                                TsExtractor tsExtractor2 = TsExtractor.this;
                                if (tsExtractor2.mode != 1) {
                                    i = TsExtractor.this.remainingPmts - 1;
                                }
                                tsExtractor2.remainingPmts = i;
                                if (TsExtractor.this.remainingPmts == 0) {
                                    TsExtractor.this.output.endTracks();
                                    TsExtractor.this.tracksEnded = true;
                                }
                            } else if (!TsExtractor.this.tracksEnded) {
                                TsExtractor.this.output.endTracks();
                                TsExtractor.this.remainingPmts = 0;
                                TsExtractor.this.tracksEnded = true;
                            }
                        }
                    }

                    private EsInfo readEsInfo(ParsableByteArray parsableByteArray, int i) {
                        int position = parsableByteArray.getPosition();
                        int i2 = i + position;
                        String str = null;
                        int i3 = -1;
                        List list = null;
                        while (parsableByteArray.getPosition() < i2) {
                            int readUnsignedByte = parsableByteArray.readUnsignedByte();
                            int position2 = parsableByteArray.getPosition() + parsableByteArray.readUnsignedByte();
                            if (readUnsignedByte == 5) {
                                long readUnsignedInt = parsableByteArray.readUnsignedInt();
                                if (readUnsignedInt == TsExtractor.AC3_FORMAT_IDENTIFIER) {
                                    i3 = 129;
                                } else if (readUnsignedInt == TsExtractor.E_AC3_FORMAT_IDENTIFIER) {
                                    i3 = 135;
                                } else if (readUnsignedInt == TsExtractor.HEVC_FORMAT_IDENTIFIER) {
                                    i3 = 36;
                                }
                            } else if (readUnsignedByte == 106) {
                                i3 = 129;
                            } else if (readUnsignedByte == 122) {
                                i3 = 135;
                            } else if (readUnsignedByte == 123) {
                                i3 = 138;
                            } else if (readUnsignedByte == 10) {
                                str = parsableByteArray.readString(3).trim();
                            } else if (readUnsignedByte == 89) {
                                ArrayList arrayList = new ArrayList();
                                while (parsableByteArray.getPosition() < position2) {
                                    String trim = parsableByteArray.readString(3).trim();
                                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                                    byte[] bArr = new byte[4];
                                    parsableByteArray.readBytes(bArr, 0, 4);
                                    arrayList.add(new DvbSubtitleInfo(trim, readUnsignedByte2, bArr));
                                }
                                list = arrayList;
                                i3 = 89;
                            }
                            parsableByteArray.skipBytes(position2 - parsableByteArray.getPosition());
                        }
                        parsableByteArray.setPosition(i2);
                        return new EsInfo(i3, str, list, Arrays.copyOfRange(parsableByteArray.data, position, i2));
                    }
                }

                public void release() {
                }

                static /* synthetic */ Extractor[] lambda$static$0() {
                    return new Extractor[]{new TsExtractor()};
                }

                public TsExtractor() {
                    this(0);
                }

                public TsExtractor(int i) {
                    this(1, i);
                }

                public TsExtractor(int i, int i2) {
                    this(i, new TimestampAdjuster(0), new DefaultTsPayloadReaderFactory(i2));
                }

                public TsExtractor(int i, TimestampAdjuster timestampAdjuster, Factory factory) {
                    this.payloadReaderFactory = (Factory) Assertions.checkNotNull(factory);
                    this.mode = i;
                    if (i == 1 || i == 2) {
                        this.timestampAdjusters = Collections.singletonList(timestampAdjuster);
                    } else {
                        this.timestampAdjusters = new ArrayList();
                        this.timestampAdjusters.add(timestampAdjuster);
                    }
                    this.tsPacketBuffer = new ParsableByteArray(new byte[BUFFER_SIZE], 0);
                    this.trackIds = new SparseBooleanArray();
                    this.trackPids = new SparseBooleanArray();
                    this.tsPayloadReaders = new SparseArray<>();
                    this.continuityCounters = new SparseIntArray();
                    this.durationReader = new TsDurationReader();
                    this.pcrPid = -1;
                    resetPayloadReaders();
                }

                public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
                    boolean z;
                    byte[] bArr = this.tsPacketBuffer.data;
                    extractorInput.peekFully(bArr, 0, 940);
                    for (int i = 0; i < 188; i++) {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= 5) {
                                z = true;
                                break;
                            } else if (bArr[(i2 * 188) + i] != 71) {
                                z = false;
                                break;
                            } else {
                                i2++;
                            }
                        }
                        if (z) {
                            extractorInput.skipFully(i);
                            return true;
                        }
                    }
                    return false;
                }

                public void init(ExtractorOutput extractorOutput) {
                    this.output = extractorOutput;
                }

                public void seek(long j, long j2) {
                    Assertions.checkState(this.mode != 2);
                    int size = this.timestampAdjusters.size();
                    for (int i = 0; i < size; i++) {
                        TimestampAdjuster timestampAdjuster = (TimestampAdjuster) this.timestampAdjusters.get(i);
                        if ((timestampAdjuster.getTimestampOffsetUs() == -9223372036854775807L) || !(timestampAdjuster.getTimestampOffsetUs() == 0 || timestampAdjuster.getFirstSampleTimestampUs() == j2)) {
                            timestampAdjuster.reset();
                            timestampAdjuster.setFirstSampleTimestampUs(j2);
                        }
                    }
                    if (j2 != 0) {
                        TsBinarySearchSeeker tsBinarySearchSeeker2 = this.tsBinarySearchSeeker;
                        if (tsBinarySearchSeeker2 != null) {
                            tsBinarySearchSeeker2.setSeekTargetUs(j2);
                        }
                    }
                    this.tsPacketBuffer.reset();
                    this.continuityCounters.clear();
                    for (int i2 = 0; i2 < this.tsPayloadReaders.size(); i2++) {
                        ((TsPayloadReader) this.tsPayloadReaders.valueAt(i2)).seek();
                    }
                    this.bytesSinceLastSync = 0;
                }

                public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
                    ExtractorInput extractorInput2 = extractorInput;
                    PositionHolder positionHolder2 = positionHolder;
                    long length = extractorInput.getLength();
                    TsPayloadReader tsPayloadReader = null;
                    if (this.tracksEnded) {
                        if (((length == -1 || this.mode == 2) ? false : true) && !this.durationReader.isDurationReadFinished()) {
                            return this.durationReader.readDuration(extractorInput2, positionHolder2, this.pcrPid);
                        }
                        maybeOutputSeekMap(length);
                        if (this.pendingSeekToStart) {
                            this.pendingSeekToStart = false;
                            seek(0, 0);
                            if (extractorInput.getPosition() != 0) {
                                positionHolder2.position = 0;
                                return 1;
                            }
                        }
                        TsBinarySearchSeeker tsBinarySearchSeeker2 = this.tsBinarySearchSeeker;
                        if (tsBinarySearchSeeker2 != null && tsBinarySearchSeeker2.isSeeking()) {
                            return this.tsBinarySearchSeeker.handlePendingSeek(extractorInput2, positionHolder2, null);
                        }
                    }
                    if (!fillBufferWithAtLeastOnePacket(extractorInput)) {
                        return -1;
                    }
                    int findEndOfFirstTsPacketInBuffer = findEndOfFirstTsPacketInBuffer();
                    int limit = this.tsPacketBuffer.limit();
                    if (findEndOfFirstTsPacketInBuffer > limit) {
                        return 0;
                    }
                    int readInt = this.tsPacketBuffer.readInt();
                    if ((8388608 & readInt) != 0) {
                        this.tsPacketBuffer.setPosition(findEndOfFirstTsPacketInBuffer);
                        return 0;
                    }
                    int i = ((4194304 & readInt) != 0 ? 1 : 0) | 0;
                    int i2 = (2096896 & readInt) >> 8;
                    boolean z = (readInt & 32) != 0;
                    if ((readInt & 16) != 0) {
                        tsPayloadReader = (TsPayloadReader) this.tsPayloadReaders.get(i2);
                    }
                    if (tsPayloadReader == null) {
                        this.tsPacketBuffer.setPosition(findEndOfFirstTsPacketInBuffer);
                        return 0;
                    }
                    if (this.mode != 2) {
                        int i3 = readInt & 15;
                        int i4 = this.continuityCounters.get(i2, i3 - 1);
                        this.continuityCounters.put(i2, i3);
                        if (i4 == i3) {
                            this.tsPacketBuffer.setPosition(findEndOfFirstTsPacketInBuffer);
                            return 0;
                        } else if (i3 != ((i4 + 1) & 15)) {
                            tsPayloadReader.seek();
                        }
                    }
                    if (z) {
                        i |= (this.tsPacketBuffer.readUnsignedByte() & 64) != 0 ? 2 : 0;
                        this.tsPacketBuffer.skipBytes(this.tsPacketBuffer.readUnsignedByte() - 1);
                    }
                    boolean z2 = this.tracksEnded;
                    if (shouldConsumePacketPayload(i2)) {
                        this.tsPacketBuffer.setLimit(findEndOfFirstTsPacketInBuffer);
                        tsPayloadReader.consume(this.tsPacketBuffer, i);
                        this.tsPacketBuffer.setLimit(limit);
                    }
                    if (this.mode != 2 && !z2 && this.tracksEnded && length != -1) {
                        this.pendingSeekToStart = true;
                    }
                    this.tsPacketBuffer.setPosition(findEndOfFirstTsPacketInBuffer);
                    return 0;
                }

                private void maybeOutputSeekMap(long j) {
                    if (!this.hasOutputSeekMap) {
                        this.hasOutputSeekMap = true;
                        if (this.durationReader.getDurationUs() != -9223372036854775807L) {
                            TsBinarySearchSeeker tsBinarySearchSeeker2 = new TsBinarySearchSeeker(this.durationReader.getPcrTimestampAdjuster(), this.durationReader.getDurationUs(), j, this.pcrPid);
                            this.tsBinarySearchSeeker = tsBinarySearchSeeker2;
                            this.output.seekMap(this.tsBinarySearchSeeker.getSeekMap());
                            return;
                        }
                        this.output.seekMap(new Unseekable(this.durationReader.getDurationUs()));
                    }
                }

                private boolean fillBufferWithAtLeastOnePacket(ExtractorInput extractorInput) throws IOException, InterruptedException {
                    byte[] bArr = this.tsPacketBuffer.data;
                    if (9400 - this.tsPacketBuffer.getPosition() < 188) {
                        int bytesLeft = this.tsPacketBuffer.bytesLeft();
                        if (bytesLeft > 0) {
                            System.arraycopy(bArr, this.tsPacketBuffer.getPosition(), bArr, 0, bytesLeft);
                        }
                        this.tsPacketBuffer.reset(bArr, bytesLeft);
                    }
                    while (this.tsPacketBuffer.bytesLeft() < 188) {
                        int limit = this.tsPacketBuffer.limit();
                        int read = extractorInput.read(bArr, limit, 9400 - limit);
                        if (read == -1) {
                            return false;
                        }
                        this.tsPacketBuffer.setLimit(limit + read);
                    }
                    return true;
                }

                private int findEndOfFirstTsPacketInBuffer() throws ParserException {
                    int position = this.tsPacketBuffer.getPosition();
                    int limit = this.tsPacketBuffer.limit();
                    int findSyncBytePosition = TsUtil.findSyncBytePosition(this.tsPacketBuffer.data, position, limit);
                    this.tsPacketBuffer.setPosition(findSyncBytePosition);
                    int i = findSyncBytePosition + 188;
                    if (i > limit) {
                        this.bytesSinceLastSync += findSyncBytePosition - position;
                        if (this.mode == 2 && this.bytesSinceLastSync > 376) {
                            throw new ParserException("Cannot find sync byte. Most likely not a Transport Stream.");
                        }
                    } else {
                        this.bytesSinceLastSync = 0;
                    }
                    return i;
                }

                private boolean shouldConsumePacketPayload(int i) {
                    if (this.mode == 2 || this.tracksEnded || !this.trackPids.get(i, false)) {
                        return true;
                    }
                    return false;
                }

                private void resetPayloadReaders() {
                    this.trackIds.clear();
                    this.tsPayloadReaders.clear();
                    SparseArray createInitialPayloadReaders = this.payloadReaderFactory.createInitialPayloadReaders();
                    int size = createInitialPayloadReaders.size();
                    for (int i = 0; i < size; i++) {
                        this.tsPayloadReaders.put(createInitialPayloadReaders.keyAt(i), createInitialPayloadReaders.valueAt(i));
                    }
                    this.tsPayloadReaders.put(0, new SectionReader(new PatReader()));
                    this.id3Reader = null;
                }
            }
