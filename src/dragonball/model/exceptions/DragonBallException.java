package dragonball.model.exceptions;

@SuppressWarnings("serial")
public abstract class DragonBallException extends Exception {

	public DragonBallException() {
		super();
	}

	public DragonBallException(String message) {
		super(message);
	}

}
