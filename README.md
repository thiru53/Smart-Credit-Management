# Smart-Credit-Management
In this project you will showcase your programming and logical skills in developing a robust Credit Management API. The project demonstrates your ability to create a comprehensive and reliable solution for effective credit management and lending operations.

# Project Description
The Credit Management API project is an application that provides loan management functionality. It allows users to apply for loans, track loan repayment status, and dynamically adjust credit limits based on borrower performance. The API handles loan applications, repayment transactions, penalty calculations, and updates credit limits accordingly. It offers features such as loan application approval, loan repayment tracking, penalty calculation, and automated credit limit adjustments based on borrower performance.

The purpose of this project is to evaluate your programming and logical skills. Each task comes with a predefined description and examples to assist you.

# Module 1

# Task 1: Dynamic Credit Limit Evaluation and Loan Application Approval
Perform the following tasks before API development:

Download the MySQL database by clicking on the provided link. CreditManagement.sql
Access your database information by clicking on the "Database Info" tab.
Set up the downloaded database in the provided database environment and code.
The goal is to process a loan application using POST endpoint "/loans/process" and determine if the loan can be approved for the given borrower ID and loan amount. The input for this task is a JSON object containing the borrower ID and loan amount.

    {      
        "borrowerId": "25",
        "loanAmount": 4000.0  
    }

The loan application process involves several checks to ensure that the loan can be granted. If the borrower ID is new (not found in the loan table), the credit limit is initially set to 5000. For subsequent loan applications from the same borrower, the credit limit is checked from the credit limit table.

The system then verifies if there are any existing unpaid loans with crossed repayment dates, which would prevent the loan from being approved. Additionally, the system checks if the loan amount exceeds the remaining credit limit available to the borrower.

To track credit limits, the "credit_limit" table is updated during the loan processing, which includes fields such as "borrowerId," "creditLimit," "usedAmount," and "remainingAmount.". After first time if borrower comes for loan the

Furthermore, the system employs a credit limit assessment based on borrower performance. This assessment is scheduled to occur at midnight on the first day of every month. The CreditLimitScheduler class handles this periodic task by invoking the assessBorrowerPerformanceAndUpdateCreditLimit method from the LoanService for each borrower.

If all the validation checks pass, the loan application is approved, and saved in the  "loan" table, which has fields 'borrowerId','loanAmount','loanDate','repaymentDate'(after one month date) ,'paymentStatus'(initially as not paid) and 'paidDate' .

The response is returned in JSON format :

    {     
        "message": "Loan application approved and transaferred",     
        "status": "success"
    }

Upon successfull approval of loan , the system retrieves the loan details from the loan table and saves them as a new payment transaction entry in the payment_transaction table. The payment details include the loan ID, loan amount, penalty amount, total amount, repayment date, paid on date, and payment status.

Additionally, if the paid on date crosses the repayment date, the system calculates the penalty amount as loanAmount * penaltyRate * daysOverdue, where the penalty rate is 0.2%. This ensures that appropriate penalties are applied when the payment is overdue.

This task ensures that only eligible borrowers with appropriate credit limits and without any outstanding loans are granted loans, providing a secure and efficient loan management system.


# Task 2: Flexible Loan Data Retrieval for Efficient Loan Management
The goal is to retrieve loan data from the "loan" table using api. There are three ways to retrieve loan data:

Retrieve Loan Data by Loan ID: The GET endpoint "/loans/{loanId}" allows  to fetch loan details based on a specific loan ID. By providing the loan ID as input, the system will search the loan table and return the corresponding loan data if found. If the loan ID is not valid or does not exist in the loan table, an appropriate error message will be returned.

The response is returned in JSON format :

    {     
        "id": 1,     
        "borrowerId": "25",     
        "loanAmount": 4000.0,     
        "loanDate": "2023-07-13",     
        "repaymentDate": "2023-08-13",     
        "paymentStatus": "Not Paid",     
        "paidDate": null 
    }



Retrieve Loan Data by Borrower ID: The GET endpoint "/loans/borrowers/{borrowerId}" enables you to retrieve all loans associated with a particular borrower ID. By providing the borrower ID as input, the system will search the loan table for loans linked to that borrower ID and return the loan data if available. If the borrower ID does not have any loans in the loan table, a suitable message will be displayed.

The response is returned in JSON format :

    [ 
        {           
            "id": 1,           
            "borrowerId": "25",           
            "loanAmount": 4000.0,           
            "loanDate": "2023-07-13",           
            "repaymentDate": "2023-08-13",           
            "paymentStatus": "Not Paid",           
            "paidDate": null       
        } 
    ]



Retrieve All Loans: The GET endpoint "/loans" provides an approach allows you to obtain all loans stored in the system. The system will retrieve and return the complete list of loans from the loan table. If no loan data is found, a message indicating the absence of loan records will be displayed.

