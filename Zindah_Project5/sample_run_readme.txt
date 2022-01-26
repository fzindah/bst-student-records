Farha Zindah (EID: E02267498)
COSC 311 - Project 5 sample code
12/6/2021

Index successfully changed to implement hash table using BST.

Tested all menu options, entered invalid records for a few to show 
how they are handled. Relevant menu options send out warning when random-access file 
does not exist, or index not built.


___________________________________________________
	Welcome to the Student Records
___________________________________________________

			Menu
---------------------------------------------------
Would you like to:

1) Make a random-access file		6) Modify a Record
2) Display random-access file		7) Add New Record
3) Build Index				8) Delete a Record
4) Display Index			9) Exit
5) Retrieve a Record

1
Enter an input text file name: students.txt
Enter random-access file name: raf
You have created a random access file named raf!

			Menu
---------------------------------------------------
Would you like to:

1) Make a random-access file		6) Modify a Record
2) Display random-access file		7) Add New Record
3) Build Index				8) Delete a Record
4) Display Index			9) Exit
5) Retrieve a Record

2
Type file name to display: 
raf
Phineas             Flynn               1111      2.4       
Ferb                Fletcher            2222      3.4       
Perry               Platypus            3333      3.2       
Dr.                 Doof                4444      1.5       
Isabella            Donnow              5555      4.2       

Return to Main Menu (M), Next Screen (N), Display All (A)
a
All Records:
Candace             Flynn               6666      2.4       
Vanessa             Doof                7777      3.2       
Baljeet             Winnows             8888      2.5       
Beauford            Bully               9999      2.2       
Mary                Flynn               1000      3.2       
Jeremy              Johnson             900       3.7       
Tom                 Flynn               2000      4.2       
Anne                Gables              3000      3.2       

			Menu
---------------------------------------------------
Would you like to:

1) Make a random-access file		6) Modify a Record
2) Display random-access file		7) Add New Record
3) Build Index				8) Delete a Record
4) Display Index			9) Exit
5) Retrieve a Record

3
Enter file name to build index: 
raf
Index built!

			Menu
---------------------------------------------------
Would you like to:

1) Make a random-access file		6) Modify a Record
2) Display random-access file		7) Add New Record
3) Build Index				8) Delete a Record
4) Display Index			9) Exit
5) Retrieve a Record

4

a) Display All
b) Display Part
a
0: (8888,7) 
7: (3333,2) 
9: (4444,3) 
11: (2222,1) 
12: (7777,6) 
14: (1000,9) 
(900,10) 
16: (5555,4) 
20: (3000,12) 
21: (1111,0) 
(2000,11) 
30: (6666,5) 
(9999,8) 

			Menu
---------------------------------------------------
Would you like to:

1) Make a random-access file		6) Modify a Record
2) Display random-access file		7) Add New Record
3) Build Index				8) Delete a Record
4) Display Index			9) Exit
5) Retrieve a Record

4

a) Display All
b) Display Part
b
Enter starting index value: 
5
Enter ending index value: 

20
7: (3333,2) 
9: (4444,3) 
11: (2222,1) 
12: (7777,6) 
14: (1000,9) 
(900,10) 
16: (5555,4) 

			Menu
---------------------------------------------------
Would you like to:

1) Make a random-access file		6) Modify a Record
2) Display random-access file		7) Add New Record
3) Build Index				8) Delete a Record
4) Display Index			9) Exit
5) Retrieve a Record

5
Please enter a student id: 3333
Perry               Platypus            3333      3.2       

			Menu
---------------------------------------------------
Would you like to:

1) Make a random-access file		6) Modify a Record
2) Display random-access file		7) Add New Record
3) Build Index				8) Delete a Record
4) Display Index			9) Exit
5) Retrieve a Record

6
Please enter a student id: 3333
Perry               Platypus            3333      3.2       

Modify: 
(f) First Name
(l) Last Name
(g) GPA
Type (m) to return to menu

g
New gpa: 3.6

Modify: 
(f) First Name
(l) Last Name
(g) GPA
Type (m) to return to menu

m

			Menu
---------------------------------------------------
Would you like to:

1) Make a random-access file		6) Modify a Record
2) Display random-access file		7) Add New Record
3) Build Index				8) Delete a Record
4) Display Index			9) Exit
5) Retrieve a Record

7
Please enter data in this format: fName lName id gpa
new name 1001 1.0

			Menu
---------------------------------------------------
Would you like to:

1) Make a random-access file		6) Modify a Record
2) Display random-access file		7) Add New Record
3) Build Index				8) Delete a Record
4) Display Index			9) Exit
5) Retrieve a Record

2
Type file name to display: 
raf
Phineas             Flynn               1111      2.4       
Ferb                Fletcher            2222      3.4       
Perry               Platypus            3333      3.6       
Dr.                 Doof                4444      1.5       
Isabella            Donnow              5555      4.2       

Return to Main Menu (M), Next Screen (N), Display All (A)
a
All Records:
Candace             Flynn               6666      2.4       
Vanessa             Doof                7777      3.2       
Baljeet             Winnows             8888      2.5       
Beauford            Bully               9999      2.2       
Mary                Flynn               1000      3.2       
Jeremy              Johnson             900       3.7       
Tom                 Flynn               2000      4.2       
Anne                Gables              3000      3.2       
new                 name                1001      1.0       

			Menu
---------------------------------------------------
Would you like to:

1) Make a random-access file		6) Modify a Record
2) Display random-access file		7) Add New Record
3) Build Index				8) Delete a Record
4) Display Index			9) Exit
5) Retrieve a Record

8
Please enter a student id: 1001
new                 name                1001      1.0       
Record deleted

			Menu
---------------------------------------------------
Would you like to:

1) Make a random-access file		6) Modify a Record
2) Display random-access file		7) Add New Record
3) Build Index				8) Delete a Record
4) Display Index			9) Exit
5) Retrieve a Record

2
Type file name to display: 
raf
Phineas             Flynn               1111      2.4       
Ferb                Fletcher            2222      3.4       
Perry               Platypus            3333      3.6       
Dr.                 Doof                4444      1.5       
Isabella            Donnow              5555      4.2       

Return to Main Menu (M), Next Screen (N), Display All (A)
a
All Records:
Candace             Flynn               6666      2.4       
Vanessa             Doof                7777      3.2       
Baljeet             Winnows             8888      2.5       
Beauford            Bully               9999      2.2       
Mary                Flynn               1000      3.2       
Jeremy              Johnson             900       3.7       
Tom                 Flynn               2000      4.2       
Anne                Gables              3000      3.2       

			Menu
---------------------------------------------------
Would you like to:

1) Make a random-access file		6) Modify a Record
2) Display random-access file		7) Add New Record
3) Build Index				8) Delete a Record
4) Display Index			9) Exit
5) Retrieve a Record

9
Program Terminated. Display Index:
________________________________________
0: (8888,7) 
7: (3333,2) 
9: (4444,3) 
11: (2222,1) 
12: (7777,6) 
14: (1000,9) (900,10) 
16: (5555,4) 
20: (3000,12) 
21: (1111,0) (2000,11) 
30: (6666,5) (9999,8) 
