package org.jawbts.morebrick.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;

@Environment(EnvType.CLIENT)
public class BlackScreen extends Screen {
    public BlackScreen() {
        super(Text.literal(""));
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, width, height, ColorHelper.withAlpha(0.5F, 0x000000));
    }
}
