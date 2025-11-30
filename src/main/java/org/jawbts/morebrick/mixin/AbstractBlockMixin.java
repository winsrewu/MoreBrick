package org.jawbts.morebrick.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.sound.BlockSoundGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractBlock.class)
public interface AbstractBlockMixin {
    @Accessor("soundGroup")
    BlockSoundGroup getSoundGroup();
}
