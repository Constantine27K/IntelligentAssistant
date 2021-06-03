package me.constantine.courseworkmod.utils.executors.help;

import me.constantine.courseworkmod.CourseWorkMod;
import me.constantine.courseworkmod.utils.containers.MessagesContainer;

public class MobHelpExecutor {
    public static void process() {
        if (CourseWorkMod.PLAYER != null)
            CourseWorkMod.PLAYER.sendRawMessage(MessagesContainer.MOB_HELP);
    }
}
