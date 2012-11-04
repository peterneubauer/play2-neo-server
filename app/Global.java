import org.neo4j.kernel.GraphDatabaseAPI;
import play.Application;
import play.GlobalSettings;

import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.neo4j.server.WrappingNeoServerBootstrapper;
import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: magnusandersson
 * Date: 11/4/12
 * Time: 3:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Global extends GlobalSettings {

    public static GraphDatabaseAPI graphDb;
    private static WrappingNeoServerBootstrapper srv;

    private static String DB_PATH = "/usr/local/play2-neo-server/db";

    @Override
    public void onStart(Application app) {

        Logger.debug("onStart");
        graphDb = new EmbeddedGraphDatabase(DB_PATH);
        srv = new WrappingNeoServerBootstrapper(graphDb);
        srv.start();

        registerShutdownHook();
    }

    public static void stop(){
        srv.stop();
    }

    private static void registerShutdownHook() {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running example before it's completed)
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Logger.debug("Closing Neo4j2");
                srv.stop();
                graphDb.shutdown();
                Logger.debug("Closed Neo4j2");
            }
        });
    }
}
