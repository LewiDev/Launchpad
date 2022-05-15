package codes.lewi.bored;

import org.bukkit.plugin.Plugin;

public class ConfigManager {

    public static void ConfigCreate(Plugin plugin) {
        plugin.getConfig().options().copyDefaults();
        plugin.saveDefaultConfig();
    }
}
