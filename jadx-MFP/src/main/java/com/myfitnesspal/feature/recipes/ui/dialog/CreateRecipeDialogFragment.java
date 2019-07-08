package com.myfitnesspal.feature.recipes.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.feature.recipes.ui.activity.CreateRecipeManuallyActivity;
import com.myfitnesspal.feature.recipes.ui.activity.RecipeImportBrowserActivity;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Date;
import javax.inject.Inject;

public class CreateRecipeDialogFragment extends CustomLayoutBaseDialogFragment {
    private static final String EXTRA_ANALYTICS_INTENT_DATA = "recipe_analytics_intent_data";
    @Inject
    Lazy<ConfigService> configService;
    @Inject
    Lazy<RecipesAnalyticsHelper> recipesAnalyticsHelper;

    public static class CreateRecipeOption {
        private int description;
        private int drawable;
        private int title;

        public CreateRecipeOption(int i, int i2, int i3) {
            setTitle(i);
            setDescription(i2);
            setDrawable(i3);
        }

        public int getTitle() {
            return this.title;
        }

        public void setTitle(int i) {
            this.title = i;
        }

        public int getDescription() {
            return this.description;
        }

        public void setDescription(int i) {
            this.description = i;
        }

        public int getDrawable() {
            return this.drawable;
        }

        public void setDrawable(int i) {
            this.drawable = i;
        }
    }

    public static CreateRecipeDialogFragment newInstance(String str, Date date, RecipeAnalyticsIntentData recipeAnalyticsIntentData) {
        Bundle bundle = new Bundle();
        bundle.putString(Extras.MEAL_NAME, str);
        bundle.putLong("date", date != null ? date.getTime() : 0);
        bundle.putParcelable(EXTRA_ANALYTICS_INTENT_DATA, recipeAnalyticsIntentData);
        CreateRecipeDialogFragment createRecipeDialogFragment = new CreateRecipeDialogFragment();
        createRecipeDialogFragment.setArguments(bundle);
        return createRecipeDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        String string = BundleUtils.getString(arguments, Extras.MEAL_NAME);
        new Date(BundleUtils.getLong(arguments, "date"));
        RecipeAnalyticsIntentData recipeAnalyticsIntentData = (RecipeAnalyticsIntentData) BundleUtils.getParcelable(arguments, EXTRA_ANALYTICS_INTENT_DATA, RecipeAnalyticsIntentData.class.getClassLoader());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new CreateRecipeOption(R.string.add_from_web, R.string.add_from_web_desc, R.drawable.ic_web_black_24dp));
        arrayList.add(new CreateRecipeOption(R.string.enter_ingredients_manually, R.string.enter_ingredients_manually_desc, R.drawable.ic_smartphone_black_24dp));
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.list_dialog, null);
        ListView listView = (ListView) ViewUtils.findById(inflate, R.id.listViewList);
        listView.setAdapter(new ArrayAdapter<CreateRecipeOption>(getActivity(), R.layout.title_desc_with_left_glyph, arrayList) {
            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = LayoutInflater.from(CreateRecipeDialogFragment.this.getActivity()).inflate(R.layout.title_desc_with_left_glyph, viewGroup, false);
                }
                TextView textView = (TextView) view.findViewById(R.id.desc);
                ImageView imageView = (ImageView) view.findViewById(R.id.glyph);
                CreateRecipeOption createRecipeOption = (CreateRecipeOption) getItem(i);
                ((TextView) view.findViewById(R.id.title)).setText(createRecipeOption.getTitle());
                textView.setText(createRecipeOption.getDescription());
                imageView.setImageResource(createRecipeOption.getDrawable());
                return view;
            }
        });
        MfpAlertDialogBuilder mfpAlertDialogBuilder = new MfpAlertDialogBuilder(getActivity());
        mfpAlertDialogBuilder.setTitle((int) R.string.new_recipe).setView(inflate);
        AlertDialog create = mfpAlertDialogBuilder.create();
        listView.setOnItemClickListener(new OnItemClickListener(recipeAnalyticsIntentData, string, create) {
            private final /* synthetic */ RecipeAnalyticsIntentData f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ AlertDialog f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                CreateRecipeDialogFragment.lambda$onCreateDialog$0(CreateRecipeDialogFragment.this, this.f$1, this.f$2, this.f$3, adapterView, view, i, j);
            }
        });
        return create;
    }

    public static /* synthetic */ void lambda$onCreateDialog$0(CreateRecipeDialogFragment createRecipeDialogFragment, RecipeAnalyticsIntentData recipeAnalyticsIntentData, String str, AlertDialog alertDialog, AdapterView adapterView, View view, int i, long j) {
        String str2;
        switch (i) {
            case 0:
                str2 = "web";
                createRecipeDialogFragment.getNavigationHelper().withIntent(RecipeImportBrowserActivity.newStartIntent(createRecipeDialogFragment.getActivity(), recipeAnalyticsIntentData, str)).startActivity(128);
                break;
            case 1:
                str2 = "manual";
                createRecipeDialogFragment.getNavigationHelper().withIntent(CreateRecipeManuallyActivity.newStartIntent(createRecipeDialogFragment.getActivity(), recipeAnalyticsIntentData, str)).startActivity(128);
                break;
            default:
                str2 = null;
                break;
        }
        if (str2 != null) {
            ((RecipesAnalyticsHelper) createRecipeDialogFragment.recipesAnalyticsHelper.get()).reportImportRecipe(recipeAnalyticsIntentData, str2);
        }
        alertDialog.dismiss();
    }
}
