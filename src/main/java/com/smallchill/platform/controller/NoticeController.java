package com.smallchill.platform.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smallchill.common.base.BaseController;
import com.smallchill.core.plugins.dao.Blade;
import com.smallchill.core.toolbox.Func;
import com.smallchill.core.toolbox.Record;
import com.smallchill.core.toolbox.ajax.AjaxResult;
import com.smallchill.core.toolbox.kit.JsonKit;
import com.smallchill.platform.model.Notice;

@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {
	private static String CODE = "notice";
	private static String PERFIX = "TB_TFW_TZGG";
	private static String DATA_SOURCE = "Notice.data";
	private static String LIST_SOURCE = "Notice.list";
	private static String BASE_PATH = "/platform/notice/";
	
	@RequestMapping(KEY_MAIN)
	public String index(ModelMap mm) {
		mm.put("code", CODE);
		return BASE_PATH + "notice.html";
	}

	@RequestMapping(KEY_ADD)
	public String add(ModelMap mm) {
		mm.put("code", CODE);
		return BASE_PATH + "notice_add.html";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(KEY_EDIT + "/{id}")
	public String edit(@PathVariable String id, ModelMap mm) {
		Map<String, Object> map = Blade.dao().selectSingle(DATA_SOURCE, Record.create().set("id", id), Map.class);
		mm.put("model", JsonKit.toJson(map));
		mm.put("id", id);
		mm.put("code", CODE);
		return BASE_PATH + "notice_edit.html";
	}

	@RequestMapping(KEY_VIEW + "/{id}")
	public String view(@PathVariable String id, ModelMap mm) {
		Notice notice = Blade.create(Notice.class).findById(id);
		//将javabean转化为rd
		Record rd = Record.parse(notice);
		//使用Func.getDictName方法从缓存中获取对应字典项的中文值
		rd.set("dic_f_it_lx", Func.getDictName(102, notice.getF_it_lx()));
		//将rd传回前台
		mm.put("model", JsonKit.toJson(rd));
		mm.put("id", id);
		mm.put("code", CODE);
		return BASE_PATH + "notice_view.html";
	}

	@ResponseBody
	@RequestMapping(KEY_LIST)
	public Object list() {
		return paginate(LIST_SOURCE);
	}

	@ResponseBody
	@RequestMapping(KEY_SAVE)
	public AjaxResult save() {
		Notice notice = mapping(PERFIX, Notice.class);
		boolean temp = Blade.create(Notice.class).save(notice);
		if (temp) {
			return success(SAVE_SUCCESS_MSG);
		} else {
			return error(SAVE_FAIL_MSG);
		}

	}

	@ResponseBody
	@RequestMapping(KEY_UPDATE)
	public AjaxResult update() {
		Notice notice = mapping(PERFIX, Notice.class);
		boolean temp = Blade.create(Notice.class).update(notice);
		if (temp) {
			return success(UPDATE_SUCCESS_MSG);
		} else {
			return error(UPDATE_FAIL_MSG);
		}
	}

	@ResponseBody
	@RequestMapping(KEY_REMOVE)
	public AjaxResult remove(@RequestParam String ids) {
		int cnt = Blade.create(Notice.class).deleteByIds(ids);
		if (cnt > 0) {
			return success(DEL_SUCCESS_MSG);
		} else {
			return error(DEL_FAIL_MSG);
		}
	}

}
