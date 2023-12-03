package xyz.zcraft.agiled_arms;

import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AgiledArms implements ModInitializer {
    @Override
    public void onInitialize() {
        Registry.register(Registries.ENCHANTMENT, Identifier.of("agiled_arms", "agile"), AgileEnchantment.INSTANCE);
    }
}
