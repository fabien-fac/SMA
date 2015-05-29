/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma_ihm;

import java.awt.Color;

import javax.swing.JLabel;

import enums.Couleurs;

/**
 *
 * @author mathieu
 */
@SuppressWarnings("serial")
public class RobotLabel extends JLabel{

    public RobotLabel() {
        setText("•");        
        setFont (getFont().deriveFont(15.0f));
        setVisible(false);
    }
    
    public void setColorLabel(String color) {
        if(color.equals(Couleurs.ROUGE.toString())) {
                setForeground(Color.RED);
        } else if(color.equals(Couleurs.BLEU.toString())) {
                setForeground(Color.BLUE);
        } else if(color.equals(Couleurs.VERT.toString())) {
                setForeground(Color.GREEN);
        }
        setVisible(true);
    }
}
