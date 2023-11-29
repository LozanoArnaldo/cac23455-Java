package ar.com.codoacodo.entity;

import java.time.LocalDate;

public class MainOrador {

	public static void main(String[] args) {
		Orador nuevoOrador = new Orador("Arnaldo", "Lozano", "lozanoarnaldo@gmail.com", "Java", LocalDate.now());
		
		System.out.println(nuevoOrador);
		
	}

}
