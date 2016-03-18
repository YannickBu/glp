package ipint.glp.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.itf.GroupeService;
import ipint.glp.api.itf.UtilisateurService;



@Controller
public class AccueilController {
	private Logger logger = Logger.getLogger("ArticleController");
	@Inject
	GroupeService gs;
	@Inject
	UtilisateurService us;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView accueil(HttpServletRequest request, Model model, @ModelAttribute UtilisateurDTO utilisateur) {
		UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setEmail(request.getUserPrincipal().getName());
		try {
			uDTO = us.trouver(uDTO);
		} catch (MetierException e) {
			logger.severe("Erreur acces publication POST - UtilisateurService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}
		GroupeDTO g = new GroupeDTO();
		g.setIdGroupe(1);
		List<UtilisateurDTO> utilisateurs=new ArrayList<>();
		try {
			utilisateurs = gs.listerUtilisateurs(g);
		} catch (MetierException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		for(UtilisateurDTO u : utilisateurs){
			System.out.println(u.getEmail());
		}
		model.addAttribute("utilisateur", uDTO);
		return new ModelAndView("redirect:/connexion");
	}
}
