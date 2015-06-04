package composants;

import interfaces.IInfos;
import interfaces.IPersistanceSystem;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import classes.ElementDTO;
import classes.Utils;
import SMA.PersistanceSystem;

public class PersistanceSystemImpl extends PersistanceSystem {

	@Override
	protected IPersistanceSystem make_persistance() {
		return new IPersistanceSystem() {

			@Override
			public void sauvegarderSystem(List<IInfos> listInfos) {
				try {

					Path currentRelativePath = Paths.get("");
					String currentDirectory = currentRelativePath
							.toAbsolutePath().toString();

					FileOutputStream fos = new FileOutputStream(currentDirectory+"persistanceSMA");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					List<ElementDTO> elements = new ArrayList<ElementDTO>();
					for (IInfos infos : listInfos) {
						ElementDTO elementDTO = Utils.iInfosToElementDTO(infos);
						elements.add(elementDTO);
					}
					oos.writeObject(elements);
					oos.close();
					fos.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}

			}
		};
	}

}
