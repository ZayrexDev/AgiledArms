package xyz.zcraft.agiled_arms;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class AgileEnchantment extends Enchantment {
    public static final AgileEnchantment INSTANCE = new AgileEnchantment(
            Enchantment.Rarity.UNCOMMON,
            EnchantmentTarget.WEAPON,
            new EquipmentSlot[]{EquipmentSlot.MAINHAND}
    );

    protected AgileEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
