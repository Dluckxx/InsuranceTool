import java.io.*;
import java.util.List;
import java.util.Properties;

public class FileManager {
	private static final FileManager instance = new FileManager();

	private FileManager() {
	}

	public static FileManager getInstance() {
		return instance;
	}

	public String getHtml() {
		StringBuilder text = new StringBuilder();
		InputStream is;
		try {
			is = new FileInputStream(readConfig().getProperty("path"));
			InputStreamReader reader = new InputStreamReader(is, "GBK");
			while (reader.ready()) {
				text.append((char) reader.read());
			}
			is.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return text.toString();
	}

	public void saveFirstCSV(List<TableFirst> tableList) {
		String path = readConfig().getProperty("path").replace(".html", ".csv");
		File file = new File(path);
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(file));
			out.write(tableList.get(0).keyToCSV());
			for (TableFirst t : tableList) {
				out.write(t.valueToCSV());
			}
			out.flush();
			out.close();
			System.out.println("表1已经存储到：" + file);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void saveSecondCSV(List<TableSecond> tableList) {
		String p = readConfig().getProperty("path").replace(".html", "2.csv");
		File file = new File(p);
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(file));
			out.write(tableList.get(0).keyToCSV());
			for (TableSecond t : tableList) {
				out.write(t.valueToCSV());
			}
			out.flush();
			out.close();
			System.out.println("表2已经存储到：" + file);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public String readRepo() {
		InputStream is = getClass().getResourceAsStream("repo.json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder json = new StringBuilder();
		try {
			while (reader.ready()) {
				json.append(reader.readLine());
			}
		} catch (IOException e) {
			System.err.println("err:" + e.getMessage());
			return null;
		}
		return json.toString();
	}

	public void writeRepo(String json) {
		File file = new File(getClass().getResource("repo.json").getFile());
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.err.println("err:" + e.getMessage());
		}
	}

	public Properties readConfig() {
		Properties config = new Properties();
		InputStream is;
		try {
			is = getClass().getResourceAsStream("config.properties");
			config.load(is);
			is.close();
		} catch (Exception e) {
			System.err.println("配置文件读取错误！");
		}
		return config;
	}

	public void writeConfig(String key, String value) {
		Properties properties = readConfig();
		OutputStream out;
		try {
			out = new FileOutputStream(getClass().getResource("config.properties").getFile());
			properties.setProperty(key, value);
			properties.store(out, "auto");
			out.close();
		} catch (Exception e) {
			System.err.println("\n配置文件未找到：" + e.getMessage());
		}

	}

	public Properties readCookie() {
		Properties cookies = new Properties();
		File file = new File("./cookies.properties");
		InputStream is;
		try {
			if (file.exists()) {
				is = new FileInputStream(file);
			} else {
				is = getClass().getResourceAsStream("cookies.properties");
			}
			cookies.load(is);
			is.close();
		} catch (Exception e) {
			System.err.println("配置文件读取错误！");
		}
		return cookies;
	}

	public void writeCookie(String key, String value) {
		File file = new File("./cookies.properties");
		Properties properties = readCookie();

		OutputStream out;
		try {
			out = new FileOutputStream(file);
			properties.setProperty(key, value);
			properties.store(out, "auto");
			out.close();
		} catch (Exception e) {
			System.err.println("写入Cookies文件错误：" + e.getMessage());
		}
	}
}
