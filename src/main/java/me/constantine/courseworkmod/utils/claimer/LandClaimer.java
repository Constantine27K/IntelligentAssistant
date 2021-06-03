package me.constantine.courseworkmod.utils.claimer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LandClaimer {
    private Block first;
    private Block second;
    private final List<Block> list = new ArrayList<>();
    private Location minLoc, maxLoc;
    private Material initialMaterial;

    public void process() {
        if (first == null || second == null) return;
        World world = first.getWorld();
        list.add(first);
        list.add(second);
        double mainY = Math.min(first.getY(), second.getY());
        list.add(new Location(world, second.getX(), mainY, first.getZ()).getBlock());
        list.add(new Location(world, first.getX(), mainY, second.getZ()).getBlock());
        compare();
        if (!validateArea(minLoc, maxLoc)) {
            Bukkit.broadcastMessage("This area is too small for building");
            clean();
            return;
        }
        initialMaterial = first.getType();
        for (Block block : list) {
            block.setType(Material.OAK_PLANKS);
        }
    }

    public void clean() {
        first = null;
        second = null;
        list.clear();
    }

    private void compare() {
        List<Integer> listX = new ArrayList<>();
        List<Integer> listY = new ArrayList<>();
        List<Integer> listZ = new ArrayList<>();
        for (Block block : list)
            listX.add(block.getX());
        for (Block block : list)
            listY.add(block.getY());
        for (Block block : list)
            listZ.add(block.getZ());
        double minX = Collections.min(listX);
        double minY = Collections.min(listY);
        double minZ = Collections.min(listZ);
        double maxX = Collections.max(listX);
        double maxY = minY + 5;
        double maxZ = Collections.max(listZ);
        minLoc = new Location(first.getWorld(), minX, minY, minZ);
        maxLoc = new Location(first.getWorld(), maxX, maxY, maxZ);
    }

    private boolean validateArea(Location start, Location end) {
        if (Math.abs(start.getX() - end.getX()) > 4) {
            return Math.abs(start.getZ() - end.getZ()) > 4;
        }
        return false;
    }

    public Block getSecond() {
        return second;
    }

    public Block getFirst() {
        return first;
    }


    public List<Block> getList() {
        return list;
    }

    public Location getMinLoc() {
        return minLoc;
    }

    public Location getMaxLoc() {
        return maxLoc;
    }

    public Material getInitialMaterial() {
        return initialMaterial;
    }

    public void setSecond(Block second) {
        this.second = second;
    }

    public void setFirst(Block first) {
        this.first = first;
    }
}
