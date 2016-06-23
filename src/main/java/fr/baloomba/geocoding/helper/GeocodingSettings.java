package fr.baloomba.geocoding.helper;

import android.content.Context;

public class GeocodingSettings {

    // <editor-fold desc="VARIABLES">

    private static final String TAG = GeocodingSettings.class.getSimpleName();

    private static final String GOOGLE_MAP_API_KEY = "fr.baloomba.geocoding.google_map_api_key";

    private static GeocodingSettings sInstance;

    private Context mContext;

    private static String sGoogleMapAPIKey;

    // </editor-fold>

    // <editor-fold desc="INSTANCE">

    public static GeocodingSettings getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new GeocodingSettings(context);
        }
        return sInstance;
    }

    // </editor-fold>

    // <editor-fold desc="CONSTRUCTORS">

    public GeocodingSettings(Context context) {
        mContext = context;
    }

    // </editor-fold>

    // <editor-fold desc="GETTERS">

    public static String getGoogleMapApiKey() {
        return sGoogleMapAPIKey;
    }

    // </editor-fold>

    // <editor-fold desc="METHODS">

    public void init() {
        sGoogleMapAPIKey = (String)MetaDataHelper.retrieveData(mContext, GOOGLE_MAP_API_KEY);
    }

    // </editor-fold>

}
