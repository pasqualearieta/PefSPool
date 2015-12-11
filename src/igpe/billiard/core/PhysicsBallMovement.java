package igpe.billiard.core;

import java.util.List;

public class PhysicsBallMovement {

	private static final double MARGIN_IN_MORE_COLLISION_FOR_THE_FIRST_CHECK = 1.5;
	private static final double MARGIN_IN_MORE_COLLISION_FOR_THE_SECOND_CHECK = 2.0;

	public boolean detectCollision(final Ball firstBall,final  Ball secondBall) {
		double firstBallXPosition = firstBall.getX()
				+ firstBall.getVelocityXAxis()
				* MARGIN_IN_MORE_COLLISION_FOR_THE_FIRST_CHECK;
		double firstBallYPosition = firstBall.getY()
				+ firstBall.getVelocityYAxis()
				* MARGIN_IN_MORE_COLLISION_FOR_THE_FIRST_CHECK;
		double secondBallXPosition = secondBall.getX()
				+ secondBall.getVelocityXAxis()
				* MARGIN_IN_MORE_COLLISION_FOR_THE_FIRST_CHECK;
		double secondBallYPosition = secondBall.getY()
				+ secondBall.getVelocityYAxis()
				* MARGIN_IN_MORE_COLLISION_FOR_THE_FIRST_CHECK;
	
		if (firstBall.getIdentificativeNumber() != secondBall
				.getIdentificativeNumber()) {
			double sqrDiameter = Ball.DIAMETER * Ball.DIAMETER;
			double distanceX = firstBallXPosition - secondBallXPosition;
			double distanceY = firstBallYPosition - secondBallYPosition;
			double distSqr = (distanceX * distanceX) + (distanceY * distanceY);
			if (distSqr <= sqrDiameter)
				return true;
		}
		return false;
	}

	public boolean detectNextCollision(final Ball firstBall, final Ball secondBall) {
		double firstBallNextXPosition = firstBall.getX()
				+ firstBall.getVelocityXAxis()
				* MARGIN_IN_MORE_COLLISION_FOR_THE_SECOND_CHECK;
		double firstBallNextYPosition = firstBall.getY()
				+ firstBall.getVelocityYAxis()
				* MARGIN_IN_MORE_COLLISION_FOR_THE_SECOND_CHECK;
		double secondBallNextXPosition = secondBall.getX()
				+ secondBall.getVelocityXAxis()
				* MARGIN_IN_MORE_COLLISION_FOR_THE_SECOND_CHECK;
		double secondBallNextYPosition = secondBall.getY()
				+ secondBall.getVelocityYAxis()
				* MARGIN_IN_MORE_COLLISION_FOR_THE_SECOND_CHECK;
	
		if (firstBall.getIdentificativeNumber() != secondBall
				.getIdentificativeNumber()) {
			double sqrDiameter = Ball.DIAMETER * Ball.DIAMETER;
			double distanceX = firstBallNextXPosition - secondBallNextXPosition;
			double distanceY = firstBallNextYPosition - secondBallNextYPosition;
			double distSqr = (distanceX * distanceX) + (distanceY * distanceY);
			if (distSqr <= sqrDiameter)
				return true;
		}
		return false;
	}

	public void handleCollision(Ball firstBall, Ball secondBall) {
		if (!firstBall.getIsPocketed() && !secondBall.getIsPocketed()) {
			double distanceX, distanceY, distanceSquared;
			distanceY = (secondBall.getY() - firstBall.getY());
			distanceX = (secondBall.getX() - firstBall.getX());
			distanceSquared = distanceX * distanceX + distanceY * distanceY;
			
			double distanceForInverseVelocityFirstBall, distancesForVelocityFirstBall, distanceForVelocitySecondBall, distanceForInverseVelocitySecondBall;
		
			distancesForVelocityFirstBall = (distanceX
					* firstBall.getVelocityXAxis() + distanceY
					* firstBall.getVelocityYAxis())
					/ distanceSquared;
			distanceForInverseVelocityFirstBall = (distanceX
					* firstBall.getVelocityYAxis() - distanceY
					* firstBall.getVelocityXAxis())
					/ distanceSquared;
			distanceForVelocitySecondBall = (distanceX
					* secondBall.getVelocityXAxis() + distanceY
					* secondBall.getVelocityYAxis())
					/ distanceSquared;
			distanceForInverseVelocitySecondBall = (distanceX
					* secondBall.getVelocityYAxis() - distanceY
					* secondBall.getVelocityXAxis())
					/ distanceSquared;
			double newVelocityXAxisFirstBall, newVelocityYAxisFirstBall, newVelocityXAxisSecondBall, newVelocityYAxisSecondBall;
			newVelocityXAxisFirstBall = distanceForVelocitySecondBall
					* distanceX - distanceForInverseVelocityFirstBall
					* distanceY;
			newVelocityYAxisFirstBall = distanceForVelocitySecondBall
					* distanceY + distanceForInverseVelocityFirstBall
					* distanceX;
			newVelocityXAxisSecondBall = distancesForVelocityFirstBall
					* distanceX - distanceForInverseVelocitySecondBall
					* distanceY;
			newVelocityYAxisSecondBall = distancesForVelocityFirstBall
					* distanceY + distanceForInverseVelocitySecondBall
					* distanceX;
			firstBall.setVelocityYAxis(newVelocityYAxisFirstBall);
			firstBall.setVelocityXAxis(newVelocityXAxisFirstBall);
			secondBall.setVelocityYAxis(newVelocityYAxisSecondBall);
			secondBall.setVelocityXAxis(newVelocityXAxisSecondBall);
		}
	}

