package dev.flare24.mod.modules.impl.player;

import dev.flare24.api.events.eventbus.EventHandler;
import dev.flare24.api.events.impl.PacketEvent;
import dev.flare24.mod.modules.Module;
import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.EnderChestBlock;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;

public class NoInteract
        extends Module {

    public NoInteract() {
        super("NoChestInteract", Category.Player);
        setChinese("没有箱子交互");
    }

    @EventHandler
    public void onPacket(PacketEvent.Send event) {
        if (nullCheck() || !(event.getPacket() instanceof PlayerInteractBlockC2SPacket packet)) {
            return;
        }
        Block block = mc.world.getBlockState(packet.getBlockHitResult().getBlockPos()).getBlock();
        if (!mc.player.isSneaking()) {
            if (block instanceof ChestBlock || block instanceof EnderChestBlock) {
                event.cancel();
            }
        }
    }
}
