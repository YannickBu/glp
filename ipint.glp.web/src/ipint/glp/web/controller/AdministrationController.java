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
import ipint.glp.api.exception.MetierException;
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
		try {
			uDTO = utilisateurEnAttenteService.trouver(Integer.parseInt(id));
		} catch (MetierException e) {
			//TODO redirection vers une page d'erreur
		}
		model.addAttribute("utilisateurTmp", uDTO);
		List<UtilisateurEnAttenteDTO> list;
		try {
			list = utilisateurEnAttenteService.lister();
			model.addAttribute("list", list);
		} catch (MetierException e) {
			//TODO redirection vers une page d'erreur
		}
		return new ModelAndView("panelInscription");
	}

	@RequestMapping(value = "/panelInscription", method = RequestMethod.GET)
	public ModelAndView administrationGET(@ModelAttribute UtilisateurEnAttenteDTO utilisateurTmp,
			Model model) {
		List<UtilisateurEnAttenteDTO> list;
		try {
			list = utilisateurEnAttenteService.lister();
			model.addAttribute("list", list);
		} catch (MetierException e) {
			//TODO redirection vers une page d'erreur
		}
		return new ModelAndView("menuInscriptionVide");
	}

	@RequestMapping(value = "/panelInscription", method = RequestMethod.POST)
	public ModelAndView administrationPOST(@ModelAttribute UtilisateurEnAttenteDTO utilisateurTmp,
			Model model) {
		List<UtilisateurEnAttenteDTO> list;
		try {
			list = utilisateurEnAttenteService.lister();
			model.addAttribute("list", list);
		} catch (MetierException e) {
			//TODO redirection vers une page d'erreur
		}
		return new ModelAndView("menuInscriptionVide");
	}

	@RequestMapping(value = "/panelInscription/{id}", method = RequestMethod.POST)
	public ModelAndView profilPost(@RequestParam("action") String action,@RequestParam("optionalMessage") String optionalMessage, @PathVariable String id,
			@ModelAttribute UtilisateurEnAttenteDTO utilisateurTmp, Model model){
		UtilisateurEnAttenteDTO uDTO = new UtilisateurEnAttenteDTO();
		try{
			uDTO = utilisateurEnAttenteService.trouver(Integer.parseInt(id));
			if (action.equals("Accepter")) {
				utilisateurEnAttenteService.valider(uDTO,optionalMessage);
			} else if (action.equals("Refuser")) {
				utilisateurEnAttenteService.refuser(uDTO,optionalMessage);
			}
		} catch (MetierException e) {
			//TODO redirection vers une page d'erreur
		}
		
		List<UtilisateurEnAttenteDTO> list = null;
		try {
			list = utilisateurEnAttenteService.lister();
		} catch (MetierException e) {
			//TODO redirection vers une page d'erreur
		}
		
		model.addAttribute("utilisateurTmp", uDTO);
		
		if(list!=null && !list.isEmpty()){
			return new ModelAndView("redirect:/panelInscription/" + list.get(0).getIdUtilisateurEnAttente());
		}
		else {
			return new ModelAndView("redirect:/panelInscription");
		}
	}
}
