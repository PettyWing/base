package com.komect.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by xieyusheng on 2018/2/13.
 */

public class SPUtil {
    private final SharedPreferences sp;

    /**
     * @param context 上下文
     */
    public SPUtil(@NonNull Context context) {
        sp = context.getSharedPreferences(
                context.getPackageName() + "_preferences", Context.MODE_PRIVATE);
    }

    /**
     * @param context 上下文
     * @param file    文件名称
     * @param mode    加载模式,例如Context.MODE_PRIVATE
     */
    public SPUtil(@NonNull Context context, @NonNull String file,
                  @NonNull int mode) {
        sp = context.getSharedPreferences(file, mode);
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public int getInt(final String key, final int defaultValue) {
        int resultInt;
        try {
            String result = sp.getString(key, "");
            resultInt = Integer.parseInt(AESUtil.decode(result));
        } catch (ClassCastException e) {
            try {
                // 由于之前存储的是int，需要通过getInt方法获取出来
                resultInt = sp.getInt(key, defaultValue);
                // 将获取出来的int通过加密的方式在存储一次
                putInt(key, resultInt);
            } catch (Exception e1) {
                e.printStackTrace();
                return defaultValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
        return resultInt;
    }

    /**
     * @param key
     * @param value
     */
    public void putInt(final String key, final int value) {
        SharedPreferences.Editor editor = sp.edit();
        // 对value 进行AES加密
        editor.putString(key, AESUtil.encode(String.valueOf(value)));
        editor.commit();
    }

    /**
     * @param key
     * @param value
     */
    public void putInt(final String key, final Integer value) {
        if (null != value) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(key, value);
            editor.commit();
        }
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public long getLong(final String key, final long defaultValue) {
        long resultLong;
        try {
            String result = sp.getString(key, "");
            resultLong = Long.parseLong(AESUtil.decode(result));
        } catch (ClassCastException e) {
            try {
                // 由于之前存储的是int，需要通过getInt方法获取出来
                resultLong = sp.getLong(key, defaultValue);
                // 将获取出来的int通过加密的方式在存储一次
                putLong(key, resultLong);
            } catch (Exception e1) {
                e.printStackTrace();
                return defaultValue;
            }
        } catch (Exception e) {
            return defaultValue;
        }
        return resultLong;
    }

    /**
     * @param key
     * @param value
     */
    public void putLong(final String key, final long value) {
        SharedPreferences.Editor editor = sp.edit();
        // 对value 进行AES加密
        editor.putString(key, AESUtil.encode(String.valueOf(value)));

        editor.commit();
    }

    /**
     * @param key
     * @param value
     */
    public void putLong(final String key, final Long value) {
        if (null != value) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putLong(key, value);
            editor.commit();
        }
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public float getFloat(final String key, final float defaultValue) {
        float resultFloat;
        try {
            String result = sp.getString(key, "");
            resultFloat = Float.parseFloat(AESUtil.decode(result));
        } catch (ClassCastException e) {
            try {
                // 由于之前存储的是int，需要通过getInt方法获取出来
                resultFloat = sp.getFloat(key, defaultValue);
                // 将获取出来的int通过加密的方式在存储一次
                putFloat(key, resultFloat);
            } catch (Exception e1) {
                e.printStackTrace();
                return defaultValue;
            }
        } catch (Exception e) {
            return defaultValue;
        }
        return resultFloat;
    }

    /**
     * @param key
     * @param value
     */
    public void putFloat(final String key, final float value) {
        SharedPreferences.Editor editor = sp.edit();
        // 对value 进行AES加密
        editor.putString(key, AESUtil.encode(String.valueOf(value)));
        editor.commit();
    }

    /**
     * @param key
     * @param value
     */
    public void putFloat(final String key, final Float value) {
        if (null != value) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putFloat(key, value);
            editor.commit();
        }
    }

    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(final String key, final String defaultValue) {
        String result = sp.getString(key, "");
        String resultString;
        // 如果不存在该key，直接返回defaultValue
        if (!sp.contains(key)) {
            return defaultValue;
        }
        // 其他按照没有加密过的处理
        try {
            resultString = AESUtil.decode(result);
        } catch (Exception e) {
            putString(key, result);
            return result;
        }
        return resultString;
    }

    /**
     * @param key
     * @param value
     */
    public void putString(final String key, final String value) {
        SharedPreferences.Editor editor = sp.edit();
        // 对value 进行AES加密
        editor.putString(key, AESUtil.encode(String.valueOf(value)));
        editor.commit();
    }


    /**
     * @param key
     * @param defaultValue
     * @return
     */
    public boolean getBoolean(final String key, final boolean defaultValue) {
        boolean resultBolean;
        try {
            String result = sp.getString(key, "");
            resultBolean = Boolean.getBoolean(AESUtil.decode(result));
        } catch (ClassCastException e) {
            try {
                // 由于之前存储的是int，需要通过getInt方法获取出来
                resultBolean = sp.getBoolean(key, defaultValue);
                // 将获取出来的int通过加密的方式在存储一次
                putBoolean(key, resultBolean);
            } catch (Exception e1) {
                e.printStackTrace();
                return defaultValue;
            }
        } catch (Exception e) {
            return defaultValue;
        }
        return resultBolean;
    }

    /**
     * @param key
     * @param value
     */
    public void putBoolean(final String key, final boolean value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * @param key
     * @param value
     */
    public void putBoolean(final String key, final Boolean value) {
        if (null != value) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

    /**
     * 删除对应的键值对
     *
     * @param key 待删除的key
     */
    public void remove(String key) {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 查询某个key是否已经存在
     */
    public boolean contains(String key) {
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }


    /**
     * 存放实体类以及任意类型
     *
     * @param context 上下文对象
     * @param key
     * @param obj
     */
    public  void putObject( String key, Object obj) {
        if (obj instanceof Serializable) {// obj必须实现Serializable接口，否则会出问题
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                String string64 = new String(Base64.encode(baos.toByteArray(), 0));
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(key, string64).apply();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            throw new IllegalArgumentException("the obj must implement Serializble");
        }

    }

    public  Object getObject(String key) {
        Object obj = null;
        try {
            String base64 = sp.getString(key, "");
            if (base64.equals("")) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
