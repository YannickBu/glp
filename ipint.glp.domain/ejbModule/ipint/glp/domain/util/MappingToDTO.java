package ipint.glp.domain.util;

import java.util.ArrayList;

import ipint.glp.api.DTO.ArticleDTO;
import ipint.glp.api.DTO.CompetenceDTO;
import ipint.glp.api.DTO.DiplomeDTO;
import ipint.glp.api.DTO.ExperienceDTO;
import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.PaysDTO;
import ipint.glp.api.DTO.ProfilDTO;
import ipint.glp.api.DTO.RegionDTO;
import ipint.glp.api.DTO.ReseauSocialDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.DTO.UtilisateurEnAttenteDTO;
import ipint.glp.api.DTO.VilleDTO;
import ipint.glp.api.exception.InformationManquanteException;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.exception.UtilisateurEnAttenteInconnuException;
import ipint.glp.domain.entity.Article;
import ipint.glp.domain.entity.Competence;
import ipint.glp.domain.entity.Diplome;
import ipint.glp.domain.entity.Experience;
import ipint.glp.domain.entity.Groupe;
import ipint.glp.domain.entity.Pays;
import ipint.glp.domain.entity.Profil;
import ipint.glp.domain.entity.Region;
import ipint.glp.domain.entity.ReseauSocial;
import ipint.glp.domain.entity.Utilisateur;
import ipint.glp.domain.entity.UtilisateurEnAttente;
import ipint.glp.domain.entity.Ville;

public class MappingToDTO {

	public static ExperienceDTO experienceToExperienceDTO(Experience exp) throws MetierException {
		if (exp == null) {
			throw new InformationManquanteException(
					"experienceToExperienceDTO : L'experience à mapper en DTO est null");
		}

		ExperienceDTO expDTO = new ExperienceDTO();
		expDTO.setIdExperience(exp.getIdExperience());
		expDTO.setAnneeDebut(exp.getAnneeDebut());
		expDTO.setAnneFin(exp.getAnneFin());
		expDTO.setDescription(exp.getDescription());
		expDTO.setEntreprise(exp.getEntreprise());
		expDTO.setLieu(exp.getLieu());
		expDTO.setPays(exp.getPays());
		expDTO.setRegion(exp.getRegion());
		expDTO.setPoste(exp.getPoste());

		return expDTO;
	}

	public static CompetenceDTO competenceToCompetenceDTO(Competence comp) throws MetierException {
		if (comp == null) {
			throw new InformationManquanteException(
					"competenceToCompetenceDTO : La competence à mapper en DTO est null");
		}

		CompetenceDTO compDTO = new CompetenceDTO();
		compDTO.setIdCompetence(comp.getIdCompetence());
		compDTO.setLibelle(comp.getLibelle());
		compDTO.setNote(comp.getNote());

		return compDTO;
	}

	public static DiplomeDTO diplomeToDiplomeDTO(Diplome dipl) throws MetierException {
		if (dipl == null) {
			throw new InformationManquanteException("diplomeToDiplomeDTO : Le diplome à mapper en DTO est null");
		}

		DiplomeDTO diplDTO = new DiplomeDTO();
		diplDTO.setIdDiplome(dipl.getIdDiplome());
		diplDTO.setAnneeDebut(dipl.getAnneeDebut());
		diplDTO.setAnneFin(dipl.getAnneFin());
		diplDTO.setLibelle(dipl.getLibelle());
		diplDTO.setLieu(dipl.getLieu());

		return diplDTO;
	}
	
	public static ReseauSocialDTO reseauToReseauDTO(ReseauSocial reseau) throws MetierException {
		if (reseau == null) {
			throw new InformationManquanteException("reseauToReseauDTO : Le éseau social à mapper en DTO est null");
		}

		ReseauSocialDTO reseauDTO = new ReseauSocialDTO();
		reseauDTO.setId(reseau.getId());
		reseauDTO.setLien(reseau.getLien());

		return reseauDTO;
	}

