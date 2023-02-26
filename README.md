<div align ="center">
 
# parcelsorter
 
A tool that reads and sorts text files
 </div>

The tool currently has 2 ways to sort:
 By Street name, then street number, followed by street letter.
 By first name, then last name. Businesses will only be considered as having a first name.
 
 The tool also provides a link to the parcel in Google Maps.

The sorting method can be changed by commenting out either one of the sorting functions on lines 143 or 146 and then compiling and running App.java:

```
        //Sorts by street, then house number, then house letter
        parcelList.sort(Comparator.comparing(Parcel::getStreet).thenComparing(Parcel::getHouseNum).thenComparing(Parcel::getHouseLetter));

        //Sorts by first name and then last name. Businesses will only have a 'first' name
        //parcelList.sort(Comparator.comparing(Parcel::getFirstName).thenComparing(Parcel::getLastName));
 ```
 
