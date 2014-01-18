package jp._RS_.huc;

import jp._RS_.huc.config.ConfigHandler;

import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageEvents implements Listener {
	private HUC huc;
	private boolean disable;
	public DamageEvents(HUC huc)
	{
		setDisabled(huc.getConfigHandler().getDamageDisabled());
		this.huc = huc;
	}
	public void loadDisabled(ConfigHandler config)
	{
		disable = config.getDamageDisabled();
	}
	public boolean getDisabled()
	{
		return disable;
	}
	public void setDisabled(boolean b)
	{
		disable = b;
	}
	@EventHandler(priority = EventPriority.LOW)
	public void onDamage(EntityDamageEvent e)
	{
		if(disable)
		{
			if(e.getEntity() instanceof Horse)
			{
				e.setDamage(0);
				e.setCancelled(true);
			}
		}
		
	}

}
