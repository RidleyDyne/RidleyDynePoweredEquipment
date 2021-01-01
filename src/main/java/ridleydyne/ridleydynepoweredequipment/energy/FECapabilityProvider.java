package ridleydyne.ridleydynepoweredequipment.energy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class FECapabilityProvider implements ICapabilityProvider {
	private ItemStack itemStack;
	private int energyCapacity;
	private int maxEnergyIO;
	private LazyOptional<IEnergyStorage> capability = LazyOptional.of(() -> new ItemFEBattery(itemStack, energyCapacity, maxEnergyIO));
	
	public FECapabilityProvider(ItemStack itemStack, int energyCapacity, int maxEnergyIO) {
		this.itemStack = itemStack;
		this.energyCapacity = energyCapacity;
		this.maxEnergyIO = maxEnergyIO;
	}

	@Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == CapabilityEnergy.ENERGY ? capability.cast() : LazyOptional.empty();
    }
}