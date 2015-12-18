package ipint.glp.domain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class DAO<T> {

	@PersistenceContext
	protected EntityManager em;

	public DAO() {}

	/**
	 * Permet de récupérer un objet
	 * 
	 * @param obj
	 * @return
	 */
	public abstract T find(T obj);

//	/**
//	 * Permet de créer une entrée dans la base de données par rapport à un objet
//	 * 
//	 * @param obj
//	 */
//	public abstract T create(T obj);
//
//	/**
//	 * Permet de mettre à jour les données d'une entrée dans la base
//	 * 
//	 * @param ancienObj
//	 * @param nouvelObj
//	 */
//	public abstract T update(T ancienObj, T nouvelObj);
//
//	/**
//	 * Permet la suppression d'une entrée de la base
//	 * 
//	 * @param obj
//	 */
//	public abstract void delete(T obj);
//
//	/**
//	 * Permet le listing des objets présents en base
//	 * 
//	 * @param obj
//	 */
//	public abstract List<T> lister();

}
