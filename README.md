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
 
 # Possible features currently not yet implemented
 1. Duplicate entries are not dealt with. ie:
   
  ```
  6010400001|11 SUSAN RD|PETERSON, RUDOLPH & LORNA|211600.00|4/9/1984|4600.00|http://okanoganwa.taxsifter.com/Search/results.aspx?q=6010400001 |     https://www.google.com/maps/place/11+SUSAN+RD+Mazama+WA
6010400001|11 SUSAN RD|PETERSON, RUDOLPH & LORNA|211600.00|4/9/1984|4600.00|http://okanoganwa.taxsifter.com/Search/results.aspx?q=6010400001 | https://www.google.com/maps/place/11+SUSAN+RD+Mazama+WA
```
    
2. Does not correct for possible address typos. ie:
    
    
```
6040603900|9 MISTY CIR 0|PLEMEL, NICHOLAS & RACHAEL|45000.00|1/22/2022|27000.00|http://okanoganwa.taxsifter.com/Search/results.aspx?q=6040603900
```

