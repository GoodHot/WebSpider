package com.goodHot.fun.service;

import com.goodHot.fun.service.callback.PhantomCallback;
import org.openqa.selenium.Cookie;

import java.util.Set;

public interface PhantomService {
    String getWebCookie(String url);

    void get(String url, PhantomCallback run);

    String packCookie(Set<Cookie> cookies);

}
