package ipint.glp.web.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.UtilisateurEnAttenteDTO;
import ipint.glp.api.itf.GroupeService;
import ipint.glp.api.itf.UtilisateurEnAttenteService;

@Controller
public class InscriptionController {

	@Inject
	UtilisateurEnAttenteService utilisateurEnAttenteService;
	@Inject
	GroupeService groupeS;

	public InscriptionController() {
	}
	
	@RequestMapping(value="/inscription", method=RequestMethod.GET)
	public ModelAndView inscriptionGet(@ModelAttribute("utilisateurTmp") UtilisateurEnAttenteDTO utilisateur, BindingResult result, Model model) {
		model.addAttribute("utilisateurTmp", utilisateur);
		return new ModelAndView("inscription");
	}
		
    @RequestMapping(value = "/inscription", method = RequestMethod.POST)
    public String InscriptionPost(@ModelAttribute("utilisateurTmp") UtilisateurEnAttenteDTO utilisateurTmp, BindingResult result,
                    Model model) {
	    	UtilisateurEnAttenteDTO ueaDTO = new UtilisateurEnAttenteDTO();
            utilisateurTmp = utilisateurEnAttenteService.créer(ueaDTO);
            model.addAttribute("utilisateurTmp", utilisateurTmp);
            return "profil";
    }
}
