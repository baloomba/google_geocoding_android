package fr.baloomba.geocoding.helper;

import java.util.ArrayList;

import fr.baloomba.geocoding.Component;

public class ComponentHelper {

    public static String concatenate(ArrayList<Component> components) {
        String concatenate = "";
        for (Component component : components) {
            if (!concatenate.isEmpty())
                concatenate += "|";
            concatenate += component.toString();
        }
        return concatenate;
    }

}
