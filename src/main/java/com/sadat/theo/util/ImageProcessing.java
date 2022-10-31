package com.sadat.theo.util;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageProcessing {

    public static Image processImage(Image image) {

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        WritableImage wImage = new WritableImage(width, height);
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter writer = wImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                Color color = pixelReader.getColor(x, y);

                writer.setColor(x, y, color.darker());
            }
        }

        return wImage;
    }

}
