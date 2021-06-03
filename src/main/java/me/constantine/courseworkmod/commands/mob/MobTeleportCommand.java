package me.constantine.courseworkmod.commands.mob;

import me.constantine.courseworkmod.CourseWorkMod;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MobTeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (CourseWorkMod.MOB != null) {
                Location playerLocation = CourseWorkMod.PLAYER.getLocation();
                CourseWorkMod.MOB.teleportAndSync(playerLocation.getX() + 1, playerLocation.getY(), playerLocation.getZ());
                Bukkit.broadcastMessage("Mob teleported");
            }
        } else {
            Bukkit.broadcastMessage("Only a player can use this command");
        }
        return false;
    }
}
