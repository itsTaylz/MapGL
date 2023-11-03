package io.github.itstaylz.mapgl.gl;

import io.github.itstaylz.mapgl.enums.GlBufferType;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class GlState {

    private Color clearColor = Color.BLACK;
    private final Map<GlBufferType, Integer> boundedBuffers = new HashMap<>();

    public void setClearColor(Color color) {
        this.clearColor = color;
    }

    public Color getClearColor() {
        return this.clearColor;
    }

    public void setBoundedArrayBuffer(GlBufferType bufferType, int id) {
        if (id == 0)
            this.boundedBuffers.remove(bufferType);
        else
            this.boundedBuffers.put(bufferType, id);
    }

    public int getBoundedArrayBuffer(GlBufferType bufferType) {
        return this.boundedBuffers.getOrDefault(bufferType, 0);
    }
}
