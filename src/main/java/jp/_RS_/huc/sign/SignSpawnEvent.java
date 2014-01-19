package jp._RS_.huc.sign;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import jp._RS_.huc.HUC;
import jp._RS_.huc.Variables;
import jp._RS_.huc.command.ColorGetter;
import jp._RS_.huc.command.HorseMaker;
import jp._RS_.huc.command.StyleGetter;
import jp._RS_.huc.command.VariantGetter;

public class SignSpawnEvent implements Listener {
	private HUC huc;
	public SignSpawnEvent(HUC huc) {
		this.huc = huc;
	}
	@EventHandler
	public void onUse(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			Block b = e.getClickedBlock();
			if(b.getType() == Material.WALL_SIGN || b.getType() == Material.SIGN_POST)
			{
				Sign s =(Sign) b.getState();
				if(s.getLine(0).equals(ChatColor.AQUA + "[HUC]"))
				{
					if(!p.hasPermission(Variables.Permission_UseSign))
					{
						p.sendMessage(Variables.NotHavePermission);
						return;
					}
					String sv = s.getLine(1).replace(ChatColor.GREEN.toString(), "");
					String sc = s.getLine(2).replace(ChatColor.GREEN.toString(), "");
					String ss = s.getLine(3).replace(ChatColor.GREEN.toString(), "");
					Variant v = VariantGetter.getVariant(sv);
					Color c = ColorGetter.getColor(sc);
					Style sy = StyleGetter.getStyle(ss);
					Horse h = HorseMaker.makeHorse(v, c, sy, p.getLocation());
					h.setPassenger(p);
					p.sendMessage("馬をスポーンしました。");
				}
			}
		}
	}

}
