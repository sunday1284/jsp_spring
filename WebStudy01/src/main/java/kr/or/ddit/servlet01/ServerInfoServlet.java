package kr.or.ddit.servlet01;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/system-info.do")
public class ServerInfoServlet extends HttpServlet{
	private Map<String, String> keyHeaders;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		keyHeaders = new HashMap<>();
		keyHeaders.put("java.vm.version", "JVM Version");
		keyHeaders.put("java.vendor", "Java Vendor");
		keyHeaders.put("os.name", "OS Name");
		keyHeaders.put("os.version", "OS Version");
		keyHeaders.put("user.country", "Server Country");
		keyHeaders.put("user.language", "Server Language");
		keyHeaders.put("user.timezone", "Server TimeZone");
	}
	
	String pattern = "<tr><td>%s</td><td>%s</td></tr>";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuffer html = new StringBuffer();
		html.append("<html>");
		html.append("<body>               ");
		html.append("<table>              ");
		html.append("<thead>              ");
		html.append("<tr>                 ");
		html.append("<th>key</th>         ");
		html.append("<th>value</th>       ");
		html.append("</tr>                ");
		html.append("</thead>             ");
		html.append("<tbody>              ");
		
		
		makePartSystemData(html);
		
		html.append("</tbody>             ");
		html.append("</table>             ");
		html.append("</body>              ");
		html.append("</html>              ");
		
		resp.getWriter().print(html);
	}
	
	private void makeAllSystemData(StringBuffer html) {
		Properties systemProps = System.getProperties();
		Set<Entry<Object, Object>> entrySet = systemProps.entrySet();
		for( Entry<Object, Object> entry : entrySet) {
			Object key = entry.getKey();
			Object value = System.getProperty(key.toString());
//			System.out.printf("%s : %s\n", key.toString(), value.toString());
			html.append(
					String.format(pattern, key, value)
			);
		}
	}
	
	private void makePartSystemData(StringBuffer html) {
//		Properties systemProps = System.getProperties();
//		Set<Entry<Object, Object>> entrySet = systemProps.entrySet();
//		for( Entry<Object, Object> entry : entrySet) {
		Set<Entry<String, String>> entrySet = keyHeaders.entrySet();
		for(Entry<String,String> entry :entrySet) {
			String key = entry.getKey();
			String header = entry.getValue();
			String value = System.getProperty(key);
//			System.out.printf("%s : %s\n", key.toString(), value.toString());
			html.append(
					String.format(pattern, header, value)
			);
		}
	}
}





















