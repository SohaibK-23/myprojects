package assignment4;


/**
 * Object representation of Tweets
 * You should not need to modify this class
 * If you feel like you do so, ask on piazza before proceeding
 */

public class Tweets
    {
        private static final String NULL = "";   //empty string
        private int Id;
        private final long MAXIMUM= 42949676;

        public int getId()
        {

            if(Id > MAXIMUM)
            {
                Id = 0;
            }

            return this.Id;
        }

        public void setId(int Id)
        {
            this.Id = Id;
        }

        private String Name;

        public String getName()
        {
            if(Name == null)         //Making sure whether the name, date and text were null
            {                        //if they were, I just set them equal to the NULL string which is just an empty string
                Name = NULL;
            }

            return this.Name;
        }

        public void setName(String Name)
        {

            this.Name = Name;
        }

        private String Date;

        public String getDate()
        {
            if(Date == null)
            {
                Date = NULL;
            }
            return this.Date;
        }

        public void setDate(String Date)
        {

            this.Date = Date;
        }

        private String Text;

        public String getText()
        {
            if(Text == null)
            {
                Text = NULL;
            }

            return this.Text;
        }

        public void setText(String Text)
        {
            this.Text = Text;
        }

        public Tweets(){}          //Constructor for the tweet object

        @Override public String toString() {
            return "(" + this.getId()
                    + " " + this.getName().toString()
                    + " " + this.getDate()
                    + ") " + this.getText();
        }
    }


