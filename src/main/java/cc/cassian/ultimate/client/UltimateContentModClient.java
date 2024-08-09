package cc.cassian.ultimate.client;

import cc.cassian.ultimate.UltimateContentMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class UltimateContentModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(UltimateContentMod.FIRE, RenderLayer.getCutout());
    }
}
