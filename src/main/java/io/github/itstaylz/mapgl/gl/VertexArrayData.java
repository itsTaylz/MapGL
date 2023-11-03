package io.github.itstaylz.mapgl.gl;

import java.util.HashMap;
import java.util.Map;

public class VertexArrayData {

    private final Map<Integer, AttribPointerData> attribPointers = new HashMap<>();

    public AttribPointerData getAttribArray(int index) {
        return attribPointers.get(index);
    }

    public void enableAttribPointerArray(int index) {
        if (!this.attribPointers.containsKey(index)) {
            AttribPointerData pointerData = new AttribPointerData();
            pointerData.setEnabled(true);
            this.attribPointers.put(index, pointerData);
        } else {
            AttribPointerData pointerData = this.attribPointers.get(index);
            pointerData.setEnabled(true);
        }
    }
}
