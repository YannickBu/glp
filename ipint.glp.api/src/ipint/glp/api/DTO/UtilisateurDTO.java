package ipint.glp.api.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import ipint.glp.api.DTO.enumType.StatutDTO;

public class UtilisateurDTO  implements Serializable  {
	private static final long serialVersionUID = -6174082190617483433L;

	private Integer idUtilisateur;
	@javax.validation.constraints.NotNull(message = "Veuillez remplir le champ Nom")
	@javax.validation.constraints.Pattern(regexp = "[A-Za-z]+", message = "Prénom invalide")
	private String nom;
	@javax.validation.constraints.NotNull(message = "Veuillez remplir le champ Prénom")
	@javax.validation.constraints.Pattern(regexp = "[A-Za-z]+", message = "Prénom invalide")
	private String prenom;
	private StatutDTO statut;
	// @javax.validation.constraints.NotNull(message="Veuillez remplir le champ
	// Identifiant")
	// private String login;
	// @javax.validation.constraints.NotNull(message="Veuillez remplir le champ
	// Mot de passe")
	// private String password;
	@javax.validation.constraints.NotNull(message = "Veuillez remplir le champ Email")
	// TODO regex email
	// @javax.validation.constraints.Pattern(regexp="\b[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,}\b",message="Email
	// invalide")
	@Column(unique = true)
	private String email;

	private ProfilDTO profil;
	@OneToMany(mappedBy = "utilisateur")
	private List<ArticleDTO> articles;
	@OneToMany(mappedBy = "utilisateurResponsable")
	private List<GroupeDTO> groupesGeres;
	@ManyToMany(mappedBy = "utilisateurs")
	private List<GroupeDTO> groupes;

	public UtilisateurDTO() {
		super();
		this.articles = new ArrayList<>();
		this.groupesGeres = new ArrayList<>();
		this.groupes = new ArrayList<>();
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public StatutDTO getStatut() {
		return statut;
	}

	public void setStatut(StatutDTO statut) {
		this.statut = statut;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ProfilDTO getProfil() {
		return profil;
	}

	public void setProfil(ProfilDTO profil) {
		this.profil = profil;
	}

	public List<ArticleDTO> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleDTO> articles) {
		this.articles = articles;
	}

	public List<GroupeDTO> getGroupesGeres() {
		return groupesGeres;
	}

	public void setGroupesGeres(List<GroupeDTO> groupesGeres) {
		this.groupesGeres = groupesGeres;
	}

	public List<GroupeDTO> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<GroupeDTO> groupes) {
		this.groupes = groupes;
	}

}
