package net.segoitch.easyfeathers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class EasyFeathers extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChickenInteract(PlayerInteractEntityEvent event) {
        if (event.getHand() == (EquipmentSlot.HAND)
                && event.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {
            if (event.getRightClicked() instanceof Chicken) { // need aditional check if chicken was damaged like 0.5 seconds ago
                event.getPlayer().getInventory().addItem(new ItemStack(Material.FEATHER, 1));
                ((Chicken) event.getRightClicked()).damage(1);
                event.getPlayer().sendMessage("Damaged");
            }
        }
    }
}
