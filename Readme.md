# java streams sandbox
--------------------

Java 8 Streams sandbox

## Input (people.csv)
```
firstname,lastname,email
 dhaval,patel,dhaval.p@intact.net
 krassimira,draguieva,krassi.d@intact.net
 jeff,song,jeff.s@intact.net
 amany,toeama,amany.t@intact.net
 appal,kurupati,appal.k@intact.net
 kannan,muthiah,kannan.m@intact.net
 sean,graham,sean.graham@intact.net
```

## Output
```
Step 1. Read CSV file and Serialize to DTO (Person) as array list
---------------
Step 2. Everytime program runs based on random order of the (Person) as array list
---------------
Step 3. (Non-unique) Key-value pair of 2 entries
---------------
 jeff song -  amany toeama
 amany toeama -  krassimira draguieva
 krassimira draguieva -  kannan muthiah
 kannan muthiah -  appal kurupati
 appal kurupati -  dhaval patel
 dhaval patel -  sean graham
---------------
Step 3. (Unique) Key-value pair of 2 entries
---------------
 jeff song -  amany toeama
 krassimira draguieva -  kannan muthiah
 appal kurupati -  dhaval patel
 sean graham
```
