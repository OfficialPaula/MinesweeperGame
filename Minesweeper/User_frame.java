package Minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Connect4.Connect4_Gui;

public class User_frame  extends JFrame implements ActionListener {
	
	JPanel main_user_panel;
	JLabel jl_row_number, jl_col_number, jl_bomb;
	JTextField jtf1, jtf2, jtf3;
	JButton btn_ok;
	
	public User_frame() {
		
		Image icon = Toolkit.getDefaultToolkit().getImage("Image/bomb1.png");  
		this.setIconImage(icon);  
		
		main_user_panel= new JPanel();
		main_user_panel.setLayout(new BorderLayout());
		

		JPanel row_col_panel = new JPanel();
		row_col_panel.setOpaque(true);
		row_col_panel.setBackground(new Color(0,255,127));
		
		JPanel user_input_panel = new JPanel();
		user_input_panel.setOpaque(true);
		user_input_panel.setBackground(new Color(0,255,127));
		
		JPanel btn_panel = new JPanel();
        btn_panel.setOpaque(true);
		btn_panel.setBackground(new Color(0,255,127));
		
		row_col_panel.setLayout(new GridLayout(3,1));
		jl_row_number = new JLabel("Row:");
		jl_col_number = new JLabel("Column:");
		jl_bomb = new JLabel("Number of Bombs:");
		
		jl_row_number.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		jl_col_number.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		jl_bomb.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		
		row_col_panel.add(jl_row_number);
		row_col_panel.add(jl_col_number);
		row_col_panel.add(jl_bomb);
		
		user_input_panel.setLayout(new GridLayout(3,1));
		jtf1 = new JTextField(15);
		jtf2 = new JTextField(15);
		jtf3 = new JTextField(15);
		
		
		user_input_panel.add(jtf1);
		user_input_panel.add(jtf2);
		user_input_panel.add(jtf3);
		
		btn_ok = new JButton("OK");
		btn_ok.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		btn_ok.setSize(5, 5);
		btn_ok.addActionListener(this);
	    btn_panel.add(btn_ok);
		
	    main_user_panel.add(row_col_panel, BorderLayout.WEST);
		main_user_panel.add(user_input_panel, BorderLayout.EAST);
		main_user_panel.add(btn_panel, BorderLayout.SOUTH);
		
		main_user_panel.setOpaque(true);
		main_user_panel.setBackground(new Color(0,255,127));
		this.add(main_user_panel);
		
		this.setTitle("MineSweeper Menu");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	    
		
		
	}
	
	
	
	
	
@Override
	public void actionPerformed(ActionEvent arg0) {
	 int rows = Integer.parseInt(jtf1.getText().trim());
	 int cols = Integer.parseInt(jtf2.getText().trim());
	 int bombs =  Integer.parseInt(jtf3.getText().trim());
	
	 this.dispose();
	  MineSweeper_main gui = new MineSweeper_main(rows,cols,bombs);
		
	}

}