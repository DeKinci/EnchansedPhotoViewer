package photoviewer.entity.model.core.unit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class PVImage extends PVUnit implements Serializable {
    public PVImage(File source) throws IOException {
        super(source);
    }

    public PVImage (PVImage source) {
        super(source);
    }

    @Override
    public BufferedImage getContent() {
        return (BufferedImage) content.getContent();
    }
}
