package ipint.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint.glp.api.DTO.ArticleDTO;
import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.itf.Service;
import ipint.glp.domain.entity.Article;
import ipint.glp.domain.entity.Groupe;
import ipint.glp.domain.entity.Profil;
import ipint.glp.domain.entity.Utilisateur;
import ipint.glp.domain.entity.util.MappingToDTO;

public class UtilisateurImpl implements Service<UtilisateurDTO> {

	@PersistenceContext(unitName = "PU")
	private EntityManager em;

	@Override
	public UtilisateurDTO creer(UtilisateurDTO utilisateurDTO) {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setEmail(utilisateurDTO.getEmail());
		utilisateur.setNom(utilisateurDTO.getNom());
		utilisateur.setPassword(utilisateurDTO.getPassword());
		utilisateur.setStatut(utilisateurDTO.getStatut());
		Profil profil = em.find(Profil.class, utilisateurDTO.getProfil().getIdProfil());
		utilisateur.setProfil(profil);
		List<Groupe> lesGroupes = new ArrayList<Groupe>();
		for (GroupeDTO groupeDTO : utilisateurDTO.getGroupes()) {
			if (groupeDTO.getIdGroupe() != null) {
				lesGroupes.add(em.find(Groupe.class, groupeDTO.getIdGroupe()));
			} else if (groupeDTO.getNomGroupe() != null) {
				Query q = em
						.createQuery("select g from Groupe g where g.nomGroupe = '" + groupeDTO.getNomGroupe() + "'");
				lesGroupes.add((Groupe) q.getSingleResult());

			}
		}
		utilisateur.setGroupes(lesGroupes);
		List<Article> lesArticles = new ArrayList<Article>();
		for (ArticleDTO articleDTO : utilisateurDTO.getArticles()) {

			lesArticles.add(em.find(Article.class, articleDTO.getIdArticle()));
		}

		utilisateur.setArticles(lesArticles);
		List<Groupe> lesGroupesGeres = new ArrayList<Groupe>();
		for (GroupeDTO groupeDTO : utilisateurDTO.getGroupesGeres()) {
			if (groupeDTO.getIdGroupe() != null) {
				lesGroupesGeres.add(em.find(Groupe.class, groupeDTO.getIdGroupe()));
			} else if (groupeDTO.getNomGroupe() != null) {
				Query q = em
						.createQuery("select g from Groupe g where g.nomGroupe = '" + groupeDTO.getNomGroupe() + "'");
				lesGroupesGeres.add((Groupe) q.getSingleResult());

			}
		}
		utilisateur.setGroupesGeres(lesGroupesGeres);
		em.persist(utilisateur);
		MappingToDTO.utilisateurToUtilisateurDTO(utilisateur);

		return utilisateurDTO;

	}

	@Override
	public UtilisateurDTO trouver(UtilisateurDTO utilisateurDTO) {
		Utilisateur utilisateur = new Utilisateur();
		if (utilisateurDTO.getIdUtilisateur() != null) {
			utilisateur = em.find(Utilisateur.class, utilisateurDTO.getIdUtilisateur());

		} else if (utilisateurDTO.getEmail() != null) {
			Query q = em.createQuery("select u from Utilisateur guwhere u.email = '" + utilisateurDTO.getEmail() + "'");
			utilisateur = (Utilisateur) q.getSingleResult();

		}
		utilisateurDTO = MappingToDTO.utilisateurToUtilisateurDTO(utilisateur);
		return utilisateurDTO;
	}

	@Override
	public UtilisateurDTO modifier(UtilisateurDTO ancienUtilisateur, UtilisateurDTO nouvelUtilisateur) {

		Utilisateur utilisateurMAJ = em.find(Utilisateur.class, ancienUtilisateur.getIdUtilisateur());

		if (nouvelUtilisateur.getStatut() != null) {

			utilisateurMAJ.setStatut(nouvelUtilisateur.getStatut());
		}

		if (nouvelUtilisateur.getProfil() != null) {
			Profil profil = new Profil();
			profil = em.find(Profil.class, nouvelUtilisateur.getProfil());
			utilisateurMAJ.setProfil(profil);
		}

		List<Article> lesArticles = new ArrayList<Article>();
		for (ArticleDTO articleDTO : nouvelUtilisateur.getArticles()) {

			lesArticles.add(em.find(Article.class, articleDTO.getIdArticle()));
		}

		utilisateurMAJ.setArticles(lesArticles);

		List<Groupe> lesGroupes = new ArrayList<Groupe>();
		for (GroupeDTO groupeDTO : nouvelUtilisateur.getGroupes()) {
			if (groupeDTO.getIdGroupe() != null) {
				lesGroupes.add(em.find(Groupe.class, groupeDTO.getIdGroupe()));
			} else if (groupeDTO.getNomGroupe() != null) {
				Query q = em
						.createQuery("select g from Groupe g where g.nomGroupe = '" + groupeDTO.getNomGroupe() + "'");
				lesGroupes.add((Groupe) q.getSingleResult());

			}
		}
		utilisateurMAJ.setGroupes(lesGroupes);
		List<Groupe> lesGroupesGeres = new ArrayList<Groupe>();
		for (GroupeDTO groupeDTO : nouvelUtilisateur.getGroupesGeres()) {
			if (groupeDTO.getIdGroupe() != null) {
				lesGroupesGeres.add(em.find(Groupe.class, groupeDTO.getIdGroupe()));
			} else if (groupeDTO.getNomGroupe() != null) {
				Query q = em
						.createQuery("select g from Groupe g where g.nomGroupe = '" + groupeDTO.getNomGroupe() + "'");
				lesGroupesGeres.add((Groupe) q.getSingleResult());

			}
		}
		utilisateurMAJ.setGroupesGeres(lesGroupesGeres);

		em.persist(utilisateurMAJ);

		return MappingToDTO.utilisateurToUtilisateurDTO(utilisateurMAJ);

	}

	@Override
	public void supprimer(UtilisateurDTO obj) {
		// TODO Auto-generated method stub

	}
}
