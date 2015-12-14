package ipint.glp.domain.dao;

import ipint.glp.domain.entity.Article;
import ipint.glp.domain.entity.Experience;
import ipint.glp.domain.entity.Groupe;
import ipint.glp.domain.entity.Profil;
import ipint.glp.domain.entity.Utilisateur;

public class DAOFactory {

	public static DAO<Utilisateur> getUtilisateurDAO() {
		return new UtilisateurDAO();
	}

	public static DAO<Profil> getProfilDAO() {
		return new ProfilDAO();
	}

	public static DAO<Experience> getExperienceDAO() {
		return new ExperienceDAO();
	}

	public static DAO<Groupe> getGroupeDAO() {
		return new GroupeDAO();
	}

	public static DAO<Article> getArticleDAO() {
		return new ArticleDAO();
	}

}
