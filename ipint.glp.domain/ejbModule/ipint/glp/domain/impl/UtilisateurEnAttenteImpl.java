package ipint.glp.domain.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint.glp.api.DTO.UtilisateurEnAttenteDTO;
import ipint.glp.api.itf.UtilisateurEnAttenteService;
import ipint.glp.domain.entity.Groupe;
import ipint.glp.domain.entity.UtilisateurEnAttente;
import ipint.glp.domain.entity.util.MappingToDTO;

/**
 * @author declerck
 *
 */
public class UtilisateurEnAttenteImpl implements UtilisateurEnAttenteService {

	@PersistenceContext(unitName = "PU")
	private EntityManager em;

	@Override
	public UtilisateurEnAttenteDTO créer(UtilisateurEnAttenteDTO utilisateurEnAttenteDTO) {
		UtilisateurEnAttente utilisateurEnAttente = new UtilisateurEnAttente();
		utilisateurEnAttente.setIdUtilisateurEnAttente(utilisateurEnAttenteDTO.getIdUtilisateurEnAttente());
		utilisateurEnAttente.setDiplome(utilisateurEnAttenteDTO.getDiplome());
		utilisateurEnAttente.setEmail(utilisateurEnAttenteDTO.getNom());
		utilisateurEnAttente.setDateNaissance(utilisateurEnAttenteDTO.getDateNaissance());
		Groupe groupe = null;
		if (utilisateurEnAttenteDTO.getGroupePrincipal() != null) {
			Query q = em.createQuery("select g from Groupe g where u.idGroupe = '"
					+ utilisateurEnAttenteDTO.getGroupePrincipal().getIdGroupe() + "'");
			groupe = (Groupe) q.getSingleResult();
		}
		utilisateurEnAttente.setGroupePrincipal(groupe);
		utilisateurEnAttente.setAnneeDiplome(utilisateurEnAttenteDTO.getAnneeDiplome());
		utilisateurEnAttente.setNom(utilisateurEnAttenteDTO.getPrenom());
		utilisateurEnAttente.setPrenom(utilisateurEnAttenteDTO.getPrenom());
		em.persist(utilisateurEnAttente);
		utilisateurEnAttente = em.find(UtilisateurEnAttente.class, utilisateurEnAttente.getIdUtilisateurEnAttente());
		return MappingToDTO.utilisateurEnAttenteToUtilisateurEnAttenteDTO(utilisateurEnAttente);

	}

	@Override
	public void valider() {
		// TODO Auto-generated method stub
	}

	@Override
	public void refuser() {
		// TODO Auto-generated method stub
	}

}
