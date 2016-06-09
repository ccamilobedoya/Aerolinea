package com.edu.udea.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.edu.udea.dao.VueloDAO;
import com.edu.udea.dto.Aeropuerto;
import com.edu.udea.dto.Itinerario;
import com.edu.udea.dto.Vuelo;
import com.edu.udea.excepcion.Excepcion;

public class VueloDAOImpl extends HibernateDaoSupport implements VueloDAO{

	@Override
	public List<Vuelo> consultar(Itinerario itinerario) throws Excepcion {
		List<Vuelo> vueloes = new ArrayList<Vuelo>();
		Session session = null;
		
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Vuelo.class).add
					(Restrictions.eq("itinerario", itinerario));
			vueloes = criteria.list();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion VueloDAO", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return vueloes;
	}

	@Override
	public List<Vuelo> consultar(Aeropuerto desde, Aeropuerto hasta, Date salida) throws Excepcion {
		List<Vuelo> vueloes = new ArrayList<Vuelo>();
		Session session = null;
				
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Vuelo.class)
					.add(Restrictions.eq("desde", desde))
					.add(Restrictions.eq("hasta", hasta))
					.add(Restrictions.ge("salida", salida));
			vueloes = criteria.list();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion VueloDAO", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return vueloes;
	}

	@Override
	public Vuelo consultar(Integer id_vuelo) throws Excepcion {
		Vuelo vuelo = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Vuelo.class).add
					(Restrictions.eq("id_vuelo", id_vuelo));
			vuelo = (Vuelo) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion VueloDAO - Consultar", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return vuelo;
	}

}
