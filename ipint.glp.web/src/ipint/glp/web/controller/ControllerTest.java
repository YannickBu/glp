package ipint.glp.web.controller;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.CompetenceDTO;
import ipint.glp.api.DTO.DiplomeDTO;
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

//				ProfilDTO pDTOm = new ProfilDTO();
//				pDTOm.setCentreInteret("Shopping, Cinema");
//				//ArrayList<String> comp = new ArrayList<String>();
//				//comp.add("JAVA");
//				//comp.add("SQL");
//				//pDTOm.setCompetence(comp);
//				CompetenceDTO compDTO = new CompetenceDTO();
//				compDTO.setLibelle("Java J2EE");
//				compDTO.setNote(3);
//				List<CompetenceDTO> comp = new ArrayList<CompetenceDTO>();
//				comp.add(compDTO);
//				compDTO = new CompetenceDTO();
//				compDTO.setLibelle("Angular JS");
//				compDTO.setNote(2);
//				comp.add(compDTO);
//				pDTOm.setCompetence(comp);
//				pDTOm.setCursus("");
//				pDTOm.setTelephone("06.20.30.20.52");
//				//ArrayList<String> dipl = new ArrayList<String>();
//				//dipl.add("2015/2016 - M2MIAGE");
//				//dipl.add("2012/2013 - L3MIAGE");
//				//dipl.add("2010/2011 - DUT Informatique");
//				//pDTOm.setDiplomes(dipl);
//				DiplomeDTO diplDTO = new DiplomeDTO();
//				diplDTO.setAnneeDebut(2011);
//				diplDTO.setAnneFin(2013);
//				diplDTO.setLibelle("DUT Informatique");
//				List<DiplomeDTO> dipl = new ArrayList<DiplomeDTO>();
//				dipl.add(diplDTO);
//				diplDTO = new DiplomeDTO();
//				diplDTO.setAnneeDebut(2009);
//				diplDTO.setAnneFin(2010);
//				diplDTO.setLibelle("Master 2 MIAGE");
//				dipl.add(diplDTO);
//				pDTOm.setDiplomes(dipl);
//				
//				ExperienceDTO expDTO = new ExperienceDTO();
//				expDTO.setAnneeDebut(2011);
//				expDTO.setAnneFin(2013);
//				expDTO.setDescription("Developpement d'un site web");
//				expDTO.setEntreprise("Nextoo");
//				expDTO.setLieu("Lille");
//				expDTO.setPoste("Developpeur web");
//				List<ExperienceDTO> exp = new ArrayList<ExperienceDTO>();
//				exp.add(expDTO);
//				pDTOm.setExperiences(exp);
//				
//				UtilisateurDTO utilDTOm = new UtilisateurDTO();
//				utilDTOm.setEmail("sarra.diagne6@gmail.com");
//				utilDTOm.setNom("Barrois");
//				utilDTOm.setPrenom("Manon");
//				utilDTOm.setPassword("psw");
//				utilDTOm.setStatut(Statut.DIPLOME);
//				GroupeDTO gDTO = new GroupeDTO();
//				gDTO.setNomGroupe("MIAGE");
//				gDTO = groupeS.trouver(gDTO);
//				GroupeDTO gDTO2 = new GroupeDTO();
//				gDTO2.setNomGroupe("SIAD");
//				gDTO2 = groupeS.trouver(gDTO2);		
//				List<GroupeDTO> grp = new ArrayList<GroupeDTO>();
//				grp.add(gDTO);
//				grp.add(gDTO2);
//				utilDTOm.setGroupes(grp);
//				GroupeDTO gPrincDTO = new GroupeDTO();
//				gPrincDTO.setNomGroupe("Informatique");
//				gPrincDTO = groupeS.trouver(gPrincDTO);
//				utilDTOm.setGroupePrincipal(gPrincDTO);
//				
//				utilDTOm.setProfil(pDTOm);	
//				
//				utilDTOm = utilS.creer(utilDTOm);

				ProfilDTO pDTOm = new ProfilDTO();
				pDTOm.setCentreInteret("Shopping, Cinema");
				//ArrayList<String> comp = new ArrayList<String>();
				//comp.add("JAVA");
				//comp.add("SQL");
				//pDTOm.setCompetence(comp);
				CompetenceDTO compDTO = new CompetenceDTO();
				compDTO.setLibelle("Java J2EE");
				compDTO.setNote(3);
				List<CompetenceDTO> comp = new ArrayList<CompetenceDTO>();
				comp.add(compDTO);
				compDTO = new CompetenceDTO();
				compDTO.setLibelle("Angular JS");
				compDTO.setNote(2);
				comp.add(compDTO);
				pDTOm.setCompetence(comp);
				pDTOm.setCursus("");
				pDTOm.setTelephone("06.20.30.20.52");
				//ArrayList<String> dipl = new ArrayList<String>();
				//dipl.add("2015/2016 - M2MIAGE");
				//dipl.add("2012/2013 - L3MIAGE");
				//dipl.add("2010/2011 - DUT Informatique");
				//pDTOm.setDiplomes(dipl);
				DiplomeDTO diplDTO = new DiplomeDTO();
				diplDTO.setAnneeDebut(2011);
				diplDTO.setAnneFin(2013);
				diplDTO.setLibelle("DUT Informatique");
				List<DiplomeDTO> dipl = new ArrayList<DiplomeDTO>();
				dipl.add(diplDTO);
				diplDTO = new DiplomeDTO();
				diplDTO.setAnneeDebut(2009);
				diplDTO.setAnneFin(2010);
				diplDTO.setLibelle("Master 2 MIAGE");
				dipl.add(diplDTO);
				pDTOm.setDiplomes(dipl);
				
				ExperienceDTO expDTO = new ExperienceDTO();
				expDTO.setAnneeDebut(2011);
				expDTO.setAnneFin(2013);
				expDTO.setDescription("Developpement d'un site web");
				expDTO.setEntreprise("Nextoo");
				expDTO.setLieu("Lille");
				expDTO.setPoste("Developpeur web");
				List<ExperienceDTO> exp = new ArrayList<ExperienceDTO>();
				exp.add(expDTO);
				pDTOm.setExperiences(exp);
				
				UtilisateurDTO utilDTOm = new UtilisateurDTO();
				utilDTOm.setEmail("sar.diagne6@gmail.com");
				utilDTOm.setNom("Barrois");
				utilDTOm.setPrenom("Manon");
				utilDTOm.setPassword("psw");
				utilDTOm.setStatut(Statut.DIPLOME);
				GroupeDTO gDTO = new GroupeDTO();
				gDTO.setNomGroupe("MIAGE");
				gDTO = groupeS.trouver(gDTO);
				GroupeDTO gDTO2 = new GroupeDTO();
				gDTO2.setNomGroupe("SIAD");
				gDTO2 = groupeS.trouver(gDTO2);		
				List<GroupeDTO> grp = new ArrayList<GroupeDTO>();
				grp.add(gDTO);
				grp.add(gDTO2);
				utilDTOm.setGroupes(grp);
				GroupeDTO gPrincDTO = new GroupeDTO();
				gPrincDTO.setNomGroupe("Informatique");
				gPrincDTO = groupeS.trouver(gPrincDTO);
				utilDTOm.setGroupePrincipal(gPrincDTO);
				
				utilDTOm.setProfil(pDTOm);	
				
				utilDTOm = utilS.creer(utilDTOm);


			return new ModelAndView("redirect:/profil/2");
	
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
