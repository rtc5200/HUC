package jp._RS_.huc.sign;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;

import jp._RS_.huc.HUC;
import jp._RS_.huc.Variables;
import jp._RS_.huc.command.ColorGetter;
import jp._RS_.huc.command.StyleGetter;
import jp._RS_.huc.command.VariantGetter;

public class SignCreateEvent implements Listener {
	private HUC huc;
	public SignCreateEvent(HUC huc) {
		this.huc = huc;
	}
	@EventHandler(priority = EventPriority.LOW)
	public void onCreate(SignChangeEvent e)
	{
		Block b = e.getBlock();
		Player p = e.getPlayer();
		if(e.getLine(0).equals("[HUC]"))
		{
			if(!p.hasPermission(Variables.Permission_CreateSign)){
				p.sendMessage(Variables.NotHavePermission);
				return;
			}
			e.setLine(0, ChatColor.AQUA + "[HUC]");
			String line1 = e.getLine(0);
			String line2 = e.getLine(1);//種類
			String line3 = e.getLine(2);//色
			String line4 = e.getLine(3);//スタイル
			boolean error = false;
			p.sendMessage("スポーン看板を以下の内容で設定しました。");
			if(VariantGetter.getVariant(line2) == null)
			{
				p.sendMessage("2行目:" + ChatColor.RED + "エラー");
				e.setLine(1, ChatColor.RED + line2);
				error = true;
			}else{
				String line = new String();
				if(line2.equals("r") || line2.equalsIgnoreCase("random"))
				{
					line = "2行目: " + ChatColor.GREEN + "ランダム";
				}else{
					line = "2行目:" + ChatColor.GREEN +VariantGetter.getVariant(line2).toString();
				}

				p.sendMessage(line);
				e.setLine(1, ChatColor.GREEN + line2);
			}
			if(ColorGetter.getColor(line3) == null)
			{
				p.sendMessage("3行目:" + ChatColor.RED + "エラー");
				e.setLine(2,ChatColor.RED + line3);
				error = true;
			}else{
				String line = new String();
				if(line3.equals("r") || line3.equalsIgnoreCase("random")){
					line = "3行目: " + ChatColor.GREEN + "ランダム";
				}else{
					line = "3行目:" + ChatColor.GREEN + ColorGetter.getColor(line3).toString();
				}

				p.sendMessage(line);
				e.setLine(2, ChatColor.GREEN + line3);
			}
			if(StyleGetter.getStyle(line4) == null)
			{
				p.sendMessage("4行目:" + ChatColor.RED + "エラー");
				e.setLine(3, ChatColor.RED + line4);
				error = true;
			}else{
				String line = new String();
				if(line4.equals("r") || line4.equalsIgnoreCase("random")){
					line = "4行目: " + ChatColor.GREEN + "ランダム";
				}else{
					line = "4行目:" + ChatColor.GREEN + ColorGetter.getColor(line4).toString();
				}
				p.sendMessage(line);
				e.setLine(3, ChatColor.GREEN + line4);
			}
			if(error)
			{
				e.setLine(0, ChatColor.RED + "[HUC]");
			}
		}
	}

}
