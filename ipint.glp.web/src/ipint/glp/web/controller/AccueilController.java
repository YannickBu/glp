package ipint.glp.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.itf.GroupeService;



@Controller
public class AccueilController {
	@Inject
	GroupeService gs;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView accueil() {
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
		
		return new ModelAndView("redirect:/publication");
	}
}
