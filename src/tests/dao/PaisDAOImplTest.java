package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.edu.udea.dao.PaisDAO;
import com.edu.udea.dto.Pais;
import com.edu.udea.excepcion.Excepcion;

import junit.framework.TestCase;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:springcfg.xml")
public class PaisDAOImplTest extends TestCase {

	@Autowired
	PaisDAO paisDao;
	
	@Test
	public void testConsultar() throws Excepcion{
		List<Pais> paises = new ArrayList<Pais>();
		paises = paisDao.consultar();
		 
		assertTrue(!paises.isEmpty());
	}

	@Test
	public void testConsultarInteger() throws Excepcion{
		Pais pais = null;
		pais = paisDao.consultar(1);
		
		assertTrue(!pais.equals(null));
	}

	@Test
	public void testConsultarString() throws Excepcion {
		Pais pais = null;
		pais = paisDao.consultar("Colombia");
		
		assertTrue(!pais.equals(null));
	}

	@Test
	public void testGuardar() throws Excepcion {
		String nombre = String.valueOf(new Random().nextInt());
		Pais pais = new Pais();
		pais.setNombre(nombre);		
		
		assertTrue(paisDao.guardar(pais));
	}
	
	@Test
	public void testActualizar() throws Excepcion {
		String nombre = String.valueOf(new Random().nextInt());
		Pais pais = new Pais();
		pais.setNombre(nombre);
		paisDao.guardar(pais);
		pais = paisDao.consultar(nombre);
		pais.setNombre(nombre + " Actualizar");		
		
		assertTrue(paisDao.actualizar(pais));
	}

	@Test
	public void testBorrar() throws Excepcion {
		String nombre = String.valueOf(new Random().nextInt());
		Pais pais = new Pais();
		pais.setNombre(nombre);
		paisDao.guardar(pais);
		pais = paisDao.consultar(nombre);		
		
		assertTrue(paisDao.borrar(pais));
	}

}
