package ipint.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint.glp.api.DTO.CompetenceDTO;
import ipint.glp.api.DTO.DiplomeDTO;
import ipint.glp.api.DTO.ExperienceDTO;
import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.DTO.enumType.Statut;
import ipint.glp.api.itf.UtilisateurService;
import ipint.glp.domain.entity.Competence;
import ipint.glp.domain.entity.Diplome;
import ipint.glp.domain.entity.Experience;
import ipint.glp.domain.entity.Groupe;
import ipint.glp.domain.entity.Profil;
import ipint.glp.domain.entity.Utilisateur;
import ipint.glp.domain.entity.UtilisateurGroupes;
import ipint.glp.domain.entity.util.MappingToDTO;

@Stateless
public class UtilisateurImpl implements UtilisateurService {

	@PersistenceContext(unitName = "PU")
	private EntityManager em;

	/* (non-Javadoc)
	 * @see ipint.glp.api.itf.UtilisateurService#creer(ipint.glp.api.DTO.UtilisateurDTO)
	 */
	@Override
	public UtilisateurDTO creer(UtilisateurDTO utilisateurDTO) {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setEmail(utilisateurDTO.getEmail());
		utilisateur.setNom(utilisateurDTO.getNom());
		utilisateur.setPrenom(utilisateurDTO.getPrenom());
		utilisateur.setPassword(utilisateurDTO.getPassword());
		utilisateur.setStatut(utilisateurDTO.getStatut());
		
		GroupeDTO groupeDTOm = utilisateurDTO.getGroupePrincipal();
		Groupe grpm = null;
		if (groupeDTOm.getIdGroupe() != null) {
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+ em);
			grpm = em.find(Groupe.class, groupeDTOm.getIdGroupe());
		} else if (groupeDTOm.getNomGroupe() != null) {
			Query q = em
					.createQuery("select g from Groupe g where g.nomGroupe = '" + groupeDTOm.getNomGroupe() + "'");
			grpm = (Groupe) q.getSingleResult();		}
		
		utilisateur.setGroupePrincipal(grpm);
		
		//Profil profil = em.find(Profil.class, utilisateurDTO.getProfil().getIdProfil());
		//utilisateur.setProfil(profil);
		
		if(utilisateurDTO.getGroupes()!=null && !utilisateurDTO.getGroupes().isEmpty())
		{
			List<Groupe> lesGroupes = new ArrayList<Groupe>();
			for (GroupeDTO groupeDTO : utilisateurDTO.getGroupes()) {
				Groupe grp = null;
				if (groupeDTO.getIdGroupe() != null) {
					grp = em.find(Groupe.class, groupeDTO.getIdGroupe());
				} else if (groupeDTO.getNomGroupe() != null) {
					Query q = em
							.createQuery("select g from Groupe g where g.nomGroupe = '" + groupeDTO.getNomGroupe() + "'");
					grp = (Groupe) q.getSingleResult();
	
				}
				if(grp!=null){
					lesGroupes.add(grp);
					grp.getUtilisateurs().add(utilisateur);
					em.persist(grp);
				}
			}
			utilisateur.setGroupes(lesGroupes);
		}
		
		if(utilisateurDTO.getGroupesGeres()!=null && !utilisateurDTO.getGroupesGeres().isEmpty()){
			List<Groupe> lesGroupesGeres = new ArrayList<Groupe>();
			for (GroupeDTO groupeDTO : utilisateurDTO.getGroupesGeres()) {
				Groupe grp = new Groupe();
				if (groupeDTO.getIdGroupe() != null) {
					grp = em.find(Groupe.class, groupeDTO.getIdGroupe());
				} else if (groupeDTO.getNomGroupe() != null) {
					Query q = em
							.createQuery("select g from Groupe g where g.nomGroupe = '" + groupeDTO.getNomGroupe() + "'");
					grp = (Groupe) q.getSingleResult();
	
				}
				if(grp!=null){
					lesGroupesGeres.add(grp);
					grp.getUtilisateurs().add(utilisateur);
					em.persist(grp);
				}
			}
			utilisateur.setGroupesGeres(lesGroupesGeres);
		}
		
