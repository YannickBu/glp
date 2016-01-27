package ipint.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint.glp.api.DTO.CompetenceDTO;
import ipint.glp.api.DTO.DiplomeDTO;
import ipint.glp.api.DTO.ExperienceDTO;
import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.DTO.enumType.Statut;
import ipint.glp.api.exception.GroupeInconnuException;
import ipint.glp.api.exception.InformationManquanteException;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.exception.ProfilInconnuException;
import ipint.glp.api.exception.UtilisateurInconnuException;
import ipint.glp.api.itf.UtilisateurService;
import ipint.glp.domain.entity.Competence;
import ipint.glp.domain.entity.Diplome;
import ipint.glp.domain.entity.Experience;
import ipint.glp.domain.entity.Groupe;
import ipint.glp.domain.entity.Profil;
import ipint.glp.domain.entity.Utilisateur;
import ipint.glp.domain.entity.UtilisateurGroupes;
import ipint.glp.domain.util.MappingToDTO;

@Stateless
public class UtilisateurImpl implements UtilisateurService {

	@PersistenceContext(unitName = "PU")
	private EntityManager em;

	public UtilisateurImpl() {
		super();
	}

	public UtilisateurImpl(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public UtilisateurDTO creer(UtilisateurDTO utilisateurDTO) throws MetierException {
		if (utilisateurDTO == null) {
			throw new InformationManquanteException("UtilisateurImpl.creer : L'utilisateurDTO est null");
		}
		if (utilisateurDTO.getEmail() == null) {
			throw new InformationManquanteException("UtilisateurImpl.creer : L'utilisateurDTO n'a pas d'email");
		}
		if (utilisateurDTO.getStatut() == null) {
			throw new InformationManquanteException("UtilisateurImpl.creer : L'utilisateurDTO n'a pas de statut");
		}
		if (utilisateurDTO.getPassword() == null) {
			throw new InformationManquanteException("UtilisateurImpl.creer : L'utilisateurDTO n'a pas de password");
		}
		if (utilisateurDTO.getNom() == null) {
			throw new InformationManquanteException("UtilisateurImpl.creer : L'utilisateurDTO n'a pas de nom");
		}
		if (utilisateurDTO.getPrenom() == null) {
			throw new InformationManquanteException("UtilisateurImpl.creer : L'utilisateurDTO n'a pas de prenom");
		}
		if (utilisateurDTO.getGroupePrincipal() == null) {
			throw new InformationManquanteException(
					"UtilisateurImpl.creer : " + utilisateurDTO.toString() + " n'a pas de groupe principal");
		}

		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setEmail(utilisateurDTO.getEmail());
		utilisateur.setNom(utilisateurDTO.getNom());
		utilisateur.setPrenom(utilisateurDTO.getPrenom());
		utilisateur.setPassword(utilisateurDTO.getPassword());
		utilisateur.setStatut(utilisateurDTO.getStatut());

		GroupeDTO groupeDTOm = utilisateurDTO.getGroupePrincipal();
		Groupe grpm = null;
		if (groupeDTOm.getIdGroupe() != null) {
			grpm = em.find(Groupe.class, groupeDTOm.getIdGroupe());
			if (grpm == null) {
				throw new GroupeInconnuException(
						"UtilisateurImpl.creer : Le groupe d'id=" + groupeDTOm.getIdGroupe() + " n'existe pas");
			}
		} else if (groupeDTOm.getNomGroupe() != null) {
			Query q = em.createQuery("select g from Groupe g where g.nomGroupe = '" + groupeDTOm.getNomGroupe() + "'");
			try {
				grpm = (Groupe) q.getSingleResult();
			} catch (NoResultException e) {
				throw new GroupeInconnuException(
						"UtilisateurImpl.creer : Le groupe ayant pour nom=" + grpm.getNomGroupe() + " n'existe pas");
			}
		}

		utilisateur.setGroupePrincipal(grpm);

		// Profil profil = em.find(Profil.class,
		// utilisateurDTO.getProfil().getIdProfil());
		// utilisateur.setProfil(profil);

		// TODO gerer les exceptions lorsque cette liste de groupes sera
		// utilisée

		// Gestion liste des groupes

		if (utilisateurDTO.getGroupes() != null && !utilisateurDTO.getGroupes().isEmpty()) {
			List<Groupe> lesGroupes = new ArrayList<Groupe>();
			for (GroupeDTO groupeDTO : utilisateurDTO.getGroupes()) {
				Groupe grp = null;
				if (groupeDTO.getIdGroupe() != null) {
					grp = em.find(Groupe.class, groupeDTO.getIdGroupe());
					if (grp == null) {
						throw new GroupeInconnuException(
								"UtilisateurImpl.creer : " + groupeDTO.toString() + " n'existe pas pour cet id");
					}
				} else if (groupeDTO.getNomGroupe() != null) {
					Query q = em.createQuery(
							"select g from Groupe g where g.nomGroupe = '" + groupeDTO.getNomGroupe() + "'");
					try {
						grp = (Groupe) q.getSingleResult();
					} catch (NoResultException e) {
						throw new GroupeInconnuException("UtilisateurImpl.creer : " + groupeDTO.toString()
								+ " n'existe pas pour cet nom de groupe");
					}
				}
				if (grp != null) {
					lesGroupes.add(grp);
					grp.getUtilisateurs().add(utilisateur);
					em.persist(grp);
				}
			}
			utilisateur.setGroupes(lesGroupes);
		}

		// TODO gerer les exceptions lorsque cette liste de groupes gérés sera
		// utilisée

		// Gestion de la liste des groupes gérés par l'utilisateur

		if (utilisateurDTO.getGroupesGeres() != null && !utilisateurDTO.getGroupesGeres().isEmpty()) {
			List<Groupe> lesGroupesGeres = new ArrayList<Groupe>();
			for (GroupeDTO groupeDTO : utilisateurDTO.getGroupesGeres()) {
				Groupe grp = new Groupe();
				if (groupeDTO.getIdGroupe() != null) {
					grp = em.find(Groupe.class, groupeDTO.getIdGroupe());
					if (grp == null) {
						throw new GroupeInconnuException(
								"UtilisateurImpl.creer : " + groupeDTO.toString() + " n'existe pas pour cet id");
					}
				} else if (groupeDTO.getNomGroupe() != null) {
					Query q = em.createQuery(
							"select g from Groupe g where g.nomGroupe = '" + groupeDTO.getNomGroupe() + "'");
					try {
						grp = (Groupe) q.getSingleResult();
					} catch (NoResultException e) {
						throw new GroupeInconnuException("UtilisateurImpl.creer : " + groupeDTO.toString()
								+ " n'existe pas pour ce nom de groupe");
					}
				}
				if (grp != null) {
					lesGroupesGeres.add(grp);
					grp.getUtilisateurs().add(utilisateur);
					em.persist(grp);
				}
			}
			utilisateur.setGroupesGeres(lesGroupesGeres);
		}

		// Gestion profil

		Profil pro = new Profil();
		if (utilisateurDTO.getProfil() != null) {
			// Champs non assoc
			pro.setCentreInteret(utilisateurDTO.getProfil().getCentreInteret());
			pro.setTelephone(utilisateurDTO.getProfil().getTelephone());

			// Assoc competence

			if (utilisateurDTO.getProfil().getCompetence() != null
					&& !utilisateurDTO.getProfil().getCompetence().isEmpty()) {
				List<Competence> listComp = new ArrayList<Competence>();
				for (CompetenceDTO compDTO : utilisateurDTO.getProfil().getCompetence()) {
					Competence comp = new Competence();
					comp.setLibelle(compDTO.getLibelle());
					comp.setNote(compDTO.getNote());
					comp.setProfil(pro);
					listComp.add(comp);
					em.persist(comp);
				}
				pro.setCompetence(listComp);
			}

			// Assoc diplome

			if (utilisateurDTO.getProfil().getDiplomes() != null
					&& !utilisateurDTO.getProfil().getDiplomes().isEmpty()) {
				List<Diplome> listDipl = new ArrayList<Diplome>();
				for (DiplomeDTO diplDTO : utilisateurDTO.getProfil().getDiplomes()) {
					Diplome dipl = new Diplome();
					dipl.setAnneeDebut(diplDTO.getAnneeDebut());
					dipl.setAnneFin(diplDTO.getAnneFin());
					dipl.setLibelle(diplDTO.getLibelle());
					dipl.setLieu(diplDTO.getLieu());
					dipl.setProfil(pro);
					listDipl.add(dipl);
					em.persist(dipl);
				}
				pro.setDiplomes(listDipl);
			}

			// Assoc experience

			if (utilisateurDTO.getProfil().getExperiences() != null
					&& !utilisateurDTO.getProfil().getExperiences().isEmpty()) {
				List<Experience> listExp = new ArrayList<Experience>();
				for (ExperienceDTO expDTO : utilisateurDTO.getProfil().getExperiences()) {
					Experience exp = new Experience();
					exp.setAnneeDebut(expDTO.getAnneeDebut());
					exp.setAnneFin(expDTO.getAnneFin());
					exp.setDescription(expDTO.getDescription());
					exp.setEntreprise(expDTO.getEntreprise());
					exp.setLieu(expDTO.getLieu());
					exp.setPoste(expDTO.getPoste());
					exp.setRegion(expDTO.getRegion());
					exp.setPays(expDTO.getPays());
					exp.setProfil(pro);
					listExp.add(exp);
					em.persist(exp);
				}
				pro.setExperiences(listExp);
			}

			// Assoc reseaux sociaux

			// if (utilisateurDTO.getProfil().getReseauxSociaux() != null
			// && !utilisateurDTO.getProfil().getReseauxSociaux().isEmpty()) {
			// List<String> listRes = new ArrayList<String>();
			// for (String res : utilisateurDTO.getProfil().getReseauxSociaux())
			// {
			// em.persist(res);
			// }
			// pro.setReseauxSociaux(listRes);
			// }

		}
		em.persist(pro);
		utilisateur.setProfil(pro);

		// Gestion des droits - ajout de dans la table UTILISATEURGROUPES en
		// fonction du statut

		UtilisateurGroupes utilGrp = new UtilisateurGroupes();
		utilGrp.setEmail(utilisateur.getEmail());
		utilGrp.setGroupe(Statut.DIPLOME.name().toLowerCase());
		em.persist(utilGrp);

		em.persist(utilisateur);

		return MappingToDTO.utilisateurToUtilisateurDTO(utilisateur);

	}

	@Override
	public UtilisateurDTO trouver(UtilisateurDTO utilisateurDTO) throws MetierException {
		if (utilisateurDTO == null) {
			throw new InformationManquanteException("UtilisateurImpl.trouver : L'utilisateurDTO est null");
		}
		if (utilisateurDTO.getEmail() == null && utilisateurDTO.getIdUtilisateur() == null) {
			throw new InformationManquanteException("UtilisateurImpl.trouver : L'utilisateurDTO n'a ni email ni id");
		}

		Utilisateur utilisateur = new Utilisateur();
		if (utilisateurDTO.getIdUtilisateur() != null) {
			utilisateur = em.find(Utilisateur.class, utilisateurDTO.getIdUtilisateur());
			if (utilisateur == null) {
				throw new UtilisateurInconnuException(
						"UtilisateurImpl.trouver : " + utilisateurDTO.toString() + " n'existe pas avec cet id");
			}
		} else {
			Query q = em.createQuery("select u from Utilisateur u where u.email = '" + utilisateurDTO.getEmail() + "'");
			try {
				utilisateur = (Utilisateur) q.getSingleResult();
			} catch (NoResultException e) {
				throw new UtilisateurInconnuException(
						"UtilisateurImpl.trouver : " + utilisateurDTO.toString() + " n'existe pas avec cet email");
			}
		}

		utilisateurDTO = MappingToDTO.utilisateurToUtilisateurDTO(utilisateur);
		return utilisateurDTO;
	}

	@Override
	public UtilisateurDTO modifier(UtilisateurDTO nouvelUtilisateur) throws MetierException {
		if (nouvelUtilisateur == null) {
			throw new InformationManquanteException("UtilisateurImpl.modifier : Le nouvelUtilisateurDTO est null");
		}
		if (nouvelUtilisateur.getIdUtilisateur() == null) {
			throw new InformationManquanteException("UtilisateurImpl.modifier : Le nouvelUtilisateurDTO n'a pas d'id");
		}

		Utilisateur utilisateurMAJ = em.find(Utilisateur.class, nouvelUtilisateur.getIdUtilisateur());
		if (utilisateurMAJ == null) {
			throw new UtilisateurInconnuException(
					"UtilisateurImpl.modifier : L'utilisateur à modifier n'existe pas (id="
							+ nouvelUtilisateur.getIdUtilisateur() + ")");
		}

		if (nouvelUtilisateur.getStatut() != null) {
			utilisateurMAJ.setStatut(nouvelUtilisateur.getStatut());
		}
		if (nouvelUtilisateur.getNom() != null && !"".equals(nouvelUtilisateur.getNom())) {
			utilisateurMAJ.setNom(nouvelUtilisateur.getNom());
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++MDP : " + nouvelUtilisateur.getPassword());

		if (nouvelUtilisateur.getPassword() != null && !"".equals(nouvelUtilisateur.getPassword())) {
			utilisateurMAJ.setPassword(nouvelUtilisateur.getPassword());
		}
		if (nouvelUtilisateur.getPrenom() != null && !"".equals(nouvelUtilisateur.getPrenom())) {
			utilisateurMAJ.setPrenom(nouvelUtilisateur.getPrenom());
		}
		if (nouvelUtilisateur.getEmail() != null && !"".equals(nouvelUtilisateur.getEmail())) {
			utilisateurMAJ.setEmail(nouvelUtilisateur.getEmail());
		}
		if (nouvelUtilisateur.getProfil() != null) {
			Profil profil = em.find(Profil.class, nouvelUtilisateur.getProfil().getIdProfil());
			if (profil == null) {
				throw new ProfilInconnuException("UtilisateurImpl.modifier : Le profil est null");
			}
			int idProfil = nouvelUtilisateur.getProfil().getIdProfil();
			profil.setTelephone(nouvelUtilisateur.getProfil().getTelephone());

			// les competences
			List<Competence> comps = new ArrayList<Competence>();
			if (nouvelUtilisateur.getProfil().getCompetence() != null
					&& !nouvelUtilisateur.getProfil().getCompetence().isEmpty()) {
				Competence comp = new Competence();
				for (CompetenceDTO compDTO : nouvelUtilisateur.getProfil().getCompetence()) {
					if (compDTO.getLibelle() != null && !"".equals(compDTO.getLibelle()) && compDTO.getNote() != 0) {
						Query q = em.createQuery("select c from Competence c where c.libelle = '" + compDTO.getLibelle()
								+ "' and c.profil.idProfil = :idProfil");
						q.setParameter("idProfil", idProfil);
						if (!q.getResultList().isEmpty()) {
							comp = (Competence) q.getSingleResult();
							if (comp != null) {
								comp.setNote(compDTO.getNote());
								comps.add(comp);
								em.merge(comp);
							}
						} else {
							comp = new Competence();
							comp.setProfil(em.find(Profil.class, nouvelUtilisateur.getProfil().getIdProfil()));
							comp.setLibelle(compDTO.getLibelle());
							comp.setNote(compDTO.getNote());
							comps.add(comp);
							em.persist(comp);
						}
					}
				}
			}
			profil.setCompetence(comps);

			// les diplomes
			List<Diplome> dipls = new ArrayList<Diplome>();
			if (nouvelUtilisateur.getProfil().getDiplomes() != null
					&& !nouvelUtilisateur.getProfil().getDiplomes().isEmpty()) {
				Diplome dipl = new Diplome();
				for (DiplomeDTO diplDTO : nouvelUtilisateur.getProfil().getDiplomes()) {
					if (diplDTO.getLibelle() != null && !"".equals(diplDTO.getLibelle())
							&& diplDTO.getAnneeDebut() != null && diplDTO.getAnneFin() != null) {
						Query q = em.createQuery("select d from Diplome d where d.libelle = '" + diplDTO.getLibelle()
								+ "' and d.profil.idProfil = :idProfil");
						q.setParameter("idProfil", idProfil);
						if (!q.getResultList().isEmpty()) {
							dipl = (Diplome) q.getSingleResult();
							if (dipl != null) {
								dipl.setAnneeDebut(diplDTO.getAnneeDebut());
								dipl.setAnneFin(diplDTO.getAnneFin());
								dipl.setLibelle(diplDTO.getLibelle());
								dipl.setLieu(diplDTO.getLieu());
								dipls.add(dipl);
								em.merge(dipl);
							}
						} else {
							dipl = new Diplome();
							dipl.setProfil(em.find(Profil.class, nouvelUtilisateur.getProfil().getIdProfil()));
							dipl.setLibelle(diplDTO.getLibelle());
							dipl.setAnneeDebut(diplDTO.getAnneeDebut());
							dipl.setAnneFin(diplDTO.getAnneFin());
							dipls.add(dipl);
							em.persist(dipl);
						}
					}
				}
			}
			profil.setDiplomes(dipls);

			// les experiences
			List<Experience> exps = new ArrayList<Experience>();
			if (nouvelUtilisateur.getProfil().getExperiences() != null
					&& !nouvelUtilisateur.getProfil().getExperiences().isEmpty()) {
				Experience exp = new Experience();
				for (ExperienceDTO expDTO : nouvelUtilisateur.getProfil().getExperiences()) {
					if (expDTO.getAnneeDebut() != null && expDTO.getAnneFin() != null && expDTO.getEntreprise() != null
							&& expDTO.getDescription() != null && !"".equals(expDTO.getDescription())
							&& expDTO.getLieu() != null && !"".equals(expDTO.getLieu()) && expDTO.getPoste() != null
							&& !"".equals(expDTO.getPoste())) {
						Query q = em.createQuery("select e from Experience e where e.entreprise = '"
								+ expDTO.getEntreprise() + "' and e.profil.idProfil = :idProfil");
						q.setParameter("idProfil", idProfil);
						if (!q.getResultList().isEmpty()) {
							exp = (Experience) q.getSingleResult();
							if (exp != null) {
								exp.setDescription(expDTO.getDescription());
								exp.setAnneeDebut(expDTO.getAnneeDebut());
								exp.setAnneFin(expDTO.getAnneFin());
								exp.setEntreprise(expDTO.getEntreprise());
								exp.setLieu(expDTO.getLieu());
								exp.setPoste(expDTO.getPoste());
								exp.setRegion(expDTO.getRegion());
								exp.setPays(expDTO.getPays());
								exps.add(exp);
								em.merge(exp);
							}
						} else {
							exp = new Experience();
							exp.setProfil(em.find(Profil.class, nouvelUtilisateur.getProfil().getIdProfil()));
							exp.setDescription(expDTO.getDescription());
							exp.setAnneeDebut(expDTO.getAnneeDebut());
							exp.setAnneFin(expDTO.getAnneFin());
							exp.setEntreprise(expDTO.getEntreprise());
							exp.setLieu(expDTO.getLieu());
							exp.setPoste(expDTO.getPoste());
							exp.setRegion(expDTO.getRegion());
							exp.setPays(expDTO.getPays());
							exps.add(exp);
							em.persist(exp);
						}
					}
				}
			}
			profil.setExperiences(exps);
			System.out.println("CENTRE INTERETS "+ nouvelUtilisateur.getProfil().getCentreInteret());
			profil.setCentreInteret(nouvelUtilisateur.getProfil().getCentreInteret());
			// profil.setCursus(nouvelUtilisateur.getProfil().getCursus());
			utilisateurMAJ.setProfil(profil);
		}

		em.persist(utilisateurMAJ);

		return MappingToDTO.utilisateurToUtilisateurDTO(utilisateurMAJ);

	}

	@Override
	public void supprimer(UtilisateurDTO obj) {
		// TODO Auto-generated method stub

	}
}
