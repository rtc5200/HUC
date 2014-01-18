package jp._RS_.huc.config;

import org.bukkit.configuration.file.FileConfiguration;

import jp._RS_.huc.HUC;
import jp._RS_.huc.Variables;

public class ConfigHandler {
	private Boolean DisableDamage = false;
	private Integer Range = 5;
	private HUC huc;
	public ConfigHandler(HUC huc)
	{
		FileConfiguration fc = huc.getConfig();
		fc.addDefault(Variables.Config_Path_EffectRange, 5);
		fc.addDefault(Variables.Config_Path_DisableDamage, false);
		fc.options().copyDefaults(true);
		String k = System.getProperty("line.separator");
		fc.options().header("---------"+ 
				k + "EffectRange    -   既存の馬を書き換えるコマンドの効果範囲の半径(デフォルト:5)"+
				k + "disable-damage    -    馬の被ダメージ有効(false)/無効(true)" + 
				k + "--------");
		fc.options().copyHeader(true);
		huc.saveConfig();
		DisableDamage = fc.getBoolean(Variables.Config_Path_DisableDamage);
		Range = fc.getInt(Variables.Config_Path_EffectRange);
	}
	public static ConfigHandler load(HUC huc)
	{
		ConfigHandler config = new ConfigHandler(huc);
		return config;	 
	}
	public static ConfigHandler reload(HUC huc)
	{
		huc.reloadConfig();
		return load(huc);
	}
	public boolean getDamageDisabled()
	{
		return DisableDamage;
	}
	public Integer getRange()
	{
		return Range;
	}

}
