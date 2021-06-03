package me.constantine.courseworkmod.events;

import me.constantine.courseworkmod.CourseWorkMod;
import me.constantine.courseworkmod.entity.Mob;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GeneralEvents implements Listener {
    private static int pickaxeCounter = 0;
    private static int swordCounter = 0;
    private static int counter = 0;

    /*@EventHandler
    public void onMobAttack(EntityDamageEvent event) {
        if (event instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent event1 = (EntityDamageByEntityEvent) event;
            if (event1.getDamager() instanceof Player)
                Bukkit.broadcastMessage(ChatColor.GREEN + "Зачем ты меня атакуешь? Я твой союзник!");
        } else if (event.getEntity() instanceof Mob) {
            Bukkit.broadcastMessage(ChatColor.RED + "Я под атакой, нужна помощь");
        }
    }*/

    @EventHandler
    public void onPlayerNeeds(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            if (CourseWorkMod.PLAYER.getFoodLevel() < 8)
                Bukkit.broadcastMessage(ChatColor.BLUE + "Твой уровень еды на исходе, нужно поесть!");
        }
    }

    @EventHandler
    public void onPlayerGetDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (CourseWorkMod.PLAYER.getHealth() < 7) {
                Bukkit.broadcastMessage(ChatColor.RED + "Ты сильно ранен!");
            } else if (CourseWorkMod.PLAYER.getHealth() < 10 && CourseWorkMod.PLAYER.getHealth() > 7) {
                Bukkit.broadcastMessage(ChatColor.YELLOW + "Ты ранен!");
            }
            if (CourseWorkMod.PLAYER.getFoodLevel() != 20) {
                Bukkit.broadcastMessage(ChatColor.BLUE + "Нужно поесть, чтобы восстановить хп");
            }
        }
    }

    public static void onNightTime(Plugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (CourseWorkMod.PLAYER == null) return;
                if (CourseWorkMod.PLAYER.getWorld().getTime() >= 12200 &&
                        CourseWorkMod.PLAYER.getWorld().getTime() <= 13000 &&
                        counter == 0) {
                    Bukkit.broadcastMessage("Наступает ночь, необходимо поспать!");
                    counter++;
                } else if (CourseWorkMod.PLAYER.getWorld().getTime() >= 200 &&
                        CourseWorkMod.PLAYER.getWorld().getTime() <= 1000 &&
                        counter == 0) {
                    Bukkit.broadcastMessage("Наступил новый день, доброе утро!");
                    counter++;
                } else if (counter > 0)
                    counter = 0;

            }
        }.runTaskTimer(plugin, 100L, 10);
    }

    public static void checkPickaxe(Plugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Inventory inventory = CourseWorkMod.PLAYER.getInventory();
                if (pickaxeCounter > 0)
                    return;
                if (inventory.contains(Material.DIAMOND_PICKAXE)) {
                    Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "Отлично, у тебя есть алмазная кирка!");
                    pickaxeCounter++;
                } else if (inventory.contains(Material.GOLDEN_PICKAXE)) {
                    Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "Отлично, у тебя есть золотая кирка, но нужно создать еще алмазную!");
                } else if (inventory.contains(Material.IRON_PICKAXE)) {
                    Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "Отлично, у тебя есть железная кирка, но можно создать еще золотую!");
                } else if (inventory.contains(Material.STONE_PICKAXE)) {
                    Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "Отлично, у тебя есть каменная кирка, но нужно создать еще железную!");
                } else if (inventory.contains(Material.WOODEN_PICKAXE)) {
                    Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "Отлично, у тебя есть стартовая кирка! Не останавливайся на этом!");
                }
            }
        }.runTaskTimer(plugin, 300, 36000);
    }

    public static void checkSword(Plugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (swordCounter > 0)
                    return;
                Inventory inventory = CourseWorkMod.PLAYER.getInventory();
                if (inventory.contains(Material.DIAMOND_SWORD)) {
                    Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "Отлично, у тебя есть алмазный меч! Вперед к дракону");
                    swordCounter++;
                } else if (inventory.contains(Material.GOLDEN_SWORD)) {
                    Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "Отлично, у тебя есть золотой меч, но нужно создать еще алмазный!");
                } else if (inventory.contains(Material.IRON_SWORD)) {
                    Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "Отлично, у тебя есть железный меч, но можно создать еще золотой!");
                } else if (inventory.contains(Material.STONE_SWORD)) {
                    Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "Отлично, у тебя есть каменный меч, но нужно создать еще железный!");
                } else if (inventory.contains(Material.WOODEN_SWORD)) {
                    Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "Отлично, у тебя есть стартовый меч! Это только начало!");
                }
            }
        }.runTaskTimer(plugin, 100, 36000);//Смотреть каждые пол часа

    }
}
