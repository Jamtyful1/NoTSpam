package me.powerranger.tspam;

import me.powerranger.tspam.versionsupport.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class NoTSpamMain extends JavaPlugin{

    private Ping ping;
    private static Logger log = Bukkit.getLogger();
    private static void out(String s){
        log.info("[NoTSpam] " + s);
    }

    public void onEnable(){
        if (pingSetup()) {

            getServer().getPluginManager().registerEvents(new ChatEvent(ping), this);
            out("tNoTSpam setup was successful!");
            out("tThe plugin setup process is complete!");
            out("tEnabled!");

        } else {

            getLogger().severe("Failed to setup NoTSpam!");
            getLogger().severe("This server version is not supported by NoTSpam!");

            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    public void onDisable(){
        out("NoTSpam Disabled");
    }

    public boolean pingSetup(){
        String version;

        try {

            version = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3];

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

        if (version.equals("v1_9_R1")) {
            ping = new Ping_1_9();

        } else if (version.equals("v1_12_R1")) {
            ping = new Ping_1_12();
        }
        return ping != null;

    }
}
