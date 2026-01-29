=== CUSTOMER ONBOARDING SYSTEM ===

=== Creating Customers ===
Customer 1 created with default constructor
Customer 2 created with partial constructor (chained)
Customer 3 created with full constructor

==================================================

Customer 2 (Rahul Sharma)
------------------------------
Full Name: RAHUL SHARMA
Email Valid: false (NOT_PROVIDED)
Phone Valid: false (NOT_PROVIDED)
Country Allowed: false (NOT_PROVIDED)
Customer ID: 101
First Name: Rahul
Last Name: Sharma
Email: NOT_PROVIDED
Phone: NOT_PROVIDED
Country: NOT_PROVIDED
First character of first name: R
First character of last name: S
Name Analysis Completed
------------------------------

Customer 3 (Priya Patel)
------------------------------
Full Name: PRIYA PATEL
Email Valid: true (priya.patel@company.com)
Phone Valid: true (9876543210)
Country Allowed: true (India)
Customer ID: 102
First Name: Priya
Last Name: Patel
Email: priya.patel@company.com
Phone: 9876543210
Country: India
First character of first name: P
First character of last name: P
Name Analysis Completed
------------------------------

Customer 4 (John Doe)
------------------------------
Full Name: JOHN DOE
Email Valid: true (john.doe@gmail.com)
Phone Valid: false (5551234567)
Country Allowed: true (USA)
Customer ID: 103
First Name: John
Last Name: Doe
Email: john.doe@gmail.com
Phone: 5551234567
Country: USA
First character of first name: J
First character of last name: D
Name Analysis Completed
------------------------------

=== Demonstrating String Immutability ===
Original String: "Hello World"
Original hashcode: 1482968390

After toUpperCase(): "HELLO WORLD"
New string hashcode: 349885916
Original unchanged: "Hello World"

After replace('o', '0'): "Hell0 W0rld"
New string hashcode: 414493378
Original unchanged: "Hello World"

After concat("!!!"): "Hello World!!!"
New string hashcode: 1984697014
Original unchanged: "Hello World"

str1 = "Java", str2 = "Java"
Same object? true
After str1 = str1.toUpperCase():
str1 = "JAVA", str2 = "Java"
Same object? false

=== Name Analysis Examples ===

=== Name Analysis for: Rahul Sharma ===
Lowercase: rahul sharma
Hyphenated: Rahul-Sharma
Name parts (2):
  Part 1: Rahul
  Part 2: Sharma
Original name length: 12
First character: R
Last character: a
Vowel count: 4

=== Name Analysis for: Priya Patel ===
Lowercase: priya patel
Hyphenated: Priya-Patel
Name parts (2):
  Part 1: Priya
  Part 2: Patel
Original name length: 11
First character: P
Last character: l
Vowel count: 4

=== Name Analysis for: John Michael Doe ===
Lowercase: john michael doe
Hyphenated: John-Michael-Doe
Name parts (3):
  Part 1: John
  Part 2: Michael
  Part 3: Doe
Original name length: 16
First character: J
Last character: e
Vowel count: 6
