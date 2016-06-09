package com.edu.udea.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.edu.udea.dao.PaisDAO;
import com.edu.udea.dto.Pais;
import com.edu.udea.excepcion.Excepcion;

public class PaisDAOImpl extends HibernateDaoSupport implements PaisDAO{

	public List<Pais> consultar() throws Excepcion{
		List<Pais> paises = new ArrayList<Pais>();
		Session session = null;
		
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Pais.class);
			paises = criteria.list();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion PaisDAO", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return paises;
	}

	public Pais consultar(Integer id_pais) throws Excepcion{
		Pais pais = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Pais.class).add
					(Restrictions.eq("id_pais", id_pais));
			pais = (Pais) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion PaisDAO - Consultar", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return pais;
	}

	public Pais consultar(String nombre) throws Excepcion{
		Pais pais = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Pais.class).add
					(Restrictions.eq("nombre", nombre));
			pais = (Pais) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion PaisDAO - Consultar", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return pais;
	}

	public Boolean guardar(Pais pais) throws Excepcion{
		boolean resultado = false;
		Session session = null;
		Transaction tr = null;
		
		try{
			session = getSession();
			tr = (Transaction) session.beginTransaction();
			session.save(pais);
			tr.commit();
			resultado = true;
		}
		catch(HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion PaisDAO - Guardar", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return resultado;
	}

	public Boolean actualizar(Pais pais) throws Excepcion{
		boolean resultado = false;
		Session session = null;
		Transaction tr = null;
		
		try{
			session = getSession();
			tr = (Transaction) session.beginTransaction();
			session.update(pais);
			tr.commit();
			resultado = true;
		}
		catch(HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion PaisDAO - Actualizar", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return resultado;
	}

	public Boolean borrar(Pais pais) throws Excepcion{
		boolean resultado = false;
		Session session = null;
		Transaction tr = null;
		
		try{
			session = getSession();
			tr = (Transaction) session.beginTransaction();
			session.delete(pais);
			tr.commit();
			resultado = true;
		}
		catch(HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion PaisDAO - Borrar", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return resultado;
	}

}
