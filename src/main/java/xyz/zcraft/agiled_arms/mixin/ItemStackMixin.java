package xyz.zcraft.agiled_arms.mixin;

import com.google.common.collect.Multimap;
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

@Mixin(ItemStack.class)
public class ItemStackMixin {
    private static final UUID ATTACK_DAMAGE_MODIFIER_ID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    @Inject(method = "getAttributeModifiers", at = @At("RETURN"))
    void getAttributeModifiers(EquipmentSlot slot, CallbackInfoReturnable<Multimap<EntityAttribute, EntityAttributeModifier>> cir) {
        if (slot.getType().equals(EquipmentSlot.Type.HAND)) {
            final ItemStack self = (ItemStack) (Object) this;
            final float agileLvl = EnchantmentHelper.getLevel(AgileEnchantment.INSTANCE, self);
            if (agileLvl > 0) {
                final Collection<EntityAttributeModifier> entityAttributeModifiers = cir.getReturnValue().get(EntityAttributes.GENERIC_ATTACK_SPEED);
                entityAttributeModifiers.add(new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Agile enchantment", agileLvl * 0.4, EntityAttributeModifier.Operation.ADDITION));
            }
        }
    }
}
