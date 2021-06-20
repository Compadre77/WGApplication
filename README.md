# WG Application

This repository contains the backend and the UI components of the WGApplication. It is based on Java, Springboot and Thymeleaf.

## Running
Users and sample data are added to the database when first running this Application. As a consequence, every user automatically 
gets and ID. Unfortunately, it can happen that the other existing ID's from the other tables don't match. 
We therefore, kindly ask you to do the following:

1. Table:TO_DO_USERIDS -> Change UserID's to match the one's in the user table
2. Table:TO -> Change UserID's to match the one's in the user table
3. Table:BILL_USERIDS -> Change UserID's to match the one's in the user table
4. Table:BILL -> Change UserID's to match the one's in the user table
  