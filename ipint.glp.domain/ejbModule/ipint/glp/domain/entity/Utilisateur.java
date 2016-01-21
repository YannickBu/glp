package ipint.glp.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ipint.glp.api.DTO.enumType.Statut;

@Entity
public class Utilisateur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5159907710336150526L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idUtilisateur;
//	@javax.validation.constraints.NotNull(message = "Veuillez remplir le champ Nom")
//	@javax.validation.constraints.Pattern(regexp = "[A-Za-z]+", message = "Prénom invalide")
	private String nom;
//	@javax.validation.constraints.NotNull(message = "Veuillez remplir le champ Prénom")
//	@javax.validation.constraints.Pattern(regexp = "[A-Za-z]+", message = "Prénom invalide")
	private String prenom;
	private Statut statut;
	// @javax.validation.constraints.NotNull(message="Veuillez remplir le champ
	// Identifiant")
	// private String login;
//	@javax.validation.constraints.NotNull(message="Veuillez remplir le champ Mot de passe")
	 private String password;
	
	@javax.validation.constraints.NotNull(message = "Veuillez remplir le champ Email")
	// TODO regex email
	// @javax.validation.constraints.Pattern(regexp="\b[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,}\b",message="Email
	// invalide")
	@Column(unique = true)
	private String email;

	@ManyToOne
	private Groupe groupePrincipal;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Profil profil;
	@OneToMany(mappedBy = "utilisateur")
	private List<Article> articles;
	@OneToMany(mappedBy = "utilisateurResponsable")
	private List<Groupe> groupesGeres;
	@ManyToMany(mappedBy = "utilisateurs")
	private List<Groupe> groupes;

	public Utilisateur() {
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

	
	public Groupe getGroupePrincipal() {
		return groupePrincipal;
	}

	public void setGroupePrincipal(Groupe groupePrincipal) {
		this.groupePrincipal = groupePrincipal;
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

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Groupe> getGroupesGeres() {
		return groupesGeres;
	}

	public void setGroupesGeres(List<Groupe> groupesGeres) {
		this.groupesGeres = groupesGeres;
	}

	public List<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString(){
		return "[Utilisateur - id="+this.idUtilisateur+", email="+this.email+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idUtilisateur == null) ? 0 : idUtilisateur.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idUtilisateur == null) {
			if (other.idUtilisateur != null)
				return false;
		} else if (!idUtilisateur.equals(other.idUtilisateur))
			return false;
		return true;
	}
	
	
}