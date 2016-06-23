package fr.baloomba.geocoding;

public class Component {

    // <editor-fold desc="GLOBAL VARIABLES (PUBLIC)">

    public static final String ROUTE = "route";
    public static final String LOCALITY = "locality";
    public static final String ADMINISTRATIVE_AREA = "administrative_area";
    public static final String POSTAL_CODE = "postal_code";
    public static final String COUNTRY = "country";

    // </editor-fold>

    // <editor-fold desc="VARIABLES">

    public String mKey;
    public String mValue;

    // </editor-fold>

    // <editor-fold desc="CONSTRUCTORS">

    public Component(String key, String value) {
        mKey = key;
        mValue = value;
    }

    // </editor-fold>

    // <editor-fold desc="OBJECT OVERRIDDEN METHODS">

    @Override
    public String toString() {
        return mKey + ":" + mValue;
    }

    // </editor-fold>

}
