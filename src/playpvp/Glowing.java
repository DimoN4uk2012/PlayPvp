
package playpvp;

import java.util.List;
import net.minecraft.server.v1_9_R2.DataWatcher;
import net.minecraft.server.v1_9_R2.DataWatcherObject;
import net.minecraft.server.v1_9_R2.DataWatcherRegistry;
import net.minecraft.server.v1_9_R2.PacketPlayOutEntityMetadata;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Glowing implements Runnable {
    private final PlayPvp plugin;
    
    Glowing(PlayPvp plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public void run() {
        if (!plugin.glowingPlayers.isEmpty()){ //Проверяет есть ли игроки которые хотят видеть свечение
            for (Player player : plugin.glowingPlayers){ //Перебирает всех игроков кто может видеть свечение
                List<Player> seeTargets = player.getWorld().getPlayers(); //Берет всех игроков которых видит player (всех кто в его мире)
                for (Player targets : seeTargets){ //Переберает всех игроков которых видит player
                    CraftPlayer target = (CraftPlayer) targets; //Преобразует игрока targets в target ванильного типа.
                    DataWatcher dw = ((CraftLivingEntity)target).getHandle().getDataWatcher(); //Сосдает энтити wb с target
                    dw.set(new DataWatcherObject<>(0, DataWatcherRegistry.a), (byte) 0x40); //Устанавливает флаг для энтити wb
                    PacketPlayOutEntityMetadata metadata = new PacketPlayOutEntityMetadata(targets.getEntityId(), dw, false); //Формирует пакет
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(metadata); //Отправляет пакеты что игрок targets светиться
                }
            }
        }
    }
    
}
