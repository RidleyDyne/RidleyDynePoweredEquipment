package ridleydyne.ridleydynepoweredequipment.item;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
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


public class RidliumPoweredSword extends SwordItem {
    private static final IEnergyStorage EMPTY_ENERGY_STORAGE = new EnergyStorage(0);
    private ModItemTier thisItemTier;

    // Energy per use
    //  Iron tools have 250 uses. With 50000 FE, 200 FE/use is the same as iron (but this is rechargable)
    //  Diamond tools have 1561 uses.
    private static int energyPerUse = 200;
    private static float energizedDamage = 5.0F; // Extra damage if there is power left

    public RidliumPoweredSword(ModItemTier tier) {
        // tier, attack damage, attack speed, builder ??
        super(tier, 3, -2.4F, ModItems.defaultItemProperties(1));
        this.thisItemTier = tier;
    }

    private static DamageSource getDamageSource() {
        return DamageSource.MAGIC;
    }
    
    // Add a fully powered version in creative inventory
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

        tooltip.add(new TranslationTextComponent("Stored Energy: ")
            .appendString(String.valueOf(energy.getEnergyStored()))
            .appendString(" / ")
            .appendString(String.valueOf(energy.getMaxEnergyStored()))
            .appendString(" FE")
            .mergeStyle(TextFormatting.GRAY));
            
        tooltip.add(new TranslationTextComponent("+" + energizedDamage + " damage while powered")
            .mergeStyle(TextFormatting.DARK_RED));
    }

    // Actually require power to do things
    private boolean hasPower(ItemStack stack, int powerAmount) 
    {
        IEnergyStorage energy = getEnergyStorage(stack);
        return (energy.getEnergyStored() >= powerAmount);
    }

    private void spendPower(ItemStack stack, int powerAmount)
    {        
        IEnergyStorage energy = getEnergyStorage(stack);
        energy.extractEnergy(powerAmount, false);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (this.hasPower(stack, energyPerUse)) 
        {
            spendPower(stack, energyPerUse); 
            target.attackEntityFrom(getDamageSource(), energizedDamage);
        } 

        
        return true;                      
     }

}
