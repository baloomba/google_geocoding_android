package fr.baloomba.geocoding;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.baloomba.geocoding.helper.ComponentHelper;
import fr.baloomba.geocoding.helper.GeocodingSettings;
import fr.baloomba.geocoding.helper.JSONHelper;

import fr.baloomba.wsvolley.WSManager;
import fr.baloomba.wsvolley.WSMethod;
import fr.baloomba.wsvolley.WSRequest;
import fr.baloomba.wsvolley.WSStringResponseListener;

public class Geocoding {

    private static final String TAG = Geocoding.class.getSimpleName();

    public static class RequestBuilder {

        // <editor-fold desc="GLOBAL VARIABLES">

        private static final int OUTPUT_JSON = 0;
        private static final int OUTPUT_XML = 1;

        private static final String PARAM_ADDRESS = "address";
        private static final String PARAM_COMPONENTS = "components";
        private static final String PARAM_BOUNDS = "bounds";
        private static final String PARAM_KEY = "key";
        private static final String PARAM_LANGUAGE = "language";
        private static final String PARAM_REGION = "region";

        private static final String URL_BASE = "https://maps.googleapis.com/maps/api/geocode";
        private static final String URL_JSON = "/json";
        private static final String URL_XML = "/xml";

        // </editor-fold>

        // <editor-fold desc="JSON KEYS">

        private static final String STATUS = "status";
        private static final String RESULTS = "results";

        // </editor-fold>

        // <editor-fold desc="VARIABLES">

        private String mAddress;
        private ArrayList<Component> mComponents;
        private Area mBounds;
        private String mLanguage;
        private String mRegion;

        private int mOutput;

        // </editor-fold>

        // <editor-fold desc="CONSTRUCTORS">

        public RequestBuilder() {
            mAddress = null;
            mComponents = null;
            mBounds = null;
            mLanguage = null;
            mRegion = null;
        }

        // </editor-fold>

        // <editor-fold desc="SETTERS">

        public RequestBuilder setAddress(String address) {
            mAddress = address;
            return this;
        }

        public RequestBuilder setBounds(Area bounds) {
            mBounds = bounds;
            return this;
        }

        public RequestBuilder setBounds(Location southwest, Location northeast) {
            mBounds = new Area(southwest, northeast);
            return this;
        }

        public RequestBuilder setBounds(double latSW, double lngSW, double latNE, double lngNE) {
            mBounds = new Area(new Location(latSW, lngSW), new Location(latNE, lngNE));
            return this;
        }

        public RequestBuilder setLanguage(String language) {
            mLanguage = language;
            return this;
        }

        public RequestBuilder setRegion(String region) {
            mRegion = region;
            return this;
        }

        public RequestBuilder setOutputJSON() {
            mOutput = OUTPUT_JSON;
            return this;
        }

        public RequestBuilder setOutputXML() {
            mOutput = OUTPUT_XML;
            return this;
        }

        public RequestBuilder addComponent(Component component) {
            if (mComponents == null)
                mComponents = new ArrayList<>();
            mComponents.add(component);
            return this;
        }

        // </editor-fold>

        // <editor-fold desc="METHODS">

        public void send(Context context, final GeocodingCallback callback) {
            if ((mAddress == null || mAddress.isEmpty()) && (mComponents == null || mComponents.isEmpty())) {
                callback.statusInvalidRequest();
                return;
            }
            String url = URL_BASE;
            if (mOutput == OUTPUT_JSON) {
                url += URL_JSON;
            } else {
                url += URL_XML;
            }
            WSRequest.Builder builder = new WSRequest.Builder(WSMethod.GET, url, "geocoding");
            if (mAddress != null && !mAddress.isEmpty())
                builder = builder.addParam(PARAM_ADDRESS, mAddress);
            if (mBounds != null)
                builder = builder.addParam(PARAM_BOUNDS, mBounds.toString());
            if (mLanguage != null && !mLanguage.isEmpty())
                builder = builder.addParam(PARAM_LANGUAGE, mLanguage);
            if (mRegion != null && !mRegion.isEmpty())
                builder = builder.addParam(PARAM_REGION, mRegion);
            if (mComponents != null && !mComponents.isEmpty())
                builder = builder.addParam(PARAM_COMPONENTS,
                        ComponentHelper.concatenate(mComponents));
            builder = builder.addParam(PARAM_KEY, GeocodingSettings.getGoogleMapApiKey());
            builder = builder.setShouldCache(true)
                    .setListener(new WSStringResponseListener() {
                        @Override
                        public void onResponse(String s) {
                            Log.d(TAG, "send.onResponse:"+s);
                            try {
                                JSONObject object = new JSONObject(s);
                                String status = JSONHelper.getString(object, STATUS);
                                switch (status) {
                                    case Status.OK:
                                        ArrayList<Geocode> results = new ArrayList<>();
                                        JSONArray array = JSONHelper.getJSONArray(object, RESULTS);
                                        for (int i = 0 ; i > array.length(); i++) {
                                            results.add(Geocode.Factory.getInstance().fromJSON(array.getJSONObject(i)));
                                        }
                                        callback.statusOk(results);
                                        break;
                                    case Status.ZERO_RESULTS:
                                        callback.statusZeroResults();
                                        break;
                                    case Status.OVER_QUERY_LIMIT:
                                        callback.statusOverQueryLimit();
                                        break;
                                    case Status.REQUEST_DENIED:
                                        callback.statusRequestDenied();
                                        break;
                                    case Status.INVALID_REQUEST:
                                        callback.statusInvalidRequest();
                                        break;
                                    case Status.UNKNOWN_ERROR:
                                    default:
                                        callback.statusError();
                                        break;
                                }
                            } catch (JSONException e) {
                                callback.statusError();
                            }
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Log.e(TAG, "send.onErrorResponse:" + volleyError.getMessage());
                            callback.statusError();
                        }
                    });
            WSManager.getInstance(context.getApplicationContext()).send(builder.build());
        }

        // </editor-fold>

    }

}
