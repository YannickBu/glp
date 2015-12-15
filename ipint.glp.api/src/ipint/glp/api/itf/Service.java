package ipint.glp.api.itf;

import ipint.glp.api.DTO.DTO;

public interface Service <T extends DTO> {
	
	public T creer(T obj);
	
	public T trouver(T obj);
	
	public T modifier(T ancienObj, T nouvelObj);
	
	public void supprimer(T obj);
	
}
