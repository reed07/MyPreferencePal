package com.myfitnesspal.feature.debug.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.model.ABTestSettings;
import com.myfitnesspal.feature.settings.model.ABTestSettings.ABTestOverrideDesc;
import com.myfitnesspal.feature.settings.model.ABTestSettings.ABTestOverrideDesc.LegalVariant;
import com.myfitnesspal.shared.model.CheckableListItem;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;

public class RolloutDebugActivity extends MfpActivity {
    private static final int MENU_FILTER = 8000;
    @Inject
    ABTestSettings abTestSettings;
    private ListView listView;
    /* access modifiers changed from: private */
    public String query;
    /* access modifiers changed from: private */
    public boolean showOnlyChangedItems;

    private static final class VariantListItem extends CheckableListItem {
        private final LegalVariant variant;

        public VariantListItem(LegalVariant legalVariant, boolean z) {
            super(legalVariant.getDisplayName(), z);
            this.variant = legalVariant;
        }

        public LegalVariant getVariant() {
            return this.variant;
        }
    }

    public boolean shouldDisplayAds() {
        return false;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, RolloutDebugActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.rollout_debug);
        component().inject(this);
        setupList();
    }

    /* access modifiers changed from: private */
    public void setupList() {
        this.listView = (ListView) findById(16908298);
        final AnonymousClass1 r8 = new ReturningFunction1<String, ABTestOverrideDesc>() {
            public String execute(ABTestOverrideDesc aBTestOverrideDesc) {
                return Strings.notEmpty(aBTestOverrideDesc.getPrettyName()) ? aBTestOverrideDesc.getPrettyName() : aBTestOverrideDesc.getName();
            }
        };
        final List list = (List) Enumerable.where((Collection<T>) this.abTestSettings.getOverrides(), (ReturningFunction1<Boolean, T>) new ReturningFunction1<Boolean, ABTestOverrideDesc>() {
            public Boolean execute(ABTestOverrideDesc aBTestOverrideDesc) {
                String name = aBTestOverrideDesc.getName();
                return Boolean.valueOf((!RolloutDebugActivity.this.showOnlyChangedItems || RolloutDebugActivity.this.abTestSettings.shouldOverride(name)) && (Strings.isEmpty(RolloutDebugActivity.this.query) || name.toLowerCase().contains(RolloutDebugActivity.this.query) || ((String) r8.execute(aBTestOverrideDesc)).toLowerCase().contains(RolloutDebugActivity.this.query)));
            }
        });
        Collections.sort(list, new Comparator<ABTestOverrideDesc>() {
            public int compare(ABTestOverrideDesc aBTestOverrideDesc, ABTestOverrideDesc aBTestOverrideDesc2) {
                return ((String) r8.execute(aBTestOverrideDesc)).compareToIgnoreCase((String) r8.execute(aBTestOverrideDesc2));
            }
        });
        AnonymousClass4 r1 = new ArrayAdapter<ABTestOverrideDesc>(this, R.layout.rollout_debug_row, R.id.rollout_name, this.abTestSettings.getOverrides()) {
            public long getItemId(int i) {
                return (long) i;
            }

            public int getCount() {
                return CollectionUtils.size((Collection<?>) list);
            }

            public ABTestOverrideDesc getItem(int i) {
                return (ABTestOverrideDesc) list.get(i);
            }

            /* JADX WARNING: Removed duplicated region for block: B:18:0x00b8  */
            /* JADX WARNING: Removed duplicated region for block: B:19:0x00bc  */
            /* JADX WARNING: Removed duplicated region for block: B:20:0x00bf  */
            /* JADX WARNING: Removed duplicated region for block: B:21:0x00c3  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public android.view.View getView(int r9, android.view.View r10, android.view.ViewGroup r11) {
                /*
                    r8 = this;
                    android.view.View r10 = super.getView(r9, r10, r11)
                    r11 = 2131363504(0x7f0a06b0, float:1.8346819E38)
                    android.view.View r11 = com.uacf.core.util.ViewUtils.findById(r10, r11)
                    android.widget.TextView r11 = (android.widget.TextView) r11
                    r0 = 2131363503(0x7f0a06af, float:1.8346817E38)
                    android.view.View r0 = com.uacf.core.util.ViewUtils.findById(r10, r0)
                    android.widget.TextView r0 = (android.widget.TextView) r0
                    r1 = 2131363508(0x7f0a06b4, float:1.8346827E38)
                    android.view.View r1 = com.uacf.core.util.ViewUtils.findById(r10, r1)
                    android.widget.TextView r1 = (android.widget.TextView) r1
                    r2 = 2131363507(0x7f0a06b3, float:1.8346825E38)
                    android.view.View r2 = com.uacf.core.util.ViewUtils.findById(r10, r2)
                    android.widget.TextView r2 = (android.widget.TextView) r2
                    com.myfitnesspal.feature.settings.model.ABTestSettings$ABTestOverrideDesc r9 = r8.getItem(r9)
                    java.lang.String r3 = r9.getName()
                    com.uacf.core.util.ReturningFunction1 r4 = r8
                    java.lang.Object r4 = r4.execute(r9)
                    java.lang.CharSequence r4 = (java.lang.CharSequence) r4
                    r11.setText(r4)
                    java.lang.StringBuilder r11 = new java.lang.StringBuilder
                    r11.<init>()
                    java.lang.String r4 = "Rollout name: "
                    r11.append(r4)
                    r11.append(r3)
                    java.lang.String r11 = r11.toString()
                    r0.setText(r11)
                    com.myfitnesspal.feature.debug.ui.activity.RolloutDebugActivity r11 = com.myfitnesspal.feature.debug.ui.activity.RolloutDebugActivity.this
                    com.myfitnesspal.feature.settings.model.ABTestSettings r11 = r11.abTestSettings
                    java.lang.String r0 = r9.getName()
                    java.lang.String r4 = "__use__server__"
                    java.lang.String r11 = r11.getVariantOverrideFor(r0, r4)
                    java.util.ArrayList r0 = new java.util.ArrayList
                    r0.<init>()
                    com.uacf.core.util.Tuple2 r0 = com.uacf.core.util.Tuple.create(r11, r0)
                    java.util.ArrayList r4 = new java.util.ArrayList
                    java.util.List r9 = r9.getLegalVariants()
                    r4.<init>(r9)
                    com.myfitnesspal.feature.debug.ui.activity.RolloutDebugActivity$4$1 r9 = new com.myfitnesspal.feature.debug.ui.activity.RolloutDebugActivity$4$1
                    r9.<init>()
                    java.util.Collections.sort(r4, r9)
                    com.myfitnesspal.feature.debug.ui.activity.RolloutDebugActivity$4$2 r9 = new com.myfitnesspal.feature.debug.ui.activity.RolloutDebugActivity$4$2
                    r9.<init>(r11, r0)
                    com.uacf.core.util.Enumerable.forEach(r4, r9)
                    int r9 = r11.hashCode()
                    r5 = 97196323(0x5cb1923, float:1.9099262E-35)
                    r6 = 0
                    if (r9 == r5) goto L_0x00a8
                    r5 = 951543133(0x38b7655d, float:8.7450004E-5)
                    if (r9 == r5) goto L_0x009e
                    r5 = 1568516906(0x5d7dab2a, float:1.14242186E18)
                    if (r9 == r5) goto L_0x0094
                    goto L_0x00b2
                L_0x0094:
                    java.lang.String r9 = "__use__server__"
                    boolean r9 = r11.equals(r9)
                    if (r9 == 0) goto L_0x00b2
                    r9 = 2
                    goto L_0x00b3
                L_0x009e:
                    java.lang.String r9 = "control"
                    boolean r9 = r11.equals(r9)
                    if (r9 == 0) goto L_0x00b2
                    r9 = 1
                    goto L_0x00b3
                L_0x00a8:
                    java.lang.String r9 = "false"
                    boolean r9 = r11.equals(r9)
                    if (r9 == 0) goto L_0x00b2
                    r9 = 0
                    goto L_0x00b3
                L_0x00b2:
                    r9 = -1
                L_0x00b3:
                    r5 = 255(0xff, float:3.57E-43)
                    switch(r9) {
                        case 0: goto L_0x00c3;
                        case 1: goto L_0x00bf;
                        case 2: goto L_0x00bc;
                        default: goto L_0x00b8;
                    }
                L_0x00b8:
                    r9 = 145(0x91, float:2.03E-43)
                    r7 = 0
                    goto L_0x00c7
                L_0x00bc:
                    r9 = 0
                    r7 = 0
                    goto L_0x00c7
                L_0x00bf:
                    r9 = 0
                    r7 = 255(0xff, float:3.57E-43)
                    goto L_0x00c7
                L_0x00c3:
                    r9 = 0
                    r6 = 255(0xff, float:3.57E-43)
                    r7 = 0
                L_0x00c7:
                    int r9 = android.graphics.Color.argb(r5, r6, r9, r7)
                    r2.setTextColor(r9)
                    java.lang.Object r9 = r0.getItem1()
                    java.lang.CharSequence r9 = (java.lang.CharSequence) r9
                    r2.setText(r9)
                    java.lang.StringBuilder r9 = new java.lang.StringBuilder
                    r9.<init>()
                    java.lang.String r2 = "Variants:\n"
                    r9.append(r2)
                    java.lang.String r2 = "\n"
                    java.lang.Object r0 = r0.getItem2()
                    java.util.Collection r0 = (java.util.Collection) r0
                    java.lang.String r0 = com.uacf.core.util.Strings.join(r2, r0)
                    r9.append(r0)
                    java.lang.String r9 = r9.toString()
                    r1.setText(r9)
                    com.myfitnesspal.feature.debug.ui.activity.RolloutDebugActivity$4$3 r9 = new com.myfitnesspal.feature.debug.ui.activity.RolloutDebugActivity$4$3
                    r9.<init>(r4, r11, r3)
                    r10.setOnClickListener(r9)
                    return r10
                */
                throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.debug.ui.activity.RolloutDebugActivity.AnonymousClass4.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
            }
        };
        this.listView.setAdapter(r1);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        getMenuInflater().inflate(R.menu.rollout_debug, menu);
        MenuItem findItem = menu.findItem(R.id.action_search);
        MenuItemCompat.setOnActionExpandListener(findItem, new OnActionExpandListener() {
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }

            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                RolloutDebugActivity.this.query = null;
                RolloutDebugActivity.this.setupList();
                return true;
            }
        });
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(findItem);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Type keyword(s)");
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                RolloutDebugActivity.this.query = str.toLowerCase();
                RolloutDebugActivity.this.setupList();
                return true;
            }

            public boolean onQueryTextChange(String str) {
                RolloutDebugActivity.this.query = str.toLowerCase();
                RolloutDebugActivity.this.setupList();
                return true;
            }
        });
        MenuItemCompat.setShowAsAction(menu.add(0, 8000, 0, this.showOnlyChangedItems ? "Show all rollouts" : "Show changed rollouts only").setIcon(this.showOnlyChangedItems ? R.drawable.ic_filter_on : R.drawable.ic_filter_off), 2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (super.onOptionsItemSelected(menuItem)) {
            return true;
        }
        if (menuItem.getItemId() != 8000) {
            return false;
        }
        this.showOnlyChangedItems = !this.showOnlyChangedItems;
        supportInvalidateOptionsMenu();
        setupList();
        return true;
    }

    /* access modifiers changed from: private */
    public void refreshList() {
        ListViewUtils.notifyDataSetChanged(this.listView);
    }
}
