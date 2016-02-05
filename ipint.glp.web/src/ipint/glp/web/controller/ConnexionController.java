package ipint.glp.web.controller;

import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.validation.Assertion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConnexionController {

	@RequestMapping(value="/connexion", method=RequestMethod.GET)
	public ModelAndView loginGet(HttpServletRequest request) {
		
//		Assertion assertion = (Assertion) request.getSession().getAttribute("_const_cas_assertion_"); 
//		System.out.println("-----------------------"+assertion.getAuthenticationDate());
//		Iterator<String> it = assertion.getPrincipal().getAttributes().keySet().iterator();
//
//		while(it.hasNext()){
//			System.out.println(assertion.getPrincipal().getAttributes().get(it.next()));
//		}
		
		return new ModelAndView("connexion");
	}
	@RequestMapping(value="/connexion", method=RequestMethod.POST)
	public ModelAndView connexionPost(HttpServletRequest request) {
		
//		Assertion assertion = (Assertion) request.getSession().getAttribute("_const_cas_assertion_"); 
//		System.out.println("-----------------------"+assertion.getAuthenticationDate());
//		Iterator<String> it = assertion.getPrincipal().getAttributes().keySet().iterator();
//
//		while(it.hasNext()){
//			System.out.println(assertion.getPrincipal().getAttributes().get(it.next()));
//		}
		
		return new ModelAndView("profil");
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
	public ModelAndView deconnexion(HttpServletRequest request) throws ServletException {
		request.logout();
		return new ModelAndView("redirect:/publication");

	}
}
