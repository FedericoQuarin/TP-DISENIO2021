package main.java.excepciones;

public class InputVacioException extends Exception{
	
	public String inputsVacios;
	
	public InputVacioException(String inputs) {
		super();
		this.inputsVacios = inputs;		
	}

}
