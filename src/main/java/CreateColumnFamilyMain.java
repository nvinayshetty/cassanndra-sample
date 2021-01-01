import com.datastax.driver.core.Session;
import persistence.Connector;

/**Create listing columnfamily
 * You can test this example by running the main() method
 * Created by navdeepsingh on 24/07/16.
 */
public class CreateColumnFamilyMain {

    public static void main(String[] args){

        createColumnFamily("cms", "listings");
        // to close the opened session
        Connector.close();
    }

    private static void createColumnFamily(String keyspaceName, String columnFamily){
        // building the query to create columnfamily
        Session session = Connector.getSession();
        System.out.println("logged keyspace: "+session.getLoggedKeyspace());

        // change keyspace to cms
        String changeKeySpaceQuery = "USE "+keyspaceName;
        // execute command
        session.execute(changeKeySpaceQuery);
        // print current keyspace
        System.out.println("logged keyspace›"+session.getLoggedKeyspace());

        // query to create columnfamily
        String query = "CREATE COLUMNFAMILY " +columnFamily+ "("+
                "listingId varchar,"+
                "sellerId Varchar,"+
                "skuId varchar,"+
                "productId varchar,"+
                "mrp int,"+
                "ssp int,"+
                "sla int,"+
                "stock int,"+
                "title text,"+
                "PRIMARY KEY (productId, listingId));";
        System.out.println(query);

        // execute the query
        session.execute(query);

    }

}
