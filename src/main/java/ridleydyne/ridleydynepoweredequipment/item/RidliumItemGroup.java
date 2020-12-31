package ridleydyne.ridleydynepoweredequipment.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import ridleydyne.ridleydynepoweredequipment.init.ModItems;

public class RidliumItemGroup extends ItemGroup {
    public RidliumItemGroup(String label) {
        super(label);
        //this.setNoTitle();
    }
    
    @Override
	public ItemStack createIcon() {
		return new ItemStack(ModItems.RIDLIUM_ENERGY_CRYSTAL.getItem());
    }
    
    @Override
	public boolean hasSearchBar() {
		return false;
	}
}