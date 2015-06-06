package composants;

import interfaces.IInfos;
import interfaces.IPersistanceSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import SMA.PersistanceSystem;
import classes.ElementDTO;
import classes.EtatInitial;
import classes.EtatInitialDTO;
import classes.Utils;

public class PersistanceSystemImpl extends PersistanceSystem {

	private final String baseName = "Persistance-";
	private SimpleDateFormat dateFormat;
	
	@Override
	protected IPersistanceSystem make_persistance() {
		return new IPersistanceSystem() {

			@Override
			public void sauvegarderSystem(EtatInitial etatInitial) {
				try {

					Path currentRelativePath = Paths.get("");
					String currentDirectory = currentRelativePath.toAbsolutePath().toString();
					
					dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

					Date date = new Date();
					String dateLog = dateFormat.format(date);
					dateLog = dateLog.replace(" ", "_");
					dateLog = dateLog.replace("/", "-");
					dateLog = dateLog.replace(":", "-");
					
					String fileName = currentDirectory+baseName+dateLog+".sma";
					System.out.println("Persistance etat initial : " + fileName);
					
					FileOutputStream fos = new FileOutputStream(fileName);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					List<ElementDTO> elements = new ArrayList<ElementDTO>();
					for (IInfos infos : etatInitial.getInfos()) {
						ElementDTO elementDTO = Utils.iInfosToElementDTO(infos);
						elements.add(elementDTO);
					}

					EtatInitialDTO etatInitialDTO = new EtatInitialDTO();
					etatInitialDTO.setInfos(elements);
					etatInitialDTO.setVitesseApparitionBoite(String
							.valueOf(etatInitial.getVitesseApparitionBoite()));
					etatInitialDTO.setNbApparitionBoite(String
							.valueOf(etatInitial.getNbApparitionBoite()));
					etatInitialDTO.setNbLignes(String.valueOf(etatInitial.getNbLignes()));
					etatInitialDTO.setNbColonnes(String.valueOf(etatInitial.getNbColonnes()));

					oos.writeObject(etatInitialDTO);
					oos.close();
					fos.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}

			}

			@Override
			public EtatInitial getEtatInitial(String nomFichier) {
				EtatInitial etatInitial = new EtatInitial();
				EtatInitialDTO etatInitialDTO;

				File file = new File(nomFichier);
				if (file.exists()) {
					FileInputStream fin;
					try {
						fin = new FileInputStream(nomFichier);
						ObjectInputStream ois = new ObjectInputStream(fin);
						etatInitialDTO = (EtatInitialDTO) ois.readObject();
						ois.close();
						
						List<IInfos> infos = new ArrayList<IInfos>();
						infos.addAll(etatInitialDTO.getInfos());

						etatInitial.setInfos(infos);
						etatInitial.setVitesseApparitionBoite(Integer.valueOf(etatInitialDTO.getVitesseApparitionBoite()));
						etatInitial.setNbApparitionBoite(Integer.valueOf(etatInitialDTO.getNbApparitionBoite()));
						etatInitial.setNbLignes(Integer.valueOf(etatInitialDTO.getNbLignes()));
						etatInitial.setNbColonnes(Integer.valueOf(etatInitialDTO.getNbColonnes()));
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				return etatInitial;
			}
		};
	}

}
