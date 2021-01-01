import com.google.common.collect.Maps;
import common.dto.AttributeNames;
import listing.Listing;
import listing.ListingPersistenceHandler;
import persistence.Connector;

import java.util.Map;

/**
 * Create a row in listings CF with listing object
 * Created by navdeepsingh on 24/07/16.
 * You can test this example by running the main() method
 */
public class InsertDataToListingMain {

    public static void main(String[] args){
        insertDataToListing();
    }

    private static void insertDataToListing(){
        // populating attributes
        Listing listing = createListingData();
        ListingPersistenceHandler listingPersistenceHandler = new ListingPersistenceHandler();
        // put data to listings
        listingPersistenceHandler.put(listing);
        Connector.close();
        createRemainingListingData();
        System.out.println("CLosing the session");
    }

    private static Listing createListingData(){
        Listing listing = new Listing();
        Map<String, Object> attributes = Maps.newHashMap();
        listing.setListingId("LISTINGFABSOFA5");
        attributes.put(AttributeNames.SELLERID.getValue(), "Fab");
        attributes.put(AttributeNames.SKUID.getValue(), "SKU2");
        attributes.put(AttributeNames.MRP.getValue(), 5000);
        attributes.put(AttributeNames.SSP.getValue(), 4000);
        attributes.put(AttributeNames.SLA.getValue(), 2);
        attributes.put(AttributeNames.STOCK.getValue(), 2);
        attributes.put(AttributeNames.PRODUCTID.getValue(), "SOFA5");
        attributes.put(AttributeNames.TITLE.getValue(), "Urban Loving Sofa 3 Seater");
        listing.setAttributes(attributes);
        return listing;


    }

    private static void createRemainingListingData(){
        Listing listing1 = new Listing();
        Map<String, Object> attributes1 = Maps.newHashMap();
        listing1.setListingId("LISTINGULTOP1");
        attributes1.put(AttributeNames.SELLERID.getValue(), "UrbanLadder");
        attributes1.put(AttributeNames.SKUID.getValue(), "SKU1");
        attributes1.put(AttributeNames.MRP.getValue(), 25000);
        attributes1.put(AttributeNames.SSP.getValue(), 20000);
        attributes1.put(AttributeNames.SLA.getValue(), 2);
        attributes1.put(AttributeNames.STOCK.getValue(), 10);
        attributes1.put(AttributeNames.PRODUCTID.getValue(), "TOP1");
        attributes1.put(AttributeNames.TITLE.getValue(), "Marble Top");
        listing1.setAttributes(attributes1);
        ListingPersistenceHandler listingPersistenceHandler = new ListingPersistenceHandler();
        listingPersistenceHandler.put(listing1);
        Connector.close();


        Listing listing = new Listing();
        Map<String, Object> attributes = Maps.newHashMap();
        listing.setListingId("LISTINGULCHA1");
        attributes.put(AttributeNames.SELLERID.getValue(), "UrbanLadder");
        attributes.put(AttributeNames.SKUID.getValue(), "SKU2");
        attributes.put(AttributeNames.MRP.getValue(), 23000);
        attributes.put(AttributeNames.SSP.getValue(), 21000);
        attributes.put(AttributeNames.SLA.getValue(), 2);
        attributes.put(AttributeNames.STOCK.getValue(), 15);
        attributes.put(AttributeNames.PRODUCTID.getValue(), "CHA1");
        attributes.put(AttributeNames.TITLE.getValue(), "Reclining Chair");
        listing.setAttributes(attributes);
        listingPersistenceHandler.put(listing);
        Connector.close();
    }
}
