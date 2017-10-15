package photoviewer.apphost;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import photoviewer.entity.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class Host extends Observable {
    private static volatile Host host;

    private Logger log;

    private ArrayList<Entity> mediaEntities;

    public static Host getHost() {
        if (host == null)
            synchronized (Host.class) {
                if (host == null)
                    host = new Host();
            }

        return host;
    }

    private Host() {
        this.mediaEntities = new ArrayList<>();
        this.log = LogManager.getLogger();
    }

    public void addInstance(Entity instance) {
        this.mediaEntities.add(instance);
    }

    public void removeInstance(Entity instance) {
        this.mediaEntities.remove(instance);
    }

    public void removeInstanceByID(int id) {
        for (Entity instance : this.mediaEntities)
            if (instance.getID() == id) {
                this.mediaEntities.remove(instance);
                break;
            }
    }

    public Iterator createIterator() {
        return this.mediaEntities.iterator();
    }
}
