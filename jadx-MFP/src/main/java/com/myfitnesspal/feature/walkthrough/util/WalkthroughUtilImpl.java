package com.myfitnesspal.feature.walkthrough.util;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.walkthrough.event.AnimationCompleteEvent;
import com.myfitnesspal.feature.walkthrough.event.SkipLoggingWalkthroughEvent;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.dialog.DialogListTextItemWithKey;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Bus;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.ReturningFunction0;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;

public class WalkthroughUtilImpl implements WalkthroughUtil {
    @Nullable
    @BindView(2131361915)
    View arrowLeft;
    @Nullable
    @BindView(2131361916)
    View arrowRight;
    @BindView(2131362297)
    TextView desc;
    private final Lazy<LocalizedStringsUtil> localizedStringsUtil;
    /* access modifiers changed from: private */
    public final Lazy<Bus> messageBus;
    @Nullable
    @BindView(2131363574)
    View searchMessageContainer;
    @Nullable
    @BindView(2131363054)
    View searchMessagePadding;
    private final Lazy<Session> session;
    @BindView(2131363667)
    Button skip;
    @BindView(2131363833)
    TextView title;
    private final Lazy<UserEnergyService> userEnergyService;
    private View view;

    public enum HideAnimationType {
        SlideOutToBottom,
        FadeOut
    }

    public enum ShowAnimationType {
        SlideUpFromBottom,
        FadeIn
    }

    public enum WalkthroughType {
        PickMeal,
        EnterQuery,
        PickFood,
        PickServingSize,
        Diary,
        NoResultsFound
    }

    public WalkthroughUtilImpl(Lazy<Bus> lazy, Lazy<Session> lazy2, Lazy<LocalizedStringsUtil> lazy3, Lazy<UserEnergyService> lazy4) {
        this.messageBus = lazy;
        this.session = lazy2;
        this.localizedStringsUtil = lazy3;
        this.userEnergyService = lazy4;
    }

    public void initialize(ReturningFunction0<String> returningFunction0, View view2, int i, int i2, boolean z, boolean z2) {
        initialize(returningFunction0, view2, i, i2);
        ViewUtils.setVisible(z2, view2.findViewById(R.id.description));
    }

