package igpe.billiard.core;

import igpe.billiard.file.FileProvider;
import igpe.billiard.sound.SoundProvider;
import igpe.billiard.sound.SoundsType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class MatchManager {
	public static final int HEIGHT = 800;
	public static final int WIDTH = 1200;
	public static final int MAX_SIZE_SET_OF_BALL_PLAYER = 7;
	public static final int MAX_WAIT_TIME_TO_PLAY_THE_TURN = 30;
	private static final int MAX_TIME_TO_SHOOT_FOR_HARD_ARTIFICIAL_INTELLIGENCE = 20;

	private Player firstPlayer = new Player("Francesco", true);
	private Player secondPlayer = new Player("Pasquale", false);
	private PhysicsBallMovement physicsBallMovement = new PhysicsBallMovement();
	private ArtificialIntelligence artificialIntelligence = new ArtificialIntelligence();
	private Table table = new Table(200, 200);
	private Stick stick = new Stick(0, 0, Direction.STOP);
	private PowerIndicator powerIndicator = new PowerIndicator(1100, 200, DirectionOfPower.NOTHING);
	private WhiteBall whiteBall;
	private Ball firstBall;
	private Ball secondBall;
	private Ball thirdBall;
	private Ball fourthBall;
	private Ball fifthBall;
	private Ball sixthBall;
	private Ball seventhBall;
	private Ball eightBall;
	private Ball ninthBall;
	private Ball tenthBall;
	private Ball eleventhBall;
	private Ball twelvethBall;
	private Ball thirteenthBall;
	private Ball fourtheenthBall;
	private Ball fifthteenthBall;
	private List<Ball> ballsOnTheTable;
	private List<Ball> ballsCollided;
	private List<Ball> ballsPocketed;
	private List<Ball> redBalls;
	private List<Ball> yellowBalls;
	private List<SoundsType> sounds;
	private boolean gameIsInPause = false;
	private boolean thinking = false;
	private boolean firstStepOfTheGame = true;
	private boolean repositionWhiteBallWithTheMouse = false;
	private boolean repositionWhiteBall = false;
	private boolean secondStepOfTheGame = false;
	private boolean thirdStepOfTheGame = false;
	private boolean setEasyArtificialIntelligenceParameters = false;
	private boolean iAmPlayingWithArtificialIntelligence = false;
	private boolean iAmPlayingWithEasyArtificialIntelligence = false;
	private boolean iAmPlayingWithMediumArtificialIntelligence = false;
	private boolean iAmPlayingWithHardArtificialIntelligence = false;
	private boolean iAmPlayingMeVsMe = false;
	private boolean iAmPlayingWithACreatedLevel;
	private boolean foulWhiteBallPocketed = false;
	private boolean foulBallHitTheOppositeColor = false;
	private boolean foulNoneBallTouched = false;
	private boolean endOfTheGame = false;
	private boolean disconnected = false;
	private boolean interrupted = false;
	private int maxWaitTimeForFoul = 5;
	private int maxSizeSetOfYellowBallForLevelEdited;
	private int maxSizeOfRedBallForLevelEdited;
	private int autoEasyShootArtificialIntelligence = 0;
	private int actualCounterForRepositionWhiteBall = 0;
	private int lastCounterForRepositionWhiteBall = 0;
	private int indexTable = 0;
	private int xMouse;
	private int yMouse;
	private int counter = 0;
	private double autoEasyShootPower;
	private Direction automaticStickDirection;
	private Direction automaticWhiteBallDirection;
	private Chronometer chronometerOfTheTurn = new Chronometer();
	private Chronometer chronometerOfTheMatch = new Chronometer();
	private Chronometer chronometerForTheWinOrLose = new Chronometer();
	private Chronometer chronometerFoul = new Chronometer();
	private Modality modalityOfTheGame = Modality.OFFLINE;
	private StringBuilder currentStatusTheGame;
	private String disconnectedClientName;

	public static int getHeight() {
		return HEIGHT;
	}

	public static int getWidth() {
		return WIDTH;
	}

	public MatchManager(final Modality modalityOfTheGame) {
		this.modalityOfTheGame = modalityOfTheGame;
		initializeObject();
		if (modalityOfTheGame == Modality.ONLINE)
			initializeBall();
	}

	private void initializeObject() {
		automaticWhiteBallDirection = Direction.STOP;
		automaticStickDirection = Direction.STOP;
		sounds = new CopyOnWriteArrayList<SoundsType>();
		indexTable = FileProvider.getCommand(NameofCommand.INDEX_TABLE_SELECTED);

		if (iAmPlayingWithArtificialIntelligence)
			artificialIntelligence.start(new Thread());

		yellowBalls = new CopyOnWriteArrayList<Ball>();
		redBalls = new CopyOnWriteArrayList<Ball>();
		ballsPocketed = new ArrayList<Ball>();
		ballsCollided = new ArrayList<Ball>();
		ballsOnTheTable = new CopyOnWriteArrayList<Ball>();
		currentStatusTheGame = new StringBuilder();
		chronometerOfTheMatch.start();
		chronometerOfTheTurn.start();
		currentStatusTheGame = new StringBuilder("");
	}

	public void initializeBall() {
		whiteBall = new WhiteBall(400, 400, 0);
		firstBall = new Ball(766, 400, 1);
		secondBall = new Ball(828, 435, 2);
		thirdBall = new Ball(828, 368, 3);
		fourthBall = new Ball(850, 400, 4);
		fifthBall = new Ball(807, 422, 5);
		sixthBall = new Ball(786, 389, 6);
		seventhBall = new Ball(850, 356, 7);
		eightBall = new Ball(807, 400, 8);
		ninthBall = new Ball(850, 422, 9);
		tenthBall = new Ball(786, 411, 10);
		eleventhBall = new Ball(850, 378, 11);
		twelvethBall = new Ball(828, 413, 12);
		thirteenthBall = new Ball(828, 390, 13);
		fourtheenthBall = new Ball(807, 378, 14);
		fifthteenthBall = new Ball(850, 444, 15);
		ballsOnTheTable.add(whiteBall);
		ballsOnTheTable.add(firstBall);
		ballsOnTheTable.add(secondBall);
		ballsOnTheTable.add(thirdBall);
		ballsOnTheTable.add(fourthBall);
		ballsOnTheTable.add(fifthBall);
		ballsOnTheTable.add(sixthBall);
		ballsOnTheTable.add(seventhBall);
		ballsOnTheTable.add(eightBall);
		ballsOnTheTable.add(ninthBall);
		ballsOnTheTable.add(tenthBall);
		ballsOnTheTable.add(eleventhBall);
		ballsOnTheTable.add(twelvethBall);
		ballsOnTheTable.add(thirteenthBall);
		ballsOnTheTable.add(fourtheenthBall);
		ballsOnTheTable.add(fifthteenthBall);
	}

	public List<Ball> getBallsOnTheTable() {
		return ballsOnTheTable;
	}

	public List<Ball> getRedBalls() {
		return redBalls;
	}

	public List<Ball> getYellowBalls() {
		return yellowBalls;
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}

	public PhysicsBallMovement getPhysicsBallMovement() {
		return physicsBallMovement;
	}

	public PowerIndicator getPowerIndicator() {
		return powerIndicator;
	}

	public Stick getStick() {
		return stick;
	}

	public Table getTable() {
		return table;
	}

	public WhiteBall getWhiteball() {
		return whiteBall;
	}

	public boolean getWhiteBallReposition() {
		return repositionWhiteBall;
	}

	public boolean getIAmPlayingMevsMe() {
		return iAmPlayingMeVsMe;
	}

	public boolean getIAmPlayingWithArtificialIntelligence() {
		return iAmPlayingWithArtificialIntelligence;
	}

	public boolean getFoulWhiteBallPocketed() {
		return foulWhiteBallPocketed;
	}

	public boolean getFoulBallHitTheOppositeColor() {
		return foulBallHitTheOppositeColor;
	}

	public boolean getFoulNoneBallsTouched() {
		return foulNoneBallTouched;
	}

	public boolean getIsThirdStepOfTheGame() {
		return thirdStepOfTheGame;
	}

	public Chronometer getChronometerForTheFoul() {
		return chronometerFoul;
	}

	public Chronometer getChronometerForTheWinOrLose() {
		return chronometerFoul;
	}

	public Chronometer getChronometerOfTheTurn() {
		return chronometerOfTheTurn;
	}

	public Chronometer getChronometerOfTheMatch() {
		return chronometerOfTheMatch;
	}

	public String getCurrentStatusOfTheGame() {
		return currentStatusTheGame.toString();
	}

	public boolean getInterrupted() {
		return interrupted;
	}

	public boolean getIsEndOfGame() {
		return endOfTheGame;
	}

	public void setXMouse(int xMouse) {
		this.xMouse = xMouse;
	}

	public void setYMouse(int yMouse) {
		this.yMouse = yMouse;
	}

	public void setEndOfGame(final boolean endOfGame) {
		this.endOfTheGame = endOfGame;
		artificialIntelligence.setEndOFLife(endOfGame);

		if (modalityOfTheGame == Modality.ONLINE)
			Thread.currentThread().interrupt();
	}

	public void setWhiteBallReposition() {
		if (!physicsBallMovement.checkThatThereAreNoOverlappingBalls(ballsOnTheTable))
			this.repositionWhiteBall = false;
		else
			this.repositionWhiteBall = true;
	}

	public void setPause(final boolean gameIsInPause) {
		this.gameIsInPause = gameIsInPause;
		if (this.gameIsInPause) {
			chronometerOfTheTurn.stop();
			chronometerOfTheMatch.stop();
		} else {
			chronometerOfTheTurn.start();
			chronometerOfTheMatch.start();
		}
	}

	public void setRepositionWithMouse(final boolean repositionWhiteBallWithTheMouse) {
		this.repositionWhiteBallWithTheMouse = repositionWhiteBallWithTheMouse;
	}

	public void setHowToPlay(final HowToPlay howToPlay, final int indexOfRescue) {
		switch (howToPlay) {
		case MEVSME:
			this.iAmPlayingMeVsMe = true;
			initializeBall();
			secondPlayer.setName(firstPlayer.getName());
			break;
		case EASY:
			initializeBall();
			this.iAmPlayingWithArtificialIntelligence = true;
			this.iAmPlayingMeVsMe = false;
			this.iAmPlayingWithHardArtificialIntelligence = false;
			this.iAmPlayingWithMediumArtificialIntelligence = false;
			this.iAmPlayingWithEasyArtificialIntelligence = true;
			secondPlayer.setName("Easy");
			break;
		case MEDIUM:
			initializeBall();
			this.iAmPlayingWithArtificialIntelligence = true;
			this.iAmPlayingMeVsMe = false;
			this.iAmPlayingWithEasyArtificialIntelligence = false;
			this.iAmPlayingWithHardArtificialIntelligence = false;
			this.iAmPlayingWithMediumArtificialIntelligence = true;
			secondPlayer.setName("Medium");
			break;
		case HARD:
			initializeBall();
			this.iAmPlayingWithArtificialIntelligence = true;
			this.iAmPlayingMeVsMe = false;
			this.iAmPlayingWithMediumArtificialIntelligence = false;
			this.iAmPlayingWithEasyArtificialIntelligence = false;
			this.iAmPlayingWithHardArtificialIntelligence = true;
			secondPlayer.setName("Hard");
			break;
		case LOAD:
			loadRescueFromSavedGame(indexOfRescue);
			break;
		case EDITOR:
			this.iAmPlayingWithACreatedLevel = true;
			loadRescueFromCreatedLevel(indexOfRescue);
			break;
		default:
			break;
		}
	}

	public void loadRescueFromSavedGame(final int indexOfRescue) {
		currentStatusTheGame = new StringBuilder("");
		currentStatusTheGame.append(FileProvider.getRescueContent(indexOfRescue, GameMode.MATCH));
		resumeAllObjectTheGame();
	}

	public void loadRescueFromCreatedLevel(final int rescue) {
		currentStatusTheGame = new StringBuilder("");
		currentStatusTheGame.append(FileProvider.getRescueContent(rescue, GameMode.EDITOR));
		resumeAllObjectTheGameFromCreatedLevel();
	}

	public void parseStatusFromString(final String currentStatus) {
		if (!currentStatus.contains("#WIN") && !currentStatus.contains("#ConnectionClosed")) {
			this.currentStatusTheGame.delete(0, currentStatusTheGame.length());
			this.currentStatusTheGame.append(currentStatus);
			resumeAllObjectTheGame();
		}
	}

	private void resumeAllObjectTheGame() {
		counter = 0;
		String[] result = currentStatusTheGame.toString().split(";");

		chronometerFoul.resume(Long.parseLong(result[counter]));
		if (modalityOfTheGame == Modality.ONLINE) {
			int second = Integer.parseInt(result[counter]);
			checkIfIsPossibleResetChronometerFoul(second);
		}

		counter++;
		resumeBallsOnTheTable(result);
		resumeBallCollided(result);
		resumeBallPocketed(result);
		foulWhiteBallPocketed = Boolean.parseBoolean(result[counter++]);
		foulBallHitTheOppositeColor = Boolean.parseBoolean(result[counter++]);
		foulNoneBallTouched = Boolean.parseBoolean(result[counter++]);
		repositionWhiteBall = Boolean.parseBoolean(result[counter++]);
		firstStepOfTheGame = Boolean.parseBoolean(result[counter++]);
		secondStepOfTheGame = Boolean.parseBoolean(result[counter++]);
		thirdStepOfTheGame = Boolean.parseBoolean(result[counter++]);
		thinking = Boolean.parseBoolean(result[counter++]);
		firstPlayer.setName(new String(result[counter++]));
		secondPlayer.setName(new String(result[counter++]));
		firstPlayer.setTurn(Boolean.parseBoolean(result[counter++]));
		secondPlayer.setTurn(Boolean.parseBoolean(result[counter++]));
		firstPlayer.setWin(Boolean.parseBoolean(result[counter++]));
		secondPlayer.setWin(Boolean.parseBoolean(result[counter++]));
		firstPlayer.setMakeFoul(Boolean.parseBoolean(result[counter++]));
		secondPlayer.setMakeFoul(Boolean.parseBoolean(result[counter++]));
		setEasyArtificialIntelligenceParameters = Boolean.parseBoolean(result[counter++]);
		iAmPlayingWithArtificialIntelligence = Boolean.parseBoolean(result[counter++]);
		iAmPlayingWithEasyArtificialIntelligence = Boolean.parseBoolean(result[counter++]);
		iAmPlayingWithMediumArtificialIntelligence = Boolean.parseBoolean(result[counter++]);
		iAmPlayingWithHardArtificialIntelligence = Boolean.parseBoolean(result[counter++]);
		iAmPlayingMeVsMe = Boolean.parseBoolean(result[counter++]);
		int colorBall = Integer.parseInt(result[counter++]);
		firstPlayer.setColorBall(resumeColorBall(colorBall));
		colorBall = Integer.parseInt(result[counter++]);
		secondPlayer.setColorBall(resumeColorBall(colorBall));
		autoEasyShootArtificialIntelligence = Integer.parseInt(result[counter++]);
		actualCounterForRepositionWhiteBall = Integer.parseInt(result[counter++]);
		lastCounterForRepositionWhiteBall = Integer.parseInt(result[counter++]);
		chronometerOfTheTurn.resume(Long.parseLong(result[counter++]));
		chronometerOfTheMatch.resume(Long.parseLong(result[counter++]));
		stick.setX(Integer.parseInt(result[counter++]));
		stick.setY(Integer.parseInt(result[counter++]));
		stick.setAngleOfRotation(Double.parseDouble(result[counter++]));
		stick.setHit(Boolean.parseBoolean(result[counter++]));
		autoEasyShootPower = Double.parseDouble(result[counter++]);

		powerIndicator.setPower(Double.parseDouble(result[counter++]));
		counter = 0;
	}

	public void resumeAllObjectTheGameFromCreatedLevel() {
		counter = 0;
		String result[] = currentStatusTheGame.toString().split(";");
		resumeBallsOnTheTable(result);
		firstStepOfTheGame = false;
		secondStepOfTheGame = false;
		thirdStepOfTheGame = true;
		thinking = false;
		final int difficult = Integer.parseInt(result[counter++]);
		if (difficult == 3) {
			iAmPlayingWithArtificialIntelligence = true;
			iAmPlayingWithHardArtificialIntelligence = true;
			secondPlayer.setName("Hard");
		} else if (difficult == 2) {
			iAmPlayingWithArtificialIntelligence = true;
			iAmPlayingWithMediumArtificialIntelligence = true;
			secondPlayer.setName("Medium");
		} else if (difficult == 1) {
			iAmPlayingWithArtificialIntelligence = true;
			iAmPlayingWithEasyArtificialIntelligence = true;
			secondPlayer.setName("Easy");
		} else if (difficult == 0) {
			iAmPlayingWithArtificialIntelligence = true;
			iAmPlayingMeVsMe = true;
			secondPlayer.setName("MevsMe");
		}

		secondPlayer.setColorBall(resumeColorBall(Integer.parseInt(result[counter++])));
		firstPlayer.setColorBall(resumeColorBall(Integer.parseInt(result[counter++])));
	}

	private Ball resumeSingleBall(String[] result) {
		Ball ball = new Ball(0, 0, 0);
		ball.setIdentificativeNumber(Integer.parseInt(result[counter++]));
		ball.setX(Integer.parseInt(result[counter++]));// x
		ball.setY(Integer.parseInt(result[counter++]));// Y
		ball.setCx(Float.parseFloat(result[counter++]));// Cx
		ball.setCy(Float.parseFloat(result[counter++]));// Cy
		ball.setVelocityXAxis(Float.parseFloat(result[counter++]));// Vx
		ball.setVelocityYAxis(Float.parseFloat(result[counter++]));// Vy
		ball.setIsPocketed(false);
		return ball;
	}

	private void resumeBallsOnTheTable(final String[] result) {
		final int countYellowBalls = Integer.parseInt(result[counter++]);
		final int countRedBalls = Integer.parseInt(result[counter++]);
		final int sizeListOfBallsOnTheTable = Integer.parseInt(result[counter++]);
		indexTable = Integer.parseInt(result[counter++]);
		FileProvider.setOnlyCommand(NameofCommand.INDEX_TABLE_SELECTED, indexTable);
		for (int index = 0; index < sizeListOfBallsOnTheTable; index++) {
			Ball ball = new Ball(resumeSingleBall(result));
			if (ball.getIdentificativeNumber() == 0) {
				whiteBall = new WhiteBall(ball);
				ballsOnTheTable.add(whiteBall);
			} else {
				ballsOnTheTable.add(ball);
			}
		}
		// 7-countYellowBall+1 <=7 = countYellowBall-1>=0 ;)
		for (int index = countYellowBalls - 1; index >= 0; index--)
			yellowBalls.add(new Ball(0, 0, index));

		for (int index = countRedBalls - 1; index >= 0; index--)
			redBalls.add(new Ball(0, 0, index));
	}

	private void resumeBallCollided(final String[] result) {
		final int countBallCollided = Integer.parseInt(result[counter++]);
		if (countBallCollided != 0) {
			for (int index = 0; index < countBallCollided; index++) {
				Ball ball = new Ball(resumeSingleBall(result));
				ballsCollided.add(ball);
			}
		}
	}

	private void resumeBallPocketed(final String[] result) {
		final int countBallPocketed = Integer.parseInt(result[counter++]);
		if (countBallPocketed != 0) {
			for (int index = 0; index < countBallPocketed; index++) {
				Ball ball = new Ball(resumeSingleBall(result));
				ballsPocketed.add(ball);
			}
		}
	}

	private ColorBall resumeColorBall(final int enumColorBall) {
		if (enumColorBall == 1) {
			return ColorBall.RED;
		} else if (enumColorBall == 2) {
			return ColorBall.YELLOW;
		} else
			return ColorBall.NOTHING;
	}

	public void updateCurrentStatusTheGame() {
		currentStatusTheGame = new StringBuilder("");
		if (modalityOfTheGame == Modality.OFFLINE || modalityOfTheGame == Modality.ONLINE && !disconnected) {
			currentStatusTheGame.append(chronometerFoul.getElapsedTime() + ";");
			checkIfIsPossibleResetChronometerFoul((int) chronometerFoul.getElapsedTime());
			currentStatusTheGame.append(yellowBalls.size() + ";");
			currentStatusTheGame.append(redBalls.size() + ";");
			currentStatusTheGame.append(ballsOnTheTable.size() + ";");
			currentStatusTheGame.append(FileProvider.getCommand(NameofCommand.INDEX_TABLE_SELECTED) + ";");
			for (Ball b : ballsOnTheTable)
				saveCurrentStatusTheBall(b);

			currentStatusTheGame.append(ballsCollided.size() + ";");
			if (!ballsCollided.isEmpty())
				for (Ball b : ballsCollided)
					saveCurrentStatusTheBall(b);

			currentStatusTheGame.append(ballsPocketed.size() + ";");
			if (!ballsPocketed.isEmpty())
				for (Ball b : ballsPocketed)
					saveCurrentStatusTheBall(b);

			currentStatusTheGame.append(foulWhiteBallPocketed + ";");
			currentStatusTheGame.append(foulBallHitTheOppositeColor + ";");
			currentStatusTheGame.append(foulNoneBallTouched + ";");
			currentStatusTheGame.append(repositionWhiteBall + ";");
			currentStatusTheGame.append(firstStepOfTheGame + ";");
			currentStatusTheGame.append(secondStepOfTheGame + ";");
			currentStatusTheGame.append(thirdStepOfTheGame + ";");
			currentStatusTheGame.append(thinking + ";");
			currentStatusTheGame.append(firstPlayer.getName() + ";");
			currentStatusTheGame.append(secondPlayer.getName() + ";");
			currentStatusTheGame.append(firstPlayer.isTurn() + ";");
			currentStatusTheGame.append(secondPlayer.isTurn() + ";");
			currentStatusTheGame.append(firstPlayer.isWin() + ";");
			currentStatusTheGame.append(secondPlayer.isWin() + ";");
			currentStatusTheGame.append(firstPlayer.isMakeFoul() + ";");
			currentStatusTheGame.append(secondPlayer.isMakeFoul() + ";");
			currentStatusTheGame.append(setEasyArtificialIntelligenceParameters + ";");
			currentStatusTheGame.append(iAmPlayingWithArtificialIntelligence + ";");
			currentStatusTheGame.append(iAmPlayingWithEasyArtificialIntelligence + ";");
			currentStatusTheGame.append(iAmPlayingWithMediumArtificialIntelligence + ";");
			currentStatusTheGame.append(iAmPlayingWithHardArtificialIntelligence + ";");
			currentStatusTheGame.append(iAmPlayingMeVsMe + ";");

			if (firstPlayer.getColorBall().equals(ColorBall.RED))
				currentStatusTheGame.append("1" + ";");
			else if (firstPlayer.getColorBall().equals(ColorBall.YELLOW))
				currentStatusTheGame.append("2" + ";");
			else
				currentStatusTheGame.append("3" + ";");

			if (secondPlayer.getColorBall().equals(ColorBall.RED))
				currentStatusTheGame.append("1" + ";");
			else if (secondPlayer.getColorBall().equals(ColorBall.YELLOW))
				currentStatusTheGame.append("2" + ";");
			else
				currentStatusTheGame.append("3" + ";");

			currentStatusTheGame.append(autoEasyShootArtificialIntelligence + ";");
			currentStatusTheGame.append(actualCounterForRepositionWhiteBall + ";");
			currentStatusTheGame.append(lastCounterForRepositionWhiteBall + ";");
			currentStatusTheGame.append(chronometerOfTheTurn.getElapsedTime() + ";");
			currentStatusTheGame.append(chronometerOfTheMatch.getElapsedTime() + ";");
			currentStatusTheGame.append(stick.getX() + ";");
			currentStatusTheGame.append(stick.getY() + ";");
			currentStatusTheGame.append(stick.getAngleOfRotation() + ";");
			currentStatusTheGame.append(stick.getIsHit() + ";");
			currentStatusTheGame.append(autoEasyShootPower + ";");

			currentStatusTheGame.append(powerIndicator.getPower() + ";");
		}

		else if (modalityOfTheGame == Modality.ONLINE && disconnected)
			currentStatusTheGame.append(disconnectedClientName + ":" + "#ConnectionClosed");

		if (modalityOfTheGame == Modality.ONLINE && (firstPlayer.isWin() || secondPlayer.isWin()) && !disconnected) {
			currentStatusTheGame = new StringBuilder("");
			if (firstPlayer.isWin())
				currentStatusTheGame.append(firstPlayer.getName() + ":" + "#WIN");
			else
				currentStatusTheGame.append(secondPlayer.getName() + ":" + "#WIN");
		}
	}

	private void saveCurrentStatusTheBall(final Ball ball) {
		currentStatusTheGame
				.append(ball.getIdentificativeNumber() + ";" + ball.getX() + ";" + ball.getY() + ";" + ball.getCx()
						+ ";" + ball.getCy() + ";" + ball.getVelocityXAxis() + ";" + ball.getVelocityYAxis() + ";");
	}

	private void callStep() {
		if (firstStepOfTheGame)
			firstStep();
		if (secondStepOfTheGame)
			secondStep();
		if (thirdStepOfTheGame)
			thirdStep();
	}

	private void firstStep() {
		if (chronometerOfTheTurn.getElapsedTime() == 0)
			chronometerOfTheTurn.start();
		checkWhichPlayerGame();
		if (physicsBallMovement.everyBallsStopped(ballsOnTheTable) && thinking) {
			if (whiteBall.getIsPocketed()) {
				changeTurnWhenWhiteBallIsPocketed();
			} else if (ballsPocketed.isEmpty() && !ballsCollided.isEmpty()) {
				changeTurn();
			} else if (ballsCollided.isEmpty() && !repositionWhiteBall) {
				changeTurnWhenIsTouchedNobodyBalls();
			} else if (!ballsPocketed.isEmpty()) {
				setColorOfAPlayer();
			}
			if (!thirdStepOfTheGame) {
				firstStepOfTheGame = false;
				secondStepOfTheGame = true;
			}
		}
	}

	private void secondStep() {
		if (chronometerOfTheTurn.getElapsedTime() == 0)
			chronometerOfTheTurn.start();
		checkWhichPlayerGame();

		if (physicsBallMovement.everyBallsStopped(ballsOnTheTable) && thinking) {
			if (whiteBall.getIsPocketed()) {
				changeTurnWhenWhiteBallIsPocketed();
			} else if (ballsPocketed.isEmpty() && !ballsCollided.isEmpty()) {
				changeTurn();
			} else if (ballsCollided.isEmpty()) {
				changeTurnWhenIsTouchedNobodyBalls();
			} else if (!ballsPocketed.isEmpty()) {
				setColorOfAPlayer();
			}
		}
	}

	private void thirdStep() {
		if (chronometerOfTheTurn.getElapsedTime() == 0)
			chronometerOfTheTurn.start();
		checkWhichPlayerGame();
		if (thinking)
			chronometerOfTheTurn.reset();
		if (physicsBallMovement.everyBallsStopped(ballsOnTheTable) && thinking) {
			thinking = false;
			chronometerOfTheTurn.reset();
			if (checkThatTheFirstBallTouchedIsNotOfAOppositePlayerColor()) {
				changeTurn();
				foulBallHitTheOppositeColor = true;
				if (firstPlayer.isTurn())
					firstPlayer.setMakeFoul(true);
				else
					secondPlayer.setMakeFoul(true);
				chronometerFoul.start();
				repositionWhiteBall = true;
			} else if (whiteBall.getIsPocketed()) {
				changeTurnWhenWhiteBallIsPocketed();
			} else if (ballsCollided.isEmpty()) {
				changeTurnWhenIsTouchedNobodyBalls();
			} else if (ballsPocketed.isEmpty())
				changeTurn();
			else if (!ballsPocketed.isEmpty()) {
				boolean flag = checkIfIsPocketedABallOfMyColor();
				ballsPocketed.clear();
				if (!flag)
					changeTurn();
			}
		}
	}

	public void changeTurn() {
		if (firstPlayer.isTurn()) {
			firstPlayer.setTurn(false);
			secondPlayer.setTurn(true);
			if (iAmPlayingWithEasyArtificialIntelligence)
				setEasyArtificialIntelligenceParameters = true;
		} else {
			firstPlayer.setTurn(true);
			secondPlayer.setTurn(false);

			if (iAmPlayingWithEasyArtificialIntelligence)
				setEasyArtificialIntelligenceParameters = false;
		}
		thinking = false;
		ballsCollided.clear();
		ballsPocketed.clear();
		chronometerOfTheTurn.reset();
		stick.setHit(false);
		stick.setStickDirection(Direction.STOP);
		powerIndicator.setPower(0.5);
	}

	private void changeTurnOutOfTime() {
		repositionWhiteBall = true;
		changeTurn();
		chronometerOfTheTurn.reset();
	}

	private void changeTurnWhenWhiteBallIsPocketed() {
		changeTurn();
		foulWhiteBallPocketed = true;
		chronometerFoul.start();
		whiteBall.setIsPocketed(false);
		resetWhiteBallPosition();
		repositionWhiteBall = true;
		if (firstPlayer.isTurn())
			firstPlayer.setMakeFoul(true);
		else
			secondPlayer.setMakeFoul(true);
	}

	private void changeTurnWhenIsTouchedNobodyBalls() {
		changeTurn();
		foulNoneBallTouched = true;
		chronometerFoul.start();
		ballsCollided.clear();
		repositionWhiteBall = true;
		if (firstPlayer.isTurn())
			firstPlayer.setMakeFoul(true);
		else
			secondPlayer.setMakeFoul(true);
	}

	private boolean checkThatTheFirstBallTouchedIsNotOfAOppositePlayerColor() {
		if (!ballsCollided.isEmpty()) {
			Ball ball = ballsCollided.get(0);
			if (ball.getIdentificativeNumber() <= 7
					&& (firstPlayer.getColorBall() == ColorBall.RED && firstPlayer.isTurn()
							|| secondPlayer.getColorBall() == ColorBall.RED && secondPlayer.isTurn())) {
				return true;
			}
			if (ball.getIdentificativeNumber() >= 9
					&& (firstPlayer.getColorBall() == ColorBall.YELLOW && firstPlayer.isTurn()
							|| secondPlayer.getColorBall() == ColorBall.YELLOW && secondPlayer.isTurn())) {
				return true;
			}
		}
		return false;
	}

	private void checkIfIsPossibleResetChronometerFoul(final int second) {
		if (second >= maxWaitTimeForFoul) {
			chronometerFoul.reset();
			foulBallHitTheOppositeColor = false;
			foulNoneBallTouched = false;
			foulWhiteBallPocketed = false;
			if (firstPlayer.isTurn())
				firstPlayer.setMakeFoul(false);
			else
				secondPlayer.setMakeFoul(false);
		}
	}

	private boolean checkIfIsPocketedABallOfMyColor() {
		for (Ball ball : ballsPocketed) {
			if ((firstPlayer.getColorBall() == ColorBall.YELLOW && firstPlayer.isTurn())
					|| (secondPlayer.getColorBall() == ColorBall.YELLOW && secondPlayer.isTurn())
							&& ball.getIdentificativeNumber() <= 7)
				return true;
			if ((firstPlayer.getColorBall() == ColorBall.RED && firstPlayer.isTurn())
					|| (secondPlayer.getColorBall() == ColorBall.RED && secondPlayer.isTurn())
							&& ball.getIdentificativeNumber() >= 9)
				return true;
		}
		return false;
	}

	private void checkWhichPlayerGame() {
		if (modalityOfTheGame == Modality.ONLINE) {
			stickUpdateAndSetWhiteBallVelocity();
			if (chronometerOfTheTurn.getElapsedTime() >= MAX_WAIT_TIME_TO_PLAY_THE_TURN)
				changeTurnOutOfTime();
		} else {
			if (firstPlayer.isTurn() && !thinking) {
				stickUpdateAndSetWhiteBallVelocity();
				if (chronometerOfTheTurn.getElapsedTime() >= MAX_WAIT_TIME_TO_PLAY_THE_TURN)
					changeTurnOutOfTime();
			}
			if (secondPlayer.isTurn() && !thinking) {
				if (iAmPlayingMeVsMe)
					stickUpdateAndSetWhiteBallVelocity();
				else if (iAmPlayingWithEasyArtificialIntelligence) {
					if (setEasyArtificialIntelligenceParameters) {
						setEasyArtificialIntelligenceStickDirectionAndSecondOfShoot();
					}
					easyArtificialIntelligence();
				} else if (iAmPlayingWithMediumArtificialIntelligence)
					mediumArtificialIntelligence();
				else if (iAmPlayingWithHardArtificialIntelligence) {
					if (chronometerOfTheTurn.getElapsedTime() <= MAX_TIME_TO_SHOOT_FOR_HARD_ARTIFICIAL_INTELLIGENCE)
						hardArtificialIntelligence();
					else
						mediumArtificialIntelligence();
				}
				if (chronometerOfTheTurn.getElapsedTime() >= MAX_WAIT_TIME_TO_PLAY_THE_TURN)
					changeTurnOutOfTime();

				ballsCollided.clear();
				ballsPocketed.clear();
			}
		}
	}

	private void checkWhichPlayerWin() {
		for (Ball ball : ballsPocketed) {
			if (ball.getIdentificativeNumber() == 8) {
				chronometerForTheWinOrLose.start();
				if (firstPlayer.isTurn()) {
					if ((firstPlayer.getColorBall() == ColorBall.RED
							&& ((redBalls.size() == MAX_SIZE_SET_OF_BALL_PLAYER) || (iAmPlayingWithACreatedLevel
									&& redBalls.size() == maxSizeOfRedBallForLevelEdited)))
							|| (firstPlayer.getColorBall() == ColorBall.YELLOW
									&& ((yellowBalls.size() == MAX_SIZE_SET_OF_BALL_PLAYER)
											|| (iAmPlayingWithACreatedLevel
													&& yellowBalls.size() == maxSizeSetOfYellowBallForLevelEdited)))) {
						firstPlayer.setWin(true);
						secondPlayer.setWin(false);
					} else {
						firstPlayer.setWin(false);
						secondPlayer.setWin(true);
					}
				}
				if (secondPlayer.isTurn()) {
					if ((secondPlayer.getColorBall() == ColorBall.RED
							&& ((redBalls.size() == MAX_SIZE_SET_OF_BALL_PLAYER) || (iAmPlayingWithACreatedLevel
									&& redBalls.size() == maxSizeOfRedBallForLevelEdited)))
							|| (secondPlayer.getColorBall() == ColorBall.YELLOW
									&& ((yellowBalls.size() == MAX_SIZE_SET_OF_BALL_PLAYER)
											|| (iAmPlayingWithACreatedLevel
													&& yellowBalls.size() == maxSizeSetOfYellowBallForLevelEdited)))) {

						firstPlayer.setWin(false);
						secondPlayer.setWin(true);
					} else {

						firstPlayer.setWin(true);
						secondPlayer.setWin(false);
					}
				}
			}
		}
	}

	private boolean checkIfICanMoveCommands() {
		if (iAmPlayingWithArtificialIntelligence && firstPlayer.isTurn() || !iAmPlayingWithArtificialIntelligence)
			return true;
		return false;
	}

	public void stop() {
		if (checkIfICanMoveCommands()) {
			if (repositionWhiteBall)
				whiteBall.setDirection(Direction.STOP);
			else {
				stick.setStickDirection(Direction.STOP);
				powerIndicator.setPowerType(DirectionOfPower.NOTHING);
			}
		}
	}

	public void moveRight() {
		if (modalityOfTheGame == Modality.OFFLINE) {
			if (checkIfICanMoveCommands())
				moveRightForOffLineAndOnLine();
		} else
			moveRightForOffLineAndOnLine();
	}

	private void moveRightForOffLineAndOnLine() {
		if (repositionWhiteBall) {
			whiteBall.setDirection(Direction.RIGHT);
			repositionWhiteBallWithTheMouse = false;
		} else
			stick.setStickDirection(Direction.RIGHT);
	}

	public void moveLeft() {
		if (modalityOfTheGame == Modality.OFFLINE) {
			if (checkIfICanMoveCommands())
				moveLeftForOffLineAndOnLineMode();
		} else
			moveLeftForOffLineAndOnLineMode();
	}

	private void moveLeftForOffLineAndOnLineMode() {
		if (repositionWhiteBall) {
			whiteBall.setDirection(Direction.LEFT);
			repositionWhiteBallWithTheMouse = false;
		} else
			stick.setStickDirection(Direction.LEFT);
		repositionWhiteBallWithTheMouse = false;
	}

	public void moveUp() {
		if (modalityOfTheGame == Modality.OFFLINE) {
			if (checkIfICanMoveCommands()) {
				moveUpForOfflineAndOnLineMode();
			}
		} else
			moveUpForOfflineAndOnLineMode();
	}

	private void moveUpForOfflineAndOnLineMode() {
		if (repositionWhiteBall) {
			whiteBall.setDirection(Direction.UP);
			repositionWhiteBallWithTheMouse = false;
		} else
			powerIndicator.setPowerType(DirectionOfPower.INCREMENT);
	}

	public void moveDown() {
		if (modalityOfTheGame == Modality.OFFLINE) {
			if (checkIfICanMoveCommands())
				moveDownForOffLineAndOnLineMode();
		} else
			moveDownForOffLineAndOnLineMode();
	}

	private void moveDownForOffLineAndOnLineMode() {
		if (repositionWhiteBall) {
			whiteBall.setDirection(Direction.DOWN);
			repositionWhiteBallWithTheMouse = false;
		} else
			powerIndicator.setPowerType(DirectionOfPower.DECREMENT);
	}

	public void hit() {
		if (modalityOfTheGame == Modality.OFFLINE) {
			if (checkIfICanMoveCommands()) {
				if (repositionWhiteBall) {
					setWhiteBallReposition();
				} else
					stick.setHit(true);
			}
		} else {
			if (repositionWhiteBall) {
				setWhiteBallReposition();
			} else {
				stick.setHit(true);
				chronometerOfTheTurn.stop();
				whiteBall.setVelocityXAxis(powerIndicator.getPower() * (-Math.sin(stick.getAngleOfRotation())));
				whiteBall.setVelocityYAxis(powerIndicator.getPower() * (-Math.cos(stick.getAngleOfRotation())));
				thinking = true;
			}
		}
	}

	public void update() {
		if (repositionWhiteBall)
			howToRepositionWhiteBall();
		else if (!repositionWhiteBall && !firstPlayer.isWin() && !secondPlayer.isWin()) {
			actualCounterForRepositionWhiteBall = 0;
			lastCounterForRepositionWhiteBall = 0;
			powerIndicator.update();
			if (physicsBallMovement.everyBallsStopped(ballsOnTheTable)) {
				if (modalityOfTheGame == Modality.ONLINE)
					stick.setHit(false);

				stick.initializeStickPosition(getWhiteball().getX(), getWhiteball().getY());
			}
			callStep();
			ballsUpdate();
		}
		updateSounds();
		updateCurrentStatusTheGame();
	}

	private void stickUpdateAndSetWhiteBallVelocity() {
		if (!stick.getIsHit())
			stick.update(getWhiteball().getX() + Ball.DIAMETER / 2, getWhiteball().getY() + Ball.DIAMETER / 2);
		else if (stick.getIsHit() && !thinking && modalityOfTheGame == Modality.OFFLINE) {
			chronometerOfTheTurn.stop();
			whiteBall.setVelocityXAxis(powerIndicator.getPower() * (-Math.sin(stick.getAngleOfRotation())));
			whiteBall.setVelocityYAxis(powerIndicator.getPower() * (-Math.cos(stick.getAngleOfRotation())));
			thinking = true;

			if (modalityOfTheGame == Modality.OFFLINE)
				stick.setHit(false);
		}
	}

	private void ballsUpdate() {
		for (Ball ball : ballsOnTheTable)
			physicsBallMovement.updateBallPosition(ball);
		for (Ball firstBall : ballsOnTheTable) {
			for (Ball secondBall : ballsOnTheTable) {
				if ((physicsBallMovement.detectCollision(firstBall, secondBall)
						|| physicsBallMovement.detectNextCollision(firstBall, secondBall))
						&& firstBall.getIdentificativeNumber() != secondBall.getIdentificativeNumber()) {
					addSoundsCollision();
					physicsBallMovement.handleCollision(firstBall, secondBall);
					physicsBallMovement.detectIfIsPocket(firstBall, ballsOnTheTable, ballsPocketed, redBalls,
							yellowBalls, table);
					if (firstBall.getIsPocketed())
						sounds.add(SoundsType.POCKET);
					else
						physicsBallMovement.checkBorderCollision(firstBall, table);
					physicsBallMovement.detectIfIsPocket(secondBall, ballsOnTheTable, ballsPocketed, redBalls,
							yellowBalls, table);
					if (secondBall.getIsPocketed())
						sounds.add(SoundsType.POCKET);
					else
						physicsBallMovement.checkBorderCollision(secondBall, table);
					physicsBallMovement.decreaseVelocityOfBall(secondBall);
					if (firstBall.getIdentificativeNumber() != 0)
						ballsCollided.add(firstBall);
					else
						ballsCollided.add(secondBall);
				}
			}
			physicsBallMovement.checkBorderCollision(firstBall, table);
			physicsBallMovement.detectIfIsPocket(firstBall, ballsOnTheTable, ballsPocketed, redBalls, yellowBalls,
					table);
			if (firstBall.getIsPocketed())
				sounds.add(SoundsType.POCKET);
			physicsBallMovement.decreaseVelocityOfBall(firstBall);
		}
		checkWhichPlayerWin();
	}

	private void updateSounds() {
		if (!sounds.isEmpty()) {
			for (SoundsType typeOfSound : sounds) {
				switch (typeOfSound) {
				case WEAK_COLLISION:
					SoundProvider.getCollisionePiccola().setFramePosition(0);
					SoundProvider.getCollisionePiccola().loop(0);
					SoundProvider.getCollisionePiccola().start();
					break;
				case STRONG_COLLISION:
					SoundProvider.getCollisioneForte().setFramePosition(0);
					SoundProvider.getCollisioneForte().loop(0);
					SoundProvider.getCollisioneForte().start();
					break;
				case MEDIUM_COLLISION:
					SoundProvider.getCollisioneMedia().setFramePosition(0);
					SoundProvider.getCollisioneMedia().loop(0);
					SoundProvider.getCollisioneMedia().start();
					break;
				case POCKET:
					SoundProvider.getImbucata().setFramePosition(0);
					SoundProvider.getImbucata().loop(0);
					SoundProvider.getImbucata().start();
					break;
				}
				sounds.remove(typeOfSound);
			}
		}
	}

	private void addSoundsCollision() {
		if (powerIndicator.getShotType() == ShotPower.WEAK) {
			sounds.add(SoundsType.WEAK_COLLISION);
		}
		if (powerIndicator.getShotType() == ShotPower.MEDIUM) {
			sounds.add(SoundsType.MEDIUM_COLLISION);
		}
		if (powerIndicator.getShotType() == ShotPower.STRONG) {
			sounds.add(SoundsType.STRONG_COLLISION);
		}
	}

	public boolean canPrintFoul() {
		if ((int) chronometerFoul.getElapsedTime() <= maxWaitTimeForFoul) {
			return true;
		} else {
			foulBallHitTheOppositeColor = false;
			foulNoneBallTouched = false;
			foulWhiteBallPocketed = false;
			chronometerFoul.reset();
			if (firstPlayer.isTurn())
				firstPlayer.setMakeFoul(false);
			else
				secondPlayer.setMakeFoul(false);
			return false;
		}

	}

	public boolean canPrintWinOrLose() {
		if (chronometerForTheWinOrLose.getElapsedTime() <= maxWaitTimeForFoul) {
			return true;
		} else {
			return false;
		}
	}

	private void setColorOfAPlayer() {
		Ball ball = ballsPocketed.get(0);
		if (ball.getIdentificativeNumber() <= 7) {
			if (firstPlayer.isTurn()) {
				firstPlayer.setColorBall(ColorBall.YELLOW);
				secondPlayer.setColorBall(ColorBall.RED);
			} else {
				firstPlayer.setColorBall(ColorBall.RED);
				secondPlayer.setColorBall(ColorBall.YELLOW);
			}
		} else if (ball.getIdentificativeNumber() >= 9) {
			if (firstPlayer.isTurn()) {
				firstPlayer.setColorBall(ColorBall.RED);
				secondPlayer.setColorBall(ColorBall.YELLOW);
			} else {
				firstPlayer.setColorBall(ColorBall.YELLOW);
				secondPlayer.setColorBall(ColorBall.RED);
			}
		}
		firstStepOfTheGame = false;
		secondStepOfTheGame = false;
		thirdStepOfTheGame = true;
		thinking = false;
		ballsPocketed.clear();
		ballsCollided.clear();
	}

	public void howToRepositionWhiteBall() {
		if (!ballsOnTheTable.contains(whiteBall)) {
			whiteBall = new WhiteBall(400, 400, 0);
			ballsOnTheTable.add(whiteBall);
		}

		if (!iAmPlayingWithArtificialIntelligence) {
			if (actualCounterForRepositionWhiteBall == 0)
				resetWhiteBallPosition();
			actualCounterForRepositionWhiteBall++;
			if (repositionWhiteBallWithTheMouse
					&& physicsBallMovement.checkThatTheBallDoesNotExceedTheEdgesOfTheTable(xMouse, yMouse, table))
				whiteBall.updateUsingMouse(xMouse, yMouse);
			else
				whiteBall.update(table.getX(), table.getY());
		} else {
			if (firstPlayer.isTurn()) {
				if (actualCounterForRepositionWhiteBall == 0)
					resetWhiteBallPosition();
				actualCounterForRepositionWhiteBall++;
				if (repositionWhiteBallWithTheMouse)
					whiteBall.updateUsingMouse(xMouse, yMouse);
				else
					whiteBall.update(table.getX(), table.getY());
			} else {
				if (actualCounterForRepositionWhiteBall == 0) {
					actualCounterForRepositionWhiteBall++;
					resetWhiteBallPosition();
				} else {
					actualCounterForRepositionWhiteBall++;
					selectWhiteBallDirection();
					whiteBall.automaticUpdate(table.getX(), table.getY(), automaticWhiteBallDirection);
					if (actualCounterForRepositionWhiteBall >= 150
							&& !physicsBallMovement.checkThatThereAreNoOverlappingBalls(ballsOnTheTable)) {
						repositionWhiteBall = false;
						actualCounterForRepositionWhiteBall = 0;
						lastCounterForRepositionWhiteBall = 0;
					}
				}
			}
			ballsCollided.clear();
			ballsPocketed.clear();
		}
	}

	public void selectWhiteBallDirection() {
		if (Math.abs(actualCounterForRepositionWhiteBall - lastCounterForRepositionWhiteBall) == 15) {
			lastCounterForRepositionWhiteBall = actualCounterForRepositionWhiteBall;
			Random randomGenerator = new Random();
			int direction = randomGenerator.nextInt(4);
			if (direction == 1)
				automaticWhiteBallDirection = Direction.UP;
			else if (direction == 2)
				automaticWhiteBallDirection = Direction.RIGHT;
			else if (direction == 3)
				automaticWhiteBallDirection = Direction.LEFT;
			else
				automaticWhiteBallDirection = Direction.DOWN;
		} else
			return;
	}

	private void resetWhiteBallPosition() {
		whiteBall.setIsPocketed(false);
		whiteBall.setX(table.getX() + Table.WIDTH / 2);
		whiteBall.setY(table.getY() + Table.HEIGHT / 2);
		whiteBall.setCx(table.getX() + Table.WIDTH / 2);
		whiteBall.setCy(table.getY() + Table.HEIGHT / 2);
		whiteBall.setVelocityXAxis(0);
		whiteBall.setVelocityYAxis(0);
	}

	public void start(final Runnable runnable) {
		new Thread() {
			@Override
			public void run() {
				while (!endOfTheGame) {
					if (!gameIsInPause) {
						update();
						try {
							Thread.sleep(25);
						} catch (final InterruptedException e) {
							interrupted = true;
						}
					}
					runnable.run();
				}
			};
		}.start();
	}

	private void autoStickUpdateForArtificialIntelligence() {
		if (iAmPlayingWithEasyArtificialIntelligence)
			stick.automaticUpdate(whiteBall.getX() + Ball.DIAMETER / 2, whiteBall.getY() + Ball.DIAMETER / 2,
					automaticStickDirection);
		else
			stick.automaticUpdate(whiteBall.getX() + Ball.DIAMETER / 2, whiteBall.getY() + Ball.DIAMETER / 2,
					Direction.RIGHT);
	}

	private void setEasyArtificialIntelligenceStickDirectionAndSecondOfShoot() {
		if (setEasyArtificialIntelligenceParameters) {
			setEasyArtificialIntelligenceParameters = false;
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(MAX_WAIT_TIME_TO_PLAY_THE_TURN);
			if (randomInt <= 3)
				randomInt = randomInt + 4;
			else if (randomInt == MAX_WAIT_TIME_TO_PLAY_THE_TURN)
				randomInt = MAX_WAIT_TIME_TO_PLAY_THE_TURN - 2;
			autoEasyShootArtificialIntelligence = randomInt;
			int randomChoose = randomGenerator.nextInt(2);
			if (randomChoose == 1)
				automaticStickDirection = Direction.RIGHT;
			else
				automaticStickDirection = Direction.LEFT;

			autoEasyShootPower = randomGenerator.nextInt(28);
			if (autoEasyShootPower < 2.0)
				autoEasyShootPower = 3.0;
		}
		setEasyArtificialIntelligenceParameters = false;
	}

	private void easyArtificialIntelligence() {
		if (!stick.getIsHit()) {
			autoStickUpdateForArtificialIntelligence();
			powerIndicator.autoUpdate(autoEasyShootPower);
		}
		if (chronometerOfTheTurn.getElapsedTime() == autoEasyShootArtificialIntelligence) {
			stick.setHit(true);
			chronometerOfTheTurn.stop();
			if (stick.getIsHit() && !thinking) {
				whiteBall.setVelocityXAxis(autoEasyShootPower * (-Math.sin(stick.getAngleOfRotation())));
				whiteBall.setVelocityYAxis(autoEasyShootPower * (-Math.cos(stick.getAngleOfRotation())));
				thinking = true;
				stick.setHit(false);
			}
		}
	}

	private void mediumArtificialIntelligence() {
		List<Ball> balls = new CopyOnWriteArrayList<Ball>(copyListOfBallsOnTheTable());
		boolean result = false;
		powerIndicator.autoUpdate(22);
		if (!stick.getIsHit() && !result)
			autoStickUpdateForArtificialIntelligence();

		result = artificialIntelligence.calculateResultForMediumArtificialIntelligence(table, balls,
				stick.getAngleOfRotation(), secondPlayer, copyYellowBalls(), copyRedBalls());
		if (result)
			stickShootforMediumAndHardArtificialIntelligence();
	}

	private void hardArtificialIntelligence() {
		List<Ball> balls = new CopyOnWriteArrayList<Ball>(copyListOfBallsOnTheTable());
		boolean result = false;
		powerIndicator.autoUpdate(22);
		if (!stick.getIsHit() && !result)
			autoStickUpdateForArtificialIntelligence();

		result = artificialIntelligence.calculateResultForHardArtificialIntelligence(table, balls,
				stick.getAngleOfRotation(), secondPlayer, copyYellowBalls(), copyRedBalls());
		if (result)
			stickShootforMediumAndHardArtificialIntelligence();
	}

	private void stickShootforMediumAndHardArtificialIntelligence() {
		stick.setHit(true);
		chronometerOfTheTurn.stop();
		if (stick.getIsHit() && !thinking) {
			whiteBall.setVelocityXAxis(22 * (-Math.sin(stick.getAngleOfRotation())));
			whiteBall.setVelocityYAxis(22 * (-Math.cos(stick.getAngleOfRotation())));
			thinking = true;
			stick.setHit(false);
		}
	}

	public void startNetworkGame() {
		initializeObject();
		initializeBall();
	}

	public void closeNetworkGame(final String nameClientDisconnected) {
		disconnected = true;
		disconnectedClientName = nameClientDisconnected;
	}

	public void clearWorld() {
		ballsOnTheTable.clear();
		ballsCollided.clear();
		ballsPocketed.clear();
		yellowBalls.clear();
		redBalls.clear();
		sounds.clear();
	}

	private List<Ball> copyYellowBalls() {
		List<Ball> balls = new CopyOnWriteArrayList<Ball>();
		if (!yellowBalls.isEmpty())
			for (Ball ball : yellowBalls) {
				Ball temporaryBall = new Ball(ball.getX(), ball.getY(), ball.getIdentificativeNumber());
				balls.add(temporaryBall);
			}
		return balls;
	}

	private List<Ball> copyRedBalls() {
		List<Ball> balls = new CopyOnWriteArrayList<Ball>();
		if (!redBalls.isEmpty())
			for (Ball ball : redBalls) {
				Ball temporaryBall = new Ball(ball.getX(), ball.getY(), ball.getIdentificativeNumber());
				balls.add(temporaryBall);
			}
		return balls;
	}

	private List<Ball> copyListOfBallsOnTheTable() {
		List<Ball> balls = new CopyOnWriteArrayList<Ball>();
		for (Ball ball : ballsOnTheTable) {
			Ball temporaryBall = new Ball(ball.getX(), ball.getY(), ball.getIdentificativeNumber());
			balls.add(temporaryBall);
		}
		return balls;
	}
}
