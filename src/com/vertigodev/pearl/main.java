package com.vertigodev.pearl;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class main extends JavaPlugin implements Listener {
    public static final Logger log = Logger.getLogger("Minecraft");

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println();
        System.out.println("[VertigoDev - NPD] Has been enabled");
        System.out.println();
    }

    public void onDisable() {
        System.out.println();
        System.out.println("[VertigoDev - NPD] Has been disabled");
        System.out.println();
    }

    @EventHandler
    public void onEntityDamageEvent(PlayerTeleportEvent event) {
        Player p = event.getPlayer();
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            event.setCancelled(true);
            p.setNoDamageTicks(1);
            p.teleport(event.getTo());
        }
    }
}
