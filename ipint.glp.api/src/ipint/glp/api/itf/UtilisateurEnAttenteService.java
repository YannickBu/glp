package ipint.glp.api.itf;

import java.util.List;

import javax.ejb.Local;

import ipint.glp.api.DTO.UtilisateurEnAttenteDTO;
import ipint.glp.api.exception.MetierException;

/**
 * Interface definissant les différent service liés à la gestion d'un
 * utilisateur en attente de validation par un modérateur.
 * 
 * @author declerck
 *
 */
@Local
public interface UtilisateurEnAttenteService {

	/**
	 * Methode de creation d'un utilisateur en attente de validation par le
	 * modérateur.
	 * 
	 * @param utilisateurEnAttenteDTOATrouver 
	 */
	public UtilisateurEnAttenteDTO creer(UtilisateurEnAttenteDTO utilisateurEnAttenteDTOATrouver) throws MetierException;

	/**
	 * Methode de validation d'un utilisateur en attente de validation par le
	 * modérateur.
	 * 
	 * @param utilisateurEnAttenteDTOAValider
	 */
	public void valider(UtilisateurEnAttenteDTO utilisateurEnAttenteDTOAValider,String optionalMessage) throws MetierException;

	/**
	 * Methode de refus d'un utilisateur en attente de validation par le
	 * modérateur.
	 * 
	 * @param utilisateurEnAttenteDTOAValider
	 */
	public void refuser(UtilisateurEnAttenteDTO utilisateurEnAttenteDTOAValider,String optionalMessage) throws MetierException;
	
	/**
	 * Methode de suppression d'un utilisateur en attente de validation par le
	 * modérateur.
	 * 
	 * @param utilisateurEnAttenteDTOAValider
	 */
	public void supprimer(UtilisateurEnAttenteDTO utilisateurEnAttenteDTOAValider) throws MetierException;

	/**
	 * Methode de listing des utilisateurs en attente de validation par le
	 * modérateur.
	 * 
	 * @return la liste des utilisateur en attente.
	 */
	public List<UtilisateurEnAttenteDTO> lister() throws MetierException;

	/**
	 * Methode de récupération d'un utilisateur en attente de validation par le
	 * modérateur.
	 * 
	 * @param idUtilisateurEnAttente
	 * @return
	 */
	public UtilisateurEnAttenteDTO trouver(int idUtilisateurEnAttente) throws MetierException;

}
