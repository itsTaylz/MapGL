package io.github.itstaylz.mapgl.gl;

import io.github.itstaylz.mapgl.enums.GlBufferType;
import io.github.itstaylz.mapgl.enums.GlUsageHint;
import io.github.itstaylz.mapgl.gpu.VirtualGPU;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.Collection;

public class MapGlContext {

    private final GlState glState;
    private final VirtualGPU vGpu;
    private BufferedImage screenBuffer;
    private BufferedImage drawBuffer;

    public MapGlContext(int width, int height) {
        this(width, height, new GlState(), new VirtualGPU());
    }

    public MapGlContext(int width, int height, VirtualGPU gpu) {
        this(width, height, new GlState(), gpu);
    }

    public MapGlContext(int width, int height, GlState state) {
        this(width, height, state, new VirtualGPU());
    }

    public MapGlContext(int width, int height, GlState state, VirtualGPU gpu) {
        this.screenBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.drawBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.glState = state;
        this.vGpu = gpu;
    }

    public void swapBuffers() {
        BufferedImage temp = this.screenBuffer;
        this.screenBuffer = this.drawBuffer;
        this.drawBuffer = temp;
    }

    public BufferedImage getScreenBuffer() {
        return this.screenBuffer;
    }

    public VirtualGPU getVGpu() {
        return this.vGpu;
    }

    // ---------------------- OPENGL FUNCTIONS ----------------------
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
        return this.vGpu.getMemory().genBuffer();
    }

    public void glDeleteBuffers(Collection<Integer> buffers) {
        for (int buffer : buffers) {
            this.vGpu.getMemory().deleteBuffer(buffer);
        }
    }

    public void glDeleteBuffers(int[] buffers) {
        for (int buffer : buffers) {
            this.vGpu.getMemory().deleteBuffer(buffer);
        }
    }

    public void glBindBuffer(GlBufferType type, int id) {
        this.glState.setBoundedArrayBuffer(type, id);
    }

    // TODO: usage
    public void glBufferData(GlBufferType type, final byte[] data, GlUsageHint usage) {
        glBufferData(type, ByteBuffer.wrap(data), usage);
    }

    public void glBufferData(GlBufferType type, ByteBuffer data, GlUsageHint usageHint) {
        int current = this.glState.getBoundedArrayBuffer(type);
        if (current == 0)
            throw new RuntimeException();
        this.vGpu.getMemory().setBufferData(current, data);
    }
}
