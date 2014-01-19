package jp._RS_.huc;

import java.util.ArrayList;

import org.bukkit.ChatColor;

public class Variables {

	//------メッセージ-------//
	public static final String NotHavePermission = ChatColor.RED + "権限がありません。権限設定を確認してください。";
	//------権限-------//
	public static final String Permission_ToggleDamage = "huc.td";
	public static final String Permission_PSpawn = "huc.pspawn";
	public static final String Permission_LSpawn = "huc.lspawn";
	public static final String Permission_HSpawn = "huc.hspawn";
	public static final String Permission_Reload = "huc.reload";
	public static final String Permission_Variant = "huc.variant";
	public static final String Permission_Style = "huc.style";
	public static final String Permission_Color = "huc.color";
	public static final String Permission_Dome = "huc.dome";
	public static final String Permission_DismountAll = "huc.dismountall";
	public static final String Permission_Remove = "huc.remove";
	public static final String Permission_Name = "huc.name";
	public static final String Permission_CreateSign = "huc.csign";
	public static final String Permission_UseSign = "huc.usign";
	public static final String Permission_BreakSign = "huc.bsign";
	//------コンフィグ------//
	public static final String Config_Path_EffectRange = "EffectRange";
	public static final String Config_Path_DisableDamage = "Disable-Damage";
	public static final String[] Commands = {"td","pspawn","lspawn","hspawn","reload","variant","style","color","dome","dismount-all","remove","name","help"};
	private static ArrayList<String> CommandsList = new ArrayList<String>();
	public static ArrayList<String> getCommandsList()
	{
		CommandsList.clear();
		for(String s : Commands)
		{
			CommandsList.add(s);
		}
		return CommandsList;
	}

}
