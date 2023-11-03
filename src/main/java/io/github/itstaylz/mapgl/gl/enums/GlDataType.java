package io.github.itstaylz.mapgl.gl.enums;

public enum GlDataType {

    FLOAT(4);

    private int size;

    GlDataType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
