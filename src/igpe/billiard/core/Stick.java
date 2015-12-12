package igpe.billiard.core;

public class Stick extends AbstractMovableObject {
	public static final int HEIGHT = 400;
	public static final int WIDTH = 40;

	private double angleOfRotation = -1.57;
	private boolean isHit = false;
	private Direction directionOfStick;

	public Stick(final int x, final int y, final Direction directionOfStick) {
		super(x, y);
		this.directionOfStick = directionOfStick;
	}

	public double getAngleOfRotation() {
		return angleOfRotation;
	}

	
	public boolean getIsHit() {
		return isHit;
	}

	public Direction getStickDirection() {
		return directionOfStick;
	}

	public void initializeStickPosition(final int xPos, final int yPos) {
		this.x = xPos;
		this.y = yPos;
	}

	public void setAngleOfRotation(final double angleOfRotation) {
		this.angleOfRotation = angleOfRotation;
	}

	public void setHit(final boolean isHit) {
		this.isHit = isHit;
	}

	public void setStickDirection(final Direction stickDirection) {
		this.directionOfStick = stickDirection;
	}

	public void update(final int xPos, final int yPos) {
		switch (directionOfStick) {
		case RIGHT: {
			this.angleOfRotation -= 0.015;
			break;
		}
		case LEFT: {
			this.angleOfRotation += 0.015;
			break;
		}
		default:
			directionOfStick = Direction.STOP;
			break;
		}
		setPositionOfStick(xPos, yPos);
	}

	public void automaticUpdate(final int xPos, final int yPos, final Direction stickDirection) {
		switch (stickDirection) {
		case RIGHT: {
			this.angleOfRotation -= 0.005;
			break;
		}
		case LEFT: {
			this.angleOfRotation += 0.005;
			break;
		}
		case STOP:
			this.directionOfStick = Direction.STOP;
		default:
		}
		setPositionOfStick(xPos, yPos);
	}

	private void setPositionOfStick(int xPos, int yPos) {
		x = xPos -= ((Ball.getDiameter() / 2) * (Math.cos(angleOfRotation)));
		y = yPos += ((Ball.getDiameter() / 2) * (Math.sin(angleOfRotation)));
	}
}
