package com.edu.udea.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.edu.udea.dao.AvionDAO;
import com.edu.udea.dto.Avion;
import com.edu.udea.excepcion.Excepcion;

public class AvionDAOImpl extends HibernateDaoSupport implements AvionDAO{

	@Override
	public List<Avion> consultar() throws Excepcion {
		List<Avion> aviones = new ArrayList<Avion>();
		Session session = null;
		
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Avion.class);
			aviones = criteria.list();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion AvionDAO", e);
		}
		
		return aviones;
	}

	@Override
	public Avion consultar(Integer id_avion) throws Excepcion {
		Avion avion = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Avion.class).add
					(Restrictions.eq("id_avion", id_avion));
			avion = (Avion) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion AvionDAO - Consultar", e);
		}
		
		return avion;
	}

}
