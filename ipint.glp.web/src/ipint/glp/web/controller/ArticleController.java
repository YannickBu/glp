package ipint.glp.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.ArticleDTO;
import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.itf.ArticleService;
import ipint.glp.api.itf.GroupeService;
import ipint.glp.api.itf.UtilisateurService;

@Controller
public class ArticleController {
	private Logger logger = Logger.getLogger("ArticleController");

	@Inject
	ArticleService as;
	@Inject
	UtilisateurService us;
	@Inject
	GroupeService gs;

	@RequestMapping(value = "/publication", method = RequestMethod.GET)
	public ModelAndView welcomeGet(HttpServletRequest request, Model model) {
		List<ArticleDTO> articles=new ArrayList<ArticleDTO>();
		UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setEmail(request.getUserPrincipal().getName());
		try {
			uDTO = us.trouver(uDTO);
			List<GroupeDTO> groupes = uDTO.getGroupes();
			GroupeDTO groupePrincipal = uDTO.getGroupePrincipal();
			groupes.add(groupePrincipal);
			//for(GroupeDTO groupe : groupes){
				for(ArticleDTO articleDTO : as.listerParDate(groupes)){
					articles.add(articleDTO);
				}
			//}
		} catch (MetierException e) {
			logger.severe("Erreur acces publication GET - UtilisateurService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}

		model.addAttribute("articles", articles);
		model.addAttribute("utilisateur", uDTO);
		model.addAttribute("groupePrincipal", uDTO.getGroupePrincipal());
		return new ModelAndView("accueil", "article", new ArticleDTO());
	}

	@RequestMapping(value = "/publication", method = RequestMethod.POST)
	public ModelAndView publicationGet(HttpServletRequest request, @ModelAttribute("article") ArticleDTO article,
			BindingResult result, Model model) throws MetierException {

		UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setEmail(request.getUserPrincipal().getName());
		try {
			uDTO = us.trouver(uDTO);
		} catch (MetierException e) {
			logger.severe("Erreur acces publication POST - UtilisateurService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}

		ArticleDTO articleDto = new ArticleDTO();
		articleDto.setContenu(article.getContenu());
		articleDto.setTitre(article.getTitre());
		Calendar cal = Calendar.getInstance();
		articleDto.setDatePublication(cal);
		articleDto.setUtilisateur(uDTO);
		System.out.println("///////////// Id :" + article.getGroupe().getIdGroupe());
		GroupeDTO grp = new GroupeDTO();
		grp.setIdGroupe(article.getGroupe().getIdGroupe());
		grp = gs.trouver(grp);
		articleDto.setGroupe(grp);
		System.out.println("***************Groupe="+grp.getNomGroupe());
		try {
			articleDto = as.creer(articleDto);
		} catch (MetierException e) {
			logger.severe("Erreur acces publication POST - ArticleService.creer renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}
		List<ArticleDTO> articles = new ArrayList<ArticleDTO>();
		List<GroupeDTO> groupes = uDTO.getGroupes();
		GroupeDTO groupePrincipal = uDTO.getGroupePrincipal();
		groupes.add(groupePrincipal);
		//for(GroupeDTO groupe : groupes){
			for(ArticleDTO articleDTO : as.listerParDate(groupes)){
				articles.add(articleDTO);
			}
	//	}
		model.addAttribute("articles", articles);
		model.addAttribute("utilisateur", uDTO);
		model.addAttribute("groupePrincipal", articleDto.getGroupe());
		return new ModelAndView("accueil");
	}

}
