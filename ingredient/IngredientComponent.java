package org.cyclops.commoncapabilities.api.ingredient;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;

/**
 * A IngredientComponent is a type of component that can be used as ingredients inside recipes.
 *
 * @param <T> The instance type.
 * @param <M> The matching condition parameter, may be Void. Instances MUST properly implement the equals method.
 * @author rubensworks
 */
public final class IngredientComponent<T, M> implements IForgeRegistryEntry<IngredientComponent<?, ?>> {

    public static final IForgeRegistry<IngredientComponent<?, ?>> REGISTRY = (IForgeRegistry) GameRegistry
            .findRegistry(IngredientComponent.class);

    @GameRegistry.ObjectHolder("minecraft:itemstack")
    public static final IngredientComponent<ItemStack, Integer> ITEMSTACK = null;
    @GameRegistry.ObjectHolder("minecraft:fluidstack")
    public static final IngredientComponent<FluidStack, Integer> FLUIDSTACK = null;
    @GameRegistry.ObjectHolder("minecraft:energy")
    public static final IngredientComponent<Integer, Void> ENERGY = null;

    private final IIngredientMatcher<T, M> matcher;
    private final IIngredientSerializer<T, M> serializer;
    private ResourceLocation name;
    private String unlocalizedName;

    public IngredientComponent(ResourceLocation name, IIngredientMatcher<T, M> matcher, IIngredientSerializer<T, M> serializer) {
        this.setRegistryName(name);
        this.matcher = matcher;
        this.serializer = serializer;
    }

    public IngredientComponent(String name, IIngredientMatcher<T, M> matcher, IIngredientSerializer<T, M> serializer) {
        this(new ResourceLocation(name), matcher, serializer);
    }

    public ResourceLocation getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[Recipe Component " + this.name + " " + hashCode() + "]";
    }

    @Override
    public IngredientComponent<T, M> setRegistryName(ResourceLocation name) {
        this.name = name;
        return this;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return this.name;
    }

    @Override
    public Class<IngredientComponent<?, ?>> getRegistryType() {
        return (Class) IngredientComponent.class;
    }

    public IngredientComponent<T, M> setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
        return this;
    }

    /**
     * @return The unlocalized name.
     */
    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    /**
     * @return A matcher instance for comparing instances of this component type.
     */
    public IIngredientMatcher<T, M> getMatcher() {
        return matcher;
    }

    /**
     * @return A (de)serializer instance for this component type.
     */
    public IIngredientSerializer<T, M> getSerializer() {
        return serializer;
    }

    /**
     * Wrap the given instance inside an equals, hashCode and compareTo-safe holder.
     * @param instance An instance.
     * @return The wrapped instance.
     */
    public IngredientInstanceWrapper<T, M> wrap(T instance) {
        return new IngredientInstanceWrapper<>(this, instance);
    }
}
