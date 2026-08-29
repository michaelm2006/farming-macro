package wtf.sable.mixin;

import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
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
        if (event.key() == 66 && action == 1) {
            FarmingMacro.MacroRunning = !FarmingMacro.MacroRunning;

            FarmingMacro.LOGGER.info(FarmingMacro.MacroRunning ? "Macro started" : "Macro stopped");
        }

        if (FarmingMacro.MacroRunning && event.key() != 66) {
            ci.cancel();
        }
    }
}