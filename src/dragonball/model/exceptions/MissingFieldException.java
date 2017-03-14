package dragonball.model.exceptions;

@SuppressWarnings("serial")
public class MissingFieldException extends InvalidFormatException{

	private int missingFields;
	public MissingFieldException(String sourceFile, int sourceLine, int missingFields) {
		super(sourceFile, sourceLine);
		this.missingFields = missingFields;
	}
	
	public MissingFieldException(String message, String sourceFile, int sourceLine, int missingFields){
		super(message, sourceFile, sourceLine);
		this.missingFields = missingFields;
	 }

	public int getMissingFields() {
		return missingFields;
	}
	

}
