package me.constantine.courseworkmod.commands;

import me.constantine.courseworkmod.CourseWorkMod;
import me.constantine.courseworkmod.items.ItemManager;
import me.constantine.courseworkmod.utils.claimer.LandClaimer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WandCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (CourseWorkMod.MOB == null) {
                Bukkit.broadcastMessage("You should spawn a mob first");
                return false;
            }
            if (CourseWorkMod.PLAYER.getInventory().contains(ItemManager.wand))
                return false;
            Player player = (Player) sender;
            CourseWorkMod.landClaimer = new LandClaimer();
            player.getInventory().addItem(ItemManager.wand);
        } else {
            Bukkit.broadcastMessage("Only a player can send this command");
        }
        return false;
    }
}
