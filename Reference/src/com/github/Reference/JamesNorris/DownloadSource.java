package com.github.Reference.JamesNorris;

public enum DownloadSource {
	BUKGET("http://api.bukget.org/api2/bukkit/plugin/%SLUG%/latest");
	
	public final String url;
	
	DownloadSource(String url) {
		this.url = url;
	}
	
	public String slugInURL(String slug) {
		return url.replaceAll("%SLUG%", slug);
	}
}
