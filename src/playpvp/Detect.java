package playpvp;

import java.util.Collection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class Detect implements Runnable {
    private final PlayPvp plugin;
    
    Detect(PlayPvp plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        plugin.glowingPlayers.clear(); //Очищает список игроков
        Collection<? extends Player> allPlayers = Bukkit.getOnlinePlayers(); //Все игроки на сервере
        if (!allPlayers.isEmpty()) { //Если игроки есть
            for (Player player : allPlayers){ //Перебирает всех игроков
                ItemStack helmet = player.getInventory().getHelmet(); //Витаскивает предмет на голове у игрока
                if (helmet.getType().equals(Material.SKULL) && helmet.getDurability() == (short) 5){ //Проверяет, это ли голова дракона
                    plugin.glowingPlayers.add(player); //Добавляет игрока в список, тех которые хотят видеть свичение
                }
            }
        }
    }
    
}
