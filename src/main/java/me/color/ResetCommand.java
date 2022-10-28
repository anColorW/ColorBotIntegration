package me.color;

import net.dv8tion.jda.api.JDA;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ResetCommand implements CommandExecutor {

    public ResetCommand(Main pl) {
        pl.getCommand("reset").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String s, String[] args) {

        sender.sendMessage("Bot is Restarting...");

        while(Main.bot.getStatus() == JDA.Status.CONNECTED){
            sender.sendMessage("Bot has been loaded up!");
            break;
        }


        return false;
    }
}
