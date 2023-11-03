package io.github.itstaylz.mapgl;

import io.github.itstaylz.mapgl.enums.GlBufferType;
import io.github.itstaylz.mapgl.enums.GlUsageHint;
import io.github.itstaylz.mapgl.gl.MapGlContext;
import io.github.itstaylz.mapgl.map.MapWindow;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Random;

public class MapGlPlugin extends JavaPlugin implements Listener {

    private static final Random RANDOM = new Random();

    private int triangle_vbo;

    private float[] triangle_data = {
            -0.5f, 0f,
            0.5f, 0f,
            0f, 0.5f
    };

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    private void onMapInit(MapInitializeEvent event) {
        MapView view = event.getMap();
        MapWindow window = new MapWindow(view);
        window.setUpdateFunction(this::onUpdate);
        MapGlContext context = window.createGlContext();
        this.triangle_vbo = context.glGenBuffer();
        context.glBindBuffer(GlBufferType.GL_ARRAY_BUFFER, this.triangle_vbo);
        ByteBuffer buffer = ByteBuffer.allocate(triangle_data.length * Float.BYTES);
        FloatBuffer floatBuffer = buffer.asFloatBuffer();
        floatBuffer.put(triangle_data);
        context.glBufferData(GlBufferType.GL_ARRAY_BUFFER, buffer, GlUsageHint.GL_STATIC_DRAW);
        Bukkit.broadcastMessage("VGPU used memory: " + context.getVGpu().getMemory().getUsedMemory());
    }

    private void onUpdate(MapWindow window, MapGlContext context) {
        int r = RANDOM.nextInt(0, 255);
        int g = RANDOM.nextInt(0, 255);
        int b = RANDOM.nextInt(0, 255);
        Color color = new Color(r, g, b);
        context.glClearColor(color);
        context.glClear();
        window.swapBuffers();
    }
}
