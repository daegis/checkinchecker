package cn.aegisa.project.dao.service.impl;

import cn.aegisa.project.dao.spi.ICommonDao;

/**
 * Created by simple on 2016/11/11.
 */
public class CommonServiceImpl extends DefaultServiceImpl {

    private ICommonDao commonDao;

    @Override
    public ICommonDao getCommonDao() {
        return commonDao;
    }

    @Override
    public void setCommonDao(ICommonDao commonDao) {
        this.commonDao = commonDao;
    }


}
