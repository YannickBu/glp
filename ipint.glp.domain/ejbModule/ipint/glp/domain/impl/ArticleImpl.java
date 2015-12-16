package ipint.glp.domain.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint.glp.api.DTO.ArticleDTO;
import ipint.glp.api.itf.ArticleService;
import ipint.glp.domain.entity.Article;
import ipint.glp.domain.entity.util.MappingToDTO;
import ipint.glp.domain.entity.util.MappingToEntity;

@Stateless
public class ArticleImpl implements ArticleService {

	@PersistenceContext(unitName = "PU")
	private EntityManager em;

	@Override
	public ArticleDTO creerArticle(ArticleDTO articleDTO) {
		// MappingToEntity mte = new MappingToEntity();
		// Article article = mte.articleDTOToArticle(articleDTO);
		Article art = new Article();
		art.setContenu(articleDTO.getContenu());
		art.setDatePublication(articleDTO.getDatePublication());
		art.setIdArticle(articleDTO.getIdArticle());
		// TODO en cours
		em.persist(art);

		return articleDTO;
	}

	@Override
	public ArticleDTO trouverArticle(ArticleDTO articleDTO) {
		Article art = em.find(Article.class, articleDTO.getIdArticle());
		articleDTO = MappingToDTO.articleToArticleDTO(art);
		return articleDTO;
	}

	@Override
	public ArticleDTO modifierArticle(ArticleDTO ancienArt, ArticleDTO nouvelArt) {
		MappingToEntity mte = new MappingToEntity();
		Article articleMAJ = mte.articleDTOToArticle(ancienArt);

		if (nouvelArt.getContenu() != null) {

			articleMAJ.setContenu(nouvelArt.getContenu());
		}
		if (nouvelArt.getDatePublication() != null) {

			articleMAJ.setDatePublication(nouvelArt.getDatePublication());
		}

		em.persist(articleMAJ);
		MappingToDTO mtd = new MappingToDTO();
		nouvelArt = mtd.articleToArticleDTO(articleMAJ);

		return nouvelArt;
	}

	@Override
	public void supprimerArticle(ArticleDTO articleASupprimer) {
		MappingToEntity mte = new MappingToEntity();
		Article article = mte.articleDTOToArticle(articleASupprimer);

		if (articleASupprimer.getIdArticle() != null) {
			em.remove(article);
		}
		// TODO gérer les exceptions pour le cas où le nom et l'id sont tous les
		// deux à null

	}

}
