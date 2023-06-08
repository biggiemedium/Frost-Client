package dev.px.frost.Client.Mixin.Minecraft;

import dev.px.frost.Client.Frost;
import dev.px.frost.api.Manager.Manager;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "shutdown", at = @At("HEAD"), cancellable = true)
    public void shutDownThread(CallbackInfo ci) {
        Frost.handler.managers.forEach(Manager::unload);
    }

}
