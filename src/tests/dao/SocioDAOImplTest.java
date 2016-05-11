package dao;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.edu.udea.dao.ClienteDAO;
import com.edu.udea.dao.SocioDAO;
import com.edu.udea.dto.Cliente;
import com.edu.udea.dto.Socio;
import com.edu.udea.excepcion.Excepcion;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springcfg.xml")
public class SocioDAOImplTest {

	@Autowired
	SocioDAO socioDao;
	@Autowired
	ClienteDAO clienteDao;
	
/*
	@Test
	public void testConsultar() {
		fail("Not yet implemented");
	}

	@Test
	public void testConsultarInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testConsultarString() {
		fail("Not yet implemented");
	}
*/
	@Test
	public void testConsultarCliente() throws Excepcion{
		Socio socio = null;
		Cliente cliente = null;
		
		cliente = clienteDao.consultar(3);
		System.out.println("Cliente consultado: " + cliente.getNombre());
		
		socio = socioDao.consultar(cliente);
		System.out.println("Socio consultado: " + socio.getUsuario());
		
		assertTrue(!socio.equals(null));
	}
/*
	@Test
	public void testGuardar() {
		fail("Not yet implemented");
	}

	@Test
	public void testActualizar() {
		fail("Not yet implemented");
	}
*/
}