	public static ProfilDTO profilToProfilDTO(Profil pro) throws MetierException {
		if (pro == null) {
			throw new InformationManquanteException("profilToProfilDTO : Le profil à mapper en DTO est null");
		}

		ProfilDTO proDTO = new ProfilDTO();
		proDTO.setIdProfil(pro.getIdProfil());
		proDTO.setCentreInteret(pro.getCentreInteret());
		proDTO.setTelephone(pro.getTelephone());
		proDTO.setDiplomePrincipal(pro.getDiplomePrincipal());
		proDTO.setMesAttentes(pro.getMesAttentes());
		proDTO.setSituation(pro.getSituation());
		proDTO.setAnneeDiplome(pro.getAnneeDiplome());

		proDTO.setCompetence(new ArrayList<>());
		if (pro.getCompetence() != null && !pro.getCompetence().isEmpty()) {
			for (Competence exp : pro.getCompetence()) {
				proDTO.getCompetence().add(competenceToCompetenceDTO(exp));
			}
		}
		proDTO.setDiplomes(new ArrayList<>());
		if (pro.getDiplomes() != null && !pro.getDiplomes().isEmpty()) {
			for (Diplome exp : pro.getDiplomes()) {
				proDTO.getDiplomes().add(diplomeToDiplomeDTO(exp));
			}
		}
		proDTO.setExperiences(new ArrayList<>());
		if (pro.getExperiences() != null && !pro.getExperiences().isEmpty()) {
			for (Experience exp : pro.getExperiences()) {
				proDTO.getExperiences().add(experienceToExperienceDTO(exp));
			}
		}
		proDTO.setReseauxSociaux(new ArrayList<ReseauSocialDTO>());
		if (pro.getReseauxSociaux() != null && !pro.getReseauxSociaux().isEmpty()) {
			for (ReseauSocial reseau : pro.getReseauxSociaux()) {
				proDTO.getReseauxSociaux().add(reseauToReseauDTO(reseau));
			}
		}

//		System.out.println(" DIPLOME PRINCIPAL DTO ->>>>>>>>>>>>>>>>>>>>>>>>>><   " + proDTO.getDiplomePrincipal());
		return proDTO;
	}

	public static UtilisateurDTO utilisateurToUtilisateurDTO(Utilisateur util) throws MetierException {
		if (util == null) {
			throw new InformationManquanteException(
					"utilisateurToUtilisateurDTO : L'utilisateur à mapper en DTO est null");
		}

		UtilisateurDTO utilDTO = utilisateurToUtilisateurDTOHorsRelation(util);
		utilDTO.setProfil(profilToProfilDTO(util.getProfil()));
		utilDTO.setGroupePrincipal(groupeToGroupeDTOLazy(util.getGroupePrincipal()));

		utilDTO.setArticles(new ArrayList<>());
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
		utilDTO.setGroupesAnimes(new ArrayList<>());
		if (util.getGroupesAnimes() != null && !util.getGroupesAnimes().isEmpty()) {
			for (Groupe grp : util.getGroupesAnimes()) {
				utilDTO.getGroupesAnimes().add(groupeToGroupeDTOLazy(grp));
			}
		}

		return utilDTO;
	}

	public static GroupeDTO groupeToGroupeDTO(Groupe grp) throws MetierException {
		if (grp == null) {
			throw new InformationManquanteException("groupeToGroupeDTO : Le groupe à mapper en DTO est null");
		}

		GroupeDTO grpDTO = groupeToGroupeDTOHorsRelation(grp);
		grpDTO.setUtilisateurResponsable(utilisateurToUtilisateurDTOLazy(grp.getUtilisateurResponsable()));

		grpDTO.setUtilisateurs(new ArrayList<>());
		if (grp.getUtilisateurs() != null && !grp.getUtilisateurs().isEmpty()) {
			for (Utilisateur util : grp.getUtilisateurs()) {
				grpDTO.getUtilisateurs().add(utilisateurToUtilisateurDTOLazy(util));
			}
		}
		grpDTO.setArticles(new ArrayList<>());
		if (grp.getArticles() != null && !grp.getArticles().isEmpty()) {
			for (Article art : grp.getArticles()) {
				grpDTO.getArticles().add(articleTOArticleDTOLazy(art));
			}
		}
		grpDTO.setAnimateurs(new ArrayList<>());
		if (grp.getAnimateurs() != null && !grp.getAnimateurs().isEmpty()) {
			for (Utilisateur util : grp.getAnimateurs()) {
				grpDTO.getAnimateurs().add(utilisateurToUtilisateurDTOLazy(util));
			}
		}

		return grpDTO;
	}

	public static ArticleDTO articleToArticleDTO(Article art) throws MetierException {
		if (art == null) {
			throw new InformationManquanteException("articleToArticleDTO : L'article à mapper en DTO est null");
		}

		ArticleDTO artDTO = articleToArticleDTOHorsRelation(art);
		if (art.getUtilisateur() != null) {
			artDTO.setUtilisateur(utilisateurToUtilisateurDTOLazy(art.getUtilisateur()));
		}
		if (art.getGroupe() != null) {
			artDTO.setGroupe(groupeToGroupeDTOLazy(art.getGroupe()));
		}
		// artDTO.setGroupes(new ArrayList<>());
		// if (art.getGroupes() != null && !art.getGroupes().isEmpty()) {
		// for (Groupe grp : art.getGroupes()) {
		// artDTO.getGroupes().add(groupeToGroupeDTOLazy(grp));
		// }
		// }

		return artDTO;
	}

