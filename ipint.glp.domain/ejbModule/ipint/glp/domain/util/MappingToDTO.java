package ipint.glp.domain.util;

import java.util.ArrayList;

import ipint.glp.api.DTO.ArticleDTO;
import ipint.glp.api.DTO.CompetenceDTO;
import ipint.glp.api.DTO.DiplomeDTO;
import ipint.glp.api.DTO.ExperienceDTO;
import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.ProfilDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.DTO.UtilisateurEnAttenteDTO;
import ipint.glp.domain.entity.Article;
import ipint.glp.domain.entity.Competence;
import ipint.glp.domain.entity.Diplome;
import ipint.glp.domain.entity.Experience;
import ipint.glp.domain.entity.Groupe;
import ipint.glp.domain.entity.Profil;
import ipint.glp.domain.entity.Utilisateur;
import ipint.glp.domain.entity.UtilisateurEnAttente;

public class MappingToDTO {

	public static ExperienceDTO experienceToExperienceDTO(Experience exp) {
		if (exp == null) {
			return null;
		}

		ExperienceDTO expDTO = new ExperienceDTO();
		expDTO.setIdExperience(exp.getIdExperience());
		expDTO.setAnneeDebut(exp.getAnneeDebut());
		expDTO.setAnneFin(exp.getAnneFin());
		expDTO.setDescription(exp.getDescription());
		expDTO.setEntreprise(exp.getEntreprise());
		expDTO.setLieu(exp.getLieu());
		expDTO.setPoste(exp.getPoste());

		return expDTO;
	}
	
	public static CompetenceDTO competenceToCompetenceDTO(Competence comp) {
		if (comp == null) {
			return null;
		}

		CompetenceDTO compDTO = new CompetenceDTO();
		compDTO.setIdCompetence(comp.getIdCompetence());
		compDTO.setLibelle(comp.getLibelle());
		compDTO.setNote(comp.getNote());
		return compDTO;
	}
	
	public static DiplomeDTO diplomeToDiplomeDTO(Diplome dipl) {
		if (dipl == null) {
			return null;
		}

		DiplomeDTO diplDTO = new DiplomeDTO();
		diplDTO.setIdDiplome(dipl.getIdDiplome());
		diplDTO.setAnneeDebut(dipl.getAnneeDebut());
		diplDTO.setAnneFin(dipl.getAnneFin());
		diplDTO.setLibelle(dipl.getLibelle());

		return diplDTO;
	}

	public static ProfilDTO profilToProfilDTO(Profil pro) {
		if (pro == null) {
			return null;
		}

		ProfilDTO proDTO = new ProfilDTO();
		proDTO.setIdProfil(pro.getIdProfil());
		proDTO.setCentreInteret(pro.getCentreInteret());
		//proDTO.setCompetence(pro.getCompetence());
		proDTO.setCompetence(new ArrayList<>());
		if (pro.getCompetence() != null && !pro.getCompetence().isEmpty()) {
			for (Competence exp : pro.getCompetence()) {
				proDTO.getCompetence().add(competenceToCompetenceDTO(exp));
			}
		}
		//proDTO.setCursus(pro.getCursus());
		//roDTO.setDiplomes(pro.getDiplomes());
		proDTO.setDiplomes(new ArrayList<>());
		if (pro.getDiplomes() != null && !pro.getDiplomes().isEmpty()) {
			for (Diplome exp : pro.getDiplomes()) {
				proDTO.getDiplomes().add(diplomeToDiplomeDTO(exp));
			}
		}
		proDTO.setTelephone(pro.getTelephone());
		proDTO.setExperiences(new ArrayList<>());
		if (pro.getExperiences() != null && !pro.getExperiences().isEmpty()) {
			for (Experience exp : pro.getExperiences()) {
				proDTO.getExperiences().add(experienceToExperienceDTO(exp));
			}
		}

		return proDTO;
	}

