package com.awake.taxi.Model;

import java.util.ArrayList;
import java.util.Hashtable;

public class CountryDataSource {

    public static final String COUNTRY_KEY = "country";
    public static final float MINIMUM_CONFIDENCE_LEVEL = 0.4F;
    public static final String DEFAULT_COUNTRY_NAME = "Kenya";
    public static final double DEFAULT_COUNTRY_LATITUDE = 37.9062;
    public static final double DEFAULT_COUNTRY_LONGITUDE = 0.0236;
    public static final String DEFAULT_MESSAGE = "WELCOME";

    private Hashtable<String, String> countriesMessages;

    public CountryDataSource(Hashtable<String, String> countriesMessages){
        this.countriesMessages = countriesMessages;

    }
}
