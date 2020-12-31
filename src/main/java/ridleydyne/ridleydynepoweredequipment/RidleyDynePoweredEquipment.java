package ridleydyne.ridleydynepoweredequipment;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import ridleydyne.ridleydynepoweredequipment.init.ModItems;
import ridleydyne.ridleydynepoweredequipment.item.RidliumItemGroup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("ridleydynepoweredequipment")
public class RidleyDynePoweredEquipment
{
    public static final String MOD_ID = "ridleydynepoweredequipment";
    public static final ItemGroup ITEM_GROUP = new RidliumItemGroup(MOD_ID);
    public static final String NBT_ENERGY_CAPACITY = "rpeMaxEnergy";
    public static final String NBT_ENERGY_CURRENT = "rpeCurrentEnergy";

    public RidleyDynePoweredEquipment() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        // Register items
        @SubscribeEvent
        public static void RegisterItems(final Register<Item> event) {
            // Items
            for(ModItems item : ModItems.values()){
                event.getRegistry().register(item.getItem());
            }           
        }
    }

    
}
