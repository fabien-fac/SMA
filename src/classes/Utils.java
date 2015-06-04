package classes;

import interfaces.IInfos;

public class Utils {

	public static ElementDTO iInfosToElementDTO(IInfos element){
		ElementDTO elementDTO = new ElementDTO();
		
		elementDTO.setCouleur(element.getCouleur());
		elementDTO.setEnergie(element.getEnergie());
		elementDTO.setNom(element.getNom());
		elementDTO.setPosition(element.getPosition());
		elementDTO.setType(element.getType());
		
		return elementDTO;
	}
}
