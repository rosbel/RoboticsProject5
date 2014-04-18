/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rosbelsanroman
 */

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Rectangle;

public class MainWindow extends javax.swing.JFrame {

	JPanel panel;
	JLabel label;
	int numLeftClicks = 0;
	int numRightClicks = 0;
	Point start = new Point(0,0), end = new Point(0,0);
	Rectangle[] rectangles = new Rectangle[3];
    /**
     * Creates new form MainWindow
     */
    
	public MainWindow() {
        initComponents();
        
        //this.setTitle("Cell Decomposition");
    }
	
	public void drawCells(){
		Graphics2D g = (Graphics2D) canvasPanel.getGraphics();
		g.setColor(Color.LIGHT_GRAY);
		for(Rectangle rectangle : rectangles)
		{
			//for every rectangle, draw the cells it composes
			//need to calculate intersections "rectangle.intersects..." will be useful here
				//g.drawLine(x1,y1,x2,y2);
		}
	}
	
	public void drawPath(){
		//actually calculate the best path to end point here.
	}
	
    private class mouseEvent 
	implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent evt) {
			int mouseX = evt.getX();
		    int mouseY = evt.getY();
		    int button = evt.getButton();
		    
		    Graphics2D g = (Graphics2D) canvasPanel.getGraphics();
		    int r = 10;
		    
		    //Add block if left click
		    if (button == 1)
		    {
		    	System.out.println("Left Click Detected!");
		    	switch (numLeftClicks) {
		            case 0:		 
			    		//draw 200x200 square
			    		rectangles[0] = new Rectangle(mouseX, mouseY, 200, 200);
			    		if (rectangles[0].contains(start) || rectangles[0].contains(end)){
			    			numLeftClicks--;
			    		}
			    		else{
				    		g.setColor(Color.GRAY);
				    		g.fillRect(mouseX, mouseY, 200, 200);
			    		}
			    		break;
		            case 1:
			    		//draw 150x150 square
			    		rectangles[1] = new Rectangle(mouseX, mouseY, 150, 150);
			    		if (rectangles[1].contains(start) || rectangles[1].contains(end)){
			    			numLeftClicks--;
			    		}
			    		else{
				    		g.setColor(Color.GRAY);
				    		g.fillRect(mouseX, mouseY, 150, 150);
			    		}
			    		break;
		            case 2:
			    		//draw 100x100 square
			    		rectangles[2] = new Rectangle(mouseX, mouseY, 100, 100);
			    		if (rectangles[2].contains(start) || rectangles[2].contains(end)){
			    			numLeftClicks--;
			    		}
			    		else{
				    		g.setColor(Color.GRAY);
				    		g.fillRect(mouseX, mouseY, 100, 100);
			    		}
			    		
			    		//after rectangles are drawn, need to draw cells on screen
			    		drawCells();
			    		break;
		    	}
		    	numLeftClicks++;
		    }
		    
		    //Add start/end point if right click
		    else if (button == 3) {
		    	System.out.println("Right Click Detected!");
		    	if (numLeftClicks>=3){
			    	switch (numRightClicks) {
			            case 0:
				    		//draw start point
			            	start.setLocation(mouseX,mouseY);
			            	if ( rectangles[2].contains(start) || rectangles[1].contains(start) || rectangles[0].contains(start)){
			            		numRightClicks--;
			            	}
			            	else{
					    		g.setColor(Color.GREEN);
					    		g.drawOval(mouseX,mouseY,r,r);
					    		g.fillOval(mouseX,mouseY,r,r);					    		
			            	}
				    		break;
			            case 1:
				    		//draw end point
			            	end.setLocation(mouseX,mouseY);
			            	if (rectangles[2].contains(end) || rectangles[1].contains(end) || rectangles[0].contains(end)){
			            		numRightClicks--;
			            	}
			            	else{
					    		g.setColor(Color.RED);
					    		g.drawOval(mouseX,mouseY,r,r);
					    		g.fillOval(mouseX,mouseY,r,r);					    		
			            	}
				    		break;
			    	}
			    	numRightClicks++;	
		    	}
		    }
		    
			if(numRightClicks >= 2 && numLeftClicks >= 3){
				drawPath();
			}
		}
		
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
	
	}
	
                           

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel canvasPanel;
    private mouseEvent mouseListener;
    // End of variables declaration  
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        canvasPanel = new javax.swing.JPanel();
        canvasPanel.setBackground(Color.WHITE);
        mouseListener = new mouseEvent();
        canvasPanel.addMouseListener(mouseListener);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aggie Robotics Project 5");
        setPreferredSize(new java.awt.Dimension(500, 500));
        setResizable(false);
        setSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout canvasPanelLayout = new javax.swing.GroupLayout(canvasPanel);
        canvasPanel.setLayout(canvasPanelLayout);
        canvasPanelLayout.setHorizontalGroup(
            canvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        canvasPanelLayout.setVerticalGroup(
            canvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(canvasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(canvasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold> 
}
