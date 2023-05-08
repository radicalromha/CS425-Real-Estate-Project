# SPRING 2023 CS425 Real Estate Project

## Credit

The data gathered for this project were based off of the databases below:

* <https://www.kaggle.com/datasets/sushamnandi/customer-names-dataset>
* <https://www.kaggle.com/datasets/austinreese/usa-housing-listings>
* <https://www.kaggle.com/datasets/crawlfeeds/usa-housing-data-from-homescom>
* <https://www.kaggle.com/datasets/alexphoffman/big-city-land-values-and-walkscores>

The company names used for this project were generated from:
<https://theclose.com/real-estate-company-names/#real-estate-company-name-generator>

## Requirements

This project has the dependencies installed within the install folder. However, this project uses vscode's `launch.json` and `settings.json` in order to launch properly, therefore you need to have VSCode and the [Extension Pack For Java extension](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) in order for this project to run properly.

## Here is the link for the Demonstration video

https://drive.google.com/file/d/159wn1rbD3ixVVMygW2rOu2GwNfIoHZda/view?usp=share_link


## Instructions

### SQL Script

Run the `SchemaAndData.sql` file in `src/javaApp/` to generate the tables necessary for this program to run. It will also populate the tables with data.

### Java App

Go to `src/javaApp/jdbcAccessor.java` and find the constructor method jdbcAccessor(). From there, edit the url, username, and password for your SQL server. Then, launch the app through the "Run and Debug" window on the left side, or through `Ctrl + Shift + D` on default.

### Creating Users

On launch, the screen will show two buttons, a `Sign Up` Button, and A `Log In` Button. Click On `Sign Up` to begin creating a user. Once on the next screen, you can choose to create a real estate agent account, or a prospecting renter account. Fill in the data as asked. In order to add a credit card to the account however, you need to copy one of the addresses you put in the `Add Addresses Here` prompt, or else the program will not let you add a credit card. Once completed, you will be sent back to the main menu, where you can log in using the email you used to create the account.

### User's Abilities

Both Agents and Renters are provided the ability to search for properties, however, Agents are only able to create, modify, or delete properties, whereas Renters are provided the ability to change their addresses and card information. On the Renter's page after logging in, one will be greeted by three selections; `Search Properties`, `Manage Your Account`, and `View Bookings`. The Agents will see `Search Properties` and `Create Property`. 

#### Searching Properties

On the Search window, a user has a variety of different ways to search for properties. Once the user presses `Search`, a popup will appear with a list of properties that fit the search description. However, if no properties can be found, the menu will notify the user.

#### Viewing Property Details

Once a user has searched for property, they can interact with the table to get more information about the property, such as price and the price of rent.

##### Renters

As a Renter, a user can book a property, using one of their credit cards to do so. Furthermore, if a Renter chose to receive reward points for booking properties, they will be provided with reward points equal to the rent price of the property. 

##### Agents

As an Agent, clicking on a property will show a button to modify the description of the property. __However, the table will not refresh until the window has been closed and reopened.__
