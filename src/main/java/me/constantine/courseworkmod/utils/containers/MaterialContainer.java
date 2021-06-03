package me.constantine.courseworkmod.utils.containers;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.AbstractMap.SimpleEntry;

import java.util.ArrayList;
import java.util.List;

public class MaterialContainer {
    public static List<SimpleEntry<Material, Integer>> house1 = new ArrayList<>();
    public static List<SimpleEntry<Material, Integer>> house2 = new ArrayList<>();
    public static List<SimpleEntry<Material, Integer>> farm = new ArrayList<>();

    public static void getHouse1BlockCounter(Location min, Location max) {
        int cobblestoneCounter = (int) ((max.getX() - min.getX() + 1) * (max.getZ() - min.getZ() + 1)); //count foundation
        int oakPlanksCounter = 16;//count piles
        oakPlanksCounter += 2 * (max.getX() - min.getX() - 1) + 2 * (max.getZ() - min.getZ() - 1) - 2;//after first level
        int glassCounter = (int) (4 * (max.getX() - min.getX() - 1));//after third level (all)
        oakPlanksCounter += 2 * (max.getZ() - min.getZ() - 1) - 2;//after second level;
        oakPlanksCounter += 2 * (max.getZ() - min.getZ() - 1);//after third level
        oakPlanksCounter += cobblestoneCounter;
        house1.add(new SimpleEntry<>(Material.COBBLESTONE, cobblestoneCounter));
        house1.add(new SimpleEntry<>(Material.GLASS, glassCounter));
        house1.add(new SimpleEntry<>(Material.OAK_PLANKS, oakPlanksCounter));
    }

    public static void getHouse2BlockCounter(Location min, Location max) {
        int cobbleCounter = (int) (2 * (max.getX() - min.getX() + 1) * (max.getZ() - min.getZ() + 1) + 16);//after all
        int oakWoodCounter = (int) (2 * (max.getX() - min.getX() - 1 + max.getZ() - min.getZ() - 1) - 2);//after first
        int glassCounter = (int) (2 * (2 * (max.getX() - min.getX() - 1) + max.getZ() - min.getZ() - 1));//after all
        oakWoodCounter += max.getZ() - min.getZ() - 3;//after second
        oakWoodCounter += max.getZ() - min.getZ() - 1;//after all
        house2.add(new SimpleEntry<>(Material.COBBLESTONE, cobbleCounter));
        house2.add(new SimpleEntry<>(Material.OAK_WOOD, oakWoodCounter));
        house2.add(new SimpleEntry<>(Material.GLASS, glassCounter));
    }

    public static void getFarmBlockCounter(Location min, Location max) {
        farm.add(new SimpleEntry<>(Material.OAK_FENCE_GATE, 1));
        farm.add(new SimpleEntry<>(Material.OAK_FENCE, (int) (2 * (max.getX() - min.getX() + 1) + 2 * (max.getZ() - min.getZ() - 1))));
        farm.add(new SimpleEntry<>(Material.FARMLAND, (int) ((max.getX() - min.getX() - 1) * max.getZ() - min.getZ() - 1)));
    }
}
