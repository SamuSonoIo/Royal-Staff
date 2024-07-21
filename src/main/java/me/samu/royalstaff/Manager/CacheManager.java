package me.samu.royalstaff.Manager;

import me.samu.royalstaff.Main;

import java.util.ArrayList;
import java.util.UUID;

public class CacheManager {

    private Main main;

    public CacheManager(Main main) { this.main = main; }
    // STAFF CHAT
    public ArrayList<UUID> staffchat;
    // FREEZE
    public ArrayList<UUID> freezati;

    public void init() {
        staffchat = new ArrayList<>();
        freezati = new ArrayList<>();
    }
}

