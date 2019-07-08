package com.myfitnesspal.feature.challenges.service;

import com.myfitnesspal.feature.challenges.model.Challenge;
import com.myfitnesspal.feature.challenges.model.ChallengeInvitePacket;
import com.myfitnesspal.feature.challenges.model.ChallengeParticipant;
import com.myfitnesspal.feature.challenges.model.ChallengeSummary;
import com.myfitnesspal.feature.challenges.model.ChallengeSummary.API_RESPONSE_MAPPER;
import com.myfitnesspal.feature.challenges.model.JoinChallengePacket;
import com.myfitnesspal.feature.challenges.model.NewChallengeParticipant;
import com.myfitnesspal.feature.challenges.model.NewInvitation;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Challenges;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import io.reactivex.Completable;
import io.reactivex.functions.Action;
import java.util.List;
import javax.inject.Provider;

public class ChallengesServiceImpl implements ChallengesService {
    private final Provider<MfpV2Api> apiProvider;
    private final Lazy<CountryService> countryService;
    private final Lazy<Session> session;

    public ChallengesServiceImpl(Provider<MfpV2Api> provider, Lazy<Session> lazy, Lazy<CountryService> lazy2) {
        this.apiProvider = provider;
        this.session = lazy;
        this.countryService = lazy2;
    }

    public List<ChallengeSummary> getChallenges(String str) throws ApiException {
        User user = ((Session) this.session.get()).getUser();
        return ((ApiResponse) ((MfpV2Api) ((MfpV2Api) this.apiProvider.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(Uri.CHALLENGES, "user_status", str, Challenges.USER_GENDER, ChallengesUtil.getGenderString(user.getGender()), Challenges.USER_COUNTRY_CODE, ((CountryService) this.countryService.get()).getShortNameFromLongName(user.getProfile().getCountryName()), Challenges.USER_AGE, Integer.valueOf(ChallengesUtil.getAge(user.getProfile().getDateOfBirth())))).getItems();
    }

    public Challenge getChallengeById(String str) throws ApiException {
        return (Challenge) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) this.apiProvider.get()).withOutputType(Challenge.API_RESPONSE_MAPPER.class)).get(String.format(Uri.CHALLENGE_BY_ID, new Object[]{str}))).getItem();
    }

    public List<ChallengeParticipant> getParticipantsForChallenge(String str, String str2) throws ApiException {
        Object[] objArr = {Challenges.RELATIONSHIP_TYPE, str2};
        return ((ApiResponse) ((MfpV2Api) ((MfpV2Api) this.apiProvider.get()).withOutputType(ChallengeParticipant.API_RESPONSE_MAPPER.class)).get(String.format(Uri.CHALLENGE_PARTICPANT, new Object[]{str}), objArr)).getItems();
    }

    public ChallengeParticipant postJoinChallenge(String str, boolean z) throws ApiException {
        return (ChallengeParticipant) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.apiProvider.get()).withOutputType(ChallengeParticipant.API_RESPONSE_MAPPER.class)).withJsonBody(new JoinChallengePacket(new NewChallengeParticipant(z)))).post(String.format(Uri.CHALLENGE_PARTICPANT, new Object[]{str}), new Object[0])).getItem();
    }

    public Completable leaveChallenge(String str, String str2) {
        return Completable.fromAction(new Action(str, str2) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: INVOKE  (wrap: com.myfitnesspal.feature.challenges.service.ChallengesServiceImpl
                      0x0000: IGET  (r0v0 com.myfitnesspal.feature.challenges.service.ChallengesServiceImpl) = (r3v0 'this' com.myfitnesspal.feature.challenges.service.-$$Lambda$ChallengesServiceImpl$K-Fj-u9CEPdMHFPPeTcR2zuQPPw A[THIS]) com.myfitnesspal.feature.challenges.service.-$$Lambda$ChallengesServiceImpl$K-Fj-u9CEPdMHFPPeTcR2zuQPPw.f$0 com.myfitnesspal.feature.challenges.service.ChallengesServiceImpl), (wrap: java.lang.String
                      0x0002: IGET  (r1v0 java.lang.String) = (r3v0 'this' com.myfitnesspal.feature.challenges.service.-$$Lambda$ChallengesServiceImpl$K-Fj-u9CEPdMHFPPeTcR2zuQPPw A[THIS]) com.myfitnesspal.feature.challenges.service.-$$Lambda$ChallengesServiceImpl$K-Fj-u9CEPdMHFPPeTcR2zuQPPw.f$1 java.lang.String), (wrap: java.lang.String
                      0x0004: IGET  (r2v0 java.lang.String) = (r3v0 'this' com.myfitnesspal.feature.challenges.service.-$$Lambda$ChallengesServiceImpl$K-Fj-u9CEPdMHFPPeTcR2zuQPPw A[THIS]) com.myfitnesspal.feature.challenges.service.-$$Lambda$ChallengesServiceImpl$K-Fj-u9CEPdMHFPPeTcR2zuQPPw.f$2 java.lang.String) com.myfitnesspal.feature.challenges.service.ChallengesServiceImpl.lambda$leaveChallenge$0(com.myfitnesspal.feature.challenges.service.ChallengesServiceImpl, java.lang.String, java.lang.String):void type: STATIC in method: com.myfitnesspal.feature.challenges.service.-$$Lambda$ChallengesServiceImpl$K-Fj-u9CEPdMHFPPeTcR2zuQPPw.run():void, dex: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:773)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:713)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:303)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
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
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                    	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                    	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                    	... 41 more
                    Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                    	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                    	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                    	at java.base/java.lang.Class.forName0(Native Method)
                    	at java.base/java.lang.Class.forName(Unknown Source)
                    	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                    	... 60 more
                    */
                /*
                    this = this;
                    com.myfitnesspal.feature.challenges.service.ChallengesServiceImpl r0 = com.myfitnesspal.feature.challenges.service.ChallengesServiceImpl.this
                    java.lang.String r1 = r3.f$1
                    java.lang.String r2 = r3.f$2
                    
                    // error: 0x0006: INVOKE  (r0 I:com.myfitnesspal.feature.challenges.service.ChallengesServiceImpl), (r1 I:java.lang.String), (r2 I:java.lang.String) com.myfitnesspal.feature.challenges.service.ChallengesServiceImpl.lambda$leaveChallenge$0(com.myfitnesspal.feature.challenges.service.ChallengesServiceImpl, java.lang.String, java.lang.String):void type: STATIC
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.challenges.service.$$Lambda$ChallengesServiceImpl$KFju9CEPdMHFPPeTcR2zuQPPw.run():void");
            }
        });
    }

    public void sendInvitation(String str, NewInvitation newInvitation) throws ApiException {
        ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.apiProvider.get()).withOutputType(Object.class)).withJsonBody(new ChallengeInvitePacket(newInvitation))).post(String.format(Uri.CHALLENGE_INVITE, new Object[]{str}), new Object[0]);
    }

    public List<ChallengeSummary> getChallengesForFriend(String str) throws ApiException {
        return ((ApiResponse) ((MfpV2Api) ((MfpV2Api) this.apiProvider.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(Uri.CHALLENGES, "user_status", "joined", "user_id", str)).getItems();
    }
}
