package ipint.glp.web.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
import ipint.glp.api.itf.UtilisateurService;

@Controller
public class ProfilController {

	@Inject
	UtilisateurService utilisateurService;

	public ProfilController() {
	}

	@RequestMapping(value = "/profil/{id}", method = RequestMethod.GET)
	public ModelAndView profilGet(@PathVariable String id, @ModelAttribute UtilisateurDTO utilisateur, Model model) {
		// ModelAndView model = new ModelAndView("profil");
		
		UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setIdUtilisateur(Integer.parseInt(id));
		uDTO = utilisateurService.trouver(uDTO);
		System.out.println("profil : " + uDTO.getProfil());
		System.out.println("ID util controller : " + uDTO.getIdUtilisateur());
		System.out.println(uDTO.getNom());
		System.out.println(uDTO.getProfil());

		model.addAttribute("utilisateur", uDTO);
		model.addAttribute("articles", uDTO.getArticles());

		// model.addObject("utilisateur", uDTO);
		return new ModelAndView("profil");
	}
	
    @RequestMapping(value = "/modifprofil/{id}", method = RequestMethod.GET)
    public String profilModifyGet(@ModelAttribute("utilisateur") UtilisateurDTO utilisateur, BindingResult result,
                    Model model, @PathVariable("id") String id) {
    	UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setIdUtilisateur(Integer.parseInt(id));
		uDTO = utilisateurService.trouver(uDTO);
		model.addAttribute("utilisateur", uDTO);
            return "modifprofil";
    }
	
    @RequestMapping(value = "/modifprofil/{id}", method = RequestMethod.POST)
    public String profilModifyPost(@ModelAttribute("utilisateur") UtilisateurDTO utilisateur, BindingResult result,
                    Model model, @PathVariable("id") String id) {
	    	UtilisateurDTO uDTO = new UtilisateurDTO();
			uDTO.setIdUtilisateur(Integer.parseInt(id));
			uDTO = utilisateurService.trouver(uDTO);
			Integer idTemp = uDTO.getProfil().getIdProfil();
			uDTO.setProfil(utilisateur.getProfil());
			uDTO.getProfil().setIdProfil(idTemp);
			
            utilisateur = utilisateurService.modifier(uDTO,utilisateur);
    		model.addAttribute("articles", uDTO.getArticles());
            model.addAttribute("utilisateur", utilisateur);
            return "profil";
    }
}
