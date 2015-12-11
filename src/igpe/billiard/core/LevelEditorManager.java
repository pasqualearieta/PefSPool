package igpe.billiard.core;

import igpe.billiard.file.FileProvider;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LevelEditorManager {

	public static final int MAX_IDENTIFICATIVE_NUMBER_YELLOW_BALLS = 7;
	public static final int MAX_IDENTIFICATIVE_NUMBER_RED_BALLS = 15;
	public static final int MAX_NUMBER_OF_BALLS_ON_THE_TABLE = 16;
	public static final int MINIMUN_NUMBER_OF_BALLS_ON_THE_TABLE = 4;

	private Table table;
	private PhysicsBallMovement physicsBallsMovement;
	private StringBuilder currentStatusOfTheGame;
	private List<Ball> ballsOnTheTable;
	private int counterYellowBalls = 0;
	private int counterRedBalls = 0;
	private ColorBall colorBallFirstPlayer = ColorBall.NOTHING;
	private ColorBall colorBallSecondPlayer = ColorBall.NOTHING;
	private String difficultSelected = "MEVSME";
	private boolean interrupted = false;
	private int indexTable = 3;
	private int counter = 4;

	public LevelEditorManager() {
		currentStatusOfTheGame = new StringBuilder("");
		table = new Table(200, 200);
		ballsOnTheTable = new CopyOnWriteArrayList<Ball>();
		physicsBallsMovement = new PhysicsBallMovement();
	}

	public Table getTable() {
		return table;
	}

	public int getIndexTable() {
		return indexTable;
	}

	public List<Ball> getBallsOnTheTable() {
		return ballsOnTheTable;
	}

	public String getDifficultSelected() {
		return difficultSelected;
	}

	public ColorBall getColorBallFirstPlayer() {
		return colorBallFirstPlayer;
	}

	public ColorBall getColorBallSecondPlayer() {
		return colorBallSecondPlayer;
	}

	public boolean getInterrupted() {
		return interrupted;
	}

	public String getStringCurrentStatusOfTheGame() {
		return currentStatusOfTheGame.toString();
	}

	public void setColorBallFirstPlayer(final ColorBall colorBallFirstPlayer) {
		this.colorBallFirstPlayer = colorBallFirstPlayer;
	}

	public void setColorBallSecondPlayer(final ColorBall colorBallSecondPlayer) {
		this.colorBallSecondPlayer = colorBallSecondPlayer;
	}

	public void setDifficult(final String difficult) {
		this.difficultSelected = difficult;
	}

	public void setIndexTable(final int indexTable) {
		this.indexTable = indexTable;
	}

	public void clear() {
		ballsOnTheTable.clear();
		counterRedBalls = 0;
		counterYellowBalls = 0;
		difficultSelected = "MEVSME";
	}

	private void setInitialPositionOfBall(final Ball ball) {
		ball.setIsPocketed(false);
		ball.setX(table.getX() + Table.WIDTH / 2);
		ball.setY(table.getY() + Table.HEIGHT / 2);
		ball.setCx(table.getX() + Table.WIDTH / 2);
		ball.setCy(table.getY() + Table.HEIGHT / 2);
		ball.setVelocityXAxis(0);
		ball.setVelocityYAxis(0);
	}

	public void addWhiteBall() {
		if (!whiteBallIsPresentOnTheTable()) {
			WhiteBall whiteBall = (new WhiteBall(0, 0, 0));
			setInitialPositionOfBall(whiteBall);
			ballsOnTheTable.add(whiteBall);
		}
	}

	public void addBlackBall() {
		if (!blackBallIsPresentOnTheTable()) {
			Ball blackBall = (new Ball(0, 0, 8));
			setInitialPositionOfBall(blackBall);
			ballsOnTheTable.add(blackBall);
		}
	}

	public void addYellowBall() {
		if (counterYellowBalls < MAX_IDENTIFICATIVE_NUMBER_YELLOW_BALLS) {
			Ball ball = (new Ball(0, 0, counterYellowBalls + 1));
			setInitialPositionOfBall(ball);
			ballsOnTheTable.add(ball);
			counterYellowBalls++;
		}
	}

	public void addRedBall() {
		if (counterRedBalls < MAX_IDENTIFICATIVE_NUMBER_RED_BALLS) {
			Ball ball = (new Ball(0, 0, counterRedBalls + 9));
			setInitialPositionOfBall(ball);
			ballsOnTheTable.add(ball);
			counterRedBalls++;
		}
	}


	public boolean checkThatTheMouseIntersectWithABall(final int xMouse,
			final int yMouse) {
		if (!ballsOnTheTable.isEmpty()) {
			for (Ball ball : ballsOnTheTable)
				if (xMouse >= ball.getX()
						&& xMouse <= ball.getX() + Ball.DIAMETER
						&& yMouse >= ball.getY()
						&& yMouse <= ball.getY() + Ball.DIAMETER) {
					ball.setCx(-1);
					return true;
				}
		}
		return false;
	}

	public Ball checkBallIWantDelete(final int xMouse, final int yMouse) {
		Ball temporaryBall = (new Ball(0, 0, 99));
		if (!ballsOnTheTable.isEmpty()) {
			for (Ball ball : ballsOnTheTable)
				if (xMouse >= ball.getX()
						&& xMouse <= ball.getX() + Ball.DIAMETER
						&& yMouse >= ball.getY()
						&& yMouse <= ball.getY() + Ball.DIAMETER) {
					temporaryBall = ball;
				}
		}
		return temporaryBall;
	}

	public boolean checkThatTheBallDoesNotExceedTheEdgesOfTheTable(
			final Ball ball) {
		final int lastXTable = (int) (table.getX() + Table.WIDTH - 1.5 * Ball.DIAMETER);
		final int startXTable = table.getX() + Ball.DIAMETER / 2;
		final int startYTable = table.getY() + Ball.DIAMETER / 2;
		final int endYTable = (int) (table.getY() + Table.HEIGHT - 1.5 * Ball.DIAMETER);
	
		if (ball.getX() <= lastXTable && ball.getX() >= startXTable
				&& ball.getY() <= endYTable && ball.getY() >= startYTable)
			return true;
		else
			return false;
	}

	public boolean checkThatThereAreNoOverlappingBalls() {
		if (!ballsOnTheTable.isEmpty())
			for (Ball firstBall : ballsOnTheTable)
				if (firstBall.getCx() == -1)
					for (Ball secondBall : ballsOnTheTable)
						if (firstBall.getIdentificativeNumber() != secondBall
								.getIdentificativeNumber())
							if (physicsBallsMovement.detectCollision(firstBall,
									secondBall))
								return true;
		return false;
	}

	public boolean checkThatBeforeSavingThereAreNoOverlappingBalls() {
		if (!ballsOnTheTable.isEmpty())
			for (Ball firstBall : ballsOnTheTable)
				for (Ball secondBall : ballsOnTheTable)
					if (firstBall.getIdentificativeNumber() != secondBall
							.getIdentificativeNumber())
						if (physicsBallsMovement.detectCollision(firstBall,
								secondBall))
							return true;
		return false;
	}

	public boolean whiteBallIsPresentOnTheTable() {
		if (!ballsOnTheTable.isEmpty()) {
			for (Ball ball : ballsOnTheTable)
				if (ball.getIdentificativeNumber() == 0)
					return true;
		}
		return false;
	}

	public boolean blackBallIsPresentOnTheTable() {
		if (!ballsOnTheTable.isEmpty()) {
			for (Ball ball : ballsOnTheTable)
				if (ball.getIdentificativeNumber() == 8)
					return true;
		}
		return false;
	}

	public boolean atLeastOneYellowBallIsPresentOnTheTable() {
		if (!ballsOnTheTable.isEmpty()) {
			for (Ball ball : ballsOnTheTable)
				if (ball.getIdentificativeNumber() >= 1
						&& ball.getIdentificativeNumber() <= 7)
					return true;
		}
		return false;
	}

	public boolean atLeastOneRedBallIsPresentOnTheTable() {
		if (!ballsOnTheTable.isEmpty()) {
			for (Ball b : ballsOnTheTable)
				if (b.getIdentificativeNumber() >= 9
						&& b.getIdentificativeNumber() <= 15)
					return true;
		}
		return false;
	}

	public void resetBallsForPositionItOnTheTable() {
		if (!ballsOnTheTable.isEmpty())
			for (Ball ball : ballsOnTheTable)
				ball.setCx(0);
	}

	public void start(Runnable runnable) {
		new Thread() {
			@Override
			public void run() {
				while (true) {
					updateCurrentStatusTheGame();
					try {
						Thread.sleep(30);
					} catch (final InterruptedException e) {
						interrupted = true;
					}
					runnable.run();
				}
			};
		}.start();
	}

	public void updateBall(final int xMouse, final int yMouse) {
		if (!ballsOnTheTable.isEmpty())
			for (Ball ball : ballsOnTheTable)
				if (ball.getCx() == -1) {
					Ball temporaryBall = new Ball(ball);
					temporaryBall.setX(xMouse);
					temporaryBall.setY(yMouse);
					if (checkThatTheBallDoesNotExceedTheEdgesOfTheTable(temporaryBall)) {
						ball.setX(xMouse);
						ball.setY(yMouse);
					}
				}
	}

	private void updateStatusOfTheBall(final Ball ball) {
		currentStatusOfTheGame.append(ball.getIdentificativeNumber() + ";"
				+ ball.getX() + ";" + ball.getY() + ";" + ball.getCx() + ";"
				+ ball.getCy() + ";" + ball.getVelocityXAxis() + ";"
				+ ball.getVelocityYAxis() + ";");
	}

	public void updateCurrentStatusTheGame() {
		currentStatusOfTheGame = new StringBuilder("");
		currentStatusOfTheGame.append(7 - counterYellowBalls + ";");
		currentStatusOfTheGame.append(7 - counterRedBalls + ";");
		currentStatusOfTheGame.append(ballsOnTheTable.size() + ";");
		currentStatusOfTheGame.append(indexTable + ";");
	
		for (Ball ball : ballsOnTheTable)
			updateStatusOfTheBall(ball);
	
		if (difficultSelected == "HARD")
			currentStatusOfTheGame.append(3 + ";");
		else if (difficultSelected == "MEDIUM")
			currentStatusOfTheGame.append(2 + ";");
		else if (difficultSelected == "EASY")
			currentStatusOfTheGame.append(1 + ";");
		else if (difficultSelected == "MEVSME")
			currentStatusOfTheGame.append(0 + ";");
	
		if (colorBallFirstPlayer.equals(ColorBall.RED)) {
			currentStatusOfTheGame.append(1 + ";");
			currentStatusOfTheGame.append(2 + ";");
		} else {
			currentStatusOfTheGame.append(1 + ";");
			currentStatusOfTheGame.append(2 + ";");
		}
	}

	public void resumeLevelCreated(int indexOfRescue) {
		currentStatusOfTheGame.delete(0, currentStatusOfTheGame.length());
		currentStatusOfTheGame.append(FileProvider.getRescueContent(indexOfRescue,
				GameMode.EDITOR));
		resumeAllObjectFromTheLevelCreated();
	}

	public void resumeAllObjectFromTheLevelCreated() {
		ballsOnTheTable.clear();
		String[] statusTheGame = currentStatusOfTheGame.toString().split(";");
		counterRedBalls = 0;
		counterYellowBalls = 0;
		counterYellowBalls = -(Integer.parseInt(statusTheGame[0]) - 7);
		counterRedBalls = -(Integer.parseInt(statusTheGame[1]) - 7);
		final int numberOfBallsOnTheTable = Integer.parseInt(statusTheGame[2]);
		for (int index = 0; index < numberOfBallsOnTheTable; index++) {
			Ball ball = resumeBallEditor(statusTheGame);
			ballsOnTheTable.add(ball);
		}
	}

	public Ball resumeBallEditor(String[] statusTheGame) {
		final int identificativeNumber = Integer
				.parseInt(statusTheGame[counter++]);
		final int xPosition = Integer.parseInt(statusTheGame[counter++]);
		final int yPosition = Integer.parseInt(statusTheGame[counter++]);
		counter = counter + 4;
		Ball ball = new Ball(xPosition, yPosition, identificativeNumber);
		return ball;
	}
}
