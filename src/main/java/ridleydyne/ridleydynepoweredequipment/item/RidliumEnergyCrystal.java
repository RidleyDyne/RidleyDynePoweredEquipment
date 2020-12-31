package ridleydyne.ridleydynepoweredequipment.item;
import net.minecraft.item.Item;
import ridleydyne.ridleydynepoweredequipment.RidleyDynePoweredEquipment;

public class RidliumEnergyCrystal extends Item {
    public RidliumEnergyCrystal() {
        super(new Item.Properties()
            .maxStackSize(64)
            .group(RidleyDynePoweredEquipment.ITEM_GROUP)
        );
    }

}
