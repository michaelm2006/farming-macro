package wtf.sable.mixin;

import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.input.MouseButtonInfo;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wtf.sable.FarmingMacro;

import net.minecraft.client.input.KeyEvent;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
    @Inject(method = "onButton", at = @At("HEAD"), cancellable = true)
    private void onButton(final long handle, final MouseButtonInfo rawButtonInfo, final @MouseButtonInfo.Action int action, CallbackInfo ci) {
        if (FarmingMacro.MacroRunning) {
            ci.cancel();
        }
    }

    @Inject(method = "onScroll", at = @At("HEAD"), cancellable = true)
    private void onScroll(final long handle, final double xoffset, final double yoffset, CallbackInfo ci) {
        if (FarmingMacro.MacroRunning) {
            ci.cancel();
        }
    }

    @Inject(method = "turnPlayer", at = @At("HEAD"), cancellable = true)
    private void turnPlayer(final double mousea, CallbackInfo ci) {
        if (FarmingMacro.MacroRunning) {
            ci.cancel();
        }
    }
}