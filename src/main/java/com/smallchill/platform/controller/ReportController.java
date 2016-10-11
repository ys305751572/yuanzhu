package com.smallchill.platform.controller;

import com.smallchill.platform.model.Report;
import com.smallchill.platform.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smallchill.common.base.BaseController;
import com.smallchill.core.toolbox.ajax.AjaxResult;
import com.smallchill.core.toolbox.kit.JsonKit;

/**
 * Generated by yesong.
 * 2016-09-14 11:35:08
 */
@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {
    private static String CODE = "report";
    private static String PERFIX = "tb_report";
    private static String LIST_SOURCE = "Report.list";
    private static String BASE_PATH = "/platform/report/";

    @Autowired
    ReportService service;

    @RequestMapping(KEY_MAIN)
    public String index(ModelMap mm) {
        mm.put("code", CODE);
        return BASE_PATH + "report.html";
    }

    @RequestMapping(KEY_ADD)
    public String add(ModelMap mm) {
        mm.put("code", CODE);
        return BASE_PATH + "report_add.html";
    }

    @RequestMapping(KEY_EDIT + "/{id}")
    public String edit(@PathVariable String id, ModelMap mm) {
        Report report = service.findById(id);
        mm.put("model", JsonKit.toJson(report));
        mm.put("id", id);
        mm.put("code", CODE);
        return BASE_PATH + "report_edit.html";
    }

    @RequestMapping(KEY_VIEW + "/{id}")
    public String view(@PathVariable String id, ModelMap mm) {
        Report report = service.findById(id);
        mm.put("model", JsonKit.toJson(report));
        mm.put("id", id);
        mm.put("code", CODE);
        return BASE_PATH + "report_view.html";
    }

    @ResponseBody
    @RequestMapping(KEY_LIST)
    public Object list() {
        Object grid = paginate(LIST_SOURCE);
        return grid;
    }

    @ResponseBody
    @RequestMapping(KEY_SAVE)
    public AjaxResult save() {
        Report report = mapping(PERFIX, Report.class);
        boolean temp = service.save(report);
        if (temp) {
            return success(SAVE_SUCCESS_MSG);
        } else {
            return error(SAVE_FAIL_MSG);
        }
    }

    @ResponseBody
    @RequestMapping(KEY_UPDATE)
    public AjaxResult update() {
        Report report = mapping(PERFIX, Report.class);
        boolean temp = service.update(report);
        if (temp) {
            return success(UPDATE_SUCCESS_MSG);
        } else {
            return error(UPDATE_FAIL_MSG);
        }
    }

    @ResponseBody
    @RequestMapping(KEY_REMOVE)
    public AjaxResult remove(@RequestParam String ids) {
        int cnt = service.deleteByIds(ids);
        if (cnt > 0) {
            return success(DEL_SUCCESS_MSG);
        } else {
            return error(DEL_FAIL_MSG);
        }
    }
}
