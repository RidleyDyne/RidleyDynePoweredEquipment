package ridleydyne.ridleydynepoweredequipment.item;

import javax.annotation.Nullable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ShovelItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import ridleydyne.ridleydynepoweredequipment.RidleyDynePoweredEquipment;
import ridleydyne.ridleydynepoweredequipment.energy.FECapabilityProvider;
import ridleydyne.ridleydynepoweredequipment.energy.ItemFEBattery;
import ridleydyne.ridleydynepoweredequipment.itemtier.ModItemTier;
import java.util.List;
import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.util.text.ITextComponent;
import ridleydyne.ridleydynepoweredequipment.init.ModItems;

public class RidliumPoweredShovel extends ShovelItem {
    public RidliumPoweredShovel(double EnergyCapacity) {
        // tier, attack damage, attack speed, builder ??
        super(ModItemTier.TIER1, -1, -2.4F, ModItems.defaultItemProperties(1));
    }


    /*
    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos) != 0.0F) {
           stack.damageItem(1, entityLiving, (entity) -> {
              entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
           });
        }
  
        return true;
     }


    public IEnergyStorage getEnergyStorage(ItemStack stack) {
		if (CapabilityEnergy.ENERGY == null)
			return storedEnergy;

		return stack.getCapability(CapabilityEnergy.ENERGY).orElse(storedEnergy);
    }

    @Override
	public double getDurabilityForDisplay(ItemStack stack) {
		IEnergyStorage energy = this.getEnergyStorage(stack);
		double stored = energy.getMaxEnergyStored() - energy.getEnergyStored();
		return stored / energy.getMaxEnergyStored();
    }
    
    @Override
	public boolean showDurabilityBar(ItemStack stack) {
		return this.getEnergyStored(stack) < this.getEnergyCapacity(stack);
    }
    

    @Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
		return new FECapabilityProvider(new ItemFEBattery(stack, EnergyCapacity));
    }
    


    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockPos blockpos1 = blockpos.offset(context.getFace());
        PlayerEntity playerEntity = context.getPlayer();

        IEnergyStorage energy = this.getEnergyStorage(context.getItem());

        // This is just debug code to see how this works
        energy.receiveEnergy(10, false);

        return ActionResultType.SUCCESS;

        
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        tooltip.add(new TranslationTextComponent("MAX FE: ").appendString(String.valueOf(this.storedEnergy.getMaxEnergyStored())).mergeStyle(TextFormatting.GRAY));
        tooltip.add(new TranslationTextComponent("FE Stored: ").appendString(String.valueOf(this.storedEnergy.getEnergyStored())).mergeStyle(TextFormatting.GRAY));
    }
    */
}
