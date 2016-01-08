package ipint.glp.domain.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UtilisateurGroupes
 *
 */
@Entity

public class UtilisateurGroupes implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idUtilisateurGroupes;
	@javax.validation.constraints.NotNull(message="Veuillez remplir le champ email")
	private String email;
	@javax.validation.constraints.NotNull(message="Veuillez remplir le champ groupe")
	private String groupe;
	private static final long serialVersionUID = 1L;

	public UtilisateurGroupes() {
		super();
	}   
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getGroupe() {
		return this.groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}
   
}
