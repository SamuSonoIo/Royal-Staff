package me.samu.royalstaff.Listener;

import me.samu.royalstaff.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeEvent implements Listener {
    private Main main;

    public FreezeEvent(Main main) { this.main = main; }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (main.getCacheManager().freezati.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
        }
    }
}
