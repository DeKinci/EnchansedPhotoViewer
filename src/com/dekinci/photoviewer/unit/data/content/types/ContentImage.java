package com.dekinci.photoviewer.unit.data.content.types;

import com.dekinci.photoviewer.unit.data.content.Content;

import java.awt.image.BufferedImage;

public class ContentImage implements Content {
    private BufferedImage image;

    public ContentImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public Class getContentType() {
        return image.getClass();
    }

    @Override
    public Object getContent() {
        return image;
    }
}
