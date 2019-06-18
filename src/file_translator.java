import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


import java.io.File; 
import java.io.IOException; 
import java.util.Scanner; 

import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

public class file_translator {

	public static ArrayList<String> file_translation(String file_name) {
		 
		ArrayList<String> names = new ArrayList<String>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file_name));
			BufferedReader word_reader = new BufferedReader(new FileReader("single_word.txt"));
			BufferedReader sequecnce_reader = new BufferedReader(new FileReader("sequence.txt"));
			String line = reader.readLine();
			while(line != null) {
				if(line.contains("\t") && !line.contains("word")) {
					//System.out.println(line);
					
					String t[] = line.split("\t");
					
					int i = 0;
					String word = null, sequence = null, speed =  null, token = null;
					word_reader = new BufferedReader(new FileReader("single_word.txt"));
					sequecnce_reader = new BufferedReader(new FileReader("sequence.txt"));
					
					while(i < Integer.valueOf(t[0])) {
						word =  word_reader.readLine();
						i++;
					}
					
					if(t[1].equals("1")) speed = "f";
					else speed = "s";
					
					i = 0;
					while(i < Integer.valueOf(t[2])) {
						sequence = sequecnce_reader.readLine();
						i++;
					}
					
					token = t[3];
					
					String record_name = word+ "_" + speed + "_" + sequence +  "_" + token + ".wav";
					System.out.println(record_name);
					
					names.add(record_name);
					word_reader.close();
					sequecnce_reader.close();
					
				}
				line = reader.readLine();
			}
			
			reader.close();
			word_reader.close();
			sequecnce_reader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return names;
	}
	

	public static void main(String[] args) {
		ArrayList<String> names = file_translation("output.txt");
		//System.out.println(names.toString());
		
     
	}

}
