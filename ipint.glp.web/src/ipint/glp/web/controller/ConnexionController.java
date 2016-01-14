package ipint.glp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConnexionController {

	@RequestMapping(value="/connexion", method=RequestMethod.GET)
	public ModelAndView loginGet() {
		return new ModelAndView("connexion");
	}
	@RequestMapping(value="/connexion", method=RequestMethod.POST)
	public ModelAndView connexionPost() {
		return new ModelAndView("profil");
	}

	@RequestMapping(value="/error", method=RequestMethod.POST)
	public ModelAndView connexionErrorPost() {
		return new ModelAndView("connexion");
	}

	@RequestMapping(value="/error", method=RequestMethod.GET)
	public ModelAndView connexionErrorGet() {
		return new ModelAndView("connexion");
	}
}
