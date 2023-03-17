package com.segoitch.easyfeathers;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public final class EasyFeathers extends JavaPlugin implements Listener {

    private Cache<Chicken, Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(500, TimeUnit.MILLISECONDS).build();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChickenInteract(PlayerInteractEntityEvent event) {
        if (event.getHand() != (EquipmentSlot.HAND)) return;
        if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {
            if (event.getRightClicked() instanceof Chicken) {
                Chicken chicken = (Chicken) event.getRightClicked();
                if (cooldown.asMap().containsKey(chicken)) return;
                cooldown.put(chicken, System.currentTimeMillis() + 500);
                if (chicken.isDead()) return;
                chicken.damage(0.5);
                event.getPlayer().getInventory().addItem(new ItemStack(Material.FEATHER, 1));
            }
        }
    }
}
