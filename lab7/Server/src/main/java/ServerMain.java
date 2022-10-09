import bd.BDCollection;
import bd.BDUser;
import server.CollectionManager;
import server.Server;
import util.Database;

public class ServerMain {
    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();
        Database database = new Database();
        database.run();
        BDUser BDUser = new BDUser();
        BDUser.runBDUser(database);
        BDCollection BDCollection = new BDCollection(database);
        collectionManager.load(BDCollection.getDragonBD());
        Server server = new Server(collectionManager);
        server.run(7070, database, BDCollection);
    }
}
