package ipint.glp.domain.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.exception.GroupeExistantException;
import ipint.glp.api.exception.GroupeInconnuException;
import ipint.glp.api.exception.InformationManquanteException;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.exception.UtilisateurInconnuException;
import ipint.glp.api.itf.GroupeService;
import ipint.glp.domain.entity.Article;
import ipint.glp.domain.entity.Groupe;
import ipint.glp.domain.entity.Utilisateur;
import ipint.glp.domain.entity.UtilisateurGroupes;
import ipint.glp.domain.util.MappingToDTO;

@Stateless
public class GroupeImpl implements GroupeService {

	@PersistenceContext(unitName = "PU")
	private EntityManager em;

	@SuppressWarnings("rawtypes")
	@Override
	public GroupeDTO creer(GroupeDTO obj) throws MetierException {
		if(obj==null){
			throw new InformationManquanteException("GroupeImpl.creer : Le groupeDTO est null");
		}
		if(obj.getNomGroupe()==null){
			throw new InformationManquanteException("GroupeImpl.creer : Le groupeDTO n'a pas de nom");
		}
		if(obj.getUtilisateurResponsable()==null){
			throw new InformationManquanteException("GroupeImpl.creer : Le groupeDTO n'a pas d'utilisateur responsable");
		}
		if(obj.getUtilisateurResponsable().getIdUtilisateur()==null && obj.getUtilisateurResponsable().getEmail()==null){
			throw new InformationManquanteException("GroupeImpl.creer : L'utilisateur responsable du groupeDTO n'a ni id ni email");
		}

		Query q = null;

		q = em.createQuery(
				"select g from Groupe g where g.nomGroupe = :nom");
		q.setParameter("nom", obj.getNomGroupe());
		if(!q.getResultList().isEmpty()){
			throw new GroupeExistantException("GroupeImpl.creer : Un groupe avec le nom "+obj.getNomGroupe()+" existe déjà");
		}


		Groupe groupe = new Groupe();
		groupe.setNomGroupe(obj.getNomGroupe());
		groupe.setDescription(obj.getDescription());
		groupe.setGroupeOfficiel(obj.isGroupeOfficiel());
		//Utilisateur responsable

		Utilisateur utilisateur = null;
		if (obj.getUtilisateurResponsable().getIdUtilisateur() != null) {
			utilisateur = em.find(Utilisateur.class, obj.getUtilisateurResponsable().getIdUtilisateur());
			if(utilisateur==null){
				throw new UtilisateurInconnuException("GroupeImpl.creer : "+obj.getUtilisateurResponsable().toString()+" n'existe pas pour cet id");
			}
		} else if (obj.getUtilisateurResponsable().getEmail() != null) {
			q = em.createQuery(
					"select u from Utilisateur u where u.email = :email");
			q.setParameter("email", obj.getUtilisateurResponsable().getEmail());
			try{
				utilisateur = (Utilisateur) q.getSingleResult();
			}catch(NoResultException e){
				throw new UtilisateurInconnuException("GroupeImpl.creer : "+obj.getUtilisateurResponsable().toString()+" n'existe pas pour cet email");
			}
		}
		if(utilisateur!=null){
			if(utilisateur.getGroupesGeres()==null){
				utilisateur.setGroupesGeres(new ArrayList<>());
			}
			if(!utilisateur.getGroupesGeres().contains(groupe)){
				List<Groupe> groupesGeres = utilisateur.getGroupesGeres();
				groupesGeres.add(groupe);
				utilisateur.setGroupesGeres(groupesGeres);
			}
			
			em.persist(utilisateur);
		}
		groupe.setUtilisateurResponsable(utilisateur);

		//Liste des articles AUCUN A LA CREATION

		List<Article> articles = new ArrayList<Article>();
		//		if (obj.getArticles()!=null && !obj.getArticles().isEmpty()) {
		//			for (ArticleDTO articleDto : obj.getArticles()) {
		//				Article article = null;
		//				if (articleDto.getIdArticle() != null) {
		//					article = em.find(Article.class, articleDto.getIdArticle());
		//					if(article==null){
		//						throw new ArticleInconnuException("GroupeImpl.creer : "+articleDto.toString()+" n'existe pas");
		//					}
		//					articles.add(article);
		//					// + gerer la bidirection
		//				}
		//			}
		//		}
		groupe.setArticles(articles);

		//Liste des utilisateurs du groupe AUCUN A LA CREATION

		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		//		if (obj.getUtilisateurs()!=null && !obj.getUtilisateurs().isEmpty()) {
		//			for (UtilisateurDTO utilisateurDto : obj.getUtilisateurs()) {
		//				Utilisateur u = null;
		//				if (utilisateurDto.getIdUtilisateur() != null) {
		//					u = em.find(Utilisateur.class, utilisateurDto.getIdUtilisateur());
		//					if(u==null){
		//						throw new UtilisateurInconnuException("GroupeImpl.creer : "+utilisateurDto.toString()+" n'existe pas pour cet id");
		//					}
		//					utilisateurs.add(u);
		//				} else if (utilisateurDto.getEmail() != null) {
		//					q = em.createQuery(
		//							"select u from Utilisateur u where u.email = '" + utilisateurDto.getEmail() + "'");
		//					try{
		//						u = (Utilisateur) q.getSingleResult();	
		//					}catch(NoResultException e){
		//						throw new UtilisateurInconnuException("GroupeImpl.creer : "+utilisateurDto.toString()+" n'existe pas pour cet email");
		//					}
		//					utilisateurs.add(u);
		//					// + gerer la bidirection
		//				}
		//			}
		//		}
		groupe.setUtilisateurs(utilisateurs);

		//Animateurs du groupe

		List<Utilisateur> animateurs = new ArrayList<>();
				for(UtilisateurDTO utilisateurDto : obj.getAnimateurs()){
					Utilisateur u = null;
					if (utilisateurDto.getIdUtilisateur() != null) {
						u = em.find(Utilisateur.class, utilisateurDto.getIdUtilisateur());
						if(u==null){
							throw new UtilisateurInconnuException("GroupeImpl.creer : "+utilisateurDto.toString()+" n'existe pas pour cet id");
						}
						animateurs.add(u);
					} else if (utilisateurDto.getEmail() != null) {
						q = em.createQuery(
								"select u from Utilisateur u where u.email = :email");
						q.setParameter("email", utilisateurDto.getEmail());
						try{
							u = (Utilisateur) q.getSingleResult();	
						}catch(NoResultException e){
							throw new UtilisateurInconnuException("GroupeImpl.creer : "+utilisateurDto.toString()+" n'existe pas pour cet email");
						}
						animateurs.add(u);
					}
					if(u!=null){
						if(u.getGroupesAnimes()==null){
							u.setGroupesAnimes(new ArrayList<>());
						}
						if(!u.getGroupesAnimes().contains(groupe)){
							u.getGroupesAnimes().add(groupe);
							em.persist(u);
						}
					}
				}
		groupe.setAnimateurs(animateurs);

		
	
		
		em.persist(groupe);
		UtilisateurGroupes utilGrp = new UtilisateurGroupes();
		q = em.createQuery(
				"select u from UtilisateurGroupes u where u.email = :email");
		q.setParameter("email", utilisateur.getEmail());
		List results = q.getResultList();
		Iterator it = results.iterator();
		boolean isAlreadyModerator = false;
		while(it.hasNext()){
			UtilisateurGroupes uGrpTmp = (UtilisateurGroupes) it.next();
			if(uGrpTmp.getGroupe().equals("moderateur")){
				isAlreadyModerator = true;
			}
		}
		if(!isAlreadyModerator){
			utilGrp.setEmail(utilisateur.getEmail());
			utilGrp.setGroupe("moderateur");
			em.persist(utilGrp);
		}
		
		return MappingToDTO.groupeToGroupeDTO(groupe);
	}