The response is returned in JSON format :

    [       
        {           
            "id": 1,           
            "borrowerId": "25",           
            "loanAmount": 4000.0,           
            "loanDate": "2023-07-13",           
            "repaymentDate": "2023-08-13",           
            "paymentStatus": "Not Paid",           
            "paidDate":  null       
        },
        {           
            "id": 2,           
            "borrowerId": "26",           
            "loanAmount": 1000.0,           
            "loanDate": "2023-07-13",           
            "repaymentDate": "2023-08-13",           
            "paymentStatus": "Not Paid",           
            "paidDate": null       
        } 
    ]

These retrieval methods provide flexibility in accessing loan data based on specific criteria. Whether you need loan details by loan ID, borrower ID, or all loans, the system ensures accurate data retrieval and appropriate responses to support loan management operations.

# Task 3: Interactive Display of Borrower Credit Limit Details
The goal is to display the credit limit details from the credit_limit table based on the borrower ID. This task enables retrieving and showcasing specific credit details for a particular borrower.

By providing the borrower ID as input to the GET endpoint "/loans/creditlimit/{borrowerId}", the system searches the credit_limit table to retrieve the corresponding credit limit details. The credit limit table contains essential fields such as "borrowerId," "creditLimit," "usedAmount," and "remainingAmount." These fields represent the borrower's credit limit, the amount utilized, and the remaining available credit limit.

If the borrower ID is found in the credit_limit table, the system displays the credit limit details, including the credit limit amount, the amount utilized, and the remaining available credit amount. This information offers insights into the borrower's current credit status and helps monitor their credit utilization.

The response is returned in JSON format :

    {     
        "id": 1,     
        "borrowerId": "25",     
        "creditLimit": 5000.0,     
        "usedAmount": 4000.0,     
        "remainingAmount": 1000.0 
    }

However, if the provided borrower ID is not present in the credit_limit table, an appropriate message is displayed to indicate that no credit limit data was found for the specified borrower ID. This message ensures clarity and informs users that credit details are not available for the given borrower:

    {     
        "message": "No credit limit data found for borrower ID: 27",     
        "status": "failure" 
    }

This task provides a convenient way to access and present credit limit information specific to a borrower, aiding in better credit management and monitoring.


# Task 4: Efficient Retrieval of Payment Transaction Details for Loan Management
The goal is to retrieve payment transaction details from the "payment_transaction" table based on the loan ID. This task involves a GET endpoint "/loans/paymentdetails/{loanId}" that returns the payment transaction details in a structured JSON format.

If the loan ID is not found in the payment_transaction table, the system displays the approprate message for the same. The payment details include the loan ID, loan amount, penalty amount, total amount, repayment date, paid on date, and payment status.

The response is returned in JSON format:

    {     
        "status": "success",     
        "message": "Payment transaction details retrieved successfully.",     
        "paymentTransaction": {          
            "id": 1,          
            "loanId": 1,          
            "loanAmount": 4000.0,          
            "penaltyAmount": 0.0,          
            "totalAmount": 4000.0,          
            "repaymentDate": "2023-08-13",          
            "paidOnDate": null,          
            "paymentStatus": "Not Paid"     
        } 
    }

The task also  keeps the system updating the existing payment transaction details based on the values obtained from the loan table. This ensures that the payment details are up to date and reflect any changes made in the penalty amount.

Additionally,while updating, if the paid on date crosses the repayment date, the system calculates the penalty amount as loanAmount * penaltyRate * daysOverdue, where the penalty rate is 0.2%. This ensures that appropriate penalties are applied when the payment is overdue.

The response of the GET endpoint includes a status indicating success, a message stating that the payment transaction details were retrieved successfully, and the payment transaction details in JSON format.

This task allows users to easily retrieve and view payment transaction details based on the loan ID, ensuring accurate and up-to-date information for loan management and payment tracking.

# Task 5: Loan Repayment Processing Endpoint
The goal is to process a loan repayment using the loan ID. This task involves a POST endpoint "/loans/paymentdetails/{loanId}/repayment" that takes the repayment amount as input and updates the corresponding loan and payment transaction records.

The endpoint should adhere to the following request JSON format:

    {   
        "repaymentAmount": 1000.0 
    }

The input for this task is a JSON object containing the repayment amount. By providing the loan ID in the endpoint URL and the repayment amount in the request body, the system processes the loan repayment as follows:

1)Retrieve the payment transaction details from the payment_transaction table based on the loan ID. 2)Validate if the repayment amount matches the total amount to be repaid. If not, return an appropriate response indicating that the full loan amount needs to be paid. 3)If the repayment amount matches, update the payment transaction record with the current date as the paid on date and set the payment status as "Paid". 4)Update the loan record with the current date as the paid date and set the payment status as "Paid". 5)Calculate and update the remaining credit limit in the credit_limit table by subtracting the loan amount from the current used amount. 6)Return a success response indicating that the loan repayment was successful.

The response is returned in JSON format :

    {     
        "message": "Loan repayment successful.",     
        "status": "success" 
    }

This task  enables borrowers to make repayments towards their loans, ensuring accurate updating of payment and credit records.




# Database Details
Host: localhost | Database: bac4353a
Username: bac4353a | Password: Cab#22se

https://projects.hicounselor.com/zootopia/tbl_sql.php