package com.edu.udea.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.edu.udea.dao.ItinerarioDAO;
import com.edu.udea.dto.Itinerario;
import com.edu.udea.excepcion.Excepcion;

public class ItinerarioDAOImpl extends HibernateDaoSupport implements ItinerarioDAO{

	@Override
	public List<Itinerario> consultar() throws Excepcion {
		List<Itinerario> itinerarioes = new ArrayList<Itinerario>();
		Session session = null;
		
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Itinerario.class);
			itinerarioes = criteria.list();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion ItinerarioDAO", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return itinerarioes;
	}

	@Override
	public Itinerario consultar(Integer id_itinerario) throws Excepcion {
		Itinerario itinerario = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Itinerario.class).add
					(Restrictions.eq("id_itinerario", id_itinerario));
			itinerario = (Itinerario) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion ItinerarioDAO - Consultar", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return itinerario;
	}

}
