package me.constantine.courseworkmod.commands.mob;

import me.constantine.courseworkmod.CourseWorkMod;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MobStandByCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if(CourseWorkMod.MOB != null){
                CourseWorkMod.MOB.stand = false;
                Bukkit.broadcastMessage("Mob is in StandBy mode\nRun /notstandby to cancel");
            }
        } else {
            Bukkit.broadcastMessage("Only a player can use this command");
        }
        return false;
    }
}
