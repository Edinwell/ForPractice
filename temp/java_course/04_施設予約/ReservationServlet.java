package jp.co.insightech;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.Calendar;
import java.util.Vector;

public class ReservationServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Calendar calendar = Calendar.getInstance();
		ReservationDao dao = new ReservationDao();
		String userName = null;

		Date date = new Date(calendar.getTime().getDate());

		try {
			String getMonth = request.getParameter("getMonth");

			Vector reservationList = dao.getReservationList(date);

			User user = new User();

			String login = request.getParameter("login");
			String logout = request.getParameter("logout");
			String prevMonth = request.getParameter("prevMonth");
			String nextMonth = request.getParameter("nextMonth");

			String today = request.getParameter("today");
			String getYear = request.getParameter("getYear");
			String getDate = request.getParameter("getDate");

			//String reserveCancel = request.getParameter("reserveCancel");
			String submitReload = request.getParameter("submitReload");
			ServletContext sc = this.getServletContext();
			Vector placeList = dao.getPlaceList();


			if (getMonth != null && getYear != null && getDate != null) {
				calendar.set(Calendar.MONTH, Integer.parseInt(getMonth));
				calendar.set(Calendar.YEAR, Integer.parseInt(getYear));
				calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(getDate));
			} 

			if (prevMonth != null) {
				calendar.add(Calendar.MONTH, -1);
			}

			if (nextMonth != null) {
				calendar.add(Calendar.MONTH, +1);
			}
			if (today != null) {
				calendar = Calendar.getInstance();
			}

			if (submitReload != null) {
				int intMonth = Integer.parseInt(getMonth);
				int intDate = Integer.parseInt(getDate);
				int intYear = Integer.parseInt(getYear);
				calendar.set(Calendar.MONTH, intMonth);
				calendar.set(Calendar.DAY_OF_MONTH, intDate);
				calendar.set(Calendar.YEAR, intYear);
			} 
			
			if (login != null) {
				String userId = request.getParameter("userId");
				userName = request.getParameter("userName");
				
				user = dao.getUserInfo(userId);

				if (user != null) {

					request.setAttribute("USER_INFO", user);

					// (2)�f�[�^�x�[�X�ɂ���f�[�^��S���擾���܂�
					// Vector reservationList = dao.getReservationList();
					// �����̗\��󋵂�\�����܂�
					// show(myReservationList, response);
					// if(logout != null) {
					//
					// }
				}

			}

			request.setAttribute("CURRENT_DATE", calendar);
			request.setAttribute("PLACE_LIST", placeList);
			request.setAttribute("RESERVATION_LIST", reservationList);
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/reservation.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// (1)�f�[�^�x�[�X��1�������R�[�h��}�����܂�
	/**
	 * int userId = request.getParameter("userId");
	 * 
	 * Reservation reservation = new Reservation();
	 * reservation.setUserId(Integer.parseInt(userId));
	 * 
	 * dao.registerReservation(reservation);
	 * 
	 * 
	 * RequestDispatcher rd =
	 * sc.getRequestDispatcher("/WEB-INF/reservation.jsp");
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 **/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