	public static UtilisateurDTO utilisateurToUtilisateurDTO(Utilisateur util) {
		if (util == null) {
			return null;
		}

		UtilisateurDTO utilDTO = utilisateurToUtilisateurDTOHorsRelation(util);
		utilDTO.setProfil(profilToProfilDTO(util.getProfil()));
		utilDTO.setArticles(new ArrayList<>());
		utilDTO.setGroupePrincipal(groupeToGroupeDTO(util.getGroupePrincipal()));
		if (util.getArticles() != null && !util.getArticles().isEmpty()) {
			for (Article art : util.getArticles()) {
				utilDTO.getArticles().add(articleTOArticleDTOLazy(art));
			}
		}
		utilDTO.setGroupes(new ArrayList<>());
		if (util.getGroupes() != null && !util.getGroupes().isEmpty()) {
			for (Groupe grp : util.getGroupes()) {
				utilDTO.getGroupes().add(groupeToGroupeDTOLazy(grp));
			}
		}
		utilDTO.setGroupesGeres(new ArrayList<>());
		if (util.getGroupesGeres() != null && !util.getGroupesGeres().isEmpty()) {
			for (Groupe grp : util.getGroupesGeres()) {
				utilDTO.getGroupesGeres().add(groupeToGroupeDTOLazy(grp));
			}
		}

		return utilDTO;
	}

	public static GroupeDTO groupeToGroupeDTO(Groupe grp) {
		if (grp == null) {
			return null;
		}

		GroupeDTO grpDTO = groupeToGroupeDTOHorsRelation(grp);
		grpDTO.setUtilisateurResponsable(utilisateurToUtilisateurDTOLazy(grp.getUtilisateurResponsable()));
		grpDTO.setUtilisateurs(new ArrayList<>());
		if (grp.getUtilisateurs() != null && !grp.getUtilisateurs().isEmpty()) {
			for (Utilisateur util : grp.getUtilisateurs()) {
				grpDTO.getUtilisateurs().add(utilisateurToUtilisateurDTOLazy(util));
			}
		}
		grp.setArticles(new ArrayList<>());
		if (grp.getArticles() != null && !grp.getArticles().isEmpty()) {
			for (Article art : grp.getArticles()) {
				grpDTO.getArticles().add(articleTOArticleDTOLazy(art));
			}
		}

		return grpDTO;
	}

	public static ArticleDTO articleToArticleDTO(Article art) {
		if (art == null) {
			return null;
		}

		ArticleDTO artDTO = articleToArticleDTOHorsRelation(art);
		if (art.getUtilisateur() != null) {
			artDTO.setUtilisateur(utilisateurToUtilisateurDTOLazy(art.getUtilisateur()));
		}
		if (art.getGroupe() != null) {
			artDTO.setGroupe(groupeToGroupeDTO(art.getGroupe()));
		}
		artDTO.setGroupes(new ArrayList<>());
		if (art.getGroupes() != null && !art.getGroupes().isEmpty()) {
			for (Groupe grp : art.getGroupes()) {
				artDTO.getGroupes().add(groupeToGroupeDTOLazy(grp));
			}
		}

		return artDTO;
	}

	private static UtilisateurDTO utilisateurToUtilisateurDTOLazy(Utilisateur util) {
		if (util == null) {
			return null;
		}

		UtilisateurDTO utilDTO = utilisateurToUtilisateurDTOHorsRelation(util);
		utilDTO.setArticles(new ArrayList<>());
		if (util.getArticles() != null && !util.getArticles().isEmpty()) {
			for (Article art : util.getArticles()) {
				utilDTO.getArticles().add(articleToArticleDTOHorsRelation(art));
			}
		}
		util.setGroupes(new ArrayList<>());
		if (util.getGroupes() != null && !util.getGroupes().isEmpty()) {
			for (Groupe grp : util.getGroupes()) {
				utilDTO.getGroupes().add(groupeToGroupeDTOHorsRelation(grp));
			}
		}
		util.setGroupesGeres(new ArrayList<>());
		if (util.getGroupesGeres() != null && !util.getGroupesGeres().isEmpty()) {
			for (Groupe grp : util.getGroupesGeres()) {
				utilDTO.getGroupesGeres().add(groupeToGroupeDTOHorsRelation(grp));
			}
		}

		return utilDTO;
	}

