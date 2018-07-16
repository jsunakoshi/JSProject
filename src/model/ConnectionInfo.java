package model;

public class ConnectionInfo {
	private long connection_no;
	private String login_id;
	private long start_time;
	private long end_time;

	public ConnectionInfo(long connection_no,String login_id,long start_time,long end_time) {
		this.connection_no = connection_no;
		this.login_id = login_id;
		this.start_time = start_time;
		this.end_time = end_time;
	}

	public long getConnection_no() {
		return connection_no;
	}

	public String getLogin_Id() {
		return login_id;
	}

	public long getStart_Time() {
		return start_time;
	}

	public long getEnd_Time() {
		return end_time;
	}
}
