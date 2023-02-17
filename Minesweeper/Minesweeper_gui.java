package Minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Connect4.GameBoardInterface;

public class Minesweeper_gui extends JFrame {
	
	private JPanel jpMain;
    private createBoard jpBoard;
    private Grid myGrid;
	
	public Minesweeper_gui() {
		
		//myGrid = new Grid();
		myGrid = new Grid(12,12,35);
		myGrid.display_bomb();
		myGrid.display_count();
		jpMain = new JPanel();
		jpMain.setLayout(new BorderLayout());
		jpBoard = new createBoard();
		
		jpMain.add(jpBoard,BorderLayout.CENTER);
	    this.add(jpMain);
	    this.setSize(500, 500);
	    this.setTitle("Minesweeper");
	    setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private class createBoard extends JPanel implements ActionListener{
	
		private JButton [][] board;
		private final int NUM_ROWS = myGrid.getNumRows();
		private final int NUM_COLS = myGrid.getNumColumns();
		int [][] count_grid = myGrid.getCountGrid();
	    int bombNumbers = myGrid.getNumBombs();
		int cell_checked = 0;
		int cellsWithoutBomb = ( NUM_ROWS * NUM_COLS ) - bombNumbers;
		
		public createBoard (){
			setLayout(new GridLayout(NUM_ROWS,NUM_COLS));
			displayBoard();
		}
		
		public void displayBoard() {
			board = new JButton[NUM_ROWS][NUM_COLS];
			for(int r = 0; r<board.length; r++){
				for(int c = 0; c < board[r].length; c++){
					board[r][c] = new JButton();
					Font bigFont = new Font(Font.SANS_SERIF, Font.BOLD, 10);
					board[r][c].setOpaque(true);
					board[r][c].setBackground(Color.CYAN);
					board[r][c].setFont(bigFont);
					board[r][c].addActionListener(this);
					board[r][c].setEnabled(true);
					board[r][c].setText("");
					this.add(board[r][c]);
					
				}
			}
			
		}
		
		private  void reavel_content () {
			ImageIcon bombImg = new ImageIcon("Image/bomb.png");
			ImageIcon scalebombImg = new ImageIcon(bombImg.getImage().getScaledInstance(60,60, Image.SCALE_FAST));
			
			for(int i = 0 ; i <count_grid.length; i++)
			{
				for(int j = 0; j< count_grid[i].length; j++)
				{
				   if(!myGrid.isBombAtLocation(i, j)) {	
				   board[i][j].setText(Integer.toString(myGrid.getCountAtLocation(i, j)));
				   }
				   else {
					   board[i][j].setText("B");              //showing bomb
					   //board[i][j].setIcon(scalebombImg);
				   }
				}
			}
			
		}
		private void reavel_particularCell(int r, int c) {
			
			      board[r][c].setText(Integer.toString(myGrid.getCountAtLocation(r, c)));     //the real one
			
		}
		
		
		public void clearBoard() {
			for(int row=0; row<board.length; row++){
				for(int col=0; col < board[row].length; col++){
					board[row][col].setOpaque(true);
					board[row][col].setBackground(Color.RED);
					board[row][col].setText("");
					board[row][col].setEnabled(true);
					board[row][col].setIcon(null);
				}
			}
			
		}
		
		
		private void promptAgain(){
            int yesNo = JOptionPane.showConfirmDialog(null,  "Another Game?", "Yes or No",JOptionPane.YES_NO_OPTION);
			if(yesNo == JOptionPane.YES_OPTION){ //want to play more
				clearBoard();
				
			}
			else{   //no more play
				
	             System.exit(EXIT_ON_CLOSE);
			}
		}
		
		
		
		
		
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btnclk = (JButton) e.getSource();
			

	            int row = 0, col =0;
	            for(int r =0; r<board.length; r++ )
	            {
	            	for(int c=0; c<board[r].length; c++)
	            	{
	            		if(board[r][c] == btnclk)
	            		{
	            			row = r;
	            			col = c;
	            			break;
	            		}
	            	}
	            }
			    if(myGrid.isBombAtLocation(row, col))         //HAS BOMB
			    {
			    	reavel_content();
			    	
			    	JOptionPane.showMessageDialog(null, "Game has been lost"); 
			    	promptAgain();
			    }	
			    else if(!myGrid.isBombAtLocation(row, col))            // DOES NT HAVE BOMB
	            {
			    	if(board[row][col].isEnabled()) {
			    	board[row][col].setEnabled(false);
			    	//board[row][col].removeActionListener(this);
			    	cell_checked++;
			    	}
			    	
	                reavel_particularCell(row,col);
	                if(cell_checked == this.cellsWithoutBomb)
			    	{
			    		JOptionPane.showMessageDialog(null, "Game has been won");
			    		promptAgain();
			    	}
	            	
	            	
	            }
			    
			
		}
		
	}
	

}