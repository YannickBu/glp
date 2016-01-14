package ipint.glp.domain.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Diplome implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idDiplome;
	@javax.validation.constraints.NotNull(message = "Veuillez remplir l'année de début")
	@javax.validation.constraints.DecimalMin(value = "1900", message = "Année impossible")
	@javax.validation.constraints.DecimalMax(value = "2100", message = "Année impossible")
	private Integer anneeDebut;
	@javax.validation.constraints.DecimalMin(value = "1900", message = "Année impossible")
	@javax.validation.constraints.DecimalMax(value = "2100", message = "Année impossible")
	private Integer anneFin;
	@javax.validation.constraints.NotNull(message = "Veuillez remplir le nom du diplome")
	private String libelle;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Profil profil;
	
	public Diplome() {
		super();
	}

	/**
	 * @return the idDiplome
	 */
	public Integer getIdDiplome() {
		return idDiplome;
	}

	/**
	 * @param idDiplome the idDiplome to set
	 */
	public void setIdDiplome(Integer idDiplome) {
		this.idDiplome = idDiplome;
	}

	/**
	 * @return the anneeDebut
	 */
	public Integer getAnneeDebut() {
		return anneeDebut;
	}

	/**
	 * @param anneeDebut the anneeDebut to set
	 */
	public void setAnneeDebut(Integer anneeDebut) {
		this.anneeDebut = anneeDebut;
	}

	/**
	 * @return the anneFin
	 */
	public Integer getAnneFin() {
		return anneFin;
	}

	/**
	 * @param anneFin the anneFin to set
	 */
	public void setAnneFin(Integer anneFin) {
		this.anneFin = anneFin;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anneFin == null) ? 0 : anneFin.hashCode());
		result = prime * result + ((anneeDebut == null) ? 0 : anneeDebut.hashCode());
		result = prime * result + ((idDiplome == null) ? 0 : idDiplome.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
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
		Diplome other = (Diplome) obj;
		if (anneFin == null) {
			if (other.anneFin != null)
				return false;
		} else if (!anneFin.equals(other.anneFin))
			return false;
		if (anneeDebut == null) {
			if (other.anneeDebut != null)
				return false;
		} else if (!anneeDebut.equals(other.anneeDebut))
			return false;
		if (idDiplome == null) {
			if (other.idDiplome != null)
				return false;
		} else if (!idDiplome.equals(other.idDiplome))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (profil == null) {
			if (other.profil != null)
				return false;
		} else if (!profil.equals(other.profil))
			return false;
		return true;
	}
	
	
	
	
	

}
