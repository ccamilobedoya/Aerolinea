package com.edu.udea.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.edu.udea.dao.PasajeDAO;
import com.edu.udea.dto.Cliente;
import com.edu.udea.dto.Pasaje;
import com.edu.udea.excepcion.Excepcion;

public class PasajeDAOImpl extends HibernateDaoSupport implements PasajeDAO{

	@Override
	public List<Pasaje> consultar() throws Excepcion {
		List<Pasaje> pasajees = new ArrayList<Pasaje>();
		Session session = null;
		
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Pasaje.class);
			pasajees = criteria.list();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion PasajeDAO", e);
		}
		
		return pasajees;
	}

	@Override
	public Pasaje consultar(Integer id_pasaje) throws Excepcion {
		Pasaje pasaje = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Pasaje.class).add
					(Restrictions.eq("id_pasaje", id_pasaje));
			pasaje = (Pasaje) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion PasajeDAO - Consultar", e);
		}
		
		return pasaje;
	}

	@Override
	public List<Pasaje> consultar(Cliente cliente) throws Excepcion {
		List<Pasaje> pasajees = new ArrayList<Pasaje>();
		Session session = null;
		
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Pasaje.class).add
					(Restrictions.eq("cliente", cliente));
			pasajees = criteria.list();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion PasajeDAO", e);
		}
		
		return pasajees;
	}

	@Override
	public Boolean guardar(Pasaje pasaje) throws Excepcion {
		boolean resultado = false;
		Session session = null;
		Transaction tr = null;
		
		try{
			session = getSession();
			tr = (Transaction) session.beginTransaction();
			session.save(pasaje);
			tr.commit();
			resultado = true;
		}
		catch(HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion PasajeDAO - Guardar", e);
		}
		
		return resultado;
	}

}
