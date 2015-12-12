package igpe.billiard.core;

import java.util.ArrayList;
import java.util.List;

public class Table {
	public static final int HEIGHT = 400;
	public static final int WIDTH = 800;
	public static final int HOLE_DIAMETER = 23;

	public static int getHeight() {
		return HEIGHT;
	}

	public static int getWidth() {
		return WIDTH;
	}

	private List<Border> tableBorder = new ArrayList<Border>();
	private int x;
	private int y;

	public Table(final int x, final int y) {
		this.x = x;
		this.y = y;
		setBorderOnTheTable();
	}

	public List<Border> getTableBorder() {
		return tableBorder;
	}

	public final int getX() {
		return x;
	}

	public final int getY() {
		return y;
	}

	private void setBorderOnTheTable() {
		Border firstBorder = new Border(x + HOLE_DIAMETER, x + WIDTH / 2
				- HOLE_DIAMETER, y, y, 1);
		Border secondBorder = new Border(x + WIDTH / 2 + HOLE_DIAMETER, x
				+ WIDTH - HOLE_DIAMETER, y, y, 2);
		Border thirdBorder = new Border(x + WIDTH, x + WIDTH,
				y + HOLE_DIAMETER, y + HEIGHT - HOLE_DIAMETER, 3);
		Border fourthBorder = new Border(x + WIDTH / 2 + HOLE_DIAMETER, x
				+ WIDTH - HOLE_DIAMETER, y + HEIGHT, y + HEIGHT, 4);
		Border fifthBorder = new Border(x + HOLE_DIAMETER, x + WIDTH / 2
				- HOLE_DIAMETER, y + HEIGHT, y + HEIGHT, 5);
		Border sixthBorder = new Border(x, x, y + HOLE_DIAMETER, y + HEIGHT
				- HOLE_DIAMETER, 6);
		tableBorder.add(thirdBorder);
		tableBorder.add(fourthBorder);
		tableBorder.add(fifthBorder);
		tableBorder.add(firstBorder);
		tableBorder.add(secondBorder);
		tableBorder.add(sixthBorder);
	}
}
