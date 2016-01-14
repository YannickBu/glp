package ipint.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.DTO.UtilisateurEnAttenteDTO;
import ipint.glp.api.DTO.enumType.Statut;
import ipint.glp.api.itf.UtilisateurEnAttenteService;
import ipint.glp.domain.entity.Groupe;
import ipint.glp.domain.entity.UtilisateurEnAttente;
import ipint.glp.domain.entity.util.GenererMotDePasse;
import ipint.glp.domain.entity.util.MappingToDTO;

/**
 * Implementation des services liés à la gestion d'un utilisateur en attente de
 * validation par un modérateur.
 * 
 * @author declerck
 *
 */
@Stateless
public class UtilisateurEnAttenteImpl implements UtilisateurEnAttenteService {

	@PersistenceContext(unitName = "PU")
	private EntityManager em;
	// @EJB
	// UtilisateurImpl utilisateurService;

	@Override
	public UtilisateurEnAttenteDTO creer(UtilisateurEnAttenteDTO utilisateurEnAttenteDTO) {
		UtilisateurEnAttente utilisateurEnAttente = new UtilisateurEnAttente();

		utilisateurEnAttente.setDiplome(utilisateurEnAttenteDTO.getDiplome());
		utilisateurEnAttente.setEmail(utilisateurEnAttenteDTO.getEmail());
		utilisateurEnAttente.setDateNaissance(utilisateurEnAttenteDTO.getDateNaissance());
		Groupe groupe = null;
		if (utilisateurEnAttenteDTO.getGroupePrincipal() != null) {
			Query q = em.createQuery("select g from Groupe g where g.idGroupe = '"
					+ utilisateurEnAttenteDTO.getGroupePrincipal().getIdGroupe() + "'");
			groupe = (Groupe) q.getSingleResult();
		}
		utilisateurEnAttente.setGroupePrincipal(groupe);
		utilisateurEnAttente.setAnneeDiplome(utilisateurEnAttenteDTO.getAnneeDiplome());
		utilisateurEnAttente.setNom(utilisateurEnAttenteDTO.getNom());
		utilisateurEnAttente.setPrenom(utilisateurEnAttenteDTO.getPrenom());
		em.persist(utilisateurEnAttente);
		utilisateurEnAttente = em.find(UtilisateurEnAttente.class, utilisateurEnAttente.getIdUtilisateurEnAttente());
		return MappingToDTO.utilisateurEnAttenteToUtilisateurEnAttenteDTO(utilisateurEnAttente);

	}

	@Override
	public void supprimer(UtilisateurEnAttenteDTO utilisateurEnAttenteAValiderDTO) {
		UtilisateurEnAttente utilisateurEnAttente = new UtilisateurEnAttente();
		utilisateurEnAttente.setIdUtilisateurEnAttente(utilisateurEnAttenteAValiderDTO.getIdUtilisateurEnAttente());
		utilisateurEnAttente = em.merge(utilisateurEnAttente);
		em.remove(utilisateurEnAttente);
	}

	@Override
	public void valider(UtilisateurEnAttenteDTO utilisateurEnAttenteAValiderDTO) {
		UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
		utilisateurDTO.setEmail(utilisateurEnAttenteAValiderDTO.getEmail());
		utilisateurDTO.setNom(utilisateurEnAttenteAValiderDTO.getNom());
		utilisateurDTO.setPrenom(utilisateurEnAttenteAValiderDTO.getPrenom());
		utilisateurDTO.setStatut(Statut.DIPLOME);
		utilisateurDTO.setGroupePrincipal(utilisateurEnAttenteAValiderDTO.getGroupePrincipal());
		GenererMotDePasse generationMotDePasse = new GenererMotDePasse(10);
		utilisateurDTO.setPassword(generationMotDePasse.nextString());
		UtilisateurImpl utilisateurService = new UtilisateurImpl(em);
		utilisateurService.creer(utilisateurDTO);
		supprimer(utilisateurEnAttenteAValiderDTO);
	}

	@Override
	public List<UtilisateurEnAttenteDTO> lister() {
		Query q = em.createQuery("select u from UtilisateurEnAttente u ");
		List<UtilisateurEnAttente> lesUtilisateurEnAttente = q.getResultList();
		List<UtilisateurEnAttenteDTO> lesUtilisateurEnAttenteDTO = new ArrayList<UtilisateurEnAttenteDTO>();
		for (UtilisateurEnAttente utilisateurEnAttente : lesUtilisateurEnAttente) {
			lesUtilisateurEnAttenteDTO
					.add(MappingToDTO.utilisateurEnAttenteToUtilisateurEnAttenteDTO(utilisateurEnAttente));
		}
		return lesUtilisateurEnAttenteDTO;
	}

	@Override
	public UtilisateurEnAttenteDTO trouver(int idUtilisateurEnAttente) {
		UtilisateurEnAttente utilisateurEnAttente = em.find(UtilisateurEnAttente.class, idUtilisateurEnAttente);
		return MappingToDTO.utilisateurEnAttenteToUtilisateurEnAttenteDTO(utilisateurEnAttente);
	}

}
