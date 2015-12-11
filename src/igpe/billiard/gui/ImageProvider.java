package igpe.billiard.gui;

import igpe.billiard.core.Ball;
import igpe.billiard.core.MatchManager;
import igpe.billiard.core.Stick;
import igpe.billiard.core.Table;
import igpe.billiard.file.FileProvider;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImageProvider {
	private static Image backgroundForTransparency;
	private static Image initialPanelBackground;
	private static Image resumeContinuePanelBackground;
	private static Image chooseHowToPlayPanelBackground;
	private static Image loadPanelBackground;
	private static Image levelEditorPanelBackground;
	private static Image settingsPanelBackground;
	private static Image backgroundGamePanel;
	private static Image whiteBall;
	private static Image blackBall;
	private static Image yellowBall;
	private static Image redBall;
	private static Image stick;
	private static Image powerIndicator;
	private static Vector<Image> imageSaveMatch;
	private static Vector<Image> imageSaveEditor;
	private static Vector<Image> numbers;
	private static Vector<Image> commandNotSet;
	private static Vector<Image> littleBackground;
	private static Image deleteBall;
	private static Image deleteBallRollovered;
	private static Image clearAll;
	private static Image clearAllRollovered;
	private static Image save;
	private static Image saveRollovered;
	private static Image yes;
	private static Image yesRollovered;
	private static Image turnLeft;
	private static Image turnRight;
	private static Image turnLeftRollovered;
	private static Image turnRightRollovered;
	private static Image setting;
	private static Image settingRollovered;
	private static Image no;
	private static Image noRollovered;
	private static Image ok;
	private static Image okRollovered;
	private static Image newGame;
	private static Image newGameRollovered;
	private static Image loadGame;
	private static Image loadGameRollevered;
	private static Image loadMatch;
	private static Image loadMatchRollover;
	private static Image loadLevel;
	private static Image loadLevelRollover;
	private static Image back;
	private static Image impossibleEstabilishedConnection;
	private static Image backRollovered;
	private static Image mevsMe;
	private static Image mevsMeRollovered;
	private static Image easy;
	private static Image easyRollovered;
	private static Image medium;
	private static Image mediumRollovered;
	private static Image onLine;
	private static Image onLineRollovered;
	private static Image offLine;
	private static Image offLineRollovered;
	private static Image hard;
	private static Image hardRollovered;
	private static Image whiteBallButton;
	private static Image whiteBallButtonRollovered;
	private static Image blackBallButton;
	private static Image blackBallButtonRollovered;
	private static Image redBallButton;
	private static Image redBallButtonRollovered;
	private static Image yellowBallButton;
	private static Image yellowBallButtonRollovered;
	private static Image resumeButton;
	private static Image resumeButtonRollovered;
	private static Image settingsButton;
	private static Image settingsButtonRollovered;
	private static Image exitButton;
	private static Image exitButtonRollovered;
	private static Image settingCommandsButton;
	private static Image settingCommandsButtonRollovered;
	private static Image hitButton;
	private static Image hitButtonRollovered;
	private static Image pauseButton;
	private static Image pauseButtonRollovered;
	private static Image rotateStickRightButton;
	private static Image rotateStickRightButtonRollovered;
	private static Image rotateStickLeftButton;
	private static Image rotateStickLeftButtonRollovered;
	private static Image incrementIndicatorButton;
	private static Image incrementIndicatorButtonRollovered;
	private static Image decrementIndicatorButton;
	private static Image decrementIndicatorButtonRollovered;
	private static Image createGame;
	private static Image createGameRollover;
	private static Image confirmButton;
	private static Image confirmButtonRollovered;
	private static Image reset;
	private static Image resetRollovered;
	private static Image saveChangesButton;
	private static Image saveChangesButtonRollovered;
	private static Image musicOn;
	private static Image musicOnRollovered;
	private static Image musicOff;
	private static Image musicOffRollovered;
	private static Image pauseMusic;
	private static Image pauseMusicRollovered;
	private static Image stopMusic;
	private static Image stopMusicRollovered;
	private static Image playMusic;
	private static Image playMusicRollovered;
	private static Image previusMusic;
	private static Image previusMusicRollovered;
	private static Image nextMusic;
	private static Image nextMusicRollovered;
	private static Image connect;
	private static Image connectRollovered;
	private static Map<Integer, Image> tableImage;
	private static Map<Integer, Image> whiteBallFoulImage;
	private static Map<Integer, Image> noneBallTouchedFoulImage;
	private static Map<Integer, Image> firstBallTouchedIsTheOppositeColorFoul;
	private static Map<Integer, Image> containerYellowBall;
	private static Map<Integer, Image> containerRedBall;
	private static Map<Integer, Image> opponentWhiteBallFoulImage;
	private static Map<Integer, Image> opponentnoneBallTouchedFoulImage;
	private static Map<Integer, Image> opponentfirstBallTouchedIsTheOppositeColorFoul;
	private static Map<String, Image> keyBoard;
	private static Image errorPointer;
	private static Image numberBall;
	private static Image difficult;
	private static Image colorBall;
	private static Image chatIcon;
	private static Image timeMaxToCompleteLevel;
	private static Image handPointer;
	private static Image sectSettings;
	private static Image saveMadeCorrectly;
	private static Image thereAreNoSavedGame;
	private static Image thereAreNoSavedGameCreated;
	private static Image theBlackBallIsNotOnTheTable;
	private static Image theWhiteBallIsNotOnTheTable;
	private static Image maximumNumberOfBallReached;
	private static Image newMatch;
	private static Image loadEditor;
	private static Image modify;
	private static Image play;
	private static Image info;
	private static Image deleteButton;
	private static Image containerBlackBall;
	private static Image arrowUp;
	private static Image arrowDown;
	private static Image point;
	private static Image disconnected;
	private static Image serverPort;
	private static Image serverIP;
	private static Image waitForYourOpponentToConnectToTheGame;
	private static Image name;
	private static Image insertYourName;
	private static Image selectAnotherName;
	private static Image firstPlayerTurn;
	private static Image secondPlayerTurn;
	private static Image onTheTableThereMustBeAtLeastARedBall;
	private static Image onTheTableThereMustBeAtLeastAYellowBall;
	private static Image doYouWantToGoOutAndDisconnectFromTheGame;
	private static Image doYouReallyWantToDeleteEveryThingAndRestartToReposion;
	private static Image doYouWantToSaveBeforeExit;
	private static Image doYouWantToSave;
	private static Image doYouWantToOverwriteTheRescue;
	private static Image doYouReallyWantToResetTheCommands;
	private static Image doYouWantToSaveChangesToTheCurrentGame;
	private static Image doYouWantToSaveYourChangesSoPermanently;
	private static Image doYouLeaveTheMatch;
	private static Image doYouWantToResetTheSettings;
	private static Image doYouReallyWantToExit;
	private static Image doYouReallyWantToRemoveThisBall;
	private static Image youWin;
	private static Image youLose;
	private static Image youHaveNotMadeAnyChangesYouWantToGoBack;
	private static Image youCanNotDeleteIfYouDoNotHaveBallsOnTheTable;
	private static Image youHaveAddedTooManyBallsThanThoseSet;
	private static Image youCanNotRemoveTheWhiteBall;
	private static Image youCanNotRemoveTheBlackBall;
	private static Image youMustAddBall;
	private static Image youCanNotAddAnotherBall;
	private static Image youCanNotPlaceABallOnAnotherBall;
	private static Image youWinOnLineMode;
	private static Image youLoseOnLineMode;
	private static Image createTable;
	private static Image joinATable;
	private static Image joinATableRolloverd;
	private static Image sendMessage;
	private static Image sendMessageRollovered;
	private static Image clearChat;
	private static Image thereAreNoTableCreated;
	private static Image clearChatRollovered;
	private static Image backgroundChat;
	private static Image backgroundList;
	private static Vector<Image> power;

	static {
		try {

			setBackgroundList(ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/background/listBackgroundConnectedPlayer.png"))
					.getScaledInstance(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT, Image.SCALE_SMOOTH));

			backgroundChat = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/background/chat.png"))
					.getScaledInstance(400, 400, Image.SCALE_SMOOTH);

			sendMessage = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/sendMessage.png"))
					.getScaledInstance(200, 65, Image.SCALE_SMOOTH);

			power = new Vector<Image>();

			for (int i = 1; i < 25; i++) {
				Image tmp = ImageIO
						.read(Thread.currentThread().getContextClassLoader().getResource("images/power/" + i + ".png"))
						.getScaledInstance(40, 20, Image.SCALE_SMOOTH);
				power.add(tmp);
			}

			sendMessageRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/sendMessageRollovered.png"))
					.getScaledInstance(205, 70, Image.SCALE_SMOOTH);

			clearChat = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/clearChat.png"))
					.getScaledInstance(200, 65, Image.SCALE_SMOOTH);

			clearChatRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/clearChatRollovered.png"))
					.getScaledInstance(205, 70, Image.SCALE_SMOOTH);

			setJoinATableRolloverd(ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/joinaATableRollover.png"))
					.getScaledInstance(255, 70, Image.SCALE_SMOOTH));

			createTable = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/createTable.png"))
					.getScaledInstance(200, 65, Image.SCALE_SMOOTH);

			joinATable = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/joinATable.png"))
					.getScaledInstance(250, 65, Image.SCALE_SMOOTH);

			backgroundGamePanel = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/background/wall.png"))
					.getScaledInstance(MainFrame.GAME_WIDTH, MainFrame.GAME_HEIGHT, Image.SCALE_SMOOTH);

			chooseHowToPlayPanelBackground = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/background/thirdBackground.png"))
					.getScaledInstance(MainFrame.STANDAR_WIDTH, MainFrame.STANDARD_HEIGHT - 15, Image.SCALE_SMOOTH);
			levelEditorPanelBackground = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/background/BackgroundEditor.png"))
					.getScaledInstance(MainFrame.GAME_WIDTH, MainFrame.GAME_HEIGHT, Image.SCALE_SMOOTH);
			backgroundForTransparency = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/background/transparency.png"))
					.getScaledInstance(MainFrame.GAME_WIDTH, MainFrame.GAME_HEIGHT, Image.SCALE_SMOOTH);

			loadPanelBackground = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/background/fourthBackground.png"))
					.getScaledInstance(MainFrame.STANDAR_WIDTH, MainFrame.STANDARD_HEIGHT - 15, Image.SCALE_SMOOTH);

			musicOnRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/musicOnRollovered.png"))
					.getScaledInstance(155, 55, Image.SCALE_SMOOTH);

			musicOffRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/musicOfRollovered.png"))
					.getScaledInstance(155, 55, Image.SCALE_SMOOTH);

			pauseMusic = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttonsMusic/pauseMusic.png"))
					.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			pauseMusicRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttonsMusic/pauseMusic.png"))
					.getScaledInstance(52, 52, Image.SCALE_SMOOTH);

			stopMusic = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttonsMusic/stopMusic.png"))
					.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

			stopMusicRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttonsMusic/stopMusic.png"))
					.getScaledInstance(52, 52, Image.SCALE_SMOOTH);

			playMusic = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttonsMusic/playMusic.png"))
					.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

			playMusicRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttonsMusic/playMusic.png"))
					.getScaledInstance(52, 52, Image.SCALE_SMOOTH);

			previusMusic = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttonsMusic/previusMusic.png"))
					.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

			previusMusicRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttonsMusic/previusMusic.png"))
					.getScaledInstance(52, 52, Image.SCALE_SMOOTH);

			nextMusic = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttonsMusic/nextMusic.png"))
					.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

			nextMusicRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttonsMusic/nextMusic.png"))
					.getScaledInstance(52, 52, Image.SCALE_SMOOTH);

			musicOff = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/musicOFF.png"))
					.getScaledInstance(150, 50, Image.SCALE_SMOOTH);

			musicOn = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/musicON.png"))
					.getScaledInstance(150, 50, Image.SCALE_SMOOTH);

			confirmButton = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/confirm.png"))
					.getScaledInstance(200, 65, Image.SCALE_SMOOTH);

			confirmButtonRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/confirmRollovered.png"))
					.getScaledInstance(200, 65, Image.SCALE_SMOOTH);

			reset = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/reset.png"))
					.getScaledInstance(200, 65, Image.SCALE_SMOOTH);

			resetRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/resetRollovered.png"))
					.getScaledInstance(200, 65, Image.SCALE_SMOOTH);

			saveChangesButton = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/saveChanges.png"))
					.getScaledInstance(250, 65, Image.SCALE_SMOOTH);

			saveChangesButtonRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/saveChangesRollovered.png"))
					.getScaledInstance(255, 70, Image.SCALE_SMOOTH);

			settingsPanelBackground = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/background/settingsBackground.png"))
					.getScaledInstance(MainFrame.GAME_WIDTH, MainFrame.GAME_HEIGHT, Image.SCALE_SMOOTH);

			keyBoard = new HashMap<String, Image>();

			initializeMapKeyboard(KeyEvent.VK_A);
			initializeMapKeyboard(KeyEvent.VK_B);
			initializeMapKeyboard(KeyEvent.VK_C);
			initializeMapKeyboard(KeyEvent.VK_D);
			initializeMapKeyboard(KeyEvent.VK_E);
			initializeMapKeyboard(KeyEvent.VK_F);
			initializeMapKeyboard(KeyEvent.VK_G);
			initializeMapKeyboard(KeyEvent.VK_H);
			initializeMapKeyboard(KeyEvent.VK_I);
			initializeMapKeyboard(KeyEvent.VK_L);
			initializeMapKeyboard(KeyEvent.VK_M);
			initializeMapKeyboard(KeyEvent.VK_N);
			initializeMapKeyboard(KeyEvent.VK_O);
			initializeMapKeyboard(KeyEvent.VK_P);
			initializeMapKeyboard(KeyEvent.VK_Q);
			initializeMapKeyboard(KeyEvent.VK_R);
			initializeMapKeyboard(KeyEvent.VK_S);
			initializeMapKeyboard(KeyEvent.VK_T);
			initializeMapKeyboard(KeyEvent.VK_U);
			initializeMapKeyboard(KeyEvent.VK_V);
			initializeMapKeyboard(KeyEvent.VK_Z);
			initializeMapKeyboard(KeyEvent.VK_W);
			initializeMapKeyboard(KeyEvent.VK_Y);
			initializeMapKeyboard(KeyEvent.VK_J);
			initializeMapKeyboard(KeyEvent.VK_K);
			initializeMapKeyboard(KeyEvent.VK_X);
			initializeMapKeyboard(KeyEvent.VK_SPACE);
			initializeMapKeyboard(KeyEvent.VK_ENTER);
			initializeMapKeyboard(KeyEvent.VK_UP);
			initializeMapKeyboard(KeyEvent.VK_DOWN);
			initializeMapKeyboard(KeyEvent.VK_RIGHT);
			initializeMapKeyboard(KeyEvent.VK_LEFT);
			initializeMapKeyboard(KeyEvent.VK_SHIFT);
			initializeMapKeyboard(KeyEvent.VK_BACK_SPACE);

			resumeButton = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/Resume.png"))
					.getScaledInstance(200, 65, Image.SCALE_SMOOTH);

			resumeButtonRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/ResumeRollovered.png"))
					.getScaledInstance(203, 68, Image.SCALE_SMOOTH);

			settingsButton = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/Settings.png"))
					.getScaledInstance(200, 65, Image.SCALE_SMOOTH);

			settingsButtonRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/SettingsRollovered.png"))
					.getScaledInstance(205, 70, Image.SCALE_SMOOTH);

			exitButton = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/Exit.png"))
					.getScaledInstance(130, 65, Image.SCALE_SMOOTH);

			exitButtonRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/ExitRollovered.png"))
					.getScaledInstance(135, 70, Image.SCALE_SMOOTH);

			settingCommandsButton = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/SettingCommands.png"))
					.getScaledInstance(180, 100, Image.SCALE_SMOOTH);

			settingCommandsButtonRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/SettingCommandsRollovered.png"))
					.getScaledInstance(185, 105, Image.SCALE_SMOOTH);

			pauseButton = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/pauseButton.png"))
					.getScaledInstance(70, 90, Image.SCALE_SMOOTH);

			pauseButtonRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/pauseButtonRollovered.png"))
					.getScaledInstance(75, 95, Image.SCALE_SMOOTH);

			decrementIndicatorButton = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/decrementIndicatorButton.png"))
					.getScaledInstance(70, 70, Image.SCALE_SMOOTH);

			decrementIndicatorButtonRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/decrementIndicatorButtonRollovered.png"))
					.getScaledInstance(75, 75, Image.SCALE_SMOOTH);

			incrementIndicatorButton = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/incrementIndicatorButton.png"))
					.getScaledInstance(70, 60, Image.SCALE_SMOOTH);

			incrementIndicatorButtonRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/incrementIndicatorButtonRollovered.png"))
					.getScaledInstance(75, 65, Image.SCALE_SMOOTH);

			rotateStickLeftButton = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/rotateStickLefttButton.png"))
					.getScaledInstance(70, 100, Image.SCALE_SMOOTH);

			rotateStickLeftButtonRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/rotateStickLefttButtonRollovered.png"))
					.getScaledInstance(75, 105, Image.SCALE_SMOOTH);

			rotateStickRightButton = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/rotateStickRightButton.png"))
					.getScaledInstance(70, 100, Image.SCALE_SMOOTH);

			rotateStickRightButtonRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/rotateStickRightButtonRollovered.png"))
					.getScaledInstance(75, 105, Image.SCALE_SMOOTH);

			hitButton = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/hitButton.png"))
					.getScaledInstance(160, 70, Image.SCALE_SMOOTH);

			hitButtonRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/hitButtonRollovered.png"))
					.getScaledInstance(165, 75, Image.SCALE_SMOOTH);

			arrowDown = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/components/frecciaSu.png"))
					.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			arrowUp = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/components/frecciaGiu.png"))
					.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			tableImage = new HashMap<Integer, Image>();
			whiteBallFoulImage = new HashMap<Integer, Image>();
			noneBallTouchedFoulImage = new HashMap<Integer, Image>();
			firstBallTouchedIsTheOppositeColorFoul = new HashMap<Integer, Image>();
			opponentfirstBallTouchedIsTheOppositeColorFoul = new HashMap<Integer, Image>();
			opponentnoneBallTouchedFoulImage = new HashMap<Integer, Image>();
			opponentWhiteBallFoulImage = new HashMap<Integer, Image>();
			for (int i = 1; i < 4; i++) {
				Image table = ImageIO
						.read(Thread.currentThread().getContextClassLoader()
								.getResource("images/table/TableBorder" + i + ".png"))
						.getScaledInstance(Table.WIDTH + 96, Table.HEIGHT + 90, Image.SCALE_SMOOTH);
				Image youPocketedTheWhiteBallImage = ImageIO
						.read(Thread.currentThread().getContextClassLoader()
								.getResource("images/foul/YouPocketedTheWhiteBall" + i + ".png"))
						.getScaledInstance(300, 40, Image.SCALE_SMOOTH);
				Image youHaveNotHitAnyBallImage = ImageIO
						.read(Thread.currentThread().getContextClassLoader()
								.getResource("images/foul/YouHaveNotHitAnyBall" + i + ".png"))
						.getScaledInstance(300, 40, Image.SCALE_SMOOTH);
				Image theFirstBallHitIsTheOppositeColorImage = ImageIO
						.read(Thread.currentThread().getContextClassLoader()
								.getResource("images/foul/TheFirstBallHitIsTheOppositeColor" + i + ".png"))
						.getScaledInstance(400, 40, Image.SCALE_SMOOTH);
				Image theFirstBallHitFromTheOpponentisTheOppositeColorImage = ImageIO
						.read(Thread.currentThread().getContextClassLoader().getResource(
								"images/foul/theFirstBallHitFromTheOpponentisTheOppositeColor" + i + ".png"))
						.getScaledInstance(400, 60, Image.SCALE_SMOOTH);
				Image TheOpponentHaveNotHitAnyBallsImage = ImageIO
						.read(Thread.currentThread().getContextClassLoader()
								.getResource("images/foul/TheOpponentHaveNotHitAnyBalls" + i + ".png"))
						.getScaledInstance(400, 60, Image.SCALE_SMOOTH);
				Image TheOpponentPocketedTheWhiteBallImage = ImageIO
						.read(Thread.currentThread().getContextClassLoader()
								.getResource("images/foul/TheOpponentPocketedTheWhiteBall" + i + ".png"))
						.getScaledInstance(400, 60, Image.SCALE_SMOOTH);
				tableImage.put(i, table);
				whiteBallFoulImage.put(i, youPocketedTheWhiteBallImage);
				noneBallTouchedFoulImage.put(i, youHaveNotHitAnyBallImage);
				firstBallTouchedIsTheOppositeColorFoul.put(i, theFirstBallHitIsTheOppositeColorImage);
				opponentfirstBallTouchedIsTheOppositeColorFoul.put(i,
						theFirstBallHitFromTheOpponentisTheOppositeColorImage);
				opponentnoneBallTouchedFoulImage.put(i, TheOpponentHaveNotHitAnyBallsImage);
				opponentWhiteBallFoulImage.put(i, TheOpponentPocketedTheWhiteBallImage);
			}
			containerYellowBall = new HashMap<Integer, Image>();
			containerRedBall = new HashMap<Integer, Image>();
			for (int i = 0; i < 7; i++) {
				Image containerYellowBallImage = ImageIO
						.read(Thread.currentThread().getContextClassLoader()
								.getResource("images/ballContainer/ContainerYellowBall" + i + ".png"))
						.getScaledInstance(240, 65, Image.SCALE_SMOOTH);
				Image containerRedBallImage = ImageIO
						.read(Thread.currentThread().getContextClassLoader()
								.getResource("images/ballContainer/ContainerRedBall" + i + ".png"))
						.getScaledInstance(240, 65, Image.SCALE_SMOOTH);
				containerYellowBall.put(i, containerYellowBallImage);
				containerRedBall.put(i, containerRedBallImage);
			}
			containerBlackBall = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/ballContainer/ContainerBlackBall.png"))
					.getScaledInstance(240, 65, Image.SCALE_SMOOTH);
			turnRight = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/turnRight.png"))
					.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			chatIcon = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/components/chaticon.png"))
					.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

			turnLeft = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/turnLeft.png"))
					.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			turnRightRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/turnRight.png"))
					.getScaledInstance(65, 65, Image.SCALE_SMOOTH);
			turnLeftRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/turnLeft.png"))
					.getScaledInstance(65, 65, Image.SCALE_SMOOTH);

			deleteButton = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/delete.png"))
					.getScaledInstance(110, 90, Image.SCALE_SMOOTH);
			numberBall = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/NumberBall.png"))
					.getScaledInstance(75, 30, Image.SCALE_SMOOTH);

			difficult = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/Difficult.png"))
					.getScaledInstance(75, 30, Image.SCALE_SMOOTH);

			colorBall = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/ColorBall.png"))
					.getScaledInstance(75, 30, Image.SCALE_SMOOTH);

			timeMaxToCompleteLevel = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/TimeMaxToCompleteLevel.png"))
					.getScaledInstance(60, 40, Image.SCALE_SMOOTH);

			info = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/components/info.png"))
					.getScaledInstance(60, 40, Image.SCALE_SMOOTH);
			secondPlayerTurn = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/components/yourTurn.png"))
					.getScaledInstance(210, 290, Image.SCALE_SMOOTH);

			firstPlayerTurn = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/components/firstPlayerTurn.png"))
					.getScaledInstance(200, 230, Image.SCALE_SMOOTH);
			play = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/play.png"))
					.getScaledInstance(100, 80, Image.SCALE_SMOOTH);
			saveRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/SaveRollovered.png"))
					.getScaledInstance(170, 70, Image.SCALE_SMOOTH);
			modify = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/modify.png"))
					.getScaledInstance(110, 80, Image.SCALE_SMOOTH);
			deleteBall = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/deleteBall.png"))
					.getScaledInstance(180, 40, Image.SCALE_SMOOTH);

			deleteBallRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/DeleteBallRollovered.png"))
					.getScaledInstance(190, 50, Image.SCALE_SMOOTH);
			loadLevel = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/LoadLevel.png"))
					.getScaledInstance(250, 70, Image.SCALE_SMOOTH);
			loadLevelRollover = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/LoadLevelRollovered.png"))
					.getScaledInstance(255, 75, Image.SCALE_SMOOTH);
			youWin = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/components/youWin.png"))
					.getScaledInstance(700, 250, Image.SCALE_SMOOTH);
			youLose = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/components/youLose.png"))
					.getScaledInstance(700, 250, Image.SCALE_SMOOTH);
			loadMatch = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/LoadMatch.png"))
					.getScaledInstance(250, 70, Image.SCALE_SMOOTH);
			loadMatchRollover = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/LoadMarchRollovered.png"))
					.getScaledInstance(255, 75, Image.SCALE_SMOOTH);
			loadGame = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/LoadGame.png"))
					.getScaledInstance(250, 60, Image.SCALE_SMOOTH);
			loadGameRollevered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/LoadGameRollovered.png"))
					.getScaledInstance(255, 65, Image.SCALE_SMOOTH);
			yes = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/Yes.png"))
					.getScaledInstance(80, 30, Image.SCALE_SMOOTH);
			no = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/No.png"))
					.getScaledInstance(80, 30, Image.SCALE_SMOOTH);
			ok = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/Ok.png"))
					.getScaledInstance(80, 30, Image.SCALE_SMOOTH);
			yesRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/YesRollovered.png"))
					.getScaledInstance(85, 35, Image.SCALE_SMOOTH);
			noRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/NoRollovered.png"))
					.getScaledInstance(85, 35, Image.SCALE_SMOOTH);
			okRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/OkRollovered.png"))
					.getScaledInstance(85, 35, Image.SCALE_SMOOTH);
			back = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/Back.png"))
					.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
			backRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/BackRollovered.png"))
					.getScaledInstance(105, 55, Image.SCALE_SMOOTH);
			newGame = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/NewGame.png"))
					.getScaledInstance(250, 60, Image.SCALE_SMOOTH);
			newGameRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/NewGameRollovered.png"))
					.getScaledInstance(255, 65, Image.SCALE_SMOOTH);
			createGame = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/editor.png"))
					.getScaledInstance(250, 60, Image.SCALE_SMOOTH);
			createGameRollover = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/editorRollover.png"))
					.getScaledInstance(255, 65, Image.SCALE_SMOOTH);
			errorPointer = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/components/error.png"))
					.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			handPointer = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/components/hanPointer.png"))
					.getScaledInstance(30, 45, Image.SCALE_SMOOTH);
			setting = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/components/bo.png"))
					.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			settingRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/components/bo.png"))
					.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
			save = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/save.png"))
					.getScaledInstance(165, 65, Image.SCALE_SMOOTH);
			clearAll = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/ClearAll.png"))
					.getScaledInstance(180, 40, Image.SCALE_SMOOTH);
			clearAllRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/ClearAllRollovered.png"))
					.getScaledInstance(185, 45, Image.SCALE_SMOOTH);
			refreshImageOfSavedMatch();

			refreshImageOfLevelCreated();
			numbers = new Vector<Image>();
			for (int i = 0; i <= 10; i++) {
				Image number;
				if (i != 10) {
					number = ImageIO.read(
							Thread.currentThread().getContextClassLoader().getResource("images/numbers/" + i + ".png"))

							.getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING);
				} else {
					number = ImageIO
							.read(Thread.currentThread().getContextClassLoader()
									.getResource("images/numbers/twoPoints.png"))
							.getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING);
				}

				numbers.addElement(number);
			}

			commandNotSet = new Vector<Image>();
			for (int i = 0; i < 6; i++) {
				Image description;
				description = ImageIO
						.read(Thread.currentThread().getContextClassLoader()
								.getResource("images/description/" + i + ".png"))
						.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

				commandNotSet.add(description);
			}

			littleBackground = new Vector<Image>();
			for (int i = 1; i <= 17; i++) {
				Image littleBackgroundImage = ImageIO
						.read(Thread.currentThread().getContextClassLoader()
								.getResource("images/littleBackground/littleBackground" + i + ".png"))
						.getScaledInstance(495, 275, Image.SCALE_AREA_AVERAGING);
				littleBackground.addElement(littleBackgroundImage);
			}

			mevsMe = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/MevsMe.png"))
					.getScaledInstance(250, 60, Image.SCALE_SMOOTH);

			mevsMeRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/MevsMeRollovered.png"))
					.getScaledInstance(255, 65, Image.SCALE_SMOOTH);

			onLine = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/OnLine.png"))
					.getScaledInstance(220, 70, Image.SCALE_SMOOTH);

			onLineRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/OnLineRollovered.png"))
					.getScaledInstance(230, 80, Image.SCALE_SMOOTH);

			offLine = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/OffLine.png"))
					.getScaledInstance(220, 70, Image.SCALE_SMOOTH);

			offLineRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/OffLineRollovered.png"))
					.getScaledInstance(230, 80, Image.SCALE_SMOOTH);

			initialPanelBackground = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/background/FirstBackgroung.png"))
					.getScaledInstance(MainFrame.STANDAR_WIDTH, MainFrame.STANDARD_HEIGHT - 15, Image.SCALE_SMOOTH);

			easy = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/Easy.png"))
					.getScaledInstance(250, 60, Image.SCALE_SMOOTH);

			easyRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/EasyRollovered.png"))
					.getScaledInstance(255, 65, Image.SCALE_SMOOTH);

			medium = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/Medium.png"))
					.getScaledInstance(250, 60, Image.SCALE_SMOOTH);

			mediumRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/MediumRollovered.png"))
					.getScaledInstance(255, 65, Image.SCALE_SMOOTH);

			hard = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("images/buttons/Hard.png"))
					.getScaledInstance(250, 60, Image.SCALE_SMOOTH);

			hardRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/HardRollovered.png"))
					.getScaledInstance(255, 65, Image.SCALE_SMOOTH);

			resumeContinuePanelBackground = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/background/SecondBackground.png"))
					.getScaledInstance(MainFrame.STANDAR_WIDTH, MainFrame.STANDARD_HEIGHT - 15, Image.SCALE_SMOOTH);

			blackBall = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/ball/BlackBall.png"))
					.getScaledInstance(Ball.getDiameter(), Ball.getDiameter(), Image.SCALE_SMOOTH);
			whiteBall = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/ball/WhiteBall.png"))
					.getScaledInstance(Ball.getDiameter(), Ball.getDiameter(), Image.SCALE_SMOOTH);
			redBall = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/ball/RedBall.png"))
					.getScaledInstance(Ball.getDiameter(), Ball.getDiameter(), Image.SCALE_SMOOTH);
			yellowBall = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/ball/YellowBall.png"))
					.getScaledInstance(Ball.getDiameter(), Ball.getDiameter(), Image.SCALE_SMOOTH);
			whiteBallButton = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/WhiteBallButton.png"))
					.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			blackBallButton = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/BlackBallButton.png"))
					.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			redBallButton = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/RedBallButton.png"))
					.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			yellowBallButton = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/YellowBallButton.png"))
					.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			whiteBallButtonRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/WhiteBallButton.png"))
					.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			blackBallButtonRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/BlackBallButton.png"))
					.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			redBallButtonRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/RedBallButton.png"))
					.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			yellowBallButtonRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/buttons/YellowBallButton.png"))
					.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			stick = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/components/Stick.png"))
					.getScaledInstance(Stick.WIDTH, Stick.HEIGHT, Image.SCALE_SMOOTH);
			powerIndicator = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/components/PowerIndicator.png"))
					.getScaledInstance(igpe.billiard.core.PowerIndicator.getWidth(),
							igpe.billiard.core.PowerIndicator.getHeight(), Image.SCALE_SMOOTH);

			doYouWantToSaveYourChangesSoPermanently = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/doYouWantToSaveYourChangesSoPermanently.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			name = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/description/name.png"))
					.getScaledInstance(60, 30, Image.SCALE_SMOOTH);
			serverPort = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/serverPort.png"))
					.getScaledInstance(90, 30, Image.SCALE_SMOOTH);
			serverIP = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/description/serverIP.png"))
					.getScaledInstance(90, 30, Image.SCALE_SMOOTH);

			doYouReallyWantToResetTheCommands = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/doYouReallyWantToResetTheCommands.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			doYouWantToSaveChangesToTheCurrentGame = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/doYouWantToSaveChangesToTheCurrentGame.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			doYouWantToSaveBeforeExit = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/DoYouWantToSaveBeforeExit.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			doYouWantToGoOutAndDisconnectFromTheGame = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/doYouWantToGoOutAndDisconnectFromTheGame.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			thereAreNoSavedGame = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/thereAreNoSavedGames.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			impossibleEstabilishedConnection = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/impossibleEstabilishedConnection.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			thereAreNoSavedGameCreated = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/thereAreNoSavedGamesCreated.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			doYouReallyWantToDeleteEveryThingAndRestartToReposion = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource(
							"images/description/DoYouReallyWantToDeleteEverythingAndStartToReposition.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			youHaveNotMadeAnyChangesYouWantToGoBack = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/youHaveNotMadeAnyChangesYouWantToGoBack.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			doYouWantToResetTheSettings = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/DoYouWantToResetTheSettings.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			doYouLeaveTheMatch = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/doYouLeaveTheMatch.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			youCanNotDeleteIfYouDoNotHaveBallsOnTheTable = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/YouCanNotDeleteIfYouDoNotHaveBallsOnTheTable.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
			youCanNotAddAnotherBall = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/YouCanNotAddAnotherBall.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
			maximumNumberOfBallReached = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/MaximumNumberOfBallReached.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
			theWhiteBallIsNotOnTheTable = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/TheWhiteBallIsNotOnTheTable.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
			youHaveAddedTooManyBallsThanThoseSet = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/YouHaveAddedTooManyBallsThanThoseSet.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
			theBlackBallIsNotOnTheTable = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/TheBlackBallIsNotOnTheTable.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
			saveMadeCorrectly = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/saveMadeCorrectly.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			onTheTableThereMustBeAtLeastAYellowBall = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/OnTheTableThereMustBeAtLeastAYellowBall.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
			onTheTableThereMustBeAtLeastARedBall = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/OnTheTableThereMustBeAtLeastARedBall.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
			youCanNotRemoveTheBlackBall = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/YouCanNotRemoveTheBlackBall.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
			youCanNotRemoveTheWhiteBall = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/YouCanNotRemoveTheWhiteBall.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			disconnected = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/disconnected.png"))
					.getScaledInstance(300, 140, Image.SCALE_SMOOTH);
			thereAreNoTableCreated = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/thereAreNoTableCreated.png"))
					.getScaledInstance(300, 140, Image.SCALE_SMOOTH);

			youWinOnLineMode = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/youWinOnLineMode.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
			youLoseOnLineMode = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/youLoseOnLineMode.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			selectAnotherName = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/selectAnotherName.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			doYouReallyWantToRemoveThisBall = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/DoYouReallyWantToRemoveThisBall.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			insertYourName = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/insertYourName.png"))
					.getScaledInstance(200, 80, Image.SCALE_SMOOTH);

			connect = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/description/connect.png"))
					.getScaledInstance(150, 60, Image.SCALE_SMOOTH);
			connectRollovered = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/connectRollovered.png"))
					.getScaledInstance(155, 65, Image.SCALE_SMOOTH);
			doYouWantToSave = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/doYouWantToSave.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);

			waitForYourOpponentToConnectToTheGame = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/waitForYourOpponentToConnectToTheGame.png"))
					.getScaledInstance(310, 120, Image.SCALE_SMOOTH);

			doYouWantToOverwriteTheRescue = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/doYouWantToOverwriteTheRescue.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
			youCanNotPlaceABallOnAnotherBall = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/YouCanNotPlaceABallOnAnotherBall.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
			doYouReallyWantToExit = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/DoYouReallyWantToExit.png"))
					.getScaledInstance(300, 80, Image.SCALE_SMOOTH);
			youMustAddBall = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/YouMustAddBall.png"))
					.getScaledInstance(250, 70, Image.SCALE_SMOOTH);
			sectSettings = ImageIO
					.read(Thread.currentThread().getContextClassLoader()
							.getResource("images/description/SectSettings.png"))
					.getScaledInstance(200, 60, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new MainFrame(),
					"Error during Load Images, please close, refresh and restart game", null,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static Image getWaitForYourOpponentToConnectToTheGame() {
		return waitForYourOpponentToConnectToTheGame;
	}

	public static Image getYouHaveNotMadeAnyChangesYouWantToGoBack() {
		return youHaveNotMadeAnyChangesYouWantToGoBack;
	}

	public static Image getBackgroundEditor() {
		return levelEditorPanelBackground;
	}

	public static Image getDoYouReallyWantToExit() {
		return doYouReallyWantToExit;
	}

	public static Image getYouMustAddBall() {
		return youMustAddBall;
	}

	public static Image getYouCanNotPlaceABallOnAnotherBall() {
		return youCanNotPlaceABallOnAnotherBall;
	}

	public static Image getDoYouReallyWantToRemoveThisBall() {
		return doYouReallyWantToRemoveThisBall;
	}

	public static Image getYouCanNotRemoveTheWhiteBall() {
		return youCanNotRemoveTheWhiteBall;
	}

	public static Image getYouCanNotRemoveTheBlackBall() {
		return youCanNotRemoveTheBlackBall;
	}

	public static Image getDoYouReallyWantToResetTheCommands() {
		return doYouReallyWantToResetTheCommands;
	}

	public static Image getDoYouWantToSaveChangesToTheCurrentGame() {
		return doYouWantToSaveChangesToTheCurrentGame;
	}

	public static Image getDoYouWantToSaveYourChangesSoPermanently() {
		return doYouWantToSaveYourChangesSoPermanently;
	}

	public static Image getDoYouLeaveTheMatch() {
		return doYouLeaveTheMatch;
	}

	public static Image getMusicOfRollovered() {
		return musicOffRollovered;
	}

	public static Image getOnTheTableThereMustBeAtLeastAYellowBall() {
		return onTheTableThereMustBeAtLeastAYellowBall;
	}

	public static Image getOnTheTableThereMustBeAtLeastARedBall() {
		return onTheTableThereMustBeAtLeastARedBall;
	}

	public static Image getYouHaveAddedTooManyBallsThanThoseSet() {
		return youHaveAddedTooManyBallsThanThoseSet;
	}

	public static Image getTheWhiteBallIsNotOnTheTable() {
		return theWhiteBallIsNotOnTheTable;
	}

	public static Image getTheBlackBallIsNotOnTheTable() {
		return theBlackBallIsNotOnTheTable;
	}

	public static Image getMaximumNumberOfBallReached() {
		return maximumNumberOfBallReached;
	}

	public static Image getThereAreNoSavedGame() {
		return thereAreNoSavedGame;
	}

	public static Image getThereAreNoSavedGameCreated() {
		return thereAreNoSavedGameCreated;
	}

	public static Image getYouCanNotDeleteIfYouDoNotHaveBallsOnTheTable() {
		return youCanNotDeleteIfYouDoNotHaveBallsOnTheTable;
	}

	public static Image getThirdBackground() {
		return chooseHowToPlayPanelBackground;
	}

	public static Image getFourthBackground() {
		return loadPanelBackground;
	}

	public static Image getYouCanNotAddAnotherBall() {
		return youCanNotAddAnotherBall;
	}

	public static Image getSectSettings() {
		return sectSettings;
	}

	public static Image getNumber(int index) {
		if (index <= numbers.size() && index >= 0)
			return numbers.get(index);
		else
			return null;
	}

	public static Image getImageSaveMatch(int index) {
		if (index <= imageSaveMatch.size() && index >= 0)
			return imageSaveMatch.get(index);
		else
			return null;
	}

	public static Image getPower(int index) {
		if (index <= power.size() && index >= 0)
			return power.get(index);
		else
			return null;
	}

	public static Image getImageSaveEditor(int index) {
		if (index <= imageSaveEditor.size() && index >= 0)
			return imageSaveEditor.get(index);
		else
			return null;
	}

	public static Image getLittleBackground(int index) {
		if (index <= littleBackground.size() && index >= 0)
			return littleBackground.get(index);
		else
			return null;
	}

	public static Image getCommandNotSet(int index) {

		return commandNotSet.get(index);
	}

	public static Image getDoYouWantToSaveBeforeExit() {
		return doYouWantToSaveBeforeExit;
	}

	public static Image getDoYouWantToResetTheSettings() {
		return doYouWantToResetTheSettings;
	}

	public static Image getDoYouReallyWantToDeleteEverythingAndRestartToReposion() {
		return doYouReallyWantToDeleteEveryThingAndRestartToReposion;
	}

	public static Image getBlackBall() {
		return blackBall;
	}

	public static Image getMevsMe() {
		return mevsMe;
	}

	public static Image getEasy() {
		return easy;
	}

	public static Image getMedium() {
		return medium;
	}

	public static Image getYes() {
		return yes;
	}

	public static Image getOk() {
		return ok;
	}

	public static Image getColorBall() {
		return colorBall;
	}

	public static Image getDifficult() {
		return difficult;
	}

	public static Image getNumberBall() {
		return numberBall;
	}

	public static Image getTimeMaxToCompleteLevel() {
		return timeMaxToCompleteLevel;
	}

	public static Image getNo() {
		return no;
	}

	public static Image getYesRollovered() {
		return yesRollovered;
	}

	public static Image getNoRollovered() {
		return noRollovered;
	}

	public static Image getOkRollovered() {
		return okRollovered;
	}

	public static Image getHard() {
		return hard;
	}

	public static Image getSaveRollovered() {
		return saveRollovered;
	}

	public static Image getWhiteBallButton() {
		return whiteBallButton;
	}

	public static Image getRedBallButton() {
		return redBallButton;
	}

	public static Image getBlackBallButton() {
		return blackBallButton;
	}

	public static Image getYellowBallButton() {
		return yellowBallButton;
	}

	public static Image getWhiteBallButtonRollovered() {
		return whiteBallButtonRollovered;
	}

	public static Image getRedBallButtonRollovered() {
		return redBallButtonRollovered;
	}

	public static Image getBlackBallButtonRollovered() {
		return blackBallButtonRollovered;
	}

	public static Image getYellowBallButtonRollovered() {
		return yellowBallButtonRollovered;
	}

	public static Image getBackgroundHowToPlay() {
		return resumeContinuePanelBackground;
	}

	public static Image getContainerBlackBall() {
		return containerBlackBall;
	}

	public static Image getHandPointer() {
		return handPointer;
	}

	public static Image getMevsMeRollovered() {
		return mevsMeRollovered;
	}

	public static Image getEasyRollovered() {
		return easyRollovered;
	}

	public static Image getMediumRollovered() {
		return mediumRollovered;
	}

	public static Image getOnLineRollovered() {
		return onLineRollovered;
	}

	public static Image getOffLineRollovered() {
		return offLineRollovered;
	}

	public static Image getHardRollovered() {
		return hardRollovered;
	}

	public static Image getBackgroundHowTo() {
		return resumeContinuePanelBackground;
	}

	public static Image getPowerIndicator() {
		return powerIndicator;
	}

	public static Image getRedBall() {
		return redBall;
	}

	public static Image getStick() {
		return stick;
	}

	public static Image getWhiteBall() {
		return whiteBall;
	}

	public static Image getOnLine() {
		return onLine;
	}

	public static Image getOffLine() {
		return offLine;
	}

	public static Image getBackgroundTwo() {
		return initialPanelBackground;
	}

	public static Image getYellowBall() {
		return yellowBall;
	}

	public static Image getNewMatch() {
		return newMatch;
	}

	public static Image getPoint() {
		return point;
	}

	public static Image getDeleteBall() {
		return deleteBall;
	}

	public static Image getDeleteBallRollovered() {
		return deleteBallRollovered;
	}

	public static Image getClearALl() {
		return clearAll;
	}

	public static Image getSave() {
		return save;
	}

	public static Image getSettingIcon() {
		return setting;
	}

	public static Image getDivietoPointer() {
		return errorPointer;
	}

	public static Image getEditor() {
		return createGame;
	}

	public static Image getLoadEditor() {
		return loadEditor;
	}

	public static Image getNewGameRollovered() {
		return newGameRollovered;
	}

	public static Image getNewGame() {
		return newGame;
	}

	public static Image getLoadGame() {
		return loadGame;
	}

	public static Image getLoadGameRollovered() {
		return loadGameRollevered;
	}

	public static Image getLoadMatch() {
		return loadMatch;
	}

	public static Image getLoadMatchRollover() {
		return loadMatchRollover;
	}

	public static Image getLoadLevel() {
		return loadLevel;
	}

	public static Image getClearAllRollovered() {
		return clearAllRollovered;
	}

	public static Image getLoadLevelRollover() {
		return loadLevelRollover;
	}

	public static Image getModify() {
		return modify;
	}

	public static Image getSettingRollovered() {
		return settingRollovered;
	}

	public static Image getPlay() {
		return play;
	}

	public static Image getInfo() {
		return info;
	}

	public static Image getDeleteButton() {
		return deleteButton;
	}

	public static Image getBack() {
		return back;
	}

	public static Image getBackRollovered() {
		return backRollovered;
	}

	public static Image getEditorRollover() {
		return createGameRollover;
	}

	public static void setEditorRollover(Image editorRollover) {
		ImageProvider.createGameRollover = editorRollover;
	}

	public static Image getFrecciaGiu() {
		return arrowUp;
	}

	public static Image getFrecciaSu() {
		return arrowDown;
	}

	public static Image getYouWin() {
		return youWin;
	}

	public static Image getYouLose() {
		return youLose;
	}

	public static Image getHitButton() {
		return hitButton;
	}

	public static void setHitButton(Image hitButton) {
		ImageProvider.hitButton = hitButton;
	}

	public static Image getHitButtonRollovered() {
		return hitButtonRollovered;
	}

	public static Image getPauseButton() {
		return pauseButton;
	}

	public static Image getPauseButtonRollovered() {
		return pauseButtonRollovered;
	}

	public static Image getRotateStickRightButton() {
		return rotateStickRightButton;
	}

	public static Image getRotateStickRightButtonRollovered() {
		return rotateStickRightButtonRollovered;
	}

	public static Image getRotateStickLeftButton() {
		return rotateStickLeftButton;
	}

	public static Image getRotateStickLeftButtonRollovered() {
		return rotateStickLeftButtonRollovered;
	}

	public static Image getIncrementIndicatorButton() {
		return incrementIndicatorButton;
	}

	public static Image getIncrementIndicatorButtonRollovered() {
		return incrementIndicatorButtonRollovered;
	}

	public static Image getName() {
		return name;
	}

	public static Image getServerPort() {
		return serverPort;
	}

	public static Image getServerIP() {
		return serverIP;
	}

	public static Image getDecrementIndicatorButton() {
		return decrementIndicatorButton;
	}

	public static Image getDecrementIndicatorButtonRollovered() {
		return decrementIndicatorButtonRollovered;
	}

	public static Image getSecondBackground() {
		return resumeContinuePanelBackground;
	}

	public static Vector<Image> getImageSaveMatch() {
		return imageSaveMatch;
	}

	public static Vector<Image> getImageSaveEditor() {
		return imageSaveEditor;
	}

	public static Vector<Image> getNumber() {
		return numbers;
	}

	public static Vector<Image> getLittleBackground() {
		return littleBackground;
	}

	public static Image getClearAll() {
		return clearAll;
	}

	public static Image getSaveEditor() {
		return save;
	}

	public static Image getSetting() {
		return setting;
	}

	public static Image getErrorPointer() {
		return errorPointer;
	}

	public static Image getSaveMadeCorrectly() {
		return saveMadeCorrectly;
	}

	public static Image getCreateGame() {
		return createGame;
	}

	public static Image getDoYouWantToSave() {
		return doYouWantToSave;
	}

	public static Image getDoYouWantToGoOutAndDisconnectFromTheGame() {
		return doYouWantToGoOutAndDisconnectFromTheGame;
	}

	public static Image getDoYouWantToOverwriteTheRescue() {
		return doYouWantToOverwriteTheRescue;
	}

	public static Image getDoYouReallyWantToDeleteEveryThingAndRestartToReposion() {
		return doYouReallyWantToDeleteEveryThingAndRestartToReposion;
	}

	public static Image getConnect() {
		return connect;
	}

	public static Image getConnectRollovered() {
		return connectRollovered;
	}

	public static Image getLoadGameRollevered() {
		return loadGameRollevered;
	}

	public static Image getCreateGameRollover() {
		return createGameRollover;
	}

	public static Map<Integer, Image> getTableImage() {
		return tableImage;
	}

	public static Map<Integer, Image> getWhiteBallFoulImage() {
		return whiteBallFoulImage;
	}

	public static Map<Integer, Image> getNoneBallTouchedFoulImage() {
		return noneBallTouchedFoulImage;
	}

	public static Map<Integer, Image> getFirstBallTouchedIsTheOppositeColorFoul() {
		return firstBallTouchedIsTheOppositeColorFoul;
	}

	public static Map<Integer, Image> getContainerYellowBall() {
		return containerYellowBall;
	}

	public static Map<Integer, Image> getContainerRedBall() {
		return containerRedBall;
	}

	public static Image getArrowUp() {
		return arrowUp;
	}

	public static Image getArrowDown() {
		return arrowDown;
	}

	public static Image getFirstBackground() {
		return initialPanelBackground;
	}

	public static Image getResumeButton() {
		return resumeButton;
	}

	public static Image getResumeButtonRollovered() {
		return resumeButtonRollovered;
	}

	public static Image getSettingsButton() {
		return settingsButton;
	}

	public static Image getSettingsButtonRolloveres() {
		return settingsButtonRollovered;
	}

	public static Image getExitButton() {
		return exitButton;
	}

	public static Image getExitButtonRolloverd() {
		return exitButtonRollovered;
	}

	public static Image getSettingCommandsButton() {
		return settingCommandsButton;
	}

	public static Image getSettingsButtonRollovered() {
		return settingsButtonRollovered;
	}

	public static Image getTurnLeft() {
		return turnLeft;
	}

	public static Image getTurnRight() {
		return turnRight;
	}

	public static Image getTurnLeftRollovered() {
		return turnLeftRollovered;
	}

	public static Image getTurnRightRollovered() {
		return turnRightRollovered;
	}

	public static Image getExitButtonRollovered() {
		return exitButtonRollovered;
	}

	public static Image getSettingCommandsButtonRollovered() {
		return settingCommandsButtonRollovered;
	}

	public static Map<String, Image> getKeyBoard() {
		return keyBoard;
	}

	private static void initializeMapKeyboard(int vk0) {

		Image tmp = null;

		try {
			tmp = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("images/keyboard/" + vk0 + ".png"))
					.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		} catch (IOException e) {

			e.printStackTrace();
		}

		keyBoard.put(vk0 + "", tmp);
	}

	public static Image getKeyPressed(String vko) {
		return keyBoard.get(vko);
	}

	public static Image getFirstPlayerTurn() {
		return firstPlayerTurn;
	}

	public static Image getSettingBackground() {
		return settingsPanelBackground;
	}

	public static Image getConfirmButton() {
		return confirmButton;
	}

	public static Image getImpossibleEstabilishedConnection() {
		return impossibleEstabilishedConnection;
	}

	public static Image getTransparency() {
		return backgroundForTransparency;
	}

	public static Image getConfirmButtonRollovered() {
		return confirmButtonRollovered;
	}

	public static Image getReset() {
		return reset;
	}

	public static Image getResetRollovered() {
		return resetRollovered;
	}

	public static Image getSaveChangesButton() {
		return saveChangesButton;
	}

	public static Image getSaveChangesButtonRollovered() {
		return saveChangesButtonRollovered;
	}

	public static Image getMusicOn() {
		return musicOn;
	}

	public static Image getMusicOf() {
		return musicOff;
	}

	public static Image getPauseMusic() {
		return pauseMusic;
	}

	public static Image getInsertYourName() {
		return insertYourName;
	}

	public static Image getPauseMusicRollovered() {
		return pauseMusicRollovered;
	}

	public static Image getStopMusic() {
		return stopMusic;
	}

	public static Image getStopMusicRollovered() {
		return stopMusicRollovered;
	}

	public static Image getPlayMusic() {
		return playMusic;
	}

	public static Image getPlayMusicRollovered() {
		return playMusicRollovered;
	}

	public static Image getPreviusMusic() {
		return previusMusic;
	}

	public static Image getPreviusMusicRollovered() {
		return previusMusicRollovered;
	}

	public static Image getNextMusic() {
		return nextMusic;
	}

	public static Image getNextMusicRollovered() {
		return nextMusicRollovered;
	}

	public static Image getMusicOnRollovered() {
		return musicOnRollovered;
	}

	public static Image getMusicOfRolloverd() {
		return musicOffRollovered;
	}

	public static Image getSecondPlayerTurn() {
		return secondPlayerTurn;
	}

	public static void refreshImageOfSavedMatch() {

		imageSaveMatch = new Vector<Image>();
		for (Integer k : FileProvider.getIndicesOfMatchRescue().keySet()) {
			Image tmp;
			try {
				tmp = ImageIO
						.read(Thread.currentThread().getContextClassLoader()
								.getResource("imagesSavedGameMatch/" + FileProvider.getIndicesOfMatchRescue().get(k)
										+ ".png"))
						.getScaledInstance(MatchManager.WIDTH, MatchManager.HEIGHT, Image.SCALE_SMOOTH);
				imageSaveMatch.addElement(tmp);

			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	}

	public static Image getDisconnected() {
		return disconnected;
	}

	public static Image getYouWinOnLineMode() {
		return youWinOnLineMode;
	}

	public static Image getYouLoseOnLineMode() {
		return youLoseOnLineMode;
	}

	public static Image getSelectAnotherName() {
		return selectAnotherName;
	}

	public static void refreshImageOfLevelCreated() {
		imageSaveEditor = new Vector<Image>();
		for (Integer k : FileProvider.getIndicesOfEditorRescue().keySet()) {
			Image tmp;
			try {
				tmp = ImageIO
						.read(Thread.currentThread().getContextClassLoader()
								.getResource("imagesSavedGameEditor/" + FileProvider.getIndicesOfEditorRescue().get(k)
										+ ".png"))
						.getScaledInstance(MatchManager.WIDTH, MatchManager.HEIGHT, Image.SCALE_SMOOTH);
				imageSaveEditor.addElement(tmp);
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

	}

	public static Map<Integer, Image> getOpponentWhiteBallFoulImage() {
		return opponentWhiteBallFoulImage;
	}

	public static Map<Integer, Image> getOpponentnoneBallTouchedFoulImage() {
		return opponentnoneBallTouchedFoulImage;
	}

	public static Map<Integer, Image> getOpponentfirstBallTouchedIsTheOppositeColorFoul() {
		return opponentfirstBallTouchedIsTheOppositeColorFoul;
	}

	public static Image getBackgroundGamePanel() {
		return backgroundGamePanel;
	}

	public static Image getCreateTable() {
		return createTable;
	}

	public static Image getChatIcon() {
		return chatIcon;
	}

	public static Image getJoinATable() {
		return joinATable;
	}

	public static Image getJoinATableRolloverd() {
		return joinATableRolloverd;
	}

	public static void setJoinATableRolloverd(Image joinATableRolloverd) {
		ImageProvider.joinATableRolloverd = joinATableRolloverd;
	}

	public static Image getSendMessage() {
		return sendMessage;
	}

	public static void setSendMessage(Image sendMessage) {
		ImageProvider.sendMessage = sendMessage;
	}

	public static Image getSendMessageRollovered() {
		return sendMessageRollovered;
	}

	public static void setSendMessageRollovered(Image sendMessageRollovered) {
		ImageProvider.sendMessageRollovered = sendMessageRollovered;
	}

	public static Image getClearChat() {
		return clearChat;
	}

	public static void setClearChat(Image clearChat) {
		ImageProvider.clearChat = clearChat;
	}

	public static Image getClearChatRollovered() {
		return clearChatRollovered;
	}

	public static void setClearChatRollovered(Image clearChatRollovered) {
		ImageProvider.clearChatRollovered = clearChatRollovered;
	}

	public static Image getBackgroundChat() {
		return backgroundChat;
	}

	public static Image getBackgroundList() {
		return backgroundList;
	}

	public static void setBackgroundList(Image backgroundList) {
		ImageProvider.backgroundList = backgroundList;
	}

	public static Image getThereAreNoTableCreated() {
		return thereAreNoTableCreated;
	}

}