		//Gestion profil
		if(utilisateurDTO.getProfil()!=null){
			Profil pro = new Profil();
			pro.setCentreInteret(utilisateurDTO.getProfil().getCentreInteret());
			//pro.setCompetence(utilisateurDTO.getProfil().getCompetence());
			if(utilisateurDTO.getProfil().getCompetence()!=null && !utilisateurDTO.getProfil().getCompetence().isEmpty()){
				List<Competence> listComp = new ArrayList<Competence>();
				for(CompetenceDTO compDTO : utilisateurDTO.getProfil().getCompetence()){
					Competence comp = new Competence();
					comp.setLibelle(compDTO.getLibelle());
					comp.setNote(compDTO.getNote());
					comp.setProfil(pro);
					listComp.add(comp);
					em.persist(comp);
				}
				pro.setCompetence(listComp);
			}
			pro.setCursus(utilisateurDTO.getProfil().getCursus());
			//pro.setDiplomes(utilisateurDTO.getProfil().getDiplomes());
			if(utilisateurDTO.getProfil().getDiplomes()!=null && !utilisateurDTO.getProfil().getDiplomes().isEmpty()){
				List<Diplome> listDipl = new ArrayList<Diplome>();
				for(DiplomeDTO diplDTO : utilisateurDTO.getProfil().getDiplomes()){
					Diplome dipl = new Diplome();
					dipl.setAnneeDebut(diplDTO.getAnneeDebut());
					dipl.setAnneFin(diplDTO.getAnneFin());
					dipl.setLibelle(diplDTO.getLibelle());
					dipl.setProfil(pro);
					listDipl.add(dipl);
					em.persist(dipl);
				}
				pro.setDiplomes(listDipl);
			}
			pro.setTelephone(utilisateurDTO.getProfil().getTelephone());
			
			if(utilisateurDTO.getProfil().getExperiences()!=null && !utilisateurDTO.getProfil().getExperiences().isEmpty()){
				List<Experience> listExp = new ArrayList<Experience>();
				for(ExperienceDTO expDTO : utilisateurDTO.getProfil().getExperiences()){
					Experience exp = new Experience();
					exp.setAnneeDebut(expDTO.getAnneeDebut());
					exp.setAnneFin(expDTO.getAnneFin());
					exp.setDescription(expDTO.getDescription());
					exp.setEntreprise(expDTO.getEntreprise());
					exp.setLieu(expDTO.getLieu());
					exp.setPoste(expDTO.getPoste());
					exp.setProfil(pro);
					listExp.add(exp);
					em.persist(exp);
				}
				pro.setExperiences(listExp);
			}
			em.persist(pro);
			utilisateur.setProfil(pro);

		}
		em.persist(utilisateur);

		if(utilisateur!=null && utilisateur.getStatut()!=null && utilisateur.getEmail()!=null){
			UtilisateurGroupes utilGrp = new UtilisateurGroupes();
			utilGrp.setEmail(utilisateur.getEmail());
			if(utilisateur.getStatut()==Statut.DIPLOME){
				utilGrp.setGroupe("diplome");
			}
			em.persist(utilGrp);
		}
		
