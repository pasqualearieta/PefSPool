package igpe.billiard.core;

public class Ball extends AbstractMovableObject {

	protected static final int DIAMETER = 20;
	private double cX;
	private double cY;
	private int identificativeNumber;
	private boolean isPocketed;
	private double velocityXAxis;
	private double velocityYAxis;

	
	public static int getDiameter() {
		return DIAMETER;
	}



	public Ball(final int x, final int y, final int identificativeNumber) {
		super(x, y);
		this.isPocketed = false;
		this.identificativeNumber = identificativeNumber;
		this.cX = x;
		this.cY = y;
		this.velocityXAxis = 0;
		this.velocityYAxis = 0;
	}

	public Ball(final Ball ball) {
		super(ball.getX(), ball.getY());
		this.isPocketed = ball.getIsPocketed();
		this.identificativeNumber = ball.getIdentificativeNumber();
		this.cX = ball.getX();
		this.cY = ball.getY();
		this.velocityXAxis = ball.getVelocityXAxis();
		this.velocityYAxis = ball.getVelocityYAxis();
	}

	public double getCx() {
		return cX;
	}

	public double getCy() {
		return cY;
	}

	public int getIdentificativeNumber() {
		return this.identificativeNumber;
	}

	public boolean getIsPocketed() {
		return isPocketed;
	}

	public double getVelocityXAxis() {
		return velocityXAxis;
	}

	public double getVelocityYAxis() {
		return velocityYAxis;
	}

	public void setCx(final double cX) {
		this.cX = cX;
	}

	public void setCy(final double cY) {
		this.cY = cY;
	}

	public void setIdentificativeNumber(final int identificativeNumber) {
		this.identificativeNumber = identificativeNumber;
	}

	public void setIsPocketed(final boolean isPocketed) {
		this.isPocketed = isPocketed;
	}

	public void setVelocityXAxis(final double velocityXAxis) {
		this.velocityXAxis = velocityXAxis;
	}

	public void setVelocityYAxis(final double velocityYAxis) {
		this.velocityYAxis = velocityYAxis;

	}

}
