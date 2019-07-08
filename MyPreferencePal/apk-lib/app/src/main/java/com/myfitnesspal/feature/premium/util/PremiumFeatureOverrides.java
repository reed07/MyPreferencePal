package com.myfitnesspal.feature.premium.util;

import lanchon.dexpatcher.annotation.*;

@DexEdit(defaultAction = DexAction.IGNORE)
public class PremiumFeatureOverrides
{
	@DexReplace
	public boolean areOverridesEnabled()
	{
		return true;
	}
	
	@DexReplace
	private boolean getStateValueFor(final String s) {
        return true;
    }
}