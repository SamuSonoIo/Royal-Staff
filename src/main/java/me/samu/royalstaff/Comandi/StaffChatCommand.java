package me.samu.royalstaff.Comandi;

import me.samu.royalstaff.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Objects;

public class StaffChatCommand implements CommandExecutor {
    private Main main;

    public StaffChatCommand(Main main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (main.getConfigManager().haPermesso(player)) {
                if (!main.getCacheManager().staffchat.contains(player.getUniqueId())) {
                    player.sendMessage(Objects.requireNonNull(main.getConfig().getString("Joined-Chat")));
                    main.getCacheManager().staffchat.add(player.getUniqueId());
                } else {
                    main.getCacheManager().staffchat.remove(player.getUniqueId());
                    player.sendMessage(Objects.requireNonNull(main.getConfig().getString("Left-Chat")));
                }
            } else {
                player.sendMessage(Objects.requireNonNull(main.getConfig().getString("Not-Enough-Perms")));
            }
        }
        return false;
    }
}
