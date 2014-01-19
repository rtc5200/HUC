package jp._RS_.huc.sign;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import jp._RS_.huc.HUC;
import jp._RS_.huc.Variables;

public class SignBreakEvent implements Listener {
	private HUC huc;
	public SignBreakEvent(HUC huc) {
		this.huc = huc;
	}
	@EventHandler(priority = EventPriority.LOW)
	public void onBreak(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if(b.getType() == Material.WALL_SIGN || b.getType() == Material.SIGN_POST)
		{
			Sign s = (Sign) b.getState();
			if(s.getLine(0).equals(ChatColor.AQUA + "[HUC]"))
			{
				if(!p.hasPermission(Variables.Permission_BreakSign))
				{
					p.sendMessage(Variables.NotHavePermission);
					e.setCancelled(true);
					return;
				}
				
			}
			
		}
	}

}
