package fr.esiea.factory;

public abstract class TargetFactory
{
  protected abstract Target createTarget();
  
  public Target getTarget()
  {
	return createTarget();
  }
}