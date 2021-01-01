package ridleydyne.ridleydynepoweredequipment.energy;

import net.minecraft.item.ItemStack;
import net.minecraftforge.energy.EnergyStorage;

public class ItemFEBattery extends EnergyStorage {
    private final ItemStack container;
	
	private static String TAG_MAX_ENERGY = "max_energy";
	private static String TAG_CURRENT_ENERGY = "energy";

    public ItemFEBattery(ItemStack itemStack, int capacity, int maxEnergyIO) {
		super(getEnergyCapacity(itemStack, capacity), maxEnergyIO, maxEnergyIO);
		this.container = itemStack;
	}

	private static int getEnergyCapacity(ItemStack stack, int capacity) {
        if( !stack.hasTag() || !stack.getTag().contains(TAG_MAX_ENERGY) )
            return capacity;

        return stack.getTag().getInt(TAG_MAX_ENERGY);
	}
	
	public void setEnergyCapacity(int newCapacity) {
        container.getOrCreateTag().putInt(TAG_MAX_ENERGY, newCapacity);
        this.capacity = newCapacity;

        // Ensure the current stored energy is up to date with the new max.
        this.receiveEnergy(1, false);
	}
	
    @Override
    public int receiveEnergy(int receivedEnergy, boolean simulate) {

		// Don't receive if we're not allowing it
		if (!this.canReceive()) {
			return 0;
		}
		// Don't receive a negative amount of energy
		if (receivedEnergy <= 0) {
			return 0;
		}

		// If we're already full, dont bother processing any more
		if (this.energy >= this.capacity) {
			return 0;
		}

		// Get current energy
		int currentEnergy = this.getEnergyStored();

		// Don't receive more than the max receive
		int adjustedEnergyReceived = Math.min(receivedEnergy, this.maxReceive);
		
		// Check for an overflow (because the super doesn't)
		if ((Integer.MAX_VALUE - receivedEnergy) < this.capacity) {
			adjustedEnergyReceived = Integer.MAX_VALUE - capacity;
		}

		// If the amount given would go over capacity, limit it to just enough to fill up
		if ((adjustedEnergyReceived + currentEnergy) > this.capacity) {
			adjustedEnergyReceived = this.capacity - currentEnergy;
		}

		// Receive the energy
		int actualEnergyReceived = super.receiveEnergy(adjustedEnergyReceived, simulate);

		// If we're not simulating, write the NBT tag to the item
		if(!simulate) {
			container.getOrCreateTag().putInt(TAG_CURRENT_ENERGY, this.energy);
			container.getOrCreateTag().putInt(TAG_MAX_ENERGY, this.capacity);
		}

		return actualEnergyReceived;
	}
	
	
	@Override
    public int extractEnergy(int extractedEnergy, boolean simulate) {

		// Don't extract if we're not allowing extraction
		if (!this.canExtract()) {
			return 0;
		}

		// Don't extract a negative amount of energy
		if (extractedEnergy <= 0) {
			return 0;
		}

		// If we're already empty (or negative for some reason), then theres nothing to extract
		if (this.getEnergyStored() <= 0) {
			return 0;
		}

		// Don't extract more than max extract
		int adjustedEnergyExtracted = Math.min(extractedEnergy, this.maxExtract);

		// If the amount would empty the tank, adjust
		if ((this.getEnergyStored() - adjustedEnergyExtracted) < 0) {
			adjustedEnergyExtracted = this.getEnergyStored();
		}

		// Extract the energy
		int actualEnergyExtracted = super.extractEnergy(adjustedEnergyExtracted, simulate);

		// If we're not simulating, write the NBT tag to the item
		if(!simulate) {
			container.getOrCreateTag().putInt(TAG_CURRENT_ENERGY, this.energy);
			container.getOrCreateTag().putInt(TAG_MAX_ENERGY, this.capacity);
		}

		return actualEnergyExtracted;
	}
	
	@Override
    public int getEnergyStored()
    {
		if( !container.hasTag() || !container.getTag().contains(TAG_CURRENT_ENERGY) ) 
		{
			return 0;
		}

		// Ensure the NBT tag matches the internal value
		int nbtValue = container.getOrCreateTag().getInt(TAG_CURRENT_ENERGY);
		
		// Trust the NBT value
		if (nbtValue != this.energy) {
			this.energy = nbtValue;
		}

        return nbtValue;
    }

    @Override
    public int getMaxEnergyStored()
    {
		if( !container.hasTag() || !container.getTag().contains(TAG_MAX_ENERGY) )
		{
			return capacity;
		}

		// Ensure the NBT tag matches the internal value
		int nbtValue = container.getOrCreateTag().getInt(TAG_MAX_ENERGY);
		
		// Trust the NBT value
		if (nbtValue > 0) {
			if (nbtValue != this.capacity) {
				this.capacity = nbtValue;
			}
		}

        return nbtValue;
    }

    @Override
    public boolean canExtract()
    {
        return true;
    }

    @Override
    public boolean canReceive()
    {
        return true;
    }


}
