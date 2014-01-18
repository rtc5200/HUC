package jp._RS_.huc.command;

import java.util.ArrayList;
import jp._RS_.huc.DamageEvents;
import jp._RS_.huc.HUC;
import jp._RS_.huc.Utils;
import jp._RS_.huc.Variables;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;

public class PlayerCommand {
	private HUC huc;

	public PlayerCommand(HUC huc) {
		this.huc = huc;
	}
	public boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args){
		int range =huc.getConfigHandler().getRange();
		Player p = (Player)sender;
		ArrayList<Horse> nh = new ArrayList<Horse>();
		for(Entity e : p.getNearbyEntities(range, 0, range))
		{
			if(e instanceof Horse)
			{
				nh.add((Horse) e);
			}
		}
		if(args[0].equalsIgnoreCase("td"))
		{
			if( !p.hasPermission(Variables.Permission_ToggleDamage))
			{
				p.sendMessage(Variables.NotHavePermission);
				return true;
			}
			if(huc.getDEvent().getDisabled())
			{
				huc.getDEvent().setDisabled(false);
				p.sendMessage("ダメージを" +"有効" + "にしました。");
				return true;
			}else{
				huc.getDEvent().setDisabled(true);
				p.sendMessage("ダメージを" + "無効" + "にしました。");
				return true;
			}
		}
		if(args[0].equalsIgnoreCase("pspawn"))
		{
			if( !p.hasPermission(Variables.Permission_PSpawn))
			{
				p.sendMessage(Variables.NotHavePermission);
				return true;
			}
			if(args.length < 5)
			{
				p.sendMessage("引数が足りません。");
				p.sendMessage("/huc pspawn [種類] [色] [スタイル] [プレイヤー]");
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
				ers = ers + ChatColor.RESET + "種類:" + ChatColor.RED + "エラー";
				error = true;
			}else{
				ers = ers + ChatColor.RESET + "種類:" + ChatColor.GREEN + "正常";
			}
			if(c == null)
			{
				ers = ers + ChatColor.RESET + "色:" + ChatColor.RED + "エラー";
				error = true;
			}else{
				ers = ers + ChatColor.RESET + "色:" + ChatColor.GREEN + "正常";
			}
			if(s == null)
			{
				ers = ers + ChatColor.RESET + "スタイル:" + ChatColor.RED + "エラー";
				error = true;
			}else{
				ers = ers + ChatColor.RESET + "スタイル:" + ChatColor.GREEN + "正常";
			}
			if(player == null || !player.isOnline())
			{
				ers = ers + ChatColor.RESET + "プレイヤー:" + ChatColor.RED + "エラー";
				error = true;
			}else{
				ers = ers + ChatColor.RESET + "プレイヤー:" + ChatColor.GREEN + "正常";
			}
			if(error)
			{
				p.sendMessage("引数でエラーが発生しました。以下の通りです。");
				p.sendMessage(ers);
				p.sendMessage("/huc pspawn [種類] [色] [スタイル] [プレイヤー]");
				return true;
			}
			Horse h = HorseMaker.makeHorse(v, c, s,player.getLocation());
			h.teleport(player);
			p.sendMessage("馬を" + player.getName() + "のもとへスポーンさせました。");
			player.sendMessage(p.getName() + "からスポーンされました。");
			h.setPassenger(player);
			return true;
		}
		if(args[0].equalsIgnoreCase("lspawn"))
		{
			if( !p.hasPermission(Variables.Permission_LSpawn))
			{
				p.sendMessage(Variables.NotHavePermission);
				return true;
			}
			if(args.length < 7)
			{
				p.sendMessage("引数が足りません。");
				p.sendMessage("/huc lspawn [種類] [色] [スタイル] [x] [y] [z]");
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
				ers = ers + ChatColor.RESET + "種類:" + ChatColor.RED + "エラー";
				error = true;
			}else{
				ers = ers + ChatColor.RESET + "種類:" + ChatColor.GREEN + "正常";
			}
			if(c == null)
			{
				ers = ers + ChatColor.RESET + "色:" + ChatColor.RED + "エラー";
				error = true;
			}else{
				ers = ers + ChatColor.RESET + "色:" + ChatColor.GREEN + "正常";
			}
			if(s == null)
			{
				ers = ers + ChatColor.RESET + "スタイル:" + ChatColor.RED + "エラー";
				error = true;
			}else{
				ers = ers + ChatColor.RESET + "スタイル:" + ChatColor.GREEN + "正常";
			}
			if(loc == null)
			{
				ers = ers + ChatColor.RESET + "座標:" + ChatColor.RED + "エラー";
				error = true;
			}else{
				ers = ers + ChatColor.RESET + "座標:" + ChatColor.GREEN + "正常";
			}
			if(error)
			{
				p.sendMessage("引数でエラーが発生しました。以下の通りです。");
				p.sendMessage(ers);
				p.sendMessage("/huc lspawn [種類] [色] [スタイル] [x] [y] [z]");
				return true;
			}
			Horse h = HorseMaker.makeHorse(v, c, s,loc);
			double x = Utils.RoundingUp(loc.getX());
			double y = Utils.RoundingUp(loc.getY());
			double z = Utils.RoundingUp(loc.getZ());
			p.sendMessage("馬を" + ChatColor.UNDERLINE + "(" + x + "," + y + "," + z + ")" + ChatColor.RESET +  "へスポーンさせました。");
			return true;
		}
		if(args[0].equalsIgnoreCase("hspawn"))
		{
			if( !p.hasPermission(Variables.Permission_HSpawn))
			{
				p.sendMessage(Variables.NotHavePermission);
				return true;
			}
			if(args.length < 4)
			{
				p.sendMessage("引数が足りません。");
				p.sendMessage("/huc hspawn [種類] [色] [スタイル]");
				return true;
			}
			Variant v = VariantGetter.getVariant(args[1]);
			Color c = ColorGetter.getColor(args[2]);
			Style s = StyleGetter.getStyle(args[3]);
			Location loc = p.getLocation();
			boolean error = false;
			String ers = "";
			if(v == null)
			{
				ers = ers + ChatColor.RESET + "種類:" + ChatColor.RED + "エラー";
				error = true;
			}else{
				ers = ers + ChatColor.RESET + "種類:" + ChatColor.GREEN + "正常";
			}
			if(c == null)
			{
				ers = ers + ChatColor.RESET + "色:" + ChatColor.RED + "エラー";
				error = true;
			}else{
				ers = ers + ChatColor.RESET + "色:" + ChatColor.GREEN + "正常";
			}
			if(s == null)
			{
				ers = ers + ChatColor.RESET + "スタイル:" + ChatColor.RED + "エラー";
				error = true;
			}else{
				ers = ers + ChatColor.RESET + "スタイル:" + ChatColor.GREEN + "正常";
			}
			if(loc == null)
			{
				ers = ers + ChatColor.RESET + "座標:" + ChatColor.RED + "エラー";
				error = true;
			}else{
				ers = ers + ChatColor.RESET + "座標:" + ChatColor.GREEN + "正常";
			}
			if(error)
			{
				p.sendMessage("引数でエラーが発生しました。以下の通りです。");
				p.sendMessage(ers);
				p.sendMessage("/huc hspawn [種類] [色] [スタイル]");
				return true;
			}
			Horse h = HorseMaker.makeHorse(v, c, s,loc);
			double x = Utils.RoundingUp(loc.getX());
			double y = Utils.RoundingUp(loc.getY());
			double z = Utils.RoundingUp(loc.getZ());
			p.sendMessage("馬を" +ChatColor.UNDERLINE + "(" + x + "," + y + "," + z + ")" + ChatColor.RESET + "へスポーンさせました。");
			return true;
		}
		if(args[0].equalsIgnoreCase("reload"))
		{
			if( !p.hasPermission(Variables.Permission_Reload))
			{
				p.sendMessage(Variables.NotHavePermission);
				return true;
			}
			huc.reloadConfig();
			range = huc.getConfig().getInt("EffectRange");
			huc.reload();
			p.sendMessage("config.ymlを再読み込みしました。");
			return true;
		}
		if(args[0].equalsIgnoreCase("help"))
		{
			Utils.sendHelpMessage(p);
			return true;
		}
		if(nh.size() < 1)
		{
			p.sendMessage(ChatColor.GOLD.toString() + range + "マス以内に馬がいません。");
			return true;
		}

		if(args[0].equalsIgnoreCase("variant"))
		{
			if( !p.hasPermission(Variables.Permission_Variant))
			{
				p.sendMessage(Variables.NotHavePermission);
				return true;
			}
			if(args.length < 2)
			{
				p.sendMessage(ChatColor.GOLD + "種類が入力されていません。");
				Utils.sendHelpOfVariant(p);
				return true;
			}
			Variant v = VariantGetter.getVariant(args[1]);
			if(v == null)
			{
				p.sendMessage("エラーが発生しました。");
				Utils.sendHelpOfVariant(p);
				return true;
			}
			for(Horse h : nh)
			{
				h.setVariant(v);
			}
			p.sendMessage(ChatColor.AQUA.toString() + nh.size() + ChatColor.RESET + "体の馬を変更しました。");
			return true;
		}
		if(args[0].equalsIgnoreCase("style"))
		{
			if( !p.hasPermission(Variables.Permission_Style))
			{
				p.sendMessage(Variables.NotHavePermission);
				return true;
			}
			if(args.length < 2)
			{
				p.sendMessage(ChatColor.GOLD + "スタイルが入力されていません。");
				Utils.sendHelpOfStyle(p);
				return true;
			}
			Style s = StyleGetter.getStyle(args[1]);
			if(s == null)
			{
				p.sendMessage("スタイルが不正です。");
				Utils.sendHelpOfStyle(p);
				return true;
			}
			for(Horse h : nh)
			{
				h.setStyle(s);
			}
			p.sendMessage(ChatColor.AQUA.toString() + nh.size() + ChatColor.RESET + "体の馬を変更しました。");
			return true;
		}
		if(args[0].equalsIgnoreCase("color"))
		{
			if( !p.hasPermission(Variables.Permission_Color))
			{
				p.sendMessage(Variables.NotHavePermission);
				return true;
			}
			if(args.length < 2)
			{
				p.sendMessage(ChatColor.GOLD + "色が入力されていません。");
				Utils.sendHelpOfColor(p);
				return true;
			}
			Color c = ColorGetter.getColor(args[1]);
			if(c == null)
			{
				p.sendMessage("色が不正です。");
				Utils.sendHelpOfColor(sender);
				return true;
			}
			for(Horse h : nh)
			{
				h.setColor(c);
			}
			p.sendMessage(ChatColor.AQUA.toString() + nh.size() + ChatColor.RESET + "体の馬を変更しました。");
			return true;
		}
		if(args[0].equalsIgnoreCase("dome"))
		{
			if( !p.hasPermission(Variables.Permission_Dome))
			{
				p.sendMessage(Variables.NotHavePermission);
				return true;
			}
			for(Horse h : nh)
			{
				h.setTamed(true);
			}
			p.sendMessage(ChatColor.AQUA.toString() + nh.size() + ChatColor.RESET + "体の馬を変更しました。");
			return true;
		}
		if(args[0].equalsIgnoreCase("dismount-all"))
		{
			if( !p.hasPermission(Variables.Permission_DismountAll))
			{
				p.sendMessage(Variables.NotHavePermission);
				return true;
			}
			int count = 0;
			for(Player pa : Bukkit.getOnlinePlayers())
			{
				if(pa.getVehicle() instanceof Horse)
				{
					pa.leaveVehicle();
					count = count + 1;
				}
			}
			p.sendMessage(ChatColor.AQUA.toString() + count +ChatColor.RESET +  "人を馬から降ろしました。");
			return true;
		}
		if(args[0].equalsIgnoreCase("remove"))
		{
			if( !p.hasPermission(Variables.Permission_Remove))
			{
				p.sendMessage(Variables.NotHavePermission);
				return true;
			}
			int count = 0;
			for(Horse hs : nh)
			{
				hs.remove();
				count = count + 1;
			}
			p.sendMessage(ChatColor.AQUA.toString() + count + ChatColor.RESET + "体削除しました。");
			return true;		
		}
		if(args[0].equalsIgnoreCase("name"))
		{
			if( !p.hasPermission(Variables.Permission_Name))
			{
				p.sendMessage(Variables.NotHavePermission);
				return true;
			}
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
		
		Utils.sendHelpMessage(p);
		return true;
	}

}
