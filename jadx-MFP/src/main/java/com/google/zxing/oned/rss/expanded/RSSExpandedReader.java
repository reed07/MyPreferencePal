package com.google.zxing.oned.rss.expanded;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.oned.rss.AbstractRSSReader;
import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;
import com.google.zxing.oned.rss.RSSUtils;
import com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder;
import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class RSSExpandedReader extends AbstractRSSReader {
    private static final int[] EVEN_TOTAL_SUBSET = {4, 20, 52, 104, RequestCodes.VIEW_FOOD};
    private static final int[][] FINDER_PATTERNS = {new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};
    private static final int[][] FINDER_PATTERN_SEQUENCES = {new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};
    private static final int FINDER_PAT_A = 0;
    private static final int FINDER_PAT_B = 1;
    private static final int FINDER_PAT_C = 2;
    private static final int FINDER_PAT_D = 3;
    private static final int FINDER_PAT_E = 4;
    private static final int FINDER_PAT_F = 5;
    private static final int[] GSUM = {0, 348, 1388, 2948, 3988};
    private static final int MAX_PAIRS = 11;
    private static final int[] SYMBOL_WIDEST = {7, 5, 4, 3, 1};
    private static final int[][] WEIGHTS = {new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, 180, 118, 143, 7, 21, 63}, new int[]{189, RequestCodes.FILE_CHOOSER, 13, 39, 117, 140, 209, RequestCodes.OS_SETTINGS}, new int[]{RequestCodes.CONFIRM_PHOTO_IMPORT, 157, 49, PacketTypes.SuggestUsernameResponse, 19, 57, RequestCodes.CHALLENGE_DETAIL, 91}, new int[]{62, RequestCodes.FACEBOOK_LOGIN, 136, RequestCodes.EDIT_FOOD, 169, 85, 44, PacketTypes.DiaryDaySummary}, new int[]{RequestCodes.MENUS, PacketTypes.ThirdPartyAccountVerify, 188, PacketTypes.Configuration, 4, 12, 36, 108}, new int[]{113, 128, 173, 97, 80, 29, 87, 50}, new int[]{150, 28, 84, 41, 123, PacketTypes.ResendEmailVerificationToUser, 52, 156}, new int[]{46, 138, RequestCodes.MEAL_COLLECTIONS, RequestCodes.PROGRESS_STATUS_UPDATE, PacketTypes.CheckFriendsByEmailResponse, RequestCodes.CONSENTS, RequestCodes.FOOD_MULTI_ADD_EDITOR, 166}, new int[]{76, 17, 51, 153, 37, 111, 122, 155}, new int[]{43, 129, 176, 106, 107, 110, 119, PacketTypes.SuggestUsername}, new int[]{16, 48, 144, 10, 30, 90, 59, 177}, new int[]{109, 116, PacketTypes.ThirdPartyAssociate, 200, RequestCodes.PROGRESS_PHOTOS_GALLERY, 112, 125, 164}, new int[]{70, 210, RequestCodes.PREMIUM_UPSELL, RequestCodes.RECIPE_DETAILS, RequestCodes.SEARCH_MATCH, 130, RequestCodes.SHARE_PROGRESS, 115}, new int[]{134, 191, PacketTypes.EmailUniquenessCheckRequest, 31, 93, 68, RequestCodes.VIEW_FOOD, 190}, new int[]{PacketTypes.ThirdPartyDissociateResponse, 22, 66, RequestCodes.MEAL_BROWSE_FILTER, 172, 94, 71, 2}, new int[]{6, 18, 54, RequestCodes.MACROS_SUMMARY, 64, 192, PacketTypes.UsernameValidationResponse, 40}, new int[]{120, 149, 25, 75, 14, 42, 126, 167}, new int[]{79, 26, 78, 23, 69, RequestCodes.SUBMIT_CORRECTION, RequestCodes.SEARCH_RECIPE_INGREDIENT, 175}, new int[]{103, 98, 83, 38, 114, PacketTypes.RetrieveDiaryDayForOtherUser, 182, PacketTypes.UnfriendUser}, new int[]{161, 61, RequestCodes.FOOD_EDITOR, Statements.GetOwnedFoodIdsDateDescending, RequestCodes.CHALLENGES, 88, 53, 159}, new int[]{55, 165, 73, 8, 24, 72, 5, 15}, new int[]{45, 135, RequestCodes.BARCODE_FOOD_MULTI_ADD_EDITOR, 160, 58, RequestCodes.JOIN_CHALLENGE, 100, 89}};
    private final List<ExpandedPair> pairs = new ArrayList(11);
    private final List<ExpandedRow> rows = new ArrayList();
    private final int[] startEnd = new int[2];
    private boolean startFromEven;

    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        this.pairs.clear();
        this.startFromEven = false;
        try {
            return constructResult(decodeRow2pairs(i, bitArray));
        } catch (NotFoundException unused) {
            this.pairs.clear();
            this.startFromEven = true;
            return constructResult(decodeRow2pairs(i, bitArray));
        }
    }

    public void reset() {
        this.pairs.clear();
        this.rows.clear();
    }

    /* access modifiers changed from: 0000 */
    public List<ExpandedPair> decodeRow2pairs(int i, BitArray bitArray) throws NotFoundException {
        while (true) {
            try {
                this.pairs.add(retrieveNextPair(bitArray, this.pairs, i));
            } catch (NotFoundException e) {
                if (this.pairs.isEmpty()) {
                    throw e;
                } else if (checkChecksum()) {
                    return this.pairs;
                } else {
                    boolean z = !this.rows.isEmpty();
                    storeRow(i, false);
                    if (z) {
                        List<ExpandedPair> checkRows = checkRows(false);
                        if (checkRows != null) {
                            return checkRows;
                        }
                        List<ExpandedPair> checkRows2 = checkRows(true);
                        if (checkRows2 != null) {
                            return checkRows2;
                        }
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
            }
        }
    }

    private List<ExpandedPair> checkRows(boolean z) {
        List<ExpandedPair> list = null;
        if (this.rows.size() > 25) {
            this.rows.clear();
            return null;
        }
        this.pairs.clear();
        if (z) {
            Collections.reverse(this.rows);
        }
        try {
            list = checkRows(new ArrayList(), 0);
        } catch (NotFoundException unused) {
        }
        if (z) {
            Collections.reverse(this.rows);
        }
        return list;
    }

    private List<ExpandedPair> checkRows(List<ExpandedRow> list, int i) throws NotFoundException {
        while (i < this.rows.size()) {
            ExpandedRow expandedRow = (ExpandedRow) this.rows.get(i);
            this.pairs.clear();
            for (ExpandedRow pairs2 : list) {
                this.pairs.addAll(pairs2.getPairs());
            }
            this.pairs.addAll(expandedRow.getPairs());
            if (!isValidSequence(this.pairs)) {
                i++;
            } else if (checkChecksum()) {
                return this.pairs;
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(list);
                arrayList.add(expandedRow);
                try {
                    return checkRows(arrayList, i + 1);
                } catch (NotFoundException unused) {
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static boolean isValidSequence(List<ExpandedPair> list) {
        int[][] iArr;
        boolean z;
        for (int[] iArr2 : FINDER_PATTERN_SEQUENCES) {
            if (list.size() <= iArr2.length) {
                int i = 0;
                while (true) {
                    if (i >= list.size()) {
                        z = true;
                        break;
                    } else if (((ExpandedPair) list.get(i)).getFinderPattern().getValue() != iArr2[i]) {
                        z = false;
                        break;
                    } else {
                        i++;
                    }
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    private void storeRow(int i, boolean z) {
        boolean z2 = false;
        int i2 = 0;
        boolean z3 = false;
        while (true) {
            if (i2 >= this.rows.size()) {
                break;
            }
            ExpandedRow expandedRow = (ExpandedRow) this.rows.get(i2);
            if (expandedRow.getRowNumber() > i) {
                z2 = expandedRow.isEquivalent(this.pairs);
                break;
            } else {
                z3 = expandedRow.isEquivalent(this.pairs);
                i2++;
            }
        }
        if (!z2 && !z3 && !isPartialRow(this.pairs, this.rows)) {
            this.rows.add(i2, new ExpandedRow(this.pairs, i, z));
            removePartialRows(this.pairs, this.rows);
        }
    }

    private static void removePartialRows(List<ExpandedPair> list, List<ExpandedRow> list2) {
        boolean z;
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            ExpandedRow expandedRow = (ExpandedRow) it.next();
            if (expandedRow.getPairs().size() != list.size()) {
                Iterator it2 = expandedRow.getPairs().iterator();
                while (true) {
                    z = false;
                    boolean z2 = true;
                    if (!it2.hasNext()) {
                        z = true;
                        break;
                    }
                    ExpandedPair expandedPair = (ExpandedPair) it2.next();
                    Iterator it3 = list.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            if (expandedPair.equals((ExpandedPair) it3.next())) {
                                continue;
                                break;
                            }
                        } else {
                            z2 = false;
                            continue;
                            break;
                        }
                    }
                    if (!z2) {
                        break;
                    }
                }
                if (z) {
                    it.remove();
                }
            }
        }
    }

    private static boolean isPartialRow(Iterable<ExpandedPair> iterable, Iterable<ExpandedRow> iterable2) {
        boolean z;
        boolean z2;
        Iterator it = iterable2.iterator();
        do {
            z = false;
            if (it.hasNext()) {
                ExpandedRow expandedRow = (ExpandedRow) it.next();
                Iterator it2 = iterable.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z = true;
                        continue;
                        break;
                    }
                    ExpandedPair expandedPair = (ExpandedPair) it2.next();
                    Iterator it3 = expandedRow.getPairs().iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            if (expandedPair.equals((ExpandedPair) it3.next())) {
                                z2 = true;
                                continue;
                                break;
                            }
                        } else {
                            z2 = false;
                            continue;
                            break;
                        }
                    }
                    if (!z2) {
                        continue;
                        break;
                    }
                }
            } else {
                return false;
            }
        } while (!z);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public List<ExpandedRow> getRows() {
        return this.rows;
    }

    static Result constructResult(List<ExpandedPair> list) throws NotFoundException, FormatException {
        String parseInformation = AbstractExpandedDecoder.createDecoder(BitArrayBuilder.buildBitArray(list)).parseInformation();
        ResultPoint[] resultPoints = ((ExpandedPair) list.get(0)).getFinderPattern().getResultPoints();
        ResultPoint[] resultPoints2 = ((ExpandedPair) list.get(list.size() - 1)).getFinderPattern().getResultPoints();
        return new Result(parseInformation, null, new ResultPoint[]{resultPoints[0], resultPoints[1], resultPoints2[0], resultPoints2[1]}, BarcodeFormat.RSS_EXPANDED);
    }

    private boolean checkChecksum() {
        ExpandedPair expandedPair = (ExpandedPair) this.pairs.get(0);
        DataCharacter leftChar = expandedPair.getLeftChar();
        DataCharacter rightChar = expandedPair.getRightChar();
        if (rightChar == null) {
            return false;
        }
        int checksumPortion = rightChar.getChecksumPortion();
        int i = 2;
        for (int i2 = 1; i2 < this.pairs.size(); i2++) {
            ExpandedPair expandedPair2 = (ExpandedPair) this.pairs.get(i2);
            checksumPortion += expandedPair2.getLeftChar().getChecksumPortion();
            i++;
            DataCharacter rightChar2 = expandedPair2.getRightChar();
            if (rightChar2 != null) {
                checksumPortion += rightChar2.getChecksumPortion();
                i++;
            }
        }
        if (((i - 4) * 211) + (checksumPortion % 211) == leftChar.getValue()) {
            return true;
        }
        return false;
    }

    private static int getNextSecondBar(BitArray bitArray, int i) {
        if (bitArray.get(i)) {
            return bitArray.getNextSet(bitArray.getNextUnset(i));
        }
        return bitArray.getNextUnset(bitArray.getNextSet(i));
    }

    /* access modifiers changed from: 0000 */
    public ExpandedPair retrieveNextPair(BitArray bitArray, List<ExpandedPair> list, int i) throws NotFoundException {
        FinderPattern parseFoundFinderPattern;
        DataCharacter dataCharacter;
        boolean z = list.size() % 2 == 0;
        if (this.startFromEven) {
            z = !z;
        }
        int i2 = -1;
        boolean z2 = true;
        do {
            findNextPair(bitArray, list, i2);
            parseFoundFinderPattern = parseFoundFinderPattern(bitArray, i, z);
            if (parseFoundFinderPattern == null) {
                i2 = getNextSecondBar(bitArray, this.startEnd[0]);
                continue;
            } else {
                z2 = false;
                continue;
            }
        } while (z2);
        DataCharacter decodeDataCharacter = decodeDataCharacter(bitArray, parseFoundFinderPattern, z, true);
        if (list.isEmpty() || !((ExpandedPair) list.get(list.size() - 1)).mustBeLast()) {
            try {
                dataCharacter = decodeDataCharacter(bitArray, parseFoundFinderPattern, z, false);
            } catch (NotFoundException unused) {
                dataCharacter = null;
            }
            return new ExpandedPair(decodeDataCharacter, dataCharacter, parseFoundFinderPattern, true);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void findNextPair(BitArray bitArray, List<ExpandedPair> list, int i) throws NotFoundException {
        int[] decodeFinderCounters = getDecodeFinderCounters();
        decodeFinderCounters[0] = 0;
        decodeFinderCounters[1] = 0;
        decodeFinderCounters[2] = 0;
        decodeFinderCounters[3] = 0;
        int size = bitArray.getSize();
        if (i < 0) {
            if (list.isEmpty()) {
                i = 0;
            } else {
                i = ((ExpandedPair) list.get(list.size() - 1)).getFinderPattern().getStartEnd()[1];
            }
        }
        boolean z = list.size() % 2 != 0;
        if (this.startFromEven) {
            z = !z;
        }
        boolean z2 = false;
        while (i < size) {
            z2 = !bitArray.get(i);
            if (!z2) {
                break;
            }
            i++;
        }
        int i2 = i;
        int i3 = 0;
        while (i < size) {
            if (bitArray.get(i) ^ z2) {
                decodeFinderCounters[i3] = decodeFinderCounters[i3] + 1;
            } else {
                if (i3 == 3) {
                    if (z) {
                        reverseCounters(decodeFinderCounters);
                    }
                    if (isFinderPattern(decodeFinderCounters)) {
                        int[] iArr = this.startEnd;
                        iArr[0] = i2;
                        iArr[1] = i;
                        return;
                    }
                    if (z) {
                        reverseCounters(decodeFinderCounters);
                    }
                    i2 += decodeFinderCounters[0] + decodeFinderCounters[1];
                    decodeFinderCounters[0] = decodeFinderCounters[2];
                    decodeFinderCounters[1] = decodeFinderCounters[3];
                    decodeFinderCounters[2] = 0;
                    decodeFinderCounters[3] = 0;
                    i3--;
                } else {
                    i3++;
                }
                decodeFinderCounters[i3] = 1;
                z2 = !z2;
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static void reverseCounters(int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length / 2; i++) {
            int i2 = iArr[i];
            int i3 = (length - i) - 1;
            iArr[i] = iArr[i3];
            iArr[i3] = i2;
        }
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitArray, int i, boolean z) {
        int i2;
        int i3;
        int i4;
        if (z) {
            int i5 = this.startEnd[0] - 1;
            while (i5 >= 0 && !bitArray.get(i5)) {
                i5--;
            }
            int i6 = i5 + 1;
            int[] iArr = this.startEnd;
            int i7 = iArr[0] - i6;
            i3 = iArr[1];
            i4 = i6;
            i2 = i7;
        } else {
            int[] iArr2 = this.startEnd;
            int i8 = iArr2[0];
            int nextUnset = bitArray.getNextUnset(iArr2[1] + 1);
            i2 = nextUnset - this.startEnd[1];
            i3 = nextUnset;
            i4 = i8;
        }
        int[] decodeFinderCounters = getDecodeFinderCounters();
        System.arraycopy(decodeFinderCounters, 0, decodeFinderCounters, 1, decodeFinderCounters.length - 1);
        decodeFinderCounters[0] = i2;
        try {
            FinderPattern finderPattern = new FinderPattern(parseFinderValue(decodeFinderCounters, FINDER_PATTERNS), new int[]{i4, i3}, i4, i3, i);
            return finderPattern;
        } catch (NotFoundException unused) {
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public DataCharacter decodeDataCharacter(BitArray bitArray, FinderPattern finderPattern, boolean z, boolean z2) throws NotFoundException {
        BitArray bitArray2 = bitArray;
        int[] dataCharacterCounters = getDataCharacterCounters();
        dataCharacterCounters[0] = 0;
        dataCharacterCounters[1] = 0;
        dataCharacterCounters[2] = 0;
        dataCharacterCounters[3] = 0;
        dataCharacterCounters[4] = 0;
        dataCharacterCounters[5] = 0;
        dataCharacterCounters[6] = 0;
        dataCharacterCounters[7] = 0;
        if (z2) {
            recordPatternInReverse(bitArray2, finderPattern.getStartEnd()[0], dataCharacterCounters);
        } else {
            recordPattern(bitArray2, finderPattern.getStartEnd()[1], dataCharacterCounters);
            int i = 0;
            for (int length = dataCharacterCounters.length - 1; i < length; length--) {
                int i2 = dataCharacterCounters[i];
                dataCharacterCounters[i] = dataCharacterCounters[length];
                dataCharacterCounters[length] = i2;
                i++;
            }
        }
        float sum = ((float) MathUtils.sum(dataCharacterCounters)) / 17.0f;
        float f = ((float) (finderPattern.getStartEnd()[1] - finderPattern.getStartEnd()[0])) / 15.0f;
        if (Math.abs(sum - f) / f <= 0.3f) {
            int[] oddCounts = getOddCounts();
            int[] evenCounts = getEvenCounts();
            float[] oddRoundingErrors = getOddRoundingErrors();
            float[] evenRoundingErrors = getEvenRoundingErrors();
            for (int i3 = 0; i3 < dataCharacterCounters.length; i3++) {
                float f2 = (((float) dataCharacterCounters[i3]) * 1.0f) / sum;
                int i4 = (int) (0.5f + f2);
                if (i4 <= 0) {
                    if (f2 >= 0.3f) {
                        i4 = 1;
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                } else if (i4 > 8) {
                    if (f2 <= 8.7f) {
                        i4 = 8;
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                }
                int i5 = i3 / 2;
                if ((i3 & 1) == 0) {
                    oddCounts[i5] = i4;
                    oddRoundingErrors[i5] = f2 - ((float) i4);
                } else {
                    evenCounts[i5] = i4;
                    evenRoundingErrors[i5] = f2 - ((float) i4);
                }
            }
            adjustOddEvenCounts(17);
            int value = (((finderPattern.getValue() * 4) + (z ? 0 : 2)) + (z2 ^ true ? 1 : 0)) - 1;
            int i6 = 0;
            int i7 = 0;
            for (int length2 = oddCounts.length - 1; length2 >= 0; length2--) {
                if (isNotA1left(finderPattern, z, z2)) {
                    i6 += oddCounts[length2] * WEIGHTS[value][length2 * 2];
                }
                i7 += oddCounts[length2];
            }
            int i8 = 0;
            for (int length3 = evenCounts.length - 1; length3 >= 0; length3--) {
                if (isNotA1left(finderPattern, z, z2)) {
                    i8 += evenCounts[length3] * WEIGHTS[value][(length3 * 2) + 1];
                }
            }
            int i9 = i6 + i8;
            if ((i7 & 1) != 0 || i7 > 13 || i7 < 4) {
                throw NotFoundException.getNotFoundInstance();
            }
            int i10 = (13 - i7) / 2;
            int i11 = SYMBOL_WIDEST[i10];
            int i12 = 9 - i11;
            return new DataCharacter((RSSUtils.getRSSvalue(oddCounts, i11, true) * EVEN_TOTAL_SUBSET[i10]) + RSSUtils.getRSSvalue(evenCounts, i12, false) + GSUM[i10], i9);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static boolean isNotA1left(FinderPattern finderPattern, boolean z, boolean z2) {
        return finderPattern.getValue() != 0 || !z || !z2;
    }

    private void adjustOddEvenCounts(int i) throws NotFoundException {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int sum = MathUtils.sum(getOddCounts());
        int sum2 = MathUtils.sum(getEvenCounts());
        boolean z5 = false;
        if (sum > 13) {
            z2 = true;
            z = false;
        } else if (sum < 4) {
            z2 = false;
            z = true;
        } else {
            z2 = false;
            z = false;
        }
        if (sum2 > 13) {
            z4 = false;
            z3 = true;
        } else if (sum2 < 4) {
            z4 = true;
            z3 = false;
        } else {
            z4 = false;
            z3 = false;
        }
        int i2 = (sum + sum2) - i;
        boolean z6 = (sum & 1) == 1;
        if ((sum2 & 1) == 0) {
            z5 = true;
        }
        if (i2 == 1) {
            if (z6) {
                if (!z5) {
                    z2 = true;
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            } else if (z5) {
                z3 = true;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        } else if (i2 == -1) {
            if (z6) {
                if (!z5) {
                    z = true;
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            } else if (z5) {
                z4 = true;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        } else if (i2 != 0) {
            throw NotFoundException.getNotFoundInstance();
        } else if (z6) {
            if (!z5) {
                throw NotFoundException.getNotFoundInstance();
            } else if (sum < sum2) {
                z3 = true;
                z = true;
            } else {
                z4 = true;
                z2 = true;
            }
        } else if (z5) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (z) {
            if (!z2) {
                increment(getOddCounts(), getOddRoundingErrors());
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if (z2) {
            decrement(getOddCounts(), getOddRoundingErrors());
        }
        if (z4) {
            if (!z3) {
                increment(getEvenCounts(), getOddRoundingErrors());
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if (z3) {
            decrement(getEvenCounts(), getEvenRoundingErrors());
        }
    }
}
