package com.edu.udea.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.edu.udea.dao.SillaDAO;
import com.edu.udea.dto.Pasaje;
import com.edu.udea.dto.Silla;
import com.edu.udea.dto.Vuelo;
import com.edu.udea.excepcion.Excepcion;

public class SillaDAOImpl extends HibernateDaoSupport implements SillaDAO{

	@Override
	public List<Silla> consultar(Vuelo vuelo) throws Excepcion {
		List<Silla> sillaes = new ArrayList<Silla>();
		Session session = null;
		
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Silla.class).add
					(Restrictions.eq("vuelo", vuelo));
			sillaes = criteria.list();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion SillaDAO", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return sillaes;
	}

	@Override
	public Silla consultar(Pasaje pasaje) throws Excepcion {
		Silla silla = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Silla.class).add
					(Restrictions.eq("pasaje", pasaje));
			silla = (Silla) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion SillaDAO - Consultar", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return silla;
	}

	@Override
	public Silla consultar(Integer id_silla) throws Excepcion {
		Silla silla = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Silla.class).add
					(Restrictions.eq("id_silla", id_silla));
			silla = (Silla) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion SillaDAO - Consultar", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return silla;
	}

	@Override
	public Boolean actualizar(Silla silla) throws Excepcion {
		boolean resultado = false;
		Session session = null;
		Transaction tr = null;
		
		try{
			session = getSession();
			tr = (Transaction) session.beginTransaction();
			session.merge(silla);
			tr.commit();
			resultado = true;
		}
		catch(HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion SillaDAO - Actualizar", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return resultado;
	}

}
