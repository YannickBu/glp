package ipint.glp.web.controller;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.ExperienceDTO;
import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.ProfilDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.DTO.enumType.Statut;
import ipint.glp.api.itf.ArticleService;
import ipint.glp.api.itf.GroupeService;
import ipint.glp.api.itf.UtilisateurService;

@Controller
public class ControllerTest {

	@Inject
	ArticleService artS;
	@Inject
	UtilisateurService utilS;
	@Inject
	GroupeService groupeS;
	
	public ControllerTest() {}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcomeGet() {
//		ArticleDTO art = new ArticleDTO();
//		art.setContenu("du contenu");
//		art = s.creerArticle(art);
//		System.out.println(art.getContenu());
//		System.out.println(art.getUtilisateur().getPrenom());
		
		
//		
//		
//		try{
//			ProfilDTO pDTO = new ProfilDTO();
//			pDTO.setCentreInteret("cent");
//		//	pDTO.setCompetence("comp");
//			pDTO.setCursus("cur");
//			pDTO.setTelephone("0311111111");
//			
//			UtilisateurDTO utilDTO = new UtilisateurDTO();
//			utilDTO.setEmail("yannick.buchart@g.com");
//			utilDTO.setNom("Buchart");
//			utilDTO.setPrenom("Yannick");
//			utilDTO.setPassword("psw");
//			utilDTO.setStatut(Statut.DIPLOME);
//			utilDTO.setProfil(pDTO);
//			
//			utilDTO = utilS.creer(utilDTO);
//			if(utilDTO!=null)
//				System.out.println("idUtil : " + utilDTO.getIdUtilisateur());
//				System.out.println(utilDTO.getEmail());
//			if(utilDTO.getProfil()!=null){
//				System.out.println(utilDTO.getProfil().getCentreInteret());
//			}
//		}
//		catch (Exception e){
//			
//		}
		
		//Creation utilisateur Manon
				ProfilDTO pDTO = new ProfilDTO();
				pDTO.setCentreInteret("Shopping, Cinema");
				List<String> comp = new ArrayList<String>();
				comp.add("JAVA");
				comp.add("SQL");
				pDTO.setCompetence(comp);
				pDTO.setCursus("");
				pDTO.setTelephone("06.20.30.20.52");
				List<String> dipl = new ArrayList<String>();
				dipl.add("2015/2016 - M2MIAGE");
				dipl.add("2012/2013 - L3MIAGE");
				dipl.add("2010/2011 - DUT Informatique");
				pDTO.setDiplomes(dipl);
				
				ExperienceDTO expDTO = new ExperienceDTO();
				expDTO.setAnneeDebut(2011);
				expDTO.setAnneFin(2013);
				expDTO.setDescription("Developpement d'un site web");
				expDTO.setEntreprise("Nextoo");
				expDTO.setLieu("Lille");
				expDTO.setPoste("Developpeur web");
				List<ExperienceDTO> exp = new ArrayList<ExperienceDTO>();
				exp.add(expDTO);
				pDTO.setExperiences(exp);
				
				UtilisateurDTO utilDTO = new UtilisateurDTO();
				utilDTO.setEmail("manon.barrois@gmail.com");
				utilDTO.setNom("Barrois");
				utilDTO.setPrenom("Manon");
				utilDTO.setPassword("psw");
				utilDTO.setStatut(Statut.DIPLOME);
				GroupeDTO gDTO = new GroupeDTO();
				gDTO.setNomGroupe("MIAGE");
				gDTO = groupeS.trouver(gDTO);
				GroupeDTO gDTO2 = new GroupeDTO();
				gDTO2.setNomGroupe("SIAD");
				gDTO2 = groupeS.trouver(gDTO2);		
				List<GroupeDTO> grp = new ArrayList<GroupeDTO>();
				grp.add(gDTO);
				grp.add(gDTO2);
				utilDTO.setGroupes(grp);
				utilDTO.setProfil(pDTO);
			
	
				
				utilDTO = utilS.creer(utilDTO);
		
				
		return new ModelAndView("accueil");
	
	}
	
	@RequestMapping(value="/connexion", method=RequestMethod.GET)
	public ModelAndView loginGet() {
		return new ModelAndView("connexion");
	}
	
	@RequestMapping(value="/profil", method=RequestMethod.GET)
	public ModelAndView profilGet() {
		return new ModelAndView("profil");
	}
}
