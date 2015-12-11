package igpe.billiard.core;

public class Border {

	private int identificativeNumberOfBorder;
	private int startX;
	private int endX;
	private int startY;
	private int endY;

	public Border(final int startX, final int endX, final int startY, final int endY,
			final int identificativeNumber) {

		this.startX = startX;
		this.endX = endX;
		this.startY = startY;
		this.endY = endY;
		this.identificativeNumberOfBorder = identificativeNumber;
	}

	public int getIdentificativeNumber() {
		return identificativeNumberOfBorder;
	}

	public int getStartX() {
		return startX;
	}

	public int getEndX() {
		return endX;
	}

	public int getStartY() {
		return startY;
	}

	public int getEndY() {
		return endY;
	}

}
