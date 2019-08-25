package org.cyclops.commoncapabilities.api.capability.wrench;

import net.minecraft.entity.player.PlayerEntity;

/**
 * Indicates that something can be used as wrench.
 * This must be used by targets (like blocks or entities) to determine if the thing that activated them is a wrench.
 * The target is responsible for checking if the wrench can be used for this target using {@link IWrench#canUse(PlayerEntity, WrenchTarget)}.
 * If it can be used, the target must first call {@link IWrench#beforeUse(PlayerEntity, WrenchTarget)}.
 * After that the target can call its own logic.
 * Finally, the target must call {@link IWrench#afterUse(PlayerEntity, WrenchTarget)}.
 * @author rubensworks
 */
public interface IWrench {

    /**
     * Check if this wrench can be used.
     * @param player The player.
     * @param target The target that is being wrenched.
     * @return If it can be used.
     */
    public boolean canUse(PlayerEntity player, WrenchTarget target);

    /**
     * Called before the wrench is being used after the canUse check if done.
     * Useful for preparing some things for the wrench-action.
     * @param player The player.
     * @param target The target that is being wrenched.
     */
    public void beforeUse(PlayerEntity player, WrenchTarget target);

    /**
     * Called after the wrench is used.
     * Useful for doing cleanup work, or consuming energy.
     * @param player The player.
     * @param target The target that is being wrenched.
     */
    public void afterUse(PlayerEntity player, WrenchTarget target);

}
