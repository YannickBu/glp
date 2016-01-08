package ipint.glp.domain.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.DTO.UtilisateurEnAttenteDTO;
import ipint.glp.api.DTO.enumType.Statut;
import ipint.glp.api.itf.UtilisateurEnAttenteService;
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

	@Override
	public UtilisateurEnAttenteDTO creer(UtilisateurEnAttenteDTO utilisateurEnAttenteDTO) {
		UtilisateurEnAttente utilisateurEnAttente = new UtilisateurEnAttente();
		utilisateurEnAttente.setDiplome(utilisateurEnAttenteDTO.getDiplome());
		utilisateurEnAttente.setEmail(utilisateurEnAttenteDTO.getNom());
		utilisateurEnAttente.setDateNaissance(utilisateurEnAttenteDTO.getDateNaissance());
		// Groupe groupe = null;
		// if (utilisateurEnAttenteDTO.getGroupePrincipal() != null) {
		// Query q = em.createQuery("select g from Groupe g where u.idGroupe =
		// '"
		// + utilisateurEnAttenteDTO.getGroupePrincipal().getIdGroupe() + "'");
		// groupe = (Groupe) q.getSingleResult();
		// }
		// utilisateurEnAttente.setGroupePrincipal(groupe);
		utilisateurEnAttente.setAnneeDiplome(utilisateurEnAttenteDTO.getAnneeDiplome());
		utilisateurEnAttente.setNom(utilisateurEnAttenteDTO.getPrenom());
		utilisateurEnAttente.setPrenom(utilisateurEnAttenteDTO.getPrenom());
		em.persist(utilisateurEnAttente);
		utilisateurEnAttente = em.find(UtilisateurEnAttente.class, utilisateurEnAttente.getIdUtilisateurEnAttente());
		return MappingToDTO.utilisateurEnAttenteToUtilisateurEnAttenteDTO(utilisateurEnAttente);

	}

	@Override
	public void supprimer(UtilisateurEnAttenteDTO utilisateurEnAttenteAValiderDTO) {
		UtilisateurEnAttente utilisateurEnAttente = new UtilisateurEnAttente();
		utilisateurEnAttente.setIdUtilisateurEnAttente(utilisateurEnAttenteAValiderDTO.getIdUtilisateurEnAttente());
		em.remove(utilisateurEnAttente);
	}

	@Override
	public void valider(UtilisateurEnAttenteDTO utilisateurEnAttenteAValiderDTO) {
		UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
		utilisateurDTO.setEmail(utilisateurEnAttenteAValiderDTO.getEmail());
		utilisateurDTO.setNom(utilisateurEnAttenteAValiderDTO.getNom());
		utilisateurDTO.setPrenom(utilisateurEnAttenteAValiderDTO.getPrenom());
		// TODO : Gérer date de naissance
		utilisateurDTO.setStatut(Statut.DIPLOME);
		// TODO : Gérer génération aléatoire du mot de passe
		utilisateurDTO.setPassword("password");
		UtilisateurImpl utilisateurService = new UtilisateurImpl();
		utilisateurService.creer(utilisateurDTO);
		supprimer(utilisateurEnAttenteAValiderDTO);
	}

}
