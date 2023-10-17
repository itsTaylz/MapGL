package io.github.itstaylz.mapgl;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MapGlContext {

    private final GlState state = new GlState();
    private final int width, height;
    private BufferedImage screenBuffer;
    private BufferedImage drawBuffer;

    public MapGlContext(int width, int height) {
        this.width = width;
        this.height = height;
        this.screenBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.drawBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void glClearColor(Color color) {
        this.state.setClearColor(color);
    }

    public void glClear() {
        int rgb = this.state.getClearColor().getRGB();
        for (int y = 0; y < this.drawBuffer.getHeight(); y++) {
            for (int x = 0; x < this.drawBuffer.getWidth(); x++) {
                this.drawBuffer.setRGB(x, y, rgb);
            }
        }
    }

    public void swapBuffers() {
        BufferedImage temp = this.screenBuffer;
        this.screenBuffer = this.drawBuffer;
        this.drawBuffer = temp;
    }

    public BufferedImage getScreenBuffer() {
        return this.screenBuffer;
    }
}
