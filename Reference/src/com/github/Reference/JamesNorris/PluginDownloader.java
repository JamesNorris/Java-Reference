package com.github.Reference.JamesNorris;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PluginDownloader {
	private static InputStream in = null;
	private static OutputStream out = null;
	private static byte[] buffer = new byte[1024];
	private static int read;

	public static boolean isAccessible(final String path) {
		return (ping(path) != -1);
	}

	public static long ping(final String path) {
		try {
			final URL url = new URL(path);
			final HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			urlConn.setConnectTimeout(1000 * 10);
			final long startTime = System.currentTimeMillis();
			urlConn.connect();
			final long endTime = System.currentTimeMillis();
			if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK)
				return (endTime - startTime);
		} catch (final MalformedURLException e) {
			System.err.println("The URL given <" + path + "> was not found!");
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static void download(final String from, final String to) {
		try {
			final URL url = new URL(from);
			out = new BufferedOutputStream(new FileOutputStream(to));
			in = url.openConnection().getInputStream();
			while ((read = in.read(buffer)) != -1)
				out.write(buffer, 0, read);
			System.out.println("The file at <" + from + "> was downloaded to the path <" + to + ">.");
		} catch (MalformedURLException e) {
			System.err.println("The URL given <" + from + "> was not found!");
		} catch (FileNotFoundException e) {
			System.err.println("The file path given <" + to + "> was not found!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeStreams();
		}
	}

	protected static void closeStreams() {
		try {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
