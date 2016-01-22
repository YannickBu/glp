package ipint.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.DTO.UtilisateurEnAttenteDTO;
import ipint.glp.api.DTO.enumType.Statut;
import ipint.glp.api.exception.GroupeInconnuException;
import ipint.glp.api.exception.InformationManquanteException;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.exception.UtilisateurEnAttenteInconnuException;
import ipint.glp.api.exception.UtilisateurExistantException;
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
	public UtilisateurEnAttenteDTO creer(UtilisateurEnAttenteDTO utilisateurEnAttenteDTO) throws MetierException {
		if(utilisateurEnAttenteDTO==null){
			throw new InformationManquanteException("L'utilisateurEnAttenteDTO est null");
		}
		if(utilisateurEnAttenteDTO.getEmail()==null){
			throw new InformationManquanteException("L'utilisateurEnAttenteDTO n'a pas d'email");
		}
		if(utilisateurEnAttenteDTO.getGroupePrincipal()==null){
			throw new InformationManquanteException("L'utilisateurEnAttenteDTO n'a pas de groupe principal");
		}
		if(utilisateurEnAttenteDTO.getGroupePrincipal().getIdGroupe()==null){
			throw new InformationManquanteException("Le groupe principal de l'utilisateurEnAttenteDTO n'a pas d'id");
		}

		UtilisateurEnAttente utilisateurEnAttente = new UtilisateurEnAttente();

		utilisateurEnAttente.setDiplome(utilisateurEnAttenteDTO.getDiplome());
		Query q = em.createQuery("select g from Utilisateur g where g.email = '" + utilisateurEnAttenteDTO.getEmail() + "'");
		if(!q.getResultList().isEmpty()){
			throw new UtilisateurExistantException("Un utilisateur possède déjà l'email " + utilisateurEnAttenteDTO.getEmail());
		}
		utilisateurEnAttente.setEmail(utilisateurEnAttenteDTO.getEmail());
		utilisateurEnAttente.setDateNaissance(utilisateurEnAttenteDTO.getDateNaissance());
		Groupe groupe = null;
		q = em.createQuery("select g from Groupe g where g.idGroupe = '"
				+ utilisateurEnAttenteDTO.getGroupePrincipal().getIdGroupe() + "'");
		try{
			groupe = (Groupe) q.getSingleResult();
		}catch(NoResultException e){
			throw new GroupeInconnuException(utilisateurEnAttenteDTO.getGroupePrincipal().toString()+" n'existe pas");
		}
		utilisateurEnAttente.setGroupePrincipal(groupe);
		utilisateurEnAttente.setAnneeDiplome(utilisateurEnAttenteDTO.getAnneeDiplome());
		utilisateurEnAttente.setNom(utilisateurEnAttenteDTO.getNom());
		utilisateurEnAttente.setPrenom(utilisateurEnAttenteDTO.getPrenom());
		em.persist(utilisateurEnAttente);
		//TODO a supprimer 
		//utilisateurEnAttente = em.find(UtilisateurEnAttente.class, utilisateurEnAttente.getIdUtilisateurEnAttente());
		return MappingToDTO.utilisateurEnAttenteToUtilisateurEnAttenteDTO(utilisateurEnAttente);

	}

	@Override
	public void supprimer(UtilisateurEnAttenteDTO utilisateurEnAttenteAValiderDTO) throws MetierException {
		if(utilisateurEnAttenteAValiderDTO==null){
			throw new InformationManquanteException("L'utilisateurEnAttenteDTO est null");
		}
		if(utilisateurEnAttenteAValiderDTO.getIdUtilisateurEnAttente()==null){
			throw new InformationManquanteException("L'utilisateurEnAttenteDTO n'a pas d'id");
		}
		UtilisateurEnAttente utilisateurEnAttente = new UtilisateurEnAttente();
		utilisateurEnAttente = em.find(UtilisateurEnAttente.class, utilisateurEnAttenteAValiderDTO.getIdUtilisateurEnAttente());
		if(utilisateurEnAttente==null){
			throw new UtilisateurEnAttenteInconnuException(utilisateurEnAttenteAValiderDTO.toString()+" n'existe pas");
		}
		em.remove(utilisateurEnAttente);
	}

	@Override
	public void valider(UtilisateurEnAttenteDTO utilisateurEnAttenteAValiderDTO) throws MetierException {
		if(utilisateurEnAttenteAValiderDTO==null){
			throw new InformationManquanteException("L'utilisateurEnAttenteAValiderDTO est null");
		}
		if(utilisateurEnAttenteAValiderDTO.getEmail()==null){
			throw new InformationManquanteException("L'utilisateurEnAttenteAValiderDTO n'a pas de mail");
		}
		UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
		utilisateurDTO.setEmail(utilisateurEnAttenteAValiderDTO.getEmail());
		utilisateurDTO.setNom(utilisateurEnAttenteAValiderDTO.getNom());
		utilisateurDTO.setPrenom(utilisateurEnAttenteAValiderDTO.getPrenom());
		utilisateurDTO.setStatut(Statut.DIPLOME);
		utilisateurDTO.setGroupePrincipal(utilisateurEnAttenteAValiderDTO.getGroupePrincipal());
		// GenererMotDePasse generationMotDePasse = new GenererMotDePasse(10);
		utilisateurDTO.setPassword("pwd");
		UtilisateurImpl utilisateurService = new UtilisateurImpl(em);
		utilisateurService.creer(utilisateurDTO);
		supprimer(utilisateurEnAttenteAValiderDTO);
	}

	@Override
	public List<UtilisateurEnAttenteDTO> lister() throws MetierException {
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
	public UtilisateurEnAttenteDTO trouver(int idUtilisateurEnAttente) throws MetierException {
		UtilisateurEnAttente utilisateurEnAttente = em.find(UtilisateurEnAttente.class, idUtilisateurEnAttente);
		if(utilisateurEnAttente==null){
			throw new UtilisateurEnAttenteInconnuException("L'utilisateurEnAttente ayyant pour id "+idUtilisateurEnAttente+" n'existe pas");
		}
		return MappingToDTO.utilisateurEnAttenteToUtilisateurEnAttenteDTO(utilisateurEnAttente);
	}

}
