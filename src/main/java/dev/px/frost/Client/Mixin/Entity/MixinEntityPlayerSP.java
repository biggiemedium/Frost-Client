package dev.px.frost.Client.Mixin.Entity;

import dev.px.frost.Client.Event.Entity.EventPlayerUpdate;
import dev.px.frost.Client.Frost;
import dev.px.frost.API.Manager.Manager;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP {

    @Inject(method = "onUpdate", at = @At("HEAD"), cancellable = true)
    public void onUpdate(CallbackInfo ci) {
        EventPlayerUpdate packet = new EventPlayerUpdate();
        Frost.EVENT_BUS.post(packet);
        
        Frost.handler.managers.forEach(Manager::onUpdate);

        if(packet.isCancelled()) {
            ci.cancel();
        }
    }
}
