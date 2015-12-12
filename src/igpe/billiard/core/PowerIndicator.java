package igpe.billiard.core;

public class PowerIndicator {
	protected static final int HEIGHT = 410;
	protected static final int WIDTH = 65;
	private static final double MAX_POWER = 24;
	private static final double MEDIUM_POWER = 14;
	private static final double MIN_POWER = 0.5;

	public static int getHeight() {
		return HEIGHT;
	}

	public static int getWidth() {
		return WIDTH;
	}

	
	private double power = 0.0;
	private DirectionOfPower directionOfThePower;
	private ShotPower shotPower;
	private int x;
	private int y;

	public PowerIndicator(final int x, final int y, final DirectionOfPower powerType) {
		this.x = x;
		this.y = y;
		this.power = 0;
		this.shotPower = ShotPower.WEAK;
		this.directionOfThePower = powerType;
		
	}

	

	public ShotPower getShotType() {
		return shotPower;
	}

	public double getPower() {
		return power;
	}


	public void setPower(final double power)
	{
		this.power=power;
	}
	public DirectionOfPower getDirectionOfThePower() {
		return directionOfThePower;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setPowerType(final DirectionOfPower powerType) {
		this.directionOfThePower = powerType;
	}

	private void setIncrementOfThePower() {
		if (this.power < MAX_POWER)
			this.power += 0.5;
			}

	private void setDecrementeOfThePower() {
		if (this.power >= MIN_POWER)
			this.power -= 0.5;
		
	}

	public void update() {
		switch (directionOfThePower) {
		case INCREMENT: {
			setIncrementOfThePower();
			directionOfThePower = DirectionOfPower.NOTHING;
			break;
		}
		case DECREMENT: {
			setDecrementeOfThePower();
			directionOfThePower = DirectionOfPower.NOTHING;
			break;
		}
		default: {
			directionOfThePower = DirectionOfPower.NOTHING;
			break;
		}
		}
		if (power >= MIN_POWER && power <= MEDIUM_POWER / 2)
			shotPower = ShotPower.WEAK;
		if (power >= MEDIUM_POWER / 2 && power <= MEDIUM_POWER)
			shotPower = ShotPower.MEDIUM;
		if (power > MEDIUM_POWER && power <= MAX_POWER)
			shotPower = ShotPower.STRONG;
	}

	public void autoUpdate(final double AutoPower) {
		if (AutoPower > power) {
			setIncrementOfThePower();
		}
		if (AutoPower < power) {
			setDecrementeOfThePower();
		}
		if (AutoPower == power)
			return;
	}
}
