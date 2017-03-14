package dragonball.model.exceptions;

@SuppressWarnings("serial")
public class NotEnoughSenzuBeansException extends NotEnoughResourcesException {

	public NotEnoughSenzuBeansException() {
		super("YOU DON'T HAVE ENOUGH SENZU BEANS!");
	}
}
