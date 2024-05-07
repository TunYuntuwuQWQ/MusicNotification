package net.kosmo.music.mixin;

import net.kosmo.music.ClientMusic;
import net.kosmo.music.gui.JukeboxScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen extends Screen {
    protected MixinTitleScreen(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        if (ClientMusic.config.SHOW_TITLE_SCREEN_BUTTON) {
            this.addDrawableChild(new TexturedButtonWidget(12, 0, 20, 20, 0, 0, 20, JukeboxScreen.JUKEBOX_ICON_TEXTURE, 64, 64, (button) -> this.client.setScreen(new JukeboxScreen(this))));
        }
    }
}
