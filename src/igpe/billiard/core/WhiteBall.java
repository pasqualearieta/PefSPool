package igpe.billiard.core;

public class WhiteBall extends Ball {
	private Direction direction;

	public WhiteBall(final int x, final int y, final int identificativeNumber) {
		super(x, y, identificativeNumber);
		direction = Direction.STOP;
	}

	public WhiteBall(final Ball ball) {
		super(ball.getX(), ball.getY(), ball.getIdentificativeNumber());
		this.direction = Direction.STOP;
		this.setCx(ball.getX());
		this.setCy(ball.getY());
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(final Direction direction) {
		this.direction = direction;
	}

	private boolean checkThatTheBallDoesNotPositionOnTheCenterHole(
			final int xTable, final int yTable) {
		if (this.x >= xTable + Table.WIDTH / 2 - Table.HOLE_DIAMETER
				&& this.x < xTable + Table.WIDTH / 2 + Table.HOLE_DIAMETER
				&& ((this.y < yTable + 1.5 * Table.HOLE_DIAMETER / 2) || (this.y
						- Ball.DIAMETER / 2 > yTable + Table.HEIGHT - 2
						* Table.HOLE_DIAMETER)))
			return false;
		return true;
	}

	private void checkThatTheWhiteBallDoesNotExceedTheEdgesOfTheTableAndSetNewPositionOfWhiteBall(
			Direction direction, final int xTable, final int yTable) {
		switch (direction) {
		case RIGHT: {
			if (x <= xTable + Table.WIDTH - 1.5 * Ball.DIAMETER) {
				this.x += 2;
				this.setCx(x);
			}
			break;
		}
		case LEFT: {
			if (x >= xTable + Ball.DIAMETER / 2) {
				this.x -= 2;
				this.setCx(x);
			}
			break;
		}
		case UP: {
			if (y >= yTable + Ball.DIAMETER / 2
					&& checkThatTheBallDoesNotPositionOnTheCenterHole(xTable,
							yTable)) {
				this.y -= 2;
				this.setCy(y);
			}
			break;
		}
		case DOWN: {
			if (y <= yTable + Table.HEIGHT - 1.5 * Ball.DIAMETER
					&& checkThatTheBallDoesNotPositionOnTheCenterHole(xTable,
							yTable)) {
				this.y += 2;
				this.setCy(y);
			}
			break;
		}
		default:
			direction = Direction.STOP;
			break;
		}
	}

	public void update(final int xTable, final int yTable) {
		checkThatTheWhiteBallDoesNotExceedTheEdgesOfTheTableAndSetNewPositionOfWhiteBall(
				this.direction, xTable, yTable);
		this.setVelocityXAxis(0);
		this.setVelocityYAxis(0);
	}

	public void updateUsingMouse(final int xMouse, final int yMouse) {
		this.x = xMouse;
		this.y = yMouse;
		this.setCx(xMouse);
		this.setCy(yMouse);
		this.setVelocityXAxis(0);
		this.setVelocityYAxis(0);
	}

	public void automaticUpdate(final int xTable, final int yTable,
			final Direction direction) {
		checkThatTheWhiteBallDoesNotExceedTheEdgesOfTheTableAndSetNewPositionOfWhiteBall(
				direction, xTable, yTable);
		this.setVelocityXAxis(0);
		this.setVelocityYAxis(0);
	}
}
