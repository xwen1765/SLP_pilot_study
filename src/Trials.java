
public class Trials {
	int word = 0;
	int sequence = 0;
	int speed = 0;
	int token = 0;
	public Trials(int word, int speed , int sequence, int token){
		this.word =  word;
		this.sequence = sequence;
		this.speed = speed;
		this.token = token;
	}


	@Override
	public String toString() {
		return "Trials [word=" + word + ", sequence=" + sequence + "]";
	}


	public int getWord() {
		return word;
	}


	public void setWord(int word) {
		this.word = word;
	}


	public int getSequence() {
		return sequence;
	}


	public void setSequence(int sequence) {
		this.sequence = sequence;
	}


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}


	public int getToken() {
		return token;
	}


	public void setToken(int token) {
		this.token = token;
	}
	
	
	
}
