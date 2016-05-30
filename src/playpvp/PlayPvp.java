
package playpvp;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class PlayPvp extends JavaPlugin implements Listener{

    List<Player> glowingPlayers = new LinkedList<>(); //Список игроков, которые хотят видеть свичение
        
    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(this, this);
        
        Runnable glowing = new Glowing(this); //Обновление
        Bukkit.getScheduler().runTaskTimer(this, glowing, 1, 1); //20 тиков - 1 секудна. Как часто быдет выполняться.
        Runnable detect = new Detect(this);
        Bukkit.getScheduler().runTaskTimer(this, detect, 20, 20);
        
        Bukkit.getLogger().info("Plugin - Enable!");
    }
    
    @Override
    public void onDisable(){
        Bukkit.getLogger().info("[ServersInventory] Plugin - Disable.");
    }

}
