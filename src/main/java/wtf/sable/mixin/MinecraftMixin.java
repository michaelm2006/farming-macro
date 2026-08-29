package wtf.sable.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.sable.FarmingMacro;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    private static boolean forward = true;
    private static boolean wasMoving = false;

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (FarmingMacro.ENABLED) {
            Minecraft mc = Minecraft.getInstance();

            boolean moving = mc.player.getDeltaMovement().horizontalDistanceSqr() > 0.0001;
            boolean vertMove = mc.player.getDeltaMovement().y > 0.0001;

            if (!vertMove) {
                if (wasMoving && !moving) {
                    forward = !forward;
                }

                wasMoving = moving;
            }

            mc.options.keyUp.setDown(forward);
            mc.options.keyDown.setDown(!forward);

            mc.options.keyAttack.setDown(true);
        }
    }
}