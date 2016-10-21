
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;


public class BallInSquare extends JPanel {
	private static final long serialVersionUID = -2410059400222404729L;
	
	private final double DIAMETER = 50;
	private final double SIZE_SQUARE = 120;
	
	private double posXBall = 30;
	private double posYBall = 30;
	
	private Ellipse2D.Double  ball;
	private Rectangle2D.Double square;
	private boolean ballIsSelected = false;

	private double previousX, previousY;


	public BallInSquare() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (ballIsSelected) {
					posXBall += e.getX() - previousX ;
					posYBall += e.getY() - previousY ;
					previousX = e.getX() ;
					previousY = e.getY() ;
					repaint();
				}
			}
		});	
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(500, 400));
		setLayout(null);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (ball.contains(e.getX(), e.getY())){
					ballIsSelected = true;
					previousX = e.getX() ;
					previousY = e.getY() ;
				}
				
			} 
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				ballIsSelected = false;
				repaint();
			}
		});
 
	}


	@Override
	public void paintComponent(Graphics g) {		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		ball = new Ellipse2D.Double(posXBall, posYBall, DIAMETER, DIAMETER);
		square = new Rectangle2D.Double(getWidth()/2.0-SIZE_SQUARE/2.0, getHeight()/2.0-SIZE_SQUARE/2.0, SIZE_SQUARE, SIZE_SQUARE);
		
		g2d.setColor(Color.blue);
		g2d.fill(square);
		
		g2d.setColor(couleurResultante(ball, square));
		
		g2d.fill(ball);
	}
	
	private Color couleurResultante(Shape shape1, Shape shape2){
		Area area1 = new Area(shape1);
		Area area2 = new Area(shape2);
		Area intersection = new Area(area1);
		intersection.intersect(area2);
		area1.subtract(intersection);
		area2.subtract(intersection);
		
		if(area1.isEmpty() || area2.isEmpty()){
			return Color.red;
		}
		else if(!intersection.isEmpty()){
			return Color.yellow;
		}
		else{
			return Color.green;
		}
	}
	
}