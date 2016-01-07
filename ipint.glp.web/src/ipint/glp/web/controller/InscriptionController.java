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
import ipint.glp.api.itf.GroupeService;
import ipint.glp.api.itf.UtilisateurService;

@Controller
public class InscriptionController {

	@Inject
	UtilisateurService utilisateurService;
	@Inject
	GroupeService groupeS;

	public InscriptionController() {
	}
	
	@RequestMapping(value="/inscription", method=RequestMethod.GET)
	public ModelAndView inscriptionGet(@ModelAttribute("utilisateurTmp") UtilisateurDTO utilisateur, BindingResult result, Model model) {
		model.addAttribute("utilisateurTmp", utilisateur);
		return new ModelAndView("inscription");
	}
		
    @RequestMapping(value = "/inscription", method = RequestMethod.POST)
    public String InscriptionPost(@ModelAttribute("utilisateurTmp") UtilisateurDTO utilisateur, BindingResult result,
                    Model model, @PathVariable("id") String id) {
	    	UtilisateurDTO uDTO = new UtilisateurDTO();
			uDTO.setIdUtilisateur(Integer.parseInt(id));
			uDTO = utilisateurService.trouver(uDTO);
			Integer idTemp = uDTO.getProfil().getIdProfil();
			uDTO.setProfil(utilisateur.getProfil());
			uDTO.getProfil().setIdProfil(idTemp);
			
            utilisateur = utilisateurService.modifier(uDTO,utilisateur);
    		model.addAttribute("articles", uDTO.getArticles());
            model.addAttribute("utilisateurTmp", utilisateur);
            return "profil";
    }
}
