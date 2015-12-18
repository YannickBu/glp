package ipint.glp.api.itf;

import javax.ejb.Local;

import ipint.glp.api.DTO.ArticleDTO;

@Local
public interface ArticleService {

	public ArticleDTO creer(ArticleDTO article);
	
	public ArticleDTO trouver(ArticleDTO article);
	
	public ArticleDTO modifier(ArticleDTO articleAModifier);
	
	public void supprimer(ArticleDTO article);
	
}
