package ipint.glp.api.itf;

import javax.ejb.Local;
import javax.ejb.Remote;

import ipint.glp.api.DTO.GroupeDTO;

@Local
public interface GroupeService extends Service<GroupeDTO> {

	public GroupeDTO creer(GroupeDTO groupe);
	
	public GroupeDTO trouverGroupe(GroupeDTO groupe);
	
}
