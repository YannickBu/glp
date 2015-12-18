package ipint.glp.api.itf;

import javax.ejb.Local;

import ipint.glp.api.DTO.GroupeDTO;

@Local
public interface GroupeService {

	public GroupeDTO creer(GroupeDTO groupe);
	
	public GroupeDTO trouver(GroupeDTO groupe);
	
	public GroupeDTO modifier(GroupeDTO ancienGroupe, GroupeDTO nouveauGroupe);
	
	public void supprimer(GroupeDTO groupe);

}