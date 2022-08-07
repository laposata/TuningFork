package com.dreamtea.mixin;

import com.dreamtea.item.ITuningFork;
import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.dreamtea.item.TuningFork.fork;

@Mixin(NoteBlock.class)
public class NoteblockMixin {

  @Shadow @Final public static IntProperty NOTE;

  @Shadow
  private void playNote(@Nullable Entity entity, World world, BlockPos pos) {}

  @Inject(method = "onUse", at=@At("HEAD"), cancellable = true)
  public void tuneNoteblock(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand arg4,
                            BlockHitResult arg5, CallbackInfoReturnable<ActionResult> cir){
    if(!world.isClient && fork(player, state, pos, world)){
      this.playNote(player, world, pos);
      player.incrementStat(Stats.TUNE_NOTEBLOCK);
      cir.setReturnValue(ActionResult.CONSUME);
    }
  }
}
