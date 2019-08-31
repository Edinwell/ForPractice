package jp.co.insightech;

import java.sql.*;
import java.util.Calendar;
import java.util.Vector;

public class ReservationDao {
	


		public Vector getReservationList(Date preciseDate) throws Exception {
		Vector reservationList = new Vector();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = this.getConnection();

			String sql = "SELECT * FROM RESERVATION_TABLE WHERE PRECISE_DATE = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, preciseDate);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				Reservation reservation = new Reservation();

				String userId = rs.getString("USER_ID");
				Integer placeId = rs.getInt("PLACE_ID");
			    preciseDate = rs.getDate("PRECISE_DATE");
			    Integer time = rs.getInt("TIME");

				reservation.setUserId(userId);
				reservation.setPlaceId(placeId);
			    reservation.setPreciseDate(preciseDate);
				reservation.setTime(time);

				reservationList.add(reservation);
				}

			} finally {
				this.close(rs);
				this.close(pstmt);
				this.close(conn);
			}

			return reservationList;
		}

	
	public User getUserInfo(String userId) throws Exception {

		User user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = this.getConnection();

			String sql = "SELECT * FROM USER_TABLE WHERE User_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			if(rs.next()) {
			 user = new User();
			 userId = rs.getString("USER_ID");
			 String userName = rs.getString("User_Name");
			 
			 user.setUserId(userId);
			 user.setUserName(userName);
			}

		} finally {
			this.close(rs);
			this.close(pstmt);
			this.close(conn);
		}

		return user;
	}
	
	
	public Vector getPlaceList () throws Exception {
		Vector placeList = new Vector();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			
			String sql = "SELECT * FROM PLACE_TABLE ORDER BY PLACE_ID DESC";
		
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Place place = new Place();
				
				Integer placeId = rs.getInt("PLACE_ID");
				String placeName = rs.getString("PLACE_NAME");
				
				place.setPlaceId(placeId);
				place.setPlaceName(placeName);
				
				placeList.add(place);
			}
		} finally {
			this.close(rs);
			this.close(pstmt);
			this.close(conn);
		}
		return placeList;
	}
	
	public void registerReserveInfo(Reservation reservation) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = this.getConnection();

			String sql = "INSERT INTO RESERVATION_TABLE VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reservation.getUserId());
			pstmt.setInt(2, reservation.getPlaceId());
			pstmt.setDate(3, reservation.getPreciseDate());
			pstmt.setInt(4, reservation.getTime());
			int i = pstmt.executeUpdate();

			} finally {
				this.close(rs);
				this.close(pstmt);
				this.close(conn);
			}
		}
	public void cancelRegistration(Reservation reservation) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM RESERVATION_TABLE WHERE USER_ID = ? AND PLACE_ID =? PRECISE_DATE = ? AND TIME = ?");
			pstmt.setString(1, reservation.getUserId());
			pstmt.setInt(2, reservation.getPlaceId());
			pstmt.setDate(3, reservation.getPreciseDate());
			pstmt.setInt(4, reservation.getTime());
			
			int i = pstmt.executeUpdate(); 
			
		} finally {
			this.close(pstmt);
			this.close(conn);
		}
	}
	
	private Connection getConnection() throws Exception {
		Class.forName("org.gjt.mm.mysql.Driver");
		Connection conn = DriverManager.getConnection
			("jdbc:mysql://localhost/tsuda_reserve_system?useUnicode=true&characterEncoding=MS932", "root", "root");
		return conn;
	}

	private void close(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	private void close(Statement stmt) throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
	}

	private void close(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}

/**	public void deleteReservation(int ) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = this.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM RESERVATION_TABLE WHERE ID=?")
			ostmt.setInt(1, intId);
			int i = pstmt.executeUpdate();
		} finally {
			this.close(pstmt);
			this.close(conn);
		}
	}
	public void registerReservation(Reservation reservation) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = this.getConnection();

			pstmt = conn.prepareStatement("INSERT INTO RESERVATION_TABLE VALUES(?,?,?,?)");

			pstmt.setInt(1, reservation.getUserId());
			pstmt.setInt(2, reservation.getPlaceId());
			pstmt.setDate(3, reservation.getPreciseDate());
			pstmt.setInt(4, reservation.getTime());
			int i = pstmt.executeUpdate();
		} finally {

			this.close(pstmt);
			this.close(conn);
		}
	}**/
}