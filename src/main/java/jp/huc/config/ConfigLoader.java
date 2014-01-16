package jp.huc.config;

import org.bukkit.configuration.file.FileConfiguration;

import jp.rtc5200.huc.HUC;

public class ConfigLoader {
	private HUC huc;
	private FileConfiguration config;
	public ConfigLoader(HUC huc)
	{
		this.huc = huc;
		config = huc.getConfig();
		config.addDefault("EffectRange", 5);
		config.addDefault("disable-damage", false);
		config.options().copyDefaults(true);
		String k = System.getProperty("line.separator");
		config.options().header("---------"+ 
				k + "EffectRange    -    "
				+ "既存の馬を書き換えるコマンドの効果範囲の半径(デフォルト:5)" + 
				k + "disable-damage    -    " + "馬の被ダメージ有効(false)/無効(true)" + 
				k + "--------");
		config.options().copyHeader(true);
		huc.saveConfig();
	}

}
