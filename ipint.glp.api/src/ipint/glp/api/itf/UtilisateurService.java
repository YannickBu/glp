package ipint.glp.api.itf;

import javax.ejb.Local;

import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.exception.MetierException;

/**
 * @author declerck
 *
 */
@Local
public interface UtilisateurService {

	/**
	 * Créer un utilisateur
	 * 
	 * @param utilisateur
	 *            l'utilisateur à créer
	 * @return l'utilisateur créé
	 */
	public UtilisateurDTO creer(UtilisateurDTO utilisateur) throws MetierException;

	/**
	 * Touver un utilisateur
	 * 
	 * @param utilisateur
	 *            l'utilisateur à trouver
	 * @return l'utilisateur trouvé
	 */
	public UtilisateurDTO trouver(UtilisateurDTO utilisateur) throws MetierException;

	/**
	 * Modifier un utilisateur
	 * 
	 * @param ancienUtilisateur
	 *            l'utilisateur à modifier
	 * @param nouvelUtilisateur
	 *            le nouvel utilisateur avec les mises à jour
	 * @return l'ancien utilisateur mis à jour
	 */
	public UtilisateurDTO modifier(UtilisateurDTO ancienUtilisateur,UtilisateurDTO nouvelUtilisateur) throws MetierException;

	/**
	 * Supprimer un utilisateur
	 * 
	 * @param utilisateur
	 *            l'utilisateur à supprimer
	 */
	public void supprimer(UtilisateurDTO utilisateur) throws MetierException;

	// public List<UtilisateurDTO> listerUtilisateur();

}
