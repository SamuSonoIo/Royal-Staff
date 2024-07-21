package me.samu.royalstaff.Listener;

import me.samu.royalstaff.Main;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class VanishInteract implements Listener {
    private Main main;

    public VanishInteract(Main main) { this.main = main; }

    private ArrayList<UUID> vanish = new ArrayList<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        NamespacedKey key = new NamespacedKey(main, "vanish");
        if (e.getItem() != null && e.getItem().hasItemMeta() && Objects.requireNonNull(e.getItem().getItemMeta()).getPersistentDataContainer().has(key, PersistentDataType.STRING) && main.getConfigManager().haPermesso(e.getPlayer())) {
            Player player = e.getPlayer();
            for ( Player ps : Bukkit.getOnlinePlayers() ) {
                if (vanish.contains(player.getUniqueId())) {
                    ps.showPlayer(main, player);
                } else {
                    ps.hidePlayer(main, player);
                }
            }
            if (vanish.contains(player.getUniqueId())) {
                player.sendMessage(Objects.requireNonNull(main.getConfig().getString("Vanish-Disabled")));
                vanish.remove(player.getUniqueId());
            } else {
                player.sendMessage(Objects.requireNonNull(main.getConfig().getString("Vanish-Success")));
                vanish.add(player.getUniqueId());
            }
        }
    }
}
