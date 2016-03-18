package ipint.glp.web.controller;

import java.util.Iterator;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.validation.Assertion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.DTO.enumType.Statut;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.exception.UtilisateurInconnuException;
import ipint.glp.api.itf.GroupeService;
import ipint.glp.api.itf.UtilisateurService;

@Controller
public class ConnexionController {
	private Logger logger = Logger.getLogger("ProfilController");
	
	public static final String ATTR_CAS = "_const_cas_assertion_";
	
	@Inject
	private UtilisateurService utilServ;
	@Inject
	private GroupeService groupeServ;
	
	@RequestMapping(value="/cas", method=RequestMethod.GET)
	public ModelAndView connexionCAS(HttpServletRequest request) {
		Assertion assertion = (Assertion) request.getSession().getAttribute(ATTR_CAS); 
		
		//Gestion CAS
		if(assertion != null){
			Iterator<String> it = assertion.getPrincipal().getAttributes().keySet().iterator();

			while(it.hasNext()){
				String next = it.next();
				System.out.println(next + " = " + assertion.getPrincipal().getAttributes().get(next));
			}

			UtilisateurDTO utilSeConnectant = new UtilisateurDTO();
			utilSeConnectant.setEmail((String) assertion.getPrincipal().getAttributes().get("mail"));
			try {
				utilSeConnectant = utilServ.trouver(utilSeConnectant);
			} catch (UtilisateurInconnuException e) {
				//1ere connexion depuis le CAS -> on linscrit
				utilSeConnectant.setNom((String) assertion.getPrincipal().getAttributes().get("sn"));
				utilSeConnectant.setPrenom((String) assertion.getPrincipal().getAttributes().get("givenname"));
				utilSeConnectant.setPassword("");//sera modifié avec un mdp aleatoire à la creation de lutilisateur
				//Etudiant
				if(assertion.getPrincipal().getAttributes().get("nip") != null){
					utilSeConnectant.setStatut(Statut.ETUDIANT);
					//Personnel
				}else{
					utilSeConnectant.setStatut(Statut.PERSONNEL);
				}
				//TODO SETTER LE GROUPE PRINCIPAL
				GroupeDTO gdto = new GroupeDTO();
				gdto.setIdGroupe(0);
				try {
					gdto = groupeServ.trouver(gdto);
					utilSeConnectant.setGroupePrincipal(gdto);
					utilSeConnectant = utilServ.creer(utilSeConnectant);
				} catch (MetierException me) {
					return new ModelAndView("redirect:/erreur");
				}
			} catch (MetierException e){
				return new ModelAndView("redirect:/erreur");
			}

			try {
				request.login(utilSeConnectant.getEmail(), utilSeConnectant.getPassword());
				request.getSession().setAttribute("nomUtil", utilSeConnectant.getNom());
				request.getSession().setAttribute("prenomUtil", utilSeConnectant.getPrenom());
			} catch (ServletException e) {
				return new ModelAndView("redirect:/erreur");
			}
		}
		return new ModelAndView("redirect:/connexion");
	}

	@RequestMapping(value="/connexion", method=RequestMethod.GET)
	public ModelAndView loginGet(HttpServletRequest request) {
		if(request.getUserPrincipal() != null){
			UtilisateurDTO util = new UtilisateurDTO();
			util.setEmail(request.getUserPrincipal().getName());
			try {
				util = utilServ.trouver(util);
				request.getSession().setAttribute("nomUtil", util.getNom());
				request.getSession().setAttribute("prenomUtil", util.getPrenom());
			} catch (MetierException e) {
				logger.severe("Erreur connexion GET - UtilisateurService.trouver renvoie : " + e.getMessage());
				return new ModelAndView("redirect:/erreur");
			}
			if(request.isUserInRole("administrateur")){
				return new ModelAndView("redirect:/administration");
			}else if(request.isUserInRole("moderateur")){
				return new ModelAndView("redirect:/moderation");
			}else{
				return new ModelAndView("redirect:/publication");
			}
		}
		return new ModelAndView("connexion");
	}
	@RequestMapping(value="/connexion", method=RequestMethod.POST)
	public ModelAndView connexionPost(HttpServletRequest request) {
		return new ModelAndView("profil");
	}
	
	@RequestMapping(value="/toConnexion", method=RequestMethod.GET)
	public ModelAndView toConnexion(HttpServletRequest request) {
		return new ModelAndView("redirect:/connexion");
	}

	@RequestMapping(value="/errorConnexion", method=RequestMethod.POST)
	public ModelAndView connexionErrorPost(HttpServletRequest request) {
		request.setAttribute("errorConnexion", Boolean.TRUE);
		return new ModelAndView("connexion");
	}

	@RequestMapping(value="/errorConnexion", method=RequestMethod.GET)
	public ModelAndView connexionErrorGet(HttpServletRequest request) {
		request.setAttribute("errorConnexion", Boolean.TRUE);
		return new ModelAndView("connexion");
	}

	//	@RequestMapping(value="/*/js/bootstrap.min.js")
	//	public ModelAndView connexionOk() {
	//		return new ModelAndView("redirect:/publication");
	//
	//	}

	@RequestMapping(value="/deconnexion")
	public String deconnexion(HttpServletRequest request) throws ServletException {
		Assertion assertion = (Assertion) request.getSession().getAttribute(ATTR_CAS);
		request.logout();
		if(assertion != null){
			request.getSession().setAttribute(ATTR_CAS, null);
			return "redirect:" + request.getServletContext().getInitParameter("urlCasLogout")
					+ request.getServletContext().getInitParameter("urlSite");
		}
		return "redirect:/connexion";

	}
}
