package com.myfitnesspal.feature.recipes.service;

import com.myfitnesspal.feature.recipes.api.CreateRecipeRequest;
import com.myfitnesspal.feature.recipes.api.RecipeIngredientMatchResult;
import com.myfitnesspal.feature.recipes.api.RecipeMatchRequest;
import com.myfitnesspal.feature.recipes.api.RecipeParseRequest;
import com.myfitnesspal.feature.recipes.api.RecipeParseResult;
import com.myfitnesspal.feature.recipes.api.RecipeParseResult.API_RESPONSE_MAPPER;
import com.myfitnesspal.feature.recipes.model.MfpRecipeListContainer;
import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiRequest;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.myfitnesspal.shared.api.MfpApiUtil;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.db.adapter.FoodDBAdapter;
import com.myfitnesspal.shared.db.adapter.RecipePropertiesDBAdapter;
import com.myfitnesspal.shared.db.table.RecipesTable;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.RecipeBoxItem;
import com.myfitnesspal.shared.model.v1.RecipeFood;
import com.myfitnesspal.shared.model.v2.MfpCorrelationIdDetails;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpIngredient;
import com.myfitnesspal.shared.model.v2.MfpIngredientItem;
import com.myfitnesspal.shared.model.v2.MfpNormalizedData;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.myfitnesspal.shared.service.id.IdService;
import com.myfitnesspal.shared.service.id.IdService.Scope;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.caching.Cache;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;

