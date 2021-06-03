package me.constantine.courseworkmod.commands;

import me.constantine.courseworkmod.CourseWorkMod;
import me.constantine.courseworkmod.utils.containers.MaterialContainer;
import me.constantine.courseworkmod.utils.containers.MessagesContainer;
import me.constantine.courseworkmod.utils.executors.help.BuildHelpExecutor;
import me.constantine.courseworkmod.utils.executors.build.farm.FarmExecutor;
import me.constantine.courseworkmod.utils.executors.build.houses.HouseFirstExecutor;
import me.constantine.courseworkmod.utils.executors.build.houses.HouseSecondExecutor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.AbstractMap;
import java.util.List;

public class BuildCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            switch (args[0]) {
                case "house1":
                    if (checkWand() & checkMaterialsHouse1())
                        HouseFirstExecutor.process();
                    break;
                case "house2":
                    if (checkWand() & checkMaterialsHouse2())
                        HouseSecondExecutor.process();
                    break;
                case "farm":
                    if (checkWand() & checkMaterialsFarm())
                        FarmExecutor.process();
                    break;
                case "help":
                    BuildHelpExecutor.process();
                    break;
            }
        } else {
            Bukkit.broadcastMessage("Only a player can use this command");
        }
        return false;
    }

    private boolean checkWand() {
        if (CourseWorkMod.landClaimer == null) {
            Bukkit.broadcastMessage(MessagesContainer.WAND_NULL);
            return false;
        }
        if (CourseWorkMod.landClaimer.getList().size() == 0) {
            Bukkit.broadcastMessage(MessagesContainer.WAND_NULL);
            return false;
        }
        return true;
    }

    private boolean checkMaterialsHouse1() {
        if (CourseWorkMod.MOB == null) {
            Bukkit.broadcastMessage("You should spawn a mob first");
            return false;
        }
        MaterialContainer.getHouse1BlockCounter(CourseWorkMod.landClaimer.getMinLoc(),
                CourseWorkMod.landClaimer.getMaxLoc());
        for (AbstractMap.SimpleEntry<Material, Integer> entry : MaterialContainer.house1)
            if (!checkContains(entry)) return false;
        payResources(MaterialContainer.house1);
        return true;
    }

    private boolean checkMaterialsHouse2() {
        if (CourseWorkMod.MOB == null) {
            Bukkit.broadcastMessage("You should spawn a mob first");
            return false;
        }
        MaterialContainer.getHouse2BlockCounter(CourseWorkMod.landClaimer.getMinLoc(),
                CourseWorkMod.landClaimer.getMaxLoc());
        for (AbstractMap.SimpleEntry<Material, Integer> entry : MaterialContainer.house2)
            if (!checkContains(entry)) return false;
        payResources(MaterialContainer.house2);
        return true;
    }

    private boolean checkMaterialsFarm() {
        if (CourseWorkMod.MOB == null) {
            Bukkit.broadcastMessage("You should spawn a mob first");
            return false;
        }
        MaterialContainer.getFarmBlockCounter(CourseWorkMod.landClaimer.getMinLoc(),
                CourseWorkMod.landClaimer.getMaxLoc());
        for (AbstractMap.SimpleEntry<Material, Integer> entry : MaterialContainer.farm)
            if (!checkContains(entry)) return false;
        payResources(MaterialContainer.farm);
        return true;
    }

    private boolean checkContains(AbstractMap.SimpleEntry<Material, Integer> entry) {
        if (!CourseWorkMod.mobInventory.contains(entry.getKey(), entry.getValue())) {
            Bukkit.broadcastMessage("Your mob is short of needed resources");
            Bukkit.broadcastMessage(entry.getKey() + " - " + entry.getValue());
            return false;
        }
        return true;
    }

    private void payResources(List<AbstractMap.SimpleEntry<Material, Integer>> list) {
        ItemStack[] stack = new ItemStack[list.size()];
        for (int i = 0; i < list.size(); i++) {
            AbstractMap.SimpleEntry<Material, Integer> entry = list.get(i);
            ItemStack itemStack = new ItemStack(entry.getKey());
            itemStack.setAmount(entry.getValue());
            stack[i] = itemStack;
        }
        CourseWorkMod.mobInventory.removeItem(stack);
    }
}
