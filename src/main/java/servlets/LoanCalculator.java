package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/loan")
public class LoanCalculator extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{
	doPost(request,response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		Double wantedLoan=null;
		Integer numofPayments=null;
		Double interestRate =null;
		Double fixedFee = null;
		
		try {
			wantedLoan = Double.parseDouble(request.getParameter("wantedLoan"));
			numofPayments = Integer.parseInt(request.getParameter("numofPayments"));
			interestRate = Double.parseDouble(request.getParameter("interestRate"));
			fixedFee = Double.parseDouble(request.getParameter("fixedFee"));
			String paymentType = request.getParameter("paymentType");
			double monthlyInterestRate = interestRate/12;
			
			response.setContentType("text/html");
			response.getWriter().println("<h1>Harmonogram splaty</h1>");
			response.getWriter().println("<Table border='1'><tr><th>Numer raty</th><th>Kwota kapitalu</th><th>Kwota odsetek</th><th>Oplaty stale</th><th>Calkowita kwota raty</th></tr>");
			
			double capitalAmount=BigDecimal.valueOf(wantedLoan/numofPayments).setScale(2, RoundingMode.HALF_UP).doubleValue(); //for diminishing payments
			double monthlyFixedFee=BigDecimal.valueOf(fixedFee/numofPayments).setScale(2, RoundingMode.HALF_UP).doubleValue();
			
			double b=1+monthlyInterestRate; //for equal payments
			double annuity = BigDecimal.valueOf(wantedLoan*Math.pow(b,numofPayments)/Math.pow(b,numofPayments-1 )).setScale(2, RoundingMode.HALF_UP).doubleValue();
			
			for (int i=1;i<=numofPayments;i++){
				
				if (paymentType.equals("diminishing"))
				{
					double interestAmount=BigDecimal.valueOf(0.01*monthlyInterestRate*wantedLoan).setScale(2, RoundingMode.HALF_UP).doubleValue();
					double totalPayment= BigDecimal.valueOf(capitalAmount + interestAmount + monthlyFixedFee).setScale(2, RoundingMode.HALF_UP).doubleValue();
					response.getWriter().println("<tr><td>"+i+"</td><td>"+capitalAmount+"</td><td>"+interestAmount+"</td><td>"+monthlyFixedFee+"</td><td>"+totalPayment+"</td></tr>");
					wantedLoan=wantedLoan-capitalAmount;
				}
				else
				{
					double interestAmount=BigDecimal.valueOf(0.01*monthlyInterestRate*wantedLoan).setScale(2, RoundingMode.HALF_UP).doubleValue();
					double capitalAmountAnnuity=BigDecimal.valueOf(annuity-interestAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
					double totalPayment= BigDecimal.valueOf(annuity + monthlyFixedFee).setScale(2, RoundingMode.HALF_UP).doubleValue();
					response.getWriter().println("<tr><td>"+i+"</td><td>"+capitalAmountAnnuity+"</td><td>"+interestAmount+"</td><td>"+monthlyFixedFee+"</td><td>"+totalPayment+"</td></tr>");
					wantedLoan=wantedLoan-capitalAmount;
				}
			}
			
			response.getWriter().println("</table></br><a href='/index.jsp'>Nowy harmonogram</a>");		
		
		}
		catch (NumberFormatException e) {
			response.sendRedirect("/");
			
		}
				
	}

}
