package io.github.itstaylz.mapgl;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.Random;

public class MapGlPlugin extends JavaPlugin implements Listener {

    private MapGlContext context;
    private static final Random RANDOM = new Random();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    private void onMapInit(MapInitializeEvent event) {
        MapView view = event.getMap();
        MapWindow window = new MapWindow(view);
        this.context = window.createGlContext();
    }

    @EventHandler
    private void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (this.context == null)
            return;
        if (player.getGameMode() == GameMode.SURVIVAL) {
            context.swapBuffers();
            return;
        }
        int r = RANDOM.nextInt(255);
        int g = RANDOM.nextInt(255);
        int b = RANDOM.nextInt(255);
        Color color = new Color(r, g, b);
        context.glClearColor(color);
        context.glClear();
        context.swapBuffers();
    }
}
