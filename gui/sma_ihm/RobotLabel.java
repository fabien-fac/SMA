/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma_ihm;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

import enums.Couleurs;

/**
 *
 * @author mathieu
 */
@SuppressWarnings("serial")
public class RobotLabel extends JLabel{
	private static final String stringRobot = "•";
	private static float tailleCarac;
    public RobotLabel(Dimension dimensionPanel) {
    	Double width = dimensionPanel.getWidth() / 3;
    	tailleCarac = width.floatValue();
        setVisible(false);
    }
    
    public void setColorLabel(String color) {
		setText(stringRobot);        
        setFont (getFont().deriveFont(tailleCarac));
        if(color.equals(Couleurs.ROUGE.toString())) {
                setForeground(Color.RED);
        } else if(color.equals(Couleurs.BLEU.toString())) {
                setForeground(Color.BLUE);
        } else if(color.equals(Couleurs.VERT.toString())) {
                setForeground(Color.GREEN);
        }
        setVisible(true);
    }
    
    public void viderLabel () {
    	setText(" ");
    	setForeground(Color.DARK_GRAY);
    }
}
