package com.vertigodev.pearl;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Logger;

public class main extends JavaPlugin implements Listener {
    public static final Logger log = Logger.getLogger("Minecraft");
    public String WebhookURL = "Your webhook URL here";
    DiscordWebhook webhook = new DiscordWebhook(WebhookURL);

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println();
        System.out.println("[VertigoDev - NPD] Has been enabled");
        webhook.addEmbed(new DiscordWebhook.EmbedObject().setDescription("Server is starting.").setColor(Color.green));
        try {
            webhook.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public void onDisable() {
        System.out.println();
        System.out.println("[VertigoDev - NPD] Has been disabled");
        webhook.addEmbed(new DiscordWebhook.EmbedObject().setDescription("Server is stopping.").setColor(Color.green));
        try {
            webhook.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    @EventHandler
    public void onEntityDamageEvent(PlayerTeleportEvent event) {
        Player p = event.getPlayer();
        Location loc = p.getLocation();
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            event.setCancelled(true);
            p.setNoDamageTicks(1);
            p.teleport(event.getTo());

            webhook.addEmbed(new DiscordWebhook.EmbedObject().setDescription(p.getDisplayName() + " has used an enderpearl.")
                    .addField("From:", String.format("%.2f", loc.getX()) + " , " + String.format("%.2f", loc.getY()) + " , " + String.format("%.2f", loc.getZ()), true)
                    .addField("To:", String.format("%.2f", event.getTo().getX()) + " , " + String.format("%.2f", event.getTo().getY()) + " , " + String.format("%.2f", event.getTo().getZ()), true)
                    .addField("Travelled:", String.format("%.2f", event.getTo().getX() - loc.getX()) + " , " + String.format("%.2f", event.getTo().getY() - loc.getY()) + " , " + String.format("%.2f", event.getTo().getZ() - loc.getZ()), false));
            try {
                webhook.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
