package ipint.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.ProfilDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.DTO.UtilisateurEnAttenteDTO;
import ipint.glp.api.DTO.enumType.Statut;
import ipint.glp.api.exception.GroupeInconnuException;
import ipint.glp.api.exception.InformationManquanteException;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.exception.UtilisateurEnAttenteInconnuException;
import ipint.glp.api.itf.UtilisateurEnAttenteService;
import ipint.glp.domain.entity.Groupe;
import ipint.glp.domain.entity.Profil;
import ipint.glp.domain.entity.Utilisateur;
import ipint.glp.domain.entity.UtilisateurEnAttente;
import ipint.glp.domain.util.GenererMotDePasse;
import ipint.glp.domain.util.Mail;
import ipint.glp.domain.util.MappingToDTO;

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
		if (utilisateurEnAttenteDTO == null) {
			throw new InformationManquanteException(
					"UtilisateurEnAttenteImpl.creer : L'utilisateurEnAttenteDTO est null");
		}
		if (utilisateurEnAttenteDTO.getEmail() == null) {
			throw new InformationManquanteException(
					"UtilisateurEnAttenteImpl.creer : L'utilisateurEnAttenteDTO n'a pas d'email");
		}
		if (utilisateurEnAttenteDTO.getGroupePrincipal() == null) {
			throw new InformationManquanteException(
					"UtilisateurEnAttenteImpl.creer : L'utilisateurEnAttenteDTO n'a pas de groupe principal");
		}
		if (utilisateurEnAttenteDTO.getGroupePrincipal().getIdGroupe() == null) {
			throw new InformationManquanteException(
					"UtilisateurEnAttenteImpl.creer : Le groupe principal de l'utilisateurEnAttenteDTO n'a pas d'id");
		}

		UtilisateurEnAttente utilisateurEnAttente = new UtilisateurEnAttente();

		utilisateurEnAttente.setDiplome(utilisateurEnAttenteDTO.getDiplome());

		Query q = em.createQuery("select g from Utilisateur g where g.email = :email");
		q.setParameter("email", utilisateurEnAttenteDTO.getEmail());

		/*
		 * if (!q.getResultList().isEmpty()) { throw new
		 * UtilisateurExistantException(
		 * "UtilisateurEnAttenteImpl.creer : Un utilisateur possède déjà l'email "
		 * + utilisateurEnAttenteDTO.getEmail()); } q = em.createQuery(
		 * "select g from UtilisateurEnAttente g where g.email = :email");
		 * q.setParameter("email", utilisateurEnAttenteDTO.getEmail()); if
		 * (!q.getResultList().isEmpty()) { throw new
		 * UtilisateurExistantException(
		 * "UtilisateurEnAttenteImpl.creer : Un utilisateur en attente de validation possède déjà l'email "
		 * + utilisateurEnAttenteDTO.getEmail()); }
		 */

		utilisateurEnAttente.setEmail(utilisateurEnAttenteDTO.getEmail());
		utilisateurEnAttente.setDateNaissance(utilisateurEnAttenteDTO.getDateNaissance());
		Groupe groupe = null;
		q = em.createQuery("select g from Groupe g where g.idGroupe = :idgroupe");
		q.setParameter("idgroupe", utilisateurEnAttenteDTO.getGroupePrincipal().getIdGroupe());
		try {
			groupe = (Groupe) q.getSingleResult();
		} catch (NoResultException e) {
			throw new GroupeInconnuException("UtilisateurEnAttenteImpl.creer : "
					+ utilisateurEnAttenteDTO.getGroupePrincipal().toString() + " n'existe pas");
		}
		utilisateurEnAttente.setGroupePrincipal(groupe);
		utilisateurEnAttente.setAnneeDiplome(utilisateurEnAttenteDTO.getAnneeDiplome());
		utilisateurEnAttente.setNom(utilisateurEnAttenteDTO.getNom());
		utilisateurEnAttente.setPrenom(utilisateurEnAttenteDTO.getPrenom());
		em.persist(utilisateurEnAttente);
		// TODO a supprimer
		// utilisateurEnAttente = em.find(UtilisateurEnAttente.class,
		// utilisateurEnAttente.getIdUtilisateurEnAttente());
		return MappingToDTO.utilisateurEnAttenteToUtilisateurEnAttenteDTO(utilisateurEnAttente);

	}

	@Override
	public void refuser(UtilisateurEnAttenteDTO utilisateurEnAttenteAValiderDTO, String optionalMessage)
			throws MetierException {
		int typeDeMessage = 3;
		String messageOptionel = "";
		String password = "";

		if (!messageOptionel.equals(optionalMessage)) {
			typeDeMessage = 4;
			messageOptionel = optionalMessage;
		}

		if (utilisateurEnAttenteAValiderDTO == null) {
			throw new InformationManquanteException(
					"UtilisateurEnAttenteImpl.refuser : L'utilisateurEnAttenteDTO est null");
		}
		if (utilisateurEnAttenteAValiderDTO.getIdUtilisateurEnAttente() == null) {
			throw new InformationManquanteException(
					"UtilisateurEnAttenteImpl.refuser : L'utilisateurEnAttenteDTO n'a pas d'id");
		}
		UtilisateurEnAttente utilisateurEnAttente = new UtilisateurEnAttente();
		utilisateurEnAttente = em.find(UtilisateurEnAttente.class,
				utilisateurEnAttenteAValiderDTO.getIdUtilisateurEnAttente());
		if (utilisateurEnAttente == null) {
			throw new UtilisateurEnAttenteInconnuException("UtilisateurEnAttenteImpl.refuser : "
					+ utilisateurEnAttenteAValiderDTO.toString() + " n'existe pas");
		}
		String messageMail = Mail.construireMail(typeDeMessage, messageOptionel, password);
		Mail.envoyerMail(utilisateurEnAttenteAValiderDTO.getEmail(), messageMail);
		supprimer(utilisateurEnAttenteAValiderDTO);
	}

	@Override
	public void supprimer(UtilisateurEnAttenteDTO utilisateurEnAttenteAValiderDTO) throws MetierException {

		if (utilisateurEnAttenteAValiderDTO == null) {
			throw new InformationManquanteException(
					"UtilisateurEnAttenteImpl.supprimer : L'utilisateurEnAttenteDTO est null");
		}
		if (utilisateurEnAttenteAValiderDTO.getIdUtilisateurEnAttente() == null) {
			throw new InformationManquanteException(
					"UtilisateurEnAttenteImpl.supprimer : L'utilisateurEnAttenteDTO n'a pas d'id");
		}
		UtilisateurEnAttente utilisateurEnAttente = new UtilisateurEnAttente();
		utilisateurEnAttente = em.find(UtilisateurEnAttente.class,
				utilisateurEnAttenteAValiderDTO.getIdUtilisateurEnAttente());
		if (utilisateurEnAttente == null) {
			throw new UtilisateurEnAttenteInconnuException("UtilisateurEnAttenteImpl.supprimer : "
					+ utilisateurEnAttenteAValiderDTO.toString() + " n'existe pas");
		}
		em.remove(utilisateurEnAttente);
	}

	@Override
	public void valider(UtilisateurEnAttenteDTO utilisateurEnAttenteAValiderDTO, String optionalMessage)
			throws MetierException {
		UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
		utilisateurDTO.setEmail(utilisateurEnAttenteAValiderDTO.getEmail());
		Utilisateur utilisateur = new Utilisateur();

		Query q = em.createQuery("select u from Utilisateur u where u.email = '" + utilisateurDTO.getEmail() + "'");
		try {
			utilisateur = (Utilisateur) q.getSingleResult();
			Groupe groupe = new Groupe();
			GroupeDTO groupeDTO = utilisateurEnAttenteAValiderDTO.getGroupePrincipal();
			groupe.setIdGroupe(groupeDTO.getIdGroupe());
			groupe = em.find(Groupe.class, groupe.getIdGroupe());
			List<Groupe> lesGroupes = utilisateur.getGroupes();
			System.out.println("GROUPE : " + groupe);
			/*
			 * for (Groupe lesGroupesUtil : lesGroupes) { System.out.println(
			 * "++++++++++++++++++++++++++++++++++++++++++++++++++++++++" +
			 * lesGroupesUtil); }
			 */
			lesGroupes.add(groupe);
			utilisateur.setGroupes(lesGroupes);
			groupe.getUtilisateurs().add(utilisateur);
			// System.out.println(("----------------------- GROUPE : " +
			// utilisateur.getGroupes()));
			// em.persist(groupe);
			em.merge(utilisateur);
			int typeDeMessage = 5;
			String messageOptionel = "";
			String messageMail = Mail.construireMail(typeDeMessage, messageOptionel, groupe.getNomGroupe());
			Mail.envoyerMail(utilisateurEnAttenteAValiderDTO.getEmail(), messageMail);

		} catch (NoResultException e) {
			System.out.println();
			int typeDeMessage = 1;
			String messageOptionel = "";

			if (!messageOptionel.equals(optionalMessage)) {
				typeDeMessage = 2;
				messageOptionel = optionalMessage;
			}

			if (utilisateurEnAttenteAValiderDTO == null) {
				throw new InformationManquanteException(
						"UtilisateurEnAttenteImpl.valider : L'utilisateurEnAttenteAValiderDTO est null");
			}
			if (utilisateurEnAttenteAValiderDTO.getEmail() == null) {
				throw new InformationManquanteException(
						"UtilisateurEnAttenteImpl.valider : L'utilisateurEnAttenteAValiderDTO n'a pas de mail");
			}
			utilisateurDTO.setNom(utilisateurEnAttenteAValiderDTO.getNom());
			utilisateurDTO.setPrenom(utilisateurEnAttenteAValiderDTO.getPrenom());
			utilisateurDTO.setStatut(Statut.DIPLOME);
			utilisateurDTO.setGroupePrincipal(utilisateurEnAttenteAValiderDTO.getGroupePrincipal());
			Profil profil = new Profil();
			profil.setDiplomePrincipal(utilisateurEnAttenteAValiderDTO.getDiplome());
			profil.setAnneeDiplome(utilisateurEnAttenteAValiderDTO.getAnneeDiplome());
			utilisateurDTO.setProfil(MappingToDTO.profilToProfilDTO(profil));
			GenererMotDePasse generationMotDePasse = new GenererMotDePasse(10);
			String password = generationMotDePasse.nextString();
			utilisateurDTO.setPassword(password);
			String messageMail = Mail.construireMail(typeDeMessage, messageOptionel, password);
			Mail.envoyerMail(utilisateurEnAttenteAValiderDTO.getEmail(), messageMail);
			UtilisateurImpl utilisateurService = new UtilisateurImpl(em);

			utilisateurService.creer(utilisateurDTO);
		}
		for (Groupe lesGroupesUtil : utilisateur.getGroupes()) {
			System.out.println(lesGroupesUtil);
		}
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
		if (utilisateurEnAttente == null) {
			throw new UtilisateurEnAttenteInconnuException(
					"UtilisateurEnAttenteImpl.trouver : L'utilisateurEnAttente ayant pour id " + idUtilisateurEnAttente
							+ " n'existe pas");
		}
		return MappingToDTO.utilisateurEnAttenteToUtilisateurEnAttenteDTO(utilisateurEnAttente);
	}

}
