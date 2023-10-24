package io.github.itstaylz.mapgl;

public class VirtualGPU {

    private final VirtualGPUMemory memory = new VirtualGPUMemory();

    public VirtualGPUMemory getMemory() {
        return memory;
    }
}
