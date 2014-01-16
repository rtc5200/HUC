package jp.huc.command;

import java.util.Random;

import org.bukkit.entity.Horse.Style;

public class StyleGetter {
	public static Style getStyle(String arg)
	{
		if(arg == null)
		{
			return null;
		}
		Style s;
		if(arg.equalsIgnoreCase("random"))
		{
			s = getRandomStyle();
		}else{
			switch(arg)
			{
			case "0":arg = "NONE";break;
			case "1":arg = "BLACK_DOTS";break;
			case "2":arg = "WHITE_DOTS";break;
			case "3":arg = "WHITE";break;
			case "4":arg = "WHITEFIELD";break;
			}
			s = Style.valueOf(arg);
			/*switch(arg)
		{
		case "0":s = Style.NONE;break;
		case "1":s = Style.BLACK_DOTS;break;
		case "2":s = Style.WHITE_DOTS;break;
		case "3":s = Style.WHITE;break;
		case "4":s = Style.WHITEFIELD;break;
		case "BLACK_DOT":s = Style.BLACK_DOTS;break;
		case "black_dot":s = Style.BLACK_DOTS;break;
		case "WHITE_DOT":s = Style.WHITE_DOTS;break;
		case "white_dot":s = Style.WHITE_DOTS;break;
		case"WHITE":s = Style.WHITE;break;
		case"whiteE":s = Style.WHITE;break;
		case"WHITEFIELD":s = Style.WHITEFIELD;break;
		case"whitefield":s = Style.WHITEFIELD;break;
		case"NONE":s = Style.NONE;break;
		case"none":s = Style.NONE;break;
		case "random": s = getRandomStyle();break;
		case "RANDOM": s = getRandomStyle();break;
		default: s = null;break;
		}*/
		}
		return s;
		
	}
	private static Style getRandomStyle()
	{
		Random r = new Random();
		int i = r.nextInt(5);
		Style s = null;
		switch (i)
		{
		case 0:s = Style.NONE;break;
		case 1:s = Style.BLACK_DOTS;break;
		case 2:s = Style.WHITE_DOTS;break;
		case 3:s = Style.WHITE;break;
		case 4:s = Style.WHITEFIELD;break;
		}
		return s;
	}

}
