package me.samu.royalstaff.Listener;

import me.samu.royalstaff.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

public class ModerationEvent implements Listener {
    private Main main;

    public ModerationEvent(Main main) { this.main = main; }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (!main.getConfigManager().haPermesso(player) && main.getConfig().getBoolean("Chat-Moderation")) {
            for ( String parola : main.getConfig().getStringList("Blocked-Words")) {
                if (e.getMessage().contains(parola)) {
                    player.sendMessage(Objects.requireNonNull(main.getConfig().getString("Message-With-Words")));
                    for ( Player ps : Bukkit.getOnlinePlayers() ) {
                        if ( main.getConfigManager().haPermesso(ps) ) {
                            ps.sendMessage(Objects.requireNonNull(main.getConfig().getString("Staff-Notify")).replace("{player}", player.getName()));
                        }
                    }
                    e.setCancelled(true);
                }
            }
        }
    }
}
