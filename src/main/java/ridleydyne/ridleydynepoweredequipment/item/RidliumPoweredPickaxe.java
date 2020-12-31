package ridleydyne.ridleydynepoweredequipment.item;

import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import ridleydyne.ridleydynepoweredequipment.RidleyDynePoweredEquipment;
import ridleydyne.ridleydynepoweredequipment.itemtier.ModItemTier;

public class RidliumPoweredPickaxe extends PickaxeItem {
    public RidliumPoweredPickaxe() {
        // tier, attack damage, attack speed, builder ??
        super(ModItemTier.BASE, 0, -2.4F, new Item.Properties()
        .maxStackSize(1)        
        .group(RidleyDynePoweredEquipment.ITEM_GROUP)
        );
    }
    
    
}
