package cat.hack.features.modules.client;

import baritone.api.BaritoneAPI;
import meteordevelopment.orbit.EventHandler;
import cat.hack.CatHack;
import cat.hack.events.impl.EventSetting;
import cat.hack.features.modules.Module;
import cat.hack.setting.Setting;

import static cat.hack.features.modules.client.ClientSettings.isRu;

public final class BaritoneSettings extends Module {
    public BaritoneSettings() {
        super("BaritoneSettings", Category.CLIENT);
    }

    public final Setting<Boolean> allowBreakBlock = new Setting<>("AllowBreakBlock", true);
    public final Setting<Boolean> allowPlace = new Setting<>("AllowPlace", true);
    public final Setting<Boolean> allowSprint = new Setting<>("AllowSprint", true);
    public final Setting<Boolean> debug = new Setting<>("Debug", false);
    public final Setting<Boolean> enterPortal = new Setting<>("EnterPortal", false);
    public final Setting<Boolean> desktopNotifications = new Setting<>("DesktopNotifications", false);

    @EventHandler
    public void onSettingChange(EventSetting e) {
        if (!CatHack.baritone) {
            sendMessage(isRu() ? "Баритон не найден (можешь скачать на https://meteorclient.com)" : "Nie posiadasz baritone (pobierz z https://meteorclient.com)");
            return;
        }
        BaritoneAPI.getSettings().allowBreak.value = allowBreakBlock.getValue();
        BaritoneAPI.getSettings().allowPlace.value = allowPlace.getValue();
        BaritoneAPI.getSettings().allowSprint.value = allowSprint.getValue();
        BaritoneAPI.getSettings().chatDebug.value = debug.getValue();
        BaritoneAPI.getSettings().enterPortal.value = enterPortal.getValue();
        BaritoneAPI.getSettings().desktopNotifications.value = desktopNotifications.getValue();
    }

    @Override
    public boolean isToggleable() {
        return false;
    }
}