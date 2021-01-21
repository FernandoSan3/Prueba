package ec.edu.ups.PruebaAD.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.edu.ups.PruebaAD.modelo.Persona;


@Stateless
public class PersonaDAO {
	
	@Inject
	private Connection con;

	@Inject
	private EntityManager em;

	/**
	 * Metodo para crear una Persona utilizando JPA
	 * 
	 * @param entity
	 * @return
	 * @throws SQLException
	 */
	public boolean insertPersona(Persona entity) throws SQLException {
		em.persist(entity);
		return true;
	}

	/**
	 * Metodo para leer una Persona mediante la cedula con JPA
	 * 
	 * @param cedula
	 * @return
	 * @throws SQLException
	 */
	public Persona readPersona(String persona_id) throws SQLException {
		Persona persona = em.find(Persona.class, persona_id);
		return persona;
	}
	/**
	 * Metodo para actualizar una persona con JPA
	 * 
	 * @param persona
	 * @return
	 * @throws SQLException
	 */
	public boolean updatePersona(Persona persona) throws SQLException {
		em.merge(persona);
		return true;
	}

	/**
	 * Metodo para eliminar una persona mediante la cedula
	 * 
	 * @param cedula
	 * @return
	 * @throws SQLException
	 */
	public boolean deletePersona(String cedula) throws SQLException {
		em.remove(readPersona(cedula));
		return true;
	}
	
	public int contarPersona() throws SQLException {
		int s;
		String sql = "SELECT MAX (persona_id) FROM Persona";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet res = ps.executeQuery();
		res.next();
		s = res.getInt(1) + 1;
		ps.execute();
		ps.close();
		return s;
	}
	
	public Persona buscarCedula(String cedula) {

		Persona persona = new Persona();
		String sql = "SELECT * FROM Persona WHERE cedula=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cedula);
			ResultSet res = ps.executeQuery();
			res.next();
			persona.setPersona_id(res.getInt("persona_id"));
			persona.setCedula(res.getString("cedula"));
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error al buscar a la persona por la cédula " + e.getMessage());
		}
		return persona;
	}

}
