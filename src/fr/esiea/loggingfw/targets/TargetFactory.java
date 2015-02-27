package fr.esiea.loggingfw.targets;

import fr.esiea.loggingfw.targets.jdbc.JdbcTarget;

/**
 * Classe pour optimiser et sécuriser la création de cibles
 *
 */
public final class TargetFactory {

	/**
	 * @param param Paramètre servant à désigner le type de cible souhaitée pour le logger (console, jdbc, file par exemple)
	 * @return La cible correspondant au choix de l'utilisateur
	 */
	public static AbstractTarget getTarget(String param)
    {
        AbstractTarget target;

		if(param.equals("console")){
			target = new ConsoleTarget();
		} else if(param.equals("file")){
			target = new FileTarget();
		} else if(param.equals("jdbc")){
			target = new JdbcTarget();
		} else {
			target = new ConsoleTarget();
		}
		
        return target;
    }
}