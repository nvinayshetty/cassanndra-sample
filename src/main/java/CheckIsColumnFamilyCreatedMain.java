import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Session;
import persistence.Connector;

import java.util.List;

/**
 * Checks if columnfamily exists
 * You can test this example by running the main() method
 * Created by navdeepsingh on 24/07/16.
 */
public class CheckIsColumnFamilyCreatedMain {
    public static void main(String[] args){

        checkIfColumnFamilyCreated("cms", "listings");
        // to close the opened session
        Connector.close();
        System.out.println("CLosing the session");

    }

    private static void checkIfColumnFamilyCreated(String keyspace, String cfName){
        //get session
        Session session = Connector.getSession();
        // get cluster
        Cluster cluster = session.getCluster();
        // get all keyspaces in cluster
        List<KeyspaceMetadata> keyspaceMetadatas = cluster.getMetadata().getKeyspaces();
        if(keyspaceMetadatas != null ){
            // iterate over keyspaces
            for(KeyspaceMetadata keyspaceMetadata : keyspaceMetadatas){
                if(keyspace.equals(keyspaceMetadata.getName())) {
                    if (keyspaceMetadata.getTable(cfName) != null) {
                        System.out.println("Column Family :"+cfName + " exists in keyspace :"+keyspace);
                        return;
                    }
                }

            }
        }
        System.out.println("Column Family :"+cfName + " doesnt exist");;
    }
}
