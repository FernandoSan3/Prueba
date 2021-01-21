package ec.edu.ups.PruebaAD.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import ec.edu.ups.PruebaAD.modelo.Cuenta;


@Stateless
public class CuentaDAO {
	
	@Inject
	private EntityManager em;

	@Inject
	private Connection con;
	
	@Inject
	private PersonaDAO personaDAO;
	
	
	public boolean insertCuenta(Cuenta cuenta) throws SQLException {
		em.persist(cuenta);
		return true;
	}
	
	public Cuenta readCueta(int cuenta_id) throws SQLException {
		Cuenta cuenta = em.find(Cuenta.class, cuenta_id);
		return cuenta;
	}

	public boolean update(Cuenta cuenta) throws SQLException {
		em.merge(cuenta);
		return true;
	}

	public boolean deleteCuenta(int cuenta_id) throws SQLException {
		em.remove(em.find(Cuenta.class, cuenta_id));
		return true;
	}
	

	public int contarCuenta() throws SQLException {
		int s;
		String sql = "SELECT MAX (cuenta_id) FROM Cuenta";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet res = ps.executeQuery();
		res.next();
		s = res.getInt(1) + 1;
		ps.execute();
		ps.close();
		return s;
	}
	
	public Cuenta buscarCuenta(int numero_cuenta) throws SQLException {
		Cuenta cuenta = new Cuenta();
		String sql = "SELECT * FROM Cuenta WHERE  numero_cuenta=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, numero_cuenta);
		ResultSet res = ps.executeQuery();
		res.next();
		cuenta.setCuenta_id(res.getInt("cuenta_id"));
		cuenta.setNumero_cuenta(res.getInt("numero_cuenta"));
		cuenta.setValor_credito(res.getDouble("valor_credito"));
		cuenta.setMeses_plazo(res.getInt("meses_plazo"));
		cuenta.setPersona(personaDAO.readPersona(res.getString("persona_id")));
		
		ps.execute();
		ps.close();
		return cuenta;
	}
	

}
