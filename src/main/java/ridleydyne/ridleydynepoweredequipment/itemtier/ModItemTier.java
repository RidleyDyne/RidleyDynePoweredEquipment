package ridleydyne.ridleydynepoweredequipment.itemtier;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import java.util.function.Supplier;

/*
    maxUses
        Setting this to 0 disables durability

    Harvest Level
        0   Wood
        1   Stone / Gold
        2   Iron
        3   Diamond

    Efficiency:  0 means it will break dirt but not anything higher than dirt
                 5 - seemed ok, maybe a bit fast?
                 15 - faster than a diamond pick
*/

public enum ModItemTier implements IItemTier {
    BASE (4, 0, 5, 5, 25, () -> {
        return Ingredient.fromItems(Items.PAPER);
    }),

    UPGRADED (5, 0, 9, 7, 25, () -> {
        return Ingredient.fromItems(Items.PAPER);
    });

    ModItemTier(
            int harvestLevel,
            int maxUses, // Is actual number of uses before it breaks, for a pick
            float efficiency,
            float attackDamage,
            int enchantability,
            Supplier<Ingredient> repairMaterial
    ) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyValue<>(repairMaterial);
    }

    private int harvestLevel;
    private int maxUses;
    private float efficiency;
    private float attackDamage;
    private int enchantability;
    private LazyValue<Ingredient> repairMaterial;


    @Override
    public int getMaxUses() {
        return this.maxUses;
    }

    @Override
    public float getEfficiency() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }
    
}
