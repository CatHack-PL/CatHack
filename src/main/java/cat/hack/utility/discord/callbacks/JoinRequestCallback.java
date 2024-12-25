package cat.hack.utility.discord.callbacks;

import cat.hack.utility.discord.DiscordUser;
import com.sun.jna.Callback;

public interface JoinRequestCallback extends Callback {
    void apply(final DiscordUser p0);
}
