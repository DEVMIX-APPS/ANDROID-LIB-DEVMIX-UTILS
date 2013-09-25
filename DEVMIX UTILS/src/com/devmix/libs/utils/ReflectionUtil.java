package com.devmix.libs.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public final class ReflectionUtil
{
    public static final String TAG = ReflectionUtil.class.getSimpleName();

    public static final String ValorDoObjeto(Class<?> mClass, Object mInstance, String mNome)
    {
        if (mClass == null || mInstance == null)
            return null;

        final Field[] mFields = mClass.getDeclaredFields();
        for (final Field mField : mFields)
        {
            mField.setAccessible(true);
            if (!mField.getName().equalsIgnoreCase(mNome))
                continue;

            try
            {
                return mField.get(mInstance).toString();
            }
            catch (Exception ignored)
            {
            }
        }

        return null;
    }

    public static final String DumpClass(Class<?> mClass, Object mInstance)
    {
        if (mClass == null || mInstance == null) return null;

        String mStr = mClass.getSimpleName() + "\n\n";

        mStr += "FIELDS\n\n";

        final Field[] mFields = mClass.getDeclaredFields();

        for (final Field mField : mFields)
        {
            mField.setAccessible(true);

            mStr += mField.getName() + " (" + mField.getType() + ") = ";

            try
            {
                mStr += mField.get(mInstance).toString();
            }
            catch (Exception e)
            {
                mStr += "null";
            }

            mStr += "\n";
        }

        mStr += "METHODS\\nn";

        // Dump all methods.

        final Method[] mMethods = mClass.getMethods();

        for (final Method mMethod : mMethods)
        {
            mMethod.setAccessible(true);

            mStr += mMethod.getReturnType() + " " + mMethod.getName() + "() = ";

            try
            {
                final Object mRet = mMethod.invoke(mInstance);
                mStr += (mRet == null) ? "null" : mMethod.invoke(mInstance).toString();
            }
            catch (Exception e)
            {
                mStr += "null";
            }

            mStr += "\n";
        }

        return mStr;
    }

    /**
     * @return A string containing the values of all static {@link Field}s.
     */
    public static final String DumpStaticFields(Class<?> mClass, Object mInstance)
    {
        if (mClass == null || mInstance == null) return null;

        String mStr = mClass.getSimpleName() + "\n\n";

        mStr += "STATIC FIELDS\n\n";

        final Field[] mFields = mClass.getDeclaredFields();

        for (final Field mField : mFields)
        {
            if (ReflectionUtil.IsStatic(mField))
            {
                mField.setAccessible(true);

                mStr += mField.getName() + " (" + mField.getType() + ") = ";

                try
                {
                    mStr += mField.get(mInstance).toString();
                }
                catch (Exception e)
                {
                    mStr += "null";
                }

                mStr += "\n";
            }
        }

        return mStr;
    }

    /**
     * @return True if the {@link Field} is static.
     */
    public final static boolean IsStatic(Field field)
    {
        final int modifiers = field.getModifiers();
        return (Modifier.isStatic(modifiers));
    }
}