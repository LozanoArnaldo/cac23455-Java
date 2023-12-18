package ar.com.codoacodo.controllers;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.repository.MysqlOradorRepository;
import ar.com.codoacodo.repository.OradorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/api/orador")
public class NuevoOradorController extends HttpServlet {
	
	private OradorRepository repository = new MysqlOradorRepository();
	
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
			String json = request.getReader()
					.lines()
					.collect(Collectors.joining(System.lineSeparator()));
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			
			
			OradorRequest oradorRequest = mapper.readValue(json, OradorRequest.class);

			Orador nuevo= new Orador(
					oradorRequest.getNombre(),
					oradorRequest.getApellido(),
					oradorRequest.getMail(),
					oradorRequest.getTema(), 
					LocalDate.now());
			
			
			
			repository.save(nuevo);
			
			String jsonParaEnviarAlFrontend = mapper.writeValueAsString(nuevo);
			
			response.getWriter().print(jsonParaEnviarAlFrontend);
			
	}
	 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		List<Orador> listado = this.repository.findAll();
		
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		
		String jsonParaEnviarAlFrontend = mapper.writeValueAsString(listado);
		response.getWriter().print(jsonParaEnviarAlFrontend);
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		OradorRepository repository = new MysqlOradorRepository();
		
		repository.delete(Long.parseLong(id));
		
		response.setStatus(HttpServletResponse.SC_OK);
		
	}
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		String json = request.getReader()
				.lines()
				.collect(Collectors.joining(System.lineSeparator()));
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		
		OradorRequest oradorRequest = mapper.readValue(json, OradorRequest.class);
		
		Orador orador =this.repository.getById(Long.parseLong(id));
		
		orador.setNombre(oradorRequest.getNombre());
		orador.setApellido(oradorRequest.getApellido());
		orador.setMail(oradorRequest.getMail());
		orador.setTema(oradorRequest.getTema());
	
		
		this.repository.update(orador);
		
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
