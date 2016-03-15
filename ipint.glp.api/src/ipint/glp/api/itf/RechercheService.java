package ipint.glp.api.itf;

import java.util.List;

import javax.ejb.Local;

import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.exception.MetierException;

/**
 * @author barrois
 *
 */
@Local
public interface RechercheService {

	/**
	 * recherche de groupes par rapport un texte de recherche
	 * @param search
	 * @return liste de groupes correspondant a la recherche
	 */
	public List<GroupeDTO> rechercheGroupes(String search) throws MetierException;

	/**
	 * recherche d'utilisateurs par rapport un texte de recherche
	 * @param search
	 * @return liste d'utilisateurs correspondant a la recherche
	 * @throws MetierException 
	 */
	public List<UtilisateurDTO> rechercheUtilisateurs(String search) throws MetierException;

}
