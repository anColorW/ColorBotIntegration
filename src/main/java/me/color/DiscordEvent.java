package me.color;


import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.*;



public class DiscordEvent extends ListenerAdapter{


    EmbedBuilder eb = new EmbedBuilder();




    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e){
            EmbedBuilder eb = new EmbedBuilder();
            eb.setAuthor("Welcome!",  null, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQSz3ZN7dvQU1aAxZKVlxnMRz-6njSouoa3Qaar1g&s");
            eb.setColor(Color.magenta);
            eb.setDescription("➦ Join our server and connect your account via /connect \n" +
                    "➥ Our website: https://xyz.com, Have Fun! ");
            e.getUser().openPrivateChannel().complete().sendMessageEmbeds(eb.build()).queue();
    }


    @Override
    public void onMessageReceived(MessageReceivedEvent e){

        if(Command.check){
        if(e.getChannel().asPrivateChannel().getUser().getId().equalsIgnoreCase(Command.id)){
            if(e.getMessage().getContentDisplay().equalsIgnoreCase(Command.code)){
                        DataBase db = new DataBase();
                        try {
                            if(db.exist("SELECT * FROM mcdcint WHERE DiscordID = " + e.getAuthor().getId() + ";") ||
                                    db.exist("SELECT * FROM mcdcint WHERE MinecraftNickName = '" + Command.sender.getName() + "';")
                            ){
                                Command.sender.sendMessage("Your account or Given discord id is already associated!");
                                eb.setAuthor("Failed!",  null, "https://github.com/anColorW/");
                                eb.setDescription("Discord or Minecraft account is already associated!");
                                eb.setColor(Color.red);
                                e.getAuthor().openPrivateChannel().complete().sendMessageEmbeds(eb.build()).queue();
                            }else{
                                db.insertQuery("INSERT INTO mcdcint (DiscordID, MinecraftNickName) VALUES (" + e.getAuthor().getId() + ", '" + Command.sender.getName() + "');");


                                Command.sender.sendMessage("Successfully connected accounts!");
                                eb.setAuthor("Success!",  null, "https://github.com/anColorW/");
                                eb.setDescription("You have correctly connected your account");
                                eb.setColor(Color.green);
                                e.getAuthor().openPrivateChannel().complete().sendMessageEmbeds(eb.build()).queue();
                            }

                            //tru
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        Command.check = false;
            }
        }
        }
    }

    @Override
    public void onGenericEvent(GenericEvent event)
    {
        if (event instanceof ReadyEvent) {

            for(int i = 0; i < Main.bot.getGuildById(Main.GuildID).getMembers().size(); i++){

                System.out.println(Main.bot.getGuildById(Main.GuildID).getMembers().get(i).getEffectiveName());

                /*if(Main.bot.getGuildById(Main.GuildID).getMembers().get(i).getUser().getId().equals("833049086516133909")){
                    System.out.println("balls");
                }*/
            }

        }
    }

}
