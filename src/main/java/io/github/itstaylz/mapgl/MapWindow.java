package io.github.itstaylz.mapgl;

import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class MapWindow {

    private static final int MAP_DIM = 128;

    private final MapView mapView;
    private MapGlContext glContext = null;

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
        this.mapView.addRenderer(new MapGlRenderer(glContext));
        return this.glContext;
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
