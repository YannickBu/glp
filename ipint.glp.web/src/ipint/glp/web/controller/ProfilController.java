package ipint.glp.web.controller;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
	public ModelAndView profilGet(HttpServletRequest request, @ModelAttribute UtilisateurDTO utilisateur, Model model) {
		// ModelAndView model = new ModelAndView("profil");

		UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setEmail(request.getUserPrincipal().getName());
		try {
			uDTO = utilisateurService.trouver(uDTO);
		} catch (MetierException e) {
			logger.severe("Erreur acces profil GET - UtilisateurService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}
		//
		// UtilisateurDTO uDTO2 = new UtilisateurDTO();
		// GroupeDTO gDTO = new GroupeDTO();
		// gDTO.setNomGroupe("MIAGE");
		// gDTO = groupeS.trouver(gDTO);
		// GroupeDTO gDTO2 = new GroupeDTO();
		// gDTO2.setNomGroupe("SIAD");
		// gDTO2 = groupeS.trouver(gDTO2);
		// List<GroupeDTO> grp = new ArrayList<GroupeDTO>();
		// grp.add(gDTO);
		// grp.add(gDTO2);
		// uDTO2.setGroupes(grp);
		// List<String> dipl = new ArrayList<String>();
		// dipl.add("2015/2016 - M2MIAGE");
		// dipl.add("2012/2013 - L3MIAGE");
		// dipl.add("2010/2011 - DUT Informatique");
		// if(uDTO.getProfil() == null){
		// System.out.println("------------------------------");
		// }
		// ProfilDTO pDTO= uDTO.getProfil();
		// //pDTO.setDiplomes(dipl);
		// uDTO2.setProfil(pDTO);
		//
		// uDTO = utilisateurService.modifier(uDTO, uDTO2);
		model.addAttribute("utilisateur", uDTO);
		model.addAttribute("profil", uDTO.getProfil());
		model.addAttribute("articles", uDTO.getArticles());

		// model.addObject("utilisateur", uDTO);
		return new ModelAndView("profil");
	}

	@RequestMapping(value = "/profil/{id}", method = RequestMethod.GET)
	public ModelAndView profilIdGet(HttpServletRequest request, @PathVariable String id,
			@ModelAttribute UtilisateurDTO utilisateur, Model model) {
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
		model.addAttribute("utilisateur", uDTO);
		model.addAttribute("profil", uDTO.getProfil());

		model.addAttribute("articles", uDTO.getArticles());

		// model.addObject("utilisateur", uDTO);
		request.setAttribute("id", id);
		return new ModelAndView("profil");
	}

	@RequestMapping(value = "/profil/modifprofil", method = RequestMethod.GET)
	public ModelAndView profilModifyGet(HttpServletRequest request,
			@ModelAttribute("utilisateur") UtilisateurDTO utilisateur, BindingResult result, Model model) throws MetierException {
		
		model.addAttribute("pays",utilsS.listerPaysDuMonde());
		List<RegionDTO> regions = utilsS.listerRegions();
		model.addAttribute("regions",regions);
		List<VilleDTO> villes = utilsS.listerVilles();
		model.addAttribute("villes",villes);
		UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setEmail(request.getUserPrincipal().getName());
		try {
			uDTO = utilisateurService.trouver(uDTO);
		} catch (MetierException e) {
			logger.severe(
					"Erreur acces profil/modifProfil GET - UtilisateurService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}
		model.addAttribute("utilisateur", uDTO);
		return new ModelAndView("modifprofil", "utilisateur", uDTO);
	}

	@RequestMapping(value = "/profil/modifprofil", method = RequestMethod.POST)
	public ModelAndView profilModifyPost(HttpServletRequest request,
			@Valid @ModelAttribute("utilisateur") UtilisateurDTO utilisateur, BindingResult result, Model model) {
		
		
//		for(ObjectError err : result.getAllErrors()){
//			System.out.println(err.getCode()+" "+err.getObjectName()+" "+err.getDefaultMessage());
//		}
		
		//Gestion des erreurs
		
//		for(FieldError fError : result.getFieldErrors()){
//			if(fError.getField().contains("diplome")){
//				result.rejectValue(fError.getField()+".err", fError.getCode(), "L'année de début est invalide");
//				fError.getDefaultMessage()
//				result.rejectValue(fError.getField(), "typeMismatch", "L'année de début est invalide");
//			}
//		}
		
		//Erreur telephone
		if(utilisateur.getProfil().getTelephone()==null || "".equals(utilisateur.getProfil().getTelephone()))
			result.rejectValue("profil.telephone", "Size","Le téléphone est invalide");
		else if(!utilisateur.getProfil().getTelephone().matches("[0-9]{10}"))
			result.rejectValue("profil.telephone", "Pattern","Le téléphone est invalide");
		
		//Erreur annees diplomes
		DiplomeDTO dipl;
		for(int indDipl = 0; indDipl<utilisateur.getProfil().getDiplomes().size();indDipl++){
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			dipl = utilisateur.getProfil().getDiplomes().get(indDipl);
			if(dipl!=null){
				if(dipl.getAnneeDebut()!=null){
					if(dipl.getAnneeDebut()<1950 || dipl.getAnneeDebut()>cal.get(GregorianCalendar.YEAR)){
						result.rejectValue("profil.diplomes["+indDipl+"].anneeDebut", "Pattern","L'année de début est invalide");
					}
				}
				if(dipl.getAnneFin()!=null){
					if(dipl.getAnneFin()==null || dipl.getAnneFin()<1950 || dipl.getAnneFin()>cal.get(GregorianCalendar.YEAR)){
						result.rejectValue("profil.diplomes["+indDipl+"].anneFin", "Pattern","L'année de fin est invalide");
					}
				}
			}
		}
		
		//Erreur annees experiences
		ExperienceDTO exp;
		for(int indExp = 0; indExp<utilisateur.getProfil().getExperiences().size();indExp++){
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			exp = utilisateur.getProfil().getExperiences().get(indExp);
			if(exp!=null){
				if(exp.getAnneeDebut()!=null){
					if(exp.getAnneeDebut()<1950 || exp.getAnneeDebut()>cal.get(GregorianCalendar.YEAR)){
						result.rejectValue("profil.experiences["+indExp+"].anneeDebut", "Pattern","L'année de début est invalide");
					}
				}
				if(exp.getAnneFin()!=null){
					if(exp.getAnneFin()<1950 || exp.getAnneFin()>cal.get(GregorianCalendar.YEAR)){
						result.rejectValue("profil.experiences["+indExp+"].anneFin", "Pattern","L'année de fin est invalide");
					}
				}
			}
		}
		
		
//		for(DiplomeDTO dipl : utilisateur.getProfil().getDiplomes()){
//			if(dipl.getAnneeDebut())
//		}
		

		if(result.hasErrors()){
			return new ModelAndView("modifprofil");
		}
		
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

		try {
			utilisateur = utilisateurService.modifier(uDTO);
		} catch (MetierException e) {
			logger.severe(
					"Erreur acces profil/modifProfil POST - UtilisateurService.modifier renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}
		model.addAttribute("articles", uDTO.getArticles());
		model.addAttribute("utilisateur", utilisateur);
		// return "redirect:/profil/{id}";
		return new ModelAndView("redirect:/profil", "utilisateur", utilisateur);
	}
}
