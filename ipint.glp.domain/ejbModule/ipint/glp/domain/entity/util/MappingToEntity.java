package ipint.glp.domain.entity.util;

import ipint.glp.api.DTO.ArticleDTO;
import ipint.glp.api.DTO.ExperienceDTO;
import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.ProfilDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.domain.dao.DAOFactory;
import ipint.glp.domain.entity.Article;
import ipint.glp.domain.entity.Experience;
import ipint.glp.domain.entity.Groupe;
import ipint.glp.domain.entity.Profil;
import ipint.glp.domain.entity.Utilisateur;

public class MappingToEntity {

	public static Experience experienceDTOToExperience(ExperienceDTO expDTO){
		Experience exp = new Experience();
		exp.setIdExperience(expDTO.getIdExperience());
		return DAOFactory.getExperienceDAO().find(exp);
	}
	
	public static Profil profilDTOToProfil(ProfilDTO proDTO){
		Profil pro = new Profil();
		pro.setIdProfil(proDTO.getIdProfil());
		return DAOFactory.getProfilDAO().find(pro);
	}

	public static Utilisateur utilisateurDTOToUtilisateur(UtilisateurDTO utilDTO){
		Utilisateur util = new Utilisateur();
		util.setIdUtilisateur(utilDTO.getIdUtilisateur());
		util.setEmail(util.getEmail());
		return DAOFactory.getUtilisateurDAO().find(util);
	}
	
	public static Groupe groupeDTOToGroupe(GroupeDTO grpDTO){
		Groupe grp = new Groupe();
		grp.setIdGroupe(grpDTO.getIdGroupe());
		grp.setNomGroupe(grpDTO.getNomGroupe());
		return DAOFactory.getGroupeDAO().find(grp);
	}
	
	public static Article articleDTOToArticle(ArticleDTO artDTO){
		Article art = new Article();
		art.setIdArticle(artDTO.getIdArticle());
		return DAOFactory.getArticleDAO().find(art);
	}
}
