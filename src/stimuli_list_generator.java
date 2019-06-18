import java.io.*;
import java.text.*;
import java.util.Date;
import java.util.Random;

public class stimuli_list_generator {
	static int num_word = 11, num_sequence = 4;
	static String subject_name = "KK";
	
	/*
	 * Randomly generate array of Trials contains all information of trials
	 */
	public static Trials[] random_sequence_generator(int num_trial) {
		Trials trial[] = new Trials[num_trial];
		for(int i = 0; i < num_trial; i++) {
			Trials t = new Trials(0, 0, 0, 0);
			trial[i] = t;
		}
		Random rand = new Random();
		
		for(int i = 0; i < num_trial; i++) {
			int word = rand.nextInt(num_word) + 1;
			int sequence = rand.nextInt(num_sequence) + 1;
			int speed = rand.nextInt(2)+1;
			int token = rand.nextInt(2)+1;
			Trials t = new Trials(word, speed , sequence, token);
			
			while(check_trials(trial, t)) {
				word = rand.nextInt(num_word) + 1;
				sequence = rand.nextInt(num_sequence) + 1;
				speed = rand.nextInt(2)+1;
				token = rand.nextInt(2)+1;
				t = new Trials(word, speed , sequence, token);
			}
			trial[i] = t;
		}
		return trial;
	}
	
	
	/*
	 * Make sure one stimulus exsit only once in the stimuli list
	 */
	public static boolean check_trials(Trials[] trial, Trials t) {
		for(Trials trials_t : trial) {
			if(trials_t.sequence == t.sequence && trials_t.word == t.word && trials_t.speed == t.speed && trials_t.token == t.token) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Write into a txt file with labels of date and subject
	 */
	public static void list_output(Trials trials[]) {
		 //FileReader in = new FileReader("input.txt");
	    FileWriter out = null;
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	 	Date date = new Date();
		try {
			out = new FileWriter("output.txt");
			out.write("Subject Number: " + subject_name + "\n");
			out.write("Generate time: " + dateFormat.format(date)+ "\n");
			out.write("word\tspeed\tsequence\ttoken\n");
			for(Trials t : trials) {
		           out.write(t.getWord() + "\t" + t.getSpeed() + "\t" + t.getSequence()+ "\t" + t.getToken()+ "\n");   // writes the bytes
		        }
			 out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		int num_trials = num_word * num_sequence * 2 * 2;//*speed *token
		Trials trial[] = random_sequence_generator(num_trials);
		list_output(trial);
		
	}

}
