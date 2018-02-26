import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.ImageIcon;
import java.awt.Font;

public class mainpage extends JFrame {

	private JPanel contentPane;
	private JTextField inputfield;
	List<String> arr = new ArrayList<String>();
	JTextArea dispArea;
	JScrollPane scrollPane;
	
	
	JPanel todospanel; 
	
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainpage frame = new mainpage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainpage() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 449);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("ToolTip.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		inputfield = new JTextField();
		inputfield.setBounds(15, 16, 328, 26);
		contentPane.add(inputfield);
		inputfield.setColumns(10);
		
		JButton addnewbtn = new JButton("Add Todo");
		addnewbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String temp = inputfield.getText();
				arr.add(temp);
				inputfield.setText(null);
				try {
					save(temp,"list.txt");
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		});
		addnewbtn.setBounds(349, 13, 115, 29);
		contentPane.add(addnewbtn);
		
		dispArea = new JTextArea();
		dispArea.setEditable(false);
		dispArea.setBounds(15, 165, 449, 216);
		contentPane.add(dispArea);
		
		JLabel lblNewLabel_1 = new JLabel("To do");
		lblNewLabel_1.setFont(new Font("Georgia", Font.PLAIN, 20));
		lblNewLabel_1.setLabelFor(dispArea);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBackground(new Color(255, 175, 175));
		lblNewLabel_1.setBounds(15, 101, 437, 35);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setIcon(new ImageIcon("S:\\ZerotoOne\\Java Practice\\Todo\\32.jpg"));
		btnNewButton.setBounds(415, 101, 37, 35);
		contentPane.add(btnNewButton);
		
	/*	todospanel = new JPanel();
		todospanel.setBackground(UIManager.getColor("ToolBar.highlight"));
		todospanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		todospanel.setBounds(15, 80, 449, 297);
		contentPane.add(todospanel);
		todospanel.setLayout(null); 
			
	/*	scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 68, 449, 309);
		contentPane.add(scrollPane); */
		
		//JScrollPane sp = new JScrollPane(dispArea);
		//contentPane.add(sp);
		
		tick.scheduleAtFixedRate(task,100,2000);
		
	}

	
	Timer tick = new Timer();
	TimerTask task = new TimerTask() {
		
		public void run(){
		    dispArea.setText(null);
			List<String> arrread = getList("list.txt");
			for(String a: arrread) {
				dispArea.append(a +"\n");
				System.out.println(a);
			}
	 	}
	};
	
	public JLabel addlabel(String s) {
	lblNewLabel = new JLabel(s);
	lblNewLabel.setForeground(SystemColor.desktop);
	lblNewLabel.setBounds(0, 0, 449, 35);
	lblNewLabel.setBackground(new Color(255, 255, 204));
	return lblNewLabel;
	}
	
	public static void save(String todo, String filename)
			throws IOException {
		PrintWriter outputFile = null;
		try {
			/*FileOutputStream f = new FileOutputStream(filename);
			OutputStreamWriter out = new OutputStreamWriter(f,
					System.getProperty("file.encoding")); */
			outputFile = new PrintWriter(new FileOutputStream(new File(filename), 
				    true));

				if (todo != null)
					outputFile.append(todo);
		} catch (FileNotFoundException e) {
			throw new IOException(
					"Error saving list! Unable to access the device "
							+ filename);
		}

		catch (SecurityException se) {
			throw new SecurityException(
					"The file does not have writtable permissions.");
		} finally {
			if (outputFile != null)
				outputFile.close();
		}
	}
	
	public static List<String> getList(String filename) {

		if (filename == null)
			throw new IllegalArgumentException("The file name cannot be null.");

		// To contain the Appointment objects
		List<String> todos = new ArrayList<String>();
		Scanner inputFile = null;
		

		try {
			BufferedReader b = new BufferedReader(new InputStreamReader(
					new FileInputStream(filename)));
			inputFile = new Scanner(b);

			

			// To load the Appointment objects into the list
			while (inputFile.hasNext()) {
				todos.add(inputFile.nextLine());
				
			}
		}
			catch (FileNotFoundException fnf) {
				throw new IllegalArgumentException("Error: " + filename
						+ " not found.");
			} finally {
				if (inputFile != null)
					inputFile.close();
			}
		
	return todos;
	}
}
