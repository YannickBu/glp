package ipint.glp.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.DTO.UtilisateurEnAttenteDTO;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.itf.GroupeService;
import ipint.glp.api.itf.UtilisateurService;

/**
 * 
 * 
 * 
 * @author hequet
 *
 */
@Controller
public class AdministrationController {
	private Logger logger = Logger.getLogger("AdministrationController");
	@Inject
	GroupeService groupeS;
	@Inject
	UtilisateurService utilisateurS;
	
	
	
	public AdministrationController() {
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param groupeTmp
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/administration/creergroupe", method = RequestMethod.GET)
	public ModelAndView createGroupeGet(HttpServletRequest request,@ModelAttribute("utilisateur") UtilisateurDTO utilisateur,@ModelAttribute("groupeTmp") GroupeDTO groupeTmp,
			BindingResult result, Model model) {
		GroupeDTO gDTO = new GroupeDTO();
		UtilisateurDTO u2DTO = new UtilisateurDTO();
		u2DTO.setEmail(request.getUserPrincipal().getName());
		try {	
			u2DTO = utilisateurS.trouver(u2DTO);
			model.addAttribute("utilisateur",u2DTO);
			model.addAttribute("grpPrincipal",u2DTO.getGroupePrincipal());
		} catch (MetierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<UtilisateurDTO> listPersonnel = new ArrayList<UtilisateurDTO>();
		try {
			listPersonnel = utilisateurS.listerParType("PERSONNEL");
		} catch (MetierException e) {
			logger.severe("Erreur createGroupeGET - UtilisateurService.listerPersonnel renvoie : " + e.getMessage());
		}
		model.addAttribute("animateurs", listPersonnel);
		model.addAttribute("utilisateurResponsables", listPersonnel);
		model.addAttribute("groupeTmp", gDTO);
		return new ModelAndView("createGroupe");
	}

	/**
	 * 
	 * 
	 * 
	 * @param request
	 * @param utilisateurTmp
	 * @param groupeTmp
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/administration/creergroupe", method = RequestMethod.POST)
	public ModelAndView createGroupePost(HttpServletRequest request,@ModelAttribute("utilisateur") UtilisateurDTO utilisateur, @ModelAttribute("utilisateurTmp") UtilisateurDTO utilisateurTmp, @ModelAttribute("groupeTmp") GroupeDTO groupeTmp,
			BindingResult result, Model model) {
		GroupeDTO gDTO = new GroupeDTO();
		UtilisateurDTO u2DTO = new UtilisateurDTO();
		u2DTO.setEmail(request.getUserPrincipal().getName());
		try {	
			u2DTO = utilisateurS.trouver(u2DTO);
			model.addAttribute("utilisateur",u2DTO);
			model.addAttribute("grpPrincipal",u2DTO.getGroupePrincipal());
		} catch (MetierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UtilisateurDTO uDTO = new UtilisateurDTO();
		List<UtilisateurDTO> luDTO = new ArrayList<UtilisateurDTO>();
		String[] moderateurList = request.getParameterValues("utilisateurResponsable.idUtilisateur");
		int idModerateur = Integer.parseInt(moderateurList[0].toString());
		String[] animateurList = request.getParameterValues("animateurs");
		for(int i=0; i<animateurList.length;i++){
			UtilisateurDTO animTmp = new UtilisateurDTO();
			animTmp.setIdUtilisateur(Integer.parseInt(animateurList[i].toString())); 
			try {
				luDTO.add(utilisateurS.trouver(animTmp));
			} catch (MetierException e) {
				logger.severe("Erreur createGroupePOST - utilisateurS.trouver renvoie : " + e.getMessage());
			}
		}
		uDTO.setIdUtilisateur(idModerateur);
		gDTO.setNomGroupe(groupeTmp.getNomGroupe());
		gDTO.setDescription(groupeTmp.getDescription());
		gDTO.setGroupeOfficiel(true);
		gDTO.setAnimateurs(luDTO);
		gDTO.setUtilisateurResponsable(uDTO);
		try {
			groupeTmp = groupeS.creer(gDTO);
		} catch (MetierException e) {
			logger.severe("Erreur createGroupePOST - groupeS.creer renvoie : " + e.getMessage());
			return new ModelAndView("panelAdministration","createdGroupe", "FAIL");
		}
		List<UtilisateurDTO> listPersonnel = new ArrayList<UtilisateurDTO>();
		try {
			listPersonnel = utilisateurS.listerParType("PERSONNEL");
		} catch (MetierException e) {
			logger.severe("Erreur createGroupeGET - UtilisateurService.listerPersonnel renvoie : " + e.getMessage());
			return new ModelAndView("panelAdministration","createdGroupe", "FAIL");
		}
		model.addAttribute("animateurs", listPersonnel);
		model.addAttribute("utilisateurResponsables", listPersonnel);
		try {
			model.addAttribute("groupesOfficiel",  groupeS.listerParType(true));
		} catch (MetierException e) {
			logger.severe("Erreur createGroupePOST - groupeS.listerParType renvoie : " + e.getMessage());
		}
		return new ModelAndView("panelAdministration","createdGroupe", "SUCCESS");
	}
		
	/**
	 * 
	 * 
	 * 
	 * @param groupeTmp
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/administration","/administration/paneladministration"}, method = RequestMethod.GET)
	public ModelAndView panelAdministrationGet(HttpServletRequest request,@ModelAttribute("utilisateur") UtilisateurDTO utilisateur,@ModelAttribute("groupeTmp") GroupeDTO groupeTmp,
			BindingResult result, Model model) {
		UtilisateurDTO u2DTO = new UtilisateurDTO();
		u2DTO.setEmail(request.getUserPrincipal().getName());
		try {	
			u2DTO = utilisateurS.trouver(u2DTO);
			model.addAttribute("utilisateur",u2DTO);
			model.addAttribute("grpPrincipal",u2DTO.getGroupePrincipal());
		} catch (MetierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			model.addAttribute("groupesOfficiel",  groupeS.listerParType(true));
		} catch (MetierException e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("panelAdministration");
	}

	/**
	 * 
	 * 
	 * 
	 * @param groupeTmp
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/administration","/administration/paneladministration"}, method = RequestMethod.POST)
	public ModelAndView panelAdministrationPost(HttpServletRequest request,@ModelAttribute("utilisateur") UtilisateurDTO utilisateur,@ModelAttribute("groupeTmp") GroupeDTO groupeTmp,
			BindingResult result, Model model) {
		UtilisateurDTO u2DTO = new UtilisateurDTO();
		u2DTO.setEmail(request.getUserPrincipal().getName());
		try {	
			u2DTO = utilisateurS.trouver(u2DTO);
			model.addAttribute("utilisateur",u2DTO);
			model.addAttribute("grpPrincipal",u2DTO.getGroupePrincipal());
		} catch (MetierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			model.addAttribute("groupesOfficiel",  groupeS.listerParType(true));
		} catch (MetierException e) {
			e.printStackTrace();
		}
		return new ModelAndView("panelAdministration");
	}
	
}
