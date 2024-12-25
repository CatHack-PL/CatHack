package cat.hack.gui.clickui.impl;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import cat.hack.core.Managers;
import cat.hack.gui.clickui.AbstractElement;
import cat.hack.gui.font.FontRenderers;
import cat.hack.gui.windows.WindowsScreen;
import cat.hack.gui.windows.impl.ItemSelectWindow;
import cat.hack.setting.Setting;
import cat.hack.setting.impl.ItemSelectSetting;

import java.awt.*;

import static cat.hack.core.manager.IManager.mc;

public class ItemSelectElement extends AbstractElement {
    private final Setting<ItemSelectSetting> setting;

    public ItemSelectElement(Setting setting) {
        super(setting);
        this.setting = setting;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        MatrixStack matrixStack = context.getMatrices();
        FontRenderers.icons.drawString(matrixStack, "H", x + width - 14f, y + 6f, new Color(0xFFECECEC, true).getRGB());
        FontRenderers.sf_medium_mini.drawString(matrixStack, setting.getName(), x + 6f, (y + height / 2 - 1f), new Color(-1).getRGB());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (hovered) {
            mc.setScreen(new WindowsScreen(new ItemSelectWindow(getItemSetting())));
            Managers.SOUND.playSwipeIn();
        }
        super.mouseClicked(mouseX, mouseY, button);
    }

    public Setting<ItemSelectSetting> getItemSetting() {
        return setting;
    }
}