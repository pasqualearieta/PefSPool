package igpe.billiard.file;

import igpe.billiard.core.NameofCommand;
import igpe.billiard.core.GameMode;
import igpe.billiard.gui.MainFrame;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class FileProvider {

	private static Properties commandProperties = new Properties();
	private static Map<Integer, String> indicesOfMatchRescue = new HashMap<Integer, String>();
	private static Map<Integer, String> indicesOfCreatedLevelRescue = new HashMap<Integer, String>();
	private final static String FILE_NAMES_MATCH_RESCUE = "Files/indexOfMatchRescue.txt";
	private final static String FILE_NAMES_CREATED_LEVEL_RESCUE = "Files/indexOfEditorRescue.txt";
	private static Image screenOfTheMatch;
	private static MainFrame principalMainFrame;

	public static void setPrincipalMainFrame(final MainFrame frame) {
		principalMainFrame = frame;
	}

	public static boolean getThatThereAreEditorRescueSaved() {
		if (indicesOfCreatedLevelRescue.isEmpty())
			return false;
		else
			return true;
	}

	public static boolean getThatThereAreMatchRescueSaved() {
		if (indicesOfMatchRescue.isEmpty())
			return false;
		else
			return true;
	}

	static {
		if (!loadCommand())
			standardCommand();

		loadIndexsOfRescue(GameMode.MATCH);
		loadIndexsOfRescue(GameMode.EDITOR);
	}

	public static void standardCommand() {

		commandProperties.setProperty(NameofCommand.PAUSE.name(), KeyEvent.VK_ENTER + "");
		commandProperties.setProperty(NameofCommand.DECREMENT_POWER.name(), KeyEvent.VK_DOWN + "");
		commandProperties.setProperty(NameofCommand.INCREMENT_POWER.name(), KeyEvent.VK_UP + "");
		commandProperties.setProperty(NameofCommand.DIRECTION_RIGHT.name(), KeyEvent.VK_RIGHT + "");
		commandProperties.setProperty(NameofCommand.DIRECTION_LEFT.name(), KeyEvent.VK_LEFT + "");
		commandProperties.setProperty(NameofCommand.HIT.name(), KeyEvent.VK_SPACE + "");
		commandProperties.setProperty(NameofCommand.INDEX_TABLE_SELECTED.name(), "1");
		writeNewCommands();

	}

	// Start command Code
	public static void setOnlyCommand(final NameofCommand command, int keyValue) {
		commandProperties.setProperty(command.name(), keyValue + "");
	}

	private static void writeNewCommands() {
		try {
			File file = new File("Files/command.properties");
			OutputStream out = new FileOutputStream(file);
			commandProperties.store(out, "");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(principalMainFrame, "Impossible Write new Command");
		}
	}

	private static boolean loadCommand() {
		commandProperties.clear();
		File file = new File("Files/command.properties");
		try {
			if (!file.exists())
				return false;

			FileInputStream fileInputStrem = new FileInputStream(file);

			try {
				commandProperties.load(fileInputStrem);
				fileInputStrem.close();
				return true;
			} catch (IOException e) {
				return false;
			}

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(principalMainFrame, "Impossible Load Command");
		}
		return false;
	}

	public void setNewCommand(final NameofCommand command, int keyValue) {
		commandProperties.setProperty(command.name(), keyValue + "");
		writeNewCommands();
	}

	public static int getCommand(final NameofCommand command) {
		return Integer.parseInt(commandProperties.getProperty(command.name()));
	}

	public static List<String> getAllCommand() {
		List<String> commands = new ArrayList<String>();

		commands.add(commandProperties.getProperty(NameofCommand.HIT.name()) + "");
		commands.add(commandProperties.getProperty(NameofCommand.DIRECTION_RIGHT.name()) + "");
		commands.add(commandProperties.getProperty(NameofCommand.DIRECTION_LEFT.name()) + "");
		commands.add(commandProperties.getProperty(NameofCommand.INCREMENT_POWER.name()) + "");
		commands.add(commandProperties.getProperty(NameofCommand.DECREMENT_POWER.name()) + "");
		commands.add(commandProperties.getProperty(NameofCommand.PAUSE.name()) + "");
		commands.add(commandProperties.getProperty(NameofCommand.INDEX_TABLE_SELECTED.name()) + "");
		return commands;

	}

	public static void setAllCommands(final List<String> commands, final boolean permanentSave) {
		int indexOfCommand = 0;
		commandProperties.clear();
		for (String nameCommand : commands) {
			switch (indexOfCommand) {
			case 0:
				commandProperties.setProperty(NameofCommand.HIT.name(), nameCommand);
				indexOfCommand++;
				break;

			case 1:
				commandProperties.setProperty(NameofCommand.DIRECTION_RIGHT.name(), nameCommand);
				indexOfCommand++;
				break;

			case 2:
				commandProperties.setProperty(NameofCommand.DIRECTION_LEFT.name(), nameCommand);
				indexOfCommand++;
				break;
			case 3:
				commandProperties.setProperty(NameofCommand.INCREMENT_POWER.name(), nameCommand);
				indexOfCommand++;
				break;

			case 4:
				commandProperties.setProperty(NameofCommand.DECREMENT_POWER.name(), nameCommand);
				indexOfCommand++;
				break;

			case 5:
				commandProperties.setProperty(NameofCommand.PAUSE.name(), nameCommand);
				indexOfCommand++;
				break;

			case 6:
				commandProperties.setProperty(NameofCommand.INDEX_TABLE_SELECTED.name(), nameCommand);
				indexOfCommand++;
				break;
			default:
				break;
			}
		}
		if (permanentSave)
			writeNewCommands();
	}

	// Finish command Code

	public static void setCurrentStatusOfTheGameIntoAFile(final GameMode modalityOfTheGame,
			final String statusTheGame) {
		try {
			File name = null;
			if (modalityOfTheGame.equals(GameMode.MATCH))
				name = new File("Files/SavedGameMatch/tmpString.txt");

			else if (modalityOfTheGame.equals(GameMode.EDITOR))
				name = new File("Files/SavedGameEditor/tmpString.txt");

			name.createNewFile();
			final FileWriter outFile = new FileWriter(name);

			// Instantiate the BufferedReader Class
			final BufferedWriter bufferwri = new BufferedWriter(outFile);

			bufferwri.write(Crypto.encrypt(statusTheGame));
			bufferwri.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setCurrentStatusTheScreenOfTheGameIntoAFile(final GameMode modalityOfTheGame,
			final BufferedImage currentScreenOfTheGame) {
		try {
			File screen = null;

			if (modalityOfTheGame.equals(GameMode.MATCH))
				screen = new File("resources/imagesSavedGameMatch/tmpImage.png");
			else if (modalityOfTheGame.equals(GameMode.EDITOR))
				screen = new File("resources/imagesSavedGameEditor/tmpImage.png");

			setScreenOfTheGame(currentScreenOfTheGame);
			screen.createNewFile();
			ImageIO.write(currentScreenOfTheGame, "png", screen);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Finish String Rescue

	// Start Match Code

	private static void loadIndexsOfRescue(GameMode gameMode) {

		if (gameMode.equals(GameMode.MATCH)) {
			getIndicesOfMatchRescue().clear();
		} else {
			getIndicesOfEditorRescue().clear();
		}

		String general = new String();

		try {

			FileReader inputFile = null;
			File indices = null;
			if (gameMode.equals(GameMode.MATCH)) {
				indices = new File(FILE_NAMES_MATCH_RESCUE);

			} else if (gameMode.equals(GameMode.EDITOR)) {
				indices = new File(FILE_NAMES_CREATED_LEVEL_RESCUE);
			}

			if (!indices.exists())
				indices.createNewFile();
			inputFile = new FileReader(indices);
			BufferedReader bufferReader = new BufferedReader(inputFile);

			String line;

			while ((line = bufferReader.readLine()) != null) {
				if (line != "\n")
					general += line;
			}

			bufferReader.close();
			String tmp = new String(general);
			general = tmp;

			while (!general.isEmpty()) {
				int indexDot = general.indexOf(";");
				String currentProcess = new String(general.substring(0, indexDot));
				general = general.substring(indexDot + 1, general.length());

				int indexU = currentProcess.indexOf("=");
				String key = new String(currentProcess.substring(0, indexU));

				String value = new String(currentProcess.substring(indexU + 1, currentProcess.length()));
				int keyI = Integer.parseInt(key);

				if (gameMode.equals(GameMode.MATCH))
					getIndicesOfMatchRescue().put(keyI, value);
				else if (gameMode.equals(GameMode.EDITOR))
					getIndicesOfEditorRescue().put(keyI, value);

			}

		} catch (Exception e) {
			System.err.println("Error indices Match Rescue not found" + e.getMessage());
		}

	}

	private static void refreshIndicesOfRescue(final GameMode modalityOfTheGame) {
		File fileName = null;

		if (modalityOfTheGame.equals(GameMode.MATCH))
			fileName = new File(FILE_NAMES_MATCH_RESCUE);

		else if (modalityOfTheGame.equals(GameMode.EDITOR))
			fileName = new File(FILE_NAMES_CREATED_LEVEL_RESCUE);

		try {
			fileName.createNewFile();
			// Create object of FileReader
			FileWriter outFile = new FileWriter(fileName);

			// Instantiate the BufferedReader Class
			BufferedWriter bufferwri = new BufferedWriter(outFile);

			// Variable to hold the one line data
			if (modalityOfTheGame.equals(GameMode.MATCH)) {
				for (Integer key : getIndicesOfMatchRescue().keySet()) {
					bufferwri.write(key.toString() + "=" + getIndicesOfMatchRescue().get(key) + ";");
				}
			} else if (modalityOfTheGame.equals(GameMode.EDITOR)) {
				for (Integer key : getIndicesOfEditorRescue().keySet()) {
					bufferwri.write(key.toString() + "=" + getIndicesOfEditorRescue().get(key) + ";");
				}
			}

			bufferwri.close();

			if (modalityOfTheGame.equals(GameMode.MATCH))
				loadIndexsOfRescue(GameMode.MATCH);
			else if (modalityOfTheGame.equals(GameMode.EDITOR))
				loadIndexsOfRescue(GameMode.EDITOR);

		} catch (Exception e) {
			System.err.println("Error indices Match Rescue not found" + e.getMessage());
		}
	}

	public static void addRescue(final GameMode modalityOfTheGame, final String nameOfTheRescue) {

		try {
			File imageFile = null;
			File stringFile = null;

			if (modalityOfTheGame.equals(GameMode.MATCH)) {
				imageFile = new File("resources/imagesSavedGameMatch/tmpImage.png");

				imageFile.renameTo(new File("resources/imagesSavedGameMatch/" + nameOfTheRescue + ".png"));

				stringFile = new File("Files/SavedGameMatch/tmpString.txt");

				stringFile.renameTo(new File("Files/SavedGameMatch/" + nameOfTheRescue + ".txt"));

			} else if (modalityOfTheGame.equals(GameMode.EDITOR)) {
				imageFile = new File("resources/imagesSavedGameEditor/tmpImage.png");

				imageFile.renameTo(new File("resources/imagesSavedGameEditor/" + nameOfTheRescue + ".png"));
				stringFile = new File("Files/SavedGameEditor/tmpString.txt");
				stringFile.renameTo(new File("Files/SavedGameEditor/" + nameOfTheRescue + ".txt"));
			}

			if (modalityOfTheGame.equals(GameMode.MATCH)) {
				Map<Integer, String> salvataggiTemp = new HashMap<Integer, String>();

				salvataggiTemp.putAll(getIndicesOfMatchRescue());

				getIndicesOfMatchRescue().clear();

				for (Integer k : salvataggiTemp.keySet()) {
					getIndicesOfMatchRescue().put(k + 1, salvataggiTemp.get(k));
				}
				getIndicesOfMatchRescue().put(1, nameOfTheRescue);

				refreshIndicesOfRescue(GameMode.MATCH);
			}

			else if (modalityOfTheGame.equals(GameMode.EDITOR)) {
				Map<Integer, String> salvataggiTemp = new HashMap<Integer, String>();

				salvataggiTemp.putAll(getIndicesOfEditorRescue());

				getIndicesOfEditorRescue().clear();

				for (Integer k : salvataggiTemp.keySet()) {
					getIndicesOfEditorRescue().put(k + 1, salvataggiTemp.get(k));
				}
				getIndicesOfEditorRescue().put(1, nameOfTheRescue);

				refreshIndicesOfRescue(GameMode.EDITOR);
			}

		} catch (Exception e) {
			System.err.println("Error it's not possible add new Rescue" + e.getMessage());
		}

	}

	public static String getRescueContent(final int index, GameMode modalityOfTheGame) {
		String nameOfRescue = null;

		if (modalityOfTheGame.equals(GameMode.MATCH))
			nameOfRescue = new String(getIndicesOfMatchRescue().get(index));

		else if (modalityOfTheGame.equals(GameMode.EDITOR))
			nameOfRescue = new String(getIndicesOfEditorRescue().get(index));

		String content = new String();
		try {

			// Create object of FileReader
			FileReader inputFile = null;

			if (modalityOfTheGame.equals(GameMode.MATCH))
				inputFile = new FileReader("Files/SavedGameMatch/" + nameOfRescue + ".txt");
			else if (modalityOfTheGame.equals(GameMode.EDITOR))
				inputFile = new FileReader("Files/SavedGameEditor/" + nameOfRescue + ".txt");

			// Instantiate the BufferedReader Class
			BufferedReader bufferReader = new BufferedReader(inputFile);

			// Variable to hold the one line data
			String line;

			// Read file line by line and print on the console
			while ((line = bufferReader.readLine()) != null) {
				// if(line!="\n")
				content += line;
			}
			// Close the buffer reader
			bufferReader.close();

			return Crypto.decrypt(content);
		} catch (Exception e) {
			System.err.println("Error file match rescue not exist:" + e.getMessage());
		}

		return content;

	}

	public static void deleteRescue(final GameMode modalityOfTheGame, int indexOfRescue) {
		File file = null;
		File file2 = null;

		if (modalityOfTheGame.equals(GameMode.MATCH)) {
			file = new File("Files/SavedGameMatch/" + getIndicesOfMatchRescue().get(indexOfRescue) + ".txt");
			file2 = new File("resources/imagesSavedGameMatch/" + getIndicesOfMatchRescue().get(indexOfRescue) + ".png");

		}

		else if (modalityOfTheGame.equals(GameMode.EDITOR)) {
			file = new File("Files/SavedGameEditor/" + getIndicesOfEditorRescue().get(indexOfRescue) + ".txt");
			file2 = new File(
					"resources/imagesSavedGameEditor/" + getIndicesOfEditorRescue().get(indexOfRescue) + ".png");

		}

		if (file.delete()) {

			file2.delete();

			if (modalityOfTheGame.equals(GameMode.MATCH))
				getIndicesOfMatchRescue().remove(indexOfRescue);
			else if (modalityOfTheGame.equals(GameMode.EDITOR))
				getIndicesOfEditorRescue().remove(indexOfRescue);

			Map<Integer, String> salvataggiTemp = new HashMap<Integer, String>();

			if (modalityOfTheGame.equals(GameMode.MATCH)) {
				salvataggiTemp.putAll(getIndicesOfMatchRescue());

				getIndicesOfMatchRescue().clear();
			} else if (modalityOfTheGame.equals(GameMode.EDITOR)) {
				salvataggiTemp.putAll(getIndicesOfEditorRescue());

				getIndicesOfEditorRescue().clear();
			}

			int cont = 1;

			for (Integer key : salvataggiTemp.keySet()) {

				if (modalityOfTheGame.equals(GameMode.MATCH))
					getIndicesOfMatchRescue().put(cont, salvataggiTemp.get(key));
				else if (modalityOfTheGame.equals(GameMode.EDITOR))
					getIndicesOfEditorRescue().put(cont, salvataggiTemp.get(key));
				cont++;

			}

			if (modalityOfTheGame.equals(GameMode.MATCH))
				refreshIndicesOfRescue(GameMode.MATCH);
			else if (modalityOfTheGame.equals(GameMode.EDITOR))
				refreshIndicesOfRescue(GameMode.EDITOR);

		}

	}

	public static void overrideRescue(final GameMode modalityOfTheGame, final int indexRescueToDelete,
			final String nameOfNewRescue) {

		deleteRescue(modalityOfTheGame, indexRescueToDelete);

		addRescue(modalityOfTheGame, nameOfNewRescue);
	}

	public static Vector<String> getRescueNames(final GameMode modalityOfTheGame) {
		Vector<String> names = new Vector<String>();

		if (modalityOfTheGame.equals(GameMode.MATCH)) {

			if (!getIndicesOfMatchRescue().isEmpty()) {
				for (Integer i : getIndicesOfMatchRescue().keySet()) {
					names.add(getIndicesOfMatchRescue().get(i));
				}
			}
		} else if (modalityOfTheGame.equals(GameMode.EDITOR)) {
			if (!getIndicesOfEditorRescue().isEmpty()) {
				for (Integer i : getIndicesOfEditorRescue().keySet()) {
					names.add(getIndicesOfEditorRescue().get(i));
				}
			}
		}

		return names;
	}

	public static Map<Integer, String> getIndicesOfMatchRescue() {
		return indicesOfMatchRescue;
	}

	public static Map<Integer, String> getIndicesOfEditorRescue() {
		return indicesOfCreatedLevelRescue;
	}

	public static Image getScreenOfTheGame() {
		return screenOfTheMatch;
	}

	public static void setScreenOfTheGame(final Image screenOfTheGame) {
		FileProvider.screenOfTheMatch = screenOfTheGame;
	}

}
