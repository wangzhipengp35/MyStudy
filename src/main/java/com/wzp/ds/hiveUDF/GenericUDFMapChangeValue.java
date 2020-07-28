package com.wzp.ds.hiveUDF;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

/**
 * @创建人 wangzhipeng
 * @创建时间 2020/5/22
 * @描述
 */
public class GenericUDFMapChangeValue extends GenericUDF {

    public ObjectInspector initialize(ObjectInspector[] objectInspectors) throws UDFArgumentException {
        return null;
    }

    public Object evaluate(DeferredObject[] deferredObjects) throws HiveException {
        return null;
    }

    public String getDisplayString(String[] strings) {
        return null;
    }
}
