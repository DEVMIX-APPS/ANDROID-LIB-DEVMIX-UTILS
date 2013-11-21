package com.orasystems.libs.utils;

import com.actionbarsherlock.app.ActionBar;

public class ActionBarSherlockUtils {
	public static void showStandardNav(ActionBar ab) {
        if (ab.getNavigationMode() != ActionBar.NAVIGATION_MODE_STANDARD) {
            ab.setDisplayShowTitleEnabled(true);
            ab.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        }
    }

    public static void showDropDownNav(ActionBar ab) {
        if (ab.getNavigationMode() != ActionBar.NAVIGATION_MODE_LIST) {
            ab.setDisplayShowTitleEnabled(false);
            ab.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        }
    }

    public static void showTabsNav(ActionBar ab) {
        if (ab.getNavigationMode() != ActionBar.NAVIGATION_MODE_TABS) {
            ab.setDisplayShowTitleEnabled(false);
            ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        }
    }
    public static void setSubTitle(ActionBar ab,String subTitle){
    	ab.setSubtitle(subTitle);
    }
    public static void setTitle(ActionBar ab,String title){

    }
    public static void showTitle(ActionBar ab,boolean showHide){
    	ab.setDisplayShowTitleEnabled(showHide);
    }
    public static void hideActionBar(ActionBar ab){
    	ab.hide();
    }
    public static void showActionBar(ActionBar ab){
    	ab.show();
    }
    public static void hideLogo(ActionBar ab){
    	ab.setDisplayUseLogoEnabled(false);
    }
    public static void showLogo(ActionBar ab){
    	ab.setDisplayUseLogoEnabled(true);
    }
    public static void hideHome(ActionBar ab){
    	ab.setDisplayShowHomeEnabled(false);
    }
    public static void showHome(ActionBar ab){
    	ab.setDisplayShowHomeEnabled(true);
    }
    public static void showBack(ActionBar ab){
    	ab.setDisplayHomeAsUpEnabled(true);
    }
    public static void hideBack(ActionBar ab){
    	ab.setDisplayHomeAsUpEnabled(false);
    }
}
