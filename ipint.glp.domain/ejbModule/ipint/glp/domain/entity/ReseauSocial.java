package ipint.glp.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReseauSocial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private String id;
	@Column(unique = true)
	private String lien;
	@javax.validation.constraints.NotNull(message = "Aucun message saisi")
	private Profil profil;
	
	public ReseauSocial() {
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the lien
	 */
	public String getLien() {
		return lien;
	}

	/**
	 * @param lien the lien to set
	 */
	public void setLien(String lien) {
		this.lien = lien;
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
	
	//HashCode viré car créait des problemes dont je ne sais pas l'origine

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReseauSocial other = (ReseauSocial) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lien == null) {
			if (other.lien != null)
				return false;
		} else if (!lien.equals(other.lien))
			return false;
		if (profil == null) {
			if (other.profil != null)
				return false;
		} else if (!profil.equals(other.profil))
			return false;
		return true;
	}

	
	
		

}