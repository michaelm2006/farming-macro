package wtf.sable.mixin;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.KeyboardHandler;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.sable.FarmingMacro;

import net.minecraft.client.input.KeyEvent;

@Mixin(KeyboardHandler.class)
public class KeyboardHandlerMixin {
    @Inject(method = "keyPress", at = @At("HEAD"), cancellable = true)
    private void onKeyPress(long handle, int action, KeyEvent event, CallbackInfo ci) {
        if (event.key() == 66 && action == GLFW.GLFW_PRESS) {
            FarmingMacro.ENABLED = !FarmingMacro.ENABLED;

            KeyMapping.releaseAll();

            FarmingMacro.LOGGER.info(FarmingMacro.ENABLED ? "Macro started" : "Macro stopped");
        }

        if (FarmingMacro.ENABLED && event.key() != 66) {
            ci.cancel();
        }
    }
}