package igpe.billiard.core;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArtificialIntelligence {
	private PhysicsBallMovement physicsBallMovement = new PhysicsBallMovement();
	private boolean endOFLife = false;

	public boolean getEndOFLife() {
		return endOFLife;
	}

	public void setEndOFLife(final boolean endOFLife) {
		this.endOFLife = endOFLife;
	}

	public boolean calculateResultForMediumArtificialIntelligence(
			final Table table, List<Ball> balls,
			final double stickAngleOfRotation, Player player,
			List<Ball> yellowBalls, List<Ball> redBalls) {
		List<Ball> ballsCollided = new CopyOnWriteArrayList<Ball>();
		List<Ball> ballsPocketed = new CopyOnWriteArrayList<Ball>();
		for (Ball ball : balls) {
			if (ball.getIdentificativeNumber() == 0) {
				ball.setVelocityXAxis(22 * (-Math.sin(stickAngleOfRotation)));
				ball.setVelocityYAxis(22 * (-Math.cos(stickAngleOfRotation)));
			}
		}
		while (!physicsBallMovement.everyBallsStopped(balls))
			ballUpdate(table, balls, ballsCollided, ballsPocketed, yellowBalls,
					redBalls);

		for (Ball ball : balls)
			if (ball.getIdentificativeNumber() == 0 && ball.getIsPocketed())
				return false;

		if (ballsCollided.isEmpty())
			return false;
		else {
			Ball firstBallCollided = ballsCollided.get(0);
			if (firstBallCollided.getIdentificativeNumber() == 0)
				firstBallCollided = ballsCollided.get(1);

			if (!ballsPocketed.isEmpty()) {
				for (Ball ball : ballsPocketed) {
					if (ball.getIdentificativeNumber() == 8) {
						if (player.getColorBall() == ColorBall.RED
								&& redBalls.size() == MatchManager.MAX_SIZE_SET_OF_BALL_PLAYER)
							return true;
						else if (player.getColorBall() == ColorBall.YELLOW
								&& yellowBalls.size() == MatchManager.MAX_SIZE_SET_OF_BALL_PLAYER)
							return true;
						else
							return false;
					}
				}
			}

			if (player.getColorBall() == ColorBall.NOTHING)
				return true;

			else {
				if (player.getColorBall() == ColorBall.YELLOW
						&& firstBallCollided.getIdentificativeNumber() <= 7)
					return true;

				else if (player.getColorBall() == ColorBall.RED
						&& firstBallCollided.getIdentificativeNumber() >= 9)
					return true;

				else if (firstBallCollided.getIdentificativeNumber() == 8
						&& player.getColorBall() == ColorBall.YELLOW
						&& yellowBalls.size() == MatchManager.MAX_SIZE_SET_OF_BALL_PLAYER)
					return true;

				else if (firstBallCollided.getIdentificativeNumber() == 8
						&& player.getColorBall() == ColorBall.RED
						&& redBalls.size() == MatchManager.MAX_SIZE_SET_OF_BALL_PLAYER)
					return true;
			}

			for (Ball ball : ballsPocketed) {
				if (player.getColorBall() == ColorBall.YELLOW
						&& ball.getIdentificativeNumber() <= 7
						&& !checkIfIsPocketARedBall(ballsPocketed))
					return true;
				else if (player.getColorBall() == ColorBall.RED
						&& ball.getIdentificativeNumber() >= 9
						&& !checkIfIsPocketAYellowBall(ballsPocketed))
					return true;
			}

		}
		return false;
	}

	public boolean calculateResultForHardArtificialIntelligence(
			final Table table, List<Ball> balls, final double stickAngleOfRotation,
			final Player player, List<Ball> yellowBalls, List<Ball> redBalls) {
		List<Ball> ballsCollided = new CopyOnWriteArrayList<Ball>();
		List<Ball> ballsPocketed = new CopyOnWriteArrayList<Ball>();
		for (Ball ball : balls) {
			if (ball.getIdentificativeNumber() == 0) {
				ball.setVelocityXAxis(22 * (-Math.sin(stickAngleOfRotation)));
				ball.setVelocityYAxis(22 * (-Math.cos(stickAngleOfRotation)));
			}
		}
		while (!physicsBallMovement.everyBallsStopped(balls))
			ballUpdate(table, balls, ballsCollided, ballsPocketed, yellowBalls,
					redBalls);
		if (ballsCollided.isEmpty() || ballsPocketed.isEmpty())
			return false;
		else {
			for (Ball ball : balls)
				if (ball.getIdentificativeNumber() == 0 && ball.getIsPocketed())
					return false;
			if (!ballsPocketed.isEmpty()) {
				if (player.getColorBall() == ColorBall.NOTHING)
					return true;

				Ball firstBallCollided = ballsCollided.get(0);
				if (firstBallCollided.getIdentificativeNumber() == 0)
					firstBallCollided = ballsCollided.get(1);

				for (Ball ball : ballsPocketed) {
					if (ball.getIdentificativeNumber() == 8) {

						if (player.getColorBall() == ColorBall.RED
								&& redBalls.size() == MatchManager.MAX_SIZE_SET_OF_BALL_PLAYER
								&& !checkIfIsPocketAYellowBall(ballsPocketed)
								&& firstBallCollided.getIdentificativeNumber() >= 8)
							return true;
						else if (player.getColorBall() == ColorBall.YELLOW
								&& yellowBalls.size() == MatchManager.MAX_SIZE_SET_OF_BALL_PLAYER
								&& !checkIfIsPocketARedBall(ballsPocketed)
								&& firstBallCollided.getIdentificativeNumber() <= 8)
							return true;
						else
							return false;
					}
				}

				for (Ball ball : ballsPocketed) {
					if (firstBallCollided.getIdentificativeNumber() == 8)
						return false;

					if (player.getColorBall() == ColorBall.YELLOW
							&& ball.getIdentificativeNumber() <= 7
							&& firstBallCollided.getIdentificativeNumber() <= 7
							&& !checkIfIsPocketARedBall(ballsPocketed))
						return true;

					else if (player.getColorBall() == ColorBall.RED
							&& ball.getIdentificativeNumber() >= 9
							&& firstBallCollided.getIdentificativeNumber() >= 9
							&& !checkIfIsPocketAYellowBall(ballsPocketed)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private void ballUpdate(final Table table, List<Ball> balls,
			List<Ball> ballsCollided, List<Ball> ballsPocketed,
			List<Ball> yellowBall, List<Ball> redBall) {
		for (Ball ball : balls)
			physicsBallMovement.updateBallPosition(ball);
		for (Ball firstBall : balls) {
			for (Ball secondBall : balls) {
				if ((physicsBallMovement.detectCollision(firstBall, secondBall) || physicsBallMovement
						.detectNextCollision(firstBall, secondBall))
						&& firstBall.getIdentificativeNumber() != secondBall
								.getIdentificativeNumber()) {
					physicsBallMovement.handleCollision(firstBall, secondBall);
					physicsBallMovement.detectIfIsPocket(firstBall, balls,
							ballsPocketed, redBall, yellowBall, table);
					if (!firstBall.getIsPocketed())
						physicsBallMovement.checkBorderCollision(firstBall,
								table);
					physicsBallMovement.detectIfIsPocket(secondBall, balls,
							ballsPocketed, redBall, yellowBall, table);
					if (!secondBall.getIsPocketed())
						physicsBallMovement.checkBorderCollision(secondBall,
								table);
					physicsBallMovement.decreaseVelocityOfBall(secondBall);
					if (firstBall.getIdentificativeNumber() != 0)
						ballsCollided.add(firstBall);
					else
						ballsCollided.add(secondBall);
				}
			}
			physicsBallMovement.checkBorderCollision(firstBall, table);
			physicsBallMovement.detectIfIsPocket(firstBall, balls,
					ballsPocketed, redBall, yellowBall, table);
			physicsBallMovement.decreaseVelocityOfBall(firstBall);
		}
	}

	private boolean checkIfIsPocketAYellowBall(final List<Ball> ballsPocketed) {
		for (Ball ball : ballsPocketed)
			if (ball.getIdentificativeNumber() <= 7
					&& ball.getIdentificativeNumber() >= 1)
				return true;
		return false;
	}

	private boolean checkIfIsPocketARedBall(final List<Ball> ballsPocketed) {
		for (Ball ball : ballsPocketed)
			if (ball.getIdentificativeNumber() >= 9
					&& ball.getIdentificativeNumber() <= 15)
				return true;
		return false;
	}

	public void start(final Runnable runnable) {
		new Thread() {
			@Override
			public void run() {
				while (!getEndOFLife()) {
					runnable.run();
					try {
						Thread.currentThread();
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
						// donothing
					}
				}
			};
		}.start();
	}
}
