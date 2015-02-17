package fr.esiea.factory;

public class TargetFactoryConsole extends TargetFactory
{
  protected Target createTarget()
  {
    return new WriteLoggerConsole();
  }
}
