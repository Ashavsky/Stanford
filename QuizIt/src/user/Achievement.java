package user;

/**
 * Created by scottparsons on 3/1/16.
 */
public enum Achievement {

    AMATEUR_AUTHOR(1) {
        public String toString() {
            return "Amateur Author";
        }
    },

    PROLIFIC_AUTHOR(5) {
        public String toString() {
            return "Prolific Author";
        }
    },

    PRODIGIOUS_AUTHOR(10) {
        public String toString() {
            return "Prodigious Author";
        }
    },

    QUIZ_AMATEUR(1) {
        public String toString() {
            return "Quiz Amateur";
        }
    },

    QUIZ_MACHINE(10) {
        public String toString() {
            return "Quiz Machine";
        }
    },

    GREATEST(0) {
        public String toString() {
            return "I am the Greatest";
        }
    },

    PRACTICE_PERFECT(0) {
        public String toString() {
            return "Practice Makes Perfect";
        }
    };

    // Instance variables
    private int threshold;

    Achievement(int threshold) {
        this.threshold = threshold;
    }

    public int getThreshold() {
        return threshold;
    }

    public String getDescription() {
        switch (this) {
            case AMATEUR_AUTHOR:
                return new String("The user created a quiz");
            case PROLIFIC_AUTHOR:
                return new String("The user created five quizzes");
            case PRODIGIOUS_AUTHOR:
                return new String("The user created ten quizzes");
            case QUIZ_AMATEUR:
                return new String("The user took a quiz");
            case QUIZ_MACHINE:
                return new String("The user took 10 quizzes");
            case GREATEST:
                return new String("The user had the highest score on a quiz");
            case PRACTICE_PERFECT:
                return new String("The user took a quiz in practice mode");
            default:
                return new String("Invalid achievement");
        }
    }

}
