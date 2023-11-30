package ar.com.codoacodo.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.com.codoacodo.entity.Orador;

public class MysqlOradorRepository implements OradorRepository{

	@Override
	public void save(Orador orador) {
		
		String sql = "insert into oradores (nombre,apellido,mail,tema,fecha_creacion) values(?,?,?,?,?) ";
		
		try (Connection con = AdministradorDeConexiones.getConnection()) {
			PreparedStatement statement = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1,orador.getNombre());
			statement.setString(2,orador.getApellido());
			statement.setString(3,orador.getMail());
			statement.setString(4,orador.getTema());
			statement.setDate(5,new java.sql.Date(System.currentTimeMillis()));
			
			statement.executeUpdate();
			
			ResultSet res = statement.getGeneratedKeys();
			
			if(res.next()) {
				Long id = res.getLong(1);
				orador.setId(id);
			}
		}catch(Exception e) {
			throw new IllegalArgumentException("No se pudo crear el orador", e);
			
		}
	}

	@Override
	public Orador getById(Long id) {
		
		String sql = "select id_orador, nombre, apellido, mail, tema, fecha_creacion from oradores where id_orador = ? ";
		Orador orador = null;
		try (Connection con = AdministradorDeConexiones.getConnection()){
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setLong(1,id);
			
			ResultSet res  = statement.executeQuery();
			
			if(res.next()) {
				Long dbId = res.getLong(1);
				String nombre = res.getString(2);
				String apellido = res.getString(3);
				String mail = res.getString(4);
				String tema = res.getString(5);
				Date fechaCreacion = res.getDate(6);
				
				orador = new Orador(dbId, nombre, apellido, mail, tema, LocalDate.from(fechaCreacion.toLocalDate()));
			}
		}catch(Exception e) {
			throw new IllegalArgumentException("No se encontro el orador", e);
			
		}
		return orador;
	}

	@Override
	public void update(Orador orador) {
		String sql = "update oradores " + "set nombre = ?, apellido = ?, mail = ?, tema = ?" + " where id_orador = ? ";
		try (Connection con = AdministradorDeConexiones.getConnection()){
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, orador.getNombre());
			statement.setString(2, orador.getApellido());
			statement.setString(3, orador.getMail());
			statement.setString(4, orador.getTema());
			statement.setLong(5, orador.getId());
			statement.executeUpdate();
		
		}catch(Exception e) {
			throw new IllegalArgumentException("No se pudo actualizar el orador", e);
			
		}
		
	}

	@Override
	public void delete(Long id) {
		String sql = "delete from oradores where id_orador = ? ";
		try (Connection con = AdministradorDeConexiones.getConnection()){
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setLong(1, id);
			statement.executeUpdate();
		
		}catch(Exception e) {
			throw new IllegalArgumentException("No se pudo eliminar el orador", e);
			
		}
	}
		

	@Override
	public List<Orador> findAll() {
		
		
		String sql = "select id_orador, nombre, apellido, mail, tema, fecha_creacion from oradores";
		List<Orador> oradores = new ArrayList<>();
		try (Connection con = AdministradorDeConexiones.getConnection()) {
			PreparedStatement statement = con.prepareStatement(sql);
			
			ResultSet res  = statement.executeQuery();
			
			while (res.next()) {
				Long dbId = res.getLong(1);
				String nombre = res.getString(2);
				String apellido = res.getString(3);
				String mail = res.getString(4);
				String tema = res.getString(5);
				Date fechaCreacion = res.getDate(6);
	
				oradores.add(new Orador(dbId, nombre, apellido, mail, tema, LocalDate.from(fechaCreacion.toLocalDate())));
				
			
			}
		}catch(Exception e) {
			throw new IllegalArgumentException("No hay orador", e);
			
		}
		return oradores;
	}

}
