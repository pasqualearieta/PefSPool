package igpe.billiard.core;

public class Player {

	private String name;
	private ColorBall colorBall;
	private boolean turn;
	private boolean win;
	private boolean makeFoul;

	public Player(final String name, final boolean turn) {
		this.name = name;
		this.colorBall = ColorBall.NOTHING;
		this.turn = turn;
		this.win = false;
		this.makeFoul = false;
	}

	public boolean isMakeFoul() {
		return makeFoul;
	}

	public void setMakeFoul(final boolean makeFoul) {
		this.makeFoul = makeFoul;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(final boolean win) {
		this.win = win;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(final boolean turn) {
		this.turn = turn;
	}

	public ColorBall getColorBall() {
		return this.colorBall;
	}

	public void setColorBall(final ColorBall colorBall) {
		this.colorBall = colorBall;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
