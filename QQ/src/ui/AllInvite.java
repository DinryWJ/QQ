package ui;

import java.awt.EventQueue;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import client.TCPConnection;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;

public class AllInvite {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AllInvite window = new AllInvite();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public AllInvite(String userId) {
		initialize(userId);
	}




	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String userId) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 432, 24);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("好友请求");
		lblNewLabel.setBounds(0, 0, 432, 24);
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 25, 432, 228);
		frame.getContentPane().add(scrollPane);
//		cInvite cinvite=new cInvite(userId);
//		String lists = cinvite.getList();//"1&user1&user2
		System.out.println("UserID"+userId);
		String lists = TCPConnection.getInstance().sendAndWaitResponse("D&"+userId);
	System.out.println("列表"+lists);
		String[] userList=lists.split("&");
		
		 String[] columnNames = { "好友申请" }; // When I increase the  
         // number of columns it  
         // hides another button
		 Object[][] data = new Object[userList.length-1][3] ;		
		 
		 for(int i=1;i<userList.length;i++){		
			 data[i-1][0]=userList[i];	
			 
			}
		 
		DefaultTableModel dlm = new DefaultTableModel(data, columnNames);

		JTable table = new JTable(dlm);

	     table.addMouseListener(new MouseListener() {
	    	  
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub				
				 if(e.getButton() == e.BUTTON3){	
					 table.setEnabled(true); 
				   int r= table.getSelectedRow();
	               int c= table.getSelectedColumn();	            
				String id=(String) table.getValueAt(r, c);	
				if (id!= null) {
					JPopupMenu popupMenu = new JPopupMenu();
										
					JButton btn1 = new JButton("同意" );
					JButton btn2 = new JButton("拒绝" );
					btn1.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
					
					btn2.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
					//System.out.println(a);
					popupMenu.add(btn1);
					popupMenu.add(btn2);
					popupMenu.show(table, e.getX(), e.getY());
				}
				 }
			}
		});
	     scrollPane.setViewportView(table);				
	}
}
