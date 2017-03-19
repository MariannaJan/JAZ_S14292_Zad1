package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import servlets.LoanCalculator;

public class TestLoanCalculator extends Mockito {

	@Test
	public void servlet_should_go_back_to_form_if_data_incorrect() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		
		LoanCalculator servlet = new LoanCalculator();
		
		when(response.getWriter()).thenReturn(writer);
		when(request.getParameter("wantedLoan")).thenReturn("");
		when(request.getParameter("numofPayments")).thenReturn("");
		when(request.getParameter("interestRate")).thenReturn("");
		when(request.getParameter("fixedFee")).thenReturn("");
		when(request.getParameter("paymentType")).thenReturn("diminishing");
		
		servlet.doPost(request, response);
		
		verify(response).sendRedirect("/");
		}
	
	@Test
	public void servlet_should_go_to_schedule_if_data_correct() throws IOException, ServletException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		PrintWriter writer = mock(PrintWriter.class);
		
		LoanCalculator servlet = new LoanCalculator();
		
		when(response.getWriter()).thenReturn(writer);
		when(request.getParameter("wantedLoan")).thenReturn("30000");
		when(request.getParameter("numofPayments")).thenReturn("6");
		when(request.getParameter("interestRate")).thenReturn("10");
		when(request.getParameter("fixedFee")).thenReturn("150");
		when(request.getParameter("paymentType")).thenReturn("diminishing");
		
		servlet.doPost(request, response);
		
		verify(writer).println("<h1>Harmonogram splaty</h1>");
		}
	
}
