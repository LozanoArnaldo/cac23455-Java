package ar.com.codoacodo.repository;

import java.time.LocalDate;
import java.util.List;

import ar.com.codoacodo.entity.Orador;

public class MainOradorRepository {
	public static void main(String[] args) {
		OradorRepository repository = new MysqlOradorRepository();
		
		//Orador newOrador = new Orador("Arnaldo", "Lozano", "lozanoarnaldo@gmail.com", "Java", LocalDate.now());
		//repository.save(newOrador);
		//System.out.println(newOrador);
		
		
		//Orador orador = repository.getById(11L);
		//System.out.println(orador);
		
		//List<Orador> listado=repository.findAll();
		//System.out.println(listado);
		
		//repository.delete(11L);
		//List<Orador> listado=repository.findAll();
		//System.out.println(listado);
		
		Orador Arnaldo = repository.getById(12L);
		
		Arnaldo.setApellido("Rasitt");
		Arnaldo.setNombre("Ariel");
		Arnaldo.setTema("C++");
		
		repository.update(Arnaldo);
		
		Orador orador = repository.getById(12L);
		System.out.println(orador);
				
		
		
	}
}
