package com.elcomercio.ga_and_gtm.util;

import android.content.Context;

import com.google.android.gms.tagmanager.DataLayer;
import com.google.android.gms.tagmanager.TagManager;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 29/03/17.
 *
 *
 */

public class TrackUtils {

    public static void pushOpenScreenEventAndScreenNameToDataLayer1(Context context, String screenName){
        DataLayer dataLayer = TagManager.getInstance(context).getDataLayer();
        dataLayer.push(DataLayer.mapOf("event", "openScreen", "screenName", screenName));
    }

    public static void pushOpenScreenEventAndScreenNameToDataLayer2(Context context, String screenName){
        DataLayer dataLayer = TagManager.getInstance(context).getDataLayer();
        dataLayer.pushEvent("openScreen", DataLayer.mapOf(screenName, screenName));
    }

    public static void pushEventAndVariableValuesToDataLayer(Context context, String categoryName, String actionName, String labelName, String screenName){
        DataLayer dataLayer = TagManager.getInstance(context).getDataLayer();
        dataLayer.pushEvent("clickButton",
                DataLayer.mapOf(
                        "buttonCategoryName",categoryName,
                        "buttonActionName",actionName,
                        "buttonLabelName",labelName,
                        "screenName",screenName));
    }

}