	private static ArticleDTO articleTOArticleDTOLazy(Article art) {
		if (art == null) {
			return null;
		}

		ArticleDTO artDTO = articleToArticleDTOHorsRelation(art);
		if (art.getUtilisateur() != null) {
			artDTO.setUtilisateur(utilisateurToUtilisateurDTOHorsRelation(art.getUtilisateur()));
		}
		if (art.getGroupe() != null) {
			artDTO.setGroupe(groupeToGroupeDTO(art.getGroupe()));
		}
		
		artDTO.setGroupes(new ArrayList<>());
		if (art.getGroupes() != null) {
			for (Groupe grp : art.getGroupes()) {
				artDTO.getGroupes().add(groupeToGroupeDTOHorsRelation(grp));
			}
		}

		return artDTO;
	}

	private static GroupeDTO groupeToGroupeDTOLazy(Groupe grp) {
		if (grp == null) {
			return null;
		}

		GroupeDTO grpDTO = groupeToGroupeDTOHorsRelation(grp);
		if (grp.getUtilisateurResponsable() != null) {
			grpDTO.setUtilisateurResponsable(utilisateurToUtilisateurDTOHorsRelation(grp.getUtilisateurResponsable()));
		}
		grpDTO.setUtilisateurs(new ArrayList<>());
		if (grp.getUtilisateurs() != null && !grp.getUtilisateurs().isEmpty()) {
			for (Utilisateur util : grp.getUtilisateurs()) {
				grpDTO.getUtilisateurs().add(utilisateurToUtilisateurDTOHorsRelation(util));
			}
		}
		grpDTO.setArticles(new ArrayList<>());
		if (grp.getArticles() != null && !grp.getArticles().isEmpty()) {
			for (Article art : grp.getArticles()) {
				grpDTO.getArticles().add(articleToArticleDTOHorsRelation(art));
			}
		}

		return grpDTO;
	}

	private static UtilisateurDTO utilisateurToUtilisateurDTOHorsRelation(Utilisateur util) {
		if (util == null) {
			return null;
		}

		UtilisateurDTO utilDTO = new UtilisateurDTO();
		utilDTO.setIdUtilisateur(util.getIdUtilisateur());
		utilDTO.setEmail(util.getEmail());
		utilDTO.setNom(util.getNom());
		utilDTO.setPrenom(util.getPrenom());
		utilDTO.setStatut(util.getStatut());
		utilDTO.setPassword(util.getPassword());

		return utilDTO;
	}

	private static ArticleDTO articleToArticleDTOHorsRelation(Article art) {
		if (art == null) {
			return null;
		}

		ArticleDTO artDTO = new ArticleDTO();
		artDTO.setIdArticle(art.getIdArticle());
		artDTO.setContenu(art.getContenu());
		artDTO.setDatePublication(art.getDatePublication());

		return artDTO;
	}

	private static GroupeDTO groupeToGroupeDTOHorsRelation(Groupe grp) {
		if (grp == null) {
			return null;
		}

		GroupeDTO grpDTO = new GroupeDTO();
		grpDTO.setIdGroupe(grp.getIdGroupe());
		grpDTO.setDescription(grp.getDescription());
		grpDTO.setNomGroupe(grp.getNomGroupe());

		return grpDTO;
	}

	public static UtilisateurEnAttenteDTO utilisateurEnAttenteToUtilisateurEnAttenteDTO(
			UtilisateurEnAttente utilisateurEnAttente) {
		UtilisateurEnAttenteDTO utilisateurEnAttenteDTO = new UtilisateurEnAttenteDTO();
		utilisateurEnAttenteDTO.setAnneeDiplome(utilisateurEnAttente.getAnneeDiplome());
		utilisateurEnAttenteDTO.setDiplome(utilisateurEnAttente.getDiplome());
		utilisateurEnAttenteDTO.setEmail(utilisateurEnAttente.getEmail());
		utilisateurEnAttenteDTO.setGroupePrincipal(groupeToGroupeDTO(utilisateurEnAttente.getGroupePrincipal()));
		utilisateurEnAttenteDTO.setIdUtilisateurEnAttente(utilisateurEnAttente.getIdUtilisateurEnAttente());
		utilisateurEnAttenteDTO.setNom(utilisateurEnAttente.getNom());
		utilisateurEnAttenteDTO.setPrenom(utilisateurEnAttente.getPrenom());
		utilisateurEnAttenteDTO.setDateNaissance(utilisateurEnAttente.getDateNaissance());
		return utilisateurEnAttenteDTO;

	}

}
