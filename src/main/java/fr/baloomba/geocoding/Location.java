package fr.baloomba.geocoding;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import fr.baloomba.geocoding.helper.JSONHelper;

public class Location implements Parcelable {

    // <editor-fold desc="VARIABLES">

	private double mLat;
	private double mLng;

    // </editor-fold>

    // <editor-fold desc="JSON KEYS">

    private static final String LAT = "lat";
    private static final String LNG = "lng";

    // </editor-fold>

    // <editor-fold desc="CONSTRUCTORS">

    public Location(double lat, double lng) {
        mLat = lat;
        mLng = lng;
    }

    public Location(LatLng latLng) {
        mLat = latLng.latitude;
        mLng = latLng.longitude;
    }

    protected Location(Parcel in) {
        mLat = in.readDouble();
        mLng = in.readDouble();
    }

    protected Location(Init<?> builder) {
        mLat = builder.mLat;
        mLng = builder.mLng;
    }

    // </editor-fold>

    //<editor-fold desc="PARCELABLE METHODS IMPLEMENTATION">

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeDouble(mLat);
        parcel.writeDouble(mLng);
    }

    public static final Creator<Location> CREATOR =
            new Creator<Location>() {
                @Override
                public Location createFromParcel(Parcel source) {
                    return new Location(source);
                }

                @Override
                public Location[] newArray(int size) {
                    return new Location[size];
                }
            };

    //</editor-fold>

    // <editor-fold desc="OBJECT OVERRIDDEN METHODS">

    @Override
    public String toString() {
        return mLat + "," + mLng;
    }

    // </editor-fold>

    // <editor-fold desc="GETTERS">

    public double getLat() {
        return mLat;
    }

    public double getLng() {
        return mLng;
    }

    // </editor-fold>

    // <editor-fold desc="SETTERS">

    public void setLat(double lat) {
        mLat = lat;
    }

    public void setLng(double lng) {
        mLng = lng;
    }

    // </editor-fold>

    // <editor-fold desc="FACTORY CLASS">

    public static class Factory {

        // <editor-fold desc="VARIABLES">

        private static Factory sInstance = new Factory();

        // </editor-fold>

        // <editor-fold desc="INSTANCE">

        public static Factory getInstance() {
            return sInstance;
        }

        // </editor-fold>

        // <editor-fold desc="METHODS">

        public Location fromJSON(JSONObject object) throws JSONException {
            if (object == null)
                return null;
            return new Builder()
                    .setLat(JSONHelper.getDouble(object, LAT))
                    .setLng(JSONHelper.getDouble(object, LNG))
                    .build();
        }

        // </editor-fold>

    }

    // </editor-fold>

    // <editor-fold desc="INIT BUILDER CLASS">

    protected static abstract class Init<T extends Init<T>> {

        // <editor-fold desc="VARIABLES">

        private double mLat;
        private double mLng;

        // </editor-fold>

        // <editor-fold desc="CONSTRUCTORS">

        public Init() {
            mLat = 0;
            mLng = 0;
        }

        // </editor-fold>

        // <editor-fold desc="SETTERS">

        public T setLat(double lat) {
            mLat = lat;
            return self();
        }

        public T setLng(double lng) {
            mLng = lng;
            return self();
        }

        // </editor-fold>

        // <editor-fold desc="METHODS">

        protected abstract T self();

        public Location build() {
            return new Location(this);
        }

        // </editor-fold>

    }

    // </editor-fold>

    // <editor-fold desc="BUILDER CLASS">

    public static class Builder extends Init<Builder> {

        // <editor-fold desc="CONSTRUCTORS">

        public Builder() {
            super();
        }

        // </editor-fold>

        // <editor-fold desc="OVERRIDDEN INIT<BUILDER> METHODS">

        @Override
        protected Builder self() {
            return this;
        }

        // </editor-fold>

    }

    // </editor-fold>

}