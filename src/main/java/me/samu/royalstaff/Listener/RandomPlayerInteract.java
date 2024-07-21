package me.samu.royalstaff.Listener;

import me.samu.royalstaff.Main;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class RandomPlayerInteract implements Listener {
    private Main main;
    private List<UUID> join = new ArrayList<>();

    public RandomPlayerInteract(Main main) { this.main = main; };

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        join.add(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        join.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        NamespacedKey key = new NamespacedKey(main, "randomplayer");
        if (e.getItem() != null && e.getItem().hasItemMeta() && Objects.requireNonNull(e.getItem().getItemMeta()).getPersistentDataContainer().has(key, PersistentDataType.STRING) && main.getConfigManager().haPermesso(e.getPlayer())) {
            Random random = new Random();
            UUID target = join.get(random.nextInt(join.size()));
            Player player = e.getPlayer();
            if (Bukkit.getPlayer(target) == player) {
                player.sendMessage(Objects.requireNonNull(main.getConfig().getString("Error-RTP")));
            } else {
                player.teleport(Objects.requireNonNull(Bukkit.getPlayer(target)).getLocation());
                String nome = Objects.requireNonNull(main.getConfig().getString("RandomPlayer")).replace("{player}", Objects.requireNonNull(Bukkit.getPlayer(target)).getName());
                player.sendMessage(nome);
            }
        }
    }
}

