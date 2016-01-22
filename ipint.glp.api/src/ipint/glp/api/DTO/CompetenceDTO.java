package ipint.glp.api.DTO;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class CompetenceDTO extends DTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCompetence;
	@javax.validation.constraints.NotNull(message = "Veuillez remplir le nom de la compétence")
	private String libelle;
	@javax.validation.constraints.DecimalMin(value = "1", message = "Minimum 1")
	@javax.validation.constraints.DecimalMax(value = "5", message = "Maximum 5")
	private int note;
	@ManyToOne
	private ProfilDTO profil;
	
	public CompetenceDTO() {
		super();
	}

	/**
	 * @return the idCompetence
	 */
	public Integer getIdCompetence() {
		return idCompetence;
	}

	/**
	 * @param idCompetence the idCompetence to set
	 */
	public void setIdCompetence(Integer idCompetence) {
		this.idCompetence = idCompetence;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the note
	 */
	public int getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(int note) {
		this.note = note;
	}

	/**
	 * @return the profil
	 */
	public ProfilDTO getProfil() {
		return profil;
	}

	/**
	 * @param profil the profil to set
	 */
	public void setProfil(ProfilDTO profil) {
		this.profil = profil;
	}

	@Override
	public String toString(){
		return "[CompetenceDTO - id="+this.idCompetence+", libelle="+this.libelle+"]";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCompetence == null) ? 0 : idCompetence.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + note;
		result = prime * result + ((profil == null) ? 0 : profil.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompetenceDTO other = (CompetenceDTO) obj;
		if (idCompetence == null) {
			if (other.idCompetence != null)
				return false;
		} else if (!idCompetence.equals(other.idCompetence))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (note != other.note)
			return false;
		if (profil == null) {
			if (other.profil != null)
				return false;
		} else if (!profil.equals(other.profil))
			return false;
		return true;
	}
	
	
	

}
