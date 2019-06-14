package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("85.113.60.127");
    assertEquals(geoIp, "<GeoIP><Country>RU</Country><State>65</State></GeoIP>");
  }

  @Test
  public void testInvalidIp() {
    String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("85.113.60.xxx");
    assertEquals(geoIp, "<GeoIP><Country>RU</Country><State>65</State></GeoIP>");
  }
}
