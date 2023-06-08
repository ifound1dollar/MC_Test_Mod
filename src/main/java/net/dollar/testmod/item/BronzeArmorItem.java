package net.dollar.testmod.item;

import net.dollar.testmod.util.IDamageHandlingArmor;
import net.dollar.testmod.util.ModUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class BronzeArmorItem extends ArmorItem implements IDamageHandlingArmor {
    boolean isFullSet;


    public BronzeArmorItem(ArmorMaterial p_40386_, Type p_266831_, Properties p_40388_) {
        super(p_40386_, p_266831_, p_40388_);
    }


    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        //do nothing if on client side OR if not chestplate
        if (level.isClientSide || LivingEntity.getEquipmentSlotForItem(stack) != EquipmentSlot.CHEST) { return; }

        //check for correct equipment, then set isFullSet accordingly
        boolean hasHelmet = player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.BRONZE_HELMET.get();
        boolean hasChestplate = player.getItemBySlot(EquipmentSlot.CHEST).getItem() == ModItems.BRONZE_CHESTPLATE.get();
        boolean hasLeggings = player.getItemBySlot(EquipmentSlot.LEGS).getItem() == ModItems.BRONZE_LEGGINGS.get();
        boolean hasBoots = player.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.BRONZE_BOOTS.get();
        isFullSet = hasHelmet && hasChestplate && hasLeggings && hasBoots;
    }

    @Override
    public float onDamaged(LivingEntity entity, EquipmentSlot slot, DamageSource source, float amount) {
        //if not chestplate OR not full set, do not alter damage
        if (slot != EquipmentSlot.CHEST || !isFullSet) { return amount; }

        //entity.sendSystemMessage(Component.literal(String.valueOf(source.type())));
        //entity.sendSystemMessage(Component.literal(String.valueOf(ModUtils.getDamageCategory(source))));

        //if taking damage from sharp source, reduce damage taken
        if (ModUtils.getDamageCategory(source) == ModUtils.DamageCategory.SHARP) {
            return amount * 0.50f;  //REDUCE BY 50%
        }
        return amount;  //if reaches here, return original amount
    }

    //USE THIS METHOD FOR GILDED BRONZE
//    @Override
//    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
//        return true;
//    }
}