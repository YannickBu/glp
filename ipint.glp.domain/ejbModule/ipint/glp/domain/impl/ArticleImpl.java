package ipint.glp.domain.impl;

import ipint.glp.api.DTO.ArticleDTO;
import ipint.glp.api.itf.Service;
import ipint.glp.domain.dao.DAOFactory;
import ipint.glp.domain.entity.Article;
import ipint.glp.domain.entity.util.MappingToDTO;
import ipint.glp.domain.entity.util.MappingToEntity;

public class ArticleImpl implements Service<ArticleDTO> {

	@Override
	public ArticleDTO creer(ArticleDTO obj) {
		Article art = MappingToEntity.articleDTOToArticle(obj);
		return MappingToDTO.articleToArticleDTO(DAOFactory.getArticleDAO().create(art));
	}

	@Override
	public ArticleDTO trouver(ArticleDTO obj) {
		Article art = MappingToEntity.articleDTOToArticle(obj);
		return MappingToDTO.articleToArticleDTO(DAOFactory.getArticleDAO().find(art));
	}

	@Override
	public ArticleDTO modifier(ArticleDTO ancienObj, ArticleDTO nouvelObj) {
		Article nouvelArt = MappingToEntity.articleDTOToArticle(ancienObj);
		Article ancienArt = MappingToEntity.articleDTOToArticle(nouvelObj);
		return MappingToDTO.articleToArticleDTO(DAOFactory.getArticleDAO().update(ancienArt, nouvelArt));
	}

	@Override
	public void supprimer(ArticleDTO obj) {
		Article art = MappingToEntity.articleDTOToArticle(obj);
		DAOFactory.getArticleDAO().delete(art);
	}

}
