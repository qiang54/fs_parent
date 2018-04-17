package com.fs.action.sysadmin;

import com.fs.action.BaseAction;
import com.fs.domain.Dept;
import com.fs.service.DeptService;
import com.fs.utils.Page;
import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * 部门管理
 */
public class DeptAction extends BaseAction implements ModelDriven<Dept> {

    private Dept model = new Dept();
    @Override
    public Dept getModel() {
        return model;
    }

    private Page page = new Page();

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    //注入DeptService
    private DeptService deptService;

    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    /**
     * 分页查询
     */
    public String list() throws Exception{

        page = deptService.findPage("from Dept", page, Dept.class, null);

        //设置url
        page.setUrl("deptAction_list");

        //放入值栈的栈顶
        super.push(page);
        return "list";
    }

    /**
     * 查看详情
     */
    public String toview() throws Exception{

        Dept dept = deptService.get(Dept.class, model.getId());
        super.push(dept);
        return "toView";
    }

    /**
     * 跳入增加页面
     */
    public String tocreate() throws Exception{

        List<Dept> depts = deptService.find("from Dept where state = 1", Dept.class, null);
        super.put("deptList",depts);
        return "toCreate";
    }

    /**
     * 新增保存
     */
    public String insert() throws Exception{

        deptService.saveOrUpdate(model); // saveorUpdate /save :有无oid
        return "alist";
    }

    /**
     * 修改
     */
    public String toupdate() throws Exception{

        Dept dept = deptService.get(Dept.class, model.getId());
        super.push(dept);

        List<Dept> depts = deptService.find("from Dept where state = 1", Dept.class, null);
        super.put("deptList",depts);

        return "toUpdate";
    }

    /**
     * 修改保存
     */
    public String update() throws Exception{

        Dept dept = deptService.get(Dept.class, model.getId());

        dept.setState(1);
        dept.setParent(model.getParent());
        dept.setDeptName(model.getDeptName());

        deptService.saveOrUpdate(dept);
        return "alist";
    }

    /**
     * 删除
     * 1.父部门删除时，子部门也被删除
     * 2.批量删除时，具有同名框的一组数据怎样封装
     *    model对id(String)类型的数据自动封装原则：(1, 2, 3, ...) 逗号，加空格！！！
     */

    public String delete() throws Exception{

        //1.获取要删除的id
        String[] ids = model.getId().split(", ");
        //2.删除
        deptService.delete(Dept.class, ids);
        return "alist";
    }
}
