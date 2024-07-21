package me.samu.royalstaff.Manager;

import me.samu.royalstaff.Main;
import org.bukkit.entity.Player;

import java.util.Objects;

public class ConfigManager {
    private Main main;

    public ConfigManager(Main main) { this.main = main; }

    public void initConfig() {
        main.getConfig().getDefaults();
        main.saveDefaultConfig();
    }

    public boolean haPermesso(Player player) {
        return player.hasPermission(Objects.requireNonNull(main.getConfig().getString("StaffMode-Perms")));
    }
}