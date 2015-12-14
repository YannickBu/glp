package ipint.glp.domain.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ipint.glp.domain.entity.Article;
import ipint.glp.domain.entity.Utilisateur;

public class ArticleDAO extends DAO {

	private static final String PERSISTENCE_UNIT_NAME = "PU";
	private EntityManagerFactory emf;
	public EntityManager em;

	public ArticleDAO() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = emf.createEntityManager();
	}

	@Override
	public Object find(Object idArticle) {
		em.getTransaction().begin();

		Query q = em.createQuery("select e from Utilisateur e where e.idArticle = '" + idArticle + "'");
		Article article = (Article) q.getSingleResult();

		em.getTransaction().commit();

		return article;
	}

	@Override
	public Object create(Object article) {
		em.getTransaction().begin();

		em.persist(article);

		em.getTransaction().commit();

		return article;
	}

	@Override
	public void update(Object ancienArt, Object nouvelArt) {
		

		if (ancienArt.() != null) {

			utilisateurMAJ.setStatut(ancienUtilisateur.getStatut());
		}
		if (ancienUtilisateur.getProfil() != null) {

			utilisateurMAJ.setProfil(ancienUtilisateur.getProfil());
		}
		em.getTransaction().begin();

		em.persist(utilisateurMAJ);

		em.getTransaction().commit();

	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub

	}

}
