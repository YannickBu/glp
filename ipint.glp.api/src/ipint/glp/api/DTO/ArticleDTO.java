package ipint.glp.api.DTO;

import java.io.Serializable;
import java.util.Calendar;

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
	@javax.validation.constraints.Size(min=1,message = "Aucun message saisi")
	@javax.validation.constraints.NotNull(message = "Aucun message saisi")
	private String contenu;

	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
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

	/**
	 * 
	 */
	private String titre;
	
	public ArticleDTO() {
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
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
	public String toString(){
		return "[ArticleDTO - id="+this.idArticle+"]";
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
