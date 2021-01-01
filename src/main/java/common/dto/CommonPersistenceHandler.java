package common.dto;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.google.common.collect.Lists;
import listing.Listing;
import persistence.Connector;
import product.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by navdeepsingh on 24/07/16.
 *
 * When we want to do db operations involving both listing and product,
 * this class will be used
 *
 * In Logged Batch example, we are using methods of this class
 */
public class CommonPersistenceHandler {

    // keyspace and columnfamilies which will be used by methods in this class
    private static String keyspace = "cms";
    private static String listingColumnFamily = "listings";
    private static String productColumnFamily = "products";

    /**
     *Executes a logged batch to update the title in the input listing and product data
     *
     * @param listing object containing primary key and title attribute
     * @param product object containing primary key and title attribute
     *
     */
    public void updatetitle(Listing listing, Product product){

        BatchStatement batch = new BatchStatement();
        if(listing != null) {
            batch.addAll(getListingUpdateStatements(listing));
        }
        if(product != null) {
            batch.addAll(getProductUpdateStatements(product));
        }

        try {
            Session session = Connector.getSession();
            session.execute(batch);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     *Returns list of update statements for every attribute other than the primary keys
     * in the listing object
     *
     * @param listing
     * @return List<Statement> - list of executable queries
     */
    private List<Statement> getListingUpdateStatements(Listing listing ){
        List<Statement> updates = Lists.newArrayList();

        String listingId = listing.getListingId();
        String productId = listing.getAttributes().get(AttributeNames.PRODUCTID.getValue()).toString();

        Map<String, Object> attributeSet = listing.getAttributes();
        // every attribute update is a separate statement
        for(String attributeName : attributeSet.keySet()){
            if(!isPartOfPrimaryKeyForListing(attributeName)) {

                Statement updateStatement = QueryBuilder.update(keyspace, listingColumnFamily)
                        .with(QueryBuilder.set(attributeName, attributeSet.get(attributeName)))
                        .where(QueryBuilder.eq(AttributeNames.PRODUCTID.getValue(), productId))
                        .and(QueryBuilder.eq(AttributeNames.LISTINGID.getValue(), listingId));
                updates.add(updateStatement);
            }
        }

        return updates;
    }

    /**
     * Returns list of update statements for every attribute other than the primary keys
     * in the product object
     *
     * @param product
     * @return List<Statement> - list of executable queries
     */
    private List<Statement> getProductUpdateStatements(Product product){
        List<Statement> updates = Lists.newArrayList();

        String productId = product.getProductId();
        String category = product.getAttributesMap().get(AttributeNames.CATEGORY.getValue()).toString();
        String brand = product.getAttributesMap().get(AttributeNames.BRAND.getValue()).toString();

        Map<String, Object> attributeSet = product.getAttributesMap();
        // every attribute update is a separate statement
        for(String attributeName : attributeSet.keySet()){
            if(!isPartOfPrimaryKeyForProduct(attributeName)) {

                Statement updateStatement = QueryBuilder.update(keyspace, productColumnFamily)
                        .with(QueryBuilder.set(attributeName, attributeSet.get(attributeName)))
                        .where(QueryBuilder.eq(AttributeNames.CATEGORY.getValue(), category))
                        .and(QueryBuilder.eq(AttributeNames.BRAND.getValue(), brand))
                        .and(QueryBuilder.eq(AttributeNames.PRODUCTID.getValue(), productId));

                updates.add(updateStatement);
            }
        }
        return updates;
    }

    /**
     * Checks whether attributeName is a part of primary key of listing
     * @param attributeName
     * @return true if the attributeName is a part of the primary key
     */
    private boolean isPartOfPrimaryKeyForListing(String attributeName){
        return (AttributeNames.PRODUCTID.getValue().equals(attributeName));
    }

    /**
     *Checks whether attributeName is a part of primary key of listing
     * @param attributeName
     * @return true if the attributeName is a part of the primary key
     */
    private boolean isPartOfPrimaryKeyForProduct(String attributeName){
        return (AttributeNames.CATEGORY.getValue().equals(attributeName) ||
                AttributeNames.BRAND.getValue().equals(attributeName));
    }
}
