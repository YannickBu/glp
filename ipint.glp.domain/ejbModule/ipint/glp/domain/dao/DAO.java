package ipint.glp.domain.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAO<T> {

	protected static final String PERSISTENCE_UNIT_NAME = "PU";
	protected EntityManagerFactory emf;
	protected EntityManager em;
	
	public DAO(){
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = emf.createEntityManager();
	}
	
	/**
	 * Permet de récupérer un objet
	 * @param obj
	 * @return
	 */
	public abstract T find(T obj);
	
	/**
	 * Permet de créer une entrée dans la base de données
	 * par rapport à un objet
	 * @param obj
	 */
	public abstract T create(T obj);
	
	/**
	 * Permet de mettre à jour les données d'une entrée dans la base 
	 * @param ancienObj
	 * @param nouvelObj
	 */
	public abstract void update(T ancienObj, T nouvelObj);
	
	/**
	 * Permet la suppression d'une entrée de la base
	 * @param obj
	 */
	public abstract void delete(T obj);

}
