package jp._RS_.huc.command;

import java.util.ArrayList;

import jp._RS_.huc.DamageEvents;
import jp._RS_.huc.HUC;
import jp._RS_.huc.Utils;
import jp._RS_.huc.Variables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;

public class CommandBlockCommand {
	private HUC huc;
	public CommandBlockCommand(HUC huc)
	{
		this.huc = huc;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args) {
		int range = huc.getConfigHandler().getRange();
		Block b = ((BlockCommandSender)sender).getBlock();
		Location bloc = b.getLocation();
		ArrayList<Horse> nh = new ArrayList<Horse>();
		for(Entity e : b.getWorld().getEntities())	
		{
			if(bloc.distance(e.getLocation()) <= range)
			{
				if(e instanceof Horse)
				{
					nh.add((Horse) e);
				}
			}
		}
		
		if(args[0].equalsIgnoreCase("td"))
		{
			if(huc.getDEvent().getDisabled())
			{
				huc.getDEvent().setDisabled(false);
				return true;
			}else{
				huc.getDEvent().setDisabled(true);
				return true;
			}
		}
		if(args[0].equalsIgnoreCase("pspawn"))
		{
			if(args.length < 5)
			{
				return true;
			}
			Variant v = VariantGetter.getVariant(args[1]);
			Color c = ColorGetter.getColor(args[2]);
			Style s = StyleGetter.getStyle(args[3]);
			Player player = Bukkit.getPlayerExact(args[4]);
			boolean error = false;
			String ers = "";
			if(v == null)
			{
				error = true;
			}
			if(c == null)
			{
				error = true;
			}
			if(s == null)
			{
				error = true;
			}
			if(player == null || !player.isOnline())
			{
				error = true;
			}
			if(error)
			{
				return true;
			}
			Horse h = HorseMaker.makeHorse(v, c, s,player.getLocation());
			h.teleport(player);
			h.setPassenger(player);
			player.sendMessage("馬がスポーンされました。");
			return true;
		}
		if(args[0].equalsIgnoreCase("lspawn"))
		{
			if(args.length < 7)
			{
				return true;
			}
			Variant v = VariantGetter.getVariant(args[1]);
			Color c = ColorGetter.getColor(args[2]);
			Style s = StyleGetter.getStyle(args[3]);
			Location loc;
			try{
				loc = new Location(Utils.getWorld(),Double.parseDouble(args[4]),Double.parseDouble(args[5]),Double.parseDouble(args[6]));
			}catch(NumberFormatException e)
			{
				loc = null;
			}
			boolean error = false;
			String ers = "";
			if(v == null)
			{
				error = true;
			}
			if(c == null)
			{
				error = true;
			}
			if(s == null)
			{
				error = true;
			}
			if(loc == null)
			{
				error = true;
			}
			if(error)
			{
				return true;
			}
			Horse h = HorseMaker.makeHorse(v, c, s,loc);
			return true;
		}
		if(args[0].equalsIgnoreCase("hspawn"))
		{
			if(args.length < 4)
			{
				return true;
			}
			Variant v = VariantGetter.getVariant(args[1]);
			Color c = ColorGetter.getColor(args[2]);
			Style s = StyleGetter.getStyle(args[3]);
			Location loc = bloc.clone();
			boolean error = false;
			String ers = "";
			if(v == null)
			{
				error = true;
			}
			if(c == null)
			{
				error = true;
			}
			if(s == null)
			{
				error = true;
			}
			if(loc == null)
			{
				error = true;
			}
			if(error)
			{
				return true;
			}
			Horse h = HorseMaker.makeHorse(v, c, s,loc);
			return true;
		}
		if(args[0].equalsIgnoreCase("reload"))
		{
			huc.reload();
			return true;
		}
		if(args[0].equalsIgnoreCase("variant"))
		{
			if(args.length < 2)
			{
				return true;
			}
			Variant v = VariantGetter.getVariant(args[1]);
			if(v == null)
			{
				return true;
			}
			for(Horse h : nh)
			{
				h.setVariant(v);
			}
			return true;
		}
		if(args[0].equalsIgnoreCase("style"))
		{
			if(args.length < 2)
			{
				return true;
			}
			Style s = StyleGetter.getStyle(args[1]);
			if(s == null)
			{
				return true;
			}
			for(Horse h : nh)
			{
				h.setStyle(s);
			}
			return true;
		}
		if(args[0].equalsIgnoreCase("color"))
		{
			if(args.length < 2)
			{
				return true;
			}
			Color c = ColorGetter.getColor(args[1]);
			if(c == null)
			{
				return true;
			}
			for(Horse h : nh)
			{
				h.setColor(c);
			}
			return true;
		}
		if(args[0].equalsIgnoreCase("dome"))
		{
			for(Horse h : nh)
			{
				h.setTamed(true);
			}
			return true;
		}
		if(args[0].equalsIgnoreCase("dismount-all"))
		{
			int count = 0;
			for(Player pa : Bukkit.getOnlinePlayers())
			{
				if(pa.getVehicle() instanceof Horse)
				{
					pa.leaveVehicle();
					count = count + 1;
				}
			}
			return true;
		}
		if(args[0].equalsIgnoreCase("remove"))
		{
			int count = 0;
			for(Horse hs : nh)
			{
				hs.remove();
				count = count + 1;
			}
			return true;		
		}
		if(args[0].equalsIgnoreCase("name"))
		{
			if(args.length == 1)
			{
				for(Horse hs : nh)
				{
					hs.setCustomName("");
					hs.setCustomNameVisible(false);
				}
				sender.sendMessage("名前を削除しました。");
				return true;
			}else{
				for(Horse hs : nh)
				{		
					hs.setCustomName(args[1]);
					hs.setCustomNameVisible(true);
				}
				sender.sendMessage("名前を設定しました");
				return true;
			}
		}
		if(args[0].equalsIgnoreCase("help"))
		{
			Utils.sendHelpMessage(sender);
			return true;
		}
		Utils.sendHelpMessage(sender);
		return true;
	}

}
