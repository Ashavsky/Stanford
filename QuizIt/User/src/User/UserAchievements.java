package User;

/**
 * Created by Alex on 3/3/2016.
 */
public class UserAchievements
{
    public enum Achievements {
        AMATEUR_AUTHOR {
            public String toString() {
                return "Amateur Author";
            }
        },

        PROLIFIC_AUTHOR {
            public String toString() {
                return "Prolific Author";
            }
        },

        PRODIGIOUS_AUTHOR {
            public String toString() {
                return "Prodigious Author";
            }
        },

        QUIZ_MACHINE {
            public String toString() {
                return "Quiz Machine";
            }
        },

        GREATEST {
            public String toString() {
                return "I am the Greatest";
            }
        },

        PRACTICE_PERFECT {
            public String toString() {
                return "Practice Makes Perfect";
            }
        }
    }
}
