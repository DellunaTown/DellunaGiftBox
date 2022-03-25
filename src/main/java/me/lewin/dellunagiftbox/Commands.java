package me.lewin.dellunagiftbox;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]){
        if (args.length == 0) return true;
        if (!(sender instanceof Player)) return true;
        Plugin plugin = JavaPlugin.getPlugin(Main.class);
        Player player = (Player) sender;
        if (player.isOp()) {
            if (args.length != 1) return true;
            player.openInventory(new InventoryGUI(plugin.getServer().getPlayer(args[0])).getInventory());
        }
        return true;
    }
}
