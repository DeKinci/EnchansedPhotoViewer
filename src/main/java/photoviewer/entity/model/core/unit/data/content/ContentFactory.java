package photoviewer.entity.model.core.unit.data.content;


import java.awt.image.BufferedImage;

public class ContentFactory {
    private volatile static ContentFactory contentFactory;

    public static ContentFactory makeContentFactory() {
        if (contentFactory == null)
            synchronized (ContentFactory.class) {
                if (contentFactory == null)
                    contentFactory = new ContentFactory();
            }

        return contentFactory;
    }

    private ContentFactory() {
    }

    public Content createContent(Object contentData) throws IllegalArgumentException{
        if (contentData instanceof BufferedImage)
            return new ContentImage((BufferedImage) contentData);
        else if (contentData instanceof ContentImage)
            return (ContentImage) contentData;
        else
            throw new IllegalArgumentException();
    }
}
