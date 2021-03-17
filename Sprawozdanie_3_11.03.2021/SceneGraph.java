import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;

/**
 * A panel that displays a two-dimensional animation that is constructed
 * using a scene graph to implement hierarchical modeling.  There is a
 * checkbox that turns the animation on and off.
 */
public class SceneGraph extends JPanel {

	public static void main(String[] args) {
		JFrame window = new JFrame("Scene Graph 2D");
		window.setContentPane( new SceneGraph() );
		window.pack();
		window.setLocation(100,60);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	//-------------------------- Create the world and implement the animation --------------

	private final static int WIDTH = 800;   // The preferred size for the drawing area.
	private final static int HEIGHT = 600;

	private final static double X_LEFT = -4;    // The xy limits for the coordinate system.
	private final static double X_RIGHT = 4;
	private final static double Y_BOTTOM = -3;
	private final static double Y_TOP = 3;

	private final static Color BACKGROUND = Color.WHITE; // Initial background color for drawing.

	private float pixelSize;  // The size of a pixel in drawing coordinates.

	private int frameNumber = 0;  // Current frame number, goes up by one in each frame.

	private CompoundObject world; // SceneGraphNode representing the entire scene.

	// TODO: Define global variables to represent animated objects in the scene.
//	private TransformedObject rotatingRect;  // (DELETE THIS EXAMPLE)
	private TransformedObject rotatingPolygon1_1;
	private TransformedObject rotatingPolygon1_2;
	private TransformedObject rectangle1;
	private  TransformedObject triangle1;

	private TransformedObject rotatingPolygon2_1;
	private TransformedObject rotatingPolygon2_2;
	private TransformedObject rectangle2;
	private  TransformedObject triangle2;

	private TransformedObject rotatingPolygon3_1;
	private TransformedObject rotatingPolygon3_2;
	private TransformedObject rectangle3;
	private  TransformedObject triangle3;

	/**
	 *  Builds the data structure that represents the entire picture. 
	 */
	private void createWorld() {

		world = new CompoundObject();  // Root node for the scene graph.

		// TODO: Create objects and add them to the scene graph.

		//figura1
		rotatingPolygon1_1 = new TransformedObject(filledPolygon);   // (DELETE THIS EXAMPLE)
		rotatingPolygon1_1.setScale(0.03,0.03).setColor(Color.pink);
		rotatingPolygon1_1.setTranslation(-1,-0.05);
		world.add(rotatingPolygon1_1);

		rotatingPolygon1_2 = new TransformedObject(filledPolygon);   // (DELETE THIS EXAMPLE)
		rotatingPolygon1_2.setScale(0.03,0.03).setColor(Color.pink);
		rotatingPolygon1_2.setTranslation(1.7,-0.75);
		world.add(rotatingPolygon1_2);

		rectangle1=new TransformedObject(filledRect);
		rectangle1.setRotation(-15);
		rectangle1.setTranslation(0.35,-0.40);
		rectangle1.setScale(3,0.2).setColor(Color.RED);
		world.add(rectangle1);

		triangle1 = new TransformedObject(filledTriangle);
		triangle1.setScale(0.75,2.5).setColor(Color.blue);
		triangle1.setTranslation(0.35,-2.9);
		world.add(triangle1);

		//figura2

		rotatingPolygon2_1 = new TransformedObject(filledPolygon);   // (DELETE THIS EXAMPLE)
		rotatingPolygon2_1.setScale(0.015,0.015).setColor(Color.yellow);
		rotatingPolygon2_1.setTranslation(1,1.975);
		world.add(rotatingPolygon2_1);

		rotatingPolygon2_2 = new TransformedObject(filledPolygon);   // (DELETE THIS EXAMPLE)
		rotatingPolygon2_2.setScale(0.015,0.015).setColor(Color.YELLOW);
		rotatingPolygon2_2.setTranslation(2.4,1.60);
		world.add(rotatingPolygon2_2);

		rectangle2=new TransformedObject(filledRect);
		rectangle2.setRotation(-15);
		rectangle2.setTranslation(1.7,1.8);
		rectangle2.setScale(1.5,0.1).setColor(Color.RED);
		world.add(rectangle2);

		triangle2 = new TransformedObject(filledTriangle);
		triangle2.setScale(0.375,1.25).setColor(Color.green);
		triangle2.setTranslation(1.7,0.64);
		world.add(triangle2);

		//figura3

		rotatingPolygon3_1 = new TransformedObject(filledPolygon);   // (DELETE THIS EXAMPLE)
		rotatingPolygon3_1.setScale(0.0225,0.0225).setColor(Color.PINK);
		rotatingPolygon3_1.setTranslation(-3.3,2.0);
		world.add(rotatingPolygon3_1);

		rotatingPolygon3_2 = new TransformedObject(filledPolygon);   // (DELETE THIS EXAMPLE)
		rotatingPolygon3_2.setScale(0.0225,0.0225).setColor(Color.PINK);
		rotatingPolygon3_2.setTranslation(-1.5,1.55);
		world.add(rotatingPolygon3_2);

		rectangle3=new TransformedObject(filledRect);
		rectangle3.setRotation(-15);
		rectangle3.setTranslation(-2.4,1.8);
		rectangle3.setScale(1.8,0.15).setColor(Color.RED);
		world.add(rectangle3);

		triangle3 = new TransformedObject(filledTriangle);
		triangle3.setScale(0.525,1.875).setColor(Color.MAGENTA);
		triangle3.setTranslation(-2.4,0.0);
		world.add(triangle3);


	} // end createWorld()


	/**
	 * This method is called just before each frame is drawn.  It updates the modeling
	 * transformations of the objects in the scene that are animated.
	 */
	public void updateFrame() {
		frameNumber++;

		// TODO: Update state in preparation for drawing the next frame.

		rotatingPolygon1_1.setRotation(frameNumber*0.75);
		rotatingPolygon1_2.setRotation(frameNumber*0.75);
		rotatingPolygon2_1.setRotation(frameNumber*0.75);
		rotatingPolygon2_2.setRotation(frameNumber*0.75);
		rotatingPolygon3_1.setRotation(frameNumber*0.75);
		rotatingPolygon3_2.setRotation(frameNumber*0.75);

	}



	//------------------- A Simple Scene Object-Oriented Scene Graph API ----------------

	private static abstract class SceneGraphNode {
		Color color;  // If not null, the default color for this node and its children.
		              // If null, the default color is inherited.
		SceneGraphNode setColor(Color c) {
			this.color = c;
			return this;
		}
		final void draw(Graphics2D g) {
			Color saveColor = null;
			if (color != null) {
				saveColor = g.getColor();
				g.setColor(color);
			}
			doDraw(g);
			if (saveColor != null) {
				g.setColor(saveColor);
			}
		}
		abstract void doDraw(Graphics2D g);
	}
	
	/**
	 *  Defines a subclass, CompoundObject, of SceneGraphNode to represent
	 *  an object that is made up of sub-objects.  Initially, there are no
	 *  sub-objects.  Objects are added with the add() method.
	 */
	private static class CompoundObject extends SceneGraphNode {
		ArrayList<SceneGraphNode> subobjects = new ArrayList<SceneGraphNode>();
		CompoundObject add(SceneGraphNode node) {
			subobjects.add(node);
			return this;
		}
		void doDraw(Graphics2D g) {
			for (SceneGraphNode node : subobjects)
				node.draw(g);
		}
	}

	/**
	 *  TransformedObject is a subclass of SceneGraphNode that
	 *  represents an object along with a modeling transformation to
	 *  be applied to that object.  The object must be specified in
	 *  the constructor.  The transformation is specified by calling
	 *  the setScale(), setRotate() and setTranslate() methods. Note that
	 *  each of these methods returns a reference to the TransformedObject
	 *  as its return value, to allow for chaining of method calls.
	 *  The modeling transformations are always applied to the object
	 *  in the order scale, then rotate, then translate.
	 */
	private static class TransformedObject extends SceneGraphNode {
		SceneGraphNode object;
		double rotationInDegrees = 0;
		double scaleX = 1, scaleY = 1;
		double translateX = 0, translateY = 0;
		TransformedObject(SceneGraphNode object) {
			this.object = object;
		}
		TransformedObject setRotation(double degrees) {
			rotationInDegrees = degrees;
			return this;
		}
		TransformedObject setTranslation(double dx, double dy) {
			translateX = dx;
			translateY = dy;
			return this;
		}
		TransformedObject setScale(double sx, double sy) {
			scaleX = sx;
			scaleY = sy;
			return this;
		}
		void doDraw(Graphics2D g) {
			AffineTransform savedTransform = g.getTransform();
			if (translateX != 0 || translateY != 0)
				g.translate(translateX,translateY);
			if (rotationInDegrees != 0)
				g.rotate( rotationInDegrees/180.0 * Math.PI);
			if (scaleX != 1 || scaleY != 1)
				g.scale(scaleX,scaleY);
			object.draw(g);
			g.setTransform(savedTransform);
		}
	}
	
	       // Create some basic objects as custom SceneGraphNodes.

	private static SceneGraphNode line = new SceneGraphNode() { 
		void doDraw(Graphics2D g) {  g.draw( new Line2D.Double( -0.5,0, 0.5,0) ); }
	};

	private static SceneGraphNode rect = new SceneGraphNode() {
		void doDraw(Graphics2D g) {  g.draw(new Rectangle2D.Double(-0.5,-0.5,1,1)); }
	};

	private static SceneGraphNode filledRect = new SceneGraphNode() {
		void doDraw(Graphics2D g) {  g.fill(new Rectangle2D.Double(-0.5,-0.5,1,1)); }
	};

	private static SceneGraphNode circle = new SceneGraphNode() {
		void doDraw(Graphics2D g) {  g.draw(new Ellipse2D.Double(-0.5,-0.5,1,1)); }
	};

	private static SceneGraphNode filledCircle = new SceneGraphNode() {
		void doDraw(Graphics2D g) {  g.fill(new Ellipse2D.Double(-0.5,-0.5,1,1)); }
	};

	private static SceneGraphNode filledTriangle = new SceneGraphNode() {
		void doDraw(Graphics2D g) {  // width = 1, height = 1, center of base is at (0,0);
			Path2D path = new Path2D.Double();
			path.moveTo(-0.5,0);
			path.lineTo(0.5,0);
			path.lineTo(0,1);
			path.closePath();
			g.fill(path);
		}
	};
	private static SceneGraphNode filledPolygon = new SceneGraphNode() {
		void doDraw(Graphics2D g) {  // width = 1, height = 1, center of base is at (0,0);
			int[] x = new int[18];
			int[] y = new int[18];
			for(int i=0;i<18;i++)
			{
				x[i]= (int)(25 * Math.cos(i * 2 * Math.PI / 18));
				y[i]= (int)(25 * Math.sin(i * 2 * Math.PI / 18));
			}
			Path2D path = new Path2D.Double();
			path.moveTo(x[0],y[0]);
			for (int i=1;i<18;i++)
			{
				path.lineTo(x[i],y[i]);
			}
			path.closePath();
			g.fill(path);
		}
	};



	//--------------------------------- Implementation ------------------------------------

	private JPanel display;  // The JPanel in which the scene is drawn.

	/**
	 * Constructor creates the scene graph data structure that represents the
	 * scene that is to be drawn in this panel, by calling createWorld().
	 * It also sets the preferred size of the panel to the constants WIDTH and HEIGHT.
	 * And it creates a timer to drive the animation.
	 */
	public SceneGraph() {
		display = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D)g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				applyLimits(g2, X_LEFT, X_RIGHT, Y_TOP, Y_BOTTOM, false);
				g2.setStroke( new BasicStroke(pixelSize) ); // set default line width to one pixel.
				world.draw(g2);
			}
		};
		display.setPreferredSize( new Dimension(WIDTH,HEIGHT));
		display.setBackground( BACKGROUND );
		final Timer timer = new Timer(17,new ActionListener() { // about 60 frames per second
			public void actionPerformed(ActionEvent evt) {
				updateFrame();
				repaint();
			}
		});
		final JCheckBox animationCheck = new JCheckBox("Run Animation");
		animationCheck.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (animationCheck.isSelected()) {
					if ( ! timer.isRunning() )
						timer.start();
				}
				else {
					if ( timer.isRunning() )
						timer.stop();
				}
			}
		});
		JPanel top = new JPanel();
		top.add(animationCheck);
		setLayout(new BorderLayout(5,5));
		setBackground(Color.DARK_GRAY);
		setBorder( BorderFactory.createLineBorder(Color.DARK_GRAY,4) );
		add(top,BorderLayout.NORTH);
		add(display,BorderLayout.CENTER);
		createWorld();
	}



	/**
	 * Applies a coordinate transform to a Graphics2D graphics context.  The upper left corner of 
	 * the viewport where the graphics context draws is assumed to be (0,0).  The coordinate
	 * transform will make a requested rectangle visible in the drawing area.  The requested
	 * limits might be adjusted to preserve the aspect ratio.  (This method sets the global variable 
	 * pixelSize to be equal to the size of one pixel in the transformed coordinate system.)
	 * @param g2 The drawing context whose transform will be set.
	 * @param xleft requested x-value at left of drawing area.
	 * @param xright requested x-value at right of drawing area.
	 * @param ytop requested y-value at top of drawing area.
	 * @param ybottom requested y-value at bottom of drawing area; can be less than ytop, which will
	 *     reverse the orientation of the y-axis to make the positive direction point upwards.
	 * @param preserveAspect if preserveAspect is false, then the requested rectangle will exactly fill
	 * the viewport; if it is true, then the limits will be expanded in one direction, horizontally or
	 * vertically, to make the aspect ratio of the displayed rectangle match the aspect ratio of the
	 * viewport.  Note that when preserveAspect is false, the units of measure in the horizontal and
	 * vertical directions will be different.
	 */
	private void applyLimits(Graphics2D g2, double xleft, double xright, 
			double ytop, double ybottom, boolean preserveAspect) {
		int width = display.getWidth();   // The width of the drawing area, in pixels.
		int height = display.getHeight(); // The height of the drawing area, in pixels.
		if (preserveAspect) {
			// Adjust the limits to match the aspect ratio of the drawing area.
			double displayAspect = Math.abs((double)height / width);
			double requestedAspect = Math.abs(( ybottom-ytop ) / ( xright-xleft ));
			if (displayAspect > requestedAspect) {
				double excess = (ybottom-ytop) * (displayAspect/requestedAspect - 1);
				ybottom += excess/2;
				ytop -= excess/2;
			}
			else if (displayAspect < requestedAspect) {
				double excess = (xright-xleft) * (requestedAspect/displayAspect - 1);
				xright += excess/2;
				xleft -= excess/2;
			}
		}
		double pixelWidth = Math.abs(( xright - xleft ) / width);
		double pixelHeight = Math.abs(( ybottom - ytop ) / height);
		pixelSize = (float)Math.min(pixelWidth,pixelHeight);
		g2.scale( width / (xright-xleft), height / (ybottom-ytop) );
		g2.translate( -xleft, -ytop );
	}

}