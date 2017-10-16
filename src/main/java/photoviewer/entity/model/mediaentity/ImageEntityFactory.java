package photoviewer.entity.model.mediaentity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import photoviewer.entity.model.Entity;
import photoviewer.entity.model.EntityFactory;
import photoviewer.entity.model.core.files.unit.factory.PVImageFactory;
import photoviewer.entity.model.core.files.unit.factory.PVUnitFactory;

import java.io.File;
import java.io.IOException;

public class ImageEntityFactory implements EntityFactory {
    private static volatile ImageEntityFactory factory;
    Logger log;

    public static ImageEntityFactory getFactory() {
        if (factory == null)
            synchronized (ImageEntityFactory.class) {
                if(factory == null)
                    factory = new ImageEntityFactory();
            }
        return factory;
    }

    private ImageEntityFactory() {
        log = LogManager.getLogger();
    }

    @Override
    public Entity getMediaEntity(File file) {
        PVUnitFactory unitFactory = PVImageFactory.getInstance();
        try {
            return new ImageEntity(unitFactory.openFile(file));
        } catch (IOException e) {
            log.warn("Exception opening file: " + e.getMessage());
            return null;
        }
    }
}
