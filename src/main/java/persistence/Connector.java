package persistence;

import com.datastax.driver.core.*;

/**
 * Created by jananiravi on 7/7/16.
 */
/*
This class creates a singleton session object
 It is shared by all classes

 */
public class Connector {
    private static String host = "localhost";
    private static int port = 9042;
    private static String userName = "cassandra";
    private static String password = "cassandra";
    private static String clusterName = "easybuy";
    private static int MAX_CONNECTIONS = 100;
    private static int CORE_CONNECTIONS = 25;

    private static Cluster cluster;
    private static Session session;



    /**
     *Returns singleton session object
     *
     */
    public static Session getSession(){
        // We are configuring the connection pool
        PoolingOptions poolingOptions = new PoolingOptions();
        // no of max connections
        poolingOptions.setMaxConnectionsPerHost(HostDistance.LOCAL, MAX_CONNECTIONS);
        // no of min connections this host will be allotted
        poolingOptions.setCoreConnectionsPerHost(HostDistance.LOCAL, CORE_CONNECTIONS);


        // Create a cluster object
        cluster = Cluster.builder().
                addContactPoint(host).
                withPort(port).
                withCredentials(userName, password).
                withPoolingOptions(poolingOptions).
                withClusterName(clusterName).
                build();


        final Metadata metadata = cluster.getMetadata();
        System.out.println("Connected to cluster: " + metadata.getClusterName());
        //printHostNames(metadata);
        session = cluster.connect();

        return session;
    }


    // close the connection
    public static void close(){
        cluster.close();
    }

    private static void printHostNames(Metadata metadata){
        for (final Host host : metadata.getAllHosts())
        {
            System.out.println("Datacenter: " + host.getDatacenter() + "host :" + host.getAddress() + "rack :" + host.getRack());
        }
    }
}
