package quiz;

public class QuizStats {
	private int quizId; 
	private int quizAttempts; // Count of quiz attempts
	private int sumActualScores; 
	private int sumPossibleScores; 
	private int userAttempts; // count by unique users 
	
	public QuizStats(int quizId)
	{
		this.quizId = quizId; 
		this.quizAttempts = 0; 
		this.sumActualScores = 0;
	}

	public QuizStats(int quizId, int quizAttempts, int sumActualScores, int sumPossibleScores, int userAttempts)
	{
		this.quizId = quizId;
		this.quizAttempts = quizAttempts;
		this.sumActualScores = sumActualScores;
		this.sumPossibleScores = sumPossibleScores;
		this.userAttempts = userAttempts;
	}
	
	public void incrementQuizAttempts() {
		quizAttempts++; 
	}
	
	public void incrementUserAttempts() {
		userAttempts++; 
	}
	
	public void addSumActualScores(int actualScore) {
		this.sumActualScores += actualScore; 
	}
	
	public void addSumPossibleScores(int possibleScore) {
		this.sumPossibleScores += possibleScore; 
	}
	
	public int getQuizId() {
		return quizId; 
	}
	
	public int getQuizAttempts() {
		return quizAttempts; 
	}
	
	public int getSumActualScores() {
		return sumActualScores; 
	}
	
	public int getSumPossibleScores() {
		return sumPossibleScores; 
	}
	
	public int getUserAttempts() {
		return userAttempts; 
	}
}
