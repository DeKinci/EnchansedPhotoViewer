package photoviewer.entity.mediaentity;

import photoviewer.entity.Entity;
import photoviewer.entity.EntityFactory;

public class ImageEntityFactory implements EntityFactory {
    private static volatile ImageEntityFactory factory;

    public static ImageEntityFactory getFactory() {
        if (factory == null)
            synchronized (ImageEntityFactory.class) {
                if(factory == null)
                    factory = new ImageEntityFactory();
            }
        return factory;
    }

    private ImageEntityFactory() {
    }

    @Override
    public Entity getMediaInstance() {
        return new ImageEntity(null);
    }
}
