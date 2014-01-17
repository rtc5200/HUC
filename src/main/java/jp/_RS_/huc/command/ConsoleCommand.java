package jp._RS_.huc.command;

import jp._RS_.huc.DamageEvents;
import jp._RS_.huc.HUC;
import jp._RS_.huc.Utils;
import jp._RS_.huc.Variables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;

public class ConsoleCommand {
	private HUC huc;
	public ConsoleCommand(HUC huc)
	{
		this.huc = huc;
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args) {
		DamageEvents de = huc.de;
		if(args[0].equalsIgnoreCase("td"))
		{
			if(de.getDisabled())
			{
				de.setDisabled(false);
				sender.sendMessage("ダメージを" +"有効" + "にしました。");
				return true;
			}else{
				de.setDisabled(true);
				sender.sendMessage("ダメージを" + "無効" + "にしました。");
				return true;
			}
		}
		if(args[0].equalsIgnoreCase("pspawn"))
		{
			if(args.length < 5)
			{
				sender.sendMessage("引数が足りません。");
				sender.sendMessage("/huc pspawn [種類] [色] [スタイル] [プレイヤー]");
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
				sender.sendMessage("引数でエラーが発生しました。以下の通りです。");
				sender.sendMessage(ers);
				sender.sendMessage("/huc pspawn [種類] [色] [スタイル] [プレイヤー]");
				return true;
			}
			Horse h = HorseMaker.makeHorse(v, c, s,player.getLocation());
			h.teleport(player);
			sender.sendMessage("馬を" + player.getName() + "のもとへスポーンさせました。");
			player.sendMessage(sender.getName() + "からスポーンされました。");
			h.setPassenger(player);
			return true;
		}
		if(args[0].equalsIgnoreCase("lspawn"))
		{
			if(args.length < 7)
			{
				sender.sendMessage("引数が足りません。");
				sender.sendMessage("/huc lspawn [種類] [色] [スタイル] [x] [y] [z]");
				return true;
			}
			Variant v = VariantGetter.getVariant(args[1]);
			Color c = ColorGetter.getColor(args[2]);
			Style s = StyleGetter.getStyle(args[3]);
			Location loc = new Location(Utils.getWorld(),Double.parseDouble(args[4]),Double.parseDouble(args[5]),Double.parseDouble(args[6]));
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
				sender.sendMessage("引数でエラーが発生しました。以下の通りです。");
				sender.sendMessage(ers);
				sender.sendMessage("/huc lspawn [種類] [色] [スタイル] [x] [y] [z]");
				return true;
			}
			Horse h = HorseMaker.makeHorse(v, c, s,loc);
			double x = Utils.RoundingUp(loc.getX());
			double y = Utils.RoundingUp(loc.getY());
			double z = Utils.RoundingUp(loc.getZ());
			sender.sendMessage("馬を" + ChatColor.UNDERLINE + "(" + x + "," + y + "," + z + ")" + ChatColor.RESET +  "へスポーンさせました。");
			return true;
		}
		if(args[0].equalsIgnoreCase("reload"))
		{
			huc.reloadConfig();
			sender.sendMessage("config.ymlを再読み込みしました。");
			de.setDisabled(huc.getConfig().getBoolean("disable-damage"));
			return true;
		}
		return false;
		
	}

}
