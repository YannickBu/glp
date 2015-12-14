package ipint.glp.domain.dao;

import ipint.glp.domain.entity.Utilisateur;

public class DAOFactory {

	public static DAO<Utilisateur> getUtilisateurDAO(){
		return new UtilisateurDAO();
	}

}
