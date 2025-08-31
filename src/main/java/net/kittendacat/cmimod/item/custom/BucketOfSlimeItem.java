package net.kittendacat.cmimod.item.custom;

import net.kittendacat.cmimod.CMIMod;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class BucketOfSlimeItem extends Item {
    private static final int MAX_USE_TIME = 32;

    public BucketOfSlimeItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {

        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 600, 3));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 600, 20));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.OOZING, 6000, 0));

        }

        if (user instanceof PlayerEntity playerEntity) {
            if (!playerEntity.isInCreativeMode()) {
                stack.decrement(1);
                if (stack.isEmpty()) {
                    return new ItemStack(Items.BUCKET); // Return empty bucket if stack is empty
                } else {
                    if (!playerEntity.getInventory().insertStack(new ItemStack(Items.BUCKET))) {
                        playerEntity.dropItem(new ItemStack(Items.BUCKET), false); // Drop bucket if inventory is full
                    }
                }
            }
            return stack; // Return the modified stack
        } else {
            stack.decrementUnlessCreative(1, user); // Handle non-player entities
            return stack;
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return MAX_USE_TIME; // Drinking duration: 32 ticks
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK; // Drinking animation
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand); // Initiates drinking action
    }
}