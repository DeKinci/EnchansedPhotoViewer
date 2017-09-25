package photoviewer.core.files.unit.data.content.types;


import photoviewer.core.files.unit.data.content.Content;

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
