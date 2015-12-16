package ipint.glp.web.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.itf.UtilisateurService;

@Controller
public class ProfilController {

	@Inject
	UtilisateurService utilisateurService;

	public ProfilController() {
	}

	@RequestMapping(value = "/profil/{id}", method = RequestMethod.GET)
	public ModelAndView profilGet(@PathVariable String id, @ModelAttribute UtilisateurDTO utilisateur) {
		ModelAndView model = new ModelAndView("profil");
		utilisateur.setIdUtilisateur(Integer.parseInt(id));
		utilisateur = utilisateurService.trouver(utilisateur);
		model.addObject("profil", utilisateur);
		return new ModelAndView("profil");
	}
}
