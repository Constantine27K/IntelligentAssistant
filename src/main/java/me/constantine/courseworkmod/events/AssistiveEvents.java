package me.constantine.courseworkmod.events;

import me.constantine.courseworkmod.CourseWorkMod;
import me.constantine.courseworkmod.items.ItemManager;
import me.constantine.courseworkmod.utils.claimer.LandClaimer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class AssistiveEvents implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        CourseWorkMod.PLAYER = event.getPlayer();
        Bukkit.broadcastMessage("Welcome " + ChatColor.GOLD +
                event.getPlayer().getDisplayName() +
                ChatColor.WHITE + "!");
        CourseWorkMod.PLAYER.getWorld().setTime(1000);
        if (CourseWorkMod.PLAYER.getInventory().contains(ItemManager.wand))
            CourseWorkMod.landClaimer = new LandClaimer();
    }

    @EventHandler
    public void onRightClickWand(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getItem() != null) {
                if (Objects.equals(event.getItem().getItemMeta(), ItemManager.wand.getItemMeta())) {
                    if (CourseWorkMod.landClaimer.getFirst() == null) {
                        Block picked = event.getClickedBlock();
                        CourseWorkMod.landClaimer.setFirst(picked);
                        Bukkit.broadcastMessage("You claimed first block at (" + picked.getX() +
                                ", " + picked.getY() + ", " + picked.getZ() + ")");
                    } else if (CourseWorkMod.landClaimer.getSecond() == null) {
                        Block picked = event.getClickedBlock();
                        CourseWorkMod.landClaimer.setSecond(picked);
                        Bukkit.broadcastMessage("You claimed second block at (" + picked.getX() +
                                ", " + picked.getY() + ", " + picked.getZ() + ")");
                    }
                    CourseWorkMod.landClaimer.process();
                }
            }
        }
    }

    @EventHandler
    public void onRightClickMob(PlayerInteractEntityEvent event) {
        if (CourseWorkMod.MOB == null) return;
        if (event.getRightClicked().getCustomName().contains("'s Mob")) {
            CourseWorkMod.PLAYER.openInventory(CourseWorkMod.mobInventory);
        }
    }

    public static void checkHealth(Plugin plugin) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if (CourseWorkMod.PLAYER != null) {
                if (CourseWorkMod.MOB != null) {
                    if (CourseWorkMod.MOB.dead) {
                        CourseWorkMod.pay = true;
                        CourseWorkMod.MOB = null;
                    }
                }
            }
        }, 40L, 0);
    }
}
