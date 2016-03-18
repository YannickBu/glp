package ipint.glp.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ModelAndView welcomeGet(HttpServletRequest request, Model model, @ModelAttribute UtilisateurDTO utilisateur, @ModelAttribute("recherche") String recherche) throws MetierException {
		List<ArticleDTO> articles = new ArrayList<ArticleDTO>();
		UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setEmail(request.getUserPrincipal().getName());
		try {
			uDTO = us.trouver(uDTO);
			List<GroupeDTO> groupes = uDTO.getGroupes();
//			List<GroupeDTO> groupes2 = new ArrayList<>();
//			groupes2.addAll(groupes);
			GroupeDTO groupePrincipal = uDTO.getGroupePrincipal();
//			System.out.println("ArticleController " + "welcomeGet" + groupes);
			groupes.add(groupePrincipal);
			articles = as.listerParDate(groupes);
		} catch (MetierException e) {
			logger.severe("Erreur acces publication GET - UtilisateurService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		} 
		
		List<GroupeDTO> tousLesGroupes = gs.listerTousLesGroupes();
		tousLesGroupes.remove(uDTO.getGroupePrincipal());
		for(GroupeDTO groupe1 : uDTO.getGroupes()){
			for(GroupeDTO groupe2 : tousLesGroupes){
				if(groupe1.equals(groupe2)){
					groupe2=null;
				}
			}
		}
		List<GroupeDTO> nouvelle = new ArrayList<GroupeDTO>(tousLesGroupes); 
		Collections.shuffle(nouvelle);
		model.addAttribute("tousLesGroupes", nouvelle);
		model.addAttribute("articles", articles);
		model.addAttribute("utilisateur", uDTO);
		return new ModelAndView("accueil", "article", new ArticleDTO());
	}

	@RequestMapping(value = "/publication", method = RequestMethod.POST)
	public ModelAndView publicationGet(HttpServletRequest request, @Valid @ModelAttribute("article") ArticleDTO article,
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
//		System.out.println(" ArticleController - publicationGet - article.getGroupe().getIdGroupe() = "
//				+ article.getGroupe().getIdGroupe());
		GroupeDTO grp = new GroupeDTO();
		grp.setIdGroupe(article.getGroupe().getIdGroupe());
		grp = gs.trouver(grp);
		articleDto.setGroupe(grp);
//		System.out.println(" ArticleController - publicationGet - grp.getNomGroupe() = " + grp.getNomGroupe());
		if(!result.hasErrors()){
			try {
				articleDto = as.creer(articleDto);
			} catch (MetierException e) {
				logger.severe("Erreur acces publication POST - ArticleService.creer renvoie : " + e.getMessage());
				return new ModelAndView("redirect:/erreur");
			}
		}
		List<ArticleDTO> articles = new ArrayList<ArticleDTO>();
		List<GroupeDTO> groupes = uDTO.getGroupes();
		GroupeDTO groupePrincipal = uDTO.getGroupePrincipal();
		groupes.add(groupePrincipal);

		articles = as.listerParDate(groupes);

		model.addAttribute("articles", articles);
		model.addAttribute("utilisateur", uDTO);
		model.addAttribute("groupePrincipal", articleDto.getGroupe());
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/supprimerArticle/{id}", method = RequestMethod.GET)
	public ModelAndView supprimer(HttpServletRequest request,  @PathVariable String id,
			@ModelAttribute ArticleDTO article, Model model) throws MetierException {

		article.setIdArticle(Integer.parseInt(id));
		try {
			article = as.trouver(article);
		} catch (MetierException e) {
			logger.severe("Erreur acces publication POST - ArticleService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}
		as.supprimer(article);
		return new ModelAndView("redirect:/");
	}
	

}