	@Override
	public GroupeDTO trouver(GroupeDTO obj) throws MetierException {
		if(obj==null){
			throw new InformationManquanteException("GroupeImpl.trouver : Le groupeDTO est null");
		}
		if(obj.getIdGroupe()==null && obj.getNomGroupe()==null){
			throw new InformationManquanteException("GroupeImpl.trouver : Le groupeDTO n'a ni id ni nom de groupe");
		}

		Groupe gr = null;

		if (obj.getIdGroupe() != null) {
			gr = em.find(Groupe.class, obj.getIdGroupe());
			if(gr==null){
				throw new GroupeInconnuException("GroupeImpl.trouver : "+obj.toString()+" n'existe pas pour cet id");
			}
		} else {
			Query q = em.createQuery("select g from Groupe g where g.nomGroupe = :nom");
			q.setParameter("nom", obj.getNomGroupe());
			try{
				gr = (Groupe) q.getSingleResult();
			}catch(NoResultException e){
				throw new GroupeInconnuException("GroupeImpl.trouver : "+obj.toString()+" n'existe pas pour ce nom de groupe");
			}
			//			Query q2 = em.createQuery("select a from Article a where a.groupe_idgroupe = '" + grp.getIdGroupe() + "'");
			//			List<Article> lesArt = q2.getResultList();
			//			grp.setArticles(lesArt);
		}
		em.refresh(gr);
		return MappingToDTO.groupeToGroupeDTO(gr);
	}

