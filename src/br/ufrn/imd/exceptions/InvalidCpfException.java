package br.ufrn.imd.exceptions;

public class InvalidCpfException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	  public String getMessage(){
	    return "Formato do CPF invalido";
	}
}
