<%@ page import="jp.co.insightech.*"%>
<%@ page import="java.util.*"%>
<%@ page import= "java.sql.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Vector placeList = (Vector) request.getAttribute("PLACE_LIST");
	User user = (User) session.getAttribute("USER_INFO");
 	
 	Calendar calendar = (Calendar) request.getAttribute("CURRENT_DATE");
 	Vector reservationList = (Vector) request.getAttribute("RESERVATION_LIST");
 	
 	
 	int year = (calendar.get(Calendar.YEAR));
 	int month = (calendar.get(Calendar.MONTH));
 	int day = (calendar.get(Calendar.DAY_OF_MONTH));
 	int maximumDay = (calendar.getActualMaximum(Calendar.DATE));
 	java.sql.Date realDate = new java.sql.Date(System.currentTimeMillis());
 	
 	final int GREEN = 0;
 	final int GREEN_CHECK = 1;
 	final int RED = 2;
 	final int SNOW = 3;
 	
 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>施設予約</title>
</head>
<body bgcolor="azure">


	<form action="./top" method="POST">
		<center>
			<td><font size="6" color="indigo">MVCモデル適用 施設予約システム </font></td> <br>
			<br>
			<td><font size="3" color="indigo">(MVC Reserve System)</font></td> <br>
			<br> <b><font size="5"> <%= year %>年 <%= month + 1 %>月
					<select name="getDate">
					<% for(int i=1; i <= maximumDay; i++) {%>
						<option value=<%= i %> 
						<% if ( day == i ){%>
							selected "<%= i %>" 
						<% }%>><%= i %></option>
					<% } %>
				</select>日</b></font> <input type="hidden" name="getYear" value="<%= year %>"> 
				<input type="hidden" name="getMonth" value="<%= month %>"> 
			
			<input type="submit" name="submitReload" value="更新"> </b></font> <br> <br>
			<input type="submit" name="prevMonth" value="←前月"> 
			<input type="submit" name="today" value="[本日]"> 
			<input type="submit" name="nextMonth" value="次月→"> 
			<br> <br>
			<br>

			<% if(user != null) {  %>
			ユーザー：<b> <%= user.getUserName() %> </b><input type="submit" name="logout"
				value="ログアウト"> <br> <br>
			<%} else {%>
			<br> <input type="text" size="15" name="userId"> <input
				type="submit" name="login" value="ログイン"> <br> <br>
			<% } %>

			<table border="1">
				<tr>
					<th>会議室名</th>
					<%
						for (int i = 9; i < 22; i++) {
					%>
					<th iwth="20"><%=i%> <br> 時</th>
					<%
						}
					%>
					<%					
					for (int i = 0; i < placeList.size(); i++) { 
					%>
				<tr>
					<th>
						<%
					 	Place place = (Place) placeList.get(i);
					 	out.print(place.getPlaceName()); 
					 	
						%>
					</th>
					<% 
						 
						for (int j = 9; j < 22; j++) {%>
							<% 
							int cellStatus = GREEN;
								if(user != null) {
									cellStatus = GREEN_CHECK;
								}
								
							 	for(int s = 0; s < reservationList.size(); s++) {  
						        	Reservation reservation = (Reservation) reservationList.get(s);
						        	int placeListFromDB = reservation.getPlaceId();	
									int timeFromDB = reservation.getTime();
									Date date = new Date(calendar.getTime().getTime());
									
									if(placeListFromDB == (place.getPlaceId()) && timeFromDB == j) {
										 if(user.getUserId().equals(reservation.getUserId())) {
											cellStatus = SNOW;
										} else {
											cellStatus = RED;
										}
										 break;
									} else {
										if(realDate.compareTo(reservation.getPreciseDate()) < 0 && user != null) {
											cellStatus = GREEN_CHECK;
									} else {
										cellStatus = GREEN;
									}
								}
							
					 if(cellStatus == GREEN_CHECK) {%>
							<th bgcolor="lightgreen" iwth="20"> 
							<input type="checkbox" name="reserve" value="<%=i%>_<%=j%>"></th> 
					<% } else if (cellStatus == GREEN) {%>
						<th bgcolor="lightgreen" iwth="20"> </th>
					<% } else if(cellStatus == SNOW) {%>
							<th bgcolor="snow" iwth="20"> 
							<input type="checkbox" name="cancel" value="C304_20"></th>
					<% } else if(cellStatus == RED) {%>
							<th bgcolor="red" iwth="20"> </th>
					<% }  else { %>
					 	<th bgcolor="lightgreen" iwth="20">
					<% }
					}
				}%>
							 
						
				</tr>


			</table>

			<td>
				<%if(user != null) { %><input type="submit" name="reserveCancel"
				value="◎予約　もしくは　取り消し×"> 
				<%} else { %> <font color="red">未ログイン時、または過去の日付の場合は、予約/取り消しできません。</font>
				<br> <input type="submit" name="DummyReserveCancel"
				value="予約/取り消し"> 
				<% } %>
			</td>
			<br>
			<hr>

			<凡例>
			<table border="1" cellpadding="5" cellspacing="0" bordercolor="black"
				bordercolorlight="gray" bordercolordark="dimgray">

				<tr>
					<td bgcolor="lightgreen"><font color="blue"><b>緑</b></font></td>
					<td>空いています<input type="checkbox">にて予約が可能です。
					</td>
				</tr>
				<tr>
					<td bgcolor="red"><font color="blue"><b>赤</b></font></td>
					<td>他の人に予約されています(予約取り消し不可)</td>
				</tr>
				<tr>
					<td bgcolor="snow"><font color="blue"><b>白</b></font></td>
					<td>ご自身で予約されています。<input type="checkbox">にて予約取り消しが可能です。
					</td>
				</tr>

			</table>
		</center>
	</form>
</body>



</html>