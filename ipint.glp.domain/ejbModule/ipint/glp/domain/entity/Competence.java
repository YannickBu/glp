package ipint.glp.domain.entity;

import java.io.Serializable;import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Competence implements Serializable{

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
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Profil profil;
	
	public Competence() {
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
	public Profil getProfil() {
		return profil;
	}

	/**
	 * @param profil the profil to set
	 */
	public void setProfil(Profil profil) {
		this.profil = profil;
	}
	
	@Override
	public String toString(){
		return "[Competence - id="+this.idCompetence+", libelle="+this.libelle+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCompetence == null) ? 0 : idCompetence.hashCode());
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
		Competence other = (Competence) obj;
		if (idCompetence == null) {
			if (other.idCompetence != null)
				return false;
		} else if (!idCompetence.equals(other.idCompetence))
			return false;
		return true;
	}
}
