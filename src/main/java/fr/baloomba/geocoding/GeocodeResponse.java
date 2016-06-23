package fr.baloomba.geocoding;

import java.util.ArrayList;
import java.util.List;

public class GeocodeResponse {

    // <editor-fold desc="VARIABLES">

	private String status;
	private List<Geocode> results = new ArrayList<>();

    // </editor-fold>

    // <editor-fold desc="GETTERS">

	public String getStatus() {
		return status;
	}

    public List<Geocode> getResults() {
        return results;
    }

    // </editor-fold>

    // <editor-fold desc="SETTERS">

    public void setStatus(String status) {
		this.status = status;
	}

    public void setResults(List<Geocode> results) {
        this.results = results;
    }

    // </editor-fold>

}

/*
    TODO
    - REMOVE IT
 */