package dev.flare24.mod.modules.impl.combat;

import dev.flare24.api.utils.world.BlockPosX;
import dev.flare24.api.utils.world.BlockUtil;
import dev.flare24.Flare;
import dev.flare24.mod.modules.Module;
import dev.flare24.mod.modules.impl.player.PacketMine;
import dev.flare24.mod.modules.settings.impl.BooleanSetting;
import net.minecraft.util.math.BlockPos;

public class AntiCrawl extends Module {
	public static AntiCrawl INSTANCE;

	public AntiCrawl() {
		super("AntiCrawl", Category.Combat);
		setChinese("反趴下");
		INSTANCE = this;
	}
	private final BooleanSetting pre = add(new BooleanSetting("Pre", true));

	public boolean work = false;
	double[] xzOffset = new double[]{0, 0.3, -0.3};
	@Override
	public void onUpdate() {
		work = false;
		if (mc.player.isCrawling() || pre.getValue() && Flare.BREAK.isMining(mc.player.getBlockPos())) {
			for (double offset : xzOffset) {
				for (double offset2 : xzOffset) {
					BlockPos pos = new BlockPosX(mc.player.getX() + offset, mc.player.getY() + 1.2, mc.player.getZ() + offset2);
					if (canBreak(pos)) {
						PacketMine.INSTANCE.mine(pos);
						work = true;
						return;
					}
				}
			}
		}
	}

	private boolean canBreak(BlockPos pos) {
		return (BlockUtil.getClickSideStrict(pos) != null || PacketMine.getBreakPos().equals(pos)) && !PacketMine.godBlocks.contains(mc.world.getBlockState(pos).getBlock()) && !mc.world.isAir(pos);
	}
}