package ipint.glp.web.controller;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.itf.ArticleService;
import ipint.glp.api.itf.GroupeService;
import ipint.glp.api.itf.RechercheService;
import ipint.glp.api.itf.UtilisateurService;
import org.springframework.ui.Model;
import java.util.logging.Logger;

@Controller
public class RechercheController {
	private Logger logger = Logger.getLogger("RechercheController");

	@Inject
	ArticleService as;
	@Inject
	UtilisateurService us;
	@Inject
	GroupeService gs;
	@Inject
	RechercheService rs;
	
	
	@RequestMapping(value = "/recherche", method = RequestMethod.POST)
	public ModelAndView recherchePost(HttpServletRequest request, Model model) throws MetierException {
		UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setEmail(request.getUserPrincipal().getName());
		try {
			uDTO = us.trouver(uDTO);
		} catch (MetierException e) {
			logger.severe("Erreur acces publication GET - UtilisateurService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		} 
		String search = (String) request.getParameter("recherche");
		List<GroupeDTO> groupes = new ArrayList<GroupeDTO>();
		List<UtilisateurDTO> utilisateurs = new ArrayList<UtilisateurDTO>();
		groupes = rs.rechercheGroupes(search);
		utilisateurs = rs.rechercheUtilisateurs(search);
		System.out.println("**************groupes = "+ groupes);
		
		List<GroupeDTO> tousLesGroupes = gs.listerTousLesGroupes();
		tousLesGroupes.remove(uDTO.getGroupePrincipal());
		for(GroupeDTO groupe1 : uDTO.getGroupes()){
			for(GroupeDTO groupe2 : tousLesGroupes){
				if(groupe1.equals(groupe2)){
					groupe2=null;
				}
			}
		}
		model.addAttribute("tousLesGroupes", tousLesGroupes);
		model.addAttribute("groupes", groupes);
		model.addAttribute("utilisateurs", utilisateurs);
		return new ModelAndView("recherche");
	}
	
	@RequestMapping(value = "/recherche", method = RequestMethod.GET)
	public ModelAndView rechercheGet(HttpServletRequest request,  Model model) throws MetierException {	
		UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setEmail(request.getUserPrincipal().getName());
		try {
			uDTO = us.trouver(uDTO);
		} catch (MetierException e) {
			logger.severe("Erreur acces publication GET - UtilisateurService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		} 
		String search = (String) request.getParameter("recherche");
		List<GroupeDTO> groupes = new ArrayList<GroupeDTO>();
		List<UtilisateurDTO> utilisateurs = new ArrayList<UtilisateurDTO>();
		groupes = rs.rechercheGroupes(search);
		utilisateurs = rs.rechercheUtilisateurs(search);
		System.out.println("**************groupes = "+ groupes);
		
		List<GroupeDTO> tousLesGroupes = gs.listerTousLesGroupes();
		tousLesGroupes.remove(uDTO.getGroupePrincipal());
		for(GroupeDTO groupe1 : uDTO.getGroupes()){
			for(GroupeDTO groupe2 : tousLesGroupes){
				if(groupe1.equals(groupe2)){
					groupe2=null;
				}
			}
		}
		model.addAttribute("tousLesGroupes", tousLesGroupes);
		model.addAttribute("groupes", groupes);
		model.addAttribute("utilisateurs", utilisateurs);
		return new ModelAndView("recherche");
	}
}
