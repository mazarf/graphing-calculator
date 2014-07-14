import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Window {
	
	static Drawing d = new Drawing();
	
	public static void main(String args[])
	{
		
		JButton functionButton = new JButton("Set Function");
		functionButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Set Function"))
				{
					d.s = JOptionPane.showInputDialog(null, "Write a function of x:");
					d.repaint();
				}
			}
			
		});

		

		
		JButton parameterButton = new JButton("Adjust Bounds");
		parameterButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Adjust Bounds"))
				{
					// online example
					JTextField leftBound = new JTextField(5);
					JTextField rightBound = new JTextField(5);
					JTextField lowerBound = new JTextField(5);
					JTextField upperBound = new JTextField(5);
					
				      JPanel myPanel = new JPanel();
				      myPanel.add(new JLabel("Min X:"));
				      myPanel.add(leftBound);
				      //myPanel.add(Box.createHorizontalStrut(15)); // a spacer
				      myPanel.add(new JLabel("Max X:"));
				      myPanel.add(rightBound);
				      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
				      myPanel.add(new JLabel("Min Y:"));
				      myPanel.add(lowerBound);
				      //myPanel.add(Box.createHorizontalStrut(15)); // a spacer
				      myPanel.add(new JLabel("Max Y:"));
				      myPanel.add(upperBound);

				      int result = JOptionPane.showConfirmDialog(null, myPanel, 
				               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
				      if (result == JOptionPane.OK_OPTION) {
				         d.minX = Integer.parseInt(leftBound.getText());
				         d.maxX = Integer.parseInt(rightBound.getText());
				         d.minY = Integer.parseInt(lowerBound.getText());
				         d.maxY = Integer.parseInt(upperBound.getText());
				         
				      }
					d.repaint();
				}
			}
			
		});
		
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		JPanel container = new JPanel();
		container.add(functionButton);
		container.add(parameterButton);
		p.add(container, BorderLayout.SOUTH);
		p.add(d, BorderLayout.CENTER);
		
		JFrame f = new JFrame("Graphing Calculator");
		f.setContentPane(p);
		f.setSize(800,600);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