	public GroupeDTO modifier(GroupeDTO ancienObj, GroupeDTO nouvelObj) throws MetierException {
		if(ancienObj==null){
			throw new InformationManquanteException("GroupeImpl.modifier : L'ancien groupeDTO est null");
		}
		if(nouvelObj==null){
			throw new InformationManquanteException("GroupeImpl.modifier : Le nouveau groupeDTO est null");
		}
		if(ancienObj.getIdGroupe()==null && ancienObj.getNomGroupe()==null){
			throw new InformationManquanteException("GroupeImpl.modifier : L'ancien groupe n'a ni id ni nom de groupe");
		}

		Query q=null;
		Groupe groupeMAJ=null;

		if(ancienObj.getIdGroupe()!=null){
			groupeMAJ = em.find(Groupe.class,ancienObj.getIdGroupe());
			if(groupeMAJ==null){
				throw new GroupeInconnuException("GroupeImpl.modifier : "+ancienObj.toString()+" est inconnu pour cet id");
			}
		}else{
			q = em.createQuery("select g from Groupe g where g.nomGroupe = :nom");
			q.setParameter("nom", ancienObj.getNomGroupe());
			try{
				groupeMAJ = (Groupe) q.getSingleResult();
			}catch(NoResultException e){
				throw new GroupeInconnuException("GroupeImpl.trouver : "+ancienObj.toString()+" n'existe pas pour ce nom de groupe");
			}
		}

		if(nouvelObj.getDescription()!=null){
			groupeMAJ.setDescription(nouvelObj.getDescription());
		}

//		if(nouvelObj.getUtilisateurResponsable()!=null){
//			Utilisateur utilisateur=null;
//			if (nouvelObj.getUtilisateurResponsable().getIdUtilisateur() != null) {
//				utilisateur = em.find(Utilisateur.class, nouvelObj.getUtilisateurResponsable().getIdUtilisateur());
//				if(utilisateur==null){
//					throw new UtilisateurInconnuException("GroupeImpl.modifier : "+nouvelObj.getUtilisateurResponsable().toString()+" n'existe pas pour cet id");
//				}
//			} else if (nouvelObj.getUtilisateurResponsable().getEmail() != null) {
//				q = em.createQuery(
//						"select u from Utilisateur u where u.email = '" + nouvelObj.getUtilisateurResponsable().getEmail() + "'");
//				try{
//					utilisateur = (Utilisateur) q.getSingleResult();
//				}catch(NoResultException e){
//					throw new UtilisateurInconnuException("GroupeImpl.modifier : "+nouvelObj.getUtilisateurResponsable().toString()+" n'existe pas pour cet email");
//				}
//			}
//
//			utilisateur.getGroupesGeres().remove(groupeMAJ);//le equals est sur l id et le nom du groupe
//			em.persist(utilisateur);
//
//			groupeMAJ.setUtilisateurResponsable(utilisateur);
//		}

		//		if(nouvelObj.getAnimateurs()!=null){
		//			for(UtilisateurDTO utilisateurDto : nouvelObj.getAnimateurs()){
		//				for(Utilisateur utilisateur : groupeMAJ.getAnimateurs()){
		//					
		//				}
		//			}
		//		}
		//		
		//		if(nouvelObj.getArticles()!=null){
		//			
		//		}
		//		
		//		if(nouvelObj.getUtilisateurs()!=null){
		//			
		//		}

		//A laisser en dernier !
		if(nouvelObj.getNomGroupe()!=null && !"".equals(nouvelObj.getNomGroupe())){
			groupeMAJ.setNomGroupe(nouvelObj.getNomGroupe());
		}

		return MappingToDTO.groupeToGroupeDTO(groupeMAJ);
	}

