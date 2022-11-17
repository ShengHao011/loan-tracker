# My Personal Project - *Loan Tracker*

## Description:
- This application is a loan tracker for users to create loans and access their details when needed.
- The application can produce loan details of 2 types of loans: 
  **Pure discount loans, Amortized Loans with equal payment**. 
  Details of these loans include present value, future value, total payment, interest paid, and principle paid. 
This is achieved by applying DCF and annuities concepts to calculate desired information.
- This application is for users to access details of their loan. They can then 
  make decisions regarding how much money should they be paying every period, and the amount of loan left to be paid.
- This project is of interest for me because I'm in business and work with loans a lot.
  There are a lot of calculations involved and having an application with the calculations implemented would be useful.
  It also allows me to add extra components in the future as I learn more about the in depth details of loans.

### *User Stories*
- As a user, I want to add a certain type of loan to a list of loan.
- As a user, I want view the list of loans that I currently have.
- As a user, I want to be able to remove a loan from a list of loans.
- As a user, I want to choose a loan and view the details of that specific loan. 
- As a user, I want to save my list of loans to file.
- As a user, I want to load my list of loans from file.

#### Instructions for Grader
- You can generate the first required event related to adding Xs to a Y by entering the details of your loan
in the 4 text fields and then clicking the *Add Pure Discount Loan* button or *Add Amortized Loan* button
depending on the type of loan. This will add the loan to the list of loans displayed on the right panel.
- You can generate the second required event related to adding Xs to a Y by selecting a loan from the 
list of loan panel on the right, and then clicking the *Delete Selected Loan* button. This will remove 
the loan from your list of loans.
- You can locate my visual component by selecting a loan from the list of loans, then clicking the
*View Selected Loan Details* button. This will display a table that contains loan details specific 
to the type of loan selected and an image. 
- You can save the state of my application by clicking the *Save* button. This will save your current
list of loans to file.
- You can reload the state of my application by clicking the *Load* button. This will load your last 
saved list of loans to your current list of loans.
 

    
  