package net.kittendacat.cmimod.item.custom;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.passive.CodEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FishyBucketOfTNTItem extends Item {
    public FishyBucketOfTNTItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        // Perform raycast to get the block the player is looking at
        BlockHitResult hitResult = (BlockHitResult) user.raycast(5.0, 0.0f, false);

        // Check if the raycast hit a block
        if (hitResult.getType() != HitResult.Type.BLOCK) {
            return TypedActionResult.pass(itemStack);
        }

        // Get the position adjacent to the targeted block
        BlockPos pos = hitResult.getBlockPos().offset(hitResult.getSide());

        // Check if the target position is replaceable
        if (!world.getBlockState(pos).isReplaceable()) {
            return TypedActionResult.pass(itemStack);
        }

        // Spawn Primed TNT
        for (int i = 0; i < 1; i++) {
            if (!world.isClient) {
                TntEntity primedTnt = new TntEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, user);
                world.spawnEntity(primedTnt);
                primedTnt.setFuse(35);
            }
        }

        // Place water block
        if (!world.isClient) {
            world.setBlockState(pos, Blocks.WATER.getDefaultState(), 3);
        }

        // Spawn Cod
        for (int i = 0; i < 1; i++) {
            if (!world.isClient) {
                CodEntity cod = new CodEntity(EntityType.COD, world);
                cod.setPosition(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
                world.spawnEntity(cod);
            }
        }

        if (!user.isInCreativeMode()) {
            itemStack.decrement(1);
            if (itemStack.isEmpty()) {
                return TypedActionResult.success(new ItemStack(Items.BUCKET), world.isClient());
            } else {
                if (!user.getInventory().insertStack(new ItemStack(Items.BUCKET))) {
                    user.dropItem(new ItemStack(Items.BUCKET), false);
                }
            }
        }

        return TypedActionResult.success(itemStack, world.isClient());

    }
}


















