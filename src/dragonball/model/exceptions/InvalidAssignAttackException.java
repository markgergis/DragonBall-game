package dragonball.model.exceptions;

@SuppressWarnings("serial")
public abstract class InvalidAssignAttackException extends DragonBallException{

	public InvalidAssignAttackException() {
		super();
	}

	public InvalidAssignAttackException(String message) {
		super(message);
	}

}
