# InviteMe [![CircleCI](https://circleci.com/gh/ritsam/InviteMe.svg?style=shield)](https://app.circleci.com/pipelines/github/ritsam/InviteMe)


InVite me is an Application that acts as a solution to Programming test

# Instrcution to Run and Test the Project
1. Download the Zip or Clone the Projectand Open in Android studio
2. Once Installed, just run the application
3. The Customers should be sorted by their GeoGraphical location and should be populated in a list.

# Key Files and their purpose

● **CustomerRepo** - Acts as a Repository and also a black box that processes plain text and provides us with a MutableLivedata that contains the List of the Customer Objct

● **GPSGreatCircleUtil** - takes radius of the planet and customer geoLocation information as paramters and returs the distance from the default location

● **Constants** - Earth radius, search radius, default (office) location etc is provided by this object. 

● **MainViewModel** - performs data in the background making the Fragment (UIThread()) truly lightweight and independant. 

● **LatLong** - Custom data class to hold lat and long values

● **Customer** - Model data class for Customers

## Screenshots
![gif-InviteMe](https://user-images.githubusercontent.com/54317407/119280848-1e209700-bc2b-11eb-8402-0ccf6ecb5b97.gif)


## Motivation
Lightweight feature focused App targeted towards a company loiking to invite customers for their events based on customer's geolocation. Distance is calculated with the Great Circle distance formulae

## Problem Statement
We have some customer records in a text file (customers.txt) -- one customer per line, JSON lines formatted. We want to invite any customer within 100km of our Dublin office for some food and drinks on us. Write a program that will read the full list of customers and output the names and user ids of matching customers (within 100km), sorted by User ID (ascending).

● You must use the first formula from this Wikipedia article to calculate distance. Don't forget, you'll need to convert degrees to radians.

● The GPS coordinates for our Dublin office are 53.339428, -6.257664.

● Use the Web API listed below to access the customer list

## Important Libraries / Methodologies
### Web API
[Great-circle distance](https://en.wikipedia.org/wiki/Great-circle_distance)

[Get Customers Data](https://s3.amazonaws.com/intercom-take-home-test/customers.txt)

### Methodology
Retrofit, MVVM Architecture, Repository, Unit Testing


## Output File (Used Logs to generate the valid Customer list)
---
**OUTPUT FILE**
 We have 21 customers in 100 Km radius
 
 User Id : 4 Name: Ian Kehoe
 
 User Id : 5 Name: Nora Dempsey
 
 User Id : 6 Name: Theresa Enright
 
 User Id : 7 Name: Frank Kehoe
 
 User Id : 8 Name: Eoin Ahearn
 
 User Id : 11 Name: Richard Finnegan
 
 User Id : 12 Name: Christina McArdle
 
 User Id : 13 Name: Olive Ahearn
 
 User Id : 15 Name: Michael Ahearn
 
 User Id : 17 Name: Patricia Cahill
 
 User Id : 20 Name: Enid Enright
 
 User Id : 23 Name: Eoin Gallagher
 
 User Id : 24 Name: Rose Enright
 
 User Id : 25 Name: David Behan
 
 User Id : 26 Name: Stephen McArdle
 
 User Id : 27 Name: Enid Gallagher
 
 User Id : 28 Name: Charlie Halligan
 
 User Id : 29 Name: Oliver Ahearn
 
 User Id : 30 Name: Nick Enright
 
 User Id : 31 Name: Alan Behan
 
 User Id : 39 Name: Lisa Ahearn
 

---

## Features
#### Schrollable List
The data is processed by the valid search radius, then sorted in ascending order and displayed in a list powered by RecyclerView for Android

## Bug Reporting
Log your issues [Here](https://github.com/ritsam/InviteMe/issues)

## Future development that I intended to do, but have to keep it minimum to satisfy the problem statement
1. Add Pull to refresh layout, loading dialog / ProgressBar / More beautification
2. Phone data / Wifi not available notification
3. Lazy Loading for too large of a data
4. Improved Animations and UI polishing

## License
MIT © [RitSam](https://github.com/ritsam)
