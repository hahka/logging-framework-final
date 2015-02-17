package fr.esiea.factory;

public class TargetFactoryDataBase extends TargetFactory
{
  protected Target createTarget()
  {
    return new WriteLoggerDataBase();
  }
}
