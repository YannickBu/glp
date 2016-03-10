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
import ipint.glp.api.exception.UtilisateurExistantException;
import ipint.glp.api.exception.UtilisateurInconnuException;
import ipint.glp.api.itf.UtilisateurService;
import ipint.glp.domain.entity.Competence;
import ipint.glp.domain.entity.Diplome;
import ipint.glp.domain.entity.Experience;
import ipint.glp.domain.entity.Groupe;
import ipint.glp.domain.entity.Profil;
import ipint.glp.domain.entity.Utilisateur;
import ipint.glp.domain.entity.UtilisateurGroupes;
import ipint.glp.domain.util.GenererMotDePasse;
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
			throw new InformationManquanteException(
					"UtilisateurImpl.creer : " + utilisateurDTO.toString() + " n'a pas d'email");
		}
		if (utilisateurDTO.getStatut() == null) {
			throw new InformationManquanteException(
					"UtilisateurImpl.creer : " + utilisateurDTO.toString() + " n'a pas de statut");
		}
		if (utilisateurDTO.getPassword() == null) {
			throw new InformationManquanteException(
					"UtilisateurImpl.creer : " + utilisateurDTO.toString() + " n'a pas de password");
		}
		if (utilisateurDTO.getNom() == null) {
			throw new InformationManquanteException(
					"UtilisateurImpl.creer : " + utilisateurDTO.toString() + " n'a pas de nom");
		}
		if (utilisateurDTO.getPrenom() == null) {
			throw new InformationManquanteException(
					"UtilisateurImpl.creer : " + utilisateurDTO.toString() + " n'a pas de prenom");
		}
		if (utilisateurDTO.getGroupePrincipal() == null) {
			throw new InformationManquanteException(
					"UtilisateurImpl.creer : " + utilisateurDTO.toString() + " n'a pas de groupe principal");
		}

		// Gestion utilisateur existant
		try {
			if (trouver(utilisateurDTO) != null) {
				throw new UtilisateurExistantException(
						"UtilisateurImpl.creer : " + utilisateurDTO.toString() + " existe déjà");
			}
		} catch (UtilisateurInconnuException e) {

		}

		// Lorsque lutilisateur vient du CAS, il faut lui generer un mdp
		if ("".equals(utilisateurDTO.getPassword())) {
			GenererMotDePasse passFacto = new GenererMotDePasse(10);
			utilisateurDTO.setPassword(passFacto.nextString());
		}

		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setEmail(utilisateurDTO.getEmail());
		utilisateur.setNom(utilisateurDTO.getNom());
		utilisateur.setPrenom(utilisateurDTO.getPrenom());
		utilisateur.setPassword(utilisateurDTO.getPassword());
		utilisateur.setStatut(utilisateurDTO.getStatut());

		// Gestion liste des groupes, AUCUN A LA CREATION DUN UTILISATEUR (on
		// ajoute apres le groupe principal dans cette liste)

		List<Groupe> lesGroupes = new ArrayList<Groupe>();
		// if (utilisateurDTO.getGroupes() != null &&
		// !utilisateurDTO.getGroupes().isEmpty()) {
		// for (GroupeDTO groupeDTO : utilisateurDTO.getGroupes()) {
		// Groupe grp = null;
		// if (groupeDTO.getIdGroupe() != null) {
		// grp = em.find(Groupe.class, groupeDTO.getIdGroupe());
		// if(grp==null){
		// throw new GroupeInconnuException("UtilisateurImpl.creer :
		// "+groupeDTO.toString()+" n'existe pas pour cet id");
		// }
		// } else if (groupeDTO.getNomGroupe() != null) {
		// Query q = em.createQuery(
		// "select g from Groupe g where g.nomGroupe = '" +
		// groupeDTO.getNomGroupe() + "'");
		// try{
		// grp = (Groupe) q.getSingleResult();
		// }catch(NoResultException e){
		// throw new GroupeInconnuException("UtilisateurImpl.creer :
		// "+groupeDTO.toString()+" n'existe pas pour cet nom de groupe");
		// }
		// }
		// if (grp != null) {
		// lesGroupes.add(grp);
		// grp.getUtilisateurs().add(utilisateur);
		// em.persist(grp);
		// }
		// }
		// }
		utilisateur.setGroupes(lesGroupes);

		// Groupe principal

		GroupeDTO groupeDTOm = utilisateurDTO.getGroupePrincipal();
		Groupe grpm = null;
		if (groupeDTOm.getIdGroupe() != null) {
			grpm = em.find(Groupe.class, groupeDTOm.getIdGroupe());
			if (grpm == null) {
				throw new GroupeInconnuException(
						"UtilisateurImpl.creer : Le groupe d'id=" + groupeDTOm.getIdGroupe() + " n'existe pas");
			}
		} else if (groupeDTOm.getNomGroupe() != null) {
			Query q = em.createQuery("select g from Groupe g where g.nomGroupe = :nomgroupe");
			q.setParameter("nomgroupe", groupeDTOm.getNomGroupe());
			try {
				grpm = (Groupe) q.getSingleResult();
			} catch (NoResultException e) {
				throw new GroupeInconnuException(
						"UtilisateurImpl.creer : Le groupe ayant pour nom=" + grpm.getNomGroupe() + " n'existe pas");
			}
		}
		if (grpm != null) {
			if (!grpm.getUtilisateurs().contains(utilisateur)) {
				grpm.getUtilisateurs().add(utilisateur);
				em.persist(grpm);
			}
		}
		utilisateur.setGroupePrincipal(grpm);
		utilisateur.getGroupes().add(grpm);

		// Profil profil = em.find(Profil.class,
		// utilisateurDTO.getProfil().getIdProfil());
		// utilisateur.setProfil(profil);

		// Gestion de la liste des groupes gérés par l'utilisateur, AUCUN A LA
		// CREATION DUN UTILISATEUR

		List<Groupe> lesGroupesGeres = new ArrayList<Groupe>();
		// if (utilisateurDTO.getGroupesGeres() != null &&
		// !utilisateurDTO.getGroupesGeres().isEmpty()) {
		// for (GroupeDTO groupeDTO : utilisateurDTO.getGroupesGeres()) {
		// Groupe grp = new Groupe();
		// if (groupeDTO.getIdGroupe() != null) {
		// grp = em.find(Groupe.class, groupeDTO.getIdGroupe());
		// if (grp == null) {
		// throw new GroupeInconnuException(
		// "UtilisateurImpl.creer :" + groupeDTO.toString() + " n'existe pas
		// pour cet id");
		// }
		// } else if (groupeDTO.getNomGroupe() != null) {
		// Query q = em.createQuery(
		// "select g from Groupe g where g.nomGroupe = '" +
		// groupeDTO.getNomGroupe() + "'");
		// try {
		// grp = (Groupe) q.getSingleResult();
		// } catch (NoResultException e) {
		// throw new GroupeInconnuException("UtilisateurImpl.creer :" +
		// groupeDTO.toString()
		// + " n'existe pas pour ce nom de groupe");
		// }
		// }
		// if (grp != null) {
		// lesGroupesGeres.add(grp);
		// grp.setUtilisateurResponsable(utilisateur);
		// em.persist(grp);
		// }
		// }
		// }
		utilisateur.setGroupesGeres(lesGroupesGeres);

		// Gestion de la liste groupes animés, AUCUN A LA CREATION DUN
		// UTILISATEUR

		List<Groupe> lesGroupesAnimes = new ArrayList<>();
		// if (utilisateurDTO.getGroupesAnimes() != null &&
		// !utilisateurDTO.getGroupesGeres().isEmpty()) {
		// for (GroupeDTO groupeDTO : utilisateurDTO.getGroupesAnimes()) {
		// Groupe grp = new Groupe();
		// if (groupeDTO.getIdGroupe() != null) {
		// grp = em.find(Groupe.class, groupeDTO.getIdGroupe());
		// if (grp == null) {
		// throw new GroupeInconnuException(
		// "UtilisateurImpl.creer :" + groupeDTO.toString() + " n'existe pas
		// pour cet id");
		// }
		// } else if (groupeDTO.getNomGroupe() != null) {
		// Query q = em.createQuery(
		// "select g from Groupe g where g.nomGroupe = '" +
		// groupeDTO.getNomGroupe() + "'");
		// try {
		// grp = (Groupe) q.getSingleResult();
		// } catch (NoResultException e) {
		// throw new GroupeInconnuException("UtilisateurImpl.creer :" +
		// groupeDTO.toString()
		// + " n'existe pas pour ce nom de groupe");
		// }
		// }
		// if (grp != null) {
		// lesGroupesAnimes.add(grp);
		// if (!grp.getAnimateurs().contains(utilisateur)) {
		// grp.getAnimateurs().add(utilisateur);
		// em.persist(grp);
		// }
		// }
		// }
		// }
		utilisateur.setGroupesAnimes(lesGroupesAnimes);

		// Gestion profil

		Profil pro = new Profil();
		if (utilisateurDTO.getProfil() != null) {

			// Champs non assoc
			pro.setCentreInteret(utilisateurDTO.getProfil().getCentreInteret());
			pro.setTelephone(utilisateurDTO.getProfil().getTelephone());
			pro.setDiplomePrincipal(utilisateurDTO.getProfil().getDiplomePrincipal());
			pro.setAnneeDiplome(utilisateurDTO.getProfil().getAnneeDiplome());
			pro.setMesAttentes(utilisateurDTO.getProfil().getMesAttentes());
			pro.setSituation("Nouvel arrivant");

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
		utilGrp.setGroupe(utilisateur.getStatut().name().toLowerCase());
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
			Query q = em.createQuery("select u from Utilisateur u where u.email = :email");
			q.setParameter("email", utilisateurDTO.getEmail());
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
		// System.out.println(
		// "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++MDP : " +
		// nouvelUtilisateur.getPassword());

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

			// Le diplome principal
			profil.setDiplomePrincipal(nouvelUtilisateur.getProfil().getDiplomePrincipal());
			profil.setAnneeDiplome((nouvelUtilisateur.getProfil().getAnneeDiplome()));

			profil.setMesAttentes(nouvelUtilisateur.getProfil().getMesAttentes());
			profil.setSituation(nouvelUtilisateur.getProfil().getSituation());

			// les competences
			List<Competence> comps = new ArrayList<Competence>();
			List<Competence> oldComps = new ArrayList<Competence>();
			oldComps = em.find(Utilisateur.class, nouvelUtilisateur.getIdUtilisateur()).getProfil().getCompetence();
			if (nouvelUtilisateur.getProfil().getCompetence() != null
					&& !nouvelUtilisateur.getProfil().getCompetence().isEmpty()) {
				Competence comp = new Competence();
				for (CompetenceDTO compDTO : nouvelUtilisateur.getProfil().getCompetence()) {
					if (compDTO.getLibelle() != null && !"".equals(compDTO.getLibelle()) && compDTO.getNote() != 0) {
						Query q = em.createQuery(
								"select c from Competence c where c.libelle = :libelle and c.profil.idProfil = :idProfil");
						q.setParameter("idProfil", idProfil);
						q.setParameter("libelle", compDTO.getLibelle());
						if (!q.getResultList().isEmpty()) {
							comp = (Competence) q.getSingleResult();
							if (comp != null) {
								comp.setNote(compDTO.getNote());
								if (!comps.contains(comp)) {
									comps.add(comp);
								}
								em.merge(comp);
							}
						} else {
							comp = new Competence();
							comp.setProfil(em.find(Profil.class, nouvelUtilisateur.getProfil().getIdProfil()));
							comp.setLibelle(compDTO.getLibelle());
							comp.setNote(compDTO.getNote());
							if (comps.contains(comp)) {
								comps.remove(comp);
							}
							comps.add(comp);
							em.persist(comp);
						}
					}
				}
			}
			for (Competence c : oldComps) {
				if (!comps.contains(c)) {
					em.remove(c);
				}
			}
			profil.setCompetence(comps);

			// les diplomes
			List<Diplome> dipls = new ArrayList<Diplome>();
			List<Diplome> oldDipls = new ArrayList<Diplome>();
			if (nouvelUtilisateur.getProfil().getDiplomes() != null
					&& !nouvelUtilisateur.getProfil().getDiplomes().isEmpty()) {
				Diplome dipl = new Diplome();
				for (DiplomeDTO diplDTO : nouvelUtilisateur.getProfil().getDiplomes()) {
					if (diplDTO.getLibelle() != null && !"".equals(diplDTO.getLibelle())
							&& diplDTO.getAnneeDebut() != null && diplDTO.getAnneFin() != null) {
						Query q = em.createQuery(
								"select d from Diplome d where d.libelle = :libelle and d.profil.idProfil = :idProfil");
						q.setParameter("idProfil", idProfil);
						q.setParameter("libelle", diplDTO.getLibelle());
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
			for (Diplome d : oldDipls) {
				if (!dipls.contains(d)) {
					em.remove(d);
				}
			}
			profil.setDiplomes(dipls);

			// les experiences
			List<Experience> exps = new ArrayList<Experience>();
			List<Experience> oldExps = new ArrayList<Experience>();
			if (nouvelUtilisateur.getProfil().getExperiences() != null
					&& !nouvelUtilisateur.getProfil().getExperiences().isEmpty()) {
				Experience exp = new Experience();
				for (ExperienceDTO expDTO : nouvelUtilisateur.getProfil().getExperiences()) {
					if (expDTO.getAnneeDebut() != null && expDTO.getAnneFin() != null && expDTO.getEntreprise() != null
							&& expDTO.getDescription() != null && !"".equals(expDTO.getDescription())
							&& expDTO.getLieu() != null && !"".equals(expDTO.getLieu()) && expDTO.getPoste() != null
							&& !"".equals(expDTO.getPoste())) {
						Query q = em.createQuery(
								"select e from Experience e where e.entreprise = :entreprise and e.profil.idProfil = :idProfil");
						q.setParameter("idProfil", idProfil);
						q.setParameter("entreprise", expDTO.getEntreprise());
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
			for (Experience e : oldExps) {
				if (!exps.contains(e)) {
					em.remove(e);
				}
			}
			profil.setExperiences(exps);

			// System.out.println("CENTRE INTERETS " +
			// nouvelUtilisateur.getProfil().getCentreInteret());
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

	@SuppressWarnings("unchecked")
	@Override
	public List<UtilisateurDTO> listerParType(String type) throws MetierException {
		Query q;
		List<Utilisateur> utilisateurs;
		List<UtilisateurDTO> utilisateursDTO = new ArrayList<>();
		Statut statut = Statut.valueOf(type);
		// SELECT * FROM l1nk_plh.UTILISATEUR WHERE email IN (SELECT email from
		// l1nk_plh.UTILISATEURGROUPES where groupe = 'personnel');
		q = em.createQuery(
				"select u from Utilisateur u where u.email in (select s.email from UtilisateurGroupes s where s.groupe = '"
						+ statut + "')");
		utilisateurs = q.getResultList();

		for (Utilisateur utilisateur : utilisateurs) {
			utilisateursDTO.add(MappingToDTO.utilisateurToUtilisateurDTO(utilisateur));
		}

		return utilisateursDTO;
	}
}