	public void updateBallPosition(final Ball ballToUpdate) {
		ballToUpdate.setCx(ballToUpdate.getCx()
				+ ballToUpdate.getVelocityXAxis());
		ballToUpdate.setX((int) ballToUpdate.getCx());
		ballToUpdate.setCy(ballToUpdate.getCy()
				+ ballToUpdate.getVelocityYAxis());
		ballToUpdate.setY((int) ballToUpdate.getCy());
	}

	public void decreaseVelocityOfBall(final Ball ball) {
		double velocity = Math.sqrt(Math.pow(ball.getVelocityXAxis(), 2)
				+ Math.pow(ball.getVelocityYAxis(), 2));
		if (!(ball.getVelocityXAxis() == 0 && ball.getVelocityYAxis() == 0)) {
			if (ball.getVelocityYAxis() == 0) {
				ball.setVelocityXAxis(ball.getVelocityXAxis() * 99 / 101);
			} else if (ball.getVelocityXAxis() == 0) {
				ball.setVelocityYAxis(ball.getVelocityYAxis() * 99 / 101);
			} else {
				double tan = ball.getVelocityYAxis() / ball.getVelocityXAxis();
				ball.setVelocityXAxis(ball.getVelocityXAxis() * 99 / 101);
				ball.setVelocityYAxis(ball.getVelocityXAxis() * tan);
			}
			if (velocity < 0.5) {
				ball.setVelocityXAxis(0);
				ball.setVelocityYAxis(0);
			}
		}
	}

	public void checkBorderCollision(final Ball ball, final Table table) {
		for (Border border : table.getTableBorder()) {
			if (ball.getX() + Ball.DIAMETER / 2 >= border.getStartX()
					&& ball.getX() + Ball.DIAMETER / 2 <= border.getEndX()
					&& ball.getY() <= border.getStartY()
					&& border.getIdentificativeNumber() == 1) {
				ball.setVelocityYAxis(-ball.getVelocityYAxis());
				ball.setCy(ball.getCy() + ball.getVelocityYAxis());
				ball.setY((int) ball.getCy());
			}
			if (ball.getX() + Ball.DIAMETER / 2 >= border.getStartX()
					&& ball.getX() + Ball.DIAMETER / 2 <= border.getEndX()
					&& ball.getY() <= border.getStartY()
					&& border.getIdentificativeNumber() == 2) {
				ball.setVelocityYAxis(-ball.getVelocityYAxis());
				ball.setCy(ball.getCy() + ball.getVelocityYAxis());
				ball.setY((int) ball.getCy());
			}
			if (ball.getX() + Ball.DIAMETER >= border.getEndX()
					&& ball.getY() + Ball.DIAMETER / 2 >= border.getStartY()
					&& ball.getY() + Ball.DIAMETER / 2 <= border.getEndY()
					&& border.getIdentificativeNumber() == 3) {
				ball.setVelocityXAxis(-ball.getVelocityXAxis());
				ball.setCx(ball.getCx() + ball.getVelocityXAxis());
				ball.setX((int) ball.getCx());
			}
			if (ball.getX() + Ball.DIAMETER / 2 >= border.getStartX()
					&& ball.getX() + Ball.DIAMETER / 2 <= border.getEndX()
					&& ball.getY() + Ball.DIAMETER >= border.getStartY()
					&& border.getIdentificativeNumber() == 4) {
				ball.setVelocityYAxis(-ball.getVelocityYAxis());
				ball.setCy(ball.getCy() + ball.getVelocityYAxis());
				ball.setY((int) ball.getCy());
			}
			if (ball.getX() + Ball.DIAMETER / 2 >= border.getStartX()
					&& ball.getX() + Ball.DIAMETER / 2 <= border.getEndX()
					&& ball.getY() + Ball.DIAMETER >= border.getStartY()
					&& border.getIdentificativeNumber() == 5) {
				ball.setVelocityYAxis(-ball.getVelocityYAxis());
				ball.setCy(ball.getCy() + ball.getVelocityYAxis());
				ball.setY((int) ball.getCy());
			}
			if (ball.getY() + Ball.DIAMETER / 2 > border.getStartY()
					&& ball.getY() + Ball.DIAMETER / 2 <= border.getEndY()
					&& border.getIdentificativeNumber() == 6
					&& ball.getX() <= border.getStartX()) {
				ball.setVelocityXAxis(-ball.getVelocityXAxis());
				ball.setCx(ball.getCx() + ball.getVelocityXAxis());
				ball.setX((int) ball.getCx());
			}
		}
	}

