package cat.hack.api;

import com.mojang.logging.LogUtils;
import cat.hack.features.cmd.Command;
import cat.hack.features.hud.HudElement;
import cat.hack.features.modules.Module;

import java.util.List;

public interface IAddon {
    void onInitialize();

    List<Module> getModules();

    List<Command> getCommands();

    List<HudElement> getHudElements();

    String getPackage();

    String getName();

    String getAuthor();

    String getRepo();

    String getVersion();

    default String getDescription() {
        return "";
    }

    default void onShutdown() {
        LogUtils.getLogger().info("Shutting down addon: " + getName());
    }
}