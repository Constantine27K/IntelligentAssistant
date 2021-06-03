package me.constantine.courseworkmod.utils.executors.build;

import me.constantine.courseworkmod.CourseWorkMod;
import org.bukkit.Location;
import org.bukkit.Material;

public class EmptySpace {

    public static void empty() {
        Location minLoc = CourseWorkMod.landClaimer.getMinLoc();
        Location maxLoc = CourseWorkMod.landClaimer.getMaxLoc();
        for (int x = (int) minLoc.getX(); x <= (int) maxLoc.getX(); x++) {
            for (int y = (int) minLoc.getY() + 1; y <= (int) maxLoc.getY(); y++) {
                for (int z = (int) minLoc.getZ(); z <= (int) maxLoc.getZ(); z++) {
                    Location cord = new Location(maxLoc.getWorld(), x, y, z);
                    cord.getBlock().setType(Material.AIR);
                }
            }
        }
    }
}
