package ar.com.codoacodo.entity;

import java.time.LocalDate;

public class Orador {
	//atributos
	private Long id;
	private String nombre;
	private String apellido;
	private String mail;
	private String tema;
	private LocalDate fechaAlta;
	
	//constructores
	//enviar objeto a db
	
	public Orador(String nombre, String apellido, String mail, String tema, LocalDate fechaAlta) {
		
		init(nombre, apellido, mail, tema, fechaAlta);
	
	}

	
	//Recibir de db
	public Orador(Long id, String nombre, String apellido, String mail, String tema, LocalDate fechaAlta) {
		
		this.id = id;
		init(nombre, apellido, mail, tema, fechaAlta);
	}
	
	private void init(String nombre, String apellido, String mail, String tema, LocalDate fechaAlta) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.tema = tema;
		this.fechaAlta = fechaAlta;
	}
	//metodo toString
	
	@Override
	public String toString() {
		return "Orador [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail + ", tema="
				+ tema + ", fechaAlta=" + fechaAlta + "]";
	}


	public Long getId() {
		return id;
	}

	/*
	public void setId(Long id) {
		this.id = id;
	}
	*/

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		if(nombre != null) {
		this.nombre = nombre;
		}else {
			this.nombre = "N/D";
		}
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		if(apellido != null) {
			this.apellido = apellido;
			}else {
				this.apellido = "N/D";
			}
		
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		if(mail != null) {
			this.mail = mail;
			}else {
				this.mail = "N/D";
			}
	}


	public String getTema() {
		return tema;
	}


	public void setTema(String tema) {
		if(tema != null) {
			this.tema =tema;
			}else {
				this.tema = "N/D";
			}
	}


	public LocalDate getFechaAlta() {
		return fechaAlta;
	}


	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	
	
	
	
}