	@Override
	public GroupeDTO ajouterUtilisateur(GroupeDTO groupeDTOAModifier, UtilisateurDTO utilisateurDTOAAjouter) throws MetierException{
		if(groupeDTOAModifier==null){
			throw new InformationManquanteException("GroupeImpl.ajouterUtilisateur : Le groupe à modifier est null");
		}
		if(utilisateurDTOAAjouter==null){
			throw new InformationManquanteException("GroupeImpl.ajouterUtilisateur : L'utilisateur à ajouter est null");
		}
		if(groupeDTOAModifier.getIdGroupe()==null && groupeDTOAModifier.getNomGroupe()==null){
			throw new InformationManquanteException("GroupeImpl.ajouterUtilisateur : Le groupe à modifier n'a ni id ni nom de groupe");
		}
		if(utilisateurDTOAAjouter.getIdUtilisateur()==null && utilisateurDTOAAjouter.getEmail()==null){
			throw new InformationManquanteException("GroupeImpl.ajouterUtilisateur : L'utilisateur n'a ni id ni email");
		}

		Query q = null;

		Utilisateur utilisateurAAjouter=null;
		if (utilisateurDTOAAjouter.getIdUtilisateur() != null) {
			utilisateurAAjouter = em.find(Utilisateur.class, utilisateurDTOAAjouter.getIdUtilisateur());
			if(utilisateurAAjouter==null){
				throw new UtilisateurInconnuException("GroupeImpl.ajouterUtilisateur : "+utilisateurDTOAAjouter.toString()+" n'existe pas pour cet id");
			}
		} else if (utilisateurDTOAAjouter.getEmail() != null) {
			q = em.createQuery("select u from Utilisateur u where u.email = :email");
			q.setParameter("email", utilisateurDTOAAjouter.getEmail());
			try{
				utilisateurAAjouter = (Utilisateur) q.getSingleResult();
			}catch(NoResultException e){
				throw new UtilisateurInconnuException("GroupeImpl.ajouterUtilisateur : "+utilisateurDTOAAjouter.toString()+" n'existe pas pour cet email");
			}
		}

		Groupe groupeAModifier = null;
		if (groupeDTOAModifier.getIdGroupe() != null) {
			groupeAModifier = em.find(Groupe.class, groupeDTOAModifier.getIdGroupe());
			if(groupeAModifier==null){
				throw new GroupeInconnuException("GroupeImpl.ajouterUtilisateur : "+groupeDTOAModifier.toString()+" n'existe pas pour cet id");
			}
		} else {
			q = em.createQuery("select g from Groupe g where g.nomGroupe = :nom");
			q.setParameter("nom", groupeDTOAModifier.getNomGroupe());
			try{
				groupeAModifier = (Groupe) q.getSingleResult();
			}catch(NoResultException e){
				throw new GroupeInconnuException("GroupeImpl.ajouterUtilisateur : "+groupeDTOAModifier.toString()+" n'existe pas pour ce nom de groupe");
			}
		}

		if(!groupeAModifier.getUtilisateurs().contains(utilisateurAAjouter)){
			groupeAModifier.getUtilisateurs().add(utilisateurAAjouter);
			em.merge(groupeAModifier);
		}
		if(!utilisateurAAjouter.getGroupes().contains(groupeAModifier)){
			utilisateurAAjouter.getGroupes().add(groupeAModifier);
			em.merge(utilisateurAAjouter);
		}

		return MappingToDTO.groupeToGroupeDTO(groupeAModifier);
	}

