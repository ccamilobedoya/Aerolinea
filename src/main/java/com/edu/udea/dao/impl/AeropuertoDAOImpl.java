package com.edu.udea.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.edu.udea.dao.AeropuertoDAO;
import com.edu.udea.dto.Aeropuerto;
import com.edu.udea.excepcion.Excepcion;

public class AeropuertoDAOImpl extends HibernateDaoSupport implements AeropuertoDAO{

	@Override
	public List<Aeropuerto> consultar() throws Excepcion {
		List<Aeropuerto> aeropuertoes = new ArrayList<Aeropuerto>();
		Session session = null;
		
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Aeropuerto.class);
			aeropuertoes = criteria.list();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion AeropuertoDAO", e);
		}
		
		return aeropuertoes;
	}

	@Override
	public Aeropuerto consultar(String IATA) throws Excepcion {
		Aeropuerto aeropuerto = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Aeropuerto.class).add
					(Restrictions.eq("iata", IATA));
			aeropuerto = (Aeropuerto) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion AeropuertoDAO - Consultar", e);
		}
		
		return aeropuerto;
	}

	@Override
	public Aeropuerto consultar(Integer id_aeropuerto) throws Excepcion {
		Aeropuerto aeropuerto = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Aeropuerto.class).add
					(Restrictions.eq("id_aeropuerto", id_aeropuerto));
			aeropuerto = (Aeropuerto) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion AeropuertoDAO - Consultar", e);
		}
		
		return aeropuerto;
	}

}
