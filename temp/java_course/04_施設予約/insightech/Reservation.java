package jp.co.insightech;

import java.sql.Date;


public class Reservation {
	
	private String userId;

	private int placeId;

	private Date date;
	
	private Integer time;


public Reservation() {
	
}

public void setUserId(String userId) {
	this.userId = userId;
}

public String getUserId() {
	return this.userId;
}

public void setPlaceId(int placeId) {
	this.placeId = placeId;
}

public int getPlaceId() {
	return this.placeId;
}


public void setPreciseDate(Date date) {
	this.date = date;
}

public Date getPreciseDate() {
	return this.date;
}

public void setTime(Integer time) {
	this.time = time;
}

public Integer getTime() {
	return this.time;
}

}