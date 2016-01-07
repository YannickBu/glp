package ipint.glp.web.controller;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
		
		
		
		
		try{
			ProfilDTO pDTO = new ProfilDTO();
			pDTO.setCentreInteret("cent");
			pDTO.setCompetence("comp");
			pDTO.setCursus("cur");
			pDTO.setTelephone("0311111111");
			
			UtilisateurDTO utilDTO = new UtilisateurDTO();
			utilDTO.setEmail("yannick.buchart@g.com");
			utilDTO.setNom("Buchart");
			utilDTO.setPrenom("Yannick");
			utilDTO.setPassword("psw");
			utilDTO.setStatut(Statut.DIPLOME);
			utilDTO.setProfil(pDTO);
			
			utilDTO = utilS.creer(utilDTO);
			if(utilDTO!=null)
				System.out.println("idUtil : " + utilDTO.getIdUtilisateur());
				System.out.println(utilDTO.getEmail());
			if(utilDTO.getProfil()!=null){
				System.out.println(utilDTO.getProfil().getCentreInteret());
			}
		}
		catch (Exception e){
			


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
