package net.sinyoo.center.core.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 15/12/17
 * Time: 上午10:56
 */
public class MyBeanUtils<S, T> {
    public List<T> copyList(List<S> sources, Class<T> target) {
        if (sources == null) {
            return null;
        }
        try {
            List<T> infoTos = new ArrayList<>();
            T infoTo = null;
            for (S info : sources) {

                infoTo = target.newInstance();
                BeanUtils.copyProperties(info, infoTo);
                infoTos.add(infoTo);
            }
            return infoTos;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T copyBean(S sources, Class<T> target) {
        if (sources == null) {
            return null;
        }
        T infoTo = null;
        try {
            infoTo = target.newInstance();
            BeanUtils.copyProperties(sources, infoTo);
            return infoTo;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