	@Override
	public GroupeDTO supprimerUtilisateur(GroupeDTO groupeDTOAModifier, UtilisateurDTO utilisateurDTOASuppimer) throws MetierException{
		if(groupeDTOAModifier==null){
			throw new InformationManquanteException("GroupeImpl.supprimerUtilisateur : Le groupe à modifier est null");
		}
		if(utilisateurDTOASuppimer==null){
			throw new InformationManquanteException("GroupeImpl.supprimerUtilisateur : L'utilisateur à supprimer est null");
		}
		if(groupeDTOAModifier.getIdGroupe()==null && groupeDTOAModifier.getNomGroupe()==null){
			throw new InformationManquanteException("GroupeImpl.supprimerUtilisateur : Le groupe à modifier n'a ni id ni nom de groupe");
		}
		if(utilisateurDTOASuppimer.getIdUtilisateur()==null && utilisateurDTOASuppimer.getEmail()==null){
			throw new InformationManquanteException("GroupeImpl.supprimerUtilisateur : L'utilisateur n'a ni id ni email");
		}

		Query q = null;

		Utilisateur utilisateurASupprimer=null;
		if (utilisateurDTOASuppimer.getIdUtilisateur() != null) {
			utilisateurASupprimer = em.find(Utilisateur.class, utilisateurDTOASuppimer.getIdUtilisateur());
			if(utilisateurASupprimer==null){
				throw new UtilisateurInconnuException("GroupeImpl.supprimerUtilisateur : "+utilisateurDTOASuppimer.toString()+" n'existe pas pour cet id");
			}
		} else if (utilisateurDTOASuppimer.getEmail() != null) {
			q = em.createQuery("select u from Utilisateur u where u.email = :email");
			q.setParameter("email", utilisateurDTOASuppimer.getEmail());
			try{
				utilisateurASupprimer = (Utilisateur) q.getSingleResult();
			}catch(NoResultException e){
				throw new UtilisateurInconnuException("GroupeImpl.supprimerUtilisateur : "+utilisateurDTOASuppimer.toString()+" n'existe pas pour cet email");
			}
		}

		Groupe groupeAModifier = null;
		if (groupeDTOAModifier.getIdGroupe() != null) {
			groupeAModifier = em.find(Groupe.class, groupeDTOAModifier.getIdGroupe());
			if(groupeAModifier==null){
				throw new GroupeInconnuException("GroupeImpl.supprimerUtilisateur : "+groupeDTOAModifier.toString()+" n'existe pas pour cet id");
			}
		} else {
			q = em.createQuery("select g from Groupe g where g.nomGroupe = :nom");
			q.setParameter("nom", groupeDTOAModifier.getNomGroupe());
			try{
				groupeAModifier = (Groupe) q.getSingleResult();
			}catch(NoResultException e){
				throw new GroupeInconnuException("GroupeImpl.supprimerUtilisateur : "+groupeDTOAModifier.toString()+" n'existe pas pour ce nom de groupe");
			}
		}

		groupeAModifier.getUtilisateurs().remove(utilisateurASupprimer);
		em.merge(groupeAModifier);

		utilisateurASupprimer.getGroupes().remove(groupeAModifier);
		em.merge(utilisateurASupprimer);

		return MappingToDTO.groupeToGroupeDTO(groupeAModifier);
	}

