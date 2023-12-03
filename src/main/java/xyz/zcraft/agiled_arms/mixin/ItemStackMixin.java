package xyz.zcraft.agiled_arms.mixin;

import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.zcraft.agiled_arms.AgileEnchantment;

import java.util.Collection;
import java.util.UUID;

@Mixin(FabricItem.class)
public class FabricItemMixin {
    protected static final UUID ATTACK_SPEED_MODIFIER_ID = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");

    @Inject(method = "getAttributeModifiers", at = @At("RETURN"))
    void getAttributeModifiers(ItemStack stack, EquipmentSlot slot, CallbackInfoReturnable<Multimap<EntityAttribute, EntityAttributeModifier>> cir) {
        if (slot.getType().equals(EquipmentSlot.Type.HAND)) {
            final float agileLvl = EnchantmentHelper.getLevel(AgileEnchantment.INSTANCE, stack);
            if (agileLvl > 0) {
                final Collection<EntityAttributeModifier> entityAttributeModifiers = cir.getReturnValue().get(EntityAttributes.GENERIC_ATTACK_SPEED);
                entityAttributeModifiers.add(new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Agile enchantment", agileLvl * (2.0 / 3.0), EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
            }
        }
    }
}
