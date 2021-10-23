package com.hotelManager.utils;

import com.hotelManager.constants.enums.HotelManagerResponseCode;
import com.hotelManager.exceptions.HotelManagerException;

import java.lang.reflect.InvocationTargetException;

public class HotelManagerUtils {

    /**
     * create error response
     *
     * @param exceptionClass exceptionClass
     * @param responseCode responseCode
     * @param <E> class
     * @return HotelManagerException
     */
    public static <E> E createResponse(Class<E> exceptionClass, HotelManagerResponseCode responseCode) {
        try {
            if (HotelManagerException.class.isAssignableFrom(exceptionClass)) {
                return exceptionClass.getConstructor(int.class, String.class)
                        .newInstance(responseCode.getResponseCode(), responseCode.getMessage());
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * throw HotelManager Exception
     *
     * @param exceptionClass
     * @param responseCode
     * @param <E> generic class
     * @throws HotelManagerException
     */
    public static <E> void throwException(Class<E> exceptionClass, HotelManagerResponseCode responseCode) throws HotelManagerException {
        throw (HotelManagerException) createResponse(exceptionClass, responseCode);
    }
}
