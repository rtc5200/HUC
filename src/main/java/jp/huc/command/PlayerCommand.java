package jp.huc.command;

import java.util.ArrayList;

import jp.rtc5200.huc.DamageEvents;
import jp.rtc5200.huc.HUC;
import jp.rtc5200.huc.Utils;
import jp.rtc5200.huc.Variables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;

public class PlayerCommand {
	HUC huc;

	public PlayerCommand(HUC huc) {
		this.huc = huc;
	}
	public boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args,HUC huc){
		int range = huc.getConfig().getInt("EffectRange");
		Player p = (Player)sender;
		ArrayList<Horse> nh = new ArrayList<Horse>();
		DamageEvents de = huc.de;
		for(Entity e : p.getNearbyEntities(range*2, 0, range*2))
		{
			if(e instanceof Horse)
			{
				nh.add((Horse) e);
			}
		}
		if(args[0].equalsIgnoreCase("td"))
		{
			if(!p.isOp() || !p.hasPermission(Variables.Permission_ToggleDamage))
			{
				p.sendMessage(Variables.NotHavePermission);
				return true;
			}
			if(de.getDisabled())
			{
				de.setDisabled(false);
				p.sendMessage("ダメージを" +"有効" + "にしました。");
				return true;
			}else{
				de.setDisabled(true);
				p.sendMessage("ダメージを" + "無効" + "にしました。");
				return true;
			}
		}
		if(args[0].equalsIgnoreCase("pspawn"))
		{
			if(!p.isOp() || !p.hasPermission(Variables.Permission_PSpawn))
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
			sender.sendMessage("馬を" + player.getName() + "のもとへスポーンさせました。");
			player.sendMessage(p.getName() + "からスポーンされました。");
			h.setPassenger(player);
			return true;
		}
		if(args[0].equalsIgnoreCase("lspawn"))
		{
			if(!p.isOp() || !p.hasPermission(Variables.Permission_LSpawn))
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
			if(!p.isOp() || !p.hasPermission(Variables.Permission_HSpawn))
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
			if(!p.isOp() || !p.hasPermission(Variables.Permission_Reload))
			{
				p.sendMessage(Variables.NotHavePermission);
				return true;
			}
			huc.reloadConfig();
			p.sendMessage("config.ymlを再読み込みしました。");
			range = huc.getConfig().getInt("EffectRange");
			de.setDisabled(huc.getConfig().getBoolean("disable-damage"));
			return true;
		}
		
		if(nh.size() < 1)
		{
			p.sendMessage(ChatColor.GOLD.toString() + range + "マス以内に馬がいません。");
			return true;
		}
		
		if(args[0].equalsIgnoreCase("variant"))
		{
			if(!p.isOp() || !p.hasPermission(Variables.Permission_Variant))
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
		
		return false;
	}

}
