package com.beans.simplejoinmessages;

import com.beans.simplejoinmessages.events.events;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public final class main extends JavaPlugin implements Listener {

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
        {
            Bukkit.getPluginManager().registerEvents(this, this);
        }
        else
        {
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        getServer().getPluginManager().registerEvents(new events(this), this);
        config.options().copyDefaults(true);
        saveDefaultConfig();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        if (cmd.getName().equalsIgnoreCase("sjmreload")) {

            if (sender.hasPermission("sjm.reload"))
            {
                reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "[SimpleJoinMessages] " + ChatColor.GRAY + "Config reloaded Successfully!");
            }
            else {
                sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command.");
            }
        }
        return true;
    }
}
