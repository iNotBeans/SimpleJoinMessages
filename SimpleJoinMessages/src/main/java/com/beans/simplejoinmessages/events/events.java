package com.beans.simplejoinmessages.events;

import com.beans.simplejoinmessages.main;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class events implements Listener {

    main plugin;

    public events(main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();

        if(!player.hasPlayedBefore()){
            for (int i = 0; i < plugin.getConfig().getList("FirstJoinMessage").size(); i++){

                String FirstJoinMessage = plugin.getConfig().getList("FirstJoinMessage").get(i).toString();
                FirstJoinMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), FirstJoinMessage);

                Bukkit.broadcastMessage(FirstJoinMessage);
                event.setJoinMessage("");
            }

            for (int i = 0; i < plugin.getConfig().getList("MOTD-message").size(); i++){

                if(plugin.getConfig().getBoolean("MOTD-onFirstJoin")){

                    String MOTDMessage = plugin.getConfig().getList("MOTD-message").get(i).toString();
                    MOTDMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), MOTDMessage);

                    player.sendMessage(MOTDMessage);
                }
            }

            if(plugin.getConfig().getBoolean("TitleMessage-enabled") && plugin.getConfig().getBoolean("TitleMessageOnFirstJoin")){

                String TitleMessage = plugin.getConfig().getString("TitleMessage");
                TitleMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), TitleMessage);

                String SubtitleMessage = plugin.getConfig().getString("SubtitleMessage");
                SubtitleMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), SubtitleMessage);

                int FadeInTime = plugin.getConfig().getInt("FadeInTime");
                int StayTime = plugin.getConfig().getInt("StayTime");
                int FadeOutTime = plugin.getConfig().getInt("FadeOutTime");

                player.sendTitle(TitleMessage, SubtitleMessage, FadeInTime, StayTime, FadeOutTime);
            }
        }
        else
        {
            for (int i = 0; i < plugin.getConfig().getList("JoinMessage").size(); i++){

                String JoinText = plugin.getConfig().getList("JoinMessage").get(i).toString();
                JoinText = PlaceholderAPI.setPlaceholders(event.getPlayer(), JoinText);

                Bukkit.broadcastMessage(JoinText);
                event.setJoinMessage("");
            }
            for (int i = 0; i < plugin.getConfig().getList("MOTD-message").size(); i++){

                if(plugin.getConfig().getBoolean("MOTD-enabled")){

                    String MOTDMessage = plugin.getConfig().getList("MOTD-message").get(i).toString();
                    MOTDMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), MOTDMessage);

                    player.sendMessage(MOTDMessage);
                }
            }

            if(plugin.getConfig().getBoolean("TitleMessage-enabled")){

                String TitleMessage = plugin.getConfig().getString("TitleMessage");
                TitleMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), TitleMessage);

                String SubtitleMessage = plugin.getConfig().getString("SubtitleMessage");
                SubtitleMessage = PlaceholderAPI.setPlaceholders(event.getPlayer(), SubtitleMessage);

                int FadeInTime = plugin.getConfig().getInt("FadeInTime");
                int StayTime = plugin.getConfig().getInt("StayTime");
                int FadeOutTime = plugin.getConfig().getInt("FadeOutTime");

                player.sendTitle(TitleMessage, SubtitleMessage, FadeInTime, StayTime, FadeOutTime);
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event)
    {
        for (int i = 0; i < plugin.getConfig().getList("LeaveMessage").size(); i++){

            String LeaveText = plugin.getConfig().getList("LeaveMessage").get(i).toString();
            LeaveText = PlaceholderAPI.setPlaceholders(event.getPlayer(), LeaveText);

            Bukkit.broadcastMessage(LeaveText);
            event.setQuitMessage("");
        }
    }


}
