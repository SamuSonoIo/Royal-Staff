package me.samu.royalstaff.Manager;

import me.samu.royalstaff.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private Main main;

    public ItemManager(Main main) { this.main = main; }

    // VANISH
    ItemStack vanish = new ItemStack(Material.BLAZE_ROD);
    public void initVanish() {

        NamespacedKey key = new NamespacedKey(main, "vanish");

        ItemMeta vanishmeta = vanish.getItemMeta();
        PersistentDataContainer pdc = vanishmeta.getPersistentDataContainer();
        pdc.set(key, PersistentDataType.STRING, "item");
        vanishmeta.setDisplayName("§c§lVANISH");

        List<String> lore = new ArrayList<>();
        lore.add("§7Go into vanish and disappear to other players!");
        vanishmeta.setLore(lore);
        vanish.setItemMeta(vanishmeta);

    }

    // RANDOM PLAYER
    ItemStack randomPlayer = new ItemStack(Material.DIAMOND);

    public void initRandomPlayer() {

        NamespacedKey key = new NamespacedKey(main, "randomplayer");

        ItemMeta randommeta = randomPlayer.getItemMeta();
        PersistentDataContainer pdc = randommeta.getPersistentDataContainer();
        pdc.set(key, PersistentDataType.STRING, "item");
        randommeta.setDisplayName("§b§lRANDOM PLAYER");

        List<String> lore = new ArrayList<>();
        lore.add("§7Teleport to a random player!");
        randommeta.setLore(lore);
        randomPlayer.setItemMeta(randommeta);

    }

    public void setInventario(Player player) {
        Inventory inventario = player.getInventory();
        inventario.clear();
        inventario.setItem(0, vanish);
        inventario.setItem(2, randomPlayer);
    }
}

