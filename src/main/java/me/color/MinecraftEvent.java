package me.color;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.Calendar;
import java.util.Objects;

public class MinecraftEvent implements Listener {

    @EventHandler
    public void onMessage(PlayerChatEvent e){

        Calendar now = Calendar.getInstance();

        String minute = "00";

        switch (now.get(Calendar.MINUTE)){
            case 0:
                minute = "00";
                break;
            case 1:
                minute = "01";
                break;
            case 2:
                minute = "02";
                break;
            case 3:
                minute = "03";
                break;
            case 4:
                minute = "04";
                break;
            case 5:
                minute = "05";
                break;
            case 6:
                minute = "06";
                break;
            case 7:
                minute = "07";
                break;
            case 8:
                minute = "08";
                break;
            case 9:
                minute = "09";
                break;
            default:
                minute = String.valueOf(now.get(Calendar.MINUTE));

        }
        String currenttime = "[" + now.get(Calendar.HOUR_OF_DAY) + ":" + minute + "] ";


        Main.bot.getGuildById(Main.GuildID).getTextChannelById("1035114882938458192").sendMessage(currenttime + e.getPlayer().getName() + " -> " + e.getMessage()).submit();
    }

}
