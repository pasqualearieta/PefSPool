package igpe.billiard.gui;

import igpe.billiard.core.Ball;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

public class StickPaint {

	private Graphics graphic;
	private AffineTransform transformation;
	public void paint(Graphics g, double angleOfRotation, int xStickPosition, int yStickPosition) {
		this.graphic = g;
		((Graphics2D) graphic).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		transformation = new AffineTransform();
		transformation.rotate(-angleOfRotation, xStickPosition, yStickPosition);
		((Graphics2D) graphic).transform(transformation);

		this.graphic.drawImage(ImageProvider.getStick(), (xStickPosition) - Ball.getDiameter() / 2
				- 2, (yStickPosition) + Ball.getDiameter() / 2, null);
	}
}