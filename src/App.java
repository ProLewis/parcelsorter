import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;


public class App {

    static class Parcel {
        private String fullListing;
        private int houseNum;
        private String houseLetter;
        private String street;
        private String firstName;
        private String lastName;


        public Parcel(String fullListing, int houseNum, String houseLetter, String street, String firstName, String lastName) {
            this.fullListing = fullListing;
            this.houseNum = houseNum;
            this.houseLetter = houseLetter;
            this.street = street;
            this.firstName = firstName;
            this.lastName = lastName;
        }


        //Getters and Setters
        public String getStreet() {
            return street;
        }

        public void setStreet(String newStreet) {
            this.street = newStreet;
        }

        public int getHouseNum() {
            return houseNum;
        }

        public void setHouseNum(int newHouseNum) {
            this.houseNum = newHouseNum;
        }

        public String getHouseLetter() {
            return houseLetter;
        }

        public void setHouseLetter(String newHouseLetter) {
            this.houseLetter = newHouseLetter;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String newFirstName) {
            this.firstName = newFirstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String newLastName) {
            this.lastName = newLastName;
        }
    }


    public static void main(String[] args) throws Exception {
        
        //Creates BufferedReader wrapping FileReader to pull data from provided text file
        BufferedReader reader = new BufferedReader(new FileReader("Parcels.txt"));

        //Creates an empty ArrayList to store parcels
        ArrayList<Parcel> parcelList = new ArrayList<Parcel>();

        //Reads a single line at a time
        String currentLine = reader.readLine();

        String columnKey = currentLine;
        //Advances one line to skip past the 1st line key in provided text file
        currentLine = reader.readLine();

        //Loops through the line length of the provided text document
        while (currentLine != null) {
            //Stores the complete line of text from the provided document for reprinting after it has been sorted
            String fullListing = currentLine;

            //Splits the currentLine at each "|" and adds each piece of data into an array
            String[] parcelFragment = currentLine.split("\\|");

            //Sets the address and name for further splitting
            String address = parcelFragment[1];
            String name = parcelFragment[2];

            //Further divides the address into house number and street name
            String[] addressFragment = address.split(" ");
            int houseNum = Integer.parseInt(addressFragment[0]);
            String houseLetter = "";
            String street = "";

            //Checks if there is a house letter included in the address, sets it if there is one and joins the street name together
            if (addressFragment[1].length() == 1) {
                houseLetter = addressFragment[1];
                for (int i = 2; i < addressFragment.length; i++){
                    street = String.join(" ", street, addressFragment[i]);
                }
            }
            else {
                for (int i = 1; i < addressFragment.length; i++){
                    street = String.join(" ", street, addressFragment[i]);
                }
            }


            //Further divides the name into first and last. Business names will just have a 'first' name
            String[] nameFragment = name.split(",");
            String lastName = "";
            String[] firstNames;
            String firstName = "";

            //Checks if it is a person or business, based on precsence of a comma. Sets first name to either just the first person listed or the whole business name
            if (nameFragment.length > 1) {
                firstNames = nameFragment[1].trim().split(" ");
                firstName = firstNames[0];
                lastName = nameFragment[0];
            }
            else {
                firstName = nameFragment[0];
            }


            //Adds a complete line to the ArrayList using parcelFragments turned temp variables
            parcelList.add(new Parcel(fullListing, houseNum, houseLetter, street, firstName, lastName));

            //Advances the reader to the next line
            currentLine = reader.readLine();
        }

        //Sorts by street, then house number, then house letter
        //parcelList.sort(Comparator.comparing(Parcel::getStreet).thenComparing(Parcel::getHouseNum).thenComparing(Parcel::getHouseLetter));

        //Sorts by first name and then last name. Businesses will only have a 'first' name
        parcelList.sort(Comparator.comparing(Parcel::getFirstName).thenComparing(Parcel::getLastName));
        
        //prints the key from the provided text file
        System.out.println(columnKey);

        //Prints out the reordered parcelList
        for (Parcel parcel : parcelList) {
            System.out.println(parcel.fullListing + " | " + "https://www.google.com/maps/place/" + parcel.houseNum + parcel.houseLetter.replaceAll("\\W", "+") + parcel.street.replaceAll("\\W", "+") + "+Mazama+WA");
        }

        reader.close();
    }
}

