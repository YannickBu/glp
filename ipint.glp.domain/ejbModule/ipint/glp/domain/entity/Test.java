package ipint.glp.domain.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ipint.glp.domain.dao.UtilisateurDAO;
import ipint.glp.domain.entity.enumType.Statut;

public class Test {

	private static final String PERSISTENCE_UNIT_NAME = "PU";
	private EntityManagerFactory emf;
	public EntityManager em;

	public Test() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = emf.createEntityManager();
	}

	public static void main(String[] args) {

		UtilisateurDAO utilisateurDao = new UtilisateurDAO();
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom("declerck");
		utilisateur.setPrenom("rodolphe");
		utilisateur.setEmail("rodolphe.declerck@gmail.com");

		System.out.println("utilisateur : " + utilisateurDao.creer(utilisateur));
		utilisateurDao.recuperer("rodolphe.declerck@gmail.com");
		System.out.println("utilisateur : " + utilisateur);
		Utilisateur tmp = new Utilisateur();
		tmp.setStatut(Statut.DIPLOME);

		utilisateurDao.modifier(tmp, "rodolphe.declerck@gmail.com");
		System.out.println("utilisateur : " + utilisateur.getStatut());

		/*
		 * Test t = new Test();
		 * 
		 * t.em.getTransaction().begin();
		 * 
		 * Utilisateur u = new Utilisateur(); u.setNom("n"); u.setPrenom("p");
		 * u.setEmail("ya.bu@gmail.com");
		 * 
		 * t.em.persist(u);
		 * 
		 * t.em.getTransaction().commit();
		 * 
		 * 
		 * 
		 * Query q = t.em.createQuery("select e from Utilisateur e");
		 * List<Utilisateur> listU = q.getResultList();
		 * System.out.println(listU.size());
		 * 
		 * t.em.getTransaction().begin();
		 * 
		 * Utilisateur u1 = t.em.find(Utilisateur.class, new Integer(1));
		 * System.out.println(u1.getIdUtilisateur());
		 * System.out.println(u1.getNom());
		 * 
		 * Utilisateur u2 = new Utilisateur(); u2.setNom("nn");
		 * u2.setPrenom("pp"); u2.setEmail("e.e@e.com");
		 * 
		 * t.em.persist(u2);
		 * 
		 * t.em.getTransaction().commit();
		 * 
		 * q = t.em.createQuery("select e from Utilisateur e"); listU =
		 * q.getResultList(); System.out.println(listU.size());
		 */

		/*
		 * Utilisateur u3 = t.em.find(Utilisateur.class, new Integer(1));
		 * System.out.println(u3.getIdUtilisateur());
		 * System.out.println(u3.getNom());
		 */
	}

}
