package com.noendgateway;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.entity.EnderDragon;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class NoEndGateway extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("NoEndGateway enabled.");
    }

    @EventHandler
    public void onDragonDeath(EntityDeathEvent event) {
        if (!(event.getEntity() instanceof EnderDragon)) return;
        World world = event.getEntity().getWorld();

        new BukkitRunnable() {
            @Override
            public void run() {
                for (int x = -200; x <= 200; x++) {
                    for (int z = -200; z <= 200; z++) {
                        for (int y = 0; y <= 100; y++) {
                            Block block = world.getBlockAt(x, y, z);
                            if (block.getType() == Material.END_GATEWAY) {
                                block.setType(Material.AIR);
                            }
                        }
                    }
                }
            }
        }.runTaskLater(this, 3L * 5);
    }
}
