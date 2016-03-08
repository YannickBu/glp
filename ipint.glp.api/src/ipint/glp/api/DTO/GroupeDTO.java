package ipint.glp.api.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



public class GroupeDTO extends DTO implements Serializable  {
	private static final long serialVersionUID = -975885730285929071L;
	
	private Integer idGroupe;
	@javax.validation.constraints.NotNull(message = "Veuillez remplir la nom du groupe")
	@Column(unique = true)
	private String nomGroupe;
	@javax.validation.constraints.NotNull(message = "Veuillez remplir la description du groupe")
	private String description;
	private boolean isGroupeOfficiel = false;

	@ManyToOne
	private UtilisateurDTO utilisateurResponsable;
	@ManyToMany
	private List<UtilisateurDTO> utilisateurs;
	@ManyToMany
	private List<UtilisateurDTO> animateurs;
	@OneToMany(mappedBy = "groupe")
	private List<ArticleDTO> articles;
	
//	@OneToMany(mappedBy = "groupePrincipal")
//	private List<UtilisateurDTO> utilisateursPrincipals;

	public GroupeDTO() {
		this.utilisateurs = new ArrayList<>();
	}
	
//	public List<UtilisateurDTO> getUtilisateursPrincipals() {
//		return utilisateursPrincipals;
//	}
//
//	public void setUtilisateursPrincipals(List<UtilisateurDTO> utilisateursPrincipals) {
//		this.utilisateursPrincipals = utilisateursPrincipals;
//	}

	
	public Integer getIdGroupe() {
		return idGroupe;
	}

	public void setIdGroupe(Integer idGroupe) {
		this.idGroupe = idGroupe;
	}

	public String getNomGroupe() {
		return nomGroupe;
	}

	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isGroupeOfficiel() {
		return isGroupeOfficiel;
	}

	public void setGroupeOfficiel(boolean isGroupeOfficiel) {
		this.isGroupeOfficiel = isGroupeOfficiel;
	}

	public UtilisateurDTO getUtilisateurResponsable() {
		return utilisateurResponsable;
	}

	public void setUtilisateurResponsable(UtilisateurDTO utilisateurResponsable) {
		this.utilisateurResponsable = utilisateurResponsable;
	}

	public List<UtilisateurDTO> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<UtilisateurDTO> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public List<UtilisateurDTO> getAnimateurs() {
		return animateurs;
	}

	public void setAnimateurs(List<UtilisateurDTO> animateurs) {
		this.animateurs = animateurs;
	}

	public List<ArticleDTO> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleDTO> articles) {
		this.articles = articles;
	}

	@Override
	public String toString(){
		return "[GroupeDTO - id="+this.idGroupe+", nomGroupe="+this.nomGroupe+"]";
	}

}
