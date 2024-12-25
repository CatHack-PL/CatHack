package cat.hack.features.cmd.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import cat.hack.CatHack;
import cat.hack.features.cmd.Command;
import baritone.api.BaritoneAPI;
import cat.hack.features.cmd.args.WayPointArgumentType;
import cat.hack.core.manager.world.WayPointManager;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;
import static cat.hack.features.modules.client.ClientSettings.isRu;

public class GotoWaypointCommand extends Command {
    public GotoWaypointCommand(){
        super("goto");
    }

    @Override
    public void executeBuild(LiteralArgumentBuilder<CommandSource> builder) {
        builder.then(arg("name", WayPointArgumentType.create()).executes(context -> {
            if (!CatHack.baritone) {
                sendMessage(isRu() ? "Baritone nie znaleziony (możesz go pobrać na https://meteorclient.com)" : "Baritone not found (you can download it at https://meteorclient.com)");
                return SINGLE_SUCCESS;
            }
            WayPointManager.WayPoint wp = context.getArgument("name", WayPointManager.WayPoint.class);
            if (!mc.world.getRegistryKey().getValue().getPath().equals(wp.getDimension())) {
                sendMessage(isRu() ? "Zaznacz w innym wymiarze" : "Waypoint is in another dimension");
                return SINGLE_SUCCESS;
            }
            BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager().execute("goto " + wp.getX() + " " + wp.getY() + " " + wp.getZ());
            return SINGLE_SUCCESS;
        }));
    }
}
