package photoviewer.entity.model.core.unit.data.content;


import java.awt.image.BufferedImage;

public class ContentImage implements Content {
    private BufferedImage image;

    public ContentImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public Object getContent() {
        return image;
    }
}
