package TFM.microservice.geoposition.services;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import TFM.microservice.geoposition.VO.ConfigVO;
import TFM.microservice.geoposition.VO.InfoMapVO;
import TFM.microservice.geoposition.VO.InfoMapVO.ZoneVO;
import TFM.microservice.geoposition.VO.InfoMapVO.ZoneVO.ZoneCoordinate;
import TFM.microservice.geoposition.VO.LocationVO;
import TFM.microservice.geoposition.VO.LocationVO.LocationCoordinate;
import TFM.microservice.geoposition.VO.StructureVO;
import TFM.microservice.geoposition.VO.ZoneCoordinateVO;
import TFM.microservice.geoposition.constants.URLConstants;

@Component
public class LocationService {

	@Value("${cisco.url}")
	private String cisco_url;
	
	@Value("${cisco.username}")
	private String username;
	
	@Value("${cisco.password}")
	private String password;

	
	public LocationVO getLocationByIp(String ip_user) throws IOException {
		Map<String, String> parameters = new HashMap<>();
		LocationVO location = null;
		parameters.put("ipAddress", ip_user);
		// TODO: QUITAR HARDCODED
		location = new LocationVO();
		return location.createLocation();
//				
//		/*Create connection*/
//		URL url = new URL(cisco_url + String.format(URLConstants.GET_USER_POSITION_BY_IP, URLEncoder.encode(ip_user, "UTF-8")));
//		HttpURLConnection con = (HttpURLConnection) url.openConnection();
//		con.setRequestMethod("GET");
//		String authorization = new String(Base64.encodeBase64("learning:learning".getBytes()));
//		con.setRequestProperty("Authorization", String.format("Basic %s", authorization));
//		
//		/*Read response*/
//		Integer status = con.getResponseCode();
//		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		String inputLine;
//		StringBuffer content = new StringBuffer();
//		while ((inputLine = in.readLine()) != null) {
//		    content.append(inputLine);
//		}
//		in.close();
//		
//		con.disconnect();
//		if(status== 200) {
//			String user_info = content.toString().substring(1, content.length()-1);
//			JsonParser parser = new JsonParser();
//			JsonObject json = parser.parse(user_info).getAsJsonObject();
//			Gson gson = new Gson();
//			location = gson.fromJson(json, LocationVO.class);
//		}
//		return location;
//		/*Transform to JSON*/
	}
	
	
	protected static class ParameterStringBuilder {
	    public static String getParamsString(Map<String, String> params) 
	      throws UnsupportedEncodingException{
	        StringBuilder result = new StringBuilder();
	 
	        for (Map.Entry<String, String> entry : params.entrySet()) {
	          result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
	          result.append("=");
	          result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
	          result.append("&");
	        }
	 
	        String resultString = result.toString();
	        return resultString.length() > 0
	          ? resultString.substring(0, resultString.length() - 1)
	          : resultString;
	    }
	}
	
	public byte[] getMapImageByCBF(String campus, String building, String floor) throws IOException {
		String url = String.format(this.cisco_url + URLConstants.GET_MAP_BY_CBF, URLEncoder.encode(campus, "UTF-8"),
																URLEncoder.encode(building, "UTF-8"),
																URLEncoder.encode(floor, "UTF-8"));
		
		//https://www.mkyong.com/java/how-to-read-an-image-from-file-or-url/
		//https://www.mkyong.com/java/how-to-convert-bufferedimage-to-byte-in-java/
		URL uurl = new URL(url);
		String authorization = new String(Base64.encodeBase64("learning:learning".getBytes()));
		
		HttpURLConnection con = (HttpURLConnection) uurl.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Authorization", String.format("Basic %s", authorization));
//		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		BufferedImage image = ImageIO.read(con.getInputStream());
//		image = resize(image, image.getWidth()/10, image.getHeight()/10);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "png", baos);
		baos.flush();
		byte[] bytes = Base64.encodeBase64(baos.toByteArray());
		baos.close();
		
		return bytes;
//		return content.toString();
		
	}
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	} 
	
	public byte[] getMapImageByName(String name) throws IOException {
		String url = String.format(URLConstants.GET_MAP_BY_NAME, URLEncoder.encode(name, "UTF-8"));
		
		//https://www.mkyong.com/java/how-to-read-an-image-from-file-or-url/
		URL uurl = new URL(url);
		BufferedImage image = ImageIO.read(uurl);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "png", baos);
		baos.flush();
		byte[] bytes = baos.toByteArray();
		baos.close();
		
		return bytes;
		
	}

	public InfoMapVO getInfoMap(String campus, String building, String floor) throws Exception{
		InfoMapVO info_map = null;
		String aux_url = String.format(this.cisco_url + URLConstants.GET_MAP_INFO, URLEncoder.encode(campus, "UTF-8"),
				URLEncoder.encode(building, "UTF-8"),
				URLEncoder.encode(floor, "UTF-8"));
		/*Create connection*/
		URL url = new URL(aux_url);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		String authorization = new String(Base64.encodeBase64("learning:learning".getBytes()));
		con.setRequestProperty("Authorization", String.format("Basic %s", authorization));
		
		/*Read response*/
		Integer status = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
		
		con.disconnect();
		if(status== 200) {
			JsonParser parser = new JsonParser();
			JsonObject json = parser.parse(content.toString()).getAsJsonObject();
			Gson gson = new Gson();
			info_map = gson.fromJson(json, InfoMapVO.class);
		}
		return info_map;
	}
	
	public void setSettings(ConfigVO config) {
		this.cisco_url = config.getUrl();
		this.username = config.getUsername();
		this.password = config.getPassword();
	}

	public ConfigVO getSettings() {
		return new ConfigVO(this.cisco_url, this.username, this.password);
	}

	public StructureVO getStructureInfo() throws Exception{
		StructureVO info_map = null;
		String aux_url = this.cisco_url + URLConstants.GET_STRUCTURE_INFO;
		/*Create connection*/
		URL url = new URL(aux_url);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		String authorization = new String(Base64.encodeBase64("learning:learning".getBytes()));
		con.setRequestProperty("Authorization", String.format("Basic %s", authorization));
		
		/*Read response*/
		Integer status = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
		
		con.disconnect();
		if(status== 200) {
			JsonParser parser = new JsonParser();
			JsonObject json = parser.parse(content.toString()).getAsJsonObject();
			Gson gson = new Gson();
			info_map = gson.fromJson(json, StructureVO.class);
		}
		return info_map;
	}

	public ZoneCoordinate getZoneCoordinate(String campus, String building, String floor, String zone) throws Exception {
		InfoMapVO info = this.getInfoMap(campus, building, floor);
		String zone_aux = new String(Base64.decodeBase64(zone));
		List<ZoneVO> zones = info.getZones().stream().filter(x -> x.getName().equals(zone_aux)).collect(Collectors.toList());
		ZoneVO zone_vo = zones.get(0);
		return zone_vo.middlePoint();
		
	}
}
