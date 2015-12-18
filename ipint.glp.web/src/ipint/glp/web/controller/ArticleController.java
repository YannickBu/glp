package ipint.glp.web.controller;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ipint.glp.api.DTO.ArticleDTO;
import ipint.glp.api.DTO.UtilisateurDTO;
import ipint.glp.api.itf.ArticleService;
import ipint.glp.api.itf.UtilisateurService;

@Controller
public class ArticleController {
        
        @Inject
        ArticleService as;
        @Inject
        UtilisateurService us;
        
        @RequestMapping(value = "/publication/{id}", method = RequestMethod.GET)
        public ModelAndView welcomeGet(@PathVariable("id") String id, Model model) {
        	UtilisateurDTO uDTO = new UtilisateurDTO();
    		uDTO.setIdUtilisateur(Integer.parseInt(id));
    		uDTO = us.trouver(uDTO);
    		model.addAttribute("articles", uDTO.getArticles());
            model.addAttribute("utilisateur", uDTO);
            return new ModelAndView("accueil", "article", new ArticleDTO());
        }
        
        @RequestMapping(value = "/publication/{id}", method = RequestMethod.POST)
        public ModelAndView publicationGet(@ModelAttribute("article") ArticleDTO article, BindingResult result, @PathVariable("id") String id, Model model) {
                //TODO
        	
        	UtilisateurDTO uDTO = new UtilisateurDTO();
    		uDTO.setIdUtilisateur(Integer.parseInt(id));
    		uDTO = us.trouver(uDTO);
    		
    		System.out.println("artCont id : "+id);
    		System.out.println("artCont id uDTO : "+uDTO.getIdUtilisateur());
    		System.out.println("artCont id uDTO : "+uDTO.getNom());
        	
        	ArticleDTO articleDto = new ArticleDTO();
            articleDto.setContenu(article.getContenu());
            Calendar cal = Calendar.getInstance();
            articleDto.setDatePublication(cal);
            articleDto.setUtilisateur(uDTO);
            articleDto = as.creer(articleDto);
            //TODO recuperer en base les articles
            List<ArticleDTO> articles = articleDto.getUtilisateur().getArticles();
            model.addAttribute("articles", articles);
            model.addAttribute("utilisateur", uDTO);
            return new ModelAndView("accueil");
        }

}
