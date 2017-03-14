package dragonball.model.exceptions;

import java.io.IOException;

@SuppressWarnings("serial")
public abstract class InvalidFormatException extends IOException{
	private String sourceFile;
	private int sourceLine = 1;
	
	
	public InvalidFormatException(String sourceFile, int sourceLine){
		super(); 
		this.sourceFile = sourceFile;
		this.sourceLine = sourceLine;
	 }
	
	 public InvalidFormatException(String message, String sourceFile, int sourceLine){
		 super(message);
		 this.sourceFile = sourceFile;
		 this.sourceLine = sourceLine;
	 }

	public String getSourceFile() {
		return sourceFile;
	}

	public int getSourceLine() {
		return sourceLine;
	}

}
