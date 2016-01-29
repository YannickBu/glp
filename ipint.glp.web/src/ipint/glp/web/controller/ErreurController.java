package ipint.glp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author hequet
 *
 */
@Controller
public class ErreurController {
	@RequestMapping(value = "/erreur")
	public ModelAndView erreurGET() {
		return new ModelAndView("erreur");
	}
	
	@RequestMapping(value = "/construction")
	public ModelAndView constructionGET() {
		return new ModelAndView("construction");
	}
}
