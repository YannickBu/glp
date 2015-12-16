package ipint.glp.api.itf;

import ipint.glp.api.DTO.GroupeDTO;

public interface GroupeService extends Service<GroupeDTO> {

	public GroupeDTO creer(GroupeDTO groupe);

	public GroupeDTO trouverGroupe(GroupeDTO groupe);

}
