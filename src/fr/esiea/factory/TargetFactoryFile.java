package fr.esiea.factory;

public class TargetFactoryFile extends TargetFactory
{
  protected Target createTarget()
  {
    return new WriteLoggerFile();
  }
}
