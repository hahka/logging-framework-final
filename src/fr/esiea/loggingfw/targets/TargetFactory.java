package fr.esiea.loggingfw.targets;

import fr.esiea.loggingfw.OurLogger;

public final class TargetFactory {

	public static AbstractTarget getTarget(String param)
    {
        AbstractTarget target;

		switch(param){
			
			case "console":
				target = new ConsoleTarget();
				break;
				
			case "file":
				target = new ConsoleTarget();
				break;
				
			case "bdd":
				target = new ConsoleTarget();
				break;
				
			default:target = new ConsoleTarget();
				break;
			
		}
        return target;
    }
}