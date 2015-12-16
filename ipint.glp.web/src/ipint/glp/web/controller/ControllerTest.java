package ipint.glp.web.controller;


import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.ArticleDTO;
import ipint.glp.api.itf.ArticleService;

@Controller
public class ControllerTest {

//	@Inject
//	ArticleService s;

	public ControllerTest() {}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView welcomeGet() {
		//ArticleDTO art = new ArticleDTO();
		//art.setContenu("du contenu");
		//art = s.creerArticle(art);
		//System.out.println(art.getContenu());
		return new ModelAndView("accueil");
	}
	
	@RequestMapping(value="/connexion", method=RequestMethod.GET)
	public ModelAndView loginGet() {
		return new ModelAndView("connexion");
	}
	
	@RequestMapping(value="/inscription", method=RequestMethod.GET)
	public ModelAndView inscriptionGet() {
		return new ModelAndView("inscription");
	}
	
	@RequestMapping(value="/profil", method=RequestMethod.GET)
	public ModelAndView profilGet() {
		return new ModelAndView("profil");
	}
}
