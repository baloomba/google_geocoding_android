package fr.baloomba.geocoding;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLngBounds;

import org.json.JSONException;
import org.json.JSONObject;

import fr.baloomba.geocoding.helper.JSONHelper;

public class Area implements Parcelable {

    // <editor-fold desc="VARIABLES">

    private Location mSouthwest;
	private Location mNortheast;

    // </editor-fold>

    // <editor-fold desc="JSON KEYS">

    private static final String SOUTHWEST = "southwest";
    private static final String NORTHEAST = "northeast";

    // </editor-fold>

    // <editor-fold desc="CONSTRUCTORS">

    public Area(LatLngBounds latLngBounds) {
        mSouthwest = new Location(latLngBounds.southwest);
        mNortheast = new Location(latLngBounds.northeast);
    }

    public Area(Location southwest, Location northeast) {
        mSouthwest = southwest;
        mNortheast = northeast;
    }

    protected Area(Parcel in) {
        mSouthwest = in.readParcelable(Location.class.getClassLoader());
        mNortheast = in.readParcelable(Location.class.getClassLoader());
    }

    protected Area(Init<?> builder) {
        mSouthwest = builder.mSouthwest;
        mNortheast = builder.mNortheast;
    }

    // </editor-fold>

    //<editor-fold desc="PARCELABLE METHODS IMPLEMENTATION">

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(mSouthwest, flags);
        parcel.writeParcelable(mNortheast, flags);
    }

    public static final Creator<Area> CREATOR =
            new Creator<Area>() {
                @Override
                public Area createFromParcel(Parcel source) {
                    return new Area(source);
                }

                @Override
                public Area[] newArray(int size) {
                    return new Area[size];
                }
            };

    //</editor-fold>

    // <editor-fold desc="GETTERS">

    public Location getSouthWest() {
		return mSouthwest;
	}

    public Location getNorthEast() {
        return mNortheast;
    }

    // </editor-fold>

    // <editor-fold desc="SETTERS">

    public void setSouthWest(Location southWest) {
		this.mSouthwest = southWest;
	}

	public void setNorthEast(Location northEast) {
		this.mNortheast = northEast;
	}

    // </editor-fold>

    // <editor-fold desc="OBJECT OVERRIDDEN METHODS">

    @Override
    public String toString() {
        return mSouthwest.toString() + "|" + mNortheast.toString();
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

        public Area fromJSON(JSONObject object) throws JSONException {
            if (object == null)
                return null;
            return new Builder()
                    .setSouthwest(Location.Factory.getInstance()
                            .fromJSON(JSONHelper.getJSONObject(object, SOUTHWEST)))
                    .setNortheast(Location.Factory.getInstance()
                            .fromJSON(JSONHelper.getJSONObject(object, NORTHEAST)))
                    .build();
        }

        // </editor-fold>

    }

    // </editor-fold>

    // <editor-fold desc="INIT BUILDER CLASS">

    protected static abstract class Init<T extends Init<T>> {

        // <editor-fold desc="VARIABLES">

        private Location mSouthwest;
        private Location mNortheast;

        // </editor-fold>

        // <editor-fold desc="CONSTRUCTORS">

        public Init() {
            mSouthwest = null;
            mNortheast = null;
        }

        // </editor-fold>

        // <editor-fold desc="SETTERS">

        public T setSouthwest(Location southwest) {
            mSouthwest = southwest;
            return self();
        }

        public T setNortheast(Location northeast) {
            mNortheast = northeast;
            return self();
        }

        // </editor-fold>

        // <editor-fold desc="METHODS">

        protected abstract T self();

        public Area build() {
            return new Area(this);
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