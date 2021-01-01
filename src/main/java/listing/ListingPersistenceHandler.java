package listing;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.ThreadLocalMonotonicTimestampGenerator;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import common.dto.AttributeNames;
import persistence.Connector;

import java.util.List;
import java.util.Map;

/**
 * Created by navdeepsingh on 23/07/16.
 *
 * This class contains methods to access and update listings column family
 * in cms keyspace
 *
 */
public class ListingPersistenceHandler {
    // name of the keyspace
    private static String keyspace = "cms";
    // name of the columnfamily on which the
    private static String columnFamily = "listings";

    /**
     * Creates a new listing
     * if productId,listingId is not unique then values of the existing listing
     * be overridden by the new values
     * Method does not check for uniqueness. To be done by the caller
     * @param listing
     */
    public void put(Listing listing){
        // insert statement
        Insert insertStatement = QueryBuilder.insertInto(keyspace, columnFamily);

        Map<String, Object> attributes = listing.getAttributes();
        // adds listingId to insert into()
        insertStatement.value(AttributeNames.LISTINGID.getValue(), listing.getListingId());
        // adds attribute names and data to the insert into()
        for(String attributeName : attributes.keySet()){
            insertStatement = insertStatement.value(attributeName, attributes.get(attributeName));
        }
        // using client side timestamp
        insertStatement.setDefaultTimestamp(new ThreadLocalMonotonicTimestampGenerator().next());
        // get session object
        Session session = Connector.getSession();
        // execute the session
        session.execute(insertStatement);

    }

    /**
     * delete is using only partition key
     * Deletes listings of @productIds from the db
     * @param productIds list of productIds
     */
    public void delete(List<String> productIds){
        // stores attribute name of productId
        String productIdAttrName = AttributeNames.PRODUCTID.getValue();
        // delete statement with In
        Statement deleteStatement = QueryBuilder.delete().from(keyspace, columnFamily)
                             .where(QueryBuilder.in(productIdAttrName,productIds));

        Session session = Connector.getSession();
        session.execute(deleteStatement);

    }



}
