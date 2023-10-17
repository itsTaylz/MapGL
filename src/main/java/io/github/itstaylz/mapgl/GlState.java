package io.github.itstaylz.mapgl;

import java.awt.*;

public class GlState {

    private Color clearColor = Color.BLACK;

    public void setClearColor(Color color) {
        this.clearColor = color;
    }

    public Color getClearColor() {
        return this.clearColor;
    }

}
