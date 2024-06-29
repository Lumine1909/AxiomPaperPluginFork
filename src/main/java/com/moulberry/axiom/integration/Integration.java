package com.moulberry.axiom.integration;

import com.moulberry.axiom.integration.plotsquared.PlotSquaredIntegration;
import com.moulberry.axiom.integration.worldguard.WorldGuardIntegration;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Integration {

    // todo: test if all this is working for both plotsqured, worldguard, plotsquared+worldguard

    public static boolean canBreakBlock(Player player, Block block) {
        if (!player.hasPermission("axiompatch.edit")) {
            return false;
        }
        return PlotSquaredIntegration.canBreakBlock(player, block) && WorldGuardIntegration.canBreakBlock(player, block.getLocation());
    }

    public static boolean canPlaceBlock(Player player, org.bukkit.Location loc) {
        if (!player.hasPermission("axiompatch.edit")) {
            return false;
        }
        return PlotSquaredIntegration.canPlaceBlock(player, loc) && WorldGuardIntegration.canPlaceBlock(player, loc);
    }

    public static SectionPermissionChecker checkSection(Player player, World world, int cx, int cy, int cz) {
        SectionPermissionChecker plotSquared = PlotSquaredIntegration.checkSection(player, world, cx, cy, cz);
        SectionPermissionChecker worldGuard = WorldGuardIntegration.checkSection(player, world, cx, cy, cz);
        SectionPermissionChecker permission = player.hasPermission("axiompatch.edit") ? SectionPermissionChecker.ALL_ALLOWED : SectionPermissionChecker.NONE_ALLOWED;
        return SectionPermissionChecker.combine(permission, SectionPermissionChecker.combine(plotSquared, worldGuard));
    }

}
