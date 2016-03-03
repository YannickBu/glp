package ipint.glp.web.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurEnAttenteDTO;

/**
 * 
 * 
 * 
 * @author hequet
 *
 */
@Controller
public class AdministrationController {
	private Logger logger = Logger.getLogger("AdministrationController");

	public AdministrationController() {
	}
	
	@RequestMapping(value = "/createGroupe", method = RequestMethod.GET)
	public ModelAndView createGroupeGet(@ModelAttribute("groupeTmp") GroupeDTO groupeTmp,
			BindingResult result, Model model) {
		return new ModelAndView("createGroupe");
	}


	@RequestMapping(value = "/createGroupe", method = RequestMethod.POST)
	public ModelAndView createGroupePost(@ModelAttribute("groupeTmp") GroupeDTO groupeTmp,
			BindingResult result, Model model) {
		return new ModelAndView("createGroupe");
	}
	
}
