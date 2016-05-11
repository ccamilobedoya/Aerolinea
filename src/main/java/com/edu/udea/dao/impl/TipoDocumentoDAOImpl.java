package com.edu.udea.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.edu.udea.dao.TipoDocumentoDAO;
import com.edu.udea.dto.TipoDocumento;
import com.edu.udea.excepcion.Excepcion;

public class TipoDocumentoDAOImpl extends HibernateDaoSupport implements TipoDocumentoDAO{

	public List<TipoDocumento> consultar() throws Excepcion {
		List<TipoDocumento> tipodocumentos = new ArrayList<TipoDocumento>();
		Session session = null;
		
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(TipoDocumento.class);
			tipodocumentos = criteria.list();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion TipoDocumentoDAO", e);
		}
		
		return tipodocumentos;
	}

	public TipoDocumento consultar(Integer id_tipodocumento) throws Excepcion {
		TipoDocumento tipodocumento = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(TipoDocumento.class).add
					(Restrictions.eq("id_tipodocumento", id_tipodocumento));
			tipodocumento = (TipoDocumento) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion TipoDocumentoDAO - Consultar", e);
		}
		
		return tipodocumento;
	}

	public TipoDocumento consultar(String nombre) throws Excepcion {
		TipoDocumento tipodocumento = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(TipoDocumento.class).add
					(Restrictions.eq("nombre", nombre));
			tipodocumento = (TipoDocumento) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion TipoDocumentoDAO - Consultar", e);
		}
		
		return tipodocumento;
	}

}
