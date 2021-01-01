import com.datastax.driver.core.Session;
import persistence.Connector;


/**
 * Creates product Column Family
 * You can test this example by running the main() method
 * Created by navdeepsingh on 24/07/16.
 */
public class CreateProductColumnFamilyMain {

    public static void main(String[] args){
        createProductColumnFamily("cms");
        //// to close the opened session
        Connector.close();
        System.out.println("CLosing the session");

    }

    private static void createProductColumnFamily(String keyspaceName){
        // building the query to create columnfamily
        Session session = Connector.getSession();
        System.out.println("logged keyspace: "+session.getLoggedKeyspace());

        // change keyspace to cms
        String changeKeySpaceQuery = "USE "+keyspaceName;
        // execute command
        session.execute(changeKeySpaceQuery);
        // print current keyspace
        System.out.println("logged keyspace›"+session.getLoggedKeyspace());

        String columnFamily = "products";
        // query to create columnfamily
        String query = "CREATE COLUMNFAMILY " +columnFamily+ "("+
                "productId varchar,"+
                "brand varchar,"+
                "length int,"+
                "breadth int,"+
                "height int,"+
                "category varchar,"+
                "title text,"+
                "publisher text,"+
                "keyfeatures list<text>,"+
                "PRIMARY KEY (category, brand, productId));";
        System.out.println(query);

        // execute the query
        session.execute(query);

    }

}
