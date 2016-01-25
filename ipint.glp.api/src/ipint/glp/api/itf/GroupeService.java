package ipint.glp.api.itf;

import java.util.List;

import javax.ejb.Local;

import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.exception.MetierException;

@Local
public interface GroupeService {

	/**
	 * Créer un groupe
	 * 
	 * @param groupe
	 *            le groupe à créer
	 * @return le groupe créé
	 */
	public GroupeDTO creer(GroupeDTO groupe) throws MetierException;

	/**
	 * Trouver un groupe
	 * 
	 * @param groupe
	 *            le groupe à trouver
	 * @return le groupe trouvé
	 */
	public GroupeDTO trouver(GroupeDTO groupe) throws MetierException;

	/**
	 * Modifier un groupe
	 * 
	 * @param ancienGroupe
	 *            le groupe à modifier
	 * @param nouveauGroupe
	 *            le nouveau groupe avec les mises à jour
	 * @return l'ancien groupe mis à jour
	 */
	public GroupeDTO modifier(GroupeDTO ancienGroupe, GroupeDTO nouveauGroupe);

	/**
	 * Supprimer un groupe
	 * 
	 * @param groupe
	 *            le groupe à supprimer
	 */
	public void supprimer(GroupeDTO groupe);

	/**
	 * Lister les groupes existant
	 * 
	 * @return la liste des groupes existant
	 */
	public List<GroupeDTO> lister() throws MetierException;

}