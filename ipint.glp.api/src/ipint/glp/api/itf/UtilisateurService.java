package ipint.glp.api.itf;

import java.util.List;

import javax.ejb.Remote;

import ipint.glp.api.DTO.UtilisateurDTO;

@Remote
public interface UtilisateurService {

	public UtilisateurDTO creer(UtilisateurDTO utilisateur);
	
	public UtilisateurDTO trouver(UtilisateurDTO utilisateur);
	
	public UtilisateurDTO modifier(UtilisateurDTO ancienUtilisateur, UtilisateurDTO nouvelUtilisateur);
	
	public void supprimer(UtilisateurDTO utilisateur);
	
//	public List<UtilisateurDTO> listerUtilisateur();
	
}
