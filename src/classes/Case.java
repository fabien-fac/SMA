package classes;

import interfaces.IInfos;

import java.util.ArrayList;
import java.util.List;

public class Case {
	
	private List<IInfos> elements = new ArrayList<IInfos>();

	public List<IInfos> getElements() {
		return elements;
	}

	public void setElements(List<IInfos> elements) {
		this.elements = elements;
	}
	
	public void addElement(IInfos element){
		elements.add(element);
	}

	public boolean hasSameElementType(IInfos element){
		for(IInfos elementCase: elements){
			if(elementCase.getType().equals(element.getType())){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean caseVide(){
		return (elements.size() == 0);
	}
}
