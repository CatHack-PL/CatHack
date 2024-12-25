package cat.hack.events.impl;

import cat.hack.events.Event;
import cat.hack.setting.Setting;

public class EventSetting extends Event {
    final Setting<?> setting;

    public EventSetting(Setting<?> setting){
        this.setting = setting;
    }

    public Setting<?> getSetting() {
        return setting;
    }
}
