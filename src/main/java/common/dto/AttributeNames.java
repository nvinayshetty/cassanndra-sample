package common.dto;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by navdeepsingh on 22/07/16.
 *
 * This enum contains  attributenames for listing, product
 * the value of enum is same as the column name in listing CF, product CF
 *
 *
 * We use this enum value when we want to mention column names in statements
 */

public enum AttributeNames {

    SELLERID("sellerid"),
    PRODUCTID("productid"),
    LISTINGID("listingid"),
    SKUID("skuid"),
    MRP("mrp"),
    SSP("ssp"),
    SLA("sla"),
    TITLE("title"),
    BRAND("brand"),
    KEYFEATURES("keyfeatures"),
    LENGTH("length"),
    BREADTH("breadth"),
    HEIGHT("height"),
    PUBLISHER("publisher"),
    CATEGORY("category"),
    STOCK("stock");




    private String value;
    AttributeNames(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }

    // these attributes are used in products CF
    public static List<String> getProductColumnNames(){
        List<String> columnNames = Lists.newArrayList();

        columnNames.add(CATEGORY.value);
        columnNames.add(BRAND.value);
        columnNames.add(PRODUCTID.value);
        columnNames.add(LENGTH.value);
        columnNames.add(BREADTH.value);
        columnNames.add(HEIGHT.value);
        columnNames.add(PUBLISHER.value);
        columnNames.add(TITLE.value);
        columnNames.add(KEYFEATURES.value);

        return columnNames;
    }

    // these attributes are used in the listing CF
    public static List<String> getListingColumnNames(){
        List<String> columnNames = Lists.newArrayList();

        columnNames.add(PRODUCTID.value);
        columnNames.add(LISTINGID.value);
        columnNames.add(SELLERID.value);
        columnNames.add(SKUID.value);
        columnNames.add(MRP.value);
        columnNames.add(SSP.value);
        columnNames.add(SLA.value);
        columnNames.add(STOCK.value);


        return columnNames;
    }


}
