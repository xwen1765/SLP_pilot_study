import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class project_launcher {
	static GraphicsDevice device = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getScreenDevices()[0];

	static ArrayList<String> names = file_translator.file_translation("output.txt");
	static int i = 0, end = names.size();
	static String response = "";
	static boolean is_respond = true, is_played = false;
	
	static FileWriter out = null;
	static BufferedWriter bw = null;
	static DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	static Date date = new Date();
	
	static String user_name = "", user_email = "";
	
	

	public static void user_input() {
		JFrame user_input_frame = new JFrame("User Input");
		int u_width = 400, u_height = 300;
		user_input_frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		user_input_frame.setPreferredSize(new Dimension(u_width, u_height));
		user_input_frame.setSize(u_width,u_height);
		Dimension u_dim = Toolkit.getDefaultToolkit().getScreenSize();
		user_input_frame.setLocation(u_dim.width/2 - user_input_frame.getSize().width/2, u_dim.height/2 - user_input_frame.getSize().height/2);

		JButton btn = new JButton("Finish");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				user_input_frame.dispose();
				running_setting();
			}
		});
		JPanel panel = new JPanel(new GridLayout());
		panel.add(btn);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

		JPanel name_panel = new JPanel(new FlowLayout());
		JLabel name = new JLabel("Name: ");
		JTextField text1 = new JTextField(16);
		name_panel.add(name);
		name_panel.add(text1);

		JPanel gender_panel = new JPanel(new FlowLayout());
		JLabel gender = new JLabel("Gender: ");
		JCheckBox male = new JCheckBox("Male");
		JCheckBox fem = new JCheckBox("Female");
		JCheckBox non = new JCheckBox("Non relevent");
		gender_panel.add(gender);
		gender_panel.add(male);
		gender_panel.add(fem);
		gender_panel.add(non);

		JPanel email_panel = new JPanel(new FlowLayout());
		JLabel email = new JLabel("E-mail Address: ");
		JTextField text2 = new JTextField(16);
		email_panel.add(email);
		email_panel.add(text2);

		panel2.add(name_panel);
		panel2.add(gender_panel);
		panel2.add(email_panel);

		user_input_frame.add(panel2, BorderLayout.CENTER);
		user_input_frame.add(panel, BorderLayout.SOUTH);
		user_input_frame.setVisible(true);

	}

	public static void running_setting(){
		try
		{
			int width = 400, height = 200;
			final JFrame frame = new JFrame("Display Mode");
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			//frame.setUndecorated(true);
			frame.setPreferredSize(new Dimension(width, height));
			frame.setSize(width,height);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation(dim.width/2 - frame.getSize().width/2, dim.height/2 - frame.getSize().height/2);
			
			File file = new File("response_"+dateFormat.format(date)+".txt");
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			
			JButton btn1 = new JButton("Full-Screen");
			btn1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					device.setFullScreenWindow(frame);
				}
			});
			JButton btn2 = new JButton("Normal");
			btn2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					device.setFullScreenWindow(null);
				}
			});
			JButton close = new JButton("Close");
			close.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.exit(0);
				}
			});

			JLabel label1 = new JLabel();
			label1.setSize(80,80);
			try {
				BufferedImage myPicture = ImageIO.read(new File("speaker3.png"));
				Image dimg = myPicture.getScaledInstance(label1.getWidth(), label1.getHeight(),
						Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(dimg);

				label1.setIcon(imageIcon);

			} catch (Exception e) {
				e.printStackTrace();
			}


			JPanel panel = new JPanel(new FlowLayout());
			panel.add(btn1);
			panel.add(btn2);
			panel.add(close);
			Border blackline = BorderFactory.createTitledBorder("Screen control");
			panel.setBorder(blackline);

			JPanel panel2 = new JPanel(new FlowLayout());
			panel2.add(label1);


			JPanel panel3 = new JPanel(new FlowLayout());


			JButton btn4 = new JButton("Play");
			panel3.add(btn4);


			btn4.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(i <= end && is_respond && !is_played) {
						try {
							i++;
							
							sound_play();
							is_respond = false;
							is_played = true;
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(i != 0) btn4.setText("Next");
					if(i >= end) {
						btn4.setText("Over");
						try {
							out.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					frame.repaint();
				}
			});

			JPanel panel4 = new JPanel(new FlowLayout());


			JButton panel4_but1 = new JButton("It");
			JButton panel4_but2 = new JButton("Eat");
			JButton panel4_but3 = new JButton("Ate");
			JButton panel4_but4 = new JButton("Etch");
			JButton panel4_but5 = new JButton("Axe");
			panel4.add(panel4_but1);
			panel4.add(panel4_but2);
			panel4.add(panel4_but3);
			panel4.add(panel4_but4);
			panel4.add(panel4_but5);



			panel4_but1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					response = panel4_but1.getText();
					is_respond = true;
					is_played = false;
					try {
						bw.write(response+'\n');
						bw.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});

			panel4_but2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					response = panel4_but2.getText();
					is_respond = true;
					is_played = false;
					try {
						bw.write(response+'\n');
						bw.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(response);
				}
			});

			panel4_but3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					response = panel4_but3.getText();
					is_respond = true;
					is_played = false;
					try {
						bw.write(response+'\n');
						bw.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(response);
				}
			});

			panel4_but4.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					response = panel4_but4.getText();
					is_respond = true;
					is_played = false;
					try {
						bw.write(response+'\n');
						bw.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(response);
				}
			});

			panel4_but5.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					response = panel4_but5.getText();
					is_respond = true;
					is_played = false;
					try {
						bw.write(response+'\n');
						bw.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(response);
				}
			});

			JPanel outpanel = new JPanel(new BorderLayout());
			outpanel.add(panel2, BorderLayout.NORTH);
			outpanel.add(panel3, BorderLayout.CENTER);
			outpanel.add(panel4, BorderLayout.SOUTH);

			frame.add(outpanel);
			frame.pack();
			frame.setVisible(true);
			

		}
		catch (Exception ex) 
		{ 
			System.out.println("Error with playing sound."); 
			ex.printStackTrace(); 

		} 
	}

	public static void sound_play() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		String file = names.get(i);
		file = "record/" + file;
		audio_player.filePath = file;
		audio_player audioPlayer = new audio_player(); 
		audioPlayer.play();

	}
	
	public static void file_writer(String str) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("samplefile1.txt"));
		writer.write(str);
		writer.close();
	}

	public static void main(String[] args) 
	{ 
		user_input();
	} 
}
