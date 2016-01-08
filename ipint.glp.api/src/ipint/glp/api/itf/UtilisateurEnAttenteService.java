package ipint.glp.api.itf;

import javax.ejb.Local;

import ipint.glp.api.DTO.UtilisateurEnAttenteDTO;

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
	public UtilisateurEnAttenteDTO creer(UtilisateurEnAttenteDTO utilisateurEnAttenteDTOATrouver);

	/**
	 * Methode de validation d'un utilisateur en attente de validation par le
	 * modérateur.
	 * 
	 * @param utilisateurEnAttenteDTOAValider
	 */
	public void valider(UtilisateurEnAttenteDTO utilisateurEnAttenteDTOAValider);

	/**
	 * Methode de suppression d'un utilisateur en attente de validation par le
	 * modérateur.
	 * 
	 * @param utilisateurEnAttenteDTOAValider
	 */
	public void supprimer(UtilisateurEnAttenteDTO utilisateurEnAttenteDTOAValider);

}
