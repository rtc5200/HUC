package jp.rtc5200.huc;

import java.util.logging.Logger;

import jp.huc.command.*;
import jp.huc.config.ConfigLoader;

import org.bukkit.plugin.java.JavaPlugin;
public class HUC extends JavaPlugin{
	CommandGeneral cg;
	Logger log;
	ConfigLoader cloader;
	public void onEnable()
	{
		log = Logger.getLogger("minecraft");
		log.info("[HUC]ロード中....");
		cg = new CommandGeneral(this);
		this.getCommand("huc").setExecutor(cg);
		cloader = new ConfigLoader(this);
		log.info("[HUC]ロード完了");
	}
	public void onDisable()
	{
		log.info("[HUC]終了...");
	}

}
