import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * GUI with hamming distance
 * 2 May, 2019
 * @author crispinsouth
 *
 */

public class Draw extends JFrame implements ActionListener, ChangeListener {
	private JPanel contentPane;
	private JPanel creativePane;
	public static ArrayList<String> mes = new ArrayList<String>();

	//GUI components and array declared here to be universal
	JLabel label1 = new JLabel("Enter Hamming Dist:");
	JSlider slider = new JSlider(1, 4, 1);
	JTextField txt1 = new JTextField("");
	JButton showStation = new JButton("Show Station");
	JTextArea txt2 = new JTextArea();
	JLabel compare = new JLabel("Compare to:");
	JComboBox<String> box = new JComboBox<String>();
	JButton calculateHD = new JButton("Calculate HD");
	JLabel d0 = new JLabel("Distance 0:");
	JLabel d1 = new JLabel("Distance 1:");
	JLabel d2 = new JLabel("Distance 2:");
	JLabel d3 = new JLabel("Distance 3:");
	JLabel d4 = new JLabel("Distance 4:");
	JTextField dis0 = new JTextField("");
	JTextField dis1 = new JTextField("");
	JTextField dis2 = new JTextField("");
	JTextField dis3 = new JTextField("");
	JTextField dis4 = new JTextField("");
	JButton addStation = new JButton("Add Station");
	JTextField add = new JTextField("");
	JLabel creative = new JLabel("Behold the guy: ");
	int[] nodes = new int[5];
	JTextArea create = new JTextArea();

	//Main method starts the program
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Draw frame = new Draw();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		mes = mes("Mesonet.txt");
	}

	// reads Mesonet.txt into arraylist like in project 1
	public static ArrayList<String> mes(String fileName) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String strg;
		br.readLine();
		br.readLine();
		br.readLine();
		strg = br.readLine();

		while ((strg = br.readLine()) != null) {
			mes.add(strg.substring(1, 5));
		}

		br.close();
		return mes;
	}
	
	// adding all the GUI elements into the panel
	public Draw() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout layout = new GridBagLayout();
		contentPane.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();

		//panel for creative bit
		creativePane = new JPanel();
		creativePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout layout1 = new GridBagLayout();
		creativePane.setLayout(layout1);
		GridBagConstraints gbc1 = new GridBagConstraints();
		create.setText("¯|_(ツ)_/¯ \n" + "        |      \n" + "       / \\     \n");
		create.setFont(new Font("Arial", Font.PLAIN, 25));
		create.setEditable(false);

		gbc1.gridx = 0;
		gbc1.gridy = 0;
		creativePane.add(creative, gbc1);

		gbc1.gridx = 0;
		gbc1.gridy = 1;
		creativePane.add(create, gbc1);

		gbc.gridx = 3;
		gbc.gridy = 2;
		contentPane.add(creativePane, gbc);

		//enter hamming dist label
		gbc.gridx = 0;
		gbc.gridy = 0;
		contentPane.add(label1, gbc);

		//slider settings
		slider.addChangeListener(this);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		gbc.gridx = 0;
		gbc.gridy = 1;
		contentPane.add(slider, gbc);

		txt1.setEditable(false);
		txt1.setText(String.valueOf(slider.getValue()));
		txt1.setColumns(5);
		gbc.gridx = 1;
		gbc.gridy = 0;
		contentPane.add(txt1, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		showStation.addActionListener(this);
		contentPane.add(showStation, gbc);

		txt2.setEditable(false);
		txt2.setColumns(20);
		txt2.setRows(15);
		gbc.gridx = 0;
		gbc.gridy = 3;

		JScrollPane scroll = new JScrollPane(txt2);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		contentPane.add(compare, gbc);

		itemsToDropdown();
		box.setSelectedItem("NRMN");
		gbc.gridx = 1;
		gbc.gridy = 4;
		contentPane.add(box, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		calculateHD.addActionListener(this);
		contentPane.add(calculateHD, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		contentPane.add(d0, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		contentPane.add(d1, gbc);

		gbc.gridx = 0;
		gbc.gridy = 8;
		contentPane.add(d2, gbc);

		gbc.gridx = 0;
		gbc.gridy = 9;
		contentPane.add(d3, gbc);

		gbc.gridx = 0;
		gbc.gridy = 10;
		contentPane.add(d4, gbc);

		gbc.gridx = 1;
		gbc.gridy = 6;
		dis0.setEditable(false);
		dis0.setColumns(5);
		contentPane.add(dis0, gbc);

		gbc.gridx = 1;
		gbc.gridy = 7;
		dis1.setEditable(false);
		dis1.setColumns(5);
		contentPane.add(dis1, gbc);

		gbc.gridx = 1;
		gbc.gridy = 8;
		dis2.setEditable(false);
		dis2.setColumns(5);
		contentPane.add(dis2, gbc);

		gbc.gridx = 1;
		gbc.gridy = 9;
		dis3.setEditable(false);
		dis3.setColumns(5);
		contentPane.add(dis3, gbc);

		gbc.gridx = 1;
		gbc.gridy = 10;
		dis4.setEditable(false);
		dis4.setColumns(5);
		contentPane.add(dis4, gbc);

		gbc.gridx = 0;
		gbc.gridy = 11;
		addStation.addActionListener(this);
		contentPane.add(addStation, gbc);

		gbc.gridx = 1;
		gbc.gridy = 11;
		add.setColumns(5);
		contentPane.add(add, gbc);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == showStation) {
			txt2.setText("");
			sortDistance((String) box.getSelectedItem());
		} else if (e.getSource() == calculateHD) {
			for (int i = 0; i < nodes.length; i++) {
				nodes[i] = 0;
			}
			nodes = findNodes((String) box.getSelectedItem());
			displayNodes();
		} else if (e.getSource() == addStation) {
			boolean addThis = false;
			for (String item : mes) {
				if (!add.getText().equalsIgnoreCase(item)) {
					addThis = true;
				}
			}
			if (addThis == true) {
				box.addItem(add.getText());
			}
		}
	}

	public void stateChanged(ChangeEvent a) {
		txt1.setText(String.valueOf(slider.getValue()));
	}

	public void itemsToDropdown() {
		for (String item : mes) {
			box.addItem(item);
		}
	}

	public int dist(String s1, String s2) {
		int dist = 0;

		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				dist += 1;
			}
		}

		return dist;
	}

	public int[] findNodes(String strg) {

		// for loop goes through each item in arraylist mes.
		for (int i = 0; i < mes.size(); i++) {
			// if below checks to make sure string doesnt get counted against itself.
			if (!strg.equals(mes.get(i))) {
				int dist = 0;
				dist = dist(strg, mes.get(i));
				if (dist == 0) {
					nodes[0] += 1;
				} else if (dist == 1) {
					nodes[1] += 1;
				} else if (dist == 2) {
					nodes[2] += 1;
				} else if (dist == 3) {
					nodes[3] += 1;
				} else if (dist == 4) {
					nodes[4] += 1;
				}
			}
		}
		nodes[4] += 1;

		return nodes;

	}

	public void displayNodes() {
		dis0.setText(String.valueOf(nodes[0]));
		dis1.setText(String.valueOf(nodes[1]));
		dis2.setText(String.valueOf(nodes[2]));
		dis3.setText(String.valueOf(nodes[3]));
		dis4.setText(String.valueOf(nodes[4]));
	}

	public void sortDistance(String strg) {
		for (String item : mes) {
			if (dist(item, strg) == slider.getValue()) {
				txt2.append(item + "\n");
			}
		}
	}
}
