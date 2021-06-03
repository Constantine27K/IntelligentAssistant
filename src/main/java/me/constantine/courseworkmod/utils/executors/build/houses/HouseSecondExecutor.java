package me.constantine.courseworkmod.utils.executors.build.houses;

import me.constantine.courseworkmod.CourseWorkMod;
import me.constantine.courseworkmod.utils.executors.build.EmptySpace;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class HouseSecondExecutor {

    public static void process() {
        build();
    }

    private static void build() {
        Location start = CourseWorkMod.landClaimer.getMinLoc();
        Location end = CourseWorkMod.landClaimer.getMaxLoc();
        double min_x = start.getX();
        double min_y = start.getY();
        double min_z = start.getZ();
        double max_x = end.getX();
        double max_y = end.getY();
        double max_z = end.getZ();
        EmptySpace.empty();
        buildFoundation(start, min_x, min_y, min_z, max_x, max_z);
        buildPiles(start, min_x, min_y, min_z);
        buildPiles(start, max_x, min_y, min_z);
        buildPiles(start, min_x, min_y, max_z);
        buildPiles(start, max_x, min_y, max_z);
        buildFirstLevel(start, min_x, min_y + 1, min_z, max_x, max_z);
        buildSecondLevel(start, min_x, min_y + 2, min_z, max_x, max_y, max_z);
        buildThirdLevel(start, min_x, min_y + 3, min_z, max_x, max_y, max_z);
        buildFoundation(start, min_x, min_y + 4, min_z, max_x, max_z);
    }

    private static void buildSecondLevel(Location start, double min_x, double min_y, double min_z, double max_x, double max_y, double max_z) {
        for (int i = 0; i < max_x - min_x - 1; i++) {
            setDefaultLocation(start, min_x + 1, min_y, min_z);
            start.add(i, 0, 0);
            start.getBlock().setType(Material.GLASS);
        }
        for (int i = 0; i < max_z - min_z - 1; i++) {
            setDefaultLocation(start, min_x, min_y, min_z + 1);
            start.add(0, 0, i);
            if (i == (max_z - min_z - 2) / 2 - 1 || i == (max_z - min_z - 2) / 2)
                continue;
            start.getBlock().setType(Material.OAK_WOOD, true);
        }
        for (int j = 0; j < max_x - min_x - 1; j++) {
            setDefaultLocation(start, min_x + 1, min_y, max_z);
            start.add(j, 0, 0);
            start.getBlock().setType(Material.GLASS, true);
        }
        for (int j = 0; j < max_z - min_z - 1; j++) {
            setDefaultLocation(start, max_x, min_y, min_z + 1);
            start.add(0, 0, j);
            start.getBlock().setType(Material.GLASS, true);
        }
    }


    private static void buildThirdLevel(Location start, double min_x, double min_y, double min_z, double max_x, double max_y, double max_z) {
        for (int i = 0; i < max_x - min_x - 1; i++) {
            setDefaultLocation(start, min_x + 1, min_y, min_z);
            start.add(i, 0, 0);
            start.getBlock().setType(Material.GLASS);
        }
        for (int i = 0; i < max_z - min_z - 1; i++) {
            setDefaultLocation(start, min_x, min_y, min_z + 1);
            start.add(0, 0, i);
            start.getBlock().setType(Material.OAK_WOOD, true);
        }
        for (int j = 0; j < max_x - min_x - 1; j++) {
            setDefaultLocation(start, min_x + 1, min_y, max_z);
            start.add(j, 0, 0);
            start.getBlock().setType(Material.GLASS, true);
        }
        for (int j = 0; j < max_z - min_z - 1; j++) {
            setDefaultLocation(start, max_x, min_y, min_z + 1);
            start.add(0, 0, j);
            start.getBlock().setType(Material.GLASS, true);
        }
    }

    private static void buildFirstLevel(Location start, double min_x, double min_y, double min_z, double max_x, double max_z) {
        for (int j = 0; j < max_x - min_x - 1; j++) {
            setDefaultLocation(start, min_x + 1, min_y, min_z);
            start.add(j, 0, 0);
            start.getBlock().setType(Material.OAK_WOOD, true);
        }
        for (int i = 0; i < max_z - min_z - 1; i++) {
            setDefaultLocation(start, min_x, min_y, min_z + 1);
            start.add(0, 0, i);
            if (i == (max_z - min_z - 2) / 2 - 1 || i == (max_z - min_z - 2) / 2)
                continue;
            start.getBlock().setType(Material.OAK_WOOD, true);
        }
        for (int j = 0; j < max_x - min_x - 1; j++) {
            setDefaultLocation(start, min_x + 1, min_y, max_z);
            start.add(j, 0, 0);
            start.getBlock().setType(Material.OAK_WOOD, true);
        }
        for (int j = 0; j < max_z - min_z - 1; j++) {
            setDefaultLocation(start, max_x, min_y, min_z + 1);
            start.add(0, 0, j);
            start.getBlock().setType(Material.OAK_WOOD, true);
        }
    }


    private static void buildPiles(Location start, double min_x, double min_y, double min_z) {
        setDefaultLocation(start, min_x, min_y + 1, min_z);
        for (int i = 0; i < 4; i++) {
            start.add(0, i, 0);
            start.getBlock().setType(Material.COBBLESTONE);
            setDefaultLocation(start, min_x, min_y + 1, min_z);
        }
    }

    private static void buildFoundation(Location start, double min_x, double min_y, double min_z, double max_x, double max_z) {
        for (int i = 0; i < max_x - min_x + 1; i++) {
            for (int j = 0; j < max_z - min_z + 1; j++) {
                setDefaultLocation(start, min_x, min_y, min_z);
                start.add(i, 0, j);
                start.getBlock().setType(Material.COBBLESTONE);
            }
        }
        CourseWorkMod.landClaimer.clean();
    }

    private static void setDefaultLocation(Location location, double x, double y, double z) {
        location.setX(x);
        location.setY(y);
        location.setZ(z);
    }
}