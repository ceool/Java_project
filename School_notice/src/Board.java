public class Board {
	private String id;
	private String title;
	private String writer;
	private String datetime;
	private String url;
	
	public Board(String id, String title, String writer, String datetime, String url) { 
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.datetime = datetime;
		this.url = url;
	}
	public String getld()
	{ 
		return id;
	}
	public String getTitle()
	{
		return title;
	}
	public String getWriter()
	{
		return writer;
	}
	public String getDatetime()
	{
		return datetime;
	}
	public String getUrl()
	{ 
		return url;
	}
}
