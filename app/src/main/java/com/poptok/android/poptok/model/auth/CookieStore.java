package com.poptok.android.poptok.model.auth;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by BIT on 2018-01-22.
 */

@EBean(scope = EBean.Scope.Singleton)
public class CookieStore {
    private static List<String> cookies = null;

    public void setCookies(List<String> cookies) {
        if(cookies != null) {
            this.cookies = cookies;
        }
    }

    public List<String> getCookies() {
        return this.cookies;
    }

    /**
     * Resets the cookie storage.
     */
    public void clear() {
        cookies = null;
    }

    /**
     * @return true if the cookie storage is not empty, otherwise false is returned.
     */
    public boolean hasCookies() {
        return cookies != null &&
                !cookies.isEmpty();
    }

    /**
     * @param name
     * @return true, if a cookie with the given name exists, otherwise false is returned.
     */
    public boolean hasCookieWithName(String name) {
        for (String cookie : cookies) {
            if (cookie.contains(name))
                return true;
        }

        return false;
    }
}
