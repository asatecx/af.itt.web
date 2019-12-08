package af.itt.rakuten.wapi;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.springframework.beans.factory.annotation.Value;

import af.base.service.BaseService;

public class AsaBaseService extends BaseService{

	@Value("${rakuten.webapi.serviceSecret}")
	private String SERVICE_SECRET;

	@Value("${rakuten.webapi.licenseKey}")
	private String LICENSE_KEY;

	public String apiGet(String uri, List<NameValuePair> params) {
		String authCode = SERVICE_SECRET + ":" + LICENSE_KEY;
		return super.doHttpGET(uri, authCode, params);
	}

	public String apiPost(String uri, Map<String, Object> param) {
		String authCode = SERVICE_SECRET + ":" + LICENSE_KEY;
		return super.doHttpPOST(uri, authCode, param);
	}

	public String apiPostXml(String uri, String xmlRequest) {
		String authCode = SERVICE_SECRET + ":" + LICENSE_KEY;
		return super.doHttpPOSTXML(uri, authCode, xmlRequest);
	}
}
