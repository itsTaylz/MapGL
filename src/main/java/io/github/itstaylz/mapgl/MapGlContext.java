package io.github.itstaylz.mapgl;

import io.github.itstaylz.mapgl.enums.GlBufferType;
import io.github.itstaylz.mapgl.enums.GlUsage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;

public class MapGlContext {

    private final GlState glState = new GlState();
    private final int width, height;
    private BufferedImage screenBuffer;
    private BufferedImage drawBuffer;
    private final VirtualGPU vGPU = new VirtualGPU();

    public MapGlContext(int width, int height) {
        this.width = width;
        this.height = height;
        this.screenBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.drawBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void swapBuffers() {
        BufferedImage temp = this.screenBuffer;
        this.screenBuffer = this.drawBuffer;
        this.drawBuffer = temp;
    }

    public BufferedImage getScreenBuffer() {
        return this.screenBuffer;
    }

    public void glClearColor(Color color) {
        this.glState.setClearColor(color);
    }

    public void glClear() {
        int rgb = this.glState.getClearColor().getRGB();
        for (int y = 0; y < this.drawBuffer.getHeight(); y++) {
            for (int x = 0; x < this.drawBuffer.getWidth(); x++) {
                this.drawBuffer.setRGB(x, y, rgb);
            }
        }
    }

    public int glGenBuffer() {
        return this.vGPU.getMemory().genBuffer();
    }

    public void glDeleteBuffers(Collection<Integer> buffers) {
        for (int buffer : buffers) {
            this.vGPU.getMemory().deleteBuffer(buffer);
        }
    }

    public void glDeleteBuffers(int[] buffers) {
        for (int buffer : buffers) {
            this.vGPU.getMemory().deleteBuffer(buffer);
        }
    }

    public void glBindBuffer(GlBufferType type, int id) {
        this.glState.setBoundedArrayBuffer(type, id);
    }

    // TODO: usage
    public void glBufferData(GlBufferType type, final byte[] data, GlUsage usage) {
        int current = this.glState.getBoundedArrayBuffer(type);
        if (current == 0)
            throw new RuntimeException();
        this.vGPU.getMemory().bufferData(current, data);
    }
}