	@Override
	public GroupeDTO ajouterAnimateur(GroupeDTO groupeDTOAModifier, UtilisateurDTO animateurDTOAAjouter) throws MetierException{
		if(groupeDTOAModifier==null){
			throw new InformationManquanteException("GroupeImpl.ajouterAnimateur : Le groupe à modifier est null");
		}
		if(animateurDTOAAjouter==null){
			throw new InformationManquanteException("GroupeImpl.ajouterAnimateur : L'animateur à ajouter est null");
		}
		if(groupeDTOAModifier.getIdGroupe()==null && groupeDTOAModifier.getNomGroupe()==null){
			throw new InformationManquanteException("GroupeImpl.ajouterAnimateur : Le groupe à modifier n'a ni id ni nom de groupe");
		}
		if(animateurDTOAAjouter.getIdUtilisateur()==null && animateurDTOAAjouter.getEmail()==null){
			throw new InformationManquanteException("GroupeImpl.ajouterAnimateur : L'animateur n'a ni id ni email");
		}

		Query q = null;

		Utilisateur animateurAAjouter=null;
		if (animateurDTOAAjouter.getIdUtilisateur() != null) {
			animateurAAjouter = em.find(Utilisateur.class, animateurDTOAAjouter.getIdUtilisateur());
			if(animateurAAjouter==null){
				throw new UtilisateurInconnuException("GroupeImpl.ajouterAnimateur : "+animateurDTOAAjouter.toString()+" n'existe pas pour cet id");
			}
		} else if (animateurDTOAAjouter.getEmail() != null) {
			q = em.createQuery("select u from Utilisateur u where u.email = :email");
			q.setParameter("email", animateurDTOAAjouter.getEmail());
			try{
				animateurAAjouter = (Utilisateur) q.getSingleResult();
			}catch(NoResultException e){
				throw new UtilisateurInconnuException("GroupeImpl.ajouterAnimateur : "+animateurDTOAAjouter.toString()+" n'existe pas pour cet email");
			}
		}

		Groupe groupeAModifier = null;
		if (groupeDTOAModifier.getIdGroupe() != null) {
			groupeAModifier = em.find(Groupe.class, groupeDTOAModifier.getIdGroupe());
			if(groupeAModifier==null){
				throw new GroupeInconnuException("GroupeImpl.ajouterAnimateur : "+groupeDTOAModifier.toString()+" n'existe pas pour cet id");
			}
		} else {
			q = em.createQuery("select g from Groupe g where g.nomGroupe = :nom");
			q.setParameter("nom", groupeDTOAModifier.getNomGroupe());
			try{
				groupeAModifier = (Groupe) q.getSingleResult();
			}catch(NoResultException e){
				throw new GroupeInconnuException("GroupeImpl.ajouterAnimateur : "+groupeDTOAModifier.toString()+" n'existe pas pour ce nom de groupe");
			}
		}

		if(!groupeAModifier.getAnimateurs().contains(animateurAAjouter)){
			groupeAModifier.getAnimateurs().add(animateurAAjouter);
		}
		if(!groupeAModifier.getUtilisateurs().contains(animateurAAjouter)){
			groupeAModifier.getUtilisateurs().add(animateurAAjouter);
		}
		if(!animateurAAjouter.getGroupesAnimes().contains(groupeAModifier)){
			animateurAAjouter.getGroupesAnimes().add(groupeAModifier);
		}
		if(!animateurAAjouter.getGroupes().contains(groupeAModifier)){
			animateurAAjouter.getGroupes().add(groupeAModifier);
		}
		em.merge(groupeAModifier);
		em.merge(animateurAAjouter);

		return MappingToDTO.groupeToGroupeDTO(groupeAModifier);
	}

