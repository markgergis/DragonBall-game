package dragonball.model.exceptions;

@SuppressWarnings("serial")
public abstract class NotEnoughResourcesException extends DragonBallException{

	public NotEnoughResourcesException(){
		super();
	}
	public NotEnoughResourcesException(String message){
		 super(message);
	 }
}
