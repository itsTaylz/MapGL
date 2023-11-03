package io.github.itstaylz.mapgl.map;

import io.github.itstaylz.mapgl.gl.MapGlContext;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.util.function.BiConsumer;

public class MapWindow {

    private static final int MAP_DIM = 128;

    private final MapView mapView;
    private MapGlContext glContext = null;
    private BiConsumer<MapWindow, MapGlContext> updateFunction = null;

    public MapWindow(MapView map) {
        this.mapView = map;
    }

    public int getWidth() {
        return MAP_DIM;
    }

    public int getHeight() {
        return MAP_DIM;
    }

    public MapGlContext createGlContext() {
        for (MapRenderer renderer : this.mapView.getRenderers()) {
            this.mapView.removeRenderer(renderer);
        }
        this.glContext = new MapGlContext(MAP_DIM, MAP_DIM);
        this.mapView.addRenderer(new MapGlRenderer(this));
        return this.glContext;
    }

    void onDraw() {
        if (this.updateFunction != null && this.glContext != null)
            this.updateFunction.accept(this, this.glContext);
    }

    public void setUpdateFunction(BiConsumer<MapWindow, MapGlContext> updateFunction) {
        this.updateFunction = updateFunction;
    }

    public boolean hasGlContext() {
        return this.glContext != null;
    }

    public MapGlContext getGlContext() {
        return this.glContext;
    }

    public void swapBuffers() {
        this.glContext.swapBuffers();
    }

}
