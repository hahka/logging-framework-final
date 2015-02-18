package fr.esiea.loggingfw.targets;

import fr.esiea.loggingfw.OurLogger;

public final class TargetFactory {

	public static AbstractTarget getTarget(String param)
    {
        AbstractTarget target;

		if(param.equals("console")){
			target = new ConsoleTarget();
		} else if(param.equals("file")){
			target = new FileTarget();
		} else {
			target = new ConsoleTarget();
		}
		
        return target;
    }
}