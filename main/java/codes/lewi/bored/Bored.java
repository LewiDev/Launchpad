package codes.lewi.bored;

import org.bukkit.plugin.java.JavaPlugin;

public final class Bored extends JavaPlugin {

    @Override
    public void onEnable() {
       ConfigManager.ConfigCreate(this);

       this.getServer().getPluginManager().registerEvents(new LaunchEvents(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
