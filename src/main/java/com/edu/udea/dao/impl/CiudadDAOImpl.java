package com.edu.udea.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.edu.udea.dao.CiudadDAO;
import com.edu.udea.dto.Ciudad;
import com.edu.udea.excepcion.Excepcion;

public class CiudadDAOImpl extends HibernateDaoSupport implements CiudadDAO{

	@Override
	public List<Ciudad> consultar() throws Excepcion {
		List<Ciudad> ciudades = new ArrayList<Ciudad>();
		Session session = null;
		
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Ciudad.class);
			ciudades = criteria.list();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion CiudadDAO", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return ciudades;
	}

	@Override
	public Ciudad consultar(Integer id_ciudad) throws Excepcion {
		Ciudad ciudad = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Ciudad.class).add
					(Restrictions.eq("id_ciudad", id_ciudad));
			ciudad = (Ciudad) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion CiudadDAO - Consultar", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return ciudad;
	}

}
