/*     */ package br.unioeste.jgoose.e4j.swing;

/*     */
 /*     */ import com.mxgraph.swing.mxGraphComponent;
/*     */ import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
/*     */ import com.mxgraph.util.mxPoint;
/*     */ import com.mxgraph.view.mxGraph;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.dnd.DropTarget;
/*     */ import java.awt.dnd.DropTargetDragEvent;
/*     */ import java.awt.dnd.DropTargetDropEvent;
/*     */ import java.awt.dnd.DropTargetEvent;
/*     */ import java.awt.dnd.DropTargetListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.TooManyListenersException;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComponent;

/*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public class EditorRuler
        /*     */ extends JComponent
        /*     */ implements MouseMotionListener, DropTargetListener /*     */ {

    /*  51 */ public static int ORIENTATION_HORIZONTAL = 0;
    public static int ORIENTATION_VERTICAL = 1;
    /*     */
 /*     */
 /*     */
 /*     */
 /*  56 */    protected static int INCH = 72;
    /*     */
 /*     */
 /*     */
 /*     */
 /*  61 */    protected static int DEFAULT_PAGESCALE = 1;
    /*     */
 /*     */
 /*     */
 /*     */
 /*  66 */    protected static boolean DEFAULT_ISMETRIC = true;
    /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*  72 */    public static final NumberFormat numberFormat = NumberFormat.getInstance();

    /*     */
 /*     */
 /*     */
 /*     */ static /*     */ {
        /*  78 */ numberFormat.setMaximumFractionDigits(2);
        /*     */    }
    /*     */
 /*     */
 /*     */
 /*  83 */    protected Color inactiveBackground = new Color(170, 170, 170);
    /*     */
 /*     */
 /*     */
 /*  87 */    protected int orientation = ORIENTATION_HORIZONTAL;
    /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*  98 */    protected double scale = DEFAULT_PAGESCALE;
    /*     */
 /*     */
 /*     */
 /*     */
 /* 103 */    protected boolean metric = DEFAULT_ISMETRIC;
    /* 104 */    protected Font labelFont = new Font("Tahoma", 0, 9);
    /*     */
 /*     */
 /*     */
 /* 108 */    protected int rulerSize = 16;
    /*     */
 /*     */
 /*     */
 /* 112 */    protected int tickDistance = 30;
    /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /* 120 */    protected Point mouse = new Point();
    /*     */
 /*     */
 /*     */
 /*     */
 /* 125 */    protected transient mxEventSource.mxIEventListener repaintHandler = new mxEventSource.mxIEventListener() /*     */ {
        /*     */ public void invoke(Object source, mxEventObject evt) {
            /* 128 */ EditorRuler.this.repaint();
            /*     */        }
        /*     */    };
    /*     */    private static final long serialVersionUID = -6310912355878668096L;
    /*     */    protected int activeoffset;
    /*     */    protected int activelength;
    /*     */    protected mxGraphComponent graphComponent;
    /*     */    protected double increment;
    /*     */    protected double units;

    /*     */
 /*     */ public EditorRuler(mxGraphComponent graphComponent, int orientation) {
        /* 139 */ this.orientation = orientation;
        /* 140 */ this.graphComponent = graphComponent;
        /* 141 */ updateIncrementAndUnits();
        /*     */
 /* 143 */ graphComponent.getGraph().getView().addListener("scale", this.repaintHandler);
        /*     */
 /* 145 */ graphComponent.getGraph().getView().addListener("translate", this.repaintHandler);
        /*     */
 /* 147 */ graphComponent.getGraph().getView().addListener("scaleAndTranslate", this.repaintHandler);
        /*     */
 /*     */
 /* 150 */ graphComponent.getGraphControl().addMouseMotionListener(this);
        /*     */
 /* 152 */ DropTarget dropTarget = graphComponent.getDropTarget();
        /*     */ try /*     */ {
            /* 155 */ if (dropTarget != null) {
                /* 156 */ dropTarget.addDropTargetListener(this);
                /*     */            }
            /*     */        } /*     */ catch (TooManyListenersException tmle) {
        }
        /*     */
 /*     */
 /* 162 */ setBorder(BorderFactory.createLineBorder(Color.black));
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void setActiveOffset(int offset) /*     */ {
        /* 171 */ this.activeoffset = ((int) (offset * this.scale));
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void setActiveLength(int length) /*     */ {
        /* 180 */ this.activelength = ((int) (length * this.scale));
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public boolean isMetric() /*     */ {
        /* 189 */ return this.metric;
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void setMetric(boolean isMetric) /*     */ {
        /* 198 */ this.metric = isMetric;
        /* 199 */ updateIncrementAndUnits();
        /* 200 */ repaint();
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public int getRulerSize() /*     */ {
        /* 209 */ return this.rulerSize;
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void setRulerSize(int rulerSize) /*     */ {
        /* 218 */ this.rulerSize = rulerSize;
        /*     */    }

    /*     */
 /*     */ public void setTickDistance(int tickDistance) {
        /* 222 */ this.tickDistance = tickDistance;
        /*     */    }

    /*     */
 /*     */ public int getTickDistance() {
        /* 226 */ return this.tickDistance;
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public Dimension getPreferredSize() /*     */ {
        /* 237 */ Dimension dim = this.graphComponent.getGraphControl().getPreferredSize();
        /*     */
 /* 239 */ if (this.orientation == ORIENTATION_VERTICAL) {
            /* 240 */ dim.width = this.rulerSize;
            /*     */        } else {
            /* 242 */ dim.height = this.rulerSize;
            /*     */        }
        /*     */
 /* 245 */ return dim;
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void dragOver(DropTargetDragEvent arg0) /*     */ {
        /* 272 */ updateMousePosition(arg0.getLocation());
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void mouseMoved(MouseEvent e) /*     */ {
        /* 298 */ updateMousePosition(e.getPoint());
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void mouseDragged(MouseEvent e) /*     */ {
        /* 306 */ updateMousePosition(e.getPoint());
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */ protected void updateMousePosition(Point pt) /*     */ {
        /* 313 */ Point old = this.mouse;
        /* 314 */ this.mouse = pt;
        /* 315 */ repaint(old.x, old.y);
        /* 316 */ repaint(this.mouse.x, this.mouse.y);
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */
 /*     */ protected void updateIncrementAndUnits() /*     */ {
        /* 324 */ double graphScale = this.graphComponent.getGraph().getView().getScale();
        /*     */
 /* 326 */ if (this.metric) {
            /* 327 */ this.units = (INCH / 2.54D);
            /* 328 */ this.units *= this.graphComponent.getPageScale() * graphScale;
            /* 329 */ this.increment = this.units;
            /*     */        } else {
            /* 331 */ this.units = INCH;
            /* 332 */ this.units *= this.graphComponent.getPageScale() * graphScale;
            /* 333 */ this.increment = (this.units / 2.0D);
            /*     */        }
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void repaint(int x, int y) /*     */ {
        /* 345 */ if (this.orientation == ORIENTATION_VERTICAL) {
            /* 346 */ repaint(0, y, this.rulerSize, 1);
            /*     */        } else {
            /* 348 */ repaint(x, 0, 1, this.rulerSize);
            /*     */        }
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */
 /*     */ public void paintComponent(Graphics g) /*     */ {
        /* 359 */ mxGraph graph = this.graphComponent.getGraph();
        /* 360 */ Rectangle clip = g.getClipBounds();
        /* 361 */ updateIncrementAndUnits();
        /*     */
 /*     */
 /* 364 */ if ((this.activelength > 0) && (this.inactiveBackground != null)) {
            /* 365 */ g.setColor(this.inactiveBackground);
            /*     */        } else {
            /* 367 */ g.setColor(getBackground());
            /*     */        }
        /*     */
 /* 370 */ g.fillRect(clip.x, clip.y, clip.width, clip.height);
        /*     */
 /*     */
 /* 373 */ g.setColor(getBackground());
        /* 374 */ Point2D p = new Point2D.Double(this.activeoffset, this.activelength);
        /*     */
 /* 376 */ if (this.orientation == ORIENTATION_HORIZONTAL) {
            /* 377 */ g.fillRect((int) p.getX(), clip.y, (int) p.getY(), clip.height);
            /*     */        } else {
            /* 379 */ g.fillRect(clip.x, (int) p.getX(), clip.width, (int) p.getY());
            /*     */        }
        /*     */
 /* 382 */ double left = clip.getX();
        /* 383 */ double top = clip.getY();
        /* 384 */ double right = left + clip.getWidth();
        /* 385 */ double bottom = top + clip.getHeight();
        /*     */
 /*     */
 /* 388 */ mxPoint trans = graph.getView().getTranslate();
        /* 389 */ double scale = graph.getView().getScale();
        /* 390 */ double tx = trans.getX() * scale;
        /* 391 */ double ty = trans.getY() * scale;
        /*     */
 /*     */
 /* 394 */ double stepping = this.increment;
        /*     */
 /* 396 */ if (stepping < this.tickDistance) /*     */ {
            /* 398 */ int count = (int) Math.round(Math.ceil(this.tickDistance / stepping) / 2.0D) * 2;
            /* 399 */ stepping = count * stepping;
            /*     */        }
        /*     */
 /*     */
 /*     */
 /* 404 */ ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        /*     */
 /* 406 */ g.setFont(this.labelFont);
        /* 407 */ g.setColor(Color.black);
        /*     */
 /* 409 */ int smallTick = this.rulerSize - this.rulerSize / 3;
        /* 410 */ int middleTick = this.rulerSize / 2;
        /*     */
 /*     */
 /* 413 */ if (this.orientation == ORIENTATION_HORIZONTAL) {
            /* 414 */ double xs = Math.floor((left - tx) / stepping) * stepping + tx;
            /* 415 */ double xe = Math.ceil(right / stepping) * stepping;
            /* 416 */ xe += (int) Math.ceil(stepping);
            /*     */
 /* 418 */ for (double x = xs; x <= xe; x += stepping) /*     */ {
                /*     */
 /*     */
 /* 422 */ double xx = Math.round((x - tx) / stepping) * stepping + tx;
                /*     */
 /* 424 */ int ix = (int) Math.round(xx);
                /* 425 */ g.drawLine(ix, this.rulerSize, ix, 0);
                /*     */
 /* 427 */ String text = format((x - tx) / this.increment);
                /* 428 */ g.drawString(text, ix + 2, this.labelFont.getSize());
                /*     */
 /* 430 */ ix += (int) Math.round(stepping / 4.0D);
                /* 431 */ g.drawLine(ix, this.rulerSize, ix, smallTick);
                /*     */
 /* 433 */ ix += (int) Math.round(stepping / 4.0D);
                /* 434 */ g.drawLine(ix, this.rulerSize, ix, middleTick);
                /*     */
 /* 436 */ ix += (int) Math.round(stepping / 4.0D);
                /* 437 */ g.drawLine(ix, this.rulerSize, ix, smallTick);
                /*     */            }
            /*     */        } else {
            /* 440 */ double ys = Math.floor((top - ty) / stepping) * stepping + ty;
            /* 441 */ double ye = Math.ceil(bottom / stepping) * stepping;
            /* 442 */ ye += (int) Math.ceil(stepping);
            /*     */
 /* 444 */ for (double y = ys; y <= ye; y += stepping) /*     */ {
                /*     */
 /*     */
 /* 448 */ y = Math.round((y - ty) / stepping) * stepping + ty;
                /*     */
 /* 450 */ int iy = (int) Math.round(y);
                /* 451 */ g.drawLine(this.rulerSize, iy, 0, iy);
                /*     */
 /* 453 */ String text = format((y - ty) / this.increment);
                /*     */
 /*     */
 /* 456 */ AffineTransform at = ((Graphics2D) g).getTransform();
                /* 457 */ ((Graphics2D) g).rotate(-1.5707963267948966D, 0.0D, iy);
                /* 458 */ g.drawString(text, 1, iy + this.labelFont.getSize());
                /* 459 */ ((Graphics2D) g).setTransform(at);
                /*     */
 /* 461 */ iy += (int) Math.round(stepping / 4.0D);
                /* 462 */ g.drawLine(this.rulerSize, iy, smallTick, iy);
                /*     */
 /* 464 */ iy += (int) Math.round(stepping / 4.0D);
                /* 465 */ g.drawLine(this.rulerSize, iy, middleTick, iy);
                /*     */
 /* 467 */ iy += (int) Math.round(stepping / 4.0D);
                /* 468 */ g.drawLine(this.rulerSize, iy, smallTick, iy);
                /*     */            }
            /*     */        }
        /*     */
 /*     */
 /* 473 */ g.setColor(Color.green);
        /*     */
 /* 475 */ if (this.orientation == ORIENTATION_HORIZONTAL) {
            /* 476 */ g.drawLine(this.mouse.x, this.rulerSize, this.mouse.x, 0);
            /*     */        } else {
            /* 478 */ g.drawLine(this.rulerSize, this.mouse.y, 0, this.mouse.y);
            /*     */        }
        /*     */    }

    /*     */
 /*     */
 /*     */
 /*     */ private String format(double value) /*     */ {
        /* 486 */ String text = numberFormat.format(value);
        /*     */
 /* 488 */ if (text.equals("-0")) {
            /* 489 */ text = "0";
            /*     */        }
        /*     */
 /* 492 */ return text;
        /*     */    }

    /*     */
 /*     */ public void dragEnter(DropTargetDragEvent arg0) {
    }

    /*     */
 /*     */ public void dragExit(DropTargetEvent arg0) {
    }

    /*     */
 /*     */ public void drop(DropTargetDropEvent arg0) {
    }

    /*     */
 /*     */ public void dropActionChanged(DropTargetDragEvent arg0) {
    }
    /*     */ }
