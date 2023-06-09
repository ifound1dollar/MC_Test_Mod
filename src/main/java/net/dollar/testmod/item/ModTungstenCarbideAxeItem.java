package net.dollar.testmod.item;

import net.dollar.testmod.util.ModUtils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class ModTungstenCarbideAxeItem extends AxeItem {
    public ModTungstenCarbideAxeItem(Tier p_42961_, float p_42962_, float p_42963_, Properties p_42964_) {
        super(p_42961_, p_42962_, p_42963_, p_42964_);
    }

    /**
     * Performs normal hurtEnemy operations but with chance to apply additional effect(s)
     * @param stack ItemStack of this item
     * @param attackedEntity Attacked living entity
     * @param attacker Attacker (holder) living entity
     * @return Whether attack was successful???
     */
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity attackedEntity, LivingEntity attacker) {
        ModUtils.rollTungstenCarbideOnHitAndApply(attackedEntity, attacker, 20);    //sharp
        return super.hurtEnemy(stack, attackedEntity, attacker);
    }

}
