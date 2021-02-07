package org.dimdev.dimdoors.block;

import java.util.Random;

import org.dimdev.dimdoors.block.entity.DetachedRiftBlockEntity;
import org.dimdev.dimdoors.particle.client.RiftParticle;
import org.dimdev.dimdoors.particle.client.RiftParticleEffect;
import org.dimdev.dimdoors.world.ModDimensions;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class DetachedRiftBlock extends Block implements RiftProvider<DetachedRiftBlockEntity> {
	public static final String ID = "rift";

	public DetachedRiftBlock(Block.Settings settings) {
		super(settings);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView blockView) {
		return new DetachedRiftBlockEntity();
	}

	@Override
	public MaterialColor getDefaultMaterialColor() {
		return MaterialColor.BLACK;
	}

	@Override
	public DetachedRiftBlockEntity getRift(World world, BlockPos pos, BlockState state) {
		return (DetachedRiftBlockEntity) world.getBlockEntity(pos);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random rand) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		// randomDisplayTick can be called before the tile entity is created in multiplayer
		if (!(blockEntity instanceof DetachedRiftBlockEntity)) return;
		DetachedRiftBlockEntity rift = (DetachedRiftBlockEntity) blockEntity;

		boolean outsidePocket = !ModDimensions.isPocketDimension(world);
		double speed = 0.1;

		if (rift.closing) {
			world.addParticle(RiftParticleEffect.of(outsidePocket),
					pos.getX() + .5,
					pos.getY() + .5,
					pos.getZ() + .5,
					rand.nextGaussian() * speed,
					rand.nextGaussian() * speed,
					rand.nextGaussian() * speed
			);
		}

		world.addParticle(RiftParticleEffect.of(outsidePocket, rift.stabilized),
				pos.getX() + .5,
				pos.getY() + .5,
				pos.getZ() + .5,
				rand.nextGaussian() * speed,
				rand.nextGaussian() * speed,
				rand.nextGaussian() * speed
		);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}
}
