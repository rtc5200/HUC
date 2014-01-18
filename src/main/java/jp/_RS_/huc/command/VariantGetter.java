package jp._RS_.huc.command;

import java.util.Random;

import org.bukkit.entity.Horse.Variant;

public class VariantGetter {
	public static Variant getVariant(String arg)
	{
		if(arg == null)
		{
			return null;
		}
		Variant v;
		if(arg.equalsIgnoreCase("random") || arg.equalsIgnoreCase("r"))
		{
			v = getRandomVariant();
		}else{
			switch(arg)
			{
			case "0": arg = "HORSE";break;
			case "1": arg = "DONKEY";break;
			case "2": arg = "MULE";break;
			case "3": arg = "UNDEAD_HORSE";break;
			case "4": arg = "SKELETON_HORSE";break;
			case "UNDEAD":arg = "UNDEAD_HORSE";break;
			case "SKELETON":arg = "SKELETON_HORSE";break;
			}
			try{
				v = Variant.valueOf(arg);
			}catch(IllegalArgumentException e)
			{
				v = null;
			}
			
			/*switch(arg)
		{
		case "0": v = Variant.HORSE;break; 
		case "1": v = Variant.DONKEY;break;
		case "2": v = Variant.MULE;break;
		case "3": v = Variant.UNDEAD_HORSE; break;
		case "4": v = Variant.SKELETON_HORSE;break;
		case "horse": v = Variant.HORSE;break;
		case "HORSE": v = Variant.HORSE;break;
		case "donkey": v = Variant.DONKEY;break;
		case "DONKEY": v = Variant.DONKEY;break;
		case "mule": v = Variant.MULE;break;
		case "MULE": v = Variant.MULE;break;
		case "undead": v = Variant.UNDEAD_HORSE; break;
		case "UNDEAD": v = Variant.UNDEAD_HORSE; break;
		case "skeleton": v = Variant.SKELETON_HORSE;break;
		case "SKELTON": v = Variant.SKELETON_HORSE;break;
		case "random": v = getRandomVariant();break;
		case "RANDOM": v = getRandomVariant();break;
		}*/
		}
		return v;
	}
	public static  Variant getRandomVariant()
	{
		Random r = new Random();
		int i = r.nextInt(4);
		Variant v = null;
		switch(i)
		{
		case 0: v = Variant.HORSE;break; 
		case 1: v = Variant.HORSE;break;
		case 2: v = Variant.DONKEY;break;
		case 3: v = Variant.MULE;break;
		case 4: v = Variant.UNDEAD_HORSE; break;
		case 5: v = Variant.SKELETON_HORSE;break;
		}
		return v;
	}

}
