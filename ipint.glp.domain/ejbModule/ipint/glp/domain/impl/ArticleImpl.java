package ipint.glp.domain.impl;

import ipint.glp.api.itf.Service;
import ipint.glp.domain.dao.DAOFactory;
import ipint.glp.domain.entity.Article;

public class ArticleImpl implements Service<Article> {

	public ArticleImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Article creer(Article obj) {
		return DAOFactory.getArticleDAO().create(obj);
	}

	@Override
	public Article trouver(Article obj) {
		return DAOFactory.getArticleDAO().find(obj);
	}

	@Override
	public Article modifier(Article ancienObj, Article nouvelObj) {
		return DAOFactory.getArticleDAO().update(ancienObj, nouvelObj);
	}

	@Override
	public void supprimer(Article obj) {
		DAOFactory.getArticleDAO().delete(obj);
	}

}