	@Override
	public GroupeDTO supprimerAnimateur(GroupeDTO groupeDTOAModifier, UtilisateurDTO animateurDTOASupprimer) throws MetierException{
		if(groupeDTOAModifier==null){
			throw new InformationManquanteException("GroupeImpl.supprimerAnimateur : Le groupe à modifier est null");
		}
		if(animateurDTOASupprimer==null){
			throw new InformationManquanteException("GroupeImpl.supprimerAnimateur : L'animateur à supprimer est null");
		}
		if(groupeDTOAModifier.getIdGroupe()==null && groupeDTOAModifier.getNomGroupe()==null){
			throw new InformationManquanteException("GroupeImpl.supprimerAnimateur : Le groupe à modifier n'a ni id ni nom de groupe");
		}
		if(animateurDTOASupprimer.getIdUtilisateur()==null && animateurDTOASupprimer.getEmail()==null){
			throw new InformationManquanteException("GroupeImpl.supprimerAnimateur : L'animateur n'a ni id ni email");
		}

		Query q = null;

		Utilisateur animateurASupprimer=null;
		if (animateurDTOASupprimer.getIdUtilisateur() != null) {
			animateurASupprimer = em.find(Utilisateur.class, animateurDTOASupprimer.getIdUtilisateur());
			if(animateurASupprimer==null){
				throw new UtilisateurInconnuException("GroupeImpl.supprimerAnimateur : "+animateurDTOASupprimer.toString()+" n'existe pas pour cet id");
			}
		} else if (animateurDTOASupprimer.getEmail() != null) {
			q = em.createQuery("select u from Utilisateur u where u.email = :email");
			q.setParameter("email", animateurDTOASupprimer.getEmail());
			try{
				animateurASupprimer = (Utilisateur) q.getSingleResult();
			}catch(NoResultException e){
				throw new UtilisateurInconnuException("GroupeImpl.supprimerAnimateur : "+animateurDTOASupprimer.toString()+" n'existe pas pour cet email");
			}
		}

		Groupe groupeAModifier = null;
		if (groupeDTOAModifier.getIdGroupe() != null) {
			groupeAModifier = em.find(Groupe.class, groupeDTOAModifier.getIdGroupe());
			if(groupeAModifier==null){
				throw new GroupeInconnuException("GroupeImpl.supprimerAnimateur : "+groupeDTOAModifier.toString()+" n'existe pas pour cet id");
			}
		} else {
			q = em.createQuery("select g from Groupe g where g.nomGroupe = :nom");
			q.setParameter("nom", groupeDTOAModifier.getNomGroupe());
			try{
				groupeAModifier = (Groupe) q.getSingleResult();
			}catch(NoResultException e){
				throw new GroupeInconnuException("GroupeImpl.supprimerAnimateur : "+groupeDTOAModifier.toString()+" n'existe pas pour ce nom de groupe");
			}
		}

		groupeAModifier.getAnimateurs().remove(animateurASupprimer);
		animateurASupprimer.getGroupesAnimes().remove(groupeAModifier);

		em.merge(groupeAModifier);
		em.merge(animateurASupprimer);

		return MappingToDTO.groupeToGroupeDTO(groupeAModifier);
	}
	
	@Override
	public GroupeDTO modifierResponsable(GroupeDTO groupeDTOAModifier, UtilisateurDTO nouveauResponsableDTO) throws MetierException{
		if(groupeDTOAModifier==null){
			throw new InformationManquanteException("GroupeImpl.modifierResponsable : Le groupe à modifier est null");
		}
		if(nouveauResponsableDTO==null){
			throw new InformationManquanteException("GroupeImpl.modifierResponsable : Le responsable à ajouter est null");
		}
		if(groupeDTOAModifier.getIdGroupe()==null && groupeDTOAModifier.getNomGroupe()==null){
			throw new InformationManquanteException("GroupeImpl.modifierResponsable : Le groupe à modifier n'a ni id ni nom de groupe");
		}
		if(nouveauResponsableDTO.getIdUtilisateur()==null && nouveauResponsableDTO.getEmail()==null){
			throw new InformationManquanteException("GroupeImpl.modifierResponsable : Le responsable n'a ni id ni email");
		}

		Query q = null;

		Utilisateur nouveauResponsable=null;
		if (nouveauResponsableDTO.getIdUtilisateur() != null) {
			nouveauResponsable = em.find(Utilisateur.class, nouveauResponsableDTO.getIdUtilisateur());
			if(nouveauResponsable==null){
				throw new UtilisateurInconnuException("GroupeImpl.modifierResponsable : "+nouveauResponsableDTO.toString()+" n'existe pas pour cet id");
			}
		} else if (nouveauResponsableDTO.getEmail() != null) {
			q = em.createQuery("select u from Utilisateur u where u.email = :email");
			q.setParameter("email", nouveauResponsableDTO.getEmail());
			try{
				nouveauResponsable = (Utilisateur) q.getSingleResult();
			}catch(NoResultException e){
				throw new UtilisateurInconnuException("GroupeImpl.modifierResponsable : "+nouveauResponsableDTO.toString()+" n'existe pas pour cet email");
			}
		}

		Groupe groupeAModifier = null;
		if (groupeDTOAModifier.getIdGroupe() != null) {
			groupeAModifier = em.find(Groupe.class, groupeDTOAModifier.getIdGroupe());
			if(groupeAModifier==null){
				throw new GroupeInconnuException("GroupeImpl.modifierResponsable : "+groupeDTOAModifier.toString()+" n'existe pas pour cet id");
			}
		} else {
			q = em.createQuery("select g from Groupe g where g.nomGroupe = :nom");
			q.setParameter("nom", groupeDTOAModifier.getNomGroupe());
			try{
				groupeAModifier = (Groupe) q.getSingleResult();
			}catch(NoResultException e){
				throw new GroupeInconnuException("GroupeImpl.modifierResponsable : "+groupeDTOAModifier.toString()+" n'existe pas pour ce nom de groupe");
			}
		}

		Utilisateur ancienResponsable = groupeAModifier.getUtilisateurResponsable();
		ancienResponsable.getGroupesGeres().remove(groupeAModifier);
		groupeAModifier.setUtilisateurResponsable(nouveauResponsable);
		if(!nouveauResponsable.getGroupesGeres().contains(groupeAModifier)){
			nouveauResponsable.getGroupesGeres().add(groupeAModifier);
		}
		
		em.merge(ancienResponsable);
		em.merge(nouveauResponsable);
		em.merge(groupeAModifier);

		return MappingToDTO.groupeToGroupeDTO(groupeAModifier);
	}
	
