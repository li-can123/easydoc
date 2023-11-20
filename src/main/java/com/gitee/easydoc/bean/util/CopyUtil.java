package com.gitee.easydoc.bean.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.gitee.easyopen.exception.ApiException;
import com.gitee.fastmybatis.core.PageInfo;
import com.gitee.fastmybatis.core.util.MyBeanUtil;

public class CopyUtil {

	/**
	 * 拷贝全部属性
	 * 
	 * @param param
	 * @param clazz
	 * @return
	 */
	public static <T> T copyAll(Object param, Class<T> clazz) {
		if(param == null) {
			return null;
		}
		try {
			T obj = clazz.newInstance();
			BeanUtils.copyProperties(param, obj);
			return obj;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 拷贝全部属性
	 * @param clazz 目标类
	 * @param obj 源数据
	 * @return
	 */
	public static <T> T copyAll(Class<T> clazz, Object... obj) {
		try {
			T info = clazz.newInstance();
			for (Object object : obj) {
				BeanUtils.copyProperties(object, info);
			}
			return info;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 忽略null属性
	 * 
	 * @param param
	 * @param clazz
	 * @return
	 */
	public static <T> T copyIgnoreNull(Object param, Class<T> clazz) {
		try {
			T obj = clazz.newInstance();
			MyBeanUtil.copyProperties(param, obj);
			return obj;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
     * 拷贝一个List,通过属性复制产生一个新的List,新的List存放newListElementCls类型的类
     * 
     * @param oldList
     * @param clazz 新的List元素class类型
     * @return
     */
    public static <T> List<T> copyList(List<?> oldList, Class<T> clazz) {
        if (CollectionUtils.isEmpty(oldList)) {
            return Collections.emptyList();
        }
        List<T> ret = new ArrayList<>(oldList.size());
        for (Object object : oldList) {
            try {
                T addr = clazz.newInstance();
                BeanUtils.copyProperties(object, addr);
                ret.add(addr);
            } catch (Exception e) {
                throw new ApiException("转换错误");
            }
        }
        return ret;
    }

    /**
     * 拷贝一个PageInfo,替换List里面的元素
     * 
     * @param pageInfo
     * @param retPojoClass
     *            List里面的新元素
     * @return 返回新的PageInfo
     */
    public static <T> PageInfo<T> copyPageInfo(PageInfo<?> pageInfo, Class<T> retPojoClass) {
        List<?> list = pageInfo.getList();
        List<T> retList = new ArrayList<>(list.size());
        for (Object obj : list) {
            try {
                T resp = retPojoClass.newInstance();
                BeanUtils.copyProperties(obj, resp);
                retList.add(resp);
            } catch (Exception e) {
                throw new ApiException("拷贝失败");
            }
        }

        PageInfo<T> retPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, retPageInfo);
        retPageInfo.setList(retList);

        return retPageInfo;
    }
    
    /**
     * 拷贝一个PageInfo,替换List里面的元素
     * 
     * @param pageInfo
     * @param retPojoClass
     *            List里面的新元素
     * @return 返回新的PageInfo
     */
    public static <T> PageInfo<T> copyPageInfo(PageInfo<?> pageInfo, Class<T> retPojoClass, CopyFilter<T> copyFilter) {
        List<?> list = pageInfo.getList();
        List<T> retList = new ArrayList<>(list.size());
        for (Object obj : list) {
            try {
                T resp = retPojoClass.newInstance();
                BeanUtils.copyProperties(obj, resp);
                copyFilter.doFilter(obj, resp);
                retList.add(resp);
            } catch (Exception e) {
                throw new ApiException("拷贝失败");
            }
        }

        PageInfo<T> retPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, retPageInfo);
        retPageInfo.setList(retList);

        return retPageInfo;
    }
    
    public static interface CopyFilter<T> {
        /**
         * @param oldPojo 老对象
         * @param t 新对象
         */
        void doFilter(Object oldPojo,T t);
    }
}