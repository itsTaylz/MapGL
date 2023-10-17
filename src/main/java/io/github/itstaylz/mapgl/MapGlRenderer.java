package io.github.itstaylz.mapgl;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

class MapGlRenderer extends MapRenderer {

    private final MapGlContext context;

    public MapGlRenderer(MapGlContext context) {
        this.context = context;
    }

    @Override
    public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
        mapCanvas.drawImage(0, 0, this.context.getScreenBuffer());
    }
}