	public List<UtilisateurDTO> listerUtilisateurs(GroupeDTO groupeDTO) throws MetierException {
		if(groupeDTO==null){
			throw new InformationManquanteException("GroupeImpl.listerUtilisateurs : Le groupe est null");
		}
		if(groupeDTO.getIdGroupe()==null && groupeDTO.getNomGroupe()==null){
			throw new InformationManquanteException("GroupeImpl.listerUtilisateurs : Le groupe n'a ni id ni nom de groupe");
		}
		
		Query q;
		List<Utilisateur> utilisateurs;
		List<UtilisateurDTO> utilisateursDTO = new ArrayList<>();
		
		if (groupeDTO.getIdGroupe() != null) {
			q = em.createQuery("select g from Groupe g where g.idGroupe = :idgroupe");
			q.setParameter("idgroupe", groupeDTO.getIdGroupe());
		}else{
			q = em.createQuery("select g from Groupe g where g.nomGroupe = :nom");
			q.setParameter("nom", groupeDTO.getNomGroupe());
		}
		
		Groupe gr = null;
		try{
			gr = (Groupe) q.getSingleResult();
		}catch(NoResultException e){
			if(groupeDTO.getIdGroupe()!=null){
				throw new GroupeInconnuException("Le groupe ayant pour id "+groupeDTO.getIdGroupe()+" est inconnu");
			}else{
				throw new GroupeInconnuException("Le groupe ayant pour nom "+groupeDTO.getNomGroupe()+" est inconnu");
			}
		}
		utilisateurs = gr.getUtilisateurs();
		
		for(Utilisateur utilisateur : utilisateurs){
			utilisateursDTO.add(MappingToDTO.utilisateurToUtilisateurDTO(utilisateur));
		}
		
		return utilisateursDTO;
	}
	
	@Override
	public void supprimer(GroupeDTO obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<GroupeDTO> listerParType(boolean isGroupeOfficiel) throws MetierException {
		Query q = em.createQuery("select g from Groupe g where g.isGroupeOfficiel = :isGroupeOfficiel");
		q.setParameter("isGroupeOfficiel", isGroupeOfficiel);
		List<Groupe> lesGroupes = q.getResultList();
		List<GroupeDTO> lesGroupesDTO = new ArrayList<GroupeDTO>();
		for (Groupe groupe : lesGroupes) {
			lesGroupesDTO.add(MappingToDTO.groupeToGroupeDTO(groupe));
		}
		return lesGroupesDTO;
	}
	
	@Override
	public List<GroupeDTO> listerTousLesGroupes() throws MetierException {
		Query q = em.createQuery("select g from Groupe g");
		List<Groupe> lesGroupes = q.getResultList();
		List<GroupeDTO> lesGroupesDTO = new ArrayList<GroupeDTO>();
		for (Groupe groupe : lesGroupes) {
			lesGroupesDTO.add(MappingToDTO.groupeToGroupeDTO(groupe));
		}
		return lesGroupesDTO;
	}

}
