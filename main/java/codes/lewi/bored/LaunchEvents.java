package codes.lewi.bored;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class LaunchEvents implements Listener {

    private Plugin plugin;

    public LaunchEvents(Plugin plugin) {
        this.plugin = plugin;
    }

    private Map map = new HashMap<String,Integer>();

    public void onBlock(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(!p.hasPermission("launchpad.use")) {
            p.sendMessage(ChatColor.RED + "You do not have the required permission to use this!");
            return;
        }
        int x = (int) e.getPlayer().getLocation().getX();
        int y = (int) e.getPlayer().getLocation().getY() - 1;
        int z = (int) e.getPlayer().getLocation().getZ();
        Block block = p.getWorld().getBlockAt(x,y,z);
        if(block.equals(Material.REDSTONE_BLOCK)) {
            for(String string : plugin.getConfig().getStringList("launchpads")) {
                String cords = x + "," + y + "," + z;
                if(cords.equals(string)) {
                    Vector vec = p.getLocation().getDirection();
                    p.setVelocity(vec.multiply(10));
                    p.sendMessage("hi");
                    map.put(p.getName(), ((int) System.currentTimeMillis()));
                }
            }
        }
    }

    public void onLand(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {return;}
        Player p = (Player) e.getEntity();
        Integer i = Math.toIntExact(System.currentTimeMillis());
        Integer l = (Integer) map.get(p.getName());
        if(i - l < 3000) {
          map.remove(p.getName());
          e.setCancelled(true);
        } else {
            map.remove(p.getName());
            return;
        }
    }

}
