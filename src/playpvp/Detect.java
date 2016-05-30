package playpvp;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
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
        List<Player> players = new LinkedList<>();
        Collection<? extends Player> allPlayers = Bukkit.getOnlinePlayers(); //Все игроки на сервере
        if (!allPlayers.isEmpty()) { //Если игроки есть
            for (Player player : allPlayers){ //Перебирает всех игроков
                ItemStack helmet = player.getInventory().getHelmet(); //Витаскивает предмет на голове у игрока
                if (helmet != null && helmet.getType().equals(Material.SKULL_ITEM) && helmet.getDurability() == (short) 5){ //Проверяет, это ли голова дракона
                    players.add(player); //Добавляет игрока в список, тех которые хотят видеть свичение
                }
            }
            plugin.glowingPlayers.clear(); //Очищает список игроков
            plugin.glowingPlayers.addAll(players);
        }
        
    }
    
}
