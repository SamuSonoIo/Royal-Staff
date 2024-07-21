package me.samu.royalstaff.Listener;

import me.samu.royalstaff.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropEvent implements Listener {

    private Main main;

    public DropEvent(Main main) { this.main = main; }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (main.staffmode.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            System.out.println("Test");
        }
    }
}
