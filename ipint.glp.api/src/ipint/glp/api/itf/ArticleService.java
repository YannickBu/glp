package ipint.glp.api.itf;

import javax.ejb.Remote;

import ipint.glp.api.DTO.ArticleDTO;

@Remote
public interface ArticleService {

	public ArticleDTO creerArticle(ArticleDTO article);
	
	public ArticleDTO trouverArticle(ArticleDTO article);
	
	public ArticleDTO modifierArticle(ArticleDTO articleAModifier);
	
	public void supprimerArticle(ArticleDTO article);
	
}
