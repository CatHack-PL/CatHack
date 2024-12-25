package cat.hack.core;

import cat.hack.CatHack;
import cat.hack.core.manager.IManager;
import cat.hack.core.manager.client.*;
import cat.hack.core.manager.player.CombatManager;
import cat.hack.core.manager.player.FriendManager;
import cat.hack.core.manager.player.PlayerManager;
import cat.hack.core.manager.world.HoleManager;
import cat.hack.core.manager.world.WayPointManager;
import cat.hack.utility.CatUtility;

import static cat.hack.CatHack.EVENT_BUS;

/**
 * Class with all CatHack Managers' instances
 *
 * @author 06ED
 * @see IManager - base interface for all managers
 * @see CatHack - managers init process
 * @since 1.7
 */
public class Managers {
    // Player
    public static final CombatManager COMBAT = new CombatManager();
    public static final FriendManager FRIEND = new FriendManager();
    public static final PlayerManager PLAYER = new PlayerManager();

    // World
    public static final HoleManager HOLE = new HoleManager(); //todo ???
    public static final WayPointManager WAYPOINT = new WayPointManager();

    // Client
    public static final AddonManager ADDON = new AddonManager();
    public static final AsyncManager ASYNC = new AsyncManager();
    public static final ModuleManager MODULE = new ModuleManager();
    public static final ConfigManager CONFIG = new ConfigManager();
    public static final MacroManager MACRO = new MacroManager();
    public static final NotificationManager NOTIFICATION = new NotificationManager();
    public static final ProxyManager PROXY = new ProxyManager();
    public static final ServerManager SERVER = new ServerManager();
    public static final ShaderManager SHADER = new ShaderManager();
    public static final SoundManager SOUND = new SoundManager();
    public static final TelemetryManager TELEMETRY = new TelemetryManager();
    public static final CommandManager COMMAND = new CommandManager();

    public static void init() {
        ADDON.initAddons();
        CONFIG.load(CONFIG.getCurrentConfig());
        MODULE.onLoad("none");
        FRIEND.loadFriends();
        MACRO.onLoad();
        WAYPOINT.onLoad();
        PROXY.onLoad();
        SOUND.registerSounds();

        ASYNC.run(() -> {
            CatUtility.syncContributors();
            CatUtility.parseStarGazer();
            CatUtility.parseCommits();
            TELEMETRY.fetchData();
        });
    }

    public static void subscribe() {
        EVENT_BUS.subscribe(NOTIFICATION);
        EVENT_BUS.subscribe(SERVER);
        EVENT_BUS.subscribe(PLAYER);
        EVENT_BUS.subscribe(COMBAT);
        EVENT_BUS.subscribe(ASYNC);
        EVENT_BUS.subscribe(TELEMETRY);
    }
}
