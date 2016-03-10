package ipint.glp.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Groupe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idGroupe;
	@javax.validation.constraints.NotNull(message = "Veuillez remplir la nom du groupe")
	@Column(unique = true)
	private String nomGroupe;
	@javax.validation.constraints.NotNull(message = "Veuillez remplir la description du groupe")
	private String description;
	private boolean isGroupeOfficiel = false;

	// Pas besoin de stocker les utilisateurs principaux de ce coté, relation
	// unidirectionnelle
	/*
	 * @OneToMany(mappedBy = "groupePrincipal") private List<Utilisateur>
	 * utilisateursPrincipals;
	 */

	@ManyToOne
	private Utilisateur utilisateurResponsable;

	@ManyToMany
	private List<Utilisateur> utilisateurs;

	@ManyToMany
	@JoinTable(name = "GROUPEANIME_ANIMATEUR", joinColumns = @JoinColumn(name = "groupes_IDGROUPE") , inverseJoinColumns = @JoinColumn(name = "utilisateurs_IDUTILISATEUR") )
	private List<Utilisateur> animateurs;

	@OneToMany(mappedBy = "groupe")
	private List<Article> articles;

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Groupe() {
		this.utilisateurs = new ArrayList<>();
	}

	// public List<Utilisateur> getUtilisateurPrincipal() {
	// return utilisateursPrincipals;
	// }
	//
	// public void setUtilisateurPrincipal(List<Utilisateur>
	// utilisateurPrincipal) {
	// this.utilisateursPrincipals = utilisateurPrincipal;
	// }

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

	public Utilisateur getUtilisateurResponsable() {
		return utilisateurResponsable;
	}

	public void setUtilisateurResponsable(Utilisateur utilisateurResponsable) {
		this.utilisateurResponsable = utilisateurResponsable;
	}

	public List<Utilisateur> getAnimateurs() {
		return animateurs;
	}

	public void setAnimateurs(List<Utilisateur> animateurs) {
		this.animateurs = animateurs;
	}

	public List<Utilisateur> getUtilisateurs() {
		System.out.println("Utilisateurs DATABASE : " + utilisateurs);
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	@Override
	public String toString() {
		return "[Groupe - id=" + this.idGroupe + ", nomGroupe=" + this.nomGroupe + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGroupe == null) ? 0 : idGroupe.hashCode());
		result = prime * result + ((nomGroupe == null) ? 0 : nomGroupe.hashCode());
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
		Groupe other = (Groupe) obj;
		if (idGroupe == null) {
			if (other.idGroupe != null)
				return false;
		} else if (!idGroupe.equals(other.idGroupe))
			return false;
		if (nomGroupe == null) {
			if (other.nomGroupe != null)
				return false;
		} else if (!nomGroupe.equals(other.nomGroupe))
			return false;
		return true;
	}

}