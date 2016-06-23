package fr.baloomba.geocoding;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import fr.baloomba.geocoding.helper.JSONHelper;

public class AddressComponent implements Parcelable {

    // <editor-fold desc="VARIABLES">

    private String mLongName;
	private String mShortName;
	private Collection<Type> mTypes = new ArrayList<>();

    // </editor-fold>

    // <editor-fold desc="JSON KEYS">

    private static final String LONG_NAME = "long_name";
    private static final String SHORT_NAME = "short_name";
    private static final String TYPES = "types";

    // </editor-fold>

    // <editor-fold desc="CONSTRUCTORS">

    protected AddressComponent(Parcel in) {
        mLongName = in.readString();
        mShortName = in.readString();
        int count = in.readInt();
        mTypes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            mTypes.add(Type.Factory.getInstance().typeFromString(in.readString()));
        }
    }

    protected AddressComponent(Init<?> builder) {
        mLongName = builder.mLongName;
        mShortName = builder.mShortName;
        mTypes = builder.mTypes;
    }

    // </editor-fold>

    //<editor-fold desc="PARCELABLE METHODS IMPLEMENTATION">

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mLongName);
        parcel.writeString(mShortName);
        if (mTypes != null) {
            parcel.writeInt(mTypes.size());
            for (Type type : mTypes) {
                parcel.writeString(type.getValue());
            }
        } else {
            parcel.writeInt(0);
        }
    }

    public static final Creator<AddressComponent> CREATOR =
            new Creator<AddressComponent>() {
                @Override
                public AddressComponent createFromParcel(Parcel source) {
                    return new AddressComponent(source);
                }

                @Override
                public AddressComponent[] newArray(int size) {
                    return new AddressComponent[size];
                }
            };

    //</editor-fold>

    // <editor-fold desc="GETTERS">

    public String getLongName() {
        return mLongName;
    }

    public String getShortName() {
        return mShortName;
    }

    public Collection<Type> getTypes() {
        return mTypes;
    }

    // </editor-fold>

    // <editor-fold desc="SETTERS">

    public void setLongName(String longName) {
        mLongName = longName;
    }

    public void setShortName(String shortName) {
        mShortName = shortName;
    }

    public void setTypes(Collection<Type> types) {
        mTypes = types;
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

        public AddressComponent fromJSON(JSONObject object) throws JSONException {
            if (object == null)
                return null;
            return new Builder()
                    .setLongName(JSONHelper.getString(object, LONG_NAME))
                    .setShortName(JSONHelper.getString(object, SHORT_NAME))
                    .setTypes(JSONHelper.getStringArray(object, TYPES))
                    .build();
        }

        // </editor-fold>

    }

    // </editor-fold>

    // <editor-fold desc="INIT BUILDER CLASS">

    protected static abstract class Init<T extends Init<T>> {

        // <editor-fold desc="VARIABLES">

        private String mLongName;
        private String mShortName;
        private Collection<Type> mTypes = new ArrayList<>();

        // </editor-fold>

        // <editor-fold desc="CONSTRUCTORS">

        public Init() {
            mLongName = null;
            mShortName = null;
            mTypes = new ArrayList<>();
        }

        // </editor-fold>

        // <editor-fold desc="SETTERS">

        public T setLongName(String longName) {
            mLongName = longName;
            return self();
        }

        public T setShortName(String shortName) {
            mShortName = shortName;
            return self();
        }

        public T setTypes(Collection<String> types) {
            if (mTypes == null)
                mTypes = new ArrayList<>();
            else
                mTypes.clear();
            for (String type : types) {
                mTypes.add(Type.Factory.getInstance().typeFromString(type));
            }
            return self();
        }

        public T addType(String type) {
            if (mTypes == null)
                mTypes = new ArrayList<>();
            mTypes.add(Type.Factory.getInstance().typeFromString(type));
            return self();
        }

        // </editor-fold>

        // <editor-fold desc="METHODS">

        protected abstract T self();

        public AddressComponent build() {
            return new AddressComponent(this);
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