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
public class GroupeController {
	@RequestMapping(value = "/groupe")
	public ModelAndView erreurGET() {
		return new ModelAndView("groupe");
	}
}
