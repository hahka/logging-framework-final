# Logging Framework
Framework de logging en Java pour le projet d'architecture logicielle, de M. Nicolas ANDRE.

## JavaDoc

Lien vers la JavaDoc de notre projet : [ici](http://logging-framework.herokuapp.com/ "JavaDoc")

## Instructions d'installation

- Téléchargez le fichier logging-framework.jar
- Ajoutez le à votre projet java (dans un dossier 'lib' par exemple)
- Ajoutez le ensuite au BuildPath de votre projet


## Instructions d'utilisation

Importez 'fr.esiea.loggingfw.OurLogger' dans votre classe
<pre>import fr.esiea.loggingfw.OurLogger;</pre>
Créez enfin un objet de type OurLogger
<pre>OurLogger logger = new OurLogger(Main.class);</pre>
Si vous le laissez tel quel, vous aurez un logger basique, qui affiche des informations dans la console, avec un niveau de priorité de base établit à ERREUR

Vous pouvez maintenant définir:
- Son niveau de priorité
<pre>logger.setLevel(LoggerLevel.DEBUG);
logger.setLevel("INFO");</pre>

- Sa ou ses cibles
<pre>logger.setTarget(TargetFactory.getTarget("console"));
logger.removeTarget(new ConsoleTarget());
logger.addTarget("jdbc");
logger.removeTargets();</pre>
  Possibilité de créer de nouvelles cibles en faisant hériter ses classes soit de AbstractTarget soit de AbstractJdbcTarget.

- Son formatter
<pre>MyOwnFormatter formatter = new MyOwnFormatter();
logger.setFormatter(formatter);</pre>
  Possibilité de créer ses propres formatter en héritant de la classe LoggerFormatter

Parmis les cibles pré-définies, 2 classes abstraites peuvent être héritées pour créer ses propres cibles:
- AbstractTarget : classe abstraite de base pour les cibles.
<table>
<thead><th>Attribut / fonction</th><th>Utilité</th></thead>
<tr><td>protected String <b>targetType</b></td><td>Sert à comparer des types de cibles basique par la fonctoin .equals(Object)</td></tr>
<tr><td>public abstract void <b>log</b>()</td><td>Fonction à @Override dans les classes filles, qui va effectuer l'action de "logging"</td></tr>
</table>

- AbstractJdbcTarger : classe abstraite pour les cibles de type base de donnée
<table><thead><th>Attribut / fonction</th><th>Utilité</th></thead>
<tr><td>protected String <b>JDBC_DRIVER</b></td><td>Driver JDBC utile pour une connexion à une base de données (différent suivant Postgresql, Mysql, etc...) </td></tr>
<tr><td>protected String <b>DB_URL</b></td><td>L'url de la base de donnée</td></tr>
<tr><td>protected String <b>USER</b></td><td>L'identifiant de l'utilisateur souhaitant accéder à la base de données</td></tr>
<tr><td>protected String <b>PASS</b></td><td>Le mot de passe de cet utilisateur</td></tr>
<tr><td>public abstract ArrayList<Log> <b>getLogs</b>();</td><td>Fonction pour récupérer les différents logs qui ont été insérés en base de données</td></tr>
</table>

Les 3 autres cibles pré-définies de base de données (JdbcTarget, MysqlTarget et PostgresqlTarget) prennent 4 paramètres en attributs de leur constructeur, qui sont les 4 protected String définies dans AbstractTarget.
JdbcTarget possède aussi un constructeur sans paramètre. Les 4 String vont alors être récupérées dans le fichier config.properties.
  - JdbcTarget : Permet l'insertion de logs sur une base de données contenant déjà la table log
  - PostgresqlTarget : Permet la création et la suppression de la table de logs et de la sequence associée pour l'id des logs, ainsi que l'insertion de logs en base pour les bases de type Postgresql
  - MysqlTarget : Permet la création de la table de logs ed du trigger associé pour la date automatique d'insertion, ainsi que l'insertion de logs en base pour les bases de type MySql

Enfin, les cibles ConsoleTarget et FileTarget permettent de logger respectivement en console et dans un fichier (de type rotatif ou non).
  
