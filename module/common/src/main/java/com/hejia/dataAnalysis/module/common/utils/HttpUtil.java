package com.hejia.dataAnalysis.module.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpUtil {
	private static HashMap<String, HttpClient> sessionMap = new HashMap<String, HttpClient>();

	private static Logger logger = Logger.getLogger(HttpUtil.class);

	public HttpUtil() {
	}

	/*
	 * 将形如“\u7cfb\u7edf\u7e41\u5fd9\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5\u3002”的字符串解码
	 */
	public static String decode(String s) {
		StringReader s1 = new StringReader(s);
		try {
			char[] chars = new char[s.length()];
			s1.read(chars);
			return new String(chars);
		} catch (Exception ex) {
		}
		return null;
	}

	/**
	 * get httpGet
	 * 
	 * @param url
	 *            String
	 * @param name
	 *            String 连接名称，用以维护session,不需要维持连接时请置null，操作结束后请调用destroy方法
	 * @param charset
	 *            String
	 * @return String
	 */
	public static String get(String url, String name, String charset) {
		if (charset == null)
			charset = HTTP.UTF_8;

		try {
			HttpClient httpclient;

			if (name == null) { // 不需要维持连接
				httpclient = createHttpClient();
			} else if (sessionMap.containsKey(name)) {
				httpclient = sessionMap.get(name);
			} else {
				httpclient = createHttpClient();
				sessionMap.put(name, httpclient);
			}

			HttpGet httpget1 = new HttpGet(url);

			// httpget1.addHeader("Authorization",
			// "OAuth oauth_consumer_key=\"450215437\",oauth_nonce=\"SThfUW\",oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"1386241655\",oauth_version=\"1.0\",x_auth_mode=\"client_auth\",x_auth_password=\"a77a125\",x_auth_username=\"450215437\",oauth_signature=\"2s6KCmXhuUgDAyl8gc/HMkT/dPY=\"");

			HttpResponse response1 = httpclient.execute(httpget1);
			HttpEntity entity1 = response1.getEntity();

			return EntityUtils.toString(entity1, charset);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getJuhe(String url, String name, String charset) {
		if (charset == null)
			charset = HTTP.UTF_8;

		// Hm_lvt_4d698a59cb2f4880202bc924e8364d90=1358670422;
		// Hm_lpvt_4d698a59cb2f4880202bc924e8364d90=1358670433;
		// PHPSESSID=r1pgkduu079mirjcdukd1s22i7
		try {
			HttpClient httpclient;

			if (name == null) { // 不需要维持连接
				httpclient = createHttpClientJuhe();
			} else if (sessionMap.containsKey(name)) {
				httpclient = sessionMap.get(name);
			} else {
				httpclient = createHttpClientJuhe();
				sessionMap.put(name, httpclient);
			}

			HttpGet httpget1 = new HttpGet(url);
			HttpResponse response1 = httpclient.execute(httpget1);
			HttpEntity entity1 = response1.getEntity();

			return EntityUtils.toString(entity1, charset);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * post
	 * 
	 * @param url
	 *            String
	 * @param map
	 *            HashMap 提交表单的键值对
	 * @param name
	 *            String 连接名称，用以维护session
	 * @param charset
	 *            String
	 * @return String
	 */
	public static String post(String url, Map<String, String> map, String name, String charset) {
		if (charset == null)
			charset = HTTP.UTF_8;

		try {
			HttpClient httpclient;

			if (name == null) { // 不需要维持连接
				httpclient = createHttpClient();
			} else if (sessionMap.containsKey(name)) {
				httpclient = sessionMap.get(name);
			} else {
				httpclient = createHttpClient();
				sessionMap.put(name, httpclient);
			}

			HttpPost httpost = new HttpPost(url);

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			if (map != null) {
				Iterator it = map.keySet().iterator();

				while (it.hasNext()) {
					String key = (String) it.next();
					nvps.add(new BasicNameValuePair(key, map.get(key)));
				}
			}

			httpost.setEntity(new UrlEncodedFormEntity(nvps, charset));

			// System.out.println("!!!!!!!!!!!!!!!");
			// long s=System.currentTimeMillis();
			HttpResponse response = httpclient.execute(httpost);
			// System.out.println((System.currentTimeMillis()-s)/1000);
			HttpEntity entity1 = response.getEntity();
			// entity1.consumeContent();
			return EntityUtils.toString(entity1, charset);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String post(String url, String content, String name, String charset) {
		if (charset == null)
			charset = HTTP.UTF_8;

		try {
			HttpClient httpclient;

			if (name == null) { // 不需要维持连接
				httpclient = createHttpClient();
			} else if (sessionMap.containsKey(name)) {
				httpclient = sessionMap.get(name);
			} else {
				httpclient = createHttpClient();
				sessionMap.put(name, httpclient);
			}

			HttpPost httpost = new HttpPost(url);
			if (content != null) {
				httpost.setEntity(new StringEntity(content, charset));
			}

			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity1 = response.getEntity();
			// entity1.consumeContent();
			return EntityUtils.toString(entity1, charset);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void destroy(String name) {
		HttpClient httpclient = sessionMap.get(name);
		httpclient.getConnectionManager().shutdown();
		sessionMap.remove(name);
	}

	private static HttpClient createHttpClient() {
		DefaultHttpClient httpclient = new DefaultHttpClient(new PoolingClientConnectionManager());
		System.getProperties()
				.setProperty(
						"httpclient.useragent",
						"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; CIBA; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");

		try {

			httpclient.addRequestInterceptor(new HttpRequestInterceptor() {

				@Override
				public void process(final HttpRequest request, final HttpContext context) throws HttpException,
						IOException {
					if (!request.containsHeader("Accept-Encoding")) {
						request.addHeader("Accept-Encoding", "gzip");
					}
				}

			});

			httpclient.addResponseInterceptor(new HttpResponseInterceptor() {

				@Override
				public void process(final HttpResponse response, final HttpContext context) throws HttpException,
						IOException {
					HttpEntity entity = response.getEntity();
					Header ceheader = entity.getContentEncoding();
					if (ceheader != null) {
						HeaderElement[] codecs = ceheader.getElements();
						for (int i = 0; i < codecs.length; i++) {
							if (codecs[i].getName().equalsIgnoreCase("gzip")) {
								response.setEntity(new GzipDecompressingEntity(response.getEntity()));
								return;
							}
						}
					}
				}

			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return httpclient;

	}

	private static HttpClient createHttpClientJuhe() {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		System.getProperties()
				.setProperty(
						"httpclient.useragent",
						"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; CIBA; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");

		try {

			httpclient.addRequestInterceptor(new HttpRequestInterceptor() {

				@Override
				public void process(final HttpRequest request, final HttpContext context) throws HttpException,
						IOException {
					if (!request.containsHeader("Accept-Encoding")) {
						request.addHeader("Accept-Encoding", "gzip");
					}
					request.addHeader("Referer", "http://lbs.juhe.cn/cell.php");
				}

			});

			httpclient.addResponseInterceptor(new HttpResponseInterceptor() {

				@Override
				public void process(final HttpResponse response, final HttpContext context) throws HttpException,
						IOException {
					HttpEntity entity = response.getEntity();
					Header ceheader = entity.getContentEncoding();
					if (ceheader != null) {
						HeaderElement[] codecs = ceheader.getElements();
						for (int i = 0; i < codecs.length; i++) {
							if (codecs[i].getName().equalsIgnoreCase("gzip")) {
								response.setEntity(new GzipDecompressingEntity(response.getEntity()));
								return;
							}
						}
					}
				}

			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return httpclient;

	}

	static class GzipDecompressingEntity extends HttpEntityWrapper {

		public GzipDecompressingEntity(final HttpEntity entity) {
			super(entity);
		}

		@Override
		public InputStream getContent() throws IOException, IllegalStateException {

			// the wrapped entity's getContent() decides about repeatability
			InputStream wrappedin = wrappedEntity.getContent();

			return new GZIPInputStream(wrappedin);
		}

		@Override
		public long getContentLength() {
			// length of ungzipped content is not known
			return -1;
		}

	}

	public static String getReg(String src, String reg) {
		if (src == null || reg == null)
			return null;
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(src);

		if (m.find()) {
			return m.group(1);
		}

		return null;
	}

	public static String get(String url, String name, String charset, boolean redirect) {
		if (charset == null)
			charset = HTTP.UTF_8;

		try {
			HttpClient httpclient;

			if (name == null) { // 不需要维持连接
				httpclient = createHttpClient();
			} else if (sessionMap.containsKey(name)) {
				httpclient = sessionMap.get(name);
			} else {
				httpclient = createHttpClient();
				sessionMap.put(name, httpclient);
			}

			HttpGet httpget1 = new HttpGet(url);

			if (!redirect) {
				HttpParams params = httpclient.getParams();
				params.setParameter(ClientPNames.HANDLE_REDIRECTS, false);
				httpget1.setParams(params);
			}

			// httpget1.addHeader("Authorization",
			// "OAuth oauth_consumer_key=\"450215437\",oauth_nonce=\"SThfUW\",oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"1386241655\",oauth_version=\"1.0\",x_auth_mode=\"client_auth\",x_auth_password=\"a77a125\",x_auth_username=\"450215437\",oauth_signature=\"2s6KCmXhuUgDAyl8gc/HMkT/dPY=\"");

			HttpResponse response1 = httpclient.execute(httpget1);

			if (response1.getStatusLine() != null && response1.getStatusLine().toString().startsWith("HTTP/1.1 30")) {
				Header[] headers = response1.getHeaders("Location");
				for (Header h : headers) {
					HttpEntity entity1 = response1.getEntity();
					entity1.consumeContent();
					return h.getValue();
				}
			}

			HttpEntity entity1 = response1.getEntity();
			return EntityUtils.toString(entity1, charset);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static byte[] downloadFile(String url, String name) {
		try {
			HttpClient httpclient;

			if (name == null) { //不需要维持连接
				httpclient = createHttpClient();
			} else if (sessionMap.containsKey(name)) {
				httpclient = sessionMap.get(name);
			} else {
				httpclient = createHttpClient();
				sessionMap.put(name, httpclient);
			}

			HttpGet httpget1 = new HttpGet(url);

			//             httpget1.addHeader("Authorization", "Mozilla/5.0 (Linux; U; Android 2.3.6; en-us; HUAWEI U8661 Build/HuaweiU8661) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");

			HttpResponse response1 = httpclient.execute(httpget1);
			HttpEntity entity1 = response1.getEntity();
			return EntityUtils.toByteArray(entity1);

			//             return EntityUtils.toString(entity1, charset);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String postException(String url, String content, String name, String charset) throws Exception {
		if (charset == null)
			charset = HTTP.UTF_8;

		HttpClient httpclient;

		if (name == null) { // 不需要维持连接
			httpclient = createHttpClient();
		} else if (sessionMap.containsKey(name)) {
			httpclient = sessionMap.get(name);
		} else {
			httpclient = createHttpClient();
			sessionMap.put(name, httpclient);
		}

		HttpPost httpost = new HttpPost(url);

		if (content != null) {
			httpost.setEntity(new StringEntity(content, charset));
		}

		HttpResponse response = httpclient.execute(httpost);

		HttpEntity entity1 = response.getEntity();

		return EntityUtils.toString(entity1, charset);

	}

	/**
	 * 发送HTTP_GET请求
	 * @see 1)该方法会自动关闭连接,释放资源
	 * @see 2)该方法会自动获取到响应消息头中[Content-Type:text/html; charset=GBK]的charset值作为响应报文解码字符集
	 * @see   若响应消息头中无Content-Type属性,则默认使用HttpClient内部默认的ISO-8859-1
	 * @param requestURL 请求地址(含参数)
	 * @return 远程主机响应正文
	 */
	public static String sendGetRequest(String reqURL) {
		long respLen = 0; //响应长度
		String respContent = null; //响应内容
		Charset respCharset = null; //响应编码
		HttpClient httpClient = new DefaultHttpClient(); //创建默认的httpClient实例
		HttpGet httpGet = new HttpGet(reqURL); //创建org.apache.http.client.methods.HttpGet
		try {
			HttpResponse response = httpClient.execute(httpGet); //执行GET请求
			HttpEntity entity = response.getEntity();
			//获取响应实体
			if (null != entity) {
				//使用EntityUtils.getContentCharSet(entity)也可以获取响应编码,但从4.1.3开始不建议使用这种方式
				respCharset = ContentType.getOrDefault(entity).getCharset();
				if (respCharset == null) {
					respCharset = Charset.defaultCharset();
				}
				respLen = entity.getContentLength();
				respContent = EntityUtils.toString(entity, respCharset);
				//Consume response content
				EntityUtils.consume(entity);
			}
			//	            System.out.println("-----------------------------------------------------------------------");
			//	            System.out.println("请求地址: " + httpGet.getURI());
			//	            System.out.println("响应状态: " + response.getStatusLine());
			//	            System.out.println("响应长度: " + respLen);
			//	            System.out.println("响应编码: " + respCharset);
			//	            System.out.println("响应内容: " + respContent);
			//	            System.out.println("-----------------------------------------------------------------------");
		} catch (ClientProtocolException e) {
			//该异常通常是协议错误导致:比如构造HttpGet对象时传入协议不对(将'http'写成'htp')or响应内容不符合HTTP协议要求等
			logger.error("协议异常,堆栈信息如下", e);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			//该异常通常是网络原因引起的,如HTTP服务器未启动等
			logger.error("网络异常,堆栈信息如下", e);
		} finally {
			//关闭连接,释放资源
			httpClient.getConnectionManager().shutdown();
		}
		return respContent;
	}

	/**
	 * 发送HTTP_POST请求
	 * @see 1)该方法的优点在于-->允许自定义任何格式和内容的HTTP请求报文体,只要你熟悉HTTP/1.1协议
	 * @see 2)该方法会自动关闭连接,释放资源
	 * @see 3)方法内设置了连接和读取超时时间,单位为毫秒,超时后方法会自动返回空字符串
	 * @see 4)请求参数含中文等特殊字符时,可在传入前自行<code>URLEncoder.encode(string,encodeCharset)</code>,再指定encodeCharset为null
	 * @see 5)亦可指定encodeCharset参数值,由本方法代为编码
	 * @see 6)该方法在解码响应报文时所采用的编码,为响应消息头中[Content-Type:text/html; charset=GBK]的charset值
	 * @see   若响应消息头中未指定Content-Type属性,则默认使用HttpClient内部默认的ISO-8859-1
	 * @param reqURL        请求地址
	 * @param reqData       请求参数,若有多个参数则应拼接为param11=value11&22=value22&33=value33的形式
	 * @param encodeCharset 编码字符集,编码请求数据时用之,其为null表示请求参数已编码完毕,不需要二次编码
	 * @return 远程主机响应正文
	 */
	public static String sendPostRequest(String reqURL, Map<String, String> headParams, Map<String, String> params,
			String encodeCharset) {
		String reseContent = "";
		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30 * 1000);
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30 * 1000);
		HttpPost httpPost = new HttpPost(reqURL);
		/* if (Util.isNotNull(headParams)) {
			for(Entry<String, String> m: headParams.entrySet()){
				httpPost.addHeader(m.getKey(), m.getValue());
			}
		}*/
		try {
			if (null != params) {
				List<NameValuePair> formParams = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> entry : params.entrySet()) {
					formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset));
			}
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (null != entity) {
				reseContent = EntityUtils.toString(entity, ContentType.getOrDefault(entity).getCharset());
				EntityUtils.consume(entity);
			}
		} catch (ConnectTimeoutException cte) {
			logger.error("与[" + reqURL + "]连接超时,自动返回空字符串");
		} catch (SocketTimeoutException ste) {
			logger.error("与[" + reqURL + "]读取超时,自动返回空字符串");
		} catch (Exception e) {
			logger.error("与[" + reqURL + "]通信异常,堆栈信息如下", e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return reseContent;
	}

	/**
	 * @Definition: 
	 * @author: chenyongqiang
	 * @Date: 2015年11月15日
	 * @param url
	 * @return
	 */
	public static String purge(String url) {
		String respContent = null; //响应内容
		Charset respCharset = null; //响应编码
		String hostname = gethost(url);
		String port = getPort(url);
		HttpHost host = null;
		if (port == null) {
			host = new HttpHost(hostname);
		} else {
			host = new HttpHost(hostname, Integer.parseInt(port));
		}
		HttpClient httpClient = new DefaultHttpClient();
		try {
			BasicHttpRequest purgeRequest = new BasicHttpRequest("PURGE", getUri(url), HttpVersion.HTTP_1_1);
			HttpResponse response = httpClient.execute(host, purgeRequest);
			HttpEntity entity = response.getEntity();
			//获取响应实体
			if (null != entity) {
				respCharset = ContentType.getOrDefault(entity).getCharset();
				if (respCharset == null) {
					respCharset = Charset.defaultCharset();
				}
				respContent = EntityUtils.toString(entity, respCharset);
				EntityUtils.consume(entity);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return respContent;
	}

	/**
	 * @Definition: 根据URL获取主机名
	 * @author: chenyongqiang
	 * @Date: 2015年11月12日
	 * @param url
	 * @return
	 */
	public static String gethost(String url) {
		String host = null;
		Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
		Matcher matcher = p.matcher(url);
		if (matcher.find()) {
			host = matcher.group();
		}
		return host;
	}

	/**
	 * @Definition: 根据URL获取端口
	 * @author: chenyongqiang
	 * @Date: 2015年11月12日
	 * @param url
	 * @return
	 */
	private static String getPort(String url) {
		String port = null;
		Pattern p = Pattern.compile("(:(\\d)+){1}");
		Matcher matcher = p.matcher(url);
		if (matcher.find()) {
			port = matcher.group(1).substring(1);
		}
		return port;
	}

	/**
	 * @Definition: 根据URL获取
	 * @author: chenyongqiang
	 * @Date: 2015年11月12日
	 * @param url
	 * @return
	 */
	private static String getUri(String url) {
		return url.substring(url.indexOf("/", 7));
	}

	/**
	 * @Definition: 检测当前URL是否可连接或是否有效
	 * @author: Henry
	 * @Date: 2016年7月14日
	 * @param url
	 * @return
	 */
	public synchronized static boolean isConnect(String urlStr) {
		int counts = 0;
		boolean b = false;
		if (urlStr == null || urlStr.length() <= 0) {
			return b;
		}
		while (counts < 5) {
			try {
				URL url = new URL(urlStr);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				int state = con.getResponseCode();
				System.out.println(counts + "= " + state);
				if (state == 200) {
					b = true;
					break;
				} else {
					counts++;
					continue;
				}

			} catch (Exception ex) {
				counts++;
				continue;
			}
		}
		return b;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 * @throws IOException 
	 */
	public static String sendPost(String url, List<Object> list) throws IOException {

		return "";
	}

	public static void main(String[] args) {
		String url = "https://www.baidu.com/";
		System.out.println(isConnect(url));
		//System.out.println(getPort(url));
		//System.out.println(getUri(url));
	}
}
