/** Creates cms keyspace
 * You can test this example by running the main() method
 * Created by jananiravi on 7/7/16.
 */

import persistence.Connector;

public class CreateKeyspaceMain {
    public static void main(String[] args){

        createKeyspace("cms");

        System.out.println("Closing the session");
        // to close the opened session
        Connector.close();
    }

    private static void createKeyspace(String keyspace){
        String query = "CREATE KEYSPACE "+ keyspace+" WITH replication "
                + "= {'class':'SimpleStrategy', 'replication_factor':3};";
        Connector.getSession().execute(query);
        System.out.println("Keyspace created :"+keyspace);
    }



























}
