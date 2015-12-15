package ipint.glp.api.itf;

import javax.ejb.Remote;

import ipint.glp.api.DTO.DTO;

@Remote
public interface Service <T extends DTO> {
	
	public T creer(T obj);
	
	public T trouver(T obj);
	
	public T modifier(T ancienObj, T nouvelObj);
	
	public void supprimer(T obj);
	
}
