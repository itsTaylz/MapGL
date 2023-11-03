package io.github.itstaylz.mapgl.map;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

class MapGlRenderer extends MapRenderer {

    private final MapWindow window;

    public MapGlRenderer(MapWindow window) {
        this.window = window;
    }

    @Override
    public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
        window.onDraw();
        mapCanvas.drawImage(0, 0, this.window.getGlContext().getScreenBuffer());
    }
}
