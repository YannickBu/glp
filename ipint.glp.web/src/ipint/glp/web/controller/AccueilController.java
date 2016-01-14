package ipint.glp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccueilController {
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView accueil() {
		return new ModelAndView("redirect:/connexion");
	}
}
