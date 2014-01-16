package jp.huc.command;

import jp.rtc5200.huc.Utils;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.entity.Horse.*;
import org.bukkit.entity.Horse.Color;
import org.bukkit.inventory.ItemStack;

public class HorseMaker {
	public static Horse  makeHorse(Variant v,Color c,Style s,Location loc)
	{
		Horse h = (Horse) Utils.getWorld().spawnEntity(loc, EntityType.HORSE);
		h.setVariant(v);
		h.setColor(c);
		h.setStyle(s);
		h.setTamed(true);
		h.getInventory().setSaddle(new ItemStack(Material.SADDLE));
		return h;
	}

}