    public void initialize(final ReturningFunction0<String> returningFunction0, View view2, int i, int i2) {
        this.view = view2;
        ButterKnife.bind((Object) this, view2);
        setTitle(i);
        setDescription(i2);
        ViewUtils.setVisible(true, view2);
        animateInFromBottom(view2);
        this.skip.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((Bus) WalkthroughUtilImpl.this.messageBus.get()).post(new SkipLoggingWalkthroughEvent((String) returningFunction0.execute()));
            }
        });
    }

    public void initialize(final Function1<View> function1, final View view2, int i, int i2) {
        this.view = view2;
        ButterKnife.bind((Object) this, view2);
        setTitle(i);
        setDescription(i2);
        ViewUtils.setVisible(true, view2);
        animateInFromBottom(view2);
        this.skip.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                function1.execute(view2);
            }
        });
    }

    public void showWalkthrough(View view2, WalkthroughType walkthroughType, Function2<View, WalkthroughType> function2) {
        showWalkthrough(view2, walkthroughType, function2, ShowAnimationType.SlideUpFromBottom);
    }

    public void showWalkthrough(View view2, WalkthroughType walkthroughType, Function2<View, WalkthroughType> function2, ShowAnimationType showAnimationType) {
        int i;
        int i2;
        String str;
        int i3;
        int i4;
        boolean z;
        boolean z2;
        boolean z3;
        ButterKnife.bind((Object) this, view2);
        final Context context = view2.getContext();
        String str2 = "+";
        switch (walkthroughType) {
            case PickMeal:
                str = null;
                z3 = true;
                z2 = false;
                z = false;
                i4 = R.string.last_meal_you_ate;
                i3 = R.string.select_a_meal;
                i2 = R.string.skip;
                i = 0;
                break;
            case EnterQuery:
                str = null;
                z3 = true;
                z2 = false;
                z = false;
                i4 = R.string.what_did_you_eat;
                i3 = 0;
                i2 = R.string.skip;
                i = 0;
                break;
            case PickFood:
                str = null;
                z3 = true;
                z2 = false;
                z = true;
                i4 = R.string.pick_the_closest_match;
                i3 = R.string.its_okay_if_its_not_exact;
                i2 = R.string.skip;
                i = 0;
                break;
            case PickServingSize:
                str = "✓";
                z3 = false;
                z2 = true;
                z = false;
                i4 = R.string.adjust_serving_size;
                i3 = R.string.okay_to_estimate;
                i2 = R.string.skip;
                i = R.drawable.ic_scaffolding_check_white_24dp;
                break;
            case Diary:
                str = str2;
                z3 = true;
                z2 = false;
                z = false;
                i4 = R.string.spotlight_nicely_done;
                i3 = R.string.spotlight_add_another_tap;
                i2 = R.string.ok_done;
                i = R.drawable.ic_scaffolding_add_white_24dp;
                break;
            case NoResultsFound:
                str = null;
                z3 = true;
                z2 = false;
                z = false;
                i4 = R.string.couldnt_find_what_your_looking_for;
                i3 = R.string.try_searching_again;
                i2 = R.string.skip;
                i = 0;
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append(walkthroughType);
                sb.append(" is not currently handled!");
                throw new IllegalArgumentException(sb.toString());
        }
        ViewUtils.setVisible(z3, this.arrowLeft);
        ViewUtils.setVisible(z2, this.arrowRight);
        ViewUtils.setVisible(true, this.skip);
        ViewUtils.setVisible(!z, this.searchMessagePadding);
        ViewUtils.setVisible(!z, this.skip);
        View view3 = this.searchMessageContainer;
        if (view3 != null && (view3.getLayoutParams() instanceof LayoutParams)) {
            LayoutParams layoutParams = (LayoutParams) this.searchMessageContainer.getLayoutParams();
            if (z) {
                layoutParams.weight = BitmapDescriptorFactory.HUE_RED;
                layoutParams.height = -2;
            } else {
                layoutParams.weight = 1.3f;
                layoutParams.height = 0;
            }
            this.searchMessageContainer.setLayoutParams(layoutParams);
        }
        this.skip.setText(i2);
        this.title.setText(i4);
        boolean z4 = i3 != 0;
        if (z4) {
            String string = context.getString(i3, new Object[]{str});
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
            if (Strings.notEmpty(str)) {
                int indexOf = string.indexOf(str);
                if (indexOf != -1) {
                    spannableStringBuilder.setSpan(new ImageSpan(context, i, 1), indexOf, Strings.length(str) + indexOf, 33);
                }
            }
            this.desc.setText(spannableStringBuilder);
        }
        ViewUtils.setVisible(z4, this.desc);
        final View view4 = view2;
        showAndAnimateView(view2, showAnimationType);
        Button button = this.skip;
        final Function2<View, WalkthroughType> function22 = function2;
        final WalkthroughType walkthroughType2 = walkthroughType;
        AnonymousClass3 r0 = new OnClickListener() {
            public void onClick(View view) {
                ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
                FunctionUtils.invokeIfValid(function22, view4, walkthroughType2);
            }
        };
        button.setOnClickListener(r0);
    }

    public void showNewMealPickerWalkthrough(View view2, Function2<View, WalkthroughType> function2, OnItemClickListener onItemClickListener) {
        showWalkthrough(view2, WalkthroughType.PickMeal, function2, ShowAnimationType.FadeIn);
        ListView listView = (ListView) ViewUtils.findById(view2, R.id.meal_list);
        setupNewMealPickerWalkthrough(listView);
        listView.setOnItemClickListener(onItemClickListener);
    }

    public void showDiaryWalkthrough(View view2, Function2<View, WalkthroughType> function2) {
        showWalkthrough(view2, WalkthroughType.Diary, function2);
        Resources resources = view2.getResources();
        addTopMargins(view2, (resources.getDimensionPixelSize(R.dimen.diary_section_header_footer_height) * 2) + resources.getDimensionPixelOffset(R.dimen.diary_entry_height) + (resources.getDimensionPixelSize(R.dimen.diary_card_vertical_margin) * 2));
    }

    private void addTopMargins(View view2, int i) {
        ((MarginLayoutParams) view2.getLayoutParams()).topMargin = i;
    }

    private void setupNewMealPickerWalkthrough(ListView listView) {
        ArrayList arrayList = new ArrayList();
        MealNames mealNames = ((Session) this.session.get()).getUser().getMealNames();
        if (mealNames.notEmpty()) {
            for (String str : mealNames.getNames()) {
                arrayList.add(new DialogListTextItemWithKey(str, ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getMealNameString(str, (UserEnergyService) this.userEnergyService.get())));
            }
        }
        listView.setAdapter(new ArrayAdapter<DialogListTextItemWithKey>(listView.getContext(), R.layout.meal_picker_item, arrayList) {
            public View getView(int i, View view, ViewGroup viewGroup) {
                DialogListTextItemWithKey dialogListTextItemWithKey = (DialogListTextItemWithKey) getItem(i);
                TextView textView = (TextView) super.getView(i, view, viewGroup);
                textView.setText(dialogListTextItemWithKey.getText());
                return textView;
            }
        });
    }

    private void showAndAnimateView(View view2, ShowAnimationType showAnimationType) {
        if (view2.getVisibility() != 0) {
            ViewUtils.setVisible(view2);
            animateView(view2, showAnimationType);
        }
    }

    private void animateView(View view2, ShowAnimationType showAnimationType) {
        int i;
        switch (showAnimationType) {
            case FadeIn:
                i = R.anim.fade_in;
                break;
            case SlideUpFromBottom:
                i = R.anim.slide_in_bottom_100_long;
                break;
            default:
                i = 0;
                break;
        }
        view2.startAnimation(AnimationUtils.loadAnimation(view2.getContext(), i));
    }

    private void animateInFromBottom(View view2) {
        animateView(view2, ShowAnimationType.SlideUpFromBottom);
    }

    private void setTitle(int i) {
        this.title.setText(i);
    }

    private void setDescription(int i) {
        this.desc.setText(i);
    }

    public void hide(View view2) {
        hide(view2, HideAnimationType.SlideOutToBottom, null);
    }

    public void hide(final View view2, HideAnimationType hideAnimationType, final Function1<View> function1) {
        if (ViewUtils.isVisible(view2)) {
            int i = 0;
            switch (hideAnimationType) {
                case FadeOut:
                    i = R.anim.fade_out;
                    break;
                case SlideOutToBottom:
                    i = R.anim.slide_out_bottom_100_short;
                    break;
            }
            Animation loadAnimation = AnimationUtils.loadAnimation(view2.getContext(), i);
            loadAnimation.setAnimationListener(new AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    ViewUtils.setVisible(false, view2);
                    ((Bus) WalkthroughUtilImpl.this.messageBus.get()).post(new AnimationCompleteEvent(0));
                    FunctionUtils.invokeIfValid(function1, view2);
                }
            });
            view2.startAnimation(loadAnimation);
        }
    }

    public void showDescription(boolean z) {
        if (ViewUtils.isVisible(this.view)) {
            if (z) {
                ViewUtils.setVisible(true, this.desc);
            }
            showView(this.desc, z);
            if (!z) {
                ViewUtils.setVisible(false, this.desc);
            }
        }
    }

    private void animateIn(View view2) {
        ViewUtils.setVisible(true, view2);
        showView(view2, true);
    }

    private void animateOut(View view2) {
        showView(view2, false);
        ViewUtils.setVisible(false, view2);
    }

    private void showView(View view2, boolean z) {
        Context context;
        int i;
        if (z) {
            context = view2.getContext();
            i = R.anim.fade_in;
        } else {
            context = view2.getContext();
            i = R.anim.fade_out;
        }
        view2.startAnimation(AnimationUtils.loadAnimation(context, i));
    }

    public void update(int i, int i2, boolean z) {
        if (ViewUtils.isVisible(this.view)) {
            if (ViewUtils.isVisible(this.title)) {
                animateOut(this.title);
            }
            if (ViewUtils.isVisible(this.desc)) {
                animateOut(this.desc);
            }
        }
        setTitle(i);
        setDescription(i2);
        if (ViewUtils.isVisible(this.view) || !z) {
            animateIn(this.title);
            animateIn(this.desc);
            return;
        }
        ViewUtils.setVisible(true, this.view);
        animateInFromBottom(this.view);
    }
}
