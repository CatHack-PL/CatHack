package cat.hack.utility.discord;

import java.util.Arrays;
import java.util.List;
import cat.hack.utility.discord.callbacks.JoinGameCallback;
import cat.hack.utility.discord.callbacks.ErroredCallback;
import cat.hack.utility.discord.callbacks.ReadyCallback;
import cat.hack.utility.discord.callbacks.SpectateGameCallback;
import cat.hack.utility.discord.callbacks.JoinRequestCallback;
import cat.hack.utility.discord.callbacks.DisconnectedCallback;
import com.sun.jna.Structure;

public class DiscordEventHandlers extends Structure {
    public DisconnectedCallback disconnected;
    public JoinRequestCallback joinRequest;
    public SpectateGameCallback spectateGame;
    public ReadyCallback ready;
    public ErroredCallback errored;
    public JoinGameCallback joinGame;
    
    protected List<String> getFieldOrder() {
        return Arrays.asList("ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest");
    }
}