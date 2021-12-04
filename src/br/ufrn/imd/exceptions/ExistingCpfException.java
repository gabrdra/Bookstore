package br.ufrn.imd.exceptions;

public class ExistingCpfException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	  public String getMessage(){
	    return "CPF digitado ja existe no sistema";
	}
}
