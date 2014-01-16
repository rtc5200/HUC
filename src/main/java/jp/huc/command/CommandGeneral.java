package jp.huc.command;

import java.util.ArrayList;
import java.util.List;

import jp.rtc5200.huc.DamageEvents;
import jp.rtc5200.huc.HUC;
import jp.rtc5200.huc.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandGeneral implements CommandExecutor {
	private HUC huc;
	DamageEvents de;
	int range = 5;
	public CommandGeneral(HUC huc)
	{
		this.huc = huc;
		de = new DamageEvents();
		huc.getServer().getPluginManager().registerEvents(de,huc);
		range = huc.getConfig().getInt("EffectRange");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] args) {
		ArrayList<Horse> nhs = new ArrayList<Horse>();
		if(sender instanceof ConsoleCommandSender)
		{
			sender.sendMessage(ChatColor.RED + "コンソールからは使えません。");
			return true;
		}
		if(sender instanceof BlockCommandSender)
		{
			World world = ((BlockCommandSender) sender).getBlock().getWorld();
			Location loc = ((BlockCommandSender) sender).getBlock().getLocation();
			
			for(Entity e : world.getEntities())	
			{
				if(loc.distance(e.getLocation()) <= range)
				{
					if(e instanceof Horse)
					{
						nhs.add((Horse) e);
					}
				}
			}
		}else{
			Player p = (Player)sender;
			if(!p.isOp() || !p.hasPermission("huc.use"))
			{
				sender.sendMessage("権限がありません。");
				return true;
			}
			List<Entity> es = p.getNearbyEntities(range*2, 0, range*2);
			for(Entity e : es)
			{
				if(e instanceof Horse)
				{
					nhs.add((Horse) e);
				}
			}
		}
		if(args == null || args.length < 1)
		{
			sender.sendMessage(ChatColor.GOLD + "引数が足りません。");
			Utils.sendHelpMessage(sender);
			return true;
		}
		//------
		
		
		if(args[0].equalsIgnoreCase("td"))
		{
			if(de.getDisabled())
			{
				de.setDisabled(false);
				sender.sendMessage("ダメージを有効にしました。");
				return true;
			}else{
				de.setDisabled(true);
				sender.sendMessage("ダメージを無効にしました。");
				return true;
			}
		}
		
		if(args[0].equalsIgnoreCase("pspawn"))
		{
			if(args.length < 5)
			{
				sender.sendMessage(ChatColor.GOLD + "引数が足りません。");
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
			Location loc = null;
			if(sender instanceof Player)
			{
				loc = new Location(((Player) sender).getWorld(),Integer.parseInt(args[4]),Integer.parseInt(args[5]),Integer.parseInt(args[6]));
			}
			if(sender instanceof BlockCommandSender)
			{
				loc = new Location(((BlockCommandSender) sender).getBlock().getWorld(),Integer.parseInt(args[4]),Integer.parseInt(args[5]),Integer.parseInt(args[6]));
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
		if(args[0].equalsIgnoreCase("hspawn"))
		{
			if(args.length < 4)
			{
				sender.sendMessage("引数が足りません。");
				sender.sendMessage("/huc hspawn [種類] [色] [スタイル]");
				return true;
			}
			Variant v = VariantGetter.getVariant(args[1]);
			Color c = ColorGetter.getColor(args[2]);
			Style s = StyleGetter.getStyle(args[3]);
			Location loc = null;
			if(sender instanceof Player)
			{
				loc = ((Player) sender).getLocation();
			}
			if(sender instanceof BlockCommandSender)
			{
				loc = ((BlockCommandSender) sender).getBlock().getLocation();
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
				sender.sendMessage("引数でエラーが発生しました。以下の通りです。");
				sender.sendMessage(ers);
				sender.sendMessage("/huc hspawn [種類] [色] [スタイル]");
				return true;
			}
			Horse h = HorseMaker.makeHorse(v, c, s,loc);
			double x = Utils.RoundingUp(loc.getX());
			double y = Utils.RoundingUp(loc.getY());
			double z = Utils.RoundingUp(loc.getZ());
			sender.sendMessage("馬を" +ChatColor.UNDERLINE + "(" + x + "," + y + "," + z + ")" + ChatColor.RESET + "へスポーンさせました。");
			return true;
		}
		if(args[0].equalsIgnoreCase("reload"))
		{
			huc.reloadConfig();
			sender.sendMessage("config.ymlを再読み込みしました。");
			range = huc.getConfig().getInt("EffectRange");
			de.setDisabled(huc.getConfig().getBoolean("disable-damage"));
			return true;
		}
		
		
		//------馬検索いらないコマンドここまで
		if(nhs.size() <= 0)
		{
			sender.sendMessage(ChatColor.GOLD.toString() + range + "マス以内に馬がいません。");
			return true;
		}
		if(args.length < 1 || args[0] == null)
		{
			sender.sendMessage(ChatColor.GOLD + "引数が足りません。");
			Utils.sendHelpMessage(sender);
			return true;
		}
		if(args[0].equalsIgnoreCase("variant"))
		{
			if(args.length < 2)
			{
				sender.sendMessage(ChatColor.GOLD + "種類が入力されていません。");
				Utils.sendHelpOfVariant(sender);
				return true;
			}
			Variant v = VariantGetter.getVariant(args[1]);
			if(v == null)
			{
				sender.sendMessage("エラーが発生しました。");
				Utils.sendHelpOfVariant(sender);
				return true;
			}
			for(Horse h : nhs)
			{
				h.setVariant(v);
			}
			sender.sendMessage(ChatColor.AQUA.toString() + nhs.size() + ChatColor.RESET + "体の馬を変更しました。");
			return true;
			//種類変更
		}
		if(args[0].equalsIgnoreCase("style"))
		{
			if(args.length < 2)
			{
				sender.sendMessage(ChatColor.GOLD + "スタイルが入力されていません。");
				Utils.sendHelpOfStyle(sender);
				return true;
			}
			Style s = StyleGetter.getStyle(args[1]);
			if(s == null)
			{
				sender.sendMessage("スタイルが不正です。");
				Utils.sendHelpOfStyle(sender);
				return true;
			}
			for(Horse h : nhs)
			{
				h.setStyle(s);
			}
			sender.sendMessage(ChatColor.AQUA.toString() + nhs.size() + ChatColor.RESET + "体の馬を変更しました。");
			return true;
			//色パターン変更
		}
		if(args[0].equalsIgnoreCase("color"))
		{
			if(args.length < 2)
			{
				sender.sendMessage(ChatColor.GOLD + "色が入力されていません。");
				Utils.sendHelpOfColor(sender);
				return true;
			}
			Color c = ColorGetter.getColor(args[1]);
			if(c == null)
			{
				sender.sendMessage("色が不正です。");
				Utils.sendHelpOfColor(sender);
				return true;
			}
			for(Horse h : nhs)
			{
				h.setColor(c);
			}
			sender.sendMessage(ChatColor.AQUA.toString() + nhs.size() + ChatColor.RESET + "体の馬を変更しました。");
			return true;
			//色変更
		}
		if(args[0].equalsIgnoreCase("dome"))
		{
			for(Horse h : nhs)
			{
				h.setTamed(true);
			}
			sender.sendMessage(ChatColor.AQUA.toString() + nhs.size() + ChatColor.RESET + "体の馬を変更しました。");
			return true;
			//家畜化
		}
		if(args[0].equalsIgnoreCase("ridable"))
		{
			for(Horse h : nhs)
			{
				h.setTamed(true);
			}
			ItemStack is = new ItemStack(Material.SADDLE);
			is.setAmount(1);
			for(Horse h : nhs)
			{
				h.getInventory().setSaddle(is);
			}
			sender.sendMessage(ChatColor.AQUA.toString() + nhs.size() + ChatColor.RESET + "体の馬を変更しました。");
			return true;
		}
		if(args[0].equalsIgnoreCase("dismount-all"))
		{
			Player[] ps = Bukkit.getOnlinePlayers();
			int count = 0;
			for(Player pa : ps)
			{
				if(pa.getVehicle() instanceof Horse)
				{
					pa.leaveVehicle();
					count = count + 1;
				}
			}
			sender.sendMessage(ChatColor.AQUA.toString() + count +ChatColor.RESET +  "人を馬から降ろしました。");
			return true;
		}
		if(args[0].equalsIgnoreCase("remove"))
		{
			int count = 0;
			for(Horse hs : nhs)
			{
				hs.remove();
				count = count + 1;
			}
			sender.sendMessage(ChatColor.AQUA.toString() + count + ChatColor.RESET + "体削除しました。");
			return true;		
		}
		if(args[0].equalsIgnoreCase("name"))
		{
			if(args.length == 1)
			{
				for(Horse hs : nhs)
				{
					hs.setCustomName("");
				hs.setCustomNameVisible(false);
				}
				sender.sendMessage("名前を削除しました。");
				return true;
			}else{
				for(Horse hs : nhs)
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
