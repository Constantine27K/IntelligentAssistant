package me.constantine.courseworkmod.commands;

import me.constantine.courseworkmod.utils.executors.help.HelpExecutor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            HelpExecutor.process();
        } else {
            Bukkit.broadcastMessage("Only a player can use this command");
        }
        return false;
    }
}
