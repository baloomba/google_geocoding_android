package fr.baloomba.geocoding;

import android.os.Parcel;
import android.os.Parcelable;

public class Type implements Parcelable {

    // <editor-fold desc="VARIABLES">

    private String mValue;

    // </editor-fold>

    // <editor-fold desc="JSON KEYS">

    private static final String STREET_ADDRESS = "street_address";
    private static final String ROUTE = "route";
    private static final String INTERSECTION = "intersection";
    private static final String POLITICAL = "political";
    private static final String COUNTRY = "country";
    private static final String ADMINISTRATIVE_AREA_LEVEL_1 = "administrative_area_level_1";
    private static final String ADMINISTRATIVE_AREA_LEVEL_2 = "administrative_area_level_2";
    private static final String ADMINISTRATIVE_AREA_LEVEL_3 = "administrative_area_level_3";
    private static final String ADMINISTRATIVE_AREA_LEVEL_4 = "administrative_area_level_4";
    private static final String ADMINISTRATIVE_AREA_LEVEL_5 = "administrative_area_level_5";
    private static final String COLLOQUIAL_AREA = "colloquial_area";
    private static final String LOCALITY = "locality";
    private static final String WARD = "ward";
    private static final String SUBLOCALITY = "sublocality";
    private static final String SUBLOCALITY_LEVEL_1 = "sublocality_level_1";
    private static final String SUBLOCALITY_LEVEL_2 = "sublocality_level_2";
    private static final String SUBLOCALITY_LEVEL_3 = "sublocality_level_3";
    private static final String SUBLOCALITY_LEVEL_4 = "sublocality_level_4";
    private static final String SUBLOCALITY_LEVEL_5 = "sublocality_level_5";
    private static final String NEIGHBORHOOD = "neighborhood";
    private static final String PREMISE = "premise";
    private static final String SUBPREMISE = "subpremise";
    private static final String POSTAL_CODE = "postal_code";
    private static final String NATURAL_FEATURE = "natural_feature";
    private static final String AIRPORT = "airport";
    private static final String PARK = "park";
    private static final String POINT_OF_INTEREST = "point_of_interest";
    private static final String FLOOR = "floor";
    private static final String ESTABLISHMENT = "establishment";
    private static final String PARKING = "parking";
    private static final String POST_BOX = "post_box";
    private static final String POSTAL_TOWN = "postal_town";
    private static final String ROOM = "room";
    private static final String STREET_NUMBER = "street_number";
    private static final String BUS_STATION = "bus_station";
    private static final String TRAIN_STATION = "train_station";
    private static final String TRANSIT_STATION = "transit_station";

    // </editor-fold>

    // <editor-fold desc="CONSTRUCTORS">

    protected Type(Parcel in) {
        mValue = in.readString();
    }

    protected Type(Init<?> builder) {
        mValue = builder.mValue;
    }

    // </editor-fold>

    //<editor-fold desc="PARCELABLE METHODS IMPLEMENTATION">

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mValue);
    }

    public static final Creator<Type> CREATOR =
            new Creator<Type>() {
                @Override
                public Type createFromParcel(Parcel source) {
                    return new Type(source);
                }

                @Override
                public Type[] newArray(int size) {
                    return new Type[size];
                }
            };

    //</editor-fold>

    // <editor-fold desc="GETTERS">

    public String getValue() {
        return mValue;
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

        public Type typeFromString(String type) {
            if (type == null || type.replace(" ", "").isEmpty())
                return null;
            Builder builder = new Builder();
            switch (type) {
                case STREET_ADDRESS:
                case ROUTE:
                case INTERSECTION:
                case POLITICAL:
                case COUNTRY:
                case ADMINISTRATIVE_AREA_LEVEL_1:
                case ADMINISTRATIVE_AREA_LEVEL_2:
                case ADMINISTRATIVE_AREA_LEVEL_3:
                case ADMINISTRATIVE_AREA_LEVEL_4:
                case ADMINISTRATIVE_AREA_LEVEL_5:
                case COLLOQUIAL_AREA:
                case LOCALITY:
                case WARD:
                case SUBLOCALITY:
                case SUBLOCALITY_LEVEL_1:
                case SUBLOCALITY_LEVEL_2:
                case SUBLOCALITY_LEVEL_3:
                case SUBLOCALITY_LEVEL_4:
                case SUBLOCALITY_LEVEL_5:
                case NEIGHBORHOOD:
                case PREMISE:
                case SUBPREMISE:
                case POSTAL_CODE:
                case NATURAL_FEATURE:
                case AIRPORT:
                case PARK:
                case POINT_OF_INTEREST:
                case FLOOR:
                case ESTABLISHMENT:
                case PARKING:
                case POST_BOX:
                case POSTAL_TOWN:
                case ROOM:
                case STREET_NUMBER:
                case BUS_STATION:
                case TRAIN_STATION:
                case TRANSIT_STATION:
                    builder = builder.setValue(type);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            return builder.build();
        }

        // </editor-fold>

    }

    // </editor-fold>

    // <editor-fold desc="INIT BUILDER CLASS">

    protected static abstract class Init<T extends Init<T>> {

        // <editor-fold desc="VARIABLES">

        private String mValue;

        // </editor-fold>

        // <editor-fold desc="CONSTRUCTORS">

        public Init() {
            mValue = null;
        }

        // </editor-fold>

        // <editor-fold desc="SETTERS">

        public T setValue(String value) {
            mValue = value;
            return self();
        }

        // </editor-fold>

        // <editor-fold desc="METHODS">

        protected abstract T self();

        public Type build() {
            return new Type(this);
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
