package com.thesalamanderdj.idleresources.item.custom;

import com.google.common.collect.ImmutableMap;
import com.thesalamanderdj.idleresources.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class wooden_crank extends Item {

    private static final Map<Block, Item> WOODEN_CRANK_ITEM_CRAFT =
            new ImmutableMap.Builder<Block, Item>()
                    .put(ModBlocks.OAK_GEN_TIER_I.get(), Blocks.OAK_LOG.asItem())
                    .build();

    public wooden_crank(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide())  {
            Level level = pContext.getLevel();
            BlockPos positionClicked = pContext.getClickedPos();
            Block blockClicked = level.getBlockState(positionClicked).getBlock();

            if(canCrank(blockClicked)) {
                ItemEntity entityItem = new ItemEntity(level,
                        positionClicked.getX(), positionClicked.getY(), positionClicked.getZ(),
                        new ItemStack(WOODEN_CRANK_ITEM_CRAFT.get(blockClicked), 1));

                level.addFreshEntity(entityItem);

            }
        }

        return super.useOn(pContext);
    }

    private boolean canCrank(Block block) {
        return WOODEN_CRANK_ITEM_CRAFT.containsKey(block);
    }
}