		return MappingToDTO.utilisateurToUtilisateurDTO(utilisateur);

	}

	/* (non-Javadoc)
	 * @see ipint.glp.api.itf.UtilisateurService#trouver(ipint.glp.api.DTO.UtilisateurDTO)
	 */
	@Override
	public UtilisateurDTO trouver(UtilisateurDTO utilisateurDTO) {
		Utilisateur utilisateur = new Utilisateur();
		if (utilisateurDTO.getIdUtilisateur() != null) {
			//utilisateur = em.find(Utilisateur.class, utilisateurDTO.getIdUtilisateur());
			Query q = em.createQuery("select u from Utilisateur u where u.idUtilisateur = '" + utilisateurDTO.getIdUtilisateur() + "'");
			utilisateur = (Utilisateur) q.getSingleResult();
 
		} else if (utilisateurDTO.getEmail() != null) {
			Query q = em.createQuery("select u from Utilisateur u where u.email = '" + utilisateurDTO.getEmail() + "'");
			utilisateur = (Utilisateur) q.getSingleResult();
		}
		
		utilisateurDTO = MappingToDTO.utilisateurToUtilisateurDTO(utilisateur);
		return utilisateurDTO;
	}

	/* (non-Javadoc)
	 * @see ipint.glp.api.itf.UtilisateurService#modifier(ipint.glp.api.DTO.UtilisateurDTO, ipint.glp.api.DTO.UtilisateurDTO)
	 */
	@Override
	public UtilisateurDTO modifier(UtilisateurDTO ancienUtilisateur, UtilisateurDTO nouvelUtilisateur) {

		Utilisateur utilisateurMAJ = em.find(Utilisateur.class, ancienUtilisateur.getIdUtilisateur());

		if (nouvelUtilisateur.getStatut() != null) {
			utilisateurMAJ.setStatut(nouvelUtilisateur.getStatut());
		}
		if(nouvelUtilisateur.getNom()!=null && !"".equals(nouvelUtilisateur.getNom())){
			utilisateurMAJ.setNom(nouvelUtilisateur.getNom());
		}
		if(nouvelUtilisateur.getPassword()!=null && !"".equals(nouvelUtilisateur.getPassword())){
			utilisateurMAJ.setPassword(nouvelUtilisateur.getPassword());
		}
		if(nouvelUtilisateur.getPrenom()!=null && !"".equals(nouvelUtilisateur.getPrenom())){
			utilisateurMAJ.setPrenom(nouvelUtilisateur.getPrenom());
		}
		if(nouvelUtilisateur.getEmail()!=null && !"".equals(nouvelUtilisateur.getEmail())){
			utilisateurMAJ.setEmail(nouvelUtilisateur.getEmail());
		}

		if (nouvelUtilisateur.getProfil() != null) {
			Profil profil = new Profil();
			profil = em.find(Profil.class, ancienUtilisateur.getProfil().getIdProfil());
			utilisateurMAJ.setProfil(profil);
			//profil.setCompetence(nouvelUtilisateur.getProfil().getCompetence());
			List<Competence> lesComps = new ArrayList<Competence>();
			for (CompetenceDTO compDTO : nouvelUtilisateur.getProfil().getCompetence()) {
				if (compDTO.getIdCompetence() != null) {
					lesComps.add(em.find(Competence.class, compDTO.getIdCompetence()));
				}
			}
			profil.setCompetence(lesComps);
			profil.setCentreInteret(nouvelUtilisateur.getProfil().getCentreInteret());
			profil.setCursus(nouvelUtilisateur.getProfil().getCursus());
			//profil.setDiplomes(nouvelUtilisateur.getProfil().getDiplomes());
			List<Diplome> lesDipls = new ArrayList<Diplome>();
			for (DiplomeDTO diplDTO : nouvelUtilisateur.getProfil().getDiplomes()) {
				if (diplDTO.getIdDiplome() != null) {
					lesDipls.add(em.find(Diplome.class, diplDTO.getIdDiplome()));
				}
			}
			profil.setDiplomes(lesDipls);
			profil.setTelephone(nouvelUtilisateur.getProfil().getTelephone());
		}

//		List<Article> lesArticles = new ArrayList<Article>();
//		for (ArticleDTO articleDTO : nouvelUtilisateur.getArticles()) {
//
//			lesArticles.add(em.find(Article.class, articleDTO.getIdArticle()));
//		}
//
//		utilisateurMAJ.setArticles(lesArticles);

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
//		List<Groupe> lesGroupesGeres = new ArrayList<Groupe>();
//		for (GroupeDTO groupeDTO : nouvelUtilisateur.getGroupesGeres()) {
//			if (groupeDTO.getIdGroupe() != null) {
//				lesGroupesGeres.add(em.find(Groupe.class, groupeDTO.getIdGroupe()));
//			} else if (groupeDTO.getNomGroupe() != null) {
//				Query q = em
//						.createQuery("select g from Groupe g where g.nomGroupe = '" + groupeDTO.getNomGroupe() + "'");
//				lesGroupesGeres.add((Groupe) q.getSingleResult());
//
//			}
//		}
//		utilisateurMAJ.setGroupesGeres(lesGroupesGeres);

		em.persist(utilisateurMAJ);

		return MappingToDTO.utilisateurToUtilisateurDTO(utilisateurMAJ);

	}

	/* (non-Javadoc)
	 * @see ipint.glp.api.itf.UtilisateurService#supprimer(ipint.glp.api.DTO.UtilisateurDTO)
	 */
	@Override
	public void supprimer(UtilisateurDTO obj) {
		// TODO Auto-generated method stub

	}
}
