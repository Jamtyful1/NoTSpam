package me.powerranger.tspam;

import me.powerranger.tspam.versionsupport.Ping;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener{
    final String onlyTRegex = "^t+$";
    final String tCommandReplace = "^t+/";
    final String tCommandRegex = "^(t+/).*";
    Ping pingGetter;

    public ChatEvent( Ping p){
        pingGetter = p;
    }

    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent e){
        if(e.getMessage().matches(tCommandRegex)){
            e.getPlayer().performCommand(e.getMessage().replaceAll(tCommandReplace, ""));
            e.getPlayer().sendMessage(ChatColor.DARK_RED +  "You put a t before your command! If you want to tell someone the command use anything but a t before the command! ");
            e.setCancelled(true);
        }
         if(e.getMessage().matches(onlyTRegex)){
            e.getPlayer().sendMessage(ChatColor.RED + "Your ping is: " + ChatColor.BOLD + ChatColor.AQUA + pingGetter.getPing(e.getPlayer()));
            e.setCancelled(true);
        }
    }

}