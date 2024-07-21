package me.samu.royalstaff.Comandi;

import me.samu.royalstaff.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class FreezeCommand implements CommandExecutor {

    private Main main;

    public FreezeCommand(Main main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (main.getConfigManager().haPermesso(player)) {
                if (args.length != 1) {
                    player.sendMessage(Objects.requireNonNull(main.getConfig().getString("Invalid-Usage-Freeze")));
                    return true;
                }
                if (Bukkit.getPlayerExact(args[0]) != null) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    assert target != null;
                    if (!main.freezati.contains(target.getUniqueId())) {
                        main.freezati.add(target.getUniqueId());
                        String nome = Objects.requireNonNull(main.getConfig().getString("Player-Freezed")).replace("{player}", target.getName());
                        player.sendMessage(nome);
                        target.sendMessage(Objects.requireNonNull(main.getConfig().getString("Been-Freezed")).replace("{player}", target.getName()));
                    } else {
                        main.freezati.remove(target.getUniqueId());
                        String nome = Objects.requireNonNull(main.getConfig().getString("Player-Unfreezed")).replace("{player}", target.getName());
                        player.sendMessage(nome);
                        target.sendMessage(Objects.requireNonNull(main.getConfig().getString("Been-Unfreezed")).replace("{player}", target.getName()));
                    }
                }
            }
        }
        return false;
    }
}
