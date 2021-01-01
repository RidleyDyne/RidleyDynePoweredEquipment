package ridleydyne.ridleydynepoweredequipment.itemtier;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import java.util.function.Supplier;

public enum ModItemTier implements IItemTier {
    // Harvest level
    //  0 - Wood tools
    //  1 - Stone / Gold tools
    //  2 - Iron
    //  3 - Diamond
    //  4 - Netherite
    // Max uses (setting to 0 disables durability)
    // Efficiency
    //  0 means it will break dirt but not anything higher than dirt
    //  5 - seemed ok, maybe a bit fast?
    //  15 - faster than a diamond pick
    // Attack damage
    // Enchantability
    // Repair material

    TIER1 (3, 0, 6.0F, 3.0F, 10, () -> { return Ingredient.EMPTY; }, 500000),
    TIER2 (4, 0, 9.0F, 4.0F, 15, () -> { return Ingredient.EMPTY; }, 5000000);

    ModItemTier(
            int harvestLevel,
            int maxUses, // Is actual number of uses before it breaks, for a pick
            float efficiency,
            float attackDamage,
            int enchantability,
            Supplier<Ingredient> repairMaterial,
            double energyCapacity
    ) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyValue<>(repairMaterial);
        this.energyCapacity = energyCapacity;
    }

    private int harvestLevel;
    private int maxUses;
    private float efficiency;
    private float attackDamage;
    private int enchantability;
    private LazyValue<Ingredient> repairMaterial;
    private double energyCapacity;

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

    public double getEnergyCapacity() {
        return this.energyCapacity;
    }
    
}