public class RecipeServiceImpl extends SimpleAsyncServiceBase implements RecipeService {
    private static final int MAX_RECIPES_PER_PAGE = 20;
    /* access modifiers changed from: private */
    public final Provider<MfpV2Api> api;
    /* access modifiers changed from: private */
    public final Cache<MfpRecipeListContainer> cache;
    private final Lazy<CountryService> countryService;
    private final FoodDBAdapter foodDBAdapter;
    private final Lazy<IdService> idService;
    private final RecipePropertiesDBAdapter recipePropertiesDBAdapter;
    private final Lazy<RecipesTable> recipesTable;
    private final Lazy<Session> session;
    private final Lazy<SyncService> syncService;

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 5;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return "RecipeServiceImpl";
    }

    @Inject
    public RecipeServiceImpl(RecipePropertiesDBAdapter recipePropertiesDBAdapter2, FoodDBAdapter foodDBAdapter2, Provider<MfpV2Api> provider, Lazy<IdService> lazy, Lazy<CountryService> lazy2, Cache<MfpRecipeListContainer> cache2, Lazy<RecipesTable> lazy3, Lazy<SyncService> lazy4, Lazy<Session> lazy5) {
        this.recipePropertiesDBAdapter = recipePropertiesDBAdapter2;
        this.foodDBAdapter = foodDBAdapter2;
        this.api = provider;
        this.idService = lazy;
        this.countryService = lazy2;
        this.cache = cache2;
        this.recipesTable = lazy3;
        this.syncService = lazy4;
        this.session = lazy5;
    }

    public RecipeFood getRecipeFoodForV2RecipeId(String str) {
        long findRecipeFoodIdForV2Recipe = this.recipePropertiesDBAdapter.findRecipeFoodIdForV2Recipe(str);
        RecipeFood recipeFood = null;
        if (findRecipeFoodIdForV2Recipe <= 0) {
            return null;
        }
        FoodDBAdapter foodDBAdapter2 = this.foodDBAdapter;
        Food fetchLatestVersionOfFoodForOriginalId = foodDBAdapter2.fetchLatestVersionOfFoodForOriginalId(foodDBAdapter2.lookupOriginalFoodIdFromLocalId(findRecipeFoodIdForV2Recipe));
        if (fetchLatestVersionOfFoodForOriginalId instanceof RecipeFood) {
            recipeFood = (RecipeFood) fetchLatestVersionOfFoodForOriginalId;
        }
        return recipeFood;
    }

    public RecipeParseResult parseRecipe(String str) throws ApiException {
        return (RecipeParseResult) Enumerable.firstOrDefault(((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.api.get()).withOutputType(API_RESPONSE_MAPPER.class)).withJsonBody(new ApiRequest(Collections.singletonList(new RecipeParseRequest(str))))).post(Uri.RECIPE_PARSE, new Object[0])).getItems());
    }

    public List<MfpIngredientItem> matchIngredients(RecipeMatchRequest recipeMatchRequest) throws ApiException {
        return ((RecipeIngredientMatchResult) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.api.get()).withOutputType(RecipeIngredientMatchResult.class)).withJsonBody(recipeMatchRequest)).post(Uri.RECIPE_MATCH_INGREDIENTS, new Object[0])).getItems();
    }

    public MfpRecipe createRecipe(MfpRecipe mfpRecipe) throws ApiException {
        CreateRecipeRequest createRecipeRequest = new CreateRecipeRequest();
        createRecipeRequest.setItems(Collections.singletonList(sanitizeRecipe(mfpRecipe)));
        MfpRecipe mfpRecipe2 = (MfpRecipe) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.api.get()).withOutputType(MfpRecipe.API_RESPONSE_MAPPER.class)).withJsonBody(createRecipeRequest)).post(Uri.RECIPES, new Object[0])).getItems().get(0);
        addRecipeToTopOfCache(mfpRecipe2);
        return mfpRecipe2;
    }

    public void getRecipesAsync(final String str, final Function1<MfpRecipeListContainer> function1, final MfpV2ApiErrorCallback mfpV2ApiErrorCallback) {
        auto(new Runnable() {
            public void run() {
                final MfpRecipeListContainer access$000 = RecipeServiceImpl.this.getCachedRecipes();
                if (access$000 != null) {
                    RecipeServiceImpl.this.postToMainThread(function1, access$000);
                }
                ((MfpV2Api) ((MfpV2Api) RecipeServiceImpl.this.api.get()).withOutputType(MfpRecipe.API_RESPONSE_MAPPER.class)).getAsync(Strings.notEmpty(str) ? str : Uri.RECIPES, (Function2<T, Map<String, List<String>>>) new Function2<ApiResponse<MfpRecipe>, Map<String, List<String>>>() {
                    public void execute(ApiResponse<MfpRecipe> apiResponse, Map<String, List<String>> map) {
                        MfpRecipeListContainer mfpRecipeListContainer;
                        List<MfpRecipe> items = apiResponse.getItems() != null ? apiResponse.getItems() : new ArrayList<>();
                        String nextUrlFromLink = MfpApiUtil.getNextUrlFromLink(Strings.toString(map.get("link")));
                        MfpRecipeListContainer mfpRecipeListContainer2 = access$000;
                        if (mfpRecipeListContainer2 != null) {
                            List recipes = mfpRecipeListContainer2.getRecipes();
                            for (MfpRecipe mfpRecipe : items) {
                                int access$100 = RecipeServiceImpl.this.findRecipeIndex(recipes, mfpRecipe.getId());
                                if (access$100 >= 0) {
                                    recipes.set(access$100, mfpRecipe);
                                } else {
                                    recipes.add(mfpRecipe);
                                }
                            }
                            access$000.setLink(nextUrlFromLink);
                            mfpRecipeListContainer = access$000;
                        } else {
                            mfpRecipeListContainer = new MfpRecipeListContainer(items, nextUrlFromLink);
                        }
                        RecipeServiceImpl.this.cache.put(RecipeServiceImpl.this.getCacheKey(), mfpRecipeListContainer);
                        FunctionUtils.invokeIfValid(function1, mfpRecipeListContainer);
                    }
                }, (ApiErrorCallback) new ApiErrorCallback() {
                    public void execute(ApiException apiException) {
                        Ln.e(apiException);
                        FunctionUtils.invokeIfValid(mfpV2ApiErrorCallback, MfpApiUtil.mapException(apiException));
                    }
                }, Strings.notEmpty(str) ? null : new Object[]{"max_items", Integer.valueOf(20)});
            }
        });
    }

    public List<MfpRecipe> deleteRecipesAndReturnFailedRecipes(List<MfpRecipe> list) {
        ArrayList arrayList = new ArrayList();
        for (MfpRecipe mfpRecipe : list) {
            try {
                String id = mfpRecipe.getId();
                ((MfpV2Api) ((MfpV2Api) this.api.get()).withOutputType(ApiResponseBase.class)).delete(String.format(Uri.SINGLE_RECIPE, new Object[]{Strings.toString(id)}), "version", Strings.toString(mfpRecipe.getVersion()));
                this.foodDBAdapter.deleteFood(getRecipeFoodForV2RecipeId(id), false, true);
                removeRecipeFromCache(id);
            } catch (ApiException unused) {
                arrayList.add(mfpRecipe);
            }
        }
        return arrayList;
    }

    public MfpRecipe editRecipe(MfpRecipe mfpRecipe) throws ApiException {
        MfpRecipe mfpRecipe2 = (MfpRecipe) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.api.get()).withOutputType(MfpRecipe.API_RESPONSE_MAPPER.class)).withJsonBody(new ApiRequest(sanitizeRecipe(mfpRecipe)))).put(String.format(Uri.SINGLE_RECIPE, new Object[]{mfpRecipe.getId()}))).getItem();
        removeRecipeFromCache(mfpRecipe.getId());
        addRecipeToTopOfCache(mfpRecipe2);
        return mfpRecipe2;
    }

    public boolean isRecipeParsingEnabledForCurrentLocale() {
        return ((CountryService) this.countryService.get()).isEnglishCurrentDialect();
    }

    public MfpRecipe getRecipeForRecipeBoxItem(RecipeBoxItem recipeBoxItem) throws ApiException {
        long recipeFoodId = recipeBoxItem.getRecipeFoodId();
        MfpRecipe recipeForFoodId = ((RecipesTable) this.recipesTable.get()).getRecipeForFoodId(recipeBoxItem.getRecipeFoodId());
        if (recipeForFoodId != null) {
            return recipeForFoodId;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MfpCorrelationIdDetails(Strings.toString(Long.valueOf(recipeBoxItem.getMasterDatabaseId())), Scope.RECIPE_FROM_RECIPE_BOX_ITEM));
        List v2IdsFromV1Ids = ((IdService) this.idService.get()).getV2IdsFromV1Ids(arrayList);
        if (CollectionUtils.isEmpty((Collection<?>) v2IdsFromV1Ids)) {
            return null;
        }
        MfpCorrelationIdDetails mfpCorrelationIdDetails = (MfpCorrelationIdDetails) v2IdsFromV1Ids.get(0);
        MfpRecipe mfpRecipe = (MfpRecipe) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) this.api.get()).withOutputType(MfpRecipe.API_RESPONSE_MAPPER.class)).get(String.format(Uri.SINGLE_RECIPE, new Object[]{mfpCorrelationIdDetails.getId()}))).getItem();
        if (mfpRecipe != null) {
            ((RecipesTable) this.recipesTable.get()).insertRecipe(mfpRecipe, recipeFoodId);
        }
        return mfpRecipe;
    }

    /* access modifiers changed from: private */
    public String getCacheKey() {
        return String.format("RECIPES::%s", new Object[]{((Session) this.session.get()).getUser().getUserId()});
    }

    /* access modifiers changed from: private */
    public MfpRecipeListContainer getCachedRecipes() {
        return (MfpRecipeListContainer) this.cache.get(getCacheKey());
    }

    private MfpRecipe sanitizeRecipe(MfpRecipe mfpRecipe) {
        MfpRecipe mfpRecipe2 = (MfpRecipe) ParcelableUtil.clone(mfpRecipe, MfpRecipe.CREATOR);
        for (MfpIngredient mfpIngredient : mfpRecipe2.getIngredients()) {
            if (mfpIngredient.getNormalizedData() == null) {
                mfpIngredient.setNormalizedData(new MfpNormalizedData());
            }
            MfpFood mfpFood = new MfpFood();
            MfpFood food = mfpIngredient.getFood();
            mfpFood.setId(food.getId());
            mfpFood.setVersion(food.getVersion());
            mfpIngredient.setFood(mfpFood);
        }
        mfpRecipe2.setNutritionalContents(null);
        return mfpRecipe2;
    }

    private void addRecipeToTopOfCache(MfpRecipe mfpRecipe) {
        MfpRecipeListContainer cachedRecipes = getCachedRecipes();
        if (cachedRecipes != null) {
            cachedRecipes.getRecipes().add(0, mfpRecipe);
            this.cache.put(getCacheKey(), cachedRecipes);
        }
    }

    private MfpRecipe findRecipe(List<MfpRecipe> list, final String str) {
        if (CollectionUtils.isEmpty((Collection<?>) list) || Strings.isEmpty(str)) {
            return null;
        }
        return (MfpRecipe) Enumerable.firstOrDefault(list, new ReturningFunction1<Boolean, MfpRecipe>() {
            public Boolean execute(MfpRecipe mfpRecipe) {
                return Boolean.valueOf(Strings.equals(mfpRecipe.getId(), str));
            }
        });
    }

    private void removeRecipeFromCache(String str) {
        MfpRecipeListContainer cachedRecipes = getCachedRecipes();
        if (cachedRecipes != null) {
            List recipes = cachedRecipes.getRecipes();
            MfpRecipe findRecipe = findRecipe(recipes, str);
            if (findRecipe != null) {
                recipes.remove(findRecipe);
                this.cache.put(getCacheKey(), cachedRecipes);
            }
        }
    }

    /* access modifiers changed from: private */
    public int findRecipeIndex(List<MfpRecipe> list, final String str) {
        if (CollectionUtils.isEmpty((Collection<?>) list) || Strings.isEmpty(str)) {
            return -1;
        }
        return Enumerable.indexOf((Collection<T>) list, (ReturningFunction1<Boolean, T>) new ReturningFunction1<Boolean, MfpRecipe>() {
            public Boolean execute(MfpRecipe mfpRecipe) {
                return Boolean.valueOf(Strings.equals(mfpRecipe.getId(), str));
            }
        });
    }
}
