package ipint.glp.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.GroupeDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.itf.GroupeService;
import ipint.glp.api.itf.UtilisateurService;

public class GroupeUtilisateurController {

	private Logger logger = Logger.getLogger("GroupeUtilisateurController");
	@Inject
	GroupeService groupeS;
	@Inject
	UtilisateurService utilisateurS;

}
