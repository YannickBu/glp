package ipint.glp.api.itf;

import javax.ejb.Local;

import ipint.glp.api.DTO.ArticleDTO;

/**
 * @author diagnem
 *
 */
@Local
public interface ArticleService {

	/** Créer un article
	 * @param article l'article à créer
	 * @return l'article créé
	 */
	public ArticleDTO creer(ArticleDTO article);
	
	/** Trouver un article
	 * @param article l'article à trouver
	 * @return l'article trouvé
	 */
	public ArticleDTO trouver(ArticleDTO article);
	
	/** Modifier un article
	 * @param article l'article à modifier
	 * @return l'article mopdifié
	 */
	/** Modifier un article
	 * @param ancienArticle l'article à modifier
	 * @param nouvelArticle le nouvel article avec les mises à jour
	 * @return l'ancien article mis à jour
	 */
	public ArticleDTO modifier(ArticleDTO ancienArticle, ArticleDTO nouvelArticle);
	
	/** Supprimer
	 * @param article l'article à supprimer
	 */
	public void supprimer(ArticleDTO article);
	
}
