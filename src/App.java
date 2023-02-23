import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;


public class App {

    static class Parcel {
        private String pin;
        private String houseNum;
        private String street;
        private String name;
        private Double marketValue;
        private String saleDate;
        private Double salePrice;
        private String link;

        public Parcel(String pin, String houseNum, String street, String name, Double marketValue, String saleDate, Double salePrice, String link) {
            this.pin = pin;
            this.houseNum = houseNum;
            this.street = street;
            this.name = name;
            this.marketValue = marketValue;
            this.saleDate = saleDate;
            this.salePrice = salePrice;
            this.link = link;
        }


        //Getters and Setters
        public String getStreet() {
            return street;
        }

        public void setStreet(String newStreet) {
            this.street = newStreet;
        }

        public String getHouseNum() {
            return houseNum;
        }

        public void setHouseNum(String newHouseNum) {
            this.street = newHouseNum;
        }

    }


    public static void main(String[] args) throws Exception {
        
        //Creates BufferedReader wrapping FileReader to pull data from provided text file
        BufferedReader reader = new BufferedReader(new FileReader("Parcels.txt"));

        //Creates an empty ArrayList to store parcels
        ArrayList<Parcel> parcelList = new ArrayList<Parcel>();

        //Reads a single line at a time
        String currentLine = reader.readLine();

        //Advances one line to skip past the 1st line key in provided text file
        currentLine = reader.readLine();

        //Loops through the line length of the provided text document
        while (currentLine != null) {

            //Splits the currentLine at each "|" and adds each piece of data into an array
            String[] parcelFragment = currentLine.split("\\|");

            //Sets each of the fragment fields to a temporary variable
            String pin = parcelFragment[0];
            String address = parcelFragment[1];
            String name = parcelFragment[2];
            Double marketValue = Double.parseDouble(parcelFragment[3]);
            String saleDate = parcelFragment[4];
            Double salePrice = Double.parseDouble(parcelFragment[5]);
            String link = parcelFragment[6];

            //Further divides the address into house number and street name
            String[] addressFragment = address.split(" ");
            String houseNum = "";
            String street = "";

            //Checks if there is a letter included in the house number, if there is, includes it with house number
            if (addressFragment[1].length() == 1) {
                houseNum = addressFragment[0] + " " + addressFragment[1];
                for (int i = 2; i < addressFragment.length; i++){
                    street = String.join(" ", street, addressFragment[i]);
                }
            }
            else {
                houseNum = addressFragment[0];
                for (int i = 1; i < addressFragment.length; i++){
                    street = String.join(" ", street, addressFragment[i]);
                }
            }


            //Adds a complete line to the ArrayList using parcelFragments turned temp variables
            parcelList.add(new Parcel(pin, houseNum, street, name, marketValue, saleDate, salePrice, link));
            
            //System.out.println(parcelFragment[1]);

            //Advances the reader to the next line
            currentLine = reader.readLine();
        }


        parcelList.sort(Comparator.comparing(Parcel::getStreet).thenComparing(Parcel::getHouseNum));
        
        //Prints out the reordered parcelList
        for (Parcel parcel : parcelList) {
            System.out.println(parcel.pin + " | " + parcel.houseNum + " " + parcel.street + " | " + parcel.name);
        }

        reader.close();
    }
}

//"https://www.google.com/maps/place/" + parcel.road + "Mazama+WA"