	public void detectIfIsPocket(Ball ball, List<Ball> ballsOnTheTable,
			List<Ball> ballsPocketed, List<Ball> redBall,
			List<Ball> yellowBall, final Table table) {
		if (ball.getX() + Ball.DIAMETER < table.getX()
				|| ball.getX() + Ball.DIAMETER / 2 < table.getX())
			ball.setIsPocketed(true);
		if (ball.getY() + Ball.DIAMETER < table.getY()
				|| ball.getY() + Ball.DIAMETER / 2 < table.getY())
			ball.setIsPocketed(true);
		if (ball.getX() + Ball.DIAMETER > table.getX() + Table.WIDTH
				|| ball.getX() + Ball.DIAMETER / 2 > table.getX() + Table.WIDTH)
			ball.setIsPocketed(true);
		if (ball.getY() + Ball.DIAMETER > table.getY() + Table.HEIGHT
				|| ball.getY() + Ball.DIAMETER / 2 > table.getY()
						+ Table.HEIGHT)
			ball.setIsPocketed(true);
		if (ball.getX() >= table.getX() + Table.WIDTH / 2 - Table.HOLE_DIAMETER
				&& ball.getX() < table.getX() + Table.WIDTH / 2
						+ Table.HOLE_DIAMETER
				&& ((ball.getY() + Ball.DIAMETER / 2 < table.getY()
						+ Table.HOLE_DIAMETER) || (ball.getY() - Ball.DIAMETER
						/ 2 > table.getY() + Table.HEIGHT - Table.HOLE_DIAMETER)))
			ball.setIsPocketed(true);
		if (ball.getIsPocketed()) {

			if (ball.getIdentificativeNumber() == 0)
				ballsOnTheTable.remove(ball);

			if (ball.getIdentificativeNumber() >= 1
					&& ball.getIdentificativeNumber() <= 7) {
				yellowBall.add(ball);
				ballsOnTheTable.remove(ball);
				ballsPocketed.add(ball);
			}
			if (ball.getIdentificativeNumber() >= 9
					&& ball.getIdentificativeNumber() <= 15) {
				redBall.add(ball);
				ballsOnTheTable.remove(ball);
				ballsPocketed.add(ball);
			}
			if (ball.getIdentificativeNumber() == 8) {
				ballsOnTheTable.remove(ball);
				ballsPocketed.add(ball);
			}
		}
	}

	public boolean checkThatTheMouseIntersectWithAWhiteBall(final int xMouse,
			final int yMouse, List<Ball> ballsOnTheTable) {
		for (Ball ball : ballsOnTheTable)
			if (xMouse >= ball.getX() && xMouse <= ball.getX() + Ball.DIAMETER
					&& yMouse >= ball.getY()
					&& yMouse <= ball.getY() + Ball.DIAMETER
					&& ball.getIdentificativeNumber() == 0) {
				return true;
			}
		return false;
	}

	public boolean checkThatThereAreNoOverlappingBalls(
			final List<Ball> ballsOnTheTable) {
		if (!ballsOnTheTable.isEmpty())
			for (Ball firstBall : ballsOnTheTable)
				for (Ball secondBall : ballsOnTheTable)
					if (firstBall.getIdentificativeNumber() != secondBall
							.getIdentificativeNumber())
						if (detectCollision(firstBall, secondBall))
							return true;
		return false;
	}

	public boolean checkThatTheBallDoesNotExceedTheEdgesOfTheTable(
			final int xMouse, final int yMouse, final Table table) {
		if (xMouse <= table.getX() + Table.WIDTH - 1.5 * Ball.DIAMETER
				&& xMouse >= table.getX() + Ball.DIAMETER / 2
				&& yMouse <= table.getY() + Table.HEIGHT - 1.5 * Ball.DIAMETER
				&& yMouse >= table.getY() + Ball.DIAMETER / 2)
			return true;
		return false;
	}

	public boolean everyBallsStopped(final List<Ball> ballsOnTheTable) {
		for (Ball ball : ballsOnTheTable)
			if (ball.getVelocityXAxis() != 0 || ball.getVelocityYAxis() != 0)
				return false;

		return true;
	}

	public boolean everyBallsStoppedExceptWhiteBall(final List<Ball> ballsOnTheTable) {
		for (Ball ball : ballsOnTheTable)
			if ((ball.getVelocityXAxis() != 0 || ball.getVelocityYAxis() != 0)
					&& ball.getIdentificativeNumber() != 0)
				return false;

		return true;
	}
}
