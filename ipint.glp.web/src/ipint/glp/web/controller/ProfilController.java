package ipint.glp.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.opensaml.ws.wssecurity.UsageBearing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.CompetenceDTO;
import ipint.glp.api.DTO.DiplomeDTO;
import ipint.glp.api.DTO.ExperienceDTO;
import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.RegionDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.DTO.VilleDTO;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.itf.GroupeService;
import ipint.glp.api.itf.UtilisateurService;
import ipint.glp.api.itf.UtilsService;

@Controller
public class ProfilController {
	private Logger logger = Logger.getLogger("ProfilController");

	@Inject
	UtilisateurService utilisateurService;
	@Inject
	GroupeService groupeS;
	@Inject
	UtilsService utilsS;

	public ProfilController() {
	}

	@RequestMapping(value = "/profil", method = RequestMethod.GET)
	public ModelAndView profilGet(HttpServletRequest request, @ModelAttribute UtilisateurDTO utilisateur, Model model) throws MetierException {
		// ModelAndView model = new ModelAndView("profil");

		UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setEmail(request.getUserPrincipal().getName());
		try {
			uDTO = utilisateurService.trouver(uDTO);
		} catch (MetierException e) {
			logger.severe("Erreur acces profil GET - UtilisateurService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}
			
		List<GroupeDTO> tousLesGroupes = groupeS.listerTousLesGroupes();
		tousLesGroupes.remove(uDTO.getGroupePrincipal());
		for(GroupeDTO groupe1 : uDTO.getGroupes()){
			for(GroupeDTO groupe2 : tousLesGroupes){
				if(groupe1.equals(groupe2)){
					groupe2=null;
				}
			}
		}
		List<GroupeDTO> nouvelle = new ArrayList<GroupeDTO>(tousLesGroupes); 
		Collections.shuffle(nouvelle);
		model.addAttribute("tousLesGroupes", nouvelle);		
		model.addAttribute("grpPrincipal", uDTO.getGroupePrincipal());
		model.addAttribute("utilisateur", uDTO);
		model.addAttribute("profil", uDTO.getProfil());
		model.addAttribute("articles", uDTO.getArticles());

		// model.addObject("utilisateur", uDTO);
		return new ModelAndView("profil");
	}

	@RequestMapping(value = "/profil/{id}", method = RequestMethod.GET)
	public ModelAndView profilIdGet(HttpServletRequest request, @PathVariable String id,
			@ModelAttribute UtilisateurDTO utilisateur, Model model) throws MetierException {
		// ModelAndView model = new ModelAndView("profil");

		UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setIdUtilisateur(Integer.parseInt(id));
		try {
			uDTO = utilisateurService.trouver(uDTO);
		} catch (MetierException e) {
			logger.severe("Erreur acces profil/id GET - UtilisateurService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}

		System.out.println("profil : " + uDTO.getProfil());
		System.out.println("ID util controller : " + uDTO.getIdUtilisateur());
		System.out.println(uDTO.getNom());
		// System.out.println(uDTO.getProfil());

		for (CompetenceDTO competence : uDTO.getProfil().getCompetence()) {
			System.out.println("Competence --------------------------->  " + competence.getLibelle());
		}
		
		List<GroupeDTO> tousLesGroupes = groupeS.listerTousLesGroupes();
		tousLesGroupes.remove(uDTO.getGroupePrincipal());
		for(GroupeDTO groupe1 : uDTO.getGroupes()){
			for(GroupeDTO groupe2 : tousLesGroupes){
				if(groupe1.equals(groupe2)){
					groupe2=null;
				}
			}
		}
		List<GroupeDTO> nouvelle = new ArrayList<GroupeDTO>(tousLesGroupes); 
		Collections.shuffle(nouvelle);
		model.addAttribute("tousLesGroupes", nouvelle);
		model.addAttribute("grpPrincipal", uDTO.getGroupePrincipal());
		model.addAttribute("utilisateur", uDTO);
		model.addAttribute("profil", uDTO.getProfil());
		System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"+utilisateurService.derniereExperience(uDTO).getLieu());
		model.addAttribute("derniereExperience", utilisateurService.derniereExperience(uDTO));
		model.addAttribute("articles", uDTO.getArticles());

		// model.addObject("utilisateur", uDTO);
		request.setAttribute("id", id);
		return new ModelAndView("profil");
	}

	@RequestMapping(value = "/profil/modifprofil", method = RequestMethod.GET)
	public ModelAndView profilModifyGet(HttpServletRequest request,
			@ModelAttribute("utilisateur") UtilisateurDTO utilisateur, BindingResult result, Model model)
					throws MetierException {

		model.addAttribute("pays", utilsS.listerPaysDuMonde());
		List<RegionDTO> regions = utilsS.listerRegions();
		model.addAttribute("regions", regions);
		List<VilleDTO> villes = utilsS.listerVilles();
		model.addAttribute("villes", villes);
		UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setEmail(request.getUserPrincipal().getName());
		try {
			uDTO = utilisateurService.trouver(uDTO);
		} catch (MetierException e) {
			logger.severe(
					"Erreur acces profil/modifProfil GET - UtilisateurService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}
		List<GroupeDTO> tousLesGroupes = groupeS.listerTousLesGroupes();
		tousLesGroupes.remove(uDTO.getGroupePrincipal());
		for(GroupeDTO groupe1 : uDTO.getGroupes()){
			for(GroupeDTO groupe2 : tousLesGroupes){
				if(groupe1.equals(groupe2)){
					groupe2=null;
				}
			}
		}
		List<GroupeDTO> nouvelle = new ArrayList<GroupeDTO>(tousLesGroupes); 
		Collections.shuffle(nouvelle);
		model.addAttribute("tousLesGroupes", nouvelle);
		model.addAttribute("grpPrincipal", uDTO.getGroupePrincipal());
		model.addAttribute("utilisateur", uDTO);
		return new ModelAndView("modifprofil", "utilisateur", uDTO);
	}

	@RequestMapping(value = "/profil/modifprofil", method = RequestMethod.POST)
	public ModelAndView profilModifyPost(HttpServletRequest request,
			@Valid @ModelAttribute("utilisateur") UtilisateurDTO utilisateur, BindingResult result, Model model) {


		// for(ObjectError err : result.getAllErrors()){
		// System.out.println(err.getCode()+" "+err.getObjectName()+"
		// "+err.getDefaultMessage());
		// }

		// Gestion des erreurs

		// for(FieldError fError : result.getFieldErrors()){
		// if(fError.getField().contains("diplome")){
		// result.rejectValue(fError.getField()+".err", fError.getCode(),
		// "L'année de début est invalide");
		// fError.getDefaultMessage()
		// result.rejectValue(fError.getField(), "typeMismatch", "L'année de
		// début est invalide");
		// }
		// }

		// Erreur telephone
//		if (utilisateur.getProfil().getTelephone() == null || "".equals(utilisateur.getProfil().getTelephone()))
//			result.rejectValue("profil.telephone", "Size", "Le téléphone est invalide");
		if (utilisateur.getProfil().getTelephone() != null && !"".equals(utilisateur.getProfil().getTelephone())
				&& !utilisateur.getProfil().getTelephone().matches("[0-9]{10}"))
			result.rejectValue("profil.telephone", "Pattern", "Le téléphone est invalide");

		// Erreur annees diplomes
		DiplomeDTO dipl;
		for (int indDipl = 0; indDipl < utilisateur.getProfil().getDiplomes().size(); indDipl++) {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			dipl = utilisateur.getProfil().getDiplomes().get(indDipl);
			if (dipl != null) {
//				if (dipl.getAnneeDebut() != null) {
//					if (dipl.getAnneeDebut() < 1950 || dipl.getAnneeDebut() > cal.get(GregorianCalendar.YEAR)) {
//						result.rejectValue("profil.diplomes[" + indDipl + "].anneeDebut", "Pattern",
//								"L'année de début est invalide ");
//					}
//				}
//				if (dipl.getAnneFin() != null) {
//					if (dipl.getAnneFin() == null || dipl.getAnneFin() < 1950
//							|| dipl.getAnneFin() > cal.get(GregorianCalendar.YEAR)) {
//						result.rejectValue("profil.diplomes[" + indDipl + "].anneFin", "Pattern",
//								"L'année de fin est invalide ");
//					}
//				}
				if(dipl.getAnneFin() != null && dipl.getAnneeDebut() != null
						&& dipl.getAnneeDebut()>dipl.getAnneFin()){
					result.rejectValue("profil.diplomes[" + indDipl + "].anneFin", "Pattern",
							"L'année de début ne peut pas être supérieure à l'année de fin ");
				}
			}
		}

		// Erreur annees experiences
		ExperienceDTO exp;
		for (int indExp = 0; indExp < utilisateur.getProfil().getExperiences().size(); indExp++) {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			exp = utilisateur.getProfil().getExperiences().get(indExp);
			System.out.println("ProfilController - profilModifPost - utilisateur.getProfil().getExperiences() = " + utilisateur.getProfil().getExperiences());
			if (exp != null) {
//				if (exp.getAnneeDebut() != null) {
//					if (exp.getAnneeDebut() < 1950 || exp.getAnneeDebut() > cal.get(GregorianCalendar.YEAR)) {
//						result.rejectValue("profil.experiences[" + indExp + "].anneeDebut", "Pattern",
//								"L'année de début est invalide ");
//					}
//				}
//				if (exp.getAnneFin() != null) {
//					if (exp.getAnneFin() < 1950 || exp.getAnneFin() > cal.get(GregorianCalendar.YEAR)) {
//						result.rejectValue("profil.experiences[" + indExp + "].anneFin", "Pattern",
//								"L'année de fin est invalide ");
//					}
//				}
				if(exp.getAnneFin() != null && exp.getAnneeDebut() != null
						&& exp.getAnneeDebut()>exp.getAnneFin()){
					result.rejectValue("profil.diplomes[" + indExp + "].anneFin", "Pattern",
							"L'année de début ne peut pas être supérieure à l'année de fin ");
				}
			}
		}

		// for(DiplomeDTO dipl : utilisateur.getProfil().getDiplomes()){
		// if(dipl.getAnneeDebut())
		// }

		UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setEmail(request.getUserPrincipal().getName());
		try {
			uDTO = utilisateurService.trouver(uDTO);
		} catch (MetierException e) {
			logger.severe(
					"Erreur acces profil/modifProfil POST - UtilisateurService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}

		Integer idTemp = uDTO.getProfil().getIdProfil();
		uDTO.setProfil(utilisateur.getProfil());
		uDTO.setPassword(utilisateur.getPassword());
		uDTO.setNom(utilisateur.getNom());
		uDTO.setPrenom(utilisateur.getPrenom());
		uDTO.getProfil().setIdProfil(idTemp);



		if (result.hasErrors()) {
			System.out.println(utilisateur.getGroupes()+" |||| "+uDTO.getGroupePrincipal());
			model.addAttribute("grpPrincipal", uDTO.getGroupePrincipal());
			model.addAttribute("utilisateur", uDTO);
			return new ModelAndView("modifprofil");
		}
		
		try {
			utilisateur = utilisateurService.modifier(uDTO);
		} catch (MetierException e) {
			logger.severe(
					"Erreur acces profil/modifProfil POST - UtilisateurService.modifier renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}
		model.addAttribute("articles", uDTO.getArticles());
		model.addAttribute("grpPrincipal", utilisateur.getGroupePrincipal());
		model.addAttribute("utilisateur", utilisateur);
		// return "redirect:/profil/{id}";
		return new ModelAndView("redirect:/profil", "util"
				+ "isateur", utilisateur);
	}
}
