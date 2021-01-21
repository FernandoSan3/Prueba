package ec.edu.ups.PruebaAD.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import ec.edu.ups.PruebaAD.ON.PersonaON;
import ec.edu.ups.PruebaAD.modelo.Amortizacion;
import ec.edu.ups.PruebaAD.modelo.Cuenta;
import ec.edu.ups.PruebaAD.modelo.Persona;
import ec.edu.ups.PruebaAD.modelo.SolicitudCredito;

@Path("clientes")
public class ClienteServiceREST {
	
	@Inject
	private PersonaON personaON;

	@Inject
	private Connection con;

	private Cuenta cuenta = new Cuenta();
	private Persona persona = new Persona();
	private SolicitudCredito solicitudCredito = new SolicitudCredito();
	private Amortizacion amortizacion = new Amortizacion();
	
	
	
	/**
	 * Tipos de invocacion
	 * GET Consultar
	 * POST Transacciones / Crear Recursos
	 * PUT	Transacciones de tipos actualizacion/ actualizar recursos
	 * DELETE Transacciones de tipo eliminar/ eliminar recursos
	 * @param nombre
	 * @return
	 */
	public String saludar (String nombre) {
		return "hola " + nombre;
	}
	
	@GET
	@Produces("application/json")
	@Path("suma")
	public int suma(@QueryParam("a")int a,@QueryParam("b") int b) {
		return a + b;
	}
	
	@GET
	@Path("multiplicacion/{a}/{b}")
	public int multiplicar(@javax.ws.rs.PathParam("a") int a,@javax.ws.rs.PathParam("b") int b) {
		return a * b;
	}
	
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("crearPersona")
	public Mensaje crearPersona(Persona person) throws Exception {
		Persona persona2 = new Persona();
		persona2 = personaON.buscarpersona(person.getCedula());
		Mensaje men = new Mensaje();
		
		if  (persona2 == null ){
			persona.setPersona_id(person.getPersona_id());
			persona.setCedula(person.getCedula());
			personaON.guardarPersona(person);
			
			men.setCode("OK");
			men.setMessage("Persona Guardada");
			return men;
		}else {
			men.setCode("Error");
			men.setMessage("Person Existente");
			return men;
		}
		
	}
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("crearCuenta")
	public Mensaje crearCuenta(Cuenta cuent) throws Exception {
		Cuenta cuenta2 = new Cuenta();
		cuenta2 = personaON.buscarcuenta(cuent.getNumero_cuenta());
		Mensaje men = new Mensaje();
		
		if  (cuenta2 == null ){
			cuenta.setCuenta_id(cuent.getCuenta_id());
			cuenta.setMeses_plazo(cuent.getMeses_plazo());
			cuenta.setNumero_cuenta(cuent.getNumero_cuenta());
			cuenta.setValor_credito(cuent.getValor_credito());
			Persona p = personaON.buscarpersona(cuent.getPersona().getCedula());
			cuenta.setPersona(p);
		
			personaON.guardarCuenta(cuenta);
			
			men.setCode("OK");
			men.setMessage("Cuenta Guardada");
			return men;
		}else {
			men.setCode("Error");
			men.setMessage("Cuenta Existente");
			return men;
		}
		
	}
	
	@GET
	@Produces("application/json")
	@Path("buscarCuenta")
	public Cuenta buscarCuenta(@QueryParam("nCuenta") String nCuenta) {
		Cuenta cue = null;
		//nCuenta = "3";
		
		try {
			cue = personaON.buscarcuenta(nCuenta);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cue = null;
			cuenta = new Cuenta();
		}
		return cuenta;
	}
	

    public static Date ParseFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
    }
	

}
