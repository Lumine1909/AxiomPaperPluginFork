package com.moulberry.axiom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public class V1_20_4Util {
    public static boolean hasDifferentLightProperties(BlockGetter blockView, BlockPos pos, BlockState oldState, BlockState newState) {
        return newState != oldState
                && (
                newState.getLightBlock(blockView, pos) != oldState.getLightBlock(blockView, pos)
                        || newState.getLightEmission() != oldState.getLightEmission()
                        || newState.useShapeForLightOcclusion()
                        || oldState.useShapeForLightOcclusion()
        );
    }
}
