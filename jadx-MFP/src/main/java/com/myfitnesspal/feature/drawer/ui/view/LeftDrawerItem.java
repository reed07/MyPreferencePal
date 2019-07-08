package com.myfitnesspal.feature.drawer.ui.view;

import android.support.annotation.IdRes;
import java.util.HashSet;
import java.util.Set;

class LeftDrawerItem {
    private int badgeId = -1;
    private Class<?> classToMatch;
    private int iconId;
    private Set<Integer> navigationIdsToMatch = new HashSet();
    private int notificationCount;
    private int titleId;
    private LeftDrawerItemType type;

    LeftDrawerItem() {
    }

    public int getTitleId() {
        return this.titleId;
    }

    public LeftDrawerItem withTitleId(int i) {
        this.titleId = i;
        return this;
    }

    public int getIconId() {
        return this.iconId;
    }

    public LeftDrawerItem withIconId(int i) {
        this.iconId = i;
        return this;
    }

    public LeftDrawerItem withBadgeId(int i) {
        this.badgeId = i;
        return this;
    }

    public LeftDrawerItemType getType() {
        return this.type;
    }

    public LeftDrawerItem withType(LeftDrawerItemType leftDrawerItemType) {
        this.type = leftDrawerItemType;
        return this;
    }

    @Deprecated
    public LeftDrawerItem withMatchClass(Class<?> cls) {
        this.classToMatch = cls;
        return this;
    }

    @Deprecated
    public boolean matchesClass(Object obj) {
        Class<?> cls = this.classToMatch;
        return (cls == null || obj == null || !cls.equals(obj.getClass())) ? false : true;
    }

    public LeftDrawerItem setNotificationCount(int i) {
        this.notificationCount = i;
        return this;
    }

    public int getNotificationCount() {
        return this.notificationCount;
    }

    public int getBadgeId() {
        return this.badgeId;
    }

    /* access modifiers changed from: 0000 */
    public LeftDrawerItem withMatchingNavigationIds(@IdRes int... iArr) {
        for (int valueOf : iArr) {
            this.navigationIdsToMatch.add(Integer.valueOf(valueOf));
        }
        return this;
    }

    /* access modifiers changed from: 0000 */
    public boolean matchesNavigationId(@IdRes int i) {
        return this.navigationIdsToMatch.contains(Integer.valueOf(i));
    }
}
