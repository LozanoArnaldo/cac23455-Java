package ar.com.codoacodo.controllers;

public class OradorRequest {

	private String nombre;
	private String apellido;
	private String mail;
	private String tema;
	
	public OradorRequest() {
		
	}
	
	public OradorRequest(String nombre, String apellido, String mail, String tema) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.tema = tema;
		
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getMail() {
		return mail;
	}

	public String getTema() {
		return tema;
	}
	
}
