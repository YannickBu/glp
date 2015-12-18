package ipint.glp.api.itf;

import javax.ejb.Local;

import ipint.glp.api.DTO.UtilisateurDTO;

@Local
public interface UtilisateurService {

	public UtilisateurDTO creer(UtilisateurDTO utilisateur);
	
	public UtilisateurDTO trouver(UtilisateurDTO utilisateur);
	
	public UtilisateurDTO modifier(UtilisateurDTO ancienUtilisateur, UtilisateurDTO nouvelUtilisateur);
	
	public void supprimer(UtilisateurDTO utilisateur);
	
//	public List<UtilisateurDTO> listerUtilisateur();
	
}
