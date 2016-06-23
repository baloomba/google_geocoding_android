package fr.baloomba.geocoding;

import java.util.ArrayList;

public abstract class GeocodingCallback {

    public abstract void statusOk(ArrayList<Geocode> geocodes);

    public abstract void statusError();

    public void statusZeroResults() {
        statusError();
    }

    public void statusOverQueryLimit() {
        statusError();
    }

    public void statusRequestDenied() {
        statusError();
    }

    public void statusInvalidRequest() {
        statusError();
    }

}
