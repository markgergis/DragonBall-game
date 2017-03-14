package dragonball.model.exceptions;

@SuppressWarnings("serial")
public class NotEnoughAbilityPointsException extends NotEnoughResourcesException{

	
	public NotEnoughAbilityPointsException(){
		super("YOU DON'T HAVE ENOUGH ABILITY POINTS");
	}
}
