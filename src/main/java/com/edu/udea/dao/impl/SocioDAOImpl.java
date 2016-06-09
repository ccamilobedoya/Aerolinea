package com.edu.udea.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.edu.udea.dao.SocioDAO;
import com.edu.udea.dto.Cliente;
import com.edu.udea.dto.Socio;
import com.edu.udea.excepcion.Excepcion;

public class SocioDAOImpl extends HibernateDaoSupport implements SocioDAO{

	public List<Socio> consultar() throws Excepcion {
		List<Socio> socios = new ArrayList<Socio>();
		Session session = null;
		
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Socio.class);
			socios = criteria.list();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion SocioDAO", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return socios;
	}

	public Socio consultar(Integer id_socio) throws Excepcion {
		Socio socio = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Socio.class).add
					(Restrictions.eq("id_socio", id_socio));
			socio = (Socio) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion SocioDAO - Consultar", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return socio;
	}

	public Socio consultar(String usuario) throws Excepcion {
		Socio socio = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Socio.class).add
					(Restrictions.eq("usuario", usuario));
			socio = (Socio) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion SocioDAO - Consultar", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return socio;
	}
	
	public Socio consultar(Cliente cliente) throws Excepcion {
		Socio socio = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Socio.class).add
					(Restrictions.eq("cliente", cliente));
			socio = (Socio) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion SocioDAO - Consultar", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return socio;
	}

	public Boolean guardar(Socio socio) throws Excepcion {
		boolean resultado = false;
		Session session = null;
		Transaction tr = null;
		
		try{
			session = getSession();
			tr = (Transaction) session.beginTransaction();
			session.save(socio);
			tr.commit();
			resultado = true;
		}
		catch(HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion SocioDAO - Guardar", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return resultado;
	}

	public Boolean actualizar(Socio socio) throws Excepcion {
		boolean resultado = false;
		Session session = null;
		Transaction tr = null;
		
		try{
			session = getSession();
			tr = (Transaction) session.beginTransaction();
			session.merge(socio);
			tr.commit();
			resultado = true;
		}
		catch(HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion SocioDAO - Actualizar", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return resultado;
	}

}
