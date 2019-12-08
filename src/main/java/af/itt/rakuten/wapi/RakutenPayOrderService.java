package af.itt.rakuten.wapi;

import java.util.Map;

import org.springframework.stereotype.Service;
@Service("rakuten.rakutenPayOrderService")
public class RakutenPayOrderService extends AsaBaseService{

	public String getOrder(Map<String, Object> param){
		return super.apiPost("https://api.rms.rakuten.co.jp/es/2.0/order/getOrder/", param);
	}

	public String searchOrder(Map<String, Object> param){
		return super.apiPost("https://api.rms.rakuten.co.jp/es/2.0/order/searchOrder/", param);
	}
}
