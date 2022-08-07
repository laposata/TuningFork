package com.dreamtea.mixin;

import com.dreamtea.item.ITuningFork;
import com.dreamtea.utils.PropertyUtils;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.TridentItem;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(TridentItem.class)
public class TridentMixin extends Item implements ITuningFork {

  public TridentMixin(Settings settings) {
    super(settings);
  }

  private boolean setNote(BlockState state, ItemStack item){
    int note = PropertyUtils.note(state);
    if(note == -1){
      return false;
    }
    item.getNbt().putInt("tune", note);
    return true;
  }

  public ActionResult useOnBlock(ItemUsageContext context){
    return setNote(context.getWorld().getBlockState(context.getBlockPos()), context.getStack())
      ? ActionResult.SUCCESS : ActionResult.FAIL;
  }

  @Override
  public int getNote(ItemStack item) {
    return item.getNbt().getInt("tune");
  }

  public boolean hasGlint(ItemStack stack) {
    return stack.hasEnchantments() || getNote(stack) != -1;
  }

  @Override
  public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
    if(getNote(stack) != -1){
      tooltip.add(Text.of("Tuned to: " + getNote(stack)));
    }
  }

}
