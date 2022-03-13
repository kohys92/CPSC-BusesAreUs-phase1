# UBC-CPSC210-BusesAreUs-phase1

## Overview

For the project you will complete an Android application called Buses Are Us. This application maps the location of stops, buses and bus routes on the Greater Vancouver Transit system (Translink) and retrieves real time arrival information at those stops. It also plots the user's location on the map and highlights the nearest bus stop. To do this, the app makes use of TransLink Open API - a service that is provided free of charge.

Sample images to indicate what the final product might look like are provided here. Be sure to study them carefully as they provide an overview of the basic functionality that is expected of the application.

![image](https://user-images.githubusercontent.com/36935693/158075835-eedce0d6-ac20-4023-96c5-ddfa82a504d9.png)

Application startup. Notice that stops that are close together are clustered. The number in the middle of the blue icons indicates the number of stops clustered at that point. When the user's location can be determined, it is plotted on the map. Assuming the user is less than 10km away from a stop, the name of the stop appears at the bottom of the application's main window and the icon representing the nearest stop is coloured green (assuming it is not clustered).

![image](https://user-images.githubusercontent.com/36935693/158075864-ba8a8e15-22c8-4baf-9cba-cfa609c4e9c5.png)

When the user taps a stop icon (not a clustered icon), an information window appears showing the stop number, name, and the bus routes that stop at this stop. All route patterns of all routes that stop at this stop are drawn, with a legend indicating which route is in which colour in the top right corner of the map.  Further, the locations of buses serving this stop are downloaded from Translink and plotted onto map.  When the user taps anywhere else on the map, this information window is closed.  The bus routes and buses that stop at the selected stop continue to be drawn until a new stop is selected.

![image](https://user-images.githubusercontent.com/36935693/158075880-913fea7d-7f67-43da-b202-d5d87b7994b2.png)

When the user taps the question mark icon in a stop information window (see above), a list of the arrivals expected at the stop is presented to the user, in the order in which they are estimated to arrive at the stop.
