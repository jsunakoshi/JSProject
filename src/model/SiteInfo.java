package model;

public class SiteInfo {
	private String site;
	private String id;
	private String pass;
	private String email;
	private String comment;

	public SiteInfo(String site,String id,String pass,String email,String comment) {
		this.site = site;
		this.id = id;
		this.pass = pass;
		this.email = email;
		this.comment = comment;
	}

	public String getSite() {
		return site;
	}

	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}

	public String getEmail() {
		return email;
	}

	public String getComment() {
		return comment;
	}

	public void putSite(String site) {
		this.site = site;
	}

	public void putId(String id) {
		this.id = id;
	}

	public void putPass(String pass) {
		this.site = pass;
	}

	public void putEmail(String email) {
		this.email = email;
	}

	public void putComment(String comment) {
		this.comment = comment;
	}

	public boolean insert() {
		return true;
	}
}
