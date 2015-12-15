package ipint.glp.domain.impl;

import javax.ejb.Stateless;

import ipint.glp.api.DTO.ArticleDTO;
import ipint.glp.api.itf.ArticleService;
import ipint.glp.domain.dao.DAOFactory;
import ipint.glp.domain.entity.Article;
import ipint.glp.domain.entity.util.MappingToDTO;
import ipint.glp.domain.entity.util.MappingToEntity;

@Stateless
public class ArticleImpl implements ArticleService	 {

	
	@Override
	public ArticleDTO creerArticle(ArticleDTO obj) {
		Article art = MappingToEntity.articleDTOToArticle(obj);
		return MappingToDTO.articleToArticleDTO(DAOFactory.getArticleDAO().create(art));
	}

	@Override
	public ArticleDTO trouverArticle(ArticleDTO obj) {
		Article art = MappingToEntity.articleDTOToArticle(obj);
		return MappingToDTO.articleToArticleDTO(DAOFactory.getArticleDAO().find(art));
	}

	@Override
	public ArticleDTO modifierArticle(ArticleDTO ancienObj, ArticleDTO nouvelObj) {
		Article nouvelArt = MappingToEntity.articleDTOToArticle(ancienObj);
		Article ancienArt = MappingToEntity.articleDTOToArticle(nouvelObj);
		return MappingToDTO.articleToArticleDTO(DAOFactory.getArticleDAO().update(ancienArt, nouvelArt));
	}

	@Override
	public void supprimerArticle(ArticleDTO obj) {
		Article art = MappingToEntity.articleDTOToArticle(obj);
		DAOFactory.getArticleDAO().delete(art);
	}

}
