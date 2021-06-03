package me.constantine.courseworkmod.commands;

import me.constantine.courseworkmod.CourseWorkMod;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnclaimCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (CourseWorkMod.landClaimer != null) {
                for(Block block : CourseWorkMod.landClaimer.getList())
                    block.setType(CourseWorkMod.landClaimer.getInitialMaterial());
                CourseWorkMod.landClaimer.clean();
                Bukkit.broadcastMessage("Territory unclaimed");
            }
        } else {
            Bukkit.broadcastMessage("Only a player can use this command");
        }
        return false;
    }
}
