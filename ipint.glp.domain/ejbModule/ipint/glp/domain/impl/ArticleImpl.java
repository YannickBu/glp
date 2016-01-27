package ipint.glp.domain.impl;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint.glp.api.DTO.ArticleDTO;
import ipint.glp.api.exception.ArticleInconnuException;
import ipint.glp.api.exception.GroupeInconnuException;
import ipint.glp.api.exception.InformationManquanteException;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.exception.UtilisateurInconnuException;
import ipint.glp.api.itf.ArticleService;
import ipint.glp.domain.entity.Article;
import ipint.glp.domain.entity.Groupe;
import ipint.glp.domain.entity.Utilisateur;
import ipint.glp.domain.util.MappingToDTO;

@Stateless
public class ArticleImpl implements ArticleService {

	@PersistenceContext(unitName = "PU")
	private EntityManager em;

	
	@Override
	public ArticleDTO creer(ArticleDTO articleDTO) throws MetierException {
		if(articleDTO==null){
			throw new InformationManquanteException("ArticleImpl.creer : L'articleDTO est null");
		}
		if(articleDTO.getUtilisateur().getEmail()==null && articleDTO.getUtilisateur().getIdUtilisateur()==null){
			throw new InformationManquanteException("ArticleImpl.creer : L'utilisateur dans l'articleDTO est null");
		}
		if(articleDTO.getGroupe()==null){
			throw new InformationManquanteException("ArticleImpl.creer : L'articleDTO n'a pas de groupe principal associé");
		}
//		if(articleDTO.getGroupes()==null || articleDTO.getGroupes().isEmpty()){
//			throw new InformationManquanteException("ArticleDTO.creer : L'articleDTO n'est associé à aucun groupe");
//		}

		Article art = new Article();
		art.setContenu(articleDTO.getContenu());
		art.setDatePublication(articleDTO.getDatePublication());
		
		//Gestion de l'utilisateur de la publication

		Utilisateur util = null;
		Query q;
		if(articleDTO.getUtilisateur().getEmail()!=null){
			q = em.createQuery("select e from Utilisateur e where e.email = '" + articleDTO.getUtilisateur().getEmail() + "'");
			try{
				util = (Utilisateur) q.getSingleResult();
			}catch(NoResultException e){
				throw new UtilisateurInconnuException("ArticleImpl.creer : "+articleDTO.getUtilisateur().toString()+" n'existe pas");
			}
		}else{
			util = em.find(Utilisateur.class, articleDTO.getUtilisateur().getIdUtilisateur());
			if(util==null){
				throw new UtilisateurInconnuException("ArticleImpl.creer : "+articleDTO.getUtilisateur().toString()+" n'existe pas");
			}
		}
		if (util == null) {
			return null;
		} else {
			util.getArticles().add(art);
			em.persist(util);
		}
		art.setUtilisateur(util);

		// Gestion du groupe de publication
		Groupe groupe = null;

		if (articleDTO.getGroupe() != null) {
			groupe = em.find(Groupe.class, articleDTO.getGroupe().getIdGroupe());
		}
		if(groupe == null){
			throw new GroupeInconnuException("ArticleImpl.creer : Le groupe ayant pour id "+articleDTO.getGroupe().getIdGroupe()+" n'existe pas");
		}
		art.setGroupe(groupe);

		//
		// if(articleDTO.getGroupes()!=null &&
		// !articleDTO.getGroupes().isEmpty()){
		// List<Groupe> listGrp = new ArrayList<>();
		// for(GroupeDTO grpDTO : articleDTO.getGroupes()){
		// Groupe grp=null;
		// if(grpDTO.getIdGroupe()!=null){
		// grp = em.find(Groupe.class, grpDTO.getIdGroupe());
		// }else{
		// q = em.createQuery("select g from Groupe g where g.nomGroupe = '" +
		// grpDTO.getNomGroupe() + "'");
		// grp = (Groupe)q.getSingleResult();
		// }
		// if(grp!=null){
		// listGrp.add(grp);
		// grp.getArticles().add(art);
		// em.persist(grp);
		// }
		// }
		// if(listGrp.isEmpty()){
		// return null;
		// }
		// art.setGroupes(listGrp);
		// }


		em.persist(art);

		return MappingToDTO.articleToArticleDTO(art);
	}

	@Override
	public ArticleDTO trouver(ArticleDTO articleDTO) throws MetierException {
		if(articleDTO==null){
			throw new InformationManquanteException("ArticleImpl.trouver : L'articleDTO est null");
		}
		if(articleDTO.getIdArticle()==null){
			throw new InformationManquanteException("ArticleImpl.trouver : L'articleDTO n'a pas d'id");
		}
		Article art = em.find(Article.class, articleDTO.getIdArticle());
		if(art==null){
			throw new ArticleInconnuException("ArticleImpl.trouver : "+articleDTO.toString()+" inconnu");
		}
		return MappingToDTO.articleToArticleDTO(art);
	}

	@Override
	public ArticleDTO modifier(ArticleDTO nouvelArt) throws MetierException {
		if(nouvelArt==null){
			throw new InformationManquanteException("ArticleImpl.modifier : L'articleDTO est null");
		}
		if(nouvelArt.getIdArticle()==null){
			throw new InformationManquanteException("ArticleImpl.modifier : L'articleDTO n'a pas d'id");
		}

		//		MappingToEntity mte = new MappingToEntity();
		//		Article articleMAJ = mte.articleDTOToArticle(ancienArt);
		//
		//		if (nouvelArt.getContenu() != null) {
		//
		//			articleMAJ.setContenu(nouvelArt.getContenu());
		//		}
		//		if (nouvelArt.getDatePublication() != null) {
		//
		//			articleMAJ.setDatePublication(nouvelArt.getDatePublication());
		//		}
		//
		//		em.persist(articleMAJ);
		//		MappingToDTO mtd = new MappingToDTO();
		//		nouvelArt = mtd.articleToArticleDTO(articleMAJ);
		//
		//		return nouvelArt;


		Article art = em.find(Article.class, nouvelArt.getIdArticle());
		if(art==null){
			throw new ArticleInconnuException("ArticleImpl.modifier : "+nouvelArt.toString()+" n'existe pas");
		}
		art.setContenu(nouvelArt.getContenu());
		art.setDatePublication(nouvelArt.getDatePublication());

		//Recupération du groupe du nouvel article
		Groupe groupeTmp = em.find(Groupe.class, nouvelArt.getGroupe().getIdGroupe());
		art.setGroupe(groupeTmp);

		em.persist(art);

		return MappingToDTO.articleToArticleDTO(art);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ipint.glp.api.itf.ArticleService#supprimer(ipint.glp.api.DTO.ArticleDTO)
	 */
	@Override
	public void supprimer(ArticleDTO articleASupprimer) throws MetierException {
		if(articleASupprimer==null){
			throw new InformationManquanteException("ArticleImpl.supprimer : L'articleDTO est null");
		}
		if(articleASupprimer.getIdArticle()==null){
			throw new InformationManquanteException("ArticleImpl.supprimer : L'articleDTO n'a pas d'id");
		}

		Article art = em.find(Article.class, articleASupprimer.getIdArticle());
		if(art==null){
			throw new ArticleInconnuException("ArticleImpl.supprimer : "+articleASupprimer.toString()+" n'existe pas");
		}
		em.remove(art);
	}
}
