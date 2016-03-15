package ipint.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.itf.RechercheService;
import ipint.glp.domain.entity.Groupe;
import ipint.glp.domain.entity.Utilisateur;
import ipint.glp.domain.util.MappingToDTO;


@Stateless
public class RechercheImpl implements RechercheService {

	@PersistenceContext(unitName = "PU")
	private EntityManager em;
	
	/**
	 * recherche de groupes par rapport un texte de recherche
	 * @param search
	 * @return liste de groupes correspondant a la recherche
	 */
	public List<GroupeDTO> rechercheGroupes(String search) throws MetierException{
		Query q = null;
		q = em.createQuery("select o from Groupe o");
		List<Groupe> grp = q.getResultList();
		List<GroupeDTO> grpDTO = new ArrayList<GroupeDTO>();
		String[] recherches = search.split(" ");
		for (Groupe e : grp) {
			for (int i = 0; i < recherches.length; i++) {
				if (e.getNomGroupe().toLowerCase().contains(recherches[i].toLowerCase())) {
					GroupeDTO gDTO = MappingToDTO.groupeToGroupeDTO(e);
					if (!grpDTO.contains(gDTO))
						grpDTO.add(gDTO);
				}
			}
		}
		return grpDTO;	
	}

	/**
	 * recherche d'utilisateurs par rapport un texte de recherche
	 * @param search
	 * @return liste d'utilisateurs correspondant a la recherche
	 * @throws MetierException 
	 */
	public List<UtilisateurDTO> rechercheUtilisateurs(String search) throws MetierException{
		Query q = null;
		q = em.createQuery("select o from Utilisateur o");
		List<Utilisateur> etu = q.getResultList();
		List<UtilisateurDTO> etuDTO = new ArrayList<UtilisateurDTO>();
		String[] recherches = search.split(" ");
		for (Utilisateur e : etu) {
			for (int i = 0; i < recherches.length; i++) {
				if (e.getNom().toLowerCase().contains(recherches[i].toLowerCase())
						|| e.getPrenom().toLowerCase().contains(recherches[i].toLowerCase())) {
					UtilisateurDTO eDTO = MappingToDTO.utilisateurToUtilisateurDTO(e);
					if (!etuDTO.contains(eDTO))
						etuDTO.add(eDTO);
				}
			}
		}
		return etuDTO;	
	}

}
