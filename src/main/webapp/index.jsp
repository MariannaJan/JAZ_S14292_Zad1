<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loan Calculator</title>
</head>
<body>

<h1>Kalkulator rat kredytu</h1></br>


<form name="loanC" action="loan" method="post">

<table>

<tr><td>Wnioskowana kwota kredytu </td><td><input type="text" name="wantedLoan" /></td></tr> 

<tr><td>Liczba rat </td><td><input type="text" name="numofPayments" /></td></tr> 

<tr><td>Oprocentowanie [%]</td><td> <input type="text" name="interestRate"/></td></tr> 

<tr><td>Oplata stala</td><td> <input type="text" name="fixedFee"/> </td></tr>

<tr><td>Rodzaj rat: </td><td></td></tr>
<tr><td>Malejace </td><td><input type="radio" name="paymentType" value="diminishing" checked/></td></tr>
<tr><td>Stale </td><td><input type="radio" name="paymentType" value="equal"/></td></tr>

<tr><td><input type="reset" value="Wyczysc" name="reset"/></td>
<td><input type="submit" value="Zatwierdz" name="ok"/></td></tr>

</table>

</form>
</body>
</html>