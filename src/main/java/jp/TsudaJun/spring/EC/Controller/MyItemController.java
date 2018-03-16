package jp.TsudaJun.spring.EC.Controller;

import java.security.Principal;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.EC.DAO.ItemAttributeDao;
import jp.TsudaJun.spring.EC.DAO.ItemDao;
import jp.TsudaJun.spring.EC.model.Item;
import jp.TsudaJun.spring.EC.model.ItemAttribute;

@Controller
public class MyItemController {
	
	@Autowired
	ItemDao iDao;
	
	@Autowired
	ItemAttributeDao iaDao;
	
	@Value("${img.accessPath}")
	private String imgPath;
	
	@RequestMapping(value ="/myitem", method=RequestMethod.GET)
	public ModelAndView show(
			Principal principal,
			ModelAndView mav){
		
		mav.setViewName("myitem");
		mav.addObject("msg", "登録した商品");
		List<Item> items = iDao.getItemByUsreid(principal.getName());
		List<ItemAttribute> attributes = iaDao.getAllAttributes();
		if(items.size() == 0) {
			mav.addObject("check", true);
		}
		mav.addObject("items", items);
		mav.addObject("attributes", attributes);
		mav.addObject("imgPath", imgPath);
		
		return mav;
	}
	
	@RequestMapping(value ="/myitem", method=RequestMethod.POST)
	public ModelAndView search(
			ModelAndView mav,
			@RequestParam("word") String word,
			@RequestParam("itemattribute") String attributeno) {
		List<Item> items = null;
		if(attributeno.equals("カテゴリーを選択してください")) {
			items = iDao.getItemsByWord(word);
		}else{
			items = iDao.getItemsByWordAndAttribute(word, Integer.parseInt(attributeno));
		}
		mav.addObject("items", items);
		List<ItemAttribute> attributes = iaDao.getAllAttributes();
		mav.addObject("attributes", attributes);
		mav.addObject("imgPath", imgPath);
		mav.addObject("msg", "登録した商品");
		
		return mav;
	}
	
	@RequestMapping(value = "/myitemlist/{attributeno}", method=RequestMethod.POST,
			produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String myItemList(
			@PathVariable("attributeno") int attributeno)
			throws JSONException{
		
		List<Item> items = iDao.getItemsByAttributeno(attributeno);
		JSONArray status = new JSONArray();
		
		for(Item item : items) {
			JSONObject data = new JSONObject();
			data.put("itemid",item.getItemid());
			data.put("itemname", item.getItemname());
			data.put("imgPath", imgPath);
			status.put(data);
		}

	    return status.toString();
		
	} 

}
