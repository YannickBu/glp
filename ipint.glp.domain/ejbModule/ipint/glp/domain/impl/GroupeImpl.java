package ipint.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint.glp.api.DTO.ArticleDTO;
import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.itf.GroupeService;
import ipint.glp.domain.entity.Article;
import ipint.glp.domain.entity.Groupe;
import ipint.glp.domain.entity.Utilisateur;
import ipint.glp.domain.entity.util.MappingToDTO;

@Stateless
public class GroupeImpl implements GroupeService {

	@PersistenceContext(unitName = "PU")
	private EntityManager em;

	/* (non-Javadoc)
	 * @see ipint.glp.api.itf.GroupeService#creer(ipint.glp.api.DTO.GroupeDTO)
	 */
	@Override
	public GroupeDTO creer(GroupeDTO obj) {
		Groupe groupe = new Groupe();
		groupe.setNomGroupe(obj.getNomGroupe());
		groupe.setDescription(obj.getDescription());
		Utilisateur utilisateur = null;
		if(obj.getUtilisateurResponsable().getIdUtilisateur() != null){
			utilisateur = em.find(Utilisateur.class, obj.getUtilisateurResponsable().getIdUtilisateur());
		}
		else if(obj.getUtilisateurResponsable().getEmail() != null){
			Query q = em.createQuery("select u from Utilisateur u where u.nom = '" + obj.getUtilisateurResponsable().getNom() + "'");
			utilisateur = (Utilisateur) q.getSingleResult();
		}
		//TODO gérer les exceptions si id et email sont null
		groupe.setUtilisateurResponsable(utilisateur);
		List<Article> articles = new ArrayList<Article>();
		if(obj.getArticles().size() > 0){
			for(ArticleDTO articleDto : obj.getArticles()){
				Article article = null;
				if(articleDto.getIdArticle() !=  null){
					article = em.find(Article.class, articleDto.getIdArticle());
					articles.add(article);
				}
				//TODO gérer l'exception si id = null
			}
		}		
		groupe.setArticles(articles);
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		if(obj.getUtilisateurs().size() > 0){
			for(UtilisateurDTO utilisateurDto : obj.getUtilisateurs()){
				Utilisateur u = null;
				if(utilisateurDto.getIdUtilisateur() !=  null){
					u = em.find(Utilisateur.class, utilisateurDto.getIdUtilisateur());
					utilisateurs.add(u);
				}
				else if(utilisateurDto.getEmail() !=  null){
					u = em.find(Utilisateur.class, utilisateurDto.getEmail());
					utilisateurs.add(u);
				}
				//TODO gérer l'exception si id et email = null
			}
		}		
		groupe.setUtilisateurs(utilisateurs);
		em.persist(groupe);
		Groupe groupeCreated = em.find(Groupe.class, groupe.getIdGroupe());
		return MappingToDTO.groupeToGroupeDTO(groupeCreated);
	}

	/* (non-Javadoc)
	 * @see ipint.glp.api.itf.GroupeService#trouver(ipint.glp.api.DTO.GroupeDTO)
	 */
	@Override
	public GroupeDTO trouver(GroupeDTO obj) {
		if(obj.getIdGroupe() != null){
			return MappingToDTO.groupeToGroupeDTO(em.find(Groupe.class, obj.getIdGroupe()));
		}
		else if(obj.getNomGroupe() != null){
			Query q = em.createQuery("select g from Groupe g where g.nomGroupe = '" + obj.getNomGroupe() +"'");
			Groupe grp = (Groupe) q.getSingleResult();
			return MappingToDTO.groupeToGroupeDTO(grp);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see ipint.glp.api.itf.GroupeService#modifier(ipint.glp.api.DTO.GroupeDTO, ipint.glp.api.DTO.GroupeDTO)
	 */
	public GroupeDTO modifier(GroupeDTO ancienObj, GroupeDTO nouvelObj) {
//		Groupe groupeMAJ = em.find(Groupe.class,ancienObj.getIdGroupe());
//
//		if (nouvelObj.getDescription() != null) {
//			groupeMAJ.setDescription(nouvelObj.getDescription());
//		}
//		if (ancienObj.getUtilisateurResponsable() != null) {
//
//			groupeMAJ.setUtilisateurResponsable(nouvelObj.getUtilisateurResponsable());
//		}
//		em.getTransaction().begin();
//
//		em.persist(groupeMAJ);
//
//		em.getTransaction().commit();
//
//		return groupeMAJ;
		//TODO
		return null;
	}

	/* (non-Javadoc)
	 * @see ipint.glp.api.itf.GroupeService#supprimer(ipint.glp.api.DTO.GroupeDTO)
	 */
	@Override
	public void supprimer(GroupeDTO obj) {
		// TODO Auto-generated method stub
		
	}



}
