package ipint.glp.api.itf;

import javax.ejb.Local;

import ipint.glp.api.DTO.ArticleDTO;
import ipint.glp.api.exception.MetierException;

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
	public ArticleDTO creer(ArticleDTO article) throws MetierException ;
	
	/** Trouver un article
	 * @param article l'article à trouver
	 * @return l'article trouvé
	 */
	public ArticleDTO trouver(ArticleDTO article) throws MetierException ;
	
	/** Modifier un article
	 * @param article l'article à modifier
	 * @return l'article mopdifié
	 */
	/** Modifier un article
	 * @param ancienArticle l'article à modifier
	 * @param nouvelArticle le nouvel article avec les mises à jour
	 * @return l'ancien article mis à jour
	 */
	public ArticleDTO modifier(ArticleDTO nouvelArticle) throws MetierException ;
	
	/** Supprimer
	 * @param article l'article à supprimer
	 */
	public void supprimer(ArticleDTO article) throws MetierException ;
	
}
