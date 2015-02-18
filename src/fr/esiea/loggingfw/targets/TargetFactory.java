package fr.esiea.loggingfw.targets;

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