	public static UtilisateurEnAttenteDTO utilisateurEnAttenteToUtilisateurEnAttenteDTO(
			UtilisateurEnAttente utilisateurEnAttente) throws MetierException {
		if (utilisateurEnAttente == null) {
			throw new UtilisateurEnAttenteInconnuException(
					"utilisateurEnAttenteToUtilisateurEnAttenteDTO : L'utilisateurEnAttente à mapper en DTO est null");
		}
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

	private static UtilisateurDTO utilisateurToUtilisateurDTOLazy(Utilisateur util) throws MetierException {
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
		util.setGroupesAnimes(new ArrayList<>());
		if (util.getGroupesAnimes() != null && !util.getGroupesAnimes().isEmpty()) {
			for (Groupe grp : util.getGroupesAnimes()) {
				utilDTO.getGroupesAnimes().add(groupeToGroupeDTOHorsRelation(grp));
			}
		}

		return utilDTO;
	}

	private static ArticleDTO articleTOArticleDTOLazy(Article art) throws MetierException {
		if (art == null) {
			return null;
		}

		ArticleDTO artDTO = articleToArticleDTOHorsRelation(art);
		if (art.getUtilisateur() != null) {
			artDTO.setUtilisateur(utilisateurToUtilisateurDTOHorsRelation(art.getUtilisateur()));
		}
		if (art.getGroupe() != null) {
			artDTO.setGroupe(groupeToGroupeDTOHorsRelation(art.getGroupe()));
		}

		// artDTO.setGroupes(new ArrayList<>());
		// if (art.getGroupes() != null) {
		// for (Groupe grp : art.getGroupes()) {
		// artDTO.getGroupes().add(groupeToGroupeDTOHorsRelation(grp));
		// }
		// }

		return artDTO;
	}

	private static GroupeDTO groupeToGroupeDTOLazy(Groupe grp) throws MetierException {
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
		grpDTO.setAnimateurs(new ArrayList<>());
		if (grp.getAnimateurs() != null && !grp.getAnimateurs().isEmpty()) {
			for (Utilisateur util : grp.getAnimateurs()) {
				grpDTO.getAnimateurs().add(utilisateurToUtilisateurDTOHorsRelation(util));
			}
		}

		return grpDTO;
	}
	
	public static PaysDTO paysToPaysDTO(Pays pays) throws MetierException {
		if (pays == null) {
			return null;
		}

		PaysDTO paysDto = new PaysDTO();
		paysDto.setCode(pays.getCode());
		paysDto.setNom(pays.getNom());

		return paysDto;
	}

	public static RegionDTO regionToRegionDTO(Region r) {
		if (r == null) {
			return null;
		}

		RegionDTO regionDto = new RegionDTO();
		regionDto.setNom(r.getNom());
		regionDto.setPays(r.getPays());

		return regionDto;
	}

	public static VilleDTO villeToVilleDTO(Ville v) {
		if (v == null) {
			return null;
		}

		VilleDTO villeDto = new VilleDTO();
		villeDto.setNom(v.getNom());
		villeDto.setRegion(v.getRegion());
		villeDto.setPays(v.getPays());

		return villeDto;
	}

	private static UtilisateurDTO utilisateurToUtilisateurDTOHorsRelation(Utilisateur util) throws MetierException {
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

	private static ArticleDTO articleToArticleDTOHorsRelation(Article art) throws MetierException {
		if (art == null) {
			return null;
		}

		ArticleDTO artDTO = new ArticleDTO();
		artDTO.setIdArticle(art.getIdArticle());
		artDTO.setContenu(art.getContenu());
		artDTO.setTitre(art.getTitre());
		artDTO.setDatePublication(art.getDatePublication());

		return artDTO;
	}

	private static GroupeDTO groupeToGroupeDTOHorsRelation(Groupe grp) throws MetierException {
		if (grp == null) {
			return null;
		}

		GroupeDTO grpDTO = new GroupeDTO();
		grpDTO.setIdGroupe(grp.getIdGroupe());
		grpDTO.setDescription(grp.getDescription());
		grpDTO.setNomGroupe(grp.getNomGroupe());

		return grpDTO;
	}

}
