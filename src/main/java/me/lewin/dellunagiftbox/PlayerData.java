package me.lewin.dellunagiftbox;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerData {
    public static void creatDataFile(OfflinePlayer player){
        String uuid = player.getUniqueId().toString();
        FileConfiguration config = getPlayerConfig(uuid);
        ArrayList<ItemStack> list = new ArrayList<>();
        config.set("item", list);
        saveDataFile(config, getPlayerFile(uuid));
    }

    public static final Plugin PLUGIN = JavaPlugin.getPlugin(Main.class);
    public static File getPlayerFile(String uuid) {
        return new File(PLUGIN.getDataFolder() + "\\Player", uuid + ".dat");
    }

    public static FileConfiguration getPlayerConfig(String uuid) {
        return YamlConfiguration.loadConfiguration(getPlayerFile(uuid));
    }

    public static void saveDataFile(FileConfiguration config, File file) {
        try {
            config.save(file);
        } catch (IOException e) {
            System.out.println("§cFile I/O Error!!");
        }
    }
}
