package io.github.itstaylz.mapgl;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class VirtualGPUMemory {

    private final Map<Integer, ByteBuffer> buffers = new HashMap<>();

    public int genBuffer() {
        int id = buffers.size() + 1;
        buffers.put(id, null);
        return id;
    }

    public void deleteBuffer(int id) {
        buffers.remove(id);
    }

    public void bufferData(int id, final byte[] data) {
        if (!this.buffers.containsKey(id))
            throw new RuntimeException();
        ByteBuffer bufferData = ByteBuffer.allocate(data.length);
        bufferData.put(data);
        this.buffers.put(id, bufferData);
    }

}
