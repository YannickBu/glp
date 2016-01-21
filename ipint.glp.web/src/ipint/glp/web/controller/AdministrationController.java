package ipint.glp.web.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.UtilisateurEnAttenteDTO;
import ipint.glp.api.itf.UtilisateurEnAttenteService;

/**
 * 
 * 
 * 
 * @author hequet
 *
 */
@Controller
public class AdministrationController {

	@Inject
	UtilisateurEnAttenteService utilisateurEnAttenteService;

	public AdministrationController() {
	}

	@RequestMapping(value = "/panelInscription/{id}", method = RequestMethod.GET)
	public ModelAndView profilGet(@PathVariable String id, @ModelAttribute UtilisateurEnAttenteDTO utilisateurTmp,
			Model model) {
		UtilisateurEnAttenteDTO uDTO = new UtilisateurEnAttenteDTO();
		uDTO = utilisateurEnAttenteService.trouver(Integer.parseInt(id));
		model.addAttribute("utilisateurTmp", uDTO);
		List<UtilisateurEnAttenteDTO> list = utilisateurEnAttenteService.lister();
		model.addAttribute("list", list);
		return new ModelAndView("panelInscription");
	}

	@RequestMapping(value = "/panelInscription", method = RequestMethod.GET)
	public ModelAndView administrationGET(@ModelAttribute UtilisateurEnAttenteDTO utilisateurTmp,
			Model model) {
		List<UtilisateurEnAttenteDTO> list = utilisateurEnAttenteService.lister();
		model.addAttribute("list", list);
		return new ModelAndView("menuInscriptionVide");
	}

	@RequestMapping(value = "/panelInscription", method = RequestMethod.POST)
	public ModelAndView administrationPOST(@ModelAttribute UtilisateurEnAttenteDTO utilisateurTmp,
			Model model) {
		List<UtilisateurEnAttenteDTO> list = utilisateurEnAttenteService.lister();
		model.addAttribute("list", list);
		return new ModelAndView("menuInscriptionVide");
	}

	@RequestMapping(value = "/panelInscription/{id}", method = RequestMethod.POST)
	public ModelAndView profilPost(@RequestParam String action, @PathVariable String id,
			@ModelAttribute UtilisateurEnAttenteDTO utilisateurTmp, Model model) {
		UtilisateurEnAttenteDTO uDTO = new UtilisateurEnAttenteDTO();
		uDTO = utilisateurEnAttenteService.trouver(Integer.parseInt(id));
		if (action.equals("Accepter")) {
			utilisateurEnAttenteService.valider(uDTO);
		} else if (action.equals("Refuser")) {
			utilisateurEnAttenteService.supprimer(uDTO);
		}
		List<UtilisateurEnAttenteDTO> list = utilisateurEnAttenteService.lister();
		model.addAttribute("utilisateurTmp", uDTO);
		System.out.println(list.toString());
		if(!list.isEmpty()){
			int idPremierList = list.get(0).getIdUtilisateurEnAttente();
			return new ModelAndView("redirect:/panelInscription/" + idPremierList);
		}
		else {
			return new ModelAndView("redirect:/panelInscription");
		}
	}
}
