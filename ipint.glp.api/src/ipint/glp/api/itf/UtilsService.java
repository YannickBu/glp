package ipint.glp.api.itf;

import java.util.List;

import javax.ejb.Local;

import ipint.glp.api.DTO.PaysDTO;
import ipint.glp.api.DTO.RegionDTO;
import ipint.glp.api.DTO.VilleDTO;
import ipint.glp.api.exception.MetierException;

/**
 * @author declerck
 *
 */
@Local
public interface UtilsService {

	
	public List<PaysDTO> listerPaysDuMonde() throws MetierException;
	
	public List<RegionDTO> listerRegions() throws MetierException;
	
	public List<VilleDTO> listerVilles() throws MetierException;

	// public List<UtilisateurDTO> listerUtilisateur();

}
