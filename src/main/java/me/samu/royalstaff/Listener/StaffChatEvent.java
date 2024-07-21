package me.samu.royalstaff.Listener;

import me.samu.royalstaff.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class StaffChatEvent implements Listener {
    private Main main;

    public StaffChatEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void AsyncChatEvent(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        Set<Player> recipients = new HashSet<>(e.getRecipients());

        if (main.getCacheManager().staffchat.contains(player.getUniqueId())) {
            recipients.clear();
            for (UUID ps : main.getCacheManager().staffchat) {
                Player recipient = Bukkit.getPlayer(ps);
                if (recipient != null) {
                    recipients.add(recipient);
                    e.setMessage("§2§l[STAFF ONLY] §f" + e.getMessage());
                }
            }
        } else {
            for (UUID ps : main.getCacheManager().staffchat) {
                Player recipient = Bukkit.getPlayer(ps);
                if (recipient != null) {
                    recipients.remove(recipient);
                }
            }
        }
        e.getRecipients().clear();
        e.getRecipients().addAll(recipients);
    }
}
