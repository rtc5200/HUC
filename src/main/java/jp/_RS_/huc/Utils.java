package jp._RS_.huc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Utils {
	public static World getWorld()
	{
		List<World> worlds = Bukkit.getServer().getWorlds();
		return worlds.get(0);
	}
	public static void sendHelpMessage(CommandSender s)
	{
		s.sendMessage("/huc [コマンド] [引数]");
		s.sendMessage(ChatColor.GREEN + "コマンド一覧");
		s.sendMessage(ChatColor.GREEN + "variant:種類変更");
		s.sendMessage(ChatColor.GREEN + "style:色パターン変更");
		s.sendMessage(ChatColor.GREEN + "color:色変更");
		s.sendMessage(ChatColor.GREEN + "dome:飼い慣らす");
		s.sendMessage(ChatColor.GREEN + "ridable:飼い慣らしてサドルをつけてすぐ乗れる状態にする");
		s.sendMessage(ChatColor.GREEN + "dismount-all:全員を馬から降ろす");
		s.sendMessage(ChatColor.GREEN + "name:名前をつける");
		s.sendMessage(ChatColor.GREEN + "remove:削除する");
		s.sendMessage(ChatColor.GREEN + "td:馬のダメージ有効/無効切り替え");
		s.sendMessage(ChatColor.GREEN + "pspawn:指定プレイヤーへ馬をスポーンさせ、乗せる");
		s.sendMessage(ChatColor.GREEN + "lspawn:指定座標で馬をスポーンさせる");
		s.sendMessage(ChatColor.GREEN + "hspawn:コマンドを使ったプレイヤー/コマブロへ馬をスポーンさせる");
		s.sendMessage(ChatColor.GREEN + "relaod:config.ymlを再読み込みする");
	}
	public static void sendHelpMessage(Player s)
	{
		s.sendMessage("/huc [コマンド] [引数]");
		s.sendMessage(ChatColor.GREEN + "コマンド一覧");
		s.sendMessage(ChatColor.GREEN + "variant:種類変更");
		s.sendMessage(ChatColor.GREEN + "style:色パターン変更");
		s.sendMessage(ChatColor.GREEN + "color:色変更");
		s.sendMessage(ChatColor.GREEN + "dome:飼い慣らす");
		s.sendMessage(ChatColor.GREEN + "ridable:飼い慣らしてサドルをつけてすぐ乗れる状態にする");
		s.sendMessage(ChatColor.GREEN + "dismount-all:全員を馬から降ろす");
		s.sendMessage(ChatColor.GREEN + "name:名前をつける");
		s.sendMessage(ChatColor.GREEN + "remove:削除する");
		s.sendMessage(ChatColor.GREEN + "td:馬のダメージ有効/無効切り替え");
		s.sendMessage(ChatColor.GREEN + "pspawn:指定プレイヤーへ馬をスポーンさせ、乗せる");
		s.sendMessage(ChatColor.GREEN + "lspawn:指定座標で馬をスポーンさせる");
		s.sendMessage(ChatColor.GREEN + "hspawn:コマンドを使ったプレイヤー/コマブロへ馬をスポーンさせる");
		s.sendMessage(ChatColor.GREEN + "relaod:config.ymlを再読み込みする");
	}
	public  static void sendHelpOfConsole(ConsoleCommandSender s)
	{
		s.sendMessage("/huc [コマンド] [引数]");
		s.sendMessage(ChatColor.GREEN + "コンソールで使えるコマンド一覧");
		s.sendMessage(ChatColor.GREEN + "dismount-all:全員を馬から降ろす");
		s.sendMessage(ChatColor.GREEN + "td:馬のダメージ有効/無効切り替え");
		s.sendMessage(ChatColor.GREEN + "pspawn:指定プレイヤーへ馬をスポーンさせ、乗せる");
		s.sendMessage(ChatColor.GREEN + "lspawn:指定座標で馬をスポーンさせる");
		s.sendMessage(ChatColor.GREEN + "relaod:config.ymlを再読み込みする");
	}
	public static void sendHelpOfVariant(CommandSender s)
	{
		s.sendMessage(ChatColor.GREEN + "馬種類:");
		s.sendMessage(ChatColor.GREEN + "horse(0):馬");
		s.sendMessage(ChatColor.GREEN + "donkey(1):ロバ");
		s.sendMessage(ChatColor.GREEN + "mule(2):ラバ");
		s.sendMessage(ChatColor.GREEN + "undead(3):アンデッド");
		s.sendMessage(ChatColor.GREEN + "skeleton(4):スケルトン");
		s.sendMessage(ChatColor.GREEN + "random:ランダム");
	}
	public static void sendHelpOfVariant(Player s)
	{
		s.sendMessage(ChatColor.GREEN + "馬種類:");
		s.sendMessage(ChatColor.GREEN + "horse(0):馬");
		s.sendMessage(ChatColor.GREEN + "donkey(1):ロバ");
		s.sendMessage(ChatColor.GREEN + "mule(2):ラバ");
		s.sendMessage(ChatColor.GREEN + "undead(3):アンデッド");
		s.sendMessage(ChatColor.GREEN + "skeleton(4):スケルトン");
		s.sendMessage(ChatColor.GREEN + "random:ランダム");
	}
	public static void sendHelpOfStyle(CommandSender s)
	{
		s.sendMessage(ChatColor.GREEN + "スタイル:");
		s.sendMessage(ChatColor.GREEN + "NONE(0):なし");
		s.sendMessage(ChatColor.GREEN + "BLACK_DOT(1):黒斑点");
		s.sendMessage(ChatColor.GREEN + "WHITE_DOT(2):白斑点");
		s.sendMessage(ChatColor.GREEN + "WHITE(3):白縞");
		s.sendMessage(ChatColor.GREEN + "WHITEFIELD(4):白しみ");
		s.sendMessage(ChatColor.GREEN + "random:ランダム");
	}
	public static void sendHelpIfStyle(Player s)
	{
		s.sendMessage(ChatColor.GREEN + "スタイル:");
		s.sendMessage(ChatColor.GREEN + "NONE(0):なし");
		s.sendMessage(ChatColor.GREEN + "BLACK_DOT(1):黒斑点");
		s.sendMessage(ChatColor.GREEN + "WHITE_DOT(2):白斑点");
		s.sendMessage(ChatColor.GREEN + "WHITE(3):白縞");
		s.sendMessage(ChatColor.GREEN + "WHITEFIELD(4):白しみ");
		s.sendMessage(ChatColor.GREEN + "random:ランダム");
	}
	public static void sendHelpOfColor(CommandSender s)
	{
		s.sendMessage(ChatColor.GREEN + "色:");
		s.sendMessage(ChatColor.GREEN + "BLACK(0):黒");
		s.sendMessage(ChatColor.GREEN + "BROWN(1):茶色");
		s.sendMessage(ChatColor.GREEN + "CHESTNUT(2):栗色");
		s.sendMessage(ChatColor.GREEN + "CREAMY(3):明るめの茶色");
		s.sendMessage(ChatColor.GREEN + "DARK_BROWN(4):暗めの茶色");
		s.sendMessage(ChatColor.GREEN + "GRAY(5):灰色");
		s.sendMessage(ChatColor.GREEN + "WHITE(6):白");
	}
	public static void sendHelpOfColor(Player s)
	{
		s.sendMessage(ChatColor.GREEN + "色:");
		s.sendMessage(ChatColor.GREEN + "BLACK(0):黒");
		s.sendMessage(ChatColor.GREEN + "BROWN(1):茶色");
		s.sendMessage(ChatColor.GREEN + "CHESTNUT(2):栗色");
		s.sendMessage(ChatColor.GREEN + "CREAMY(3):明るめの茶色");
		s.sendMessage(ChatColor.GREEN + "DARK_BROWN(4):暗めの茶色");
		s.sendMessage(ChatColor.GREEN + "GRAY(5):灰色");
		s.sendMessage(ChatColor.GREEN + "WHITE(6):白");
	}
	public static double RoundingUp(double d)
	{
		BigDecimal db = new BigDecimal(d);
		return db.setScale(0,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public static List<String> getCommands()
	{
		ArrayList<String> c = new ArrayList<String>();
		c.add("variant");
		c.add("style");
		c.add("color");
		c.add("dome");
		c.add("ridable");
		c.add("dismount-all");
		c.add("remove");
		c.add("td");
		c.add("pspawn");
		c.add("lspawn");
		c.add("hspawn");
		c.add("name");
		c.add("reload");
		return c;
	}

}
