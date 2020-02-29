package af.itt.rakuten.order.orderinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import af.base.model.JsonModel;
import af.base.model.JsonModelTable;
import af.itt.rakuten.wapi.RakutenPayOrderService;
import af.itt.sc.model.SearchCondition;
import af.util.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*************************************************************************
 * Copyright     Asatecx Co.,Ltd.<br/>
 * Package       stella.portal.wapi.notice<br/>
 * Class         OrderInfoController<br/>
 * Summary       お知らせ管理<br/>
 * <br/>
 * Date             Author      Category     Version     Note<br/>
 * 2019/10/16       tang-xf      Add          1.0.0      新規作成<br/>
 ************************************************************************/
@Scope("request")
@RestController
public class OrderInfoController {
    @Autowired
    @Qualifier("rakuten.rakutenPayOrderService")
	private RakutenPayOrderService rakutenPayOrderService;

    /**
     * Search notice list.
     * @param searchCondition 検索条件
     * @return notice list JsonModel
     */
    @RequestMapping(value = "/asashop/order/orderInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonModel getNoitces(@RequestBody SearchCondition searchCondition) {
    	Map<String, Object> param = new HashMap<String, Object>();

//		String startDate = DateUtil.convertFormatDate("2019-10-20", "1");
//		String endDate = DateUtil.convertFormatDate("2019-10-20", "2");
    	String orderStartDate = String.valueOf(searchCondition.getFilterInfo().get("orderStartDate"));
    	String orderEndDate = String.valueOf(searchCondition.getFilterInfo().get("orderEndDate"));
    	String startDate = DateUtil.convertFormatDate(orderStartDate, "1");
		String endDate = DateUtil.convertFormatDate(orderEndDate, "2");

		Map<String, Object> paginationRequestModel = new HashMap<String, Object>();
		paginationRequestModel.put("requestRecordsAmount", 300);
		paginationRequestModel.put("requestPage", 1);

		List<Map<String, Object>> sortModelList = new ArrayList<Map<String, Object>>();
		Map<String, Object> sortModel = new HashMap<String, Object>();
		sortModel.put("sortColumn", 1);
		sortModel.put("sortDirection", 2);
		sortModelList.add(sortModel);
		paginationRequestModel.put("SortModelList", sortModelList);

		param.put("dateType", 1);
		param.put("startDatetime", startDate);
		param.put("endDatetime", endDate);
		param.put("PaginationRequestModel", paginationRequestModel);

		String res = rakutenPayOrderService.searchOrder(param);

		JSONObject json = JSONObject.fromObject(res);
		List<String> orderList = (List<String>)json.get("orderNumberList");

		List<Map<String, Object>> orderInfos = getOrder(orderList);

		return new JsonModelTable(orderInfos.size(), orderInfos);
    }

    @RequestMapping("/RakutenPayOrderService/getOrder")
	public List<Map<String, Object>> getOrder(List<String> orderNumberList) {
    	List<Map<String, Object>> orderInfoList = new ArrayList<Map<String, Object>>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderNumberList", orderNumberList);
		JSONObject json = JSONObject.fromObject(rakutenPayOrderService.getOrder(param));
		JSONArray orderModelList = (JSONArray)json.get("OrderModelList");

		for(int i=0; i < orderModelList.size(); i++) {
			Map orderInfo = JSON.parseObject(orderModelList.getJSONObject(i).toString(), Map.class);
			JSONObject ordererModel = JSONObject.fromObject(orderModelList.getJSONObject(i).get("OrdererModel"));
			Map ordererInfo = JSON.parseObject(ordererModel.toString(), Map.class);
			orderInfo.putAll(ordererInfo);

			JSONObject deliveryModel = JSONObject.fromObject(orderModelList.getJSONObject(i).get("DeliveryModel"));
			Map deliveryInfo = JSON.parseObject(deliveryModel.toString(), Map.class);
			orderInfo.putAll(deliveryInfo);

			JSONObject settlementModel = JSONObject.fromObject(orderModelList.getJSONObject(i).get("SettlementModel"));
			Map settlementInfo = JSON.parseObject(settlementModel.toString(), Map.class);
			orderInfo.putAll(settlementInfo);

//			JSONObject senderModel = JSONObject.fromObject(orderModelList.getJSONObject(i).get("SettlementModel"));
//			Map senderInfo = JSON.parseObject(senderModel.toString(), Map.class);
//			orderInfo.putAll(senderInfo);

			orderInfoList.add(orderInfo);
		}
		return orderInfoList;
	}
}
