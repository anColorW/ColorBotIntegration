package me.color;

import com.avaje.ebean.RawSql;
import com.avaje.ebean.SqlQuery;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import java.sql.*;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.EventListener;



public class Main extends JavaPlugin implements EventListener, Listener{

    static Main instance;


    public static  JDA bot;
    public static String servername = "NAZWASERVERA.PL";
    public static String GuildID = "1033468901524254810";


    FileConfiguration config = getConfig();

    public void onEnable() {

        config.addDefault("token", "TOKENHERE"); //<-- Put token in config.yml
        config.options().copyDefaults(true);
        saveConfig();

        initbot();
        new ResetCommand(this);
        instance = this;
        new Command(this);
        this.getServer().getPluginManager().registerEvents(new MinecraftEvent(), this);

    }



    public void initbot(){
        bot = JDABuilder.createDefault(config.getString("token"))
                .addEventListeners(new DiscordEvent())
                .setActivity(Activity.playing(servername))
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.DIRECT_MESSAGES)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .setChunkingFilter(ChunkingFilter.ALL)
                .build();
    }

    public static Main getInstance() {
        return instance;
    }


}
