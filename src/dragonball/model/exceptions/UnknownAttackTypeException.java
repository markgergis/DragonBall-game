package dragonball.model.exceptions;

@SuppressWarnings("serial")
public class UnknownAttackTypeException extends InvalidFormatException {

	private String unknownType;

	public UnknownAttackTypeException(String sourceFile, int sourceLine,
			String type) {
		super(sourceFile, sourceLine);
		unknownType = type;
	}

	public UnknownAttackTypeException(String message, String sourceFile,
			int sourceLine, String type) {
		super(message, sourceFile, sourceLine);
		unknownType = type;
	}

	public String getUnknownType() {
		return unknownType;
	}

}
