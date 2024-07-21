package me.samu.royalstaff;

import me.samu.royalstaff.Comandi.FreezeCommand;
import me.samu.royalstaff.Comandi.StaffChatCommand;
import me.samu.royalstaff.Comandi.StaffMode;
import me.samu.royalstaff.Listener.*;
import me.samu.royalstaff.Manager.CacheManager;
import me.samu.royalstaff.Manager.ConfigManager;
import me.samu.royalstaff.Manager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public final class Main extends JavaPlugin {
    private ConfigManager configManager;
    private ItemManager itemManager;
    private CacheManager cacheManager;
    public final ArrayList<UUID> staffmode = new ArrayList<>();
    public ArrayList<UUID> freezati = new ArrayList<>();

    @Override
    public void onEnable() {
        // LOGGER
        Bukkit.getLogger().info("Plugin started correctly!");
        // CONFIG MANAGER
        configManager = new ConfigManager(this);
        configManager.initConfig();
        // ITEM MANAGER
        itemManager = new ItemManager(this);
        // CACHE MANAGER
        cacheManager = new CacheManager(this);
        cacheManager.init();
        // LISTENERS
        getServer().getPluginManager().registerEvents(new VanishInteract(this), this);
        getServer().getPluginManager().registerEvents(new RandomPlayerInteract(this), this);
        getServer().getPluginManager().registerEvents(new DropEvent(this), this);
        getServer().getPluginManager().registerEvents(new FreezeEvent(this), this);
        getServer().getPluginManager().registerEvents(new StaffChatEvent(this), this);
        getServer().getPluginManager().registerEvents(new ModerationEvent(this), this);
        // COMANDI
        Objects.requireNonNull(getCommand("staffmode")).setExecutor(new StaffMode(this));
        Objects.requireNonNull(getCommand("freeze")).setExecutor(new FreezeCommand(this));
        Objects.requireNonNull(getCommand("staffchat")).setExecutor(new StaffChatCommand(this));
        // ITEMS
        itemManager.initVanish();
        itemManager.initRandomPlayer();
    }

    @Override
    public void onDisable() {
        // LOGGER
        Bukkit.getLogger().info("Plugin stopped correctly! Bye <3");
    }

    // RETURN CONFIG MANAGER
    public ConfigManager getConfigManager() { return configManager; }

    // RETURN ITEM MANAGER
    public ItemManager getItemManager() { return itemManager; }

    // RETURN CACHE MANAGER
    public CacheManager getCacheManager() { return cacheManager; }
}