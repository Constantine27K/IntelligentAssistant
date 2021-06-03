package me.constantine.courseworkmod;

import me.constantine.courseworkmod.commands.*;
import me.constantine.courseworkmod.commands.mob.*;
import me.constantine.courseworkmod.entity.Mob;
import me.constantine.courseworkmod.events.AssistiveEvents;
import me.constantine.courseworkmod.events.GeneralEvents;
import me.constantine.courseworkmod.items.ItemManager;
import me.constantine.courseworkmod.utils.claimer.LandClaimer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CourseWorkMod extends JavaPlugin {
    public static Player PLAYER;
    public static Mob MOB;
    public static Inventory mobInventory;
    public static LandClaimer landClaimer;
    public static boolean pay = false;

    @Override
    public void onEnable() {
        ItemManager.init();
        AssistiveEvents.checkHealth(this);
        GeneralEvents.checkPickaxe(this);
        GeneralEvents.checkSword(this);
        GeneralEvents.onNightTime(this);
        getServer().getPluginManager().registerEvents(new AssistiveEvents(), this);
        getServer().getPluginManager().registerEvents(new GeneralEvents(), this);
        Objects.requireNonNull(getCommand("mob")).setExecutor(new MobSpawnCommand());
        Objects.requireNonNull(getCommand("wand")).setExecutor(new WandCommand());
        Objects.requireNonNull(getCommand("unclaim")).setExecutor(new UnclaimCommand());
        Objects.requireNonNull(getCommand("build")).setExecutor(new BuildCommand());
        Objects.requireNonNull(getCommand("standby")).setExecutor(new MobStandByCommand());
        Objects.requireNonNull(getCommand("notstandby")).setExecutor(new MobNotStandByCommand());
        Objects.requireNonNull(getCommand("teleport")).setExecutor(new MobTeleportCommand());
        Objects.requireNonNull(getCommand("die")).setExecutor(new MobDieCommand());
        Objects.requireNonNull(getCommand("help")).setExecutor(new HelpCommand());
        Objects.requireNonNull(getCommand("tptomob")).setExecutor(new TpToMobCommand());
    }
}
