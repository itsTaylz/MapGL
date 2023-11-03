package io.github.itstaylz.mapgl.gpu;

import io.github.itstaylz.mapgl.gl.VertexArrayData;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class VirtualGPUMemory {

    private final Map<Integer, ByteBuffer> buffers = new HashMap<>();
    private final Map<Integer, VertexArrayData> vertexArrays = new HashMap<>();
    private long usedMemory = 0L;

    public long getUsedMemory() {
        return this.usedMemory;
    }

    public int genBuffer() {
        int id = buffers.size() + 1;
        buffers.put(id, null);
        return id;
    }

    public void deleteBuffer(int id) {
        buffers.remove(id);
    }

    public void setBufferData(int id, final byte[] data) {
        setBufferData(id, ByteBuffer.wrap(data));
    }

    public void setBufferData(int id, ByteBuffer data) {
        if (!this.buffers.containsKey(id))
            throw new RuntimeException();
        ByteBuffer previousBuffer = this.buffers.get(id);
        if (previousBuffer != null)
            this.usedMemory -= previousBuffer.position();
        ByteBuffer copy = data.duplicate();
        copy.put(data);
        this.usedMemory += copy.position();
        this.buffers.put(id, copy);
    }

    public ByteBuffer getBufferData(int id) {
        return this.buffers.get(id);
    }

    public int genVertexArray() {
        int id = vertexArrays.size() + 1;
        VertexArrayData data = new VertexArrayData();
        vertexArrays.put(id, data);
        return id;
    }

    public void enableVertexAttribArray(int vao, int index) {
        this.vertexArrays.get(0).enableAttribPointerArray(index);
    }

}
