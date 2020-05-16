package Lab4DataBase;

import Lab4DataBase.DataBase;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
//
import javax.swing.table.TableColumn;


//import com.sun.media.sound.ModelAbstractChannelMixer;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class checkProducts extends JFrame {


    private Scanner scanner;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtCost;
	private JTextField txtCostFilter1;
	private JTextField txtCostFilter2;
	private JTextField txtnamechange;
	private JTextField txtNewCost;
	private static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					checkProducts frame = new checkProducts();
					frame.setVisible(true);
					var listVal=DataBase.instance().getData();
					var columData=ListToStrings(listVal);
					table.setModel(new DefaultTableModel(columData,
							new String[] { "Prodid", "Title", "Cost" }));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public static String[][] ListToStrings(ArrayList<Good> listVal){
		var columData=new String[listVal.size()][3];
		int index=0;
		for (Good good : listVal) {
			columData[index][0]=Integer.toString(good.getProdid());
			columData[index][1]=good.getTitle();
			columData[index][2]=Double.toString(good.getCost());
			index++;
		}
		return columData;
	}
	public checkProducts() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(10, 11, 41, 14);
		contentPane.add(lblTitle);

		JLabel lblCost = new JLabel("Cost");
		lblCost.setBounds(10, 36, 41, 14);
		contentPane.add(lblCost);

		txtName = new JTextField();
		txtName.setBounds(61, 8, 175, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtCost = new JTextField();
		txtCost.setBounds(61, 35, 175, 20);
		contentPane.add(txtCost);
		txtCost.setColumns(10);

		txtCostFilter1 = new JTextField();
		txtCostFilter1.setBounds(465, 8, 66, 20);
		contentPane.add(txtCostFilter1);
		txtCostFilter1.setColumns(10);

		JLabel lblTo = new JLabel("to");
		lblTo.setBounds(541, 11, 21, 14);
		contentPane.add(lblTo);

		txtCostFilter2 = new JTextField();
		txtCostFilter2.setBounds(572, 8, 53, 20);
		contentPane.add(txtCostFilter2);
		txtCostFilter2.setColumns(10);

		txtnamechange = new JTextField();
		txtnamechange.setBounds(465, 33, 160, 20);
		contentPane.add(txtnamechange);
		txtnamechange.setColumns(10);

		JLabel lblNewCost = new JLabel("with new cost");
		lblNewCost.setBounds(377, 61, 89, 14);
		contentPane.add(lblNewCost);

		txtNewCost = new JTextField();
		txtNewCost.setBounds(465, 61, 160, 20);
		contentPane.add(txtNewCost);
		txtNewCost.setColumns(10);

		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(DataBase.instance().addProduct(txtName.getText(),  Double.parseDouble(txtCost.getText()))) {
						var listVal=DataBase.instance().getData();
						var columData=ListToStrings(listVal);
						table.setModel(new DefaultTableModel(columData,
								new String[] { "Prodid", "Title", "Cost" }));
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				
					
//					table.setModel(new DefaultTableModel(new String[][] {{"",txtName.getText(),txtCost.getText()}},
//						new String[] { "Prodid", "Title", "Cost" }));
			}
		});
		btnAdd.setBounds(111, 66, 70, 23);
		contentPane.add(btnAdd);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DataBase.instance().deleteProduct(txtName.getText())) {
					var listVal=DataBase.instance().getData();
					var columData=ListToStrings(listVal);
					table.setModel(new DefaultTableModel(columData,
							new String[] { "Prodid", "Title", "Cost" }));
				}
				else {
					JOptionPane.showMessageDialog(null, txtName.getText()+" not found");
				}
				
			}
		});
		btnDelete.setBounds(255, 32, 70, 23);
		contentPane.add(btnDelete);

		JButton btnShow = new JButton("Show All");
	
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var listVal=DataBase.instance().getData();
				var columData=ListToStrings(listVal);
				table.setModel(new DefaultTableModel(columData,
						new String[] { "Prodid", "Title", "Cost" }));
			}
		});
		
		btnShow.setBounds(255, 57, 89, 23);
		contentPane.add(btnShow);

		JButton btnFilter = new JButton("Filter cost from");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					var listVal=DataBase.instance().showProductsInPriceRange(Double.parseDouble(txtCostFilter1.getText())
							, Double.parseDouble(txtCostFilter2.getText()));
					var columData=ListToStrings(listVal);
					table.setModel(new DefaultTableModel(columData,
							new String[] { "Prodid", "Title", "Cost" }));
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				
			}
		});
		btnFilter.setBounds(335, 7, 121, 23);
		contentPane.add(btnFilter);

		JButton btnChangeCost = new JButton("Change cost of");
		btnChangeCost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DataBase.instance().changePrice(txtnamechange.getText(),Double.parseDouble(txtNewCost.getText()));
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		btnChangeCost.setBounds(335, 32, 121, 23);
		contentPane.add(btnChangeCost);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 615, 509);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Prodid", "Title", "Cost" }));
		scrollPane.setViewportView(table);
		
		JButton buttonFindValue = new JButton("Search");
		buttonFindValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var good=DataBase.instance().getPriceByTitle(txtName.getText());
				if(good!=null)
				table.setModel(new DefaultTableModel(new String[][] {{Integer.toString(good.getProdid()),
					txtName.getText(),Double.toString(good.getCost()) }},
						new String[] { "Prodid", "Title", "Cost" }));
			}
		});
		buttonFindValue.setBounds(255, 7, 70, 23);
		contentPane.add(buttonFindValue);
	}
}
