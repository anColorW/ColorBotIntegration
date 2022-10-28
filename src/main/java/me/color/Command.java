package me.color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.xml.crypto.Data;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Command implements CommandExecutor {

    public Command(Main pl) {
        pl.getCommand("connect").setExecutor(this);
    }


    public static String randomCode(){
        String letters  = "ABCDEFGHIJKLMNOPRSTUWVXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()";
        String code = "";
        Random rand = new Random();
        for (int i = 0; i < 8; i++){
            code = code + letters.charAt(rand.nextInt(letters.length()));
        }
        return code;
    }

    public static String code;
    public static String id;
    public static Player sender;
    public static boolean check;

    public static boolean isNum(String s)
    {
        try
        { long i = Long.parseLong(s); return true; }

        catch(NumberFormatException er)
        { return false; }
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String s, String[] args) {

        Player p = (Player) sender;

        if(args.length != 1 || args[0] == null){
            p.sendMessage("Usage /connect [DISCORD ID]");
            return true;
        }
            code = randomCode();
            id = args[0];
            Command.sender = p;

            DataBase db = new DataBase();

            if(!isNum(args[0]) || Main.bot.getUserById(args[0]) == null){
                p.sendMessage("Invalid ID");
                return true;
            }

            try {
                if(db.exist("SELECT * FROM mcdcint WHERE MinecraftNickName = '" + p.getName() + "';")){
                    p.sendMessage("Your Minecraft account is already associated!");
                    return true;
                }

                if(db.exist("SELECT * FROM mcdcint WHERE DiscordID = " + args[0])){
                    p.sendMessage("Given discord id is already associated!");
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


            sendPM(Main.bot.getUserById(args[0]), code);
            p.sendMessage("Message has been sent, check your discord!");
            check = true;

        return false;
    }



    public static void sendPM(User user, String code) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor("Verify Yourself",  null, "https://github.com/anColorW/");
        eb.setDescription("**Your verification code is " + code + "\n Resend code and verify yourself!**");
        try {
            user.openPrivateChannel().complete().sendMessageEmbeds(eb.build()).queue();

        } catch (ErrorResponseException ignored) {
        }
    }
}
