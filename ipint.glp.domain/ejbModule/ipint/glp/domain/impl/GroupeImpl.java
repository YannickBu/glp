package ipint.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint.glp.api.DTO.ArticleDTO;
import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.exception.ArticleInconnuException;
import ipint.glp.api.exception.GroupeInconnuException;
import ipint.glp.api.exception.InformationManquanteException;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.exception.UtilisateurInconnuException;
import ipint.glp.api.itf.GroupeService;
import ipint.glp.domain.entity.Article;
import ipint.glp.domain.entity.Groupe;
import ipint.glp.domain.entity.Utilisateur;
import ipint.glp.domain.util.MappingToDTO;

@Stateless
public class GroupeImpl implements GroupeService {

	@PersistenceContext(unitName = "PU")
	private EntityManager em;

	@Override
	public GroupeDTO creer(GroupeDTO obj) throws MetierException {
		if(obj==null){
			throw new InformationManquanteException("Le groupeDTO est null");
		}
		if(obj.getNomGroupe()==null){
			throw new InformationManquanteException("Le groupeDTO n'a pas de nom");
		}
		if(obj.getUtilisateurResponsable()==null){
			throw new InformationManquanteException("Le groupeDTO n'a pas d'utilisateur responsable");
		}
		if(obj.getUtilisateurResponsable().getIdUtilisateur()==null && obj.getUtilisateurResponsable().getEmail()==null){
			throw new InformationManquanteException("L'utilisateur responsable du groupeDTO n'a ni id ni email");
		}
		
		Query q = null;
		
		Groupe groupe = new Groupe();
		groupe.setNomGroupe(obj.getNomGroupe());
		groupe.setDescription(obj.getDescription());
		
		Utilisateur utilisateur = null;
		if (obj.getUtilisateurResponsable().getIdUtilisateur() != null) {
			utilisateur = em.find(Utilisateur.class, obj.getUtilisateurResponsable().getIdUtilisateur());
			if(utilisateur==null){
				throw new UtilisateurInconnuException(obj.getUtilisateurResponsable().toString()+" n'existe pas pour cet id");
			}
		} else if (obj.getUtilisateurResponsable().getEmail() != null) {
			q = em.createQuery(
					"select u from Utilisateur u where u.email = '" + obj.getUtilisateurResponsable().getEmail() + "'");
			try{
				utilisateur = (Utilisateur) q.getSingleResult();
			}catch(NoResultException e){
				throw new UtilisateurInconnuException(obj.getUtilisateurResponsable().toString()+" n'existe pas pour cet email");
			}
		}
		groupe.setUtilisateurResponsable(utilisateur);
		
		List<Article> articles = new ArrayList<Article>();
		if (obj.getArticles()!=null && !obj.getArticles().isEmpty()) {
			for (ArticleDTO articleDto : obj.getArticles()) {
				Article article = null;
				if (articleDto.getIdArticle() != null) {
					article = em.find(Article.class, articleDto.getIdArticle());
					if(article==null){
						throw new ArticleInconnuException(articleDto.toString()+" n'existe pas");
					}
					articles.add(article);	
				}
			}
		}
		groupe.setArticles(articles);
		
		//TODO Lutilisateur responsable fait il parti de cette liste?
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		if (obj.getUtilisateurs()!=null && !obj.getUtilisateurs().isEmpty()) {
			for (UtilisateurDTO utilisateurDto : obj.getUtilisateurs()) {
				Utilisateur u = null;
				if (utilisateurDto.getIdUtilisateur() != null) {
					u = em.find(Utilisateur.class, utilisateurDto.getIdUtilisateur());
					if(u==null){
						throw new UtilisateurInconnuException(utilisateurDto.toString()+" n'existe pas pour cet id");
					}
					utilisateurs.add(u);
				} else if (utilisateurDto.getEmail() != null) {
					q = em.createQuery(
							"select u from Utilisateur u where u.email = '" + utilisateurDto.getEmail() + "'");
					try{
						u = (Utilisateur) q.getSingleResult();	
					}catch(NoResultException e){
						throw new UtilisateurInconnuException(utilisateurDto.toString()+" n'existe pas pour cet email");
					}
					utilisateurs.add(u);		
				}
			}
		}
		groupe.setUtilisateurs(utilisateurs);
		
		em.persist(groupe);
		
		return MappingToDTO.groupeToGroupeDTO(groupe);
	}

	@Override
	public GroupeDTO trouver(GroupeDTO obj) throws MetierException {
		if(obj==null){
			throw new InformationManquanteException("Le groupeDTO est null");
		}
		if(obj.getIdGroupe()==null && obj.getNomGroupe()==null){
			throw new InformationManquanteException("Le groupeDTO n'a ni id ni nom de groupe");
		}
		
		Groupe gr = null;
		
		if (obj.getIdGroupe() != null) {
			gr = em.find(Groupe.class, obj.getIdGroupe());
			if(gr==null){
				throw new GroupeInconnuException(obj.toString()+" n'existe pas pour cet id");
			}
		} else {
			Query q = em.createQuery("select g from Groupe g where g.nomGroupe = '" + obj.getNomGroupe() + "'");
			try{
				gr = (Groupe) q.getSingleResult();
			}catch(NoResultException e){
				throw new GroupeInconnuException(obj.toString()+" n'existe pas pour ce nom de groupe");
			}
//			Query q2 = em.createQuery("select a from Article a where a.groupe_idgroupe = '" + grp.getIdGroupe() + "'");
//			List<Article> lesArt = q2.getResultList();
//			grp.setArticles(lesArt);
		}
		return MappingToDTO.groupeToGroupeDTO(gr);
	}

	public GroupeDTO modifier(GroupeDTO ancienObj, GroupeDTO nouvelObj) {
		// Groupe groupeMAJ = em.find(Groupe.class,ancienObj.getIdGroupe());
		//
		// if (nouvelObj.getDescription() != null) {
		// groupeMAJ.setDescription(nouvelObj.getDescription());
		// }
		// if (ancienObj.getUtilisateurResponsable() != null) {
		//
		// groupeMAJ.setUtilisateurResponsable(nouvelObj.getUtilisateurResponsable());
		// }
		// em.getTransaction().begin();
		//
		// em.persist(groupeMAJ);
		//
		// em.getTransaction().commit();
		//
		// return groupeMAJ;
		// TODO
		return null;
	}

	@Override
	public void supprimer(GroupeDTO obj) {
		// TODO Auto-generated method stub

	}

	public List<GroupeDTO> lister() throws MetierException {
		Query q = em.createQuery("select g from Groupe g ");
		List<Groupe> lesGroupes = q.getResultList();
		List<GroupeDTO> lesGroupesDTO = new ArrayList<GroupeDTO>();
		for (Groupe groupe : lesGroupes) {
			lesGroupesDTO.add(MappingToDTO.groupeToGroupeDTO(groupe));
		}
		return lesGroupesDTO;
	}

}
