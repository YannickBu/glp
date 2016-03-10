package ipint.glp.api.itf;

import java.util.List;

import javax.ejb.Local;

import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.exception.MetierException;

@Local
public interface GroupeService {

	/**
	 * Créer un groupe
	 * REQUIRED Le groupeDTO en paramètre  DOIT avoir un utilisateur responsable
	 * REQUIRED Sa liste danimateurs doit avoir au moins un utilisateur (qui sera mis dans la liste danimateurs ET dutilisateurs)
	 * REQUIRED Nom du groupe
	 * NONE Sa liste d'utilisateurs est inutile ici
	 * NONE Sa liste darticles est inutile ici
	 * 
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
	public GroupeDTO modifier(GroupeDTO ancienGroupe, GroupeDTO nouveauGroupe) throws MetierException;

	/**
	 * REQUIRED - id et nom de utilisateurDTOAAjouter
	 * REQUIRED - id et nom de groupeDTOAModifier
	 * 
	 * @param groupeDTOAModifier
	 * @param utilisateurDTOAAjouter
	 * @return
	 * @throws MetierException
	 */
	public GroupeDTO ajouterUtilisateur(GroupeDTO groupeDTOAModifier, UtilisateurDTO utilisateurDTOAAjouter) throws MetierException;
	
	/**
	 * REQUIRED - id et nom de utilisateurDTOASuppimer
	 * REQUIRED - id et nom de groupeDTOAModifier
	 * 
	 * @param groupeDTOASupprimer
	 * @param utilisateurDTOASuppimer
	 * @return
	 * @throws MetierException
	 */
	public GroupeDTO supprimerUtilisateur(GroupeDTO groupeDTOASupprimer, UtilisateurDTO utilisateurDTOASuppimer) throws MetierException;
	
	/**
	 * REQUIRED - id et nom de animateurDTOAAjouter
	 * REQUIRED - id et nom de groupeDTOAModifier
	 * 
	 * @param groupeDTOAModifier
	 * @param animateurDTOAAjouter
	 * @return
	 * @throws MetierException
	 */
	public GroupeDTO ajouterAnimateur(GroupeDTO groupeDTOAModifier, UtilisateurDTO animateurDTOAAjouter) throws MetierException;
	
	/**
	 * REQUIRED - id et nom de animateurDTOAAjouter
	 * REQUIRED - id et nom de groupeDTOAModifier
	 * 
	 * @param groupeDTOAModifier
	 * @param animateurDTOAAjouter
	 * @return
	 * @throws MetierException
	 */
	public GroupeDTO supprimerAnimateur(GroupeDTO groupeDTOAModifier, UtilisateurDTO animateurDTOAAjouter) throws MetierException;
	
	/**
	 * REQUIRED - id et nom de nouveauResponsableDTO
	 * REQUIRED - id et nom de groupeDTOAModifier
	 * 
	 * @param groupeDTOAModifier
	 * @param nouveauResponsableDTO
	 * @return
	 * @throws MetierException
	 */
	public GroupeDTO modifierResponsable(GroupeDTO groupeDTOAModifier, UtilisateurDTO nouveauResponsableDTO) throws MetierException;
	
	/**
	 * Supprimer un groupe
	 * 
	 * @param groupe
	 *            le groupe à supprimer
	 */
	public void supprimer(GroupeDTO groupe);

	/**
	 * Lister les groupes existant en fonction de leur type
	 * 
	 * @param isGroupeOfficiel
	 * @return la liste des groupes existant
	 */
	public List<GroupeDTO> listerParType(boolean isGroupeOfficiel) throws MetierException;
	
	public List<UtilisateurDTO> listerUtilisateurs(GroupeDTO groupeDTO) throws MetierException;

	public List<GroupeDTO> listerTousLesGroupes() throws MetierException;
	

}