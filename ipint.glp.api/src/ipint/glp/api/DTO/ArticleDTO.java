package ipint.glp.api.DTO;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author declerck
 *
 */
public class ArticleDTO extends DTO implements Serializable {
	private static final long serialVersionUID = 2382557002234011191L;

	/**
	 * 
	 */
	private Integer idArticle;
	/**
	 * 
	 */
	@javax.validation.constraints.NotNull(message = "Veuillez remplir le contenu")
	private String contenu;

	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@javax.validation.constraints.NotNull(message = "Veuillez remplir la date de publication")
	@javax.validation.constraints.Past(message = "Date de publication invalide")
	private Calendar datePublication;
	
	/**
	 * 
	 */
	@ManyToOne
	private GroupeDTO groupe;

	/**
	 * 
	 */
	@ManyToOne
	private UtilisateurDTO utilisateur;


	public ArticleDTO() {
	}
	
	public GroupeDTO getGroupe() {
		return groupe;
	}

	public void setGroupe(GroupeDTO groupe) {
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

	public UtilisateurDTO getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(UtilisateurDTO utilisateur) {
		this.utilisateur = utilisateur;
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
		ArticleDTO other = (ArticleDTO) obj;
		if (idArticle == null) {
			if (other.idArticle != null)
				return false;
		} else if (!idArticle.equals(other.idArticle))
			return false;
		return true;
	}
}
