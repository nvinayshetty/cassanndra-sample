import com.google.common.collect.Lists;
import listing.ListingPersistenceHandler;
import persistence.Connector;

import java.util.List;

/**
 * Delete listings for the productIds
 * You can test this example by running the main() method
 * Created by navdeepsingh on 24/07/16.
 */
public class DeleteListingsMain {

    public static void main(String[] args){
        deleteListings();
        // to close the opened session
        Connector.close();
        System.out.println("CLosing the session");

    }

    private static void deleteListings(){
        // list of productids whose listings we want to delete
        List<String> productIds = Lists.newArrayList();
        productIds.add("CHA1");
        productIds.add("TOP1");

        ListingPersistenceHandler handler = new ListingPersistenceHandler();
        handler.delete(productIds);
    }
}
