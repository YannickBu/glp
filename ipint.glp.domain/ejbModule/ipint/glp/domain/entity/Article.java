package ipint.glp.domain.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Article implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idArticle;
	@javax.validation.constraints.NotNull(message = "Veuillez remplir le contenu")
	private String contenu;
	@Temporal(TemporalType.TIMESTAMP)
	//TODO décommenter apres test
	/*@javax.validation.constraints.NotNull(message = "Veuillez remplir la date de publication")
	@javax.validation.constraints.Past(message = "Date de publication invalide")*/
	private Calendar datePublication;
	
	private String titre;
	
	//Si on garde cet attribut, pensez a rajouter le OneToMany dans groupe et toute l'impl qui va avec
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Groupe groupe;
	@ManyToOne
	private Utilisateur utilisateur;

	public Article() {
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public Integer getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(Integer idArticle) {
		this.idArticle = idArticle;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Calendar getDatePublication() {
		return datePublication;
	}

	public void setDatePublication(Calendar datePublication) {
		this.datePublication = datePublication;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString(){
		return "[Article - id="+this.idArticle+"]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idArticle == null) ? 0 : idArticle.hashCode());
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
		Article other = (Article) obj;
		if (idArticle == null) {
			if (other.idArticle != null)
				return false;
		} else if (!idArticle.equals(other.idArticle))
			return false;
		return true;
	}

}