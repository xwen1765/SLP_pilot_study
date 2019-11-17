import java.io.*;
import java.text.*;
import java.util.Date;
import java.util.Random;

import javax.sound.sampled.ReverbType;

public class stimuli_list_generator {
	int num_word = 11, num_sequence = 3, repetition = 2, num_token = 2;
	int required_sequence = 1;
	String subject_name = "KK";

	/*
	 * Randomly generate array of Trials contains all information of trials
	 */
	public Trials[] random_sequence_generator(int required_sequence) {
		int num_trial = num_word * 2 * 2 * 2 + num_word * 2;//*token * repetition * speed + whole word (word * token; fast)
		int num_list1 = num_word * 2 * 2, num_list2 = num_word * 2 * 2, num_list3 = num_word * 2;
		
		//initialize all trials in the list
		Trials trial[] = new Trials[num_trial];
		for(int i = 0; i < num_trial; i++) {
			Trials t = new Trials(0, 0, 0, 0);
			trial[i] = t;
		}
		Random rand = new Random();
		
		int required_speed = 1;
		for(int i = 0; i < num_list1; i++) {
			int word = rand.nextInt(num_word) + 1;
			int sequence = required_sequence;
			int speed = required_speed;
			int token = rand.nextInt(num_token) + 1;
			Trials t = new Trials(word, speed , sequence, token);
			while(!check_trials(trial, t, 2)) {
				word = rand.nextInt(num_word) + 1;
				sequence = required_sequence;
				speed = required_speed;
				token = rand.nextInt(num_token) + 1;
				t = new Trials(word, speed , sequence, token);
			}
			trial[i] = t;
		}
		
		required_speed = 2;//slow
		for(int i = num_list1; i < num_list1+num_list2; i++) {
			int word = rand.nextInt(num_word) + 1;
			int sequence = required_sequence;
			int speed = required_speed;
			int token = rand.nextInt(num_token) + 1;
			Trials t = new Trials(word, speed , sequence, token);

			while(!check_trials(trial, t, 2)) {
				word = rand.nextInt(num_word) + 1;
				sequence = required_sequence;
				speed = required_speed;
				token = rand.nextInt(num_token) + 1;
				t = new Trials(word, speed , sequence, token);
			}
			trial[i] = t;
		}
		
		required_speed = 2;
		for(int i = num_list1+num_list2; i < num_list1+num_list2+num_list3; i++) {
			
			int word = rand.nextInt(num_word) + 1;
			int sequence = 3;
			int speed = required_speed;
			int token = rand.nextInt(num_token) + 1;
			Trials t = new Trials(word, speed , sequence, token);

			while(!check_trials(trial, t, 1)) {
				word = rand.nextInt(num_word) + 1;
				sequence = 3;
				speed = required_speed;
				token = rand.nextInt(num_token) + 1;
				t = new Trials(word, speed , sequence, token);
			}
			trial[i] = t;
		}
		subject_name = subject_name.replaceAll("\\s+","");
		list_output(trial,"subject_stimuli_list/list_"+subject_name+".txt");
		return trial;
	}

	public static int reverse_token(int token) {
		if(token == 1) return 2;
		else return 1;

	}

	public static void randomize_token(Trials[] trial) {
		for(int j = 0; j < trial.length/2; j++) {
			int rnd = new Random().nextInt(trial.length);
			Trials select_t = trial[rnd];
			select_t.token = reverse_token(select_t.token);
			for(int i = 0; i < trial.length; i++) {
				if(trial[i] == select_t) {
					trial[i].token = reverse_token(trial[i].token);
					break;
				}
			}
			trial[rnd].token = reverse_token(trial[rnd].token);
		}
	}


	/*
	 * Make sure one stimulus exsit only once in the stimuli list
	 */
	public boolean check_trials(Trials[] trial, Trials t, int required_repetition) {
		int num_rep = 0;
		for(Trials trials_t : trial) {
			if(trials_t.sequence == t.sequence && trials_t.word == t.word && trials_t.speed == t.speed && trials_t.token == t.token) {
				num_rep++;
			}
		}
		if(num_rep < required_repetition) return true;
		return false;
	}

	/*
	 * Write into a txt file with labels of date and subject
	 */
	public void list_output(Trials trials[], String output_name) {
		//FileReader in = new FileReader("input.txt");
		FileWriter out = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		try {
			out = new FileWriter(output_name);
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
		String a = "a a a a a";
		a = a.replaceAll("\\s+","");
		System.out.println(a);
//		Trials trial[] = random_sequence_generator(1);
//		list_output(trial,"list_"+subject_name+".txt");
//		for(int i = 0; i< trial.length; i++)
//			System.out.println(trial[i].toString());

	}

}
