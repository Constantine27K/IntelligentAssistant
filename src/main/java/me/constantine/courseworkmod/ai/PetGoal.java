package me.constantine.courseworkmod.ai;

import me.constantine.courseworkmod.CourseWorkMod;
import net.minecraft.server.v1_16_R3.*;

import java.util.EnumSet;

public class PetGoal extends PathfinderGoal {

    private final EntityInsentient pet;
    private EntityLiving owner;

    private final double speed; // pet's speed
    private final float maxDistance; // distance between owner & pet
    private final float minDistance;

    private double x;
    private double y;
    private double z;

    public PetGoal(EntityInsentient pet, double speed, float minDistance, float maxDistance) {
        this.pet = pet;
        this.speed = speed;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.a(EnumSet.of(Type.MOVE));
    }

    @Override
    public boolean a() {
        // Will start event if this is true
        // runs every tick
        if (!CourseWorkMod.MOB.stand) return false;
        this.owner = this.pet.getGoalTarget();
        if (this.owner == null) {
            return false;
        } else if (this.pet.getDisplayName() == null) {
            return false;
        } else if (!(this.pet.getDisplayName().toString().contains(this.owner.getName()))) {
            return false;
        } else if (getDistanceSq(owner) < (double) (minDistance * minDistance)) {
            return false;
        } else if (this.owner.h(this.pet) > (double) (this.maxDistance * this.maxDistance)) {
            // distance is too far then teleport pet
            pet.setPosition(this.owner.locX(), this.owner.locY(), this.owner.locZ());
            return false;
        } else {
            // follow owner
            Vec3D vec = RandomPositionGenerator.a((EntityCreature) this.pet, 16, 7, this.owner.getPositionVector());
            // in air
            if (vec == null)
                return false;
            this.x = this.owner.locX(); // x
            this.y = this.owner.locY(); // y
            this.z = this.owner.locZ(); // z
            return true;
            // call upon c()
        }
    }

    public boolean b() {
        // run every tick as long as its true (repeats c)
        if (!CourseWorkMod.MOB.stand) return false;
        return !this.pet.getNavigation().m() && this.owner.h(this.pet) < (double) (this.maxDistance * this.maxDistance);
    }

    public void c() {
        if (!CourseWorkMod.MOB.stand) return;
        // runner :)
        this.pet.getNavigation().a(this.x + 1, this.y, this.z + 1, this.speed);
    }


    public void d() {
        // runs when done (b is false)
        this.owner = null;
    }

    public double getDistanceSq(Entity entityIn) {
        return getDistanceSq(entityIn.getPositionVector());
    }

    public double getDistanceSq(Vec3D vec) {
        double d0 = pet.locX() - vec.x;
        double d1 = pet.locY() - vec.y;
        double d2 = pet.locZ() - vec.z;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }
}
