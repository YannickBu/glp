package ipint.glp.web.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.itf.UtilisateurService;

@Controller
public class AdministrationController {

	@Inject
	UtilisateurService utilisateurService;

	public AdministrationController() {
	}

	@RequestMapping(value = "/panelInscription", method = RequestMethod.GET)
	public ModelAndView profilGet(@ModelAttribute UtilisateurDTO utilisateurTmp, Model model) {
		UtilisateurDTO uDTO = new UtilisateurDTO();
		model.addAttribute("utilisateurTmp", uDTO);
		return new ModelAndView("panelInscription");
	}
}
