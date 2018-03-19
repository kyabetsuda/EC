package jp.TsudaJun.spring.EC.Controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.EC.DAO.ItemAttributeDao;
import jp.TsudaJun.spring.EC.DAO.ItemDao;
import jp.TsudaJun.spring.EC.model.Item;
import jp.TsudaJun.spring.EC.model.ItemAttribute;

@Controller
public class IndexController {
	
	@Autowired
	ItemDao iDao;
	
	@Autowired
	ItemAttributeDao iaDao;
	
	@Value("${img.accessPath}")
	private String imgPath;
	
	@RequestMapping(value ={"/", "/index"}, method=RequestMethod.GET)
	public ModelAndView show(ModelAndView mav){
		
		List<Item> items = iDao.getAllItemsByDesc();
		List<ItemAttribute> attributes = iaDao.getAllAttributes();
		mav.addObject("items", items);
		mav.addObject("attributes", attributes);
		mav.setViewName("index");
		return mav;
		
	}
	
	@RequestMapping(value ={"/index"}, method=RequestMethod.POST)
	public ModelAndView search(ModelAndView mav){
		
		System.out.println("a");
		return mav;
		
	}
	
	@RequestMapping(value = "/getimg", method=RequestMethod.POST,
			produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String roomList()
			throws JSONException{
		
		List<Item> items = iDao.getAllItemsByDesc();
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
