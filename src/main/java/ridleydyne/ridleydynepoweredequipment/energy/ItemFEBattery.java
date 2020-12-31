package ridleydyne.ridleydynepoweredequipment.energy;

import net.minecraft.item.ItemStack;
import net.minecraftforge.energy.EnergyStorage;
import ridleydyne.ridleydynepoweredequipment.helpers.NBTHelper;

public class ItemFEBattery extends EnergyStorage {
    private final ItemStack container;
    private boolean canExtract = true;
    private boolean canReceive = true;

    public ItemFEBattery(ItemStack stack, int capacity) {
		super(capacity);
		this.container = stack;
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		int energy = NBTHelper.getInt(this.container, "Energy");
		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

		if (!simulate) {
			energy += energyReceived;
			NBTHelper.setInt(this.container, "Energy", energy);
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		int energy = NBTHelper.getInt(this.container, "Energy");
		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

		if (!simulate) {
			energy -= energyExtracted;
			NBTHelper.setInt(this.container, "Energy", energy);
		}

		return energyExtracted;
	}

	@Override
	public int getEnergyStored() {
		return NBTHelper.getInt(this.container, "Energy");
	}

	@Override
	public int getMaxEnergyStored() {
		return this.capacity;
	}
	
	@Override
	public boolean canExtract() {
		return this.canExtract;
	}
	
	@Override
	public boolean canReceive() {
		return this.canReceive;
	}
}
