package photoviewer.apphost;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import photoviewer.entity.model.entities.Entity;

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

    public void addEntity(Entity entity) {
        this.mediaEntities.add(entity);
        setChanged();
        notifyObservers(entity);
    }

    public void removeEntity(Entity entity) {
        this.mediaEntities.remove(entity);
        setChanged();
        notifyObservers(entity);
    }

    public void removeEntityByID(int id) {
        for (Entity entity : this.mediaEntities)
            if (entity.getID() == id) {
                this.mediaEntities.remove(entity);
                setChanged();
                notifyObservers();
                break;
            }
    }

    public Iterator createIterator() {
        return this.mediaEntities.iterator();
    }


}
