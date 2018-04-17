package com.fs.service;

import com.fs.dao.BaseDao;
import com.fs.domain.Dept;
import com.fs.utils.Page;
import com.fs.utils.UtilFuns;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class DeptServiceImpl implements DeptService {

    //注入BaseDao,提供setter方法。
    private BaseDao baseDao;

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public List<Dept> find(String hql, Class<Dept> entityClass, Object[] params) {
        return baseDao.find(hql, entityClass,params);
    }

    @Override
    public Dept get(Class<Dept> entityClass, Serializable id) {
        return baseDao.get(entityClass, id);
    }

    @Override
    public Page<Dept> findPage(String hql, Page<Dept> page, Class<Dept> entityClass, Object[] params) {
        return baseDao.findPage(hql, page, entityClass,params);
    }

    @Override
    public void saveOrUpdate(Dept entity) {

        if(UtilFuns.isEmpty(entity.getId())){
            entity.setState(1);// 部门状态 1启用 0停用 ，默认启用
        }
        baseDao.saveOrUpdate(entity);
    }

    @Override
    public void saveOrUpdateAll(Collection<Dept> entitys) {
        baseDao.saveOrUpdate(entitys);
    }

    @Override
    public void deleteById(Class<Dept> entityClass, Serializable id) {

        //1.查看删除部门是否为父部门，
        String hql = "from Dept where parent.id = ?";
        List<Dept> depts = baseDao.find(hql, Dept.class, new Object[]{id});
        if( depts != null && depts.size() > 0){
            for (Dept dept: depts
                 ) {
                deleteById( Dept .class, dept.getId());
            }
        }
        baseDao.deleteById(entityClass, id);
    }

    @Override
    public void delete(Class<Dept> entityClass, Serializable[] ids) {

        for (Serializable id: ids
             ) {
            deleteById(Dept.class, id);
        }
    }
}
