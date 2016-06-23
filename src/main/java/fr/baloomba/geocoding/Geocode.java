package fr.baloomba.geocoding;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class Geocode implements Parcelable {

    // <editor-fold desc="VARIABLES">

    private Collection<AddressComponent> mAddressComponents;
    private String mFormattedAddress;
    private Geometry mGeometry;
    private Collection<String> mTypes;
    private boolean mPartialMatch;

    // </editor-fold>

    // <editor-fold desc="CONSTRUCTORS">

    protected Geocode(Parcel in) {
        int size = in.readInt();
        mAddressComponents = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            mAddressComponents.add((AddressComponent)in.readParcelable(AddressComponent.class.getClassLoader()));
        }
        mFormattedAddress = in.readString();
        mGeometry = in.readParcelable(Geometry.class.getClassLoader());
        size = in.readInt();
        for (int i = 0; i < size; i++) {
            mTypes.add(in.readString());
        }
        mPartialMatch = in.readInt() == 1;
    }

    protected Geocode(Init<?> builder) {
        mAddressComponents = builder.mAddressComponents;
        mFormattedAddress = builder.mFormattedAddress;
        mGeometry = builder.mGeometry;
        mTypes = builder.mTypes;
        mPartialMatch = builder.mPartialMatch;
    }

    // </editor-fold>

    //<editor-fold desc="PARCELABLE METHODS IMPLEMENTATION">

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        if (mAddressComponents != null) {
            parcel.writeInt(mAddressComponents.size());
            for (AddressComponent addressComponent : mAddressComponents) {
                parcel.writeParcelable(addressComponent, flags);
            }
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(mFormattedAddress);
        parcel.writeParcelable(mGeometry, flags);
        if (mTypes != null) {
            parcel.writeInt(mTypes.size());
            for (String type : mTypes) {
                parcel.writeString(type);
            }
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(mPartialMatch ? 1 : 0);
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

    // <editor-fold desc="GETTERS">

    public Collection<String> getTypes() {
        return mTypes;
    }

    public String getFormatted_address() {
        return mFormattedAddress;
    }

    public Collection<AddressComponent> getAddress_components() {
        return mAddressComponents;
    }

    public Geometry getGeometry() {
        return mGeometry;
    }

    public boolean isPartialMatch() {
        return mPartialMatch;
    }

    // </editor-fold>

    // <editor-fold desc="SETTERS">

    public void setTypes(Collection<String> types) {
        mTypes = types;
    }

    public void setFormattedAddress(String formatted_address) {
        mFormattedAddress = formatted_address;
    }

    public void setAddressComponents(Collection<AddressComponent> address_components) {
        mAddressComponents = address_components;
    }

    public void setGeometry(Geometry geometry) {
        mGeometry = geometry;
    }

    public void setPartialMatch(boolean partialMatch) {
        mPartialMatch = partialMatch;
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

        public Geocode fromJSON(JSONObject object) throws JSONException {
            if (object == null)
                return null;
            return new Builder()
                    .build();
        }

        // </editor-fold>

    }

    // </editor-fold>

    // <editor-fold desc="INIT BUILDER CLASS">

    protected static abstract class Init<T extends Init<T>> {

        // <editor-fold desc="VARIABLES">

        private Collection<AddressComponent> mAddressComponents = new ArrayList<>();
        private String mFormattedAddress;
        private Geometry mGeometry;
        private Collection<String> mTypes = new ArrayList<>();
        private boolean mPartialMatch;

        // </editor-fold>

        // <editor-fold desc="CONSTRUCTORS">

        public Init() {
            mAddressComponents = null;
            mFormattedAddress = null;
            mGeometry = null;
            mTypes = null;
            mPartialMatch = false;
        }

        // </editor-fold>

        // <editor-fold desc="SETTERS">

        public T setTypes(Collection<String> types) {
            mTypes = types;
            return self();
        }

        public T setFormattedAddress(String formatted_address) {
            mFormattedAddress = formatted_address;
            return self();
        }

        public T setAddressComponents(Collection<AddressComponent> address_components) {
            mAddressComponents = address_components;
            return self();
        }

        public T setGeometry(Geometry geometry) {
            mGeometry = geometry;
            return self();
        }

        public T setPartialMatch(boolean partialMatch) {
            mPartialMatch = partialMatch;
            return self();
        }

        // </editor-fold>

        // <editor-fold desc="METHODS">

        protected abstract T self();

        public Geocode build() {
            return new Geocode(this);
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

/*
    TODO
    - BETTER IMPLEMENTATION
 */