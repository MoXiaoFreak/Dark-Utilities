package net.darkhax.darkutils.features.charms;

import java.util.List;

import net.darkhax.bookshelf.item.ItemInventory;
import net.darkhax.bookshelf.util.StackUtils;
import net.darkhax.darkutils.DarkUtils;
import net.darkhax.darkutils.handler.GuiHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemNullCharm extends ItemCharm {

    @Override
    public ActionResult<ItemStack> onItemRightClick (World world, EntityPlayer player, EnumHand handIn) {

        if (!world.isRemote) {
            player.openGui(DarkUtils.instance, GuiHandler.FILTER, world, 0, 0, 0);
        }

        return super.onItemRightClick(world, player, handIn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {

        for (final ItemStack blacklisted : ItemInventory.getContents(stack)) {
            if (!blacklisted.isEmpty()) {
                tooltip.add(blacklisted.getDisplayName());
            }
        }
    }

    public static boolean isBlackListed (ItemStack stack, ItemStack charmStack) {

        for (final ItemStack blacklisted : ItemInventory.getContents(charmStack)) {
            if (StackUtils.areStacksEqual(stack, blacklisted, true)) {
                return true;
            }
        }

        return false;
    }
}
