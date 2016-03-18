package com.jersey.smartmi.models.quote_details_response_parser;

import java.util.ArrayList;

/**
 * Created by Vishnu on 01/03/16.
 */
public class Quotes {
    public String quoteId;
    public String quoteReference;
    public String customerName;
    public String projectName;

    public String salesEngineer;
    public String quotationDate;
    public String quoteStatus;
    public String quoteValue;
    public ArrayList<ProductsDetails> productsDetails;
}
