package com.edu.udea.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.edu.udea.dao.ClienteDAO;
import com.edu.udea.dto.Cliente;
import com.edu.udea.dto.TipoDocumento;
import com.edu.udea.excepcion.Excepcion;

public class ClienteDAOImpl extends HibernateDaoSupport implements ClienteDAO{

	public List<Cliente> consultar() throws Excepcion {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Session session = null;
		
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Cliente.class);
			clientes = criteria.list();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion ClienteDAO", e);
		}
		
		return clientes;		
	}

	public Cliente consultar(Integer id_cliente) throws Excepcion {
		Cliente cliente = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Cliente.class).add
					(Restrictions.eq("id_cliente", id_cliente));
			cliente = (Cliente) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion ClienteDAO - Consultar", e);
		}
		
		return cliente;
	}

	public Cliente consultar(String documento, TipoDocumento tipoDocumento) throws Excepcion {
		Cliente cliente = null;
		Session session = null;
		
		try{
			session = getSession();
			Criteria criteria = session.createCriteria(Cliente.class).add
					(Restrictions.eq("documento", documento)).add
					(Restrictions.eq("tipoDocumento", tipoDocumento));
			cliente = (Cliente) criteria.uniqueResult();
		}
		catch (HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion ClienteDAO - Consultar", e);
		}
		
		return cliente;
	}

	public Boolean guardar(Cliente cliente) throws Excepcion {
		boolean resultado = false;
		Session session = null;
		Transaction tr = null;
		
		try{
			session = getSession();
			tr = (Transaction) session.beginTransaction();
			session.save(cliente);
			tr.commit();
			resultado = true;
		}
		catch(HibernateException e){
			throw new Excepcion("Problema con hibernate - Seccion ClienteDAO - Guardar", e);
		}
		
		return resultado;
	}

}
