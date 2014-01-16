package jp.huc.command;

import java.util.Random;

import org.bukkit.entity.Horse.Color;

public class ColorGetter {
	public static Color getColor(String arg)
	{
		if(arg == null)
		{
			return null;
		}
		Color c;
		if(arg.equalsIgnoreCase("random"))
		{
			c = getRandomColor();
		}else{
			switch(arg)
			{
			case "0": arg = "BLACK";break;
			case "1": arg = "BROWN";break;
			case "2": arg = "CHESTNUT";break;
			case "3": arg = "CREAMY";break;
			case "4": arg = "DARK_BROWN";break;
			case "5": arg = "GRAY";break;
			case "6": arg = "WHITE";break;
			}
			c = Color.valueOf(arg);
			/*switch(arg)
		{
		case "0":c = Color.BLACK;break;
		case "1":c = Color.BROWN;break;
		case "2":c = Color.CHESTNUT;break;
		case "3":c = Color.CREAMY;break;
		case "4":c = Color.DARK_BROWN;break;
		case "5":c = Color.GRAY;break;
		case "6":c = Color.WHITE;break;
		case "BLACK": c = Color.BLACK;break;
		case "BROWN": c = Color.BROWN;break;
		case "CHESTNUT": c = Color.CHESTNUT;break;
		case "CREAMY": c = Color.CREAMY;break;
		case "DARK_BROWN": c = Color.DARK_BROWN;break;
		case "GRAY": c = Color.GRAY;break;
		case "WHITE": c = Color.WHITE;break;
		case "random": c = getRandomColor();break;
		default:c = null;	
		}*/
		}
		return c;
	}
	private static Color getRandomColor()
	{
		Random r = new Random();
		int i = r.nextInt(7);
		Color c = null;
		switch (i)
		{
		case 0:c = Color.BLACK;break;
		case 1:c = Color.BROWN;break;
		case 2:c = Color.CHESTNUT;break;
		case 3:c = Color.CREAMY;break;
		case 4:c = Color.DARK_BROWN;break;
		case 5:c = Color.GRAY;break;
		case 6:c = Color.WHITE;break;
		}
		return c;
	}
}
