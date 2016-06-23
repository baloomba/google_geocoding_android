package fr.baloomba.geocoding;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import fr.baloomba.geocoding.helper.JSONHelper;

public class Geometry implements Parcelable {

    // <editor-fold desc="VARIABLES">

	private Location mLocation;
	private String mLocationType;
	private Area mViewport;
	private Area mBounds;

    // </editor-fold>

    // <editor-fold desc="JSON KEYS">

    private static final String LOCATION = "location";
    private static final String LOCATION_TYPE = "location_type";
    private static final String VIEWPORT = "viewport";
    private static final String BOUNDS = "bounds";

    // </editor-fold>

    // <editor-fold desc="CONSTRUCTORS">

    protected Geometry(Parcel in) {
        mLocation = in.readParcelable(Location.class.getClassLoader());
        mLocationType = in.readString();
        mViewport = in.readParcelable(Area.class.getClassLoader());
        mBounds = in.readParcelable(Area.class.getClassLoader());
    }

    protected Geometry(Init<?> builder) {
        mLocation = builder.mLocation;
        mLocationType = builder.mLocationType;
        mViewport = builder.mViewport;
        mBounds = builder.mBounds;
    }

    // </editor-fold>

    //<editor-fold desc="PARCELABLE METHODS IMPLEMENTATION">

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(mLocation, flags);
        parcel.writeString(mLocationType);
        parcel.writeParcelable(mViewport, flags);
        parcel.writeParcelable(mBounds, flags);
    }

    public static final Creator<Geometry> CREATOR =
            new Creator<Geometry>() {
                @Override
                public Geometry createFromParcel(Parcel source) {
                    return new Geometry(source);
                }

                @Override
                public Geometry[] newArray(int size) {
                    return new Geometry[size];
                }
            };

    //</editor-fold>

    // <editor-fold desc="GETTERS">

	public Location getLocation() {
		return mLocation;
	}

    public String getLocationType() {
        return mLocationType;
    }

    public Area getViewport() {
        return mViewport;
    }

    public Area getBounds() {
        return mBounds;
    }

    // </editor-fold>

    // <editor-fold desc="SETTERS">

    public void setLocation(Location location) {
        mLocation = location;
    }

    public void setLocationType(String locationType) {
        mLocationType = locationType;
    }

    public void setViewport(Area viewport) {
        this.mViewport = viewport;
    }

    public void setBounds(Area bounds) {
        mBounds = bounds;
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

        public Geometry fromJSON(JSONObject object) throws JSONException {
            if (object == null)
                return null;
            return new Builder()
                    .setLocation(Location.Factory.getInstance()
                            .fromJSON(JSONHelper.getJSONObject(object, LOCATION)))
                    .setLocationType(JSONHelper.getString(object, LOCATION_TYPE))
                    .setViewport(Area.Factory.getInstance()
                            .fromJSON(JSONHelper.getJSONObject(object, VIEWPORT)))
                    .setBounds(Area.Factory.getInstance()
                            .fromJSON(JSONHelper.getJSONObject(object, BOUNDS)))
                    .build();
        }

        // </editor-fold>

    }

    // </editor-fold>

    // <editor-fold desc="INIT BUILDER CLASS">

    protected static abstract class Init<T extends Init<T>> {

        // <editor-fold desc="VARIABLES">

        private Location mLocation;
        private String mLocationType;
        private Area mViewport;
        private Area mBounds;

        // </editor-fold>

        // <editor-fold desc="CONSTRUCTORS">

        public Init() {
            mLocation = null;
            mLocationType = null;
            mViewport = null;
            mBounds = null;
        }

        // </editor-fold>

        // <editor-fold desc="SETTERS">

        public T setLocation(Location location) {
            mLocation = location;
            return self();
        }

        public T setLocationType(String locationType) {
            mLocationType = locationType;
            return self();
        }

        public T setViewport(Area viewport) {
            mViewport = viewport;
            return self();
        }

        public T setBounds(Area bounds) {
            mBounds = bounds;
            return self();
        }

        // </editor-fold>

        // <editor-fold desc="METHODS">

        protected abstract T self();

        public Geometry build() {
            return new Geometry(this);
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