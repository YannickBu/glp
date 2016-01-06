package ipint.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint.glp.api.DTO.ArticleDTO;
import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.itf.ArticleService;
import ipint.glp.domain.entity.Article;
import ipint.glp.domain.entity.Groupe;
import ipint.glp.domain.entity.Utilisateur;
import ipint.glp.domain.entity.util.MappingToDTO;

@Stateless
public class ArticleImpl implements ArticleService {

	@PersistenceContext(unitName = "PU")
	private EntityManager em;

	/* (non-Javadoc)
	 * @see ipint.glp.api.itf.ArticleService#creer(ipint.glp.api.DTO.ArticleDTO)
	 */
	@Override
	public ArticleDTO creer(ArticleDTO articleDTO) {		
		Article art = new Article();
		art.setContenu(articleDTO.getContenu());
		art.setDatePublication(articleDTO.getDatePublication());
		
		Utilisateur util = null;
		Query q;
		if(articleDTO.getUtilisateur().getEmail()!=null){
			q = em.createQuery("select e from Utilisateur e where e.email = '" + articleDTO.getUtilisateur().getEmail() + "'");
			util = (Utilisateur) q.getSingleResult();
		}else{
			util = em.find(Utilisateur.class, articleDTO.getUtilisateur().getIdUtilisateur());
		}
		if(util == null){
			return null;
		}else{
			util.getArticles().add(art);
			em.persist(util);
		}
		art.setUtilisateur(util);
		
		if(articleDTO.getGroupes()!=null && !articleDTO.getGroupes().isEmpty()){
			List<Groupe> listGrp = new ArrayList<>();
			for(GroupeDTO grpDTO : articleDTO.getGroupes()){
				Groupe grp=null;
				if(grpDTO.getIdGroupe()!=null){
					grp = em.find(Groupe.class, grpDTO.getIdGroupe());
				}else{
					q = em.createQuery("select g from Groupe g where g.nomGroupe = '" + grpDTO.getNomGroupe() + "'");
					grp = (Groupe)q.getSingleResult();
				}
				if(grp!=null){
					listGrp.add(grp);
					grp.getArticles().add(art);
					em.persist(grp);
				}
			}
			if(listGrp.isEmpty()){
				return null;
			}
			art.setGroupes(listGrp);
		}
		
		em.persist(art);

		return MappingToDTO.articleToArticleDTO(art);
	}

	@Override
	public ArticleDTO trouver(ArticleDTO articleDTO) {
		Article art = em.find(Article.class, articleDTO.getIdArticle());
		return MappingToDTO.articleToArticleDTO(art);
	}

//	@Override
//	public ArticleDTO modifier(ArticleDTO ancienArt) {
////		MappingToEntity mte = new MappingToEntity();
////		Article articleMAJ = mte.articleDTOToArticle(ancienArt);
////
////		if (nouvelArt.getContenu() != null) {
////
////			articleMAJ.setContenu(nouvelArt.getContenu());
////		}
////		if (nouvelArt.getDatePublication() != null) {
////
////			articleMAJ.setDatePublication(nouvelArt.getDatePublication());
////		}
////
////		em.persist(articleMAJ);
////		MappingToDTO mtd = new MappingToDTO();
////		nouvelArt = mtd.articleToArticleDTO(articleMAJ);
////
////		return nouvelArt;
//		
//		Article art = em.find(Article.class, ancienArt.getIdArticle());
//		art.setContenu(ancienArt.getContenu());
//		art.setDatePublication(ancienArt.getDatePublication());
//		
//		em.persist(art);
//		
//		return MappingToDTO.articleToArticleDTO(art);
//	}

	/* (non-Javadoc)
	 * @see ipint.glp.api.itf.ArticleService#supprimer(ipint.glp.api.DTO.ArticleDTO)
	 */
	@Override
	public void supprimer(ArticleDTO articleASupprimer) {

		if (articleASupprimer.getIdArticle() != null) {
			Article art = em.find(Article.class, articleASupprimer.getIdArticle());
			em.remove(art);
		}
		// TODO gérer les exceptions pour le cas où le nom et l'id sont tous les
		// deux à null

	}

	/* (non-Javadoc)
	 * @see ipint.glp.api.itf.ArticleService#modifier(ipint.glp.api.DTO.ArticleDTO, ipint.glp.api.DTO.ArticleDTO)
	 */
	@Override
	public ArticleDTO modifier(ArticleDTO ancienArticle, ArticleDTO nouvelArticle) {
		// TODO Auto-generated method stub
		return null;
	}

}
