package ipint.glp.api.itf;

import javax.ejb.Local;

import ipint.glp.api.DTO.UtilisateurEnAttenteDTO;

/**
 * @author declerck
 *
 */
@Local
public interface UtilisateurEnAttenteService {

	/**
	 * @param utilisateurEnAttenteDTOATrouver
	 */
	public UtilisateurEnAttenteDTO créer(UtilisateurEnAttenteDTO utilisateurEnAttenteDTOATrouver);

	/**
	 * 
	 */
	public void valider();

	/**
	 * 
	 */
	public void refuser();

}
