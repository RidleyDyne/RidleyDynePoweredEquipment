package ridleydyne.ridleydynepoweredequipment.item;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ShovelItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import ridleydyne.ridleydynepoweredequipment.energy.FECapabilityProvider;
import ridleydyne.ridleydynepoweredequipment.energy.ItemFEBattery;
import ridleydyne.ridleydynepoweredequipment.init.ModItems;
import ridleydyne.ridleydynepoweredequipment.itemtier.ModItemTier;

public class RidliumPoweredShovel extends ShovelItem {
    private static final IEnergyStorage EMPTY_ENERGY_STORAGE = new EnergyStorage(0);
    private ModItemTier thisItemTier;

    public RidliumPoweredShovel(ModItemTier tier) {
        // tier, attack damage, attack speed, builder ??
        super(tier, 1.5F, -3.0F, ModItems.defaultItemProperties(1));

        this.thisItemTier = tier;
    }

    @Override
    public void fillItemGroup(@Nonnull ItemGroup group, @Nonnull NonNullList<ItemStack> items) {
        super.fillItemGroup(group, items);
        if (!isInGroup(group))
            return;

        ItemStack charged = new ItemStack(this);
        charged.getOrCreateTag().putInt(ItemFEBattery.TAG_CURRENT_ENERGY, thisItemTier.getEnergyCapacity());
        items.add(charged);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new FECapabilityProvider(stack, thisItemTier.getEnergyCapacity(), thisItemTier.getMaxEnergyIO());
    }

    @Override
	public boolean showDurabilityBar(ItemStack stack) {
        IEnergyStorage energy = this.getEnergyStorage(stack);
		if (energy.getMaxEnergyStored() == energy.getEnergyStored()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return stack.getCapability(CapabilityEnergy.ENERGY, null)
                .map(e -> 1D - (e.getEnergyStored() / (double) e.getMaxEnergyStored()))
                .orElse(0D);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    private IEnergyStorage getEnergyStorage(ItemStack stack) {
		if (CapabilityEnergy.ENERGY == null)
			return EMPTY_ENERGY_STORAGE;

		return stack.getCapability(CapabilityEnergy.ENERGY).orElse(EMPTY_ENERGY_STORAGE);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);

        IEnergyStorage energy = getEnergyStorage(stack);

        Minecraft mc = Minecraft.getInstance();
        if (world == null || mc.player == null) {
            return;
        }

        //boolean sneakPressed = Screen.hasShiftDown();

        tooltip.add(new TranslationTextComponent("Stored Energy: ")
            .appendString(String.valueOf(energy.getEnergyStored()))
            .appendString(" / ")
            .appendString(String.valueOf(energy.getMaxEnergyStored()))
            .appendString(" FE")
            .mergeStyle(TextFormatting.GRAY));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockPos blockpos1 = blockpos.offset(context.getFace());
        PlayerEntity playerEntity = context.getPlayer();

        IEnergyStorage energy = this.getEnergyStorage(context.getItem());

        // This is just debug code to see how this works
        if (playerEntity.isSneaking()) {
            energy.receiveEnergy(1000, false);
        } else {
            energy.extractEnergy(1000, false);
        }

        return ActionResultType.SUCCESS;

        
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


    

    
    
    
    

    @Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
		return new FECapabilityProvider(new ItemFEBattery(stack, EnergyCapacity));
    }
    


    
    
    
    */
}
