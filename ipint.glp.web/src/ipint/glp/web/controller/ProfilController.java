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
		request.setAttribute("id", id);
		return new ModelAndView("profil");
	}

	@RequestMapping(value = "/profil/modifprofil", method = RequestMethod.GET)
	public ModelAndView profilModifyGet(HttpServletRequest request,
			@ModelAttribute("utilisateur") UtilisateurDTO utilisateur, BindingResult result, Model model) {

		try {
			model.addAttribute("pays", utilsS.listerPaysDuMonde());
			List<RegionDTO> regions = utilsS.listerRegions();
			model.addAttribute("regions", regions);
			List<VilleDTO> villes = utilsS.listerVilles();
			model.addAttribute("villes", villes);
		} catch (MetierException e1) {
			logger.severe(
					"Erreur modifProfil GET - erreur sur acces regions/pays - Renvoie : " + e1.getMessage());
			return new ModelAndView("redirect:/erreur");
		}
		
		UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setEmail(request.getUserPrincipal().getName());
		try {
			uDTO = utilisateurService.trouver(uDTO);
		} catch (MetierException e) {
			logger.severe(
					"Erreur acces profil/modifProfil GET - UtilisateurService.trouver renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
		}
		
		
		List<GroupeDTO> tousLesGroupes;
		try {
			tousLesGroupes = groupeS.listerTousLesGroupes();
			tousLesGroupes.remove(uDTO.getGroupePrincipal());
			for(GroupeDTO groupe1 : uDTO.getGroupes()){
				for(GroupeDTO groupe2 : tousLesGroupes){
					if(groupe1.equals(groupe2)){
						groupe2=null;
					}
				}
			}
			model.addAttribute("tousLesGroupes", tousLesGroupes);
		} catch (MetierException e) {
			logger.severe(
					"Erreur modifProfil GET - GroupeService.listerTousLesGroupes renvoie : " + e.getMessage());
			return new ModelAndView("redirect:/erreur");
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

		// Gestion des erreurs

		if((utilisateur.getProfil().getSituation()==null || "".equals(utilisateur.getProfil().getSituation()))
				&& (utilisateur.getProfil().getLieuSituation()!=null || !"".equals(utilisateur.getProfil().getLieuSituation()))){
			result.rejectValue("profil.situation", "Null", "Aucune situation saisie");
		}

		if (utilisateur.getProfil().getTelephone() != null && !"".equals(utilisateur.getProfil().getTelephone())
				&& !utilisateur.getProfil().getTelephone().matches("[0-9]{10}")){
			result.rejectValue("profil.telephone", "Pattern", "Le téléphone est invalide");
		}

		// Erreur diplomes
		DiplomeDTO dipl;
		for (int indDipl = 0; indDipl < utilisateur.getProfil().getDiplomes().size(); indDipl++) {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			dipl = utilisateur.getProfil().getDiplomes().get(indDipl);
			if (dipl != null) {
				if(dipl.getAnneFin() != null && dipl.getAnneeDebut() != null
						&& dipl.getAnneeDebut()>dipl.getAnneFin()){
					result.rejectValue("profil.diplomes[" + indDipl + "].anneFin", "Pattern",
							"L'année de début ne peut pas être supérieure à l'année de fin ");
				}
				if((dipl.getLibelle()==null || "".equals(dipl.getLibelle())) && (dipl.getLieu()==null || "".equals(dipl.getLieu()))){
					dipl.setAnneeDebut(null);
					dipl.setAnneFin(null);
				}else{
					if(dipl.getLibelle()==null || "".equals(dipl.getLibelle())){
						result.rejectValue("profil.diplomes[" + indDipl + "].libelle", "Pattern",
						"Veuillez saisir un nom pour ce diplôme");
					}
					if(dipl.getLieu()==null || "".equals(dipl.getLieu())){
						result.rejectValue("profil.diplomes[" + indDipl + "].lieu", "Pattern",
						"Veuillez saisir un lieu pour ce diplôme");
					}
				}
			}
		}

		// Erreur annees experiences
		ExperienceDTO exp;
		for (int indExp = 0; indExp < utilisateur.getProfil().getExperiences().size(); indExp++) {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			exp = utilisateur.getProfil().getExperiences().get(indExp);
			if (exp != null) {
				if(exp.getAnneFin() != null && exp.getAnneeDebut() != null
						&& exp.getAnneeDebut()>exp.getAnneFin()){
					result.rejectValue("profil.experiences[" + indExp + "].anneFin", "Pattern",
							"L'année de début ne peut pas être supérieure à l'année de fin ");
				}
				if((exp.getDescription()==null || "".equals(exp.getDescription())) && (exp.getLieu()==null || "".equals(exp.getLieu()))
						&& (exp.getEntreprise()==null || "".equals(exp.getEntreprise())) && (exp.getPoste())==null || "".equals(exp.getPoste())){
					exp.setAnneeDebut(null);
					exp.setAnneFin(null);
				}else{
					
				}
			}
		}
		
		//Erreur compétences
		CompetenceDTO comp;
		for (int indComp = 0; indComp < utilisateur.getProfil().getCompetence().size(); indComp++) {
			comp = utilisateur.getProfil().getCompetence().get(indComp);
			if(comp.getLibelle()!=null && !"".equals(comp.getLibelle()) && comp.getNote()==null){
				result.rejectValue("profil.competence[" + indComp + "].note", "Null",
						"Veuillez saisir une note ");
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

		try {
			model.addAttribute("pays", utilsS.listerPaysDuMonde());
			List<RegionDTO> regions = utilsS.listerRegions();
			model.addAttribute("regions", regions);
			List<VilleDTO> villes = utilsS.listerVilles();
			model.addAttribute("villes", villes);
		} catch (MetierException e1) {
			logger.severe(
					"Erreur modifProfil POST - erreur sur acces regions/pays - Renvoie : " + e1.getMessage());
			return new ModelAndView("redirect:/erreur");
		}
		
		Integer idTemp = uDTO.getProfil().getIdProfil();
		uDTO.setProfil(utilisateur.getProfil());
		uDTO.setPassword(utilisateur.getPassword());
		uDTO.setNom(utilisateur.getNom());
		uDTO.setPrenom(utilisateur.getPrenom());
		uDTO.getProfil().setIdProfil(idTemp);



		if (result.hasErrors()) {
			model.addAttribute("grpPrincipal", uDTO.getGroupePrincipal());
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
