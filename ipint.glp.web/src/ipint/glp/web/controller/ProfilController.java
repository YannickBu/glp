package ipint.glp.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.itf.GroupeService;
import ipint.glp.api.itf.UtilisateurService;

@Controller
public class ProfilController {

	@Inject
	UtilisateurService utilisateurService;
	@Inject
	GroupeService groupeS;

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
			//TODO rediriger page erreur
		}
		//		
		//		UtilisateurDTO uDTO2 = new UtilisateurDTO();
		//		GroupeDTO gDTO = new GroupeDTO();
		//		gDTO.setNomGroupe("MIAGE");
		//		gDTO = groupeS.trouver(gDTO);
		//		GroupeDTO gDTO2 = new GroupeDTO();
		//		gDTO2.setNomGroupe("SIAD");
		//		gDTO2 = groupeS.trouver(gDTO2);		
		//		List<GroupeDTO> grp = new ArrayList<GroupeDTO>();
		//		grp.add(gDTO);
		//		grp.add(gDTO2);
		//		uDTO2.setGroupes(grp);
		//		List<String> dipl = new ArrayList<String>();
		//		dipl.add("2015/2016 - M2MIAGE");
		//		dipl.add("2012/2013 - L3MIAGE");
		//		dipl.add("2010/2011 - DUT Informatique");
		//		if(uDTO.getProfil() == null){
		//			System.out.println("------------------------------");
		//		}
		//		ProfilDTO pDTO= uDTO.getProfil();
		//		//pDTO.setDiplomes(dipl);
		//		uDTO2.setProfil(pDTO);
		//			
		//		uDTO = utilisateurService.modifier(uDTO, uDTO2);




		System.out.println("profil : " + uDTO.getProfil());
		System.out.println("ID util controller : " + uDTO.getIdUtilisateur());
		System.out.println(uDTO.getNom());
		//System.out.println(uDTO.getProfil());

		model.addAttribute("utilisateur", uDTO);
		model.addAttribute("profil", uDTO.getProfil());
		model.addAttribute("articles", uDTO.getArticles());

		// model.addObject("utilisateur", uDTO);
		return new ModelAndView("profil");
	}

	@RequestMapping(value = "/profil/{id}", method = RequestMethod.GET)
	public ModelAndView profilIdGet(HttpServletRequest request, @PathVariable String id, @ModelAttribute UtilisateurDTO utilisateur, Model model) {
		// ModelAndView model = new ModelAndView("profil");
		
		UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setIdUtilisateur(Integer.parseInt(id));
		try {
			uDTO = utilisateurService.trouver(uDTO);
		} catch (MetierException e) {
			//TODO rediriger page erreur
		}
		
		System.out.println("profil : " + uDTO.getProfil());
		System.out.println("ID util controller : " + uDTO.getIdUtilisateur());
		System.out.println(uDTO.getNom());
		//System.out.println(uDTO.getProfil());

		model.addAttribute("utilisateur", uDTO);
		model.addAttribute("profil", uDTO.getProfil());
		model.addAttribute("articles", uDTO.getArticles());

		// model.addObject("utilisateur", uDTO);
		request.setAttribute("id", id);
		return new ModelAndView("profil");
	}
	
	@RequestMapping(value = "/profil/modifprofil", method = RequestMethod.GET)
	public ModelAndView profilModifyGet(HttpServletRequest request, @ModelAttribute("utilisateur") UtilisateurDTO utilisateur, BindingResult result,
			Model model) {
		UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setEmail(request.getUserPrincipal().getName());
		try {
			uDTO = utilisateurService.trouver(uDTO);
		} catch (MetierException e) {
			//TODO rediriger page erreur
		}
		model.addAttribute("utilisateur", uDTO);
		return new ModelAndView("modifprofil", "utilisateur",uDTO);
	}

	@RequestMapping(value = "/profil/modifprofil", method = RequestMethod.POST)
	public ModelAndView profilModifyPost(HttpServletRequest request, @ModelAttribute("utilisateur") UtilisateurDTO utilisateur, BindingResult result,
			Model model) {
		UtilisateurDTO uDTO = new UtilisateurDTO();
		uDTO.setEmail(request.getUserPrincipal().getName());
		try {
			uDTO = utilisateurService.trouver(uDTO);
		} catch (MetierException e) {
			//TODO rediriger page erreur
		}
		Integer idTemp = uDTO.getProfil().getIdProfil();
		uDTO.setProfil(utilisateur.getProfil());
		uDTO.getProfil().setIdProfil(idTemp);

		try {
			utilisateur = utilisateurService.modifier(utilisateur,uDTO);
		} catch (MetierException e) {
			//TODO rediriger page erreur
		}
		model.addAttribute("articles", uDTO.getArticles());
		model.addAttribute("utilisateur", utilisateur);
		//return "redirect:/profil/{id}";
		return new ModelAndView("redirect:/profil","utilisateur",uDTO);
	}
}
