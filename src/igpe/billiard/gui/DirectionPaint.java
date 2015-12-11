package igpe.billiard.gui;

import igpe.billiard.core.Ball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.concurrent.CopyOnWriteArrayList;

public class DirectionPaint {

	private Graphics graphic;
	private CopyOnWriteArrayList<Point> points;

	public void paintDirectionOfStick(final Graphics currentGraphic,final int xStickPosition,final int yStickPosition) {
		
		points = new CopyOnWriteArrayList<>();

		this.graphic = currentGraphic;
		
		((Graphics2D) graphic).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		for (int i = yStickPosition - 15; i >= -100; i -= 10)
			points.add(new Point(xStickPosition, i));

		for (Point point : points) {
			graphic.setColor(Color.WHITE);
			graphic.fillRect(point.x + Ball.getDiameter() / 2 - 2, point.y, 3, 3);
		}

	}
}
