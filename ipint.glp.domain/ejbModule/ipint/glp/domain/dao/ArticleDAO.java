package ipint.glp.domain.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ipint.glp.domain.entity.Article;
import ipint.glp.domain.entity.Utilisateur;

public class ArticleDAO extends DAO<Article> {

	private static final String PERSISTENCE_UNIT_NAME = "PU";
	private EntityManagerFactory emf;
	public EntityManager em;

	public ArticleDAO() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = emf.createEntityManager();
	}

	public void update(final Article ancienArt, final Article nouvelArt) {

		Article articleMAJ = find(ancienArt);

		if (ancienArt.getContenu() != null) {

			articleMAJ.setContenu(ancienArt.getContenu());
		}
		if (ancienArt.getDatePublication() != null) {

			articleMAJ.setDatePublication(ancienArt.getDatePublication());
		}
		em.getTransaction().begin();

		em.persist(articleMAJ);

		em.getTransaction().commit();

	}

	@Override
	public Article find(Article idArticle) {
		em.getTransaction().begin();

		Query q = em.createQuery("select e from Utilisateur e where e.idArticle = '" + idArticle + "'");
		Article article = (Article) q.getSingleResult();

		em.getTransaction().commit();

		return article;
	}

	@Override
	public Article create(Article article) {
		em.getTransaction().begin();

		em.persist(article);

		em.getTransaction().commit();

		return article;
	}

	@Override
	public void delete(Article obj) {
		// TODO Auto-generated method stub

	}

}
