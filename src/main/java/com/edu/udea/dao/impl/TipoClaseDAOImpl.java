package com.edu.udea.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.edu.udea.dao.TipoClaseDAO;
import com.edu.udea.dto.TipoClase;
import com.edu.udea.excepcion.Excepcion;

public class TipoClaseDAOImpl extends HibernateDaoSupport implements TipoClaseDAO{

	@Override
	public TipoClase consultar(Integer id_tipoclase) throws Excepcion {
		TipoClase tipoclase = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(TipoClase.class).add
					(Restrictions.eq("id_tipoclase", id_tipoclase));
			tipoclase = (TipoClase) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion TipoClaseDAO - Consultar", e);
		}
		finally {
			if (session != null)
				session.close();
		}
		
		return tipoclase;
	}

}
