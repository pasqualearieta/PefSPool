package igpe.billiard.core;

public class AbstractMovableObject implements MovableObject {

	public static final int HEIGHT = 10;
	public static final int WIDTH = 10;
	protected int x;
	protected int y;

	AbstractMovableObject(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void setX(final int x) {
		this.x = x;
	}

	@Override
	public void setY(final int y) {
		this.y = y;
	}

}
