package ipint.glp.web.controller;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.ArticleDTO;
import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.DTO.UtilisateurEnAttenteDTO;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.itf.ArticleService;
import ipint.glp.api.itf.GroupeService;
import ipint.glp.api.itf.UtilisateurEnAttenteService;
import ipint.glp.api.itf.UtilisateurService;

/**
 *
 * @author hequet
 *
 */
@Controller
public class GroupeController {
	private Logger logger = Logger.getLogger("ProfilController");
	@Inject
	UtilisateurService utilisateurService;
	@Inject
	GroupeService groupeService;
	@Inject
	ArticleService articleService;
	@Inject
	UtilisateurEnAttenteService utilisateurEnAttenteService;

	// @RequestMapping(value = "/groupe")
	// public ModelAndView groupeGET() {
	// return new ModelAndView("groupe");
	// }

	@RequestMapping(value = "/groupe/{id}/inscriptionGroupe", method = RequestMethod.GET)
	public ModelAndView groupeInscription(HttpServletRequest request, @PathVariable String id,
			@ModelAttribute GroupeDTO leGroupe, Model model) {
		GroupeDTO gDTO = new GroupeDTO();
		gDTO.setIdGroupe(Integer.parseInt(id));
		try {
			gDTO = groupeService.trouver(gDTO);
		} catch (MetierException e) {
			logger.severe("Erreur acces groupeidProfil GET - groupeService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}
		model.addAttribute("leGroupe", gDTO);
		UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
		utilisateurDTO.setEmail(request.getUserPrincipal().getName());
		try {
			utilisateurDTO = utilisateurService.trouver(utilisateurDTO);
		} catch (MetierException e) {
			logger.severe("Erreur acces publication GET - UtilisateurService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}
		UtilisateurEnAttenteDTO utilisateurEnAttenteDTO = new UtilisateurEnAttenteDTO();
		utilisateurEnAttenteDTO.setAnneeDiplome(utilisateurDTO.getProfil().getAnneeDiplome());
		// TODO : gestion de la date
		utilisateurEnAttenteDTO.setDateNaissance(new Date());
		utilisateurEnAttenteDTO.setDiplome(utilisateurDTO.getProfil().getDiplomePrincipal());
		utilisateurEnAttenteDTO.setEmail(utilisateurDTO.getEmail());
		utilisateurEnAttenteDTO.setGroupePrincipal(gDTO);
		utilisateurEnAttenteDTO.setNom(utilisateurDTO.getNom());
		utilisateurEnAttenteDTO.setPrenom(utilisateurDTO.getPrenom());
		try {
			utilisateurEnAttenteService.creer(utilisateurEnAttenteDTO);
		} catch (MetierException e) {
			logger.severe("Erreur acces publication GET - UtilisateurService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}
		model.addAttribute("leGroupe", gDTO);

		return new ModelAndView("groupe");
	}

	@RequestMapping(value = "/groupe/{id}/desinscriptionGroupe", method = RequestMethod.GET)
	public ModelAndView groupeDesinscription(HttpServletRequest request, @PathVariable String id,
			@ModelAttribute GroupeDTO leGroupe, Model model) {
		GroupeDTO gDTO = new GroupeDTO();
		gDTO.setIdGroupe(Integer.parseInt(id));
		try {
			gDTO = groupeService.trouver(gDTO);
		} catch (MetierException e) {
			logger.severe("Erreur acces groupeidProfil GET - groupeService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}
		model.addAttribute("leGroupe", gDTO);
		UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
		utilisateurDTO.setEmail(request.getUserPrincipal().getName());
		try {
			utilisateurDTO = utilisateurService.trouver(utilisateurDTO);
		} catch (MetierException e) {
			logger.severe("Erreur acces publication GET - UtilisateurService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}

		/*
		 * gDTO.getUtilisateurs().remove(utilisateurDTO); try {
		 * groupeService.modifier(gDTO, gDTO); } catch (MetierException e1) { //
		 * TODO Auto-generated catch block e1.printStackTrace(); }
		 */
		try {
			groupeService.supprimerUtilisateur(gDTO, utilisateurDTO);
		} catch (MetierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("leGroupe", gDTO);

		return new ModelAndView("groupe");
	}

	@RequestMapping(value = "/groupe/{id}", method = RequestMethod.GET)
	public ModelAndView groupeidGET(HttpServletRequest request, @PathVariable String id,
			@ModelAttribute GroupeDTO leGroupe, Model model) {
		GroupeDTO gDTO = new GroupeDTO();
		gDTO.setIdGroupe(Integer.parseInt(id));
		try {
			gDTO = groupeService.trouver(gDTO);
		} catch (MetierException e) {
			logger.severe("Erreur acces groupeidProfil GET - groupeService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}
		List<UtilisateurDTO> animateursGroupe = gDTO.getAnimateurs();
		List<UtilisateurDTO> membresGroupe = gDTO.getUtilisateurs();
		List<ArticleDTO> articlesGroupe = gDTO.getArticles();

		model.addAttribute("leGroupe", gDTO);
		model.addAttribute("animateursGroupe", animateursGroupe);
		model.addAttribute("membresGroupe", membresGroupe);
		model.addAttribute("articlesGroupe", articlesGroupe);
		return new ModelAndView("groupe");
	}
}
