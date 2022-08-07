package com.dreamtea.item;

import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.time.Instant;

import static net.minecraft.block.NoteBlock.NOTE;


public class TuningFork{

  public static boolean fork(LivingEntity player, BlockState state, BlockPos pos, World world){
    ItemStack using = player.getStackInHand(Hand.MAIN_HAND);

    if (using.getItem() instanceof ITuningFork tuningFork
      && tuningFork.getNote(using) != -1)
    {
      state = state.with(NOTE, tuningFork.getNote(using));
      world.setBlockState(pos, state, 3);
      return true;
    }
    return false;
  }
}
