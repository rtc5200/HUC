package jp._RS_.huc;

import java.util.logging.Logger;
import jp._RS_.huc.command.*;
import jp._RS_.huc.config.ConfigHandler;
import org.bukkit.plugin.java.JavaPlugin;
public class HUC extends JavaPlugin{
	private CommandGeneral cg;
	private Logger log;
	private ConfigHandler config;
	private DamageEvents de;
	public void onEnable()
	{
		log = Logger.getLogger("minecraft");
		log.info("[HUC]ロード中....");
		config = ConfigHandler.load(this);
		cg = new CommandGeneral(this);
		this.getCommand("huc").setExecutor(cg);
		de = new DamageEvents(this);
		getServer().getPluginManager().registerEvents(de,this);
		log.info("[HUC]ロード完了");
	}
	public void onDisable()
	{
		log.info("[HUC]終了...");
	}
	public void reload()
	{
		this.reloadConfig();
		config = ConfigHandler.load(this);
		de.loadDisabled(config);
	}
	public DamageEvents getDEvent()
	{
		return de;
	}
	public ConfigHandler getConfigHandler()
	{
		return config;
	}

}
