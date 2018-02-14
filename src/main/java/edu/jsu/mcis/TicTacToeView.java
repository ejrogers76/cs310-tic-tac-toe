package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeView extends JPanel implements ActionListener {

    private TicTacToeModel model;
    
    /* CONSTRUCTOR */
    
    private JButton[][] squares;
    private JPanel squaresPanel;
    private JLabel resultLabel;
    
    
    public TicTacToeView(TicTacToeModel model) {
        
        this.model = model;
        
        int width = model.getWidth();
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        squares = new JButton[width][width];
        squaresPanel = new JPanel(new GridLayout(width,width));
        resultLabel = new JLabel();
        resultLabel.setName("ResultLabel");
        
        for (int row = 0; row < width; row++){
    
            for (int col = 0; col < width; col++){
                
                squares[row][col] = new JButton();
                squares[row][col].addActionListener(this);
                squares[row][col].setName("Square" + row + col);
                squares[row][col].setPreferredSize(new Dimension(128,128));
                squaresPanel.add(squares[row][col]);
            }
        }
        
        add(squaresPanel);
        add(resultLabel);
        
        resultLabel.setText("Welcome to Tic-Tac-Toe");
    }
	
    @Override
    public void actionPerformed(ActionEvent event){
        JButton button = ((JButton) event.getSource());
        String name = button.getName();
        
        int row = (int) name.charAt(6) - 48, col = (int) name.charAt(7) - 48;
        
        model.makeMark(row,col);
        
        button.setText(model.getMark(row,col).toString());
        
        if(model.getResult() == TicTacToeModel.Result.X){
            JOptionPane.showMessageDialog(this, "X Wins!");
            System.exit(0);
        }
        
        else if(model.getResult() == TicTacToeModel.Result.O){
            JOptionPane.showMessageDialog(this, "O Wins!");
            System.exit(0);
        }
        
        else if(model.getResult() == TicTacToeModel.Result.TIE){
            JOptionPane.showMessageDialog(this, "No one wins! Its a tie!");
            System.exit(0);
        }
        
        else{
            showNextMovePrompt();
        }
        
        
    }
    
    public void showNextMovePrompt() {

        /* Display a prompt for the player's next move (see examples) */

        if(model.isXTurn()){
            resultLabel.setText("X's Turn");
        }
        else{
            resultLabel.setText("O's Turn");
        }
    }

    public void showInputError() {

        /* Display an error if input is invalid (see examples) */

        resultLabel.setText("Choose another space!");

    }
}