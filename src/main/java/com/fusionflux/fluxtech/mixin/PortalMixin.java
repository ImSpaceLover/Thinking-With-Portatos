package com.fusionflux.fluxtech.mixin;

import com.qouteall.immersive_portals.PehkuiInterface;
import com.qouteall.immersive_portals.portal.Portal;
import com.qouteall.immersive_portals.portal.PortalLike;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Portal.class)
public abstract class PortalMixin extends Entity implements PortalLike {

    public PortalMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow public abstract Vec3d transformLocalVec(Vec3d localVec);


    @Inject(method = "transformVelocity", at = @At("TAIL"), cancellable = true,remap = false)
    public void transformVelocity(Entity entity, CallbackInfo ci) {
       if(entity.getVelocity().length()<1.5){
           double velocityavg = entity.getVelocity().multiply(1.64835432639D,1.64835432639D,1.64835432639D).length();
            entity.setVelocity(entity.getVelocity().normalize().multiply(velocityavg));
       }else{
           entity.setVelocity((entity.getVelocity().normalize().multiply(1)));
       }

    }
}