package me.samu.royalstaff.Comandi;

import me.samu.royalstaff.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class StaffMode implements CommandExecutor {
    private final Main main;

    private final HashMap<UUID, ItemStack[]> inventari = new HashMap<>();

    public StaffMode(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (!main.getConfigManager().haPermesso(player)) {
                player.sendMessage(Objects.requireNonNull(main.getConfig().getString("Not-Enough-Perms")));
                return true;
            }

            UUID uuid = player.getUniqueId();
            if (main.staffmode.contains(uuid)) {
                player.getInventory().setContents(inventari.remove(uuid));
                main.staffmode.remove(uuid);
                player.sendMessage(Objects.requireNonNull(main.getConfig().getString("StaffMode-Disabled")));
            } else {
                inventari.put(uuid, player.getInventory().getContents());
                main.getItemManager().setInventario(player);
                main.staffmode.add(uuid);
                player.sendMessage(Objects.requireNonNull(main.getConfig().getString("StaffMode-Success")));
            }
        }
        return true;
    }
}