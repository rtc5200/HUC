package jp.rtc5200.huc;




import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageEvents implements Listener {
	private boolean disable = false;
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
		if(!disable)
		{
			return;
		}
		if(e.getEntity() instanceof Horse)
		{
			e.setDamage(0);
			e.setCancelled(true);
		}
	}

}
