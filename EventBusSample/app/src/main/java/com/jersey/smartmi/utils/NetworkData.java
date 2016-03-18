package com.jersey.smartmi.utils;


public class NetworkData {
    private NetworkData() {
    }
    public static final String BASE_URL="http://192.168.20.64:8080/smartmi/";
    public static String LOGIN_API = BASE_URL+"login";
    public static final String VIEW_QUOTES_API=BASE_URL+"viewquotes";
    public static final String VIEW_QUOTE_DETAILS_API=BASE_URL+"quotedetails";
}
