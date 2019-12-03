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
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class project_launcher {
	static GraphicsDevice device = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getScreenDevices()[0];

	static ArrayList<String> names;
	static int i = 0, end = 0, is_whole_word = 0;
	static String response = "";
	static boolean is_respond = true, is_played = false;
	static String file_name = "";
	static long start_time = 0, end_time = 0;
	static FileWriter out = null;
	static BufferedWriter bw = null;
	static DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	static Date date = new Date();

	static String user_name = "", user_email = "";


	public static void user_input() {
		JFrame user_input_frame = new JFrame("User Input");
		int u_width = 400, u_height = 200;
		user_input_frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		user_input_frame.setPreferredSize(new Dimension(u_width, u_height));
		user_input_frame.setSize(u_width,u_height);
		Dimension u_dim = Toolkit.getDefaultToolkit().getScreenSize();
		user_input_frame.setLocation(u_dim.width/2 - user_input_frame.getSize().width/2, u_dim.height/2 - user_input_frame.getSize().height/2);



		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

		JPanel name_panel = new JPanel(new FlowLayout());
		JLabel name = new JLabel("Name: ");
		JTextField text1 = new JTextField(16);
		name_panel.add(name);
		name_panel.add(text1);

		text1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				user_name = text1.getText();
			}
		});


		JPanel email_panel = new JPanel(new FlowLayout());
		JLabel email = new JLabel("E-mail Address: ");
		JTextField text2 = new JTextField(16);
		email_panel.add(email);
		email_panel.add(text2);

		text2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				user_email = text2.getText();
			}
		});

		JButton btn = new JButton("Finish");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				user_name = text1.getText();
				user_email = text2.getText();
				initialize();
				user_input_frame.dispose();
				running_setting();
			}
		});
		JPanel panel = new JPanel(new GridLayout());
		panel.add(btn);

		panel2.add(name_panel);
		panel2.add(email_panel);

		user_input_frame.add(panel2, BorderLayout.CENTER);
		user_input_frame.add(panel, BorderLayout.SOUTH);
		user_input_frame.setVisible(true);

	}

	public static void initialize() {
		stimuli_list_generator gn = new stimuli_list_generator();
		gn.subject_name = user_name;
		gn.required_sequence = 1;
		gn.random_sequence_generator(gn.required_sequence);
		String file_name = "subject_stimuli_list/list_"+gn.subject_name+".txt";
		names = file_translator.file_translation(file_name);
		String file_word_name = "subject_stimuli_word_list/word_list_"+gn.subject_name+".txt";
		file_translator.list_output(file_name,file_word_name);
		end = names.size();
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
			
			File file = new File("subject_response/response_"+user_name+"_"+dateFormat.format(date)+".txt");
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write("Subject name:" + user_name + '\n');
			bw.write("Subject contact info:" + user_email + '\n');
			bw.flush();

			
			
			JLabel label1 = new JLabel();
			label1.setSize(80,80);
			try {
				BufferedImage myPicture = ImageIO.read(new File("speaker.png"));
				Image dimg = myPicture.getScaledInstance(label1.getWidth(), label1.getHeight(),
						Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(dimg);

				label1.setIcon(imageIcon);

			} catch (Exception e) {
				e.printStackTrace();
			}

			JPanel panel2 = new JPanel(new FlowLayout());
			panel2.add(label1);


			JPanel panel3 = new JPanel(new FlowLayout());


			JButton btn4 = new JButton("Play");
			panel3.add(btn4);


			btn4.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(i < end && is_respond && !is_played) {
						try {
							
							if(is_whole_word == 1) {
								System.out.println("In here");
								int width2 = 400, height2 = 200;
								JFrame frame2 = new JFrame("Break");
								//frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
								//frame.setUndecorated(true);
								frame2.setPreferredSize(new Dimension(width2, height2));
								frame2.setSize(width2,height2);
								Dimension dim2 = Toolkit.getDefaultToolkit().getScreenSize();
								frame2.setLocation(dim2.width/2 - frame2.getSize().width/2, dim2.height/2 - frame2.getSize().height/2);
								frame2.setLayout(new BorderLayout());
								
								
								frame2.setVisible(true);
								
								TimeUnit.SECONDS.sleep(10);
								frame2.dispatchEvent(new WindowEvent(frame2, WindowEvent.WINDOW_CLOSING));
								is_whole_word = 2;
							}else {
								start_time = System.nanoTime();
								sound_play();
								bw.write(line_translation(file_name));
								bw.flush();
								is_respond = false;
								is_played = true;
								i++;
								
								if(i < end-1) {
									whole_word_checker(i);
								}
								//System.out.println(file_name);
							}
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(i != 0) btn4.setText("Next");
					if(i >= end) {
						btn4.setText("Over");
						i++;
						
					}
					if(i > end+1) {
						frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
						try {
							out.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
			});

			JPanel panel4 = new JPanel(new FlowLayout());


			JButton panel4_but1 = new JButton("itch");
			JButton panel4_but2 = new JButton("egg");
			JButton panel4_but3 = new JButton("axe");
			JButton panel4_but4 = new JButton("ease");
			JButton panel4_but5 = new JButton("ape");
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
						end_time = System.nanoTime();
						bw.write(response+'\t');
						bw.write(Long.toString((end_time - start_time)/1000000) + '\n');
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
						end_time = System.nanoTime();
						bw.write(response+'\t');
						bw.write(Long.toString((end_time- start_time)/1000000) + '\n');
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
						end_time = System.nanoTime();
						bw.write(response+'\t');
						bw.write(Long.toString((end_time - start_time)/1000000) + '\n');
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
						end_time = System.nanoTime();
						bw.write(response+'\t');
						bw.write(Long.toString((end_time- start_time)/1000000) + '\n');
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
						end_time = System.nanoTime();
						bw.write(response+'\t');
						bw.write(Long.toString((end_time- start_time)/1000000) + '\n');
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

	public static void sound_play() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		
		file_name = names.get(i);
		file_name = "record/" + file_name;
		
		audio_player.filePath = file_name;
		audio_player audioPlayer = new audio_player(); 
		audioPlayer.play();
		

	}



	public static String line_translation(String line) {

		String names = "";

		line = line.replace("record/", "");
		line = line.replace(".wav", "");
		String t[] = line.split("_");

		names = t[0]+ '\t' + t[1] + '\t' + t[2] +  '\t' + t[3] + '\t';
		return names;
	}
	
	public static int whole_word_checker(int i) {
		String line = names.get(i);
		line = line.replace("record/", "");
		line = line.replace(".wav", "");
		if(line.contains("w") && is_whole_word == 0) {
			is_whole_word = 1;
			return 1;
		}
		return 0;
	}
	
	

	public static void main(String[] args) 
	{ 
		user_input();
	} 
}
