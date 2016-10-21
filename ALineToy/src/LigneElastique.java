import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class LigneElastique  extends JPanel {
	private static final long serialVersionUID = 1L;
	private int x1,y1, x2, y2;
	private Line2D.Double line;
	private boolean origin = false;

	public LigneElastique() {
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (origin){
					x2 = e.getX();
					y2 = e.getY();
					repaint();
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				x1= e.getX();
				y1 = e.getY();
				x2 = e.getX();
				y2 = e.getY();
				origin = true;
				repaint();
			}
		});
		setBackground(Color.black);
		setPreferredSize(new Dimension(600, 300));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		if(origin){
			line = new Line2D.Double(x1, y1, x2, y2);
			g2d.setColor(Color.red);		
			g2d.draw(line);
		}
	}
}

